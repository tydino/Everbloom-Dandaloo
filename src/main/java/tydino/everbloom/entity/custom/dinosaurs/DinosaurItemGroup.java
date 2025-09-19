package tydino.everbloom.entity.custom.dinosaurs;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;

public class DinosaurItemGroup {

    public static final ItemGroup DinosaurItemGroup = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EverbloomDandaloo.MOD_ID, "dino_item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(DinosaurItems.SILVER_SCARAB))
                    .displayName(Text.translatable("itemgroup.everbloom.dino_item_group"))
                    .entries((displayContext, entries) -> {

                        entries.add(DinosaurBlocks.MEGANEURA_EGG);
                        entries.add(DinosaurBlocks.HYPSILOPHODON_EGG);
                        entries.add(DinosaurBlocks.COMPSOGNATHUS_EGG);
                        entries.add(DinosaurBlocks.ARCHAEOPTERYX_EGG);
                        entries.add(DinosaurBlocks.PARASAUROLOPHUS_EGG);
                        entries.add(DinosaurBlocks.PTERANODON_EGG);

                        //scarabs
                        entries.add(DinosaurItems.BRONZE_SCARAB);
                        entries.add(DinosaurItems.SILVER_SCARAB);

                        //docile
                        entries.add(DinosaurItems.SPAWN_MEGANEURA);
                        entries.add(DinosaurItems.SPAWN_HYPSILOPHODON);
                        entries.add(DinosaurItems.SPAWN_COPSOGNATHUS);
                        entries.add(DinosaurItems.SPAWN_ARCHAEOPTRYX);
                        entries.add(DinosaurItems.SPAWN_PARASAUROLOPHUS);
                        entries.add(DinosaurItems.SPAWN_PTERANODON);
                        //nondocile
                        entries.add(DinosaurItems.SPAWN_AGGRESSIVE_MEGANEURA);
                        entries.add(DinosaurItems.SPAWN_UNTAMABLE_HYPSILOPHODON);
                        entries.add(DinosaurItems.SPAWN_UNTAMABLE_COPSOGNATHUS);
                        entries.add(DinosaurItems.SPAWN_UNTAMABLE_ARCHAEOPTRYX);
                        entries.add(DinosaurItems.SPAWN_UNTAMABLE_PARASAUROLOPHUS);
                        entries.add(DinosaurItems.SPAWN_UNTAMABLE_PTERANODON);
                    }).build());

    public static void dinoItemGroup(){
        EverbloomDandaloo.LOGGER.info("Registering dinosaur items");
    }
}
