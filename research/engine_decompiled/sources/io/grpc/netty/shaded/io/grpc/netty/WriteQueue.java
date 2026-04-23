/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

class WriteQueue {
    @VisibleForTesting
    static final int DEQUE_CHUNK_SIZE = 128;
    private final Runnable later = new Runnable(){

        @Override
        public void run() {
            WriteQueue.this.flush();
        }
    };
    private final Channel channel;
    private final Queue<QueuedCommand> queue;
    private final AtomicBoolean scheduled = new AtomicBoolean();

    public WriteQueue(Channel channel) {
        this.channel = Preconditions.checkNotNull(channel, "channel");
        this.queue = new ConcurrentLinkedQueue<QueuedCommand>();
    }

    void scheduleFlush() {
        if (this.scheduled.compareAndSet(false, true)) {
            this.channel.eventLoop().execute(this.later);
        }
    }

    @CanIgnoreReturnValue
    ChannelFuture enqueue(QueuedCommand command, boolean flush2) {
        return this.enqueue(command, this.channel.newPromise(), flush2);
    }

    @CanIgnoreReturnValue
    ChannelFuture enqueue(QueuedCommand command, ChannelPromise promise, boolean flush2) {
        Preconditions.checkArgument(command.promise() == null, "promise must not be set on command");
        command.promise(promise);
        this.queue.add(command);
        if (flush2) {
            this.scheduleFlush();
        }
        return promise;
    }

    void enqueue(Runnable runnable, boolean flush2) {
        this.queue.add(new RunnableCommand(runnable));
        if (flush2) {
            this.scheduleFlush();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void flush() {
        try {
            QueuedCommand cmd;
            int i = 0;
            boolean flushedOnce = false;
            while ((cmd = this.queue.poll()) != null) {
                cmd.run(this.channel);
                if (++i != 128) continue;
                i = 0;
                this.channel.flush();
                flushedOnce = true;
            }
            if (i != 0 || !flushedOnce) {
                this.channel.flush();
            }
        }
        finally {
            this.scheduled.set(false);
            if (!this.queue.isEmpty()) {
                this.scheduleFlush();
            }
        }
    }

    static interface QueuedCommand {
        public ChannelPromise promise();

        public void promise(ChannelPromise var1);

        public void run(Channel var1);
    }

    static abstract class AbstractQueuedCommand
    implements QueuedCommand {
        private ChannelPromise promise;

        AbstractQueuedCommand() {
        }

        @Override
        public final void promise(ChannelPromise promise) {
            this.promise = promise;
        }

        @Override
        public final ChannelPromise promise() {
            return this.promise;
        }

        @Override
        public final void run(Channel channel) {
            channel.write(this, this.promise);
        }
    }

    private static class RunnableCommand
    implements QueuedCommand {
        private final Runnable runnable;

        public RunnableCommand(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public final void promise(ChannelPromise promise) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final ChannelPromise promise() {
            throw new UnsupportedOperationException();
        }

        @Override
        public final void run(Channel channel) {
            this.runnable.run();
        }
    }
}

