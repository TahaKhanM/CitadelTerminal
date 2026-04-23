/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.CreateSinkRequestOrBuilder;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class CreateSinkRequest
extends GeneratedMessageV3
implements CreateSinkRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PARENT_FIELD_NUMBER = 1;
    private volatile Object parent_;
    public static final int SINK_FIELD_NUMBER = 2;
    private LogSink sink_;
    public static final int UNIQUE_WRITER_IDENTITY_FIELD_NUMBER = 3;
    private boolean uniqueWriterIdentity_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final CreateSinkRequest DEFAULT_INSTANCE = new CreateSinkRequest();
    private static final Parser<CreateSinkRequest> PARSER = new AbstractParser<CreateSinkRequest>(){

        @Override
        public CreateSinkRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new CreateSinkRequest(input2, extensionRegistry);
        }
    };

    private CreateSinkRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CreateSinkRequest() {
        this.parent_ = "";
        this.uniqueWriterIdentity_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateSinkRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block12: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block12;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.parent_ = s2;
                        continue block12;
                    }
                    case 18: {
                        LogSink.Builder subBuilder = null;
                        if (this.sink_ != null) {
                            subBuilder = this.sink_.toBuilder();
                        }
                        this.sink_ = input2.readMessage(LogSink.parser(), extensionRegistry);
                        if (subBuilder == null) continue block12;
                        subBuilder.mergeFrom(this.sink_);
                        this.sink_ = subBuilder.buildPartial();
                        continue block12;
                    }
                    case 24: {
                        this.uniqueWriterIdentity_ = input2.readBool();
                        continue block12;
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
        return LoggingConfigProto.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateSinkRequest.class, Builder.class);
    }

    @Override
    public String getParent() {
        Object ref = this.parent_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.parent_ = s2;
        return s2;
    }

    @Override
    public ByteString getParentBytes() {
        Object ref = this.parent_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.parent_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasSink() {
        return this.sink_ != null;
    }

    @Override
    public LogSink getSink() {
        return this.sink_ == null ? LogSink.getDefaultInstance() : this.sink_;
    }

    @Override
    public LogSinkOrBuilder getSinkOrBuilder() {
        return this.getSink();
    }

    @Override
    public boolean getUniqueWriterIdentity() {
        return this.uniqueWriterIdentity_;
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
        if (!this.getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.parent_);
        }
        if (this.sink_ != null) {
            output.writeMessage(2, this.getSink());
        }
        if (this.uniqueWriterIdentity_) {
            output.writeBool(3, this.uniqueWriterIdentity_);
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
        if (!this.getParentBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.parent_);
        }
        if (this.sink_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getSink());
        }
        if (this.uniqueWriterIdentity_) {
            size2 += CodedOutputStream.computeBoolSize(3, this.uniqueWriterIdentity_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateSinkRequest)) {
            return super.equals(obj);
        }
        CreateSinkRequest other = (CreateSinkRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getParent().equals(other.getParent());
        boolean bl = result2 = result2 && this.hasSink() == other.hasSink();
        if (this.hasSink()) {
            result2 = result2 && this.getSink().equals(other.getSink());
        }
        result2 = result2 && this.getUniqueWriterIdentity() == other.getUniqueWriterIdentity();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + CreateSinkRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getParent().hashCode();
        if (this.hasSink()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getSink().hashCode();
        }
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashBoolean(this.getUniqueWriterIdentity());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static CreateSinkRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateSinkRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateSinkRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateSinkRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateSinkRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateSinkRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateSinkRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CreateSinkRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CreateSinkRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static CreateSinkRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CreateSinkRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CreateSinkRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return CreateSinkRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateSinkRequest prototype) {
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

    public static CreateSinkRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateSinkRequest> parser() {
        return PARSER;
    }

    public Parser<CreateSinkRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public CreateSinkRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements CreateSinkRequestOrBuilder {
        private Object parent_ = "";
        private LogSink sink_ = null;
        private SingleFieldBuilderV3<LogSink, LogSink.Builder, LogSinkOrBuilder> sinkBuilder_;
        private boolean uniqueWriterIdentity_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateSinkRequest.class, Builder.class);
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
            this.parent_ = "";
            if (this.sinkBuilder_ == null) {
                this.sink_ = null;
            } else {
                this.sink_ = null;
                this.sinkBuilder_ = null;
            }
            this.uniqueWriterIdentity_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
        }

        @Override
        public CreateSinkRequest getDefaultInstanceForType() {
            return CreateSinkRequest.getDefaultInstance();
        }

        @Override
        public CreateSinkRequest build() {
            CreateSinkRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public CreateSinkRequest buildPartial() {
            CreateSinkRequest result2 = new CreateSinkRequest(this);
            result2.parent_ = this.parent_;
            if (this.sinkBuilder_ == null) {
                result2.sink_ = this.sink_;
            } else {
                result2.sink_ = this.sinkBuilder_.build();
            }
            result2.uniqueWriterIdentity_ = this.uniqueWriterIdentity_;
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
            if (other instanceof CreateSinkRequest) {
                return this.mergeFrom((CreateSinkRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(CreateSinkRequest other) {
            if (other == CreateSinkRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getParent().isEmpty()) {
                this.parent_ = other.parent_;
                this.onChanged();
            }
            if (other.hasSink()) {
                this.mergeSink(other.getSink());
            }
            if (other.getUniqueWriterIdentity()) {
                this.setUniqueWriterIdentity(other.getUniqueWriterIdentity());
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
            CreateSinkRequest parsedMessage = null;
            try {
                parsedMessage = (CreateSinkRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (CreateSinkRequest)e.getUnfinishedMessage();
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
        public String getParent() {
            Object ref = this.parent_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.parent_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getParentBytes() {
            Object ref = this.parent_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.parent_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setParent(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.parent_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearParent() {
            this.parent_ = CreateSinkRequest.getDefaultInstance().getParent();
            this.onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            CreateSinkRequest.checkByteStringIsUtf8(value);
            this.parent_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasSink() {
            return this.sinkBuilder_ != null || this.sink_ != null;
        }

        @Override
        public LogSink getSink() {
            if (this.sinkBuilder_ == null) {
                return this.sink_ == null ? LogSink.getDefaultInstance() : this.sink_;
            }
            return this.sinkBuilder_.getMessage();
        }

        public Builder setSink(LogSink value) {
            if (this.sinkBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.sink_ = value;
                this.onChanged();
            } else {
                this.sinkBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setSink(LogSink.Builder builderForValue) {
            if (this.sinkBuilder_ == null) {
                this.sink_ = builderForValue.build();
                this.onChanged();
            } else {
                this.sinkBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeSink(LogSink value) {
            if (this.sinkBuilder_ == null) {
                this.sink_ = this.sink_ != null ? LogSink.newBuilder(this.sink_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.sinkBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearSink() {
            if (this.sinkBuilder_ == null) {
                this.sink_ = null;
                this.onChanged();
            } else {
                this.sink_ = null;
                this.sinkBuilder_ = null;
            }
            return this;
        }

        public LogSink.Builder getSinkBuilder() {
            this.onChanged();
            return this.getSinkFieldBuilder().getBuilder();
        }

        @Override
        public LogSinkOrBuilder getSinkOrBuilder() {
            if (this.sinkBuilder_ != null) {
                return this.sinkBuilder_.getMessageOrBuilder();
            }
            return this.sink_ == null ? LogSink.getDefaultInstance() : this.sink_;
        }

        private SingleFieldBuilderV3<LogSink, LogSink.Builder, LogSinkOrBuilder> getSinkFieldBuilder() {
            if (this.sinkBuilder_ == null) {
                this.sinkBuilder_ = new SingleFieldBuilderV3(this.getSink(), this.getParentForChildren(), this.isClean());
                this.sink_ = null;
            }
            return this.sinkBuilder_;
        }

        @Override
        public boolean getUniqueWriterIdentity() {
            return this.uniqueWriterIdentity_;
        }

        public Builder setUniqueWriterIdentity(boolean value) {
            this.uniqueWriterIdentity_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearUniqueWriterIdentity() {
            this.uniqueWriterIdentity_ = false;
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

