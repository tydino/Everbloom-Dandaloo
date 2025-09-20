package tydino.everbloom.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.unsorted.DaggerStabberEntity;

public class DaggerStabberRenderer extends MobEntityRenderer<DaggerStabberEntity, DaggerStabberRenderState, DaggerStabberModel> {
    private static final Identifier TEXTURE = Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dagger-stabber.png");

    public DaggerStabberRenderer(EntityRendererFactory.Context context) {
        super(context, new DaggerStabberModel(context.getPart(DaggerStabberModel.DAGGER_STABBER)), 0.45f);//shadow size is set by f
    }

    @Override
    public Identifier getTexture(DaggerStabberRenderState state) {
        return TEXTURE;
    }

    @Override
    public void render(DaggerStabberRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(state.baby){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public DaggerStabberRenderState createRenderState() {
        return new DaggerStabberRenderState();
    }

    @Override
    public void updateRenderState(DaggerStabberEntity livingEntity, DaggerStabberRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.attackAnimationState.copyFrom(livingEntity.attackAnimationState);
    }
}
