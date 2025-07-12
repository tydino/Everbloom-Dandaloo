package tydino.everbloom.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;

import java.util.function.Function;

public class ModItems {

    //power

    public static final Item POWER_METER = registerItem("power_meter", Item::new);

    //circuit boards
    public static final Item CIRCUIT_BOARD_BLANK = registerItem("circuit_board_blank", Item::new);
    public static final Item CIRCUIT_BOARD_INSCRIBED = registerItem("circuit_board_inscribed", Item::new);

    //food

    //crops
    public static final Item TOMATO = registerItem("tomato", setting -> new BlockItem(ModBlocks.TOMATO_BUSH, setting.food(ModFoodComponents.TOMATO)));

    //pizzas
    public static final Item PIZZA_DOUGH = registerItem("pizza_dough", Item::new);
    public static final Item PIZZA_PLAIN = registerItem("pizza_plain", Item::new);
    public static final Item PIZZA_UNCOOKED = registerItem("pizza_uncooked", Item::new);

    //griddle
    public static final Item COOKED_EGG = registerItem("cooked_egg", setting -> new Item(setting.food(ModFoodComponents.CookedEgg)));

    //frother
    public static final Item BUTTER = registerItem("butter", Item::new);
    public static final Item BUTTER_MELTED = registerItem("butter_melted", Item::new);
    public static final Item CHEESE = registerItem("cheese", setting -> new Item(setting.food(ModFoodComponents.CHEESE)));
    public static final Item FLOUR = registerItem("flour", Item::new);

    //noncatagorized
    public static final Item DOUGH = registerItem("dough", Item::new);
    public static final Item TOMATO_SAUCE = registerItem("tomato_sauce", setting -> new Item(setting.food(ModFoodComponents.TOMATO_SAUCE)));

    //entities

    //mallard
    public static final Item MALLARD_EGG = registerItem("mallard_egg", setting -> new SpawnEggItem(ModEntities.MALLARD, setting));
    public static final Item MALLARD_MEAT = registerItem("mallard_meat", setting -> new Item(setting.food(ModFoodComponents.RawMallard, ModFoodComponents.RawMallardEffect)));
    public static final Item COOKED_MALLARD_MEAT = registerItem("cooked_mallard_meat", setting -> new Item(setting.food(ModFoodComponents.CookedMallard)));
    public static final Item SPAWN_MALLARD_EGG = registerItem("spawn-mallard", setting -> new SpawnEggItem(ModEntities.MALLARD, setting));

    //dagger stabber
    public static final Item DAGGER_STABBER_DAGGER = registerItem("dagger_stabber_dagger", setting -> new SwordItem(ModToolMaterials.DaggerStabber, 2, -1.0f, setting));
    public static final Item DAGER_STABBER_MEAT = registerItem("dagger_stabber_meat", setting -> new Item(setting.food(ModFoodComponents.RawDaggerStabber, ModFoodComponents.RawDaggerStabberEffect)));
    public static final Item COOKED_DAGER_STABBER_MEAT = registerItem("cooked_dagger_stabber_meat", setting -> new Item(setting.food(ModFoodComponents.CookedDaggerStabber)));
    public static final Item SPAWN_DAGGER_STABBER = registerItem("spawn-dagger-stabber", setting -> new SpawnEggItem(ModEntities.DAGGER_STABBER, setting));

    //tortoise
    public static final Item SPAWN_TORTOISE = registerItem("spawn-tortoise", setting -> new SpawnEggItem(ModEntities.TORTOISE, setting));

    //ores

    //alumium
    public static Item RAW_ALUMIUM = registerItem("raw_alumium", Item::new);
    public static Item ALUMIUM = registerItem("alumium", Item::new);

    //tin
    public static Item RAWTIN = registerItem("raw_tin", Item::new);
    public static Item TIN_INGOT = registerItem("tin_ingot", Item::new);

    //alloys

    //bronze
    public static Item BRONZE = registerItem("bronze", Item::new);//tin and copper being mixxed

    //custom objects

    //custom items
    public static Item METAL_SHEET = registerItem("metal_sheet", Item::new);
    public static Item METAL_BOWL = registerItem("metal_bowl", Item::new);
    public static Item TIN_STICK = registerItem("tin_stick", Item::new);

    //voids

    static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }

    public static void registerModItems() {
        EverbloomDandaloo.LOGGER.info("Registering Items");
    }
}
