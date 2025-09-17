package tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxRenderState;
import tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon.PteranodonRenderState;

public class PteranodonModel extends EntityModel<PteranodonRenderState> {
	public static final EntityModelLayer PTERANODON = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "pteranodon"), "main");
	private final ModelPart pteranodon;
	private final ModelPart head;
	public PteranodonModel(ModelPart root) {
		super(root);
		this.pteranodon = root.getChild("pteranodon");
		this.head = this.pteranodon.getChild("butt").getChild("body").getChild("neck").getChild("headlead").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData pteranodon = modelPartData.addChild("pteranodon", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData butt = pteranodon.addChild("butt", ModelPartBuilder.create().uv(26, 0).cuboid(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 5.0F, -1.1781F, 0.0F, 0.0F));

		ModelPartData body = butt.addChild("body", ModelPartBuilder.create().uv(24, 7).cuboid(-3.0F, -1.0F, -4.0F, 6.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData neck = body.addChild("neck", ModelPartBuilder.create().uv(27, 16).cuboid(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -4.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData headlead = neck.addChild("headlead", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, -4.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData head = headlead.addChild("head", ModelPartBuilder.create().uv(27, 32).cuboid(-1.5F, -2.0F, -5.0F, 3.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData headthing_r1 = head.addChild("headthing_r1", ModelPartBuilder.create().uv(27, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData topjaw_r1 = head.addChild("topjaw_r1", ModelPartBuilder.create().uv(0, 41).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -10.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(56, 43).cuboid(-0.5F, 0.5F, -5.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -5.0F));

		ModelPartData lefteye = head.addChild("lefteye", ModelPartBuilder.create().uv(25, 40).cuboid(-1.51F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

		ModelPartData righteye = head.addChild("righteye", ModelPartBuilder.create().uv(43, 40).cuboid(1.51F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

		ModelPartData rightwing = body.addChild("rightwing", ModelPartBuilder.create().uv(37, 28).cuboid(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 24).cuboid(0.0F, 0.5F, 1.0F, 10.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(43, 22).cuboid(8.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, -4.0F, 0.0F, -0.3927F, 1.1781F));

		ModelPartData rightwingtip = rightwing.addChild("rightwingtip", ModelPartBuilder.create().uv(38, 35).cuboid(0.0F, -0.5F, -0.5F, 15.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 32).cuboid(0.0F, 0.0F, 0.5F, 15.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(10.0F, 0.5F, 0.5F, 0.0F, -0.3927F, -2.3562F));

		ModelPartData leftwing = body.addChild("leftwing", ModelPartBuilder.create().uv(11, 28).cuboid(-10.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(9, 24).cuboid(-10.0F, 0.5F, 1.0F, 10.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(21, 22).cuboid(-10.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, -4.0F, 0.0F, 0.3927F, -1.1781F));

		ModelPartData leftwingtip = leftwing.addChild("leftwingtip", ModelPartBuilder.create().uv(0, 35).cuboid(-15.0F, -0.5F, -0.5F, 15.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-15.0F, 0.0F, 0.5F, 15.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 0.5F, 0.5F, 0.0F, 0.3927F, 2.3562F));

		ModelPartData rightleg = butt.addChild("rightleg", ModelPartBuilder.create().uv(39, 1).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 1.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData leftleg = butt.addChild("leftleg", ModelPartBuilder.create().uv(23, 1).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 1.0F, -0.3927F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 70, 50);
	}
	@Override
	public void setAngles(PteranodonRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.PTERANODON_ANIMATIONS.walk, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.PTERANODON_ANIMATIONS.idle, state.age, 1f);
		this.animate(state.eatAnimationState, ModAnimations.PTERANODON_ANIMATIONS.eat, state.age, 1f);
		this.animate(state.flyAnimationState, ModAnimations.PTERANODON_ANIMATIONS.fly, state.age, 1f);
		this.animate(state.sitAnimationState, ModAnimations.PTERANODON_ANIMATIONS.sit, state.age, 1f);
		this.animate(state.sittingdownAnimationState, ModAnimations.PTERANODON_ANIMATIONS.sittingdown, state.age, 1f);
		this.animate(state.standingupAnimationState, ModAnimations.PTERANODON_ANIMATIONS.standingup, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}