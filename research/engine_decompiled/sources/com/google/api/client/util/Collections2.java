/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Collections2 {
    static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection)iterable;
    }

    private Collections2() {
    }
}

