package tydino.everbloom.entity.custom.unsorted;

import java.util.Arrays;
import java.util.Comparator;

public enum ToadVariant {
    MAIN(0),
    SECONDARY(1);

    private static final ToadVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(ToadVariant::getId)).toArray(ToadVariant[]::new);
    private final int id;

    ToadVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ToadVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
