package tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.dinosaurs.insectoids.meganeura.MeganeuraVariant;

public class MeganeuraRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public MeganeuraVariant variant;
}
