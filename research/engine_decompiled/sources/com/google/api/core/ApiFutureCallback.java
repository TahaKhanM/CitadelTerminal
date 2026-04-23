/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

public interface ApiFutureCallback<V> {
    public void onFailure(Throwable var1);

    public void onSuccess(V var1);
}

