/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface UpdateSinkRequestOrBuilder
extends MessageOrBuilder {
    public String getSinkName();

    public ByteString getSinkNameBytes();

    public boolean hasSink();

    public LogSink getSink();

    public LogSinkOrBuilder getSinkOrBuilder();

    public boolean getUniqueWriterIdentity();

    public boolean hasUpdateMask();

    public FieldMask getUpdateMask();

    public FieldMaskOrBuilder getUpdateMaskOrBuilder();
}

