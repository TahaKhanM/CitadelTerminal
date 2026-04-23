/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.LabelDescriptorOrBuilder;
import com.google.api.LabelProto;
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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LabelDescriptor
extends GeneratedMessageV3
implements LabelDescriptorOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int KEY_FIELD_NUMBER = 1;
    private volatile Object key_;
    public static final int VALUE_TYPE_FIELD_NUMBER = 2;
    private int valueType_;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    private volatile Object description_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LabelDescriptor DEFAULT_INSTANCE = new LabelDescriptor();
    private static final Parser<LabelDescriptor> PARSER = new AbstractParser<LabelDescriptor>(){

        @Override
        public LabelDescriptor parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LabelDescriptor(input2, extensionRegistry);
        }
    };

    private LabelDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LabelDescriptor() {
        this.key_ = "";
        this.valueType_ = 0;
        this.description_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LabelDescriptor(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
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
                        this.key_ = s2;
                        continue block12;
                    }
                    case 16: {
                        int rawValue;
                        this.valueType_ = rawValue = input2.readEnum();
                        continue block12;
                    }
                    case 26: 
                }
                s2 = input2.readStringRequireUtf8();
                this.description_ = s2;
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
        return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
    }

    @Override
    public String getKey() {
        Object ref = this.key_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.key_ = s2;
        return s2;
    }

    @Override
    public ByteString getKeyBytes() {
        Object ref = this.key_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.key_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public int getValueTypeValue() {
        return this.valueType_;
    }

    @Override
    public ValueType getValueType() {
        ValueType result2 = ValueType.valueOf(this.valueType_);
        return result2 == null ? ValueType.UNRECOGNIZED : result2;
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
        if (!this.getKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.key_);
        }
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            output.writeEnum(2, this.valueType_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.description_);
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
        if (!this.getKeyBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.key_);
        }
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(2, this.valueType_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LabelDescriptor)) {
            return super.equals(obj);
        }
        LabelDescriptor other = (LabelDescriptor)obj;
        boolean result2 = true;
        result2 = result2 && this.getKey().equals(other.getKey());
        result2 = result2 && this.valueType_ == other.valueType_;
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LabelDescriptor.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getKey().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.valueType_;
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getDescription().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LabelDescriptor parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LabelDescriptor parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LabelDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LabelDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LabelDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LabelDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LabelDescriptor parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LabelDescriptor parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LabelDescriptor parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LabelDescriptor parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LabelDescriptor.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LabelDescriptor prototype) {
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

    public static LabelDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LabelDescriptor> parser() {
        return PARSER;
    }

    public Parser<LabelDescriptor> getParserForType() {
        return PARSER;
    }

    @Override
    public LabelDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LabelDescriptorOrBuilder {
        private Object key_ = "";
        private int valueType_ = 0;
        private Object description_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
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
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        @Override
        public LabelDescriptor getDefaultInstanceForType() {
            return LabelDescriptor.getDefaultInstance();
        }

        @Override
        public LabelDescriptor build() {
            LabelDescriptor result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LabelDescriptor buildPartial() {
            LabelDescriptor result2 = new LabelDescriptor(this);
            result2.key_ = this.key_;
            result2.valueType_ = this.valueType_;
            result2.description_ = this.description_;
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
            if (other instanceof LabelDescriptor) {
                return this.mergeFrom((LabelDescriptor)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LabelDescriptor other) {
            if (other == LabelDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!other.getKey().isEmpty()) {
                this.key_ = other.key_;
                this.onChanged();
            }
            if (other.valueType_ != 0) {
                this.setValueTypeValue(other.getValueTypeValue());
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
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
            LabelDescriptor parsedMessage = null;
            try {
                parsedMessage = (LabelDescriptor)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LabelDescriptor)e.getUnfinishedMessage();
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
        public String getKey() {
            Object ref = this.key_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.key_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getKeyBytes() {
            Object ref = this.key_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.key_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setKey(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.key_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearKey() {
            this.key_ = LabelDescriptor.getDefaultInstance().getKey();
            this.onChanged();
            return this;
        }

        public Builder setKeyBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LabelDescriptor.checkByteStringIsUtf8(value);
            this.key_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public int getValueTypeValue() {
            return this.valueType_;
        }

        public Builder setValueTypeValue(int value) {
            this.valueType_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public ValueType getValueType() {
            ValueType result2 = ValueType.valueOf(this.valueType_);
            return result2 == null ? ValueType.UNRECOGNIZED : result2;
        }

        public Builder setValueType(ValueType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.valueType_ = value.getNumber();
            this.onChanged();
            return this;
        }

        public Builder clearValueType() {
            this.valueType_ = 0;
            this.onChanged();
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
            this.description_ = LabelDescriptor.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LabelDescriptor.checkByteStringIsUtf8(value);
            this.description_ = value;
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

    public static enum ValueType implements ProtocolMessageEnum
    {
        STRING(0),
        BOOL(1),
        INT64(2),
        UNRECOGNIZED(-1);

        public static final int STRING_VALUE = 0;
        public static final int BOOL_VALUE = 1;
        public static final int INT64_VALUE = 2;
        private static final Internal.EnumLiteMap<ValueType> internalValueMap;
        private static final ValueType[] VALUES;
        private final int value;

        @Override
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static ValueType valueOf(int value) {
            return ValueType.forNumber(value);
        }

        public static ValueType forNumber(int value) {
            switch (value) {
                case 0: {
                    return STRING;
                }
                case 1: {
                    return BOOL;
                }
                case 2: {
                    return INT64;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ValueType.getDescriptor().getValues().get(this.ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ValueType.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return LabelDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static ValueType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ValueType.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private ValueType(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ValueType>(){

                @Override
                public ValueType findValueByNumber(int number) {
                    return ValueType.forNumber(number);
                }
            };
            VALUES = ValueType.values();
        }
    }
}

