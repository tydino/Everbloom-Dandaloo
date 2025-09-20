package tydino.everbloom.entity.dinosaurs.insectoids.meganeura;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.dinosaurs.DinosaurBlocks;
import tydino.everbloom.entity.dinosaurs.DinosaurEntities;

public class MeganeuraEntity extends AnimalEntity implements Flutterer {

    public static final TrackedData<Boolean> HAS_EGG =
            DataTracker.registerData(MeganeuraEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> EggLaying =
            DataTracker.registerData(MeganeuraEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    int eggLayingCounter;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(MeganeuraEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    //animation code
    private void setupAnimationStates(){
        if (this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = 40; //20*seconds at end
            this.idleAnimationState.start(this.age);
        }else{
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    //operation code

    public MeganeuraEntity(EntityType<? extends MeganeuraEntity> entityType, World world){
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            Items.CHICKEN, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.COOKED_CHICKEN
    );

    @Override
    public void initGoals(){
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(3, new MateGoal(this, 1.0F));
        this.goalSelector.add(3, new LayEggGoal(this, 1.0F));
        this.goalSelector.add(4, new TemptGoal(this, 1.05f, BREEDING_INGREDIENT, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(6, new FlyGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public boolean isInAir() {
        return !this.isOnGround();
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(true);
        birdNavigation.setCanSwim(true);
        return birdNavigation;
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply((double)0.8F));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply((double)0.5F));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply((double)0.91F));
            }
        }
    }

    @Override
    public boolean isFlappingWings() {
        return !this.isOnGround();
    }

    @Nullable
    public MeganeuraEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return DinosaurEntities.MEGANEURA.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public static DefaultAttributeContainer.Builder createMeganeuraAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 25)
                .add(EntityAttributes.MOVEMENT_SPEED, .2f)
                .add(EntityAttributes.FLYING_SPEED, .2f)
                .add(EntityAttributes.TEMPT_RANGE, 20);

    }

    //variant
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.setHasEgg(nbt.getBoolean("HasEgg"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        nbt.putBoolean("HasEgg", this.hasEgg());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        builder.add(HAS_EGG, false);
        builder.add(EggLaying, false);
    }

    public MeganeuraVariant getVariant() {
        return MeganeuraVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(MeganeuraVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        MeganeuraVariant variant = Util.getRandom(MeganeuraVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    //egg

    public boolean hasEgg() {
        return (Boolean)this.dataTracker.get(HAS_EGG);
    }

    void setHasEgg(boolean hasEgg) {
        this.dataTracker.set(HAS_EGG, hasEgg);
    }

    public boolean isDiggingSand() {
        return (Boolean)this.dataTracker.get(EggLaying);
    }

    void setDiggingSand(boolean diggingSand) {
        this.eggLayingCounter = diggingSand ? 1 : 0;
        this.dataTracker.set(EggLaying, diggingSand);
    }

    static class MateGoal extends AnimalMateGoal {
        private final MeganeuraEntity entity;

        MateGoal(MeganeuraEntity entity, double speed) {
            super(entity, speed);
            this.entity = entity;
        }

        public boolean canStart() {
            return super.canStart() && !this.entity.hasEgg();
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
        private final MeganeuraEntity entity;

        LayEggGoal(MeganeuraEntity entity, double speed) {
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
                } else if (this.entity.eggLayingCounter > this.getTickCount(600)) {
                    World world = this.entity.getWorld();
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
                    world.setBlockState(BlockPos.ofFloored(this.entity.getPos()), DinosaurBlocks.MEGANEURA_EGG.getDefaultState());
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
