/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
@InternalExtensionOnly
public class StreamingCallSettings<RequestT, ResponseT> {
    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder();
    }

    protected StreamingCallSettings() {
    }

    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder(this);
    }

    public static class Builder<RequestT, ResponseT> {
        public Builder() {
        }

        public Builder(StreamingCallSettings<RequestT, ResponseT> settings) {
        }

        public StreamingCallSettings<RequestT, ResponseT> build() {
            return new StreamingCallSettings();
        }
    }
}

