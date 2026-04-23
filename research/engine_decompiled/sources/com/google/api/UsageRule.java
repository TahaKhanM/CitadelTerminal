/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.UsageProto;
import com.google.api.UsageRuleOrBuilder;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UsageRule
extends GeneratedMessageV3
implements UsageRuleOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private volatile Object selector_;
    public static final int ALLOW_UNREGISTERED_CALLS_FIELD_NUMBER = 2;
    private boolean allowUnregisteredCalls_;
    public static final int SKIP_SERVICE_CONTROL_FIELD_NUMBER = 3;
    private boolean skipServiceControl_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final UsageRule DEFAULT_INSTANCE = new UsageRule();
    private static final Parser<UsageRule> PARSER = new AbstractParser<UsageRule>(){

        @Override
        public UsageRule parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new UsageRule(input2, extensionRegistry);
        }
    };

    private UsageRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UsageRule() {
        this.selector_ = "";
        this.allowUnregisteredCalls_ = false;
        this.skipServiceControl_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UsageRule(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
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
                        this.selector_ = s2;
                        continue block12;
                    }
                    case 16: {
                        this.allowUnregisteredCalls_ = input2.readBool();
                        continue block12;
                    }
                    case 24: 
                }
                this.skipServiceControl_ = input2.readBool();
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
        return UsageProto.internal_static_google_api_UsageRule_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_UsageRule_fieldAccessorTable.ensureFieldAccessorsInitialized(UsageRule.class, Builder.class);
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
    public boolean getAllowUnregisteredCalls() {
        return this.allowUnregisteredCalls_;
    }

    @Override
    public boolean getSkipServiceControl() {
        return this.skipServiceControl_;
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
        if (this.allowUnregisteredCalls_) {
            output.writeBool(2, this.allowUnregisteredCalls_);
        }
        if (this.skipServiceControl_) {
            output.writeBool(3, this.skipServiceControl_);
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
        if (this.allowUnregisteredCalls_) {
            size2 += CodedOutputStream.computeBoolSize(2, this.allowUnregisteredCalls_);
        }
        if (this.skipServiceControl_) {
            size2 += CodedOutputStream.computeBoolSize(3, this.skipServiceControl_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UsageRule)) {
            return super.equals(obj);
        }
        UsageRule other = (UsageRule)obj;
        boolean result2 = true;
        result2 = result2 && this.getSelector().equals(other.getSelector());
        result2 = result2 && this.getAllowUnregisteredCalls() == other.getAllowUnregisteredCalls();
        result2 = result2 && this.getSkipServiceControl() == other.getSkipServiceControl();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + UsageRule.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSelector().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashBoolean(this.getAllowUnregisteredCalls());
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashBoolean(this.getSkipServiceControl());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static UsageRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UsageRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UsageRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UsageRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UsageRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UsageRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UsageRule parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UsageRule parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UsageRule parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static UsageRule parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UsageRule parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UsageRule parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return UsageRule.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsageRule prototype) {
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

    public static UsageRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsageRule> parser() {
        return PARSER;
    }

    public Parser<UsageRule> getParserForType() {
        return PARSER;
    }

    @Override
    public UsageRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements UsageRuleOrBuilder {
        private Object selector_ = "";
        private boolean allowUnregisteredCalls_;
        private boolean skipServiceControl_;

        public static final Descriptors.Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_UsageRule_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_UsageRule_fieldAccessorTable.ensureFieldAccessorsInitialized(UsageRule.class, Builder.class);
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
            this.selector_ = "";
            this.allowUnregisteredCalls_ = false;
            this.skipServiceControl_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_UsageRule_descriptor;
        }

        @Override
        public UsageRule getDefaultInstanceForType() {
            return UsageRule.getDefaultInstance();
        }

        @Override
        public UsageRule build() {
            UsageRule result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public UsageRule buildPartial() {
            UsageRule result2 = new UsageRule(this);
            result2.selector_ = this.selector_;
            result2.allowUnregisteredCalls_ = this.allowUnregisteredCalls_;
            result2.skipServiceControl_ = this.skipServiceControl_;
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
            if (other instanceof UsageRule) {
                return this.mergeFrom((UsageRule)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(UsageRule other) {
            if (other == UsageRule.getDefaultInstance()) {
                return this;
            }
            if (!other.getSelector().isEmpty()) {
                this.selector_ = other.selector_;
                this.onChanged();
            }
            if (other.getAllowUnregisteredCalls()) {
                this.setAllowUnregisteredCalls(other.getAllowUnregisteredCalls());
            }
            if (other.getSkipServiceControl()) {
                this.setSkipServiceControl(other.getSkipServiceControl());
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
            UsageRule parsedMessage = null;
            try {
                parsedMessage = (UsageRule)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (UsageRule)e.getUnfinishedMessage();
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
            this.selector_ = UsageRule.getDefaultInstance().getSelector();
            this.onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            UsageRule.checkByteStringIsUtf8(value);
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getAllowUnregisteredCalls() {
            return this.allowUnregisteredCalls_;
        }

        public Builder setAllowUnregisteredCalls(boolean value) {
            this.allowUnregisteredCalls_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAllowUnregisteredCalls() {
            this.allowUnregisteredCalls_ = false;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getSkipServiceControl() {
            return this.skipServiceControl_;
        }

        public Builder setSkipServiceControl(boolean value) {
            this.skipServiceControl_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSkipServiceControl() {
            this.skipServiceControl_ = false;
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

