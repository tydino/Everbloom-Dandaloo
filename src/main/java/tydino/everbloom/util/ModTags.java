package tydino.everbloom.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DAGGER_STABBER_TOOL = createTag("needs_pink_garnet_tool");
        public static final TagKey<Block> INCORRECT_FOR_DAGGER_STABBER_TOOL = createTag("incorrect_for_pink_garnet_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(EverbloomDandaloo.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DAGGER_STABBER_REPAIR = createTag("pink_garnet_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name));
        }
    }
}
