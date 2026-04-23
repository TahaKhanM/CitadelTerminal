/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import java.util.HashSet;
import java.util.TreeSet;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Sets {
    public static <E> HashSet<E> newHashSet() {
        return new HashSet();
    }

    public static <E extends Comparable<?>> TreeSet<E> newTreeSet() {
        return new TreeSet();
    }

    private Sets() {
    }
}

