/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractSubchannel;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ChannelExecutor;
import io.grpc.internal.ChannelTracer;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.DelayedClientTransport;
import io.grpc.internal.Instrumented;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.RetriableStream;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class OobChannel
extends ManagedChannel
implements Instrumented<Channelz.ChannelStats> {
    private static final Logger log = Logger.getLogger(OobChannel.class.getName());
    private InternalSubchannel subchannel;
    private AbstractSubchannel subchannelImpl;
    private LoadBalancer.SubchannelPicker subchannelPicker;
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final String authority;
    private final DelayedClientTransport delayedTransport;
    private final Channelz channelz;
    private final ObjectPool<? extends Executor> executorPool;
    private final Executor executor;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    private volatile boolean shutdown;
    private final CallTracer channelCallsTracer;
    @CheckForNull
    private final ChannelTracer channelTracer;
    private final ClientCallImpl.ClientTransportProvider transportProvider = new ClientCallImpl.ClientTransportProvider(){

        @Override
        public ClientTransport get(LoadBalancer.PickSubchannelArgs args) {
            return OobChannel.this.delayedTransport;
        }

        @Override
        public <ReqT> RetriableStream<ReqT> newRetriableStream(MethodDescriptor<ReqT, ?> method, CallOptions callOptions, Metadata headers, Context context) {
            throw new UnsupportedOperationException("OobChannel should not create retriable streams");
        }
    };

    OobChannel(String authority, ObjectPool<? extends Executor> executorPool, ScheduledExecutorService deadlineCancellationExecutor, ChannelExecutor channelExecutor, CallTracer callsTracer, @Nullable ChannelTracer channelTracer, Channelz channelz) {
        this.authority = Preconditions.checkNotNull(authority, "authority");
        this.executorPool = Preconditions.checkNotNull(executorPool, "executorPool");
        this.executor = Preconditions.checkNotNull(executorPool.getObject(), "executor");
        this.deadlineCancellationExecutor = Preconditions.checkNotNull(deadlineCancellationExecutor, "deadlineCancellationExecutor");
        this.delayedTransport = new DelayedClientTransport(this.executor, channelExecutor);
        this.channelz = Preconditions.checkNotNull(channelz);
        this.delayedTransport.start(new ManagedClientTransport.Listener(){

            @Override
            public void transportShutdown(Status s2) {
            }

            @Override
            public void transportTerminated() {
                OobChannel.this.subchannelImpl.shutdown();
            }

            @Override
            public void transportReady() {
            }

            @Override
            public void transportInUse(boolean inUse) {
            }
        });
        this.channelCallsTracer = callsTracer;
        this.channelTracer = channelTracer;
    }

    void setSubchannel(final InternalSubchannel subchannel) {
        log.log(Level.FINE, "[{0}] Created with [{1}]", new Object[]{this, subchannel});
        this.subchannel = subchannel;
        this.subchannelImpl = new AbstractSubchannel(){

            @Override
            public void shutdown() {
                subchannel.shutdown(Status.UNAVAILABLE.withDescription("OobChannel is shutdown"));
            }

            @Override
            ClientTransport obtainActiveTransport() {
                return subchannel.obtainActiveTransport();
            }

            @Override
            Instrumented<Channelz.ChannelStats> getInternalSubchannel() {
                return subchannel;
            }

            @Override
            public void requestConnection() {
                subchannel.obtainActiveTransport();
            }

            @Override
            public EquivalentAddressGroup getAddresses() {
                return subchannel.getAddressGroup();
            }

            @Override
            public Attributes getAttributes() {
                return Attributes.EMPTY;
            }
        };
        this.subchannelPicker = new LoadBalancer.SubchannelPicker(){
            final LoadBalancer.PickResult result;
            {
                this.result = LoadBalancer.PickResult.withSubchannel(OobChannel.this.subchannelImpl);
            }

            @Override
            public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
                return this.result;
            }
        };
        this.delayedTransport.reprocess(this.subchannelPicker);
    }

    void updateAddresses(EquivalentAddressGroup eag) {
        this.subchannel.updateAddresses(eag);
    }

    @Override
    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        return new ClientCallImpl<RequestT, ResponseT>(methodDescriptor, callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor(), callOptions, this.transportProvider, this.deadlineCancellationExecutor, this.channelCallsTracer, false);
    }

    @Override
    public String authority() {
        return this.authority;
    }

    @Override
    public boolean isTerminated() {
        return this.terminatedLatch.getCount() == 0L;
    }

    @Override
    public boolean awaitTermination(long time, TimeUnit unit) throws InterruptedException {
        return this.terminatedLatch.await(time, unit);
    }

    @Override
    public ManagedChannel shutdown() {
        this.shutdown = true;
        this.delayedTransport.shutdown(Status.UNAVAILABLE.withDescription("OobChannel.shutdown() called"));
        return this;
    }

    @Override
    public boolean isShutdown() {
        return this.shutdown;
    }

    @Override
    public ManagedChannel shutdownNow() {
        this.shutdown = true;
        this.delayedTransport.shutdownNow(Status.UNAVAILABLE.withDescription("OobChannel.shutdownNow() called"));
        return this;
    }

    void handleSubchannelStateChange(final ConnectivityStateInfo newState) {
        switch (newState.getState()) {
            case READY: 
            case IDLE: {
                this.delayedTransport.reprocess(this.subchannelPicker);
                break;
            }
            case TRANSIENT_FAILURE: {
                this.delayedTransport.reprocess(new LoadBalancer.SubchannelPicker(){
                    final LoadBalancer.PickResult errorResult;
                    {
                        this.errorResult = LoadBalancer.PickResult.withError(newState.getStatus());
                    }

                    @Override
                    public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
                        return this.errorResult;
                    }
                });
                break;
            }
        }
    }

    void handleSubchannelTerminated() {
        this.channelz.removeSubchannel(this);
        this.executorPool.returnObject(this.executor);
        this.terminatedLatch.countDown();
    }

    @VisibleForTesting
    LoadBalancer.Subchannel getSubchannel() {
        return this.subchannelImpl;
    }

    InternalSubchannel getInternalSubchannel() {
        return this.subchannel;
    }

    @Override
    public ListenableFuture<Channelz.ChannelStats> getStats() {
        SettableFuture<Channelz.ChannelStats> ret = SettableFuture.create();
        Channelz.ChannelStats.Builder builder = new Channelz.ChannelStats.Builder();
        this.channelCallsTracer.updateBuilder(builder);
        if (this.channelTracer != null) {
            this.channelTracer.updateBuilder(builder);
        }
        builder.setTarget(this.authority).setState(this.subchannel.getState()).setSubchannels(Collections.singletonList(this.subchannel));
        ret.set(builder.build());
        return ret;
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("authority", this.authority).toString();
    }

    @Override
    public void resetConnectBackoff() {
        this.subchannel.resetConnectBackoff();
    }
}

