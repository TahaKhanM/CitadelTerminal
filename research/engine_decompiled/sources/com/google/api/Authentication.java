/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthProto;
import com.google.api.AuthProvider;
import com.google.api.AuthProviderOrBuilder;
import com.google.api.AuthenticationOrBuilder;
import com.google.api.AuthenticationRule;
import com.google.api.AuthenticationRuleOrBuilder;
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

public final class Authentication
extends GeneratedMessageV3
implements AuthenticationOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RULES_FIELD_NUMBER = 3;
    private List<AuthenticationRule> rules_;
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    private List<AuthProvider> providers_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Authentication DEFAULT_INSTANCE = new Authentication();
    private static final Parser<Authentication> PARSER = new AbstractParser<Authentication>(){

        @Override
        public Authentication parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Authentication(input2, extensionRegistry);
        }
    };

    private Authentication(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Authentication() {
        this.rules_ = Collections.emptyList();
        this.providers_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Authentication(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
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
                    case 26: {
                        if ((mutable_bitField0_ & 1) != 1) {
                            this.rules_ = new ArrayList<AuthenticationRule>();
                            mutable_bitField0_ |= 1;
                        }
                        this.rules_.add(input2.readMessage(AuthenticationRule.parser(), extensionRegistry));
                        continue block11;
                    }
                    case 34: 
                }
                if ((mutable_bitField0_ & 2) != 2) {
                    this.providers_ = new ArrayList<AuthProvider>();
                    mutable_bitField0_ |= 2;
                }
                this.providers_.add(input2.readMessage(AuthProvider.parser(), extensionRegistry));
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
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            if ((mutable_bitField0_ & 2) == 2) {
                this.providers_ = Collections.unmodifiableList(this.providers_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_Authentication_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
    }

    @Override
    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    @Override
    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override
    public AuthenticationRule getRules(int index) {
        return this.rules_.get(index);
    }

    @Override
    public AuthenticationRuleOrBuilder getRulesOrBuilder(int index) {
        return this.rules_.get(index);
    }

    @Override
    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    @Override
    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    @Override
    public int getProvidersCount() {
        return this.providers_.size();
    }

    @Override
    public AuthProvider getProviders(int index) {
        return this.providers_.get(index);
    }

    @Override
    public AuthProviderOrBuilder getProvidersOrBuilder(int index) {
        return this.providers_.get(index);
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
        for (i = 0; i < this.rules_.size(); ++i) {
            output.writeMessage(3, this.rules_.get(i));
        }
        for (i = 0; i < this.providers_.size(); ++i) {
            output.writeMessage(4, this.providers_.get(i));
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
        for (i = 0; i < this.rules_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.rules_.get(i));
        }
        for (i = 0; i < this.providers_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(4, this.providers_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Authentication)) {
            return super.equals(obj);
        }
        Authentication other = (Authentication)obj;
        boolean result2 = true;
        result2 = result2 && this.getRulesList().equals(other.getRulesList());
        result2 = result2 && this.getProvidersList().equals(other.getProvidersList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Authentication.getDescriptor().hashCode();
        if (this.getRulesCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getRulesList().hashCode();
        }
        if (this.getProvidersCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getProvidersList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Authentication parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Authentication parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Authentication parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Authentication parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Authentication parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Authentication parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Authentication parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Authentication parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Authentication parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Authentication parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Authentication parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Authentication parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Authentication.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Authentication prototype) {
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

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return PARSER;
    }

    public Parser<Authentication> getParserForType() {
        return PARSER;
    }

    @Override
    public Authentication getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuthenticationOrBuilder {
        private int bitField0_;
        private List<AuthenticationRule> rules_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> rulesBuilder_;
        private List<AuthProvider> providers_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> providersBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
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
                this.getProvidersFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.rulesBuilder_ == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.rulesBuilder_.clear();
            }
            if (this.providersBuilder_ == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
            } else {
                this.providersBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        @Override
        public Authentication getDefaultInstanceForType() {
            return Authentication.getDefaultInstance();
        }

        @Override
        public Authentication build() {
            Authentication result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Authentication buildPartial() {
            Authentication result2 = new Authentication(this);
            int from_bitField0_ = this.bitField0_;
            if (this.rulesBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.rules_ = this.rules_;
            } else {
                result2.rules_ = this.rulesBuilder_.build();
            }
            if (this.providersBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.providers_ = Collections.unmodifiableList(this.providers_);
                    this.bitField0_ &= 0xFFFFFFFD;
                }
                result2.providers_ = this.providers_;
            } else {
                result2.providers_ = this.providersBuilder_.build();
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
            if (other instanceof Authentication) {
                return this.mergeFrom((Authentication)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Authentication other) {
            if (other == Authentication.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!other.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = other.rules_;
                        this.bitField0_ &= 0xFFFFFFFE;
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
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.rulesBuilder_ = alwaysUseFieldBuilders ? this.getRulesFieldBuilder() : null;
                } else {
                    this.rulesBuilder_.addAllMessages(other.rules_);
                }
            }
            if (this.providersBuilder_ == null) {
                if (!other.providers_.isEmpty()) {
                    if (this.providers_.isEmpty()) {
                        this.providers_ = other.providers_;
                        this.bitField0_ &= 0xFFFFFFFD;
                    } else {
                        this.ensureProvidersIsMutable();
                        this.providers_.addAll(other.providers_);
                    }
                    this.onChanged();
                }
            } else if (!other.providers_.isEmpty()) {
                if (this.providersBuilder_.isEmpty()) {
                    this.providersBuilder_.dispose();
                    this.providersBuilder_ = null;
                    this.providers_ = other.providers_;
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.providersBuilder_ = alwaysUseFieldBuilders ? this.getProvidersFieldBuilder() : null;
                } else {
                    this.providersBuilder_.addAllMessages(other.providers_);
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
            Authentication parsedMessage = null;
            try {
                parsedMessage = (Authentication)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Authentication)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.rules_ = new ArrayList<AuthenticationRule>(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<AuthenticationRule> getRulesList() {
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
        public AuthenticationRule getRules(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessage(index);
        }

        public Builder setRules(int index, AuthenticationRule value) {
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

        public Builder setRules(int index, AuthenticationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addRules(AuthenticationRule value) {
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

        public Builder addRules(int index, AuthenticationRule value) {
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

        public Builder addRules(AuthenticationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addRules(int index, AuthenticationRule.Builder builderForValue) {
            if (this.rulesBuilder_ == null) {
                this.ensureRulesIsMutable();
                this.rules_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.rulesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> values) {
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
                this.bitField0_ &= 0xFFFFFFFE;
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

        public AuthenticationRule.Builder getRulesBuilder(int index) {
            return this.getRulesFieldBuilder().getBuilder(index);
        }

        @Override
        public AuthenticationRuleOrBuilder getRulesOrBuilder(int index) {
            if (this.rulesBuilder_ == null) {
                return this.rules_.get(index);
            }
            return this.rulesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
            if (this.rulesBuilder_ != null) {
                return this.rulesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public AuthenticationRule.Builder addRulesBuilder() {
            return this.getRulesFieldBuilder().addBuilder(AuthenticationRule.getDefaultInstance());
        }

        public AuthenticationRule.Builder addRulesBuilder(int index) {
            return this.getRulesFieldBuilder().addBuilder(index, AuthenticationRule.getDefaultInstance());
        }

        public List<AuthenticationRule.Builder> getRulesBuilderList() {
            return this.getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3(this.rules_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        private void ensureProvidersIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.providers_ = new ArrayList<AuthProvider>(this.providers_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        public List<AuthProvider> getProvidersList() {
            if (this.providersBuilder_ == null) {
                return Collections.unmodifiableList(this.providers_);
            }
            return this.providersBuilder_.getMessageList();
        }

        @Override
        public int getProvidersCount() {
            if (this.providersBuilder_ == null) {
                return this.providers_.size();
            }
            return this.providersBuilder_.getCount();
        }

        @Override
        public AuthProvider getProviders(int index) {
            if (this.providersBuilder_ == null) {
                return this.providers_.get(index);
            }
            return this.providersBuilder_.getMessage(index);
        }

        public Builder setProviders(int index, AuthProvider value) {
            if (this.providersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureProvidersIsMutable();
                this.providers_.set(index, value);
                this.onChanged();
            } else {
                this.providersBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setProviders(int index, AuthProvider.Builder builderForValue) {
            if (this.providersBuilder_ == null) {
                this.ensureProvidersIsMutable();
                this.providers_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.providersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addProviders(AuthProvider value) {
            if (this.providersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureProvidersIsMutable();
                this.providers_.add(value);
                this.onChanged();
            } else {
                this.providersBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addProviders(int index, AuthProvider value) {
            if (this.providersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureProvidersIsMutable();
                this.providers_.add(index, value);
                this.onChanged();
            } else {
                this.providersBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builderForValue) {
            if (this.providersBuilder_ == null) {
                this.ensureProvidersIsMutable();
                this.providers_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.providersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addProviders(int index, AuthProvider.Builder builderForValue) {
            if (this.providersBuilder_ == null) {
                this.ensureProvidersIsMutable();
                this.providers_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.providersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> values) {
            if (this.providersBuilder_ == null) {
                this.ensureProvidersIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.providers_);
                this.onChanged();
            } else {
                this.providersBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearProviders() {
            if (this.providersBuilder_ == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
                this.onChanged();
            } else {
                this.providersBuilder_.clear();
            }
            return this;
        }

        public Builder removeProviders(int index) {
            if (this.providersBuilder_ == null) {
                this.ensureProvidersIsMutable();
                this.providers_.remove(index);
                this.onChanged();
            } else {
                this.providersBuilder_.remove(index);
            }
            return this;
        }

        public AuthProvider.Builder getProvidersBuilder(int index) {
            return this.getProvidersFieldBuilder().getBuilder(index);
        }

        @Override
        public AuthProviderOrBuilder getProvidersOrBuilder(int index) {
            if (this.providersBuilder_ == null) {
                return this.providers_.get(index);
            }
            return this.providersBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
            if (this.providersBuilder_ != null) {
                return this.providersBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.providers_);
        }

        public AuthProvider.Builder addProvidersBuilder() {
            return this.getProvidersFieldBuilder().addBuilder(AuthProvider.getDefaultInstance());
        }

        public AuthProvider.Builder addProvidersBuilder(int index) {
            return this.getProvidersFieldBuilder().addBuilder(index, AuthProvider.getDefaultInstance());
        }

        public List<AuthProvider.Builder> getProvidersBuilderList() {
            return this.getProvidersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> getProvidersFieldBuilder() {
            if (this.providersBuilder_ == null) {
                this.providersBuilder_ = new RepeatedFieldBuilderV3(this.providers_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                this.providers_ = null;
            }
            return this.providersBuilder_;
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

