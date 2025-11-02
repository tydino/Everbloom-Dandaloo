package tydino.everbloomcore.entity.core_entity_brains;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloomcore.entity.EDCEntities;
import tydino.everbloomcore.entity.bases.EDTamableEntity;
import tydino.everbloomcore.entity.goals.EDTamableEntity_FollowingGoal;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ManedWolfEntity extends EDTamableEntity {

    static final Ingredient Food = Ingredient.ofItems(
            Items.CHICKEN, Items.COOKED_CHICKEN
    );

    // SUPER //
    public ManedWolfEntity(EntityType<? extends TameableEntity> entityType, World world) {
        /// ADD PROPPPER TAME ITEM, FOOD LIST, AND CHANCE OF TAME ///
        super(entityType, world, Items.EMERALD, 1);
    }

    // ANIMATION //
    public final AnimationState idleAnimationState= new AnimationState();
    int idleAnimationTimeout = 0;

    public final AnimationState sitAnimationState = new AnimationState();
    private int sitAnimationTimeout = 0;
    boolean properlySitting;
    public final AnimationState sittingdownAnimationState = new AnimationState();
    private int sittingAnimationTimeout = 0;
    boolean isSittingDown;
    public final AnimationState standingupAnimationState = new AnimationState();
    private int standingAnimationTimeout = 0;
    boolean isStandingUp;

    void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;//animation time in seconds *20
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        setUpSitting();
        if (properlySitting) {
            if (this.sitAnimationTimeout <= 0) {
                this.sitAnimationTimeout = 10;//animation time in seconds *20
                this.sitAnimationState.start(this.age);
                this.sittingdownAnimationState.stop();
            } else {
                --this.sitAnimationTimeout;
            }
        }
        if (isSittingDown && isSittingDownNow()) {
            if (this.sittingAnimationTimeout <= 0) {
                this.sittingAnimationTimeout = 20;//animation time in seconds *20
                this.sittingdownAnimationState.start(this.age);
            } else {
                --this.sittingAnimationTimeout;
            }
        }else{
            this.sittingdownAnimationState.stop();
            sittingAnimationTimeout = 0;
        }
        if (isStandingUp) {
            if (this.standingAnimationTimeout <= 0) {
                this.standingAnimationTimeout = 20;//animation time in seconds *20
                this.standingupAnimationState.start(this.age);
                this.sitAnimationState.stop();
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

    // OPERATION CODE //


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(4, new EDTamableEntity_FollowingGoal(this, (double)1.0f, 10.0f, 2.0f));
        this.goalSelector.add(5, new AnimalMateGoal(this, (double)1.0f));
        this.goalSelector.add(6, new AnimalMateGoal(this, (double)1.0f));
        this.goalSelector.add(7, new TemptGoal(this, 1.05f, Food, true));
        this.goalSelector.add(8, new FollowParentGoal(this, 1.25f));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(10, new LookAroundGoal(this));
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EDCEntities.MANED_WOLF.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return Food.test(stack);
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.TEMPT_RANGE, 15);
    }
}
