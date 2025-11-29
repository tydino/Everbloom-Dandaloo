package tydino.everbloomcore.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.recipe.power.shredder.ShredderRecipe;

public class EDCRecipes {

    public static RecipeSerializer<ShredderRecipe> SHREDDER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(EverbloomDandalooCore.MOD_ID, "shredder"),
            new ShredderRecipe.Serializer());

    public static RecipeType<ShredderRecipe> SHREDDER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(EverbloomDandalooCore.MOD_ID, "shredder"), new RecipeType<ShredderRecipe>() {
                @Override
                public String toString() {
                    return "shredder";
                }
            });

    public static void registerRecipes() {
        EverbloomDandalooCore.LOGGER.info("Registering Custom Recipes");
    }
}
