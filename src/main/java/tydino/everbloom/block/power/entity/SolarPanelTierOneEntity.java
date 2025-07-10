package tydino.everbloom.block.power.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import tydino.everbloom.block.entity.ModBlockEntities;

public class SolarPanelTierOneEntity extends BlockEntity {

    public SolarPanelTierOneEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SOLAR_PANEL_TIER_ONE_BE, pos, state);
    }
}
