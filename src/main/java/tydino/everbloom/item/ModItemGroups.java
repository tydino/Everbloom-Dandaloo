package tydino.everbloom.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.dinosaurs.DinosaurItemGroup;

public class ModItemGroups {
    public static final ItemGroup EverBloomDandalooItemGroup = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EverbloomDandaloo.MOD_ID, "item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DAGER_STABBER_MEAT))
                    .displayName(Text.translatable("itemgroup.everbloom.item_group"))
                    .entries((displayContext, entries) -> {

                        //cooking
                        entries.add(ModBlocks.GRIDDLE_TIER_ONE);
                        entries.add(ModBlocks.FROTHER);

                        //food

                        //crops
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.TOMATO_SAUCE);

                        //pizzas
                        entries.add(ModItems.PIZZA_DOUGH);
                        entries.add(ModItems.PIZZA_UNCOOKED);
                        entries.add(ModItems.PIZZA_PLAIN);

                        //griddle
                        entries.add(ModItems.COOKED_EGG);

                        //frother
                        entries.add(ModItems.BUTTER);
                        entries.add(ModItems.BUTTER_MELTED);
                        entries.add(ModItems.CHEESE);
                        entries.add(ModItems.FLOUR);

                        //uncatagorized
                        entries.add(ModItems.DOUGH);

                        //entities

                        //mallard
                        entries.add(ModItems.MALLARD_EGG);
                        entries.add(ModItems.MALLARD_MEAT);
                        entries.add(ModItems.COOKED_MALLARD_MEAT);

                        //dagger stabber
                        entries.add(ModItems.DAGGER_STABBER_DAGGER);
                        entries.add(ModItems.DAGER_STABBER_MEAT);
                        entries.add(ModItems.COOKED_DAGER_STABBER_MEAT);

                        //animal blocks
                        entries.add(ModBlocks.TOAD_BURROW);

                        //ores

                        //alumium
                        entries.add(ModItems.RAW_ALUMIUM);
                        entries.add(ModItems.ALUMIUM);
                        entries.add(ModBlocks.ALUMIUM_BLOCK);
                        entries.add(ModBlocks.ALUMIUM_ORE);
                        entries.add(ModBlocks.DEEPSLATE_ALUMIUM_ORE);

                        //tin
                        entries.add(ModItems.RAWTIN);
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModBlocks.TIN_BLOCK);
                        entries.add(ModBlocks.TIN_ORE);
                        entries.add(ModBlocks.DEEPSLATE_TIN_ORE);

                        //silicon
                        entries.add(ModItems.RAW_SILICON);
                        entries.add(ModItems.SILICON);
                        entries.add(ModBlocks.SILICON_BLOCK);
                        entries.add(ModBlocks.SILICON_ORE);

                        //alloys

                        //bronze
                        entries.add(ModItems.BRONZE);
                        entries.add(ModBlocks.BRONZE_BLOCK);

                        //custom objects

                        //custom items

                        //just tin
                        entries.add(ModItems.METAL_SHEET);
                        entries.add(ModItems.METAL_BOWL);
                        entries.add(ModItems.TIN_STICK);
                        entries.add(ModItems.COMPRESSOR_BANK);

                        //just silicon
                        entries.add(ModItems.SILICON_CONDUCTOR);
                        entries.add(ModItems.SOLAR_CELL);
                        entries.add(ModItems.SOLAR_SHEET);

                        //just bronze
                        entries.add(ModItems.BRONZE_CONDUCTOR);

                        entries.add(ModItems.WIRE);

                        //eggs

                        entries.add(ModBlocks.TORTOISE_EGG);

                        //spawn eggs
                        entries.add(ModItems.SPAWN_MALLARD_EGG);
                        entries.add(ModItems.SPAWN_DAGGER_STABBER);
                        entries.add(ModItems.SPAWN_TORTOISE);
                        entries.add(ModItems.SPAWN_TOAD);

                        //power
                        entries.add(ModItems.POWER_METER);
                        entries.add(ModItems.CIRCUIT_BOARD_BLANK);
                        entries.add(ModItems.CIRCUIT_BOARD_INSCRIBED);
                        entries.add(ModBlocks.POWER_HECTOGON_TIER_ONE);
                        entries.add(ModBlocks.SOLAR_PANEL_TIER_ONE);
                        entries.add(ModBlocks.POWER_STORAGE_TIER_ONE);
                        entries.add(ModBlocks.ITEM_COMPRESSOR_TIER_ONE);
                        entries.add(ModBlocks.GRINDER);
                    }).build());

    public static void registerItemGroups() {
        EverbloomDandaloo.LOGGER.info("registering item groups");
        DinosaurItemGroup.dinoItemGroup();
    }
}
