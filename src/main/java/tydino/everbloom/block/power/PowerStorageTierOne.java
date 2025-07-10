package tydino.everbloom.block.power;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
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
import tydino.everbloom.block.power.entity.PowerStorageTierOneEntity;
import tydino.everbloom.block.power.entity.SolarPanelTierOneEntity;
import tydino.everbloom.item.ModItems;
import tydino.everbloom.utility.TickableBlockEntity;

public class PowerStorageTierOne extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<PowerStorageTierOne> CODEC = PowerStorageTierOne.createCodec(PowerStorageTierOne::new);

    static final VoxelShape SHAPE = PowerStorageTierOne.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public PowerStorageTierOne(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PowerStorageTierOneEntity(pos, state);
    }

    //power
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            if(player.getMainHandStack().getItem().equals(ModItems.POWER_METER)) {
                if (world.getBlockEntity(pos) instanceof PowerStorageTierOneEntity energyGenerator) { //remember the entity
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
