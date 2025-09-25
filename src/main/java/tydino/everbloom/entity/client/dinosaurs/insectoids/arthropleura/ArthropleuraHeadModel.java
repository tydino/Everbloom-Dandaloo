package tydino.everbloom.entity.client.dinosaurs.insectoids.arthropleura;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import tydino.everbloom.entity.client.dinosaurs.insectoids.arthropleura.ArthropleuraRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;
public class ArthropleuraHeadModel extends EntityModel<ArthropleuraRenderState> {
	public static final EntityModelLayer ARTHROPLEURA = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "arthropleura_head"), "main");
	private final ModelPart arthropleura_head;
	private final ModelPart body_base;
	private final ModelPart legbackleft;
	private final ModelPart legbackright;
	private final ModelPart body_mid;
	private final ModelPart legmiddleleft;
	private final ModelPart legmiddleright;
	private final ModelPart body_tip;
	private final ModelPart legfrontleft;
	private final ModelPart legfrontright;
	private final ModelPart head;
	private final ModelPart leftatenea;
	private final ModelPart rightatenea;
	public ArthropleuraHeadModel(ModelPart root) {
        super(root);
        this.arthropleura_head = root.getChild("arthropleura_head");
		this.body_base = this.arthropleura_head.getChild("body_base");
		this.legbackleft = this.body_base.getChild("legbackleft");
		this.legbackright = this.body_base.getChild("legbackright");
		this.body_mid = this.body_base.getChild("body_mid");
		this.legmiddleleft = this.body_mid.getChild("legmiddleleft");
		this.legmiddleright = this.body_mid.getChild("legmiddleright");
		this.body_tip = this.body_mid.getChild("body_tip");
		this.legfrontleft = this.body_tip.getChild("legfrontleft");
		this.legfrontright = this.body_tip.getChild("legfrontright");
		this.head = this.body_tip.getChild("head");
		this.leftatenea = this.head.getChild("leftatenea");
		this.rightatenea = this.head.getChild("rightatenea");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData arthropleura_head = modelPartData.addChild("arthropleura_head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body_base = arthropleura_head.addChild("body_base", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 3.0F));

		ModelPartData bodyright_r1 = body_base.addChild("bodyright_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, -0.2618F));

		ModelPartData bodyleft_r1 = body_base.addChild("bodyleft_r1", ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

		ModelPartData legbackleft = body_base.addChild("legbackleft", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData cube_r1 = legbackleft.addChild("cube_r1", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData legbackright = body_base.addChild("legbackright", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r2 = legbackright.addChild("cube_r2", ModelPartBuilder.create().uv(10, 12).cuboid(-1.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData body_mid = body_base.addChild("body_mid", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData bodyright_r2 = body_mid.addChild("bodyright_r2", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, -0.2618F));

		ModelPartData bodyleft_r2 = body_mid.addChild("bodyleft_r2", ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

		ModelPartData legmiddleleft = body_mid.addChild("legmiddleleft", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData cube_r3 = legmiddleleft.addChild("cube_r3", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData legmiddleright = body_mid.addChild("legmiddleright", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r4 = legmiddleright.addChild("cube_r4", ModelPartBuilder.create().uv(10, 12).cuboid(-1.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData body_tip = body_mid.addChild("body_tip", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData bodyright_r3 = body_tip.addChild("bodyright_r3", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, -0.2618F));

		ModelPartData bodyleft_r3 = body_tip.addChild("bodyleft_r3", ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

		ModelPartData legfrontleft = body_tip.addChild("legfrontleft", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData cube_r5 = legfrontleft.addChild("cube_r5", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData legfrontright = body_tip.addChild("legfrontright", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r6 = legfrontright.addChild("cube_r6", ModelPartBuilder.create().uv(10, 12).cuboid(-1.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData head = body_tip.addChild("head", ModelPartBuilder.create().uv(3, 14).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData rightbody_r1 = head.addChild("rightbody_r1", ModelPartBuilder.create().uv(2, 21).cuboid(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.2618F, -0.2618F, 0.1745F));

		ModelPartData leftbody_r1 = head.addChild("leftbody_r1", ModelPartBuilder.create().uv(2, 18).cuboid(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.2618F, 0.2618F, -0.1745F));

		ModelPartData leftatenea = head.addChild("leftatenea", ModelPartBuilder.create().uv(2, 24).cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, -1.5F, 0.5236F, 0.0F, 0.4363F));

		ModelPartData rightatenea = head.addChild("rightatenea", ModelPartBuilder.create().uv(7, 24).cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, -1.5F, 0.5236F, 0.0F, -0.4363F));
		return TexturedModelData.of(modelData, 14, 28);
	}

	@Override
	public void setAngles(ArthropleuraRenderState state) {
		super.setAngles(state);

		this.animateWalking(ModAnimations.ARTHROPLEURA_HEAD_ANIMATIONS.walk, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, ModAnimations.ARTHROPLEURA_HEAD_ANIMATIONS.idle, state.age, 1f);
	}
}