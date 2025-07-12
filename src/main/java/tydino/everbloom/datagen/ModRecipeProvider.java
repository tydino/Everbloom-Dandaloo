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

                createShaped(RecipeCategory.MISC, ModItems.TIN_STICK, 4)
                        .pattern("s")
                        .pattern("s")
                        .input('s', ModItems.TIN_INGOT)
                        .criterion(hasItem(ModItems.TIN_INGOT), conditionsFromItem(ModItems.TIN_INGOT))
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

                createShaped(RecipeCategory.MISC, ModItems.DOUGH, 3)
                        .pattern("fee")
                        .pattern("fmm")
                        .pattern("fhh")
                        .input('f', ModItems.FLOUR)
                        .input('e', Items.EGG)
                        .input('m', Items.MILK_BUCKET)
                        .input('h', Items.HONEY_BOTTLE)
                        .criterion(hasItem(ModItems.FLOUR), conditionsFromItem(ModItems.FLOUR))
                        .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                        .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModItems.TOMATO_SAUCE)
                        .pattern("t")
                        .pattern("b")
                        .input('t', ModItems.TOMATO)
                        .input('b', Items.GLASS_BOTTLE)
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
                createShaped(RecipeCategory.REDSTONE, ModItems.CIRCUIT_BOARD_BLANK)
                        .pattern("ggg")
                        .pattern("brb")
                        .pattern("ggg")
                        .input('g', Items.LIME_STAINED_GLASS_PANE)
                        .input('b', ModItems.BRONZE)
                        .input('r', Items.REDSTONE)
                        .criterion(hasItem(Items.LIME_STAINED_GLASS_PANE), conditionsFromItem(Items.LIME_STAINED_GLASS_PANE))
                        .criterion(hasItem(ModItems.BRONZE), conditionsFromItem(ModItems.BRONZE))
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, ModItems.POWER_METER)
                        .pattern(" s ")
                        .pattern("mgm")
                        .pattern("mcm")
                        .input('s', ModItems.TIN_STICK)
                        .input('m', ModItems.METAL_SHEET)
                        .input('g', Items.GLASS)
                        .input('c', ModItems.CIRCUIT_BOARD_BLANK)
                        .criterion(hasItem(ModItems.TIN_STICK), conditionsFromItem(ModItems.TIN_STICK))
                        .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                        .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .criterion(hasItem(ModItems.CIRCUIT_BOARD_BLANK), conditionsFromItem(ModItems.CIRCUIT_BOARD_BLANK))
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

