/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LoggingProto;
import com.google.logging.v2.WriteLogEntriesPartialErrorsOrBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class WriteLogEntriesPartialErrors
extends GeneratedMessageV3
implements WriteLogEntriesPartialErrorsOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int LOG_ENTRY_ERRORS_FIELD_NUMBER = 1;
    private MapField<Integer, Status> logEntryErrors_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final WriteLogEntriesPartialErrors DEFAULT_INSTANCE = new WriteLogEntriesPartialErrors();
    private static final Parser<WriteLogEntriesPartialErrors> PARSER = new AbstractParser<WriteLogEntriesPartialErrors>(){

        @Override
        public WriteLogEntriesPartialErrors parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new WriteLogEntriesPartialErrors(input2, extensionRegistry);
        }
    };

    private WriteLogEntriesPartialErrors(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private WriteLogEntriesPartialErrors() {
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private WriteLogEntriesPartialErrors(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block10: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block10;
                    }
                    case 10: {
                        if (!(mutable_bitField0_ & true)) {
                            this.logEntryErrors_ = MapField.newMapField(LogEntryErrorsDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= true;
                        }
                        MapEntry<Integer, Status> logEntryErrors__ = input2.readMessage(LogEntryErrorsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.logEntryErrors_.getMutableMap().put(logEntryErrors__.getKey(), logEntryErrors__.getValue());
                        continue block10;
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
        return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 1: {
                return this.internalGetLogEntryErrors();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_fieldAccessorTable.ensureFieldAccessorsInitialized(WriteLogEntriesPartialErrors.class, Builder.class);
    }

    private MapField<Integer, Status> internalGetLogEntryErrors() {
        if (this.logEntryErrors_ == null) {
            return MapField.emptyMapField(LogEntryErrorsDefaultEntryHolder.defaultEntry);
        }
        return this.logEntryErrors_;
    }

    @Override
    public int getLogEntryErrorsCount() {
        return this.internalGetLogEntryErrors().getMap().size();
    }

    @Override
    public boolean containsLogEntryErrors(int key) {
        return this.internalGetLogEntryErrors().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<Integer, Status> getLogEntryErrors() {
        return this.getLogEntryErrorsMap();
    }

    @Override
    public Map<Integer, Status> getLogEntryErrorsMap() {
        return this.internalGetLogEntryErrors().getMap();
    }

    @Override
    public Status getLogEntryErrorsOrDefault(int key, Status defaultValue) {
        Map<Integer, Status> map2 = this.internalGetLogEntryErrors().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public Status getLogEntryErrorsOrThrow(int key) {
        Map<Integer, Status> map2 = this.internalGetLogEntryErrors().getMap();
        if (!map2.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map2.get(key);
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
        GeneratedMessageV3.serializeIntegerMapTo(output, this.internalGetLogEntryErrors(), LogEntryErrorsDefaultEntryHolder.defaultEntry, 1);
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        for (Map.Entry<Integer, Status> entry : this.internalGetLogEntryErrors().getMap().entrySet()) {
            Message logEntryErrors__ = ((MapEntry.Builder)LogEntryErrorsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(1, logEntryErrors__);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WriteLogEntriesPartialErrors)) {
            return super.equals(obj);
        }
        WriteLogEntriesPartialErrors other = (WriteLogEntriesPartialErrors)obj;
        boolean result2 = true;
        result2 = result2 && this.internalGetLogEntryErrors().equals(other.internalGetLogEntryErrors());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + WriteLogEntriesPartialErrors.getDescriptor().hashCode();
        if (!this.internalGetLogEntryErrors().getMap().isEmpty()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.internalGetLogEntryErrors().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static WriteLogEntriesPartialErrors parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesPartialErrors parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesPartialErrors parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesPartialErrors parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesPartialErrors parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static WriteLogEntriesPartialErrors parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static WriteLogEntriesPartialErrors parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesPartialErrors parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static WriteLogEntriesPartialErrors parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesPartialErrors parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static WriteLogEntriesPartialErrors parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static WriteLogEntriesPartialErrors parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return WriteLogEntriesPartialErrors.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WriteLogEntriesPartialErrors prototype) {
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

    public static WriteLogEntriesPartialErrors getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteLogEntriesPartialErrors> parser() {
        return PARSER;
    }

    public Parser<WriteLogEntriesPartialErrors> getParserForType() {
        return PARSER;
    }

    @Override
    public WriteLogEntriesPartialErrors getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements WriteLogEntriesPartialErrorsOrBuilder {
        private int bitField0_;
        private MapField<Integer, Status> logEntryErrors_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 1: {
                    return this.internalGetLogEntryErrors();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 1: {
                    return this.internalGetMutableLogEntryErrors();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_fieldAccessorTable.ensureFieldAccessorsInitialized(WriteLogEntriesPartialErrors.class, Builder.class);
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
            this.internalGetMutableLogEntryErrors().clear();
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor;
        }

        @Override
        public WriteLogEntriesPartialErrors getDefaultInstanceForType() {
            return WriteLogEntriesPartialErrors.getDefaultInstance();
        }

        @Override
        public WriteLogEntriesPartialErrors build() {
            WriteLogEntriesPartialErrors result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public WriteLogEntriesPartialErrors buildPartial() {
            WriteLogEntriesPartialErrors result2 = new WriteLogEntriesPartialErrors(this);
            int from_bitField0_ = this.bitField0_;
            result2.logEntryErrors_ = this.internalGetLogEntryErrors();
            result2.logEntryErrors_.makeImmutable();
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
            if (other instanceof WriteLogEntriesPartialErrors) {
                return this.mergeFrom((WriteLogEntriesPartialErrors)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(WriteLogEntriesPartialErrors other) {
            if (other == WriteLogEntriesPartialErrors.getDefaultInstance()) {
                return this;
            }
            this.internalGetMutableLogEntryErrors().mergeFrom(other.internalGetLogEntryErrors());
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
            WriteLogEntriesPartialErrors parsedMessage = null;
            try {
                parsedMessage = (WriteLogEntriesPartialErrors)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (WriteLogEntriesPartialErrors)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private MapField<Integer, Status> internalGetLogEntryErrors() {
            if (this.logEntryErrors_ == null) {
                return MapField.emptyMapField(LogEntryErrorsDefaultEntryHolder.defaultEntry);
            }
            return this.logEntryErrors_;
        }

        private MapField<Integer, Status> internalGetMutableLogEntryErrors() {
            this.onChanged();
            if (this.logEntryErrors_ == null) {
                this.logEntryErrors_ = MapField.newMapField(LogEntryErrorsDefaultEntryHolder.defaultEntry);
            }
            if (!this.logEntryErrors_.isMutable()) {
                this.logEntryErrors_ = this.logEntryErrors_.copy();
            }
            return this.logEntryErrors_;
        }

        @Override
        public int getLogEntryErrorsCount() {
            return this.internalGetLogEntryErrors().getMap().size();
        }

        @Override
        public boolean containsLogEntryErrors(int key) {
            return this.internalGetLogEntryErrors().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<Integer, Status> getLogEntryErrors() {
            return this.getLogEntryErrorsMap();
        }

        @Override
        public Map<Integer, Status> getLogEntryErrorsMap() {
            return this.internalGetLogEntryErrors().getMap();
        }

        @Override
        public Status getLogEntryErrorsOrDefault(int key, Status defaultValue) {
            Map<Integer, Status> map2 = this.internalGetLogEntryErrors().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public Status getLogEntryErrorsOrThrow(int key) {
            Map<Integer, Status> map2 = this.internalGetLogEntryErrors().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearLogEntryErrors() {
            this.internalGetMutableLogEntryErrors().getMutableMap().clear();
            return this;
        }

        public Builder removeLogEntryErrors(int key) {
            this.internalGetMutableLogEntryErrors().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<Integer, Status> getMutableLogEntryErrors() {
            return this.internalGetMutableLogEntryErrors().getMutableMap();
        }

        public Builder putLogEntryErrors(int key, Status value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLogEntryErrors().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllLogEntryErrors(Map<Integer, Status> values) {
            this.internalGetMutableLogEntryErrors().getMutableMap().putAll(values);
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

    private static final class LogEntryErrorsDefaultEntryHolder {
        static final MapEntry<Integer, Status> defaultEntry = MapEntry.newDefaultInstance(LoggingProto.internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_descriptor, WireFormat.FieldType.INT32, 0, WireFormat.FieldType.MESSAGE, Status.getDefaultInstance());

        private LogEntryErrorsDefaultEntryHolder() {
        }
    }
}

