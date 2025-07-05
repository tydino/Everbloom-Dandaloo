package tydino.everbloom.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.recipe.GriddleRecipe;
import tydino.everbloom.recipe.ModRecipes;
import tydino.everbloom.screen.custom.GriddleTierOneScreen;

public class EverbloomREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new GriddleCatagory());

        registry.addWorkstations(GriddleCatagory.GRIDDLE, EntryStacks.of(ModBlocks.GRIDDLE_TIER_ONE));//add for other tiers
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        /*registry.registerRecipeFiller(GriddleRecipe.class, ModRecipes.GRIDDLE_TYPE,
                GriddleDisplay::new);*/
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25), GriddleTierOneScreen.class,
                GriddleCatagory.GRIDDLE);//also do per tier swapping screen for each.
    }
}
