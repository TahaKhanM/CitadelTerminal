/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.NameResolverProvider;
import io.grpc.internal.DnsNameResolver;
import io.grpc.internal.GrpcUtil;
import java.net.URI;

public final class DnsNameResolverProvider
extends NameResolverProvider {
    private static final String SCHEME = "dns";

    @Override
    public DnsNameResolver newNameResolver(URI targetUri, Attributes params2) {
        if (SCHEME.equals(targetUri.getScheme())) {
            String targetPath = Preconditions.checkNotNull(targetUri.getPath(), "targetPath");
            Preconditions.checkArgument(targetPath.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", (Object)targetPath, (Object)targetUri);
            String name = targetPath.substring(1);
            return new DnsNameResolver(targetUri.getAuthority(), name, params2, GrpcUtil.SHARED_CHANNEL_EXECUTOR, GrpcUtil.getDefaultProxyDetector());
        }
        return null;
    }

    @Override
    public String getDefaultScheme() {
        return SCHEME;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }
}

