package tydino.everbloomcore.datagen;

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
import techreborn.init.TRContent;
import techreborn.init.TRItemGroup;
import tydino.everbloomcore.item.EDCItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EDCRecipeProvider extends FabricRecipeProvider {
    public EDCRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
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

                //power
                for(ItemConvertible item : GLASS){
                    createShaped(RecipeCategory.REDSTONE, EDCItems.POWER_METER)
                            .pattern(" s ")
                            .pattern("mgm")
                            .pattern("mcm")
                            .input('s', Items.LIGHTNING_ROD)
                            .input('m', TRContent.Plates.COPPER)
                            .input('g', item)
                            .input('c', TRContent.Parts.ELECTRONIC_CIRCUIT)
                            .criterion(hasItem(Items.LIGHTNING_ROD), conditionsFromItem(Items.LIGHTNING_ROD))
                            .criterion(hasItem(TRContent.Plates.COPPER), conditionsFromItem(TRContent.Plates.COPPER))
                            .criterion(hasItem(item), conditionsFromItem(item))
                            .criterion(hasItem(TRContent.Parts.ELECTRONIC_CIRCUIT), conditionsFromItem(TRContent.Parts.ELECTRONIC_CIRCUIT))
                            .offerTo(exporter, item + "_power_meter_recipe");
                }
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

