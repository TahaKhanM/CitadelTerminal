/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

@BetaApi
public class Distribution {
    private final AtomicLongArray buckets;
    private final AtomicInteger count = new AtomicInteger(0);

    public Distribution(int endValue) {
        Preconditions.checkArgument(endValue > 0);
        this.buckets = new AtomicLongArray(endValue);
    }

    @Deprecated
    public long getNthPercentile(double percentile) {
        return this.getPercentile(percentile);
    }

    public int getPercentile(double percentile) {
        Preconditions.checkArgument(percentile > 0.0);
        Preconditions.checkArgument(percentile <= 100.0);
        long targetRank = (long)Math.ceil(percentile * (double)this.count.get() / 100.0);
        long rank = 0L;
        for (int i = 0; i < this.buckets.length(); ++i) {
            if ((rank += this.buckets.get(i)) < targetRank) continue;
            return i;
        }
        return this.buckets.length();
    }

    public void record(int value) {
        Preconditions.checkArgument(value >= 0);
        if (value >= this.buckets.length()) {
            value = this.buckets.length() - 1;
        }
        this.buckets.incrementAndGet(value);
        this.count.incrementAndGet();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("endValue", this.buckets.length()).add("count", this.count.get()).toString();
    }
}

