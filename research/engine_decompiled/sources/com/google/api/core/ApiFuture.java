/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface ApiFuture<V>
extends Future<V> {
    public void addListener(Runnable var1, Executor var2);
}

