/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.propagation;

import com.google.common.base.Preconditions;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.propagation.SpanContextParseException;
import java.text.ParseException;

public abstract class BinaryFormat {
    static final NoopBinaryFormat NOOP_BINARY_FORMAT = new NoopBinaryFormat();

    @Deprecated
    public byte[] toBinaryValue(SpanContext spanContext) {
        return this.toByteArray(spanContext);
    }

    public byte[] toByteArray(SpanContext spanContext) {
        return this.toBinaryValue(spanContext);
    }

    @Deprecated
    public SpanContext fromBinaryValue(byte[] bytes2) throws ParseException {
        try {
            return this.fromByteArray(bytes2);
        }
        catch (SpanContextParseException e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    public SpanContext fromByteArray(byte[] bytes2) throws SpanContextParseException {
        try {
            return this.fromBinaryValue(bytes2);
        }
        catch (ParseException e) {
            throw new SpanContextParseException("Error while parsing.", e);
        }
    }

    static BinaryFormat getNoopBinaryFormat() {
        return NOOP_BINARY_FORMAT;
    }

    private static final class NoopBinaryFormat
    extends BinaryFormat {
        @Override
        public byte[] toByteArray(SpanContext spanContext) {
            Preconditions.checkNotNull(spanContext, "spanContext");
            return new byte[0];
        }

        @Override
        public SpanContext fromByteArray(byte[] bytes2) {
            Preconditions.checkNotNull(bytes2, "bytes");
            return SpanContext.INVALID;
        }

        private NoopBinaryFormat() {
        }
    }
}

