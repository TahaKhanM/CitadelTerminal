/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthProto;
import com.google.api.AuthProviderOrBuilder;
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

public final class AuthProvider
extends GeneratedMessageV3
implements AuthProviderOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int ID_FIELD_NUMBER = 1;
    private volatile Object id_;
    public static final int ISSUER_FIELD_NUMBER = 2;
    private volatile Object issuer_;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    private volatile Object jwksUri_;
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    private volatile Object audiences_;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    private volatile Object authorizationUrl_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final AuthProvider DEFAULT_INSTANCE = new AuthProvider();
    private static final Parser<AuthProvider> PARSER = new AbstractParser<AuthProvider>(){

        @Override
        public AuthProvider parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new AuthProvider(input2, extensionRegistry);
        }
    };

    private AuthProvider(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private AuthProvider() {
        this.id_ = "";
        this.issuer_ = "";
        this.jwksUri_ = "";
        this.audiences_ = "";
        this.authorizationUrl_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthProvider(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block14: while (!done) {
                String s2;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block14;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block14;
                        done = true;
                        continue block14;
                    }
                    case 10: {
                        s2 = input2.readStringRequireUtf8();
                        this.id_ = s2;
                        continue block14;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        this.issuer_ = s2;
                        continue block14;
                    }
                    case 26: {
                        s2 = input2.readStringRequireUtf8();
                        this.jwksUri_ = s2;
                        continue block14;
                    }
                    case 34: {
                        s2 = input2.readStringRequireUtf8();
                        this.audiences_ = s2;
                        continue block14;
                    }
                    case 42: 
                }
                s2 = input2.readStringRequireUtf8();
                this.authorizationUrl_ = s2;
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
        return AuthProto.internal_static_google_api_AuthProvider_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
    }

    @Override
    public String getId() {
        Object ref = this.id_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.id_ = s2;
        return s2;
    }

    @Override
    public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.id_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getIssuer() {
        Object ref = this.issuer_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.issuer_ = s2;
        return s2;
    }

    @Override
    public ByteString getIssuerBytes() {
        Object ref = this.issuer_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.issuer_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getJwksUri() {
        Object ref = this.jwksUri_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.jwksUri_ = s2;
        return s2;
    }

    @Override
    public ByteString getJwksUriBytes() {
        Object ref = this.jwksUri_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.jwksUri_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getAudiences() {
        Object ref = this.audiences_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.audiences_ = s2;
        return s2;
    }

    @Override
    public ByteString getAudiencesBytes() {
        Object ref = this.audiences_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.audiences_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getAuthorizationUrl() {
        Object ref = this.authorizationUrl_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.authorizationUrl_ = s2;
        return s2;
    }

    @Override
    public ByteString getAuthorizationUrlBytes() {
        Object ref = this.authorizationUrl_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.authorizationUrl_ = b;
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
        if (!this.getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.id_);
        }
        if (!this.getIssuerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.issuer_);
        }
        if (!this.getJwksUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.jwksUri_);
        }
        if (!this.getAudiencesBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.audiences_);
        }
        if (!this.getAuthorizationUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.authorizationUrl_);
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
        if (!this.getIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.id_);
        }
        if (!this.getIssuerBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.issuer_);
        }
        if (!this.getJwksUriBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.jwksUri_);
        }
        if (!this.getAudiencesBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.audiences_);
        }
        if (!this.getAuthorizationUrlBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.authorizationUrl_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthProvider)) {
            return super.equals(obj);
        }
        AuthProvider other = (AuthProvider)obj;
        boolean result2 = true;
        result2 = result2 && this.getId().equals(other.getId());
        result2 = result2 && this.getIssuer().equals(other.getIssuer());
        result2 = result2 && this.getJwksUri().equals(other.getJwksUri());
        result2 = result2 && this.getAudiences().equals(other.getAudiences());
        result2 = result2 && this.getAuthorizationUrl().equals(other.getAuthorizationUrl());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + AuthProvider.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getId().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getIssuer().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getJwksUri().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getAudiences().hashCode();
        hash = 37 * hash + 5;
        hash = 53 * hash + this.getAuthorizationUrl().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static AuthProvider parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthProvider parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthProvider parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthProvider parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthProvider parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthProvider parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthProvider parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthProvider parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthProvider parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static AuthProvider parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthProvider parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthProvider parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return AuthProvider.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthProvider prototype) {
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

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return PARSER;
    }

    public Parser<AuthProvider> getParserForType() {
        return PARSER;
    }

    @Override
    public AuthProvider getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuthProviderOrBuilder {
        private Object id_ = "";
        private Object issuer_ = "";
        private Object jwksUri_ = "";
        private Object audiences_ = "";
        private Object authorizationUrl_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
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
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        @Override
        public AuthProvider getDefaultInstanceForType() {
            return AuthProvider.getDefaultInstance();
        }

        @Override
        public AuthProvider build() {
            AuthProvider result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public AuthProvider buildPartial() {
            AuthProvider result2 = new AuthProvider(this);
            result2.id_ = this.id_;
            result2.issuer_ = this.issuer_;
            result2.jwksUri_ = this.jwksUri_;
            result2.audiences_ = this.audiences_;
            result2.authorizationUrl_ = this.authorizationUrl_;
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
            if (other instanceof AuthProvider) {
                return this.mergeFrom((AuthProvider)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(AuthProvider other) {
            if (other == AuthProvider.getDefaultInstance()) {
                return this;
            }
            if (!other.getId().isEmpty()) {
                this.id_ = other.id_;
                this.onChanged();
            }
            if (!other.getIssuer().isEmpty()) {
                this.issuer_ = other.issuer_;
                this.onChanged();
            }
            if (!other.getJwksUri().isEmpty()) {
                this.jwksUri_ = other.jwksUri_;
                this.onChanged();
            }
            if (!other.getAudiences().isEmpty()) {
                this.audiences_ = other.audiences_;
                this.onChanged();
            }
            if (!other.getAuthorizationUrl().isEmpty()) {
                this.authorizationUrl_ = other.authorizationUrl_;
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
            AuthProvider parsedMessage = null;
            try {
                parsedMessage = (AuthProvider)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (AuthProvider)e.getUnfinishedMessage();
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
        public String getId() {
            Object ref = this.id_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.id_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getIdBytes() {
            Object ref = this.id_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.id_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.id_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = AuthProvider.getDefaultInstance().getId();
            this.onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthProvider.checkByteStringIsUtf8(value);
            this.id_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getIssuer() {
            Object ref = this.issuer_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.issuer_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getIssuerBytes() {
            Object ref = this.issuer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.issuer_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setIssuer(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.issuer_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearIssuer() {
            this.issuer_ = AuthProvider.getDefaultInstance().getIssuer();
            this.onChanged();
            return this;
        }

        public Builder setIssuerBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthProvider.checkByteStringIsUtf8(value);
            this.issuer_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getJwksUri() {
            Object ref = this.jwksUri_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.jwksUri_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getJwksUriBytes() {
            Object ref = this.jwksUri_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.jwksUri_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setJwksUri(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.jwksUri_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearJwksUri() {
            this.jwksUri_ = AuthProvider.getDefaultInstance().getJwksUri();
            this.onChanged();
            return this;
        }

        public Builder setJwksUriBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthProvider.checkByteStringIsUtf8(value);
            this.jwksUri_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getAudiences() {
            Object ref = this.audiences_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.audiences_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getAudiencesBytes() {
            Object ref = this.audiences_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.audiences_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setAudiences(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.audiences_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAudiences() {
            this.audiences_ = AuthProvider.getDefaultInstance().getAudiences();
            this.onChanged();
            return this;
        }

        public Builder setAudiencesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthProvider.checkByteStringIsUtf8(value);
            this.audiences_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getAuthorizationUrl() {
            Object ref = this.authorizationUrl_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.authorizationUrl_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getAuthorizationUrlBytes() {
            Object ref = this.authorizationUrl_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.authorizationUrl_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setAuthorizationUrl(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.authorizationUrl_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAuthorizationUrl() {
            this.authorizationUrl_ = AuthProvider.getDefaultInstance().getAuthorizationUrl();
            this.onChanged();
            return this;
        }

        public Builder setAuthorizationUrlBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthProvider.checkByteStringIsUtf8(value);
            this.authorizationUrl_ = value;
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

