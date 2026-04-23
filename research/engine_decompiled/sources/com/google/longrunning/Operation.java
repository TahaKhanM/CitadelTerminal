/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning;

import com.google.longrunning.OperationOrBuilder;
import com.google.longrunning.OperationsProto;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Operation
extends GeneratedMessageV3
implements OperationOrBuilder {
    private static final long serialVersionUID = 0L;
    private int resultCase_ = 0;
    private Object result_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int METADATA_FIELD_NUMBER = 2;
    private Any metadata_;
    public static final int DONE_FIELD_NUMBER = 3;
    private boolean done_;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int RESPONSE_FIELD_NUMBER = 5;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Operation DEFAULT_INSTANCE = new Operation();
    private static final Parser<Operation> PARSER = new AbstractParser<Operation>(){

        @Override
        public Operation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Operation(input2, extensionRegistry);
        }
    };

    private Operation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Operation() {
        this.name_ = "";
        this.done_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Operation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block14: while (!done) {
                GeneratedMessageV3.Builder subBuilder;
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
                        String s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block14;
                    }
                    case 18: {
                        subBuilder = null;
                        if (this.metadata_ != null) {
                            subBuilder = this.metadata_.toBuilder();
                        }
                        this.metadata_ = input2.readMessage(Any.parser(), extensionRegistry);
                        if (subBuilder == null) continue block14;
                        ((Any.Builder)subBuilder).mergeFrom(this.metadata_);
                        this.metadata_ = ((Any.Builder)subBuilder).buildPartial();
                        continue block14;
                    }
                    case 24: {
                        this.done_ = input2.readBool();
                        continue block14;
                    }
                    case 34: {
                        subBuilder = null;
                        if (this.resultCase_ == 4) {
                            subBuilder = ((Status)this.result_).toBuilder();
                        }
                        this.result_ = input2.readMessage(Status.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            ((Status.Builder)subBuilder).mergeFrom((Status)this.result_);
                            this.result_ = ((Status.Builder)subBuilder).buildPartial();
                        }
                        this.resultCase_ = 4;
                        continue block14;
                    }
                    case 42: 
                }
                subBuilder = null;
                if (this.resultCase_ == 5) {
                    subBuilder = ((Any)this.result_).toBuilder();
                }
                this.result_ = input2.readMessage(Any.parser(), extensionRegistry);
                if (subBuilder != null) {
                    ((Any.Builder)subBuilder).mergeFrom((Any)this.result_);
                    this.result_ = ((Any.Builder)subBuilder).buildPartial();
                }
                this.resultCase_ = 5;
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
        return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
    }

    @Override
    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
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

    @Override
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override
    public Any getMetadata() {
        return this.metadata_ == null ? Any.getDefaultInstance() : this.metadata_;
    }

    @Override
    public AnyOrBuilder getMetadataOrBuilder() {
        return this.getMetadata();
    }

    @Override
    public boolean getDone() {
        return this.done_;
    }

    @Override
    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    @Override
    public Status getError() {
        if (this.resultCase_ == 4) {
            return (Status)this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override
    public StatusOrBuilder getErrorOrBuilder() {
        if (this.resultCase_ == 4) {
            return (Status)this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override
    public boolean hasResponse() {
        return this.resultCase_ == 5;
    }

    @Override
    public Any getResponse() {
        if (this.resultCase_ == 5) {
            return (Any)this.result_;
        }
        return Any.getDefaultInstance();
    }

    @Override
    public AnyOrBuilder getResponseOrBuilder() {
        if (this.resultCase_ == 5) {
            return (Any)this.result_;
        }
        return Any.getDefaultInstance();
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        if (this.metadata_ != null) {
            output.writeMessage(2, this.getMetadata());
        }
        if (this.done_) {
            output.writeBool(3, this.done_);
        }
        if (this.resultCase_ == 4) {
            output.writeMessage(4, (Status)this.result_);
        }
        if (this.resultCase_ == 5) {
            output.writeMessage(5, (Any)this.result_);
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
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.metadata_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getMetadata());
        }
        if (this.done_) {
            size2 += CodedOutputStream.computeBoolSize(3, this.done_);
        }
        if (this.resultCase_ == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, (Status)this.result_);
        }
        if (this.resultCase_ == 5) {
            size2 += CodedOutputStream.computeMessageSize(5, (Any)this.result_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operation)) {
            return super.equals(obj);
        }
        Operation other = (Operation)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        boolean bl = result2 = result2 && this.hasMetadata() == other.hasMetadata();
        if (this.hasMetadata()) {
            result2 = result2 && this.getMetadata().equals(other.getMetadata());
        }
        result2 = result2 && this.getDone() == other.getDone();
        boolean bl2 = result2 = result2 && this.getResultCase().equals(other.getResultCase());
        if (!result2) {
            return false;
        }
        switch (this.resultCase_) {
            case 4: {
                result2 = result2 && this.getError().equals(other.getError());
                break;
            }
            case 5: {
                result2 = result2 && this.getResponse().equals(other.getResponse());
                break;
            }
        }
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Operation.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        if (this.hasMetadata()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getMetadata().hashCode();
        }
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashBoolean(this.getDone());
        switch (this.resultCase_) {
            case 4: {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getError().hashCode();
                break;
            }
            case 5: {
                hash = 37 * hash + 5;
                hash = 53 * hash + this.getResponse().hashCode();
                break;
            }
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Operation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Operation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Operation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Operation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Operation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Operation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Operation parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Operation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Operation parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Operation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Operation parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Operation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Operation.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Operation prototype) {
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

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Operation> parser() {
        return PARSER;
    }

    public Parser<Operation> getParserForType() {
        return PARSER;
    }

    @Override
    public Operation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements OperationOrBuilder {
        private int resultCase_ = 0;
        private Object result_;
        private Object name_ = "";
        private Any metadata_ = null;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> metadataBuilder_;
        private boolean done_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> errorBuilder_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> responseBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
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
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.done_ = false;
            this.resultCase_ = 0;
            this.result_ = null;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        @Override
        public Operation getDefaultInstanceForType() {
            return Operation.getDefaultInstance();
        }

        @Override
        public Operation build() {
            Operation result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Operation buildPartial() {
            Operation result2 = new Operation(this);
            result2.name_ = this.name_;
            if (this.metadataBuilder_ == null) {
                result2.metadata_ = this.metadata_;
            } else {
                result2.metadata_ = this.metadataBuilder_.build();
            }
            result2.done_ = this.done_;
            if (this.resultCase_ == 4) {
                if (this.errorBuilder_ == null) {
                    result2.result_ = this.result_;
                } else {
                    result2.result_ = this.errorBuilder_.build();
                }
            }
            if (this.resultCase_ == 5) {
                if (this.responseBuilder_ == null) {
                    result2.result_ = this.result_;
                } else {
                    result2.result_ = this.responseBuilder_.build();
                }
            }
            result2.resultCase_ = this.resultCase_;
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
            if (other instanceof Operation) {
                return this.mergeFrom((Operation)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Operation other) {
            if (other == Operation.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (other.hasMetadata()) {
                this.mergeMetadata(other.getMetadata());
            }
            if (other.getDone()) {
                this.setDone(other.getDone());
            }
            switch (other.getResultCase()) {
                case ERROR: {
                    this.mergeError(other.getError());
                    break;
                }
                case RESPONSE: {
                    this.mergeResponse(other.getResponse());
                    break;
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
            Operation parsedMessage = null;
            try {
                parsedMessage = (Operation)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Operation)e.getUnfinishedMessage();
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
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        public Builder clearResult() {
            this.resultCase_ = 0;
            this.result_ = null;
            this.onChanged();
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
            this.name_ = Operation.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Operation.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasMetadata() {
            return this.metadataBuilder_ != null || this.metadata_ != null;
        }

        @Override
        public Any getMetadata() {
            if (this.metadataBuilder_ == null) {
                return this.metadata_ == null ? Any.getDefaultInstance() : this.metadata_;
            }
            return this.metadataBuilder_.getMessage();
        }

        public Builder setMetadata(Any value) {
            if (this.metadataBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.metadata_ = value;
                this.onChanged();
            } else {
                this.metadataBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setMetadata(Any.Builder builderForValue) {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = builderForValue.build();
                this.onChanged();
            } else {
                this.metadataBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeMetadata(Any value) {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = this.metadata_ != null ? Any.newBuilder(this.metadata_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.metadataBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                this.onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getMetadataBuilder() {
            this.onChanged();
            return this.getMetadataFieldBuilder().getBuilder();
        }

        @Override
        public AnyOrBuilder getMetadataOrBuilder() {
            if (this.metadataBuilder_ != null) {
                return this.metadataBuilder_.getMessageOrBuilder();
            }
            return this.metadata_ == null ? Any.getDefaultInstance() : this.metadata_;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3(this.getMetadata(), this.getParentForChildren(), this.isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override
        public boolean getDone() {
            return this.done_;
        }

        public Builder setDone(boolean value) {
            this.done_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDone() {
            this.done_ = false;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasError() {
            return this.resultCase_ == 4;
        }

        @Override
        public Status getError() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ == 4) {
                    return (Status)this.result_;
                }
                return Status.getDefaultInstance();
            }
            if (this.resultCase_ == 4) {
                return this.errorBuilder_.getMessage();
            }
            return Status.getDefaultInstance();
        }

        public Builder setError(Status value) {
            if (this.errorBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.result_ = value;
                this.onChanged();
            } else {
                this.errorBuilder_.setMessage(value);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder setError(Status.Builder builderForValue) {
            if (this.errorBuilder_ == null) {
                this.result_ = builderForValue.build();
                this.onChanged();
            } else {
                this.errorBuilder_.setMessage(builderForValue.build());
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder mergeError(Status value) {
            if (this.errorBuilder_ == null) {
                this.result_ = this.resultCase_ == 4 && this.result_ != Status.getDefaultInstance() ? Status.newBuilder((Status)this.result_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                if (this.resultCase_ == 4) {
                    this.errorBuilder_.mergeFrom(value);
                }
                this.errorBuilder_.setMessage(value);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder clearError() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                    this.onChanged();
                }
            } else {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.errorBuilder_.clear();
            }
            return this;
        }

        public Status.Builder getErrorBuilder() {
            return this.getErrorFieldBuilder().getBuilder();
        }

        @Override
        public StatusOrBuilder getErrorOrBuilder() {
            if (this.resultCase_ == 4 && this.errorBuilder_ != null) {
                return this.errorBuilder_.getMessageOrBuilder();
            }
            if (this.resultCase_ == 4) {
                return (Status)this.result_;
            }
            return Status.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getErrorFieldBuilder() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ != 4) {
                    this.result_ = Status.getDefaultInstance();
                }
                this.errorBuilder_ = new SingleFieldBuilderV3((Status)this.result_, this.getParentForChildren(), this.isClean());
                this.result_ = null;
            }
            this.resultCase_ = 4;
            this.onChanged();
            return this.errorBuilder_;
        }

        @Override
        public boolean hasResponse() {
            return this.resultCase_ == 5;
        }

        @Override
        public Any getResponse() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ == 5) {
                    return (Any)this.result_;
                }
                return Any.getDefaultInstance();
            }
            if (this.resultCase_ == 5) {
                return this.responseBuilder_.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setResponse(Any value) {
            if (this.responseBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.result_ = value;
                this.onChanged();
            } else {
                this.responseBuilder_.setMessage(value);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder setResponse(Any.Builder builderForValue) {
            if (this.responseBuilder_ == null) {
                this.result_ = builderForValue.build();
                this.onChanged();
            } else {
                this.responseBuilder_.setMessage(builderForValue.build());
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder mergeResponse(Any value) {
            if (this.responseBuilder_ == null) {
                this.result_ = this.resultCase_ == 5 && this.result_ != Any.getDefaultInstance() ? Any.newBuilder((Any)this.result_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                if (this.resultCase_ == 5) {
                    this.responseBuilder_.mergeFrom(value);
                }
                this.responseBuilder_.setMessage(value);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                    this.onChanged();
                }
            } else {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.responseBuilder_.clear();
            }
            return this;
        }

        public Any.Builder getResponseBuilder() {
            return this.getResponseFieldBuilder().getBuilder();
        }

        @Override
        public AnyOrBuilder getResponseOrBuilder() {
            if (this.resultCase_ == 5 && this.responseBuilder_ != null) {
                return this.responseBuilder_.getMessageOrBuilder();
            }
            if (this.resultCase_ == 5) {
                return (Any)this.result_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ != 5) {
                    this.result_ = Any.getDefaultInstance();
                }
                this.responseBuilder_ = new SingleFieldBuilderV3((Any)this.result_, this.getParentForChildren(), this.isClean());
                this.result_ = null;
            }
            this.resultCase_ = 5;
            this.onChanged();
            return this.responseBuilder_;
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

    public static enum ResultCase implements Internal.EnumLite
    {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);

        private final int value;

        private ResultCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static ResultCase valueOf(int value) {
            return ResultCase.forNumber(value);
        }

        public static ResultCase forNumber(int value) {
            switch (value) {
                case 4: {
                    return ERROR;
                }
                case 5: {
                    return RESPONSE;
                }
                case 0: {
                    return RESULT_NOT_SET;
                }
            }
            return null;
        }

        @Override
        public int getNumber() {
            return this.value;
        }
    }
}

