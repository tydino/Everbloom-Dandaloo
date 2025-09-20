package tydino.everbloom.entity.dinosaurs.biped.hypsilophodon;

import java.util.Arrays;
import java.util.Comparator;

public enum HypsilophodonVariant {
    DESERT(0),
    DRY(1),
    LEAF(2),
    LIGHT(3),
    SKY(4);

    private static final HypsilophodonVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(HypsilophodonVariant::getId)).toArray(HypsilophodonVariant[]::new);
    private final int id;

    HypsilophodonVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static HypsilophodonVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
