package tydino.everbloomcore.item.food;

import net.minecraft.item.*;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.block.bushes.EDCBushBlocks;
import tydino.everbloomcore.item.EDCItems;

public class EDCFoodItems extends EDCItems {
    //crops
    public static final Item TOMATO = registerItem("tomato", setting -> new BlockItem(EDCBushBlocks.TOMATO_BUSH, setting.food(EDCFoodComponents.TOMATO)));

    //grinder
    public static final Item TOMATO_SAUCE = registerItem("tomato_sauce", setting -> new Item(setting.food(EDCFoodComponents.TOMATO_SAUCE)));

    public static void registerModItems(){
        EverbloomDandalooCore.LOGGER.info("Registering Food Items");
    }
}
