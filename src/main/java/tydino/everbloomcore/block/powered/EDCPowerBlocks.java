package tydino.everbloomcore.block.powered;

import net.minecraft.block.Block;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.block.ModBlocks;
import tydino.everbloomcore.block.powered.shredder.ShredderBlock;

public class EDCPowerBlocks extends ModBlocks {
    public static final Block SHREDDER = registerBlock("shredder", ShredderBlock::new);


    public static void registerModBlocks(){
        EverbloomDandalooCore.LOGGER.info("Registering Block Entities");
    }
}
