package tydino.everbloom.recipe;

import tydino.everbloom.EverbloomDandaloo;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    //needs to be set manually in data.
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

    public static void registerRecipes() {
        EverbloomDandaloo.LOGGER.info("Registering Custom Recipes");
    }
}
