package tydino.everbloom.entity.dinosaurs.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import tydino.everbloom.entity.dinosaurs.FlyingTamableDinosaurEntity;

import java.util.EnumSet;

public class FlyingTamableDinosaurTiredFlyingGoal extends Goal {
    final FlyingTamableDinosaurEntity mob;
    final double speed;

    public FlyingTamableDinosaurTiredFlyingGoal(FlyingTamableDinosaurEntity mob, double speed){
        this.mob = mob;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        return !this.mob.isOnGround() && this.mob.getFlyingTime() >= this.mob.MAX_FLYING_TIME;
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.isOnGround() && this.mob.getFlyingTime() >= this.mob.MAX_FLYING_TIME && this.mob.tiredCooldown < this.mob.TIRED_COOLDOWN_TIME;
    }

    @Override
    public void start() {
        this.mob.getNavigation().stop();
        //this.mob.clearGoalsAndTasks();
    }

    @Override
    public void tick() {
        if (this.mob.getNavigation().isIdle()) {
            BlockPos pos = findGroundPos();
            if (pos != null) {
                this.mob.getNavigation().startMovingTo(pos.getX(), pos.getY(), pos.getZ(), this.speed);
            }
        }
    }

    private BlockPos findGroundPos() {
        // Look for a spot within a certain radius
        BlockPos currentPos = this.mob.getBlockPos();
        for (int i = 0; i < 16; i++) {
            BlockPos searchPos = currentPos.add(
                    (this.mob.getRandom().nextInt(32) - 16),
                    (this.mob.getRandom().nextInt(16) - 8),
                    (this.mob.getRandom().nextInt(32) - 16)
            );
            if (this.mob.getWorld().getBlockState(searchPos).isFullCube(this.mob.getWorld(), searchPos)) {
                // Return a position just above a solid block
                return searchPos.up();
            }
        }
        return null;
    }
}
