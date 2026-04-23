/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListSinksResponseOrBuilder
extends MessageOrBuilder {
    public List<LogSink> getSinksList();

    public LogSink getSinks(int var1);

    public int getSinksCount();

    public List<? extends LogSinkOrBuilder> getSinksOrBuilderList();

    public LogSinkOrBuilder getSinksOrBuilder(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

