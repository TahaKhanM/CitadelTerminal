/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
import com.google.logging.v2.UpdateSinkRequestOrBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
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

public final class UpdateSinkRequest
extends GeneratedMessageV3
implements UpdateSinkRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int SINK_NAME_FIELD_NUMBER = 1;
    private volatile Object sinkName_;
    public static final int SINK_FIELD_NUMBER = 2;
    private LogSink sink_;
    public static final int UNIQUE_WRITER_IDENTITY_FIELD_NUMBER = 3;
    private boolean uniqueWriterIdentity_;
    public static final int UPDATE_MASK_FIELD_NUMBER = 4;
    private FieldMask updateMask_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final UpdateSinkRequest DEFAULT_INSTANCE = new UpdateSinkRequest();
    private static final Parser<UpdateSinkRequest> PARSER = new AbstractParser<UpdateSinkRequest>(){

        @Override
        public UpdateSinkRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new UpdateSinkRequest(input2, extensionRegistry);
        }
    };

    private UpdateSinkRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateSinkRequest() {
        this.sinkName_ = "";
        this.uniqueWriterIdentity_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateSinkRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.sinkName_ = s2;
                        continue block13;
                    }
                    case 18: {
                        GeneratedMessageV3.Builder subBuilder = null;
                        if (this.sink_ != null) {
                            subBuilder = this.sink_.toBuilder();
                        }
                        this.sink_ = input2.readMessage(LogSink.parser(), extensionRegistry);
                        if (subBuilder == null) continue block13;
                        ((LogSink.Builder)subBuilder).mergeFrom(this.sink_);
                        this.sink_ = ((LogSink.Builder)subBuilder).buildPartial();
                        continue block13;
                    }
                    case 24: {
                        this.uniqueWriterIdentity_ = input2.readBool();
                        continue block13;
                    }
                    case 34: {
                        GeneratedMessageV3.Builder subBuilder = null;
                        if (this.updateMask_ != null) {
                            subBuilder = this.updateMask_.toBuilder();
                        }
                        this.updateMask_ = input2.readMessage(FieldMask.parser(), extensionRegistry);
                        if (subBuilder == null) continue block13;
                        ((FieldMask.Builder)subBuilder).mergeFrom(this.updateMask_);
                        this.updateMask_ = ((FieldMask.Builder)subBuilder).buildPartial();
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
        return LoggingConfigProto.internal_static_google_logging_v2_UpdateSinkRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_UpdateSinkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSinkRequest.class, Builder.class);
    }

    @Override
    public String getSinkName() {
        Object ref = this.sinkName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.sinkName_ = s2;
        return s2;
    }

    @Override
    public ByteString getSinkNameBytes() {
        Object ref = this.sinkName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.sinkName_ = b;
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
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override
    public FieldMask getUpdateMask() {
        return this.updateMask_ == null ? FieldMask.getDefaultInstance() : this.updateMask_;
    }

    @Override
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return this.getUpdateMask();
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
        if (!this.getSinkNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.sinkName_);
        }
        if (this.sink_ != null) {
            output.writeMessage(2, this.getSink());
        }
        if (this.uniqueWriterIdentity_) {
            output.writeBool(3, this.uniqueWriterIdentity_);
        }
        if (this.updateMask_ != null) {
            output.writeMessage(4, this.getUpdateMask());
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
        if (!this.getSinkNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.sinkName_);
        }
        if (this.sink_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getSink());
        }
        if (this.uniqueWriterIdentity_) {
            size2 += CodedOutputStream.computeBoolSize(3, this.uniqueWriterIdentity_);
        }
        if (this.updateMask_ != null) {
            size2 += CodedOutputStream.computeMessageSize(4, this.getUpdateMask());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateSinkRequest)) {
            return super.equals(obj);
        }
        UpdateSinkRequest other = (UpdateSinkRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getSinkName().equals(other.getSinkName());
        boolean bl = result2 = result2 && this.hasSink() == other.hasSink();
        if (this.hasSink()) {
            result2 = result2 && this.getSink().equals(other.getSink());
        }
        result2 = result2 && this.getUniqueWriterIdentity() == other.getUniqueWriterIdentity();
        boolean bl2 = result2 = result2 && this.hasUpdateMask() == other.hasUpdateMask();
        if (this.hasUpdateMask()) {
            result2 = result2 && this.getUpdateMask().equals(other.getUpdateMask());
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
        hash = 19 * hash + UpdateSinkRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSinkName().hashCode();
        if (this.hasSink()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getSink().hashCode();
        }
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashBoolean(this.getUniqueWriterIdentity());
        if (this.hasUpdateMask()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getUpdateMask().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static UpdateSinkRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateSinkRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateSinkRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateSinkRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateSinkRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateSinkRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateSinkRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateSinkRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateSinkRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static UpdateSinkRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateSinkRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateSinkRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return UpdateSinkRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateSinkRequest prototype) {
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

    public static UpdateSinkRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateSinkRequest> parser() {
        return PARSER;
    }

    public Parser<UpdateSinkRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public UpdateSinkRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements UpdateSinkRequestOrBuilder {
        private Object sinkName_ = "";
        private LogSink sink_ = null;
        private SingleFieldBuilderV3<LogSink, LogSink.Builder, LogSinkOrBuilder> sinkBuilder_;
        private boolean uniqueWriterIdentity_;
        private FieldMask updateMask_ = null;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateSinkRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateSinkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSinkRequest.class, Builder.class);
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
            this.sinkName_ = "";
            if (this.sinkBuilder_ == null) {
                this.sink_ = null;
            } else {
                this.sink_ = null;
                this.sinkBuilder_ = null;
            }
            this.uniqueWriterIdentity_ = false;
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateSinkRequest_descriptor;
        }

        @Override
        public UpdateSinkRequest getDefaultInstanceForType() {
            return UpdateSinkRequest.getDefaultInstance();
        }

        @Override
        public UpdateSinkRequest build() {
            UpdateSinkRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public UpdateSinkRequest buildPartial() {
            UpdateSinkRequest result2 = new UpdateSinkRequest(this);
            result2.sinkName_ = this.sinkName_;
            if (this.sinkBuilder_ == null) {
                result2.sink_ = this.sink_;
            } else {
                result2.sink_ = this.sinkBuilder_.build();
            }
            result2.uniqueWriterIdentity_ = this.uniqueWriterIdentity_;
            if (this.updateMaskBuilder_ == null) {
                result2.updateMask_ = this.updateMask_;
            } else {
                result2.updateMask_ = this.updateMaskBuilder_.build();
            }
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
            if (other instanceof UpdateSinkRequest) {
                return this.mergeFrom((UpdateSinkRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(UpdateSinkRequest other) {
            if (other == UpdateSinkRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getSinkName().isEmpty()) {
                this.sinkName_ = other.sinkName_;
                this.onChanged();
            }
            if (other.hasSink()) {
                this.mergeSink(other.getSink());
            }
            if (other.getUniqueWriterIdentity()) {
                this.setUniqueWriterIdentity(other.getUniqueWriterIdentity());
            }
            if (other.hasUpdateMask()) {
                this.mergeUpdateMask(other.getUpdateMask());
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
            UpdateSinkRequest parsedMessage = null;
            try {
                parsedMessage = (UpdateSinkRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (UpdateSinkRequest)e.getUnfinishedMessage();
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
        public String getSinkName() {
            Object ref = this.sinkName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.sinkName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSinkNameBytes() {
            Object ref = this.sinkName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.sinkName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSinkName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sinkName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSinkName() {
            this.sinkName_ = UpdateSinkRequest.getDefaultInstance().getSinkName();
            this.onChanged();
            return this;
        }

        public Builder setSinkNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            UpdateSinkRequest.checkByteStringIsUtf8(value);
            this.sinkName_ = value;
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
        public boolean hasUpdateMask() {
            return this.updateMaskBuilder_ != null || this.updateMask_ != null;
        }

        @Override
        public FieldMask getUpdateMask() {
            if (this.updateMaskBuilder_ == null) {
                return this.updateMask_ == null ? FieldMask.getDefaultInstance() : this.updateMask_;
            }
            return this.updateMaskBuilder_.getMessage();
        }

        public Builder setUpdateMask(FieldMask value) {
            if (this.updateMaskBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.updateMask_ = value;
                this.onChanged();
            } else {
                this.updateMaskBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setUpdateMask(FieldMask.Builder builderForValue) {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = builderForValue.build();
                this.onChanged();
            } else {
                this.updateMaskBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeUpdateMask(FieldMask value) {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = this.updateMask_ != null ? FieldMask.newBuilder(this.updateMask_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.updateMaskBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearUpdateMask() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
                this.onChanged();
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        public FieldMask.Builder getUpdateMaskBuilder() {
            this.onChanged();
            return this.getUpdateMaskFieldBuilder().getBuilder();
        }

        @Override
        public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
            if (this.updateMaskBuilder_ != null) {
                return this.updateMaskBuilder_.getMessageOrBuilder();
            }
            return this.updateMask_ == null ? FieldMask.getDefaultInstance() : this.updateMask_;
        }

        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> getUpdateMaskFieldBuilder() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMaskBuilder_ = new SingleFieldBuilderV3(this.getUpdateMask(), this.getParentForChildren(), this.isClean());
                this.updateMask_ = null;
            }
            return this.updateMaskBuilder_;
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

