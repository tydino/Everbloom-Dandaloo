package tydino.everbloom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.client.DaggerStabberModel;
import tydino.everbloom.entity.client.DaggerStabberRenderer;
import tydino.everbloom.entity.client.MallardModel;
import tydino.everbloom.entity.client.MallardRenderer;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.screen.custom.GriddleTierOneScreen;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        //mobs

        //mallard
        EntityModelLayerRegistry.registerModelLayer(MallardModel.MALLARD, MallardModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MALLARD, MallardRenderer::new);

        //dagger stabber
        EntityModelLayerRegistry.registerModelLayer(DaggerStabberModel.DAGGER_STABBER, DaggerStabberModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DAGGER_STABBER, DaggerStabberRenderer::new);

        //entities

        //cooking
        HandledScreens.register(ModScreenHandler.GRIDDLE_TIER_ONE_SCREEN_HANDLER_SCREEN_HANDLER, GriddleTierOneScreen::new);
    }
}
