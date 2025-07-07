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

public record FrotherRecipe(Ingredient inputItem, ItemStack output) implements Recipe<FrotherRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(FrotherRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(FrotherRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<FrotherRecipeInput>> getSerializer() {
        return ModRecipes.FROTHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<FrotherRecipeInput>> getType() {
        return ModRecipes.FROTHER_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<FrotherRecipe> {
        public static final MapCodec<FrotherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FrotherRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FrotherRecipe::output)
        ).apply(inst, FrotherRecipe::new));

        public static final PacketCodec<RegistryByteBuf, FrotherRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, FrotherRecipe::inputItem,
                        ItemStack.PACKET_CODEC, FrotherRecipe::output,
                        FrotherRecipe::new);

        @Override
        public MapCodec<FrotherRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, FrotherRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
