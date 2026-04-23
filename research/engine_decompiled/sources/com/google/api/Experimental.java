/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthorizationConfig;
import com.google.api.AuthorizationConfigOrBuilder;
import com.google.api.ExperimentalOrBuilder;
import com.google.api.ExperimentalProto;
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

public final class Experimental
extends GeneratedMessageV3
implements ExperimentalOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int AUTHORIZATION_FIELD_NUMBER = 8;
    private AuthorizationConfig authorization_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Experimental DEFAULT_INSTANCE = new Experimental();
    private static final Parser<Experimental> PARSER = new AbstractParser<Experimental>(){

        @Override
        public Experimental parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Experimental(input2, extensionRegistry);
        }
    };

    private Experimental(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Experimental() {
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Experimental(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 66: 
                }
                AuthorizationConfig.Builder subBuilder = null;
                if (this.authorization_ != null) {
                    subBuilder = this.authorization_.toBuilder();
                }
                this.authorization_ = input2.readMessage(AuthorizationConfig.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                subBuilder.mergeFrom(this.authorization_);
                this.authorization_ = subBuilder.buildPartial();
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
        return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ExperimentalProto.internal_static_google_api_Experimental_fieldAccessorTable.ensureFieldAccessorsInitialized(Experimental.class, Builder.class);
    }

    @Override
    public boolean hasAuthorization() {
        return this.authorization_ != null;
    }

    @Override
    public AuthorizationConfig getAuthorization() {
        return this.authorization_ == null ? AuthorizationConfig.getDefaultInstance() : this.authorization_;
    }

    @Override
    public AuthorizationConfigOrBuilder getAuthorizationOrBuilder() {
        return this.getAuthorization();
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
        if (this.authorization_ != null) {
            output.writeMessage(8, this.getAuthorization());
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
        if (this.authorization_ != null) {
            size2 += CodedOutputStream.computeMessageSize(8, this.getAuthorization());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Experimental)) {
            return super.equals(obj);
        }
        Experimental other = (Experimental)obj;
        boolean result2 = true;
        boolean bl = result2 = result2 && this.hasAuthorization() == other.hasAuthorization();
        if (this.hasAuthorization()) {
            result2 = result2 && this.getAuthorization().equals(other.getAuthorization());
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
        hash = 19 * hash + Experimental.getDescriptor().hashCode();
        if (this.hasAuthorization()) {
            hash = 37 * hash + 8;
            hash = 53 * hash + this.getAuthorization().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Experimental parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Experimental parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Experimental parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Experimental parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Experimental parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Experimental parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Experimental parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Experimental parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Experimental parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Experimental parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Experimental parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Experimental parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Experimental.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Experimental prototype) {
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

    public static Experimental getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Experimental> parser() {
        return PARSER;
    }

    public Parser<Experimental> getParserForType() {
        return PARSER;
    }

    @Override
    public Experimental getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ExperimentalOrBuilder {
        private AuthorizationConfig authorization_ = null;
        private SingleFieldBuilderV3<AuthorizationConfig, AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> authorizationBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExperimentalProto.internal_static_google_api_Experimental_fieldAccessorTable.ensureFieldAccessorsInitialized(Experimental.class, Builder.class);
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
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = null;
            } else {
                this.authorization_ = null;
                this.authorizationBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
        }

        @Override
        public Experimental getDefaultInstanceForType() {
            return Experimental.getDefaultInstance();
        }

        @Override
        public Experimental build() {
            Experimental result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Experimental buildPartial() {
            Experimental result2 = new Experimental(this);
            if (this.authorizationBuilder_ == null) {
                result2.authorization_ = this.authorization_;
            } else {
                result2.authorization_ = this.authorizationBuilder_.build();
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
            if (other instanceof Experimental) {
                return this.mergeFrom((Experimental)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Experimental other) {
            if (other == Experimental.getDefaultInstance()) {
                return this;
            }
            if (other.hasAuthorization()) {
                this.mergeAuthorization(other.getAuthorization());
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
            Experimental parsedMessage = null;
            try {
                parsedMessage = (Experimental)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Experimental)e.getUnfinishedMessage();
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
        public boolean hasAuthorization() {
            return this.authorizationBuilder_ != null || this.authorization_ != null;
        }

        @Override
        public AuthorizationConfig getAuthorization() {
            if (this.authorizationBuilder_ == null) {
                return this.authorization_ == null ? AuthorizationConfig.getDefaultInstance() : this.authorization_;
            }
            return this.authorizationBuilder_.getMessage();
        }

        public Builder setAuthorization(AuthorizationConfig value) {
            if (this.authorizationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.authorization_ = value;
                this.onChanged();
            } else {
                this.authorizationBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setAuthorization(AuthorizationConfig.Builder builderForValue) {
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = builderForValue.build();
                this.onChanged();
            } else {
                this.authorizationBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeAuthorization(AuthorizationConfig value) {
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = this.authorization_ != null ? AuthorizationConfig.newBuilder(this.authorization_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.authorizationBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearAuthorization() {
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = null;
                this.onChanged();
            } else {
                this.authorization_ = null;
                this.authorizationBuilder_ = null;
            }
            return this;
        }

        public AuthorizationConfig.Builder getAuthorizationBuilder() {
            this.onChanged();
            return this.getAuthorizationFieldBuilder().getBuilder();
        }

        @Override
        public AuthorizationConfigOrBuilder getAuthorizationOrBuilder() {
            if (this.authorizationBuilder_ != null) {
                return this.authorizationBuilder_.getMessageOrBuilder();
            }
            return this.authorization_ == null ? AuthorizationConfig.getDefaultInstance() : this.authorization_;
        }

        private SingleFieldBuilderV3<AuthorizationConfig, AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> getAuthorizationFieldBuilder() {
            if (this.authorizationBuilder_ == null) {
                this.authorizationBuilder_ = new SingleFieldBuilderV3(this.getAuthorization(), this.getParentForChildren(), this.isClean());
                this.authorization_ = null;
            }
            return this.authorizationBuilder_;
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

