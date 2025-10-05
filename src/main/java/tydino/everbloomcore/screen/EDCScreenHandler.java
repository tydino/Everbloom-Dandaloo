package tydino.everbloomcore.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.screen.machine.shredder.ShredderScreenHandler;

public class EDCScreenHandler {

    public static final ScreenHandlerType<ShredderScreenHandler> SHREDDER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EverbloomDandalooCore.MOD_ID, "shredder_screen_handler"),
                    new ExtendedScreenHandlerType<>(ShredderScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        EverbloomDandalooCore.LOGGER.info("Registering Screen Handlers");
    }
}
