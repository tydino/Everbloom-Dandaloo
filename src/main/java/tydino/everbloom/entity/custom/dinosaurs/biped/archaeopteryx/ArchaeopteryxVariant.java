package tydino.everbloom.entity.custom.dinosaurs.biped.archaeopteryx;

import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum ArchaeopteryxVariant {
    BLACKBERRY(0),
    BLUEBERRY(1),
    LEAF(2),
    PINKALICIOUS(3),
    SAND(4);

    private static final ArchaeopteryxVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(ArchaeopteryxVariant::getId)).toArray(ArchaeopteryxVariant[]::new);
    private final int id;

    ArchaeopteryxVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ArchaeopteryxVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
