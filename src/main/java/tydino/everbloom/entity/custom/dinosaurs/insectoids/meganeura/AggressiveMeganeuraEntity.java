package tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.entity.ModEntities;

public class AggressiveMeganeuraEntity extends MeganeuraEntity {

    public AggressiveMeganeuraEntity(EntityType<? extends MeganeuraEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initGoals() {//as this is a overide all previous incarnations are overidden
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(1, new AttackGoal((double)1.2F, false));
        this.goalSelector.add(2, new FlyGoal(this, 1.0));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(final double speed, final boolean pauseWhenIdle) {
            super(AggressiveMeganeuraEntity.this, speed, pauseWhenIdle);
        }

        protected void attack(LivingEntity target) {
            if (this.canAttack(target)) {
                this.resetCooldown();
                this.mob.tryAttack(getServerWorld(this.mob), target);
                AggressiveMeganeuraEntity.this.playSound(SoundEvents.ENTITY_FOX_BITE, 1.0F, 1.0F);
            }

        }

        public boolean canStart() {
            return super.canStart();
        }
    }

    public static DefaultAttributeContainer.Builder createMeganeuraAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 25)
                .add(EntityAttributes.MOVEMENT_SPEED, .2f)
                .add(EntityAttributes.FLYING_SPEED, .2f)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.TEMPT_RANGE, 20);
    }

    @Override
    public @Nullable MeganeuraEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return ModEntities.AGGRESSIVE_MEGANEURA.create(world, SpawnReason.BREEDING);
    }
}
