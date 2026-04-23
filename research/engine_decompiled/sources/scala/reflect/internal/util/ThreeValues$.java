/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

public final class ThreeValues$ {
    public static final ThreeValues$ MODULE$;
    private final int YES;
    private final int NO;
    private final int UNKNOWN;

    static {
        new ThreeValues$();
    }

    public final int YES() {
        return 1;
    }

    public final int NO() {
        return -1;
    }

    public final int UNKNOWN() {
        return 0;
    }

    public byte fromBoolean(boolean b) {
        return b ? (byte)1 : -1;
    }

    public boolean toBoolean(byte x) {
        return x == 1;
    }

    private ThreeValues$() {
        MODULE$ = this;
    }
}

