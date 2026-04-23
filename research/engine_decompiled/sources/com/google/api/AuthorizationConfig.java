/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthorizationConfigOrBuilder;
import com.google.api.AuthorizationConfigProto;
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

public final class AuthorizationConfig
extends GeneratedMessageV3
implements AuthorizationConfigOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PROVIDER_FIELD_NUMBER = 1;
    private volatile Object provider_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final AuthorizationConfig DEFAULT_INSTANCE = new AuthorizationConfig();
    private static final Parser<AuthorizationConfig> PARSER = new AbstractParser<AuthorizationConfig>(){

        @Override
        public AuthorizationConfig parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new AuthorizationConfig(input2, extensionRegistry);
        }
    };

    private AuthorizationConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private AuthorizationConfig() {
        this.provider_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthorizationConfig(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.provider_ = s2;
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
        return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationConfig.class, Builder.class);
    }

    @Override
    public String getProvider() {
        Object ref = this.provider_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.provider_ = s2;
        return s2;
    }

    @Override
    public ByteString getProviderBytes() {
        Object ref = this.provider_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.provider_ = b;
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
        if (!this.getProviderBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.provider_);
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
        if (!this.getProviderBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.provider_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthorizationConfig)) {
            return super.equals(obj);
        }
        AuthorizationConfig other = (AuthorizationConfig)obj;
        boolean result2 = true;
        result2 = result2 && this.getProvider().equals(other.getProvider());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + AuthorizationConfig.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getProvider().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static AuthorizationConfig parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthorizationConfig parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthorizationConfig parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthorizationConfig parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthorizationConfig parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthorizationConfig parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthorizationConfig parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthorizationConfig parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthorizationConfig parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static AuthorizationConfig parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthorizationConfig parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthorizationConfig parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return AuthorizationConfig.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthorizationConfig prototype) {
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

    public static AuthorizationConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthorizationConfig> parser() {
        return PARSER;
    }

    public Parser<AuthorizationConfig> getParserForType() {
        return PARSER;
    }

    @Override
    public AuthorizationConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuthorizationConfigOrBuilder {
        private Object provider_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationConfig.class, Builder.class);
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
            this.provider_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
        }

        @Override
        public AuthorizationConfig getDefaultInstanceForType() {
            return AuthorizationConfig.getDefaultInstance();
        }

        @Override
        public AuthorizationConfig build() {
            AuthorizationConfig result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public AuthorizationConfig buildPartial() {
            AuthorizationConfig result2 = new AuthorizationConfig(this);
            result2.provider_ = this.provider_;
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
            if (other instanceof AuthorizationConfig) {
                return this.mergeFrom((AuthorizationConfig)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(AuthorizationConfig other) {
            if (other == AuthorizationConfig.getDefaultInstance()) {
                return this;
            }
            if (!other.getProvider().isEmpty()) {
                this.provider_ = other.provider_;
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
            AuthorizationConfig parsedMessage = null;
            try {
                parsedMessage = (AuthorizationConfig)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (AuthorizationConfig)e.getUnfinishedMessage();
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
        public String getProvider() {
            Object ref = this.provider_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.provider_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getProviderBytes() {
            Object ref = this.provider_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.provider_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setProvider(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.provider_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearProvider() {
            this.provider_ = AuthorizationConfig.getDefaultInstance().getProvider();
            this.onChanged();
            return this;
        }

        public Builder setProviderBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthorizationConfig.checkByteStringIsUtf8(value);
            this.provider_ = value;
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

