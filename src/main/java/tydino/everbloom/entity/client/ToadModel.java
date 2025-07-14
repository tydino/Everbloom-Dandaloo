package tydino.everbloom.entity.client;
// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;

public class ToadModel extends EntityModel<ToadRenderState> {
	public static final EntityModelLayer TOAD = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "toad"), "main");

	public ToadModel(ModelPart root) {
		super(root);
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData toad = modelPartData.addChild("toad", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = toad.addChild("body", ModelPartBuilder.create().uv(1, 0).cuboid(-4.0F, -5.0F, -8.0F, 8.0F, 5.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 2.0F));

		ModelPartData eyeLeft = body.addChild("eyeLeft", ModelPartBuilder.create().uv(6, 16).cuboid(0.0F, -2.0F, -2.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -5.0F, -4.0F));

		ModelPartData eyeRight = body.addChild("eyeRight", ModelPartBuilder.create().uv(24, 16).cuboid(-3.0F, -2.0F, -2.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -5.0F, -4.0F));

		ModelPartData backLeftLeg = toad.addChild("backLeftLeg", ModelPartBuilder.create().uv(2, 23).cuboid(-1.0F, -2.0F, -4.0F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -2.0F, 5.0F));

		ModelPartData backLeftFoot = backLeftLeg.addChild("backLeftFoot", ModelPartBuilder.create().uv(3, 20).cuboid(-2.0F, 0.0F, -3.0F, 5.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 2.0F, -3.0F));

		ModelPartData backRightLeg = toad.addChild("backRightLeg", ModelPartBuilder.create().uv(20, 23).cuboid(-3.0F, -2.0F, -4.0F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -2.0F, 5.0F));

		ModelPartData backRightFoot = backRightLeg.addChild("backRightFoot", ModelPartBuilder.create().uv(21, 20).cuboid(-3.0F, 0.0F, -3.0F, 5.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 2.0F, -3.0F));

		ModelPartData frontLeftLeg = toad.addChild("frontLeftLeg", ModelPartBuilder.create().uv(2, 5).cuboid(0.0F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -1.0F, -2.0F));

		ModelPartData frontLeftFoot = frontLeftLeg.addChild("frontLeftFoot", ModelPartBuilder.create().uv(1, 3).cuboid(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 1.0F, -1.0F));

		ModelPartData frontRightLeg = toad.addChild("frontRightLeg", ModelPartBuilder.create().uv(28, 5).cuboid(-2.0F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -1.0F, -2.0F));

		ModelPartData frontRightFoot = frontRightLeg.addChild("frontRightFoot", ModelPartBuilder.create().uv(29, 3).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 1.0F, -1.0F));
		return TexturedModelData.of(modelData, 40, 32);
	}
	@Override
	public void setAngles(ToadRenderState state) {
		super.setAngles(state);

		this.animateWalking(ModAnimations.TOAD_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.TOAD_ANIMATIONS.IDLE, state.age, 1f);
		this.animate(state.sleepingTransitionAnimationState, ModAnimations.TOAD_ANIMATIONS.GOINGTOSLEEP, state.age, 1f);
		this.animate(state.sleepingAnimationState, ModAnimations.TOAD_ANIMATIONS.ASLEEP, state.age, 1f);
		this.animate(state.standingTransitionAnimationState, ModAnimations.TOAD_ANIMATIONS.WAKINGUP, state.age, 1f);
	}
}