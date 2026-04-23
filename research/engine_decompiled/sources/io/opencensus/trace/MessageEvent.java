/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import io.opencensus.trace.AutoValue_MessageEvent;
import io.opencensus.trace.BaseMessageEvent;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class MessageEvent
extends BaseMessageEvent {
    public static Builder builder(Type type, long messageId) {
        return new AutoValue_MessageEvent.Builder().setType(Preconditions.checkNotNull(type, "type")).setMessageId(messageId).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
    }

    public abstract Type getType();

    public abstract long getMessageId();

    public abstract long getUncompressedMessageSize();

    public abstract long getCompressedMessageSize();

    MessageEvent() {
    }

    public static abstract class Builder {
        abstract Builder setType(Type var1);

        abstract Builder setMessageId(long var1);

        public abstract Builder setUncompressedMessageSize(long var1);

        public abstract Builder setCompressedMessageSize(long var1);

        public abstract MessageEvent build();

        Builder() {
        }
    }

    public static enum Type {
        SENT,
        RECEIVED;

    }
}

