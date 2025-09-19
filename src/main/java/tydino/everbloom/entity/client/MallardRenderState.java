package tydino.everbloom.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.custom.unsorted.MallardVariant;

public class MallardRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public MallardVariant variant;
}
