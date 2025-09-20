package tydino.everbloom.entity.dinosaurs.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import tydino.everbloom.entity.dinosaurs.TamableDinosaurEntity;

import java.util.EnumSet;

public class TamableDinosaurTrackOwnerAttackerGoal extends TrackTargetGoal {
    private final TamableDinosaurEntity tameable;
    private LivingEntity attacker;
    private int lastAttackedTime;

    public TamableDinosaurTrackOwnerAttackerGoal(TamableDinosaurEntity tameable) {
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
                this.attacker = livingEntity.getAttacker();
                int i = livingEntity.getLastAttackedTime();
                return i != this.lastAttackedTime && this.canTrack(this.attacker, TargetPredicate.DEFAULT) && this.tameable.canAttackWithOwner(this.attacker, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacker);
        LivingEntity livingEntity = this.tameable.getOwner();
        if (livingEntity != null) {
            this.lastAttackedTime = livingEntity.getLastAttackedTime();
        }

        super.start();
    }
}
