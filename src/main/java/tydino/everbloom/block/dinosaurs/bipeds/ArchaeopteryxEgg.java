package tydino.everbloom.block.dinosaurs.bipeds;

import com.mojang.serialization.MapCodec;
import tydino.everbloom.block.dinosaurs.DinosaurEgg;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.dinosaurs.DinosaurEntities;

public class ArchaeopteryxEgg extends DinosaurEgg {
    public static final MapCodec<ArchaeopteryxEgg> CODEC = createCodec(ArchaeopteryxEgg::new);

    public ArchaeopteryxEgg(Settings settings) {
        super(settings, 6, 0, 6, 10, 4, 10, 600, DinosaurEntities.ARCHAEOPTERYX, CODEC);
    }
}
