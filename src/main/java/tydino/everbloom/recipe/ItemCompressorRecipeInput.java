package tydino.everbloom.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ItemCompressorRecipeInput(ItemStack input, ItemStack inputTwo) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot == 0) {
            return input;
        } else if(slot ==1){
            return inputTwo;
        }else {
            return null;
        }
    }

    @Override
    public int size() {
        return 1;
    }
}
