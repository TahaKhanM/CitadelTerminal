/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import java.net.SocketAddress;
import javax.net.ssl.SSLSession;

public final class Grpc {
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1710")
    public static final Attributes.Key<SocketAddress> TRANSPORT_ATTR_REMOTE_ADDR = Attributes.Key.create("remote-addr");
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1710")
    public static final Attributes.Key<SSLSession> TRANSPORT_ATTR_SSL_SESSION = Attributes.Key.create("ssl-session");

    private Grpc() {
    }
}

