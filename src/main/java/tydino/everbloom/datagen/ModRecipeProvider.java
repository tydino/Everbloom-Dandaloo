package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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

                //tin
                List<ItemConvertible> TIN_SMELTABLES = List.of(
                        ModItems.RAWTIN,
                        ModBlocks.TIN_ORE,
                        ModBlocks.DEEPSLATE_TIN_ORE
                );
                offerSmelting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 200, "tin_ingot");
                offerBlasting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 100, "tin_ingot");

                OreBlockToIngot(ModItems.TIN_INGOT, ModBlocks.TIN_BLOCK, exporter);

                //vanilla items

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

