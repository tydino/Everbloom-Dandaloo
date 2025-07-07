package tydino.everbloom.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.item.ModItems;
import org.jetbrains.annotations.Nullable;//solve issues

//https://youtu.be/wgVnkqqBGFs?list=PLKGarocXCE1EO43Dlf5JGh7Yk-kRAXUEJ

//you can click on animal entity, middle mouse button it, then go all the way to entity down the path, then click on the class name then ctrl+h to see all references
public class MallardEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(MallardEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int eggLayTime;

    public MallardEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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

    //operation code

    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            Items.CHICKEN, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.COOKED_CHICKEN
    );

    @Override
    protected void initGoals(){
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1));
        this.goalSelector.add(4, new TemptGoal(this, 1, BREEDING_INGREDIENT, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, ChickenEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        /*if (!this.getWorld().isClient && this.isAlive() && !this.isBaby() && --this.eggLayTime <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(ModItems.MALLARD_EGG);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.eggLayTime = this.random.nextInt(6000) + 6000;
        }*/ //figure out how to fix
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Nullable
    public MallardEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return ModEntities.MALLARD.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("EggLayTime")) {
            this.eggLayTime = nbt.getInt("EggLayTime");
        }
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("EggLayTime", this.eggLayTime);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    public static DefaultAttributeContainer.Builder createMallardAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 6)
                .add(EntityAttributes.MOVEMENT_SPEED, .25f)
                .add(EntityAttributes.TEMPT_RANGE, 12);
    }

    //variant
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    public MallardVariant getVariant() {
        return MallardVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(MallardVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        MallardVariant variant = Util.getRandom(MallardVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
