/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.NetworkEvent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Deprecated
@Immutable
final class AutoValue_NetworkEvent
extends NetworkEvent {
    private final Timestamp kernelTimestamp;
    private final NetworkEvent.Type type;
    private final long messageId;
    private final long uncompressedMessageSize;
    private final long compressedMessageSize;

    private AutoValue_NetworkEvent(@Nullable Timestamp kernelTimestamp, NetworkEvent.Type type, long messageId, long uncompressedMessageSize, long compressedMessageSize) {
        this.kernelTimestamp = kernelTimestamp;
        this.type = type;
        this.messageId = messageId;
        this.uncompressedMessageSize = uncompressedMessageSize;
        this.compressedMessageSize = compressedMessageSize;
    }

    @Override
    @Nullable
    public Timestamp getKernelTimestamp() {
        return this.kernelTimestamp;
    }

    @Override
    public NetworkEvent.Type getType() {
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
        return "NetworkEvent{kernelTimestamp=" + this.kernelTimestamp + ", type=" + (Object)((Object)this.type) + ", messageId=" + this.messageId + ", uncompressedMessageSize=" + this.uncompressedMessageSize + ", compressedMessageSize=" + this.compressedMessageSize + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof NetworkEvent) {
            NetworkEvent that = (NetworkEvent)o;
            return (this.kernelTimestamp == null ? that.getKernelTimestamp() == null : this.kernelTimestamp.equals(that.getKernelTimestamp())) && this.type.equals((Object)that.getType()) && this.messageId == that.getMessageId() && this.uncompressedMessageSize == that.getUncompressedMessageSize() && this.compressedMessageSize == that.getCompressedMessageSize();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.kernelTimestamp == null ? 0 : this.kernelTimestamp.hashCode();
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
    extends NetworkEvent.Builder {
        private Timestamp kernelTimestamp;
        private NetworkEvent.Type type;
        private Long messageId;
        private Long uncompressedMessageSize;
        private Long compressedMessageSize;

        Builder() {
        }

        @Override
        public NetworkEvent.Builder setKernelTimestamp(@Nullable Timestamp kernelTimestamp) {
            this.kernelTimestamp = kernelTimestamp;
            return this;
        }

        @Override
        NetworkEvent.Builder setType(NetworkEvent.Type type) {
            if (type == null) {
                throw new NullPointerException("Null type");
            }
            this.type = type;
            return this;
        }

        @Override
        NetworkEvent.Builder setMessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        @Override
        public NetworkEvent.Builder setUncompressedMessageSize(long uncompressedMessageSize) {
            this.uncompressedMessageSize = uncompressedMessageSize;
            return this;
        }

        @Override
        public NetworkEvent.Builder setCompressedMessageSize(long compressedMessageSize) {
            this.compressedMessageSize = compressedMessageSize;
            return this;
        }

        @Override
        public NetworkEvent build() {
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
            return new AutoValue_NetworkEvent(this.kernelTimestamp, this.type, this.messageId, this.uncompressedMessageSize, this.compressedMessageSize);
        }
    }
}

