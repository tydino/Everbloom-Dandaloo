package tydino.everbloom.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EDCBlockCheckers {
    public static boolean hasNoBlocksAbove(World world, BlockPos blockEntityPos) {
        for (int y = blockEntityPos.getY() + 1; y < 380; y++) {
            BlockPos currentPos = new BlockPos(blockEntityPos.getX(), y, blockEntityPos.getZ());
            BlockState blockState = world.getBlockState(currentPos);

            if (!blockState.isAir()) { // Check if the block is not air
                return false; // Found a block above
            }
        }
        return true; // No blocks found above up to build height
    }
}
