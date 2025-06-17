package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.SmokingRecipe;
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
    public void generate(RecipeExporter exporter) {

        //food
        List<ItemConvertible> UNCOOKED_PIZZA = List.of(ModItems.PIZZA_UNCOOKED);
        offerSmelting(exporter, UNCOOKED_PIZZA, RecipeCategory.FOOD, ModItems.PIZZA_PLAIN, 1f, 200, "pizza");

        //ore

        //tin
        List<ItemConvertible> TIN_SMELTABLES = List.of(
                ModItems.RAWTIN,
                ModBlocks.TIN_ORE,
                ModBlocks.DEEPSLATE_TIN_ORE
        );
        offerSmelting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 200, "tin_ingot");
        offerBlasting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 100, "tin_ingot");

        OreBlockToIngot(ModItems.TIN_INGOT, ModBlocks.TIN_BLOCK, exporter);

        //custom items

        //metal sheet
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.METAL_SHEET)
                .pattern("#%")
                .pattern("%#")
                .input('#', ModItems.TIN_INGOT)
                .input('%', Items.IRON_INGOT)
                .criterion(hasItem(ModItems.TIN_INGOT), conditionsFromItem(ModItems.TIN_INGOT))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        //metal bowl
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.METAL_BOWL)
                .pattern("# #")
                .pattern(" # ")
                .input('#', ModItems.METAL_SHEET)
                .criterion(hasItem(ModItems.METAL_SHEET), conditionsFromItem(ModItems.METAL_SHEET))
                .offerTo(exporter);

        //cooking

        //griddle tier one
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GRIDDLE_TIER_ONE)
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

    public void OreBlockToIngot(Item ingot, Block block, RecipeExporter exporter){

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ingot)
                .criterion(hasItem(ingot), conditionsFromItem(ingot))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ingot, 9)
                .input(block)
                .criterion(hasItem(block), conditionsFromItem(block))
                .offerTo(exporter);
    }
}
