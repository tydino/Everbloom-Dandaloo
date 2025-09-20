package tydino.everbloom.entity.dinosaurs.biped.compsognathus;

import java.util.Arrays;
import java.util.Comparator;

public enum CompsognathusVariant {
    CANDY(0),
    LEAF(1),
    NEON(2),
    SANDY(3);

    private static final CompsognathusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(CompsognathusVariant::getId)).toArray(CompsognathusVariant[]::new);
    private final int id;

    CompsognathusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CompsognathusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
