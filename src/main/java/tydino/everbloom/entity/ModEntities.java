package tydino.everbloom.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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
import tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx.ArchaeopteryxEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx.NontamableArchaeoptryxEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.NontamableCompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.NontamableHypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.AggressiveMeganeuraEntity;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.meganeura.MeganeuraEntity;
import tydino.everbloom.item.ModItems;

public class ModEntities {

    /*public static final Ingredient CARNIVOROUS_BASIC = Ingredient.ofItems(
            Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.MUTTON, Items.COOKED_MUTTON, Items.RABBIT, Items.COOKED_RABBIT, ModItems.MALLARD_MEAT, ModItems.COOKED_MALLARD_MEAT, ModItems.DAGER_STABBER_MEAT, ModItems.COOKED_DAGER_STABBER_MEAT
    );*/

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
    static final RegistryKey<EntityType<?>> HYPSILOPHODON_UNTAMABLE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon_untamable"));

    //compsognathus
    static final RegistryKey<EntityType<?>> COMPSOGNATHUS_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus"));
    static final RegistryKey<EntityType<?>> COMPSOGNATHUS_UNTAMABLE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus_untamable"));

    //archaeopteryx
    static final RegistryKey<EntityType<?>> ARCHAEOPTERYX_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx"));
    static final RegistryKey<EntityType<?>> ARCHAEOPTERYX_UNTAMABLE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx_untamable"));

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
    public static final EntityType<NontamableHypsilophodonEntity> HYPSILOPHODON_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon_untamable"),
            EntityType.Builder.create(NontamableHypsilophodonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.65f).build(HYPSILOPHODON_UNTAMABLE_KEY));

    //compsognathus
    public static EntityType<CompsognathusEntity> COMPSOGNATHUS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus"),
            EntityType.Builder.create(CompsognathusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build(COMPSOGNATHUS_KEY));
    public static final EntityType<NontamableCompsognathusEntity> COMPSOGNATHUS_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus_untamable"),
            EntityType.Builder.create(NontamableCompsognathusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.65f).build(COMPSOGNATHUS_UNTAMABLE_KEY));

    //archaeopteryx
    public static EntityType<ArchaeopteryxEntity> ARCHAEOPTERYX = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx"),
            EntityType.Builder.create(ArchaeopteryxEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build(ARCHAEOPTERYX_KEY));

    public static EntityType<NontamableArchaeoptryxEntity> ARCHAEOPTERYX_UNTAMABLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx_untamable"),
            EntityType.Builder.create(NontamableArchaeoptryxEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build(ARCHAEOPTERYX_UNTAMABLE_KEY));

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

        //hypsilophodon (set to gold scarab where noted once added)
        FabricDefaultAttributeRegistry.register(ModEntities.HYPSILOPHODON, HypsilophodonEntity.createHypsilophodonAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HYPSILOPHODON_UNTAMABLE, NontamableHypsilophodonEntity.createHypsilophodonAttributes());

        //compsognathus
        FabricDefaultAttributeRegistry.register(ModEntities.COMPSOGNATHUS, CompsognathusEntity.createCompsognathusAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.COMPSOGNATHUS_UNTAMABLE, NontamableCompsognathusEntity.createCompsognathusAttributes());

        //archaeopteryx
        FabricDefaultAttributeRegistry.register(ModEntities.ARCHAEOPTERYX, ArchaeopteryxEntity.createArchaeopteryxAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ARCHAEOPTERYX_UNTAMABLE, NontamableArchaeoptryxEntity.createArchaeoptryxAttributes());
    }
}
