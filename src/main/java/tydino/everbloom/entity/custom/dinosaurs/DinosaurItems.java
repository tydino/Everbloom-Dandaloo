package tydino.everbloom.entity.custom.dinosaurs;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;

import java.util.function.Function;

public class DinosaurItems {

    //scarabs
    public static final Item BRONZE_SCARAB = registerItem("bronze_scarab", setting -> new SpawnEggItem(DinosaurEntities.BRONZE_SCARAB, setting));
    public static final Item SILVER_SCARAB = registerItem("silver_scarab", Item::new);

    //insectoids
    public static final Item SPAWN_MEGANEURA = registerItem("spawn-meganeura", setting -> new SpawnEggItem(DinosaurEntities.MEGANEURA, setting));
    public static final Item SPAWN_AGGRESSIVE_MEGANEURA = registerItem("spawn-aggressive_meganeura", setting -> new SpawnEggItem(DinosaurEntities.AGGRESSIVE_MEGANEURA, setting));

    //bipeds
    public static final Item SPAWN_HYPSILOPHODON = registerItem("spawn-hypsilophodon", setting -> new SpawnEggItem(DinosaurEntities.HYPSILOPHODON, setting));
    public static final Item SPAWN_UNTAMABLE_HYPSILOPHODON = registerItem("spawn-untamable_hypsilophodon", setting -> new SpawnEggItem(DinosaurEntities.HYPSILOPHODON_UNTAMABLE, setting));

    public static final Item SPAWN_COPSOGNATHUS = registerItem("spawn-compsognathus", setting -> new SpawnEggItem(DinosaurEntities.COMPSOGNATHUS, setting));
    public static final Item SPAWN_UNTAMABLE_COPSOGNATHUS = registerItem("spawn-untamable_compsognathus", setting -> new SpawnEggItem(DinosaurEntities.COMPSOGNATHUS_UNTAMABLE, setting));

    public static final Item SPAWN_ARCHAEOPTRYX = registerItem("spawn-archaeopteryx", setting -> new SpawnEggItem(DinosaurEntities.ARCHAEOPTERYX, setting));
    public static final Item SPAWN_UNTAMABLE_ARCHAEOPTRYX = registerItem("spawn-untamable_archaeopteryx", setting -> new SpawnEggItem(DinosaurEntities.ARCHAEOPTERYX_UNTAMABLE, setting));

    public static final Item SPAWN_PTERANODON = registerItem("spawn-pteranodon", setting -> new SpawnEggItem(DinosaurEntities.PTERANODON, setting));
    public static final Item SPAWN_UNTAMABLE_PTERANODON = registerItem("spawn-untamable_pteranodon", setting -> new SpawnEggItem(DinosaurEntities.PTERANODON_UNTAMABLE, setting));

    //quadrepeds
    public static final Item SPAWN_PARASAUROLOPHUS = registerItem("spawn-parasaurolophus", setting -> new SpawnEggItem(DinosaurEntities.PARASAUROLOPHUS, setting));
    public static final Item SPAWN_UNTAMABLE_PARASAUROLOPHUS = registerItem("spawn-untamable_parasaurolophus", setting -> new SpawnEggItem(DinosaurEntities.PARASAUROLOPHUS_UNTAMABLE, setting));

    public static void registerDinosaurItems(){EverbloomDandaloo.LOGGER.info("Registering the dinosaur items");}

    static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EverbloomDandaloo.MOD_ID, name)))));
    }
}
