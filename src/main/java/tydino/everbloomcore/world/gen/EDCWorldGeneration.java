package tydino.everbloomcore.world.gen;

import tydino.everbloomcore.EverbloomDandalooCore;

public class EDCWorldGeneration {
    public static void generateWorldGen(){
        EverbloomDandalooCore.LOGGER.info("registering world generation");
        EDCOreGeneration.generateOres();

        //ModTreeGeneration.generateTrees();
        EDCBushGeneration.generateBushes();

        EDCEntitySpawns.addSpawns();
    }
}
