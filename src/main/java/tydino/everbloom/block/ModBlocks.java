package tydino.everbloom.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.custom.GriddleTierOne;

public class ModBlocks {

    //cooking blocks
    public static final Block GRIDDLE_TIER_ONE = registerBlock("griddle_tier_one",
            new GriddleTierOne(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle_tier_one")))
                    .nonOpaque()));

    //ores

    //tin
    public static final Block TIN_BLOCK = registerBlock("tin_block",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, "tin_block")))
                    .strength(4f)
                    .requiresTool()));

    public static final Block TIN_ORE = registerBlock("tin_ore",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, "tin_ore")))
                    .strength(4f)
                    .requiresTool()));

    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, "deepslate_tin_ore")))
                    .strength(4f)
                    .requiresTool()));

    //voids

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        EverbloomDandaloo.LOGGER.info("registering blocks");
    }
}
