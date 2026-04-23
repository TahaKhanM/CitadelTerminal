/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.internal;

import com.google.common.base.Preconditions;
import io.opencensus.trace.BaseMessageEvent;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;

public final class BaseMessageEventUtil {
    public static MessageEvent asMessageEvent(BaseMessageEvent event) {
        Preconditions.checkNotNull(event);
        if (event instanceof MessageEvent) {
            return (MessageEvent)event;
        }
        NetworkEvent networkEvent = (NetworkEvent)event;
        MessageEvent.Type type = networkEvent.getType() == NetworkEvent.Type.RECV ? MessageEvent.Type.RECEIVED : MessageEvent.Type.SENT;
        return MessageEvent.builder(type, networkEvent.getMessageId()).setUncompressedMessageSize(networkEvent.getUncompressedMessageSize()).setCompressedMessageSize(networkEvent.getCompressedMessageSize()).build();
    }

    public static NetworkEvent asNetworkEvent(BaseMessageEvent event) {
        Preconditions.checkNotNull(event);
        if (event instanceof NetworkEvent) {
            return (NetworkEvent)event;
        }
        MessageEvent messageEvent = (MessageEvent)event;
        NetworkEvent.Type type = messageEvent.getType() == MessageEvent.Type.RECEIVED ? NetworkEvent.Type.RECV : NetworkEvent.Type.SENT;
        return NetworkEvent.builder(type, messageEvent.getMessageId()).setUncompressedMessageSize(messageEvent.getUncompressedMessageSize()).setCompressedMessageSize(messageEvent.getCompressedMessageSize()).build();
    }

    private BaseMessageEventUtil() {
    }
}

