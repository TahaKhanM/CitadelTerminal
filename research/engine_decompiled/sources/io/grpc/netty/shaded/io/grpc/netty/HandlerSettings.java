/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Internal;
import io.grpc.netty.shaded.io.grpc.netty.NettyHandlerSettings;

@Deprecated
@VisibleForTesting
@Internal
public final class HandlerSettings {
    public static void enable(boolean enable) {
        NettyHandlerSettings.enable(enable);
    }

    public static synchronized void autoWindowOn(boolean autoFlowControl) {
        NettyHandlerSettings.autoWindowOn(autoFlowControl);
    }

    public static synchronized int getLatestClientWindow() {
        return NettyHandlerSettings.getLatestServerWindow();
    }

    public static synchronized int getLatestServerWindow() {
        return NettyHandlerSettings.getLatestServerWindow();
    }
}

