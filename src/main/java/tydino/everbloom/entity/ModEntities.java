package tydino.everbloom.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.MallardEntity;

public class ModEntities {

    //mallard

    public static final EntityType<MallardEntity> MALLARD = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "mallard"),
            EntityType.Builder.create(MallardEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.375f, 0.75f).build());//this sets hit box size

    public static void registerModEntities() {
        EverbloomDandaloo.LOGGER.info("Registering Mod Entities");

        //mallard

        FabricDefaultAttributeRegistry.register(ModEntities.MALLARD, MallardEntity.createMallardAttributes());
    }
}
