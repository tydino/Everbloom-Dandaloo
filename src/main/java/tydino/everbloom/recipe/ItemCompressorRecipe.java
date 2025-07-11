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

public record ItemCompressorRecipe(Ingredient inputItem, ItemStack output) implements Recipe<ItemCompressorRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(ItemCompressorRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(ItemCompressorRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<ItemCompressorRecipeInput>> getSerializer() {
        return ModRecipes.ITEM_COMPRESSOR_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<ItemCompressorRecipeInput>> getType() {
        return ModRecipes.ITEM_COMPRESSOR_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<ItemCompressorRecipe> {
        public static final MapCodec<ItemCompressorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(ItemCompressorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ItemCompressorRecipe::output)
        ).apply(inst, ItemCompressorRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ItemCompressorRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ItemCompressorRecipe::inputItem,
                        ItemStack.PACKET_CODEC, ItemCompressorRecipe::output,
                        ItemCompressorRecipe::new);

        @Override
        public MapCodec<ItemCompressorRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ItemCompressorRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
