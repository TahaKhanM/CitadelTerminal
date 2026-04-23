/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

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
import com.google.rpc.ErrorDetailsProto;
import com.google.rpc.ResourceInfoOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ResourceInfo
extends GeneratedMessageV3
implements ResourceInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 1;
    private volatile Object resourceType_;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 2;
    private volatile Object resourceName_;
    public static final int OWNER_FIELD_NUMBER = 3;
    private volatile Object owner_;
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    private volatile Object description_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ResourceInfo DEFAULT_INSTANCE = new ResourceInfo();
    private static final Parser<ResourceInfo> PARSER = new AbstractParser<ResourceInfo>(){

        @Override
        public ResourceInfo parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ResourceInfo(input2, extensionRegistry);
        }
    };

    private ResourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ResourceInfo() {
        this.resourceType_ = "";
        this.resourceName_ = "";
        this.owner_ = "";
        this.description_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ResourceInfo(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
                String s2;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block13;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block13;
                        done = true;
                        continue block13;
                    }
                    case 10: {
                        s2 = input2.readStringRequireUtf8();
                        this.resourceType_ = s2;
                        continue block13;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        this.resourceName_ = s2;
                        continue block13;
                    }
                    case 26: {
                        s2 = input2.readStringRequireUtf8();
                        this.owner_ = s2;
                        continue block13;
                    }
                    case 34: 
                }
                s2 = input2.readStringRequireUtf8();
                this.description_ = s2;
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
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
    }

    @Override
    public String getResourceType() {
        Object ref = this.resourceType_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.resourceType_ = s2;
        return s2;
    }

    @Override
    public ByteString getResourceTypeBytes() {
        Object ref = this.resourceType_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.resourceType_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getResourceName() {
        Object ref = this.resourceName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.resourceName_ = s2;
        return s2;
    }

    @Override
    public ByteString getResourceNameBytes() {
        Object ref = this.resourceName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.resourceName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getOwner() {
        Object ref = this.owner_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.owner_ = s2;
        return s2;
    }

    @Override
    public ByteString getOwnerBytes() {
        Object ref = this.owner_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.owner_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getDescription() {
        Object ref = this.description_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.description_ = s2;
        return s2;
    }

    @Override
    public ByteString getDescriptionBytes() {
        Object ref = this.description_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.description_ = b;
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
        if (!this.getResourceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.resourceType_);
        }
        if (!this.getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.resourceName_);
        }
        if (!this.getOwnerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.owner_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.description_);
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
        if (!this.getResourceTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.resourceType_);
        }
        if (!this.getResourceNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.resourceName_);
        }
        if (!this.getOwnerBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.owner_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.description_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceInfo)) {
            return super.equals(obj);
        }
        ResourceInfo other = (ResourceInfo)obj;
        boolean result2 = true;
        result2 = result2 && this.getResourceType().equals(other.getResourceType());
        result2 = result2 && this.getResourceName().equals(other.getResourceName());
        result2 = result2 && this.getOwner().equals(other.getOwner());
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ResourceInfo.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getResourceType().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getResourceName().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getOwner().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getDescription().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ResourceInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ResourceInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ResourceInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ResourceInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ResourceInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ResourceInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ResourceInfo parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ResourceInfo parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ResourceInfo parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ResourceInfo parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ResourceInfo.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResourceInfo prototype) {
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

    public static ResourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceInfo> parser() {
        return PARSER;
    }

    public Parser<ResourceInfo> getParserForType() {
        return PARSER;
    }

    @Override
    public ResourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ResourceInfoOrBuilder {
        private Object resourceType_ = "";
        private Object resourceName_ = "";
        private Object owner_ = "";
        private Object description_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
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
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        @Override
        public ResourceInfo getDefaultInstanceForType() {
            return ResourceInfo.getDefaultInstance();
        }

        @Override
        public ResourceInfo build() {
            ResourceInfo result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ResourceInfo buildPartial() {
            ResourceInfo result2 = new ResourceInfo(this);
            result2.resourceType_ = this.resourceType_;
            result2.resourceName_ = this.resourceName_;
            result2.owner_ = this.owner_;
            result2.description_ = this.description_;
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
            if (other instanceof ResourceInfo) {
                return this.mergeFrom((ResourceInfo)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ResourceInfo other) {
            if (other == ResourceInfo.getDefaultInstance()) {
                return this;
            }
            if (!other.getResourceType().isEmpty()) {
                this.resourceType_ = other.resourceType_;
                this.onChanged();
            }
            if (!other.getResourceName().isEmpty()) {
                this.resourceName_ = other.resourceName_;
                this.onChanged();
            }
            if (!other.getOwner().isEmpty()) {
                this.owner_ = other.owner_;
                this.onChanged();
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
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
            ResourceInfo parsedMessage = null;
            try {
                parsedMessage = (ResourceInfo)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ResourceInfo)e.getUnfinishedMessage();
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
        public String getResourceType() {
            Object ref = this.resourceType_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.resourceType_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getResourceTypeBytes() {
            Object ref = this.resourceType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.resourceType_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setResourceType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.resourceType_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearResourceType() {
            this.resourceType_ = ResourceInfo.getDefaultInstance().getResourceType();
            this.onChanged();
            return this;
        }

        public Builder setResourceTypeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ResourceInfo.checkByteStringIsUtf8(value);
            this.resourceType_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getResourceName() {
            Object ref = this.resourceName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.resourceName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getResourceNameBytes() {
            Object ref = this.resourceName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.resourceName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setResourceName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.resourceName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearResourceName() {
            this.resourceName_ = ResourceInfo.getDefaultInstance().getResourceName();
            this.onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ResourceInfo.checkByteStringIsUtf8(value);
            this.resourceName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getOwner() {
            Object ref = this.owner_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.owner_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getOwnerBytes() {
            Object ref = this.owner_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.owner_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setOwner(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.owner_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearOwner() {
            this.owner_ = ResourceInfo.getDefaultInstance().getOwner();
            this.onChanged();
            return this;
        }

        public Builder setOwnerBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ResourceInfo.checkByteStringIsUtf8(value);
            this.owner_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getDescription() {
            Object ref = this.description_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.description_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDescriptionBytes() {
            Object ref = this.description_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.description_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDescription(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.description_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = ResourceInfo.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ResourceInfo.checkByteStringIsUtf8(value);
            this.description_ = value;
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

