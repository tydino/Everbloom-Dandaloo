package tydino.everbloom.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import tydino.everbloom.EverbloomDandaloo;

import java.util.List;

public class ModPlacedFeatures {
    //ores

    public static final RegistryKey<PlacedFeature> ALUMIUM_ORE_PLACED_KEY = registerKey("alumium_ore_placed");

    public static final RegistryKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");

    public static final RegistryKey<PlacedFeature> SILICON_ORE_PLACED_KEY = registerKey("silicon_ore_placed");

    //bushes

    public static final RegistryKey<PlacedFeature> TOMATO_BUSH_PLACED_KEY = registerKey("tomato_bush_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        //ores

        register(context, ALUMIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(10,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-100), YOffset.fixed(150))));

        register(context, TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, SILICON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILICON_ORE_KEY),
                ModOrePlacement.modifiersWithCount(10,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-100), YOffset.fixed(150))));

        //bushes
        register(context, TOMATO_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOMATO_BUSH_KEY),
                RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(EverbloomDandaloo.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
