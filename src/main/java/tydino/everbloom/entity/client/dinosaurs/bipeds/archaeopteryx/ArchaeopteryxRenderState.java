package tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx.ArchaeopteryxVariant;

public class ArchaeopteryxRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState glidingAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sittingdownAnimationState = new AnimationState();
    public final AnimationState standingupAnimationState = new AnimationState();

    public ArchaeopteryxVariant variant;
}
