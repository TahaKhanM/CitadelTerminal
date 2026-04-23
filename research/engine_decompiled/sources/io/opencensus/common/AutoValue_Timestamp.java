/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import io.opencensus.common.Timestamp;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Timestamp
extends Timestamp {
    private final long seconds;
    private final int nanos;

    AutoValue_Timestamp(long seconds, int nanos) {
        this.seconds = seconds;
        this.nanos = nanos;
    }

    @Override
    public long getSeconds() {
        return this.seconds;
    }

    @Override
    public int getNanos() {
        return this.nanos;
    }

    public String toString() {
        return "Timestamp{seconds=" + this.seconds + ", nanos=" + this.nanos + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Timestamp) {
            Timestamp that = (Timestamp)o;
            return this.seconds == that.getSeconds() && this.nanos == that.getNanos();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (this.seconds >>> 32 ^ this.seconds));
        h *= 1000003;
        return h ^= this.nanos;
    }
}

