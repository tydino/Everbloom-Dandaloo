package tydino.everbloom.entity.dinosaurs.biped.hypsilophodon;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.dinosaurs.DinosaurBlocks;
import tydino.everbloom.entity.dinosaurs.DinosaurEntities;
import tydino.everbloom.entity.dinosaurs.DinosaurItems;
import tydino.everbloom.entity.dinosaurs.TamableDinosaurEntity;
import tydino.everbloom.entity.dinosaurs.goals.TamableDinosaurFollowingGoal;
import tydino.everbloom.entity.dinosaurs.goals.TamableDinosaurLayEggGoal;
import tydino.everbloom.entity.dinosaurs.goals.TamableDinosaurMateGoal;

public class HypsilophodonEntity extends TamableDinosaurEntity {

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(HypsilophodonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public HypsilophodonEntity(EntityType<? extends HypsilophodonEntity> entityType, World world) {
        super(entityType, world, DinosaurItems.SILVER_SCARAB);///set to gold as it is from cretaceous
        this.setTamed(false, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 10.0F);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, 15.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, 10.0F);
    }
    //animation code
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

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

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new TamableDinosaurFollowingGoal(this, (double)1.0F, 10.0F, 2.0F));
        this.goalSelector.add(3, new TamableDinosaurMateGoal(this, 1.0F));
        this.goalSelector.add(3, new TamableDinosaurLayEggGoal(this, 1.0F, DinosaurBlocks.HYPSILOPHODON_EGG, 400));
        this.goalSelector.add(4, new TemptGoal(this, 1.05f, HERBIVORE, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, (double)1.0F));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

    }

    @Nullable
    public HypsilophodonEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return DinosaurEntities.HYPSILOPHODON.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return HERBIVORE.test(stack);
    }

    public static DefaultAttributeContainer.Builder createHypsilophodonAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20)
                .add(EntityAttributes.MOVEMENT_SPEED, .2f)
                .add(EntityAttributes.TEMPT_RANGE, 15);

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

    public HypsilophodonVariant getVariant() {
        return HypsilophodonVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(HypsilophodonVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        HypsilophodonVariant variant = Util.getRandom(HypsilophodonVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
