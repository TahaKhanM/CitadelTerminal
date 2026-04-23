/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.rpc.TransportChannel;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

@InternalExtensionOnly
public interface TransportChannelProvider {
    public boolean shouldAutoClose();

    public boolean needsExecutor();

    public TransportChannelProvider withExecutor(ScheduledExecutorService var1);

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public boolean needsHeaders();

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public TransportChannelProvider withHeaders(Map<String, String> var1);

    public boolean needsEndpoint();

    public TransportChannelProvider withEndpoint(String var1);

    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public boolean acceptsPoolSize();

    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public TransportChannelProvider withPoolSize(int var1);

    public TransportChannel getTransportChannel() throws IOException;

    public String getTransportName();
}

