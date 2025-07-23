package tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx;

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
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.MeganeuraEntity;

public class NontamableArchaeoptryxEntity extends ArchaeopteryxEntity{
    public NontamableArchaeoptryxEntity(EntityType<? extends ArchaeopteryxEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new FlyForwardWhenAirborneGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.5f));
        this.goalSelector.add(6, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(11, new LookAroundGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(4, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(5, new ActiveTargetGoal(this, MeganeuraEntity.class, true));
        this.targetSelector.add(6, new UniversalAngerGoal(this, true));
    }

    public static DefaultAttributeContainer.Builder createArchaeoptryxAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.ATTACK_DAMAGE, 5)
                .add(EntityAttributes.TEMPT_RANGE, 0);

    }

    @Override
    public @Nullable ArchaeopteryxEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.FAIL;
    }
}
