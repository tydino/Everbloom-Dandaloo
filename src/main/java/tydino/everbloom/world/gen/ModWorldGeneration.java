package tydino.everbloom.world.gen;

import tydino.everbloom.EverbloomDandalooCore;

public class ModWorldGeneration {
    public static void generateWorldGen(){
        EverbloomDandalooCore.LOGGER.info("registering world generation");
        ModOreGeneration.generateOres();

        //ModTreeGeneration.generateTrees();
        ModBushGeneration.generateBushes();

        ModEntitySpawns.addSpawns();
    }
}
