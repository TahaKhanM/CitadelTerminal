/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListLogsResponseOrBuilder
extends MessageOrBuilder {
    public List<String> getLogNamesList();

    public int getLogNamesCount();

    public String getLogNames(int var1);

    public ByteString getLogNamesBytes(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

