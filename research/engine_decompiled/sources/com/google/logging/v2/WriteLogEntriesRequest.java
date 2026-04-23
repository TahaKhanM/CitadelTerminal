/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.MonitoredResource;
import com.google.api.MonitoredResourceOrBuilder;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOrBuilder;
import com.google.logging.v2.LoggingProto;
import com.google.logging.v2.WriteLogEntriesRequestOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class WriteLogEntriesRequest
extends GeneratedMessageV3
implements WriteLogEntriesRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int LOG_NAME_FIELD_NUMBER = 1;
    private volatile Object logName_;
    public static final int RESOURCE_FIELD_NUMBER = 2;
    private MonitoredResource resource_;
    public static final int LABELS_FIELD_NUMBER = 3;
    private MapField<String, String> labels_;
    public static final int ENTRIES_FIELD_NUMBER = 4;
    private List<LogEntry> entries_;
    public static final int PARTIAL_SUCCESS_FIELD_NUMBER = 5;
    private boolean partialSuccess_;
    public static final int DRY_RUN_FIELD_NUMBER = 6;
    private boolean dryRun_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final WriteLogEntriesRequest DEFAULT_INSTANCE = new WriteLogEntriesRequest();
    private static final Parser<WriteLogEntriesRequest> PARSER = new AbstractParser<WriteLogEntriesRequest>(){

        @Override
        public WriteLogEntriesRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new WriteLogEntriesRequest(input2, extensionRegistry);
        }
    };

    private WriteLogEntriesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private WriteLogEntriesRequest() {
        this.logName_ = "";
        this.entries_ = Collections.emptyList();
        this.partialSuccess_ = false;
        this.dryRun_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private WriteLogEntriesRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block15: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block15;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.logName_ = s2;
                        continue block15;
                    }
                    case 18: {
                        MonitoredResource.Builder subBuilder = null;
                        if (this.resource_ != null) {
                            subBuilder = this.resource_.toBuilder();
                        }
                        this.resource_ = input2.readMessage(MonitoredResource.parser(), extensionRegistry);
                        if (subBuilder == null) continue block15;
                        subBuilder.mergeFrom(this.resource_);
                        this.resource_ = subBuilder.buildPartial();
                        continue block15;
                    }
                    case 26: {
                        if ((mutable_bitField0_ & 4) != 4) {
                            this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= 4;
                        }
                        MapEntry<String, String> labels__ = input2.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.labels_.getMutableMap().put(labels__.getKey(), labels__.getValue());
                        continue block15;
                    }
                    case 34: {
                        if ((mutable_bitField0_ & 8) != 8) {
                            this.entries_ = new ArrayList<LogEntry>();
                            mutable_bitField0_ |= 8;
                        }
                        this.entries_.add(input2.readMessage(LogEntry.parser(), extensionRegistry));
                        continue block15;
                    }
                    case 40: {
                        this.partialSuccess_ = input2.readBool();
                        continue block15;
                    }
                    case 48: {
                        this.dryRun_ = input2.readBool();
                        continue block15;
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
            if ((mutable_bitField0_ & 8) == 8) {
                this.entries_ = Collections.unmodifiableList(this.entries_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 3: {
                return this.internalGetLabels();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(WriteLogEntriesRequest.class, Builder.class);
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
    public List<LogEntry> getEntriesList() {
        return this.entries_;
    }

    @Override
    public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList() {
        return this.entries_;
    }

    @Override
    public int getEntriesCount() {
        return this.entries_.size();
    }

    @Override
    public LogEntry getEntries(int index) {
        return this.entries_.get(index);
    }

    @Override
    public LogEntryOrBuilder getEntriesOrBuilder(int index) {
        return this.entries_.get(index);
    }

    @Override
    public boolean getPartialSuccess() {
        return this.partialSuccess_;
    }

    @Override
    public boolean getDryRun() {
        return this.dryRun_;
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
        if (!this.getLogNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.logName_);
        }
        if (this.resource_ != null) {
            output.writeMessage(2, this.getResource());
        }
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 3);
        for (int i = 0; i < this.entries_.size(); ++i) {
            output.writeMessage(4, this.entries_.get(i));
        }
        if (this.partialSuccess_) {
            output.writeBool(5, this.partialSuccess_);
        }
        if (this.dryRun_) {
            output.writeBool(6, this.dryRun_);
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
        if (!this.getLogNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.logName_);
        }
        if (this.resource_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getResource());
        }
        for (Map.Entry<String, String> entry : this.internalGetLabels().getMap().entrySet()) {
            Message labels__ = ((MapEntry.Builder)LabelsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(3, labels__);
        }
        for (int i = 0; i < this.entries_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(4, this.entries_.get(i));
        }
        if (this.partialSuccess_) {
            size2 += CodedOutputStream.computeBoolSize(5, this.partialSuccess_);
        }
        if (this.dryRun_) {
            size2 += CodedOutputStream.computeBoolSize(6, this.dryRun_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WriteLogEntriesRequest)) {
            return super.equals(obj);
        }
        WriteLogEntriesRequest other = (WriteLogEntriesRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getLogName().equals(other.getLogName());
        boolean bl = result2 = result2 && this.hasResource() == other.hasResource();
        if (this.hasResource()) {
            result2 = result2 && this.getResource().equals(other.getResource());
        }
        result2 = result2 && this.internalGetLabels().equals(other.internalGetLabels());
        result2 = result2 && this.getEntriesList().equals(other.getEntriesList());
        result2 = result2 && this.getPartialSuccess() == other.getPartialSuccess();
        result2 = result2 && this.getDryRun() == other.getDryRun();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + WriteLogEntriesRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getLogName().hashCode();
        if (this.hasResource()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getResource().hashCode();
        }
        if (!this.internalGetLabels().getMap().isEmpty()) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.internalGetLabels().hashCode();
        }
        if (this.getEntriesCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getEntriesList().hashCode();
        }
        hash = 37 * hash + 5;
        hash = 53 * hash + Internal.hashBoolean(this.getPartialSuccess());
        hash = 37 * hash + 6;
        hash = 53 * hash + Internal.hashBoolean(this.getDryRun());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static WriteLogEntriesRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static WriteLogEntriesRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static WriteLogEntriesRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return WriteLogEntriesRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WriteLogEntriesRequest prototype) {
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

    public static WriteLogEntriesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteLogEntriesRequest> parser() {
        return PARSER;
    }

    public Parser<WriteLogEntriesRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public WriteLogEntriesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements WriteLogEntriesRequestOrBuilder {
        private int bitField0_;
        private Object logName_ = "";
        private MonitoredResource resource_ = null;
        private SingleFieldBuilderV3<MonitoredResource, MonitoredResource.Builder, MonitoredResourceOrBuilder> resourceBuilder_;
        private MapField<String, String> labels_;
        private List<LogEntry> entries_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogEntry, LogEntry.Builder, LogEntryOrBuilder> entriesBuilder_;
        private boolean partialSuccess_;
        private boolean dryRun_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 3: {
                    return this.internalGetLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 3: {
                    return this.internalGetMutableLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(WriteLogEntriesRequest.class, Builder.class);
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
                this.getEntriesFieldBuilder();
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
            this.internalGetMutableLabels().clear();
            if (this.entriesBuilder_ == null) {
                this.entries_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
            } else {
                this.entriesBuilder_.clear();
            }
            this.partialSuccess_ = false;
            this.dryRun_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor;
        }

        @Override
        public WriteLogEntriesRequest getDefaultInstanceForType() {
            return WriteLogEntriesRequest.getDefaultInstance();
        }

        @Override
        public WriteLogEntriesRequest build() {
            WriteLogEntriesRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public WriteLogEntriesRequest buildPartial() {
            WriteLogEntriesRequest result2 = new WriteLogEntriesRequest(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.logName_ = this.logName_;
            if (this.resourceBuilder_ == null) {
                result2.resource_ = this.resource_;
            } else {
                result2.resource_ = this.resourceBuilder_.build();
            }
            result2.labels_ = this.internalGetLabels();
            result2.labels_.makeImmutable();
            if (this.entriesBuilder_ == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.entries_ = Collections.unmodifiableList(this.entries_);
                    this.bitField0_ &= 0xFFFFFFF7;
                }
                result2.entries_ = this.entries_;
            } else {
                result2.entries_ = this.entriesBuilder_.build();
            }
            result2.partialSuccess_ = this.partialSuccess_;
            result2.dryRun_ = this.dryRun_;
            result2.bitField0_ = to_bitField0_;
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
            if (other instanceof WriteLogEntriesRequest) {
                return this.mergeFrom((WriteLogEntriesRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(WriteLogEntriesRequest other) {
            if (other == WriteLogEntriesRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getLogName().isEmpty()) {
                this.logName_ = other.logName_;
                this.onChanged();
            }
            if (other.hasResource()) {
                this.mergeResource(other.getResource());
            }
            this.internalGetMutableLabels().mergeFrom(other.internalGetLabels());
            if (this.entriesBuilder_ == null) {
                if (!other.entries_.isEmpty()) {
                    if (this.entries_.isEmpty()) {
                        this.entries_ = other.entries_;
                        this.bitField0_ &= 0xFFFFFFF7;
                    } else {
                        this.ensureEntriesIsMutable();
                        this.entries_.addAll(other.entries_);
                    }
                    this.onChanged();
                }
            } else if (!other.entries_.isEmpty()) {
                if (this.entriesBuilder_.isEmpty()) {
                    this.entriesBuilder_.dispose();
                    this.entriesBuilder_ = null;
                    this.entries_ = other.entries_;
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.entriesBuilder_ = alwaysUseFieldBuilders ? this.getEntriesFieldBuilder() : null;
                } else {
                    this.entriesBuilder_.addAllMessages(other.entries_);
                }
            }
            if (other.getPartialSuccess()) {
                this.setPartialSuccess(other.getPartialSuccess());
            }
            if (other.getDryRun()) {
                this.setDryRun(other.getDryRun());
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
            WriteLogEntriesRequest parsedMessage = null;
            try {
                parsedMessage = (WriteLogEntriesRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (WriteLogEntriesRequest)e.getUnfinishedMessage();
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
            this.logName_ = WriteLogEntriesRequest.getDefaultInstance().getLogName();
            this.onChanged();
            return this;
        }

        public Builder setLogNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            WriteLogEntriesRequest.checkByteStringIsUtf8(value);
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

        private void ensureEntriesIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.entries_ = new ArrayList<LogEntry>(this.entries_);
                this.bitField0_ |= 8;
            }
        }

        @Override
        public List<LogEntry> getEntriesList() {
            if (this.entriesBuilder_ == null) {
                return Collections.unmodifiableList(this.entries_);
            }
            return this.entriesBuilder_.getMessageList();
        }

        @Override
        public int getEntriesCount() {
            if (this.entriesBuilder_ == null) {
                return this.entries_.size();
            }
            return this.entriesBuilder_.getCount();
        }

        @Override
        public LogEntry getEntries(int index) {
            if (this.entriesBuilder_ == null) {
                return this.entries_.get(index);
            }
            return this.entriesBuilder_.getMessage(index);
        }

        public Builder setEntries(int index, LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.set(index, value);
                this.onChanged();
            } else {
                this.entriesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setEntries(int index, LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addEntries(LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.add(value);
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addEntries(int index, LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.add(index, value);
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addEntries(LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addEntries(int index, LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllEntries(Iterable<? extends LogEntry> values) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.entries_);
                this.onChanged();
            } else {
                this.entriesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearEntries() {
            if (this.entriesBuilder_ == null) {
                this.entries_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
                this.onChanged();
            } else {
                this.entriesBuilder_.clear();
            }
            return this;
        }

        public Builder removeEntries(int index) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.remove(index);
                this.onChanged();
            } else {
                this.entriesBuilder_.remove(index);
            }
            return this;
        }

        public LogEntry.Builder getEntriesBuilder(int index) {
            return this.getEntriesFieldBuilder().getBuilder(index);
        }

        @Override
        public LogEntryOrBuilder getEntriesOrBuilder(int index) {
            if (this.entriesBuilder_ == null) {
                return this.entries_.get(index);
            }
            return this.entriesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList() {
            if (this.entriesBuilder_ != null) {
                return this.entriesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entries_);
        }

        public LogEntry.Builder addEntriesBuilder() {
            return this.getEntriesFieldBuilder().addBuilder(LogEntry.getDefaultInstance());
        }

        public LogEntry.Builder addEntriesBuilder(int index) {
            return this.getEntriesFieldBuilder().addBuilder(index, LogEntry.getDefaultInstance());
        }

        public List<LogEntry.Builder> getEntriesBuilderList() {
            return this.getEntriesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogEntry, LogEntry.Builder, LogEntryOrBuilder> getEntriesFieldBuilder() {
            if (this.entriesBuilder_ == null) {
                this.entriesBuilder_ = new RepeatedFieldBuilderV3(this.entries_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                this.entries_ = null;
            }
            return this.entriesBuilder_;
        }

        @Override
        public boolean getPartialSuccess() {
            return this.partialSuccess_;
        }

        public Builder setPartialSuccess(boolean value) {
            this.partialSuccess_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPartialSuccess() {
            this.partialSuccess_ = false;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getDryRun() {
            return this.dryRun_;
        }

        public Builder setDryRun(boolean value) {
            this.dryRun_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDryRun() {
            this.dryRun_ = false;
            this.onChanged();
            return this;
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
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(LoggingProto.internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }
}

