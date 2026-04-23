/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcTransportChannel;
import io.grpc.ManagedChannel;

final class AutoValue_GrpcTransportChannel
extends GrpcTransportChannel {
    private final ManagedChannel managedChannel;

    private AutoValue_GrpcTransportChannel(ManagedChannel managedChannel) {
        if (managedChannel == null) {
            throw new NullPointerException("Null managedChannel");
        }
        this.managedChannel = managedChannel;
    }

    @Override
    ManagedChannel getManagedChannel() {
        return this.managedChannel;
    }

    public String toString() {
        return "GrpcTransportChannel{managedChannel=" + this.managedChannel + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof GrpcTransportChannel) {
            GrpcTransportChannel that = (GrpcTransportChannel)o;
            return this.managedChannel.equals(that.getManagedChannel());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.managedChannel.hashCode();
    }

    static final class Builder
    extends GrpcTransportChannel.Builder {
        private ManagedChannel managedChannel;

        Builder() {
        }

        Builder(GrpcTransportChannel source) {
            this.managedChannel = source.getManagedChannel();
        }

        @Override
        public GrpcTransportChannel.Builder setManagedChannel(ManagedChannel managedChannel) {
            this.managedChannel = managedChannel;
            return this;
        }

        @Override
        public GrpcTransportChannel build() {
            String missing = "";
            if (this.managedChannel == null) {
                missing = missing + " managedChannel";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_GrpcTransportChannel(this.managedChannel);
        }
    }
}

