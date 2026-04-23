/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import java.util.Iterator;
import java.util.function.BiConsumer;

public class ProximityUtil {
    private ProximityUtil() {
    }

    public static <T> void combine(Iterable<T> iterable, BiConsumer<T, T> consumer) {
        int aInt = 0;
        for (T aElem : iterable) {
            Iterator<T> bIter = iterable.iterator();
            for (int bInt = 0; bInt < aInt; ++bInt) {
                T bElem = bIter.next();
                consumer.accept(aElem, bElem);
            }
            ++aInt;
        }
    }
}

