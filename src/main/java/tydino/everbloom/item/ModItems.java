package tydino.everbloom.item;

import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.ModEntities;

import java.util.function.Function;

public class ModItems {

    //food

    //pizzas
    public static final Item PIZZA_PLAIN = registerItem("pizza_plain", Item::new);
    public static final Item PIZZA_UNCOOKED = registerItem("pizza_uncooked", Item::new);

    //griddle
    public static final Item COOKED_EGG = registerItem("cooked_egg", setting -> new Item(setting.food(ModFoodComponents.CookedEgg)));

    //entities

    //mallard
    public static final Item MALLARD_EGG = registerItem("mallard_egg", setting -> new SpawnEggItem(ModEntities.MALLARD, 0xffffff, 0xffffff, setting));
    public static final Item MALLARD_MEAT = registerItem("mallard_meat", setting -> new Item(setting.food(ModFoodComponents.RawMallard, ModFoodComponents.RawMallardEffect)));
    public static final Item COOKED_MALLARD_MEAT = registerItem("cooked_mallard_meat", setting -> new Item(setting.food(ModFoodComponents.CookedMallard)));
    public static final Item SPAWN_MALLARD_EGG = registerItem("spawn-mallard", setting -> new SpawnEggItem(ModEntities.MALLARD, 0xffffff, 0xffffff, setting));

    //dagger stabber
    public static final Item DAGGER_STABBER_DAGGER = registerItem("dagger_stabber_dagger", setting -> new SwordItem(ModToolMaterials.DaggerStabber, 2, -1.0f, setting));
    public static final Item DAGER_STABBER_MEAT = registerItem("dagger_stabber_meat", setting -> new Item(setting.food(ModFoodComponents.RawDaggerStabber, ModFoodComponents.RawDaggerStabberEffect)));
    public static final Item COOKED_DAGER_STABBER_MEAT = registerItem("cooked_dagger_stabber_meat", setting -> new Item(setting.food(ModFoodComponents.CookedDaggerStabber)));
    public static final Item SPAWN_DAGGER_STABBER = registerItem("spawn-dagger-stabber", setting -> new SpawnEggItem(ModEntities.DAGGER_STABBER, 0xffffff, 0xffffff, setting));

    //ores

    //tin
    public static Item RAWTIN = registerItem("raw_tin", Item::new);
    public static Item TIN_INGOT = registerItem("tin_ingot", Item::new);

    //custom objects

    //custom items
    public static Item METAL_SHEET = registerItem("metal_sheet", Item::new);
    public static Item METAL_BOWL = registerItem("metal_bowl", Item::new);

    //voids

    static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }

    public static void registerModItems() {
        EverbloomDandaloo.LOGGER.info("Registering Items");
    }
}
