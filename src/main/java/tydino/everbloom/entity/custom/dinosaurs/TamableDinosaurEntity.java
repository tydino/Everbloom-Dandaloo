package tydino.everbloom.entity.custom.dinosaurs;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.item.ModItems;

public class TamableDinosaurEntity extends TameableEntity {
    public static final TrackedData<Boolean> HAS_EGG =
            DataTracker.registerData(TamableDinosaurEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> EggLaying =
            DataTracker.registerData(TamableDinosaurEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(TamableDinosaurEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int eggLayingCounter;

    public Item Scarab;

    protected TamableDinosaurEntity(EntityType<? extends TameableEntity> entityType, World world, Item scarab) {
        super(entityType, world);
        this.Scarab = scarab;
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTamed()) {
            if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                this.eat(player, hand, itemStack);
                FoodComponent foodComponent = (FoodComponent)itemStack.get(DataComponentTypes.FOOD);
                float f = foodComponent != null ? (float)foodComponent.nutrition() : 1.0F;
                this.heal(2.0F * f);
                return ActionResult.SUCCESS;
            }else {
                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted() && this.isOwner(player)) {
                    this.setSitting(!isSittingDownNow());
                    this.setSittingDown(!isSittingDownNow());
                    this.jumping = false;
                    this.navigation.stop();
                    return ActionResult.SUCCESS.noIncrementStat();
                } else {
                    return actionResult;
                }
            }
        }else if (!this.getWorld().isClient && itemStack.isOf(Scarab)) {//set to gold scarab as it comes from cretaceous
            itemStack.decrementUnlessCreative(1, player);
            this.tryTame(player);
            return ActionResult.SUCCESS_SERVER;
        }
        return super.interactMob(player, hand);
    }

    private void tryTame(PlayerEntity player) {
        if (this.random.nextInt(3) == 0) {
            this.setOwner(player);
            this.navigation.stop();
            this.setTarget((LivingEntity)null);
            this.setSitting(true);
            this.setSittingDown(true);
            this.getWorld().sendEntityStatus(this, (byte)7);
        } else {
            this.getWorld().sendEntityStatus(this, (byte)6);
        }

    }

    //variant
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasEgg(nbt.getBoolean("HasEgg"));
        this.setSittingDown(nbt.getBoolean("Sitting"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasEgg", this.hasEgg());
        nbt.putBoolean("Sitting", this.isSittingDownNow());
    }

    public void setSittingDown(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
    }

    public boolean isSittingDownNow() {
        return (Boolean)this.dataTracker.get(SITTING);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HAS_EGG, false);
        builder.add(EggLaying, false);
        builder.add(SITTING, false);
    }

    public boolean hasEgg() {
        return (Boolean)this.dataTracker.get(HAS_EGG);
    }

    public void setHasEgg(boolean hasEgg) {
        this.dataTracker.set(HAS_EGG, hasEgg);
    }

    public boolean isDiggingSand() {
        return (Boolean)this.dataTracker.get(EggLaying);
    }

    public void setDiggingSand(boolean diggingSand) {
        this.eggLayingCounter = diggingSand ? 1 : 0;
        this.dataTracker.set(EggLaying, diggingSand);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
