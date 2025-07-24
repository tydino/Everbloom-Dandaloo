package tydino.everbloom.block.dinosaurs.bipeds;

import com.mojang.serialization.MapCodec;
import tydino.everbloom.block.dinosaurs.DinosaurEgg;
import tydino.everbloom.entity.ModEntities;

public class HypsilophodonEgg extends DinosaurEgg {
    public static final MapCodec<HypsilophodonEgg> CODEC = createCodec(HypsilophodonEgg::new);

    public HypsilophodonEgg(Settings settings) {
        super(settings, 6, 0, 6, 10, 5, 10, 450, ModEntities.HYPSILOPHODON, CODEC);
    }
}
