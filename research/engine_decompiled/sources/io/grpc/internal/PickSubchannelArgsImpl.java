/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

final class PickSubchannelArgsImpl
extends LoadBalancer.PickSubchannelArgs {
    private final CallOptions callOptions;
    private final Metadata headers;
    private final MethodDescriptor<?, ?> method;

    PickSubchannelArgsImpl(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
        this.method = Preconditions.checkNotNull(method, "method");
        this.headers = Preconditions.checkNotNull(headers, "headers");
        this.callOptions = Preconditions.checkNotNull(callOptions, "callOptions");
    }

    @Override
    public Metadata getHeaders() {
        return this.headers;
    }

    @Override
    public CallOptions getCallOptions() {
        return this.callOptions;
    }

    @Override
    public MethodDescriptor<?, ?> getMethodDescriptor() {
        return this.method;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        PickSubchannelArgsImpl that = (PickSubchannelArgsImpl)o;
        return Objects.equal(this.callOptions, that.callOptions) && Objects.equal(this.headers, that.headers) && Objects.equal(this.method, that.method);
    }

    public int hashCode() {
        return Objects.hashCode(this.callOptions, this.headers, this.method);
    }

    public final String toString() {
        return "[method=" + this.method + " headers=" + this.headers + " callOptions=" + this.callOptions + "]";
    }
}

