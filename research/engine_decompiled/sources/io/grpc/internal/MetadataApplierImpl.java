/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.FailingClientStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class MetadataApplierImpl
implements CallCredentials.MetadataApplier {
    private final ClientTransport transport;
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;
    private final CallOptions callOptions;
    private final Context ctx;
    private final Object lock = new Object();
    @Nullable
    @GuardedBy(value="lock")
    private ClientStream returnedStream;
    boolean finalized;
    DelayedStream delayedStream;

    MetadataApplierImpl(ClientTransport transport, MethodDescriptor<?, ?> method, Metadata origHeaders, CallOptions callOptions) {
        this.transport = transport;
        this.method = method;
        this.origHeaders = origHeaders;
        this.callOptions = callOptions;
        this.ctx = Context.current();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void apply(Metadata headers) {
        ClientStream realStream;
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(headers, "headers");
        this.origHeaders.merge(headers);
        Context origCtx = this.ctx.attach();
        try {
            realStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions);
        }
        finally {
            this.ctx.detach(origCtx);
        }
        this.finalizeWith(realStream);
    }

    @Override
    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        this.finalizeWith(new FailingClientStream(status));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void finalizeWith(ClientStream stream) {
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        Object object = this.lock;
        synchronized (object) {
            if (this.returnedStream == null) {
                this.returnedStream = stream;
                return;
            }
        }
        Preconditions.checkState(this.delayedStream != null, "delayedStream is null");
        this.delayedStream.setStream(stream);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    ClientStream returnStream() {
        Object object = this.lock;
        synchronized (object) {
            if (this.returnedStream == null) {
                this.delayedStream = new DelayedStream();
                this.returnedStream = this.delayedStream;
                return this.returnedStream;
            }
            return this.returnedStream;
        }
    }
}

