/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    private Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public static <X, Y> Tuple<X, Y> of(X x, Y y) {
        return new Tuple<X, Y>(x, y);
    }

    public X x() {
        return this.x;
    }

    public Y y() {
        return this.y;
    }
}

