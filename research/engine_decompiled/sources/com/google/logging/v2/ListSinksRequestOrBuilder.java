/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ListSinksRequestOrBuilder
extends MessageOrBuilder {
    public String getParent();

    public ByteString getParentBytes();

    public String getPageToken();

    public ByteString getPageTokenBytes();

    public int getPageSize();
}

