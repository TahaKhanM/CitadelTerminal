/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.MonitoredResource;
import com.google.api.MonitoredResourceMetadata;
import com.google.api.MonitoredResourceMetadataOrBuilder;
import com.google.api.MonitoredResourceOrBuilder;
import com.google.logging.type.HttpRequest;
import com.google.logging.type.HttpRequestOrBuilder;
import com.google.logging.type.LogSeverity;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOperation;
import com.google.logging.v2.LogEntryOperationOrBuilder;
import com.google.logging.v2.LogEntrySourceLocation;
import com.google.logging.v2.LogEntrySourceLocationOrBuilder;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import java.util.Map;

public interface LogEntryOrBuilder
extends MessageOrBuilder {
    public String getLogName();

    public ByteString getLogNameBytes();

    public boolean hasResource();

    public MonitoredResource getResource();

    public MonitoredResourceOrBuilder getResourceOrBuilder();

    public boolean hasProtoPayload();

    public Any getProtoPayload();

    public AnyOrBuilder getProtoPayloadOrBuilder();

    public String getTextPayload();

    public ByteString getTextPayloadBytes();

    public boolean hasJsonPayload();

    public Struct getJsonPayload();

    public StructOrBuilder getJsonPayloadOrBuilder();

    public boolean hasTimestamp();

    public Timestamp getTimestamp();

    public TimestampOrBuilder getTimestampOrBuilder();

    public boolean hasReceiveTimestamp();

    public Timestamp getReceiveTimestamp();

    public TimestampOrBuilder getReceiveTimestampOrBuilder();

    public int getSeverityValue();

    public LogSeverity getSeverity();

    public String getInsertId();

    public ByteString getInsertIdBytes();

    public boolean hasHttpRequest();

    public HttpRequest getHttpRequest();

    public HttpRequestOrBuilder getHttpRequestOrBuilder();

    public int getLabelsCount();

    public boolean containsLabels(String var1);

    @Deprecated
    public Map<String, String> getLabels();

    public Map<String, String> getLabelsMap();

    public String getLabelsOrDefault(String var1, String var2);

    public String getLabelsOrThrow(String var1);

    public boolean hasMetadata();

    public MonitoredResourceMetadata getMetadata();

    public MonitoredResourceMetadataOrBuilder getMetadataOrBuilder();

    public boolean hasOperation();

    public LogEntryOperation getOperation();

    public LogEntryOperationOrBuilder getOperationOrBuilder();

    public String getTrace();

    public ByteString getTraceBytes();

    public String getSpanId();

    public ByteString getSpanIdBytes();

    public boolean hasSourceLocation();

    public LogEntrySourceLocation getSourceLocation();

    public LogEntrySourceLocationOrBuilder getSourceLocationOrBuilder();

    public LogEntry.PayloadCase getPayloadCase();
}

