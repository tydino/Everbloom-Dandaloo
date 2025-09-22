package tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.client.dinosaurs.DinosaurRenderState;
import tydino.everbloom.entity.dinosaurs.biped.pteranodon.PteranodonVariant;

public class PteranodonRenderState extends DinosaurRenderState {
    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    public PteranodonVariant variant;
}
