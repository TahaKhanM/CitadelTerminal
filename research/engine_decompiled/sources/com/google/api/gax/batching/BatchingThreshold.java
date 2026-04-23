/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public interface BatchingThreshold<E> {
    public void accumulate(E var1);

    public boolean isThresholdReached();

    public BatchingThreshold<E> copyWithZeroedValue();
}

