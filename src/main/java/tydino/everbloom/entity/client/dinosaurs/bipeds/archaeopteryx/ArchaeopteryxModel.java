package tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxRenderState;
import tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus.CompsognathusRenderState;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ArchaeopteryxModel extends EntityModel<ArchaeopteryxRenderState> {
	public static final EntityModelLayer ARCHAEOPTERYX = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "archaeopteryx"), "main");
	private final ModelPart archaeopteryx;
	private final ModelPart head;
	public ArchaeopteryxModel(ModelPart root) {
		super(root);
		this.archaeopteryx = root.getChild("archaeopteryx");
		this.head = this.archaeopteryx.getChild("body").getChild("neck").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData archaeopteryx = modelPartData.addChild("archaeopteryx", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData body = archaeopteryx.addChild("body", ModelPartBuilder.create().uv(4, 12).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

		ModelPartData neck = body.addChild("neck", ModelPartBuilder.create().uv(7, 17).cuboid(-0.5F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 2.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(7, 25).cuboid(-0.5F, -1.0F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 1.0F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(5, 21).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -0.25F, -1.5708F, 0.0F, 0.0F));

		ModelPartData tailbase = body.addChild("tailbase", ModelPartBuilder.create().uv(5, 9).cuboid(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.5F, -1.0F));

		ModelPartData tailmid = tailbase.addChild("tailmid", ModelPartBuilder.create().uv(6, 6).cuboid(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(5, 4).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData tailtip = tailmid.addChild("tailtip", ModelPartBuilder.create().uv(1, 0).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(3, 19).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 15).cuboid(-0.5F, 0.0F, -2.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.0F, 1.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(11, 19).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 15).cuboid(0.5F, 0.0F, -2.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 1.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData leftfoot = archaeopteryx.addChild("leftfoot", ModelPartBuilder.create().uv(2, 6).cuboid(-0.5F, -0.05F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.0F, -1.0F));

		ModelPartData lowerleftleg = leftfoot.addChild("lowerleftleg", ModelPartBuilder.create().uv(2, 7).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData upperleftleg = lowerleftleg.addChild("upperleftleg", ModelPartBuilder.create().uv(1, 10).cuboid(-0.5F, -2.5F, 0.5F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -1.0F));

		ModelPartData rightfoot = archaeopteryx.addChild("rightfoot", ModelPartBuilder.create().uv(12, 6).cuboid(-0.5F, -0.05F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 0.0F, -1.0F));

		ModelPartData lowerrightleg = rightfoot.addChild("lowerrightleg", ModelPartBuilder.create().uv(12, 7).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData upperrightleg = lowerrightleg.addChild("upperrightleg", ModelPartBuilder.create().uv(11, 10).cuboid(-0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
		return TexturedModelData.of(modelData, 18, 27);
	}
	@Override
	public void setAngles(ArchaeopteryxRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.ARCHAEOPTERYX_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.IDLE, state.age, 1f);
		this.animate(state.runAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.RUN, state.age, 1f);
		this.animate(state.glidingAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.GLIDING, state.age, 1f);
		this.animate(state.sitAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.SIT, state.age, 1f);
		this.animate(state.sittingdownAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.SITTINGDOWN, state.age, 1f);
		this.animate(state.standingupAnimationState, ModAnimations.ARCHAEOPTERYX_ANIMATIONS.STANDINGUP, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}