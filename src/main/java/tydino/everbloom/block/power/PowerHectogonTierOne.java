package tydino.everbloom.block.power;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.power.entity.PowerHectogonTierOneEntity;
import tydino.everbloom.item.ModItems;
import tydino.everbloom.utility.TickableBlockEntity;

public class PowerHectogonTierOne extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<PowerHectogonTierOne> CODEC = PowerStorageTierOne.createCodec(PowerHectogonTierOne::new);

    static final VoxelShape SHAPE = PowerHectogonTierOne.createCuboidShape(1, 1, 1, 15, 15, 15);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public PowerHectogonTierOne(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PowerHectogonTierOneEntity(pos, state);
    }

    //power
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            if(player.getMainHandStack().getItem().equals(ModItems.POWER_METER)) {
                if (world.getBlockEntity(pos) instanceof PowerHectogonTierOneEntity energyGenerator) { //remember the entity
                    player.sendMessage(Text.literal("Energy: " + energyGenerator.getEnergyStorage().getAmount()), true);
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return TickableBlockEntity.getTicker(world);
    }
}
