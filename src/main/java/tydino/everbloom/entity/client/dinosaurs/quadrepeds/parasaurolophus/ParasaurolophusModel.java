package tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus;

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
public class ParasaurolophusModel extends EntityModel<ParasaurolophusRenderState> {
	public static final EntityModelLayer PARASAUROLOPHUS = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "parasaurolophus"), "main");
	private final ModelPart parasaurolophus;
	private final ModelPart head;
	public ParasaurolophusModel(ModelPart root) {
		super(root);
		this.parasaurolophus = root.getChild("parasaurolophus");
		this.head = this.parasaurolophus.getChild("butt").getChild("body").getChild("neck").getChild("necktip").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData parasaurolophus = modelPartData.addChild("parasaurolophus", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData butt = parasaurolophus.addChild("butt", ModelPartBuilder.create().uv(20, 132).cuboid(-10.0F, -32.0F, -26.0F, 20.0F, 32.0F, 32.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -16.0F, 18.0F));

		ModelPartData body = butt.addChild("body", ModelPartBuilder.create().uv(38, 196).cuboid(-8.0F, -12.0F, -17.0F, 16.0F, 24.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -15.0F, -25.0F));

		ModelPartData neck = body.addChild("neck", ModelPartBuilder.create().uv(42, 238).cuboid(-5.0F, -6.0F, -16.0F, 10.0F, 13.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -16.0F, -0.4363F, 0.0F, 0.0F));

		ModelPartData necktip = neck.addChild("necktip", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.0F, -16.0F, 0.4363F, 0.0F, 0.0F));

		ModelPartData head = necktip.addChild("head", ModelPartBuilder.create().uv(44, 271).cuboid(-6.0F, -4.0F, -16.0F, 12.0F, 14.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 2.0F));

		ModelPartData upperjaw_r1 = head.addChild("upperjaw_r1", ModelPartBuilder.create().uv(0, 250).cuboid(-8.5F, -6.0F, -1.0F, 9.0F, 6.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 6.0F, -25.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData nostril_r1 = head.addChild("nostril_r1", ModelPartBuilder.create().uv(7, 228).cuboid(-5.0F, -16.0F, -1.0F, 6.0F, 16.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -3.0F, -3.0F, -1.0472F, 0.0F, 0.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(3, 267).cuboid(-4.0F, 0.0F, -9.0F, 8.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, -16.0F));

		ModelPartData lefteye = head.addChild("lefteye", ModelPartBuilder.create().uv(0, -2).cuboid(6.025F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -11.0F));

		ModelPartData righteye = head.addChild("righteye", ModelPartBuilder.create().uv(0, -2).cuboid(-6.025F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -11.0F));

		ModelPartData tailbase = butt.addChild("tailbase", ModelPartBuilder.create().uv(38, 96).cuboid(-7.0F, -7.0F, -1.0F, 14.0F, 16.0F, 20.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 5.0F));

		ModelPartData tailmid = tailbase.addChild("tailmid", ModelPartBuilder.create().uv(38, 60).cuboid(-5.0F, -6.0F, -1.0F, 10.0F, 12.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 19.0F));

		ModelPartData tailmidtip = tailmid.addChild("tailmidtip", ModelPartBuilder.create().uv(42, 28).cuboid(-3.0F, -6.0F, -1.0F, 6.0F, 8.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 22.0F));

		ModelPartData tailtip = tailmidtip.addChild("tailtip", ModelPartBuilder.create().uv(44, 0).cuboid(-2.0F, -5.0F, -1.0F, 4.0F, 4.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 22.0F));

		ModelPartData backrightfoot = parasaurolophus.addChild("backrightfoot", ModelPartBuilder.create().uv(82, 70).cuboid(-5.0F, -1.0F, -8.0F, 10.0F, 3.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(-12.0F, -2.0F, 14.0F));

		ModelPartData backrightshin = backrightfoot.addChild("backrightshin", ModelPartBuilder.create().uv(98, 58).cuboid(-3.0F, -8.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData backrightknee = backrightshin.addChild("backrightknee", ModelPartBuilder.create().uv(98, 88).cuboid(-2.5F, -19.0F, -3.0F, 5.0F, 20.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData backrighthip = backrightknee.addChild("backrighthip", ModelPartBuilder.create().uv(92, 116).cuboid(-4.0F, -24.0F, -3.0F, 10.0F, 24.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -17.0F, -2.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData backleftfoot = parasaurolophus.addChild("backleftfoot", ModelPartBuilder.create().uv(20, 70).cuboid(-5.0F, -1.0F, -8.0F, 10.0F, 3.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(12.0F, -2.0F, 14.0F));

		ModelPartData backleftshin = backleftfoot.addChild("backleftshin", ModelPartBuilder.create().uv(26, 58).cuboid(-3.0F, -8.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData backleftknee = backleftshin.addChild("backleftknee", ModelPartBuilder.create().uv(20, 88).cuboid(-2.5F, -19.0F, -3.0F, 5.0F, 20.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData backlefthip = backleftknee.addChild("backlefthip", ModelPartBuilder.create().uv(0, 116).cuboid(-6.0F, -24.0F, -3.0F, 10.0F, 24.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -17.0F, -2.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData frontrightfoot = parasaurolophus.addChild("frontrightfoot", ModelPartBuilder.create().uv(84, 279).cuboid(-5.0F, 0.0F, -5.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, -2.0F, -16.0F));

		ModelPartData frontrightshin = frontrightfoot.addChild("frontrightshin", ModelPartBuilder.create().uv(82, 242).cuboid(-4.0F, -11.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData frontrightknee = frontrightshin.addChild("frontrightknee", ModelPartBuilder.create().uv(106, 196).cuboid(-5.0F, -23.0F, -1.0F, 6.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData frontleftfoot = parasaurolophus.addChild("frontleftfoot", ModelPartBuilder.create().uv(36, 279).cuboid(-1.0F, 0.0F, -5.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, -2.0F, -16.0F));

		ModelPartData frontleftshin = frontleftfoot.addChild("frontleftshin", ModelPartBuilder.create().uv(46, 242).cuboid(0.0F, -11.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData frontleftknee = frontleftshin.addChild("frontleftknee", ModelPartBuilder.create().uv(10, 196).cuboid(-1.0F, -23.0F, -1.0F, 6.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 144, 301);
	}
	@Override
	public void setAngles(ParasaurolophusRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.PARASAUROLOPHUS_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.IDLE, state.age, 1f);
		this.animate(state.eatAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.EAT, state.age, 1f);
		this.animate(state.runAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.RUN, state.age, 1f);
		this.animate(state.sitAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.SIT, state.age, 1f);
		this.animate(state.sittingdownAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.SITTINGDOWN, state.age, 1f);
		this.animate(state.standingupAnimationState, ModAnimations.PARASAUROLOPHUS_ANIMATIONS.STANDINGUP, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;//this makes the head face forward
	}
}