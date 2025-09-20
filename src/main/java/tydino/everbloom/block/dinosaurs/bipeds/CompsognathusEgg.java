package tydino.everbloom.block.dinosaurs.bipeds;

import com.mojang.serialization.MapCodec;
import tydino.everbloom.block.dinosaurs.DinosaurEgg;
import tydino.everbloom.entity.dinosaurs.DinosaurEntities;

public class CompsognathusEgg extends DinosaurEgg {
    public static final MapCodec<CompsognathusEgg> CODEC = createCodec(CompsognathusEgg::new);

    public CompsognathusEgg(Settings settings) {
        super(settings, 7, 0, 7, 9, 3, 9, 300, DinosaurEntities.COMPSOGNATHUS, CODEC);
    }
}
