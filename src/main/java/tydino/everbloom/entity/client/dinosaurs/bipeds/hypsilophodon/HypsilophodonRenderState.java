package tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.client.dinosaurs.DinosaurRenderState;
import tydino.everbloom.entity.dinosaurs.biped.hypsilophodon.HypsilophodonVariant;

public class HypsilophodonRenderState extends DinosaurRenderState {
    public final AnimationState runAnimationState = new AnimationState();

    public HypsilophodonVariant variant;
}
