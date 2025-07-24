package tydino.everbloom.entity.custom.dinosaurs.quadrepeds;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.dinosaurs.TamableDinosaurEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.goals.TamableDinosaurFollowingGoal;
import tydino.everbloom.entity.custom.dinosaurs.goals.TamableDinosaurLayEggGoal;
import tydino.everbloom.entity.custom.dinosaurs.goals.TamableDinosaurMateGoal;
import tydino.everbloom.item.ModItems;

public class ParasaurolophusEntity extends TamableDinosaurEntity {

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(ParasaurolophusEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ParasaurolophusEntity(EntityType<? extends ParasaurolophusEntity> entityType, World world) {
        super(entityType, world, ModItems.SILVER_SCARAB);///switch to gold for cretaceous
        this.setTamed(false, false);
    }
    //animation code
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState eatAnimationState = new AnimationState();

    boolean isIdle(){
        return !isSitting() && !isInDanger();
    }

    public final AnimationState runAnimationState = new AnimationState();
    private int runAnimationTimeout = 0;

    public final AnimationState sitAnimationState = new AnimationState();
    private int sitAnimationTimeout = 0;
    boolean properlySitting;
    public final AnimationState sittingdownAnimationState = new AnimationState();
    private int sittingAnimationTimeout = 0;
    boolean isSittingDown;
    public final AnimationState standingupAnimationState = new AnimationState();
    private int standingAnimationTimeout = 0;
    boolean isStandingUp;

    private void setupAnimationStates() {
        if (isIdle()) {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 160;//animation time in seconds *20
                this.idleAnimationState.start(this.age);
                this.runAnimationState.stop();
            } else {
                --this.idleAnimationTimeout;
            }
        }
        if (isInDanger()) {
            if (this.runAnimationTimeout <= 0) {
                this.runAnimationTimeout = 20;//animation time in seconds *20
                this.runAnimationState.start(this.age);
                this.idleAnimationState.stop();
                this.setSitting(false);
                this.setSittingDown(false);
            } else {
                --this.runAnimationTimeout;
            }
        }
        setUpSitting();
        if (properlySitting) {
            if (this.sitAnimationTimeout <= 0) {
                this.sitAnimationTimeout = 40;//animation time in seconds *20
                this.sitAnimationState.start(this.age);
                this.sittingdownAnimationState.stop();
            } else {
                --this.sitAnimationTimeout;
            }
        }
        if (isSittingDown && isSittingDownNow()) {
            if (this.sittingAnimationTimeout <= 0) {
                this.sittingAnimationTimeout = 21;//animation time in seconds *20
                this.sittingdownAnimationState.start(this.age);
                this.idleAnimationState.stop();
            } else {
                --this.sittingAnimationTimeout;
            }
        }else{
            this.sittingdownAnimationState.stop();
            sittingAnimationTimeout = 0;
        }
        if (isStandingUp) {
            if (this.standingAnimationTimeout <= 0) {
                this.standingAnimationTimeout = 21;//animation time in seconds *20
                this.standingupAnimationState.start(this.age);
                this.sitAnimationState.stop();
                this.idleAnimationState.stop();
            } else {
                --this.standingAnimationTimeout;
            }
        }

    }

    void setUpSitting(){
        if(!properlySitting && !isSittingDown && !isStandingUp && isSittingDownNow()){
            isSittingDown = true;
            sittingAnimationTimeout = 0;
        } else if(!properlySitting && isSittingDown && !isStandingUp && isSittingDownNow()){
            if(sittingAnimationTimeout == 1){
                sittingAnimationTimeout = 0;
                sitAnimationTimeout = 0;
                isSittingDown = false;
                properlySitting = true;
            }
        }else if(properlySitting && !isSittingDown && !isStandingUp && !isSittingDownNow()){
            standingAnimationTimeout = 0;
            sitAnimationTimeout = 0;
            properlySitting = false;
            isStandingUp = true;
        }

        if(isStandingUp && standingAnimationTimeout == 1){
            isStandingUp = false;
            properlySitting = false;
            isSittingDown = false;
            standingAnimationTimeout =0;
            sitAnimationTimeout = 0;
            sittingAnimationTimeout = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    protected boolean isInDanger() {
        return hurtTime > 0;
    }

    //operation code

    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            Items.ACACIA_LEAVES, Items.AZALEA_LEAVES, Items.OAK_LEAVES, Items.BIRCH_LEAVES, Items.DARK_OAK_LEAVES, Items.CHERRY_LEAVES, Items.FLOWERING_AZALEA_LEAVES, Items.PALE_OAK_LEAVES, Items.MANGROVE_LEAVES, Items.SPRUCE_LEAVES, Items.JUNGLE_LEAVES, Items.WHEAT, Items.BEETROOT, Items.POTATO, Items.BAKED_POTATO, Items.BREAD
    );

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new TamableDinosaurFollowingGoal(this, (double)1.0F, 10.0F, 2.0F));
        this.goalSelector.add(3, new TamableDinosaurMateGoal(this, 1.0F));
        this.goalSelector.add(3, new TamableDinosaurLayEggGoal(this, 1.0F, ModBlocks.PARASAUROLOPHUS_EGG, 400));//needs done
        this.goalSelector.add(4, new TemptGoal(this, 1.05f, BREEDING_INGREDIENT, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, (double)1.0F));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

    }

    @Nullable
    public ParasaurolophusEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return ModEntities.PARASAUROLOPHUS.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public static DefaultAttributeContainer.Builder createParasaurolophusAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 100)
                .add(EntityAttributes.MOVEMENT_SPEED, .3f)
                .add(EntityAttributes.TEMPT_RANGE, 20);

    }

    //variant
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    public ParasaurolophusVariant getVariant() {
        return ParasaurolophusVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(ParasaurolophusVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        ParasaurolophusVariant variant = Util.getRandom(ParasaurolophusVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
