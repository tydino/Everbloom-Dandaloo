package tydino.everbloom.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import tydino.everbloom.entity.ModEntities;

public class ModEntitySpawns {
    public static void addSpawns(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS/*can add more here*/),
                SpawnGroup.CREATURE, ModEntities.MALLARD, 50, 3, 6);
    }
}
