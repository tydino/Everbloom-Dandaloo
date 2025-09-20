package tydino.everbloom.block.dinosaurs.bipeds;

import com.mojang.serialization.MapCodec;
import tydino.everbloom.block.dinosaurs.DinosaurEgg;
import tydino.everbloom.entity.dinosaurs.DinosaurEntities;

public class PteranodonEgg extends DinosaurEgg {
    public static final MapCodec<PteranodonEgg> CODEC = createCodec(PteranodonEgg::new);

    public PteranodonEgg(Settings settings) {
        super(settings, 5, 0, 5, 9, 10, 9, 800, DinosaurEntities.PTERANODON, CODEC);
    }
}
