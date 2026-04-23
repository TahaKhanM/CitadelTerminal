/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.net.InetSocketAddress;

public class NewConnection {
    public final InetSocketAddress address;
    public final Object handshakeMessage;

    public NewConnection(InetSocketAddress address, Object handshakeMessage) {
        this.address = address;
        this.handshakeMessage = handshakeMessage;
    }
}

