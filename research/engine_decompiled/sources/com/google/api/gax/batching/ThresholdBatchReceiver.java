/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public interface ThresholdBatchReceiver<BatchT> {
    public void validateBatch(BatchT var1);

    public ApiFuture<?> processBatch(BatchT var1);
}

