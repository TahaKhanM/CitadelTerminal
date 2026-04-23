/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.MonitoredResourceMetadataOrBuilder;
import com.google.api.MonitoredResourceProto;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class MonitoredResourceMetadata
extends GeneratedMessageV3
implements MonitoredResourceMetadataOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SYSTEM_LABELS_FIELD_NUMBER = 1;
    private Struct systemLabels_;
    public static final int USER_LABELS_FIELD_NUMBER = 2;
    private MapField<String, String> userLabels_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final MonitoredResourceMetadata DEFAULT_INSTANCE = new MonitoredResourceMetadata();
    private static final Parser<MonitoredResourceMetadata> PARSER = new AbstractParser<MonitoredResourceMetadata>(){

        @Override
        public MonitoredResourceMetadata parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new MonitoredResourceMetadata(input2, extensionRegistry);
        }
    };

    private MonitoredResourceMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private MonitoredResourceMetadata() {
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MonitoredResourceMetadata(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
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
                        Struct.Builder subBuilder = null;
                        if (this.systemLabels_ != null) {
                            subBuilder = this.systemLabels_.toBuilder();
                        }
                        this.systemLabels_ = input2.readMessage(Struct.parser(), extensionRegistry);
                        if (subBuilder == null) continue block11;
                        subBuilder.mergeFrom(this.systemLabels_);
                        this.systemLabels_ = subBuilder.buildPartial();
                        continue block11;
                    }
                    case 18: 
                }
                if ((mutable_bitField0_ & 2) != 2) {
                    this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
                    mutable_bitField0_ |= 2;
                }
                MapEntry<String, String> userLabels__ = input2.readMessage(UserLabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                this.userLabels_.getMutableMap().put(userLabels__.getKey(), userLabels__.getValue());
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
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 2: {
                return this.internalGetUserLabels();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
    }

    @Override
    public boolean hasSystemLabels() {
        return this.systemLabels_ != null;
    }

    @Override
    public Struct getSystemLabels() {
        return this.systemLabels_ == null ? Struct.getDefaultInstance() : this.systemLabels_;
    }

    @Override
    public StructOrBuilder getSystemLabelsOrBuilder() {
        return this.getSystemLabels();
    }

    private MapField<String, String> internalGetUserLabels() {
        if (this.userLabels_ == null) {
            return MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry);
        }
        return this.userLabels_;
    }

    @Override
    public int getUserLabelsCount() {
        return this.internalGetUserLabels().getMap().size();
    }

    @Override
    public boolean containsUserLabels(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return this.internalGetUserLabels().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<String, String> getUserLabels() {
        return this.getUserLabelsMap();
    }

    @Override
    public Map<String, String> getUserLabelsMap() {
        return this.internalGetUserLabels().getMap();
    }

    @Override
    public String getUserLabelsOrDefault(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetUserLabels().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public String getUserLabelsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetUserLabels().getMap();
        if (!map2.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map2.get(key);
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
        if (this.systemLabels_ != null) {
            output.writeMessage(1, this.getSystemLabels());
        }
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetUserLabels(), UserLabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (this.systemLabels_ != null) {
            size2 += CodedOutputStream.computeMessageSize(1, this.getSystemLabels());
        }
        for (Map.Entry<String, String> entry : this.internalGetUserLabels().getMap().entrySet()) {
            Message userLabels__ = ((MapEntry.Builder)UserLabelsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(2, userLabels__);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MonitoredResourceMetadata)) {
            return super.equals(obj);
        }
        MonitoredResourceMetadata other = (MonitoredResourceMetadata)obj;
        boolean result2 = true;
        boolean bl = result2 = result2 && this.hasSystemLabels() == other.hasSystemLabels();
        if (this.hasSystemLabels()) {
            result2 = result2 && this.getSystemLabels().equals(other.getSystemLabels());
        }
        result2 = result2 && this.internalGetUserLabels().equals(other.internalGetUserLabels());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + MonitoredResourceMetadata.getDescriptor().hashCode();
        if (this.hasSystemLabels()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getSystemLabels().hashCode();
        }
        if (!this.internalGetUserLabels().getMap().isEmpty()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.internalGetUserLabels().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return MonitoredResourceMetadata.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceMetadata prototype) {
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

    public static MonitoredResourceMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceMetadata> parser() {
        return PARSER;
    }

    public Parser<MonitoredResourceMetadata> getParserForType() {
        return PARSER;
    }

    @Override
    public MonitoredResourceMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements MonitoredResourceMetadataOrBuilder {
        private int bitField0_;
        private Struct systemLabels_ = null;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> systemLabelsBuilder_;
        private MapField<String, String> userLabels_;

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetUserLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetMutableUserLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
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
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            this.internalGetMutableUserLabels().clear();
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        @Override
        public MonitoredResourceMetadata getDefaultInstanceForType() {
            return MonitoredResourceMetadata.getDefaultInstance();
        }

        @Override
        public MonitoredResourceMetadata build() {
            MonitoredResourceMetadata result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public MonitoredResourceMetadata buildPartial() {
            MonitoredResourceMetadata result2 = new MonitoredResourceMetadata(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.systemLabelsBuilder_ == null) {
                result2.systemLabels_ = this.systemLabels_;
            } else {
                result2.systemLabels_ = this.systemLabelsBuilder_.build();
            }
            result2.userLabels_ = this.internalGetUserLabels();
            result2.userLabels_.makeImmutable();
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
            if (other instanceof MonitoredResourceMetadata) {
                return this.mergeFrom((MonitoredResourceMetadata)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceMetadata other) {
            if (other == MonitoredResourceMetadata.getDefaultInstance()) {
                return this;
            }
            if (other.hasSystemLabels()) {
                this.mergeSystemLabels(other.getSystemLabels());
            }
            this.internalGetMutableUserLabels().mergeFrom(other.internalGetUserLabels());
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
            MonitoredResourceMetadata parsedMessage = null;
            try {
                parsedMessage = (MonitoredResourceMetadata)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (MonitoredResourceMetadata)e.getUnfinishedMessage();
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
        public boolean hasSystemLabels() {
            return this.systemLabelsBuilder_ != null || this.systemLabels_ != null;
        }

        @Override
        public Struct getSystemLabels() {
            if (this.systemLabelsBuilder_ == null) {
                return this.systemLabels_ == null ? Struct.getDefaultInstance() : this.systemLabels_;
            }
            return this.systemLabelsBuilder_.getMessage();
        }

        public Builder setSystemLabels(Struct value) {
            if (this.systemLabelsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.systemLabels_ = value;
                this.onChanged();
            } else {
                this.systemLabelsBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setSystemLabels(Struct.Builder builderForValue) {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = builderForValue.build();
                this.onChanged();
            } else {
                this.systemLabelsBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeSystemLabels(Struct value) {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = this.systemLabels_ != null ? Struct.newBuilder(this.systemLabels_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.systemLabelsBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearSystemLabels() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
                this.onChanged();
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getSystemLabelsBuilder() {
            this.onChanged();
            return this.getSystemLabelsFieldBuilder().getBuilder();
        }

        @Override
        public StructOrBuilder getSystemLabelsOrBuilder() {
            if (this.systemLabelsBuilder_ != null) {
                return this.systemLabelsBuilder_.getMessageOrBuilder();
            }
            return this.systemLabels_ == null ? Struct.getDefaultInstance() : this.systemLabels_;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getSystemLabelsFieldBuilder() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabelsBuilder_ = new SingleFieldBuilderV3(this.getSystemLabels(), this.getParentForChildren(), this.isClean());
                this.systemLabels_ = null;
            }
            return this.systemLabelsBuilder_;
        }

        private MapField<String, String> internalGetUserLabels() {
            if (this.userLabels_ == null) {
                return MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry);
            }
            return this.userLabels_;
        }

        private MapField<String, String> internalGetMutableUserLabels() {
            this.onChanged();
            if (this.userLabels_ == null) {
                this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.userLabels_.isMutable()) {
                this.userLabels_ = this.userLabels_.copy();
            }
            return this.userLabels_;
        }

        @Override
        public int getUserLabelsCount() {
            return this.internalGetUserLabels().getMap().size();
        }

        @Override
        public boolean containsUserLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return this.internalGetUserLabels().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<String, String> getUserLabels() {
            return this.getUserLabelsMap();
        }

        @Override
        public Map<String, String> getUserLabelsMap() {
            return this.internalGetUserLabels().getMap();
        }

        @Override
        public String getUserLabelsOrDefault(String key, String defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetUserLabels().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public String getUserLabelsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetUserLabels().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearUserLabels() {
            this.internalGetMutableUserLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeUserLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableUserLabels().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableUserLabels() {
            return this.internalGetMutableUserLabels().getMutableMap();
        }

        public Builder putUserLabels(String key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableUserLabels().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllUserLabels(Map<String, String> values) {
            this.internalGetMutableUserLabels().getMutableMap().putAll(values);
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

    private static final class UserLabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private UserLabelsDefaultEntryHolder() {
        }
    }
}

