package tydino.everbloom.entity.unsorted;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;

public class TortoiseEntity extends AnimalEntity {
    public static final TrackedData<Boolean> HAS_EGG =
            DataTracker.registerData(TortoiseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> EggLaying =
            DataTracker.registerData(TortoiseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    int eggLayingCounter;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public TortoiseEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    //animation code
    private void setupAnimationStates(){
        if (this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = 160;
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

    //operation
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            Items.SEAGRASS, Items.SHORT_GRASS, Items.TALL_GRASS, Items.GRASS_BLOCK
    );

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.75));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MateGoal(this, (double)1.0f));
        this.goalSelector.add(3, new LayEggGoal(this, (double)1.0F));
        this.goalSelector.add(4, new TemptGoal(this, 1, BREEDING_INGREDIENT, true));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.2));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Nullable
    public TortoiseEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return ModEntities.TORTOISE.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public static DefaultAttributeContainer.Builder createTortoiseAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 12)
                .add(EntityAttributes.MOVEMENT_SPEED, .15f)
                .add(EntityAttributes.TEMPT_RANGE, 20);
    }

    //eggs

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HAS_EGG, false);
        builder.add(EggLaying, false);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasEgg", this.hasEgg());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasEgg(nbt.getBoolean("HasEgg"));
    }

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
        private final TortoiseEntity entity;

        MateGoal(TortoiseEntity entity, double speed) {
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
        private final TortoiseEntity entity;

        LayEggGoal(TortoiseEntity entity, double speed) {
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
                    world.setBlockState(BlockPos.ofFloored(this.entity.getPos()), ModBlocks.TORTOISE_EGG.getDefaultState());
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
            return world.isAir(pos.up()) && TurtleEggBlock.isSand(world, pos);
        }
    }
}
