/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.CustomHttpPattern;
import com.google.api.CustomHttpPatternOrBuilder;
import com.google.api.HttpProto;
import com.google.api.HttpRuleOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HttpRule
extends GeneratedMessageV3
implements HttpRuleOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    private int patternCase_ = 0;
    private Object pattern_;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private volatile Object selector_;
    public static final int GET_FIELD_NUMBER = 2;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    public static final int BODY_FIELD_NUMBER = 7;
    private volatile Object body_;
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    private List<HttpRule> additionalBindings_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final HttpRule DEFAULT_INSTANCE = new HttpRule();
    private static final Parser<HttpRule> PARSER = new AbstractParser<HttpRule>(){

        @Override
        public HttpRule parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new HttpRule(input2, extensionRegistry);
        }
    };

    private HttpRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private HttpRule() {
        this.selector_ = "";
        this.body_ = "";
        this.additionalBindings_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HttpRule(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block18: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block18;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block18;
                        done = true;
                        continue block18;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.selector_ = s2;
                        continue block18;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.patternCase_ = 2;
                        this.pattern_ = s2;
                        continue block18;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.patternCase_ = 3;
                        this.pattern_ = s2;
                        continue block18;
                    }
                    case 34: {
                        String s2 = input2.readStringRequireUtf8();
                        this.patternCase_ = 4;
                        this.pattern_ = s2;
                        continue block18;
                    }
                    case 42: {
                        String s2 = input2.readStringRequireUtf8();
                        this.patternCase_ = 5;
                        this.pattern_ = s2;
                        continue block18;
                    }
                    case 50: {
                        String s2 = input2.readStringRequireUtf8();
                        this.patternCase_ = 6;
                        this.pattern_ = s2;
                        continue block18;
                    }
                    case 58: {
                        String s2 = input2.readStringRequireUtf8();
                        this.body_ = s2;
                        continue block18;
                    }
                    case 66: {
                        CustomHttpPattern.Builder subBuilder = null;
                        if (this.patternCase_ == 8) {
                            subBuilder = ((CustomHttpPattern)this.pattern_).toBuilder();
                        }
                        this.pattern_ = input2.readMessage(CustomHttpPattern.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom((CustomHttpPattern)this.pattern_);
                            this.pattern_ = subBuilder.buildPartial();
                        }
                        this.patternCase_ = 8;
                        continue block18;
                    }
                    case 90: 
                }
                if ((mutable_bitField0_ & 0x100) != 256) {
                    this.additionalBindings_ = new ArrayList<HttpRule>();
                    mutable_bitField0_ |= 0x100;
                }
                this.additionalBindings_.add(input2.readMessage(HttpRule.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x100) == 256) {
                this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpProto.internal_static_google_api_HttpRule_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
    }

    @Override
    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    @Override
    public String getSelector() {
        Object ref = this.selector_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.selector_ = s2;
        return s2;
    }

    @Override
    public ByteString getSelectorBytes() {
        Object ref = this.selector_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.selector_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getGet() {
        Object ref = "";
        if (this.patternCase_ == 2) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.patternCase_ == 2) {
            this.pattern_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getGetBytes() {
        Object ref = "";
        if (this.patternCase_ == 2) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.patternCase_ == 2) {
                this.pattern_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getPut() {
        Object ref = "";
        if (this.patternCase_ == 3) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.patternCase_ == 3) {
            this.pattern_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getPutBytes() {
        Object ref = "";
        if (this.patternCase_ == 3) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.patternCase_ == 3) {
                this.pattern_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getPost() {
        Object ref = "";
        if (this.patternCase_ == 4) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.patternCase_ == 4) {
            this.pattern_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getPostBytes() {
        Object ref = "";
        if (this.patternCase_ == 4) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.patternCase_ == 4) {
                this.pattern_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getDelete() {
        Object ref = "";
        if (this.patternCase_ == 5) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.patternCase_ == 5) {
            this.pattern_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getDeleteBytes() {
        Object ref = "";
        if (this.patternCase_ == 5) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.patternCase_ == 5) {
                this.pattern_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getPatch() {
        Object ref = "";
        if (this.patternCase_ == 6) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        if (this.patternCase_ == 6) {
            this.pattern_ = s2;
        }
        return s2;
    }

    @Override
    public ByteString getPatchBytes() {
        Object ref = "";
        if (this.patternCase_ == 6) {
            ref = this.pattern_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            if (this.patternCase_ == 6) {
                this.pattern_ = b;
            }
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    @Override
    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern)this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override
    public CustomHttpPatternOrBuilder getCustomOrBuilder() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern)this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override
    public String getBody() {
        Object ref = this.body_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.body_ = s2;
        return s2;
    }

    @Override
    public ByteString getBodyBytes() {
        Object ref = this.body_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.body_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    @Override
    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    @Override
    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    @Override
    public HttpRule getAdditionalBindings(int index) {
        return this.additionalBindings_.get(index);
    }

    @Override
    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int index) {
        return this.additionalBindings_.get(index);
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
        if (!this.getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.selector_);
        }
        if (this.patternCase_ == 2) {
            GeneratedMessageV3.writeString(output, 2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            GeneratedMessageV3.writeString(output, 3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            GeneratedMessageV3.writeString(output, 4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            GeneratedMessageV3.writeString(output, 5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            GeneratedMessageV3.writeString(output, 6, this.pattern_);
        }
        if (!this.getBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 7, this.body_);
        }
        if (this.patternCase_ == 8) {
            output.writeMessage(8, (CustomHttpPattern)this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); ++i) {
            output.writeMessage(11, this.additionalBindings_.get(i));
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
        if (!this.getSelectorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        if (this.patternCase_ == 2) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            size2 += GeneratedMessageV3.computeStringSize(6, this.pattern_);
        }
        if (!this.getBodyBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(7, this.body_);
        }
        if (this.patternCase_ == 8) {
            size2 += CodedOutputStream.computeMessageSize(8, (CustomHttpPattern)this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(11, this.additionalBindings_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpRule)) {
            return super.equals(obj);
        }
        HttpRule other = (HttpRule)obj;
        boolean result2 = true;
        result2 = result2 && this.getSelector().equals(other.getSelector());
        result2 = result2 && this.getBody().equals(other.getBody());
        result2 = result2 && this.getAdditionalBindingsList().equals(other.getAdditionalBindingsList());
        boolean bl = result2 = result2 && this.getPatternCase().equals(other.getPatternCase());
        if (!result2) {
            return false;
        }
        switch (this.patternCase_) {
            case 2: {
                result2 = result2 && this.getGet().equals(other.getGet());
                break;
            }
            case 3: {
                result2 = result2 && this.getPut().equals(other.getPut());
                break;
            }
            case 4: {
                result2 = result2 && this.getPost().equals(other.getPost());
                break;
            }
            case 5: {
                result2 = result2 && this.getDelete().equals(other.getDelete());
                break;
            }
            case 6: {
                result2 = result2 && this.getPatch().equals(other.getPatch());
                break;
            }
            case 8: {
                result2 = result2 && this.getCustom().equals(other.getCustom());
                break;
            }
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
        hash = 19 * hash + HttpRule.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSelector().hashCode();
        hash = 37 * hash + 7;
        hash = 53 * hash + this.getBody().hashCode();
        if (this.getAdditionalBindingsCount() > 0) {
            hash = 37 * hash + 11;
            hash = 53 * hash + this.getAdditionalBindingsList().hashCode();
        }
        switch (this.patternCase_) {
            case 2: {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getGet().hashCode();
                break;
            }
            case 3: {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getPut().hashCode();
                break;
            }
            case 4: {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getPost().hashCode();
                break;
            }
            case 5: {
                hash = 37 * hash + 5;
                hash = 53 * hash + this.getDelete().hashCode();
                break;
            }
            case 6: {
                hash = 37 * hash + 6;
                hash = 53 * hash + this.getPatch().hashCode();
                break;
            }
            case 8: {
                hash = 37 * hash + 8;
                hash = 53 * hash + this.getCustom().hashCode();
                break;
            }
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static HttpRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HttpRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HttpRule parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static HttpRule parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static HttpRule parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static HttpRule parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static HttpRule parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static HttpRule parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return HttpRule.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpRule prototype) {
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

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return PARSER;
    }

    public Parser<HttpRule> getParserForType() {
        return PARSER;
    }

    @Override
    public HttpRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements HttpRuleOrBuilder {
        private int patternCase_ = 0;
        private Object pattern_;
        private int bitField0_;
        private Object selector_ = "";
        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> customBuilder_;
        private Object body_ = "";
        private List<HttpRule> additionalBindings_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> additionalBindingsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
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
                this.getAdditionalBindingsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.body_ = "";
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFEFF;
            } else {
                this.additionalBindingsBuilder_.clear();
            }
            this.patternCase_ = 0;
            this.pattern_ = null;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        @Override
        public HttpRule getDefaultInstanceForType() {
            return HttpRule.getDefaultInstance();
        }

        @Override
        public HttpRule build() {
            HttpRule result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public HttpRule buildPartial() {
            HttpRule result2 = new HttpRule(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.selector_ = this.selector_;
            if (this.patternCase_ == 2) {
                result2.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 3) {
                result2.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 4) {
                result2.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 5) {
                result2.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 6) {
                result2.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 8) {
                if (this.customBuilder_ == null) {
                    result2.pattern_ = this.pattern_;
                } else {
                    result2.pattern_ = this.customBuilder_.build();
                }
            }
            result2.body_ = this.body_;
            if (this.additionalBindingsBuilder_ == null) {
                if ((this.bitField0_ & 0x100) == 256) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                    this.bitField0_ &= 0xFFFFFEFF;
                }
                result2.additionalBindings_ = this.additionalBindings_;
            } else {
                result2.additionalBindings_ = this.additionalBindingsBuilder_.build();
            }
            result2.bitField0_ = to_bitField0_;
            result2.patternCase_ = this.patternCase_;
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
            if (other instanceof HttpRule) {
                return this.mergeFrom((HttpRule)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(HttpRule other) {
            if (other == HttpRule.getDefaultInstance()) {
                return this;
            }
            if (!other.getSelector().isEmpty()) {
                this.selector_ = other.selector_;
                this.onChanged();
            }
            if (!other.getBody().isEmpty()) {
                this.body_ = other.body_;
                this.onChanged();
            }
            if (this.additionalBindingsBuilder_ == null) {
                if (!other.additionalBindings_.isEmpty()) {
                    if (this.additionalBindings_.isEmpty()) {
                        this.additionalBindings_ = other.additionalBindings_;
                        this.bitField0_ &= 0xFFFFFEFF;
                    } else {
                        this.ensureAdditionalBindingsIsMutable();
                        this.additionalBindings_.addAll(other.additionalBindings_);
                    }
                    this.onChanged();
                }
            } else if (!other.additionalBindings_.isEmpty()) {
                if (this.additionalBindingsBuilder_.isEmpty()) {
                    this.additionalBindingsBuilder_.dispose();
                    this.additionalBindingsBuilder_ = null;
                    this.additionalBindings_ = other.additionalBindings_;
                    this.bitField0_ &= 0xFFFFFEFF;
                    this.additionalBindingsBuilder_ = alwaysUseFieldBuilders ? this.getAdditionalBindingsFieldBuilder() : null;
                } else {
                    this.additionalBindingsBuilder_.addAllMessages(other.additionalBindings_);
                }
            }
            switch (other.getPatternCase()) {
                case GET: {
                    this.patternCase_ = 2;
                    this.pattern_ = other.pattern_;
                    this.onChanged();
                    break;
                }
                case PUT: {
                    this.patternCase_ = 3;
                    this.pattern_ = other.pattern_;
                    this.onChanged();
                    break;
                }
                case POST: {
                    this.patternCase_ = 4;
                    this.pattern_ = other.pattern_;
                    this.onChanged();
                    break;
                }
                case DELETE: {
                    this.patternCase_ = 5;
                    this.pattern_ = other.pattern_;
                    this.onChanged();
                    break;
                }
                case PATCH: {
                    this.patternCase_ = 6;
                    this.pattern_ = other.pattern_;
                    this.onChanged();
                    break;
                }
                case CUSTOM: {
                    this.mergeCustom(other.getCustom());
                    break;
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
            HttpRule parsedMessage = null;
            try {
                parsedMessage = (HttpRule)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (HttpRule)e.getUnfinishedMessage();
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
        public PatternCase getPatternCase() {
            return PatternCase.forNumber(this.patternCase_);
        }

        public Builder clearPattern() {
            this.patternCase_ = 0;
            this.pattern_ = null;
            this.onChanged();
            return this;
        }

        @Override
        public String getSelector() {
            Object ref = this.selector_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.selector_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSelectorBytes() {
            Object ref = this.selector_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.selector_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSelector(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = HttpRule.getDefaultInstance().getSelector();
            this.onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getGet() {
            Object ref = "";
            if (this.patternCase_ == 2) {
                ref = this.pattern_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.patternCase_ == 2) {
                    this.pattern_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getGetBytes() {
            Object ref = "";
            if (this.patternCase_ == 2) {
                ref = this.pattern_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.patternCase_ == 2) {
                    this.pattern_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setGet(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 2;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearGet() {
            if (this.patternCase_ == 2) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setGetBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.patternCase_ = 2;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getPut() {
            Object ref = "";
            if (this.patternCase_ == 3) {
                ref = this.pattern_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.patternCase_ == 3) {
                    this.pattern_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPutBytes() {
            Object ref = "";
            if (this.patternCase_ == 3) {
                ref = this.pattern_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.patternCase_ == 3) {
                    this.pattern_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPut(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 3;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPut() {
            if (this.patternCase_ == 3) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setPutBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.patternCase_ = 3;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getPost() {
            Object ref = "";
            if (this.patternCase_ == 4) {
                ref = this.pattern_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.patternCase_ == 4) {
                    this.pattern_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPostBytes() {
            Object ref = "";
            if (this.patternCase_ == 4) {
                ref = this.pattern_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.patternCase_ == 4) {
                    this.pattern_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPost(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 4;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPost() {
            if (this.patternCase_ == 4) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setPostBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.patternCase_ = 4;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getDelete() {
            Object ref = "";
            if (this.patternCase_ == 5) {
                ref = this.pattern_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.patternCase_ == 5) {
                    this.pattern_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDeleteBytes() {
            Object ref = "";
            if (this.patternCase_ == 5) {
                ref = this.pattern_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.patternCase_ == 5) {
                    this.pattern_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDelete(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 5;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDelete() {
            if (this.patternCase_ == 5) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setDeleteBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.patternCase_ = 5;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getPatch() {
            Object ref = "";
            if (this.patternCase_ == 6) {
                ref = this.pattern_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (this.patternCase_ == 6) {
                    this.pattern_ = s2;
                }
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPatchBytes() {
            Object ref = "";
            if (this.patternCase_ == 6) {
                ref = this.pattern_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                if (this.patternCase_ == 6) {
                    this.pattern_ = b;
                }
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPatch(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 6;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPatch() {
            if (this.patternCase_ == 6) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                this.onChanged();
            }
            return this;
        }

        public Builder setPatchBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.patternCase_ = 6;
            this.pattern_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasCustom() {
            return this.patternCase_ == 8;
        }

        @Override
        public CustomHttpPattern getCustom() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ == 8) {
                    return (CustomHttpPattern)this.pattern_;
                }
                return CustomHttpPattern.getDefaultInstance();
            }
            if (this.patternCase_ == 8) {
                return this.customBuilder_.getMessage();
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        public Builder setCustom(CustomHttpPattern value) {
            if (this.customBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.pattern_ = value;
                this.onChanged();
            } else {
                this.customBuilder_.setMessage(value);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builderForValue) {
            if (this.customBuilder_ == null) {
                this.pattern_ = builderForValue.build();
                this.onChanged();
            } else {
                this.customBuilder_.setMessage(builderForValue.build());
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern value) {
            if (this.customBuilder_ == null) {
                this.pattern_ = this.patternCase_ == 8 && this.pattern_ != CustomHttpPattern.getDefaultInstance() ? CustomHttpPattern.newBuilder((CustomHttpPattern)this.pattern_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                if (this.patternCase_ == 8) {
                    this.customBuilder_.mergeFrom(value);
                }
                this.customBuilder_.setMessage(value);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder clearCustom() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                    this.onChanged();
                }
            } else {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                }
                this.customBuilder_.clear();
            }
            return this;
        }

        public CustomHttpPattern.Builder getCustomBuilder() {
            return this.getCustomFieldBuilder().getBuilder();
        }

        @Override
        public CustomHttpPatternOrBuilder getCustomOrBuilder() {
            if (this.patternCase_ == 8 && this.customBuilder_ != null) {
                return this.customBuilder_.getMessageOrBuilder();
            }
            if (this.patternCase_ == 8) {
                return (CustomHttpPattern)this.pattern_;
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> getCustomFieldBuilder() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ != 8) {
                    this.pattern_ = CustomHttpPattern.getDefaultInstance();
                }
                this.customBuilder_ = new SingleFieldBuilderV3((CustomHttpPattern)this.pattern_, this.getParentForChildren(), this.isClean());
                this.pattern_ = null;
            }
            this.patternCase_ = 8;
            this.onChanged();
            return this.customBuilder_;
        }

        @Override
        public String getBody() {
            Object ref = this.body_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.body_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getBodyBytes() {
            Object ref = this.body_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.body_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setBody(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.body_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearBody() {
            this.body_ = HttpRule.getDefaultInstance().getBody();
            this.onChanged();
            return this;
        }

        public Builder setBodyBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            HttpRule.checkByteStringIsUtf8(value);
            this.body_ = value;
            this.onChanged();
            return this;
        }

        private void ensureAdditionalBindingsIsMutable() {
            if ((this.bitField0_ & 0x100) != 256) {
                this.additionalBindings_ = new ArrayList<HttpRule>(this.additionalBindings_);
                this.bitField0_ |= 0x100;
            }
        }

        @Override
        public List<HttpRule> getAdditionalBindingsList() {
            if (this.additionalBindingsBuilder_ == null) {
                return Collections.unmodifiableList(this.additionalBindings_);
            }
            return this.additionalBindingsBuilder_.getMessageList();
        }

        @Override
        public int getAdditionalBindingsCount() {
            if (this.additionalBindingsBuilder_ == null) {
                return this.additionalBindings_.size();
            }
            return this.additionalBindingsBuilder_.getCount();
        }

        @Override
        public HttpRule getAdditionalBindings(int index) {
            if (this.additionalBindingsBuilder_ == null) {
                return this.additionalBindings_.get(index);
            }
            return this.additionalBindingsBuilder_.getMessage(index);
        }

        public Builder setAdditionalBindings(int index, HttpRule value) {
            if (this.additionalBindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(index, value);
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setAdditionalBindings(int index, Builder builderForValue) {
            if (this.additionalBindingsBuilder_ == null) {
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(HttpRule value) {
            if (this.additionalBindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(value);
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addAdditionalBindings(int index, HttpRule value) {
            if (this.additionalBindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(index, value);
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addAdditionalBindings(Builder builderForValue) {
            if (this.additionalBindingsBuilder_ == null) {
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(int index, Builder builderForValue) {
            if (this.additionalBindingsBuilder_ == null) {
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> values) {
            if (this.additionalBindingsBuilder_ == null) {
                this.ensureAdditionalBindingsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.additionalBindings_);
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearAdditionalBindings() {
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFEFF;
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.clear();
            }
            return this;
        }

        public Builder removeAdditionalBindings(int index) {
            if (this.additionalBindingsBuilder_ == null) {
                this.ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.remove(index);
                this.onChanged();
            } else {
                this.additionalBindingsBuilder_.remove(index);
            }
            return this;
        }

        public Builder getAdditionalBindingsBuilder(int index) {
            return this.getAdditionalBindingsFieldBuilder().getBuilder(index);
        }

        @Override
        public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int index) {
            if (this.additionalBindingsBuilder_ == null) {
                return this.additionalBindings_.get(index);
            }
            return this.additionalBindingsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
            if (this.additionalBindingsBuilder_ != null) {
                return this.additionalBindingsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.additionalBindings_);
        }

        public Builder addAdditionalBindingsBuilder() {
            return this.getAdditionalBindingsFieldBuilder().addBuilder(HttpRule.getDefaultInstance());
        }

        public Builder addAdditionalBindingsBuilder(int index) {
            return this.getAdditionalBindingsFieldBuilder().addBuilder(index, HttpRule.getDefaultInstance());
        }

        public List<Builder> getAdditionalBindingsBuilderList() {
            return this.getAdditionalBindingsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> getAdditionalBindingsFieldBuilder() {
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindingsBuilder_ = new RepeatedFieldBuilderV3(this.additionalBindings_, (this.bitField0_ & 0x100) == 256, this.getParentForChildren(), this.isClean());
                this.additionalBindings_ = null;
            }
            return this.additionalBindingsBuilder_;
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

    public static enum PatternCase implements Internal.EnumLite
    {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);

        private final int value;

        private PatternCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static PatternCase valueOf(int value) {
            return PatternCase.forNumber(value);
        }

        public static PatternCase forNumber(int value) {
            switch (value) {
                case 2: {
                    return GET;
                }
                case 3: {
                    return PUT;
                }
                case 4: {
                    return POST;
                }
                case 5: {
                    return DELETE;
                }
                case 6: {
                    return PATCH;
                }
                case 8: {
                    return CUSTOM;
                }
                case 0: {
                    return PATTERN_NOT_SET;
                }
            }
            return null;
        }

        @Override
        public int getNumber() {
            return this.value;
        }
    }
}

