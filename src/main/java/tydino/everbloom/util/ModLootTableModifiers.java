package tydino.everbloom.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandalooCore;
import tydino.everbloom.item.ModItems;

//https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X/blob/54-modifyVanillaLootTables/src/main/java/net/kaupenjoe/tutorialmod/util/ModLootTableModifiers.java
public class ModLootTableModifiers {

    public static void modifyLootTables(){
        EverbloomDandalooCore.LOGGER.info("adjusting vanilla loot tables to have modded items");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) ->{

        });
    }
}
