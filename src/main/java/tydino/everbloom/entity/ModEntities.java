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
import tydino.everbloom.entity.custom.DaggerStabberEntity;
import tydino.everbloom.entity.custom.MallardEntity;

public class ModEntities {

    //register keys for mobs

    static final RegistryKey<EntityType<?>> MALLARD_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "mallard"));
    static final RegistryKey<EntityType<?>> DAGGER_STABBER_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "dagger-stabber"));

    //registering mobs

    //mallard

    public static final EntityType<MallardEntity> MALLARD = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "mallard"),
            EntityType.Builder.create(MallardEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.375f, 0.75f).build(MALLARD_KEY));//this sets hit box size

    //dagger stabber
    public static final EntityType<DaggerStabberEntity> DAGGER_STABBER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "dagger-stabber"),
            EntityType.Builder.create(DaggerStabberEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.375f, 1.75f).build(DAGGER_STABBER_KEY));

    public static void registerModEntities() {
        EverbloomDandaloo.LOGGER.info("Registering Mod Entities");

        //mallard

        FabricDefaultAttributeRegistry.register(ModEntities.MALLARD, MallardEntity.createMallardAttributes());

        //dagger stabber

        FabricDefaultAttributeRegistry.register(ModEntities.DAGGER_STABBER, DaggerStabberEntity.createStabberDaggerAttributes());

    }
}
