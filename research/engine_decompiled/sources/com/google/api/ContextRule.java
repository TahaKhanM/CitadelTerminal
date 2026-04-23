/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.ContextProto;
import com.google.api.ContextRuleOrBuilder;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ContextRule
extends GeneratedMessageV3
implements ContextRuleOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private volatile Object selector_;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    private LazyStringList requested_;
    public static final int PROVIDED_FIELD_NUMBER = 3;
    private LazyStringList provided_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ContextRule DEFAULT_INSTANCE = new ContextRule();
    private static final Parser<ContextRule> PARSER = new AbstractParser<ContextRule>(){

        @Override
        public ContextRule parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ContextRule(input2, extensionRegistry);
        }
    };

    private ContextRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ContextRule() {
        this.selector_ = "";
        this.requested_ = LazyStringArrayList.EMPTY;
        this.provided_ = LazyStringArrayList.EMPTY;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ContextRule(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.selector_ = s2;
                        continue block12;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.requested_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 2;
                        }
                        this.requested_.add(s2);
                        continue block12;
                    }
                    case 26: 
                }
                s2 = input2.readStringRequireUtf8();
                if ((mutable_bitField0_ & 4) != 4) {
                    this.provided_ = new LazyStringArrayList();
                    mutable_bitField0_ |= 4;
                }
                this.provided_.add(s2);
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 2) == 2) {
                this.requested_ = this.requested_.getUnmodifiableView();
            }
            if ((mutable_bitField0_ & 4) == 4) {
                this.provided_ = this.provided_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ContextProto.internal_static_google_api_ContextRule_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
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

    public ProtocolStringList getRequestedList() {
        return this.requested_;
    }

    @Override
    public int getRequestedCount() {
        return this.requested_.size();
    }

    @Override
    public String getRequested(int index) {
        return (String)this.requested_.get(index);
    }

    @Override
    public ByteString getRequestedBytes(int index) {
        return this.requested_.getByteString(index);
    }

    public ProtocolStringList getProvidedList() {
        return this.provided_;
    }

    @Override
    public int getProvidedCount() {
        return this.provided_.size();
    }

    @Override
    public String getProvided(int index) {
        return (String)this.provided_.get(index);
    }

    @Override
    public ByteString getProvidedBytes(int index) {
        return this.provided_.getByteString(index);
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
        if (!this.getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.selector_);
        }
        for (i = 0; i < this.requested_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 2, this.requested_.getRaw(i));
        }
        for (i = 0; i < this.provided_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 3, this.provided_.getRaw(i));
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
        if (!this.getSelectorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        int dataSize = 0;
        for (i = 0; i < this.requested_.size(); ++i) {
            dataSize += ContextRule.computeStringSizeNoTag(this.requested_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getRequestedList().size();
        dataSize = 0;
        for (i = 0; i < this.provided_.size(); ++i) {
            dataSize += ContextRule.computeStringSizeNoTag(this.provided_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getProvidedList().size();
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextRule)) {
            return super.equals(obj);
        }
        ContextRule other = (ContextRule)obj;
        boolean result2 = true;
        result2 = result2 && this.getSelector().equals(other.getSelector());
        result2 = result2 && this.getRequestedList().equals(other.getRequestedList());
        result2 = result2 && this.getProvidedList().equals(other.getProvidedList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ContextRule.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSelector().hashCode();
        if (this.getRequestedCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getRequestedList().hashCode();
        }
        if (this.getProvidedCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getProvidedList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ContextRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ContextRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ContextRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ContextRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ContextRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ContextRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ContextRule parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ContextRule parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ContextRule parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ContextRule parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ContextRule parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ContextRule parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ContextRule.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ContextRule prototype) {
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

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ContextRule> parser() {
        return PARSER;
    }

    public Parser<ContextRule> getParserForType() {
        return PARSER;
    }

    @Override
    public ContextRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ContextRuleOrBuilder {
        private int bitField0_;
        private Object selector_ = "";
        private LazyStringList requested_ = LazyStringArrayList.EMPTY;
        private LazyStringList provided_ = LazyStringArrayList.EMPTY;

        public static final Descriptors.Descriptor getDescriptor() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
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
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFB;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        @Override
        public ContextRule getDefaultInstanceForType() {
            return ContextRule.getDefaultInstance();
        }

        @Override
        public ContextRule build() {
            ContextRule result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ContextRule buildPartial() {
            ContextRule result2 = new ContextRule(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.selector_ = this.selector_;
            if ((this.bitField0_ & 2) == 2) {
                this.requested_ = this.requested_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFD;
            }
            result2.requested_ = this.requested_;
            if ((this.bitField0_ & 4) == 4) {
                this.provided_ = this.provided_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFB;
            }
            result2.provided_ = this.provided_;
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
            if (other instanceof ContextRule) {
                return this.mergeFrom((ContextRule)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ContextRule other) {
            if (other == ContextRule.getDefaultInstance()) {
                return this;
            }
            if (!other.getSelector().isEmpty()) {
                this.selector_ = other.selector_;
                this.onChanged();
            }
            if (!other.requested_.isEmpty()) {
                if (this.requested_.isEmpty()) {
                    this.requested_ = other.requested_;
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.ensureRequestedIsMutable();
                    this.requested_.addAll(other.requested_);
                }
                this.onChanged();
            }
            if (!other.provided_.isEmpty()) {
                if (this.provided_.isEmpty()) {
                    this.provided_ = other.provided_;
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.ensureProvidedIsMutable();
                    this.provided_.addAll(other.provided_);
                }
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
            ContextRule parsedMessage = null;
            try {
                parsedMessage = (ContextRule)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ContextRule)e.getUnfinishedMessage();
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
            this.selector_ = ContextRule.getDefaultInstance().getSelector();
            this.onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ContextRule.checkByteStringIsUtf8(value);
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        private void ensureRequestedIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.requested_ = new LazyStringArrayList(this.requested_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getRequestedList() {
            return this.requested_.getUnmodifiableView();
        }

        @Override
        public int getRequestedCount() {
            return this.requested_.size();
        }

        @Override
        public String getRequested(int index) {
            return (String)this.requested_.get(index);
        }

        @Override
        public ByteString getRequestedBytes(int index) {
            return this.requested_.getByteString(index);
        }

        public Builder setRequested(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRequestedIsMutable();
            this.requested_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addRequested(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRequestedIsMutable();
            this.requested_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllRequested(Iterable<String> values) {
            this.ensureRequestedIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.requested_);
            this.onChanged();
            return this;
        }

        public Builder clearRequested() {
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.onChanged();
            return this;
        }

        public Builder addRequestedBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ContextRule.checkByteStringIsUtf8(value);
            this.ensureRequestedIsMutable();
            this.requested_.add(value);
            this.onChanged();
            return this;
        }

        private void ensureProvidedIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.provided_ = new LazyStringArrayList(this.provided_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getProvidedList() {
            return this.provided_.getUnmodifiableView();
        }

        @Override
        public int getProvidedCount() {
            return this.provided_.size();
        }

        @Override
        public String getProvided(int index) {
            return (String)this.provided_.get(index);
        }

        @Override
        public ByteString getProvidedBytes(int index) {
            return this.provided_.getByteString(index);
        }

        public Builder setProvided(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureProvidedIsMutable();
            this.provided_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addProvided(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureProvidedIsMutable();
            this.provided_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllProvided(Iterable<String> values) {
            this.ensureProvidedIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.provided_);
            this.onChanged();
            return this;
        }

        public Builder clearProvided() {
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFB;
            this.onChanged();
            return this;
        }

        public Builder addProvidedBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ContextRule.checkByteStringIsUtf8(value);
            this.ensureProvidedIsMutable();
            this.provided_.add(value);
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

