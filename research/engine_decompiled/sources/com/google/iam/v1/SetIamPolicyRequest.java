/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.IamPolicyProto;
import com.google.iam.v1.Policy;
import com.google.iam.v1.PolicyOrBuilder;
import com.google.iam.v1.SetIamPolicyRequestOrBuilder;
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

public final class SetIamPolicyRequest
extends GeneratedMessageV3
implements SetIamPolicyRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RESOURCE_FIELD_NUMBER = 1;
    private volatile Object resource_;
    public static final int POLICY_FIELD_NUMBER = 2;
    private Policy policy_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final SetIamPolicyRequest DEFAULT_INSTANCE = new SetIamPolicyRequest();
    private static final Parser<SetIamPolicyRequest> PARSER = new AbstractParser<SetIamPolicyRequest>(){

        @Override
        public SetIamPolicyRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new SetIamPolicyRequest(input2, extensionRegistry);
        }
    };

    private SetIamPolicyRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private SetIamPolicyRequest() {
        this.resource_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SetIamPolicyRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block11;
                        done = true;
                        continue block11;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.resource_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                Policy.Builder subBuilder = null;
                if (this.policy_ != null) {
                    subBuilder = this.policy_.toBuilder();
                }
                this.policy_ = input2.readMessage(Policy.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                subBuilder.mergeFrom(this.policy_);
                this.policy_ = subBuilder.buildPartial();
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
        return IamPolicyProto.internal_static_google_iam_v1_SetIamPolicyRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IamPolicyProto.internal_static_google_iam_v1_SetIamPolicyRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(SetIamPolicyRequest.class, Builder.class);
    }

    @Override
    public String getResource() {
        Object ref = this.resource_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.resource_ = s2;
        return s2;
    }

    @Override
    public ByteString getResourceBytes() {
        Object ref = this.resource_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.resource_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasPolicy() {
        return this.policy_ != null;
    }

    @Override
    public Policy getPolicy() {
        return this.policy_ == null ? Policy.getDefaultInstance() : this.policy_;
    }

    @Override
    public PolicyOrBuilder getPolicyOrBuilder() {
        return this.getPolicy();
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
        if (!this.getResourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.resource_);
        }
        if (this.policy_ != null) {
            output.writeMessage(2, this.getPolicy());
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
        if (!this.getResourceBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.resource_);
        }
        if (this.policy_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getPolicy());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetIamPolicyRequest)) {
            return super.equals(obj);
        }
        SetIamPolicyRequest other = (SetIamPolicyRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getResource().equals(other.getResource());
        boolean bl = result2 = result2 && this.hasPolicy() == other.hasPolicy();
        if (this.hasPolicy()) {
            result2 = result2 && this.getPolicy().equals(other.getPolicy());
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
        hash = 19 * hash + SetIamPolicyRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getResource().hashCode();
        if (this.hasPolicy()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getPolicy().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static SetIamPolicyRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SetIamPolicyRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SetIamPolicyRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SetIamPolicyRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SetIamPolicyRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SetIamPolicyRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SetIamPolicyRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static SetIamPolicyRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static SetIamPolicyRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static SetIamPolicyRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static SetIamPolicyRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static SetIamPolicyRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return SetIamPolicyRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SetIamPolicyRequest prototype) {
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

    public static SetIamPolicyRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SetIamPolicyRequest> parser() {
        return PARSER;
    }

    public Parser<SetIamPolicyRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public SetIamPolicyRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements SetIamPolicyRequestOrBuilder {
        private Object resource_ = "";
        private Policy policy_ = null;
        private SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> policyBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return IamPolicyProto.internal_static_google_iam_v1_SetIamPolicyRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IamPolicyProto.internal_static_google_iam_v1_SetIamPolicyRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(SetIamPolicyRequest.class, Builder.class);
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
            this.resource_ = "";
            if (this.policyBuilder_ == null) {
                this.policy_ = null;
            } else {
                this.policy_ = null;
                this.policyBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return IamPolicyProto.internal_static_google_iam_v1_SetIamPolicyRequest_descriptor;
        }

        @Override
        public SetIamPolicyRequest getDefaultInstanceForType() {
            return SetIamPolicyRequest.getDefaultInstance();
        }

        @Override
        public SetIamPolicyRequest build() {
            SetIamPolicyRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public SetIamPolicyRequest buildPartial() {
            SetIamPolicyRequest result2 = new SetIamPolicyRequest(this);
            result2.resource_ = this.resource_;
            if (this.policyBuilder_ == null) {
                result2.policy_ = this.policy_;
            } else {
                result2.policy_ = this.policyBuilder_.build();
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
            if (other instanceof SetIamPolicyRequest) {
                return this.mergeFrom((SetIamPolicyRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(SetIamPolicyRequest other) {
            if (other == SetIamPolicyRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getResource().isEmpty()) {
                this.resource_ = other.resource_;
                this.onChanged();
            }
            if (other.hasPolicy()) {
                this.mergePolicy(other.getPolicy());
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
            SetIamPolicyRequest parsedMessage = null;
            try {
                parsedMessage = (SetIamPolicyRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (SetIamPolicyRequest)e.getUnfinishedMessage();
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
        public String getResource() {
            Object ref = this.resource_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.resource_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getResourceBytes() {
            Object ref = this.resource_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.resource_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setResource(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.resource_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearResource() {
            this.resource_ = SetIamPolicyRequest.getDefaultInstance().getResource();
            this.onChanged();
            return this;
        }

        public Builder setResourceBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            SetIamPolicyRequest.checkByteStringIsUtf8(value);
            this.resource_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasPolicy() {
            return this.policyBuilder_ != null || this.policy_ != null;
        }

        @Override
        public Policy getPolicy() {
            if (this.policyBuilder_ == null) {
                return this.policy_ == null ? Policy.getDefaultInstance() : this.policy_;
            }
            return this.policyBuilder_.getMessage();
        }

        public Builder setPolicy(Policy value) {
            if (this.policyBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.policy_ = value;
                this.onChanged();
            } else {
                this.policyBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setPolicy(Policy.Builder builderForValue) {
            if (this.policyBuilder_ == null) {
                this.policy_ = builderForValue.build();
                this.onChanged();
            } else {
                this.policyBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergePolicy(Policy value) {
            if (this.policyBuilder_ == null) {
                this.policy_ = this.policy_ != null ? Policy.newBuilder(this.policy_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.policyBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearPolicy() {
            if (this.policyBuilder_ == null) {
                this.policy_ = null;
                this.onChanged();
            } else {
                this.policy_ = null;
                this.policyBuilder_ = null;
            }
            return this;
        }

        public Policy.Builder getPolicyBuilder() {
            this.onChanged();
            return this.getPolicyFieldBuilder().getBuilder();
        }

        @Override
        public PolicyOrBuilder getPolicyOrBuilder() {
            if (this.policyBuilder_ != null) {
                return this.policyBuilder_.getMessageOrBuilder();
            }
            return this.policy_ == null ? Policy.getDefaultInstance() : this.policy_;
        }

        private SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> getPolicyFieldBuilder() {
            if (this.policyBuilder_ == null) {
                this.policyBuilder_ = new SingleFieldBuilderV3(this.getPolicy(), this.getParentForChildren(), this.isClean());
                this.policy_ = null;
            }
            return this.policyBuilder_;
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

