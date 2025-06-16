package tydino.everbloom.world.gen;

import tydino.everbloom.EverbloomDandaloo;

public class ModWorldGeneration {
    public static void generateWorldGen(){
        EverbloomDandaloo.LOGGER.info("registering world generation");
        //ModOreGeneration.generateOres();

        //ModTreeGeneration.generateTrees();

        ModEntitySpawns.addSpawns();
    }
}
