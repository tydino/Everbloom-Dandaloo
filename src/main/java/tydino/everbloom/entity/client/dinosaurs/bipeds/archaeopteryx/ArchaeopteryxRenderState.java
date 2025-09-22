package tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.client.dinosaurs.DinosaurRenderState;
import tydino.everbloom.entity.dinosaurs.biped.archaeopteryx.ArchaeopteryxVariant;

public class ArchaeopteryxRenderState extends DinosaurRenderState {
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState glidingAnimationState = new AnimationState();

    public ArchaeopteryxVariant variant;
}
