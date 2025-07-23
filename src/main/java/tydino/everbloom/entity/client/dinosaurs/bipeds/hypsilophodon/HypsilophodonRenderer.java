package tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.hypsilophodon.HypsilophodonVariant;

import java.util.Map;

public class HypsilophodonRenderer extends MobEntityRenderer<HypsilophodonEntity, HypsilophodonRenderState, HypsilophodonModel> {
    private static final Map<HypsilophodonVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HypsilophodonVariant.class), map -> {
                map.put(HypsilophodonVariant.DESERT,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/hypsilophodon/hypsilophodon_desert.png"));
                map.put(HypsilophodonVariant.DRY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/hypsilophodon/hypsilophodon_dry.png"));
                map.put(HypsilophodonVariant.LEAF,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/hypsilophodon/hypsilophodon_leafy.png"));
                map.put(HypsilophodonVariant.LIGHT,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/hypsilophodon/hypsilophodon_light.png"));
                map.put(HypsilophodonVariant.SKY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/hypsilophodon/hypsilophodon_sky.png"));
            });

    public HypsilophodonRenderer(EntityRendererFactory.Context context) {
        super(context, new HypsilophodonModel(context.getPart(HypsilophodonModel.HYPSILOPHODON)), 0.25f);//shadow size set as f
    }

    @Override
    public Identifier getTexture(HypsilophodonRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(HypsilophodonRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.baby){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public HypsilophodonRenderState createRenderState() {
        return new HypsilophodonRenderState();
    }

    @Override
    public void updateRenderState(HypsilophodonEntity livingEntity, HypsilophodonRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.runAnimationState.copyFrom(livingEntity.runAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.sittingdownAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.standingupAnimationState.copyFrom(livingEntity.standingupAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
