/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.DistributionOrBuilder;
import com.google.api.DistributionProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Distribution
extends GeneratedMessageV3
implements DistributionOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int COUNT_FIELD_NUMBER = 1;
    private long count_;
    public static final int MEAN_FIELD_NUMBER = 2;
    private double mean_;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    private double sumOfSquaredDeviation_;
    public static final int RANGE_FIELD_NUMBER = 4;
    private Range range_;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    private BucketOptions bucketOptions_;
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    private List<Long> bucketCounts_;
    private int bucketCountsMemoizedSerializedSize = -1;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Distribution DEFAULT_INSTANCE = new Distribution();
    private static final Parser<Distribution> PARSER = new AbstractParser<Distribution>(){

        @Override
        public Distribution parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Distribution(input2, extensionRegistry);
        }
    };

    private Distribution(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Distribution() {
        this.count_ = 0L;
        this.mean_ = 0.0;
        this.sumOfSquaredDeviation_ = 0.0;
        this.bucketCounts_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Distribution(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block16: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block16;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block16;
                        done = true;
                        continue block16;
                    }
                    case 8: {
                        this.count_ = input2.readInt64();
                        continue block16;
                    }
                    case 17: {
                        this.mean_ = input2.readDouble();
                        continue block16;
                    }
                    case 25: {
                        this.sumOfSquaredDeviation_ = input2.readDouble();
                        continue block16;
                    }
                    case 34: {
                        Range.Builder subBuilder = null;
                        if (this.range_ != null) {
                            subBuilder = this.range_.toBuilder();
                        }
                        this.range_ = input2.readMessage(Range.parser(), extensionRegistry);
                        if (subBuilder == null) continue block16;
                        subBuilder.mergeFrom(this.range_);
                        this.range_ = subBuilder.buildPartial();
                        continue block16;
                    }
                    case 50: {
                        BucketOptions.Builder subBuilder = null;
                        if (this.bucketOptions_ != null) {
                            subBuilder = this.bucketOptions_.toBuilder();
                        }
                        this.bucketOptions_ = input2.readMessage(BucketOptions.parser(), extensionRegistry);
                        if (subBuilder == null) continue block16;
                        subBuilder.mergeFrom(this.bucketOptions_);
                        this.bucketOptions_ = subBuilder.buildPartial();
                        continue block16;
                    }
                    case 56: {
                        if ((mutable_bitField0_ & 0x20) != 32) {
                            this.bucketCounts_ = new ArrayList<Long>();
                            mutable_bitField0_ |= 0x20;
                        }
                        this.bucketCounts_.add(input2.readInt64());
                        continue block16;
                    }
                    case 58: 
                }
                int length = input2.readRawVarint32();
                int limit = input2.pushLimit(length);
                if ((mutable_bitField0_ & 0x20) != 32 && input2.getBytesUntilLimit() > 0) {
                    this.bucketCounts_ = new ArrayList<Long>();
                    mutable_bitField0_ |= 0x20;
                }
                while (input2.getBytesUntilLimit() > 0) {
                    this.bucketCounts_.add(input2.readInt64());
                }
                input2.popLimit(limit);
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x20) == 32) {
                this.bucketCounts_ = Collections.unmodifiableList(this.bucketCounts_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DistributionProto.internal_static_google_api_Distribution_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
    }

    @Override
    public long getCount() {
        return this.count_;
    }

    @Override
    public double getMean() {
        return this.mean_;
    }

    @Override
    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    @Override
    public boolean hasRange() {
        return this.range_ != null;
    }

    @Override
    public Range getRange() {
        return this.range_ == null ? Range.getDefaultInstance() : this.range_;
    }

    @Override
    public RangeOrBuilder getRangeOrBuilder() {
        return this.getRange();
    }

    @Override
    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    @Override
    public BucketOptions getBucketOptions() {
        return this.bucketOptions_ == null ? BucketOptions.getDefaultInstance() : this.bucketOptions_;
    }

    @Override
    public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
        return this.getBucketOptions();
    }

    @Override
    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    @Override
    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    @Override
    public long getBucketCounts(int index) {
        return this.bucketCounts_.get(index);
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
        this.getSerializedSize();
        if (this.count_ != 0L) {
            output.writeInt64(1, this.count_);
        }
        if (this.mean_ != 0.0) {
            output.writeDouble(2, this.mean_);
        }
        if (this.sumOfSquaredDeviation_ != 0.0) {
            output.writeDouble(3, this.sumOfSquaredDeviation_);
        }
        if (this.range_ != null) {
            output.writeMessage(4, this.getRange());
        }
        if (this.bucketOptions_ != null) {
            output.writeMessage(6, this.getBucketOptions());
        }
        if (this.getBucketCountsList().size() > 0) {
            output.writeUInt32NoTag(58);
            output.writeUInt32NoTag(this.bucketCountsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.bucketCounts_.size(); ++i) {
            output.writeInt64NoTag(this.bucketCounts_.get(i));
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
        if (this.count_ != 0L) {
            size2 += CodedOutputStream.computeInt64Size(1, this.count_);
        }
        if (this.mean_ != 0.0) {
            size2 += CodedOutputStream.computeDoubleSize(2, this.mean_);
        }
        if (this.sumOfSquaredDeviation_ != 0.0) {
            size2 += CodedOutputStream.computeDoubleSize(3, this.sumOfSquaredDeviation_);
        }
        if (this.range_ != null) {
            size2 += CodedOutputStream.computeMessageSize(4, this.getRange());
        }
        if (this.bucketOptions_ != null) {
            size2 += CodedOutputStream.computeMessageSize(6, this.getBucketOptions());
        }
        int dataSize = 0;
        for (int i = 0; i < this.bucketCounts_.size(); ++i) {
            dataSize += CodedOutputStream.computeInt64SizeNoTag(this.bucketCounts_.get(i));
        }
        size2 += dataSize;
        if (!this.getBucketCountsList().isEmpty()) {
            ++size2;
            size2 += CodedOutputStream.computeInt32SizeNoTag(dataSize);
        }
        this.bucketCountsMemoizedSerializedSize = dataSize;
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return super.equals(obj);
        }
        Distribution other = (Distribution)obj;
        boolean result2 = true;
        result2 = result2 && this.getCount() == other.getCount();
        result2 = result2 && Double.doubleToLongBits(this.getMean()) == Double.doubleToLongBits(other.getMean());
        result2 = result2 && Double.doubleToLongBits(this.getSumOfSquaredDeviation()) == Double.doubleToLongBits(other.getSumOfSquaredDeviation());
        boolean bl = result2 = result2 && this.hasRange() == other.hasRange();
        if (this.hasRange()) {
            result2 = result2 && this.getRange().equals(other.getRange());
        }
        boolean bl2 = result2 = result2 && this.hasBucketOptions() == other.hasBucketOptions();
        if (this.hasBucketOptions()) {
            result2 = result2 && this.getBucketOptions().equals(other.getBucketOptions());
        }
        result2 = result2 && this.getBucketCountsList().equals(other.getBucketCountsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Distribution.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + Internal.hashLong(this.getCount());
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getMean()));
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getSumOfSquaredDeviation()));
        if (this.hasRange()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getRange().hashCode();
        }
        if (this.hasBucketOptions()) {
            hash = 37 * hash + 6;
            hash = 53 * hash + this.getBucketOptions().hashCode();
        }
        if (this.getBucketCountsCount() > 0) {
            hash = 37 * hash + 7;
            hash = 53 * hash + this.getBucketCountsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Distribution parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Distribution parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Distribution parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Distribution parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Distribution parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Distribution parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Distribution parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Distribution parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Distribution parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Distribution parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Distribution parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Distribution parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Distribution.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Distribution prototype) {
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

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Distribution> parser() {
        return PARSER;
    }

    public Parser<Distribution> getParserForType() {
        return PARSER;
    }

    @Override
    public Distribution getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements DistributionOrBuilder {
        private int bitField0_;
        private long count_;
        private double mean_;
        private double sumOfSquaredDeviation_;
        private Range range_ = null;
        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> rangeBuilder_;
        private BucketOptions bucketOptions_ = null;
        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> bucketOptionsBuilder_;
        private List<Long> bucketCounts_ = Collections.emptyList();

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
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
            this.count_ = 0L;
            this.mean_ = 0.0;
            this.sumOfSquaredDeviation_ = 0.0;
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            this.bucketCounts_ = Collections.emptyList();
            this.bitField0_ &= 0xFFFFFFDF;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        @Override
        public Distribution getDefaultInstanceForType() {
            return Distribution.getDefaultInstance();
        }

        @Override
        public Distribution build() {
            Distribution result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Distribution buildPartial() {
            Distribution result2 = new Distribution(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.count_ = this.count_;
            result2.mean_ = this.mean_;
            result2.sumOfSquaredDeviation_ = this.sumOfSquaredDeviation_;
            if (this.rangeBuilder_ == null) {
                result2.range_ = this.range_;
            } else {
                result2.range_ = this.rangeBuilder_.build();
            }
            if (this.bucketOptionsBuilder_ == null) {
                result2.bucketOptions_ = this.bucketOptions_;
            } else {
                result2.bucketOptions_ = this.bucketOptionsBuilder_.build();
            }
            if ((this.bitField0_ & 0x20) == 32) {
                this.bucketCounts_ = Collections.unmodifiableList(this.bucketCounts_);
                this.bitField0_ &= 0xFFFFFFDF;
            }
            result2.bucketCounts_ = this.bucketCounts_;
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
            if (other instanceof Distribution) {
                return this.mergeFrom((Distribution)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Distribution other) {
            if (other == Distribution.getDefaultInstance()) {
                return this;
            }
            if (other.getCount() != 0L) {
                this.setCount(other.getCount());
            }
            if (other.getMean() != 0.0) {
                this.setMean(other.getMean());
            }
            if (other.getSumOfSquaredDeviation() != 0.0) {
                this.setSumOfSquaredDeviation(other.getSumOfSquaredDeviation());
            }
            if (other.hasRange()) {
                this.mergeRange(other.getRange());
            }
            if (other.hasBucketOptions()) {
                this.mergeBucketOptions(other.getBucketOptions());
            }
            if (!other.bucketCounts_.isEmpty()) {
                if (this.bucketCounts_.isEmpty()) {
                    this.bucketCounts_ = other.bucketCounts_;
                    this.bitField0_ &= 0xFFFFFFDF;
                } else {
                    this.ensureBucketCountsIsMutable();
                    this.bucketCounts_.addAll(other.bucketCounts_);
                }
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
            Distribution parsedMessage = null;
            try {
                parsedMessage = (Distribution)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Distribution)e.getUnfinishedMessage();
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
        public long getCount() {
            return this.count_;
        }

        public Builder setCount(long value) {
            this.count_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearCount() {
            this.count_ = 0L;
            this.onChanged();
            return this;
        }

        @Override
        public double getMean() {
            return this.mean_;
        }

        public Builder setMean(double value) {
            this.mean_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMean() {
            this.mean_ = 0.0;
            this.onChanged();
            return this;
        }

        @Override
        public double getSumOfSquaredDeviation() {
            return this.sumOfSquaredDeviation_;
        }

        public Builder setSumOfSquaredDeviation(double value) {
            this.sumOfSquaredDeviation_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            this.sumOfSquaredDeviation_ = 0.0;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasRange() {
            return this.rangeBuilder_ != null || this.range_ != null;
        }

        @Override
        public Range getRange() {
            if (this.rangeBuilder_ == null) {
                return this.range_ == null ? Range.getDefaultInstance() : this.range_;
            }
            return this.rangeBuilder_.getMessage();
        }

        public Builder setRange(Range value) {
            if (this.rangeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.range_ = value;
                this.onChanged();
            } else {
                this.rangeBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setRange(Range.Builder builderForValue) {
            if (this.rangeBuilder_ == null) {
                this.range_ = builderForValue.build();
                this.onChanged();
            } else {
                this.rangeBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeRange(Range value) {
            if (this.rangeBuilder_ == null) {
                this.range_ = this.range_ != null ? Range.newBuilder(this.range_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.rangeBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearRange() {
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
                this.onChanged();
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            return this;
        }

        public Range.Builder getRangeBuilder() {
            this.onChanged();
            return this.getRangeFieldBuilder().getBuilder();
        }

        @Override
        public RangeOrBuilder getRangeOrBuilder() {
            if (this.rangeBuilder_ != null) {
                return this.rangeBuilder_.getMessageOrBuilder();
            }
            return this.range_ == null ? Range.getDefaultInstance() : this.range_;
        }

        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> getRangeFieldBuilder() {
            if (this.rangeBuilder_ == null) {
                this.rangeBuilder_ = new SingleFieldBuilderV3(this.getRange(), this.getParentForChildren(), this.isClean());
                this.range_ = null;
            }
            return this.rangeBuilder_;
        }

        @Override
        public boolean hasBucketOptions() {
            return this.bucketOptionsBuilder_ != null || this.bucketOptions_ != null;
        }

        @Override
        public BucketOptions getBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                return this.bucketOptions_ == null ? BucketOptions.getDefaultInstance() : this.bucketOptions_;
            }
            return this.bucketOptionsBuilder_.getMessage();
        }

        public Builder setBucketOptions(BucketOptions value) {
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

        public Builder setBucketOptions(BucketOptions.Builder builderForValue) {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = builderForValue.build();
                this.onChanged();
            } else {
                this.bucketOptionsBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeBucketOptions(BucketOptions value) {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = this.bucketOptions_ != null ? BucketOptions.newBuilder(this.bucketOptions_).mergeFrom(value).buildPartial() : value;
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

        public BucketOptions.Builder getBucketOptionsBuilder() {
            this.onChanged();
            return this.getBucketOptionsFieldBuilder().getBuilder();
        }

        @Override
        public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
            if (this.bucketOptionsBuilder_ != null) {
                return this.bucketOptionsBuilder_.getMessageOrBuilder();
            }
            return this.bucketOptions_ == null ? BucketOptions.getDefaultInstance() : this.bucketOptions_;
        }

        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> getBucketOptionsFieldBuilder() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptionsBuilder_ = new SingleFieldBuilderV3(this.getBucketOptions(), this.getParentForChildren(), this.isClean());
                this.bucketOptions_ = null;
            }
            return this.bucketOptionsBuilder_;
        }

        private void ensureBucketCountsIsMutable() {
            if ((this.bitField0_ & 0x20) != 32) {
                this.bucketCounts_ = new ArrayList<Long>(this.bucketCounts_);
                this.bitField0_ |= 0x20;
            }
        }

        @Override
        public List<Long> getBucketCountsList() {
            return Collections.unmodifiableList(this.bucketCounts_);
        }

        @Override
        public int getBucketCountsCount() {
            return this.bucketCounts_.size();
        }

        @Override
        public long getBucketCounts(int index) {
            return this.bucketCounts_.get(index);
        }

        public Builder setBucketCounts(int index, long value) {
            this.ensureBucketCountsIsMutable();
            this.bucketCounts_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addBucketCounts(long value) {
            this.ensureBucketCountsIsMutable();
            this.bucketCounts_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> values) {
            this.ensureBucketCountsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.bucketCounts_);
            this.onChanged();
            return this;
        }

        public Builder clearBucketCounts() {
            this.bucketCounts_ = Collections.emptyList();
            this.bitField0_ &= 0xFFFFFFDF;
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

    public static final class BucketOptions
    extends GeneratedMessageV3
    implements BucketOptionsOrBuilder {
        private static final long serialVersionUID = 0L;
        private int optionsCase_ = 0;
        private Object options_;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        private byte memoizedIsInitialized = (byte)-1;
        private static final BucketOptions DEFAULT_INSTANCE = new BucketOptions();
        private static final Parser<BucketOptions> PARSER = new AbstractParser<BucketOptions>(){

            @Override
            public BucketOptions parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new BucketOptions(input2, extensionRegistry);
            }
        };

        private BucketOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private BucketOptions() {
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BucketOptions(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block12: while (!done) {
                    GeneratedMessageV3.Builder subBuilder;
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
                        case 10: {
                            subBuilder = null;
                            if (this.optionsCase_ == 1) {
                                subBuilder = ((Linear)this.options_).toBuilder();
                            }
                            this.options_ = input2.readMessage(Linear.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                ((Linear.Builder)subBuilder).mergeFrom((Linear)this.options_);
                                this.options_ = ((Linear.Builder)subBuilder).buildPartial();
                            }
                            this.optionsCase_ = 1;
                            continue block12;
                        }
                        case 18: {
                            subBuilder = null;
                            if (this.optionsCase_ == 2) {
                                subBuilder = ((Exponential)this.options_).toBuilder();
                            }
                            this.options_ = input2.readMessage(Exponential.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                ((Exponential.Builder)subBuilder).mergeFrom((Exponential)this.options_);
                                this.options_ = ((Exponential.Builder)subBuilder).buildPartial();
                            }
                            this.optionsCase_ = 2;
                            continue block12;
                        }
                        case 26: 
                    }
                    subBuilder = null;
                    if (this.optionsCase_ == 3) {
                        subBuilder = ((Explicit)this.options_).toBuilder();
                    }
                    this.options_ = input2.readMessage(Explicit.parser(), extensionRegistry);
                    if (subBuilder != null) {
                        ((Explicit.Builder)subBuilder).mergeFrom((Explicit)this.options_);
                        this.options_ = ((Explicit.Builder)subBuilder).buildPartial();
                    }
                    this.optionsCase_ = 3;
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
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
        }

        @Override
        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        @Override
        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
        }

        @Override
        public Linear getLinearBuckets() {
            if (this.optionsCase_ == 1) {
                return (Linear)this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override
        public LinearOrBuilder getLinearBucketsOrBuilder() {
            if (this.optionsCase_ == 1) {
                return (Linear)this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override
        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        @Override
        public Exponential getExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                return (Exponential)this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override
        public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
            if (this.optionsCase_ == 2) {
                return (Exponential)this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override
        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        @Override
        public Explicit getExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                return (Explicit)this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        @Override
        public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
            if (this.optionsCase_ == 3) {
                return (Explicit)this.options_;
            }
            return Explicit.getDefaultInstance();
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
            if (this.optionsCase_ == 1) {
                output.writeMessage(1, (Linear)this.options_);
            }
            if (this.optionsCase_ == 2) {
                output.writeMessage(2, (Exponential)this.options_);
            }
            if (this.optionsCase_ == 3) {
                output.writeMessage(3, (Explicit)this.options_);
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
            if (this.optionsCase_ == 1) {
                size2 += CodedOutputStream.computeMessageSize(1, (Linear)this.options_);
            }
            if (this.optionsCase_ == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, (Exponential)this.options_);
            }
            if (this.optionsCase_ == 3) {
                size2 += CodedOutputStream.computeMessageSize(3, (Explicit)this.options_);
            }
            this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
            return size2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BucketOptions)) {
                return super.equals(obj);
            }
            BucketOptions other = (BucketOptions)obj;
            boolean result2 = true;
            boolean bl = result2 = result2 && this.getOptionsCase().equals(other.getOptionsCase());
            if (!result2) {
                return false;
            }
            switch (this.optionsCase_) {
                case 1: {
                    result2 = result2 && this.getLinearBuckets().equals(other.getLinearBuckets());
                    break;
                }
                case 2: {
                    result2 = result2 && this.getExponentialBuckets().equals(other.getExponentialBuckets());
                    break;
                }
                case 3: {
                    result2 = result2 && this.getExplicitBuckets().equals(other.getExplicitBuckets());
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
            hash = 19 * hash + BucketOptions.getDescriptor().hashCode();
            switch (this.optionsCase_) {
                case 1: {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.getLinearBuckets().hashCode();
                    break;
                }
                case 2: {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + this.getExponentialBuckets().hashCode();
                    break;
                }
                case 3: {
                    hash = 37 * hash + 3;
                    hash = 53 * hash + this.getExplicitBuckets().hashCode();
                    break;
                }
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static BucketOptions parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BucketOptions parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BucketOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BucketOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BucketOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BucketOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BucketOptions parseFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static BucketOptions parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        public static BucketOptions parseDelimitedFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
        }

        public static BucketOptions parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
        }

        public static BucketOptions parseFrom(CodedInputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static BucketOptions parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return BucketOptions.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BucketOptions prototype) {
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

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BucketOptions> parser() {
            return PARSER;
        }

        public Parser<BucketOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public BucketOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements BucketOptionsOrBuilder {
            private int optionsCase_ = 0;
            private Object options_;
            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> linearBucketsBuilder_;
            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> exponentialBucketsBuilder_;
            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> explicitBucketsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
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
                this.optionsCase_ = 0;
                this.options_ = null;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            @Override
            public BucketOptions getDefaultInstanceForType() {
                return BucketOptions.getDefaultInstance();
            }

            @Override
            public BucketOptions build() {
                BucketOptions result2 = this.buildPartial();
                if (!result2.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result2);
                }
                return result2;
            }

            @Override
            public BucketOptions buildPartial() {
                BucketOptions result2 = new BucketOptions(this);
                if (this.optionsCase_ == 1) {
                    if (this.linearBucketsBuilder_ == null) {
                        result2.options_ = this.options_;
                    } else {
                        result2.options_ = this.linearBucketsBuilder_.build();
                    }
                }
                if (this.optionsCase_ == 2) {
                    if (this.exponentialBucketsBuilder_ == null) {
                        result2.options_ = this.options_;
                    } else {
                        result2.options_ = this.exponentialBucketsBuilder_.build();
                    }
                }
                if (this.optionsCase_ == 3) {
                    if (this.explicitBucketsBuilder_ == null) {
                        result2.options_ = this.options_;
                    } else {
                        result2.options_ = this.explicitBucketsBuilder_.build();
                    }
                }
                result2.optionsCase_ = this.optionsCase_;
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
                if (other instanceof BucketOptions) {
                    return this.mergeFrom((BucketOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(BucketOptions other) {
                if (other == BucketOptions.getDefaultInstance()) {
                    return this;
                }
                switch (other.getOptionsCase()) {
                    case LINEAR_BUCKETS: {
                        this.mergeLinearBuckets(other.getLinearBuckets());
                        break;
                    }
                    case EXPONENTIAL_BUCKETS: {
                        this.mergeExponentialBuckets(other.getExponentialBuckets());
                        break;
                    }
                    case EXPLICIT_BUCKETS: {
                        this.mergeExplicitBuckets(other.getExplicitBuckets());
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
                BucketOptions parsedMessage = null;
                try {
                    parsedMessage = (BucketOptions)PARSER.parsePartialFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (BucketOptions)e.getUnfinishedMessage();
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
            public OptionsCase getOptionsCase() {
                return OptionsCase.forNumber(this.optionsCase_);
            }

            public Builder clearOptions() {
                this.optionsCase_ = 0;
                this.options_ = null;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLinearBuckets() {
                return this.optionsCase_ == 1;
            }

            @Override
            public Linear getLinearBuckets() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 1) {
                        return (Linear)this.options_;
                    }
                    return Linear.getDefaultInstance();
                }
                if (this.optionsCase_ == 1) {
                    return this.linearBucketsBuilder_.getMessage();
                }
                return Linear.getDefaultInstance();
            }

            public Builder setLinearBuckets(Linear value) {
                if (this.linearBucketsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.linearBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder setLinearBuckets(Linear.Builder builderForValue) {
                if (this.linearBucketsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.linearBucketsBuilder_.setMessage(builderForValue.build());
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder mergeLinearBuckets(Linear value) {
                if (this.linearBucketsBuilder_ == null) {
                    this.options_ = this.optionsCase_ == 1 && this.options_ != Linear.getDefaultInstance() ? Linear.newBuilder((Linear)this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    if (this.optionsCase_ == 1) {
                        this.linearBucketsBuilder_.mergeFrom(value);
                    }
                    this.linearBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder clearLinearBuckets() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        this.onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.linearBucketsBuilder_.clear();
                }
                return this;
            }

            public Linear.Builder getLinearBucketsBuilder() {
                return this.getLinearBucketsFieldBuilder().getBuilder();
            }

            @Override
            public LinearOrBuilder getLinearBucketsOrBuilder() {
                if (this.optionsCase_ == 1 && this.linearBucketsBuilder_ != null) {
                    return this.linearBucketsBuilder_.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 1) {
                    return (Linear)this.options_;
                }
                return Linear.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> getLinearBucketsFieldBuilder() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 1) {
                        this.options_ = Linear.getDefaultInstance();
                    }
                    this.linearBucketsBuilder_ = new SingleFieldBuilderV3((Linear)this.options_, this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 1;
                this.onChanged();
                return this.linearBucketsBuilder_;
            }

            @Override
            public boolean hasExponentialBuckets() {
                return this.optionsCase_ == 2;
            }

            @Override
            public Exponential getExponentialBuckets() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 2) {
                        return (Exponential)this.options_;
                    }
                    return Exponential.getDefaultInstance();
                }
                if (this.optionsCase_ == 2) {
                    return this.exponentialBucketsBuilder_.getMessage();
                }
                return Exponential.getDefaultInstance();
            }

            public Builder setExponentialBuckets(Exponential value) {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.exponentialBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder setExponentialBuckets(Exponential.Builder builderForValue) {
                if (this.exponentialBucketsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.exponentialBucketsBuilder_.setMessage(builderForValue.build());
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential value) {
                if (this.exponentialBucketsBuilder_ == null) {
                    this.options_ = this.optionsCase_ == 2 && this.options_ != Exponential.getDefaultInstance() ? Exponential.newBuilder((Exponential)this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    if (this.optionsCase_ == 2) {
                        this.exponentialBucketsBuilder_.mergeFrom(value);
                    }
                    this.exponentialBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder clearExponentialBuckets() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        this.onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.exponentialBucketsBuilder_.clear();
                }
                return this;
            }

            public Exponential.Builder getExponentialBucketsBuilder() {
                return this.getExponentialBucketsFieldBuilder().getBuilder();
            }

            @Override
            public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
                if (this.optionsCase_ == 2 && this.exponentialBucketsBuilder_ != null) {
                    return this.exponentialBucketsBuilder_.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 2) {
                    return (Exponential)this.options_;
                }
                return Exponential.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> getExponentialBucketsFieldBuilder() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 2) {
                        this.options_ = Exponential.getDefaultInstance();
                    }
                    this.exponentialBucketsBuilder_ = new SingleFieldBuilderV3((Exponential)this.options_, this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 2;
                this.onChanged();
                return this.exponentialBucketsBuilder_;
            }

            @Override
            public boolean hasExplicitBuckets() {
                return this.optionsCase_ == 3;
            }

            @Override
            public Explicit getExplicitBuckets() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 3) {
                        return (Explicit)this.options_;
                    }
                    return Explicit.getDefaultInstance();
                }
                if (this.optionsCase_ == 3) {
                    return this.explicitBucketsBuilder_.getMessage();
                }
                return Explicit.getDefaultInstance();
            }

            public Builder setExplicitBuckets(Explicit value) {
                if (this.explicitBucketsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.explicitBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder setExplicitBuckets(Explicit.Builder builderForValue) {
                if (this.explicitBucketsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.explicitBucketsBuilder_.setMessage(builderForValue.build());
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder mergeExplicitBuckets(Explicit value) {
                if (this.explicitBucketsBuilder_ == null) {
                    this.options_ = this.optionsCase_ == 3 && this.options_ != Explicit.getDefaultInstance() ? Explicit.newBuilder((Explicit)this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    if (this.optionsCase_ == 3) {
                        this.explicitBucketsBuilder_.mergeFrom(value);
                    }
                    this.explicitBucketsBuilder_.setMessage(value);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder clearExplicitBuckets() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        this.onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.explicitBucketsBuilder_.clear();
                }
                return this;
            }

            public Explicit.Builder getExplicitBucketsBuilder() {
                return this.getExplicitBucketsFieldBuilder().getBuilder();
            }

            @Override
            public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
                if (this.optionsCase_ == 3 && this.explicitBucketsBuilder_ != null) {
                    return this.explicitBucketsBuilder_.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 3) {
                    return (Explicit)this.options_;
                }
                return Explicit.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> getExplicitBucketsFieldBuilder() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 3) {
                        this.options_ = Explicit.getDefaultInstance();
                    }
                    this.explicitBucketsBuilder_ = new SingleFieldBuilderV3((Explicit)this.options_, this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 3;
                this.onChanged();
                return this.explicitBucketsBuilder_;
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

        public static enum OptionsCase implements Internal.EnumLite
        {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);

            private final int value;

            private OptionsCase(int value) {
                this.value = value;
            }

            @Deprecated
            public static OptionsCase valueOf(int value) {
                return OptionsCase.forNumber(value);
            }

            public static OptionsCase forNumber(int value) {
                switch (value) {
                    case 1: {
                        return LINEAR_BUCKETS;
                    }
                    case 2: {
                        return EXPONENTIAL_BUCKETS;
                    }
                    case 3: {
                        return EXPLICIT_BUCKETS;
                    }
                    case 0: {
                        return OPTIONS_NOT_SET;
                    }
                }
                return null;
            }

            @Override
            public int getNumber() {
                return this.value;
            }
        }

        public static final class Explicit
        extends GeneratedMessageV3
        implements ExplicitOrBuilder {
            private static final long serialVersionUID = 0L;
            public static final int BOUNDS_FIELD_NUMBER = 1;
            private List<Double> bounds_;
            private int boundsMemoizedSerializedSize = -1;
            private byte memoizedIsInitialized = (byte)-1;
            private static final Explicit DEFAULT_INSTANCE = new Explicit();
            private static final Parser<Explicit> PARSER = new AbstractParser<Explicit>(){

                @Override
                public Explicit parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Explicit(input2, extensionRegistry);
                }
            };

            private Explicit(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private Explicit() {
                this.bounds_ = Collections.emptyList();
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Explicit(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistry == null) {
                    throw new NullPointerException();
                }
                boolean mutable_bitField0_ = false;
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
                            case 9: {
                                if (!(mutable_bitField0_ & true)) {
                                    this.bounds_ = new ArrayList<Double>();
                                    mutable_bitField0_ |= true;
                                }
                                this.bounds_.add(input2.readDouble());
                                continue block11;
                            }
                            case 10: 
                        }
                        int length = input2.readRawVarint32();
                        int limit = input2.pushLimit(length);
                        if (!(mutable_bitField0_ & true) && input2.getBytesUntilLimit() > 0) {
                            this.bounds_ = new ArrayList<Double>();
                            mutable_bitField0_ |= true;
                        }
                        while (input2.getBytesUntilLimit() > 0) {
                            this.bounds_.add(input2.readDouble());
                        }
                        input2.popLimit(limit);
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
                        this.bounds_ = Collections.unmodifiableList(this.bounds_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
            }

            @Override
            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            @Override
            public int getBoundsCount() {
                return this.bounds_.size();
            }

            @Override
            public double getBounds(int index) {
                return this.bounds_.get(index);
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
                this.getSerializedSize();
                if (this.getBoundsList().size() > 0) {
                    output.writeUInt32NoTag(10);
                    output.writeUInt32NoTag(this.boundsMemoizedSerializedSize);
                }
                for (int i = 0; i < this.bounds_.size(); ++i) {
                    output.writeDoubleNoTag(this.bounds_.get(i));
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
                int dataSize = 0;
                dataSize = 8 * this.getBoundsList().size();
                size2 += dataSize;
                if (!this.getBoundsList().isEmpty()) {
                    ++size2;
                    size2 += CodedOutputStream.computeInt32SizeNoTag(dataSize);
                }
                this.boundsMemoizedSerializedSize = dataSize;
                this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
                return size2;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Explicit)) {
                    return super.equals(obj);
                }
                Explicit other = (Explicit)obj;
                boolean result2 = true;
                result2 = result2 && this.getBoundsList().equals(other.getBoundsList());
                result2 = result2 && this.unknownFields.equals(other.unknownFields);
                return result2;
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + Explicit.getDescriptor().hashCode();
                if (this.getBoundsCount() > 0) {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.getBoundsList().hashCode();
                }
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static Explicit parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Explicit parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Explicit parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Explicit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Explicit parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Explicit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Explicit parseFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Explicit parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Explicit parseDelimitedFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
            }

            public static Explicit parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Explicit parseFrom(CodedInputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Explicit parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            @Override
            public Builder newBuilderForType() {
                return Explicit.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Explicit prototype) {
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

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Explicit> parser() {
                return PARSER;
            }

            public Parser<Explicit> getParserForType() {
                return PARSER;
            }

            @Override
            public Explicit getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements ExplicitOrBuilder {
                private int bitField0_;
                private List<Double> bounds_ = Collections.emptyList();

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
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
                    this.bounds_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                @Override
                public Explicit getDefaultInstanceForType() {
                    return Explicit.getDefaultInstance();
                }

                @Override
                public Explicit build() {
                    Explicit result2 = this.buildPartial();
                    if (!result2.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result2);
                    }
                    return result2;
                }

                @Override
                public Explicit buildPartial() {
                    Explicit result2 = new Explicit(this);
                    int from_bitField0_ = this.bitField0_;
                    if ((this.bitField0_ & 1) == 1) {
                        this.bounds_ = Collections.unmodifiableList(this.bounds_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result2.bounds_ = this.bounds_;
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
                    if (other instanceof Explicit) {
                        return this.mergeFrom((Explicit)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Explicit other) {
                    if (other == Explicit.getDefaultInstance()) {
                        return this;
                    }
                    if (!other.bounds_.isEmpty()) {
                        if (this.bounds_.isEmpty()) {
                            this.bounds_ = other.bounds_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureBoundsIsMutable();
                            this.bounds_.addAll(other.bounds_);
                        }
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
                    Explicit parsedMessage = null;
                    try {
                        parsedMessage = (Explicit)PARSER.parsePartialFrom(input2, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Explicit)e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                    finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                private void ensureBoundsIsMutable() {
                    if ((this.bitField0_ & 1) != 1) {
                        this.bounds_ = new ArrayList<Double>(this.bounds_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override
                public List<Double> getBoundsList() {
                    return Collections.unmodifiableList(this.bounds_);
                }

                @Override
                public int getBoundsCount() {
                    return this.bounds_.size();
                }

                @Override
                public double getBounds(int index) {
                    return this.bounds_.get(index);
                }

                public Builder setBounds(int index, double value) {
                    this.ensureBoundsIsMutable();
                    this.bounds_.set(index, value);
                    this.onChanged();
                    return this;
                }

                public Builder addBounds(double value) {
                    this.ensureBoundsIsMutable();
                    this.bounds_.add(value);
                    this.onChanged();
                    return this;
                }

                public Builder addAllBounds(Iterable<? extends Double> values) {
                    this.ensureBoundsIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.bounds_);
                    this.onChanged();
                    return this;
                }

                public Builder clearBounds() {
                    this.bounds_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
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

        public static interface ExplicitOrBuilder
        extends MessageOrBuilder {
            public List<Double> getBoundsList();

            public int getBoundsCount();

            public double getBounds(int var1);
        }

        public static final class Exponential
        extends GeneratedMessageV3
        implements ExponentialOrBuilder {
            private static final long serialVersionUID = 0L;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            private int numFiniteBuckets_;
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            private double growthFactor_;
            public static final int SCALE_FIELD_NUMBER = 3;
            private double scale_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final Exponential DEFAULT_INSTANCE = new Exponential();
            private static final Parser<Exponential> PARSER = new AbstractParser<Exponential>(){

                @Override
                public Exponential parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Exponential(input2, extensionRegistry);
                }
            };

            private Exponential(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private Exponential() {
                this.numFiniteBuckets_ = 0;
                this.growthFactor_ = 0.0;
                this.scale_ = 0.0;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Exponential(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistry == null) {
                    throw new NullPointerException();
                }
                boolean mutable_bitField0_ = false;
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
                                this.numFiniteBuckets_ = input2.readInt32();
                                continue block12;
                            }
                            case 17: {
                                this.growthFactor_ = input2.readDouble();
                                continue block12;
                            }
                            case 25: 
                        }
                        this.scale_ = input2.readDouble();
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
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
            }

            @Override
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override
            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            @Override
            public double getScale() {
                return this.scale_;
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
                if (this.numFiniteBuckets_ != 0) {
                    output.writeInt32(1, this.numFiniteBuckets_);
                }
                if (this.growthFactor_ != 0.0) {
                    output.writeDouble(2, this.growthFactor_);
                }
                if (this.scale_ != 0.0) {
                    output.writeDouble(3, this.scale_);
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
                if (this.numFiniteBuckets_ != 0) {
                    size2 += CodedOutputStream.computeInt32Size(1, this.numFiniteBuckets_);
                }
                if (this.growthFactor_ != 0.0) {
                    size2 += CodedOutputStream.computeDoubleSize(2, this.growthFactor_);
                }
                if (this.scale_ != 0.0) {
                    size2 += CodedOutputStream.computeDoubleSize(3, this.scale_);
                }
                this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
                return size2;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Exponential)) {
                    return super.equals(obj);
                }
                Exponential other = (Exponential)obj;
                boolean result2 = true;
                result2 = result2 && this.getNumFiniteBuckets() == other.getNumFiniteBuckets();
                result2 = result2 && Double.doubleToLongBits(this.getGrowthFactor()) == Double.doubleToLongBits(other.getGrowthFactor());
                result2 = result2 && Double.doubleToLongBits(this.getScale()) == Double.doubleToLongBits(other.getScale());
                result2 = result2 && this.unknownFields.equals(other.unknownFields);
                return result2;
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + Exponential.getDescriptor().hashCode();
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getNumFiniteBuckets();
                hash = 37 * hash + 2;
                hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getGrowthFactor()));
                hash = 37 * hash + 3;
                hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getScale()));
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static Exponential parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Exponential parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Exponential parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Exponential parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Exponential parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Exponential parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Exponential parseFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Exponential parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Exponential parseDelimitedFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
            }

            public static Exponential parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Exponential parseFrom(CodedInputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Exponential parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            @Override
            public Builder newBuilderForType() {
                return Exponential.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Exponential prototype) {
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

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Exponential> parser() {
                return PARSER;
            }

            public Parser<Exponential> getParserForType() {
                return PARSER;
            }

            @Override
            public Exponential getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements ExponentialOrBuilder {
                private int numFiniteBuckets_;
                private double growthFactor_;
                private double scale_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
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
                    this.numFiniteBuckets_ = 0;
                    this.growthFactor_ = 0.0;
                    this.scale_ = 0.0;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                @Override
                public Exponential getDefaultInstanceForType() {
                    return Exponential.getDefaultInstance();
                }

                @Override
                public Exponential build() {
                    Exponential result2 = this.buildPartial();
                    if (!result2.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result2);
                    }
                    return result2;
                }

                @Override
                public Exponential buildPartial() {
                    Exponential result2 = new Exponential(this);
                    result2.numFiniteBuckets_ = this.numFiniteBuckets_;
                    result2.growthFactor_ = this.growthFactor_;
                    result2.scale_ = this.scale_;
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
                    if (other instanceof Exponential) {
                        return this.mergeFrom((Exponential)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Exponential other) {
                    if (other == Exponential.getDefaultInstance()) {
                        return this;
                    }
                    if (other.getNumFiniteBuckets() != 0) {
                        this.setNumFiniteBuckets(other.getNumFiniteBuckets());
                    }
                    if (other.getGrowthFactor() != 0.0) {
                        this.setGrowthFactor(other.getGrowthFactor());
                    }
                    if (other.getScale() != 0.0) {
                        this.setScale(other.getScale());
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
                    Exponential parsedMessage = null;
                    try {
                        parsedMessage = (Exponential)PARSER.parsePartialFrom(input2, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Exponential)e.getUnfinishedMessage();
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
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int value) {
                    this.numFiniteBuckets_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    this.onChanged();
                    return this;
                }

                @Override
                public double getGrowthFactor() {
                    return this.growthFactor_;
                }

                public Builder setGrowthFactor(double value) {
                    this.growthFactor_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearGrowthFactor() {
                    this.growthFactor_ = 0.0;
                    this.onChanged();
                    return this;
                }

                @Override
                public double getScale() {
                    return this.scale_;
                }

                public Builder setScale(double value) {
                    this.scale_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearScale() {
                    this.scale_ = 0.0;
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

        public static interface ExponentialOrBuilder
        extends MessageOrBuilder {
            public int getNumFiniteBuckets();

            public double getGrowthFactor();

            public double getScale();
        }

        public static final class Linear
        extends GeneratedMessageV3
        implements LinearOrBuilder {
            private static final long serialVersionUID = 0L;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            private int numFiniteBuckets_;
            public static final int WIDTH_FIELD_NUMBER = 2;
            private double width_;
            public static final int OFFSET_FIELD_NUMBER = 3;
            private double offset_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final Linear DEFAULT_INSTANCE = new Linear();
            private static final Parser<Linear> PARSER = new AbstractParser<Linear>(){

                @Override
                public Linear parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Linear(input2, extensionRegistry);
                }
            };

            private Linear(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private Linear() {
                this.numFiniteBuckets_ = 0;
                this.width_ = 0.0;
                this.offset_ = 0.0;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Linear(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistry == null) {
                    throw new NullPointerException();
                }
                boolean mutable_bitField0_ = false;
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
                                this.numFiniteBuckets_ = input2.readInt32();
                                continue block12;
                            }
                            case 17: {
                                this.width_ = input2.readDouble();
                                continue block12;
                            }
                            case 25: 
                        }
                        this.offset_ = input2.readDouble();
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
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
            }

            @Override
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override
            public double getWidth() {
                return this.width_;
            }

            @Override
            public double getOffset() {
                return this.offset_;
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
                if (this.numFiniteBuckets_ != 0) {
                    output.writeInt32(1, this.numFiniteBuckets_);
                }
                if (this.width_ != 0.0) {
                    output.writeDouble(2, this.width_);
                }
                if (this.offset_ != 0.0) {
                    output.writeDouble(3, this.offset_);
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
                if (this.numFiniteBuckets_ != 0) {
                    size2 += CodedOutputStream.computeInt32Size(1, this.numFiniteBuckets_);
                }
                if (this.width_ != 0.0) {
                    size2 += CodedOutputStream.computeDoubleSize(2, this.width_);
                }
                if (this.offset_ != 0.0) {
                    size2 += CodedOutputStream.computeDoubleSize(3, this.offset_);
                }
                this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
                return size2;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Linear)) {
                    return super.equals(obj);
                }
                Linear other = (Linear)obj;
                boolean result2 = true;
                result2 = result2 && this.getNumFiniteBuckets() == other.getNumFiniteBuckets();
                result2 = result2 && Double.doubleToLongBits(this.getWidth()) == Double.doubleToLongBits(other.getWidth());
                result2 = result2 && Double.doubleToLongBits(this.getOffset()) == Double.doubleToLongBits(other.getOffset());
                result2 = result2 && this.unknownFields.equals(other.unknownFields);
                return result2;
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + Linear.getDescriptor().hashCode();
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getNumFiniteBuckets();
                hash = 37 * hash + 2;
                hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getWidth()));
                hash = 37 * hash + 3;
                hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getOffset()));
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static Linear parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Linear parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Linear parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Linear parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Linear parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Linear parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Linear parseFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Linear parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Linear parseDelimitedFrom(InputStream input2) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
            }

            public static Linear parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
            }

            public static Linear parseFrom(CodedInputStream input2) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2);
            }

            public static Linear parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
            }

            @Override
            public Builder newBuilderForType() {
                return Linear.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Linear prototype) {
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

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Linear> parser() {
                return PARSER;
            }

            public Parser<Linear> getParserForType() {
                return PARSER;
            }

            @Override
            public Linear getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements LinearOrBuilder {
                private int numFiniteBuckets_;
                private double width_;
                private double offset_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
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
                    this.numFiniteBuckets_ = 0;
                    this.width_ = 0.0;
                    this.offset_ = 0.0;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                @Override
                public Linear getDefaultInstanceForType() {
                    return Linear.getDefaultInstance();
                }

                @Override
                public Linear build() {
                    Linear result2 = this.buildPartial();
                    if (!result2.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result2);
                    }
                    return result2;
                }

                @Override
                public Linear buildPartial() {
                    Linear result2 = new Linear(this);
                    result2.numFiniteBuckets_ = this.numFiniteBuckets_;
                    result2.width_ = this.width_;
                    result2.offset_ = this.offset_;
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
                    if (other instanceof Linear) {
                        return this.mergeFrom((Linear)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Linear other) {
                    if (other == Linear.getDefaultInstance()) {
                        return this;
                    }
                    if (other.getNumFiniteBuckets() != 0) {
                        this.setNumFiniteBuckets(other.getNumFiniteBuckets());
                    }
                    if (other.getWidth() != 0.0) {
                        this.setWidth(other.getWidth());
                    }
                    if (other.getOffset() != 0.0) {
                        this.setOffset(other.getOffset());
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
                    Linear parsedMessage = null;
                    try {
                        parsedMessage = (Linear)PARSER.parsePartialFrom(input2, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Linear)e.getUnfinishedMessage();
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
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int value) {
                    this.numFiniteBuckets_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    this.onChanged();
                    return this;
                }

                @Override
                public double getWidth() {
                    return this.width_;
                }

                public Builder setWidth(double value) {
                    this.width_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearWidth() {
                    this.width_ = 0.0;
                    this.onChanged();
                    return this;
                }

                @Override
                public double getOffset() {
                    return this.offset_;
                }

                public Builder setOffset(double value) {
                    this.offset_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearOffset() {
                    this.offset_ = 0.0;
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

        public static interface LinearOrBuilder
        extends MessageOrBuilder {
            public int getNumFiniteBuckets();

            public double getWidth();

            public double getOffset();
        }
    }

    public static interface BucketOptionsOrBuilder
    extends MessageOrBuilder {
        public boolean hasLinearBuckets();

        public BucketOptions.Linear getLinearBuckets();

        public BucketOptions.LinearOrBuilder getLinearBucketsOrBuilder();

        public boolean hasExponentialBuckets();

        public BucketOptions.Exponential getExponentialBuckets();

        public BucketOptions.ExponentialOrBuilder getExponentialBucketsOrBuilder();

        public boolean hasExplicitBuckets();

        public BucketOptions.Explicit getExplicitBuckets();

        public BucketOptions.ExplicitOrBuilder getExplicitBucketsOrBuilder();

        public BucketOptions.OptionsCase getOptionsCase();
    }

    public static final class Range
    extends GeneratedMessageV3
    implements RangeOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int MIN_FIELD_NUMBER = 1;
        private double min_;
        public static final int MAX_FIELD_NUMBER = 2;
        private double max_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Range DEFAULT_INSTANCE = new Range();
        private static final Parser<Range> PARSER = new AbstractParser<Range>(){

            @Override
            public Range parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Range(input2, extensionRegistry);
            }
        };

        private Range(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Range() {
            this.min_ = 0.0;
            this.max_ = 0.0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Range(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
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
                        case 9: {
                            this.min_ = input2.readDouble();
                            continue block11;
                        }
                        case 17: 
                    }
                    this.max_ = input2.readDouble();
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
            return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
        }

        @Override
        public double getMin() {
            return this.min_;
        }

        @Override
        public double getMax() {
            return this.max_;
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
            if (this.min_ != 0.0) {
                output.writeDouble(1, this.min_);
            }
            if (this.max_ != 0.0) {
                output.writeDouble(2, this.max_);
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
            if (this.min_ != 0.0) {
                size2 += CodedOutputStream.computeDoubleSize(1, this.min_);
            }
            if (this.max_ != 0.0) {
                size2 += CodedOutputStream.computeDoubleSize(2, this.max_);
            }
            this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
            return size2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return super.equals(obj);
            }
            Range other = (Range)obj;
            boolean result2 = true;
            result2 = result2 && Double.doubleToLongBits(this.getMin()) == Double.doubleToLongBits(other.getMin());
            result2 = result2 && Double.doubleToLongBits(this.getMax()) == Double.doubleToLongBits(other.getMax());
            result2 = result2 && this.unknownFields.equals(other.unknownFields);
            return result2;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Range.getDescriptor().hashCode();
            hash = 37 * hash + 1;
            hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getMin()));
            hash = 37 * hash + 2;
            hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getMax()));
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Range parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Range parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Range parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Range parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Range parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Range parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Range parseFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Range parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Range parseDelimitedFrom(InputStream input2) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
        }

        public static Range parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
        }

        public static Range parseFrom(CodedInputStream input2) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2);
        }

        public static Range parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return Range.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Range prototype) {
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

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Range> parser() {
            return PARSER;
        }

        public Parser<Range> getParserForType() {
            return PARSER;
        }

        @Override
        public Range getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements RangeOrBuilder {
            private double min_;
            private double max_;

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
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
                this.min_ = 0.0;
                this.max_ = 0.0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            @Override
            public Range getDefaultInstanceForType() {
                return Range.getDefaultInstance();
            }

            @Override
            public Range build() {
                Range result2 = this.buildPartial();
                if (!result2.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result2);
                }
                return result2;
            }

            @Override
            public Range buildPartial() {
                Range result2 = new Range(this);
                result2.min_ = this.min_;
                result2.max_ = this.max_;
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
                if (other instanceof Range) {
                    return this.mergeFrom((Range)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Range other) {
                if (other == Range.getDefaultInstance()) {
                    return this;
                }
                if (other.getMin() != 0.0) {
                    this.setMin(other.getMin());
                }
                if (other.getMax() != 0.0) {
                    this.setMax(other.getMax());
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
                Range parsedMessage = null;
                try {
                    parsedMessage = (Range)PARSER.parsePartialFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Range)e.getUnfinishedMessage();
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
            public double getMin() {
                return this.min_;
            }

            public Builder setMin(double value) {
                this.min_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMin() {
                this.min_ = 0.0;
                this.onChanged();
                return this;
            }

            @Override
            public double getMax() {
                return this.max_;
            }

            public Builder setMax(double value) {
                this.max_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMax() {
                this.max_ = 0.0;
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

    public static interface RangeOrBuilder
    extends MessageOrBuilder {
        public double getMin();

        public double getMax();
    }
}

