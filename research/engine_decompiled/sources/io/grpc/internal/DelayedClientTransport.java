/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ChannelExecutor;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.PickSubchannelArgsImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class DelayedClientTransport
implements ManagedClientTransport {
    private final LogId lodId = LogId.allocate(this.getClass().getName());
    private final Object lock = new Object();
    private final Executor defaultAppExecutor;
    private final ChannelExecutor channelExecutor;
    private Runnable reportTransportInUse;
    private Runnable reportTransportNotInUse;
    private Runnable reportTransportTerminated;
    private ManagedClientTransport.Listener listener;
    @Nonnull
    @GuardedBy(value="lock")
    private Collection<PendingStream> pendingStreams = new LinkedHashSet<PendingStream>();
    @GuardedBy(value="lock")
    private Status shutdownStatus;
    @Nullable
    @GuardedBy(value="lock")
    private LoadBalancer.SubchannelPicker lastPicker;
    @GuardedBy(value="lock")
    private long lastPickerVersion;

    DelayedClientTransport(Executor defaultAppExecutor, ChannelExecutor channelExecutor) {
        this.defaultAppExecutor = defaultAppExecutor;
        this.channelExecutor = channelExecutor;
    }

    @Override
    public final Runnable start(final ManagedClientTransport.Listener listener) {
        this.listener = listener;
        this.reportTransportInUse = new Runnable(){

            @Override
            public void run() {
                listener.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable(){

            @Override
            public void run() {
                listener.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable(){

            @Override
            public void run() {
                listener.transportTerminated();
            }
        };
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public final ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
        block17: {
            args = new PickSubchannelArgsImpl(method, headers, callOptions);
            pickerVersion = -1L;
            var8_6 = this.lock;
            // MONITORENTER : var8_6
            if (this.shutdownStatus == null) {
                if (this.lastPicker == null) {
                    var9_7 = this.createPendingStream(args);
                    // MONITOREXIT : var8_6
                    this.channelExecutor.drain();
                    return var9_7;
                }
                picker = this.lastPicker;
                pickerVersion = this.lastPickerVersion;
                break block17;
            }
            var9_8 = new FailingClientStream(this.shutdownStatus);
            // MONITOREXIT : var8_6
            this.channelExecutor.drain();
            return var9_8;
        }
        try {
            // MONITOREXIT : var8_6
            while (true) {
                if ((transport = GrpcUtil.getTransportFromPickResult(pickResult = picker.pickSubchannel(args), callOptions.isWaitForReady())) == null) ** break block18
                var10_11 = transport.newStream(args.getMethodDescriptor(), args.getHeaders(), args.getCallOptions());
                this.channelExecutor.drain();
                return var10_11;
            }
        }
        catch (Throwable var13_14) {
            this.channelExecutor.drain();
            throw var13_14;
        }
        {
            block19: {
                var10_11 = this.lock;
                // MONITORENTER : var10_11
                if (this.shutdownStatus != null) {
                    var11_12 = new FailingClientStream(this.shutdownStatus);
                    // MONITOREXIT : var10_11
                    this.channelExecutor.drain();
                    return var11_12;
                }
                if (pickerVersion != this.lastPickerVersion) break block19;
                var11_13 = this.createPendingStream(args);
                // MONITOREXIT : var10_11
                this.channelExecutor.drain();
                return var11_13;
            }
            picker = this.lastPicker;
            pickerVersion = this.lastPickerVersion;
            // MONITOREXIT : var10_11
            continue;
        }
    }

    @GuardedBy(value="lock")
    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs args) {
        PendingStream pendingStream = new PendingStream(args);
        this.pendingStreams.add(pendingStream);
        if (this.getPendingStreamsCount() == 1) {
            this.channelExecutor.executeLater(this.reportTransportInUse);
        }
        return pendingStream;
    }

    @Override
    public final void ping(ClientTransport.PingCallback callback, Executor executor) {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    @Override
    public ListenableFuture<Channelz.SocketStats> getStats() {
        SettableFuture<Channelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void shutdown(final Status status) {
        Object object = this.lock;
        synchronized (object) {
            if (this.shutdownStatus != null) {
                return;
            }
            this.shutdownStatus = status;
            this.channelExecutor.executeLater(new Runnable(){

                @Override
                public void run() {
                    DelayedClientTransport.this.listener.transportShutdown(status);
                }
            });
            if (!this.hasPendingStreams() && this.reportTransportTerminated != null) {
                this.channelExecutor.executeLater(this.reportTransportTerminated);
                this.reportTransportTerminated = null;
            }
        }
        this.channelExecutor.drain();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void shutdownNow(Status status) {
        Runnable savedReportTransportTerminated;
        Collection<PendingStream> savedPendingStreams;
        this.shutdown(status);
        Iterator<PendingStream> iterator2 = this.lock;
        synchronized (iterator2) {
            savedPendingStreams = this.pendingStreams;
            savedReportTransportTerminated = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!this.pendingStreams.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (savedReportTransportTerminated != null) {
            for (PendingStream stream : savedPendingStreams) {
                stream.cancel(status);
            }
            this.channelExecutor.executeLater(savedReportTransportTerminated).drain();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final boolean hasPendingStreams() {
        Object object = this.lock;
        synchronized (object) {
            return !this.pendingStreams.isEmpty();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @VisibleForTesting
    final int getPendingStreamsCount() {
        Object object = this.lock;
        synchronized (object) {
            return this.pendingStreams.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final void reprocess(@Nullable LoadBalancer.SubchannelPicker picker) {
        ArrayList<PendingStream> toProcess;
        Object object = this.lock;
        synchronized (object) {
            this.lastPicker = picker;
            ++this.lastPickerVersion;
            if (picker == null || !this.hasPendingStreams()) {
                return;
            }
            toProcess = new ArrayList<PendingStream>(this.pendingStreams);
        }
        ArrayList<PendingStream> toRemove = new ArrayList<PendingStream>();
        for (final PendingStream stream : toProcess) {
            CallOptions callOptions;
            LoadBalancer.PickResult pickResult = picker.pickSubchannel(stream.args);
            final ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickResult, (callOptions = stream.args.getCallOptions()).isWaitForReady());
            if (transport == null) continue;
            Executor executor = this.defaultAppExecutor;
            if (callOptions.getExecutor() != null) {
                executor = callOptions.getExecutor();
            }
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    stream.createRealStream(transport);
                }
            });
            toRemove.add(stream);
        }
        Object object2 = this.lock;
        synchronized (object2) {
            if (!this.hasPendingStreams()) {
                return;
            }
            this.pendingStreams.removeAll(toRemove);
            if (this.pendingStreams.isEmpty()) {
                this.pendingStreams = new LinkedHashSet<PendingStream>();
            }
            if (!this.hasPendingStreams()) {
                this.channelExecutor.executeLater(this.reportTransportNotInUse);
                if (this.shutdownStatus != null && this.reportTransportTerminated != null) {
                    this.channelExecutor.executeLater(this.reportTransportTerminated);
                    this.reportTransportTerminated = null;
                }
            }
        }
        this.channelExecutor.drain();
    }

    @Override
    public LogId getLogId() {
        return this.lodId;
    }

    private class PendingStream
    extends DelayedStream {
        private final LoadBalancer.PickSubchannelArgs args;
        private final Context context = Context.current();

        private PendingStream(LoadBalancer.PickSubchannelArgs args) {
            this.args = args;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void createRealStream(ClientTransport transport) {
            ClientStream realStream;
            Context origContext = this.context.attach();
            try {
                realStream = transport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions());
            }
            finally {
                this.context.detach(origContext);
            }
            this.setStream(realStream);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void cancel(Status reason) {
            super.cancel(reason);
            Object object = DelayedClientTransport.this.lock;
            synchronized (object) {
                if (DelayedClientTransport.this.reportTransportTerminated != null) {
                    boolean justRemovedAnElement = DelayedClientTransport.this.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && justRemovedAnElement) {
                        DelayedClientTransport.this.channelExecutor.executeLater(DelayedClientTransport.this.reportTransportNotInUse);
                        if (DelayedClientTransport.this.shutdownStatus != null) {
                            DelayedClientTransport.this.channelExecutor.executeLater(DelayedClientTransport.this.reportTransportTerminated);
                            DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.channelExecutor.drain();
        }
    }
}

