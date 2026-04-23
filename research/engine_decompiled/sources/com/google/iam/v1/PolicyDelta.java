/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.BindingDelta;
import com.google.iam.v1.BindingDeltaOrBuilder;
import com.google.iam.v1.PolicyDeltaOrBuilder;
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

public final class PolicyDelta
extends GeneratedMessageV3
implements PolicyDeltaOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int BINDING_DELTAS_FIELD_NUMBER = 1;
    private List<BindingDelta> bindingDeltas_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final PolicyDelta DEFAULT_INSTANCE = new PolicyDelta();
    private static final Parser<PolicyDelta> PARSER = new AbstractParser<PolicyDelta>(){

        @Override
        public PolicyDelta parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new PolicyDelta(input2, extensionRegistry);
        }
    };

    private PolicyDelta(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private PolicyDelta() {
        this.bindingDeltas_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PolicyDelta(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block10: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block10;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block10;
                        done = true;
                        continue block10;
                    }
                    case 10: 
                }
                if (!(mutable_bitField0_ & true)) {
                    this.bindingDeltas_ = new ArrayList<BindingDelta>();
                    mutable_bitField0_ |= true;
                }
                this.bindingDeltas_.add(input2.readMessage(BindingDelta.parser(), extensionRegistry));
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
                this.bindingDeltas_ = Collections.unmodifiableList(this.bindingDeltas_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return PolicyProto.internal_static_google_iam_v1_PolicyDelta_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PolicyProto.internal_static_google_iam_v1_PolicyDelta_fieldAccessorTable.ensureFieldAccessorsInitialized(PolicyDelta.class, Builder.class);
    }

    @Override
    public List<BindingDelta> getBindingDeltasList() {
        return this.bindingDeltas_;
    }

    @Override
    public List<? extends BindingDeltaOrBuilder> getBindingDeltasOrBuilderList() {
        return this.bindingDeltas_;
    }

    @Override
    public int getBindingDeltasCount() {
        return this.bindingDeltas_.size();
    }

    @Override
    public BindingDelta getBindingDeltas(int index) {
        return this.bindingDeltas_.get(index);
    }

    @Override
    public BindingDeltaOrBuilder getBindingDeltasOrBuilder(int index) {
        return this.bindingDeltas_.get(index);
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
        for (int i = 0; i < this.bindingDeltas_.size(); ++i) {
            output.writeMessage(1, this.bindingDeltas_.get(i));
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
        for (int i = 0; i < this.bindingDeltas_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.bindingDeltas_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PolicyDelta)) {
            return super.equals(obj);
        }
        PolicyDelta other = (PolicyDelta)obj;
        boolean result2 = true;
        result2 = result2 && this.getBindingDeltasList().equals(other.getBindingDeltasList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + PolicyDelta.getDescriptor().hashCode();
        if (this.getBindingDeltasCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getBindingDeltasList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static PolicyDelta parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PolicyDelta parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PolicyDelta parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PolicyDelta parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PolicyDelta parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PolicyDelta parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PolicyDelta parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static PolicyDelta parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static PolicyDelta parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static PolicyDelta parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static PolicyDelta parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static PolicyDelta parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return PolicyDelta.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PolicyDelta prototype) {
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

    public static PolicyDelta getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PolicyDelta> parser() {
        return PARSER;
    }

    public Parser<PolicyDelta> getParserForType() {
        return PARSER;
    }

    @Override
    public PolicyDelta getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements PolicyDeltaOrBuilder {
        private int bitField0_;
        private List<BindingDelta> bindingDeltas_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<BindingDelta, BindingDelta.Builder, BindingDeltaOrBuilder> bindingDeltasBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return PolicyProto.internal_static_google_iam_v1_PolicyDelta_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PolicyProto.internal_static_google_iam_v1_PolicyDelta_fieldAccessorTable.ensureFieldAccessorsInitialized(PolicyDelta.class, Builder.class);
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
                this.getBindingDeltasFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.bindingDeltasBuilder_ == null) {
                this.bindingDeltas_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.bindingDeltasBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return PolicyProto.internal_static_google_iam_v1_PolicyDelta_descriptor;
        }

        @Override
        public PolicyDelta getDefaultInstanceForType() {
            return PolicyDelta.getDefaultInstance();
        }

        @Override
        public PolicyDelta build() {
            PolicyDelta result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public PolicyDelta buildPartial() {
            PolicyDelta result2 = new PolicyDelta(this);
            int from_bitField0_ = this.bitField0_;
            if (this.bindingDeltasBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.bindingDeltas_ = Collections.unmodifiableList(this.bindingDeltas_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.bindingDeltas_ = this.bindingDeltas_;
            } else {
                result2.bindingDeltas_ = this.bindingDeltasBuilder_.build();
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
            if (other instanceof PolicyDelta) {
                return this.mergeFrom((PolicyDelta)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(PolicyDelta other) {
            if (other == PolicyDelta.getDefaultInstance()) {
                return this;
            }
            if (this.bindingDeltasBuilder_ == null) {
                if (!other.bindingDeltas_.isEmpty()) {
                    if (this.bindingDeltas_.isEmpty()) {
                        this.bindingDeltas_ = other.bindingDeltas_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureBindingDeltasIsMutable();
                        this.bindingDeltas_.addAll(other.bindingDeltas_);
                    }
                    this.onChanged();
                }
            } else if (!other.bindingDeltas_.isEmpty()) {
                if (this.bindingDeltasBuilder_.isEmpty()) {
                    this.bindingDeltasBuilder_.dispose();
                    this.bindingDeltasBuilder_ = null;
                    this.bindingDeltas_ = other.bindingDeltas_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.bindingDeltasBuilder_ = alwaysUseFieldBuilders ? this.getBindingDeltasFieldBuilder() : null;
                } else {
                    this.bindingDeltasBuilder_.addAllMessages(other.bindingDeltas_);
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
            PolicyDelta parsedMessage = null;
            try {
                parsedMessage = (PolicyDelta)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (PolicyDelta)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureBindingDeltasIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.bindingDeltas_ = new ArrayList<BindingDelta>(this.bindingDeltas_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<BindingDelta> getBindingDeltasList() {
            if (this.bindingDeltasBuilder_ == null) {
                return Collections.unmodifiableList(this.bindingDeltas_);
            }
            return this.bindingDeltasBuilder_.getMessageList();
        }

        @Override
        public int getBindingDeltasCount() {
            if (this.bindingDeltasBuilder_ == null) {
                return this.bindingDeltas_.size();
            }
            return this.bindingDeltasBuilder_.getCount();
        }

        @Override
        public BindingDelta getBindingDeltas(int index) {
            if (this.bindingDeltasBuilder_ == null) {
                return this.bindingDeltas_.get(index);
            }
            return this.bindingDeltasBuilder_.getMessage(index);
        }

        public Builder setBindingDeltas(int index, BindingDelta value) {
            if (this.bindingDeltasBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.set(index, value);
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setBindingDeltas(int index, BindingDelta.Builder builderForValue) {
            if (this.bindingDeltasBuilder_ == null) {
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addBindingDeltas(BindingDelta value) {
            if (this.bindingDeltasBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.add(value);
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addBindingDeltas(int index, BindingDelta value) {
            if (this.bindingDeltasBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.add(index, value);
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addBindingDeltas(BindingDelta.Builder builderForValue) {
            if (this.bindingDeltasBuilder_ == null) {
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addBindingDeltas(int index, BindingDelta.Builder builderForValue) {
            if (this.bindingDeltasBuilder_ == null) {
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllBindingDeltas(Iterable<? extends BindingDelta> values) {
            if (this.bindingDeltasBuilder_ == null) {
                this.ensureBindingDeltasIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.bindingDeltas_);
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearBindingDeltas() {
            if (this.bindingDeltasBuilder_ == null) {
                this.bindingDeltas_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.clear();
            }
            return this;
        }

        public Builder removeBindingDeltas(int index) {
            if (this.bindingDeltasBuilder_ == null) {
                this.ensureBindingDeltasIsMutable();
                this.bindingDeltas_.remove(index);
                this.onChanged();
            } else {
                this.bindingDeltasBuilder_.remove(index);
            }
            return this;
        }

        public BindingDelta.Builder getBindingDeltasBuilder(int index) {
            return this.getBindingDeltasFieldBuilder().getBuilder(index);
        }

        @Override
        public BindingDeltaOrBuilder getBindingDeltasOrBuilder(int index) {
            if (this.bindingDeltasBuilder_ == null) {
                return this.bindingDeltas_.get(index);
            }
            return this.bindingDeltasBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends BindingDeltaOrBuilder> getBindingDeltasOrBuilderList() {
            if (this.bindingDeltasBuilder_ != null) {
                return this.bindingDeltasBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.bindingDeltas_);
        }

        public BindingDelta.Builder addBindingDeltasBuilder() {
            return this.getBindingDeltasFieldBuilder().addBuilder(BindingDelta.getDefaultInstance());
        }

        public BindingDelta.Builder addBindingDeltasBuilder(int index) {
            return this.getBindingDeltasFieldBuilder().addBuilder(index, BindingDelta.getDefaultInstance());
        }

        public List<BindingDelta.Builder> getBindingDeltasBuilderList() {
            return this.getBindingDeltasFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<BindingDelta, BindingDelta.Builder, BindingDeltaOrBuilder> getBindingDeltasFieldBuilder() {
            if (this.bindingDeltasBuilder_ == null) {
                this.bindingDeltasBuilder_ = new RepeatedFieldBuilderV3(this.bindingDeltas_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.bindingDeltas_ = null;
            }
            return this.bindingDeltasBuilder_;
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

