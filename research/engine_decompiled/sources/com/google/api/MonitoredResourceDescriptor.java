/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.LabelDescriptor;
import com.google.api.LabelDescriptorOrBuilder;
import com.google.api.MonitoredResourceDescriptorOrBuilder;
import com.google.api.MonitoredResourceProto;
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

public final class MonitoredResourceDescriptor
extends GeneratedMessageV3
implements MonitoredResourceDescriptorOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 5;
    private volatile Object name_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private volatile Object type_;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    private volatile Object displayName_;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    private volatile Object description_;
    public static final int LABELS_FIELD_NUMBER = 4;
    private List<LabelDescriptor> labels_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final MonitoredResourceDescriptor DEFAULT_INSTANCE = new MonitoredResourceDescriptor();
    private static final Parser<MonitoredResourceDescriptor> PARSER = new AbstractParser<MonitoredResourceDescriptor>(){

        @Override
        public MonitoredResourceDescriptor parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new MonitoredResourceDescriptor(input2, extensionRegistry);
        }
    };

    private MonitoredResourceDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private MonitoredResourceDescriptor() {
        this.name_ = "";
        this.type_ = "";
        this.displayName_ = "";
        this.description_ = "";
        this.labels_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MonitoredResourceDescriptor(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.type_ = s2;
                        continue block14;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        this.displayName_ = s2;
                        continue block14;
                    }
                    case 26: {
                        s2 = input2.readStringRequireUtf8();
                        this.description_ = s2;
                        continue block14;
                    }
                    case 34: {
                        if ((mutable_bitField0_ & 0x10) != 16) {
                            this.labels_ = new ArrayList<LabelDescriptor>();
                            mutable_bitField0_ |= 0x10;
                        }
                        this.labels_.add(input2.readMessage(LabelDescriptor.parser(), extensionRegistry));
                        continue block14;
                    }
                    case 42: 
                }
                s2 = input2.readStringRequireUtf8();
                this.name_ = s2;
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x10) == 16) {
                this.labels_ = Collections.unmodifiableList(this.labels_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
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
    public String getType() {
        Object ref = this.type_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.type_ = s2;
        return s2;
    }

    @Override
    public ByteString getTypeBytes() {
        Object ref = this.type_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.type_ = b;
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
        if (!this.getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.type_);
        }
        if (!this.getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.displayName_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.description_);
        }
        for (int i = 0; i < this.labels_.size(); ++i) {
            output.writeMessage(4, this.labels_.get(i));
        }
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.name_);
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
        if (!this.getTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.type_);
        }
        if (!this.getDisplayNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (!this.getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        for (int i = 0; i < this.labels_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(4, this.labels_.get(i));
        }
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.name_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MonitoredResourceDescriptor)) {
            return super.equals(obj);
        }
        MonitoredResourceDescriptor other = (MonitoredResourceDescriptor)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getType().equals(other.getType());
        result2 = result2 && this.getDisplayName().equals(other.getDisplayName());
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.getLabelsList().equals(other.getLabelsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + MonitoredResourceDescriptor.getDescriptor().hashCode();
        hash = 37 * hash + 5;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getType().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getDisplayName().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getDescription().hashCode();
        if (this.getLabelsCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getLabelsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return MonitoredResourceDescriptor.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceDescriptor prototype) {
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

    public static MonitoredResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceDescriptor> parser() {
        return PARSER;
    }

    public Parser<MonitoredResourceDescriptor> getParserForType() {
        return PARSER;
    }

    @Override
    public MonitoredResourceDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements MonitoredResourceDescriptorOrBuilder {
        private int bitField0_;
        private Object name_ = "";
        private Object type_ = "";
        private Object displayName_ = "";
        private Object description_ = "";
        private List<LabelDescriptor> labels_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
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
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            if (this.labelsBuilder_ == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFEF;
            } else {
                this.labelsBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
        }

        @Override
        public MonitoredResourceDescriptor getDefaultInstanceForType() {
            return MonitoredResourceDescriptor.getDefaultInstance();
        }

        @Override
        public MonitoredResourceDescriptor build() {
            MonitoredResourceDescriptor result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public MonitoredResourceDescriptor buildPartial() {
            MonitoredResourceDescriptor result2 = new MonitoredResourceDescriptor(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.name_ = this.name_;
            result2.type_ = this.type_;
            result2.displayName_ = this.displayName_;
            result2.description_ = this.description_;
            if (this.labelsBuilder_ == null) {
                if ((this.bitField0_ & 0x10) == 16) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= 0xFFFFFFEF;
                }
                result2.labels_ = this.labels_;
            } else {
                result2.labels_ = this.labelsBuilder_.build();
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
            if (other instanceof MonitoredResourceDescriptor) {
                return this.mergeFrom((MonitoredResourceDescriptor)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceDescriptor other) {
            if (other == MonitoredResourceDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.getType().isEmpty()) {
                this.type_ = other.type_;
                this.onChanged();
            }
            if (!other.getDisplayName().isEmpty()) {
                this.displayName_ = other.displayName_;
                this.onChanged();
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
                this.onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!other.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = other.labels_;
                        this.bitField0_ &= 0xFFFFFFEF;
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
                    this.bitField0_ &= 0xFFFFFFEF;
                    this.labelsBuilder_ = alwaysUseFieldBuilders ? this.getLabelsFieldBuilder() : null;
                } else {
                    this.labelsBuilder_.addAllMessages(other.labels_);
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
            MonitoredResourceDescriptor parsedMessage = null;
            try {
                parsedMessage = (MonitoredResourceDescriptor)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (MonitoredResourceDescriptor)e.getUnfinishedMessage();
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
            this.name_ = MonitoredResourceDescriptor.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            MonitoredResourceDescriptor.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getType() {
            Object ref = this.type_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.type_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getTypeBytes() {
            Object ref = this.type_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.type_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.type_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = MonitoredResourceDescriptor.getDefaultInstance().getType();
            this.onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            MonitoredResourceDescriptor.checkByteStringIsUtf8(value);
            this.type_ = value;
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
            this.displayName_ = MonitoredResourceDescriptor.getDefaultInstance().getDisplayName();
            this.onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            MonitoredResourceDescriptor.checkByteStringIsUtf8(value);
            this.displayName_ = value;
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
            this.description_ = MonitoredResourceDescriptor.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            MonitoredResourceDescriptor.checkByteStringIsUtf8(value);
            this.description_ = value;
            this.onChanged();
            return this;
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 0x10) != 16) {
                this.labels_ = new ArrayList<LabelDescriptor>(this.labels_);
                this.bitField0_ |= 0x10;
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
                this.bitField0_ &= 0xFFFFFFEF;
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
                this.labelsBuilder_ = new RepeatedFieldBuilderV3(this.labels_, (this.bitField0_ & 0x10) == 16, this.getParentForChildren(), this.isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
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

