package tydino.everbloom.entity.client.dinosaurs.scarabs.bronze;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
import tydino.everbloom.entity.client.MallardRenderState;

public class BronzeScarabModel extends EntityModel<BronzeScarabRenderState> {
	public static final EntityModelLayer SCARAB = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "bronze_scarab"), "main");
	private final ModelPart scarab;
	private final ModelPart head;
	public BronzeScarabModel(ModelPart root) {
		super(root);
		this.scarab = root.getChild("scarab");
		this.head = this.scarab.getChild("body").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData scarab = modelPartData.addChild("scarab", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = scarab.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -7.0F, 6.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 3.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(7, 11).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 1).cuboid(1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 1).cuboid(-2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -7.0F));

		ModelPartData backrightleg = body.addChild("backrightleg", ModelPartBuilder.create().uv(0, 11).cuboid(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData middlerightleg = body.addChild("middlerightleg", ModelPartBuilder.create().uv(0, 11).cuboid(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData frontrightleg = body.addChild("frontrightleg", ModelPartBuilder.create().uv(0, 11).cuboid(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, -6.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData backleftleg = body.addChild("backleftleg", ModelPartBuilder.create().uv(18, 11).cuboid(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData middleleftleg = body.addChild("middleleftleg", ModelPartBuilder.create().uv(18, 11).cuboid(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData frontleftleg = body.addChild("frontleftleg", ModelPartBuilder.create().uv(18, 11).cuboid(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, -6.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 26, 15);
	}

	@Override
	public void setAngles(BronzeScarabRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(ModAnimations.BRONZE_SCARAB_ANIMATIONS.waddle, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.BRONZE_SCARAB_ANIMATIONS.idle, state.age, 1f);
	}

		private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}