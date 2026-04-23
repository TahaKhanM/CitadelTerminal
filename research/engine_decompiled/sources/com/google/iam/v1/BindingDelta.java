/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.BindingDeltaOrBuilder;
import com.google.iam.v1.PolicyProto;
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

public final class BindingDelta
extends GeneratedMessageV3
implements BindingDeltaOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int ACTION_FIELD_NUMBER = 1;
    private int action_;
    public static final int ROLE_FIELD_NUMBER = 2;
    private volatile Object role_;
    public static final int MEMBER_FIELD_NUMBER = 3;
    private volatile Object member_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final BindingDelta DEFAULT_INSTANCE = new BindingDelta();
    private static final Parser<BindingDelta> PARSER = new AbstractParser<BindingDelta>(){

        @Override
        public BindingDelta parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new BindingDelta(input2, extensionRegistry);
        }
    };

    private BindingDelta(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private BindingDelta() {
        this.action_ = 0;
        this.role_ = "";
        this.member_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BindingDelta(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 8: {
                        int rawValue;
                        this.action_ = rawValue = input2.readEnum();
                        continue block12;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.role_ = s2;
                        continue block12;
                    }
                    case 26: 
                }
                String s3 = input2.readStringRequireUtf8();
                this.member_ = s3;
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
        return PolicyProto.internal_static_google_iam_v1_BindingDelta_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PolicyProto.internal_static_google_iam_v1_BindingDelta_fieldAccessorTable.ensureFieldAccessorsInitialized(BindingDelta.class, Builder.class);
    }

    @Override
    public int getActionValue() {
        return this.action_;
    }

    @Override
    public Action getAction() {
        Action result2 = Action.valueOf(this.action_);
        return result2 == null ? Action.UNRECOGNIZED : result2;
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

    @Override
    public String getMember() {
        Object ref = this.member_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.member_ = s2;
        return s2;
    }

    @Override
    public ByteString getMemberBytes() {
        Object ref = this.member_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.member_ = b;
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
        if (this.action_ != Action.ACTION_UNSPECIFIED.getNumber()) {
            output.writeEnum(1, this.action_);
        }
        if (!this.getRoleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.role_);
        }
        if (!this.getMemberBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.member_);
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
        if (this.action_ != Action.ACTION_UNSPECIFIED.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(1, this.action_);
        }
        if (!this.getRoleBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.role_);
        }
        if (!this.getMemberBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.member_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BindingDelta)) {
            return super.equals(obj);
        }
        BindingDelta other = (BindingDelta)obj;
        boolean result2 = true;
        result2 = result2 && this.action_ == other.action_;
        result2 = result2 && this.getRole().equals(other.getRole());
        result2 = result2 && this.getMember().equals(other.getMember());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + BindingDelta.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.action_;
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getRole().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getMember().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static BindingDelta parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BindingDelta parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BindingDelta parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BindingDelta parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BindingDelta parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BindingDelta parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BindingDelta parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static BindingDelta parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static BindingDelta parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static BindingDelta parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static BindingDelta parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static BindingDelta parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return BindingDelta.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BindingDelta prototype) {
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

    public static BindingDelta getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BindingDelta> parser() {
        return PARSER;
    }

    public Parser<BindingDelta> getParserForType() {
        return PARSER;
    }

    @Override
    public BindingDelta getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements BindingDeltaOrBuilder {
        private int action_ = 0;
        private Object role_ = "";
        private Object member_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return PolicyProto.internal_static_google_iam_v1_BindingDelta_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PolicyProto.internal_static_google_iam_v1_BindingDelta_fieldAccessorTable.ensureFieldAccessorsInitialized(BindingDelta.class, Builder.class);
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
            this.action_ = 0;
            this.role_ = "";
            this.member_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return PolicyProto.internal_static_google_iam_v1_BindingDelta_descriptor;
        }

        @Override
        public BindingDelta getDefaultInstanceForType() {
            return BindingDelta.getDefaultInstance();
        }

        @Override
        public BindingDelta build() {
            BindingDelta result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public BindingDelta buildPartial() {
            BindingDelta result2 = new BindingDelta(this);
            result2.action_ = this.action_;
            result2.role_ = this.role_;
            result2.member_ = this.member_;
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
            if (other instanceof BindingDelta) {
                return this.mergeFrom((BindingDelta)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(BindingDelta other) {
            if (other == BindingDelta.getDefaultInstance()) {
                return this;
            }
            if (other.action_ != 0) {
                this.setActionValue(other.getActionValue());
            }
            if (!other.getRole().isEmpty()) {
                this.role_ = other.role_;
                this.onChanged();
            }
            if (!other.getMember().isEmpty()) {
                this.member_ = other.member_;
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
            BindingDelta parsedMessage = null;
            try {
                parsedMessage = (BindingDelta)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (BindingDelta)e.getUnfinishedMessage();
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
        public int getActionValue() {
            return this.action_;
        }

        public Builder setActionValue(int value) {
            this.action_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public Action getAction() {
            Action result2 = Action.valueOf(this.action_);
            return result2 == null ? Action.UNRECOGNIZED : result2;
        }

        public Builder setAction(Action value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.action_ = value.getNumber();
            this.onChanged();
            return this;
        }

        public Builder clearAction() {
            this.action_ = 0;
            this.onChanged();
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
            this.role_ = BindingDelta.getDefaultInstance().getRole();
            this.onChanged();
            return this;
        }

        public Builder setRoleBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            BindingDelta.checkByteStringIsUtf8(value);
            this.role_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getMember() {
            Object ref = this.member_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.member_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getMemberBytes() {
            Object ref = this.member_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.member_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setMember(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.member_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMember() {
            this.member_ = BindingDelta.getDefaultInstance().getMember();
            this.onChanged();
            return this;
        }

        public Builder setMemberBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            BindingDelta.checkByteStringIsUtf8(value);
            this.member_ = value;
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

    public static enum Action implements ProtocolMessageEnum
    {
        ACTION_UNSPECIFIED(0),
        ADD(1),
        REMOVE(2),
        UNRECOGNIZED(-1);

        public static final int ACTION_UNSPECIFIED_VALUE = 0;
        public static final int ADD_VALUE = 1;
        public static final int REMOVE_VALUE = 2;
        private static final Internal.EnumLiteMap<Action> internalValueMap;
        private static final Action[] VALUES;
        private final int value;

        @Override
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static Action valueOf(int value) {
            return Action.forNumber(value);
        }

        public static Action forNumber(int value) {
            switch (value) {
                case 0: {
                    return ACTION_UNSPECIFIED;
                }
                case 1: {
                    return ADD;
                }
                case 2: {
                    return REMOVE;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<Action> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return Action.getDescriptor().getValues().get(this.ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return Action.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return BindingDelta.getDescriptor().getEnumTypes().get(0);
        }

        public static Action valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != Action.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private Action(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<Action>(){

                @Override
                public Action findValueByNumber(int number) {
                    return Action.forNumber(number);
                }
            };
            VALUES = Action.values();
        }
    }
}

