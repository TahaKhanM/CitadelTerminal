/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.BatchingThreshold;
import com.google.api.gax.batching.ElementCounter;
import com.google.common.base.Preconditions;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class NumericThreshold<E>
implements BatchingThreshold<E> {
    private final long threshold;
    private final ElementCounter<E> extractor;
    private long sum;

    public NumericThreshold(long threshold, ElementCounter<E> extractor) {
        this.threshold = threshold;
        this.extractor = Preconditions.checkNotNull(extractor);
        this.sum = 0L;
    }

    @Override
    public void accumulate(E e) {
        this.sum += this.extractor.count(e);
    }

    @Override
    public boolean isThresholdReached() {
        return this.sum >= this.threshold;
    }

    @Override
    public BatchingThreshold<E> copyWithZeroedValue() {
        return new NumericThreshold<E>(this.threshold, this.extractor);
    }
}

