package tydino.everbloomcore.entity.core_entity_client.manedwolf;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.entity.core_entity_animations.EDC_ANIMATIONS;
import tydino.everbloomcore.entity.core_entity_client.manedwolf.ManedWolfRenderState;

public class ManedWolfModel extends EntityModel<ManedWolfRenderState> {
	public static final EntityModelLayer MANED_WOLF = new EntityModelLayer(Identifier.of(EverbloomDandalooCore.MOD_ID, "maned_wolf"), "main");

	private final ModelPart maned_wolf;
	private final ModelPart torso;
	private final ModelPart chest;
	private final ModelPart neck;
	private final ModelPart head;
	public ManedWolfModel(ModelPart root) {
		super(root);
		this.maned_wolf = root.getChild("maned_wolf");
		this.torso = this.maned_wolf.getChild("torso");
		this.chest = this.torso.getChild("chest");
		this.neck = this.chest.getChild("neck");
		this.head = this.neck.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData maned_wolf = modelPartData.addChild("maned_wolf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData torso = maned_wolf.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -16.0F, 6.0F));

		ModelPartData butt_r1 = torso.addChild("butt_r1", ModelPartBuilder.create().uv(4, 12).cuboid(-2.0F, -4.0F, -1.0F, 3.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 3.0F, -3.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(8, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData chest = torso.addChild("chest", ModelPartBuilder.create().uv(0, 21).cuboid(-2.0F, -1.0F, -6.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -5.0F));

		ModelPartData neck = chest.addChild("neck", ModelPartBuilder.create().uv(4, 34).cuboid(-1.5F, -5.0F, -3.0F, 3.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, 0.6981F, 0.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(3, 46).cuboid(-2.5F, -3.0F, -3.0F, 5.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData muzzle_r1 = head.addChild("muzzle_r1", ModelPartBuilder.create().uv(5, 55).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 2.0F, -4.0F, 0.2618F, 0.0F, 0.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -3.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData leftear = head.addChild("leftear", ModelPartBuilder.create().uv(2, 24).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -2.0F, 0.0F, 0.1745F, 0.0F, 0.4363F));

		ModelPartData rightear = head.addChild("rightear", ModelPartBuilder.create().uv(16, 24).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -2.0F, 0.0F, 0.1745F, 0.0F, -0.4363F));

		ModelPartData lefteyelid = head.addChild("lefteyelid", ModelPartBuilder.create().uv(5, 49).cuboid(-0.5F, -1.0F, -0.01F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.0F, -2.0F));

		ModelPartData righteyelid = head.addChild("righteyelid", ModelPartBuilder.create().uv(17, 49).cuboid(-0.5F, -1.0F, -0.01F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -1.0F, -2.0F));

		ModelPartData neckhair = neck.addChild("neckhair", ModelPartBuilder.create().uv(0, 14).cuboid(0.0F, -4.0F, -1.0F, 0.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 3.0F));

		ModelPartData backhair = chest.addChild("backhair", ModelPartBuilder.create().uv(2, 10).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));

		ModelPartData hindleftfoot = maned_wolf.addChild("hindleftfoot", ModelPartBuilder.create().uv(22, 61).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -1.0F, 5.0F));

		ModelPartData hindleftshin = hindleftfoot.addChild("hindleftshin", ModelPartBuilder.create().uv(23, 54).cuboid(-0.5F, -4.0F, -1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData hindleftcalf = hindleftshin.addChild("hindleftcalf", ModelPartBuilder.create().uv(22, 44).cuboid(-1.0F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		ModelPartData hindleftthigh = hindleftcalf.addChild("hindleftthigh", ModelPartBuilder.create().uv(20, 34).cuboid(0.5F, -5.0F, -1.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -7.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		ModelPartData hindrightfoot = maned_wolf.addChild("hindrightfoot", ModelPartBuilder.create().uv(36, 61).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -1.0F, 5.0F));

		ModelPartData hindrightshin = hindrightfoot.addChild("hindrightshin", ModelPartBuilder.create().uv(37, 54).cuboid(-0.5F, -4.0F, -1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData hindrightcalf = hindrightshin.addChild("hindrightcalf", ModelPartBuilder.create().uv(36, 44).cuboid(-1.0F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		ModelPartData hindrightthigh = hindrightcalf.addChild("hindrightthigh", ModelPartBuilder.create().uv(34, 34).cuboid(-0.5F, -5.0F, -1.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -7.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		ModelPartData frontleftfoot = maned_wolf.addChild("frontleftfoot", ModelPartBuilder.create().uv(24, 30).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -1.0F, -4.0F));

		ModelPartData frontleftshin = frontleftfoot.addChild("frontleftshin", ModelPartBuilder.create().uv(26, 24).cuboid(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData frontleftcalf = frontleftshin.addChild("frontleftcalf", ModelPartBuilder.create().uv(25, 12).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData frontleftthigh = frontleftcalf.addChild("frontleftthigh", ModelPartBuilder.create().uv(24, 5).cuboid(-0.5F, -4.0F, -1.5F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData frontrightfoot = maned_wolf.addChild("frontrightfoot", ModelPartBuilder.create().uv(38, 30).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -1.0F, -4.0F));

		ModelPartData frontrightshin = frontrightfoot.addChild("frontrightshin", ModelPartBuilder.create().uv(40, 24).cuboid(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData frontrightcalf = frontrightshin.addChild("frontrightcalf", ModelPartBuilder.create().uv(39, 12).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData frontrightthigh = frontrightcalf.addChild("frontrightthigh", ModelPartBuilder.create().uv(38, 5).cuboid(-1.5F, -4.0F, -1.5F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.5236F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 48, 64);
	}
	@Override
	public void setAngles(ManedWolfRenderState state) {
		super.setAngles(state);
		this.setHeadAngles(state.yawDegrees, state.pitch);

		this.animateWalking(EDC_ANIMATIONS.ManedWolfAnimations.WALKING, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
		this.animate(state.idleAnimationState, EDC_ANIMATIONS.ManedWolfAnimations.IDLE, state.age, 1f);
	}
	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = (headPitch-45f) * 0.017453292f;//(headpitch+45f) can be swapped with headpitch, this just makes the ehad face forward
	}
}