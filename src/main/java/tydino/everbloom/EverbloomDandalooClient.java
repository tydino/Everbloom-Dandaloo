package tydino.everbloom;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.screen.custom.GriddleTierOneScreen;

public class EverbloomDandalooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //entities

        //cooking
        HandledScreens.register(ModScreenHandler.GRIDDLE_TIER_ONE_SCREEN_HANDLER_SCREEN_HANDLER, GriddleTierOneScreen::new);
    }
}
