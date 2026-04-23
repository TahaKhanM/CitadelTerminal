/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.DocumentationProto;
import com.google.api.PageOrBuilder;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Page
extends GeneratedMessageV3
implements PageOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int CONTENT_FIELD_NUMBER = 2;
    private volatile Object content_;
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    private List<Page> subpages_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Page DEFAULT_INSTANCE = new Page();
    private static final Parser<Page> PARSER = new AbstractParser<Page>(){

        @Override
        public Page parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Page(input2, extensionRegistry);
        }
    };

    private Page(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Page() {
        this.name_ = "";
        this.content_ = "";
        this.subpages_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Page(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.name_ = s2;
                        continue block12;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.content_ = s2;
                        continue block12;
                    }
                    case 26: 
                }
                if ((mutable_bitField0_ & 4) != 4) {
                    this.subpages_ = new ArrayList<Page>();
                    mutable_bitField0_ |= 4;
                }
                this.subpages_.add(input2.readMessage(Page.parser(), extensionRegistry));
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
                this.subpages_ = Collections.unmodifiableList(this.subpages_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Page_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
    }

    @Override
    public String getName() {
        Object ref = this.name_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.name_ = s2;
        return s2;
    }

    @Override
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getContent() {
        Object ref = this.content_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.content_ = s2;
        return s2;
    }

    @Override
    public ByteString getContentBytes() {
        Object ref = this.content_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.content_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    @Override
    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
    }

    @Override
    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    @Override
    public Page getSubpages(int index) {
        return this.subpages_.get(index);
    }

    @Override
    public PageOrBuilder getSubpagesOrBuilder(int index) {
        return this.subpages_.get(index);
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        if (!this.getContentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.content_);
        }
        for (int i = 0; i < this.subpages_.size(); ++i) {
            output.writeMessage(3, this.subpages_.get(i));
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
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (!this.getContentBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.content_);
        }
        for (int i = 0; i < this.subpages_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.subpages_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return super.equals(obj);
        }
        Page other = (Page)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getContent().equals(other.getContent());
        result2 = result2 && this.getSubpagesList().equals(other.getSubpagesList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Page.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getContent().hashCode();
        if (this.getSubpagesCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getSubpagesList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Page parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Page parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Page parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Page parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Page parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Page parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Page parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Page parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Page parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Page parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Page parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Page parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Page.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Page prototype) {
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

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Page> parser() {
        return PARSER;
    }

    public Parser<Page> getParserForType() {
        return PARSER;
    }

    @Override
    public Page getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements PageOrBuilder {
        private int bitField0_;
        private Object name_ = "";
        private Object content_ = "";
        private List<Page> subpages_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> subpagesBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
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
                this.getSubpagesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.content_ = "";
            if (this.subpagesBuilder_ == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
            } else {
                this.subpagesBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        @Override
        public Page getDefaultInstanceForType() {
            return Page.getDefaultInstance();
        }

        @Override
        public Page build() {
            Page result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Page buildPartial() {
            Page result2 = new Page(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.name_ = this.name_;
            result2.content_ = this.content_;
            if (this.subpagesBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                    this.bitField0_ &= 0xFFFFFFFB;
                }
                result2.subpages_ = this.subpages_;
            } else {
                result2.subpages_ = this.subpagesBuilder_.build();
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
            if (other instanceof Page) {
                return this.mergeFrom((Page)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Page other) {
            if (other == Page.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.getContent().isEmpty()) {
                this.content_ = other.content_;
                this.onChanged();
            }
            if (this.subpagesBuilder_ == null) {
                if (!other.subpages_.isEmpty()) {
                    if (this.subpages_.isEmpty()) {
                        this.subpages_ = other.subpages_;
                        this.bitField0_ &= 0xFFFFFFFB;
                    } else {
                        this.ensureSubpagesIsMutable();
                        this.subpages_.addAll(other.subpages_);
                    }
                    this.onChanged();
                }
            } else if (!other.subpages_.isEmpty()) {
                if (this.subpagesBuilder_.isEmpty()) {
                    this.subpagesBuilder_.dispose();
                    this.subpagesBuilder_ = null;
                    this.subpages_ = other.subpages_;
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.subpagesBuilder_ = alwaysUseFieldBuilders ? this.getSubpagesFieldBuilder() : null;
                } else {
                    this.subpagesBuilder_.addAllMessages(other.subpages_);
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
            Page parsedMessage = null;
            try {
                parsedMessage = (Page)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Page)e.getUnfinishedMessage();
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
        public String getName() {
            Object ref = this.name_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.name_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Page.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Page.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getContent() {
            Object ref = this.content_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.content_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getContentBytes() {
            Object ref = this.content_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.content_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setContent(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.content_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearContent() {
            this.content_ = Page.getDefaultInstance().getContent();
            this.onChanged();
            return this;
        }

        public Builder setContentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Page.checkByteStringIsUtf8(value);
            this.content_ = value;
            this.onChanged();
            return this;
        }

        private void ensureSubpagesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.subpages_ = new ArrayList<Page>(this.subpages_);
                this.bitField0_ |= 4;
            }
        }

        @Override
        public List<Page> getSubpagesList() {
            if (this.subpagesBuilder_ == null) {
                return Collections.unmodifiableList(this.subpages_);
            }
            return this.subpagesBuilder_.getMessageList();
        }

        @Override
        public int getSubpagesCount() {
            if (this.subpagesBuilder_ == null) {
                return this.subpages_.size();
            }
            return this.subpagesBuilder_.getCount();
        }

        @Override
        public Page getSubpages(int index) {
            if (this.subpagesBuilder_ == null) {
                return this.subpages_.get(index);
            }
            return this.subpagesBuilder_.getMessage(index);
        }

        public Builder setSubpages(int index, Page value) {
            if (this.subpagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSubpagesIsMutable();
                this.subpages_.set(index, value);
                this.onChanged();
            } else {
                this.subpagesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setSubpages(int index, Builder builderForValue) {
            if (this.subpagesBuilder_ == null) {
                this.ensureSubpagesIsMutable();
                this.subpages_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.subpagesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addSubpages(Page value) {
            if (this.subpagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSubpagesIsMutable();
                this.subpages_.add(value);
                this.onChanged();
            } else {
                this.subpagesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addSubpages(int index, Page value) {
            if (this.subpagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSubpagesIsMutable();
                this.subpages_.add(index, value);
                this.onChanged();
            } else {
                this.subpagesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addSubpages(Builder builderForValue) {
            if (this.subpagesBuilder_ == null) {
                this.ensureSubpagesIsMutable();
                this.subpages_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.subpagesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addSubpages(int index, Builder builderForValue) {
            if (this.subpagesBuilder_ == null) {
                this.ensureSubpagesIsMutable();
                this.subpages_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.subpagesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllSubpages(Iterable<? extends Page> values) {
            if (this.subpagesBuilder_ == null) {
                this.ensureSubpagesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.subpages_);
                this.onChanged();
            } else {
                this.subpagesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearSubpages() {
            if (this.subpagesBuilder_ == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
                this.onChanged();
            } else {
                this.subpagesBuilder_.clear();
            }
            return this;
        }

        public Builder removeSubpages(int index) {
            if (this.subpagesBuilder_ == null) {
                this.ensureSubpagesIsMutable();
                this.subpages_.remove(index);
                this.onChanged();
            } else {
                this.subpagesBuilder_.remove(index);
            }
            return this;
        }

        public Builder getSubpagesBuilder(int index) {
            return this.getSubpagesFieldBuilder().getBuilder(index);
        }

        @Override
        public PageOrBuilder getSubpagesOrBuilder(int index) {
            if (this.subpagesBuilder_ == null) {
                return this.subpages_.get(index);
            }
            return this.subpagesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
            if (this.subpagesBuilder_ != null) {
                return this.subpagesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.subpages_);
        }

        public Builder addSubpagesBuilder() {
            return this.getSubpagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Builder addSubpagesBuilder(int index) {
            return this.getSubpagesFieldBuilder().addBuilder(index, Page.getDefaultInstance());
        }

        public List<Builder> getSubpagesBuilderList() {
            return this.getSubpagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> getSubpagesFieldBuilder() {
            if (this.subpagesBuilder_ == null) {
                this.subpagesBuilder_ = new RepeatedFieldBuilderV3(this.subpages_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                this.subpages_ = null;
            }
            return this.subpagesBuilder_;
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

