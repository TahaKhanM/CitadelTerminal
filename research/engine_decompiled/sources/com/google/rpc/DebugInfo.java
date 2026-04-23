/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

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
import com.google.rpc.DebugInfoOrBuilder;
import com.google.rpc.ErrorDetailsProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DebugInfo
extends GeneratedMessageV3
implements DebugInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private LazyStringList stackEntries_;
    public static final int DETAIL_FIELD_NUMBER = 2;
    private volatile Object detail_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final DebugInfo DEFAULT_INSTANCE = new DebugInfo();
    private static final Parser<DebugInfo> PARSER = new AbstractParser<DebugInfo>(){

        @Override
        public DebugInfo parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new DebugInfo(input2, extensionRegistry);
        }
    };

    private DebugInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private DebugInfo() {
        this.stackEntries_ = LazyStringArrayList.EMPTY;
        this.detail_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DebugInfo(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
                String s2;
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
                    case 10: {
                        s2 = input2.readStringRequireUtf8();
                        if (!(mutable_bitField0_ & true)) {
                            this.stackEntries_ = new LazyStringArrayList();
                            mutable_bitField0_ |= true;
                        }
                        this.stackEntries_.add(s2);
                        continue block11;
                    }
                    case 18: 
                }
                s2 = input2.readStringRequireUtf8();
                this.detail_ = s2;
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
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
    }

    public ProtocolStringList getStackEntriesList() {
        return this.stackEntries_;
    }

    @Override
    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    @Override
    public String getStackEntries(int index) {
        return (String)this.stackEntries_.get(index);
    }

    @Override
    public ByteString getStackEntriesBytes(int index) {
        return this.stackEntries_.getByteString(index);
    }

    @Override
    public String getDetail() {
        Object ref = this.detail_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.detail_ = s2;
        return s2;
    }

    @Override
    public ByteString getDetailBytes() {
        Object ref = this.detail_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.detail_ = b;
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
        for (int i = 0; i < this.stackEntries_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 1, this.stackEntries_.getRaw(i));
        }
        if (!this.getDetailBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.detail_);
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
        for (int i = 0; i < this.stackEntries_.size(); ++i) {
            dataSize += DebugInfo.computeStringSizeNoTag(this.stackEntries_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getStackEntriesList().size();
        if (!this.getDetailBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.detail_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugInfo)) {
            return super.equals(obj);
        }
        DebugInfo other = (DebugInfo)obj;
        boolean result2 = true;
        result2 = result2 && this.getStackEntriesList().equals(other.getStackEntriesList());
        result2 = result2 && this.getDetail().equals(other.getDetail());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + DebugInfo.getDescriptor().hashCode();
        if (this.getStackEntriesCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getStackEntriesList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getDetail().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static DebugInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DebugInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DebugInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DebugInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DebugInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DebugInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DebugInfo parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static DebugInfo parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static DebugInfo parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static DebugInfo parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static DebugInfo parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static DebugInfo parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return DebugInfo.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DebugInfo prototype) {
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

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return PARSER;
    }

    public Parser<DebugInfo> getParserForType() {
        return PARSER;
    }

    @Override
    public DebugInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements DebugInfoOrBuilder {
        private int bitField0_;
        private LazyStringList stackEntries_ = LazyStringArrayList.EMPTY;
        private Object detail_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
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
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.detail_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        @Override
        public DebugInfo getDefaultInstanceForType() {
            return DebugInfo.getDefaultInstance();
        }

        @Override
        public DebugInfo build() {
            DebugInfo result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public DebugInfo buildPartial() {
            DebugInfo result2 = new DebugInfo(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((this.bitField0_ & 1) == 1) {
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFE;
            }
            result2.stackEntries_ = this.stackEntries_;
            result2.detail_ = this.detail_;
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
            if (other instanceof DebugInfo) {
                return this.mergeFrom((DebugInfo)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(DebugInfo other) {
            if (other == DebugInfo.getDefaultInstance()) {
                return this;
            }
            if (!other.stackEntries_.isEmpty()) {
                if (this.stackEntries_.isEmpty()) {
                    this.stackEntries_ = other.stackEntries_;
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.ensureStackEntriesIsMutable();
                    this.stackEntries_.addAll(other.stackEntries_);
                }
                this.onChanged();
            }
            if (!other.getDetail().isEmpty()) {
                this.detail_ = other.detail_;
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
            DebugInfo parsedMessage = null;
            try {
                parsedMessage = (DebugInfo)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (DebugInfo)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureStackEntriesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.stackEntries_ = new LazyStringArrayList(this.stackEntries_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getStackEntriesList() {
            return this.stackEntries_.getUnmodifiableView();
        }

        @Override
        public int getStackEntriesCount() {
            return this.stackEntries_.size();
        }

        @Override
        public String getStackEntries(int index) {
            return (String)this.stackEntries_.get(index);
        }

        @Override
        public ByteString getStackEntriesBytes(int index) {
            return this.stackEntries_.getByteString(index);
        }

        public Builder setStackEntries(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureStackEntriesIsMutable();
            this.stackEntries_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addStackEntries(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureStackEntriesIsMutable();
            this.stackEntries_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllStackEntries(Iterable<String> values) {
            this.ensureStackEntriesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.stackEntries_);
            this.onChanged();
            return this;
        }

        public Builder clearStackEntries() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            DebugInfo.checkByteStringIsUtf8(value);
            this.ensureStackEntriesIsMutable();
            this.stackEntries_.add(value);
            this.onChanged();
            return this;
        }

        @Override
        public String getDetail() {
            Object ref = this.detail_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.detail_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDetailBytes() {
            Object ref = this.detail_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.detail_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDetail(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.detail_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDetail() {
            this.detail_ = DebugInfo.getDefaultInstance().getDetail();
            this.onChanged();
            return this;
        }

        public Builder setDetailBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            DebugInfo.checkByteStringIsUtf8(value);
            this.detail_ = value;
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

