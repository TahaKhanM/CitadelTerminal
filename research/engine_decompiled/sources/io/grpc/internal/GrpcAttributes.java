/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.Attributes;
import java.util.Map;

public final class GrpcAttributes {
    public static final Attributes.Key<Map<String, Object>> NAME_RESOLVER_SERVICE_CONFIG = Attributes.Key.create("service-config");
    public static final Attributes.Key<String> ATTR_LB_ADDR_AUTHORITY = Attributes.Key.create("io.grpc.grpclb.lbAddrAuthority");

    private GrpcAttributes() {
    }
}

