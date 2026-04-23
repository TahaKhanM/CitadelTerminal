/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.Watchdog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import org.threeten.bp.Duration;

class WatchdogServerStreamingCallable<RequestT, ResponseT>
extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> inner;
    private final Watchdog watchdog;

    WatchdogServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> inner2, Watchdog watchdog) {
        Preconditions.checkNotNull(inner2);
        Preconditions.checkNotNull(watchdog);
        this.inner = inner2;
        this.watchdog = watchdog;
    }

    @Override
    public void call(RequestT request, ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        Duration waitTimeout = MoreObjects.firstNonNull(context.getStreamWaitTimeout(), Duration.ZERO);
        Duration idleTimeout = MoreObjects.firstNonNull(context.getStreamIdleTimeout(), Duration.ZERO);
        responseObserver = this.watchdog.watch(responseObserver, waitTimeout, idleTimeout);
        this.inner.call(request, responseObserver, context);
    }
}

