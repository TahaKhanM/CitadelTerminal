/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CreateSinkRequestOrBuilder
extends MessageOrBuilder {
    public String getParent();

    public ByteString getParentBytes();

    public boolean hasSink();

    public LogSink getSink();

    public LogSinkOrBuilder getSinkOrBuilder();

    public boolean getUniqueWriterIdentity();
}

