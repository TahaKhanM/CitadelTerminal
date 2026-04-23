/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import javax.annotation.Nullable;

public final class ProxyParameters {
    public final InetSocketAddress proxyAddress;
    @Nullable
    public final String username;
    @Nullable
    public final String password;

    public ProxyParameters(InetSocketAddress proxyAddress, @Nullable String username, @Nullable String password) {
        Preconditions.checkNotNull(proxyAddress);
        Preconditions.checkState(!proxyAddress.isUnresolved());
        this.proxyAddress = proxyAddress;
        this.username = username;
        this.password = password;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ProxyParameters)) {
            return false;
        }
        ProxyParameters that = (ProxyParameters)o;
        return Objects.equal(this.proxyAddress, that.proxyAddress) && Objects.equal(this.username, that.username) && Objects.equal(this.password, that.password);
    }

    public int hashCode() {
        return Objects.hashCode(this.proxyAddress, this.username, this.password);
    }
}

