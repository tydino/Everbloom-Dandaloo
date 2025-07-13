package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import tydino.everbloom.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                //alumium
                .add(ModBlocks.ALUMIUM_BLOCK)
                .add(ModBlocks.ALUMIUM_ORE)
                .add(ModBlocks.DEEPSLATE_ALUMIUM_ORE)

                //tin
                .add(ModBlocks.TIN_BLOCK)
                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.DEEPSLATE_TIN_ORE)

                //silicon
                .add(ModBlocks.SILICON_BLOCK)
                .add(ModBlocks.SILICON_ORE)

                //alloys

                //bronze
                .add(ModBlocks.BRONZE_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                //tin
                .add(ModBlocks.DEEPSLATE_TIN_ORE)
                .add(ModBlocks.TIN_ORE)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                //alumium
                .add(ModBlocks.DEEPSLATE_ALUMIUM_ORE)
                .add(ModBlocks.ALUMIUM_ORE)
                //silicon
                .add(ModBlocks.SILICON_ORE)
        ;
    }
}
