/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1770")
public final class EquivalentAddressGroup {
    private final List<SocketAddress> addrs;
    private final Attributes attrs;
    private final int hashCode;

    public EquivalentAddressGroup(List<SocketAddress> addrs) {
        this(addrs, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(List<SocketAddress> addrs, Attributes attrs) {
        Preconditions.checkArgument(!addrs.isEmpty(), "addrs is empty");
        this.addrs = Collections.unmodifiableList(new ArrayList<SocketAddress>(addrs));
        this.attrs = Preconditions.checkNotNull(attrs, "attrs");
        this.hashCode = this.addrs.hashCode();
    }

    public EquivalentAddressGroup(SocketAddress addr) {
        this(addr, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(SocketAddress addr, Attributes attrs) {
        this(Collections.singletonList(addr), attrs);
    }

    public List<SocketAddress> getAddresses() {
        return this.addrs;
    }

    public Attributes getAttributes() {
        return this.attrs;
    }

    public String toString() {
        return "[addrs=" + this.addrs + ", attrs=" + this.attrs + "]";
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object other) {
        if (!(other instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup that = (EquivalentAddressGroup)other;
        if (this.addrs.size() != that.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); ++i) {
            if (this.addrs.get(i).equals(that.addrs.get(i))) continue;
            return false;
        }
        return this.attrs.equals(that.attrs);
    }
}

