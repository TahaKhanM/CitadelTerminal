/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSink;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

public interface LogSinkOrBuilder
extends MessageOrBuilder {
    public String getName();

    public ByteString getNameBytes();

    public String getDestination();

    public ByteString getDestinationBytes();

    public String getFilter();

    public ByteString getFilterBytes();

    @Deprecated
    public int getOutputVersionFormatValue();

    @Deprecated
    public LogSink.VersionFormat getOutputVersionFormat();

    public String getWriterIdentity();

    public ByteString getWriterIdentityBytes();

    public boolean getIncludeChildren();

    @Deprecated
    public boolean hasStartTime();

    @Deprecated
    public Timestamp getStartTime();

    @Deprecated
    public TimestampOrBuilder getStartTimeOrBuilder();

    @Deprecated
    public boolean hasEndTime();

    @Deprecated
    public Timestamp getEndTime();

    @Deprecated
    public TimestampOrBuilder getEndTimeOrBuilder();
}

