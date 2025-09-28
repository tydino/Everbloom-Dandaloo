package tydino.everbloom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import tydino.everbloom.block.bushes.EDCBushBlocks;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EverbloomDandalooCore.LOGGER.info("Registering client things");

        BlockRenderLayerMap.INSTANCE.putBlock(EDCBushBlocks.TOMATO_BUSH, RenderLayer.getCutout());
    }
}
