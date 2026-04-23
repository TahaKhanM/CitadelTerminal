/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthProto;
import com.google.api.AuthRequirement;
import com.google.api.AuthRequirementOrBuilder;
import com.google.api.AuthenticationRuleOrBuilder;
import com.google.api.OAuthRequirements;
import com.google.api.OAuthRequirementsOrBuilder;
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

public final class AuthenticationRule
extends GeneratedMessageV3
implements AuthenticationRuleOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private volatile Object selector_;
    public static final int OAUTH_FIELD_NUMBER = 2;
    private OAuthRequirements oauth_;
    public static final int ALLOW_WITHOUT_CREDENTIAL_FIELD_NUMBER = 5;
    private boolean allowWithoutCredential_;
    public static final int REQUIREMENTS_FIELD_NUMBER = 7;
    private List<AuthRequirement> requirements_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final AuthenticationRule DEFAULT_INSTANCE = new AuthenticationRule();
    private static final Parser<AuthenticationRule> PARSER = new AbstractParser<AuthenticationRule>(){

        @Override
        public AuthenticationRule parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new AuthenticationRule(input2, extensionRegistry);
        }
    };

    private AuthenticationRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private AuthenticationRule() {
        this.selector_ = "";
        this.allowWithoutCredential_ = false;
        this.requirements_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthenticationRule(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
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
                        String s2 = input2.readStringRequireUtf8();
                        this.selector_ = s2;
                        continue block13;
                    }
                    case 18: {
                        OAuthRequirements.Builder subBuilder = null;
                        if (this.oauth_ != null) {
                            subBuilder = this.oauth_.toBuilder();
                        }
                        this.oauth_ = input2.readMessage(OAuthRequirements.parser(), extensionRegistry);
                        if (subBuilder == null) continue block13;
                        subBuilder.mergeFrom(this.oauth_);
                        this.oauth_ = subBuilder.buildPartial();
                        continue block13;
                    }
                    case 40: {
                        this.allowWithoutCredential_ = input2.readBool();
                        continue block13;
                    }
                    case 58: 
                }
                if ((mutable_bitField0_ & 8) != 8) {
                    this.requirements_ = new ArrayList<AuthRequirement>();
                    mutable_bitField0_ |= 8;
                }
                this.requirements_.add(input2.readMessage(AuthRequirement.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 8) == 8) {
                this.requirements_ = Collections.unmodifiableList(this.requirements_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
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
    public boolean hasOauth() {
        return this.oauth_ != null;
    }

    @Override
    public OAuthRequirements getOauth() {
        return this.oauth_ == null ? OAuthRequirements.getDefaultInstance() : this.oauth_;
    }

    @Override
    public OAuthRequirementsOrBuilder getOauthOrBuilder() {
        return this.getOauth();
    }

    @Override
    public boolean getAllowWithoutCredential() {
        return this.allowWithoutCredential_;
    }

    @Override
    public List<AuthRequirement> getRequirementsList() {
        return this.requirements_;
    }

    @Override
    public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
        return this.requirements_;
    }

    @Override
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override
    public AuthRequirement getRequirements(int index) {
        return this.requirements_.get(index);
    }

    @Override
    public AuthRequirementOrBuilder getRequirementsOrBuilder(int index) {
        return this.requirements_.get(index);
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
        if (this.oauth_ != null) {
            output.writeMessage(2, this.getOauth());
        }
        if (this.allowWithoutCredential_) {
            output.writeBool(5, this.allowWithoutCredential_);
        }
        for (int i = 0; i < this.requirements_.size(); ++i) {
            output.writeMessage(7, this.requirements_.get(i));
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
        if (this.oauth_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getOauth());
        }
        if (this.allowWithoutCredential_) {
            size2 += CodedOutputStream.computeBoolSize(5, this.allowWithoutCredential_);
        }
        for (int i = 0; i < this.requirements_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(7, this.requirements_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthenticationRule)) {
            return super.equals(obj);
        }
        AuthenticationRule other = (AuthenticationRule)obj;
        boolean result2 = true;
        result2 = result2 && this.getSelector().equals(other.getSelector());
        boolean bl = result2 = result2 && this.hasOauth() == other.hasOauth();
        if (this.hasOauth()) {
            result2 = result2 && this.getOauth().equals(other.getOauth());
        }
        result2 = result2 && this.getAllowWithoutCredential() == other.getAllowWithoutCredential();
        result2 = result2 && this.getRequirementsList().equals(other.getRequirementsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + AuthenticationRule.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSelector().hashCode();
        if (this.hasOauth()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getOauth().hashCode();
        }
        hash = 37 * hash + 5;
        hash = 53 * hash + Internal.hashBoolean(this.getAllowWithoutCredential());
        if (this.getRequirementsCount() > 0) {
            hash = 37 * hash + 7;
            hash = 53 * hash + this.getRequirementsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static AuthenticationRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthenticationRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthenticationRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuthenticationRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthenticationRule parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuthenticationRule parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return AuthenticationRule.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthenticationRule prototype) {
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

    public static AuthenticationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthenticationRule> parser() {
        return PARSER;
    }

    public Parser<AuthenticationRule> getParserForType() {
        return PARSER;
    }

    @Override
    public AuthenticationRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuthenticationRuleOrBuilder {
        private int bitField0_;
        private Object selector_ = "";
        private OAuthRequirements oauth_ = null;
        private SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> oauthBuilder_;
        private boolean allowWithoutCredential_;
        private List<AuthRequirement> requirements_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> requirementsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
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
                this.getRequirementsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            this.allowWithoutCredential_ = false;
            if (this.requirementsBuilder_ == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
            } else {
                this.requirementsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        @Override
        public AuthenticationRule getDefaultInstanceForType() {
            return AuthenticationRule.getDefaultInstance();
        }

        @Override
        public AuthenticationRule build() {
            AuthenticationRule result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public AuthenticationRule buildPartial() {
            AuthenticationRule result2 = new AuthenticationRule(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.selector_ = this.selector_;
            if (this.oauthBuilder_ == null) {
                result2.oauth_ = this.oauth_;
            } else {
                result2.oauth_ = this.oauthBuilder_.build();
            }
            result2.allowWithoutCredential_ = this.allowWithoutCredential_;
            if (this.requirementsBuilder_ == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.requirements_ = Collections.unmodifiableList(this.requirements_);
                    this.bitField0_ &= 0xFFFFFFF7;
                }
                result2.requirements_ = this.requirements_;
            } else {
                result2.requirements_ = this.requirementsBuilder_.build();
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
            if (other instanceof AuthenticationRule) {
                return this.mergeFrom((AuthenticationRule)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(AuthenticationRule other) {
            if (other == AuthenticationRule.getDefaultInstance()) {
                return this;
            }
            if (!other.getSelector().isEmpty()) {
                this.selector_ = other.selector_;
                this.onChanged();
            }
            if (other.hasOauth()) {
                this.mergeOauth(other.getOauth());
            }
            if (other.getAllowWithoutCredential()) {
                this.setAllowWithoutCredential(other.getAllowWithoutCredential());
            }
            if (this.requirementsBuilder_ == null) {
                if (!other.requirements_.isEmpty()) {
                    if (this.requirements_.isEmpty()) {
                        this.requirements_ = other.requirements_;
                        this.bitField0_ &= 0xFFFFFFF7;
                    } else {
                        this.ensureRequirementsIsMutable();
                        this.requirements_.addAll(other.requirements_);
                    }
                    this.onChanged();
                }
            } else if (!other.requirements_.isEmpty()) {
                if (this.requirementsBuilder_.isEmpty()) {
                    this.requirementsBuilder_.dispose();
                    this.requirementsBuilder_ = null;
                    this.requirements_ = other.requirements_;
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.requirementsBuilder_ = alwaysUseFieldBuilders ? this.getRequirementsFieldBuilder() : null;
                } else {
                    this.requirementsBuilder_.addAllMessages(other.requirements_);
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
            AuthenticationRule parsedMessage = null;
            try {
                parsedMessage = (AuthenticationRule)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (AuthenticationRule)e.getUnfinishedMessage();
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
            this.selector_ = AuthenticationRule.getDefaultInstance().getSelector();
            this.onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuthenticationRule.checkByteStringIsUtf8(value);
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasOauth() {
            return this.oauthBuilder_ != null || this.oauth_ != null;
        }

        @Override
        public OAuthRequirements getOauth() {
            if (this.oauthBuilder_ == null) {
                return this.oauth_ == null ? OAuthRequirements.getDefaultInstance() : this.oauth_;
            }
            return this.oauthBuilder_.getMessage();
        }

        public Builder setOauth(OAuthRequirements value) {
            if (this.oauthBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.oauth_ = value;
                this.onChanged();
            } else {
                this.oauthBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setOauth(OAuthRequirements.Builder builderForValue) {
            if (this.oauthBuilder_ == null) {
                this.oauth_ = builderForValue.build();
                this.onChanged();
            } else {
                this.oauthBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeOauth(OAuthRequirements value) {
            if (this.oauthBuilder_ == null) {
                this.oauth_ = this.oauth_ != null ? OAuthRequirements.newBuilder(this.oauth_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.oauthBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearOauth() {
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
                this.onChanged();
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            return this;
        }

        public OAuthRequirements.Builder getOauthBuilder() {
            this.onChanged();
            return this.getOauthFieldBuilder().getBuilder();
        }

        @Override
        public OAuthRequirementsOrBuilder getOauthOrBuilder() {
            if (this.oauthBuilder_ != null) {
                return this.oauthBuilder_.getMessageOrBuilder();
            }
            return this.oauth_ == null ? OAuthRequirements.getDefaultInstance() : this.oauth_;
        }

        private SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> getOauthFieldBuilder() {
            if (this.oauthBuilder_ == null) {
                this.oauthBuilder_ = new SingleFieldBuilderV3(this.getOauth(), this.getParentForChildren(), this.isClean());
                this.oauth_ = null;
            }
            return this.oauthBuilder_;
        }

        @Override
        public boolean getAllowWithoutCredential() {
            return this.allowWithoutCredential_;
        }

        public Builder setAllowWithoutCredential(boolean value) {
            this.allowWithoutCredential_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAllowWithoutCredential() {
            this.allowWithoutCredential_ = false;
            this.onChanged();
            return this;
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.requirements_ = new ArrayList<AuthRequirement>(this.requirements_);
                this.bitField0_ |= 8;
            }
        }

        @Override
        public List<AuthRequirement> getRequirementsList() {
            if (this.requirementsBuilder_ == null) {
                return Collections.unmodifiableList(this.requirements_);
            }
            return this.requirementsBuilder_.getMessageList();
        }

        @Override
        public int getRequirementsCount() {
            if (this.requirementsBuilder_ == null) {
                return this.requirements_.size();
            }
            return this.requirementsBuilder_.getCount();
        }

        @Override
        public AuthRequirement getRequirements(int index) {
            if (this.requirementsBuilder_ == null) {
                return this.requirements_.get(index);
            }
            return this.requirementsBuilder_.getMessage(index);
        }

        public Builder setRequirements(int index, AuthRequirement value) {
            if (this.requirementsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRequirementsIsMutable();
                this.requirements_.set(index, value);
                this.onChanged();
            } else {
                this.requirementsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setRequirements(int index, AuthRequirement.Builder builderForValue) {
            if (this.requirementsBuilder_ == null) {
                this.ensureRequirementsIsMutable();
                this.requirements_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.requirementsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addRequirements(AuthRequirement value) {
            if (this.requirementsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRequirementsIsMutable();
                this.requirements_.add(value);
                this.onChanged();
            } else {
                this.requirementsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addRequirements(int index, AuthRequirement value) {
            if (this.requirementsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureRequirementsIsMutable();
                this.requirements_.add(index, value);
                this.onChanged();
            } else {
                this.requirementsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addRequirements(AuthRequirement.Builder builderForValue) {
            if (this.requirementsBuilder_ == null) {
                this.ensureRequirementsIsMutable();
                this.requirements_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.requirementsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addRequirements(int index, AuthRequirement.Builder builderForValue) {
            if (this.requirementsBuilder_ == null) {
                this.ensureRequirementsIsMutable();
                this.requirements_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.requirementsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllRequirements(Iterable<? extends AuthRequirement> values) {
            if (this.requirementsBuilder_ == null) {
                this.ensureRequirementsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.requirements_);
                this.onChanged();
            } else {
                this.requirementsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearRequirements() {
            if (this.requirementsBuilder_ == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
                this.onChanged();
            } else {
                this.requirementsBuilder_.clear();
            }
            return this;
        }

        public Builder removeRequirements(int index) {
            if (this.requirementsBuilder_ == null) {
                this.ensureRequirementsIsMutable();
                this.requirements_.remove(index);
                this.onChanged();
            } else {
                this.requirementsBuilder_.remove(index);
            }
            return this;
        }

        public AuthRequirement.Builder getRequirementsBuilder(int index) {
            return this.getRequirementsFieldBuilder().getBuilder(index);
        }

        @Override
        public AuthRequirementOrBuilder getRequirementsOrBuilder(int index) {
            if (this.requirementsBuilder_ == null) {
                return this.requirements_.get(index);
            }
            return this.requirementsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
            if (this.requirementsBuilder_ != null) {
                return this.requirementsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.requirements_);
        }

        public AuthRequirement.Builder addRequirementsBuilder() {
            return this.getRequirementsFieldBuilder().addBuilder(AuthRequirement.getDefaultInstance());
        }

        public AuthRequirement.Builder addRequirementsBuilder(int index) {
            return this.getRequirementsFieldBuilder().addBuilder(index, AuthRequirement.getDefaultInstance());
        }

        public List<AuthRequirement.Builder> getRequirementsBuilderList() {
            return this.getRequirementsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> getRequirementsFieldBuilder() {
            if (this.requirementsBuilder_ == null) {
                this.requirementsBuilder_ = new RepeatedFieldBuilderV3(this.requirements_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                this.requirements_ = null;
            }
            return this.requirementsBuilder_;
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

