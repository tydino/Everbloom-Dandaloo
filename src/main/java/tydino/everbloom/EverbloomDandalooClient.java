package tydino.everbloom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.client.*;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus.CompsognathusModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus.CompsognathusRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon.PteranodonModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon.PteranodonRenderer;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraModel;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraRenderer;
import tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusModel;
import tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusRenderer;
import tydino.everbloom.entity.client.dinosaurs.scarabs.bronze.BronzeScarabModel;
import tydino.everbloom.entity.client.dinosaurs.scarabs.bronze.BronzeScarabRenderer;
import tydino.everbloom.entity.custom.dinosaurs.biped.pteranodon.PteranodonEntity;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.screen.custom.FrotherScreen;
import tydino.everbloom.screen.custom.GriddleTierOneScreen;
import tydino.everbloom.screen.power.Grinder.GrinderScreen;
import tydino.everbloom.screen.power.itemCompressor.ItemCompressorTierOneScreen;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        //power
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWER_STORAGE_TIER_ONE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ITEM_COMPRESSOR_TIER_ONE, RenderLayer.getCutout());
        HandledScreens.register(ModScreenHandler.ITEM_COMPRESSOR_TIER_ONE_SCREEN_HANDLER, ItemCompressorTierOneScreen::new);

        HandledScreens.register(ModScreenHandler.GRINDER_SCREEN_HANDLER, GrinderScreen::new);

        //crops

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_BUSH, RenderLayer.getCutout());

        //mobs

        //mallard
        EntityModelLayerRegistry.registerModelLayer(MallardModel.MALLARD, MallardModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MALLARD, MallardRenderer::new);

        //dagger stabber
        EntityModelLayerRegistry.registerModelLayer(DaggerStabberModel.DAGGER_STABBER, DaggerStabberModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DAGGER_STABBER, DaggerStabberRenderer::new);

        //tortoise
        EntityModelLayerRegistry.registerModelLayer(TortoiseModel.TORTOISE, TortoiseModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TORTOISE, TortoiseRenderer::new);

        //toad
        EntityModelLayerRegistry.registerModelLayer(ToadModel.TOAD, ToadModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TOAD, ToadRenderer::new);

        //dinosaurs

        //scarabs
        EntityModelLayerRegistry.registerModelLayer(BronzeScarabModel.SCARAB, BronzeScarabModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BRONZE_SCARAB, BronzeScarabRenderer::new);

        //insectoids

        //meganeura
        EntityModelLayerRegistry.registerModelLayer(MeganeuraModel.MEGANEURA, MeganeuraModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MEGANEURA, MeganeuraRenderer::new);
        EntityRendererRegistry.register(ModEntities.AGGRESSIVE_MEGANEURA, MeganeuraRenderer::new);

        //bipeds

        //hypsilophodon
        EntityModelLayerRegistry.registerModelLayer(HypsilophodonModel.HYPSILOPHODON, HypsilophodonModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HYPSILOPHODON, HypsilophodonRenderer::new);
        EntityRendererRegistry.register(ModEntities.HYPSILOPHODON_UNTAMABLE, HypsilophodonRenderer::new);

        //compsognathus
        EntityModelLayerRegistry.registerModelLayer(CompsognathusModel.COMPSOGNATHUS, CompsognathusModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.COMPSOGNATHUS, CompsognathusRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPSOGNATHUS_UNTAMABLE, CompsognathusRenderer::new);

        //archaeopteryx
        EntityModelLayerRegistry.registerModelLayer(ArchaeopteryxModel.ARCHAEOPTERYX, ArchaeopteryxModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ARCHAEOPTERYX, ArchaeopteryxRenderer::new);
        EntityRendererRegistry.register(ModEntities.ARCHAEOPTERYX_UNTAMABLE, ArchaeopteryxRenderer::new);

        //pteranodon
        EntityModelLayerRegistry.registerModelLayer(PteranodonModel.PTERANODON, PteranodonModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PTERANODON, PteranodonRenderer::new);

        //quadrepeds

        //parasaurolophus
        EntityModelLayerRegistry.registerModelLayer(ParasaurolophusModel.PARASAUROLOPHUS, ParasaurolophusModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PARASAUROLOPHUS, ParasaurolophusRenderer::new);
        EntityRendererRegistry.register(ModEntities.PARASAUROLOPHUS_UNTAMABLE, ParasaurolophusRenderer::new);

        //entities

        //cooking
        HandledScreens.register(ModScreenHandler.GRIDDLE_TIER_ONE_SCREEN_HANDLER_SCREEN_HANDLER, GriddleTierOneScreen::new);
        HandledScreens.register(ModScreenHandler.FROTHER_SCREEN_HANDLER, FrotherScreen::new);
    }
}
