package tydino.everbloom.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import tydino.everbloom.world.ModPlacedFeatures;

import java.util.List;
import java.util.function.Predicate;

public class ModOreGeneration {
    public static void generateOres() {

        List<Predicate<BiomeSelectionContext>> AETHER_BIOMES = List.of(
                BiomeSelectors.includeByKey(RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_jungle"))),
                BiomeSelectors.includeByKey(RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_plains"))),
                BiomeSelectors.includeByKey(RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_desert")))
        );

        //aether
        BiomeModifications.addFeature((BiomeSelectors.includeByKey(
                RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_jungle")),
                        RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_plains")),
                        RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_desert"))
                        )),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.ALUMIUM_ORE_PLACED_KEY);

        BiomeModifications.addFeature((BiomeSelectors.includeByKey(
                        RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_jungle")),
                        RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_plains")),
                        RegistryKey.of(RegistryKeys.BIOME, Identifier.of("everbloom", "aether_desert"))
                )),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.SILICON_ORE_PLACED_KEY);

        //overworld

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.TIN_ORE_PLACED_KEY);

        // Example for individual Biomes
        // BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.PLAINS),
        // GenerationStep.Feature.UNDERGROUND_ORES,
        //         ModPlacedFeatures.PINK_GARNET_ORE_PLACED_KEY);

        /*BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.NETHER_PINK_GARNET_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.END_PINK_GARNET_ORE_PLACED_KEY);*/ //from tutorial for different dimensions
    }
}
