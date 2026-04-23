/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.rpc.StatusOrBuilder;
import com.google.rpc.StatusProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Status
extends GeneratedMessageV3
implements StatusOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int CODE_FIELD_NUMBER = 1;
    private int code_;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private volatile Object message_;
    public static final int DETAILS_FIELD_NUMBER = 3;
    private List<Any> details_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Status DEFAULT_INSTANCE = new Status();
    private static final Parser<Status> PARSER = new AbstractParser<Status>(){

        @Override
        public Status parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Status(input2, extensionRegistry);
        }
    };

    private Status(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Status() {
        this.code_ = 0;
        this.message_ = "";
        this.details_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Status(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.code_ = input2.readInt32();
                        continue block12;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.message_ = s2;
                        continue block12;
                    }
                    case 26: 
                }
                if ((mutable_bitField0_ & 4) != 4) {
                    this.details_ = new ArrayList<Any>();
                    mutable_bitField0_ |= 4;
                }
                this.details_.add(input2.readMessage(Any.parser(), extensionRegistry));
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 4) == 4) {
                this.details_ = Collections.unmodifiableList(this.details_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StatusProto.internal_static_google_rpc_Status_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
    }

    @Override
    public int getCode() {
        return this.code_;
    }

    @Override
    public String getMessage() {
        Object ref = this.message_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.message_ = s2;
        return s2;
    }

    @Override
    public ByteString getMessageBytes() {
        Object ref = this.message_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.message_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public List<Any> getDetailsList() {
        return this.details_;
    }

    @Override
    public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
        return this.details_;
    }

    @Override
    public int getDetailsCount() {
        return this.details_.size();
    }

    @Override
    public Any getDetails(int index) {
        return this.details_.get(index);
    }

    @Override
    public AnyOrBuilder getDetailsOrBuilder(int index) {
        return this.details_.get(index);
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
        if (this.code_ != 0) {
            output.writeInt32(1, this.code_);
        }
        if (!this.getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.message_);
        }
        for (int i = 0; i < this.details_.size(); ++i) {
            output.writeMessage(3, this.details_.get(i));
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
        if (this.code_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(1, this.code_);
        }
        if (!this.getMessageBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.message_);
        }
        for (int i = 0; i < this.details_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.details_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Status)) {
            return super.equals(obj);
        }
        Status other = (Status)obj;
        boolean result2 = true;
        result2 = result2 && this.getCode() == other.getCode();
        result2 = result2 && this.getMessage().equals(other.getMessage());
        result2 = result2 && this.getDetailsList().equals(other.getDetailsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Status.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getMessage().hashCode();
        if (this.getDetailsCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getDetailsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Status parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Status parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Status parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Status parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Status parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Status parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Status parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Status parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Status parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Status parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Status parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Status parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Status.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Status prototype) {
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

    public static Status getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Status> parser() {
        return PARSER;
    }

    public Parser<Status> getParserForType() {
        return PARSER;
    }

    @Override
    public Status getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements StatusOrBuilder {
        private int bitField0_;
        private int code_;
        private Object message_ = "";
        private List<Any> details_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> detailsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
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
                this.getDetailsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.code_ = 0;
            this.message_ = "";
            if (this.detailsBuilder_ == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
            } else {
                this.detailsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        @Override
        public Status getDefaultInstanceForType() {
            return Status.getDefaultInstance();
        }

        @Override
        public Status build() {
            Status result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Status buildPartial() {
            Status result2 = new Status(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.code_ = this.code_;
            result2.message_ = this.message_;
            if (this.detailsBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.details_ = Collections.unmodifiableList(this.details_);
                    this.bitField0_ &= 0xFFFFFFFB;
                }
                result2.details_ = this.details_;
            } else {
                result2.details_ = this.detailsBuilder_.build();
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
            if (other instanceof Status) {
                return this.mergeFrom((Status)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Status other) {
            if (other == Status.getDefaultInstance()) {
                return this;
            }
            if (other.getCode() != 0) {
                this.setCode(other.getCode());
            }
            if (!other.getMessage().isEmpty()) {
                this.message_ = other.message_;
                this.onChanged();
            }
            if (this.detailsBuilder_ == null) {
                if (!other.details_.isEmpty()) {
                    if (this.details_.isEmpty()) {
                        this.details_ = other.details_;
                        this.bitField0_ &= 0xFFFFFFFB;
                    } else {
                        this.ensureDetailsIsMutable();
                        this.details_.addAll(other.details_);
                    }
                    this.onChanged();
                }
            } else if (!other.details_.isEmpty()) {
                if (this.detailsBuilder_.isEmpty()) {
                    this.detailsBuilder_.dispose();
                    this.detailsBuilder_ = null;
                    this.details_ = other.details_;
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.detailsBuilder_ = alwaysUseFieldBuilders ? this.getDetailsFieldBuilder() : null;
                } else {
                    this.detailsBuilder_.addAllMessages(other.details_);
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
            Status parsedMessage = null;
            try {
                parsedMessage = (Status)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Status)e.getUnfinishedMessage();
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
        public int getCode() {
            return this.code_;
        }

        public Builder setCode(int value) {
            this.code_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearCode() {
            this.code_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public String getMessage() {
            Object ref = this.message_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.message_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getMessageBytes() {
            Object ref = this.message_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.message_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setMessage(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.message_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMessage() {
            this.message_ = Status.getDefaultInstance().getMessage();
            this.onChanged();
            return this;
        }

        public Builder setMessageBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Status.checkByteStringIsUtf8(value);
            this.message_ = value;
            this.onChanged();
            return this;
        }

        private void ensureDetailsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.details_ = new ArrayList<Any>(this.details_);
                this.bitField0_ |= 4;
            }
        }

        @Override
        public List<Any> getDetailsList() {
            if (this.detailsBuilder_ == null) {
                return Collections.unmodifiableList(this.details_);
            }
            return this.detailsBuilder_.getMessageList();
        }

        @Override
        public int getDetailsCount() {
            if (this.detailsBuilder_ == null) {
                return this.details_.size();
            }
            return this.detailsBuilder_.getCount();
        }

        @Override
        public Any getDetails(int index) {
            if (this.detailsBuilder_ == null) {
                return this.details_.get(index);
            }
            return this.detailsBuilder_.getMessage(index);
        }

        public Builder setDetails(int index, Any value) {
            if (this.detailsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDetailsIsMutable();
                this.details_.set(index, value);
                this.onChanged();
            } else {
                this.detailsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setDetails(int index, Any.Builder builderForValue) {
            if (this.detailsBuilder_ == null) {
                this.ensureDetailsIsMutable();
                this.details_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.detailsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addDetails(Any value) {
            if (this.detailsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDetailsIsMutable();
                this.details_.add(value);
                this.onChanged();
            } else {
                this.detailsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addDetails(int index, Any value) {
            if (this.detailsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDetailsIsMutable();
                this.details_.add(index, value);
                this.onChanged();
            } else {
                this.detailsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addDetails(Any.Builder builderForValue) {
            if (this.detailsBuilder_ == null) {
                this.ensureDetailsIsMutable();
                this.details_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.detailsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addDetails(int index, Any.Builder builderForValue) {
            if (this.detailsBuilder_ == null) {
                this.ensureDetailsIsMutable();
                this.details_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.detailsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllDetails(Iterable<? extends Any> values) {
            if (this.detailsBuilder_ == null) {
                this.ensureDetailsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.details_);
                this.onChanged();
            } else {
                this.detailsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearDetails() {
            if (this.detailsBuilder_ == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFB;
                this.onChanged();
            } else {
                this.detailsBuilder_.clear();
            }
            return this;
        }

        public Builder removeDetails(int index) {
            if (this.detailsBuilder_ == null) {
                this.ensureDetailsIsMutable();
                this.details_.remove(index);
                this.onChanged();
            } else {
                this.detailsBuilder_.remove(index);
            }
            return this;
        }

        public Any.Builder getDetailsBuilder(int index) {
            return this.getDetailsFieldBuilder().getBuilder(index);
        }

        @Override
        public AnyOrBuilder getDetailsOrBuilder(int index) {
            if (this.detailsBuilder_ == null) {
                return this.details_.get(index);
            }
            return this.detailsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
            if (this.detailsBuilder_ != null) {
                return this.detailsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.details_);
        }

        public Any.Builder addDetailsBuilder() {
            return this.getDetailsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addDetailsBuilder(int index) {
            return this.getDetailsFieldBuilder().addBuilder(index, Any.getDefaultInstance());
        }

        public List<Any.Builder> getDetailsBuilderList() {
            return this.getDetailsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getDetailsFieldBuilder() {
            if (this.detailsBuilder_ == null) {
                this.detailsBuilder_ = new RepeatedFieldBuilderV3(this.details_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                this.details_ = null;
            }
            return this.detailsBuilder_;
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

