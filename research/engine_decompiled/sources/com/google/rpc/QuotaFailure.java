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
import com.google.rpc.ErrorDetailsProto;
import com.google.rpc.QuotaFailureOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuotaFailure
extends GeneratedMessageV3
implements QuotaFailureOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int VIOLATIONS_FIELD_NUMBER = 1;
    private List<Violation> violations_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final QuotaFailure DEFAULT_INSTANCE = new QuotaFailure();
    private static final Parser<QuotaFailure> PARSER = new AbstractParser<QuotaFailure>(){

        @Override
        public QuotaFailure parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new QuotaFailure(input2, extensionRegistry);
        }
    };

    private QuotaFailure(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private QuotaFailure() {
        this.violations_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private QuotaFailure(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    this.violations_ = new ArrayList<Violation>();
                    mutable_bitField0_ |= true;
                }
                this.violations_.add(input2.readMessage(Violation.parser(), extensionRegistry));
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
                this.violations_ = Collections.unmodifiableList(this.violations_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaFailure.class, Builder.class);
    }

    @Override
    public List<Violation> getViolationsList() {
        return this.violations_;
    }

    @Override
    public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
        return this.violations_;
    }

    @Override
    public int getViolationsCount() {
        return this.violations_.size();
    }

    @Override
    public Violation getViolations(int index) {
        return this.violations_.get(index);
    }

    @Override
    public ViolationOrBuilder getViolationsOrBuilder(int index) {
        return this.violations_.get(index);
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
        for (int i = 0; i < this.violations_.size(); ++i) {
            output.writeMessage(1, this.violations_.get(i));
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
        for (int i = 0; i < this.violations_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.violations_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QuotaFailure)) {
            return super.equals(obj);
        }
        QuotaFailure other = (QuotaFailure)obj;
        boolean result2 = true;
        result2 = result2 && this.getViolationsList().equals(other.getViolationsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + QuotaFailure.getDescriptor().hashCode();
        if (this.getViolationsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getViolationsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static QuotaFailure parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static QuotaFailure parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static QuotaFailure parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static QuotaFailure parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static QuotaFailure parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static QuotaFailure parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static QuotaFailure parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static QuotaFailure parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static QuotaFailure parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static QuotaFailure parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static QuotaFailure parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static QuotaFailure parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return QuotaFailure.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QuotaFailure prototype) {
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

    public static QuotaFailure getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QuotaFailure> parser() {
        return PARSER;
    }

    public Parser<QuotaFailure> getParserForType() {
        return PARSER;
    }

    @Override
    public QuotaFailure getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements QuotaFailureOrBuilder {
        private int bitField0_;
        private List<Violation> violations_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> violationsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaFailure.class, Builder.class);
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
                this.getViolationsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.violationsBuilder_ == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.violationsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_descriptor;
        }

        @Override
        public QuotaFailure getDefaultInstanceForType() {
            return QuotaFailure.getDefaultInstance();
        }

        @Override
        public QuotaFailure build() {
            QuotaFailure result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public QuotaFailure buildPartial() {
            QuotaFailure result2 = new QuotaFailure(this);
            int from_bitField0_ = this.bitField0_;
            if (this.violationsBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.violations_ = this.violations_;
            } else {
                result2.violations_ = this.violationsBuilder_.build();
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
            if (other instanceof QuotaFailure) {
                return this.mergeFrom((QuotaFailure)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(QuotaFailure other) {
            if (other == QuotaFailure.getDefaultInstance()) {
                return this;
            }
            if (this.violationsBuilder_ == null) {
                if (!other.violations_.isEmpty()) {
                    if (this.violations_.isEmpty()) {
                        this.violations_ = other.violations_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureViolationsIsMutable();
                        this.violations_.addAll(other.violations_);
                    }
                    this.onChanged();
                }
            } else if (!other.violations_.isEmpty()) {
                if (this.violationsBuilder_.isEmpty()) {
                    this.violationsBuilder_.dispose();
                    this.violationsBuilder_ = null;
                    this.violations_ = other.violations_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.violationsBuilder_ = alwaysUseFieldBuilders ? this.getViolationsFieldBuilder() : null;
                } else {
                    this.violationsBuilder_.addAllMessages(other.violations_);
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
            QuotaFailure parsedMessage = null;
            try {
                parsedMessage = (QuotaFailure)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (QuotaFailure)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureViolationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.violations_ = new ArrayList<Violation>(this.violations_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<Violation> getViolationsList() {
            if (this.violationsBuilder_ == null) {
                return Collections.unmodifiableList(this.violations_);
            }
            return this.violationsBuilder_.getMessageList();
        }

        @Override
        public int getViolationsCount() {
            if (this.violationsBuilder_ == null) {
                return this.violations_.size();
            }
            return this.violationsBuilder_.getCount();
        }

        @Override
        public Violation getViolations(int index) {
            if (this.violationsBuilder_ == null) {
                return this.violations_.get(index);
            }
            return this.violationsBuilder_.getMessage(index);
        }

        public Builder setViolations(int index, Violation value) {
            if (this.violationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureViolationsIsMutable();
                this.violations_.set(index, value);
                this.onChanged();
            } else {
                this.violationsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setViolations(int index, Violation.Builder builderForValue) {
            if (this.violationsBuilder_ == null) {
                this.ensureViolationsIsMutable();
                this.violations_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.violationsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addViolations(Violation value) {
            if (this.violationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureViolationsIsMutable();
                this.violations_.add(value);
                this.onChanged();
            } else {
                this.violationsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addViolations(int index, Violation value) {
            if (this.violationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureViolationsIsMutable();
                this.violations_.add(index, value);
                this.onChanged();
            } else {
                this.violationsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addViolations(Violation.Builder builderForValue) {
            if (this.violationsBuilder_ == null) {
                this.ensureViolationsIsMutable();
                this.violations_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.violationsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addViolations(int index, Violation.Builder builderForValue) {
            if (this.violationsBuilder_ == null) {
                this.ensureViolationsIsMutable();
                this.violations_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.violationsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllViolations(Iterable<? extends Violation> values) {
            if (this.violationsBuilder_ == null) {
                this.ensureViolationsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.violations_);
                this.onChanged();
            } else {
                this.violationsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearViolations() {
            if (this.violationsBuilder_ == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.violationsBuilder_.clear();
            }
            return this;
        }

        public Builder removeViolations(int index) {
            if (this.violationsBuilder_ == null) {
                this.ensureViolationsIsMutable();
                this.violations_.remove(index);
                this.onChanged();
            } else {
                this.violationsBuilder_.remove(index);
            }
            return this;
        }

        public Violation.Builder getViolationsBuilder(int index) {
            return this.getViolationsFieldBuilder().getBuilder(index);
        }

        @Override
        public ViolationOrBuilder getViolationsOrBuilder(int index) {
            if (this.violationsBuilder_ == null) {
                return this.violations_.get(index);
            }
            return this.violationsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
            if (this.violationsBuilder_ != null) {
                return this.violationsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.violations_);
        }

        public Violation.Builder addViolationsBuilder() {
            return this.getViolationsFieldBuilder().addBuilder(Violation.getDefaultInstance());
        }

        public Violation.Builder addViolationsBuilder(int index) {
            return this.getViolationsFieldBuilder().addBuilder(index, Violation.getDefaultInstance());
        }

        public List<Violation.Builder> getViolationsBuilderList() {
            return this.getViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> getViolationsFieldBuilder() {
            if (this.violationsBuilder_ == null) {
                this.violationsBuilder_ = new RepeatedFieldBuilderV3(this.violations_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.violations_ = null;
            }
            return this.violationsBuilder_;
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

    public static final class Violation
    extends GeneratedMessageV3
    implements ViolationOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int SUBJECT_FIELD_NUMBER = 1;
        private volatile Object subject_;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        private volatile Object description_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Violation DEFAULT_INSTANCE = new Violation();
        private static final Parser<Violation> PARSER = new AbstractParser<Violation>(){

            @Override
            public Violation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Violation(input2, extensionRegistry);
            }
        };

        private Violation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Violation() {
            this.subject_ = "";
            this.description_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Violation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.subject_ = s2;
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
            return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_Violation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
        }

        @Override
        public String getSubject() {
            Object ref = this.subject_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            this.subject_ = s2;
            return s2;
        }

        @Override
        public ByteString getSubjectBytes() {
            Object ref = this.subject_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.subject_ = b;
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
            if (!this.getSubjectBytes().isEmpty()) {
                GeneratedMessageV3.writeString(output, 1, this.subject_);
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
            if (!this.getSubjectBytes().isEmpty()) {
                size2 += GeneratedMessageV3.computeStringSize(1, this.subject_);
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
            if (!(obj instanceof Violation)) {
                return super.equals(obj);
            }
            Violation other = (Violation)obj;
            boolean result2 = true;
            result2 = result2 && this.getSubject().equals(other.getSubject());
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
            hash = 19 * hash + Violation.getDescriptor().hashCode();
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getSubject().hashCode();
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getDescription().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Violation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Violation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Violation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Violation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Violation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Violation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Violation parseFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Violation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Violation parseDelimitedFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
        }

        public static Violation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Violation parseFrom(CodedInputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Violation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return Violation.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Violation prototype) {
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

        public static Violation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Violation> parser() {
            return PARSER;
        }

        public Parser<Violation> getParserForType() {
            return PARSER;
        }

        @Override
        public Violation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ViolationOrBuilder {
            private Object subject_ = "";
            private Object description_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_Violation_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
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
                this.subject_ = "";
                this.description_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_QuotaFailure_Violation_descriptor;
            }

            @Override
            public Violation getDefaultInstanceForType() {
                return Violation.getDefaultInstance();
            }

            @Override
            public Violation build() {
                Violation result2 = this.buildPartial();
                if (!result2.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result2);
                }
                return result2;
            }

            @Override
            public Violation buildPartial() {
                Violation result2 = new Violation(this);
                result2.subject_ = this.subject_;
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
                if (other instanceof Violation) {
                    return this.mergeFrom((Violation)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Violation other) {
                if (other == Violation.getDefaultInstance()) {
                    return this;
                }
                if (!other.getSubject().isEmpty()) {
                    this.subject_ = other.subject_;
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
                Violation parsedMessage = null;
                try {
                    parsedMessage = (Violation)PARSER.parsePartialFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Violation)e.getUnfinishedMessage();
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
            public String getSubject() {
                Object ref = this.subject_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    this.subject_ = s2;
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSubjectBytes() {
                Object ref = this.subject_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.subject_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSubject(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.subject_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSubject() {
                this.subject_ = Violation.getDefaultInstance().getSubject();
                this.onChanged();
                return this;
            }

            public Builder setSubjectBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                Violation.checkByteStringIsUtf8(value);
                this.subject_ = value;
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
                this.description_ = Violation.getDefaultInstance().getDescription();
                this.onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                Violation.checkByteStringIsUtf8(value);
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

    public static interface ViolationOrBuilder
    extends MessageOrBuilder {
        public String getSubject();

        public ByteString getSubjectBytes();

        public String getDescription();

        public ByteString getDescriptionBytes();
    }
}

