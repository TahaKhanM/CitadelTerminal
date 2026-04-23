/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.structure;

import java.util.Objects;

public class BiSet<T> {
    public final T elem1;
    public final T elem2;

    public BiSet(T elem1, T elem2) {
        this.elem1 = elem1;
        this.elem2 = elem2;
    }

    public String toString() {
        return "{" + String.valueOf(this.elem1) + ", " + String.valueOf(this.elem2) + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BiSet biSet = (BiSet)o;
        return Objects.equals(this.elem1, biSet.elem1) && Objects.equals(this.elem2, biSet.elem2) || Objects.equals(this.elem1, biSet.elem2) && Objects.equals(this.elem2, biSet.elem1);
    }

    public int hashCode() {
        int hash1 = Objects.hash(this.elem1);
        int hash2 = Objects.hash(this.elem2);
        if (hash2 > hash1) {
            return Objects.hash(hash1, hash2);
        }
        return Objects.hash(hash2, hash1);
    }
}
