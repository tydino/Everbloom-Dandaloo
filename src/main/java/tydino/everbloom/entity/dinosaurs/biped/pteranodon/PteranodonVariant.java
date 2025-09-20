package tydino.everbloom.entity.dinosaurs.biped.pteranodon;

import java.util.Arrays;
import java.util.Comparator;

public enum PteranodonVariant {
    MUD(0),
    DUCK(1),
    FIRE(2);

    private static final PteranodonVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(PteranodonVariant::getId)).toArray(PteranodonVariant[]::new);
    private final int id;

    PteranodonVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PteranodonVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
