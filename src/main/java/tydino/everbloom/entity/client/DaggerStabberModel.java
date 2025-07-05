package tydino.everbloom.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.custom.DaggerStabberEntity;

public class DaggerStabberModel extends EntityModel<DaggerStabberRenderState> {
	public static final EntityModelLayer DAGGER_STABBER = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "dagger-stabber"), "main");
	private final ModelPart daggerStabber;
	private final ModelPart head;

	public DaggerStabberModel(ModelPart root) {
		super(root);
		this.daggerStabber = root.getChild("daggerStabber");
		this.head = daggerStabber.getChild("body").getChild("torso").getChild("neck").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData daggerStabber = modelPartData.addChild("daggerStabber", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = daggerStabber.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 23).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 0.0F));

		ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(22, 24).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData neck = torso.addChild("neck", ModelPartBuilder.create().uv(0, 10).cuboid(-2.0F, -9.0F, -1.0F, 4.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 2.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -5.0F, -2.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData earL_r1 = head.addChild("earL_r1", ModelPartBuilder.create().uv(17, 21).cuboid(-1.0F, -11.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.2618F));

		ModelPartData earR_r1 = head.addChild("earR_r1", ModelPartBuilder.create().uv(17, 14).cuboid(-1.0F, -11.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 4.0F, 1.0F, 0.0F, 0.0F, -0.2618F));

		ModelPartData dagger = head.addChild("dagger", ModelPartBuilder.create().uv(34, 23).cuboid(-1.5F, -1.0F, -4.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 3.0F));

		ModelPartData jabber = dagger.addChild("jabber", ModelPartBuilder.create().uv(36, 16).cuboid(-1.0F, -0.5F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData stabber = jabber.addChild("stabber", ModelPartBuilder.create().uv(34, 9).cuboid(-0.5F, 0.0F, -6.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData legL = body.addChild("legL", ModelPartBuilder.create().uv(22, 0).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -11.0F, 0.0F));

		ModelPartData shinL = legL.addChild("shinL", ModelPartBuilder.create().uv(26, 0).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData footL = shinL.addChild("footL", ModelPartBuilder.create().uv(23, 17).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData legR = body.addChild("legR", ModelPartBuilder.create().uv(30, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -11.0F, 0.0F));

		ModelPartData shinR = legR.addChild("shinR", ModelPartBuilder.create().uv(34, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData footR = shinR.addChild("footR", ModelPartBuilder.create().uv(23, 17).cuboid(-2.0F, 0.0F, -3.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
		return TexturedModelData.of(modelData, 48, 32);
	}
	@Override
	public void setAngles(DaggerStabberRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.DAGGERSTABBER_ANIMATIONS.DAGGERSTABBER_WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.DAGGERSTABBER_ANIMATIONS.DAGGERSTABBER_IDLE, state.age, 1f);
		this.animate(state.attackAnimationState, ModAnimations.DAGGERSTABBER_ANIMATIONS.DAGGERSTABBER_ATTACK, state.age, 1f);
	}
	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}