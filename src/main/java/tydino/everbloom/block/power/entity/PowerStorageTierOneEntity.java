package tydino.everbloom.block.power.entity;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import tydino.everbloom.block.entity.ModBlockEntities;
import tydino.everbloom.util.BlockCheckers;
import tydino.everbloom.utility.TickableBlockEntity;

public class PowerStorageTierOneEntity extends BlockEntity implements TickableBlockEntity {
    public PowerStorageTierOneEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_STORAGE_TIER_ONE_BE, pos, state);
    }

    //power
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10_000, 100, 10) {
        @Override
        protected void onFinalCommit() {
            super.onFinalCommit();

            markDirty();
        }
    };

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("Energy", NbtElement.LONG_TYPE)) {
            this.energyStorage.amount = nbt.getLong("Energy");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putLong("Energy", this.energyStorage.amount);
    }

    public SimpleEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    public SimpleEnergyStorage getEnergyProvider(Direction direction) {
        return this.energyStorage;
    }

    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }

    //power transfer

    @Override
    public void tick() {
        if (this.world == null || this.world.isClient)
            return;

        /*if (energyStorage.amount < energyStorage.getCapacity()) {
            energyStorage.amount = MathHelper.clamp(energyStorage.amount + 1, 0, energyStorage.getCapacity());
            update();
        }*/

        for (Direction direction : Direction.values()) {
            EnergyStorage storage = EnergyStorage.SIDED.find(this.world, this.pos.offset(direction), direction.getOpposite());
            if (storage != null && storage.supportsInsertion()) {
                try (Transaction transaction = Transaction.openOuter()) {
                    long insertable;
                    try (Transaction simulateTransaction = transaction.openNested()) {
                        insertable = storage.insert(Long.MAX_VALUE, simulateTransaction);
                    }

                    long extracted = this.energyStorage.extract(insertable, transaction);
                    long inserted = storage.insert(extracted, transaction);
                    if (extracted == inserted)
                        transaction.commit();
                }
            }
        }

    }
}
