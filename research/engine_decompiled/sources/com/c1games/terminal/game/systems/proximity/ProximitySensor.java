/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.proximity.GridMask;
import java.util.Objects;

public class ProximitySensor<U> {
    public final U userData;
    public final float radius;
    public final Coords location;
    public final GridMask gridMask;
    public final int identityMask;
    public final int isHitByMask;

    public ProximitySensor(U userData, float radius, Coords location, GridMask gridMask, int identityMask, int isHitByMask) {
        Objects.requireNonNull(location);
        this.userData = userData;
        this.radius = radius;
        this.location = location;
        this.gridMask = gridMask;
        this.identityMask = identityMask;
        this.isHitByMask = isHitByMask;
    }

    public char identityChar() {
        int m;
        int n = 0;
        for (m = this.identityMask; n < 10 && m != 1; ++n, m >>= 1) {
        }
        if (m == 1) {
            return (char)(48 + n);
        }
        return '!';
    }

    public boolean isHitBy(ProximitySensor<U> other) {
        if ((this.isHitByMask & other.identityMask) == 0) {
            return false;
        }
        return Math.sqrt(this.location.toFloat().distSqrd(other.location.toFloat())) <= (double)(this.radius + other.radius);
    }

    public String toString() {
        return "ProximitySensor{userData=" + String.valueOf(this.userData) + ", radius=" + this.radius + ", location=" + String.valueOf(this.location) + ", gridMask=null, identityMask=" + this.identityMask + ", isHitByMask=" + this.isHitByMask + "}";
    }
}

