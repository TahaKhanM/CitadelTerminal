/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.BlockingSemaphore;
import com.google.api.gax.batching.FlowControlSettings;
import com.google.api.gax.batching.NonBlockingSemaphore;
import com.google.api.gax.batching.Semaphore64;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public class FlowController {
    @Nullable
    private final Semaphore64 outstandingElementCount;
    @Nullable
    private final Semaphore64 outstandingByteCount;
    @Nullable
    private final Long maxOutstandingElementCount;
    @Nullable
    private final Long maxOutstandingRequestBytes;

    public FlowController(FlowControlSettings settings) {
        switch (settings.getLimitExceededBehavior()) {
            case ThrowException: 
            case Block: {
                break;
            }
            case Ignore: {
                this.maxOutstandingElementCount = null;
                this.maxOutstandingRequestBytes = null;
                this.outstandingElementCount = null;
                this.outstandingByteCount = null;
                return;
            }
            default: {
                throw new IllegalArgumentException("Unknown LimitBehaviour: " + (Object)((Object)settings.getLimitExceededBehavior()));
            }
        }
        this.maxOutstandingElementCount = settings.getMaxOutstandingElementCount();
        this.outstandingElementCount = this.maxOutstandingElementCount == null ? null : (settings.getLimitExceededBehavior() == LimitExceededBehavior.Block ? new BlockingSemaphore(this.maxOutstandingElementCount) : new NonBlockingSemaphore(this.maxOutstandingElementCount));
        this.maxOutstandingRequestBytes = settings.getMaxOutstandingRequestBytes();
        this.outstandingByteCount = this.maxOutstandingRequestBytes == null ? null : (settings.getLimitExceededBehavior() == LimitExceededBehavior.Block ? new BlockingSemaphore(this.maxOutstandingRequestBytes) : new NonBlockingSemaphore(this.maxOutstandingRequestBytes));
    }

    public void reserve(long elements, long bytes2) throws FlowControlException {
        long permitsToDraw;
        Preconditions.checkArgument(elements >= 0L);
        Preconditions.checkArgument(bytes2 >= 0L);
        if (this.outstandingElementCount != null && !this.outstandingElementCount.acquire(elements)) {
            throw new MaxOutstandingElementCountReachedException(this.maxOutstandingElementCount);
        }
        if (this.outstandingByteCount != null && !this.outstandingByteCount.acquire(permitsToDraw = Math.min(bytes2, this.maxOutstandingRequestBytes))) {
            if (this.outstandingElementCount != null) {
                this.outstandingElementCount.release(elements);
            }
            throw new MaxOutstandingRequestBytesReachedException(this.maxOutstandingRequestBytes);
        }
    }

    public void release(long elements, long bytes2) {
        Preconditions.checkArgument(elements >= 0L);
        Preconditions.checkArgument(bytes2 >= 0L);
        if (this.outstandingElementCount != null) {
            this.outstandingElementCount.release(elements);
        }
        if (this.outstandingByteCount != null) {
            long permitsToReturn = Math.min(bytes2, this.maxOutstandingRequestBytes);
            this.outstandingByteCount.release(permitsToReturn);
        }
    }

    @BetaApi
    public static enum LimitExceededBehavior {
        ThrowException,
        Block,
        Ignore;

    }

    @BetaApi
    public static final class MaxOutstandingRequestBytesReachedException
    extends FlowControlException {
        private final long currentMaxBytes;

        public MaxOutstandingRequestBytesReachedException(long currentMaxBytes) {
            this.currentMaxBytes = currentMaxBytes;
        }

        public long getCurrentMaxBatchBytes() {
            return this.currentMaxBytes;
        }

        @Override
        public String toString() {
            return String.format("The maximum number of batch bytes: %d have been reached.", this.currentMaxBytes);
        }
    }

    @BetaApi
    public static final class MaxOutstandingElementCountReachedException
    extends FlowControlException {
        private final long currentMaxElementCount;

        public MaxOutstandingElementCountReachedException(long currentMaxElementCount) {
            this.currentMaxElementCount = currentMaxElementCount;
        }

        public long getCurrentMaxBatchElementCount() {
            return this.currentMaxElementCount;
        }

        @Override
        public String toString() {
            return String.format("The maximum number of batch elements: %d have been reached.", this.currentMaxElementCount);
        }
    }

    @BetaApi
    public static class FlowControlRuntimeException
    extends RuntimeException {
        private FlowControlRuntimeException(FlowControlException e) {
            super(e);
        }

        public static FlowControlRuntimeException fromFlowControlException(FlowControlException e) {
            return new FlowControlRuntimeException(e);
        }
    }

    public static abstract class FlowControlException
    extends Exception {
        private FlowControlException() {
        }
    }
}

