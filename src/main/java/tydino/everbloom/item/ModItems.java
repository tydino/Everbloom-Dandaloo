package tydino.everbloom.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;

public class ModItems {

    //food

    //griddle
    public static final Item COOKED_EGG = registerItem("cooked_egg", new Item(new Item.Settings().food(ModFoodComponents.CookedEgg)));

    //entities

    //dagger stabber
    public static final Item DAGER_STABBER_MEAT = registerItem("dagger_stabber_meat", new Item(new Item.Settings()));
    public static final Item COOKED_DAGER_STABBER_MEAT = registerItem("cooked_dagger_stabber_meat", new Item(new Item.Settings().food(ModFoodComponents.CookedDaggerStabber)));

    //ores

    //tin
    public static Item RAWTIN = registerItem("raw_tin", new Item(new Item.Settings()));
    public static Item TIN_INGOT = registerItem("tin_ingot", new Item(new Item.Settings()));

    //voids

    static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EverbloomDandaloo.LOGGER.info("Registering Items");
    }
}
