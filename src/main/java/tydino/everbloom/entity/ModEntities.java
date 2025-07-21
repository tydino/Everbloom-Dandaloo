package tydino.everbloom.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
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
import tydino.everbloom.entity.custom.ToadEntity;
import tydino.everbloom.entity.custom.TortoiseEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.AggressiveMeganeuraEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.MeganeuraEntity;

public class ModEntities {

    //register keys for mobs

    static final RegistryKey<EntityType<?>> MALLARD_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "mallard"));

    static final RegistryKey<EntityType<?>> DAGGER_STABBER_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "dagger-stabber"));

    static final RegistryKey<EntityType<?>> TORTOISE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "tortoise"));

    static final RegistryKey<EntityType<?>> TOAD_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "toad"));

    //dinosaurs

    //insectoids

    //meganeura
    static final RegistryKey<EntityType<?>> MEGANEURA_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "meganeura"));
    static final RegistryKey<EntityType<?>> MEGANEURA_AGGRESSIVE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "aggressive_meganeura"));

    //bipeds

    //hypsilophodon
    static final RegistryKey<EntityType<?>> HYPSILOPHODON_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon"));

    ///registering mobs         /*       stop        here      */

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

    //tortoise

    public static final EntityType<TortoiseEntity> TORTOISE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "tortoise"),
            EntityType.Builder.create(TortoiseEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.5f).build(TORTOISE_KEY));

    //toad
    public static final EntityType<ToadEntity> TOAD = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "toad"),
            EntityType.Builder.create(ToadEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.625f, 0.5f).build(TOAD_KEY));

    //dinosaurs

    //insectoids

    //meganeura
    public static final EntityType<MeganeuraEntity> MEGANEURA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "meganeura"),
            EntityType.Builder.create(MeganeuraEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.25f).build(MEGANEURA_KEY));
    public static final EntityType<AggressiveMeganeuraEntity> AGGRESSIVE_MEGANEURA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "aggressive_meganeura"),
            EntityType.Builder.create(AggressiveMeganeuraEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.25f).build(MEGANEURA_AGGRESSIVE_KEY));

    //bipeds

    //hypsilophodon
    public static final EntityType<HypsilophodonEntity> HYPSILOPHODON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon"),
            EntityType.Builder.create(HypsilophodonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.65f).build(HYPSILOPHODON_KEY));

    public static void registerModEntities() {
        EverbloomDandaloo.LOGGER.info("Registering Mod Entities");

        //mallard

        FabricDefaultAttributeRegistry.register(ModEntities.MALLARD, MallardEntity.createMallardAttributes());

        //dagger stabber

        FabricDefaultAttributeRegistry.register(ModEntities.DAGGER_STABBER, DaggerStabberEntity.createStabberDaggerAttributes());

        //tortoise

        FabricDefaultAttributeRegistry.register(ModEntities.TORTOISE, TortoiseEntity.createTortoiseAttributes());

        //toad

        FabricDefaultAttributeRegistry.register(ModEntities.TOAD, ToadEntity.createToadAttributes());

        //dinosaurs

        //insectoids

        //meganeura
        FabricDefaultAttributeRegistry.register(ModEntities.MEGANEURA, MeganeuraEntity.createMeganeuraAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.AGGRESSIVE_MEGANEURA, AggressiveMeganeuraEntity.createMeganeuraAttributes());

        //bipeds

        //hypsilophodon
        FabricDefaultAttributeRegistry.register(ModEntities.HYPSILOPHODON, HypsilophodonEntity.createHypsilophodonAttributes());
    }
}
