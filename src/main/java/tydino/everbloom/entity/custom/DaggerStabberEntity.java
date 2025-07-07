package tydino.everbloom.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.item.ModItems;

public class DaggerStabberEntity extends AnimalEntity{

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public DaggerStabberEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    //animation code
    private void setupAnimationStates(){
        if (this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.age);
        }else{
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.age);
        }else{
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
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
            ModItems.MALLARD_EGG, Items.EGG, Items.BONE, Items.ROTTEN_FLESH
    );

    @Override
    protected void initGoals(){
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1));
        this.goalSelector.add(3, new TemptGoal(this, 1, BREEDING_INGREDIENT, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1));
        this.targetSelector.add(6, new ActiveTargetGoal(this, ZombieEntity.class, true));
        this.goalSelector.add(7, new LookAtEntityGoal(this, SkeletonEntity.class, 9.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
    }

    @Nullable
    public DaggerStabberEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return ModEntities.DAGGER_STABBER.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public static DefaultAttributeContainer.Builder createStabberDaggerAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 6)
                .add(EntityAttributes.ATTACK_DAMAGE, 6f)
                .add(EntityAttributes.MOVEMENT_SPEED, .15f)
                .add(EntityAttributes.TEMPT_RANGE, 15f);
    }

    //sounds

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return super.getDeathSound();//set to custom sound when ready
    }
}
