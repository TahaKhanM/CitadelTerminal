/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.ErrorDetailsProto;
import com.google.rpc.RetryInfoOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RetryInfo
extends GeneratedMessageV3
implements RetryInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RETRY_DELAY_FIELD_NUMBER = 1;
    private Duration retryDelay_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final RetryInfo DEFAULT_INSTANCE = new RetryInfo();
    private static final Parser<RetryInfo> PARSER = new AbstractParser<RetryInfo>(){

        @Override
        public RetryInfo parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new RetryInfo(input2, extensionRegistry);
        }
    };

    private RetryInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private RetryInfo() {
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RetryInfo(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block10;
                        done = true;
                        continue block10;
                    }
                    case 10: 
                }
                Duration.Builder subBuilder = null;
                if (this.retryDelay_ != null) {
                    subBuilder = this.retryDelay_.toBuilder();
                }
                this.retryDelay_ = input2.readMessage(Duration.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                subBuilder.mergeFrom(this.retryDelay_);
                this.retryDelay_ = subBuilder.buildPartial();
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
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
    }

    @Override
    public boolean hasRetryDelay() {
        return this.retryDelay_ != null;
    }

    @Override
    public Duration getRetryDelay() {
        return this.retryDelay_ == null ? Duration.getDefaultInstance() : this.retryDelay_;
    }

    @Override
    public DurationOrBuilder getRetryDelayOrBuilder() {
        return this.getRetryDelay();
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
        if (this.retryDelay_ != null) {
            output.writeMessage(1, this.getRetryDelay());
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
        if (this.retryDelay_ != null) {
            size2 += CodedOutputStream.computeMessageSize(1, this.getRetryDelay());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetryInfo)) {
            return super.equals(obj);
        }
        RetryInfo other = (RetryInfo)obj;
        boolean result2 = true;
        boolean bl = result2 = result2 && this.hasRetryDelay() == other.hasRetryDelay();
        if (this.hasRetryDelay()) {
            result2 = result2 && this.getRetryDelay().equals(other.getRetryDelay());
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
        hash = 19 * hash + RetryInfo.getDescriptor().hashCode();
        if (this.hasRetryDelay()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getRetryDelay().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static RetryInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RetryInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RetryInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RetryInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RetryInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RetryInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RetryInfo parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static RetryInfo parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static RetryInfo parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static RetryInfo parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static RetryInfo parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static RetryInfo parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return RetryInfo.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RetryInfo prototype) {
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

    public static RetryInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RetryInfo> parser() {
        return PARSER;
    }

    public Parser<RetryInfo> getParserForType() {
        return PARSER;
    }

    @Override
    public RetryInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements RetryInfoOrBuilder {
        private Duration retryDelay_ = null;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> retryDelayBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
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
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        @Override
        public RetryInfo getDefaultInstanceForType() {
            return RetryInfo.getDefaultInstance();
        }

        @Override
        public RetryInfo build() {
            RetryInfo result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public RetryInfo buildPartial() {
            RetryInfo result2 = new RetryInfo(this);
            if (this.retryDelayBuilder_ == null) {
                result2.retryDelay_ = this.retryDelay_;
            } else {
                result2.retryDelay_ = this.retryDelayBuilder_.build();
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
            if (other instanceof RetryInfo) {
                return this.mergeFrom((RetryInfo)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(RetryInfo other) {
            if (other == RetryInfo.getDefaultInstance()) {
                return this;
            }
            if (other.hasRetryDelay()) {
                this.mergeRetryDelay(other.getRetryDelay());
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
            RetryInfo parsedMessage = null;
            try {
                parsedMessage = (RetryInfo)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (RetryInfo)e.getUnfinishedMessage();
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
        public boolean hasRetryDelay() {
            return this.retryDelayBuilder_ != null || this.retryDelay_ != null;
        }

        @Override
        public Duration getRetryDelay() {
            if (this.retryDelayBuilder_ == null) {
                return this.retryDelay_ == null ? Duration.getDefaultInstance() : this.retryDelay_;
            }
            return this.retryDelayBuilder_.getMessage();
        }

        public Builder setRetryDelay(Duration value) {
            if (this.retryDelayBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.retryDelay_ = value;
                this.onChanged();
            } else {
                this.retryDelayBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setRetryDelay(Duration.Builder builderForValue) {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = builderForValue.build();
                this.onChanged();
            } else {
                this.retryDelayBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeRetryDelay(Duration value) {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = this.retryDelay_ != null ? Duration.newBuilder(this.retryDelay_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.retryDelayBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearRetryDelay() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
                this.onChanged();
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getRetryDelayBuilder() {
            this.onChanged();
            return this.getRetryDelayFieldBuilder().getBuilder();
        }

        @Override
        public DurationOrBuilder getRetryDelayOrBuilder() {
            if (this.retryDelayBuilder_ != null) {
                return this.retryDelayBuilder_.getMessageOrBuilder();
            }
            return this.retryDelay_ == null ? Duration.getDefaultInstance() : this.retryDelay_;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getRetryDelayFieldBuilder() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelayBuilder_ = new SingleFieldBuilderV3(this.getRetryDelay(), this.getParentForChildren(), this.isClean());
                this.retryDelay_ = null;
            }
            return this.retryDelayBuilder_;
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

