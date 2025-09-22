package tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.dinosaurs.biped.pteranodon.PteranodonVariant;
import tydino.everbloom.entity.dinosaurs.biped.pteranodon.PteranodonEntity;

import java.util.Map;

public class PteranodonRenderer extends MobEntityRenderer<PteranodonEntity, PteranodonRenderState, PteranodonModel> {
    private static final Map<PteranodonVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PteranodonVariant.class), map -> {
                map.put(PteranodonVariant.MUD,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/pteranodon/pteranodon_mud.png"));
                map.put(PteranodonVariant.DUCK,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/pteranodon/pteranodon_duck.png"));
                map.put(PteranodonVariant.FIRE,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/pteranodon/pteranodon_fire.png"));
            });

    public PteranodonRenderer(EntityRendererFactory.Context context) {
        super(context, new PteranodonModel(context.getPart(PteranodonModel.PTERANODON)), 0.5f);//shadow is set by f

    }
    @Override
    public Identifier getTexture(PteranodonRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(PteranodonRenderState state, MatrixStack matrixStack, VertexConsumerProvider
            vertexConsumerProvider, int i) {
        /// for testing purposes
        if (state.DinoAge <= 3) {
            matrixStack.scale(0.4f, 0.4f, 0.4f);
        }else if(state.DinoAge <= 6){
            matrixStack.scale(0.5f, 0.5f,0.5f);
        }else if(state.DinoAge <= 10){
            matrixStack.scale(0.6f, 0.6f,0.6f);
        }else if(state.DinoAge <= 15){
            matrixStack.scale(0.7f, 0.7f,0.7f);
        }else if(state.DinoAge <= 18){
            matrixStack.scale(0.8f, 0.8f,0.8f);
        }else if(state.DinoAge <= 19){
            matrixStack.scale(0.9f, 0.9f,0.9f);
        }else if(state.DinoAge >= 20){
            matrixStack.scale(1f, 1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public PteranodonRenderState createRenderState() {
        return new PteranodonRenderState();
    }

    @Override
    public void updateRenderState(PteranodonEntity livingEntity, PteranodonRenderState livingEntityRenderState, float f) {
        livingEntityRenderState.DinoAge = livingEntity.whatAge();
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.eatAnimationState.copyFrom(livingEntity.eatAnimationState);
        livingEntityRenderState.flyAnimationState.copyFrom(livingEntity.flyAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.sittingdownAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.standingupAnimationState.copyFrom(livingEntity.standingupAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
