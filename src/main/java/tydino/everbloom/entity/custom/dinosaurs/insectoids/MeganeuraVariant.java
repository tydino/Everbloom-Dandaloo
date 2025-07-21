package tydino.everbloom.entity.custom.dinosaurs.insectoids;

import tydino.everbloom.entity.custom.MallardVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum MeganeuraVariant {
    BUBBLEGUM(0),
    NEUTRAL(1),
    SANDY(2);

    private static final MeganeuraVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MeganeuraVariant::getId)).toArray(MeganeuraVariant[]::new);
    private final int id;

    MeganeuraVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MeganeuraVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
