package tydino.everbloom.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.MallardEntity;
import tydino.everbloom.entity.custom.ToadEntity;
import tydino.everbloom.entity.custom.ToadVariant;

import java.util.Map;

public class ToadRenderer extends MobEntityRenderer<ToadEntity, ToadRenderState, ToadModel> {
    private static final Map<ToadVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ToadVariant.class), map -> {
                map.put(ToadVariant.MAIN,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/toad/toad_main.png"));
                map.put(ToadVariant.SECONDARY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/toad/toad_secondary.png"));
            });

    public ToadRenderer(EntityRendererFactory.Context context) {
        super(context, new ToadModel(context.getPart(ToadModel.TOAD)), 0.5f);
    }

    @Override
    public Identifier getTexture(ToadRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(ToadRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(state.baby){
            matrixStack.scale(0.75f, 0.75f, 0.75f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ToadRenderState createRenderState() {
        return new ToadRenderState();
    }

    @Override
    public void updateRenderState(ToadEntity livingEntity, ToadRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
