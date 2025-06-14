package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
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
