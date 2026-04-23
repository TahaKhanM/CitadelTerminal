/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.ForwardingConnectionClientTransport;
import io.grpc.internal.MetadataApplierImpl;
import io.grpc.internal.ProxyParameters;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

final class CallCredentialsApplyingTransportFactory
implements ClientTransportFactory {
    private final ClientTransportFactory delegate;
    private final Executor appExecutor;

    CallCredentialsApplyingTransportFactory(ClientTransportFactory delegate, Executor appExecutor) {
        this.delegate = Preconditions.checkNotNull(delegate, "delegate");
        this.appExecutor = Preconditions.checkNotNull(appExecutor, "appExecutor");
    }

    @Override
    public ConnectionClientTransport newClientTransport(SocketAddress serverAddress, String authority, @Nullable String userAgent, @Nullable ProxyParameters proxy) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(serverAddress, authority, userAgent, proxy), authority);
    }

    @Override
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    @Override
    public void close() {
        this.delegate.close();
    }

    private class CallCredentialsApplyingTransport
    extends ForwardingConnectionClientTransport {
        private final ConnectionClientTransport delegate;
        private final String authority;

        CallCredentialsApplyingTransport(ConnectionClientTransport delegate, String authority) {
            this.delegate = Preconditions.checkNotNull(delegate, "delegate");
            this.authority = Preconditions.checkNotNull(authority, "authority");
        }

        @Override
        protected ConnectionClientTransport delegate() {
            return this.delegate;
        }

        @Override
        public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
            CallCredentials creds = callOptions.getCredentials();
            if (creds != null) {
                MetadataApplierImpl applier = new MetadataApplierImpl(this.delegate, method, headers, callOptions);
                Attributes.Builder effectiveAttrsBuilder = Attributes.newBuilder().set(CallCredentials.ATTR_AUTHORITY, this.authority).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE).setAll(this.delegate.getAttributes());
                if (callOptions.getAuthority() != null) {
                    effectiveAttrsBuilder.set(CallCredentials.ATTR_AUTHORITY, callOptions.getAuthority());
                }
                try {
                    creds.applyRequestMetadata(method, effectiveAttrsBuilder.build(), MoreObjects.firstNonNull(callOptions.getExecutor(), CallCredentialsApplyingTransportFactory.this.appExecutor), applier);
                }
                catch (Throwable t) {
                    applier.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(t));
                }
                return applier.returnStream();
            }
            return this.delegate.newStream(method, headers, callOptions);
        }
    }
}

