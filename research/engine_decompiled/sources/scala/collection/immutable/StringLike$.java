/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

public final class StringLike$ {
    public static final StringLike$ MODULE$;
    private final int LF;
    private final int FF;
    private final int CR;
    private final int SU;

    static {
        new StringLike$();
    }

    private final int LF() {
        return 10;
    }

    private final int FF() {
        return 12;
    }

    private final int CR() {
        return 13;
    }

    private final int SU() {
        return 26;
    }

    private StringLike$() {
        MODULE$ = this;
    }
}

