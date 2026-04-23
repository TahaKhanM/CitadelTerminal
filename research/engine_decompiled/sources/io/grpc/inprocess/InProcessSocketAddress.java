/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.inprocess;

import java.net.SocketAddress;

public final class InProcessSocketAddress
extends SocketAddress {
    private static final long serialVersionUID = -2803441206326023474L;
    private final String name;

    public InProcessSocketAddress(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

