package tydino.everbloom.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodComponent CookedEgg = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build();

    //mobs
    public static FoodComponent RawMallard = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static ConsumableComponent RawMallardEffect = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 500), 0.75f)).build();

    public static final FoodComponent CookedMallard = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build();

    public static FoodComponent RawDaggerStabber = new FoodComponent.Builder().nutrition(4).saturationModifier(0.25f).build();
    public static ConsumableComponent RawDaggerStabberEffect = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 500), 0.75f)).build();

    public static final FoodComponent CookedDaggerStabber = new FoodComponent.Builder().nutrition(10).saturationModifier(1f).build();
}
