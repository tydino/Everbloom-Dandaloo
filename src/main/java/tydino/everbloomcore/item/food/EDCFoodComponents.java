package tydino.everbloomcore.item.food;

import net.minecraft.component.type.FoodComponent;

public class EDCFoodComponents {
    //crops
    public static FoodComponent TOMATO = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build();

    //grinder
    public static final FoodComponent TOMATO_SAUCE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.5f).build();
}
