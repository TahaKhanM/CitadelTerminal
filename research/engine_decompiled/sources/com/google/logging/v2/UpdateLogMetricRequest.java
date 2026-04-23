/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.logging.v2.LoggingMetricsProto;
import com.google.logging.v2.UpdateLogMetricRequestOrBuilder;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UpdateLogMetricRequest
extends GeneratedMessageV3
implements UpdateLogMetricRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int METRIC_NAME_FIELD_NUMBER = 1;
    private volatile Object metricName_;
    public static final int METRIC_FIELD_NUMBER = 2;
    private LogMetric metric_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final UpdateLogMetricRequest DEFAULT_INSTANCE = new UpdateLogMetricRequest();
    private static final Parser<UpdateLogMetricRequest> PARSER = new AbstractParser<UpdateLogMetricRequest>(){

        @Override
        public UpdateLogMetricRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new UpdateLogMetricRequest(input2, extensionRegistry);
        }
    };

    private UpdateLogMetricRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateLogMetricRequest() {
        this.metricName_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateLogMetricRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        String s2 = input2.readStringRequireUtf8();
                        this.metricName_ = s2;
                        continue block11;
                    }
                    case 18: {
                        LogMetric.Builder subBuilder = null;
                        if (this.metric_ != null) {
                            subBuilder = this.metric_.toBuilder();
                        }
                        this.metric_ = input2.readMessage(LogMetric.parser(), extensionRegistry);
                        if (subBuilder == null) continue block11;
                        subBuilder.mergeFrom(this.metric_);
                        this.metric_ = subBuilder.buildPartial();
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
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingMetricsProto.internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingMetricsProto.internal_static_google_logging_v2_UpdateLogMetricRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateLogMetricRequest.class, Builder.class);
    }

    @Override
    public String getMetricName() {
        Object ref = this.metricName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.metricName_ = s2;
        return s2;
    }

    @Override
    public ByteString getMetricNameBytes() {
        Object ref = this.metricName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.metricName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean hasMetric() {
        return this.metric_ != null;
    }

    @Override
    public LogMetric getMetric() {
        return this.metric_ == null ? LogMetric.getDefaultInstance() : this.metric_;
    }

    @Override
    public LogMetricOrBuilder getMetricOrBuilder() {
        return this.getMetric();
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
        if (!this.getMetricNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.metricName_);
        }
        if (this.metric_ != null) {
            output.writeMessage(2, this.getMetric());
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
        if (!this.getMetricNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.metricName_);
        }
        if (this.metric_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getMetric());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateLogMetricRequest)) {
            return super.equals(obj);
        }
        UpdateLogMetricRequest other = (UpdateLogMetricRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getMetricName().equals(other.getMetricName());
        boolean bl = result2 = result2 && this.hasMetric() == other.hasMetric();
        if (this.hasMetric()) {
            result2 = result2 && this.getMetric().equals(other.getMetric());
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
        hash = 19 * hash + UpdateLogMetricRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getMetricName().hashCode();
        if (this.hasMetric()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getMetric().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static UpdateLogMetricRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateLogMetricRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateLogMetricRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateLogMetricRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateLogMetricRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UpdateLogMetricRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UpdateLogMetricRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateLogMetricRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateLogMetricRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static UpdateLogMetricRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static UpdateLogMetricRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static UpdateLogMetricRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return UpdateLogMetricRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateLogMetricRequest prototype) {
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

    public static UpdateLogMetricRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateLogMetricRequest> parser() {
        return PARSER;
    }

    public Parser<UpdateLogMetricRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public UpdateLogMetricRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements UpdateLogMetricRequestOrBuilder {
        private Object metricName_ = "";
        private LogMetric metric_ = null;
        private SingleFieldBuilderV3<LogMetric, LogMetric.Builder, LogMetricOrBuilder> metricBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingMetricsProto.internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingMetricsProto.internal_static_google_logging_v2_UpdateLogMetricRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateLogMetricRequest.class, Builder.class);
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
            this.metricName_ = "";
            if (this.metricBuilder_ == null) {
                this.metric_ = null;
            } else {
                this.metric_ = null;
                this.metricBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingMetricsProto.internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor;
        }

        @Override
        public UpdateLogMetricRequest getDefaultInstanceForType() {
            return UpdateLogMetricRequest.getDefaultInstance();
        }

        @Override
        public UpdateLogMetricRequest build() {
            UpdateLogMetricRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public UpdateLogMetricRequest buildPartial() {
            UpdateLogMetricRequest result2 = new UpdateLogMetricRequest(this);
            result2.metricName_ = this.metricName_;
            if (this.metricBuilder_ == null) {
                result2.metric_ = this.metric_;
            } else {
                result2.metric_ = this.metricBuilder_.build();
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
            if (other instanceof UpdateLogMetricRequest) {
                return this.mergeFrom((UpdateLogMetricRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(UpdateLogMetricRequest other) {
            if (other == UpdateLogMetricRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getMetricName().isEmpty()) {
                this.metricName_ = other.metricName_;
                this.onChanged();
            }
            if (other.hasMetric()) {
                this.mergeMetric(other.getMetric());
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
            UpdateLogMetricRequest parsedMessage = null;
            try {
                parsedMessage = (UpdateLogMetricRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (UpdateLogMetricRequest)e.getUnfinishedMessage();
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
        public String getMetricName() {
            Object ref = this.metricName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.metricName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getMetricNameBytes() {
            Object ref = this.metricName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.metricName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setMetricName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.metricName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMetricName() {
            this.metricName_ = UpdateLogMetricRequest.getDefaultInstance().getMetricName();
            this.onChanged();
            return this;
        }

        public Builder setMetricNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            UpdateLogMetricRequest.checkByteStringIsUtf8(value);
            this.metricName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasMetric() {
            return this.metricBuilder_ != null || this.metric_ != null;
        }

        @Override
        public LogMetric getMetric() {
            if (this.metricBuilder_ == null) {
                return this.metric_ == null ? LogMetric.getDefaultInstance() : this.metric_;
            }
            return this.metricBuilder_.getMessage();
        }

        public Builder setMetric(LogMetric value) {
            if (this.metricBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.metric_ = value;
                this.onChanged();
            } else {
                this.metricBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setMetric(LogMetric.Builder builderForValue) {
            if (this.metricBuilder_ == null) {
                this.metric_ = builderForValue.build();
                this.onChanged();
            } else {
                this.metricBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeMetric(LogMetric value) {
            if (this.metricBuilder_ == null) {
                this.metric_ = this.metric_ != null ? LogMetric.newBuilder(this.metric_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.metricBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearMetric() {
            if (this.metricBuilder_ == null) {
                this.metric_ = null;
                this.onChanged();
            } else {
                this.metric_ = null;
                this.metricBuilder_ = null;
            }
            return this;
        }

        public LogMetric.Builder getMetricBuilder() {
            this.onChanged();
            return this.getMetricFieldBuilder().getBuilder();
        }

        @Override
        public LogMetricOrBuilder getMetricOrBuilder() {
            if (this.metricBuilder_ != null) {
                return this.metricBuilder_.getMessageOrBuilder();
            }
            return this.metric_ == null ? LogMetric.getDefaultInstance() : this.metric_;
        }

        private SingleFieldBuilderV3<LogMetric, LogMetric.Builder, LogMetricOrBuilder> getMetricFieldBuilder() {
            if (this.metricBuilder_ == null) {
                this.metricBuilder_ = new SingleFieldBuilderV3(this.getMetric(), this.getParentForChildren(), this.isClean());
                this.metric_ = null;
            }
            return this.metricBuilder_;
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

