/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.Deframer;
import io.grpc.internal.GzipInflatingBuffer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.ReadableBuffer;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.Nullable;

public class ApplicationThreadDeframer
implements Deframer,
MessageDeframer.Listener {
    private final MessageDeframer.Listener storedListener;
    private final MessageDeframer deframer;
    private final TransportExecutor transportExecutor;
    private final Queue<InputStream> messageReadQueue = new ArrayDeque<InputStream>();

    ApplicationThreadDeframer(MessageDeframer.Listener listener, TransportExecutor transportExecutor, MessageDeframer deframer) {
        this.storedListener = Preconditions.checkNotNull(listener, "listener");
        this.transportExecutor = Preconditions.checkNotNull(transportExecutor, "transportExecutor");
        deframer.setListener(this);
        this.deframer = deframer;
    }

    @Override
    public void setMaxInboundMessageSize(int messageSize) {
        this.deframer.setMaxInboundMessageSize(messageSize);
    }

    @Override
    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    @Override
    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
        this.deframer.setFullStreamDecompressor(fullStreamDecompressor);
    }

    @Override
    public void request(final int numMessages) {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable(){

            @Override
            public void run() {
                if (ApplicationThreadDeframer.this.deframer.isClosed()) {
                    return;
                }
                try {
                    ApplicationThreadDeframer.this.deframer.request(numMessages);
                }
                catch (Throwable t) {
                    ApplicationThreadDeframer.this.storedListener.deframeFailed(t);
                    ApplicationThreadDeframer.this.deframer.close();
                }
            }
        }));
    }

    @Override
    public void deframe(final ReadableBuffer data) {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable(){

            @Override
            public void run() {
                try {
                    ApplicationThreadDeframer.this.deframer.deframe(data);
                }
                catch (Throwable t) {
                    ApplicationThreadDeframer.this.deframeFailed(t);
                    ApplicationThreadDeframer.this.deframer.close();
                }
            }
        }));
    }

    @Override
    public void closeWhenComplete() {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable(){

            @Override
            public void run() {
                ApplicationThreadDeframer.this.deframer.closeWhenComplete();
            }
        }));
    }

    @Override
    public void close() {
        this.deframer.stopDelivery();
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable(){

            @Override
            public void run() {
                ApplicationThreadDeframer.this.deframer.close();
            }
        }));
    }

    @Override
    public void bytesRead(final int numBytes) {
        this.transportExecutor.runOnTransportThread(new Runnable(){

            @Override
            public void run() {
                ApplicationThreadDeframer.this.storedListener.bytesRead(numBytes);
            }
        });
    }

    @Override
    public void messagesAvailable(StreamListener.MessageProducer producer) {
        InputStream message;
        while ((message = producer.next()) != null) {
            this.messageReadQueue.add(message);
        }
    }

    @Override
    public void deframerClosed(final boolean hasPartialMessage) {
        this.transportExecutor.runOnTransportThread(new Runnable(){

            @Override
            public void run() {
                ApplicationThreadDeframer.this.storedListener.deframerClosed(hasPartialMessage);
            }
        });
    }

    @Override
    public void deframeFailed(final Throwable cause) {
        this.transportExecutor.runOnTransportThread(new Runnable(){

            @Override
            public void run() {
                ApplicationThreadDeframer.this.storedListener.deframeFailed(cause);
            }
        });
    }

    private class InitializingMessageProducer
    implements StreamListener.MessageProducer {
        private final Runnable runnable;
        private boolean initialized = false;

        private InitializingMessageProducer(Runnable runnable) {
            this.runnable = runnable;
        }

        private void initialize() {
            if (!this.initialized) {
                this.runnable.run();
                this.initialized = true;
            }
        }

        @Override
        @Nullable
        public InputStream next() {
            this.initialize();
            return (InputStream)ApplicationThreadDeframer.this.messageReadQueue.poll();
        }
    }

    static interface TransportExecutor {
        public void runOnTransportThread(Runnable var1);
    }
}

