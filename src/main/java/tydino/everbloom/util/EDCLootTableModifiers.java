package tydino.everbloom.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import tydino.everbloom.EverbloomDandalooCore;

//https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X/blob/54-modifyVanillaLootTables/src/main/java/net/kaupenjoe/tutorialmod/util/ModLootTableModifiers.java
public class EDCLootTableModifiers {

    public static void modifyLootTables(){
        EverbloomDandalooCore.LOGGER.info("adjusting vanilla loot tables to have modded items");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) ->{

        });
    }
}
