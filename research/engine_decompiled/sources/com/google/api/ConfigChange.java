/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.Advice;
import com.google.api.AdviceOrBuilder;
import com.google.api.ChangeType;
import com.google.api.ConfigChangeOrBuilder;
import com.google.api.ConfigChangeProto;
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

public final class ConfigChange
extends GeneratedMessageV3
implements ConfigChangeOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    private volatile Object element_;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private volatile Object oldValue_;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    private volatile Object newValue_;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    private int changeType_;
    public static final int ADVICES_FIELD_NUMBER = 5;
    private List<Advice> advices_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ConfigChange DEFAULT_INSTANCE = new ConfigChange();
    private static final Parser<ConfigChange> PARSER = new AbstractParser<ConfigChange>(){

        @Override
        public ConfigChange parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ConfigChange(input2, extensionRegistry);
        }
    };

    private ConfigChange(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ConfigChange() {
        this.element_ = "";
        this.oldValue_ = "";
        this.newValue_ = "";
        this.changeType_ = 0;
        this.advices_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ConfigChange(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.element_ = s2;
                        continue block14;
                    }
                    case 18: {
                        String s3 = input2.readStringRequireUtf8();
                        this.oldValue_ = s3;
                        continue block14;
                    }
                    case 26: {
                        String s4 = input2.readStringRequireUtf8();
                        this.newValue_ = s4;
                        continue block14;
                    }
                    case 32: {
                        int rawValue;
                        this.changeType_ = rawValue = input2.readEnum();
                        continue block14;
                    }
                    case 42: 
                }
                if ((mutable_bitField0_ & 0x10) != 16) {
                    this.advices_ = new ArrayList<Advice>();
                    mutable_bitField0_ |= 0x10;
                }
                this.advices_.add(input2.readMessage(Advice.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x10) == 16) {
                this.advices_ = Collections.unmodifiableList(this.advices_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
    }

    @Override
    public String getElement() {
        Object ref = this.element_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.element_ = s2;
        return s2;
    }

    @Override
    public ByteString getElementBytes() {
        Object ref = this.element_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.element_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getOldValue() {
        Object ref = this.oldValue_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.oldValue_ = s2;
        return s2;
    }

    @Override
    public ByteString getOldValueBytes() {
        Object ref = this.oldValue_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.oldValue_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getNewValue() {
        Object ref = this.newValue_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.newValue_ = s2;
        return s2;
    }

    @Override
    public ByteString getNewValueBytes() {
        Object ref = this.newValue_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.newValue_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public int getChangeTypeValue() {
        return this.changeType_;
    }

    @Override
    public ChangeType getChangeType() {
        ChangeType result2 = ChangeType.valueOf(this.changeType_);
        return result2 == null ? ChangeType.UNRECOGNIZED : result2;
    }

    @Override
    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    @Override
    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    @Override
    public int getAdvicesCount() {
        return this.advices_.size();
    }

    @Override
    public Advice getAdvices(int index) {
        return this.advices_.get(index);
    }

    @Override
    public AdviceOrBuilder getAdvicesOrBuilder(int index) {
        return this.advices_.get(index);
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
        if (!this.getElementBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.element_);
        }
        if (!this.getOldValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.oldValue_);
        }
        if (!this.getNewValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            output.writeEnum(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); ++i) {
            output.writeMessage(5, this.advices_.get(i));
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
        if (!this.getElementBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.element_);
        }
        if (!this.getOldValueBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.oldValue_);
        }
        if (!this.getNewValueBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(5, this.advices_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConfigChange)) {
            return super.equals(obj);
        }
        ConfigChange other = (ConfigChange)obj;
        boolean result2 = true;
        result2 = result2 && this.getElement().equals(other.getElement());
        result2 = result2 && this.getOldValue().equals(other.getOldValue());
        result2 = result2 && this.getNewValue().equals(other.getNewValue());
        result2 = result2 && this.changeType_ == other.changeType_;
        result2 = result2 && this.getAdvicesList().equals(other.getAdvicesList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ConfigChange.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getElement().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getOldValue().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getNewValue().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.changeType_;
        if (this.getAdvicesCount() > 0) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getAdvicesList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ConfigChange parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ConfigChange parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ConfigChange parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ConfigChange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ConfigChange parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ConfigChange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ConfigChange parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ConfigChange parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ConfigChange parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ConfigChange parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ConfigChange.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigChange prototype) {
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

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return PARSER;
    }

    public Parser<ConfigChange> getParserForType() {
        return PARSER;
    }

    @Override
    public ConfigChange getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ConfigChangeOrBuilder {
        private int bitField0_;
        private Object element_ = "";
        private Object oldValue_ = "";
        private Object newValue_ = "";
        private int changeType_ = 0;
        private List<Advice> advices_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> advicesBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
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
                this.getAdvicesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            if (this.advicesBuilder_ == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFEF;
            } else {
                this.advicesBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        @Override
        public ConfigChange getDefaultInstanceForType() {
            return ConfigChange.getDefaultInstance();
        }

        @Override
        public ConfigChange build() {
            ConfigChange result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ConfigChange buildPartial() {
            ConfigChange result2 = new ConfigChange(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.element_ = this.element_;
            result2.oldValue_ = this.oldValue_;
            result2.newValue_ = this.newValue_;
            result2.changeType_ = this.changeType_;
            if (this.advicesBuilder_ == null) {
                if ((this.bitField0_ & 0x10) == 16) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                    this.bitField0_ &= 0xFFFFFFEF;
                }
                result2.advices_ = this.advices_;
            } else {
                result2.advices_ = this.advicesBuilder_.build();
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
            if (other instanceof ConfigChange) {
                return this.mergeFrom((ConfigChange)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ConfigChange other) {
            if (other == ConfigChange.getDefaultInstance()) {
                return this;
            }
            if (!other.getElement().isEmpty()) {
                this.element_ = other.element_;
                this.onChanged();
            }
            if (!other.getOldValue().isEmpty()) {
                this.oldValue_ = other.oldValue_;
                this.onChanged();
            }
            if (!other.getNewValue().isEmpty()) {
                this.newValue_ = other.newValue_;
                this.onChanged();
            }
            if (other.changeType_ != 0) {
                this.setChangeTypeValue(other.getChangeTypeValue());
            }
            if (this.advicesBuilder_ == null) {
                if (!other.advices_.isEmpty()) {
                    if (this.advices_.isEmpty()) {
                        this.advices_ = other.advices_;
                        this.bitField0_ &= 0xFFFFFFEF;
                    } else {
                        this.ensureAdvicesIsMutable();
                        this.advices_.addAll(other.advices_);
                    }
                    this.onChanged();
                }
            } else if (!other.advices_.isEmpty()) {
                if (this.advicesBuilder_.isEmpty()) {
                    this.advicesBuilder_.dispose();
                    this.advicesBuilder_ = null;
                    this.advices_ = other.advices_;
                    this.bitField0_ &= 0xFFFFFFEF;
                    this.advicesBuilder_ = alwaysUseFieldBuilders ? this.getAdvicesFieldBuilder() : null;
                } else {
                    this.advicesBuilder_.addAllMessages(other.advices_);
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
            ConfigChange parsedMessage = null;
            try {
                parsedMessage = (ConfigChange)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ConfigChange)e.getUnfinishedMessage();
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
        public String getElement() {
            Object ref = this.element_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.element_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getElementBytes() {
            Object ref = this.element_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.element_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setElement(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.element_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearElement() {
            this.element_ = ConfigChange.getDefaultInstance().getElement();
            this.onChanged();
            return this;
        }

        public Builder setElementBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ConfigChange.checkByteStringIsUtf8(value);
            this.element_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getOldValue() {
            Object ref = this.oldValue_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.oldValue_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getOldValueBytes() {
            Object ref = this.oldValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.oldValue_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setOldValue(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.oldValue_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearOldValue() {
            this.oldValue_ = ConfigChange.getDefaultInstance().getOldValue();
            this.onChanged();
            return this;
        }

        public Builder setOldValueBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ConfigChange.checkByteStringIsUtf8(value);
            this.oldValue_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getNewValue() {
            Object ref = this.newValue_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.newValue_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNewValueBytes() {
            Object ref = this.newValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.newValue_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setNewValue(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.newValue_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearNewValue() {
            this.newValue_ = ConfigChange.getDefaultInstance().getNewValue();
            this.onChanged();
            return this;
        }

        public Builder setNewValueBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ConfigChange.checkByteStringIsUtf8(value);
            this.newValue_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public int getChangeTypeValue() {
            return this.changeType_;
        }

        public Builder setChangeTypeValue(int value) {
            this.changeType_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public ChangeType getChangeType() {
            ChangeType result2 = ChangeType.valueOf(this.changeType_);
            return result2 == null ? ChangeType.UNRECOGNIZED : result2;
        }

        public Builder setChangeType(ChangeType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.changeType_ = value.getNumber();
            this.onChanged();
            return this;
        }

        public Builder clearChangeType() {
            this.changeType_ = 0;
            this.onChanged();
            return this;
        }

        private void ensureAdvicesIsMutable() {
            if ((this.bitField0_ & 0x10) != 16) {
                this.advices_ = new ArrayList<Advice>(this.advices_);
                this.bitField0_ |= 0x10;
            }
        }

        @Override
        public List<Advice> getAdvicesList() {
            if (this.advicesBuilder_ == null) {
                return Collections.unmodifiableList(this.advices_);
            }
            return this.advicesBuilder_.getMessageList();
        }

        @Override
        public int getAdvicesCount() {
            if (this.advicesBuilder_ == null) {
                return this.advices_.size();
            }
            return this.advicesBuilder_.getCount();
        }

        @Override
        public Advice getAdvices(int index) {
            if (this.advicesBuilder_ == null) {
                return this.advices_.get(index);
            }
            return this.advicesBuilder_.getMessage(index);
        }

        public Builder setAdvices(int index, Advice value) {
            if (this.advicesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdvicesIsMutable();
                this.advices_.set(index, value);
                this.onChanged();
            } else {
                this.advicesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setAdvices(int index, Advice.Builder builderForValue) {
            if (this.advicesBuilder_ == null) {
                this.ensureAdvicesIsMutable();
                this.advices_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.advicesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAdvices(Advice value) {
            if (this.advicesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdvicesIsMutable();
                this.advices_.add(value);
                this.onChanged();
            } else {
                this.advicesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addAdvices(int index, Advice value) {
            if (this.advicesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAdvicesIsMutable();
                this.advices_.add(index, value);
                this.onChanged();
            } else {
                this.advicesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addAdvices(Advice.Builder builderForValue) {
            if (this.advicesBuilder_ == null) {
                this.ensureAdvicesIsMutable();
                this.advices_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.advicesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addAdvices(int index, Advice.Builder builderForValue) {
            if (this.advicesBuilder_ == null) {
                this.ensureAdvicesIsMutable();
                this.advices_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.advicesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> values) {
            if (this.advicesBuilder_ == null) {
                this.ensureAdvicesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.advices_);
                this.onChanged();
            } else {
                this.advicesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearAdvices() {
            if (this.advicesBuilder_ == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFEF;
                this.onChanged();
            } else {
                this.advicesBuilder_.clear();
            }
            return this;
        }

        public Builder removeAdvices(int index) {
            if (this.advicesBuilder_ == null) {
                this.ensureAdvicesIsMutable();
                this.advices_.remove(index);
                this.onChanged();
            } else {
                this.advicesBuilder_.remove(index);
            }
            return this;
        }

        public Advice.Builder getAdvicesBuilder(int index) {
            return this.getAdvicesFieldBuilder().getBuilder(index);
        }

        @Override
        public AdviceOrBuilder getAdvicesOrBuilder(int index) {
            if (this.advicesBuilder_ == null) {
                return this.advices_.get(index);
            }
            return this.advicesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
            if (this.advicesBuilder_ != null) {
                return this.advicesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.advices_);
        }

        public Advice.Builder addAdvicesBuilder() {
            return this.getAdvicesFieldBuilder().addBuilder(Advice.getDefaultInstance());
        }

        public Advice.Builder addAdvicesBuilder(int index) {
            return this.getAdvicesFieldBuilder().addBuilder(index, Advice.getDefaultInstance());
        }

        public List<Advice.Builder> getAdvicesBuilderList() {
            return this.getAdvicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> getAdvicesFieldBuilder() {
            if (this.advicesBuilder_ == null) {
                this.advicesBuilder_ = new RepeatedFieldBuilderV3(this.advices_, (this.bitField0_ & 0x10) == 16, this.getParentForChildren(), this.isClean());
                this.advices_ = null;
            }
            return this.advicesBuilder_;
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

