/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import java.net.SocketAddress;

final class PairSocketAddress
extends SocketAddress {
    private static final long serialVersionUID = -6854992294603212793L;
    private final SocketAddress address;
    private final Attributes attributes;

    @VisibleForTesting
    PairSocketAddress(SocketAddress address, Attributes attributes) {
        this.address = Preconditions.checkNotNull(address);
        this.attributes = Preconditions.checkNotNull(attributes);
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    public SocketAddress getAddress() {
        return this.address;
    }
}

