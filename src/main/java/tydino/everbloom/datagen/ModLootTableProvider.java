package tydino.everbloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.custom.bushes.TomatoBushBlock;
import tydino.everbloom.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        //power

        //power hectogons
        addDrop(ModBlocks.POWER_HECTOGON_TIER_ONE);

        //generators

        //solar panels
        addDrop(ModBlocks.SOLAR_PANEL_TIER_ONE);

        //batteries

        //power storages
        addDrop(ModBlocks.POWER_STORAGE_TIER_ONE);

        //power users
        addDrop(ModBlocks.ITEM_COMPRESSOR_TIER_ONE);
        addDrop(ModBlocks.GRINDER);

        //crops
        this.addDrop(ModBlocks.TOMATO_BUSH,
                block -> this.applyExplosionDecay(
                        block,LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_BUSH).properties(StatePredicate.Builder.create().exactMatch(TomatoBushBlock.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(ModItems.TOMATO))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                        )/*.pool(LootPool.builder().conditionally( //age 2 collectability
                                        BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_BUSH).properties(StatePredicate.Builder.create().exactMatch(TomatoBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(ModItems.TOMATO))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))*/
                                ));

        //eggs
        addDrop(ModBlocks.TORTOISE_EGG);
        addDrop(ModBlocks.MEGANEURA_EGG);
        addDrop(ModBlocks.HYPSILOPHODON_EGG);

        //cooking
        addDrop(ModBlocks.GRIDDLE_TIER_ONE);

        //ores

        //alumium
        addDrop(ModBlocks.ALUMIUM_BLOCK);

        addDrop(ModBlocks.ALUMIUM_ORE, oreDrops(ModBlocks.ALUMIUM_ORE, ModItems.RAW_ALUMIUM));
        addDrop(ModBlocks.DEEPSLATE_ALUMIUM_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_ALUMIUM_ORE, ModItems.RAW_ALUMIUM, 2, 4));

        //tin
        addDrop(ModBlocks.TIN_BLOCK);

        addDrop(ModBlocks.TIN_ORE, oreDrops(ModBlocks.TIN_ORE, ModItems.RAWTIN));
        addDrop(ModBlocks.DEEPSLATE_TIN_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAWTIN, 1, 3));

        //silicon
        addDrop(ModBlocks.SILICON_BLOCK);

        addDrop(ModBlocks.SILICON_ORE, oreDrops(ModBlocks.SILICON_ORE, ModItems.RAW_SILICON));

        //alloys
        addDrop(ModBlocks.BRONZE_BLOCK);

    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
