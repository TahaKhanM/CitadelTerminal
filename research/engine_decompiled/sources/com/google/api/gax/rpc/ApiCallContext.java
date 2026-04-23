/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.rpc.TransportChannel;
import com.google.auth.Credentials;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

@InternalExtensionOnly
public interface ApiCallContext {
    public ApiCallContext withCredentials(Credentials var1);

    public ApiCallContext withTransportChannel(TransportChannel var1);

    public ApiCallContext withTimeout(Duration var1);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public ApiCallContext withStreamWaitTimeout(@Nullable Duration var1);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Duration getStreamWaitTimeout();

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public ApiCallContext withStreamIdleTimeout(@Nullable Duration var1);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Duration getStreamIdleTimeout();

    public ApiCallContext nullToSelf(ApiCallContext var1);

    public ApiCallContext merge(ApiCallContext var1);

    @BetaApi(value="The surface for extra headers is not stable yet and may change in the future.")
    public ApiCallContext withExtraHeaders(Map<String, List<String>> var1);

    @BetaApi(value="The surface for extra headers is not stable yet and may change in the future.")
    public Map<String, List<String>> getExtraHeaders();
}

