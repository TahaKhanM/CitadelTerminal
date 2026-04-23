/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.resolver.AddressResolver;
import io.grpc.netty.shaded.io.netty.resolver.InetSocketAddressResolver;
import io.grpc.netty.shaded.io.netty.resolver.SimpleNameResolver;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public abstract class InetNameResolver
extends SimpleNameResolver<InetAddress> {
    private volatile AddressResolver<InetSocketAddress> addressResolver;

    protected InetNameResolver(EventExecutor executor) {
        super(executor);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public AddressResolver<InetSocketAddress> asAddressResolver() {
        InetSocketAddressResolver result2 = this.addressResolver;
        if (result2 == null) {
            InetNameResolver inetNameResolver = this;
            synchronized (inetNameResolver) {
                result2 = this.addressResolver;
                if (result2 == null) {
                    this.addressResolver = result2 = new InetSocketAddressResolver(this.executor(), this);
                }
            }
        }
        return result2;
    }
}

