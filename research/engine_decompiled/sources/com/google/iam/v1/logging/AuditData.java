/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1.logging;

import com.google.iam.v1.PolicyDelta;
import com.google.iam.v1.PolicyDeltaOrBuilder;
import com.google.iam.v1.logging.AuditDataOrBuilder;
import com.google.iam.v1.logging.AuditDataProto;
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

public final class AuditData
extends GeneratedMessageV3
implements AuditDataOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int POLICY_DELTA_FIELD_NUMBER = 2;
    private PolicyDelta policyDelta_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final AuditData DEFAULT_INSTANCE = new AuditData();
    private static final Parser<AuditData> PARSER = new AbstractParser<AuditData>(){

        @Override
        public AuditData parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new AuditData(input2, extensionRegistry);
        }
    };

    private AuditData(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private AuditData() {
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuditData(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 18: 
                }
                PolicyDelta.Builder subBuilder = null;
                if (this.policyDelta_ != null) {
                    subBuilder = this.policyDelta_.toBuilder();
                }
                this.policyDelta_ = input2.readMessage(PolicyDelta.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                subBuilder.mergeFrom(this.policyDelta_);
                this.policyDelta_ = subBuilder.buildPartial();
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
        return AuditDataProto.internal_static_google_iam_v1_logging_AuditData_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditDataProto.internal_static_google_iam_v1_logging_AuditData_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditData.class, Builder.class);
    }

    @Override
    public boolean hasPolicyDelta() {
        return this.policyDelta_ != null;
    }

    @Override
    public PolicyDelta getPolicyDelta() {
        return this.policyDelta_ == null ? PolicyDelta.getDefaultInstance() : this.policyDelta_;
    }

    @Override
    public PolicyDeltaOrBuilder getPolicyDeltaOrBuilder() {
        return this.getPolicyDelta();
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
        if (this.policyDelta_ != null) {
            output.writeMessage(2, this.getPolicyDelta());
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
        if (this.policyDelta_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getPolicyDelta());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuditData)) {
            return super.equals(obj);
        }
        AuditData other = (AuditData)obj;
        boolean result2 = true;
        boolean bl = result2 = result2 && this.hasPolicyDelta() == other.hasPolicyDelta();
        if (this.hasPolicyDelta()) {
            result2 = result2 && this.getPolicyDelta().equals(other.getPolicyDelta());
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
        hash = 19 * hash + AuditData.getDescriptor().hashCode();
        if (this.hasPolicyDelta()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getPolicyDelta().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static AuditData parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditData parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditData parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditData parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditData parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuditData parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuditData parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static AuditData parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuditData parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuditData parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return AuditData.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuditData prototype) {
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

    public static AuditData getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditData> parser() {
        return PARSER;
    }

    public Parser<AuditData> getParserForType() {
        return PARSER;
    }

    @Override
    public AuditData getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuditDataOrBuilder {
        private PolicyDelta policyDelta_ = null;
        private SingleFieldBuilderV3<PolicyDelta, PolicyDelta.Builder, PolicyDeltaOrBuilder> policyDeltaBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditDataProto.internal_static_google_iam_v1_logging_AuditData_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditDataProto.internal_static_google_iam_v1_logging_AuditData_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditData.class, Builder.class);
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
            if (this.policyDeltaBuilder_ == null) {
                this.policyDelta_ = null;
            } else {
                this.policyDelta_ = null;
                this.policyDeltaBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditDataProto.internal_static_google_iam_v1_logging_AuditData_descriptor;
        }

        @Override
        public AuditData getDefaultInstanceForType() {
            return AuditData.getDefaultInstance();
        }

        @Override
        public AuditData build() {
            AuditData result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public AuditData buildPartial() {
            AuditData result2 = new AuditData(this);
            if (this.policyDeltaBuilder_ == null) {
                result2.policyDelta_ = this.policyDelta_;
            } else {
                result2.policyDelta_ = this.policyDeltaBuilder_.build();
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
            if (other instanceof AuditData) {
                return this.mergeFrom((AuditData)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(AuditData other) {
            if (other == AuditData.getDefaultInstance()) {
                return this;
            }
            if (other.hasPolicyDelta()) {
                this.mergePolicyDelta(other.getPolicyDelta());
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
            AuditData parsedMessage = null;
            try {
                parsedMessage = (AuditData)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (AuditData)e.getUnfinishedMessage();
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
        public boolean hasPolicyDelta() {
            return this.policyDeltaBuilder_ != null || this.policyDelta_ != null;
        }

        @Override
        public PolicyDelta getPolicyDelta() {
            if (this.policyDeltaBuilder_ == null) {
                return this.policyDelta_ == null ? PolicyDelta.getDefaultInstance() : this.policyDelta_;
            }
            return this.policyDeltaBuilder_.getMessage();
        }

        public Builder setPolicyDelta(PolicyDelta value) {
            if (this.policyDeltaBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.policyDelta_ = value;
                this.onChanged();
            } else {
                this.policyDeltaBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setPolicyDelta(PolicyDelta.Builder builderForValue) {
            if (this.policyDeltaBuilder_ == null) {
                this.policyDelta_ = builderForValue.build();
                this.onChanged();
            } else {
                this.policyDeltaBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergePolicyDelta(PolicyDelta value) {
            if (this.policyDeltaBuilder_ == null) {
                this.policyDelta_ = this.policyDelta_ != null ? PolicyDelta.newBuilder(this.policyDelta_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.policyDeltaBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearPolicyDelta() {
            if (this.policyDeltaBuilder_ == null) {
                this.policyDelta_ = null;
                this.onChanged();
            } else {
                this.policyDelta_ = null;
                this.policyDeltaBuilder_ = null;
            }
            return this;
        }

        public PolicyDelta.Builder getPolicyDeltaBuilder() {
            this.onChanged();
            return this.getPolicyDeltaFieldBuilder().getBuilder();
        }

        @Override
        public PolicyDeltaOrBuilder getPolicyDeltaOrBuilder() {
            if (this.policyDeltaBuilder_ != null) {
                return this.policyDeltaBuilder_.getMessageOrBuilder();
            }
            return this.policyDelta_ == null ? PolicyDelta.getDefaultInstance() : this.policyDelta_;
        }

        private SingleFieldBuilderV3<PolicyDelta, PolicyDelta.Builder, PolicyDeltaOrBuilder> getPolicyDeltaFieldBuilder() {
            if (this.policyDeltaBuilder_ == null) {
                this.policyDeltaBuilder_ = new SingleFieldBuilderV3(this.getPolicyDelta(), this.getParentForChildren(), this.isClean());
                this.policyDelta_ = null;
            }
            return this.policyDeltaBuilder_;
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

