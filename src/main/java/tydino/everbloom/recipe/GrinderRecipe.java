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

public record GrinderRecipe(Ingredient inputItem, Ingredient inputItem2, ItemStack output) implements Recipe<GrinderRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        list.add(this.inputItem2);
        return list;
    }

    @Override
    public boolean matches(GrinderRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(GrinderRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<GrinderRecipeInput>> getSerializer() {
        return ModRecipes.GRINDER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<GrinderRecipeInput>> getType() {
        return ModRecipes.GRINDER_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<GrinderRecipe> {
        public static final MapCodec<GrinderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(GrinderRecipe::inputItem),
                Ingredient.CODEC.fieldOf("output").forGetter(GrinderRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(GrinderRecipe::output)
        ).apply(inst, GrinderRecipe::new));

        public static final PacketCodec<RegistryByteBuf, GrinderRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, GrinderRecipe::inputItem,
                        Ingredient.PACKET_CODEC, GrinderRecipe::inputItem2,
                        ItemStack.PACKET_CODEC, GrinderRecipe::output,
                        GrinderRecipe::new);

        @Override
        public MapCodec<GrinderRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GrinderRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
