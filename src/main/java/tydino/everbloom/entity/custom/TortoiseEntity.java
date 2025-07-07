package tydino.everbloom.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.ModEntities;

public class TortoiseEntity extends AnimalEntity {
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
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.75));
        this.goalSelector.add(3, new AnimalMateGoal(this, 2));
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
                .add(EntityAttributes.MOVEMENT_SPEED, .25f)
                .add(EntityAttributes.TEMPT_RANGE, 20);
    }
}
