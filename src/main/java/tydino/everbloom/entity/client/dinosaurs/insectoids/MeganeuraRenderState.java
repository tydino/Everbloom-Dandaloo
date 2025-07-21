package tydino.everbloom.entity.client.dinosaurs.insectoids;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.custom.dinosaurs.insectoids.MeganeuraVariant;

public class MeganeuraRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public MeganeuraVariant variant;
}
