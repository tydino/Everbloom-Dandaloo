package tydino.everbloomcore.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.entity.core_entity_brains.ManedWolfEntity;

public class EDCEntities {

    // MANED WOLF //
    public static final EntityType<ManedWolfEntity> MANED_WOLF = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandalooCore.MOD_ID, "maned_wolf"),
            EntityType.Builder.create(ManedWolfEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f,1.125f).build(//hitbox
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandalooCore.MOD_ID, "maned_wolf"))));

    public static void registerModEntities() {
        EverbloomDandalooCore.LOGGER.info("Registering Mod Entities");

        // MANED WOLF //
        FabricDefaultAttributeRegistry.register(EDCEntities.MANED_WOLF, ManedWolfEntity.createAttributes());
    }
}
