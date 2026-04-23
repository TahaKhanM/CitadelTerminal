/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ListLogsRequestOrBuilder
extends MessageOrBuilder {
    public String getParent();

    public ByteString getParentBytes();

    public int getPageSize();

    public String getPageToken();

    public ByteString getPageTokenBytes();
}

