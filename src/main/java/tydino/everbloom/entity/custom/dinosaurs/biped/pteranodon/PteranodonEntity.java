package tydino.everbloom.entity.custom.dinosaurs.biped.pteranodon;

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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.dinosaurs.DinosaurEntities;
import tydino.everbloom.entity.custom.dinosaurs.DinosaurItems;
import tydino.everbloom.entity.custom.dinosaurs.FlyingTamableDinosaurEntity;
import tydino.everbloom.entity.custom.dinosaurs.TamableDinosaurEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.goals.*;
import tydino.everbloom.item.ModItems;
//implement FlyingEntity
public class PteranodonEntity extends FlyingTamableDinosaurEntity {
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(PteranodonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PteranodonEntity(EntityType<? extends PteranodonEntity> entityType, World world) {
        super(entityType, world, DinosaurItems.SILVER_SCARAB, 20, 1.0f);
        this.setTamed(false, false);
    }

    //animation code
    public final AnimationState eatAnimationState = new AnimationState();
    private int EatAnimationTimeout = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    boolean isIdle(){
        return !isSitting() /*&& !isInDanger()replace with is flying*/;
    }

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
            if (this.idleAnimationTimeout <= 0 && this.isOnGround()) {
                this.idleAnimationTimeout = 160;//animation time in seconds *20
                this.idleAnimationState.start(this.age);
                this.flyAnimationState.stop();
            } else {
                --this.idleAnimationTimeout;
            }
        }

        if(EatAnimationTimeout > 0){
            --EatAnimationTimeout;
        }else{
            eatAnimationState.stop();
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
                this.sittingAnimationTimeout = 11;//animation time in seconds *20
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
                this.standingAnimationTimeout = 11;//animation time in seconds *20
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

    //operation code

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.1f));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.25f, true));
        this.goalSelector.add(4, new SitGoal(this));
        this.goalSelector.add(5, new TamableDinosaurFollowingGoal(this, (double)1.0F, 10.0F, 2.0F));
        this.goalSelector.add(6, new TamableDinosaurMateGoal(this, 1.0F));
        this.goalSelector.add(7, new TamableDinosaurLayEggGoal(this, 1.0F, ModBlocks.PTERANODON_EGG, 400));
        this.goalSelector.add(8, new TemptGoal(this, 1.05f, CARNIVORE, false));
        this.goalSelector.add(9, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(10, new WanderAroundFarGoal(this, (double)1.0F));
        this.goalSelector.add(11, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(12, new LookAtEntityGoal(this, CompsognathusEntity.class, 15.0F));
        this.goalSelector.add(13, new LookAroundGoal(this));

        this.targetSelector.add(1, new TamableDinosaurTrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new TamableDinosaurAttackWithOwnerGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal(this, CompsognathusEntity.class, true));

    }

    @Nullable
    public PteranodonEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return DinosaurEntities.PTERANODON.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return CARNIVORE.test(stack);
    }

    public static DefaultAttributeContainer.Builder createPteradonAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.FLYING_SPEED, .25f)
                .add(EntityAttributes.ATTACK_DAMAGE, 10)
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

    public PteranodonVariant getVariant() {
        return PteranodonVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(PteranodonVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        PteranodonVariant variant = Util.getRandom(PteranodonVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
    @Override/// is all that is needed to check whether it is eating
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if(this.isFoodIngredient(itemStack, true)){/// swap is carnivore if carnivore
            this.eatAnimationState.start(this.age);
            this.EatAnimationTimeout = 40;
        }
        return super.interactMob(player, hand);
    }
}
