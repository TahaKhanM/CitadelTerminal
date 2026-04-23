/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.BindingOrBuilder;
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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Binding
extends GeneratedMessageV3
implements BindingOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int ROLE_FIELD_NUMBER = 1;
    private volatile Object role_;
    public static final int MEMBERS_FIELD_NUMBER = 2;
    private LazyStringList members_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Binding DEFAULT_INSTANCE = new Binding();
    private static final Parser<Binding> PARSER = new AbstractParser<Binding>(){

        @Override
        public Binding parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Binding(input2, extensionRegistry);
        }
    };

    private Binding(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Binding() {
        this.role_ = "";
        this.members_ = LazyStringArrayList.EMPTY;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Binding(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
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
                        this.role_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                s2 = input2.readStringRequireUtf8();
                if ((mutable_bitField0_ & 2) != 2) {
                    this.members_ = new LazyStringArrayList();
                    mutable_bitField0_ |= 2;
                }
                this.members_.add(s2);
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
                this.members_ = this.members_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return PolicyProto.internal_static_google_iam_v1_Binding_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PolicyProto.internal_static_google_iam_v1_Binding_fieldAccessorTable.ensureFieldAccessorsInitialized(Binding.class, Builder.class);
    }

    @Override
    public String getRole() {
        Object ref = this.role_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.role_ = s2;
        return s2;
    }

    @Override
    public ByteString getRoleBytes() {
        Object ref = this.role_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.role_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    public ProtocolStringList getMembersList() {
        return this.members_;
    }

    @Override
    public int getMembersCount() {
        return this.members_.size();
    }

    @Override
    public String getMembers(int index) {
        return (String)this.members_.get(index);
    }

    @Override
    public ByteString getMembersBytes(int index) {
        return this.members_.getByteString(index);
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
        if (!this.getRoleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.role_);
        }
        for (int i = 0; i < this.members_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 2, this.members_.getRaw(i));
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
        if (!this.getRoleBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.role_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.members_.size(); ++i) {
            dataSize += Binding.computeStringSizeNoTag(this.members_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getMembersList().size();
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Binding)) {
            return super.equals(obj);
        }
        Binding other = (Binding)obj;
        boolean result2 = true;
        result2 = result2 && this.getRole().equals(other.getRole());
        result2 = result2 && this.getMembersList().equals(other.getMembersList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Binding.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getRole().hashCode();
        if (this.getMembersCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getMembersList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Binding parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Binding parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Binding parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Binding parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Binding parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Binding parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Binding parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Binding parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Binding parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Binding parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Binding parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Binding parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Binding.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Binding prototype) {
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

    public static Binding getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Binding> parser() {
        return PARSER;
    }

    public Parser<Binding> getParserForType() {
        return PARSER;
    }

    @Override
    public Binding getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements BindingOrBuilder {
        private int bitField0_;
        private Object role_ = "";
        private LazyStringList members_ = LazyStringArrayList.EMPTY;

        public static final Descriptors.Descriptor getDescriptor() {
            return PolicyProto.internal_static_google_iam_v1_Binding_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PolicyProto.internal_static_google_iam_v1_Binding_fieldAccessorTable.ensureFieldAccessorsInitialized(Binding.class, Builder.class);
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
            this.role_ = "";
            this.members_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return PolicyProto.internal_static_google_iam_v1_Binding_descriptor;
        }

        @Override
        public Binding getDefaultInstanceForType() {
            return Binding.getDefaultInstance();
        }

        @Override
        public Binding build() {
            Binding result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Binding buildPartial() {
            Binding result2 = new Binding(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.role_ = this.role_;
            if ((this.bitField0_ & 2) == 2) {
                this.members_ = this.members_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFD;
            }
            result2.members_ = this.members_;
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
            if (other instanceof Binding) {
                return this.mergeFrom((Binding)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Binding other) {
            if (other == Binding.getDefaultInstance()) {
                return this;
            }
            if (!other.getRole().isEmpty()) {
                this.role_ = other.role_;
                this.onChanged();
            }
            if (!other.members_.isEmpty()) {
                if (this.members_.isEmpty()) {
                    this.members_ = other.members_;
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.ensureMembersIsMutable();
                    this.members_.addAll(other.members_);
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
            Binding parsedMessage = null;
            try {
                parsedMessage = (Binding)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Binding)e.getUnfinishedMessage();
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
        public String getRole() {
            Object ref = this.role_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.role_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getRoleBytes() {
            Object ref = this.role_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.role_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setRole(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.role_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearRole() {
            this.role_ = Binding.getDefaultInstance().getRole();
            this.onChanged();
            return this;
        }

        public Builder setRoleBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Binding.checkByteStringIsUtf8(value);
            this.role_ = value;
            this.onChanged();
            return this;
        }

        private void ensureMembersIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.members_ = new LazyStringArrayList(this.members_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getMembersList() {
            return this.members_.getUnmodifiableView();
        }

        @Override
        public int getMembersCount() {
            return this.members_.size();
        }

        @Override
        public String getMembers(int index) {
            return (String)this.members_.get(index);
        }

        @Override
        public ByteString getMembersBytes(int index) {
            return this.members_.getByteString(index);
        }

        public Builder setMembers(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureMembersIsMutable();
            this.members_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addMembers(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureMembersIsMutable();
            this.members_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllMembers(Iterable<String> values) {
            this.ensureMembersIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.members_);
            this.onChanged();
            return this;
        }

        public Builder clearMembers() {
            this.members_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.onChanged();
            return this;
        }

        public Builder addMembersBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Binding.checkByteStringIsUtf8(value);
            this.ensureMembersIsMutable();
            this.members_.add(value);
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

