/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import io.opencensus.common.Timestamp;
import io.opencensus.trace.AutoValue_NetworkEvent;
import io.opencensus.trace.BaseMessageEvent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Deprecated
@Immutable
public abstract class NetworkEvent
extends BaseMessageEvent {
    public static Builder builder(Type type, long messageId) {
        return new AutoValue_NetworkEvent.Builder().setType(Preconditions.checkNotNull(type, "type")).setMessageId(messageId).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
    }

    @Nullable
    public abstract Timestamp getKernelTimestamp();

    public abstract Type getType();

    public abstract long getMessageId();

    public abstract long getUncompressedMessageSize();

    public abstract long getCompressedMessageSize();

    @Deprecated
    public long getMessageSize() {
        return this.getUncompressedMessageSize();
    }

    NetworkEvent() {
    }

    @Deprecated
    public static abstract class Builder {
        abstract Builder setType(Type var1);

        abstract Builder setMessageId(long var1);

        public abstract Builder setKernelTimestamp(@Nullable Timestamp var1);

        @Deprecated
        public Builder setMessageSize(long messageSize) {
            return this.setUncompressedMessageSize(messageSize);
        }

        public abstract Builder setUncompressedMessageSize(long var1);

        public abstract Builder setCompressedMessageSize(long var1);

        public abstract NetworkEvent build();

        Builder() {
        }
    }

    public static enum Type {
        SENT,
        RECV;

    }
}

