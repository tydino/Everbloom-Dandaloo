package tydino.everbloom.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandalooCore;

import java.util.function.Function;

public class ModItems {

    static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandalooCore.MOD_ID, name)))));
    }

    public static void registerModItems() {
        EverbloomDandalooCore.LOGGER.info("Registering Items");
    }
}
