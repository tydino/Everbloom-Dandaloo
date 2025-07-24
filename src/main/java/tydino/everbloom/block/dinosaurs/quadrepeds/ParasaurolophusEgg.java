package tydino.everbloom.block.dinosaurs.quadrepeds;

import com.mojang.serialization.MapCodec;
import tydino.everbloom.block.dinosaurs.DinosaurEgg;
import tydino.everbloom.entity.ModEntities;

public class ParasaurolophusEgg extends DinosaurEgg {
    public static final MapCodec<ParasaurolophusEgg> CODEC = createCodec(ParasaurolophusEgg::new);

    public ParasaurolophusEgg(Settings settings) {
        super(settings, 5, 0, 5, 12, 8, 12, 2000, ModEntities.PARASAUROLOPHUS, CODEC);
    }
}
