/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Joiner {
    private final com.google.api.client.repackaged.com.google.common.base.Joiner wrapped;

    public static Joiner on(char separator) {
        return new Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner.on(separator));
    }

    private Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner wrapped) {
        this.wrapped = wrapped;
    }

    public final String join(Iterable<?> parts) {
        return this.wrapped.join(parts);
    }
}

