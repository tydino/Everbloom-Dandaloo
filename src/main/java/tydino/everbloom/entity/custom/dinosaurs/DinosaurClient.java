package tydino.everbloom.entity.custom.dinosaurs;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import tydino.everbloom.EverbloomDandaloo;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.archaeopteryx.ArchaeopteryxRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus.CompsognathusModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.compsognathus.CompsognathusRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.hypsilophodon.HypsilophodonRenderer;
import tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon.PteranodonModel;
import tydino.everbloom.entity.client.dinosaurs.bipeds.pteranodon.PteranodonRenderer;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraModel;
import tydino.everbloom.entity.client.dinosaurs.insectoids.meganeura.MeganeuraRenderer;
import tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusModel;
import tydino.everbloom.entity.client.dinosaurs.quadrepeds.parasaurolophus.ParasaurolophusRenderer;
import tydino.everbloom.entity.client.dinosaurs.scarabs.bronze.BronzeScarabModel;
import tydino.everbloom.entity.client.dinosaurs.scarabs.bronze.BronzeScarabRenderer;

public class DinosaurClient {

    public static void clientDino(){
        EverbloomDandaloo.LOGGER.info("Making Dinosaurs visible");

        //scarabs
        EntityModelLayerRegistry.registerModelLayer(BronzeScarabModel.SCARAB, BronzeScarabModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.BRONZE_SCARAB, BronzeScarabRenderer::new);

        //insectoids

        //meganeura
        EntityModelLayerRegistry.registerModelLayer(MeganeuraModel.MEGANEURA, MeganeuraModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.MEGANEURA, MeganeuraRenderer::new);
        EntityRendererRegistry.register(DinosaurEntities.AGGRESSIVE_MEGANEURA, MeganeuraRenderer::new);

        //bipeds

        //hypsilophodon
        EntityModelLayerRegistry.registerModelLayer(HypsilophodonModel.HYPSILOPHODON, HypsilophodonModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.HYPSILOPHODON, HypsilophodonRenderer::new);
        EntityRendererRegistry.register(DinosaurEntities.HYPSILOPHODON_UNTAMABLE, HypsilophodonRenderer::new);

        //compsognathus
        EntityModelLayerRegistry.registerModelLayer(CompsognathusModel.COMPSOGNATHUS, CompsognathusModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.COMPSOGNATHUS, CompsognathusRenderer::new);
        EntityRendererRegistry.register(DinosaurEntities.COMPSOGNATHUS_UNTAMABLE, CompsognathusRenderer::new);

        //archaeopteryx
        EntityModelLayerRegistry.registerModelLayer(ArchaeopteryxModel.ARCHAEOPTERYX, ArchaeopteryxModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.ARCHAEOPTERYX, ArchaeopteryxRenderer::new);
        EntityRendererRegistry.register(DinosaurEntities.ARCHAEOPTERYX_UNTAMABLE, ArchaeopteryxRenderer::new);

        //pteranodon
        EntityModelLayerRegistry.registerModelLayer(PteranodonModel.PTERANODON, PteranodonModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.PTERANODON, PteranodonRenderer::new);

        //quadrepeds

        //parasaurolophus
        EntityModelLayerRegistry.registerModelLayer(ParasaurolophusModel.PARASAUROLOPHUS, ParasaurolophusModel::getTexturedModelData);
        EntityRendererRegistry.register(DinosaurEntities.PARASAUROLOPHUS, ParasaurolophusRenderer::new);
        EntityRendererRegistry.register(DinosaurEntities.PARASAUROLOPHUS_UNTAMABLE, ParasaurolophusRenderer::new);
    }
}
