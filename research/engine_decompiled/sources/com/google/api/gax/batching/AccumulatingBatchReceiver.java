/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.batching.ThresholdBatchReceiver;
import java.util.ArrayList;
import java.util.List;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class AccumulatingBatchReceiver<T>
implements ThresholdBatchReceiver<T> {
    private final List<T> batches = new ArrayList<T>();

    @Override
    public void validateBatch(T message) {
    }

    @Override
    public ApiFuture<?> processBatch(T batch) {
        this.batches.add(batch);
        return ApiFutures.immediateFuture(null);
    }

    public List<T> getBatches() {
        return this.batches;
    }
}

