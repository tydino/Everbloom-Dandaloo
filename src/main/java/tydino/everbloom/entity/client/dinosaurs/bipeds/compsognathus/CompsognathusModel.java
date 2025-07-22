package tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CompsognathusModel extends EntityModel<CompsognathusRenderState> {
	public static final EntityModelLayer COMPSOGNATHUS = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "compsognathus"), "main");
	private final ModelPart compsognathus;
	private final ModelPart head;
	public CompsognathusModel(ModelPart root) {
		super(root);
		this.compsognathus = root.getChild("compsognathus");
		this.head = this.compsognathus.getChild("body").getChild("neck").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData compsognathus = modelPartData.addChild("compsognathus", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = compsognathus.addChild("body", ModelPartBuilder.create().uv(4, 9).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 2.0F));

		ModelPartData neck = body.addChild("neck", ModelPartBuilder.create().uv(8, 5).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -4.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(5, 0).cuboid(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(4, 15).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(4, 5).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, -3.0F));

		ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(12, 5).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 0.0F, -3.0F));

		ModelPartData rightfoot = compsognathus.addChild("rightfoot", ModelPartBuilder.create().uv(11, 17).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.0F, 2.0F));

		ModelPartData rightleg = rightfoot.addChild("rightleg", ModelPartBuilder.create().uv(15, 14).cuboid(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData righthip = rightleg.addChild("righthip", ModelPartBuilder.create().uv(12, 9).cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -1.0F));

		ModelPartData leftfoot = compsognathus.addChild("leftfoot", ModelPartBuilder.create().uv(1, 17).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -1.0F, 2.0F));

		ModelPartData leftleg = leftfoot.addChild("leftleg", ModelPartBuilder.create().uv(1, 14).cuboid(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData lefthip = leftleg.addChild("lefthip", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -1.0F));
		return TexturedModelData.of(modelData, 20, 21);
	}
	@Override
	public void setAngles(CompsognathusRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.COMPSOGNATHUS_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.COMPSOGNATHUS_ANIMATIONS.IDLE, state.age, 1f);
		this.animate(state.runAnimationState, ModAnimations.COMPSOGNATHUS_ANIMATIONS.RUN, state.age, 1f);
		this.animate(state.sitAnimationState, ModAnimations.COMPSOGNATHUS_ANIMATIONS.SIT, state.age, 1f);
		this.animate(state.sittingdownAnimationState, ModAnimations.COMPSOGNATHUS_ANIMATIONS.SITTINGDOWN, state.age, 1f);
		this.animate(state.standingupAnimationState, ModAnimations.COMPSOGNATHUS_ANIMATIONS.STANDINGUP, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}