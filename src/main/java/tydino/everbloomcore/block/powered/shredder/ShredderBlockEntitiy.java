package tydino.everbloomcore.block.powered.shredder;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import tydino.everbloomcore.block.entity.EDCBlockEntities;
import tydino.everbloomcore.block.entity.ImplementedInventory;
import tydino.everbloomcore.recipe.EDCRecipes;
import tydino.everbloomcore.recipe.power.shredder.ShredderRecipe;
import tydino.everbloomcore.recipe.power.shredder.ShredderRecipeInput;
import tydino.everbloomcore.screen.machine.shredder.ShredderScreenHandler;

import java.util.Optional;

public class ShredderBlockEntitiy  extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    static final int INPUT_SLOT = 0;
    static final int OUTPUT_SLOT = 1;
    static final int INPUT_SLOT_TWO = 2;

    protected final PropertyDelegate propertyDelegate;
    int progress = 0;
    int maxProgress = 500;

    public ShredderBlockEntitiy(BlockPos pos, BlockState state){
        super(EDCBlockEntities.SHREDDER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> ShredderBlockEntitiy.this.progress;
                    case 1 -> ShredderBlockEntitiy.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: ShredderBlockEntitiy.this.progress = value;
                    case 1: ShredderBlockEntitiy.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.everbloom_core.shredder");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ShredderScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putLong("Energy", this.energyStorage.amount);
        nbt.putInt("shredder.progress", progress);
        nbt.putInt("shredder.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("shredder.progress");
        maxProgress = nbt.getInt("shredder.max_progress");
        if (nbt.contains("Energy", NbtElement.LONG_TYPE)) {
            this.energyStorage.amount = nbt.getLong("Energy");
        }
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state){
        if(hasRecipe() && hasPower()) {
            increaseCraftingProgress();
            extractEnergy();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }
    private void extractEnergy() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.energyStorage.extract(1, transaction);
            transaction.commit();
        }
    }

    private boolean hasPower() {
        return this.energyStorage.amount >= 10;
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 250;
    }

    private void craftItem() {
        Optional<RecipeEntry<ShredderRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output();
        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(INPUT_SLOT_TWO, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<ShredderRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<ShredderRecipe>> getCurrentRecipe() {
        return ((ServerWorld) this.getWorld()).getRecipeManager()
                .getFirstMatch(EDCRecipes.SHREDDER_TYPE, new ShredderRecipeInput(inventory.get(INPUT_SLOT), inventory.get(INPUT_SLOT_TWO)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    //power
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10_000, 100, 100) {
        @Override
        protected void onFinalCommit() {
            super.onFinalCommit();

            markDirty();
        }
    };

    public SimpleEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    public SimpleEnergyStorage getEnergyProvider(Direction direction) {
        return this.energyStorage;
    }
}
