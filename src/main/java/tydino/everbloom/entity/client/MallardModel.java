package tydino.everbloom.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.custom.MallardEntity;

// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MallardModel<T extends MallardEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer MALLARD = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "mallard"), "main");
	private final ModelPart mallard;
	private final ModelPart head;

	public MallardModel(ModelPart root) {
		this.mallard = root.getChild("mallard");
		this.head = mallard.getChild("body").getChild("torso").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData mallard = modelPartData.addChild("mallard", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = mallard.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 18).cuboid(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(20, 19).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -3.0F));

		ModelPartData topBeak = head.addChild("topBeak", ModelPartBuilder.create().uv(20, 1).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));

		ModelPartData bottomBeak = head.addChild("bottomBeak", ModelPartBuilder.create().uv(22, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));

		ModelPartData wingL = torso.addChild("wingL", ModelPartBuilder.create().uv(8, 9).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -3.0F, -1.0F));

		ModelPartData wingR = torso.addChild("wingR", ModelPartBuilder.create().uv(8, 0).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -3.0F, -1.0F));

		ModelPartData legL = body.addChild("legL", ModelPartBuilder.create().uv(12, 10).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, 2.0F));

		ModelPartData footL = legL.addChild("footL", ModelPartBuilder.create().uv(3, 12).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData legR = body.addChild("legR", ModelPartBuilder.create().uv(12, 1).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 0.0F, 2.0F));

		ModelPartData footR = legR.addChild("footR", ModelPartBuilder.create().uv(3, 3).cuboid(-4.0F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 5.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(MallardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.MALLARD_ANIMATIONS.MALLARD_RUN, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.MALLARD_ANIMATIONS.MALLARD_IDLE, ageInTicks, 1f);
	}
	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		mallard.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return mallard;
	}
}