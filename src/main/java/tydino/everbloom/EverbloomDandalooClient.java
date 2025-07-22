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
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonRenderer;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraModel;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraRenderer;
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

        //entities

        //cooking
        HandledScreens.register(ModScreenHandler.GRIDDLE_TIER_ONE_SCREEN_HANDLER_SCREEN_HANDLER, GriddleTierOneScreen::new);
        HandledScreens.register(ModScreenHandler.FROTHER_SCREEN_HANDLER, FrotherScreen::new);
    }
}
