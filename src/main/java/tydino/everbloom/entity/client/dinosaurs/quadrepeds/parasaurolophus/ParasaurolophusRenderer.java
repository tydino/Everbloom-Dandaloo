package tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusEntity;
import tydino.everbloom.entity.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusVariant;

import java.util.Map;

public class ParasaurolophusRenderer extends MobEntityRenderer<ParasaurolophusEntity, ParasaurolophusRenderState, ParasaurolophusModel> {
    private static final Map<ParasaurolophusVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ParasaurolophusVariant.class), map -> {
                map.put(ParasaurolophusVariant.CHOCOLATE,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/parasaurolophus/parasaurolophus_chocolate.png"));
                map.put(ParasaurolophusVariant.LEMON,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/parasaurolophus/parasaurolophus_lemon.png"));
                map.put(ParasaurolophusVariant.PEACH,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/parasaurolophus/parasaurolophus_peach.png"));
                map.put(ParasaurolophusVariant.ZEBRA,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/parasaurolophus/parasaurolophus_zebra.png"));
            });

    public ParasaurolophusRenderer(EntityRendererFactory.Context context) {
        super(context, new ParasaurolophusModel(context.getPart(ParasaurolophusModel.PARASAUROLOPHUS)), 2f);//shadow size set as f
    }

    @Override
    public Identifier getTexture(ParasaurolophusRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(ParasaurolophusRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.DinoAge <= 10){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else if(state.DinoAge <= 20){
            matrixStack.scale(0.6f, 0.6f, 0.6f);
        }else if(state.DinoAge <= 30){
            matrixStack.scale(0.7f, 0.7f, 0.7f);
        }else if(state.DinoAge <= 40){
            matrixStack.scale(0.8f, 0.8f, 0.8f);
        }else if(state.DinoAge <= 50){
            matrixStack.scale(0.9f, 0.9f, 0.9f);
        }else if(state.DinoAge >= 60){
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ParasaurolophusRenderState createRenderState() {
        return new ParasaurolophusRenderState();
    }

    @Override
    public void updateRenderState(ParasaurolophusEntity livingEntity, ParasaurolophusRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.eatAnimationState.copyFrom(livingEntity.eatAnimationState);
        livingEntityRenderState.runAnimationState.copyFrom(livingEntity.runAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.sittingdownAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.standingupAnimationState.copyFrom(livingEntity.standingupAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
