package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                //glass
                List<ItemConvertible> GLASS = List.of(
                        Items.GLASS,
                        Items.GRAY_STAINED_GLASS,
                        Items.BLUE_STAINED_GLASS,
                        Items.BLACK_STAINED_GLASS,
                        Items.BROWN_STAINED_GLASS,
                        Items.CYAN_STAINED_GLASS,
                        Items.GREEN_STAINED_GLASS,
                        Items.LIGHT_BLUE_STAINED_GLASS,
                        Items.LIGHT_GRAY_STAINED_GLASS,
                        Items.LIME_STAINED_GLASS,
                        Items.MAGENTA_STAINED_GLASS,
                        Items.ORANGE_STAINED_GLASS,
                        Items.PINK_STAINED_GLASS,
                        Items.PURPLE_STAINED_GLASS,
                        Items.RED_STAINED_GLASS,
                        Items.WHITE_STAINED_GLASS,
                        Items.YELLOW_STAINED_GLASS
                );
                List<ItemConvertible> GLASS_PANES = List.of(
                        Items.GLASS_PANE,
                        Items.GRAY_STAINED_GLASS_PANE,
                        Items.BLUE_STAINED_GLASS_PANE,
                        Items.BLACK_STAINED_GLASS_PANE,
                        Items.BROWN_STAINED_GLASS_PANE,
                        Items.CYAN_STAINED_GLASS_PANE,
                        Items.GREEN_STAINED_GLASS_PANE,
                        Items.LIGHT_BLUE_STAINED_GLASS_PANE,
                        Items.LIGHT_GRAY_STAINED_GLASS_PANE,
                        Items.LIME_STAINED_GLASS_PANE,
                        Items.MAGENTA_STAINED_GLASS_PANE,
                        Items.ORANGE_STAINED_GLASS_PANE,
                        Items.PINK_STAINED_GLASS_PANE,
                        Items.PURPLE_STAINED_GLASS_PANE,
                        Items.RED_STAINED_GLASS_PANE,
                        Items.WHITE_STAINED_GLASS_PANE,
                        Items.YELLOW_STAINED_GLASS_PANE
                );

                //ore

                //alumium
                List<ItemConvertible> ALUMIUM_SMELTABLES = List.of(
                        ModItems.RAW_ALUMIUM,
                        ModBlocks.ALUMIUM_ORE,
                        ModBlocks.DEEPSLATE_ALUMIUM_ORE
                );
                offerSmelting(ALUMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMIUM, 0.25f, 200, "alumium");
                offerBlasting(ALUMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMIUM, 0.25f, 100, "alumium");

                OreBlockToIngot(ModItems.ALUMIUM, ModBlocks.ALUMIUM_BLOCK, exporter);

                //tin
                List<ItemConvertible> TIN_SMELTABLES = List.of(
                        ModItems.RAWTIN,
                        ModBlocks.TIN_ORE,
                        ModBlocks.DEEPSLATE_TIN_ORE
                );
                offerSmelting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 200, "tin_ingot");
                offerBlasting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 100, "tin_ingot");

                OreBlockToIngot(ModItems.TIN_INGOT, ModBlocks.TIN_BLOCK, exporter);

                //silicon
                List<ItemConvertible> SILICON_SMELTABLES = List.of(
                        ModItems.RAW_SILICON,
                        ModBlocks.SILICON_ORE
                );
                offerSmelting(SILICON_SMELTABLES, RecipeCategory.MISC, ModItems.SILICON, 0.5f, 200, "silicon");
                offerBlasting(SILICON_SMELTABLES, RecipeCategory.MISC, ModItems.SILICON, 0.5f, 100, "silicon");

                OreBlockToIngot(ModItems.SILICON, ModBlocks.SILICON_BLOCK, exporter);

                //alloys
                //alloy part is in data/recipe
                OreBlockToIngot(ModItems.BRONZE, ModBlocks.BRONZE_BLOCK, exporter);

                //recipes for vanilla items

                createShaped(RecipeCategory.MISC, Items.SADDLE, 1)
                        .pattern(" l ")
                        .pattern("lil")
                        .input('l', Items.LEATHER)
                        .input('i', Items.IRON_INGOT)
                        .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Items.NAME_TAG, 1)
                        .pattern(" p")
                        .pattern("s ")
                        .input('p', Items.PAPER)
                        .input('s', Items.STRING)
                        .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                        .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                        .offerTo(exporter);

                //custom items

                //just tin

                //metal sheet
                createShaped(RecipeCategory.MISC, ModItems.METAL_SHEET, 4)
                        .pattern("#%")
                        .pattern("%#")
                        .input('#', ModItems.TIN_INGOT)
                        .input('%', Items.IRON_INGOT)
                        .criterion(hasItem(ModItems.TIN_INGOT), conditionsFromItem(ModItems.TIN_INGOT))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                //metal bowl
                createShaped(RecipeCategory.MISC, ModItems.METAL_BOWL, 3)
                        .pattern("# #")
                        .pattern(" # ")
                        .input('#', ModItems.METAL_SHEET)
                        .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                        .offerTo(exporter);

                //tin stick
                createShaped(RecipeCategory.MISC, ModItems.TIN_STICK, 4)
                        .pattern("s")
                        .pattern("s")
                        .input('s', ModItems.TIN_INGOT)
                        .criterion(hasItem(ModItems.TIN_INGOT), conditionsFromItem(ModItems.TIN_INGOT))
                        .offerTo(exporter);

                //just silicon

                //silicon conductor
                createShaped(RecipeCategory.REDSTONE, ModItems.SILICON_CONDUCTOR, 2)
                        .pattern("  s")
                        .pattern(" r ")
                        .pattern("s  ")
                        .input('s', ModItems.SILICON)
                        .input('r', Items.REDSTONE)
                        .criterion(hasItem(ModItems.SILICON), conditionsFromItem(ModItems.SILICON))
                        .offerTo(exporter);

                //solar cell
                createShapeless(RecipeCategory.REDSTONE, ModItems.SOLAR_CELL, 4)
                        .input(ModItems.SILICON_CONDUCTOR, 2)
                        .criterion(hasItem(ModItems.SILICON_CONDUCTOR), conditionsFromItem(ModItems.SILICON))
                        .offerTo(exporter);

                //solar sheet
                createShaped(RecipeCategory.REDSTONE, ModItems.SOLAR_SHEET, 2)
                        .pattern("sss")
                        .input('s', ModItems.SOLAR_CELL)
                        .criterion(hasItem(ModItems.SOLAR_CELL), conditionsFromItem(ModItems.SILICON_CONDUCTOR))
                        .offerTo(exporter);

                //just bronze

                //bronze conductor
                createShaped(RecipeCategory.REDSTONE, ModItems.BRONZE_CONDUCTOR, 2)
                        .pattern("  b")
                        .pattern(" r ")
                        .pattern("b  ")
                        .input('b', ModItems.BRONZE)
                        .input('r', Items.REDSTONE)
                        .criterion(hasItem(ModItems.BRONZE), conditionsFromItem(ModItems.BRONZE))
                        .offerTo(exporter);

                //cooking

                //griddle tier one
                createShaped(RecipeCategory.MISC, ModBlocks.GRIDDLE_TIER_ONE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("#B#")
                        .input('#', Items.RED_CONCRETE)
                        .input('S', ModItems.METAL_SHEET)
                        .input('C', Items.COAL)
                        .input('B', ModItems.METAL_BOWL)
                        .criterion(hasItem(Items.RED_CONCRETE), conditionsFromItem(Items.RED_CONCRETE))
                        .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                        .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                        .criterion(hasItem(ModItems.METAL_BOWL), conditionsFromItem(ModItems.METAL_BOWL))
                        .offerTo(exporter);

                //frother
                createShaped(RecipeCategory.MISC, ModBlocks.FROTHER)
                        .pattern("lsl")
                        .pattern("l l")
                        .pattern("lSl")
                        .input('l', Items.JUNGLE_LOG)
                        .input('s', Items.STICK)
                        .input('S', Items.JUNGLE_SLAB)
                        .criterion(hasItem(Items.JUNGLE_LOG), conditionsFromItem(Items.JUNGLE_LOG))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .criterion(hasItem(Items.JUNGLE_SLAB), conditionsFromItem(Items.JUNGLE_SLAB))
                        .offerTo(exporter);

                //food

                createShapeless(RecipeCategory.FOOD, ModItems.DOUGH, 3)
                        .input(ModItems.FLOUR, 3)
                        .input(Items.EGG, 2)
                        .input(Items.MILK_BUCKET, 2)
                        .input(Items.HONEY_BOTTLE, 2)
                        .criterion(hasItem(ModItems.FLOUR), conditionsFromItem(ModItems.FLOUR))
                        .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                        .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, ModItems.TOMATO_SAUCE)
                        .input(ModItems.TOMATO)
                        .input(Items.GLASS_BOTTLE)
                        .criterion(hasItem(ModItems.TOMATO), conditionsFromItem(ModItems.TOMATO))
                        .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModItems.PIZZA_UNCOOKED)
                        .pattern("c")
                        .pattern("s")
                        .pattern("d")
                        .input('c', ModItems.CHEESE)
                        .input('s', ModItems.TOMATO_SAUCE)
                        .input('d', ModItems.PIZZA_DOUGH)
                        .criterion(hasItem(ModItems.CHEESE), conditionsFromItem(ModItems.CHEESE))
                        .criterion(hasItem(ModItems.TOMATO_SAUCE), conditionsFromItem(ModItems.TOMATO_SAUCE))
                        .criterion(hasItem(ModItems.PIZZA_DOUGH), conditionsFromItem(ModItems.PIZZA_DOUGH))
                        .offerTo(exporter);

                //power
                for (ItemConvertible item : GLASS_PANES ) {
                    createShaped(RecipeCategory.REDSTONE, ModItems.CIRCUIT_BOARD_BLANK)
                            .pattern("ggg")
                            .pattern("brb")
                            .pattern("ggg")
                            .input('g', item)
                            .input('b', ModItems.BRONZE)
                            .input('r', Items.REDSTONE)
                            .criterion(hasItem(item), conditionsFromItem(item))
                            .criterion(hasItem(ModItems.BRONZE), conditionsFromItem(ModItems.BRONZE))
                            .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                            .offerTo(exporter, item + "_circuit_board_blank_recipe");
                }

                for (ItemConvertible item : GLASS) {
                    createShaped(RecipeCategory.REDSTONE, ModItems.POWER_METER)
                            .pattern(" s ")
                            .pattern("mgm")
                            .pattern("mcm")
                            .input('s', ModItems.TIN_STICK)
                            .input('m', ModItems.METAL_SHEET)
                            .input('g', item)
                            .input('c', ModItems.CIRCUIT_BOARD_INSCRIBED)
                            .criterion(hasItem(ModItems.TIN_STICK), conditionsFromItem(ModItems.TIN_STICK))
                            .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                            .criterion(hasItem(item), conditionsFromItem(item))
                            .criterion(hasItem(ModItems.CIRCUIT_BOARD_INSCRIBED), conditionsFromItem(ModItems.CIRCUIT_BOARD_INSCRIBED))
                            .offerTo(exporter, item + "_power_meter_recipe");
                }

                createShaped(RecipeCategory.REDSTONE, ModItems.WIRE)
                        .pattern(" s ")
                        .pattern("sbs")
                        .pattern(" s ")
                        .input('s', ModItems.SILICON)
                        .input('b', ModItems.BRONZE)
                        .criterion(hasItem(ModItems.SILICON), conditionsFromItem(ModItems.SILICON))
                        .criterion(hasItem(ModItems.BRONZE), conditionsFromItem(ModItems.BRONZE))
                        .offerTo(exporter);

                //solar panels
                createShaped(RecipeCategory.REDSTONE, ModBlocks.SOLAR_PANEL_TIER_ONE)
                        .pattern("sss")
                        .pattern(" t ")
                        .pattern(" b ")
                        .input('s', ModItems.SOLAR_SHEET)
                        .input('t', ModItems.SILICON_CONDUCTOR)
                        .input('b', ModBlocks.SILICON_BLOCK)
                        .criterion(hasItem(ModItems.SOLAR_SHEET), conditionsFromItem(ModItems.SOLAR_SHEET))
                        .offerTo(exporter);

                //power hectogons
                createShaped(RecipeCategory.REDSTONE, ModBlocks.POWER_HECTOGON_TIER_ONE)
                        .pattern("wsw")
                        .pattern("sws")
                        .pattern("wsw")
                        .input('s', ModItems.SILICON_CONDUCTOR)
                        .input('w', ModItems.WIRE)
                        .criterion(hasItem(ModItems.SILICON_CONDUCTOR), conditionsFromItem(ModItems.SILICON_CONDUCTOR))
                        .criterion(hasItem(ModItems.WIRE), conditionsFromItem(ModItems.WIRE))
                        .offerTo(exporter);

                //power storaged
                createShaped(RecipeCategory.REDSTONE, ModBlocks.POWER_STORAGE_TIER_ONE)
                        .pattern("sbs")
                        .pattern("bcb")
                        .pattern("sbs")
                        .input('s', ModItems.METAL_SHEET)
                        .input('b', ModItems.BRONZE_CONDUCTOR)
                        .input('c', ModItems.SILICON_CONDUCTOR)
                        .criterion(hasItem(ModItems.SILICON_CONDUCTOR), conditionsFromItem(ModItems.SILICON_CONDUCTOR))
                        .offerTo(exporter);

                //item compressor
                createShaped(RecipeCategory.REDSTONE, ModItems.COMPRESSOR_BANK)
                        .pattern(" s ")
                        .pattern(" s ")
                        .pattern("mmm")
                        .input('s', ModItems.TIN_STICK)
                        .input('m', ModItems.METAL_SHEET)
                        .criterion(hasItem(ModItems.TIN_STICK), conditionsFromItem(ModItems.TIN_STICK))
                        .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, ModBlocks.ITEM_COMPRESSOR_TIER_ONE)
                        .pattern("mmm")
                        .pattern("mcm")
                        .pattern("mCm")
                        .input('m', ModItems.METAL_SHEET)
                        .input('c', ModItems.COMPRESSOR_BANK)
                        .input('C', ModItems.CIRCUIT_BOARD_INSCRIBED)
                        .criterion(hasItem(ModItems.COMPRESSOR_BANK), conditionsFromItem(ModItems.COMPRESSOR_BANK))
                        .offerTo(exporter);
            }

            public void OreBlockToIngot(Item ingot, Block block, RecipeExporter exporter) {

                createShaped(RecipeCategory.MISC, block)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .input('#', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ingot, 9)
                        .input(block)
                        .criterion(hasItem(block), conditionsFromItem(block))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Everbloom Recipes";
    }
}

