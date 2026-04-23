/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.audit;

import com.google.cloud.audit.AuditLogProto;
import com.google.cloud.audit.RequestMetadataOrBuilder;
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

public final class RequestMetadata
extends GeneratedMessageV3
implements RequestMetadataOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int CALLER_IP_FIELD_NUMBER = 1;
    private volatile Object callerIp_;
    public static final int CALLER_SUPPLIED_USER_AGENT_FIELD_NUMBER = 2;
    private volatile Object callerSuppliedUserAgent_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final RequestMetadata DEFAULT_INSTANCE = new RequestMetadata();
    private static final Parser<RequestMetadata> PARSER = new AbstractParser<RequestMetadata>(){

        @Override
        public RequestMetadata parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new RequestMetadata(input2, extensionRegistry);
        }
    };

    private RequestMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private RequestMetadata() {
        this.callerIp_ = "";
        this.callerSuppliedUserAgent_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RequestMetadata(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
                String s2;
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
                        s2 = input2.readStringRequireUtf8();
                        this.callerIp_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                s2 = input2.readStringRequireUtf8();
                this.callerSuppliedUserAgent_ = s2;
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
        return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
    }

    @Override
    public String getCallerIp() {
        Object ref = this.callerIp_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.callerIp_ = s2;
        return s2;
    }

    @Override
    public ByteString getCallerIpBytes() {
        Object ref = this.callerIp_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.callerIp_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getCallerSuppliedUserAgent() {
        Object ref = this.callerSuppliedUserAgent_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.callerSuppliedUserAgent_ = s2;
        return s2;
    }

    @Override
    public ByteString getCallerSuppliedUserAgentBytes() {
        Object ref = this.callerSuppliedUserAgent_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.callerSuppliedUserAgent_ = b;
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
        if (!this.getCallerIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.callerIp_);
        }
        if (!this.getCallerSuppliedUserAgentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.callerSuppliedUserAgent_);
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
        if (!this.getCallerIpBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.callerIp_);
        }
        if (!this.getCallerSuppliedUserAgentBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.callerSuppliedUserAgent_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestMetadata)) {
            return super.equals(obj);
        }
        RequestMetadata other = (RequestMetadata)obj;
        boolean result2 = true;
        result2 = result2 && this.getCallerIp().equals(other.getCallerIp());
        result2 = result2 && this.getCallerSuppliedUserAgent().equals(other.getCallerSuppliedUserAgent());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + RequestMetadata.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getCallerIp().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getCallerSuppliedUserAgent().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static RequestMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RequestMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RequestMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RequestMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RequestMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RequestMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RequestMetadata parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static RequestMetadata parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static RequestMetadata parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static RequestMetadata parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return RequestMetadata.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RequestMetadata prototype) {
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

    public static RequestMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestMetadata> parser() {
        return PARSER;
    }

    public Parser<RequestMetadata> getParserForType() {
        return PARSER;
    }

    @Override
    public RequestMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements RequestMetadataOrBuilder {
        private Object callerIp_ = "";
        private Object callerSuppliedUserAgent_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
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
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        @Override
        public RequestMetadata getDefaultInstanceForType() {
            return RequestMetadata.getDefaultInstance();
        }

        @Override
        public RequestMetadata build() {
            RequestMetadata result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public RequestMetadata buildPartial() {
            RequestMetadata result2 = new RequestMetadata(this);
            result2.callerIp_ = this.callerIp_;
            result2.callerSuppliedUserAgent_ = this.callerSuppliedUserAgent_;
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
            if (other instanceof RequestMetadata) {
                return this.mergeFrom((RequestMetadata)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(RequestMetadata other) {
            if (other == RequestMetadata.getDefaultInstance()) {
                return this;
            }
            if (!other.getCallerIp().isEmpty()) {
                this.callerIp_ = other.callerIp_;
                this.onChanged();
            }
            if (!other.getCallerSuppliedUserAgent().isEmpty()) {
                this.callerSuppliedUserAgent_ = other.callerSuppliedUserAgent_;
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
            RequestMetadata parsedMessage = null;
            try {
                parsedMessage = (RequestMetadata)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (RequestMetadata)e.getUnfinishedMessage();
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
        public String getCallerIp() {
            Object ref = this.callerIp_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.callerIp_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getCallerIpBytes() {
            Object ref = this.callerIp_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.callerIp_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setCallerIp(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.callerIp_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearCallerIp() {
            this.callerIp_ = RequestMetadata.getDefaultInstance().getCallerIp();
            this.onChanged();
            return this;
        }

        public Builder setCallerIpBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            RequestMetadata.checkByteStringIsUtf8(value);
            this.callerIp_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getCallerSuppliedUserAgent() {
            Object ref = this.callerSuppliedUserAgent_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.callerSuppliedUserAgent_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getCallerSuppliedUserAgentBytes() {
            Object ref = this.callerSuppliedUserAgent_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.callerSuppliedUserAgent_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setCallerSuppliedUserAgent(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.callerSuppliedUserAgent_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearCallerSuppliedUserAgent() {
            this.callerSuppliedUserAgent_ = RequestMetadata.getDefaultInstance().getCallerSuppliedUserAgent();
            this.onChanged();
            return this;
        }

        public Builder setCallerSuppliedUserAgentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            RequestMetadata.checkByteStringIsUtf8(value);
            this.callerSuppliedUserAgent_ = value;
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

