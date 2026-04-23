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
public final class SpanId
implements Comparable<SpanId> {
    public static final int SIZE = 8;
    public static final SpanId INVALID = new SpanId(new byte[8]);
    private final byte[] bytes;

    private SpanId(byte[] bytes2) {
        this.bytes = bytes2;
    }

    public static SpanId fromBytes(byte[] buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkArgument(buffer.length == 8, "Invalid size: expected %s, got %s", new Object[]{8, buffer.length});
        byte[] bytesCopied = Arrays.copyOf(buffer, 8);
        return new SpanId(bytesCopied);
    }

    public static SpanId fromBytes(byte[] src, int srcOffset) {
        byte[] bytes2 = new byte[8];
        System.arraycopy(src, srcOffset, bytes2, 0, 8);
        return new SpanId(bytes2);
    }

    public static SpanId fromLowerBase16(CharSequence src) {
        Preconditions.checkArgument(src.length() == 16, "Invalid size: expected %s, got %s", new Object[]{16, src.length()});
        byte[] bytes2 = BaseEncoding.base16().lowerCase().decode(src);
        return new SpanId(bytes2);
    }

    public static SpanId generateRandomId(Random random) {
        byte[] bytes2 = new byte[8];
        do {
            random.nextBytes(bytes2);
        } while (Arrays.equals(bytes2, SpanId.INVALID.bytes));
        return new SpanId(bytes2);
    }

    public byte[] getBytes() {
        return Arrays.copyOf(this.bytes, 8);
    }

    public void copyBytesTo(byte[] dest, int destOffset) {
        System.arraycopy(this.bytes, 0, dest, destOffset, 8);
    }

    public boolean isValid() {
        return !Arrays.equals(this.bytes, SpanId.INVALID.bytes);
    }

    public String toLowerBase16() {
        return BaseEncoding.base16().lowerCase().encode(this.bytes);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanId)) {
            return false;
        }
        SpanId that = (SpanId)obj;
        return Arrays.equals(this.bytes, that.bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("spanId", BaseEncoding.base16().lowerCase().encode(this.bytes)).toString();
    }

    @Override
    public int compareTo(SpanId that) {
        for (int i = 0; i < 8; ++i) {
            if (this.bytes[i] == that.bytes[i]) continue;
            return this.bytes[i] < that.bytes[i] ? -1 : 1;
        }
        return 0;
    }
}

