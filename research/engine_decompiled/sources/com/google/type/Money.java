/*
 * Decompiled with CFR 0.152.
 */
package com.google.type;

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
import com.google.type.MoneyOrBuilder;
import com.google.type.MoneyProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Money
extends GeneratedMessageV3
implements MoneyOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    private volatile Object currencyCode_;
    public static final int UNITS_FIELD_NUMBER = 2;
    private long units_;
    public static final int NANOS_FIELD_NUMBER = 3;
    private int nanos_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Money DEFAULT_INSTANCE = new Money();
    private static final Parser<Money> PARSER = new AbstractParser<Money>(){

        @Override
        public Money parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Money(input2, extensionRegistry);
        }
    };

    private Money(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Money() {
        this.currencyCode_ = "";
        this.units_ = 0L;
        this.nanos_ = 0;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Money(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.currencyCode_ = s2;
                        continue block12;
                    }
                    case 16: {
                        this.units_ = input2.readInt64();
                        continue block12;
                    }
                    case 24: 
                }
                this.nanos_ = input2.readInt32();
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
        return MoneyProto.internal_static_google_type_Money_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
    }

    @Override
    public String getCurrencyCode() {
        Object ref = this.currencyCode_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.currencyCode_ = s2;
        return s2;
    }

    @Override
    public ByteString getCurrencyCodeBytes() {
        Object ref = this.currencyCode_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.currencyCode_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public long getUnits() {
        return this.units_;
    }

    @Override
    public int getNanos() {
        return this.nanos_;
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
        if (!this.getCurrencyCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.currencyCode_);
        }
        if (this.units_ != 0L) {
            output.writeInt64(2, this.units_);
        }
        if (this.nanos_ != 0) {
            output.writeInt32(3, this.nanos_);
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
        if (!this.getCurrencyCodeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.currencyCode_);
        }
        if (this.units_ != 0L) {
            size2 += CodedOutputStream.computeInt64Size(2, this.units_);
        }
        if (this.nanos_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(3, this.nanos_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Money)) {
            return super.equals(obj);
        }
        Money other = (Money)obj;
        boolean result2 = true;
        result2 = result2 && this.getCurrencyCode().equals(other.getCurrencyCode());
        result2 = result2 && this.getUnits() == other.getUnits();
        result2 = result2 && this.getNanos() == other.getNanos();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Money.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getCurrencyCode().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashLong(this.getUnits());
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getNanos();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Money parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Money parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Money parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Money parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Money parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Money parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Money parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Money parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Money parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Money parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Money parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Money parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Money.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Money prototype) {
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

    public static Money getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Money> parser() {
        return PARSER;
    }

    public Parser<Money> getParserForType() {
        return PARSER;
    }

    @Override
    public Money getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements MoneyOrBuilder {
        private Object currencyCode_ = "";
        private long units_;
        private int nanos_;

        public static final Descriptors.Descriptor getDescriptor() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
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
            this.currencyCode_ = "";
            this.units_ = 0L;
            this.nanos_ = 0;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        @Override
        public Money getDefaultInstanceForType() {
            return Money.getDefaultInstance();
        }

        @Override
        public Money build() {
            Money result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Money buildPartial() {
            Money result2 = new Money(this);
            result2.currencyCode_ = this.currencyCode_;
            result2.units_ = this.units_;
            result2.nanos_ = this.nanos_;
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
            if (other instanceof Money) {
                return this.mergeFrom((Money)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Money other) {
            if (other == Money.getDefaultInstance()) {
                return this;
            }
            if (!other.getCurrencyCode().isEmpty()) {
                this.currencyCode_ = other.currencyCode_;
                this.onChanged();
            }
            if (other.getUnits() != 0L) {
                this.setUnits(other.getUnits());
            }
            if (other.getNanos() != 0) {
                this.setNanos(other.getNanos());
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
            Money parsedMessage = null;
            try {
                parsedMessage = (Money)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Money)e.getUnfinishedMessage();
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
        public String getCurrencyCode() {
            Object ref = this.currencyCode_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.currencyCode_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getCurrencyCodeBytes() {
            Object ref = this.currencyCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.currencyCode_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setCurrencyCode(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.currencyCode_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearCurrencyCode() {
            this.currencyCode_ = Money.getDefaultInstance().getCurrencyCode();
            this.onChanged();
            return this;
        }

        public Builder setCurrencyCodeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Money.checkByteStringIsUtf8(value);
            this.currencyCode_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public long getUnits() {
            return this.units_;
        }

        public Builder setUnits(long value) {
            this.units_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearUnits() {
            this.units_ = 0L;
            this.onChanged();
            return this;
        }

        @Override
        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int value) {
            this.nanos_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
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

