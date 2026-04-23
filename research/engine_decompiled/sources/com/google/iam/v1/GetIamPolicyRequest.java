/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.GetIamPolicyRequestOrBuilder;
import com.google.iam.v1.IamPolicyProto;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class GetIamPolicyRequest
extends GeneratedMessageV3
implements GetIamPolicyRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RESOURCE_FIELD_NUMBER = 1;
    private volatile Object resource_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final GetIamPolicyRequest DEFAULT_INSTANCE = new GetIamPolicyRequest();
    private static final Parser<GetIamPolicyRequest> PARSER = new AbstractParser<GetIamPolicyRequest>(){

        @Override
        public GetIamPolicyRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new GetIamPolicyRequest(input2, extensionRegistry);
        }
    };

    private GetIamPolicyRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GetIamPolicyRequest() {
        this.resource_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private GetIamPolicyRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                String s2 = input2.readStringRequireUtf8();
                this.resource_ = s2;
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
        return IamPolicyProto.internal_static_google_iam_v1_GetIamPolicyRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IamPolicyProto.internal_static_google_iam_v1_GetIamPolicyRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(GetIamPolicyRequest.class, Builder.class);
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
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIamPolicyRequest)) {
            return super.equals(obj);
        }
        GetIamPolicyRequest other = (GetIamPolicyRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getResource().equals(other.getResource());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + GetIamPolicyRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getResource().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static GetIamPolicyRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetIamPolicyRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetIamPolicyRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetIamPolicyRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetIamPolicyRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetIamPolicyRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetIamPolicyRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static GetIamPolicyRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static GetIamPolicyRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static GetIamPolicyRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static GetIamPolicyRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static GetIamPolicyRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return GetIamPolicyRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetIamPolicyRequest prototype) {
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

    public static GetIamPolicyRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetIamPolicyRequest> parser() {
        return PARSER;
    }

    public Parser<GetIamPolicyRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public GetIamPolicyRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements GetIamPolicyRequestOrBuilder {
        private Object resource_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return IamPolicyProto.internal_static_google_iam_v1_GetIamPolicyRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IamPolicyProto.internal_static_google_iam_v1_GetIamPolicyRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(GetIamPolicyRequest.class, Builder.class);
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
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return IamPolicyProto.internal_static_google_iam_v1_GetIamPolicyRequest_descriptor;
        }

        @Override
        public GetIamPolicyRequest getDefaultInstanceForType() {
            return GetIamPolicyRequest.getDefaultInstance();
        }

        @Override
        public GetIamPolicyRequest build() {
            GetIamPolicyRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public GetIamPolicyRequest buildPartial() {
            GetIamPolicyRequest result2 = new GetIamPolicyRequest(this);
            result2.resource_ = this.resource_;
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
            if (other instanceof GetIamPolicyRequest) {
                return this.mergeFrom((GetIamPolicyRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(GetIamPolicyRequest other) {
            if (other == GetIamPolicyRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getResource().isEmpty()) {
                this.resource_ = other.resource_;
                this.onChanged();
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
            GetIamPolicyRequest parsedMessage = null;
            try {
                parsedMessage = (GetIamPolicyRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (GetIamPolicyRequest)e.getUnfinishedMessage();
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
            this.resource_ = GetIamPolicyRequest.getDefaultInstance().getResource();
            this.onChanged();
            return this;
        }

        public Builder setResourceBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            GetIamPolicyRequest.checkByteStringIsUtf8(value);
            this.resource_ = value;
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

