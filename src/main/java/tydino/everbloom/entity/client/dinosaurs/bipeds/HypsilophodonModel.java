package tydino.everbloom.entity.client.dinosaurs.bipeds;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.client.dinosaurs.bipeds.HypsilophodonRenderState;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class HypsilophodonModel extends EntityModel<HypsilophodonRenderState> {
	public static final EntityModelLayer HYPSILOPHODON = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "hypsilophodon"), "main");
	private final ModelPart hypsilophodon;
	private final ModelPart head;
	public HypsilophodonModel(ModelPart root) {
		super(root);
		this.hypsilophodon = root.getChild("hypsilophodon");
		this.head = this.hypsilophodon.getChild("bodybase").getChild("bodytip").getChild("neck").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData hypsilophodon = modelPartData.addChild("hypsilophodon", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bodybase = hypsilophodon.addChild("bodybase", ModelPartBuilder.create().uv(12, 36).cuboid(-2.5F, -3.0F, -6.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 6.0F));

		ModelPartData bodytip = bodybase.addChild("bodytip", ModelPartBuilder.create().uv(13, 48).cuboid(-2.0F, -2.5F, -5.0F, 4.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -6.0F));

		ModelPartData neck = bodytip.addChild("neck", ModelPartBuilder.create().uv(14, 59).cuboid(-1.5F, 0.0F, -4.0F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -4.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(15, 68).cuboid(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -3.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData snoot_r1 = head.addChild("snoot_r1", ModelPartBuilder.create().uv(17, 75).cuboid(-2.5F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, -5.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(17, 80).cuboid(-1.5F, -0.25F, -2.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -3.0F));

		ModelPartData lefteye = head.addChild("lefteye", ModelPartBuilder.create().uv(17, 75).cuboid(-2.05F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, -2.5F));

		ModelPartData righteye = head.addChild("righteye", ModelPartBuilder.create().uv(27, 75).cuboid(2.05F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, -2.5F));

		ModelPartData leftarm = bodytip.addChild("leftarm", ModelPartBuilder.create().uv(2, 60).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 1.0F, -4.0F, 1.309F, 0.0F, 0.0F));

		ModelPartData rightarm = bodytip.addChild("rightarm", ModelPartBuilder.create().uv(32, 60).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 1.0F, -4.0F, 1.309F, 0.0F, 0.0F));

		ModelPartData tailbase = bodybase.addChild("tailbase", ModelPartBuilder.create().uv(13, 26).cuboid(-2.0F, -2.5F, -3.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

		ModelPartData tailmid = tailbase.addChild("tailmid", ModelPartBuilder.create().uv(11, 14).cuboid(-1.5F, -1.0F, 2.0F, 3.0F, 3.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData tailtip = tailmid.addChild("tailtip", ModelPartBuilder.create().uv(9, 0).cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 10.0F));

		ModelPartData rightfoot = hypsilophodon.addChild("rightfoot", ModelPartBuilder.create().uv(32, 49).cuboid(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, 1.0F));

		ModelPartData rightlowerleg = rightfoot.addChild("rightlowerleg", ModelPartBuilder.create().uv(35, 42).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -1.0F, 2.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData rightupperleg = rightlowerleg.addChild("rightupperleg", ModelPartBuilder.create().uv(33, 33).cuboid(-1.5F, -5.0F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.5F, -1.0F));

		ModelPartData leftfoot = hypsilophodon.addChild("leftfoot", ModelPartBuilder.create().uv(0, 49).cuboid(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, 1.0F));

		ModelPartData leftlowerleg = leftfoot.addChild("leftlowerleg", ModelPartBuilder.create().uv(3, 42).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -1.0F, 2.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData leftupperleg = leftlowerleg.addChild("leftupperleg", ModelPartBuilder.create().uv(1, 33).cuboid(-1.5F, -5.0F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.5F, -1.0F));
		return TexturedModelData.of(modelData, 46, 84);
	}
	@Override
	public void setAngles(HypsilophodonRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.HYPSILOPHODON_ANIMATIONS.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.HYPSILOPHODON_ANIMATIONS.IDLE, state.age, 1f);
		this.animate(state.runAnimationState, ModAnimations.HYPSILOPHODON_ANIMATIONS.RUN, state.age, 1f);
		this.animate(state.sitAnimationState, ModAnimations.HYPSILOPHODON_ANIMATIONS.SIT, state.age, 1f);
		this.animate(state.sittingdownAnimationState, ModAnimations.HYPSILOPHODON_ANIMATIONS.SITTING_DOWN, state.age, 1f);
		this.animate(state.standingupAnimationState, ModAnimations.HYPSILOPHODON_ANIMATIONS.STANDING_UP, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = (headYaw * 0.017453292f);
		this.head.pitch = headPitch * 0.017453292f;
	}
}