/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.MetricRuleOrBuilder;
import com.google.api.QuotaProto;
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

public final class MetricRule
extends GeneratedMessageV3
implements MetricRuleOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private volatile Object selector_;
    public static final int METRIC_COSTS_FIELD_NUMBER = 2;
    private MapField<String, Long> metricCosts_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final MetricRule DEFAULT_INSTANCE = new MetricRule();
    private static final Parser<MetricRule> PARSER = new AbstractParser<MetricRule>(){

        @Override
        public MetricRule parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new MetricRule(input2, extensionRegistry);
        }
    };

    private MetricRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private MetricRule() {
        this.selector_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MetricRule(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        String s2 = input2.readStringRequireUtf8();
                        this.selector_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                if ((mutable_bitField0_ & 2) != 2) {
                    this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
                    mutable_bitField0_ |= 2;
                }
                MapEntry<String, Long> metricCosts__ = input2.readMessage(MetricCostsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                this.metricCosts_.getMutableMap().put(metricCosts__.getKey(), metricCosts__.getValue());
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
        return QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 2: {
                return this.internalGetMetricCosts();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
    }

    @Override
    public String getSelector() {
        Object ref = this.selector_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.selector_ = s2;
        return s2;
    }

    @Override
    public ByteString getSelectorBytes() {
        Object ref = this.selector_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.selector_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    private MapField<String, Long> internalGetMetricCosts() {
        if (this.metricCosts_ == null) {
            return MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry);
        }
        return this.metricCosts_;
    }

    @Override
    public int getMetricCostsCount() {
        return this.internalGetMetricCosts().getMap().size();
    }

    @Override
    public boolean containsMetricCosts(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return this.internalGetMetricCosts().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<String, Long> getMetricCosts() {
        return this.getMetricCostsMap();
    }

    @Override
    public Map<String, Long> getMetricCostsMap() {
        return this.internalGetMetricCosts().getMap();
    }

    @Override
    public long getMetricCostsOrDefault(String key, long defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Long> map2 = this.internalGetMetricCosts().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public long getMetricCostsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Long> map2 = this.internalGetMetricCosts().getMap();
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
        if (!this.getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.selector_);
        }
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetMetricCosts(), MetricCostsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (!this.getSelectorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        for (Map.Entry<String, Long> entry : this.internalGetMetricCosts().getMap().entrySet()) {
            Message metricCosts__ = ((MapEntry.Builder)MetricCostsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(2, metricCosts__);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricRule)) {
            return super.equals(obj);
        }
        MetricRule other = (MetricRule)obj;
        boolean result2 = true;
        result2 = result2 && this.getSelector().equals(other.getSelector());
        result2 = result2 && this.internalGetMetricCosts().equals(other.internalGetMetricCosts());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + MetricRule.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getSelector().hashCode();
        if (!this.internalGetMetricCosts().getMap().isEmpty()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.internalGetMetricCosts().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static MetricRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MetricRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MetricRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MetricRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MetricRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static MetricRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static MetricRule parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MetricRule parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MetricRule parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static MetricRule parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static MetricRule parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static MetricRule parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return MetricRule.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MetricRule prototype) {
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

    public static MetricRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricRule> parser() {
        return PARSER;
    }

    public Parser<MetricRule> getParserForType() {
        return PARSER;
    }

    @Override
    public MetricRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements MetricRuleOrBuilder {
        private int bitField0_;
        private Object selector_ = "";
        private MapField<String, Long> metricCosts_;

        public static final Descriptors.Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetMetricCosts();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 2: {
                    return this.internalGetMutableMetricCosts();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
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
            this.selector_ = "";
            this.internalGetMutableMetricCosts().clear();
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        @Override
        public MetricRule getDefaultInstanceForType() {
            return MetricRule.getDefaultInstance();
        }

        @Override
        public MetricRule build() {
            MetricRule result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public MetricRule buildPartial() {
            MetricRule result2 = new MetricRule(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.selector_ = this.selector_;
            result2.metricCosts_ = this.internalGetMetricCosts();
            result2.metricCosts_.makeImmutable();
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
            if (other instanceof MetricRule) {
                return this.mergeFrom((MetricRule)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(MetricRule other) {
            if (other == MetricRule.getDefaultInstance()) {
                return this;
            }
            if (!other.getSelector().isEmpty()) {
                this.selector_ = other.selector_;
                this.onChanged();
            }
            this.internalGetMutableMetricCosts().mergeFrom(other.internalGetMetricCosts());
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
            MetricRule parsedMessage = null;
            try {
                parsedMessage = (MetricRule)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (MetricRule)e.getUnfinishedMessage();
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
        public String getSelector() {
            Object ref = this.selector_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.selector_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSelectorBytes() {
            Object ref = this.selector_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.selector_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSelector(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = MetricRule.getDefaultInstance().getSelector();
            this.onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            MetricRule.checkByteStringIsUtf8(value);
            this.selector_ = value;
            this.onChanged();
            return this;
        }

        private MapField<String, Long> internalGetMetricCosts() {
            if (this.metricCosts_ == null) {
                return MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry);
            }
            return this.metricCosts_;
        }

        private MapField<String, Long> internalGetMutableMetricCosts() {
            this.onChanged();
            if (this.metricCosts_ == null) {
                this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
            }
            if (!this.metricCosts_.isMutable()) {
                this.metricCosts_ = this.metricCosts_.copy();
            }
            return this.metricCosts_;
        }

        @Override
        public int getMetricCostsCount() {
            return this.internalGetMetricCosts().getMap().size();
        }

        @Override
        public boolean containsMetricCosts(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return this.internalGetMetricCosts().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<String, Long> getMetricCosts() {
            return this.getMetricCostsMap();
        }

        @Override
        public Map<String, Long> getMetricCostsMap() {
            return this.internalGetMetricCosts().getMap();
        }

        @Override
        public long getMetricCostsOrDefault(String key, long defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Long> map2 = this.internalGetMetricCosts().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public long getMetricCostsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Long> map2 = this.internalGetMetricCosts().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearMetricCosts() {
            this.internalGetMutableMetricCosts().getMutableMap().clear();
            return this;
        }

        public Builder removeMetricCosts(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableMetricCosts().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, Long> getMutableMetricCosts() {
            return this.internalGetMutableMetricCosts().getMutableMap();
        }

        public Builder putMetricCosts(String key, long value) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableMetricCosts().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllMetricCosts(Map<String, Long> values) {
            this.internalGetMutableMetricCosts().getMutableMap().putAll(values);
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

    private static final class MetricCostsDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private MetricCostsDefaultEntryHolder() {
        }
    }
}

