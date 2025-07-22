package tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusVariant;

import java.util.Map;

public class CompsognathusRenderer extends MobEntityRenderer<CompsognathusEntity, CompsognathusRenderState, CompsognathusModel> {
    private static final Map<CompsognathusVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CompsognathusVariant.class), map -> {
                map.put(CompsognathusVariant.CANDY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/compsognathus/compsognathus_candy.png"));
                map.put(CompsognathusVariant.LEAF,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/compsognathus/compsognathus_leaf.png"));
                map.put(CompsognathusVariant.NEON,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/compsognathus/compsognathus_neon.png"));
                map.put(CompsognathusVariant.SANDY,
                        Identifier.of(EverbloomDandaloo.MOD_ID, "textures/entity/dinosaurs/compsognathus/compsognathus_sandy.png"));
            });

    public CompsognathusRenderer(EntityRendererFactory.Context context) {
        super(context, new CompsognathusModel(context.getPart(CompsognathusModel.COMPSOGNATHUS)), 0.5f);
    }

    @Override
    public Identifier getTexture(CompsognathusRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(CompsognathusRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public CompsognathusRenderState createRenderState() {
        return new CompsognathusRenderState();
    }

    @Override
    public void updateRenderState(CompsognathusEntity livingEntity, CompsognathusRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.runAnimationState.copyFrom(livingEntity.runAnimationState);
        livingEntityRenderState.sitAnimationState.copyFrom(livingEntity.sitAnimationState);
        livingEntityRenderState.sittingdownAnimationState.copyFrom(livingEntity.sittingdownAnimationState);
        livingEntityRenderState.standingupAnimationState.copyFrom(livingEntity.standingupAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();

    }
}
