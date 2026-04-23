/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.Semaphore64;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;

class NonBlockingSemaphore
implements Semaphore64 {
    private final AtomicLong currentPermits;

    private static void checkNotNegative(long l) {
        Preconditions.checkArgument(l >= 0L, "negative permits not allowed: %s", l);
    }

    NonBlockingSemaphore(long permits) {
        NonBlockingSemaphore.checkNotNegative(permits);
        this.currentPermits = new AtomicLong(permits);
    }

    @Override
    public void release(long permits) {
        NonBlockingSemaphore.checkNotNegative(permits);
        this.currentPermits.addAndGet(permits);
    }

    @Override
    public boolean acquire(long permits) {
        long old;
        NonBlockingSemaphore.checkNotNegative(permits);
        do {
            if ((old = this.currentPermits.get()) >= permits) continue;
            return false;
        } while (!this.currentPermits.compareAndSet(old, old - permits));
        return true;
    }
}

