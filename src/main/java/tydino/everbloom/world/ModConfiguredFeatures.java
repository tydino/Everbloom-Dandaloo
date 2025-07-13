package tydino.everbloom.world;

import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    //ores

    public static final RegistryKey<ConfiguredFeature<?, ?>> ALUMIUM_ORE_KEY = registerKey("alumium_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");


    public static final RegistryKey<ConfiguredFeature<?, ?>> SILICON_ORE_KEY = registerKey("silicon_ore");

    //bushes
    public static final RegistryKey<ConfiguredFeature<?, ?>> TOMATO_BUSH_KEY = registerKey("tomato_bush");

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EverbloomDandaloo.MOD_ID, name));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        //ores
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> alumiumOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.ALUMIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_ALUMIUM_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> tinOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TIN_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> siliconOre = List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SILICON_ORE.getDefaultState()));

        register(context, ALUMIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(alumiumOres, 8));
        register(context, TIN_ORE_KEY, Feature.ORE, new OreFeatureConfig(tinOres, 12));
        register(context, SILICON_ORE_KEY, Feature.ORE, new OreFeatureConfig(siliconOre, 8));

        //bushes

        register(context, TOMATO_BUSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.TOMATO_BUSH
                                .getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
