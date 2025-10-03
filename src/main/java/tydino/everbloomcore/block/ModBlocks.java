package tydino.everbloomcore.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.block.bushes.EDCBushBlocks;

import java.util.function.Function;

//when making a block that isnt the full perimeter of the bottom or any sides; make one side or all one pixel smaller each and then it shouldnt be an x-ray

public class ModBlocks {

    public static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name), toRegister);
    }

    public static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandalooCore.MOD_ID, name)))));
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        EverbloomDandalooCore.LOGGER.info("Registering Blocks");
        EDCBushBlocks.registerModBlocks();
    }
}
