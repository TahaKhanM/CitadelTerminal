/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcStatusCode;
import io.grpc.Status;

final class AutoValue_GrpcStatusCode
extends GrpcStatusCode {
    private final Status.Code transportCode;

    AutoValue_GrpcStatusCode(Status.Code transportCode) {
        if (transportCode == null) {
            throw new NullPointerException("Null transportCode");
        }
        this.transportCode = transportCode;
    }

    @Override
    public Status.Code getTransportCode() {
        return this.transportCode;
    }

    public String toString() {
        return "GrpcStatusCode{transportCode=" + (Object)((Object)this.transportCode) + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof GrpcStatusCode) {
            GrpcStatusCode that = (GrpcStatusCode)o;
            return this.transportCode.equals((Object)that.getTransportCode());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.transportCode.hashCode();
    }
}

