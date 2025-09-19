package tydino.everbloom.entity.custom.dinosaurs;

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
import tydino.everbloom.block.dinosaurs.bipeds.ArchaeopteryxEgg;
import tydino.everbloom.block.dinosaurs.bipeds.CompsognathusEgg;
import tydino.everbloom.block.dinosaurs.bipeds.HypsilophodonEgg;
import tydino.everbloom.block.dinosaurs.bipeds.PteranodonEgg;
import tydino.everbloom.block.dinosaurs.insectoids.MeganeuraEgg;
import tydino.everbloom.block.dinosaurs.quadrepeds.ParasaurolophusEgg;

import java.util.function.Function;

public class DinosaurBlocks {
    //insectoids
    public static final Block MEGANEURA_EGG = registerBlock("meganeura_egg", MeganeuraEgg::new);

    //bipeds
    public static final Block HYPSILOPHODON_EGG = registerBlock("hypsilophodon_egg", HypsilophodonEgg::new);

    public static final Block COMPSOGNATHUS_EGG = registerBlock("compsognathus_egg", CompsognathusEgg::new);

    public static final Block ARCHAEOPTERYX_EGG = registerBlock("archaeopteryx_egg", ArchaeopteryxEgg::new);

    public static final Block PTERANODON_EGG = registerBlock("pteranodon_egg", PteranodonEgg::new);

    //quadrepeds
    public static final Block PARASAUROLOPHUS_EGG = registerBlock("parasaurolophus_egg", ParasaurolophusEgg::new);


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

    public static void DinoBlocks(){
        EverbloomDandaloo.LOGGER.info("registering dinosaur blocks");
    }
}
