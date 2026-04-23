/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler;

final class NettyHandlerSettings {
    private static volatile boolean enabled;
    private static boolean autoFlowControlOn;
    private static AbstractNettyHandler clientHandler;
    private static AbstractNettyHandler serverHandler;

    NettyHandlerSettings() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void setAutoWindow(AbstractNettyHandler handler) {
        if (!enabled) {
            return;
        }
        Class<NettyHandlerSettings> clazz = NettyHandlerSettings.class;
        synchronized (NettyHandlerSettings.class) {
            handler.setAutoTuneFlowControl(autoFlowControlOn);
            if (handler instanceof NettyClientHandler) {
                clientHandler = handler;
            } else if (handler instanceof NettyServerHandler) {
                serverHandler = handler;
            } else {
                throw new RuntimeException("Expecting NettyClientHandler or NettyServerHandler");
            }
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }

    public static void enable(boolean enable) {
        enabled = enable;
    }

    public static synchronized void autoWindowOn(boolean autoFlowControl) {
        autoFlowControlOn = autoFlowControl;
    }

    public static synchronized int getLatestClientWindow() {
        return NettyHandlerSettings.getLatestWindow(clientHandler);
    }

    public static synchronized int getLatestServerWindow() {
        return NettyHandlerSettings.getLatestWindow(serverHandler);
    }

    private static synchronized int getLatestWindow(AbstractNettyHandler handler) {
        Preconditions.checkNotNull(handler);
        return handler.decoder().flowController().initialWindowSize(handler.connection().connectionStream());
    }
}

