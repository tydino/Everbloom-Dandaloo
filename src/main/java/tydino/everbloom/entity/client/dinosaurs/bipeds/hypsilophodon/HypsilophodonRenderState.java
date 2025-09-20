package tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.dinosaurs.biped.hypsilophodon.HypsilophodonVariant;

public class HypsilophodonRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sittingdownAnimationState = new AnimationState();
    public final AnimationState standingupAnimationState = new AnimationState();

    public HypsilophodonVariant variant;
}
