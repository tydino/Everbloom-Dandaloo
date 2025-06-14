package tydino.everbloom.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup EverBloomDandalooItemGroup = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EverbloomDandaloo.MOD_ID, "item_group"),
            FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.DAGER_STABBER_MEAT))
                    .displayName(Text.translatable("itemgroup.everbloom.item_group"))
                    .entries((displayContext, entries) -> {

                        //food

                        //griddle
                        entries.add(ModItems.COOKED_EGG);

                        //entities

                        //dagger stabber
                        entries.add(ModItems.DAGER_STABBER_MEAT);
                        entries.add(ModItems.COOKED_DAGER_STABBER_MEAT);

                        //ores

                        //tin
                        entries.add(ModItems.RAWTIN);
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModBlocks.TIN_BLOCK);
                        entries.add(ModBlocks.TIN_ORE);
                        entries.add(ModBlocks.DEEPSLATE_TIN_ORE);
                    }).build());

    public static void registerItemGroups(){
        EverbloomDandaloo.LOGGER.info("registering item group");
    }
}
