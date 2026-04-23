/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.CreateExclusionRequestOrBuilder;
import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class CreateExclusionRequest
extends GeneratedMessageV3
implements CreateExclusionRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PARENT_FIELD_NUMBER = 1;
    private volatile Object parent_;
    public static final int EXCLUSION_FIELD_NUMBER = 2;
    private LogExclusion exclusion_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final CreateExclusionRequest DEFAULT_INSTANCE = new CreateExclusionRequest();
    private static final Parser<CreateExclusionRequest> PARSER = new AbstractParser<CreateExclusionRequest>(){

        @Override
        public CreateExclusionRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new CreateExclusionRequest(input2, extensionRegistry);
        }
    };

    private CreateExclusionRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CreateExclusionRequest() {
        this.parent_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateExclusionRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block11;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.parent_ = s2;
                        continue block11;
                    }
                    case 18: {
                        LogExclusion.Builder subBuilder = null;
                        if (this.exclusion_ != null) {
                            subBuilder = this.exclusion_.toBuilder();
                        }
                        this.exclusion_ = input2.readMessage(LogExclusion.parser(), extensionRegistry);
                        if (subBuilder == null) continue block11;
                        subBuilder.mergeFrom(this.exclusion_);
                        this.exclusion_ = subBuilder.buildPartial();
                        continue block11;
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
        return LoggingConfigProto.internal_static_google_logging_v2_CreateExclusionRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_CreateExclusionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateExclusionRequest.class, Builder.class);
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
    public boolean hasExclusion() {
        return this.exclusion_ != null;
    }

    @Override
    public LogExclusion getExclusion() {
        return this.exclusion_ == null ? LogExclusion.getDefaultInstance() : this.exclusion_;
    }

    @Override
    public LogExclusionOrBuilder getExclusionOrBuilder() {
        return this.getExclusion();
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
        if (this.exclusion_ != null) {
            output.writeMessage(2, this.getExclusion());
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
        if (this.exclusion_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getExclusion());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateExclusionRequest)) {
            return super.equals(obj);
        }
        CreateExclusionRequest other = (CreateExclusionRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getParent().equals(other.getParent());
        boolean bl = result2 = result2 && this.hasExclusion() == other.hasExclusion();
        if (this.hasExclusion()) {
            result2 = result2 && this.getExclusion().equals(other.getExclusion());
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
        hash = 19 * hash + CreateExclusionRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getParent().hashCode();
        if (this.hasExclusion()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getExclusion().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static CreateExclusionRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateExclusionRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateExclusionRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateExclusionRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateExclusionRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CreateExclusionRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CreateExclusionRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CreateExclusionRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CreateExclusionRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static CreateExclusionRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CreateExclusionRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CreateExclusionRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return CreateExclusionRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateExclusionRequest prototype) {
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

    public static CreateExclusionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateExclusionRequest> parser() {
        return PARSER;
    }

    public Parser<CreateExclusionRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public CreateExclusionRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements CreateExclusionRequestOrBuilder {
        private Object parent_ = "";
        private LogExclusion exclusion_ = null;
        private SingleFieldBuilderV3<LogExclusion, LogExclusion.Builder, LogExclusionOrBuilder> exclusionBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateExclusionRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateExclusionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateExclusionRequest.class, Builder.class);
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
            if (this.exclusionBuilder_ == null) {
                this.exclusion_ = null;
            } else {
                this.exclusion_ = null;
                this.exclusionBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_CreateExclusionRequest_descriptor;
        }

        @Override
        public CreateExclusionRequest getDefaultInstanceForType() {
            return CreateExclusionRequest.getDefaultInstance();
        }

        @Override
        public CreateExclusionRequest build() {
            CreateExclusionRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public CreateExclusionRequest buildPartial() {
            CreateExclusionRequest result2 = new CreateExclusionRequest(this);
            result2.parent_ = this.parent_;
            if (this.exclusionBuilder_ == null) {
                result2.exclusion_ = this.exclusion_;
            } else {
                result2.exclusion_ = this.exclusionBuilder_.build();
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
            if (other instanceof CreateExclusionRequest) {
                return this.mergeFrom((CreateExclusionRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(CreateExclusionRequest other) {
            if (other == CreateExclusionRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getParent().isEmpty()) {
                this.parent_ = other.parent_;
                this.onChanged();
            }
            if (other.hasExclusion()) {
                this.mergeExclusion(other.getExclusion());
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
            CreateExclusionRequest parsedMessage = null;
            try {
                parsedMessage = (CreateExclusionRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (CreateExclusionRequest)e.getUnfinishedMessage();
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
            this.parent_ = CreateExclusionRequest.getDefaultInstance().getParent();
            this.onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            CreateExclusionRequest.checkByteStringIsUtf8(value);
            this.parent_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasExclusion() {
            return this.exclusionBuilder_ != null || this.exclusion_ != null;
        }

        @Override
        public LogExclusion getExclusion() {
            if (this.exclusionBuilder_ == null) {
                return this.exclusion_ == null ? LogExclusion.getDefaultInstance() : this.exclusion_;
            }
            return this.exclusionBuilder_.getMessage();
        }

        public Builder setExclusion(LogExclusion value) {
            if (this.exclusionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.exclusion_ = value;
                this.onChanged();
            } else {
                this.exclusionBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setExclusion(LogExclusion.Builder builderForValue) {
            if (this.exclusionBuilder_ == null) {
                this.exclusion_ = builderForValue.build();
                this.onChanged();
            } else {
                this.exclusionBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeExclusion(LogExclusion value) {
            if (this.exclusionBuilder_ == null) {
                this.exclusion_ = this.exclusion_ != null ? LogExclusion.newBuilder(this.exclusion_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.exclusionBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearExclusion() {
            if (this.exclusionBuilder_ == null) {
                this.exclusion_ = null;
                this.onChanged();
            } else {
                this.exclusion_ = null;
                this.exclusionBuilder_ = null;
            }
            return this;
        }

        public LogExclusion.Builder getExclusionBuilder() {
            this.onChanged();
            return this.getExclusionFieldBuilder().getBuilder();
        }

        @Override
        public LogExclusionOrBuilder getExclusionOrBuilder() {
            if (this.exclusionBuilder_ != null) {
                return this.exclusionBuilder_.getMessageOrBuilder();
            }
            return this.exclusion_ == null ? LogExclusion.getDefaultInstance() : this.exclusion_;
        }

        private SingleFieldBuilderV3<LogExclusion, LogExclusion.Builder, LogExclusionOrBuilder> getExclusionFieldBuilder() {
            if (this.exclusionBuilder_ == null) {
                this.exclusionBuilder_ = new SingleFieldBuilderV3(this.getExclusion(), this.getParentForChildren(), this.isClean());
                this.exclusion_ = null;
            }
            return this.exclusionBuilder_;
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

