/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.Binding;
import com.google.iam.v1.BindingOrBuilder;
import com.google.iam.v1.PolicyOrBuilder;
import com.google.iam.v1.PolicyProto;
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

public final class Policy
extends GeneratedMessageV3
implements PolicyOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int version_;
    public static final int BINDINGS_FIELD_NUMBER = 4;
    private List<Binding> bindings_;
    public static final int ETAG_FIELD_NUMBER = 3;
    private ByteString etag_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Policy DEFAULT_INSTANCE = new Policy();
    private static final Parser<Policy> PARSER = new AbstractParser<Policy>(){

        @Override
        public Policy parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Policy(input2, extensionRegistry);
        }
    };

    private Policy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Policy() {
        this.version_ = 0;
        this.bindings_ = Collections.emptyList();
        this.etag_ = ByteString.EMPTY;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Policy(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
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
                    case 8: {
                        this.version_ = input2.readInt32();
                        continue block12;
                    }
                    case 26: {
                        this.etag_ = input2.readBytes();
                        continue block12;
                    }
                    case 34: 
                }
                if ((mutable_bitField0_ & 2) != 2) {
                    this.bindings_ = new ArrayList<Binding>();
                    mutable_bitField0_ |= 2;
                }
                this.bindings_.add(input2.readMessage(Binding.parser(), extensionRegistry));
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
                this.bindings_ = Collections.unmodifiableList(this.bindings_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return PolicyProto.internal_static_google_iam_v1_Policy_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PolicyProto.internal_static_google_iam_v1_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
    }

    @Override
    public int getVersion() {
        return this.version_;
    }

    @Override
    public List<Binding> getBindingsList() {
        return this.bindings_;
    }

    @Override
    public List<? extends BindingOrBuilder> getBindingsOrBuilderList() {
        return this.bindings_;
    }

    @Override
    public int getBindingsCount() {
        return this.bindings_.size();
    }

    @Override
    public Binding getBindings(int index) {
        return this.bindings_.get(index);
    }

    @Override
    public BindingOrBuilder getBindingsOrBuilder(int index) {
        return this.bindings_.get(index);
    }

    @Override
    public ByteString getEtag() {
        return this.etag_;
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
        if (this.version_ != 0) {
            output.writeInt32(1, this.version_);
        }
        if (!this.etag_.isEmpty()) {
            output.writeBytes(3, this.etag_);
        }
        for (int i = 0; i < this.bindings_.size(); ++i) {
            output.writeMessage(4, this.bindings_.get(i));
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
        if (this.version_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(1, this.version_);
        }
        if (!this.etag_.isEmpty()) {
            size2 += CodedOutputStream.computeBytesSize(3, this.etag_);
        }
        for (int i = 0; i < this.bindings_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(4, this.bindings_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Policy)) {
            return super.equals(obj);
        }
        Policy other = (Policy)obj;
        boolean result2 = true;
        result2 = result2 && this.getVersion() == other.getVersion();
        result2 = result2 && this.getBindingsList().equals(other.getBindingsList());
        result2 = result2 && this.getEtag().equals(other.getEtag());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Policy.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getVersion();
        if (this.getBindingsCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getBindingsList().hashCode();
        }
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getEtag().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Policy parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Policy parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Policy parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Policy parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Policy parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Policy parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Policy parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Policy parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Policy parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Policy parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Policy parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Policy parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Policy.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Policy prototype) {
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

    public static Policy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Policy> parser() {
        return PARSER;
    }

    public Parser<Policy> getParserForType() {
        return PARSER;
    }

    @Override
    public Policy getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements PolicyOrBuilder {
        private int bitField0_;
        private int version_;
        private List<Binding> bindings_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Binding, Binding.Builder, BindingOrBuilder> bindingsBuilder_;
        private ByteString etag_ = ByteString.EMPTY;

        public static final Descriptors.Descriptor getDescriptor() {
            return PolicyProto.internal_static_google_iam_v1_Policy_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PolicyProto.internal_static_google_iam_v1_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
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
                this.getBindingsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.version_ = 0;
            if (this.bindingsBuilder_ == null) {
                this.bindings_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
            } else {
                this.bindingsBuilder_.clear();
            }
            this.etag_ = ByteString.EMPTY;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return PolicyProto.internal_static_google_iam_v1_Policy_descriptor;
        }

        @Override
        public Policy getDefaultInstanceForType() {
            return Policy.getDefaultInstance();
        }

        @Override
        public Policy build() {
            Policy result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Policy buildPartial() {
            Policy result2 = new Policy(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.version_ = this.version_;
            if (this.bindingsBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.bindings_ = Collections.unmodifiableList(this.bindings_);
                    this.bitField0_ &= 0xFFFFFFFD;
                }
                result2.bindings_ = this.bindings_;
            } else {
                result2.bindings_ = this.bindingsBuilder_.build();
            }
            result2.etag_ = this.etag_;
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
            if (other instanceof Policy) {
                return this.mergeFrom((Policy)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Policy other) {
            if (other == Policy.getDefaultInstance()) {
                return this;
            }
            if (other.getVersion() != 0) {
                this.setVersion(other.getVersion());
            }
            if (this.bindingsBuilder_ == null) {
                if (!other.bindings_.isEmpty()) {
                    if (this.bindings_.isEmpty()) {
                        this.bindings_ = other.bindings_;
                        this.bitField0_ &= 0xFFFFFFFD;
                    } else {
                        this.ensureBindingsIsMutable();
                        this.bindings_.addAll(other.bindings_);
                    }
                    this.onChanged();
                }
            } else if (!other.bindings_.isEmpty()) {
                if (this.bindingsBuilder_.isEmpty()) {
                    this.bindingsBuilder_.dispose();
                    this.bindingsBuilder_ = null;
                    this.bindings_ = other.bindings_;
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.bindingsBuilder_ = alwaysUseFieldBuilders ? this.getBindingsFieldBuilder() : null;
                } else {
                    this.bindingsBuilder_.addAllMessages(other.bindings_);
                }
            }
            if (other.getEtag() != ByteString.EMPTY) {
                this.setEtag(other.getEtag());
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
            Policy parsedMessage = null;
            try {
                parsedMessage = (Policy)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Policy)e.getUnfinishedMessage();
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
        public int getVersion() {
            return this.version_;
        }

        public Builder setVersion(int value) {
            this.version_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearVersion() {
            this.version_ = 0;
            this.onChanged();
            return this;
        }

        private void ensureBindingsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.bindings_ = new ArrayList<Binding>(this.bindings_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        public List<Binding> getBindingsList() {
            if (this.bindingsBuilder_ == null) {
                return Collections.unmodifiableList(this.bindings_);
            }
            return this.bindingsBuilder_.getMessageList();
        }

        @Override
        public int getBindingsCount() {
            if (this.bindingsBuilder_ == null) {
                return this.bindings_.size();
            }
            return this.bindingsBuilder_.getCount();
        }

        @Override
        public Binding getBindings(int index) {
            if (this.bindingsBuilder_ == null) {
                return this.bindings_.get(index);
            }
            return this.bindingsBuilder_.getMessage(index);
        }

        public Builder setBindings(int index, Binding value) {
            if (this.bindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingsIsMutable();
                this.bindings_.set(index, value);
                this.onChanged();
            } else {
                this.bindingsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setBindings(int index, Binding.Builder builderForValue) {
            if (this.bindingsBuilder_ == null) {
                this.ensureBindingsIsMutable();
                this.bindings_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.bindingsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addBindings(Binding value) {
            if (this.bindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingsIsMutable();
                this.bindings_.add(value);
                this.onChanged();
            } else {
                this.bindingsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addBindings(int index, Binding value) {
            if (this.bindingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingsIsMutable();
                this.bindings_.add(index, value);
                this.onChanged();
            } else {
                this.bindingsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addBindings(Binding.Builder builderForValue) {
            if (this.bindingsBuilder_ == null) {
                this.ensureBindingsIsMutable();
                this.bindings_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.bindingsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addBindings(int index, Binding.Builder builderForValue) {
            if (this.bindingsBuilder_ == null) {
                this.ensureBindingsIsMutable();
                this.bindings_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.bindingsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllBindings(Iterable<? extends Binding> values) {
            if (this.bindingsBuilder_ == null) {
                this.ensureBindingsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.bindings_);
                this.onChanged();
            } else {
                this.bindingsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearBindings() {
            if (this.bindingsBuilder_ == null) {
                this.bindings_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
                this.onChanged();
            } else {
                this.bindingsBuilder_.clear();
            }
            return this;
        }

        public Builder removeBindings(int index) {
            if (this.bindingsBuilder_ == null) {
                this.ensureBindingsIsMutable();
                this.bindings_.remove(index);
                this.onChanged();
            } else {
                this.bindingsBuilder_.remove(index);
            }
            return this;
        }

        public Binding.Builder getBindingsBuilder(int index) {
            return this.getBindingsFieldBuilder().getBuilder(index);
        }

        @Override
        public BindingOrBuilder getBindingsOrBuilder(int index) {
            if (this.bindingsBuilder_ == null) {
                return this.bindings_.get(index);
            }
            return this.bindingsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends BindingOrBuilder> getBindingsOrBuilderList() {
            if (this.bindingsBuilder_ != null) {
                return this.bindingsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.bindings_);
        }

        public Binding.Builder addBindingsBuilder() {
            return this.getBindingsFieldBuilder().addBuilder(Binding.getDefaultInstance());
        }

        public Binding.Builder addBindingsBuilder(int index) {
            return this.getBindingsFieldBuilder().addBuilder(index, Binding.getDefaultInstance());
        }

        public List<Binding.Builder> getBindingsBuilderList() {
            return this.getBindingsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Binding, Binding.Builder, BindingOrBuilder> getBindingsFieldBuilder() {
            if (this.bindingsBuilder_ == null) {
                this.bindingsBuilder_ = new RepeatedFieldBuilderV3(this.bindings_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                this.bindings_ = null;
            }
            return this.bindingsBuilder_;
        }

        @Override
        public ByteString getEtag() {
            return this.etag_;
        }

        public Builder setEtag(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.etag_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearEtag() {
            this.etag_ = Policy.getDefaultInstance().getEtag();
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

