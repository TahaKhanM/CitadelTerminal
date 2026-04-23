/*
 * Decompiled with CFR 0.152.
 */
package com.google.type;

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
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.LatLngOrBuilder;
import com.google.type.LatLngProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LatLng
extends GeneratedMessageV3
implements LatLngOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int LATITUDE_FIELD_NUMBER = 1;
    private double latitude_;
    public static final int LONGITUDE_FIELD_NUMBER = 2;
    private double longitude_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LatLng DEFAULT_INSTANCE = new LatLng();
    private static final Parser<LatLng> PARSER = new AbstractParser<LatLng>(){

        @Override
        public LatLng parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LatLng(input2, extensionRegistry);
        }
    };

    private LatLng(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LatLng() {
        this.latitude_ = 0.0;
        this.longitude_ = 0.0;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LatLng(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.latitude_ = input2.readDouble();
                        continue block11;
                    }
                    case 17: 
                }
                this.longitude_ = input2.readDouble();
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
        return LatLngProto.internal_static_google_type_LatLng_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LatLngProto.internal_static_google_type_LatLng_fieldAccessorTable.ensureFieldAccessorsInitialized(LatLng.class, Builder.class);
    }

    @Override
    public double getLatitude() {
        return this.latitude_;
    }

    @Override
    public double getLongitude() {
        return this.longitude_;
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
        if (this.latitude_ != 0.0) {
            output.writeDouble(1, this.latitude_);
        }
        if (this.longitude_ != 0.0) {
            output.writeDouble(2, this.longitude_);
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
        if (this.latitude_ != 0.0) {
            size2 += CodedOutputStream.computeDoubleSize(1, this.latitude_);
        }
        if (this.longitude_ != 0.0) {
            size2 += CodedOutputStream.computeDoubleSize(2, this.longitude_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return super.equals(obj);
        }
        LatLng other = (LatLng)obj;
        boolean result2 = true;
        result2 = result2 && Double.doubleToLongBits(this.getLatitude()) == Double.doubleToLongBits(other.getLatitude());
        result2 = result2 && Double.doubleToLongBits(this.getLongitude()) == Double.doubleToLongBits(other.getLongitude());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LatLng.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getLatitude()));
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getLongitude()));
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LatLng parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LatLng parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LatLng parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LatLng parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LatLng parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LatLng parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LatLng parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LatLng parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LatLng parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LatLng parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LatLng parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LatLng parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LatLng.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LatLng prototype) {
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

    public static LatLng getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LatLng> parser() {
        return PARSER;
    }

    public Parser<LatLng> getParserForType() {
        return PARSER;
    }

    @Override
    public LatLng getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LatLngOrBuilder {
        private double latitude_;
        private double longitude_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LatLngProto.internal_static_google_type_LatLng_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LatLngProto.internal_static_google_type_LatLng_fieldAccessorTable.ensureFieldAccessorsInitialized(LatLng.class, Builder.class);
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
            this.latitude_ = 0.0;
            this.longitude_ = 0.0;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LatLngProto.internal_static_google_type_LatLng_descriptor;
        }

        @Override
        public LatLng getDefaultInstanceForType() {
            return LatLng.getDefaultInstance();
        }

        @Override
        public LatLng build() {
            LatLng result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LatLng buildPartial() {
            LatLng result2 = new LatLng(this);
            result2.latitude_ = this.latitude_;
            result2.longitude_ = this.longitude_;
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
            if (other instanceof LatLng) {
                return this.mergeFrom((LatLng)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LatLng other) {
            if (other == LatLng.getDefaultInstance()) {
                return this;
            }
            if (other.getLatitude() != 0.0) {
                this.setLatitude(other.getLatitude());
            }
            if (other.getLongitude() != 0.0) {
                this.setLongitude(other.getLongitude());
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
            LatLng parsedMessage = null;
            try {
                parsedMessage = (LatLng)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LatLng)e.getUnfinishedMessage();
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
        public double getLatitude() {
            return this.latitude_;
        }

        public Builder setLatitude(double value) {
            this.latitude_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLatitude() {
            this.latitude_ = 0.0;
            this.onChanged();
            return this;
        }

        @Override
        public double getLongitude() {
            return this.longitude_;
        }

        public Builder setLongitude(double value) {
            this.longitude_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLongitude() {
            this.longitude_ = 0.0;
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

