package tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.client.dinosaurs.DinosaurRenderState;
import tydino.everbloom.entity.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusVariant;

public class ParasaurolophusRenderState extends DinosaurRenderState {
    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();

    public ParasaurolophusVariant variant;
}
