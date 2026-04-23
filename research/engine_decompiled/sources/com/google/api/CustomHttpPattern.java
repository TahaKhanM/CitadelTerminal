/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.CustomHttpPatternOrBuilder;
import com.google.api.HttpProto;
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

public final class CustomHttpPattern
extends GeneratedMessageV3
implements CustomHttpPatternOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int KIND_FIELD_NUMBER = 1;
    private volatile Object kind_;
    public static final int PATH_FIELD_NUMBER = 2;
    private volatile Object path_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final CustomHttpPattern DEFAULT_INSTANCE = new CustomHttpPattern();
    private static final Parser<CustomHttpPattern> PARSER = new AbstractParser<CustomHttpPattern>(){

        @Override
        public CustomHttpPattern parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new CustomHttpPattern(input2, extensionRegistry);
        }
    };

    private CustomHttpPattern(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CustomHttpPattern() {
        this.kind_ = "";
        this.path_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CustomHttpPattern(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.kind_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                s2 = input2.readStringRequireUtf8();
                this.path_ = s2;
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
        return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_CustomHttpPattern_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHttpPattern.class, Builder.class);
    }

    @Override
    public String getKind() {
        Object ref = this.kind_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.kind_ = s2;
        return s2;
    }

    @Override
    public ByteString getKindBytes() {
        Object ref = this.kind_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.kind_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getPath() {
        Object ref = this.path_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.path_ = s2;
        return s2;
    }

    @Override
    public ByteString getPathBytes() {
        Object ref = this.path_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.path_ = b;
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
        if (!this.getKindBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.kind_);
        }
        if (!this.getPathBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.path_);
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
        if (!this.getKindBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.kind_);
        }
        if (!this.getPathBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.path_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomHttpPattern)) {
            return super.equals(obj);
        }
        CustomHttpPattern other = (CustomHttpPattern)obj;
        boolean result2 = true;
        result2 = result2 && this.getKind().equals(other.getKind());
        result2 = result2 && this.getPath().equals(other.getPath());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + CustomHttpPattern.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getKind().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getPath().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static CustomHttpPattern parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CustomHttpPattern parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CustomHttpPattern parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CustomHttpPattern parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CustomHttpPattern parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static CustomHttpPattern parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static CustomHttpPattern parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CustomHttpPattern parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CustomHttpPattern parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static CustomHttpPattern parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static CustomHttpPattern parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static CustomHttpPattern parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return CustomHttpPattern.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CustomHttpPattern prototype) {
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

    public static CustomHttpPattern getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CustomHttpPattern> parser() {
        return PARSER;
    }

    public Parser<CustomHttpPattern> getParserForType() {
        return PARSER;
    }

    @Override
    public CustomHttpPattern getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements CustomHttpPatternOrBuilder {
        private Object kind_ = "";
        private Object path_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHttpPattern.class, Builder.class);
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
            this.kind_ = "";
            this.path_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
        }

        @Override
        public CustomHttpPattern getDefaultInstanceForType() {
            return CustomHttpPattern.getDefaultInstance();
        }

        @Override
        public CustomHttpPattern build() {
            CustomHttpPattern result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public CustomHttpPattern buildPartial() {
            CustomHttpPattern result2 = new CustomHttpPattern(this);
            result2.kind_ = this.kind_;
            result2.path_ = this.path_;
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
            if (other instanceof CustomHttpPattern) {
                return this.mergeFrom((CustomHttpPattern)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(CustomHttpPattern other) {
            if (other == CustomHttpPattern.getDefaultInstance()) {
                return this;
            }
            if (!other.getKind().isEmpty()) {
                this.kind_ = other.kind_;
                this.onChanged();
            }
            if (!other.getPath().isEmpty()) {
                this.path_ = other.path_;
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
            CustomHttpPattern parsedMessage = null;
            try {
                parsedMessage = (CustomHttpPattern)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (CustomHttpPattern)e.getUnfinishedMessage();
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
        public String getKind() {
            Object ref = this.kind_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.kind_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getKindBytes() {
            Object ref = this.kind_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.kind_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setKind(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.kind_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearKind() {
            this.kind_ = CustomHttpPattern.getDefaultInstance().getKind();
            this.onChanged();
            return this;
        }

        public Builder setKindBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            CustomHttpPattern.checkByteStringIsUtf8(value);
            this.kind_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getPath() {
            Object ref = this.path_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.path_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPathBytes() {
            Object ref = this.path_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.path_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPath(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.path_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPath() {
            this.path_ = CustomHttpPattern.getDefaultInstance().getPath();
            this.onChanged();
            return this;
        }

        public Builder setPathBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            CustomHttpPattern.checkByteStringIsUtf8(value);
            this.path_ = value;
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

