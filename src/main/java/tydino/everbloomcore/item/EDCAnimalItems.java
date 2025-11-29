package tydino.everbloomcore.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.entity.EDCEntities;

public class EDCAnimalItems extends EDCItems{

    // MANED WOLF //
    public static final Item MANED_WOLF = registerItem("maned_wolf-spawn_egg", setting -> new SpawnEggItem(EDCEntities.MANED_WOLF, setting));

    public static void registerModItems(){
        EverbloomDandalooCore.LOGGER.info("Registering Animal Items");
    }
}
