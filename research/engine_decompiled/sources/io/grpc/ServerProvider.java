/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Internal;
import io.grpc.ManagedChannelProvider;
import io.grpc.ServerBuilder;
import io.grpc.ServiceProviders;
import java.util.Collections;

@Internal
public abstract class ServerProvider {
    private static final ServerProvider provider = ServiceProviders.load(ServerProvider.class, Collections.<Class<?>>emptyList(), ServerProvider.class.getClassLoader(), new ServiceProviders.PriorityAccessor<ServerProvider>(){

        @Override
        public boolean isAvailable(ServerProvider provider) {
            return provider.isAvailable();
        }

        @Override
        public int getPriority(ServerProvider provider) {
            return provider.priority();
        }
    });

    public static ServerProvider provider() {
        if (provider == null) {
            throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty artifact");
        }
        return provider;
    }

    protected abstract boolean isAvailable();

    protected abstract int priority();

    protected abstract ServerBuilder<?> builderForPort(int var1);
}

