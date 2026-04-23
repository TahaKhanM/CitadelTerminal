/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.structure;

import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.Objects;

public class Vec2 {
    public final float x;
    public final float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coords floor() {
        return new Coords((int)this.x, (int)this.y);
    }

    public float distSqrd(Vec2 other) {
        return (this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y);
    }

    public float dist(Vec2 other) {
        return (float)Math.sqrt(this.distSqrd(other));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Vec2 vec2 = (Vec2)o;
        return Float.compare(vec2.x, this.x) == 0 && Float.compare(vec2.y, this.y) == 0;
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.x), Float.valueOf(this.y));
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}

