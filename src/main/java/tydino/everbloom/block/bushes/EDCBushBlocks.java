package tydino.everbloom.block.bushes;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import tydino.everbloom.EverbloomDandalooCore;
import tydino.everbloom.block.ModBlocks;

public class EDCBushBlocks extends ModBlocks{
    //crops
    public static final Block TOMATO_BUSH = registerBlockWithoutBlockItem("tomato_bush",
            properties -> new TomatoBushBlock(properties.mapColor(MapColor.EMERALD_GREEN).ticksRandomly()
                    .noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static void registerModBlocks(){
        EverbloomDandalooCore.LOGGER.info("registering bush blocks");
    }
}
