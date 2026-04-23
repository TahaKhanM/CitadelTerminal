/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

class DelayedStream
implements ClientStream {
    private volatile boolean passThrough;
    private ClientStreamListener listener;
    private ClientStream realStream;
    @GuardedBy(value="this")
    private Status error;
    @GuardedBy(value="this")
    private List<Runnable> pendingCalls = new ArrayList<Runnable>();
    @GuardedBy(value="this")
    private DelayedStreamListener delayedListener;

    DelayedStream() {
    }

    @Override
    public void setMaxInboundMessageSize(final int maxSize) {
        if (this.passThrough) {
            this.realStream.setMaxInboundMessageSize(maxSize);
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.setMaxInboundMessageSize(maxSize);
                }
            });
        }
    }

    @Override
    public void setMaxOutboundMessageSize(final int maxSize) {
        if (this.passThrough) {
            this.realStream.setMaxOutboundMessageSize(maxSize);
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.setMaxOutboundMessageSize(maxSize);
                }
            });
        }
    }

    @Override
    public void setDeadline(final Deadline deadline) {
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.setDeadline(deadline);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final void setStream(ClientStream stream) {
        DelayedStream delayedStream = this;
        synchronized (delayedStream) {
            if (this.realStream != null) {
                return;
            }
            this.realStream = Preconditions.checkNotNull(stream, "stream");
        }
        this.drainPendingCalls();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drainPendingCalls() {
        assert (this.realStream != null);
        assert (!this.passThrough);
        List<Runnable> toRun = new ArrayList<Runnable>();
        DelayedStreamListener delayedListener = null;
        while (true) {
            DelayedStream delayedStream = this;
            synchronized (delayedStream) {
                if (this.pendingCalls.isEmpty()) {
                    this.pendingCalls = null;
                    this.passThrough = true;
                    delayedListener = this.delayedListener;
                    break;
                }
                ArrayList<Runnable> tmp = toRun;
                toRun = this.pendingCalls;
                this.pendingCalls = tmp;
            }
            for (Runnable runnable : toRun) {
                runnable.run();
            }
            toRun.clear();
        }
        if (delayedListener != null) {
            delayedListener.drainPendingCallbacks();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void delayOrExecute(Runnable runnable) {
        DelayedStream delayedStream = this;
        synchronized (delayedStream) {
            if (!this.passThrough) {
                this.pendingCalls.add(runnable);
                return;
            }
        }
        runnable.run();
    }

    @Override
    public void setAuthority(final String authority) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(authority, "authority");
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.setAuthority(authority);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void start(ClientStreamListener listener) {
        boolean savedPassThrough;
        Status savedError;
        Preconditions.checkState(this.listener == null, "already started");
        DelayedStream delayedStream = this;
        synchronized (delayedStream) {
            this.listener = Preconditions.checkNotNull(listener, "listener");
            savedError = this.error;
            savedPassThrough = this.passThrough;
            if (!savedPassThrough) {
                this.delayedListener = new DelayedStreamListener(listener);
                listener = this.delayedListener;
            }
        }
        if (savedError != null) {
            listener.closed(savedError, new Metadata());
            return;
        }
        if (savedPassThrough) {
            this.realStream.start(listener);
        } else {
            final ClientStreamListener finalListener = listener;
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.start(finalListener);
                }
            });
        }
    }

    @Override
    public Attributes getAttributes() {
        Preconditions.checkState(this.passThrough, "Called getAttributes before attributes are ready");
        return this.realStream.getAttributes();
    }

    @Override
    public void writeMessage(final InputStream message) {
        Preconditions.checkNotNull(message, "message");
        if (this.passThrough) {
            this.realStream.writeMessage(message);
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.writeMessage(message);
                }
            });
        }
    }

    @Override
    public void flush() {
        if (this.passThrough) {
            this.realStream.flush();
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.flush();
                }
            });
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void cancel(final Status reason) {
        Preconditions.checkNotNull(reason, "reason");
        boolean delegateToRealStream = true;
        ClientStreamListener listenerToClose = null;
        DelayedStream delayedStream = this;
        synchronized (delayedStream) {
            if (this.realStream == null) {
                this.realStream = NoopClientStream.INSTANCE;
                delegateToRealStream = false;
                listenerToClose = this.listener;
                this.error = reason;
            }
        }
        if (delegateToRealStream) {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.cancel(reason);
                }
            });
        } else {
            if (listenerToClose != null) {
                listenerToClose.closed(reason, new Metadata());
            }
            this.drainPendingCalls();
        }
    }

    @Override
    public void halfClose() {
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.halfClose();
            }
        });
    }

    @Override
    public void request(final int numMessages) {
        if (this.passThrough) {
            this.realStream.request(numMessages);
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.request(numMessages);
                }
            });
        }
    }

    @Override
    public void setCompressor(final Compressor compressor) {
        Preconditions.checkNotNull(compressor, "compressor");
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.setCompressor(compressor);
            }
        });
    }

    @Override
    public void setFullStreamDecompression(final boolean fullStreamDecompression) {
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.setFullStreamDecompression(fullStreamDecompression);
            }
        });
    }

    @Override
    public void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        this.delayOrExecute(new Runnable(){

            @Override
            public void run() {
                DelayedStream.this.realStream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    @Override
    public boolean isReady() {
        if (this.passThrough) {
            return this.realStream.isReady();
        }
        return false;
    }

    @Override
    public void setMessageCompression(final boolean enable) {
        if (this.passThrough) {
            this.realStream.setMessageCompression(enable);
        } else {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStream.this.realStream.setMessageCompression(enable);
                }
            });
        }
    }

    @VisibleForTesting
    ClientStream getRealStream() {
        return this.realStream;
    }

    private static class DelayedStreamListener
    implements ClientStreamListener {
        private final ClientStreamListener realListener;
        private volatile boolean passThrough;
        @GuardedBy(value="this")
        private List<Runnable> pendingCallbacks = new ArrayList<Runnable>();

        public DelayedStreamListener(ClientStreamListener listener) {
            this.realListener = listener;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void delayOrExecute(Runnable runnable) {
            DelayedStreamListener delayedStreamListener = this;
            synchronized (delayedStreamListener) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                    return;
                }
            }
            runnable.run();
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            if (this.passThrough) {
                this.realListener.messagesAvailable(producer);
            } else {
                this.delayOrExecute(new Runnable(){

                    @Override
                    public void run() {
                        DelayedStreamListener.this.realListener.messagesAvailable(producer);
                    }
                });
            }
        }

        @Override
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                this.delayOrExecute(new Runnable(){

                    @Override
                    public void run() {
                        DelayedStreamListener.this.realListener.onReady();
                    }
                });
            }
        }

        @Override
        public void headersRead(final Metadata headers) {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.headersRead(headers);
                }
            });
        }

        @Override
        public void closed(final Status status, final Metadata trailers) {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, trailers);
                }
            });
        }

        @Override
        public void closed(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata trailers) {
            this.delayOrExecute(new Runnable(){

                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, rpcProgress, trailers);
                }
            });
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void drainPendingCallbacks() {
            assert (!this.passThrough);
            List<Runnable> toRun = new ArrayList<Runnable>();
            while (true) {
                DelayedStreamListener delayedStreamListener = this;
                synchronized (delayedStreamListener) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        break;
                    }
                    ArrayList<Runnable> tmp = toRun;
                    toRun = this.pendingCallbacks;
                    this.pendingCallbacks = tmp;
                }
                for (Runnable runnable : toRun) {
                    runnable.run();
                }
                toRun.clear();
            }
        }
    }
}

