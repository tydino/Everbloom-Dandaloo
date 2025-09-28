package tydino.everbloom;

import net.fabricmc.api.ClientModInitializer;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EverbloomDandalooCore.LOGGER.info("Registering client things");

    }
}
