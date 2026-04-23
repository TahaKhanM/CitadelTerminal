/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Throwables {
    public static RuntimeException propagate(Throwable throwable) {
        return com.google.api.client.repackaged.com.google.common.base.Throwables.propagate(throwable);
    }

    public static void propagateIfPossible(Throwable throwable) {
        if (throwable != null) {
            com.google.api.client.repackaged.com.google.common.base.Throwables.throwIfUnchecked(throwable);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable throwable, Class<X> declaredType) throws X {
        com.google.api.client.repackaged.com.google.common.base.Throwables.propagateIfPossible(throwable, declaredType);
    }

    private Throwables() {
    }
}

