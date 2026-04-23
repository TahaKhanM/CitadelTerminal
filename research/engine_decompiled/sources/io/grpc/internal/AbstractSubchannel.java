/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.LoadBalancer;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.Instrumented;
import javax.annotation.Nullable;

abstract class AbstractSubchannel
extends LoadBalancer.Subchannel {
    AbstractSubchannel() {
    }

    @Nullable
    abstract ClientTransport obtainActiveTransport();

    @VisibleForTesting
    abstract Instrumented<Channelz.ChannelStats> getInternalSubchannel();
}

