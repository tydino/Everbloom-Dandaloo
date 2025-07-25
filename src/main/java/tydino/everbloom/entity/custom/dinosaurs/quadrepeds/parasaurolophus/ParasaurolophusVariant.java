package tydino.everbloom.entity.custom.dinosaurs.quadrepeds.parasaurolophus;

import java.util.Arrays;
import java.util.Comparator;

public enum ParasaurolophusVariant {

    CHOCOLATE(0),
    LEMON(1),
    PEACH(2),
    ZEBRA(3);

    private static final ParasaurolophusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(ParasaurolophusVariant::getId)).toArray(ParasaurolophusVariant[]::new);
    private final int id;

    ParasaurolophusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ParasaurolophusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
