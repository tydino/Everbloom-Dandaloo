package tydino.everbloom.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.item.ModItems;

//https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X/blob/54-modifyVanillaLootTables/src/main/java/net/kaupenjoe/tutorialmod/util/ModLootTableModifiers.java
public class ModLootTableModifiers {
    static final Identifier CREEPER_ID = Identifier.of("minecraft", "entities/creeper");

    public static void modifyLootTables(){
        EverbloomDandaloo.LOGGER.info("adjusting vanilla loot tables to have modded items");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) ->{
            //can put more here
            if(CREEPER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) // Drops 50% of the time
                        .with(ItemEntry.builder(ModItems.RAWTIN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            //can put more here
        });
    }
}
