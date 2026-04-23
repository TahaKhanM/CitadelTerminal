/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.UsageOrBuilder;
import com.google.api.UsageProto;
import com.google.api.UsageRule;
import com.google.api.UsageRuleOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Usage
extends GeneratedMessageV3
implements UsageOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int REQUIREMENTS_FIELD_NUMBER = 1;
    private LazyStringList requirements_;
    public static final int RULES_FIELD_NUMBER = 6;
    private List<UsageRule> rules_;
    public static final int PRODUCER_NOTIFICATION_CHANNEL_FIELD_NUMBER = 7;
    private volatile Object producerNotificationChannel_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Usage DEFAULT_INSTANCE = new Usage();
    private static final Parser<Usage> PARSER = new AbstractParser<Usage>(){

        @Override
        public Usage parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Usage(input2, extensionRegistry);
        }
    };

    private Usage(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Usage() {
        this.requirements_ = LazyStringArrayList.EMPTY;
        this.rules_ = Collections.emptyList();
        this.producerNotificationChannel_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Usage(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block12: while (!done) {
                String s2;
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
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 1) != 1) {
                            this.requirements_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 1;
                        }
                        this.requirements_.add(s2);
                        continue block12;
                    }
                    case 50: {
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.rules_ = new ArrayList<UsageRule>();
                            mutable_bitField0_ |= 2;
                        }
                        this.rules_.add(input2.readMessage(UsageRule.parser(), extensionRegistry));
                        continue block12;
                    }
                    case 58: 
                }
                s2 = input2.readStringRequireUtf8();
                this.producerNotificationChannel_ = s2;
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
                this.requirements_ = this.requirements_.getUnmodifiableView();
            }
            if ((mutable_bitField0_ & 2) == 2) {
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return UsageProto.internal_static_google_api_Usage_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
    }

    public ProtocolStringList getRequirementsList() {
        return this.requirements_;
    }

    @Override
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override
    public String getRequirements(int index) {
        return (String)this.requirements_.get(index);
    }

    @Override
    public ByteString getRequirementsBytes(int index) {
        return this.requirements_.getByteString(index);
    }

    @Override
    public List<UsageRule> getRulesList() {
        return this.rules_;
    }

    @Override
    public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override
    public UsageRule getRules(int index) {
        return this.rules_.get(index);
    }

    @Override
    public UsageRuleOrBuilder getRulesOrBuilder(int index) {
        return this.rules_.get(index);
    }

    @Override
    public String getProducerNotificationChannel() {
        Object ref = this.producerNotificationChannel_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.producerNotificationChannel_ = s2;
        return s2;
    }

    @Override
    public ByteString getProducerNotificationChannelBytes() {
        Object ref = this.producerNotificationChannel_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.producerNotificationChannel_ = b;
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
        for (i = 0; i < this.requirements_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 1, this.requirements_.getRaw(i));
        }
        for (i = 0; i < this.rules_.size(); ++i) {
            output.writeMessage(6, this.rules_.get(i));
        }
        if (!this.getProducerNotificationChannelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 7, this.producerNotificationChannel_);
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
        int dataSize = 0;
        for (int i = 0; i < this.requirements_.size(); ++i) {
            dataSize += Usage.computeStringSizeNoTag(this.requirements_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getRequirementsList().size();
        for (int i = 0; i < this.rules_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(6, this.rules_.get(i));
        }
        if (!this.getProducerNotificationChannelBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(7, this.producerNotificationChannel_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Usage)) {
            return super.equals(obj);
        }
        Usage other = (Usage)obj;
        boolean result2 = true;
        result2 = result2 && this.getRequirementsList().equals(other.getRequirementsList());
        result2 = result2 && this.getRulesList().equals(other.getRulesList());
        result2 = result2 && this.getProducerNotificationChannel().equals(other.getProducerNotificationChannel());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Usage.getDescriptor().hashCode();
        if (this.getRequirementsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getRequirementsList().hashCode();
        }
        if (this.getRulesCount() > 0) {
            hash = 37 * hash + 6;
            hash = 53 * hash + this.getRulesList().hashCode();
        }
        hash = 37 * hash + 7;
        hash = 53 * hash + this.getProducerNotificationChannel().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Usage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Usage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Usage parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Usage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Usage parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Usage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Usage parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Usage parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Usage parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Usage parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Usage parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Usage parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Usage.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Usage prototype) {
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

    public static Usage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Usage> parser() {
        return PARSER;
    }

    public Parser<Usage> getParserForType() {
        return PARSER;
    }

    @Override
    public Usage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements UsageOrBuilder {
        private int bitField0_;
        private LazyStringList requirements_ = LazyStringArrayList.EMPTY;
        private List<UsageRule> rules_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> rulesBuilder_;
        private Object producerNotificationChannel_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
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
                this.getRulesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            if (this.rulesBuilder_ == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
            } else {
                this.rulesBuilder_.clear();
            }
            this.producerNotificationChannel_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override
        public Usage getDefaultInstanceForType() {
            return Usage.getDefaultInstance();
        }

        @Override
        public Usage build() {
            Usage result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Usage buildPartial() {
            Usage result2 = new Usage(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((this.bitField0_ & 1) == 1) {
                this.requirements_ = this.requirements_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFE;
            }
            result2.requirements_ = this.requirements_;
            if (this.rulesBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= 0xFFFFFFFD;
                }
                result2.rules_ = this.rules_;
            } else {
                result2.rules_ = this.rulesBuilder_.build();
            }
            result2.producerNotificationChannel_ = this.producerNotificationChannel_;
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
            if (other instanceof Usage) {
                return this.mergeFrom((Usage)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Usage other) {
            if (other == Usage.getDefaultInstance()) {
                return this;
            }
            if (!other.requirements_.isEmpty()) {
                if (this.requirements_.isEmpty()) {
                    this.requirements_ = other.requirements_;
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.ensureRequirementsIsMutable();
                    this.requirements_.addAll(other.requirements_);
                }
                this.onChanged();
            }
            if (this.rulesBuilder_ == null) {
                if (!other.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = other.rules_;
                        this.bitField0_ &= 0xFFFFFFFD;
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
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.rulesBuilder_ = alwaysUseFieldBuilders ? this.getRulesFieldBuilder() : null;
                } else {
                    this.rulesBuilder_.addAllMessages(other.rules_);
                }
            }
            if (!other.getProducerNotificationChannel().isEmpty()) {
                this.producerNotificationChannel_ = other.producerNotificationChannel_;
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
            Usage parsedMessage = null;
            try {
                parsedMessage = (Usage)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Usage)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.requirements_ = new LazyStringArrayList(this.requirements_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getRequirementsList() {
            return this.requirements_.getUnmodifiableView();
        }

        @Override
        public int getRequirementsCount() {
            return this.requirements_.size();
        }

        @Override
        public String getRequirements(int index) {
            return (String)this.requirements_.get(index);
        }

        @Override
        public ByteString getRequirementsBytes(int index) {
            return this.requirements_.getByteString(index);
        }

        public Builder setRequirements(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRequirementsIsMutable();
            this.requirements_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addRequirements(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRequirementsIsMutable();
            this.requirements_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllRequirements(Iterable<String> values) {
            this.ensureRequirementsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.requirements_);
            this.onChanged();
            return this;
        }

        public Builder clearRequirements() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }

        public Builder addRequirementsBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Usage.checkByteStringIsUtf8(value);
            this.ensureRequirementsIsMutable();
            this.requirements_.add(value);
            this.onChanged();
            return this;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.rules_ = new ArrayList<UsageRule>(this.rules_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        public List<UsageRule> getRulesList() {
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
        public UsageRule getRules(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessage(index);
        }

        public Builder setRules(int index, UsageRule value) {
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

        public Builder setRules(int index, UsageRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addRules(UsageRule value) {
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

        public Builder addRules(int index, UsageRule value) {
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

        public Builder addRules(UsageRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addRules(int index, UsageRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends UsageRule> values) {
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
                this.bitField0_ &= 0xFFFFFFFD;
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

        public UsageRule.Builder getRulesBuilder(int index) {
            return this.getRulesFieldBuilder().getBuilder(index);
        }

        @Override
        public UsageRuleOrBuilder getRulesOrBuilder(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
            if (this.rulesBuilder_ != null) {
                return this.rulesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public UsageRule.Builder addRulesBuilder() {
            return this.getRulesFieldBuilder().addBuilder(UsageRule.getDefaultInstance());
        }

        public UsageRule.Builder addRulesBuilder(int index) {
            return this.getRulesFieldBuilder().addBuilder(index, UsageRule.getDefaultInstance());
        }

        public List<UsageRule.Builder> getRulesBuilderList() {
            return this.getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3(this.rules_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        @Override
        public String getProducerNotificationChannel() {
            Object ref = this.producerNotificationChannel_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.producerNotificationChannel_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getProducerNotificationChannelBytes() {
            Object ref = this.producerNotificationChannel_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.producerNotificationChannel_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setProducerNotificationChannel(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.producerNotificationChannel_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearProducerNotificationChannel() {
            this.producerNotificationChannel_ = Usage.getDefaultInstance().getProducerNotificationChannel();
            this.onChanged();
            return this;
        }

        public Builder setProducerNotificationChannelBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Usage.checkByteStringIsUtf8(value);
            this.producerNotificationChannel_ = value;
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

