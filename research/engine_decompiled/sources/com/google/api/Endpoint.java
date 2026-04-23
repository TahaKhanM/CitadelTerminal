/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.EndpointOrBuilder;
import com.google.api.EndpointProto;
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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Endpoint
extends GeneratedMessageV3
implements EndpointOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int ALIASES_FIELD_NUMBER = 2;
    private LazyStringList aliases_;
    public static final int FEATURES_FIELD_NUMBER = 4;
    private LazyStringList features_;
    public static final int TARGET_FIELD_NUMBER = 101;
    private volatile Object target_;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    private boolean allowCors_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>(){

        @Override
        public Endpoint parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Endpoint(input2, extensionRegistry);
        }
    };

    private Endpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Endpoint() {
        this.name_ = "";
        this.aliases_ = LazyStringArrayList.EMPTY;
        this.features_ = LazyStringArrayList.EMPTY;
        this.target_ = "";
        this.allowCors_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Endpoint(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block14: while (!done) {
                String s2;
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
                        s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block14;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.aliases_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 2;
                        }
                        this.aliases_.add(s2);
                        continue block14;
                    }
                    case 34: {
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 4) != 4) {
                            this.features_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 4;
                        }
                        this.features_.add(s2);
                        continue block14;
                    }
                    case 40: {
                        this.allowCors_ = input2.readBool();
                        continue block14;
                    }
                    case 810: 
                }
                s2 = input2.readStringRequireUtf8();
                this.target_ = s2;
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
                this.aliases_ = this.aliases_.getUnmodifiableView();
            }
            if ((mutable_bitField0_ & 4) == 4) {
                this.features_ = this.features_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EndpointProto.internal_static_google_api_Endpoint_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
    }

    @Override
    public String getName() {
        Object ref = this.name_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.name_ = s2;
        return s2;
    }

    @Override
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    public ProtocolStringList getAliasesList() {
        return this.aliases_;
    }

    @Override
    public int getAliasesCount() {
        return this.aliases_.size();
    }

    @Override
    public String getAliases(int index) {
        return (String)this.aliases_.get(index);
    }

    @Override
    public ByteString getAliasesBytes(int index) {
        return this.aliases_.getByteString(index);
    }

    public ProtocolStringList getFeaturesList() {
        return this.features_;
    }

    @Override
    public int getFeaturesCount() {
        return this.features_.size();
    }

    @Override
    public String getFeatures(int index) {
        return (String)this.features_.get(index);
    }

    @Override
    public ByteString getFeaturesBytes(int index) {
        return this.features_.getByteString(index);
    }

    @Override
    public String getTarget() {
        Object ref = this.target_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.target_ = s2;
        return s2;
    }

    @Override
    public ByteString getTargetBytes() {
        Object ref = this.target_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.target_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean getAllowCors() {
        return this.allowCors_;
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        for (i = 0; i < this.aliases_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 2, this.aliases_.getRaw(i));
        }
        for (i = 0; i < this.features_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 4, this.features_.getRaw(i));
        }
        if (this.allowCors_) {
            output.writeBool(5, this.allowCors_);
        }
        if (!this.getTargetBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 101, this.target_);
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
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        int dataSize = 0;
        for (i = 0; i < this.aliases_.size(); ++i) {
            dataSize += Endpoint.computeStringSizeNoTag(this.aliases_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getAliasesList().size();
        dataSize = 0;
        for (i = 0; i < this.features_.size(); ++i) {
            dataSize += Endpoint.computeStringSizeNoTag(this.features_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getFeaturesList().size();
        if (this.allowCors_) {
            size2 += CodedOutputStream.computeBoolSize(5, this.allowCors_);
        }
        if (!this.getTargetBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(101, this.target_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint other = (Endpoint)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getAliasesList().equals(other.getAliasesList());
        result2 = result2 && this.getFeaturesList().equals(other.getFeaturesList());
        result2 = result2 && this.getTarget().equals(other.getTarget());
        result2 = result2 && this.getAllowCors() == other.getAllowCors();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Endpoint.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        if (this.getAliasesCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getAliasesList().hashCode();
        }
        if (this.getFeaturesCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getFeaturesList().hashCode();
        }
        hash = 37 * hash + 101;
        hash = 53 * hash + this.getTarget().hashCode();
        hash = 37 * hash + 5;
        hash = 53 * hash + Internal.hashBoolean(this.getAllowCors());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Endpoint parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Endpoint parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Endpoint parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Endpoint parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Endpoint parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Endpoint parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Endpoint parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Endpoint parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Endpoint parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Endpoint parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Endpoint parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Endpoint parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Endpoint.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Endpoint prototype) {
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

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    public Parser<Endpoint> getParserForType() {
        return PARSER;
    }

    @Override
    public Endpoint getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements EndpointOrBuilder {
        private int bitField0_;
        private Object name_ = "";
        private LazyStringList aliases_ = LazyStringArrayList.EMPTY;
        private LazyStringList features_ = LazyStringArrayList.EMPTY;
        private Object target_ = "";
        private boolean allowCors_;

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
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
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFB;
            this.target_ = "";
            this.allowCors_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        @Override
        public Endpoint getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        @Override
        public Endpoint build() {
            Endpoint result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Endpoint buildPartial() {
            Endpoint result2 = new Endpoint(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.name_ = this.name_;
            if ((this.bitField0_ & 2) == 2) {
                this.aliases_ = this.aliases_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFD;
            }
            result2.aliases_ = this.aliases_;
            if ((this.bitField0_ & 4) == 4) {
                this.features_ = this.features_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFB;
            }
            result2.features_ = this.features_;
            result2.target_ = this.target_;
            result2.allowCors_ = this.allowCors_;
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
            if (other instanceof Endpoint) {
                return this.mergeFrom((Endpoint)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Endpoint other) {
            if (other == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.aliases_.isEmpty()) {
                if (this.aliases_.isEmpty()) {
                    this.aliases_ = other.aliases_;
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.ensureAliasesIsMutable();
                    this.aliases_.addAll(other.aliases_);
                }
                this.onChanged();
            }
            if (!other.features_.isEmpty()) {
                if (this.features_.isEmpty()) {
                    this.features_ = other.features_;
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.ensureFeaturesIsMutable();
                    this.features_.addAll(other.features_);
                }
                this.onChanged();
            }
            if (!other.getTarget().isEmpty()) {
                this.target_ = other.target_;
                this.onChanged();
            }
            if (other.getAllowCors()) {
                this.setAllowCors(other.getAllowCors());
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
            Endpoint parsedMessage = null;
            try {
                parsedMessage = (Endpoint)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Endpoint)e.getUnfinishedMessage();
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
        public String getName() {
            Object ref = this.name_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.name_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Endpoint.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Endpoint.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        private void ensureAliasesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.aliases_ = new LazyStringArrayList(this.aliases_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getAliasesList() {
            return this.aliases_.getUnmodifiableView();
        }

        @Override
        public int getAliasesCount() {
            return this.aliases_.size();
        }

        @Override
        public String getAliases(int index) {
            return (String)this.aliases_.get(index);
        }

        @Override
        public ByteString getAliasesBytes(int index) {
            return this.aliases_.getByteString(index);
        }

        public Builder setAliases(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureAliasesIsMutable();
            this.aliases_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addAliases(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureAliasesIsMutable();
            this.aliases_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllAliases(Iterable<String> values) {
            this.ensureAliasesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.aliases_);
            this.onChanged();
            return this;
        }

        public Builder clearAliases() {
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.onChanged();
            return this;
        }

        public Builder addAliasesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Endpoint.checkByteStringIsUtf8(value);
            this.ensureAliasesIsMutable();
            this.aliases_.add(value);
            this.onChanged();
            return this;
        }

        private void ensureFeaturesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.features_ = new LazyStringArrayList(this.features_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getFeaturesList() {
            return this.features_.getUnmodifiableView();
        }

        @Override
        public int getFeaturesCount() {
            return this.features_.size();
        }

        @Override
        public String getFeatures(int index) {
            return (String)this.features_.get(index);
        }

        @Override
        public ByteString getFeaturesBytes(int index) {
            return this.features_.getByteString(index);
        }

        public Builder setFeatures(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureFeaturesIsMutable();
            this.features_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addFeatures(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureFeaturesIsMutable();
            this.features_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllFeatures(Iterable<String> values) {
            this.ensureFeaturesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.features_);
            this.onChanged();
            return this;
        }

        public Builder clearFeatures() {
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFB;
            this.onChanged();
            return this;
        }

        public Builder addFeaturesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Endpoint.checkByteStringIsUtf8(value);
            this.ensureFeaturesIsMutable();
            this.features_.add(value);
            this.onChanged();
            return this;
        }

        @Override
        public String getTarget() {
            Object ref = this.target_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.target_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getTargetBytes() {
            Object ref = this.target_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.target_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setTarget(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.target_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearTarget() {
            this.target_ = Endpoint.getDefaultInstance().getTarget();
            this.onChanged();
            return this;
        }

        public Builder setTargetBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Endpoint.checkByteStringIsUtf8(value);
            this.target_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getAllowCors() {
            return this.allowCors_;
        }

        public Builder setAllowCors(boolean value) {
            this.allowCors_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAllowCors() {
            this.allowCors_ = false;
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

