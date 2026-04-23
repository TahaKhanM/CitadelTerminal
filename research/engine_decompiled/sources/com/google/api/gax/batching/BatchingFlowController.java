/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.ElementCounter;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public class BatchingFlowController<T> {
    private final FlowController flowController;
    private final ElementCounter<T> elementCounter;
    private final ElementCounter<T> byteCounter;

    public BatchingFlowController(FlowController flowController, ElementCounter<T> elementCounter, ElementCounter<T> byteCounter) {
        this.flowController = flowController;
        this.elementCounter = elementCounter;
        this.byteCounter = byteCounter;
    }

    public void reserve(T batch) throws FlowController.FlowControlException {
        Preconditions.checkNotNull(batch);
        int elements = Ints.checkedCast(this.elementCounter.count(batch));
        int bytes2 = Ints.checkedCast(this.byteCounter.count(batch));
        this.flowController.reserve(elements, bytes2);
    }

    public void release(T batch) {
        Preconditions.checkNotNull(batch);
        int elements = Ints.checkedCast(this.elementCounter.count(batch));
        int bytes2 = Ints.checkedCast(this.byteCounter.count(batch));
        this.flowController.release(elements, bytes2);
    }
}

