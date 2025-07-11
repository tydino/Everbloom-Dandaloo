package tydino.everbloom.recipe;

import net.minecraft.item.Item;
import tydino.everbloom.EverbloomDandaloo;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    //needs to be set manually in data.

    //power blocks
    public static final RecipeSerializer<ItemCompressorRecipe> ITEM_COMPRESSOR_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(EverbloomDandaloo.MOD_ID, "item_compressor"),
            new ItemCompressorRecipe.Serializer());

    public static final RecipeType<ItemCompressorRecipe> ITEM_COMPRESSOR_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "item_compressor"), new RecipeType<ItemCompressorRecipe>() {
                @Override
                public String toString() {
                    return "item_compressor";
                }
            });

    //cooking

    //griddle
    public static final RecipeSerializer<GriddleRecipe> GRIDDLE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle"),
            new GriddleRecipe.Serializer());

    public static final RecipeType<GriddleRecipe> GRIDDLE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle"), new RecipeType<GriddleRecipe>() {
                @Override
                public String toString() {
                    return "griddle";
                }
            });

    //frother
    public static final RecipeSerializer<FrotherRecipe> FROTHER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(EverbloomDandaloo.MOD_ID, "frother"),
            new FrotherRecipe.Serializer());

    public static final RecipeType<FrotherRecipe> FROTHER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "frother"), new RecipeType<FrotherRecipe>() {
                @Override
                public String toString() {
                    return "frother";
                }
            });

    public static void registerRecipes() {
        EverbloomDandaloo.LOGGER.info("Registering Custom Recipes");
    }
}
