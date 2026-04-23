/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Internal;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Iterator;

@Internal
public abstract class ManagedChannelProvider {
    @VisibleForTesting
    static final Iterable<Class<?>> HARDCODED_CLASSES = new HardcodedClasses();
    private static final ManagedChannelProvider provider = ServiceProviders.load(ManagedChannelProvider.class, HARDCODED_CLASSES, ManagedChannelProvider.class.getClassLoader(), new ServiceProviders.PriorityAccessor<ManagedChannelProvider>(){

        @Override
        public boolean isAvailable(ManagedChannelProvider provider) {
            return provider.isAvailable();
        }

        @Override
        public int getPriority(ManagedChannelProvider provider) {
            return provider.priority();
        }
    });

    public static ManagedChannelProvider provider() {
        if (provider == null) {
            throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp or grpc-netty artifact");
        }
        return provider;
    }

    protected abstract boolean isAvailable();

    protected abstract int priority();

    protected abstract ManagedChannelBuilder<?> builderForAddress(String var1, int var2);

    protected abstract ManagedChannelBuilder<?> builderForTarget(String var1);

    private static final class HardcodedClasses
    implements Iterable<Class<?>> {
        private HardcodedClasses() {
        }

        @Override
        public Iterator<Class<?>> iterator() {
            ArrayList list2 = new ArrayList();
            try {
                list2.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            try {
                list2.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            return list2.iterator();
        }
    }

    public static final class ProviderNotFoundException
    extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ProviderNotFoundException(String msg) {
            super(msg);
        }
    }
}

