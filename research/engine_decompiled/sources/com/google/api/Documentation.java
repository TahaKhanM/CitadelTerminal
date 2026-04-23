/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.DocumentationOrBuilder;
import com.google.api.DocumentationProto;
import com.google.api.DocumentationRule;
import com.google.api.DocumentationRuleOrBuilder;
import com.google.api.Page;
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

public final class Documentation
extends GeneratedMessageV3
implements DocumentationOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SUMMARY_FIELD_NUMBER = 1;
    private volatile Object summary_;
    public static final int PAGES_FIELD_NUMBER = 5;
    private List<Page> pages_;
    public static final int RULES_FIELD_NUMBER = 3;
    private List<DocumentationRule> rules_;
    public static final int DOCUMENTATION_ROOT_URL_FIELD_NUMBER = 4;
    private volatile Object documentationRootUrl_;
    public static final int OVERVIEW_FIELD_NUMBER = 2;
    private volatile Object overview_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Documentation DEFAULT_INSTANCE = new Documentation();
    private static final Parser<Documentation> PARSER = new AbstractParser<Documentation>(){

        @Override
        public Documentation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Documentation(input2, extensionRegistry);
        }
    };

    private Documentation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Documentation() {
        this.summary_ = "";
        this.pages_ = Collections.emptyList();
        this.rules_ = Collections.emptyList();
        this.documentationRootUrl_ = "";
        this.overview_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Documentation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block14: while (!done) {
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
                        String s2 = input2.readStringRequireUtf8();
                        this.summary_ = s2;
                        continue block14;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.overview_ = s2;
                        continue block14;
                    }
                    case 26: {
                        if ((mutable_bitField0_ & 4) != 4) {
                            this.rules_ = new ArrayList<DocumentationRule>();
                            mutable_bitField0_ |= 4;
                        }
                        this.rules_.add(input2.readMessage(DocumentationRule.parser(), extensionRegistry));
                        continue block14;
                    }
                    case 34: {
                        String s2 = input2.readStringRequireUtf8();
                        this.documentationRootUrl_ = s2;
                        continue block14;
                    }
                    case 42: 
                }
                if ((mutable_bitField0_ & 2) != 2) {
                    this.pages_ = new ArrayList<Page>();
                    mutable_bitField0_ |= 2;
                }
                this.pages_.add(input2.readMessage(Page.parser(), extensionRegistry));
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
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            if ((mutable_bitField0_ & 2) == 2) {
                this.pages_ = Collections.unmodifiableList(this.pages_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Documentation_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
    }

    @Override
    public String getSummary() {
        Object ref = this.summary_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.summary_ = s2;
        return s2;
    }

    @Override
    public ByteString getSummaryBytes() {
        Object ref = this.summary_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.summary_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public List<Page> getPagesList() {
        return this.pages_;
    }

    @Override
    public List<? extends PageOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    @Override
    public int getPagesCount() {
        return this.pages_.size();
    }

    @Override
    public Page getPages(int index) {
        return this.pages_.get(index);
    }

    @Override
    public PageOrBuilder getPagesOrBuilder(int index) {
        return this.pages_.get(index);
    }

    @Override
    public List<DocumentationRule> getRulesList() {
        return this.rules_;
    }

    @Override
    public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override
    public DocumentationRule getRules(int index) {
        return this.rules_.get(index);
    }

    @Override
    public DocumentationRuleOrBuilder getRulesOrBuilder(int index) {
        return this.rules_.get(index);
    }

    @Override
    public String getDocumentationRootUrl() {
        Object ref = this.documentationRootUrl_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.documentationRootUrl_ = s2;
        return s2;
    }

    @Override
    public ByteString getDocumentationRootUrlBytes() {
        Object ref = this.documentationRootUrl_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.documentationRootUrl_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getOverview() {
        Object ref = this.overview_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.overview_ = s2;
        return s2;
    }

    @Override
    public ByteString getOverviewBytes() {
        Object ref = this.overview_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.overview_ = b;
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
        int i;
        if (!this.getSummaryBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.summary_);
        }
        if (!this.getOverviewBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.overview_);
        }
        for (i = 0; i < this.rules_.size(); ++i) {
            output.writeMessage(3, this.rules_.get(i));
        }
        if (!this.getDocumentationRootUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.documentationRootUrl_);
        }
        for (i = 0; i < this.pages_.size(); ++i) {
            output.writeMessage(5, this.pages_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int i;
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (!this.getSummaryBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.summary_);
        }
        if (!this.getOverviewBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.overview_);
        }
        for (i = 0; i < this.rules_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.rules_.get(i));
        }
        if (!this.getDocumentationRootUrlBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.documentationRootUrl_);
        }
        for (i = 0; i < this.pages_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(5, this.pages_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Documentation)) {
            return super.equals(obj);
        }
        Documentation other = (Documentation)obj;
        boolean result2 = true;
        result2 = result2 && this.getSummary().equals(other.getSummary());
        result2 = result2 && this.getPagesList().equals(other.getPagesList());
        result2 = result2 && this.getRulesList().equals(other.getRulesList());
        result2 = result2 && this.getDocumentationRootUrl().equals(other.getDocumentationRootUrl());
        result2 = result2 && this.getOverview().equals(other.getOverview());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Documentation.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSummary().hashCode();
        if (this.getPagesCount() > 0) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getPagesList().hashCode();
        }
        if (this.getRulesCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getRulesList().hashCode();
        }
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getDocumentationRootUrl().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getOverview().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Documentation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Documentation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Documentation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Documentation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Documentation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Documentation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Documentation parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Documentation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Documentation parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Documentation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Documentation parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Documentation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Documentation.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Documentation prototype) {
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

    public static Documentation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Documentation> parser() {
        return PARSER;
    }

    public Parser<Documentation> getParserForType() {
        return PARSER;
    }

    @Override
    public Documentation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements DocumentationOrBuilder {
        private int bitField0_;
        private Object summary_ = "";
        private List<Page> pages_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> pagesBuilder_;
        private List<DocumentationRule> rules_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> rulesBuilder_;
        private Object documentationRootUrl_ = "";
        private Object overview_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
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
                this.getPagesFieldBuilder();
                this.getRulesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.summary_ = "";
            if (this.pagesBuilder_ == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
            } else {
                this.pagesBuilder_.clear();
            }
            if (this.rulesBuilder_ == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
            } else {
                this.rulesBuilder_.clear();
            }
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        @Override
        public Documentation getDefaultInstanceForType() {
            return Documentation.getDefaultInstance();
        }

        @Override
        public Documentation build() {
            Documentation result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Documentation buildPartial() {
            Documentation result2 = new Documentation(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.summary_ = this.summary_;
            if (this.pagesBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.pages_ = Collections.unmodifiableList(this.pages_);
                    this.bitField0_ &= 0xFFFFFFFD;
                }
                result2.pages_ = this.pages_;
            } else {
                result2.pages_ = this.pagesBuilder_.build();
            }
            if (this.rulesBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= 0xFFFFFFFB;
                }
                result2.rules_ = this.rules_;
            } else {
                result2.rules_ = this.rulesBuilder_.build();
            }
            result2.documentationRootUrl_ = this.documentationRootUrl_;
            result2.overview_ = this.overview_;
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
            if (other instanceof Documentation) {
                return this.mergeFrom((Documentation)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Documentation other) {
            if (other == Documentation.getDefaultInstance()) {
                return this;
            }
            if (!other.getSummary().isEmpty()) {
                this.summary_ = other.summary_;
                this.onChanged();
            }
            if (this.pagesBuilder_ == null) {
                if (!other.pages_.isEmpty()) {
                    if (this.pages_.isEmpty()) {
                        this.pages_ = other.pages_;
                        this.bitField0_ &= 0xFFFFFFFD;
                    } else {
                        this.ensurePagesIsMutable();
                        this.pages_.addAll(other.pages_);
                    }
                    this.onChanged();
                }
            } else if (!other.pages_.isEmpty()) {
                if (this.pagesBuilder_.isEmpty()) {
                    this.pagesBuilder_.dispose();
                    this.pagesBuilder_ = null;
                    this.pages_ = other.pages_;
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.pagesBuilder_ = alwaysUseFieldBuilders ? this.getPagesFieldBuilder() : null;
                } else {
                    this.pagesBuilder_.addAllMessages(other.pages_);
                }
            }
            if (this.rulesBuilder_ == null) {
                if (!other.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = other.rules_;
                        this.bitField0_ &= 0xFFFFFFFB;
                    } else {
                        this.ensureRulesIsMutable();
                        this.rules_.addAll(other.rules_);
                    }
                    this.onChanged();
                }
            } else if (!other.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = other.rules_;
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.rulesBuilder_ = alwaysUseFieldBuilders ? this.getRulesFieldBuilder() : null;
                } else {
                    this.rulesBuilder_.addAllMessages(other.rules_);
                }
            }
            if (!other.getDocumentationRootUrl().isEmpty()) {
                this.documentationRootUrl_ = other.documentationRootUrl_;
                this.onChanged();
            }
            if (!other.getOverview().isEmpty()) {
                this.overview_ = other.overview_;
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
            Documentation parsedMessage = null;
            try {
                parsedMessage = (Documentation)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Documentation)e.getUnfinishedMessage();
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
        public String getSummary() {
            Object ref = this.summary_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.summary_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSummaryBytes() {
            Object ref = this.summary_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.summary_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSummary(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.summary_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSummary() {
            this.summary_ = Documentation.getDefaultInstance().getSummary();
            this.onChanged();
            return this;
        }

        public Builder setSummaryBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Documentation.checkByteStringIsUtf8(value);
            this.summary_ = value;
            this.onChanged();
            return this;
        }

        private void ensurePagesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.pages_ = new ArrayList<Page>(this.pages_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        public List<Page> getPagesList() {
            if (this.pagesBuilder_ == null) {
                return Collections.unmodifiableList(this.pages_);
            }
            return this.pagesBuilder_.getMessageList();
        }

        @Override
        public int getPagesCount() {
            if (this.pagesBuilder_ == null) {
                return this.pages_.size();
            }
            return this.pagesBuilder_.getCount();
        }

        @Override
        public Page getPages(int index) {
            if (this.pagesBuilder_ == null) {
                return this.pages_.get(index);
            }
            return this.pagesBuilder_.getMessage(index);
        }

        public Builder setPages(int index, Page value) {
            if (this.pagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensurePagesIsMutable();
                this.pages_.set(index, value);
                this.onChanged();
            } else {
                this.pagesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setPages(int index, Page.Builder builderForValue) {
            if (this.pagesBuilder_ == null) {
                this.ensurePagesIsMutable();
                this.pages_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.pagesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addPages(Page value) {
            if (this.pagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensurePagesIsMutable();
                this.pages_.add(value);
                this.onChanged();
            } else {
                this.pagesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addPages(int index, Page value) {
            if (this.pagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensurePagesIsMutable();
                this.pages_.add(index, value);
                this.onChanged();
            } else {
                this.pagesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addPages(Page.Builder builderForValue) {
            if (this.pagesBuilder_ == null) {
                this.ensurePagesIsMutable();
                this.pages_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.pagesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addPages(int index, Page.Builder builderForValue) {
            if (this.pagesBuilder_ == null) {
                this.ensurePagesIsMutable();
                this.pages_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.pagesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllPages(Iterable<? extends Page> values) {
            if (this.pagesBuilder_ == null) {
                this.ensurePagesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.pages_);
                this.onChanged();
            } else {
                this.pagesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearPages() {
            if (this.pagesBuilder_ == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
                this.onChanged();
            } else {
                this.pagesBuilder_.clear();
            }
            return this;
        }

        public Builder removePages(int index) {
            if (this.pagesBuilder_ == null) {
                this.ensurePagesIsMutable();
                this.pages_.remove(index);
                this.onChanged();
            } else {
                this.pagesBuilder_.remove(index);
            }
            return this;
        }

        public Page.Builder getPagesBuilder(int index) {
            return this.getPagesFieldBuilder().getBuilder(index);
        }

        @Override
        public PageOrBuilder getPagesOrBuilder(int index) {
            if (this.pagesBuilder_ == null) {
                return this.pages_.get(index);
            }
            return this.pagesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends PageOrBuilder> getPagesOrBuilderList() {
            if (this.pagesBuilder_ != null) {
                return this.pagesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.pages_);
        }

        public Page.Builder addPagesBuilder() {
            return this.getPagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Page.Builder addPagesBuilder(int index) {
            return this.getPagesFieldBuilder().addBuilder(index, Page.getDefaultInstance());
        }

        public List<Page.Builder> getPagesBuilderList() {
            return this.getPagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> getPagesFieldBuilder() {
            if (this.pagesBuilder_ == null) {
                this.pagesBuilder_ = new RepeatedFieldBuilderV3(this.pages_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                this.pages_ = null;
            }
            return this.pagesBuilder_;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.rules_ = new ArrayList<DocumentationRule>(this.rules_);
                this.bitField0_ |= 4;
            }
        }

        @Override
        public List<DocumentationRule> getRulesList() {
            if (this.rulesBuilder_ == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return this.rulesBuilder_.getMessageList();
        }

        @Override
        public int getRulesCount() {
            if (this.rulesBuilder_ == null) {
                return this.rules_.size();
            }
            return this.rulesBuilder_.getCount();
        }

        @Override
        public DocumentationRule getRules(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessage(index);
        }

        public Builder setRules(int index, DocumentationRule value) {
            if (this.rulesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRulesIsMutable();
                this.rules_.set(index, value);
                this.onChanged();
            } else {
                this.rulesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setRules(int index, DocumentationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addRules(DocumentationRule value) {
            if (this.rulesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRulesIsMutable();
                this.rules_.add(value);
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addRules(int index, DocumentationRule value) {
            if (this.rulesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRulesIsMutable();
                this.rules_.add(index, value);
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addRules(DocumentationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addRules(int index, DocumentationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends DocumentationRule> values) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.rules_);
                this.onChanged();
            } else {
                this.rulesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearRules() {
            if (this.rulesBuilder_ == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
                this.onChanged();
            } else {
                this.rulesBuilder_.clear();
            }
            return this;
        }

        public Builder removeRules(int index) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.remove(index);
                this.onChanged();
            } else {
                this.rulesBuilder_.remove(index);
            }
            return this;
        }

        public DocumentationRule.Builder getRulesBuilder(int index) {
            return this.getRulesFieldBuilder().getBuilder(index);
        }

        @Override
        public DocumentationRuleOrBuilder getRulesOrBuilder(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
            if (this.rulesBuilder_ != null) {
                return this.rulesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public DocumentationRule.Builder addRulesBuilder() {
            return this.getRulesFieldBuilder().addBuilder(DocumentationRule.getDefaultInstance());
        }

        public DocumentationRule.Builder addRulesBuilder(int index) {
            return this.getRulesFieldBuilder().addBuilder(index, DocumentationRule.getDefaultInstance());
        }

        public List<DocumentationRule.Builder> getRulesBuilderList() {
            return this.getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3(this.rules_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        @Override
        public String getDocumentationRootUrl() {
            Object ref = this.documentationRootUrl_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.documentationRootUrl_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDocumentationRootUrlBytes() {
            Object ref = this.documentationRootUrl_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.documentationRootUrl_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDocumentationRootUrl(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.documentationRootUrl_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDocumentationRootUrl() {
            this.documentationRootUrl_ = Documentation.getDefaultInstance().getDocumentationRootUrl();
            this.onChanged();
            return this;
        }

        public Builder setDocumentationRootUrlBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Documentation.checkByteStringIsUtf8(value);
            this.documentationRootUrl_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getOverview() {
            Object ref = this.overview_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.overview_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getOverviewBytes() {
            Object ref = this.overview_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.overview_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setOverview(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.overview_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearOverview() {
            this.overview_ = Documentation.getDefaultInstance().getOverview();
            this.onChanged();
            return this;
        }

        public Builder setOverviewBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Documentation.checkByteStringIsUtf8(value);
            this.overview_ = value;
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

