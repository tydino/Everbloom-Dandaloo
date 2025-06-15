package tydino.everbloom.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;

public class GriddleCatagory implements DisplayCategory<BasicDisplay> {
    static final Identifier TEXTURE =
            Identifier.of(EverbloomDandaloo.MOD_ID, "textures/gui/griddle_tier_one/griddle_tier_one.png");
    public static final CategoryIdentifier<GriddleDisplay> GRIDDLE =
            CategoryIdentifier.of(EverbloomDandaloo.MOD_ID, "griddle");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return GRIDDLE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.everbloom.griddle");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.GRIDDLE_TIER_ONE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);//sets to bottom left
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y +34))
                .entries(display.getInputEntries().get(0)).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y +34))
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
