/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;

public class FixedRecvByteBufAllocator
extends DefaultMaxMessagesRecvByteBufAllocator {
    private final int bufferSize;

    public FixedRecvByteBufAllocator(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("bufferSize must greater than 0: " + bufferSize);
        }
        this.bufferSize = bufferSize;
    }

    @Override
    public RecvByteBufAllocator.Handle newHandle() {
        return new HandleImpl(this.bufferSize);
    }

    @Override
    public FixedRecvByteBufAllocator respectMaybeMoreData(boolean respectMaybeMoreData) {
        super.respectMaybeMoreData(respectMaybeMoreData);
        return this;
    }

    private final class HandleImpl
    extends DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle {
        private final int bufferSize;

        public HandleImpl(int bufferSize) {
            super(FixedRecvByteBufAllocator.this);
            this.bufferSize = bufferSize;
        }

        @Override
        public int guess() {
            return this.bufferSize;
        }
    }
}

