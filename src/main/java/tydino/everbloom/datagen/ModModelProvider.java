package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //cooking
        blockStateModelGenerator.registerSimpleState(ModBlocks.GRIDDLE_TIER_ONE);

        //ores

        //tin
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        //food

        //griddle
        itemModelGenerator.register(ModItems.COOKED_EGG, Models.GENERATED);

        //entities

        //mallard
        itemModelGenerator.register(ModItems.MALLARD_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.MALLARD_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_MALLARD_MEAT, Models.GENERATED);

        //dagger stabber
        itemModelGenerator.register(ModItems.DAGER_STABBER_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_DAGER_STABBER_MEAT, Models.GENERATED);

        //ores

        //tin
        itemModelGenerator.register(ModItems.RAWTIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
    }
}
