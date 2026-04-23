/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListLogMetricsResponseOrBuilder;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.logging.v2.LoggingMetricsProto;
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

public final class ListLogMetricsResponse
extends GeneratedMessageV3
implements ListLogMetricsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int METRICS_FIELD_NUMBER = 1;
    private List<LogMetric> metrics_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListLogMetricsResponse DEFAULT_INSTANCE = new ListLogMetricsResponse();
    private static final Parser<ListLogMetricsResponse> PARSER = new AbstractParser<ListLogMetricsResponse>(){

        @Override
        public ListLogMetricsResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListLogMetricsResponse(input2, extensionRegistry);
        }
    };

    private ListLogMetricsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListLogMetricsResponse() {
        this.metrics_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLogMetricsResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 10: {
                        if (!(mutable_bitField0_ & true)) {
                            this.metrics_ = new ArrayList<LogMetric>();
                            mutable_bitField0_ |= true;
                        }
                        this.metrics_.add(input2.readMessage(LogMetric.parser(), extensionRegistry));
                        continue block11;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.nextPageToken_ = s2;
                        continue block11;
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
            if (mutable_bitField0_ & true) {
                this.metrics_ = Collections.unmodifiableList(this.metrics_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingMetricsProto.internal_static_google_logging_v2_ListLogMetricsResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingMetricsProto.internal_static_google_logging_v2_ListLogMetricsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogMetricsResponse.class, Builder.class);
    }

    @Override
    public List<LogMetric> getMetricsList() {
        return this.metrics_;
    }

    @Override
    public List<? extends LogMetricOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    @Override
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override
    public LogMetric getMetrics(int index) {
        return this.metrics_.get(index);
    }

    @Override
    public LogMetricOrBuilder getMetricsOrBuilder(int index) {
        return this.metrics_.get(index);
    }

    @Override
    public String getNextPageToken() {
        Object ref = this.nextPageToken_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.nextPageToken_ = s2;
        return s2;
    }

    @Override
    public ByteString getNextPageTokenBytes() {
        Object ref = this.nextPageToken_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.nextPageToken_ = b;
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
        for (int i = 0; i < this.metrics_.size(); ++i) {
            output.writeMessage(1, this.metrics_.get(i));
        }
        if (!this.getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.nextPageToken_);
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
        for (int i = 0; i < this.metrics_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.metrics_.get(i));
        }
        if (!this.getNextPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListLogMetricsResponse)) {
            return super.equals(obj);
        }
        ListLogMetricsResponse other = (ListLogMetricsResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getMetricsList().equals(other.getMetricsList());
        result2 = result2 && this.getNextPageToken().equals(other.getNextPageToken());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ListLogMetricsResponse.getDescriptor().hashCode();
        if (this.getMetricsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getMetricsList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListLogMetricsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogMetricsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogMetricsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogMetricsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogMetricsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogMetricsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogMetricsResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogMetricsResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogMetricsResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListLogMetricsResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogMetricsResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogMetricsResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListLogMetricsResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLogMetricsResponse prototype) {
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

    public static ListLogMetricsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLogMetricsResponse> parser() {
        return PARSER;
    }

    public Parser<ListLogMetricsResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListLogMetricsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListLogMetricsResponseOrBuilder {
        private int bitField0_;
        private List<LogMetric> metrics_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogMetric, LogMetric.Builder, LogMetricOrBuilder> metricsBuilder_;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingMetricsProto.internal_static_google_logging_v2_ListLogMetricsResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingMetricsProto.internal_static_google_logging_v2_ListLogMetricsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogMetricsResponse.class, Builder.class);
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
                this.getMetricsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.metricsBuilder_ == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.metricsBuilder_.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingMetricsProto.internal_static_google_logging_v2_ListLogMetricsResponse_descriptor;
        }

        @Override
        public ListLogMetricsResponse getDefaultInstanceForType() {
            return ListLogMetricsResponse.getDefaultInstance();
        }

        @Override
        public ListLogMetricsResponse build() {
            ListLogMetricsResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListLogMetricsResponse buildPartial() {
            ListLogMetricsResponse result2 = new ListLogMetricsResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.metricsBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.metrics_ = this.metrics_;
            } else {
                result2.metrics_ = this.metricsBuilder_.build();
            }
            result2.nextPageToken_ = this.nextPageToken_;
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
            if (other instanceof ListLogMetricsResponse) {
                return this.mergeFrom((ListLogMetricsResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListLogMetricsResponse other) {
            if (other == ListLogMetricsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.metricsBuilder_ == null) {
                if (!other.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = other.metrics_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureMetricsIsMutable();
                        this.metrics_.addAll(other.metrics_);
                    }
                    this.onChanged();
                }
            } else if (!other.metrics_.isEmpty()) {
                if (this.metricsBuilder_.isEmpty()) {
                    this.metricsBuilder_.dispose();
                    this.metricsBuilder_ = null;
                    this.metrics_ = other.metrics_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.metricsBuilder_ = alwaysUseFieldBuilders ? this.getMetricsFieldBuilder() : null;
                } else {
                    this.metricsBuilder_.addAllMessages(other.metrics_);
                }
            }
            if (!other.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = other.nextPageToken_;
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
            ListLogMetricsResponse parsedMessage = null;
            try {
                parsedMessage = (ListLogMetricsResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListLogMetricsResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureMetricsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.metrics_ = new ArrayList<LogMetric>(this.metrics_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<LogMetric> getMetricsList() {
            if (this.metricsBuilder_ == null) {
                return Collections.unmodifiableList(this.metrics_);
            }
            return this.metricsBuilder_.getMessageList();
        }

        @Override
        public int getMetricsCount() {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.size();
            }
            return this.metricsBuilder_.getCount();
        }

        @Override
        public LogMetric getMetrics(int index) {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.get(index);
            }
            return this.metricsBuilder_.getMessage(index);
        }

        public Builder setMetrics(int index, LogMetric value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.set(index, value);
                this.onChanged();
            } else {
                this.metricsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setMetrics(int index, LogMetric.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addMetrics(LogMetric value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.add(value);
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addMetrics(int index, LogMetric value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.add(index, value);
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addMetrics(LogMetric.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addMetrics(int index, LogMetric.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends LogMetric> values) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.metrics_);
                this.onChanged();
            } else {
                this.metricsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearMetrics() {
            if (this.metricsBuilder_ == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.metricsBuilder_.clear();
            }
            return this;
        }

        public Builder removeMetrics(int index) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.remove(index);
                this.onChanged();
            } else {
                this.metricsBuilder_.remove(index);
            }
            return this;
        }

        public LogMetric.Builder getMetricsBuilder(int index) {
            return this.getMetricsFieldBuilder().getBuilder(index);
        }

        @Override
        public LogMetricOrBuilder getMetricsOrBuilder(int index) {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.get(index);
            }
            return this.metricsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogMetricOrBuilder> getMetricsOrBuilderList() {
            if (this.metricsBuilder_ != null) {
                return this.metricsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metrics_);
        }

        public LogMetric.Builder addMetricsBuilder() {
            return this.getMetricsFieldBuilder().addBuilder(LogMetric.getDefaultInstance());
        }

        public LogMetric.Builder addMetricsBuilder(int index) {
            return this.getMetricsFieldBuilder().addBuilder(index, LogMetric.getDefaultInstance());
        }

        public List<LogMetric.Builder> getMetricsBuilderList() {
            return this.getMetricsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogMetric, LogMetric.Builder, LogMetricOrBuilder> getMetricsFieldBuilder() {
            if (this.metricsBuilder_ == null) {
                this.metricsBuilder_ = new RepeatedFieldBuilderV3(this.metrics_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.metrics_ = null;
            }
            return this.metricsBuilder_;
        }

        @Override
        public String getNextPageToken() {
            Object ref = this.nextPageToken_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.nextPageToken_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNextPageTokenBytes() {
            Object ref = this.nextPageToken_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.nextPageToken_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setNextPageToken(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.nextPageToken_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListLogMetricsResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogMetricsResponse.checkByteStringIsUtf8(value);
            this.nextPageToken_ = value;
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

