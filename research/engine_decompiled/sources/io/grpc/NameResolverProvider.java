/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import io.grpc.NameResolver;
import io.grpc.ServiceProviders;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/4159")
public abstract class NameResolverProvider
extends NameResolver.Factory {
    public static final Attributes.Key<Integer> PARAMS_DEFAULT_PORT = NameResolver.Factory.PARAMS_DEFAULT_PORT;
    @VisibleForTesting
    static final Iterable<Class<?>> HARDCODED_CLASSES = new HardcodedClasses();
    private static final List<NameResolverProvider> providers = ServiceProviders.loadAll(NameResolverProvider.class, HARDCODED_CLASSES, NameResolverProvider.class.getClassLoader(), new ServiceProviders.PriorityAccessor<NameResolverProvider>(){

        @Override
        public boolean isAvailable(NameResolverProvider provider) {
            return provider.isAvailable();
        }

        @Override
        public int getPriority(NameResolverProvider provider) {
            return provider.priority();
        }
    });
    private static final NameResolver.Factory factory = new NameResolverFactory(providers);

    public static List<NameResolverProvider> providers() {
        return providers;
    }

    public static NameResolver.Factory asFactory() {
        return factory;
    }

    @VisibleForTesting
    static NameResolver.Factory asFactory(List<NameResolverProvider> providers) {
        return new NameResolverFactory(providers);
    }

    protected abstract boolean isAvailable();

    protected abstract int priority();

    @VisibleForTesting
    static final class HardcodedClasses
    implements Iterable<Class<?>> {
        HardcodedClasses() {
        }

        @Override
        public Iterator<Class<?>> iterator() {
            ArrayList list2 = new ArrayList();
            try {
                list2.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            return list2.iterator();
        }
    }

    private static class NameResolverFactory
    extends NameResolver.Factory {
        private final List<NameResolverProvider> providers;

        public NameResolverFactory(List<NameResolverProvider> providers) {
            this.providers = providers;
        }

        @Override
        public NameResolver newNameResolver(URI targetUri, Attributes params2) {
            this.checkForProviders();
            for (NameResolverProvider provider : this.providers) {
                NameResolver resolver = provider.newNameResolver(targetUri, params2);
                if (resolver == null) continue;
                return resolver;
            }
            return null;
        }

        @Override
        public String getDefaultScheme() {
            this.checkForProviders();
            return this.providers.get(0).getDefaultScheme();
        }

        private void checkForProviders() {
            Preconditions.checkState(!this.providers.isEmpty(), "No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
        }
    }
}

