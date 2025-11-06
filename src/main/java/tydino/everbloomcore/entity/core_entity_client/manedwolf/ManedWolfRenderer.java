package tydino.everbloomcore.entity.core_entity_client.manedwolf;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tydino.everbloomcore.EverbloomDandalooCore;
import tydino.everbloomcore.entity.core_entity_brains.ManedWolfEntity;

public class ManedWolfRenderer extends MobEntityRenderer<ManedWolfEntity, ManedWolfRenderState, ManedWolfModel> {
    static final Identifier TEXTURE = Identifier.of(EverbloomDandalooCore.MOD_ID, "textures/entities/maned_wolf_red.png");

    public ManedWolfRenderer(EntityRendererFactory.Context context){
        super(context, new ManedWolfModel(context.getPart(ManedWolfModel.MANED_WOLF)), 0.5f);//shadow
    }

    @Override
    public Identifier getTexture(ManedWolfRenderState state) {
        return TEXTURE;
    }

    @Override
    public void render(ManedWolfRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(state.baby){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ManedWolfRenderState createRenderState() {
        return new ManedWolfRenderState();
    }

    @Override
    public void updateRenderState(ManedWolfEntity livingEntity, ManedWolfRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.sittingAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.standingAnimationState.copyFrom(livingEntity.standingupAnimationState);
    }
}
