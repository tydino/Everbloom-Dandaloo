package tydino.everbloom.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;

public class TortoiseModel extends EntityModel<TortoiseRenderState> {
    public static final EntityModelLayer TORTOISE = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "tortoise"), "main");

    private final ModelPart tortoise;
    private final ModelPart head;

    public TortoiseModel(ModelPart root) {
        super(root);
        this.tortoise = root.getChild("tortoise");
        this.head = this.tortoise.getChild("torso").getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData tortoise = modelPartData.addChild("tortoise", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData torso = tortoise.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -6.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 2.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 13).cuboid(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -6.0F));

        ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(16, 23).cuboid(-1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData frontleftleg = tortoise.addChild("frontleftleg", ModelPartBuilder.create().uv(16, 13).cuboid(-2.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -3.0F, -1.0F));

        ModelPartData frontrightleg = tortoise.addChild("frontrightleg", ModelPartBuilder.create().uv(16, 18).cuboid(0.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -3.0F, -1.0F));

        ModelPartData backleftleg = tortoise.addChild("backleftleg", ModelPartBuilder.create().uv(8, 20).cuboid(-2.0F, 0.0F, 0.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -3.0F, 1.0F));

        ModelPartData backrightleg = tortoise.addChild("backrightleg", ModelPartBuilder.create().uv(0, 20).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -3.0F, 1.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(TortoiseRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.yawDegrees, state.pitch);

        this.animateWalking(ModAnimations.TORTOISE_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
        this.animate(state.idleAnimationState, ModAnimations.TORTOISE_ANIMATIONS.IDLE, state.age, 1f);
    }
    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -30f, 30f);
        headPitch = MathHelper.clamp(headPitch, -25f, 45f);

        this.head.yaw = headYaw * 0.017453292f;
        this.head.pitch = headPitch * 0.017453292f;
    }
}
