package tydino.everbloomcore.entity.bases;

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
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class EDTamableEntity extends TameableEntity {

    public boolean isEating;

    public ArrayList<Item> Food_Items;

    public boolean isFoodIngredient(ItemStack itemStack){
        for(Item ingredient : Food_Items){
            if(itemStack.getItem() == ingredient){
                return true;
            }
        }
        return false;
    }

    public Item TameItem;

    public int ChanceOfTame;

    public static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(EDTamableEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> FOLLOWING =
            DataTracker.registerData(EDTamableEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> WANDERING =
            DataTracker.registerData(EDTamableEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public EDTamableEntity(EntityType<? extends TameableEntity> entityType, World world, Item tameItem, ArrayList<Item> foodList, int chanceOfTame){
        super(entityType, world);
        this.TameItem = tameItem;
        this.Food_Items.addAll(foodList);
        this.ChanceOfTame = chanceOfTame;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTamed()) {
            if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                setEating(true);
                this.eat(player, hand, itemStack);
                FoodComponent foodComponent = (FoodComponent)itemStack.get(DataComponentTypes.FOOD);
                float f = foodComponent != null ? (float)foodComponent.nutrition() : 1.0F;
                this.heal(2.0F * f);
                return ActionResult.SUCCESS;
            }else {
                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted() && this.isOwner(player)) {
                    if(isFollowing()) {
                        this.setSitting(true);
                        this.setSittingDown(true);
                        this.setFollowing(false);
                        this.setWandering(false);
                        this.jumping = false;
                        this.navigation.stop();
                        player.sendMessage(Text.of("Sitting"), true);

                        return ActionResult.SUCCESS.noIncrementStat();
                    }else if(isSittingDownNow()){
                        this.setSitting(false);
                        this.setSittingDown(false);
                        this.setFollowing(false);
                        this.setWandering(true);
                        this.jumping = false;
                        this.navigation.stop();
                        player.sendMessage(Text.of("Wandering"), true);

                        return ActionResult.SUCCESS.noIncrementStat();
                    }else if(isWandering()){
                        this.setSitting(false);
                        this.setSittingDown(false);
                        this.setFollowing(true);
                        this.setWandering(false);
                        this.jumping = false;
                        this.navigation.stop();
                        player.sendMessage(Text.of("Following"), true);

                        return ActionResult.SUCCESS.noIncrementStat();
                    }
                }
                else {
                    return ActionResult.FAIL;
                }
            }
        }else if (!this.getWorld().isClient && itemStack.isOf(TameItem)) {
            itemStack.decrementUnlessCreative(1, player);
            this.tryTame(player);
            return ActionResult.SUCCESS_SERVER;
        }
        return super.interactMob(player, hand);
    }

    private void tryTame(PlayerEntity player) {
        if (this.random.nextInt(ChanceOfTame) == 0) {
            this.setOwner(player);
            this.navigation.stop();
            this.setTarget((LivingEntity) null);
            this.setSitting(true);
            this.setSittingDown(true);
            this.getWorld().sendEntityStatus(this, (byte) 7);
        } else {
            this.getWorld().sendEntityStatus(this, (byte) 6);
        }
    }


    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSittingDown(nbt.getBoolean("Sitting"));
        this.setWandering(nbt.getBoolean("Wandering"));
        this.setFollowing(nbt.getBoolean("Following"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sitting", this.isSittingDownNow());
        nbt.putBoolean("Wandering", this.isWandering());
        nbt.putBoolean("Following", this.isFollowing());
    }

    public void setSittingDown(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
    }

    public boolean isSittingDownNow() {
        return (Boolean)this.dataTracker.get(SITTING);
    }

    public void setWandering(boolean wandering){this.dataTracker.set(WANDERING, wandering);}

    public boolean isWandering(){return (Boolean)this.dataTracker.get(WANDERING);}

    public void setFollowing(boolean following){this.dataTracker.set(FOLLOWING, following);}

    public boolean isFollowing(){return (Boolean)this.dataTracker.get(FOLLOWING);}

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SITTING, false);
        builder.add(FOLLOWING, false);
        builder.add(WANDERING, true);
    }

    public boolean IsEating() {
        return this.isEating;
    }

    public void setEating(boolean eating){
        this.isEating = eating;
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
