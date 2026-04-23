/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.HttpBodyOrBuilder;
import com.google.api.HttpBodyProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HttpBody
extends GeneratedMessageV3
implements HttpBodyOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    private volatile Object contentType_;
    public static final int DATA_FIELD_NUMBER = 2;
    private ByteString data_;
    public static final int EXTENSIONS_FIELD_NUMBER = 3;
    private List<Any> extensions_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final HttpBody DEFAULT_INSTANCE = new HttpBody();
    private static final Parser<HttpBody> PARSER = new AbstractParser<HttpBody>(){

        @Override
        public HttpBody parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new HttpBody(input2, extensionRegistry);
        }
    };

    private HttpBody(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private HttpBody() {
        this.contentType_ = "";
        this.data_ = ByteString.EMPTY;
        this.extensions_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HttpBody(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
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
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block12;
                        done = true;
                        continue block12;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.contentType_ = s2;
                        continue block12;
                    }
                    case 18: {
                        this.data_ = input2.readBytes();
                        continue block12;
                    }
                    case 26: 
                }
                if ((mutable_bitField0_ & 4) != 4) {
                    this.extensions_ = new ArrayList<Any>();
                    mutable_bitField0_ |= 4;
                }
                this.extensions_.add(input2.readMessage(Any.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 4) == 4) {
                this.extensions_ = Collections.unmodifiableList(this.extensions_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
    }

    @Override
    public String getContentType() {
        Object ref = this.contentType_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.contentType_ = s2;
        return s2;
    }

    @Override
    public ByteString getContentTypeBytes() {
        Object ref = this.contentType_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.contentType_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public ByteString getData() {
        return this.data_;
    }

    @Override
    public List<Any> getExtensionsList() {
        return this.extensions_;
    }

    @Override
    public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
        return this.extensions_;
    }

    @Override
    public int getExtensionsCount() {
        return this.extensions_.size();
    }

    @Override
    public Any getExtensions(int index) {
        return this.extensions_.get(index);
    }

    @Override
    public AnyOrBuilder getExtensionsOrBuilder(int index) {
        return this.extensions_.get(index);
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
        if (!this.getContentTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.contentType_);
        }
        if (!this.data_.isEmpty()) {
            output.writeBytes(2, this.data_);
        }
        for (int i = 0; i < this.extensions_.size(); ++i) {
            output.writeMessage(3, this.extensions_.get(i));
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
        if (!this.getContentTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.contentType_);
        }
        if (!this.data_.isEmpty()) {
            size2 += CodedOutputStream.computeBytesSize(2, this.data_);
        }
        for (int i = 0; i < this.extensions_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.extensions_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpBody)) {
            return super.equals(obj);
        }
        HttpBody other = (HttpBody)obj;
        boolean result2 = true;
        result2 = result2 && this.getContentType().equals(other.getContentType());
        result2 = result2 && this.getData().equals(other.getData());
        result2 = result2 && this.getExtensionsList().equals(other.getExtensionsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + HttpBody.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getContentType().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getData().hashCode();
        if (this.getExtensionsCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getExtensionsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static HttpBody parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpBody parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpBody parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static HttpBody parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static HttpBody parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static HttpBody parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static HttpBody parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static HttpBody parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return HttpBody.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpBody prototype) {
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

    public static HttpBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpBody> parser() {
        return PARSER;
    }

    public Parser<HttpBody> getParserForType() {
        return PARSER;
    }

    @Override
    public HttpBody getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements HttpBodyOrBuilder {
        private int bitField0_;
        private Object contentType_ = "";
        private ByteString data_ = ByteString.EMPTY;
        private List<Any> extensions_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> extensionsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
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
                this.getExtensionsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            if (this.extensionsBuilder_ == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
            } else {
                this.extensionsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        @Override
        public HttpBody getDefaultInstanceForType() {
            return HttpBody.getDefaultInstance();
        }

        @Override
        public HttpBody build() {
            HttpBody result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public HttpBody buildPartial() {
            HttpBody result2 = new HttpBody(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.contentType_ = this.contentType_;
            result2.data_ = this.data_;
            if (this.extensionsBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.extensions_ = Collections.unmodifiableList(this.extensions_);
                    this.bitField0_ &= 0xFFFFFFFB;
                }
                result2.extensions_ = this.extensions_;
            } else {
                result2.extensions_ = this.extensionsBuilder_.build();
            }
            result2.bitField0_ = to_bitField0_;
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
            if (other instanceof HttpBody) {
                return this.mergeFrom((HttpBody)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(HttpBody other) {
            if (other == HttpBody.getDefaultInstance()) {
                return this;
            }
            if (!other.getContentType().isEmpty()) {
                this.contentType_ = other.contentType_;
                this.onChanged();
            }
            if (other.getData() != ByteString.EMPTY) {
                this.setData(other.getData());
            }
            if (this.extensionsBuilder_ == null) {
                if (!other.extensions_.isEmpty()) {
                    if (this.extensions_.isEmpty()) {
                        this.extensions_ = other.extensions_;
                        this.bitField0_ &= 0xFFFFFFFB;
                    } else {
                        this.ensureExtensionsIsMutable();
                        this.extensions_.addAll(other.extensions_);
                    }
                    this.onChanged();
                }
            } else if (!other.extensions_.isEmpty()) {
                if (this.extensionsBuilder_.isEmpty()) {
                    this.extensionsBuilder_.dispose();
                    this.extensionsBuilder_ = null;
                    this.extensions_ = other.extensions_;
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.extensionsBuilder_ = alwaysUseFieldBuilders ? this.getExtensionsFieldBuilder() : null;
                } else {
                    this.extensionsBuilder_.addAllMessages(other.extensions_);
                }
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
            HttpBody parsedMessage = null;
            try {
                parsedMessage = (HttpBody)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (HttpBody)e.getUnfinishedMessage();
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
        public String getContentType() {
            Object ref = this.contentType_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.contentType_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getContentTypeBytes() {
            Object ref = this.contentType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.contentType_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setContentType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.contentType_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearContentType() {
            this.contentType_ = HttpBody.getDefaultInstance().getContentType();
            this.onChanged();
            return this;
        }

        public Builder setContentTypeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpBody.checkByteStringIsUtf8(value);
            this.contentType_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public ByteString getData() {
            return this.data_;
        }

        public Builder setData(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.data_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearData() {
            this.data_ = HttpBody.getDefaultInstance().getData();
            this.onChanged();
            return this;
        }

        private void ensureExtensionsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.extensions_ = new ArrayList<Any>(this.extensions_);
                this.bitField0_ |= 4;
            }
        }

        @Override
        public List<Any> getExtensionsList() {
            if (this.extensionsBuilder_ == null) {
                return Collections.unmodifiableList(this.extensions_);
            }
            return this.extensionsBuilder_.getMessageList();
        }

        @Override
        public int getExtensionsCount() {
            if (this.extensionsBuilder_ == null) {
                return this.extensions_.size();
            }
            return this.extensionsBuilder_.getCount();
        }

        @Override
        public Any getExtensions(int index) {
            if (this.extensionsBuilder_ == null) {
                return this.extensions_.get(index);
            }
            return this.extensionsBuilder_.getMessage(index);
        }

        public Builder setExtensions(int index, Any value) {
            if (this.extensionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExtensionsIsMutable();
                this.extensions_.set(index, value);
                this.onChanged();
            } else {
                this.extensionsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setExtensions(int index, Any.Builder builderForValue) {
            if (this.extensionsBuilder_ == null) {
                this.ensureExtensionsIsMutable();
                this.extensions_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.extensionsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addExtensions(Any value) {
            if (this.extensionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExtensionsIsMutable();
                this.extensions_.add(value);
                this.onChanged();
            } else {
                this.extensionsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addExtensions(int index, Any value) {
            if (this.extensionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExtensionsIsMutable();
                this.extensions_.add(index, value);
                this.onChanged();
            } else {
                this.extensionsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addExtensions(Any.Builder builderForValue) {
            if (this.extensionsBuilder_ == null) {
                this.ensureExtensionsIsMutable();
                this.extensions_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.extensionsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addExtensions(int index, Any.Builder builderForValue) {
            if (this.extensionsBuilder_ == null) {
                this.ensureExtensionsIsMutable();
                this.extensions_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.extensionsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllExtensions(Iterable<? extends Any> values) {
            if (this.extensionsBuilder_ == null) {
                this.ensureExtensionsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.extensions_);
                this.onChanged();
            } else {
                this.extensionsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearExtensions() {
            if (this.extensionsBuilder_ == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
                this.onChanged();
            } else {
                this.extensionsBuilder_.clear();
            }
            return this;
        }

        public Builder removeExtensions(int index) {
            if (this.extensionsBuilder_ == null) {
                this.ensureExtensionsIsMutable();
                this.extensions_.remove(index);
                this.onChanged();
            } else {
                this.extensionsBuilder_.remove(index);
            }
            return this;
        }

        public Any.Builder getExtensionsBuilder(int index) {
            return this.getExtensionsFieldBuilder().getBuilder(index);
        }

        @Override
        public AnyOrBuilder getExtensionsOrBuilder(int index) {
            if (this.extensionsBuilder_ == null) {
                return this.extensions_.get(index);
            }
            return this.extensionsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
            if (this.extensionsBuilder_ != null) {
                return this.extensionsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.extensions_);
        }

        public Any.Builder addExtensionsBuilder() {
            return this.getExtensionsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addExtensionsBuilder(int index) {
            return this.getExtensionsFieldBuilder().addBuilder(index, Any.getDefaultInstance());
        }

        public List<Any.Builder> getExtensionsBuilderList() {
            return this.getExtensionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getExtensionsFieldBuilder() {
            if (this.extensionsBuilder_ == null) {
                this.extensionsBuilder_ = new RepeatedFieldBuilderV3(this.extensions_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                this.extensions_ = null;
            }
            return this.extensionsBuilder_;
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

