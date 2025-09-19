package tydino.everbloom.entity.custom.dinosaurs;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx.ArchaeopteryxEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx.NontamableArchaeoptryxEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.NontamableCompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.NontamableHypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.pteranodon.PteranodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.AggressiveMeganeuraEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.MeganeuraEntity;
import tydino.everbloom.entity.custom.dinosaurs.quadrepeds.parasaurolophus.NontamableParasaurolophusEntity;
import tydino.everbloom.entity.custom.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusEntity;
import tydino.everbloom.entity.custom.dinosaurs.scarab.BronzeScarabEntity;

public class DinosaurEntities {

    /// DINOSAUR VARIBLES ///

    //scarabs
    public static final EntityType<BronzeScarabEntity> BRONZE_SCARAB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "bronze_scarab"),
            EntityType.Builder.create(BronzeScarabEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.5f, 0.25f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "bronze_scarab"))));

    //insectoids

    //meganeura
    public static final EntityType<MeganeuraEntity> MEGANEURA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "meganeura"),
            EntityType.Builder.create(MeganeuraEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.25f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "meganeura"))));

    public static final EntityType<AggressiveMeganeuraEntity> AGGRESSIVE_MEGANEURA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "aggressive_meganeura"),
            EntityType.Builder.create(AggressiveMeganeuraEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.25f, 0.25f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "aggressive_meganeura"))));

    //bipeds

    //hypsilophodon
    public static final EntityType<HypsilophodonEntity> HYPSILOPHODON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon"),
            EntityType.Builder.create(HypsilophodonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.65f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon"))));

    public static final EntityType<NontamableHypsilophodonEntity> HYPSILOPHODON_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon_untamable"),
            EntityType.Builder.create(NontamableHypsilophodonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.65f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon_untamable"))));

    //compsognathus
    public static EntityType<CompsognathusEntity> COMPSOGNATHUS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus"),
            EntityType.Builder.create(CompsognathusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus"))));

    public static final EntityType<NontamableCompsognathusEntity> COMPSOGNATHUS_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus_untamable"),
            EntityType.Builder.create(NontamableCompsognathusEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.5f, 0.65f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus_untamable"))));

    //archaeopteryx
    public static EntityType<ArchaeopteryxEntity> ARCHAEOPTERYX = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx"),
            EntityType.Builder.create(ArchaeopteryxEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx"))));

    public static EntityType<NontamableArchaeoptryxEntity> ARCHAEOPTERYX_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx_untamable"),
            EntityType.Builder.create(NontamableArchaeoptryxEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.25f, 0.5f).build(RegistryKey.of(
                            RegistryKeys.ENTITY_TYPE,
                            Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx_untamable"))));

    //pteranodon
    public static EntityType<PteranodonEntity> PTERANODON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "pteranodon"),
            EntityType.Builder.create(PteranodonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.75f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of(EverbloomDandaloo.MOD_ID, "pteranodon"))));

    //quadreped

    //parasaurolophus
    public static EntityType<ParasaurolophusEntity> PARASAUROLOPHUS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "parasaurolophus"),
            EntityType.Builder.create(ParasaurolophusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2f, 3f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                    Identifier.of(EverbloomDandaloo.MOD_ID, "parasaurolophus"))));

    public static EntityType<NontamableParasaurolophusEntity> PARASAUROLOPHUS_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "parasaurolophus_untamable"),
            EntityType.Builder.create(NontamableParasaurolophusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2f, 3f).build(
                            RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of(EverbloomDandaloo.MOD_ID, "parasaurolophus_untamable"))));

    /// REGISTER DINOSAURS ///

    public static void registerDinosaurs(){
        EverbloomDandaloo.LOGGER.info("Registering Dinosaurs");
        //Scarabs
        FabricDefaultAttributeRegistry.register(BRONZE_SCARAB, BronzeScarabEntity.createBronzeScarabAttributes());

        //insectoids

        //meganeura
        FabricDefaultAttributeRegistry.register(MEGANEURA, MeganeuraEntity.createMeganeuraAttributes());
        FabricDefaultAttributeRegistry.register(AGGRESSIVE_MEGANEURA, AggressiveMeganeuraEntity.createMeganeuraAttributes());

        //bipeds

        //hypsilophodon (set to gold scarab where noted once added)
        FabricDefaultAttributeRegistry.register(HYPSILOPHODON, HypsilophodonEntity.createHypsilophodonAttributes());
        FabricDefaultAttributeRegistry.register(HYPSILOPHODON_UNTAMABLE, NontamableHypsilophodonEntity.createHypsilophodonAttributes());

        //compsognathus
        FabricDefaultAttributeRegistry.register(COMPSOGNATHUS, CompsognathusEntity.createCompsognathusAttributes());
        FabricDefaultAttributeRegistry.register(COMPSOGNATHUS_UNTAMABLE, NontamableCompsognathusEntity.createCompsognathusAttributes());

        //archaeopteryx
        FabricDefaultAttributeRegistry.register(ARCHAEOPTERYX, ArchaeopteryxEntity.createArchaeopteryxAttributes());
        FabricDefaultAttributeRegistry.register(ARCHAEOPTERYX_UNTAMABLE, NontamableArchaeoptryxEntity.createArchaeoptryxAttributes());

        //pteranodon
        FabricDefaultAttributeRegistry.register(PTERANODON, PteranodonEntity.createPteradonAttributes());

        //quadrepeds

        //parasaurolophus
        FabricDefaultAttributeRegistry.register(PARASAUROLOPHUS, ParasaurolophusEntity.createParasaurolophusAttributes());
        FabricDefaultAttributeRegistry.register(PARASAUROLOPHUS_UNTAMABLE, NontamableParasaurolophusEntity.createParasaurolophusAttributes());

    }
}
