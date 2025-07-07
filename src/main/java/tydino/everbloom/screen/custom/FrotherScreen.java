package tydino.everbloom.screen.custom;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;

public class FrotherScreen extends HandledScreen<FrotherScreenHandler> {
    static final Identifier GUI_TEXTURE =
            Identifier.of(EverbloomDandaloo.MOD_ID, "textures/gui/frother/frother.png");
    static final Identifier ARROW_TEXTURE =
            Identifier.of(EverbloomDandaloo.MOD_ID, "textures/gui/arrow_progress_top_bottom.png");

    public FrotherScreen(FrotherScreenHandler handler, PlayerInventory inventory, Text title){
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(RenderLayer::getGuiTextured, ARROW_TEXTURE, x + 79, y + 31, 0, 0,
                    handler.getScaledArrowProgress(), 24, 16, 24);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
