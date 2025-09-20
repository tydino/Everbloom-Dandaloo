package tydino.everbloom.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.unsorted.TortoiseEntity;

public class TortoiseRenderer extends MobEntityRenderer<TortoiseEntity, TortoiseRenderState, TortoiseModel> {
    private static final Identifier TEXTURE = Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/tortoise.png");

    public TortoiseRenderer(EntityRendererFactory.Context context) {
        super(context, new TortoiseModel(context.getPart(TortoiseModel.TORTOISE)), 0.5f);
    }

    @Override
    public Identifier getTexture(TortoiseRenderState state) {
        return TEXTURE;
    }

    @Override
    public void render(TortoiseRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(state.baby){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public TortoiseRenderState createRenderState() {
        return new TortoiseRenderState();
    }

    @Override
    public void updateRenderState(TortoiseEntity livingEntity, TortoiseRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }
}
