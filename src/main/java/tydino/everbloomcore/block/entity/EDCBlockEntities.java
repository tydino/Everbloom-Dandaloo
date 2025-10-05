package tydino.everbloomcore.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.block.powered.EDCPowerBlocks;
import tydino.everbloomcore.block.powered.shredder.ShredderBlockEntitiy;

public class EDCBlockEntities {

    public static BlockEntityType<ShredderBlockEntitiy> SHREDDER = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EverbloomDandalooCore.MOD_ID, "shredder_be"),
            FabricBlockEntityTypeBuilder.create(ShredderBlockEntitiy::new, EDCPowerBlocks.SHREDDER).build());

    public static void registerBlockEntities(){
        EverbloomDandalooCore.LOGGER.info("Registering Block Entities");
    }
}
