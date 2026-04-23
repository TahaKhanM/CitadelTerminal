/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
import com.google.logging.v2.UpdateExclusionRequestOrBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UpdateExclusionRequest
extends GeneratedMessageV3
implements UpdateExclusionRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int EXCLUSION_FIELD_NUMBER = 2;
    private LogExclusion exclusion_;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    private FieldMask updateMask_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final UpdateExclusionRequest DEFAULT_INSTANCE = new UpdateExclusionRequest();
    private static final Parser<UpdateExclusionRequest> PARSER = new AbstractParser<UpdateExclusionRequest>(){

        @Override
        public UpdateExclusionRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new UpdateExclusionRequest(input2, extensionRegistry);
        }
    };

    private UpdateExclusionRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateExclusionRequest() {
        this.name_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateExclusionRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.name_ = s2;
                        continue block12;
                    }
                    case 18: {
                        GeneratedMessageV3.Builder subBuilder = null;
                        if (this.exclusion_ != null) {
                            subBuilder = this.exclusion_.toBuilder();
                        }
                        this.exclusion_ = input2.readMessage(LogExclusion.parser(), extensionRegistry);
                        if (subBuilder == null) continue block12;
                        ((LogExclusion.Builder)subBuilder).mergeFrom(this.exclusion_);
                        this.exclusion_ = ((LogExclusion.Builder)subBuilder).buildPartial();
                        continue block12;
                    }
                    case 26: {
                        GeneratedMessageV3.Builder subBuilder = null;
                        if (this.updateMask_ != null) {
                            subBuilder = this.updateMask_.toBuilder();
                        }
                        this.updateMask_ = input2.readMessage(FieldMask.parser(), extensionRegistry);
                        if (subBuilder == null) continue block12;
                        ((FieldMask.Builder)subBuilder).mergeFrom(this.updateMask_);
                        this.updateMask_ = ((FieldMask.Builder)subBuilder).buildPartial();
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
        return LoggingConfigProto.internal_static_google_logging_v2_UpdateExclusionRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_UpdateExclusionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateExclusionRequest.class, Builder.class);
    }

    @Override
    public String getName() {
        Object ref = this.name_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.name_ = s2;
        return s2;
    }

    @Override
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        if (this.exclusion_ != null) {
            output.writeMessage(2, this.getExclusion());
        }
        if (this.updateMask_ != null) {
            output.writeMessage(3, this.getUpdateMask());
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
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.exclusion_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getExclusion());
        }
        if (this.updateMask_ != null) {
            size2 += CodedOutputStream.computeMessageSize(3, this.getUpdateMask());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateExclusionRequest)) {
            return super.equals(obj);
        }
        UpdateExclusionRequest other = (UpdateExclusionRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        boolean bl = result2 = result2 && this.hasExclusion() == other.hasExclusion();
        if (this.hasExclusion()) {
            result2 = result2 && this.getExclusion().equals(other.getExclusion());
        }
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
        hash = 19 * hash + UpdateExclusionRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        if (this.hasExclusion()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getExclusion().hashCode();
        }
        if (this.hasUpdateMask()) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getUpdateMask().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static UpdateExclusionRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateExclusionRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateExclusionRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateExclusionRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateExclusionRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateExclusionRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateExclusionRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateExclusionRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateExclusionRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static UpdateExclusionRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateExclusionRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateExclusionRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return UpdateExclusionRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateExclusionRequest prototype) {
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

    public static UpdateExclusionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateExclusionRequest> parser() {
        return PARSER;
    }

    public Parser<UpdateExclusionRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public UpdateExclusionRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements UpdateExclusionRequestOrBuilder {
        private Object name_ = "";
        private LogExclusion exclusion_ = null;
        private SingleFieldBuilderV3<LogExclusion, LogExclusion.Builder, LogExclusionOrBuilder> exclusionBuilder_;
        private FieldMask updateMask_ = null;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateExclusionRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateExclusionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateExclusionRequest.class, Builder.class);
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
            this.name_ = "";
            if (this.exclusionBuilder_ == null) {
                this.exclusion_ = null;
            } else {
                this.exclusion_ = null;
                this.exclusionBuilder_ = null;
            }
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
            return LoggingConfigProto.internal_static_google_logging_v2_UpdateExclusionRequest_descriptor;
        }

        @Override
        public UpdateExclusionRequest getDefaultInstanceForType() {
            return UpdateExclusionRequest.getDefaultInstance();
        }

        @Override
        public UpdateExclusionRequest build() {
            UpdateExclusionRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public UpdateExclusionRequest buildPartial() {
            UpdateExclusionRequest result2 = new UpdateExclusionRequest(this);
            result2.name_ = this.name_;
            if (this.exclusionBuilder_ == null) {
                result2.exclusion_ = this.exclusion_;
            } else {
                result2.exclusion_ = this.exclusionBuilder_.build();
            }
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
            if (other instanceof UpdateExclusionRequest) {
                return this.mergeFrom((UpdateExclusionRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(UpdateExclusionRequest other) {
            if (other == UpdateExclusionRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (other.hasExclusion()) {
                this.mergeExclusion(other.getExclusion());
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
            UpdateExclusionRequest parsedMessage = null;
            try {
                parsedMessage = (UpdateExclusionRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (UpdateExclusionRequest)e.getUnfinishedMessage();
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
        public String getName() {
            Object ref = this.name_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.name_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = UpdateExclusionRequest.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            UpdateExclusionRequest.checkByteStringIsUtf8(value);
            this.name_ = value;
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

