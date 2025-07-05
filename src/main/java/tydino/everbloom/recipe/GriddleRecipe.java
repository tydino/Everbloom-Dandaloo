package tydino.everbloom.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record GriddleRecipe(Ingredient inputItem, ItemStack output) implements Recipe<GriddleRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new GrowthChamberRecipe

    @Override
    public boolean matches(GriddleRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(GriddleRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<GriddleRecipeInput>> getSerializer() {
        return ModRecipes.GRIDDLE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<GriddleRecipeInput>> getType() {
        return ModRecipes.GRIDDLE_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<GriddleRecipe> {
        public static final MapCodec<GriddleRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(GriddleRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(GriddleRecipe::output)
        ).apply(inst, GriddleRecipe::new));

        public static final PacketCodec<RegistryByteBuf, GriddleRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, GriddleRecipe::inputItem,
                        ItemStack.PACKET_CODEC, GriddleRecipe::output,
                        GriddleRecipe::new);

        @Override
        public MapCodec<GriddleRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GriddleRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
