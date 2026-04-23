/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class TraceOptions {
    private static final byte DEFAULT_OPTIONS = 0;
    private static final byte IS_SAMPLED = 1;
    public static final int SIZE = 1;
    public static final TraceOptions DEFAULT = new TraceOptions(0);
    private final byte options;

    private TraceOptions(byte options) {
        this.options = options;
    }

    public static TraceOptions fromBytes(byte[] buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkArgument(buffer.length == 1, "Invalid size: expected %s, got %s", new Object[]{1, buffer.length});
        return new TraceOptions(buffer[0]);
    }

    public static TraceOptions fromBytes(byte[] src, int srcOffset) {
        Preconditions.checkElementIndex(srcOffset, src.length);
        return new TraceOptions(src[srcOffset]);
    }

    public byte[] getBytes() {
        byte[] bytes2 = new byte[]{this.options};
        return bytes2;
    }

    public void copyBytesTo(byte[] dest, int destOffset) {
        Preconditions.checkElementIndex(destOffset, dest.length);
        dest[destOffset] = this.options;
    }

    public static Builder builder() {
        return new Builder(0);
    }

    public static Builder builder(TraceOptions traceOptions) {
        return new Builder(traceOptions.options);
    }

    public boolean isSampled() {
        return this.hasOption(1);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceOptions)) {
            return false;
        }
        TraceOptions that = (TraceOptions)obj;
        return this.options == that.options;
    }

    public int hashCode() {
        return Objects.hashCode(this.options);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("sampled", this.isSampled()).toString();
    }

    @VisibleForTesting
    byte getOptions() {
        return this.options;
    }

    private boolean hasOption(int mask) {
        return (this.options & mask) != 0;
    }

    public static final class Builder {
        private byte options;

        private Builder(byte options) {
            this.options = options;
        }

        @Deprecated
        public Builder setIsSampled() {
            return this.setIsSampled(true);
        }

        public Builder setIsSampled(boolean isSampled) {
            this.options = isSampled ? (byte)(this.options | 1) : (byte)(this.options & 0xFFFFFFFE);
            return this;
        }

        public TraceOptions build() {
            return new TraceOptions(this.options);
        }
    }
}

