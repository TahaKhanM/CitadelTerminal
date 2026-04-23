/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.DeleteLogMetricRequestOrBuilder;
import com.google.logging.v2.LoggingMetricsProto;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DeleteLogMetricRequest
extends GeneratedMessageV3
implements DeleteLogMetricRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int METRIC_NAME_FIELD_NUMBER = 1;
    private volatile Object metricName_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final DeleteLogMetricRequest DEFAULT_INSTANCE = new DeleteLogMetricRequest();
    private static final Parser<DeleteLogMetricRequest> PARSER = new AbstractParser<DeleteLogMetricRequest>(){

        @Override
        public DeleteLogMetricRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new DeleteLogMetricRequest(input2, extensionRegistry);
        }
    };

    private DeleteLogMetricRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private DeleteLogMetricRequest() {
        this.metricName_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DeleteLogMetricRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.metricName_ = s2;
                        continue block10;
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
        return LoggingMetricsProto.internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingMetricsProto.internal_static_google_logging_v2_DeleteLogMetricRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(DeleteLogMetricRequest.class, Builder.class);
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
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeleteLogMetricRequest)) {
            return super.equals(obj);
        }
        DeleteLogMetricRequest other = (DeleteLogMetricRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getMetricName().equals(other.getMetricName());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + DeleteLogMetricRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getMetricName().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static DeleteLogMetricRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DeleteLogMetricRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DeleteLogMetricRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DeleteLogMetricRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DeleteLogMetricRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static DeleteLogMetricRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static DeleteLogMetricRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static DeleteLogMetricRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static DeleteLogMetricRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static DeleteLogMetricRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static DeleteLogMetricRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static DeleteLogMetricRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return DeleteLogMetricRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DeleteLogMetricRequest prototype) {
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

    public static DeleteLogMetricRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeleteLogMetricRequest> parser() {
        return PARSER;
    }

    public Parser<DeleteLogMetricRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public DeleteLogMetricRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements DeleteLogMetricRequestOrBuilder {
        private Object metricName_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingMetricsProto.internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingMetricsProto.internal_static_google_logging_v2_DeleteLogMetricRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(DeleteLogMetricRequest.class, Builder.class);
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
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingMetricsProto.internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor;
        }

        @Override
        public DeleteLogMetricRequest getDefaultInstanceForType() {
            return DeleteLogMetricRequest.getDefaultInstance();
        }

        @Override
        public DeleteLogMetricRequest build() {
            DeleteLogMetricRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public DeleteLogMetricRequest buildPartial() {
            DeleteLogMetricRequest result2 = new DeleteLogMetricRequest(this);
            result2.metricName_ = this.metricName_;
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
            if (other instanceof DeleteLogMetricRequest) {
                return this.mergeFrom((DeleteLogMetricRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(DeleteLogMetricRequest other) {
            if (other == DeleteLogMetricRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getMetricName().isEmpty()) {
                this.metricName_ = other.metricName_;
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
            DeleteLogMetricRequest parsedMessage = null;
            try {
                parsedMessage = (DeleteLogMetricRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (DeleteLogMetricRequest)e.getUnfinishedMessage();
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
            this.metricName_ = DeleteLogMetricRequest.getDefaultInstance().getMetricName();
            this.onChanged();
            return this;
        }

        public Builder setMetricNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            DeleteLogMetricRequest.checkByteStringIsUtf8(value);
            this.metricName_ = value;
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

