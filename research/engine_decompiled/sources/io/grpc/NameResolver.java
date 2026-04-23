/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ExperimentalApi;
import io.grpc.Status;
import java.net.URI;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1770")
@ThreadSafe
public abstract class NameResolver {
    public abstract String getServiceAuthority();

    public abstract void start(Listener var1);

    public abstract void shutdown();

    public void refresh() {
    }

    @ThreadSafe
    public static interface Listener {
        public void onAddresses(List<EquivalentAddressGroup> var1, Attributes var2);

        public void onError(Status var1);
    }

    public static abstract class Factory {
        public static final Attributes.Key<Integer> PARAMS_DEFAULT_PORT = Attributes.Key.create("params-default-port");

        @Nullable
        public abstract NameResolver newNameResolver(URI var1, Attributes var2);

        public abstract String getDefaultScheme();
    }
}

