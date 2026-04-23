/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

@InternalExtensionOnly
public class FixedTransportChannelProvider
implements TransportChannelProvider {
    private final TransportChannel transportChannel;

    private FixedTransportChannelProvider(TransportChannel transportChannel) {
        this.transportChannel = Preconditions.checkNotNull(transportChannel);
    }

    @Override
    public boolean shouldAutoClose() {
        return false;
    }

    @Override
    public boolean needsExecutor() {
        return false;
    }

    @Override
    public FixedTransportChannelProvider withExecutor(ScheduledExecutorService executor) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need an executor");
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public boolean needsHeaders() {
        return false;
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public FixedTransportChannelProvider withHeaders(Map<String, String> headers) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need headers");
    }

    @Override
    public boolean needsEndpoint() {
        return false;
    }

    @Override
    public TransportChannelProvider withEndpoint(String endpoint) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need an endpoint");
    }

    @Override
    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public boolean acceptsPoolSize() {
        return false;
    }

    @Override
    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public TransportChannelProvider withPoolSize(int size2) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't allow pool size customization");
    }

    @Override
    public TransportChannel getTransportChannel() throws IOException {
        return this.transportChannel;
    }

    @Override
    public String getTransportName() {
        return this.transportChannel.getTransportName();
    }

    public static FixedTransportChannelProvider create(TransportChannel transportChannel) {
        return new FixedTransportChannelProvider(transportChannel);
    }
}

