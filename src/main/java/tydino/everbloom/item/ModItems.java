package tydino.everbloom.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.ModEntities;

public class ModItems {

    //food

    //pizzas
    public static final Item PIZZA_PLAIN = registerItem("pizza_plain", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "pizza_plain")))));
    public static final Item PIZZA_UNCOOKED = registerItem("pizza_uncooked", new Item(new Item.Settings()));

    //griddle
    public static final Item COOKED_EGG = registerItem("cooked_egg", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "cooked_egg"))).food(ModFoodComponents.CookedEgg)));

    //entities

    //mallard
    public static final Item MALLARD_EGG = registerItem("mallard_egg", new SpawnEggItem(ModEntities.MALLARD, 0xffffff, 0xffffff, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "mallard_egg")))));
    public static final Item MALLARD_MEAT = registerItem("mallard_meat", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "mallard_meat")))));
    public static final Item COOKED_MALLARD_MEAT = registerItem("cooked_mallard_meat", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "cooked_mallard_meat"))).food(ModFoodComponents.CookedMallard)));
    public static final Item SPAWN_MALLARD_EGG = registerItem("spawn-mallard", new SpawnEggItem(ModEntities.MALLARD, 0xffffff, 0xffffff, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "spawn-mallard")))));

    //dagger stabber
    public static final Item DAGGER_STABBER_DAGGER = registerItem("dagger_stabber_dagger", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "dagger_stabber_dagger"))).maxCount(1).maxDamage(6)));//figure out how to get the dagger to do dammage
    public static final Item DAGER_STABBER_MEAT = registerItem("dagger_stabber_meat", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "dagger_stabber_meat")))));
    public static final Item COOKED_DAGER_STABBER_MEAT = registerItem("cooked_dagger_stabber_meat", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "cooked_dagger_stabber_meat"))).food(ModFoodComponents.CookedDaggerStabber)));
    public static final Item SPAWN_DAGGER_STABBER = registerItem("spawn-dagger-stabber", new SpawnEggItem(ModEntities.DAGGER_STABBER, 0xffffff, 0xffffff, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "spawn-dagger-stabber")))));

    //ores

    //tin
    public static Item RAWTIN = registerItem("raw_tin", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "raw_tin")))));
    public static Item TIN_INGOT = registerItem("tin_ingot", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "tin_ingot")))));

    //custom objects

    //custom items
    public static Item METAL_SHEET = registerItem("metal_sheet", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "metal_sheet")))));
    public static Item METAL_BOWL = registerItem("metal_bowl", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, "metal_bowl")))));

    //voids

    static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EverbloomDandaloo.LOGGER.info("Registering Items");
    }
}
