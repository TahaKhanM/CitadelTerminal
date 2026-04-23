/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiFuture;

public interface ApiAsyncFunction<I, O> {
    public ApiFuture<O> apply(I var1) throws Exception;
}

