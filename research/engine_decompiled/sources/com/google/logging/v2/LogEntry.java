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
import com.google.logging.v2.LogEntryOperation;
import com.google.logging.v2.LogEntryOperationOrBuilder;
import com.google.logging.v2.LogEntryOrBuilder;
import com.google.logging.v2.LogEntryProto;
import com.google.logging.v2.LogEntrySourceLocation;
import com.google.logging.v2.LogEntrySourceLocationOrBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class LogEntry
extends GeneratedMessageV3
implements LogEntryOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    private int payloadCase_ = 0;
    private Object payload_;
    public static final int LOG_NAME_FIELD_NUMBER = 12;
    private volatile Object logName_;
    public static final int RESOURCE_FIELD_NUMBER = 8;
    private MonitoredResource resource_;
    public static final int PROTO_PAYLOAD_FIELD_NUMBER = 2;
    public static final int TEXT_PAYLOAD_FIELD_NUMBER = 3;
    public static final int JSON_PAYLOAD_FIELD_NUMBER = 6;
    public static final int TIMESTAMP_FIELD_NUMBER = 9;
    private Timestamp timestamp_;
    public static final int RECEIVE_TIMESTAMP_FIELD_NUMBER = 24;
    private Timestamp receiveTimestamp_;
    public static final int SEVERITY_FIELD_NUMBER = 10;
    private int severity_;
    public static final int INSERT_ID_FIELD_NUMBER = 4;
    private volatile Object insertId_;
    public static final int HTTP_REQUEST_FIELD_NUMBER = 7;
    private HttpRequest httpRequest_;
    public static final int LABELS_FIELD_NUMBER = 11;
    private MapField<String, String> labels_;
    public static final int METADATA_FIELD_NUMBER = 25;
    private MonitoredResourceMetadata metadata_;
    public static final int OPERATION_FIELD_NUMBER = 15;
    private LogEntryOperation operation_;
    public static final int TRACE_FIELD_NUMBER = 22;
    private volatile Object trace_;
    public static final int SPAN_ID_FIELD_NUMBER = 27;
    private volatile Object spanId_;
    public static final int SOURCE_LOCATION_FIELD_NUMBER = 23;
    private LogEntrySourceLocation sourceLocation_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogEntry DEFAULT_INSTANCE = new LogEntry();
    private static final Parser<LogEntry> PARSER = new AbstractParser<LogEntry>(){

        @Override
        public LogEntry parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogEntry(input2, extensionRegistry);
        }
    };

    private LogEntry(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogEntry() {
        this.logName_ = "";
        this.severity_ = 0;
        this.insertId_ = "";
        this.trace_ = "";
        this.spanId_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogEntry(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block25: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block25;
                    }
                    case 18: {
                        Any.Builder subBuilder = null;
                        if (this.payloadCase_ == 2) {
                            subBuilder = ((Any)this.payload_).toBuilder();
                        }
                        this.payload_ = input2.readMessage(Any.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom((Any)this.payload_);
                            this.payload_ = subBuilder.buildPartial();
                        }
                        this.payloadCase_ = 2;
                        continue block25;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.payloadCase_ = 3;
                        this.payload_ = s2;
                        continue block25;
                    }
                    case 34: {
                        String s3 = input2.readStringRequireUtf8();
                        this.insertId_ = s3;
                        continue block25;
                    }
                    case 50: {
                        Struct.Builder subBuilder = null;
                        if (this.payloadCase_ == 6) {
                            subBuilder = ((Struct)this.payload_).toBuilder();
                        }
                        this.payload_ = input2.readMessage(Struct.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom((Struct)this.payload_);
                            this.payload_ = subBuilder.buildPartial();
                        }
                        this.payloadCase_ = 6;
                        continue block25;
                    }
                    case 58: {
                        HttpRequest.Builder subBuilder = null;
                        if (this.httpRequest_ != null) {
                            subBuilder = this.httpRequest_.toBuilder();
                        }
                        this.httpRequest_ = input2.readMessage(HttpRequest.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.httpRequest_);
                        this.httpRequest_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 66: {
                        MonitoredResource.Builder subBuilder = null;
                        if (this.resource_ != null) {
                            subBuilder = this.resource_.toBuilder();
                        }
                        this.resource_ = input2.readMessage(MonitoredResource.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.resource_);
                        this.resource_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 74: {
                        Timestamp.Builder subBuilder = null;
                        if (this.timestamp_ != null) {
                            subBuilder = this.timestamp_.toBuilder();
                        }
                        this.timestamp_ = input2.readMessage(Timestamp.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.timestamp_);
                        this.timestamp_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 80: {
                        int rawValue;
                        this.severity_ = rawValue = input2.readEnum();
                        continue block25;
                    }
                    case 90: {
                        if ((mutable_bitField0_ & 0x400) != 1024) {
                            this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= 0x400;
                        }
                        MapEntry<String, String> labels__ = input2.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.labels_.getMutableMap().put(labels__.getKey(), labels__.getValue());
                        continue block25;
                    }
                    case 98: {
                        String s4 = input2.readStringRequireUtf8();
                        this.logName_ = s4;
                        continue block25;
                    }
                    case 122: {
                        LogEntryOperation.Builder subBuilder = null;
                        if (this.operation_ != null) {
                            subBuilder = this.operation_.toBuilder();
                        }
                        this.operation_ = input2.readMessage(LogEntryOperation.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.operation_);
                        this.operation_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 178: {
                        String s5 = input2.readStringRequireUtf8();
                        this.trace_ = s5;
                        continue block25;
                    }
                    case 186: {
                        LogEntrySourceLocation.Builder subBuilder = null;
                        if (this.sourceLocation_ != null) {
                            subBuilder = this.sourceLocation_.toBuilder();
                        }
                        this.sourceLocation_ = input2.readMessage(LogEntrySourceLocation.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.sourceLocation_);
                        this.sourceLocation_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 194: {
                        Timestamp.Builder subBuilder = null;
                        if (this.receiveTimestamp_ != null) {
                            subBuilder = this.receiveTimestamp_.toBuilder();
                        }
                        this.receiveTimestamp_ = input2.readMessage(Timestamp.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.receiveTimestamp_);
                        this.receiveTimestamp_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 202: {
                        MonitoredResourceMetadata.Builder subBuilder = null;
                        if (this.metadata_ != null) {
                            subBuilder = this.metadata_.toBuilder();
                        }
                        this.metadata_ = input2.readMessage(MonitoredResourceMetadata.parser(), extensionRegistry);
                        if (subBuilder == null) continue block25;
                        subBuilder.mergeFrom(this.metadata_);
                        this.metadata_ = subBuilder.buildPartial();
                        continue block25;
                    }
                    case 218: {
                        String s6 = input2.readStringRequireUtf8();
                        this.spanId_ = s6;
                        continue block25;
                    }
                }
                if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue;
                done = true;
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LogEntryProto.internal_static_google_logging_v2_LogEntry_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 11: {
                return this.internalGetLabels();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LogEntryProto.internal_static_google_logging_v2_LogEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntry.class, Builder.class);
    }

    @Override
    public PayloadCase getPayloadCase() {
        return PayloadCase.forNumber(this.payloadCase_);
    }

    @Override
    public String getLogName() {
        Object ref = this.logName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.logName_ = s2;
        return s2;
    }

    @Override
    public ByteString getLogNameBytes() {
        Object ref = this.logName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.logName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasResource() {
        return this.resource_ != null;
    }

    @Override
    public MonitoredResource getResource() {
        return this.resource_ == null ? MonitoredResource.getDefaultInstance() : this.resource_;
    }

    @Override
    public MonitoredResourceOrBuilder getResourceOrBuilder() {
        return this.getResource();
    }

    @Override
    public boolean hasProtoPayload() {
        return this.payloadCase_ == 2;
    }

    @Override
    public Any getProtoPayload() {
        if (this.payloadCase_ == 2) {
            return (Any)this.payload_;
        }
        return Any.getDefaultInstance();
    }

    @Override
    public AnyOrBuilder getProtoPayloadOrBuilder() {
        if (this.payloadCase_ == 2) {
            return (Any)this.payload_;
        }
        return Any.getDefaultInstance();
    }

    @Override
    public String getTextPayload() {
        Object ref = "";
        if (this.payloadCase_ == 3) {
            ref = this.payload_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.payloadCase_ == 3) {
            this.payload_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getTextPayloadBytes() {
        Object ref = "";
        if (this.payloadCase_ == 3) {
            ref = this.payload_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.payloadCase_ == 3) {
                this.payload_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasJsonPayload() {
        return this.payloadCase_ == 6;
    }

    @Override
    public Struct getJsonPayload() {
        if (this.payloadCase_ == 6) {
            return (Struct)this.payload_;
        }
        return Struct.getDefaultInstance();
    }

    @Override
    public StructOrBuilder getJsonPayloadOrBuilder() {
        if (this.payloadCase_ == 6) {
            return (Struct)this.payload_;
        }
        return Struct.getDefaultInstance();
    }

    @Override
    public boolean hasTimestamp() {
        return this.timestamp_ != null;
    }

    @Override
    public Timestamp getTimestamp() {
        return this.timestamp_ == null ? Timestamp.getDefaultInstance() : this.timestamp_;
    }

    @Override
    public TimestampOrBuilder getTimestampOrBuilder() {
        return this.getTimestamp();
    }

    @Override
    public boolean hasReceiveTimestamp() {
        return this.receiveTimestamp_ != null;
    }

    @Override
    public Timestamp getReceiveTimestamp() {
        return this.receiveTimestamp_ == null ? Timestamp.getDefaultInstance() : this.receiveTimestamp_;
    }

    @Override
    public TimestampOrBuilder getReceiveTimestampOrBuilder() {
        return this.getReceiveTimestamp();
    }

    @Override
    public int getSeverityValue() {
        return this.severity_;
    }

    @Override
    public LogSeverity getSeverity() {
        LogSeverity result2 = LogSeverity.valueOf(this.severity_);
        return result2 == null ? LogSeverity.UNRECOGNIZED : result2;
    }

    @Override
    public String getInsertId() {
        Object ref = this.insertId_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.insertId_ = s2;
        return s2;
    }

    @Override
    public ByteString getInsertIdBytes() {
        Object ref = this.insertId_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.insertId_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasHttpRequest() {
        return this.httpRequest_ != null;
    }

    @Override
    public HttpRequest getHttpRequest() {
        return this.httpRequest_ == null ? HttpRequest.getDefaultInstance() : this.httpRequest_;
    }

    @Override
    public HttpRequestOrBuilder getHttpRequestOrBuilder() {
        return this.getHttpRequest();
    }

    private MapField<String, String> internalGetLabels() {
        if (this.labels_ == null) {
            return MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry);
        }
        return this.labels_;
    }

    @Override
    public int getLabelsCount() {
        return this.internalGetLabels().getMap().size();
    }

    @Override
    public boolean containsLabels(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return this.internalGetLabels().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<String, String> getLabels() {
        return this.getLabelsMap();
    }

    @Override
    public Map<String, String> getLabelsMap() {
        return this.internalGetLabels().getMap();
    }

    @Override
    public String getLabelsOrDefault(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabels().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public String getLabelsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabels().getMap();
        if (!map2.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map2.get(key);
    }

    @Override
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override
    public MonitoredResourceMetadata getMetadata() {
        return this.metadata_ == null ? MonitoredResourceMetadata.getDefaultInstance() : this.metadata_;
    }

    @Override
    public MonitoredResourceMetadataOrBuilder getMetadataOrBuilder() {
        return this.getMetadata();
    }

    @Override
    public boolean hasOperation() {
        return this.operation_ != null;
    }

    @Override
    public LogEntryOperation getOperation() {
        return this.operation_ == null ? LogEntryOperation.getDefaultInstance() : this.operation_;
    }

    @Override
    public LogEntryOperationOrBuilder getOperationOrBuilder() {
        return this.getOperation();
    }

    @Override
    public String getTrace() {
        Object ref = this.trace_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.trace_ = s2;
        return s2;
    }

    @Override
    public ByteString getTraceBytes() {
        Object ref = this.trace_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.trace_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getSpanId() {
        Object ref = this.spanId_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.spanId_ = s2;
        return s2;
    }

    @Override
    public ByteString getSpanIdBytes() {
        Object ref = this.spanId_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.spanId_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasSourceLocation() {
        return this.sourceLocation_ != null;
    }

    @Override
    public LogEntrySourceLocation getSourceLocation() {
        return this.sourceLocation_ == null ? LogEntrySourceLocation.getDefaultInstance() : this.sourceLocation_;
    }

    @Override
    public LogEntrySourceLocationOrBuilder getSourceLocationOrBuilder() {
        return this.getSourceLocation();
    }

    @Override
    public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.payloadCase_ == 2) {
            output.writeMessage(2, (Any)this.payload_);
        }
        if (this.payloadCase_ == 3) {
            GeneratedMessageV3.writeString(output, 3, this.payload_);
        }
        if (!this.getInsertIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.insertId_);
        }
        if (this.payloadCase_ == 6) {
            output.writeMessage(6, (Struct)this.payload_);
        }
        if (this.httpRequest_ != null) {
            output.writeMessage(7, this.getHttpRequest());
        }
        if (this.resource_ != null) {
            output.writeMessage(8, this.getResource());
        }
        if (this.timestamp_ != null) {
            output.writeMessage(9, this.getTimestamp());
        }
        if (this.severity_ != LogSeverity.DEFAULT.getNumber()) {
            output.writeEnum(10, this.severity_);
        }
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 11);
        if (!this.getLogNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 12, this.logName_);
        }
        if (this.operation_ != null) {
            output.writeMessage(15, this.getOperation());
        }
        if (!this.getTraceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 22, this.trace_);
        }
        if (this.sourceLocation_ != null) {
            output.writeMessage(23, this.getSourceLocation());
        }
        if (this.receiveTimestamp_ != null) {
            output.writeMessage(24, this.getReceiveTimestamp());
        }
        if (this.metadata_ != null) {
            output.writeMessage(25, this.getMetadata());
        }
        if (!this.getSpanIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 27, this.spanId_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (this.payloadCase_ == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, (Any)this.payload_);
        }
        if (this.payloadCase_ == 3) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.payload_);
        }
        if (!this.getInsertIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.insertId_);
        }
        if (this.payloadCase_ == 6) {
            size2 += CodedOutputStream.computeMessageSize(6, (Struct)this.payload_);
        }
        if (this.httpRequest_ != null) {
            size2 += CodedOutputStream.computeMessageSize(7, this.getHttpRequest());
        }
        if (this.resource_ != null) {
            size2 += CodedOutputStream.computeMessageSize(8, this.getResource());
        }
        if (this.timestamp_ != null) {
            size2 += CodedOutputStream.computeMessageSize(9, this.getTimestamp());
        }
        if (this.severity_ != LogSeverity.DEFAULT.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(10, this.severity_);
        }
        for (Map.Entry<String, String> entry : this.internalGetLabels().getMap().entrySet()) {
            Message labels__ = ((MapEntry.Builder)LabelsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(11, labels__);
        }
        if (!this.getLogNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(12, this.logName_);
        }
        if (this.operation_ != null) {
            size2 += CodedOutputStream.computeMessageSize(15, this.getOperation());
        }
        if (!this.getTraceBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(22, this.trace_);
        }
        if (this.sourceLocation_ != null) {
            size2 += CodedOutputStream.computeMessageSize(23, this.getSourceLocation());
        }
        if (this.receiveTimestamp_ != null) {
            size2 += CodedOutputStream.computeMessageSize(24, this.getReceiveTimestamp());
        }
        if (this.metadata_ != null) {
            size2 += CodedOutputStream.computeMessageSize(25, this.getMetadata());
        }
        if (!this.getSpanIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(27, this.spanId_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEntry)) {
            return super.equals(obj);
        }
        LogEntry other = (LogEntry)obj;
        boolean result2 = true;
        result2 = result2 && this.getLogName().equals(other.getLogName());
        boolean bl = result2 = result2 && this.hasResource() == other.hasResource();
        if (this.hasResource()) {
            result2 = result2 && this.getResource().equals(other.getResource());
        }
        boolean bl2 = result2 = result2 && this.hasTimestamp() == other.hasTimestamp();
        if (this.hasTimestamp()) {
            result2 = result2 && this.getTimestamp().equals(other.getTimestamp());
        }
        boolean bl3 = result2 = result2 && this.hasReceiveTimestamp() == other.hasReceiveTimestamp();
        if (this.hasReceiveTimestamp()) {
            result2 = result2 && this.getReceiveTimestamp().equals(other.getReceiveTimestamp());
        }
        result2 = result2 && this.severity_ == other.severity_;
        result2 = result2 && this.getInsertId().equals(other.getInsertId());
        boolean bl4 = result2 = result2 && this.hasHttpRequest() == other.hasHttpRequest();
        if (this.hasHttpRequest()) {
            result2 = result2 && this.getHttpRequest().equals(other.getHttpRequest());
        }
        result2 = result2 && this.internalGetLabels().equals(other.internalGetLabels());
        boolean bl5 = result2 = result2 && this.hasMetadata() == other.hasMetadata();
        if (this.hasMetadata()) {
            result2 = result2 && this.getMetadata().equals(other.getMetadata());
        }
        boolean bl6 = result2 = result2 && this.hasOperation() == other.hasOperation();
        if (this.hasOperation()) {
            result2 = result2 && this.getOperation().equals(other.getOperation());
        }
        result2 = result2 && this.getTrace().equals(other.getTrace());
        result2 = result2 && this.getSpanId().equals(other.getSpanId());
        boolean bl7 = result2 = result2 && this.hasSourceLocation() == other.hasSourceLocation();
        if (this.hasSourceLocation()) {
            result2 = result2 && this.getSourceLocation().equals(other.getSourceLocation());
        }
        boolean bl8 = result2 = result2 && this.getPayloadCase().equals(other.getPayloadCase());
        if (!result2) {
            return false;
        }
        switch (this.payloadCase_) {
            case 2: {
                result2 = result2 && this.getProtoPayload().equals(other.getProtoPayload());
                break;
            }
            case 3: {
                result2 = result2 && this.getTextPayload().equals(other.getTextPayload());
                break;
            }
            case 6: {
                result2 = result2 && this.getJsonPayload().equals(other.getJsonPayload());
                break;
            }
        }
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogEntry.getDescriptor().hashCode();
        hash = 37 * hash + 12;
        hash = 53 * hash + this.getLogName().hashCode();
        if (this.hasResource()) {
            hash = 37 * hash + 8;
            hash = 53 * hash + this.getResource().hashCode();
        }
        if (this.hasTimestamp()) {
            hash = 37 * hash + 9;
            hash = 53 * hash + this.getTimestamp().hashCode();
        }
        if (this.hasReceiveTimestamp()) {
            hash = 37 * hash + 24;
            hash = 53 * hash + this.getReceiveTimestamp().hashCode();
        }
        hash = 37 * hash + 10;
        hash = 53 * hash + this.severity_;
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getInsertId().hashCode();
        if (this.hasHttpRequest()) {
            hash = 37 * hash + 7;
            hash = 53 * hash + this.getHttpRequest().hashCode();
        }
        if (!this.internalGetLabels().getMap().isEmpty()) {
            hash = 37 * hash + 11;
            hash = 53 * hash + this.internalGetLabels().hashCode();
        }
        if (this.hasMetadata()) {
            hash = 37 * hash + 25;
            hash = 53 * hash + this.getMetadata().hashCode();
        }
        if (this.hasOperation()) {
            hash = 37 * hash + 15;
            hash = 53 * hash + this.getOperation().hashCode();
        }
        hash = 37 * hash + 22;
        hash = 53 * hash + this.getTrace().hashCode();
        hash = 37 * hash + 27;
        hash = 53 * hash + this.getSpanId().hashCode();
        if (this.hasSourceLocation()) {
            hash = 37 * hash + 23;
            hash = 53 * hash + this.getSourceLocation().hashCode();
        }
        switch (this.payloadCase_) {
            case 2: {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getProtoPayload().hashCode();
                break;
            }
            case 3: {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getTextPayload().hashCode();
                break;
            }
            case 6: {
                hash = 37 * hash + 6;
                hash = 53 * hash + this.getJsonPayload().hashCode();
                break;
            }
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogEntry parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntry parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntry parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntry parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntry parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntry parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntry parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogEntry parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntry parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntry parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogEntry.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogEntry prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    public static LogEntry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogEntry> parser() {
        return PARSER;
    }

    public Parser<LogEntry> getParserForType() {
        return PARSER;
    }

    @Override
    public LogEntry getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogEntryOrBuilder {
        private int payloadCase_ = 0;
        private Object payload_;
        private int bitField0_;
        private Object logName_ = "";
        private MonitoredResource resource_ = null;
        private SingleFieldBuilderV3<MonitoredResource, MonitoredResource.Builder, MonitoredResourceOrBuilder> resourceBuilder_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> protoPayloadBuilder_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> jsonPayloadBuilder_;
        private Timestamp timestamp_ = null;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
        private Timestamp receiveTimestamp_ = null;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> receiveTimestampBuilder_;
        private int severity_ = 0;
        private Object insertId_ = "";
        private HttpRequest httpRequest_ = null;
        private SingleFieldBuilderV3<HttpRequest, HttpRequest.Builder, HttpRequestOrBuilder> httpRequestBuilder_;
        private MapField<String, String> labels_;
        private MonitoredResourceMetadata metadata_ = null;
        private SingleFieldBuilderV3<MonitoredResourceMetadata, MonitoredResourceMetadata.Builder, MonitoredResourceMetadataOrBuilder> metadataBuilder_;
        private LogEntryOperation operation_ = null;
        private SingleFieldBuilderV3<LogEntryOperation, LogEntryOperation.Builder, LogEntryOperationOrBuilder> operationBuilder_;
        private Object trace_ = "";
        private Object spanId_ = "";
        private LogEntrySourceLocation sourceLocation_ = null;
        private SingleFieldBuilderV3<LogEntrySourceLocation, LogEntrySourceLocation.Builder, LogEntrySourceLocationOrBuilder> sourceLocationBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntry_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 11: {
                    return this.internalGetLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 11: {
                    return this.internalGetMutableLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntry.class, Builder.class);
        }

        private Builder() {
            this.maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (alwaysUseFieldBuilders) {
                // empty if block
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.logName_ = "";
            if (this.resourceBuilder_ == null) {
                this.resource_ = null;
            } else {
                this.resource_ = null;
                this.resourceBuilder_ = null;
            }
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestamp_ = null;
            } else {
                this.receiveTimestamp_ = null;
                this.receiveTimestampBuilder_ = null;
            }
            this.severity_ = 0;
            this.insertId_ = "";
            if (this.httpRequestBuilder_ == null) {
                this.httpRequest_ = null;
            } else {
                this.httpRequest_ = null;
                this.httpRequestBuilder_ = null;
            }
            this.internalGetMutableLabels().clear();
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            if (this.operationBuilder_ == null) {
                this.operation_ = null;
            } else {
                this.operation_ = null;
                this.operationBuilder_ = null;
            }
            this.trace_ = "";
            this.spanId_ = "";
            if (this.sourceLocationBuilder_ == null) {
                this.sourceLocation_ = null;
            } else {
                this.sourceLocation_ = null;
                this.sourceLocationBuilder_ = null;
            }
            this.payloadCase_ = 0;
            this.payload_ = null;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntry_descriptor;
        }

        @Override
        public LogEntry getDefaultInstanceForType() {
            return LogEntry.getDefaultInstance();
        }

        @Override
        public LogEntry build() {
            LogEntry result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogEntry buildPartial() {
            LogEntry result2 = new LogEntry(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.logName_ = this.logName_;
            if (this.resourceBuilder_ == null) {
                result2.resource_ = this.resource_;
            } else {
                result2.resource_ = this.resourceBuilder_.build();
            }
            if (this.payloadCase_ == 2) {
                if (this.protoPayloadBuilder_ == null) {
                    result2.payload_ = this.payload_;
                } else {
                    result2.payload_ = this.protoPayloadBuilder_.build();
                }
            }
            if (this.payloadCase_ == 3) {
                result2.payload_ = this.payload_;
            }
            if (this.payloadCase_ == 6) {
                if (this.jsonPayloadBuilder_ == null) {
                    result2.payload_ = this.payload_;
                } else {
                    result2.payload_ = this.jsonPayloadBuilder_.build();
                }
            }
            if (this.timestampBuilder_ == null) {
                result2.timestamp_ = this.timestamp_;
            } else {
                result2.timestamp_ = this.timestampBuilder_.build();
            }
            if (this.receiveTimestampBuilder_ == null) {
                result2.receiveTimestamp_ = this.receiveTimestamp_;
            } else {
                result2.receiveTimestamp_ = this.receiveTimestampBuilder_.build();
            }
            result2.severity_ = this.severity_;
            result2.insertId_ = this.insertId_;
            if (this.httpRequestBuilder_ == null) {
                result2.httpRequest_ = this.httpRequest_;
            } else {
                result2.httpRequest_ = this.httpRequestBuilder_.build();
            }
            result2.labels_ = this.internalGetLabels();
            result2.labels_.makeImmutable();
            if (this.metadataBuilder_ == null) {
                result2.metadata_ = this.metadata_;
            } else {
                result2.metadata_ = this.metadataBuilder_.build();
            }
            if (this.operationBuilder_ == null) {
                result2.operation_ = this.operation_;
            } else {
                result2.operation_ = this.operationBuilder_.build();
            }
            result2.trace_ = this.trace_;
            result2.spanId_ = this.spanId_;
            if (this.sourceLocationBuilder_ == null) {
                result2.sourceLocation_ = this.sourceLocation_;
            } else {
                result2.sourceLocation_ = this.sourceLocationBuilder_.build();
            }
            result2.bitField0_ = to_bitField0_;
            result2.payloadCase_ = this.payloadCase_;
            this.onBuilt();
            return result2;
        }

        @Override
        public Builder clone() {
            return (Builder)super.clone();
        }

        @Override
        public Builder setField(Descriptors.FieldDescriptor field2, Object value) {
            return (Builder)super.setField(field2, value);
        }

        @Override
        public Builder clearField(Descriptors.FieldDescriptor field2) {
            return (Builder)super.clearField(field2);
        }

        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder)super.clearOneof(oneof);
        }

        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field2, int index, Object value) {
            return (Builder)super.setRepeatedField(field2, index, value);
        }

        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field2, Object value) {
            return (Builder)super.addRepeatedField(field2, value);
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof LogEntry) {
                return this.mergeFrom((LogEntry)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogEntry other) {
            if (other == LogEntry.getDefaultInstance()) {
                return this;
            }
            if (!other.getLogName().isEmpty()) {
                this.logName_ = other.logName_;
                this.onChanged();
            }
            if (other.hasResource()) {
                this.mergeResource(other.getResource());
            }
            if (other.hasTimestamp()) {
                this.mergeTimestamp(other.getTimestamp());
            }
            if (other.hasReceiveTimestamp()) {
                this.mergeReceiveTimestamp(other.getReceiveTimestamp());
            }
            if (other.severity_ != 0) {
                this.setSeverityValue(other.getSeverityValue());
            }
            if (!other.getInsertId().isEmpty()) {
                this.insertId_ = other.insertId_;
                this.onChanged();
            }
            if (other.hasHttpRequest()) {
                this.mergeHttpRequest(other.getHttpRequest());
            }
            this.internalGetMutableLabels().mergeFrom(other.internalGetLabels());
            if (other.hasMetadata()) {
                this.mergeMetadata(other.getMetadata());
            }
            if (other.hasOperation()) {
                this.mergeOperation(other.getOperation());
            }
            if (!other.getTrace().isEmpty()) {
                this.trace_ = other.trace_;
                this.onChanged();
            }
            if (!other.getSpanId().isEmpty()) {
                this.spanId_ = other.spanId_;
                this.onChanged();
            }
            if (other.hasSourceLocation()) {
                this.mergeSourceLocation(other.getSourceLocation());
            }
            switch (other.getPayloadCase()) {
                case PROTO_PAYLOAD: {
                    this.mergeProtoPayload(other.getProtoPayload());
                    break;
                }
                case TEXT_PAYLOAD: {
                    this.payloadCase_ = 3;
                    this.payload_ = other.payload_;
                    this.onChanged();
                    break;
                }
                case JSON_PAYLOAD: {
                    this.mergeJsonPayload(other.getJsonPayload());
                    break;
                }
            }
            this.mergeUnknownFields(other.unknownFields);
            this.onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            LogEntry parsedMessage = null;
            try {
                parsedMessage = (LogEntry)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogEntry)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        @Override
        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        public Builder clearPayload() {
            this.payloadCase_ = 0;
            this.payload_ = null;
            this.onChanged();
            return this;
        }

        @Override
        public String getLogName() {
            Object ref = this.logName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.logName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getLogNameBytes() {
            Object ref = this.logName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.logName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setLogName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.logName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLogName() {
            this.logName_ = LogEntry.getDefaultInstance().getLogName();
            this.onChanged();
            return this;
        }

        public Builder setLogNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntry.checkByteStringIsUtf8(value);
            this.logName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasResource() {
            return this.resourceBuilder_ != null || this.resource_ != null;
        }

        @Override
        public MonitoredResource getResource() {
            if (this.resourceBuilder_ == null) {
                return this.resource_ == null ? MonitoredResource.getDefaultInstance() : this.resource_;
            }
            return this.resourceBuilder_.getMessage();
        }

        public Builder setResource(MonitoredResource value) {
            if (this.resourceBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.resource_ = value;
                this.onChanged();
            } else {
                this.resourceBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setResource(MonitoredResource.Builder builderForValue) {
            if (this.resourceBuilder_ == null) {
                this.resource_ = builderForValue.build();
                this.onChanged();
            } else {
                this.resourceBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeResource(MonitoredResource value) {
            if (this.resourceBuilder_ == null) {
                this.resource_ = this.resource_ != null ? MonitoredResource.newBuilder(this.resource_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.resourceBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearResource() {
            if (this.resourceBuilder_ == null) {
                this.resource_ = null;
                this.onChanged();
            } else {
                this.resource_ = null;
                this.resourceBuilder_ = null;
            }
            return this;
        }

        public MonitoredResource.Builder getResourceBuilder() {
            this.onChanged();
            return this.getResourceFieldBuilder().getBuilder();
        }

        @Override
        public MonitoredResourceOrBuilder getResourceOrBuilder() {
            if (this.resourceBuilder_ != null) {
                return this.resourceBuilder_.getMessageOrBuilder();
            }
            return this.resource_ == null ? MonitoredResource.getDefaultInstance() : this.resource_;
        }

        private SingleFieldBuilderV3<MonitoredResource, MonitoredResource.Builder, MonitoredResourceOrBuilder> getResourceFieldBuilder() {
            if (this.resourceBuilder_ == null) {
                this.resourceBuilder_ = new SingleFieldBuilderV3(this.getResource(), this.getParentForChildren(), this.isClean());
                this.resource_ = null;
            }
            return this.resourceBuilder_;
        }

        @Override
        public boolean hasProtoPayload() {
            return this.payloadCase_ == 2;
        }

        @Override
        public Any getProtoPayload() {
            if (this.protoPayloadBuilder_ == null) {
                if (this.payloadCase_ == 2) {
                    return (Any)this.payload_;
                }
                return Any.getDefaultInstance();
            }
            if (this.payloadCase_ == 2) {
                return this.protoPayloadBuilder_.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setProtoPayload(Any value) {
            if (this.protoPayloadBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.payload_ = value;
                this.onChanged();
            } else {
                this.protoPayloadBuilder_.setMessage(value);
            }
            this.payloadCase_ = 2;
            return this;
        }

        public Builder setProtoPayload(Any.Builder builderForValue) {
            if (this.protoPayloadBuilder_ == null) {
                this.payload_ = builderForValue.build();
                this.onChanged();
            } else {
                this.protoPayloadBuilder_.setMessage(builderForValue.build());
            }
            this.payloadCase_ = 2;
            return this;
        }

        public Builder mergeProtoPayload(Any value) {
            if (this.protoPayloadBuilder_ == null) {
                this.payload_ = this.payloadCase_ == 2 && this.payload_ != Any.getDefaultInstance() ? Any.newBuilder((Any)this.payload_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                if (this.payloadCase_ == 2) {
                    this.protoPayloadBuilder_.mergeFrom(value);
                }
                this.protoPayloadBuilder_.setMessage(value);
            }
            this.payloadCase_ = 2;
            return this;
        }

        public Builder clearProtoPayload() {
            if (this.protoPayloadBuilder_ == null) {
                if (this.payloadCase_ == 2) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    this.onChanged();
                }
            } else {
                if (this.payloadCase_ == 2) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.protoPayloadBuilder_.clear();
            }
            return this;
        }

        public Any.Builder getProtoPayloadBuilder() {
            return this.getProtoPayloadFieldBuilder().getBuilder();
        }

        @Override
        public AnyOrBuilder getProtoPayloadOrBuilder() {
            if (this.payloadCase_ == 2 && this.protoPayloadBuilder_ != null) {
                return this.protoPayloadBuilder_.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 2) {
                return (Any)this.payload_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getProtoPayloadFieldBuilder() {
            if (this.protoPayloadBuilder_ == null) {
                if (this.payloadCase_ != 2) {
                    this.payload_ = Any.getDefaultInstance();
                }
                this.protoPayloadBuilder_ = new SingleFieldBuilderV3((Any)this.payload_, this.getParentForChildren(), this.isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 2;
            this.onChanged();
            return this.protoPayloadBuilder_;
        }

        @Override
        public String getTextPayload() {
            Object ref = "";
            if (this.payloadCase_ == 3) {
                ref = this.payload_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.payloadCase_ == 3) {
                    this.payload_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getTextPayloadBytes() {
            Object ref = "";
            if (this.payloadCase_ == 3) {
                ref = this.payload_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.payloadCase_ == 3) {
                    this.payload_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setTextPayload(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.payloadCase_ = 3;
            this.payload_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearTextPayload() {
            if (this.payloadCase_ == 3) {
                this.payloadCase_ = 0;
                this.payload_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setTextPayloadBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntry.checkByteStringIsUtf8(value);
            this.payloadCase_ = 3;
            this.payload_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasJsonPayload() {
            return this.payloadCase_ == 6;
        }

        @Override
        public Struct getJsonPayload() {
            if (this.jsonPayloadBuilder_ == null) {
                if (this.payloadCase_ == 6) {
                    return (Struct)this.payload_;
                }
                return Struct.getDefaultInstance();
            }
            if (this.payloadCase_ == 6) {
                return this.jsonPayloadBuilder_.getMessage();
            }
            return Struct.getDefaultInstance();
        }

        public Builder setJsonPayload(Struct value) {
            if (this.jsonPayloadBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.payload_ = value;
                this.onChanged();
            } else {
                this.jsonPayloadBuilder_.setMessage(value);
            }
            this.payloadCase_ = 6;
            return this;
        }

        public Builder setJsonPayload(Struct.Builder builderForValue) {
            if (this.jsonPayloadBuilder_ == null) {
                this.payload_ = builderForValue.build();
                this.onChanged();
            } else {
                this.jsonPayloadBuilder_.setMessage(builderForValue.build());
            }
            this.payloadCase_ = 6;
            return this;
        }

        public Builder mergeJsonPayload(Struct value) {
            if (this.jsonPayloadBuilder_ == null) {
                this.payload_ = this.payloadCase_ == 6 && this.payload_ != Struct.getDefaultInstance() ? Struct.newBuilder((Struct)this.payload_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                if (this.payloadCase_ == 6) {
                    this.jsonPayloadBuilder_.mergeFrom(value);
                }
                this.jsonPayloadBuilder_.setMessage(value);
            }
            this.payloadCase_ = 6;
            return this;
        }

        public Builder clearJsonPayload() {
            if (this.jsonPayloadBuilder_ == null) {
                if (this.payloadCase_ == 6) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    this.onChanged();
                }
            } else {
                if (this.payloadCase_ == 6) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.jsonPayloadBuilder_.clear();
            }
            return this;
        }

        public Struct.Builder getJsonPayloadBuilder() {
            return this.getJsonPayloadFieldBuilder().getBuilder();
        }

        @Override
        public StructOrBuilder getJsonPayloadOrBuilder() {
            if (this.payloadCase_ == 6 && this.jsonPayloadBuilder_ != null) {
                return this.jsonPayloadBuilder_.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 6) {
                return (Struct)this.payload_;
            }
            return Struct.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getJsonPayloadFieldBuilder() {
            if (this.jsonPayloadBuilder_ == null) {
                if (this.payloadCase_ != 6) {
                    this.payload_ = Struct.getDefaultInstance();
                }
                this.jsonPayloadBuilder_ = new SingleFieldBuilderV3((Struct)this.payload_, this.getParentForChildren(), this.isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 6;
            this.onChanged();
            return this.jsonPayloadBuilder_;
        }

        @Override
        public boolean hasTimestamp() {
            return this.timestampBuilder_ != null || this.timestamp_ != null;
        }

        @Override
        public Timestamp getTimestamp() {
            if (this.timestampBuilder_ == null) {
                return this.timestamp_ == null ? Timestamp.getDefaultInstance() : this.timestamp_;
            }
            return this.timestampBuilder_.getMessage();
        }

        public Builder setTimestamp(Timestamp value) {
            if (this.timestampBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.timestamp_ = value;
                this.onChanged();
            } else {
                this.timestampBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setTimestamp(Timestamp.Builder builderForValue) {
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = builderForValue.build();
                this.onChanged();
            } else {
                this.timestampBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeTimestamp(Timestamp value) {
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = this.timestamp_ != null ? Timestamp.newBuilder(this.timestamp_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.timestampBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearTimestamp() {
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
                this.onChanged();
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getTimestampBuilder() {
            this.onChanged();
            return this.getTimestampFieldBuilder().getBuilder();
        }

        @Override
        public TimestampOrBuilder getTimestampOrBuilder() {
            if (this.timestampBuilder_ != null) {
                return this.timestampBuilder_.getMessageOrBuilder();
            }
            return this.timestamp_ == null ? Timestamp.getDefaultInstance() : this.timestamp_;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimestampFieldBuilder() {
            if (this.timestampBuilder_ == null) {
                this.timestampBuilder_ = new SingleFieldBuilderV3(this.getTimestamp(), this.getParentForChildren(), this.isClean());
                this.timestamp_ = null;
            }
            return this.timestampBuilder_;
        }

        @Override
        public boolean hasReceiveTimestamp() {
            return this.receiveTimestampBuilder_ != null || this.receiveTimestamp_ != null;
        }

        @Override
        public Timestamp getReceiveTimestamp() {
            if (this.receiveTimestampBuilder_ == null) {
                return this.receiveTimestamp_ == null ? Timestamp.getDefaultInstance() : this.receiveTimestamp_;
            }
            return this.receiveTimestampBuilder_.getMessage();
        }

        public Builder setReceiveTimestamp(Timestamp value) {
            if (this.receiveTimestampBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.receiveTimestamp_ = value;
                this.onChanged();
            } else {
                this.receiveTimestampBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setReceiveTimestamp(Timestamp.Builder builderForValue) {
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestamp_ = builderForValue.build();
                this.onChanged();
            } else {
                this.receiveTimestampBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeReceiveTimestamp(Timestamp value) {
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestamp_ = this.receiveTimestamp_ != null ? Timestamp.newBuilder(this.receiveTimestamp_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.receiveTimestampBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearReceiveTimestamp() {
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestamp_ = null;
                this.onChanged();
            } else {
                this.receiveTimestamp_ = null;
                this.receiveTimestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getReceiveTimestampBuilder() {
            this.onChanged();
            return this.getReceiveTimestampFieldBuilder().getBuilder();
        }

        @Override
        public TimestampOrBuilder getReceiveTimestampOrBuilder() {
            if (this.receiveTimestampBuilder_ != null) {
                return this.receiveTimestampBuilder_.getMessageOrBuilder();
            }
            return this.receiveTimestamp_ == null ? Timestamp.getDefaultInstance() : this.receiveTimestamp_;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getReceiveTimestampFieldBuilder() {
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestampBuilder_ = new SingleFieldBuilderV3(this.getReceiveTimestamp(), this.getParentForChildren(), this.isClean());
                this.receiveTimestamp_ = null;
            }
            return this.receiveTimestampBuilder_;
        }

        @Override
        public int getSeverityValue() {
            return this.severity_;
        }

        public Builder setSeverityValue(int value) {
            this.severity_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public LogSeverity getSeverity() {
            LogSeverity result2 = LogSeverity.valueOf(this.severity_);
            return result2 == null ? LogSeverity.UNRECOGNIZED : result2;
        }

        public Builder setSeverity(LogSeverity value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.severity_ = value.getNumber();
            this.onChanged();
            return this;
        }

        public Builder clearSeverity() {
            this.severity_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public String getInsertId() {
            Object ref = this.insertId_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.insertId_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getInsertIdBytes() {
            Object ref = this.insertId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.insertId_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setInsertId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.insertId_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearInsertId() {
            this.insertId_ = LogEntry.getDefaultInstance().getInsertId();
            this.onChanged();
            return this;
        }

        public Builder setInsertIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntry.checkByteStringIsUtf8(value);
            this.insertId_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasHttpRequest() {
            return this.httpRequestBuilder_ != null || this.httpRequest_ != null;
        }

        @Override
        public HttpRequest getHttpRequest() {
            if (this.httpRequestBuilder_ == null) {
                return this.httpRequest_ == null ? HttpRequest.getDefaultInstance() : this.httpRequest_;
            }
            return this.httpRequestBuilder_.getMessage();
        }

        public Builder setHttpRequest(HttpRequest value) {
            if (this.httpRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.httpRequest_ = value;
                this.onChanged();
            } else {
                this.httpRequestBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setHttpRequest(HttpRequest.Builder builderForValue) {
            if (this.httpRequestBuilder_ == null) {
                this.httpRequest_ = builderForValue.build();
                this.onChanged();
            } else {
                this.httpRequestBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeHttpRequest(HttpRequest value) {
            if (this.httpRequestBuilder_ == null) {
                this.httpRequest_ = this.httpRequest_ != null ? HttpRequest.newBuilder(this.httpRequest_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.httpRequestBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearHttpRequest() {
            if (this.httpRequestBuilder_ == null) {
                this.httpRequest_ = null;
                this.onChanged();
            } else {
                this.httpRequest_ = null;
                this.httpRequestBuilder_ = null;
            }
            return this;
        }

        public HttpRequest.Builder getHttpRequestBuilder() {
            this.onChanged();
            return this.getHttpRequestFieldBuilder().getBuilder();
        }

        @Override
        public HttpRequestOrBuilder getHttpRequestOrBuilder() {
            if (this.httpRequestBuilder_ != null) {
                return this.httpRequestBuilder_.getMessageOrBuilder();
            }
            return this.httpRequest_ == null ? HttpRequest.getDefaultInstance() : this.httpRequest_;
        }

        private SingleFieldBuilderV3<HttpRequest, HttpRequest.Builder, HttpRequestOrBuilder> getHttpRequestFieldBuilder() {
            if (this.httpRequestBuilder_ == null) {
                this.httpRequestBuilder_ = new SingleFieldBuilderV3(this.getHttpRequest(), this.getParentForChildren(), this.isClean());
                this.httpRequest_ = null;
            }
            return this.httpRequestBuilder_;
        }

        private MapField<String, String> internalGetLabels() {
            if (this.labels_ == null) {
                return MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            return this.labels_;
        }

        private MapField<String, String> internalGetMutableLabels() {
            this.onChanged();
            if (this.labels_ == null) {
                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.copy();
            }
            return this.labels_;
        }

        @Override
        public int getLabelsCount() {
            return this.internalGetLabels().getMap().size();
        }

        @Override
        public boolean containsLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return this.internalGetLabels().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<String, String> getLabels() {
            return this.getLabelsMap();
        }

        @Override
        public Map<String, String> getLabelsMap() {
            return this.internalGetLabels().getMap();
        }

        @Override
        public String getLabelsOrDefault(String key, String defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabels().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public String getLabelsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabels().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearLabels() {
            this.internalGetMutableLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabels().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableLabels() {
            return this.internalGetMutableLabels().getMutableMap();
        }

        public Builder putLabels(String key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabels().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllLabels(Map<String, String> values) {
            this.internalGetMutableLabels().getMutableMap().putAll(values);
            return this;
        }

        @Override
        public boolean hasMetadata() {
            return this.metadataBuilder_ != null || this.metadata_ != null;
        }

        @Override
        public MonitoredResourceMetadata getMetadata() {
            if (this.metadataBuilder_ == null) {
                return this.metadata_ == null ? MonitoredResourceMetadata.getDefaultInstance() : this.metadata_;
            }
            return this.metadataBuilder_.getMessage();
        }

        public Builder setMetadata(MonitoredResourceMetadata value) {
            if (this.metadataBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.metadata_ = value;
                this.onChanged();
            } else {
                this.metadataBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setMetadata(MonitoredResourceMetadata.Builder builderForValue) {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = builderForValue.build();
                this.onChanged();
            } else {
                this.metadataBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeMetadata(MonitoredResourceMetadata value) {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = this.metadata_ != null ? MonitoredResourceMetadata.newBuilder(this.metadata_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.metadataBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                this.onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public MonitoredResourceMetadata.Builder getMetadataBuilder() {
            this.onChanged();
            return this.getMetadataFieldBuilder().getBuilder();
        }

        @Override
        public MonitoredResourceMetadataOrBuilder getMetadataOrBuilder() {
            if (this.metadataBuilder_ != null) {
                return this.metadataBuilder_.getMessageOrBuilder();
            }
            return this.metadata_ == null ? MonitoredResourceMetadata.getDefaultInstance() : this.metadata_;
        }

        private SingleFieldBuilderV3<MonitoredResourceMetadata, MonitoredResourceMetadata.Builder, MonitoredResourceMetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3(this.getMetadata(), this.getParentForChildren(), this.isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override
        public boolean hasOperation() {
            return this.operationBuilder_ != null || this.operation_ != null;
        }

        @Override
        public LogEntryOperation getOperation() {
            if (this.operationBuilder_ == null) {
                return this.operation_ == null ? LogEntryOperation.getDefaultInstance() : this.operation_;
            }
            return this.operationBuilder_.getMessage();
        }

        public Builder setOperation(LogEntryOperation value) {
            if (this.operationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.operation_ = value;
                this.onChanged();
            } else {
                this.operationBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setOperation(LogEntryOperation.Builder builderForValue) {
            if (this.operationBuilder_ == null) {
                this.operation_ = builderForValue.build();
                this.onChanged();
            } else {
                this.operationBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeOperation(LogEntryOperation value) {
            if (this.operationBuilder_ == null) {
                this.operation_ = this.operation_ != null ? LogEntryOperation.newBuilder(this.operation_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.operationBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearOperation() {
            if (this.operationBuilder_ == null) {
                this.operation_ = null;
                this.onChanged();
            } else {
                this.operation_ = null;
                this.operationBuilder_ = null;
            }
            return this;
        }

        public LogEntryOperation.Builder getOperationBuilder() {
            this.onChanged();
            return this.getOperationFieldBuilder().getBuilder();
        }

        @Override
        public LogEntryOperationOrBuilder getOperationOrBuilder() {
            if (this.operationBuilder_ != null) {
                return this.operationBuilder_.getMessageOrBuilder();
            }
            return this.operation_ == null ? LogEntryOperation.getDefaultInstance() : this.operation_;
        }

        private SingleFieldBuilderV3<LogEntryOperation, LogEntryOperation.Builder, LogEntryOperationOrBuilder> getOperationFieldBuilder() {
            if (this.operationBuilder_ == null) {
                this.operationBuilder_ = new SingleFieldBuilderV3(this.getOperation(), this.getParentForChildren(), this.isClean());
                this.operation_ = null;
            }
            return this.operationBuilder_;
        }

        @Override
        public String getTrace() {
            Object ref = this.trace_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.trace_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getTraceBytes() {
            Object ref = this.trace_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.trace_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setTrace(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.trace_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearTrace() {
            this.trace_ = LogEntry.getDefaultInstance().getTrace();
            this.onChanged();
            return this;
        }

        public Builder setTraceBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntry.checkByteStringIsUtf8(value);
            this.trace_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getSpanId() {
            Object ref = this.spanId_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.spanId_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSpanIdBytes() {
            Object ref = this.spanId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.spanId_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSpanId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.spanId_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSpanId() {
            this.spanId_ = LogEntry.getDefaultInstance().getSpanId();
            this.onChanged();
            return this;
        }

        public Builder setSpanIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntry.checkByteStringIsUtf8(value);
            this.spanId_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasSourceLocation() {
            return this.sourceLocationBuilder_ != null || this.sourceLocation_ != null;
        }

        @Override
        public LogEntrySourceLocation getSourceLocation() {
            if (this.sourceLocationBuilder_ == null) {
                return this.sourceLocation_ == null ? LogEntrySourceLocation.getDefaultInstance() : this.sourceLocation_;
            }
            return this.sourceLocationBuilder_.getMessage();
        }

        public Builder setSourceLocation(LogEntrySourceLocation value) {
            if (this.sourceLocationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.sourceLocation_ = value;
                this.onChanged();
            } else {
                this.sourceLocationBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setSourceLocation(LogEntrySourceLocation.Builder builderForValue) {
            if (this.sourceLocationBuilder_ == null) {
                this.sourceLocation_ = builderForValue.build();
                this.onChanged();
            } else {
                this.sourceLocationBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeSourceLocation(LogEntrySourceLocation value) {
            if (this.sourceLocationBuilder_ == null) {
                this.sourceLocation_ = this.sourceLocation_ != null ? LogEntrySourceLocation.newBuilder(this.sourceLocation_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.sourceLocationBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearSourceLocation() {
            if (this.sourceLocationBuilder_ == null) {
                this.sourceLocation_ = null;
                this.onChanged();
            } else {
                this.sourceLocation_ = null;
                this.sourceLocationBuilder_ = null;
            }
            return this;
        }

        public LogEntrySourceLocation.Builder getSourceLocationBuilder() {
            this.onChanged();
            return this.getSourceLocationFieldBuilder().getBuilder();
        }

        @Override
        public LogEntrySourceLocationOrBuilder getSourceLocationOrBuilder() {
            if (this.sourceLocationBuilder_ != null) {
                return this.sourceLocationBuilder_.getMessageOrBuilder();
            }
            return this.sourceLocation_ == null ? LogEntrySourceLocation.getDefaultInstance() : this.sourceLocation_;
        }

        private SingleFieldBuilderV3<LogEntrySourceLocation, LogEntrySourceLocation.Builder, LogEntrySourceLocationOrBuilder> getSourceLocationFieldBuilder() {
            if (this.sourceLocationBuilder_ == null) {
                this.sourceLocationBuilder_ = new SingleFieldBuilderV3(this.getSourceLocation(), this.getParentForChildren(), this.isClean());
                this.sourceLocation_ = null;
            }
            return this.sourceLocationBuilder_;
        }

        @Override
        public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.setUnknownFieldsProto3(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.mergeUnknownFields(unknownFields);
        }
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(LogEntryProto.internal_static_google_logging_v2_LogEntry_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    public static enum PayloadCase implements Internal.EnumLite
    {
        PROTO_PAYLOAD(2),
        TEXT_PAYLOAD(3),
        JSON_PAYLOAD(6),
        PAYLOAD_NOT_SET(0);

        private final int value;

        private PayloadCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static PayloadCase valueOf(int value) {
            return PayloadCase.forNumber(value);
        }

        public static PayloadCase forNumber(int value) {
            switch (value) {
                case 2: {
                    return PROTO_PAYLOAD;
                }
                case 3: {
                    return TEXT_PAYLOAD;
                }
                case 6: {
                    return JSON_PAYLOAD;
                }
                case 0: {
                    return PAYLOAD_NOT_SET;
                }
            }
            return null;
        }

        @Override
        public int getNumber() {
            return this.value;
        }
    }
}

