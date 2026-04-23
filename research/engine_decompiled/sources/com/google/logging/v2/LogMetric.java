/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.Distribution;
import com.google.api.MetricDescriptor;
import com.google.api.MetricDescriptorOrBuilder;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.logging.v2.LoggingMetricsProto;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class LogMetric
extends GeneratedMessageV3
implements LogMetricOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    private volatile Object description_;
    public static final int FILTER_FIELD_NUMBER = 3;
    private volatile Object filter_;
    public static final int METRIC_DESCRIPTOR_FIELD_NUMBER = 5;
    private MetricDescriptor metricDescriptor_;
    public static final int VALUE_EXTRACTOR_FIELD_NUMBER = 6;
    private volatile Object valueExtractor_;
    public static final int LABEL_EXTRACTORS_FIELD_NUMBER = 7;
    private MapField<String, String> labelExtractors_;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 8;
    private Distribution.BucketOptions bucketOptions_;
    public static final int VERSION_FIELD_NUMBER = 4;
    private int version_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogMetric DEFAULT_INSTANCE = new LogMetric();
    private static final Parser<LogMetric> PARSER = new AbstractParser<LogMetric>(){

        @Override
        public LogMetric parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogMetric(input2, extensionRegistry);
        }
    };

    private LogMetric(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogMetric() {
        this.name_ = "";
        this.description_ = "";
        this.filter_ = "";
        this.valueExtractor_ = "";
        this.version_ = 0;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogMetric(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block17: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block17;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block17;
                    }
                    case 18: {
                        String s3 = input2.readStringRequireUtf8();
                        this.description_ = s3;
                        continue block17;
                    }
                    case 26: {
                        String s4 = input2.readStringRequireUtf8();
                        this.filter_ = s4;
                        continue block17;
                    }
                    case 32: {
                        int rawValue;
                        this.version_ = rawValue = input2.readEnum();
                        continue block17;
                    }
                    case 42: {
                        MetricDescriptor.Builder subBuilder = null;
                        if (this.metricDescriptor_ != null) {
                            subBuilder = this.metricDescriptor_.toBuilder();
                        }
                        this.metricDescriptor_ = input2.readMessage(MetricDescriptor.parser(), extensionRegistry);
                        if (subBuilder == null) continue block17;
                        subBuilder.mergeFrom(this.metricDescriptor_);
                        this.metricDescriptor_ = subBuilder.buildPartial();
                        continue block17;
                    }
                    case 50: {
                        String s5 = input2.readStringRequireUtf8();
                        this.valueExtractor_ = s5;
                        continue block17;
                    }
                    case 58: {
                        if ((mutable_bitField0_ & 0x20) != 32) {
                            this.labelExtractors_ = MapField.newMapField(LabelExtractorsDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= 0x20;
                        }
                        MapEntry<String, String> labelExtractors__ = input2.readMessage(LabelExtractorsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.labelExtractors_.getMutableMap().put(labelExtractors__.getKey(), labelExtractors__.getValue());
                        continue block17;
                    }
                    case 66: {
                        Distribution.BucketOptions.Builder subBuilder = null;
                        if (this.bucketOptions_ != null) {
                            subBuilder = this.bucketOptions_.toBuilder();
                        }
                        this.bucketOptions_ = input2.readMessage(Distribution.BucketOptions.parser(), extensionRegistry);
                        if (subBuilder == null) continue block17;
                        subBuilder.mergeFrom(this.bucketOptions_);
                        this.bucketOptions_ = subBuilder.buildPartial();
                        continue block17;
                    }
                }
                if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue;
                done = true;
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
        return LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_descriptor;
    }

    @Override
    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 7: {
                return this.internalGetLabelExtractors();
            }
        }
        throw new RuntimeException("Invalid map field number: " + number);
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_fieldAccessorTable.ensureFieldAccessorsInitialized(LogMetric.class, Builder.class);
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
    public String getFilter() {
        Object ref = this.filter_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.filter_ = s2;
        return s2;
    }

    @Override
    public ByteString getFilterBytes() {
        Object ref = this.filter_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.filter_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasMetricDescriptor() {
        return this.metricDescriptor_ != null;
    }

    @Override
    public MetricDescriptor getMetricDescriptor() {
        return this.metricDescriptor_ == null ? MetricDescriptor.getDefaultInstance() : this.metricDescriptor_;
    }

    @Override
    public MetricDescriptorOrBuilder getMetricDescriptorOrBuilder() {
        return this.getMetricDescriptor();
    }

    @Override
    public String getValueExtractor() {
        Object ref = this.valueExtractor_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.valueExtractor_ = s2;
        return s2;
    }

    @Override
    public ByteString getValueExtractorBytes() {
        Object ref = this.valueExtractor_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.valueExtractor_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    private MapField<String, String> internalGetLabelExtractors() {
        if (this.labelExtractors_ == null) {
            return MapField.emptyMapField(LabelExtractorsDefaultEntryHolder.defaultEntry);
        }
        return this.labelExtractors_;
    }

    @Override
    public int getLabelExtractorsCount() {
        return this.internalGetLabelExtractors().getMap().size();
    }

    @Override
    public boolean containsLabelExtractors(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return this.internalGetLabelExtractors().getMap().containsKey(key);
    }

    @Override
    @Deprecated
    public Map<String, String> getLabelExtractors() {
        return this.getLabelExtractorsMap();
    }

    @Override
    public Map<String, String> getLabelExtractorsMap() {
        return this.internalGetLabelExtractors().getMap();
    }

    @Override
    public String getLabelExtractorsOrDefault(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabelExtractors().getMap();
        return map2.containsKey(key) ? map2.get(key) : defaultValue;
    }

    @Override
    public String getLabelExtractorsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, String> map2 = this.internalGetLabelExtractors().getMap();
        if (!map2.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map2.get(key);
    }

    @Override
    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    @Override
    public Distribution.BucketOptions getBucketOptions() {
        return this.bucketOptions_ == null ? Distribution.BucketOptions.getDefaultInstance() : this.bucketOptions_;
    }

    @Override
    public Distribution.BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
        return this.getBucketOptions();
    }

    @Override
    @Deprecated
    public int getVersionValue() {
        return this.version_;
    }

    @Override
    @Deprecated
    public ApiVersion getVersion() {
        ApiVersion result2 = ApiVersion.valueOf(this.version_);
        return result2 == null ? ApiVersion.UNRECOGNIZED : result2;
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
        if (!this.getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.description_);
        }
        if (!this.getFilterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.filter_);
        }
        if (this.version_ != ApiVersion.V2.getNumber()) {
            output.writeEnum(4, this.version_);
        }
        if (this.metricDescriptor_ != null) {
            output.writeMessage(5, this.getMetricDescriptor());
        }
        if (!this.getValueExtractorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 6, this.valueExtractor_);
        }
        GeneratedMessageV3.serializeStringMapTo(output, this.internalGetLabelExtractors(), LabelExtractorsDefaultEntryHolder.defaultEntry, 7);
        if (this.bucketOptions_ != null) {
            output.writeMessage(8, this.getBucketOptions());
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
        if (!this.getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.description_);
        }
        if (!this.getFilterBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.filter_);
        }
        if (this.version_ != ApiVersion.V2.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(4, this.version_);
        }
        if (this.metricDescriptor_ != null) {
            size2 += CodedOutputStream.computeMessageSize(5, this.getMetricDescriptor());
        }
        if (!this.getValueExtractorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(6, this.valueExtractor_);
        }
        for (Map.Entry<String, String> entry : this.internalGetLabelExtractors().getMap().entrySet()) {
            Message labelExtractors__ = ((MapEntry.Builder)LabelExtractorsDefaultEntryHolder.defaultEntry.newBuilderForType()).setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(7, labelExtractors__);
        }
        if (this.bucketOptions_ != null) {
            size2 += CodedOutputStream.computeMessageSize(8, this.getBucketOptions());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogMetric)) {
            return super.equals(obj);
        }
        LogMetric other = (LogMetric)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.getFilter().equals(other.getFilter());
        boolean bl = result2 = result2 && this.hasMetricDescriptor() == other.hasMetricDescriptor();
        if (this.hasMetricDescriptor()) {
            result2 = result2 && this.getMetricDescriptor().equals(other.getMetricDescriptor());
        }
        result2 = result2 && this.getValueExtractor().equals(other.getValueExtractor());
        result2 = result2 && this.internalGetLabelExtractors().equals(other.internalGetLabelExtractors());
        boolean bl2 = result2 = result2 && this.hasBucketOptions() == other.hasBucketOptions();
        if (this.hasBucketOptions()) {
            result2 = result2 && this.getBucketOptions().equals(other.getBucketOptions());
        }
        result2 = result2 && this.version_ == other.version_;
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogMetric.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getDescription().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getFilter().hashCode();
        if (this.hasMetricDescriptor()) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getMetricDescriptor().hashCode();
        }
        hash = 37 * hash + 6;
        hash = 53 * hash + this.getValueExtractor().hashCode();
        if (!this.internalGetLabelExtractors().getMap().isEmpty()) {
            hash = 37 * hash + 7;
            hash = 53 * hash + this.internalGetLabelExtractors().hashCode();
        }
        if (this.hasBucketOptions()) {
            hash = 37 * hash + 8;
            hash = 53 * hash + this.getBucketOptions().hashCode();
        }
        hash = 37 * hash + 4;
        hash = 53 * hash + this.version_;
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogMetric parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogMetric parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogMetric parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogMetric parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogMetric parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogMetric parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogMetric parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogMetric parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogMetric.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogMetric prototype) {
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

    public static LogMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogMetric> parser() {
        return PARSER;
    }

    public Parser<LogMetric> getParserForType() {
        return PARSER;
    }

    @Override
    public LogMetric getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogMetricOrBuilder {
        private int bitField0_;
        private Object name_ = "";
        private Object description_ = "";
        private Object filter_ = "";
        private MetricDescriptor metricDescriptor_ = null;
        private SingleFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> metricDescriptorBuilder_;
        private Object valueExtractor_ = "";
        private MapField<String, String> labelExtractors_;
        private Distribution.BucketOptions bucketOptions_ = null;
        private SingleFieldBuilderV3<Distribution.BucketOptions, Distribution.BucketOptions.Builder, Distribution.BucketOptionsOrBuilder> bucketOptionsBuilder_;
        private int version_ = 0;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 7: {
                    return this.internalGetLabelExtractors();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 7: {
                    return this.internalGetMutableLabelExtractors();
                }
            }
            throw new RuntimeException("Invalid map field number: " + number);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_fieldAccessorTable.ensureFieldAccessorsInitialized(LogMetric.class, Builder.class);
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
            this.description_ = "";
            this.filter_ = "";
            if (this.metricDescriptorBuilder_ == null) {
                this.metricDescriptor_ = null;
            } else {
                this.metricDescriptor_ = null;
                this.metricDescriptorBuilder_ = null;
            }
            this.valueExtractor_ = "";
            this.internalGetMutableLabelExtractors().clear();
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            this.version_ = 0;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_descriptor;
        }

        @Override
        public LogMetric getDefaultInstanceForType() {
            return LogMetric.getDefaultInstance();
        }

        @Override
        public LogMetric build() {
            LogMetric result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogMetric buildPartial() {
            LogMetric result2 = new LogMetric(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.name_ = this.name_;
            result2.description_ = this.description_;
            result2.filter_ = this.filter_;
            if (this.metricDescriptorBuilder_ == null) {
                result2.metricDescriptor_ = this.metricDescriptor_;
            } else {
                result2.metricDescriptor_ = this.metricDescriptorBuilder_.build();
            }
            result2.valueExtractor_ = this.valueExtractor_;
            result2.labelExtractors_ = this.internalGetLabelExtractors();
            result2.labelExtractors_.makeImmutable();
            if (this.bucketOptionsBuilder_ == null) {
                result2.bucketOptions_ = this.bucketOptions_;
            } else {
                result2.bucketOptions_ = this.bucketOptionsBuilder_.build();
            }
            result2.version_ = this.version_;
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
            if (other instanceof LogMetric) {
                return this.mergeFrom((LogMetric)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogMetric other) {
            if (other == LogMetric.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
                this.onChanged();
            }
            if (!other.getFilter().isEmpty()) {
                this.filter_ = other.filter_;
                this.onChanged();
            }
            if (other.hasMetricDescriptor()) {
                this.mergeMetricDescriptor(other.getMetricDescriptor());
            }
            if (!other.getValueExtractor().isEmpty()) {
                this.valueExtractor_ = other.valueExtractor_;
                this.onChanged();
            }
            this.internalGetMutableLabelExtractors().mergeFrom(other.internalGetLabelExtractors());
            if (other.hasBucketOptions()) {
                this.mergeBucketOptions(other.getBucketOptions());
            }
            if (other.version_ != 0) {
                this.setVersionValue(other.getVersionValue());
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
            LogMetric parsedMessage = null;
            try {
                parsedMessage = (LogMetric)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogMetric)e.getUnfinishedMessage();
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
            this.name_ = LogMetric.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogMetric.checkByteStringIsUtf8(value);
            this.name_ = value;
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
            this.description_ = LogMetric.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogMetric.checkByteStringIsUtf8(value);
            this.description_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getFilter() {
            Object ref = this.filter_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.filter_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getFilterBytes() {
            Object ref = this.filter_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.filter_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setFilter(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.filter_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearFilter() {
            this.filter_ = LogMetric.getDefaultInstance().getFilter();
            this.onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogMetric.checkByteStringIsUtf8(value);
            this.filter_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasMetricDescriptor() {
            return this.metricDescriptorBuilder_ != null || this.metricDescriptor_ != null;
        }

        @Override
        public MetricDescriptor getMetricDescriptor() {
            if (this.metricDescriptorBuilder_ == null) {
                return this.metricDescriptor_ == null ? MetricDescriptor.getDefaultInstance() : this.metricDescriptor_;
            }
            return this.metricDescriptorBuilder_.getMessage();
        }

        public Builder setMetricDescriptor(MetricDescriptor value) {
            if (this.metricDescriptorBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.metricDescriptor_ = value;
                this.onChanged();
            } else {
                this.metricDescriptorBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setMetricDescriptor(MetricDescriptor.Builder builderForValue) {
            if (this.metricDescriptorBuilder_ == null) {
                this.metricDescriptor_ = builderForValue.build();
                this.onChanged();
            } else {
                this.metricDescriptorBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeMetricDescriptor(MetricDescriptor value) {
            if (this.metricDescriptorBuilder_ == null) {
                this.metricDescriptor_ = this.metricDescriptor_ != null ? MetricDescriptor.newBuilder(this.metricDescriptor_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.metricDescriptorBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearMetricDescriptor() {
            if (this.metricDescriptorBuilder_ == null) {
                this.metricDescriptor_ = null;
                this.onChanged();
            } else {
                this.metricDescriptor_ = null;
                this.metricDescriptorBuilder_ = null;
            }
            return this;
        }

        public MetricDescriptor.Builder getMetricDescriptorBuilder() {
            this.onChanged();
            return this.getMetricDescriptorFieldBuilder().getBuilder();
        }

        @Override
        public MetricDescriptorOrBuilder getMetricDescriptorOrBuilder() {
            if (this.metricDescriptorBuilder_ != null) {
                return this.metricDescriptorBuilder_.getMessageOrBuilder();
            }
            return this.metricDescriptor_ == null ? MetricDescriptor.getDefaultInstance() : this.metricDescriptor_;
        }

        private SingleFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> getMetricDescriptorFieldBuilder() {
            if (this.metricDescriptorBuilder_ == null) {
                this.metricDescriptorBuilder_ = new SingleFieldBuilderV3(this.getMetricDescriptor(), this.getParentForChildren(), this.isClean());
                this.metricDescriptor_ = null;
            }
            return this.metricDescriptorBuilder_;
        }

        @Override
        public String getValueExtractor() {
            Object ref = this.valueExtractor_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.valueExtractor_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getValueExtractorBytes() {
            Object ref = this.valueExtractor_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.valueExtractor_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setValueExtractor(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.valueExtractor_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearValueExtractor() {
            this.valueExtractor_ = LogMetric.getDefaultInstance().getValueExtractor();
            this.onChanged();
            return this;
        }

        public Builder setValueExtractorBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogMetric.checkByteStringIsUtf8(value);
            this.valueExtractor_ = value;
            this.onChanged();
            return this;
        }

        private MapField<String, String> internalGetLabelExtractors() {
            if (this.labelExtractors_ == null) {
                return MapField.emptyMapField(LabelExtractorsDefaultEntryHolder.defaultEntry);
            }
            return this.labelExtractors_;
        }

        private MapField<String, String> internalGetMutableLabelExtractors() {
            this.onChanged();
            if (this.labelExtractors_ == null) {
                this.labelExtractors_ = MapField.newMapField(LabelExtractorsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labelExtractors_.isMutable()) {
                this.labelExtractors_ = this.labelExtractors_.copy();
            }
            return this.labelExtractors_;
        }

        @Override
        public int getLabelExtractorsCount() {
            return this.internalGetLabelExtractors().getMap().size();
        }

        @Override
        public boolean containsLabelExtractors(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return this.internalGetLabelExtractors().getMap().containsKey(key);
        }

        @Override
        @Deprecated
        public Map<String, String> getLabelExtractors() {
            return this.getLabelExtractorsMap();
        }

        @Override
        public Map<String, String> getLabelExtractorsMap() {
            return this.internalGetLabelExtractors().getMap();
        }

        @Override
        public String getLabelExtractorsOrDefault(String key, String defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabelExtractors().getMap();
            return map2.containsKey(key) ? map2.get(key) : defaultValue;
        }

        @Override
        public String getLabelExtractorsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, String> map2 = this.internalGetLabelExtractors().getMap();
            if (!map2.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map2.get(key);
        }

        public Builder clearLabelExtractors() {
            this.internalGetMutableLabelExtractors().getMutableMap().clear();
            return this;
        }

        public Builder removeLabelExtractors(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabelExtractors().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableLabelExtractors() {
            return this.internalGetMutableLabelExtractors().getMutableMap();
        }

        public Builder putLabelExtractors(String key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            this.internalGetMutableLabelExtractors().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllLabelExtractors(Map<String, String> values) {
            this.internalGetMutableLabelExtractors().getMutableMap().putAll(values);
            return this;
        }

        @Override
        public boolean hasBucketOptions() {
            return this.bucketOptionsBuilder_ != null || this.bucketOptions_ != null;
        }

        @Override
        public Distribution.BucketOptions getBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                return this.bucketOptions_ == null ? Distribution.BucketOptions.getDefaultInstance() : this.bucketOptions_;
            }
            return this.bucketOptionsBuilder_.getMessage();
        }

        public Builder setBucketOptions(Distribution.BucketOptions value) {
            if (this.bucketOptionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bucketOptions_ = value;
                this.onChanged();
            } else {
                this.bucketOptionsBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setBucketOptions(Distribution.BucketOptions.Builder builderForValue) {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = builderForValue.build();
                this.onChanged();
            } else {
                this.bucketOptionsBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeBucketOptions(Distribution.BucketOptions value) {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = this.bucketOptions_ != null ? Distribution.BucketOptions.newBuilder(this.bucketOptions_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.bucketOptionsBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
                this.onChanged();
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            return this;
        }

        public Distribution.BucketOptions.Builder getBucketOptionsBuilder() {
            this.onChanged();
            return this.getBucketOptionsFieldBuilder().getBuilder();
        }

        @Override
        public Distribution.BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
            if (this.bucketOptionsBuilder_ != null) {
                return this.bucketOptionsBuilder_.getMessageOrBuilder();
            }
            return this.bucketOptions_ == null ? Distribution.BucketOptions.getDefaultInstance() : this.bucketOptions_;
        }

        private SingleFieldBuilderV3<Distribution.BucketOptions, Distribution.BucketOptions.Builder, Distribution.BucketOptionsOrBuilder> getBucketOptionsFieldBuilder() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptionsBuilder_ = new SingleFieldBuilderV3(this.getBucketOptions(), this.getParentForChildren(), this.isClean());
                this.bucketOptions_ = null;
            }
            return this.bucketOptionsBuilder_;
        }

        @Override
        @Deprecated
        public int getVersionValue() {
            return this.version_;
        }

        @Deprecated
        public Builder setVersionValue(int value) {
            this.version_ = value;
            this.onChanged();
            return this;
        }

        @Override
        @Deprecated
        public ApiVersion getVersion() {
            ApiVersion result2 = ApiVersion.valueOf(this.version_);
            return result2 == null ? ApiVersion.UNRECOGNIZED : result2;
        }

        @Deprecated
        public Builder setVersion(ApiVersion value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.version_ = value.getNumber();
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder clearVersion() {
            this.version_ = 0;
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

    private static final class LabelExtractorsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(LoggingMetricsProto.internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelExtractorsDefaultEntryHolder() {
        }
    }

    public static enum ApiVersion implements ProtocolMessageEnum
    {
        V2(0),
        V1(1),
        UNRECOGNIZED(-1);

        public static final int V2_VALUE = 0;
        public static final int V1_VALUE = 1;
        private static final Internal.EnumLiteMap<ApiVersion> internalValueMap;
        private static final ApiVersion[] VALUES;
        private final int value;

        @Override
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static ApiVersion valueOf(int value) {
            return ApiVersion.forNumber(value);
        }

        public static ApiVersion forNumber(int value) {
            switch (value) {
                case 0: {
                    return V2;
                }
                case 1: {
                    return V1;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ApiVersion> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ApiVersion.getDescriptor().getValues().get(this.ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ApiVersion.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return LogMetric.getDescriptor().getEnumTypes().get(0);
        }

        public static ApiVersion valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ApiVersion.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private ApiVersion(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ApiVersion>(){

                @Override
                public ApiVersion findValueByNumber(int number) {
                    return ApiVersion.forNumber(number);
                }
            };
            VALUES = ApiVersion.values();
        }
    }
}

