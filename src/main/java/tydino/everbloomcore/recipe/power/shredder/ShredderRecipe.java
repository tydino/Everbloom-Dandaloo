package tydino.everbloomcore.recipe.power.shredder;

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
import tydino.everbloomcore.recipe.EDCRecipes;

public record ShredderRecipe(Ingredient inputItem, Ingredient inputItem2, ItemStack output) implements Recipe<ShredderRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        list.add(this.inputItem2);
        return list;
    }

    @Override
    public boolean matches(ShredderRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(ShredderRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<ShredderRecipeInput>> getSerializer() {
        return EDCRecipes.SHREDDER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<ShredderRecipeInput>> getType() {
        return EDCRecipes.SHREDDER_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<ShredderRecipe> {
        public static final MapCodec<ShredderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(ShredderRecipe::inputItem),
                Ingredient.CODEC.fieldOf("output").forGetter(ShredderRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(ShredderRecipe::output)
        ).apply(inst, ShredderRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ShredderRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ShredderRecipe::inputItem,
                        Ingredient.PACKET_CODEC, ShredderRecipe::inputItem2,
                        ItemStack.PACKET_CODEC, ShredderRecipe::output,
                        ShredderRecipe::new);

        @Override
        public MapCodec<ShredderRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ShredderRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
