package tydino.everbloom.block.custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tydino.everbloom.entity.custom.ToadEntity;

public class ToadBurrow extends Block {
    public static final BooleanProperty FILLED = BooleanProperty.of("filled");

    public ToadBurrow(Settings settings) {
        super(settings.strength(1f));
        setDefaultState(this.getDefaultState().with(FILLED, false));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()){
            world.setBlockState(pos, state.cycle(FILLED));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }
}
