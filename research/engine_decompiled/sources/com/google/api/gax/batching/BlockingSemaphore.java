/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.Semaphore64;
import com.google.common.base.Preconditions;

class BlockingSemaphore
implements Semaphore64 {
    private long currentPermits;

    private static void checkNotNegative(long l) {
        Preconditions.checkArgument(l >= 0L, "negative permits not allowed: %s", l);
    }

    BlockingSemaphore(long permits) {
        BlockingSemaphore.checkNotNegative(permits);
        this.currentPermits = permits;
    }

    @Override
    public synchronized void release(long permits) {
        BlockingSemaphore.checkNotNegative(permits);
        this.currentPermits += permits;
        this.notifyAll();
    }

    @Override
    public synchronized boolean acquire(long permits) {
        BlockingSemaphore.checkNotNegative(permits);
        boolean interrupted = false;
        while (this.currentPermits < permits) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                interrupted = true;
            }
        }
        this.currentPermits -= permits;
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return true;
    }
}

