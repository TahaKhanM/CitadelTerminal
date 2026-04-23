/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.MessageEvent;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_MessageEvent
extends MessageEvent {
    private final MessageEvent.Type type;
    private final long messageId;
    private final long uncompressedMessageSize;
    private final long compressedMessageSize;

    private AutoValue_MessageEvent(MessageEvent.Type type, long messageId, long uncompressedMessageSize, long compressedMessageSize) {
        this.type = type;
        this.messageId = messageId;
        this.uncompressedMessageSize = uncompressedMessageSize;
        this.compressedMessageSize = compressedMessageSize;
    }

    @Override
    public MessageEvent.Type getType() {
        return this.type;
    }

    @Override
    public long getMessageId() {
        return this.messageId;
    }

    @Override
    public long getUncompressedMessageSize() {
        return this.uncompressedMessageSize;
    }

    @Override
    public long getCompressedMessageSize() {
        return this.compressedMessageSize;
    }

    public String toString() {
        return "MessageEvent{type=" + (Object)((Object)this.type) + ", messageId=" + this.messageId + ", uncompressedMessageSize=" + this.uncompressedMessageSize + ", compressedMessageSize=" + this.compressedMessageSize + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof MessageEvent) {
            MessageEvent that = (MessageEvent)o;
            return this.type.equals((Object)that.getType()) && this.messageId == that.getMessageId() && this.uncompressedMessageSize == that.getUncompressedMessageSize() && this.compressedMessageSize == that.getCompressedMessageSize();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.type.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (this.messageId >>> 32 ^ this.messageId));
        h *= 1000003;
        h = (int)((long)h ^ (this.uncompressedMessageSize >>> 32 ^ this.uncompressedMessageSize));
        h *= 1000003;
        h = (int)((long)h ^ (this.compressedMessageSize >>> 32 ^ this.compressedMessageSize));
        return h;
    }

    static final class Builder
    extends MessageEvent.Builder {
        private MessageEvent.Type type;
        private Long messageId;
        private Long uncompressedMessageSize;
        private Long compressedMessageSize;

        Builder() {
        }

        @Override
        MessageEvent.Builder setType(MessageEvent.Type type) {
            if (type == null) {
                throw new NullPointerException("Null type");
            }
            this.type = type;
            return this;
        }

        @Override
        MessageEvent.Builder setMessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        @Override
        public MessageEvent.Builder setUncompressedMessageSize(long uncompressedMessageSize) {
            this.uncompressedMessageSize = uncompressedMessageSize;
            return this;
        }

        @Override
        public MessageEvent.Builder setCompressedMessageSize(long compressedMessageSize) {
            this.compressedMessageSize = compressedMessageSize;
            return this;
        }

        @Override
        public MessageEvent build() {
            String missing = "";
            if (this.type == null) {
                missing = missing + " type";
            }
            if (this.messageId == null) {
                missing = missing + " messageId";
            }
            if (this.uncompressedMessageSize == null) {
                missing = missing + " uncompressedMessageSize";
            }
            if (this.compressedMessageSize == null) {
                missing = missing + " compressedMessageSize";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_MessageEvent(this.type, this.messageId, this.uncompressedMessageSize, this.compressedMessageSize);
        }
    }
}

