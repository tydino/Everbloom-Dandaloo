package tydino.everbloom.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.unsorted.MallardEntity;
import tydino.everbloom.entity.custom.unsorted.MallardVariant;

import java.util.Map;

public class MallardRenderer extends MobEntityRenderer<MallardEntity, MallardRenderState, MallardModel> {
    private static final Map<MallardVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MallardVariant.class), map -> {
                map.put(MallardVariant.MALE,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/mallard/mallard_male.png"));
                map.put(MallardVariant.FEMALE,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/mallard/mallard_female.png"));
            });

    public MallardRenderer(EntityRendererFactory.Context context) {
        super(context, new MallardModel(context.getPart(MallardModel.MALLARD)), 0.5f);
    }

    @Override
    public Identifier getTexture(MallardRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(MallardRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(state.baby){
            matrixStack.scale(0.75f, 0.75f, 0.75f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public MallardRenderState createRenderState() {
        return new MallardRenderState();
    }


    @Override
    public void updateRenderState(MallardEntity livingEntity, MallardRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
