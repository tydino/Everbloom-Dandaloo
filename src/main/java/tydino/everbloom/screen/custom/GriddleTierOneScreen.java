package tydino.everbloom.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import tydino.everbloom.EverbloomDandaloo;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GriddleTierOneScreen extends HandledScreen<GriddleTierOneScreenHandler>{
    static final Identifier GUI_TEXTURE =
            Identifier.of(EverbloomDandaloo.MOD_ID, "textures/gui/griddle_tier_one/griddle_tier_one.png");
    static final Identifier ARROW_TEXTURE =
            Identifier.of(EverbloomDandaloo.MOD_ID, "textures/gui/arrow_progress_left_right.png");

    public GriddleTierOneScreen(GriddleTierOneScreenHandler handler, PlayerInventory inventory, Text title){
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 73, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
