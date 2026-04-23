/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.ErrorDetailsProto;
import com.google.rpc.HelpOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Help
extends GeneratedMessageV3
implements HelpOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int LINKS_FIELD_NUMBER = 1;
    private List<Link> links_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Help DEFAULT_INSTANCE = new Help();
    private static final Parser<Help> PARSER = new AbstractParser<Help>(){

        @Override
        public Help parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Help(input2, extensionRegistry);
        }
    };

    private Help(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Help() {
        this.links_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Help(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                if (!(mutable_bitField0_ & true)) {
                    this.links_ = new ArrayList<Link>();
                    mutable_bitField0_ |= true;
                }
                this.links_.add(input2.readMessage(Link.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if (mutable_bitField0_ & true) {
                this.links_ = Collections.unmodifiableList(this.links_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
    }

    @Override
    public List<Link> getLinksList() {
        return this.links_;
    }

    @Override
    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    @Override
    public int getLinksCount() {
        return this.links_.size();
    }

    @Override
    public Link getLinks(int index) {
        return this.links_.get(index);
    }

    @Override
    public LinkOrBuilder getLinksOrBuilder(int index) {
        return this.links_.get(index);
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
        for (int i = 0; i < this.links_.size(); ++i) {
            output.writeMessage(1, this.links_.get(i));
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
        for (int i = 0; i < this.links_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.links_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Help)) {
            return super.equals(obj);
        }
        Help other = (Help)obj;
        boolean result2 = true;
        result2 = result2 && this.getLinksList().equals(other.getLinksList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Help.getDescriptor().hashCode();
        if (this.getLinksCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getLinksList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Help parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Help parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Help parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Help parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Help parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Help parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Help parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Help parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Help parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Help parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Help parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Help parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Help.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Help prototype) {
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

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Help> parser() {
        return PARSER;
    }

    public Parser<Help> getParserForType() {
        return PARSER;
    }

    @Override
    public Help getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements HelpOrBuilder {
        private int bitField0_;
        private List<Link> links_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> linksBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
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
                this.getLinksFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.linksBuilder_ == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.linksBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        @Override
        public Help getDefaultInstanceForType() {
            return Help.getDefaultInstance();
        }

        @Override
        public Help build() {
            Help result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Help buildPartial() {
            Help result2 = new Help(this);
            int from_bitField0_ = this.bitField0_;
            if (this.linksBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.links_ = Collections.unmodifiableList(this.links_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.links_ = this.links_;
            } else {
                result2.links_ = this.linksBuilder_.build();
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
            if (other instanceof Help) {
                return this.mergeFrom((Help)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Help other) {
            if (other == Help.getDefaultInstance()) {
                return this;
            }
            if (this.linksBuilder_ == null) {
                if (!other.links_.isEmpty()) {
                    if (this.links_.isEmpty()) {
                        this.links_ = other.links_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureLinksIsMutable();
                        this.links_.addAll(other.links_);
                    }
                    this.onChanged();
                }
            } else if (!other.links_.isEmpty()) {
                if (this.linksBuilder_.isEmpty()) {
                    this.linksBuilder_.dispose();
                    this.linksBuilder_ = null;
                    this.links_ = other.links_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.linksBuilder_ = alwaysUseFieldBuilders ? this.getLinksFieldBuilder() : null;
                } else {
                    this.linksBuilder_.addAllMessages(other.links_);
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
            Help parsedMessage = null;
            try {
                parsedMessage = (Help)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Help)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureLinksIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.links_ = new ArrayList<Link>(this.links_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<Link> getLinksList() {
            if (this.linksBuilder_ == null) {
                return Collections.unmodifiableList(this.links_);
            }
            return this.linksBuilder_.getMessageList();
        }

        @Override
        public int getLinksCount() {
            if (this.linksBuilder_ == null) {
                return this.links_.size();
            }
            return this.linksBuilder_.getCount();
        }

        @Override
        public Link getLinks(int index) {
            if (this.linksBuilder_ == null) {
                return this.links_.get(index);
            }
            return this.linksBuilder_.getMessage(index);
        }

        public Builder setLinks(int index, Link value) {
            if (this.linksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLinksIsMutable();
                this.links_.set(index, value);
                this.onChanged();
            } else {
                this.linksBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setLinks(int index, Link.Builder builderForValue) {
            if (this.linksBuilder_ == null) {
                this.ensureLinksIsMutable();
                this.links_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.linksBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addLinks(Link value) {
            if (this.linksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLinksIsMutable();
                this.links_.add(value);
                this.onChanged();
            } else {
                this.linksBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addLinks(int index, Link value) {
            if (this.linksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLinksIsMutable();
                this.links_.add(index, value);
                this.onChanged();
            } else {
                this.linksBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addLinks(Link.Builder builderForValue) {
            if (this.linksBuilder_ == null) {
                this.ensureLinksIsMutable();
                this.links_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.linksBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addLinks(int index, Link.Builder builderForValue) {
            if (this.linksBuilder_ == null) {
                this.ensureLinksIsMutable();
                this.links_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.linksBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllLinks(Iterable<? extends Link> values) {
            if (this.linksBuilder_ == null) {
                this.ensureLinksIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.links_);
                this.onChanged();
            } else {
                this.linksBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearLinks() {
            if (this.linksBuilder_ == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.linksBuilder_.clear();
            }
            return this;
        }

        public Builder removeLinks(int index) {
            if (this.linksBuilder_ == null) {
                this.ensureLinksIsMutable();
                this.links_.remove(index);
                this.onChanged();
            } else {
                this.linksBuilder_.remove(index);
            }
            return this;
        }

        public Link.Builder getLinksBuilder(int index) {
            return this.getLinksFieldBuilder().getBuilder(index);
        }

        @Override
        public LinkOrBuilder getLinksOrBuilder(int index) {
            if (this.linksBuilder_ == null) {
                return this.links_.get(index);
            }
            return this.linksBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
            if (this.linksBuilder_ != null) {
                return this.linksBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.links_);
        }

        public Link.Builder addLinksBuilder() {
            return this.getLinksFieldBuilder().addBuilder(Link.getDefaultInstance());
        }

        public Link.Builder addLinksBuilder(int index) {
            return this.getLinksFieldBuilder().addBuilder(index, Link.getDefaultInstance());
        }

        public List<Link.Builder> getLinksBuilderList() {
            return this.getLinksFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> getLinksFieldBuilder() {
            if (this.linksBuilder_ == null) {
                this.linksBuilder_ = new RepeatedFieldBuilderV3(this.links_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.links_ = null;
            }
            return this.linksBuilder_;
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

    public static final class Link
    extends GeneratedMessageV3
    implements LinkOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        private volatile Object description_;
        public static final int URL_FIELD_NUMBER = 2;
        private volatile Object url_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Link DEFAULT_INSTANCE = new Link();
        private static final Parser<Link> PARSER = new AbstractParser<Link>(){

            @Override
            public Link parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Link(input2, extensionRegistry);
            }
        };

        private Link(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Link() {
            this.description_ = "";
            this.url_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Link(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.description_ = s2;
                            continue block11;
                        }
                        case 18: 
                    }
                    s2 = input2.readStringRequireUtf8();
                    this.url_ = s2;
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
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
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
        public String getUrl() {
            Object ref = this.url_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            this.url_ = s2;
            return s2;
        }

        @Override
        public ByteString getUrlBytes() {
            Object ref = this.url_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.url_ = b;
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
            if (!this.getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(output, 1, this.description_);
            }
            if (!this.getUrlBytes().isEmpty()) {
                GeneratedMessageV3.writeString(output, 2, this.url_);
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
            if (!this.getDescriptionBytes().isEmpty()) {
                size2 += GeneratedMessageV3.computeStringSize(1, this.description_);
            }
            if (!this.getUrlBytes().isEmpty()) {
                size2 += GeneratedMessageV3.computeStringSize(2, this.url_);
            }
            this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
            return size2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Link)) {
                return super.equals(obj);
            }
            Link other = (Link)obj;
            boolean result2 = true;
            result2 = result2 && this.getDescription().equals(other.getDescription());
            result2 = result2 && this.getUrl().equals(other.getUrl());
            result2 = result2 && this.unknownFields.equals(other.unknownFields);
            return result2;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Link.getDescriptor().hashCode();
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getDescription().hashCode();
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getUrl().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Link parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Link parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Link parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Link parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Link parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Link parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Link parseFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Link parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Link parseDelimitedFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
        }

        public static Link parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Link parseFrom(CodedInputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Link parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return Link.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Link prototype) {
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

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return PARSER;
        }

        public Parser<Link> getParserForType() {
            return PARSER;
        }

        @Override
        public Link getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements LinkOrBuilder {
            private Object description_ = "";
            private Object url_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
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
                this.description_ = "";
                this.url_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            @Override
            public Link getDefaultInstanceForType() {
                return Link.getDefaultInstance();
            }

            @Override
            public Link build() {
                Link result2 = this.buildPartial();
                if (!result2.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result2);
                }
                return result2;
            }

            @Override
            public Link buildPartial() {
                Link result2 = new Link(this);
                result2.description_ = this.description_;
                result2.url_ = this.url_;
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
                if (other instanceof Link) {
                    return this.mergeFrom((Link)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Link other) {
                if (other == Link.getDefaultInstance()) {
                    return this;
                }
                if (!other.getDescription().isEmpty()) {
                    this.description_ = other.description_;
                    this.onChanged();
                }
                if (!other.getUrl().isEmpty()) {
                    this.url_ = other.url_;
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
                Link parsedMessage = null;
                try {
                    parsedMessage = (Link)PARSER.parsePartialFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Link)e.getUnfinishedMessage();
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
                this.description_ = Link.getDefaultInstance().getDescription();
                this.onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                Link.checkByteStringIsUtf8(value);
                this.description_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public String getUrl() {
                Object ref = this.url_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    this.url_ = s2;
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getUrlBytes() {
                Object ref = this.url_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.url_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setUrl(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.url_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearUrl() {
                this.url_ = Link.getDefaultInstance().getUrl();
                this.onChanged();
                return this;
            }

            public Builder setUrlBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                Link.checkByteStringIsUtf8(value);
                this.url_ = value;
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

    public static interface LinkOrBuilder
    extends MessageOrBuilder {
        public String getDescription();

        public ByteString getDescriptionBytes();

        public String getUrl();

        public ByteString getUrlBytes();
    }
}

