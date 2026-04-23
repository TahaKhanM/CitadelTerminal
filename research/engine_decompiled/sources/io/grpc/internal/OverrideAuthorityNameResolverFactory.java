/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.internal.ForwardingNameResolver;
import java.net.URI;
import javax.annotation.Nullable;

final class OverrideAuthorityNameResolverFactory
extends NameResolver.Factory {
    private final NameResolver.Factory delegate;
    private final String authorityOverride;

    OverrideAuthorityNameResolverFactory(NameResolver.Factory delegate, String authorityOverride) {
        this.delegate = delegate;
        this.authorityOverride = authorityOverride;
    }

    @Override
    @Nullable
    public NameResolver newNameResolver(URI targetUri, Attributes params2) {
        NameResolver resolver = this.delegate.newNameResolver(targetUri, params2);
        if (resolver == null) {
            return null;
        }
        return new ForwardingNameResolver(resolver){

            @Override
            public String getServiceAuthority() {
                return OverrideAuthorityNameResolverFactory.this.authorityOverride;
            }
        };
    }

    @Override
    public String getDefaultScheme() {
        return this.delegate.getDefaultScheme();
    }
}

