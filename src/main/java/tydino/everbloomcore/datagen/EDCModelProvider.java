package tydino.everbloomcore.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import tydino.everbloomcore.block.bushes.EDCBushBlocks;
import tydino.everbloomcore.block.bushes.TomatoBushBlock;
import tydino.everbloomcore.item.EDCAnimalItems;
import tydino.everbloomcore.item.EDCItems;
import tydino.everbloomcore.item.food.EDCFoodItems;

public class EDCModelProvider extends FabricModelProvider {
    public EDCModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // CROPS //
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(EDCBushBlocks.TOMATO_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                TomatoBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // POWER //
        itemModelGenerator.register(EDCItems.POWER_METER, Models.GENERATED);

        // GRINDER //
        itemModelGenerator.register(EDCFoodItems.TOMATO_SAUCE, Models.GENERATED);

        // ANIMALS //

        // MANED WOLF //
        itemModelGenerator.register(EDCAnimalItems.MANED_WOLF, Models.GENERATED);
    }
}
