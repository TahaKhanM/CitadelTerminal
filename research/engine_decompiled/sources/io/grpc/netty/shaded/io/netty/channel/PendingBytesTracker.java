/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

abstract class PendingBytesTracker
implements MessageSizeEstimator.Handle {
    private final MessageSizeEstimator.Handle estimatorHandle;

    private PendingBytesTracker(MessageSizeEstimator.Handle estimatorHandle) {
        this.estimatorHandle = ObjectUtil.checkNotNull(estimatorHandle, "estimatorHandle");
    }

    @Override
    public final int size(Object msg) {
        return this.estimatorHandle.size(msg);
    }

    public abstract void incrementPendingOutboundBytes(long var1);

    public abstract void decrementPendingOutboundBytes(long var1);

    static PendingBytesTracker newTracker(Channel channel) {
        if (channel.pipeline() instanceof DefaultChannelPipeline) {
            return new DefaultChannelPipelinePendingBytesTracker((DefaultChannelPipeline)channel.pipeline());
        }
        ChannelOutboundBuffer buffer = channel.unsafe().outboundBuffer();
        MessageSizeEstimator.Handle handle = channel.config().getMessageSizeEstimator().newHandle();
        return buffer == null ? new NoopPendingBytesTracker(handle) : new ChannelOutboundBufferPendingBytesTracker(buffer, handle);
    }

    private static final class NoopPendingBytesTracker
    extends PendingBytesTracker {
        NoopPendingBytesTracker(MessageSizeEstimator.Handle estimatorHandle) {
            super(estimatorHandle);
        }

        @Override
        public void incrementPendingOutboundBytes(long bytes2) {
        }

        @Override
        public void decrementPendingOutboundBytes(long bytes2) {
        }
    }

    private static final class ChannelOutboundBufferPendingBytesTracker
    extends PendingBytesTracker {
        private final ChannelOutboundBuffer buffer;

        ChannelOutboundBufferPendingBytesTracker(ChannelOutboundBuffer buffer, MessageSizeEstimator.Handle estimatorHandle) {
            super(estimatorHandle);
            this.buffer = buffer;
        }

        @Override
        public void incrementPendingOutboundBytes(long bytes2) {
            this.buffer.incrementPendingOutboundBytes(bytes2);
        }

        @Override
        public void decrementPendingOutboundBytes(long bytes2) {
            this.buffer.decrementPendingOutboundBytes(bytes2);
        }
    }

    private static final class DefaultChannelPipelinePendingBytesTracker
    extends PendingBytesTracker {
        private final DefaultChannelPipeline pipeline;

        DefaultChannelPipelinePendingBytesTracker(DefaultChannelPipeline pipeline) {
            super(pipeline.estimatorHandle());
            this.pipeline = pipeline;
        }

        @Override
        public void incrementPendingOutboundBytes(long bytes2) {
            this.pipeline.incrementPendingOutboundBytes(bytes2);
        }

        @Override
        public void decrementPendingOutboundBytes(long bytes2) {
            this.pipeline.decrementPendingOutboundBytes(bytes2);
        }
    }
}

