/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import io.grpc.ClientInterceptor;
import java.util.List;

@BetaApi(value="The surface for adding custom interceptors is not stable yet and may change in the future.")
public interface GrpcInterceptorProvider {
    public List<ClientInterceptor> getInterceptors();
}

