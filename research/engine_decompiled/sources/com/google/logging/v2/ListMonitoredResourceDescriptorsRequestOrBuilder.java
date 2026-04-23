/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ListMonitoredResourceDescriptorsRequestOrBuilder
extends MessageOrBuilder {
    public int getPageSize();

    public String getPageToken();

    public ByteString getPageTokenBytes();
}

