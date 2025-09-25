package tydino.everbloom.entity.client.dinosaurs.insectoids.arthropleura;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.animation.ModAnimations;

public class ArthropleuraBodyModel extends EntityModel<LivingEntityRenderState> {
	public static final EntityModelLayer ARTHROPLEURA = new EntityModelLayer(Identifier.of(EverbloomDandaloo.MOD_ID, "arthropleura_body"), "main");
	private final ModelPart arthropleura_head;
	private final ModelPart body_base;
	private final ModelPart legbackleft;
	private final ModelPart legbackright;
	private final ModelPart body_mid;
	private final ModelPart legmiddleleft;
	private final ModelPart legmiddleright;
	private final ModelPart body_mid2;
	private final ModelPart legmiddleleft2;
	private final ModelPart legmiddleright2;
	private final ModelPart body_tip;
	private final ModelPart legfrontleft;
	private final ModelPart legfrontright;
	public ArthropleuraBodyModel(ModelPart root) {
		super(root);
		this.arthropleura_head = root.getChild("arthropleura_head");
		this.body_base = this.arthropleura_head.getChild("body_base");
		this.legbackleft = this.body_base.getChild("legbackleft");
		this.legbackright = this.body_base.getChild("legbackright");
		this.body_mid = this.body_base.getChild("body_mid");
		this.legmiddleleft = this.body_mid.getChild("legmiddleleft");
		this.legmiddleright = this.body_mid.getChild("legmiddleright");
		this.body_mid2 = this.body_mid.getChild("body_mid2");
		this.legmiddleleft2 = this.body_mid2.getChild("legmiddleleft2");
		this.legmiddleright2 = this.body_mid2.getChild("legmiddleright2");
		this.body_tip = this.body_mid2.getChild("body_tip");
		this.legfrontleft = this.body_tip.getChild("legfrontleft");
		this.legfrontright = this.body_tip.getChild("legfrontright");
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

		ModelPartData body_mid2 = body_mid.addChild("body_mid2", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData bodyright_r3 = body_mid2.addChild("bodyright_r3", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, -0.2618F));

		ModelPartData bodyleft_r3 = body_mid2.addChild("bodyleft_r3", ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

		ModelPartData legmiddleleft2 = body_mid2.addChild("legmiddleleft2", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData cube_r5 = legmiddleleft2.addChild("cube_r5", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData legmiddleright2 = body_mid2.addChild("legmiddleright2", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r6 = legmiddleright2.addChild("cube_r6", ModelPartBuilder.create().uv(10, 12).cuboid(-1.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData body_tip = body_mid2.addChild("body_tip", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData bodyright_r4 = body_tip.addChild("bodyright_r4", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, -0.2618F));

		ModelPartData bodyleft_r4 = body_tip.addChild("bodyleft_r4", ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 1.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

		ModelPartData legfrontleft = body_tip.addChild("legfrontleft", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData cube_r7 = legfrontleft.addChild("cube_r7", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData legfrontright = body_tip.addChild("legfrontright", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r8 = legfrontright.addChild("cube_r8", ModelPartBuilder.create().uv(10, 12).cuboid(-1.0F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 14, 16);
	}

	@Override
	public void setAngles(LivingEntityRenderState state) {
		super.setAngles(state);

		this.animateWalking(ModAnimations.ARTHROPLEURA_BODY_ANIMATION.walk, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
	}
}