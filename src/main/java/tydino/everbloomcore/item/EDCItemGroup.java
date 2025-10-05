package tydino.everbloomcore.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.block.powered.EDCPowerBlocks;
import tydino.everbloomcore.item.food.EDCFoodItems;

public class EDCItemGroup {
    public static final ItemGroup EverBloomDandalooItemGroup = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EverbloomDandalooCore.MOD_ID, "core"),
            FabricItemGroup.builder().icon(() -> new ItemStack(EDCFoodItems.TOMATO))
                    .displayName(Text.translatable("itemgroup.everbloom_core.item_group"))
                    .entries((displayContext, entries) -> {

                        entries.add(EDCFoodItems.TOMATO);
                        entries.add(EDCFoodItems.TOMATO_SAUCE);
                        entries.add(EDCItems.POWER_METER);
                        entries.add(EDCPowerBlocks.SHREDDER);

                    }).build());

    public static void registerItemGroup(){
        EverbloomDandalooCore.LOGGER.info("Registering Item Groups");
        EverbloomDandalooCore.LOGGER.info("Registering Core Item Group");
    }
}
