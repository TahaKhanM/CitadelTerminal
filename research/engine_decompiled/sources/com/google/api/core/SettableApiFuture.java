/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.AbstractApiFuture;

public final class SettableApiFuture<V>
extends AbstractApiFuture<V> {
    private SettableApiFuture() {
    }

    public static <V> SettableApiFuture<V> create() {
        return new SettableApiFuture<V>();
    }

    @Override
    public boolean set(V value) {
        return super.set(value);
    }

    @Override
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }
}

