package tydino.everbloomcore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import tydino.everbloomcore.block.bushes.EDCBushBlocks;
import tydino.everbloomcore.entity.EDCEntities;
import tydino.everbloomcore.entity.core_entity_client.manedwolf.ManedWolfModel;
import tydino.everbloomcore.entity.core_entity_client.manedwolf.ManedWolfRenderer;
import tydino.everbloomcore.screen.EDCScreenHandler;
import tydino.everbloomcore.screen.machine.shredder.ShredderScreen;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EverbloomDandalooCore.LOGGER.info("Registering client things");

        HandledScreens.register(EDCScreenHandler.SHREDDER_SCREEN_HANDLER, ShredderScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(EDCBushBlocks.TOMATO_BUSH, RenderLayer.getCutout());

        // MOBS //

        // MANED WOLF //
        EntityModelLayerRegistry.registerModelLayer(ManedWolfModel.MANED_WOLF, ManedWolfModel::getTexturedModelData);
        EntityRendererRegistry.register(EDCEntities.MANED_WOLF, ManedWolfRenderer::new);
    }
}
