/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

interface Semaphore64 {
    public boolean acquire(long var1);

    public void release(long var1);
}

