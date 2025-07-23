package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.custom.ToadBurrow;
import tydino.everbloom.block.custom.bushes.TomatoBushBlock;
import tydino.everbloom.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {//ones that rotate see the commented griddle

        //power hectogons
        blockStateModelGenerator.registerSimpleState(ModBlocks.POWER_HECTOGON_TIER_ONE);

        //power storages
        blockStateModelGenerator.registerSimpleState(ModBlocks.POWER_STORAGE_TIER_ONE);

        //crops
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.TOMATO_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                TomatoBushBlock.AGE, 0, 1, 2, 3);

        //eggs
        blockStateModelGenerator.registerSimpleState(ModBlocks.TORTOISE_EGG);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MEGANEURA_EGG);
        blockStateModelGenerator.registerSimpleState(ModBlocks.HYPSILOPHODON_EGG);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COMPSOGNATHUS_EGG);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ARCHAEOPTERYX_EGG);

        //animal blocks
        Identifier ToadBurrow_empty = TexturedModel.CUBE_ALL.upload(ModBlocks.TOAD_BURROW, blockStateModelGenerator.modelCollector);
        Identifier ToadBurrow_filled = blockStateModelGenerator.createSubModel(ModBlocks.TOAD_BURROW, "_filled", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.TOAD_BURROW)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(ToadBurrow.FILLED, ToadBurrow_filled, ToadBurrow_empty)));

        //cooking
        //blockStateModelGenerator.registerSimpleState(ModBlocks.GRIDDLE_TIER_ONE); check the assets/blockstates folder the item is generate automatically after running datagen
        blockStateModelGenerator.registerSimpleState(ModBlocks.FROTHER);

        //ores

        //alumium
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ALUMIUM_ORE);

        //tin
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);

        //silicon
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILICON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILICON_ORE);

        //alloys

        //bronze
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRONZE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        //power
        itemModelGenerator.register(ModItems.POWER_METER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CIRCUIT_BOARD_BLANK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CIRCUIT_BOARD_INSCRIBED, Models.GENERATED);

        //griddle
        itemModelGenerator.register(ModItems.COOKED_EGG, Models.GENERATED);

        //frother
        itemModelGenerator.register(ModItems.BUTTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUTTER_MELTED, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLOUR, Models.GENERATED);

        //uncatagorized
        itemModelGenerator.register(ModItems.DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIZZA_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO_SAUCE, Models.GENERATED);

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

        //silicon
        itemModelGenerator.register(ModItems.SILICON, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SILICON, Models.GENERATED);

        //alloys

        //bronze
        itemModelGenerator.register(ModItems.BRONZE, Models.GENERATED);

        //custom objects

        //custom items

        //just tin
        itemModelGenerator.register(ModItems.METAL_SHEET, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_BOWL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.COMPRESSOR_BANK, Models.GENERATED);

        //just silicon
        itemModelGenerator.register(ModItems.SILICON_CONDUCTOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOLAR_CELL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOLAR_SHEET, Models.GENERATED);

        //just bronze
        itemModelGenerator.register(ModItems.BRONZE_CONDUCTOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.WIRE, Models.GENERATED);

        // spawn eggs
        itemModelGenerator.register(ModItems.SPAWN_MALLARD_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_DAGGER_STABBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_TORTOISE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_TOAD, Models.GENERATED);
        //dinosaurs

        //insectoids
        itemModelGenerator.register(ModItems.SPAWN_MEGANEURA, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_AGGRESSIVE_MEGANEURA, Models.GENERATED);

        //bipeds
        itemModelGenerator.register(ModItems.SPAWN_HYPSILOPHODON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_UNTAMABLE_HYPSILOPHODON, Models.GENERATED);

        itemModelGenerator.register(ModItems.SPAWN_COPSOGNATHUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_UNTAMABLE_COPSOGNATHUS, Models.GENERATED);

        //archaeopteryx
        itemModelGenerator.register(ModItems.SPAWN_ARCHAEOPTRYX, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWN_UNTAMABLE_ARCHAEOPTRYX, Models.GENERATED);

        //scarabs
        itemModelGenerator.register(ModItems.SILVER_SCARAB, Models.GENERATED);
    }
}
