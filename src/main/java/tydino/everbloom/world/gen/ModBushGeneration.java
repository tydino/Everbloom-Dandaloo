package tydino.everbloom.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import tydino.everbloom.world.ModPlacedFeatures;

public class ModBushGeneration {
    public static void generateBushes() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_jungle"))),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TOMATO_BUSH_PLACED_KEY);
    }
}
