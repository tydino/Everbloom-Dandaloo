package tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusVariant;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.MeganeuraEntity;
import tydino.everbloom.item.ModItems;

import java.util.UUID;

public class ArchaeopteryxEntity  extends TameableEntity implements Angerable {
    public static final TrackedData<Boolean> HAS_EGG =
            DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> EggLaying =
            DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    int eggLayingCounter;

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(ArchaeopteryxEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ArchaeopteryxEntity(EntityType<? extends ArchaeopteryxEntity> entityType, World world) {
        super(entityType, world);
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
        if (isSittingDown) {
            if (this.sittingAnimationTimeout <= 0) {
                this.sittingAnimationTimeout = 21;//animation time in seconds *20
                this.sittingdownAnimationState.start(this.age);
                this.idleAnimationState.stop();
            } else {
                --this.sittingAnimationTimeout;
            }
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

    public boolean IsGliding() {
        return !this.isOnGround() && this.getVelocity().y < -0.1;
    }

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

    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.MUTTON, Items.COOKED_MUTTON, Items.RABBIT, Items.COOKED_RABBIT, ModItems.MALLARD_MEAT, ModItems.COOKED_MALLARD_MEAT, ModItems.DAGER_STABBER_MEAT, ModItems.COOKED_DAGER_STABBER_MEAT
    );

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new MateGoal(this, 1.0F));
        this.goalSelector.add(5, new LayEggGoal(this, 1.0F));
        this.goalSelector.add(6, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.add(7, new FollowOwnerGoal(this, 1.0F, 10.0F, 2.0F));
        this.goalSelector.add(8, new AnimalMateGoal(this, 1.0F));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(11, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(4, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(5, new ActiveTargetGoal(this, MeganeuraEntity.class, true));
        this.targetSelector.add(6, new UniversalAngerGoal(this, true));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTamed()) {
            if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                this.eat(player, hand, itemStack);
                FoodComponent foodComponent = itemStack.get(DataComponentTypes.FOOD);
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
        }else if (!this.getWorld().isClient && itemStack.isOf(ModItems.SILVER_SCARAB) && !this.hasAngerTime()) {
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
            this.setTarget(null);
            this.setSitting(true);
            this.setSittingDown(true);
            this.getWorld().sendEntityStatus(this, (byte)7);
        } else {
            this.getWorld().sendEntityStatus(this, (byte)6);
        }

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
        return ModEntities.ARCHAEOPTERYX.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public static DefaultAttributeContainer.Builder createArchaeopteryxAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 15)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.ATTACK_DAMAGE, 5)
                .add(EntityAttributes.TEMPT_RANGE, 15);

    }

    //variant
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.setHasEgg(nbt.getBoolean("HasEgg"));
        this.setSittingDown(nbt.getBoolean("Sitting"));
        this.readAngerFromNbt(this.getWorld(), nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        nbt.putBoolean("HasEgg", this.hasEgg());
        nbt.putBoolean("Sitting", this.isSittingDownNow());
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();//is needed or mob doesnt move at all
        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        }

        if (this.isGliding()) { //is how it glides
            Vec3d velocity = this.getVelocity();
            this.setVelocity(velocity.x, -0.1, velocity.z);
        }
    }

    void setSittingDown(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
    }

    public boolean isSittingDownNow() {
        return this.dataTracker.get(SITTING);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        builder.add(HAS_EGG, false);
        builder.add(EggLaying, false);
        builder.add(SITTING, false);
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

    //egg

    public boolean hasEgg() {
        return this.dataTracker.get(HAS_EGG);
    }

    void setHasEgg(boolean hasEgg) {
        this.dataTracker.set(HAS_EGG, hasEgg);
    }

    public boolean isDiggingSand() {
        return this.dataTracker.get(EggLaying);
    }

    void setDiggingSand(boolean diggingSand) {
        this.eggLayingCounter = diggingSand ? 1 : 0;
        this.dataTracker.set(EggLaying, diggingSand);
    }

    static class MateGoal extends AnimalMateGoal {
        private final ArchaeopteryxEntity entity;

        MateGoal(ArchaeopteryxEntity entity, double speed) {
            super(entity, speed);
            this.entity = entity;
        }

        public boolean canStart() {
            return super.canStart() && !this.entity.hasEgg() && entity.isTamed();
        }

        protected void breed() {
            ServerPlayerEntity serverPlayerEntity = this.animal.getLovingPlayer();
            if (serverPlayerEntity == null && this.mate.getLovingPlayer() != null) {
                serverPlayerEntity = this.mate.getLovingPlayer();
            }

            if (serverPlayerEntity != null) {
                serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
                Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this.animal, this.mate, (PassiveEntity)null);
            }

            this.entity.setHasEgg(true);
            this.animal.setBreedingAge(6000);
            this.mate.setBreedingAge(6000);
            this.animal.resetLoveTicks();
            this.mate.resetLoveTicks();
            Random random = this.animal.getRandom();
            if (castToServerWorld(this.world).getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
                this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }

        }
    }

    static class LayEggGoal extends MoveToTargetPosGoal {
        private final ArchaeopteryxEntity entity;

        LayEggGoal(ArchaeopteryxEntity entity, double speed) {
            super(entity, speed, 16);
            this.entity = entity;
        }

        public boolean canStart() {
            return this.entity.hasEgg();
        }

        public void tick() {
            super.tick();
            BlockPos blockPos = this.entity.getBlockPos();
            if (canStart()) {
                if (this.entity.eggLayingCounter < 1) {
                    this.entity.setDiggingSand(true);
                } else if (this.entity.eggLayingCounter > this.getTickCount(800)) {//takes about 8 seconds to lay an egg
                    World world = this.entity.getWorld();
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
                    world.setBlockState(BlockPos.ofFloored(this.entity.getPos()), ModBlocks.ARCHAEOPTERYX_EGG.getDefaultState());
                    this.entity.setHasEgg(false);
                    this.entity.setDiggingSand(false);
                    this.entity.setLoveTicks(600);
                }

                if (this.entity.isDiggingSand()) {
                    ++this.entity.eggLayingCounter;
                }
            }

        }

        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.isAir(pos.up());
        }
    }
}
