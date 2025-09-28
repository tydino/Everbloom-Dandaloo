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
import tydino.everbloom.EverbloomDandalooCore;

import java.util.function.Function;

//when making a block that isnt the full perimeter of the bottom or any sides; make one side or all one pixel smaller each and then it shouldnt be an x-ray

public class ModBlocks {

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        EverbloomDandalooCore.LOGGER.info("registering blocks");
    }
}
