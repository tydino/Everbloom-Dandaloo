package tydino.everbloom.entity.dinosaurs.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import tydino.everbloom.entity.dinosaurs.TamableDinosaurEntity;

import java.util.EnumSet;

public class TamableDinosaurAttackWithOwnerGoal extends TrackTargetGoal {
    private final TamableDinosaurEntity tameable;
    private LivingEntity attacking;
    private int lastAttackTime;

    public TamableDinosaurAttackWithOwnerGoal(TamableDinosaurEntity tameable) {
        super(tameable, false);
        this.tameable = tameable;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    public boolean canStart() {
        if (this.tameable.isTamed() && !this.tameable.isSitting() && this.tameable.isFollowing()) {
            LivingEntity livingEntity = this.tameable.getOwner();
            if (livingEntity == null) {
                return false;
            } else {
                this.attacking = livingEntity.getAttacking();
                int i = livingEntity.getLastAttackTime();
                return i != this.lastAttackTime && this.canTrack(this.attacking, TargetPredicate.DEFAULT) && this.tameable.canAttackWithOwner(this.attacking, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacking);
        LivingEntity livingEntity = this.tameable.getOwner();
        if (livingEntity != null) {
            this.lastAttackTime = livingEntity.getLastAttackTime();
        }

        super.start();
    }
}
