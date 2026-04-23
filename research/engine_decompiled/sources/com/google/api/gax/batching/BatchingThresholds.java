/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.BatchingThreshold;
import com.google.api.gax.batching.ElementCounter;
import com.google.api.gax.batching.NumericThreshold;
import com.google.common.collect.ImmutableList;
import java.util.List;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class BatchingThresholds {
    public static <E> List<BatchingThreshold<E>> create(long elementThreshold) {
        NumericThreshold batchingThreshold = new NumericThreshold(elementThreshold, new ElementCounter<E>(){

            @Override
            public long count(E e) {
                return 1L;
            }
        });
        return ImmutableList.of(batchingThreshold);
    }
}

