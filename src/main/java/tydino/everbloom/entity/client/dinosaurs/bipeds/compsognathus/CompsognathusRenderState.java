package tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import tydino.everbloom.entity.client.dinosaurs.DinosaurRenderState;
import tydino.everbloom.entity.dinosaurs.biped.compsognathus.CompsognathusVariant;

public class CompsognathusRenderState extends DinosaurRenderState {
    public final AnimationState runAnimationState = new AnimationState();

    public CompsognathusVariant variant;
}
