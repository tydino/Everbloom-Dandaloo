package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
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

        //alumium
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ALUMIUM_ORE);

        //tin
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);

        //alloys

        //bronze
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRONZE_BLOCK);
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

        //alumium
        itemModelGenerator.register(ModItems.RAW_ALUMIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMIUM, Models.GENERATED);

        //tin
        itemModelGenerator.register(ModItems.RAWTIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);

        //alloys

        //bronze
        itemModelGenerator.register(ModItems.BRONZE, Models.GENERATED);

        //custom objects

        //custom items
        itemModelGenerator.register(ModItems.METAL_SHEET, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_BOWL, Models.GENERATED);

        // spawn eggs
        itemModelGenerator.register(ModItems.SPAWN_MALLARD_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_DAGGER_STABBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_TORTOISE, Models.GENERATED);
    }
}
