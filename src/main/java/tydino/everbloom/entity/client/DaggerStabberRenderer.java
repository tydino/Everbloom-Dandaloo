package tydino.everbloom.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.DaggerStabberEntity;

public class DaggerStabberRenderer extends MobEntityRenderer<DaggerStabberEntity, DaggerStabberModel<DaggerStabberEntity>> {
    private static final Identifier TEXTURE = Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dagger_stabber.png");

    public DaggerStabberRenderer(EntityRendererFactory.Context context) {
        super(context, new DaggerStabberModel<>(context.getPart(DaggerStabberModel.DAGGER_STABBER)), 0.5f);
    }

    @Override
    public Identifier getTexture(DaggerStabberEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DaggerStabberEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(mobEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
