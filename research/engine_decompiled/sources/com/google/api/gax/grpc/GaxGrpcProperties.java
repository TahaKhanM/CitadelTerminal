/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.core.GaxProperties;
import io.grpc.Channel;
import java.util.regex.Pattern;

@InternalApi
public class GaxGrpcProperties {
    private static final String GAX_GRPC_VERSION = GaxProperties.getLibraryVersion(GaxGrpcProperties.class);
    private static final String GRPC_VERSION = GaxProperties.getLibraryVersion(Channel.class);
    private static final Pattern DEFAULT_API_CLIENT_HEADER_PATTERN = Pattern.compile("gl-java/.+ gapic/.* gax/.+ grpc/.+");

    private GaxGrpcProperties() {
    }

    public static String getGrpcVersion() {
        return GRPC_VERSION;
    }

    public static String getGrpcTokenName() {
        return "grpc";
    }

    public static String getGaxGrpcVersion() {
        return GAX_GRPC_VERSION;
    }

    public static Pattern getDefaultApiClientHeaderPattern() {
        return DEFAULT_API_CLIENT_HEADER_PATTERN;
    }
}

