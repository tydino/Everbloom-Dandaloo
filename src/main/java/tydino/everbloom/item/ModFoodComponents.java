package tydino.everbloom.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CookedEgg = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build();
    public static final FoodComponent CookedDaggerStabber = new FoodComponent.Builder().nutrition(10).saturationModifier(1f).build();
}
