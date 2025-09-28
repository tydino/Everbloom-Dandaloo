package tydino.everbloom.world.gen;

import tydino.everbloom.EverbloomDandalooCore;

public class EDCWorldGeneration {
    public static void generateWorldGen(){
        EverbloomDandalooCore.LOGGER.info("registering world generation");
        EDCOreGeneration.generateOres();

        //ModTreeGeneration.generateTrees();
        EDCBushGeneration.generateBushes();

        EDCEntitySpawns.addSpawns();
    }
}
