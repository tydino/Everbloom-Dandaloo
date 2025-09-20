package tydino.everbloom.entity.dinosaurs.biped.archaeopteryx;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.dinosaurs.DinosaurBlocks;
import tydino.everbloom.entity.dinosaurs.DinosaurEntities;
import tydino.everbloom.entity.dinosaurs.DinosaurItems;
import tydino.everbloom.entity.dinosaurs.TamableDinosaurEntity;
import tydino.everbloom.entity.dinosaurs.goals.*;
import tydino.everbloom.entity.dinosaurs.insectoids.meganeura.MeganeuraEntity;

import java.util.EnumSet;
import java.util.UUID;

public class ArchaeopteryxEntity  extends TamableDinosaurEntity implements Angerable {

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ArchaeopteryxEntity(EntityType<? extends ArchaeopteryxEntity> entityType, World world) {
        super(entityType, world, DinosaurItems.SILVER_SCARAB);
        this.setTamed(false, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 10.0F);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, 5.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, 5.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, 20.0F);
    }

    //animation code
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    boolean isIdle(){
        return !isSitting() && !isInDanger() && !IsGliding();
    }

    public final AnimationState runAnimationState = new AnimationState();
    private int runAnimationTimeout = 0;

    public final AnimationState glidingAnimationState = new AnimationState();
    private int glidingAnimationTimeout = 0;

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
                this.idleAnimationTimeout = 80;//animation time in seconds *20
                this.idleAnimationState.start(this.age);
                this.runAnimationState.stop();
                this.glidingAnimationState.stop();
            } else {
                --this.idleAnimationTimeout;
            }
        }
        if (isInDanger()) {
            if (this.runAnimationTimeout <= 0) {
                this.runAnimationTimeout = 20;//animation time in seconds *20
                this.runAnimationState.start(this.age);
                this.idleAnimationState.stop();
                this.glidingAnimationState.stop();
                this.setSitting(false);
                this.setSittingDown(false);
            } else {
                --this.runAnimationTimeout;
            }
        }
        if (IsGliding()) {
            if (this.glidingAnimationTimeout <= 0) {
                this.glidingAnimationTimeout = 80;//animation time in seconds *20
                this.glidingAnimationState.start(this.age);
                this.idleAnimationState.stop();
                this.runAnimationState.stop();
                this.setSitting(false);
                this.setSittingDown(false);
            } else {
                --this.glidingAnimationTimeout;
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
            sittingdownAnimationState.stop();
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
        return hurtTime > 0 && !IsGliding();
    }

    public Boolean IsGliding() {
        return !this.isOnGround();
    };

    //operational code


    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        //nothing to stop fall damage
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        if (source.isOf(DamageTypes.FALL)) {
            return true; // Makes the mob invulnerable to fall damage
        }
        return super.isInvulnerableTo(world, source);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new FlyForwardWhenAirborneGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new TamableDinosaurMateGoal(this, 1.0F));
        this.goalSelector.add(5, new TamableDinosaurLayEggGoal(this, 1.0F, DinosaurBlocks.ARCHAEOPTERYX_EGG, 600));
        this.goalSelector.add(6, new MeleeAttackGoal(this, 1.75F, true));
        this.goalSelector.add(7, new TemptGoal(this, 1.05f, CARNIVORE, false));
        this.goalSelector.add(7, new TamableDinosaurFollowingGoal(this, 1.0F, 10.0F, 2.0F));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(11, new LookAroundGoal(this));

        this.targetSelector.add(1, new TamableDinosaurTrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new TamableDinosaurAttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(4, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(5, new ActiveTargetGoal(this, MeganeuraEntity.class, true));
        this.targetSelector.add(6, new UniversalAngerGoal(this, true));
    }

    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Nullable
    public ArchaeopteryxEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return DinosaurEntities.ARCHAEOPTERYX.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return CARNIVORE.test(stack);
    }

    public static DefaultAttributeContainer.Builder createArchaeopteryxAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.ATTACK_DAMAGE, 5)
                .add(EntityAttributes.TEMPT_RANGE, 15);

    }

    //variant
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.readAngerFromNbt(this.getWorld(), nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();//is needed or mob doesnt move at all
        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        }

        if (this.IsGliding()) {
            Vec3d currentVelocity = this.getVelocity();

            double upwardForce = -0.25D;
            this.setVelocity(currentVelocity.x, upwardForce, currentVelocity.z);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        builder.add(ANGER_TIME, 0);
    }

    public ArchaeopteryxVariant getVariant() {
        return ArchaeopteryxVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(ArchaeopteryxVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        ArchaeopteryxVariant variant = Util.getRandom(ArchaeopteryxVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public class FlyForwardWhenAirborneGoal extends Goal {
        private final ArchaeopteryxEntity mob;

        public FlyForwardWhenAirborneGoal(ArchaeopteryxEntity mob) {
            this.mob = mob;
            this.setControls(EnumSet.of(Goal.Control.MOVE)); // Indicate that this goal controls movement.
        }

        @Override
        public boolean canStart() {
            return !this.mob.isOnGround() && this.mob.getY() > 1.5D; // Check if airborne and above 1 block.
        }

        @Override
        public void tick() {
            // Calculate the forward direction based on mob's rotation.
            Vec3d forwardVector = Vec3d.fromPolar(0, this.mob.getYaw());

            // Apply a forward force (adjust the multiplier as needed).
            this.mob.addVelocity(forwardVector.multiply(0.1));
        }
    }
}
