/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Internal;
import io.grpc.netty.shaded.io.grpc.netty.NettyHandlerSettings;

@VisibleForTesting
@Internal
public final class InternalHandlerSettings {
    public static void enable(boolean enable) {
        NettyHandlerSettings.enable(enable);
    }

    public static synchronized void autoWindowOn(boolean autoFlowControl) {
        NettyHandlerSettings.autoWindowOn(autoFlowControl);
    }

    public static synchronized int getLatestClientWindow() {
        return NettyHandlerSettings.getLatestClientWindow();
    }

    public static synchronized int getLatestServerWindow() {
        return NettyHandlerSettings.getLatestServerWindow();
    }
}

