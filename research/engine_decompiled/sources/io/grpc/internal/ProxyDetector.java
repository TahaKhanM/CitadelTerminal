/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.internal.ProxyParameters;
import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

public interface ProxyDetector {
    public static final Attributes.Key<ProxyParameters> PROXY_PARAMS_KEY = Attributes.Key.create("proxy-params-key");

    @Nullable
    public ProxyParameters proxyFor(SocketAddress var1) throws IOException;
}

