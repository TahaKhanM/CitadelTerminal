/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListExclusionsResponseOrBuilder
extends MessageOrBuilder {
    public List<LogExclusion> getExclusionsList();

    public LogExclusion getExclusions(int var1);

    public int getExclusionsCount();

    public List<? extends LogExclusionOrBuilder> getExclusionsOrBuilderList();

    public LogExclusionOrBuilder getExclusionsOrBuilder(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

