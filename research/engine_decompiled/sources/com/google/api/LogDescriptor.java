/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.LabelDescriptor;
import com.google.api.LabelDescriptorOrBuilder;
import com.google.api.LogDescriptorOrBuilder;
import com.google.api.LogProto;
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

public final class LogDescriptor
extends GeneratedMessageV3
implements LogDescriptorOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int LABELS_FIELD_NUMBER = 2;
    private List<LabelDescriptor> labels_;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    private volatile Object description_;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 4;
    private volatile Object displayName_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogDescriptor DEFAULT_INSTANCE = new LogDescriptor();
    private static final Parser<LogDescriptor> PARSER = new AbstractParser<LogDescriptor>(){

        @Override
        public LogDescriptor parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogDescriptor(input2, extensionRegistry);
        }
    };

    private LogDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogDescriptor() {
        this.name_ = "";
        this.labels_ = Collections.emptyList();
        this.description_ = "";
        this.displayName_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogDescriptor(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
                String s2;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block13;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block13;
                        done = true;
                        continue block13;
                    }
                    case 10: {
                        s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block13;
                    }
                    case 18: {
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.labels_ = new ArrayList<LabelDescriptor>();
                            mutable_bitField0_ |= 2;
                        }
                        this.labels_.add(input2.readMessage(LabelDescriptor.parser(), extensionRegistry));
                        continue block13;
                    }
                    case 26: {
                        s2 = input2.readStringRequireUtf8();
                        this.description_ = s2;
                        continue block13;
                    }
                    case 34: 
                }
                s2 = input2.readStringRequireUtf8();
                this.displayName_ = s2;
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
                this.labels_ = Collections.unmodifiableList(this.labels_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LogProto.internal_static_google_api_LogDescriptor_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LogProto.internal_static_google_api_LogDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LogDescriptor.class, Builder.class);
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
    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    @Override
    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    @Override
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override
    public LabelDescriptor getLabels(int index) {
        return this.labels_.get(index);
    }

    @Override
    public LabelDescriptorOrBuilder getLabelsOrBuilder(int index) {
        return this.labels_.get(index);
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
    public String getDisplayName() {
        Object ref = this.displayName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.displayName_ = s2;
        return s2;
    }

    @Override
    public ByteString getDisplayNameBytes() {
        Object ref = this.displayName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.displayName_ = b;
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        for (int i = 0; i < this.labels_.size(); ++i) {
            output.writeMessage(2, this.labels_.get(i));
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.description_);
        }
        if (!this.getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.displayName_);
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
        for (int i = 0; i < this.labels_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(2, this.labels_.get(i));
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        if (!this.getDisplayNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.displayName_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogDescriptor)) {
            return super.equals(obj);
        }
        LogDescriptor other = (LogDescriptor)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getLabelsList().equals(other.getLabelsList());
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.getDisplayName().equals(other.getDisplayName());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogDescriptor.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        if (this.getLabelsCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getLabelsList().hashCode();
        }
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getDescription().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getDisplayName().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogDescriptor parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogDescriptor parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogDescriptor parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogDescriptor parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogDescriptor parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogDescriptor parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogDescriptor parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogDescriptor parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogDescriptor.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogDescriptor prototype) {
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

    public static LogDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogDescriptor> parser() {
        return PARSER;
    }

    public Parser<LogDescriptor> getParserForType() {
        return PARSER;
    }

    @Override
    public LogDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogDescriptorOrBuilder {
        private int bitField0_;
        private Object name_ = "";
        private List<LabelDescriptor> labels_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;
        private Object description_ = "";
        private Object displayName_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LogProto.internal_static_google_api_LogDescriptor_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LogProto.internal_static_google_api_LogDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LogDescriptor.class, Builder.class);
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
                this.getLabelsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.labelsBuilder_ == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
            } else {
                this.labelsBuilder_.clear();
            }
            this.description_ = "";
            this.displayName_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LogProto.internal_static_google_api_LogDescriptor_descriptor;
        }

        @Override
        public LogDescriptor getDefaultInstanceForType() {
            return LogDescriptor.getDefaultInstance();
        }

        @Override
        public LogDescriptor build() {
            LogDescriptor result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogDescriptor buildPartial() {
            LogDescriptor result2 = new LogDescriptor(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.name_ = this.name_;
            if (this.labelsBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= 0xFFFFFFFD;
                }
                result2.labels_ = this.labels_;
            } else {
                result2.labels_ = this.labelsBuilder_.build();
            }
            result2.description_ = this.description_;
            result2.displayName_ = this.displayName_;
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
            if (other instanceof LogDescriptor) {
                return this.mergeFrom((LogDescriptor)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogDescriptor other) {
            if (other == LogDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!other.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = other.labels_;
                        this.bitField0_ &= 0xFFFFFFFD;
                    } else {
                        this.ensureLabelsIsMutable();
                        this.labels_.addAll(other.labels_);
                    }
                    this.onChanged();
                }
            } else if (!other.labels_.isEmpty()) {
                if (this.labelsBuilder_.isEmpty()) {
                    this.labelsBuilder_.dispose();
                    this.labelsBuilder_ = null;
                    this.labels_ = other.labels_;
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.labelsBuilder_ = alwaysUseFieldBuilders ? this.getLabelsFieldBuilder() : null;
                } else {
                    this.labelsBuilder_.addAllMessages(other.labels_);
                }
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
                this.onChanged();
            }
            if (!other.getDisplayName().isEmpty()) {
                this.displayName_ = other.displayName_;
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
            LogDescriptor parsedMessage = null;
            try {
                parsedMessage = (LogDescriptor)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogDescriptor)e.getUnfinishedMessage();
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
            this.name_ = LogDescriptor.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogDescriptor.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.labels_ = new ArrayList<LabelDescriptor>(this.labels_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        public List<LabelDescriptor> getLabelsList() {
            if (this.labelsBuilder_ == null) {
                return Collections.unmodifiableList(this.labels_);
            }
            return this.labelsBuilder_.getMessageList();
        }

        @Override
        public int getLabelsCount() {
            if (this.labelsBuilder_ == null) {
                return this.labels_.size();
            }
            return this.labelsBuilder_.getCount();
        }

        @Override
        public LabelDescriptor getLabels(int index) {
            if (this.labelsBuilder_ == null) {
                return this.labels_.get(index);
            }
            return this.labelsBuilder_.getMessage(index);
        }

        public Builder setLabels(int index, LabelDescriptor value) {
            if (this.labelsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLabelsIsMutable();
                this.labels_.set(index, value);
                this.onChanged();
            } else {
                this.labelsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setLabels(int index, LabelDescriptor.Builder builderForValue) {
            if (this.labelsBuilder_ == null) {
                this.ensureLabelsIsMutable();
                this.labels_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.labelsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor value) {
            if (this.labelsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLabelsIsMutable();
                this.labels_.add(value);
                this.onChanged();
            } else {
                this.labelsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addLabels(int index, LabelDescriptor value) {
            if (this.labelsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLabelsIsMutable();
                this.labels_.add(index, value);
                this.onChanged();
            } else {
                this.labelsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builderForValue) {
            if (this.labelsBuilder_ == null) {
                this.ensureLabelsIsMutable();
                this.labels_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.labelsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addLabels(int index, LabelDescriptor.Builder builderForValue) {
            if (this.labelsBuilder_ == null) {
                this.ensureLabelsIsMutable();
                this.labels_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.labelsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> values) {
            if (this.labelsBuilder_ == null) {
                this.ensureLabelsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.labels_);
                this.onChanged();
            } else {
                this.labelsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearLabels() {
            if (this.labelsBuilder_ == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
                this.onChanged();
            } else {
                this.labelsBuilder_.clear();
            }
            return this;
        }

        public Builder removeLabels(int index) {
            if (this.labelsBuilder_ == null) {
                this.ensureLabelsIsMutable();
                this.labels_.remove(index);
                this.onChanged();
            } else {
                this.labelsBuilder_.remove(index);
            }
            return this;
        }

        public LabelDescriptor.Builder getLabelsBuilder(int index) {
            return this.getLabelsFieldBuilder().getBuilder(index);
        }

        @Override
        public LabelDescriptorOrBuilder getLabelsOrBuilder(int index) {
            if (this.labelsBuilder_ == null) {
                return this.labels_.get(index);
            }
            return this.labelsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
            if (this.labelsBuilder_ != null) {
                return this.labelsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labels_);
        }

        public LabelDescriptor.Builder addLabelsBuilder() {
            return this.getLabelsFieldBuilder().addBuilder(LabelDescriptor.getDefaultInstance());
        }

        public LabelDescriptor.Builder addLabelsBuilder(int index) {
            return this.getLabelsFieldBuilder().addBuilder(index, LabelDescriptor.getDefaultInstance());
        }

        public List<LabelDescriptor.Builder> getLabelsBuilderList() {
            return this.getLabelsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> getLabelsFieldBuilder() {
            if (this.labelsBuilder_ == null) {
                this.labelsBuilder_ = new RepeatedFieldBuilderV3(this.labels_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
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
            this.description_ = LogDescriptor.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogDescriptor.checkByteStringIsUtf8(value);
            this.description_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getDisplayName() {
            Object ref = this.displayName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.displayName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDisplayNameBytes() {
            Object ref = this.displayName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.displayName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDisplayName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.displayName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = LogDescriptor.getDefaultInstance().getDisplayName();
            this.onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogDescriptor.checkByteStringIsUtf8(value);
            this.displayName_ = value;
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

