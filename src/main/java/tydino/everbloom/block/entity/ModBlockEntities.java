package tydino.everbloom.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.entity.custom.GriddleTierOneEntity;

public class ModBlockEntities {
    public static final BlockEntityType<GriddleTierOneEntity> GRIDDLE_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle_tier_one_be"),
            FabricBlockEntityTypeBuilder.create(GriddleTierOneEntity::new, ModBlocks.GRIDDLE_TIER_ONE).build(null));

    public static void registerBlockEntities() {
        EverbloomDandaloo.LOGGER.info("Registering Block Entities");
    }
}
