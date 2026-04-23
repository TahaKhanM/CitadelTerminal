/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.proximity.GridMask;
import com.c1games.terminal.game.systems.proximity.ProximitySensor;
import java.util.Collection;

public class ProximitySensorFactory {
    private final float radius;
    private final GridMask gridMask;
    private final int identityMask;
    private final int isHitByMask;

    public <T extends Enum<T>> ProximitySensorFactory(float radius, T identity, Collection<T> isHitBy) {
        this.radius = radius;
        this.gridMask = ProximitySensorFactory.rasterize(radius);
        this.identityMask = 1 << identity.ordinal();
        int filterMask = 0;
        for (Enum sensorClass : isHitBy) {
            filterMask |= 1 << sensorClass.ordinal();
        }
        this.isHitByMask = filterMask;
    }

    private static GridMask rasterize(double radius) {
        int min2 = (int)Math.floor(-radius);
        int max2 = (int)Math.ceil(radius) + 1;
        GridMask mask = new GridMask(new Coords(min2, min2), new Coords(max2, max2));
        for (int x = min2; x < max2; ++x) {
            for (int y = min2; y < max2; ++y) {
                double cy;
                double cx = Math.max(Math.min(0.0, (double)x + 0.5), (double)x - 0.5);
                if (!(cx * cx + (cy = Math.max(Math.min(0.0, (double)y + 0.5), (double)y - 0.5)) * cy <= radius * radius + 0.01)) continue;
                mask.raise(new Coords(x, y));
            }
        }
        return mask;
    }

    public <U> ProximitySensor<U> create(U userData, Coords location) {
        return new ProximitySensor<U>(userData, this.radius, location, this.gridMask, this.identityMask, this.isHitByMask);
    }

    public boolean identifiesAs(Enum<?> maskBit) {
        return (this.identityMask & 1 << maskBit.ordinal()) != 0;
    }
}

