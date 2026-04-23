/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class TraceId
implements Comparable<TraceId> {
    public static final int SIZE = 16;
    public static final TraceId INVALID = new TraceId(new byte[16]);
    private final byte[] bytes;

    private TraceId(byte[] bytes2) {
        this.bytes = bytes2;
    }

    public static TraceId fromBytes(byte[] buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkArgument(buffer.length == 16, "Invalid size: expected %s, got %s", new Object[]{16, buffer.length});
        byte[] bytesCopied = Arrays.copyOf(buffer, 16);
        return new TraceId(bytesCopied);
    }

    public static TraceId fromBytes(byte[] src, int srcOffset) {
        byte[] bytes2 = new byte[16];
        System.arraycopy(src, srcOffset, bytes2, 0, 16);
        return new TraceId(bytes2);
    }

    public static TraceId fromLowerBase16(CharSequence src) {
        Preconditions.checkArgument(src.length() == 32, "Invalid size: expected %s, got %s", new Object[]{32, src.length()});
        byte[] bytes2 = BaseEncoding.base16().lowerCase().decode(src);
        return new TraceId(bytes2);
    }

    public static TraceId generateRandomId(Random random) {
        byte[] bytes2 = new byte[16];
        do {
            random.nextBytes(bytes2);
        } while (Arrays.equals(bytes2, TraceId.INVALID.bytes));
        return new TraceId(bytes2);
    }

    public byte[] getBytes() {
        return Arrays.copyOf(this.bytes, 16);
    }

    public void copyBytesTo(byte[] dest, int destOffset) {
        System.arraycopy(this.bytes, 0, dest, destOffset, 16);
    }

    public boolean isValid() {
        return !Arrays.equals(this.bytes, TraceId.INVALID.bytes);
    }

    public String toLowerBase16() {
        return BaseEncoding.base16().lowerCase().encode(this.bytes);
    }

    public long getLowerLong() {
        long result2 = 0L;
        for (int i = 0; i < 8; ++i) {
            result2 <<= 8;
            result2 |= (long)(this.bytes[i] & 0xFF);
        }
        if (result2 < 0L) {
            return -result2;
        }
        return result2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceId)) {
            return false;
        }
        TraceId that = (TraceId)obj;
        return Arrays.equals(this.bytes, that.bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("traceId", BaseEncoding.base16().lowerCase().encode(this.bytes)).toString();
    }

    @Override
    public int compareTo(TraceId that) {
        for (int i = 0; i < 16; ++i) {
            if (this.bytes[i] == that.bytes[i]) continue;
            return this.bytes[i] < that.bytes[i] ? -1 : 1;
        }
        return 0;
    }
}

