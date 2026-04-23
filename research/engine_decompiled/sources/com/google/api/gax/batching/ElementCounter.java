/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public interface ElementCounter<E> {
    public long count(E var1);
}

