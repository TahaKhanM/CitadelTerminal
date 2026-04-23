/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RequestMetadataOrBuilder
extends MessageOrBuilder {
    public String getCallerIp();

    public ByteString getCallerIpBytes();

    public String getCallerSuppliedUserAgent();

    public ByteString getCallerSuppliedUserAgentBytes();
}

