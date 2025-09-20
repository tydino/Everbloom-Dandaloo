package tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.dinosaurs.insectoids.meganeura.MeganeuraEntity;
import tydino.everbloom.entity.dinosaurs.insectoids.meganeura.MeganeuraVariant;

import java.util.Map;

public class MeganeuraRenderer extends MobEntityRenderer<MeganeuraEntity, MeganeuraRenderState, MeganeuraModel> {
    private static final Map<MeganeuraVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MeganeuraVariant.class), map -> {
                map.put(MeganeuraVariant.BUBBLEGUM,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/meganeura/meganeura_bubblegum.png"));
                map.put(MeganeuraVariant.NEUTRAL,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/meganeura/meganeura_neutral.png"));
                map.put(MeganeuraVariant.SANDY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/meganeura/meganeura_sandy.png"));
            });

    public MeganeuraRenderer(EntityRendererFactory.Context context) {
        super(context, new MeganeuraModel(context.getPart(MeganeuraModel.MEGANEURA)), 0.25f);// shoadow size set in f
    }

    @Override
    public Identifier getTexture(MeganeuraRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(MeganeuraRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.baby){
            matrixStack.scale(0.25f, 0.25f, 0.25f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public MeganeuraRenderState createRenderState() {
        return new MeganeuraRenderState();
    }

    @Override
    public void updateRenderState(MeganeuraEntity livingEntity, MeganeuraRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
