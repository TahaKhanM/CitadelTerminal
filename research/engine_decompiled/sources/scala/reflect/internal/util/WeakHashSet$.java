/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.internal.util.WeakHashSet;

public final class WeakHashSet$ {
    public static final WeakHashSet$ MODULE$;
    private final int defaultInitialCapacity;
    private final double defaultLoadFactor;

    static {
        new WeakHashSet$();
    }

    public int defaultInitialCapacity() {
        return this.defaultInitialCapacity;
    }

    public double defaultLoadFactor() {
        return this.defaultLoadFactor;
    }

    public <A> WeakHashSet<A> apply(int initialCapacity, double loadFactor) {
        return new WeakHashSet(initialCapacity, this.defaultLoadFactor());
    }

    public <A> int apply$default$1() {
        return this.defaultInitialCapacity();
    }

    public <A> double apply$default$2() {
        return this.defaultLoadFactor();
    }

    private WeakHashSet$() {
        MODULE$ = this;
        this.defaultInitialCapacity = 16;
        this.defaultLoadFactor = 0.75;
    }
}

