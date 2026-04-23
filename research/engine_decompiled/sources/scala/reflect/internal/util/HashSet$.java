/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.internal.util.HashSet;

public final class HashSet$ {
    public static final HashSet$ MODULE$;

    static {
        new HashSet$();
    }

    public <T> HashSet<T> apply(int initialCapacity) {
        return this.apply("No Label", initialCapacity);
    }

    public <T> HashSet<T> apply(String label, int initialCapacity) {
        return new HashSet(label, initialCapacity);
    }

    private HashSet$() {
        MODULE$ = this;
    }
}

