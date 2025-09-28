package tydino.everbloom.item.food;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandalooCore;
import tydino.everbloom.block.bushes.EDCBushBlocks;
import tydino.everbloom.item.ModItems;

public class EDCFoodItems extends ModItems {
    //crops
    public static final Item TOMATO = registerItem("tomato", setting -> new BlockItem(EDCBushBlocks.TOMATO_BUSH, setting.food(EDCFoodComponents.TOMATO)));

    public static void registerModItems(){
        EverbloomDandalooCore.LOGGER.info("Registering Food Items");
    }
}
