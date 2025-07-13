package tydino.everbloom.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.custom.Frother;
import tydino.everbloom.block.custom.GriddleTierOne;
import tydino.everbloom.block.custom.TortoiseEgg;
import tydino.everbloom.block.custom.bushes.TomatoBushBlock;
import tydino.everbloom.block.power.ItemCompressorTierOne;
import tydino.everbloom.block.power.PowerHectogonTierOne;
import tydino.everbloom.block.power.PowerStorageTierOne;
import tydino.everbloom.block.power.SolarPanelTierOne;

import java.util.function.Function;

//when making a block that isnt the full perimeter of the bottom or any sides; make one side or all one pixel smaller each and then it shouldnt be an x-ray

public class ModBlocks {

    //power

    //power hectogons
    public static Block POWER_HECTOGON_TIER_ONE = registerBlock("power_hectogon_tier_one", PowerHectogonTierOne::new);

    //generator

    //solar panels
    public static final Block SOLAR_PANEL_TIER_ONE = registerBlock("solar_panel_tier_one", SolarPanelTierOne::new);

    //batteries

    //power storages
    public static final Block POWER_STORAGE_TIER_ONE = registerBlock("power_storage_tier_one", PowerStorageTierOne::new);

    //power users

    //item compressors
    public static final Block ITEM_COMPRESSOR_TIER_ONE = registerBlock("item_compressor_tier_one", ItemCompressorTierOne::new);

    //crops
    public static final Block TOMATO_BUSH = registerBlockWithoutBlockItem("tomato_bush",
            properties -> new TomatoBushBlock(properties.mapColor(MapColor.EMERALD_GREEN).ticksRandomly()
                    .noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    //eggs
    public static final Block TORTOISE_EGG = registerBlock("tortoise_egg", TortoiseEgg::new);

    //cooking blocks
    public static final Block GRIDDLE_TIER_ONE = registerBlock("griddle_tier_one", GriddleTierOne::new);

    public static final Block FROTHER = registerBlock("frother", Frother::new);

    //ores

    //alumium
    public static Block ALUMIUM_BLOCK = registerBlock("alumium_block", properties -> new Block(properties.strength(2f).requiresTool()));

    public static Block ALUMIUM_ORE = registerBlock("alumium_ore", properties -> new Block(properties.strength(2f).requiresTool()));

    public static Block DEEPSLATE_ALUMIUM_ORE = registerBlock("deepslate_alumium_ore", properties -> new Block(properties.strength(2f).requiresTool()));

    //tin
    public static final Block TIN_BLOCK = registerBlock("tin_block", properties -> new Block(properties.strength(4f).requiresTool()));

    public static final Block TIN_ORE = registerBlock("tin_ore", properties -> new Block(properties.strength(4f).requiresTool()));

    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", properties -> new Block(properties.strength(4f).requiresTool()));

    //silicon
    public static final Block SILICON_BLOCK = registerBlock("silicon_block", properties -> new Block(properties.strength(6f).requiresTool()));

    public static final Block SILICON_ORE = registerBlock("silicon_ore", properties -> new Block(properties.strength(4f).requiresTool()));

    //alloy

    //bronze
    public static final Block BRONZE_BLOCK = registerBlock("bronze_block", properties -> new Block(properties.strength(4f).requiresTool()));

    //voids

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        EverbloomDandaloo.LOGGER.info("registering blocks");
    }
}
