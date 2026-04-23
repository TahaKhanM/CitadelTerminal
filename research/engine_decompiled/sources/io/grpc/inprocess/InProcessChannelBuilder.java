/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import io.grpc.ExperimentalApi;
import io.grpc.Internal;
import io.grpc.inprocess.InProcessSocketAddress;
import io.grpc.inprocess.InProcessTransport;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.SharedResourceHolder;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1783")
public final class InProcessChannelBuilder
extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private final String name;
    private ScheduledExecutorService scheduledExecutorService;

    public static InProcessChannelBuilder forName(String name) {
        return new InProcessChannelBuilder(name);
    }

    public static InProcessChannelBuilder forTarget(String target) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static InProcessChannelBuilder forAddress(String name, int port) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    private InProcessChannelBuilder(String name) {
        super(new InProcessSocketAddress(name), "localhost");
        this.name = Preconditions.checkNotNull(name, "name");
        this.setStatsRecordStartedRpcs(false);
        this.setStatsRecordFinishedRpcs(false);
    }

    @Override
    public final InProcessChannelBuilder maxInboundMessageSize(int max2) {
        return (InProcessChannelBuilder)super.maxInboundMessageSize(max2);
    }

    @Override
    public InProcessChannelBuilder useTransportSecurity() {
        return this;
    }

    @Override
    @Deprecated
    public InProcessChannelBuilder usePlaintext(boolean skipNegotiation) {
        return this;
    }

    @Override
    public InProcessChannelBuilder usePlaintext() {
        return this;
    }

    @Override
    public InProcessChannelBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public InProcessChannelBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public InProcessChannelBuilder keepAliveWithoutCalls(boolean enable) {
        return this;
    }

    public InProcessChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        return this;
    }

    @Override
    @Internal
    protected ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.name, this.scheduledExecutorService);
    }

    static final class InProcessClientTransportFactory
    implements ClientTransportFactory {
        private final String name;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;
        private boolean closed;

        private InProcessClientTransportFactory(String name, @Nullable ScheduledExecutorService scheduledExecutorService) {
            this.name = name;
            this.useSharedTimer = scheduledExecutorService == null;
            this.timerService = this.useSharedTimer ? SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
        }

        @Override
        public ConnectionClientTransport newClientTransport(SocketAddress addr, String authority, String userAgent, ProxyParameters proxy) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            return new InProcessTransport(this.name, authority, userAgent);
        }

        @Override
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.useSharedTimer) {
                SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
            }
        }
    }
}

