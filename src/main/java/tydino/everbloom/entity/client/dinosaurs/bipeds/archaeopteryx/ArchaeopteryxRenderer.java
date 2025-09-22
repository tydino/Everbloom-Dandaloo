package tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.dinosaurs.biped.archaeopteryx.ArchaeopteryxEntity;
import tydino.everbloom.entity.dinosaurs.biped.archaeopteryx.ArchaeopteryxVariant;

import java.util.Map;

public class ArchaeopteryxRenderer extends MobEntityRenderer<ArchaeopteryxEntity, ArchaeopteryxRenderState, ArchaeopteryxModel> {
    private static final Map<ArchaeopteryxVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ArchaeopteryxVariant.class), map -> {
                map.put(ArchaeopteryxVariant.BLACKBERRY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/archaeopteryx/archaeopteryx_blackberry.png"));
                map.put(ArchaeopteryxVariant.BLUEBERRY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/archaeopteryx/archaeopteryx_blueberry.png"));
                map.put(ArchaeopteryxVariant.LEAF,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/archaeopteryx/archaeopteryx_leaf.png"));
                map.put(ArchaeopteryxVariant.PINKALICIOUS,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/archaeopteryx/archaeopteryx_pinkalicious.png"));
                map.put(ArchaeopteryxVariant.SAND,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/archaeopteryx/archaeopteryx_sand.png"));
            });

    public ArchaeopteryxRenderer(EntityRendererFactory.Context context) {
        super(context, new ArchaeopteryxModel(context.getPart(ArchaeopteryxModel.ARCHAEOPTERYX)), 0.25f);//shadow is set by f

    }
    @Override
    public Identifier getTexture(ArchaeopteryxRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(ArchaeopteryxRenderState state, MatrixStack matrixStack, VertexConsumerProvider
            vertexConsumerProvider, int i) {
        if (state.DinoAge == 1) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else if(state.DinoAge == 2){
            matrixStack.scale(0.6f, 0.6f, 0.6f);
        }else if(state.DinoAge == 3){
            matrixStack.scale(0.7f, 0.7f, 0.7f);
        }else if(state.DinoAge == 4){
            matrixStack.scale(0.85f, 0.85f, 0.85f);
        }else if(state.DinoAge >= 5){
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ArchaeopteryxRenderState createRenderState() {
        return new ArchaeopteryxRenderState();
    }

    @Override
    public void updateRenderState(ArchaeopteryxEntity livingEntity, ArchaeopteryxRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.runAnimationState.copyFrom(livingEntity.runAnimationState);
        livingEntityRenderState.glidingAnimationState.copyFrom(livingEntity.glidingAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.sittingdownAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.standingupAnimationState.copyFrom(livingEntity.standingupAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();

    }
}
