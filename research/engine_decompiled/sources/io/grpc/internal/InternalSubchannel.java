/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.errorprone.annotations.ForOverride;
import io.grpc.CallOptions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ChannelExecutor;
import io.grpc.internal.ChannelTracer;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.ForwardingClientStream;
import io.grpc.internal.ForwardingClientStreamListener;
import io.grpc.internal.ForwardingConnectionClientTransport;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.Instrumented;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.PairSocketAddress;
import io.grpc.internal.ProxyDetector;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.WithLogId;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class InternalSubchannel
implements Instrumented<Channelz.ChannelStats> {
    private static final Logger log = Logger.getLogger(InternalSubchannel.class.getName());
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final String authority;
    private final String userAgent;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final Callback callback;
    private final ClientTransportFactory transportFactory;
    private final ScheduledExecutorService scheduledExecutor;
    private final Channelz channelz;
    private final CallTracer callsTracer;
    @CheckForNull
    private final ChannelTracer channelTracer;
    private final Object lock = new Object();
    private final ChannelExecutor channelExecutor;
    @GuardedBy(value="lock")
    private EquivalentAddressGroup addressGroup;
    @GuardedBy(value="lock")
    private int addressIndex;
    @GuardedBy(value="lock")
    private BackoffPolicy reconnectPolicy;
    @GuardedBy(value="lock")
    private final Stopwatch connectingTimer;
    @Nullable
    @GuardedBy(value="lock")
    private ScheduledFuture<?> reconnectTask;
    @GuardedBy(value="lock")
    private boolean reconnectCanceled;
    @GuardedBy(value="lock")
    private final Collection<ConnectionClientTransport> transports = new ArrayList<ConnectionClientTransport>();
    private final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>(){

        @Override
        void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        @Override
        void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    @Nullable
    @GuardedBy(value="lock")
    private ConnectionClientTransport pendingTransport;
    @Nullable
    private volatile ManagedClientTransport activeTransport;
    @GuardedBy(value="lock")
    private ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
    @GuardedBy(value="lock")
    private Status shutdownReason;

    InternalSubchannel(EquivalentAddressGroup addressGroup, String authority, String userAgent, BackoffPolicy.Provider backoffPolicyProvider, ClientTransportFactory transportFactory, ScheduledExecutorService scheduledExecutor, Supplier<Stopwatch> stopwatchSupplier, ChannelExecutor channelExecutor, Callback callback, Channelz channelz, CallTracer callsTracer, @Nullable ChannelTracer channelTracer) {
        this.addressGroup = Preconditions.checkNotNull(addressGroup, "addressGroup");
        this.authority = authority;
        this.userAgent = userAgent;
        this.backoffPolicyProvider = backoffPolicyProvider;
        this.transportFactory = transportFactory;
        this.scheduledExecutor = scheduledExecutor;
        this.connectingTimer = stopwatchSupplier.get();
        this.channelExecutor = channelExecutor;
        this.callback = callback;
        this.channelz = channelz;
        this.callsTracer = callsTracer;
        this.channelTracer = channelTracer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    ClientTransport obtainActiveTransport() {
        ManagedClientTransport savedTransport = this.activeTransport;
        if (savedTransport != null) {
            return savedTransport;
        }
        try {
            Object object = this.lock;
            synchronized (object) {
                block10: {
                    savedTransport = this.activeTransport;
                    if (savedTransport == null) break block10;
                    ManagedClientTransport managedClientTransport = savedTransport;
                    return managedClientTransport;
                }
                if (this.state.getState() == ConnectivityState.IDLE) {
                    this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    this.startNewTransport();
                }
            }
        }
        finally {
            this.channelExecutor.drain();
        }
        return null;
    }

    @GuardedBy(value="lock")
    private void startNewTransport() {
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex == 0) {
            this.connectingTimer.reset().start();
        }
        List<SocketAddress> addrs = this.addressGroup.getAddresses();
        SocketAddress address = addrs.get(this.addressIndex);
        ProxyParameters proxy = null;
        if (address instanceof PairSocketAddress) {
            proxy = ((PairSocketAddress)address).getAttributes().get(ProxyDetector.PROXY_PARAMS_KEY);
            address = ((PairSocketAddress)address).getAddress();
        }
        CallTracingTransport transport = new CallTracingTransport(this.transportFactory.newClientTransport(address, this.authority, this.userAgent, proxy), this.callsTracer);
        this.channelz.addClientSocket(transport);
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "[{0}] Created {1} for {2}", new Object[]{this.logId, transport.getLogId(), address});
        }
        this.pendingTransport = transport;
        this.transports.add(transport);
        Runnable runnable = transport.start(new TransportListener(transport, address));
        if (runnable != null) {
            this.channelExecutor.executeLater(runnable);
        }
    }

    @GuardedBy(value="lock")
    private void scheduleBackoff(Status status) {
        this.gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long delayNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "[{0}] Scheduling backoff for {1} ns", new Object[]{this.logId, delayNanos});
        }
        Preconditions.checkState(this.reconnectTask == null, "previous reconnectTask is not done");
        this.reconnectCanceled = false;
        class EndOfCurrentBackoff
        implements Runnable {
            EndOfCurrentBackoff() {
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                try {
                    Object object = InternalSubchannel.this.lock;
                    synchronized (object) {
                        block10: {
                            InternalSubchannel.this.reconnectTask = null;
                            if (!InternalSubchannel.this.reconnectCanceled) break block10;
                            return;
                        }
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                        InternalSubchannel.this.startNewTransport();
                    }
                }
                catch (Throwable t) {
                    log.log(Level.WARNING, "Exception handling end of backoff", t);
                }
                finally {
                    InternalSubchannel.this.channelExecutor.drain();
                }
            }
        }
        this.reconnectTask = this.scheduledExecutor.schedule(new LogExceptionRunnable(new EndOfCurrentBackoff()), delayNanos, TimeUnit.NANOSECONDS);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void resetConnectBackoff() {
        try {
            Object object = this.lock;
            synchronized (object) {
                block8: {
                    if (this.state.getState() == ConnectivityState.TRANSIENT_FAILURE) break block8;
                    return;
                }
                this.cancelReconnectTask();
                this.gotoNonErrorState(ConnectivityState.CONNECTING);
                this.startNewTransport();
            }
        }
        finally {
            this.channelExecutor.drain();
        }
    }

    @GuardedBy(value="lock")
    private void gotoNonErrorState(ConnectivityState newState) {
        this.gotoState(ConnectivityStateInfo.forNonError(newState));
    }

    @GuardedBy(value="lock")
    private void gotoState(final ConnectivityStateInfo newState) {
        if (this.state.getState() != newState.getState()) {
            Preconditions.checkState(this.state.getState() != ConnectivityState.SHUTDOWN, "Cannot transition out of SHUTDOWN to " + newState);
            this.state = newState;
            this.channelExecutor.executeLater(new Runnable(){

                @Override
                public void run() {
                    InternalSubchannel.this.callback.onStateChange(InternalSubchannel.this, newState);
                }
            });
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateAddresses(EquivalentAddressGroup newAddressGroup) {
        ManagedClientTransport savedTransport = null;
        try {
            Object object = this.lock;
            synchronized (object) {
                EquivalentAddressGroup oldAddressGroup = this.addressGroup;
                this.addressGroup = newAddressGroup;
                if (this.state.getState() == ConnectivityState.READY || this.state.getState() == ConnectivityState.CONNECTING) {
                    SocketAddress address = oldAddressGroup.getAddresses().get(this.addressIndex);
                    int newIndex = newAddressGroup.getAddresses().indexOf(address);
                    if (newIndex != -1) {
                        this.addressIndex = newIndex;
                    } else if (this.state.getState() == ConnectivityState.READY) {
                        savedTransport = this.activeTransport;
                        this.activeTransport = null;
                        this.addressIndex = 0;
                        this.gotoNonErrorState(ConnectivityState.IDLE);
                    } else {
                        savedTransport = this.pendingTransport;
                        this.pendingTransport = null;
                        this.addressIndex = 0;
                        this.startNewTransport();
                    }
                }
            }
        }
        finally {
            this.channelExecutor.drain();
        }
        if (savedTransport != null) {
            savedTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown(Status reason) {
        ConnectionClientTransport savedPendingTransport;
        ManagedClientTransport savedActiveTransport;
        try {
            Object object = this.lock;
            synchronized (object) {
                block12: {
                    if (this.state.getState() != ConnectivityState.SHUTDOWN) break block12;
                    return;
                }
                this.shutdownReason = reason;
                this.gotoNonErrorState(ConnectivityState.SHUTDOWN);
                savedActiveTransport = this.activeTransport;
                savedPendingTransport = this.pendingTransport;
                this.activeTransport = null;
                this.pendingTransport = null;
                this.addressIndex = 0;
                if (this.transports.isEmpty()) {
                    this.handleTermination();
                    if (log.isLoggable(Level.FINE)) {
                        log.log(Level.FINE, "[{0}] Terminated in shutdown()", this.logId);
                    }
                }
                this.cancelReconnectTask();
            }
        }
        finally {
            this.channelExecutor.drain();
        }
        if (savedActiveTransport != null) {
            savedActiveTransport.shutdown(reason);
        }
        if (savedPendingTransport != null) {
            savedPendingTransport.shutdown(reason);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString() {
        EquivalentAddressGroup addressGroupCopy;
        Object object = this.lock;
        synchronized (object) {
            addressGroupCopy = this.addressGroup;
        }
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("addressGroup", addressGroupCopy).toString();
    }

    @GuardedBy(value="lock")
    private void handleTermination() {
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    private void handleTransportInUseState(final ConnectionClientTransport transport, final boolean inUse) {
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(transport, inUse);
            }
        }).drain();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void shutdownNow(Status reason) {
        ArrayList<ConnectionClientTransport> transportsCopy;
        this.shutdown(reason);
        try {
            Iterator iterator2 = this.lock;
            synchronized (iterator2) {
                transportsCopy = new ArrayList<ConnectionClientTransport>(this.transports);
            }
        }
        finally {
            this.channelExecutor.drain();
        }
        for (ManagedClientTransport managedClientTransport : transportsCopy) {
            managedClientTransport.shutdownNow(reason);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    EquivalentAddressGroup getAddressGroup() {
        try {
            Object object = this.lock;
            synchronized (object) {
                EquivalentAddressGroup equivalentAddressGroup = this.addressGroup;
                return equivalentAddressGroup;
            }
        }
        finally {
            this.channelExecutor.drain();
        }
    }

    @GuardedBy(value="lock")
    private void cancelReconnectTask() {
        if (this.reconnectTask != null) {
            this.reconnectTask.cancel(false);
            this.reconnectCanceled = true;
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ListenableFuture<Channelz.ChannelStats> getStats() {
        ArrayList<WithLogId> transportsSnapshot;
        EquivalentAddressGroup addressGroupSnapshot;
        SettableFuture<Channelz.ChannelStats> ret = SettableFuture.create();
        Channelz.ChannelStats.Builder builder = new Channelz.ChannelStats.Builder();
        Object object = this.lock;
        synchronized (object) {
            addressGroupSnapshot = this.addressGroup;
            transportsSnapshot = new ArrayList<WithLogId>(this.transports);
        }
        builder.setTarget(addressGroupSnapshot.toString()).setState(this.getState());
        builder.setSockets(transportsSnapshot);
        this.callsTracer.updateBuilder(builder);
        if (this.channelTracer != null) {
            this.channelTracer.updateBuilder(builder);
        }
        ret.set(builder.build());
        return ret;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @VisibleForTesting
    ConnectivityState getState() {
        try {
            Object object = this.lock;
            synchronized (object) {
                ConnectivityState connectivityState = this.state.getState();
                return connectivityState;
            }
        }
        finally {
            this.channelExecutor.drain();
        }
    }

    @VisibleForTesting
    static final class CallTracingTransport
    extends ForwardingConnectionClientTransport {
        private final ConnectionClientTransport delegate;
        private final CallTracer callTracer;

        private CallTracingTransport(ConnectionClientTransport delegate, CallTracer callTracer) {
            this.delegate = delegate;
            this.callTracer = callTracer;
        }

        @Override
        protected ConnectionClientTransport delegate() {
            return this.delegate;
        }

        @Override
        public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
            final ClientStream streamDelegate = super.newStream(method, headers, callOptions);
            return new ForwardingClientStream(){

                @Override
                protected ClientStream delegate() {
                    return streamDelegate;
                }

                @Override
                public void start(final ClientStreamListener listener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener(){

                        @Override
                        protected ClientStreamListener delegate() {
                            return listener;
                        }

                        @Override
                        public void closed(Status status, Metadata trailers) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, trailers);
                        }

                        @Override
                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, trailers);
                        }
                    });
                }
            };
        }
    }

    static abstract class Callback {
        Callback() {
        }

        @ForOverride
        void onTerminated(InternalSubchannel is) {
        }

        @ForOverride
        void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
        }

        @ForOverride
        void onInUse(InternalSubchannel is) {
        }

        @ForOverride
        void onNotInUse(InternalSubchannel is) {
        }
    }

    private class TransportListener
    implements ManagedClientTransport.Listener {
        final ConnectionClientTransport transport;
        final SocketAddress address;

        TransportListener(ConnectionClientTransport transport, SocketAddress address) {
            this.transport = transport;
            this.address = address;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void transportReady() {
            Status savedShutdownReason;
            if (log.isLoggable(Level.FINE)) {
                log.log(Level.FINE, "[{0}] {1} for {2} is ready", new Object[]{InternalSubchannel.this.logId, this.transport.getLogId(), this.address});
            }
            try {
                Object object = InternalSubchannel.this.lock;
                synchronized (object) {
                    savedShutdownReason = InternalSubchannel.this.shutdownReason;
                    InternalSubchannel.this.reconnectPolicy = null;
                    if (savedShutdownReason != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                    } else if (InternalSubchannel.this.pendingTransport == this.transport) {
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                        InternalSubchannel.this.activeTransport = this.transport;
                        InternalSubchannel.this.pendingTransport = null;
                    }
                }
            }
            finally {
                InternalSubchannel.this.channelExecutor.drain();
            }
            if (savedShutdownReason != null) {
                this.transport.shutdown(savedShutdownReason);
            }
        }

        @Override
        public void transportInUse(boolean inUse) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, inUse);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void transportShutdown(Status s2) {
            if (log.isLoggable(Level.FINE)) {
                log.log(Level.FINE, "[{0}] {1} for {2} is being shutdown with status {3}", new Object[]{InternalSubchannel.this.logId, this.transport.getLogId(), this.address, s2});
            }
            try {
                Object object = InternalSubchannel.this.lock;
                synchronized (object) {
                    block14: {
                        if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) break block14;
                        return;
                    }
                    if (InternalSubchannel.this.activeTransport == this.transport) {
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                        InternalSubchannel.this.activeTransport = null;
                        InternalSubchannel.this.addressIndex = 0;
                    } else if (InternalSubchannel.this.pendingTransport == this.transport) {
                        Preconditions.checkState(InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", (Object)InternalSubchannel.this.state.getState());
                        InternalSubchannel.this.addressIndex++;
                        if (InternalSubchannel.this.addressIndex >= InternalSubchannel.this.addressGroup.getAddresses().size()) {
                            InternalSubchannel.this.pendingTransport = null;
                            InternalSubchannel.this.addressIndex = 0;
                            InternalSubchannel.this.scheduleBackoff(s2);
                        } else {
                            InternalSubchannel.this.startNewTransport();
                        }
                    }
                }
            }
            finally {
                InternalSubchannel.this.channelExecutor.drain();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void transportTerminated() {
            if (log.isLoggable(Level.FINE)) {
                log.log(Level.FINE, "[{0}] {1} for {2} is terminated", new Object[]{InternalSubchannel.this.logId, this.transport.getLogId(), this.address});
            }
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            try {
                Object object = InternalSubchannel.this.lock;
                synchronized (object) {
                    InternalSubchannel.this.transports.remove(this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        if (log.isLoggable(Level.FINE)) {
                            log.log(Level.FINE, "[{0}] Terminated in transportTerminated()", InternalSubchannel.this.logId);
                        }
                        InternalSubchannel.this.handleTermination();
                    }
                }
            }
            finally {
                InternalSubchannel.this.channelExecutor.drain();
            }
            Preconditions.checkState(InternalSubchannel.this.activeTransport != this.transport, "activeTransport still points to this transport. Seems transportShutdown() was not called.");
        }
    }
}

