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
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.BadRequestOrBuilder;
import com.google.rpc.ErrorDetailsProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BadRequest
extends GeneratedMessageV3
implements BadRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int FIELD_VIOLATIONS_FIELD_NUMBER = 1;
    private List<FieldViolation> fieldViolations_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final BadRequest DEFAULT_INSTANCE = new BadRequest();
    private static final Parser<BadRequest> PARSER = new AbstractParser<BadRequest>(){

        @Override
        public BadRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new BadRequest(input2, extensionRegistry);
        }
    };

    private BadRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private BadRequest() {
        this.fieldViolations_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BadRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    this.fieldViolations_ = new ArrayList<FieldViolation>();
                    mutable_bitField0_ |= true;
                }
                this.fieldViolations_.add(input2.readMessage(FieldViolation.parser(), extensionRegistry));
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
                this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
    }

    @Override
    public List<FieldViolation> getFieldViolationsList() {
        return this.fieldViolations_;
    }

    @Override
    public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
        return this.fieldViolations_;
    }

    @Override
    public int getFieldViolationsCount() {
        return this.fieldViolations_.size();
    }

    @Override
    public FieldViolation getFieldViolations(int index) {
        return this.fieldViolations_.get(index);
    }

    @Override
    public FieldViolationOrBuilder getFieldViolationsOrBuilder(int index) {
        return this.fieldViolations_.get(index);
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
        for (int i = 0; i < this.fieldViolations_.size(); ++i) {
            output.writeMessage(1, this.fieldViolations_.get(i));
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
        for (int i = 0; i < this.fieldViolations_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.fieldViolations_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BadRequest)) {
            return super.equals(obj);
        }
        BadRequest other = (BadRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getFieldViolationsList().equals(other.getFieldViolationsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + BadRequest.getDescriptor().hashCode();
        if (this.getFieldViolationsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getFieldViolationsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static BadRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BadRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BadRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BadRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BadRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static BadRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static BadRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static BadRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static BadRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static BadRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static BadRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static BadRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return BadRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BadRequest prototype) {
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

    public static BadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BadRequest> parser() {
        return PARSER;
    }

    public Parser<BadRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public BadRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements BadRequestOrBuilder {
        private int bitField0_;
        private List<FieldViolation> fieldViolations_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> fieldViolationsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
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
                this.getFieldViolationsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.fieldViolationsBuilder_ == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.fieldViolationsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        @Override
        public BadRequest getDefaultInstanceForType() {
            return BadRequest.getDefaultInstance();
        }

        @Override
        public BadRequest build() {
            BadRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public BadRequest buildPartial() {
            BadRequest result2 = new BadRequest(this);
            int from_bitField0_ = this.bitField0_;
            if (this.fieldViolationsBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.fieldViolations_ = this.fieldViolations_;
            } else {
                result2.fieldViolations_ = this.fieldViolationsBuilder_.build();
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
            if (other instanceof BadRequest) {
                return this.mergeFrom((BadRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(BadRequest other) {
            if (other == BadRequest.getDefaultInstance()) {
                return this;
            }
            if (this.fieldViolationsBuilder_ == null) {
                if (!other.fieldViolations_.isEmpty()) {
                    if (this.fieldViolations_.isEmpty()) {
                        this.fieldViolations_ = other.fieldViolations_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureFieldViolationsIsMutable();
                        this.fieldViolations_.addAll(other.fieldViolations_);
                    }
                    this.onChanged();
                }
            } else if (!other.fieldViolations_.isEmpty()) {
                if (this.fieldViolationsBuilder_.isEmpty()) {
                    this.fieldViolationsBuilder_.dispose();
                    this.fieldViolationsBuilder_ = null;
                    this.fieldViolations_ = other.fieldViolations_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.fieldViolationsBuilder_ = alwaysUseFieldBuilders ? this.getFieldViolationsFieldBuilder() : null;
                } else {
                    this.fieldViolationsBuilder_.addAllMessages(other.fieldViolations_);
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
            BadRequest parsedMessage = null;
            try {
                parsedMessage = (BadRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (BadRequest)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureFieldViolationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.fieldViolations_ = new ArrayList<FieldViolation>(this.fieldViolations_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<FieldViolation> getFieldViolationsList() {
            if (this.fieldViolationsBuilder_ == null) {
                return Collections.unmodifiableList(this.fieldViolations_);
            }
            return this.fieldViolationsBuilder_.getMessageList();
        }

        @Override
        public int getFieldViolationsCount() {
            if (this.fieldViolationsBuilder_ == null) {
                return this.fieldViolations_.size();
            }
            return this.fieldViolationsBuilder_.getCount();
        }

        @Override
        public FieldViolation getFieldViolations(int index) {
            if (this.fieldViolationsBuilder_ == null) {
                return this.fieldViolations_.get(index);
            }
            return this.fieldViolationsBuilder_.getMessage(index);
        }

        public Builder setFieldViolations(int index, FieldViolation value) {
            if (this.fieldViolationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(index, value);
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setFieldViolations(int index, FieldViolation.Builder builderForValue) {
            if (this.fieldViolationsBuilder_ == null) {
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addFieldViolations(FieldViolation value) {
            if (this.fieldViolationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(value);
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addFieldViolations(int index, FieldViolation value) {
            if (this.fieldViolationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(index, value);
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addFieldViolations(FieldViolation.Builder builderForValue) {
            if (this.fieldViolationsBuilder_ == null) {
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addFieldViolations(int index, FieldViolation.Builder builderForValue) {
            if (this.fieldViolationsBuilder_ == null) {
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllFieldViolations(Iterable<? extends FieldViolation> values) {
            if (this.fieldViolationsBuilder_ == null) {
                this.ensureFieldViolationsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.fieldViolations_);
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearFieldViolations() {
            if (this.fieldViolationsBuilder_ == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.clear();
            }
            return this;
        }

        public Builder removeFieldViolations(int index) {
            if (this.fieldViolationsBuilder_ == null) {
                this.ensureFieldViolationsIsMutable();
                this.fieldViolations_.remove(index);
                this.onChanged();
            } else {
                this.fieldViolationsBuilder_.remove(index);
            }
            return this;
        }

        public FieldViolation.Builder getFieldViolationsBuilder(int index) {
            return this.getFieldViolationsFieldBuilder().getBuilder(index);
        }

        @Override
        public FieldViolationOrBuilder getFieldViolationsOrBuilder(int index) {
            if (this.fieldViolationsBuilder_ == null) {
                return this.fieldViolations_.get(index);
            }
            return this.fieldViolationsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
            if (this.fieldViolationsBuilder_ != null) {
                return this.fieldViolationsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fieldViolations_);
        }

        public FieldViolation.Builder addFieldViolationsBuilder() {
            return this.getFieldViolationsFieldBuilder().addBuilder(FieldViolation.getDefaultInstance());
        }

        public FieldViolation.Builder addFieldViolationsBuilder(int index) {
            return this.getFieldViolationsFieldBuilder().addBuilder(index, FieldViolation.getDefaultInstance());
        }

        public List<FieldViolation.Builder> getFieldViolationsBuilderList() {
            return this.getFieldViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> getFieldViolationsFieldBuilder() {
            if (this.fieldViolationsBuilder_ == null) {
                this.fieldViolationsBuilder_ = new RepeatedFieldBuilderV3(this.fieldViolations_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.fieldViolations_ = null;
            }
            return this.fieldViolationsBuilder_;
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

    public static final class FieldViolation
    extends GeneratedMessageV3
    implements FieldViolationOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int FIELD_FIELD_NUMBER = 1;
        private volatile Object field_;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        private volatile Object description_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FieldViolation DEFAULT_INSTANCE = new FieldViolation();
        private static final Parser<FieldViolation> PARSER = new AbstractParser<FieldViolation>(){

            @Override
            public FieldViolation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FieldViolation(input2, extensionRegistry);
            }
        };

        private FieldViolation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FieldViolation() {
            this.field_ = "";
            this.description_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FieldViolation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.field_ = s2;
                            continue block11;
                        }
                        case 18: 
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
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
        }

        @Override
        public String getField() {
            Object ref = this.field_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            this.field_ = s2;
            return s2;
        }

        @Override
        public ByteString getFieldBytes() {
            Object ref = this.field_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.field_ = b;
                return b;
            }
            return (ByteString)ref;
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
            if (!this.getFieldBytes().isEmpty()) {
                GeneratedMessageV3.writeString(output, 1, this.field_);
            }
            if (!this.getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(output, 2, this.description_);
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
            if (!this.getFieldBytes().isEmpty()) {
                size2 += GeneratedMessageV3.computeStringSize(1, this.field_);
            }
            if (!this.getDescriptionBytes().isEmpty()) {
                size2 += GeneratedMessageV3.computeStringSize(2, this.description_);
            }
            this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
            return size2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldViolation)) {
                return super.equals(obj);
            }
            FieldViolation other = (FieldViolation)obj;
            boolean result2 = true;
            result2 = result2 && this.getField().equals(other.getField());
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
            hash = 19 * hash + FieldViolation.getDescriptor().hashCode();
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getField().hashCode();
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getDescription().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FieldViolation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldViolation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldViolation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldViolation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldViolation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldViolation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldViolation parseFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static FieldViolation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        public static FieldViolation parseDelimitedFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
        }

        public static FieldViolation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
        }

        public static FieldViolation parseFrom(CodedInputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static FieldViolation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return FieldViolation.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldViolation prototype) {
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

        public static FieldViolation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldViolation> parser() {
            return PARSER;
        }

        public Parser<FieldViolation> getParserForType() {
            return PARSER;
        }

        @Override
        public FieldViolation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FieldViolationOrBuilder {
            private Object field_ = "";
            private Object description_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
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
                this.field_ = "";
                this.description_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            @Override
            public FieldViolation getDefaultInstanceForType() {
                return FieldViolation.getDefaultInstance();
            }

            @Override
            public FieldViolation build() {
                FieldViolation result2 = this.buildPartial();
                if (!result2.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result2);
                }
                return result2;
            }

            @Override
            public FieldViolation buildPartial() {
                FieldViolation result2 = new FieldViolation(this);
                result2.field_ = this.field_;
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
                if (other instanceof FieldViolation) {
                    return this.mergeFrom((FieldViolation)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FieldViolation other) {
                if (other == FieldViolation.getDefaultInstance()) {
                    return this;
                }
                if (!other.getField().isEmpty()) {
                    this.field_ = other.field_;
                    this.onChanged();
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
                FieldViolation parsedMessage = null;
                try {
                    parsedMessage = (FieldViolation)PARSER.parsePartialFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FieldViolation)e.getUnfinishedMessage();
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
            public String getField() {
                Object ref = this.field_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    this.field_ = s2;
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getFieldBytes() {
                Object ref = this.field_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.field_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setField(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.field_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearField() {
                this.field_ = FieldViolation.getDefaultInstance().getField();
                this.onChanged();
                return this;
            }

            public Builder setFieldBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                FieldViolation.checkByteStringIsUtf8(value);
                this.field_ = value;
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
                this.description_ = FieldViolation.getDefaultInstance().getDescription();
                this.onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                FieldViolation.checkByteStringIsUtf8(value);
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
    }

    public static interface FieldViolationOrBuilder
    extends MessageOrBuilder {
        public String getField();

        public ByteString getFieldBytes();

        public String getDescription();

        public ByteString getDescriptionBytes();
    }
}

