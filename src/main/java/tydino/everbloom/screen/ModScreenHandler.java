package tydino.everbloom.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.client.realms.exception.RealmsHttpException;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.screen.custom.FrotherScreenHandler;
import tydino.everbloom.screen.custom.GriddleTierOneScreenHandler;
import tydino.everbloom.screen.power.Grinder.GrinderScreenHandler;
import tydino.everbloom.screen.power.itemCompressor.ItemCompressorTierOneScreenHandler;

public class ModScreenHandler {

    //power
    public static final ScreenHandlerType<ItemCompressorTierOneScreenHandler> ITEM_COMPRESSOR_TIER_ONE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EverbloomDandaloo.MOD_ID, "item_compressor_tier_one_screen_handler"),
                    new ExtendedScreenHandlerType<>(ItemCompressorTierOneScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<GrinderScreenHandler> GRINDER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EverbloomDandaloo.MOD_ID, "grinder_screen_handler"),
                    new ExtendedScreenHandlerType<>(GrinderScreenHandler::new, BlockPos.PACKET_CODEC));

    //cooking
    public static final ScreenHandlerType<GriddleTierOneScreenHandler> GRIDDLE_TIER_ONE_SCREEN_HANDLER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EverbloomDandaloo.MOD_ID, "griddle-tier-one-screen-handler"),
                    new ExtendedScreenHandlerType<>(GriddleTierOneScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<FrotherScreenHandler> FROTHER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EverbloomDandaloo.MOD_ID, "frother-screen-handler"),
                    new ExtendedScreenHandlerType<>(FrotherScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        EverbloomDandaloo.LOGGER.info("Registering Screen Handlers");
    }
}
