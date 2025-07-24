package tydino.everbloom.entity.custom.dinosaurs.goals;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import tydino.everbloom.entity.custom.dinosaurs.TamableDinosaurEntity;

public class TamableDinosaurLayEggGoal extends MoveToTargetPosGoal {
    private final TamableDinosaurEntity entity;
    final Block Egg;
    final int EggLayTime;

    public TamableDinosaurLayEggGoal(TamableDinosaurEntity entity, double speed, Block egg, int ticksToLayEgg) {
        super(entity, speed, 16);
        this.entity = entity;
        this.Egg = egg;
        this.EggLayTime = ticksToLayEgg;
    }

    public boolean canStart() {
        return this.entity.hasEgg();
    }

    public void tick() {
        super.tick();
        BlockPos blockPos = this.entity.getBlockPos();
        if (canStart()) {
            if (this.entity.eggLayingCounter < 1) {
                this.entity.setDiggingSand(true);
            } else if (this.entity.eggLayingCounter > this.getTickCount(EggLayTime)) {
                World world = this.entity.getWorld();
                world.playSound((PlayerEntity)null, blockPos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
                world.setBlockState(BlockPos.ofFloored(this.entity.getPos()), Egg.getDefaultState());
                this.entity.setHasEgg(false);
                this.entity.setDiggingSand(false);
                this.entity.setLoveTicks(600);
            }

            if (this.entity.isDiggingSand()) {
                ++this.entity.eggLayingCounter;
            }
        }

    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.isAir(pos.up());
    }
}
