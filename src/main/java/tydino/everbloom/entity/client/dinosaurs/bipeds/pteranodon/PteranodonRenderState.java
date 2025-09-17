package tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.custom.dinosaurs.biped.pteranodon.PteranodonVariant;

public class PteranodonRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sittingdownAnimationState = new AnimationState();
    public final AnimationState standingupAnimationState = new AnimationState();

    public PteranodonVariant variant;
}
