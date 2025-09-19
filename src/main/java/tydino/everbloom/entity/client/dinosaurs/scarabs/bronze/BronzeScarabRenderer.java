package tydino.everbloom.entity.client.dinosaurs.scarabs.bronze;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.client.DaggerStabberRenderState;
import tydino.everbloom.entity.custom.dinosaurs.scarab.BronzeScarabEntity;

public class BronzeScarabRenderer extends MobEntityRenderer<BronzeScarabEntity, BronzeScarabRenderState, BronzeScarabModel> {
    private static final Identifier TEXTURE = Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/scarabs/bronze_scarab.png");

    public BronzeScarabRenderer(EntityRendererFactory.Context context){
        super(context, new BronzeScarabModel(context.getPart(BronzeScarabModel.SCARAB)), 0.4f);//sets shadow size
    }

    @Override
    public Identifier getTexture(BronzeScarabRenderState state) {
        return TEXTURE;
    }

    @Override
    public BronzeScarabRenderState createRenderState() {
        return new BronzeScarabRenderState();
    }

    @Override
    public void updateRenderState(BronzeScarabEntity livingEntity, BronzeScarabRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }
}
