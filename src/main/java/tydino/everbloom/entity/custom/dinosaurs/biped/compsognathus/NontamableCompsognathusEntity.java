package tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;

public class NontamableCompsognathusEntity extends CompsognathusEntity{
    public NontamableCompsognathusEntity(EntityType<? extends CompsognathusEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));this.goalSelector.add(5, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, (double)1.0F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal(this, HypsilophodonEntity.class, true));
        this.targetSelector.add(3, new UniversalAngerGoal(this, true));
    }

    public static DefaultAttributeContainer.Builder createCompsognathusAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 15)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.ATTACK_DAMAGE, 2)
                .add(EntityAttributes.TEMPT_RANGE, 0);

    }

    @Override
    public @Nullable CompsognathusEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.FAIL;
    }
}
