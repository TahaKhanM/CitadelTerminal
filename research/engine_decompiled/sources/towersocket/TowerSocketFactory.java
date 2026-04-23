/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import towersocket.Serializer;
import towersocket.TowerSocket;
import towersocket.impl.TowerSocketImpl;

public class TowerSocketFactory {
    private TowerSocketFactory() {
    }

    public static TowerSocket create(Serializer serializer) {
        System.out.println("Tower Socket: 1.0.6");
        return new TowerSocketImpl(serializer);
    }
}

