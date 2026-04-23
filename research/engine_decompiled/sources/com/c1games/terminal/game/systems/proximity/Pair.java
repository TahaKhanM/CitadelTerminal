/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import java.util.Objects;

public class Pair<T> {
    public final T elem1;
    public final T elem2;

    public Pair(T elem1, T elem2) {
        this.elem1 = elem1;
        this.elem2 = elem2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair)o;
        return Objects.equals(this.elem1, pair.elem1) && Objects.equals(this.elem2, pair.elem2);
    }

    public int hashCode() {
        return Objects.hash(this.elem1, this.elem2);
    }

    public String toString() {
        return "(" + String.valueOf(this.elem1) + ", " + String.valueOf(this.elem2) + ")";
    }
}

