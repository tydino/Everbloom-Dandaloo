package tydino.everbloom.entity.unsorted;

import java.util.Arrays;
import java.util.Comparator;

public enum MallardVariant {
    MALE(0),
    FEMALE(1);

    private static final MallardVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MallardVariant::getId)).toArray(MallardVariant[]::new);
    private final int id;

    MallardVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MallardVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
