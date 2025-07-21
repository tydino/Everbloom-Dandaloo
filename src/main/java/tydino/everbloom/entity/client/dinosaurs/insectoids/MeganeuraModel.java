package tydino.everbloom.entity.client.dinosaurs.insectoids;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;

public class MeganeuraModel extends EntityModel<MeganeuraRenderState> {
    public static final EntityModelLayer MEGANEURA = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "meganeura"), "main");
    private final ModelPart meganeura;
    private final ModelPart head;
    public MeganeuraModel(ModelPart root) {
        super(root);
        this.meganeura = root.getChild("meganeura");
        this.head = this.meganeura.getChild("body").getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData meganeura = modelPartData.addChild("meganeura", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = meganeura.addChild("body", ModelPartBuilder.create().uv(7, 16).cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(9, 25).cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(1, 23).cuboid(-3.0F, -4.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(23, 23).cuboid(1.0F, -4.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -3.0F));

        ModelPartData frontleftwing = body.addChild("frontleftwing", ModelPartBuilder.create().uv(2, 44).cuboid(-10.0F, 0.0F, -1.0F, 10.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.2618F));

        ModelPartData frontrightwing = body.addChild("frontrightwing", ModelPartBuilder.create().uv(2, 39).cuboid(-9.0F, 0.0F, -2.0F, 10.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, -1.0F, -3.1416F, -0.2618F, 2.8798F));

        ModelPartData backleftwing = body.addChild("backleftwing", ModelPartBuilder.create().uv(5, 35).cuboid(-8.0F, 1.0F, 0.0F, 8.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, 0.0F, 0.0F, 0.2618F, 0.1745F));

        ModelPartData backrightwing = body.addChild("backrightwing", ModelPartBuilder.create(), ModelTransform.of(1.0F, -2.0F, 0.0F, 0.0F, -0.2618F, -0.1745F));

        ModelPartData wing_r1 = backrightwing.addChild("wing_r1", ModelPartBuilder.create().uv(5, 31).cuboid(-7.0F, 0.0F, -1.0F, 8.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 2.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(modelData, 32, 50);
    }

    @Override
    public void setAngles(MeganeuraRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.yawDegrees, state.pitch);

        //this.animateWalking(ModAnimations.MEGANEURA_ANIMATIONS.ONLY, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
        this.animate(state.idleAnimationState, ModAnimations.MEGANEURA_ANIMATIONS.ONLY, state.age, 1f);
    }
    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -30f, 30f);
        headPitch = MathHelper.clamp(headPitch, -25f, 45f);

        this.head.yaw = headYaw * 0.017453292f;
        this.head.pitch = headPitch * 0.017453292f;
    }
}