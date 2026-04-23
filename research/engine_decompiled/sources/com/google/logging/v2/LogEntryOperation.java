/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogEntryOperationOrBuilder;
import com.google.logging.v2.LogEntryProto;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LogEntryOperation
extends GeneratedMessageV3
implements LogEntryOperationOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int ID_FIELD_NUMBER = 1;
    private volatile Object id_;
    public static final int PRODUCER_FIELD_NUMBER = 2;
    private volatile Object producer_;
    public static final int FIRST_FIELD_NUMBER = 3;
    private boolean first_;
    public static final int LAST_FIELD_NUMBER = 4;
    private boolean last_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogEntryOperation DEFAULT_INSTANCE = new LogEntryOperation();
    private static final Parser<LogEntryOperation> PARSER = new AbstractParser<LogEntryOperation>(){

        @Override
        public LogEntryOperation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogEntryOperation(input2, extensionRegistry);
        }
    };

    private LogEntryOperation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogEntryOperation() {
        this.id_ = "";
        this.producer_ = "";
        this.first_ = false;
        this.last_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogEntryOperation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block13;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.id_ = s2;
                        continue block13;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.producer_ = s2;
                        continue block13;
                    }
                    case 24: {
                        this.first_ = input2.readBool();
                        continue block13;
                    }
                    case 32: {
                        this.last_ = input2.readBool();
                        continue block13;
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
        return LogEntryProto.internal_static_google_logging_v2_LogEntryOperation_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LogEntryProto.internal_static_google_logging_v2_LogEntryOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntryOperation.class, Builder.class);
    }

    @Override
    public String getId() {
        Object ref = this.id_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.id_ = s2;
        return s2;
    }

    @Override
    public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.id_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getProducer() {
        Object ref = this.producer_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.producer_ = s2;
        return s2;
    }

    @Override
    public ByteString getProducerBytes() {
        Object ref = this.producer_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.producer_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean getFirst() {
        return this.first_;
    }

    @Override
    public boolean getLast() {
        return this.last_;
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
        if (!this.getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.id_);
        }
        if (!this.getProducerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.producer_);
        }
        if (this.first_) {
            output.writeBool(3, this.first_);
        }
        if (this.last_) {
            output.writeBool(4, this.last_);
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
        if (!this.getIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.id_);
        }
        if (!this.getProducerBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.producer_);
        }
        if (this.first_) {
            size2 += CodedOutputStream.computeBoolSize(3, this.first_);
        }
        if (this.last_) {
            size2 += CodedOutputStream.computeBoolSize(4, this.last_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEntryOperation)) {
            return super.equals(obj);
        }
        LogEntryOperation other = (LogEntryOperation)obj;
        boolean result2 = true;
        result2 = result2 && this.getId().equals(other.getId());
        result2 = result2 && this.getProducer().equals(other.getProducer());
        result2 = result2 && this.getFirst() == other.getFirst();
        result2 = result2 && this.getLast() == other.getLast();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogEntryOperation.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getId().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getProducer().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashBoolean(this.getFirst());
        hash = 37 * hash + 4;
        hash = 53 * hash + Internal.hashBoolean(this.getLast());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogEntryOperation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntryOperation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntryOperation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntryOperation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntryOperation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntryOperation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntryOperation parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntryOperation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntryOperation parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogEntryOperation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntryOperation parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntryOperation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogEntryOperation.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogEntryOperation prototype) {
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

    public static LogEntryOperation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogEntryOperation> parser() {
        return PARSER;
    }

    public Parser<LogEntryOperation> getParserForType() {
        return PARSER;
    }

    @Override
    public LogEntryOperation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogEntryOperationOrBuilder {
        private Object id_ = "";
        private Object producer_ = "";
        private boolean first_;
        private boolean last_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntryOperation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntryOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntryOperation.class, Builder.class);
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
            this.id_ = "";
            this.producer_ = "";
            this.first_ = false;
            this.last_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntryOperation_descriptor;
        }

        @Override
        public LogEntryOperation getDefaultInstanceForType() {
            return LogEntryOperation.getDefaultInstance();
        }

        @Override
        public LogEntryOperation build() {
            LogEntryOperation result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogEntryOperation buildPartial() {
            LogEntryOperation result2 = new LogEntryOperation(this);
            result2.id_ = this.id_;
            result2.producer_ = this.producer_;
            result2.first_ = this.first_;
            result2.last_ = this.last_;
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
            if (other instanceof LogEntryOperation) {
                return this.mergeFrom((LogEntryOperation)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogEntryOperation other) {
            if (other == LogEntryOperation.getDefaultInstance()) {
                return this;
            }
            if (!other.getId().isEmpty()) {
                this.id_ = other.id_;
                this.onChanged();
            }
            if (!other.getProducer().isEmpty()) {
                this.producer_ = other.producer_;
                this.onChanged();
            }
            if (other.getFirst()) {
                this.setFirst(other.getFirst());
            }
            if (other.getLast()) {
                this.setLast(other.getLast());
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
            LogEntryOperation parsedMessage = null;
            try {
                parsedMessage = (LogEntryOperation)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogEntryOperation)e.getUnfinishedMessage();
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
        public String getId() {
            Object ref = this.id_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.id_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getIdBytes() {
            Object ref = this.id_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.id_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.id_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = LogEntryOperation.getDefaultInstance().getId();
            this.onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntryOperation.checkByteStringIsUtf8(value);
            this.id_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getProducer() {
            Object ref = this.producer_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.producer_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getProducerBytes() {
            Object ref = this.producer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.producer_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setProducer(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.producer_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearProducer() {
            this.producer_ = LogEntryOperation.getDefaultInstance().getProducer();
            this.onChanged();
            return this;
        }

        public Builder setProducerBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntryOperation.checkByteStringIsUtf8(value);
            this.producer_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getFirst() {
            return this.first_;
        }

        public Builder setFirst(boolean value) {
            this.first_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearFirst() {
            this.first_ = false;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getLast() {
            return this.last_;
        }

        public Builder setLast(boolean value) {
            this.last_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLast() {
            this.last_ = false;
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
}

