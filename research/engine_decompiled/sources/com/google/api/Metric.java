/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.MetricOrBuilder;
import com.google.api.MetricProto;
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
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class Metric
extends GeneratedMessageV3
implements MetricOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 3;
    private volatile Object type_;
    public static final int LABELS_FIELD_NUMBER = 2;
    private MapField<String, String> labels_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Metric DEFAULT_INSTANCE = new Metric();
    private static final Parser<Metric> PARSER = new AbstractParser<Metric>(){

        @Override
        public Metric parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Metric(input2, extensionRegistry);
        }
    };

    private Metric(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Metric() {
        this.type_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Metric(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 18: {
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= 2;
                        }
                        MapEntry<String, String> labels__ = input2.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.labels_.getMutableMap().put(labels__.getKey(), labels__.getValue());
                        continue block11;
                    }
                    case 26: 
                }
                String s2 = input2.readStringRequireUtf8();
                this.type_ = s2;
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
        return MetricProto.internal_static_google_api_Metric_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 2: {
                return this.internalGetLabels();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MetricProto.internal_static_google_api_Metric_fieldAccessorTable.ensureFieldAccessorsInitialized(Metric.class, Builder.class);
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

    private MapField<String, String> internalGetLabels() {
        if (this.labels_ == null) {
            return MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry);
        }
        return this.labels_;
    }

    @Override
    public int getLabelsCount() {
        return this.internalGetLabels().getMap().size();
    }

    @Override
    public boolean containsLabels(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return this.internalGetLabels().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<String, String> getLabels() {
        return this.getLabelsMap();
    }

    @Override
    public Map<String, String> getLabelsMap() {
        return this.internalGetLabels().getMap();
    }

    @Override
    public String getLabelsOrDefault(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabels().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public String getLabelsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabels().getMap();
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
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 2);
        if (!this.getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.type_);
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
        for (Map.Entry<String, String> entry : this.internalGetLabels().getMap().entrySet()) {
            Message labels__ = ((MapEntry.Builder)LabelsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(2, labels__);
        }
        if (!this.getTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.type_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Metric)) {
            return super.equals(obj);
        }
        Metric other = (Metric)obj;
        boolean result2 = true;
        result2 = result2 && this.getType().equals(other.getType());
        result2 = result2 && this.internalGetLabels().equals(other.internalGetLabels());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Metric.getDescriptor().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getType().hashCode();
        if (!this.internalGetLabels().getMap().isEmpty()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.internalGetLabels().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Metric parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Metric parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Metric parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Metric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Metric parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Metric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Metric parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Metric parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Metric parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Metric parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Metric parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Metric parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Metric.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Metric prototype) {
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

    public static Metric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Metric> parser() {
        return PARSER;
    }

    public Parser<Metric> getParserForType() {
        return PARSER;
    }

    @Override
    public Metric getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements MetricOrBuilder {
        private int bitField0_;
        private Object type_ = "";
        private MapField<String, String> labels_;

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricProto.internal_static_google_api_Metric_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetMutableLabels();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricProto.internal_static_google_api_Metric_fieldAccessorTable.ensureFieldAccessorsInitialized(Metric.class, Builder.class);
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
            this.type_ = "";
            this.internalGetMutableLabels().clear();
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return MetricProto.internal_static_google_api_Metric_descriptor;
        }

        @Override
        public Metric getDefaultInstanceForType() {
            return Metric.getDefaultInstance();
        }

        @Override
        public Metric build() {
            Metric result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Metric buildPartial() {
            Metric result2 = new Metric(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.type_ = this.type_;
            result2.labels_ = this.internalGetLabels();
            result2.labels_.makeImmutable();
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
            if (other instanceof Metric) {
                return this.mergeFrom((Metric)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Metric other) {
            if (other == Metric.getDefaultInstance()) {
                return this;
            }
            if (!other.getType().isEmpty()) {
                this.type_ = other.type_;
                this.onChanged();
            }
            this.internalGetMutableLabels().mergeFrom(other.internalGetLabels());
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
            Metric parsedMessage = null;
            try {
                parsedMessage = (Metric)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Metric)e.getUnfinishedMessage();
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
            this.type_ = Metric.getDefaultInstance().getType();
            this.onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Metric.checkByteStringIsUtf8(value);
            this.type_ = value;
            this.onChanged();
            return this;
        }

        private MapField<String, String> internalGetLabels() {
            if (this.labels_ == null) {
                return MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            return this.labels_;
        }

        private MapField<String, String> internalGetMutableLabels() {
            this.onChanged();
            if (this.labels_ == null) {
                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.copy();
            }
            return this.labels_;
        }

        @Override
        public int getLabelsCount() {
            return this.internalGetLabels().getMap().size();
        }

        @Override
        public boolean containsLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return this.internalGetLabels().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<String, String> getLabels() {
            return this.getLabelsMap();
        }

        @Override
        public Map<String, String> getLabelsMap() {
            return this.internalGetLabels().getMap();
        }

        @Override
        public String getLabelsOrDefault(String key, String defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabels().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public String getLabelsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabels().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearLabels() {
            this.internalGetMutableLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeLabels(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabels().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableLabels() {
            return this.internalGetMutableLabels().getMutableMap();
        }

        public Builder putLabels(String key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabels().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllLabels(Map<String, String> values) {
            this.internalGetMutableLabels().getMutableMap().putAll(values);
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

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MetricProto.internal_static_google_api_Metric_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }
}

