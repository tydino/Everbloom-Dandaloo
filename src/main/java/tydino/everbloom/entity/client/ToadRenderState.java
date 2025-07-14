package tydino.everbloom.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.custom.ToadVariant;

public class ToadRenderState extends LivingEntityRenderState {
    public final AnimationState sleepingTransitionAnimationState = new AnimationState();
    public final AnimationState sleepingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public ToadVariant variant;
}
