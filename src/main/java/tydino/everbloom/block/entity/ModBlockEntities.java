package tydino.everbloom.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.entity.custom.FrotherEntity;
import tydino.everbloom.block.entity.custom.GriddleTierOneEntity;
import tydino.everbloom.block.power.entity.*;

public class ModBlockEntities {

    //power

    //wires
    public static final BlockEntityType<PowerHectogonTierOneEntity> POWER_HECTOGON_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "power_hectogon_tier_one_be"),
            FabricBlockEntityTypeBuilder.create(PowerHectogonTierOneEntity::new, ModBlocks.POWER_HECTOGON_TIER_ONE).build());

    //generators

    //solar panels
    public static final BlockEntityType<SolarPanelTierOneEntity> SOLAR_PANEL_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "solar_panel_tier_one_be"),
            FabricBlockEntityTypeBuilder.create(SolarPanelTierOneEntity::new, ModBlocks.SOLAR_PANEL_TIER_ONE).build());

    //batteries

    //power storages
    public static final BlockEntityType<PowerStorageTierOneEntity> POWER_STORAGE_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "power_storage_tier_one_be"),
            FabricBlockEntityTypeBuilder.create(PowerStorageTierOneEntity::new, ModBlocks.POWER_STORAGE_TIER_ONE).build());

    //grinder
    public static final BlockEntityType<GrinderEntity> GRINDER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "grinder_be"),
            FabricBlockEntityTypeBuilder.create(GrinderEntity::new, ModBlocks.GRINDER).build());

    //power users

    //item compressors
    public static final BlockEntityType<ItemCompressorTierOneEntity> ITEM_COMPRESSOR_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "item_compressor_tier_one"),
            FabricBlockEntityTypeBuilder.create(ItemCompressorTierOneEntity::new, ModBlocks.ITEM_COMPRESSOR_TIER_ONE).build());

    //cooking
    public static final BlockEntityType<GriddleTierOneEntity> GRIDDLE_TIER_ONE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle_tier_one_be"),
            FabricBlockEntityTypeBuilder.create(GriddleTierOneEntity::new, ModBlocks.GRIDDLE_TIER_ONE).build());

    public static final BlockEntityType<FrotherEntity> Frother_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "frother_be"),
            FabricBlockEntityTypeBuilder.create(FrotherEntity::new, ModBlocks.FROTHER).build());

    public static void registerBlockEntities() {
        EverbloomDandaloo.LOGGER.info("Registering Block Entities");
    }
}
