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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.TimeOfDayOrBuilder;
import com.google.type.TimeOfDayProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class TimeOfDay
extends GeneratedMessageV3
implements TimeOfDayOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int HOURS_FIELD_NUMBER = 1;
    private int hours_;
    public static final int MINUTES_FIELD_NUMBER = 2;
    private int minutes_;
    public static final int SECONDS_FIELD_NUMBER = 3;
    private int seconds_;
    public static final int NANOS_FIELD_NUMBER = 4;
    private int nanos_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final TimeOfDay DEFAULT_INSTANCE = new TimeOfDay();
    private static final Parser<TimeOfDay> PARSER = new AbstractParser<TimeOfDay>(){

        @Override
        public TimeOfDay parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new TimeOfDay(input2, extensionRegistry);
        }
    };

    private TimeOfDay(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private TimeOfDay() {
        this.hours_ = 0;
        this.minutes_ = 0;
        this.seconds_ = 0;
        this.nanos_ = 0;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private TimeOfDay(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block13;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block13;
                        done = true;
                        continue block13;
                    }
                    case 8: {
                        this.hours_ = input2.readInt32();
                        continue block13;
                    }
                    case 16: {
                        this.minutes_ = input2.readInt32();
                        continue block13;
                    }
                    case 24: {
                        this.seconds_ = input2.readInt32();
                        continue block13;
                    }
                    case 32: 
                }
                this.nanos_ = input2.readInt32();
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
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
    }

    @Override
    public int getHours() {
        return this.hours_;
    }

    @Override
    public int getMinutes() {
        return this.minutes_;
    }

    @Override
    public int getSeconds() {
        return this.seconds_;
    }

    @Override
    public int getNanos() {
        return this.nanos_;
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
        if (this.hours_ != 0) {
            output.writeInt32(1, this.hours_);
        }
        if (this.minutes_ != 0) {
            output.writeInt32(2, this.minutes_);
        }
        if (this.seconds_ != 0) {
            output.writeInt32(3, this.seconds_);
        }
        if (this.nanos_ != 0) {
            output.writeInt32(4, this.nanos_);
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
        if (this.hours_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(1, this.hours_);
        }
        if (this.minutes_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(2, this.minutes_);
        }
        if (this.seconds_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(3, this.seconds_);
        }
        if (this.nanos_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(4, this.nanos_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeOfDay)) {
            return super.equals(obj);
        }
        TimeOfDay other = (TimeOfDay)obj;
        boolean result2 = true;
        result2 = result2 && this.getHours() == other.getHours();
        result2 = result2 && this.getMinutes() == other.getMinutes();
        result2 = result2 && this.getSeconds() == other.getSeconds();
        result2 = result2 && this.getNanos() == other.getNanos();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + TimeOfDay.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getHours();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getMinutes();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getSeconds();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getNanos();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static TimeOfDay parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TimeOfDay parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TimeOfDay parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TimeOfDay parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TimeOfDay parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TimeOfDay parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TimeOfDay parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TimeOfDay parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TimeOfDay parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TimeOfDay parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return TimeOfDay.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TimeOfDay prototype) {
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

    public static TimeOfDay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TimeOfDay> parser() {
        return PARSER;
    }

    public Parser<TimeOfDay> getParserForType() {
        return PARSER;
    }

    @Override
    public TimeOfDay getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements TimeOfDayOrBuilder {
        private int hours_;
        private int minutes_;
        private int seconds_;
        private int nanos_;

        public static final Descriptors.Descriptor getDescriptor() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
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
            this.hours_ = 0;
            this.minutes_ = 0;
            this.seconds_ = 0;
            this.nanos_ = 0;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        @Override
        public TimeOfDay getDefaultInstanceForType() {
            return TimeOfDay.getDefaultInstance();
        }

        @Override
        public TimeOfDay build() {
            TimeOfDay result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public TimeOfDay buildPartial() {
            TimeOfDay result2 = new TimeOfDay(this);
            result2.hours_ = this.hours_;
            result2.minutes_ = this.minutes_;
            result2.seconds_ = this.seconds_;
            result2.nanos_ = this.nanos_;
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
            if (other instanceof TimeOfDay) {
                return this.mergeFrom((TimeOfDay)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(TimeOfDay other) {
            if (other == TimeOfDay.getDefaultInstance()) {
                return this;
            }
            if (other.getHours() != 0) {
                this.setHours(other.getHours());
            }
            if (other.getMinutes() != 0) {
                this.setMinutes(other.getMinutes());
            }
            if (other.getSeconds() != 0) {
                this.setSeconds(other.getSeconds());
            }
            if (other.getNanos() != 0) {
                this.setNanos(other.getNanos());
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
            TimeOfDay parsedMessage = null;
            try {
                parsedMessage = (TimeOfDay)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (TimeOfDay)e.getUnfinishedMessage();
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
        public int getHours() {
            return this.hours_;
        }

        public Builder setHours(int value) {
            this.hours_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearHours() {
            this.hours_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public int getMinutes() {
            return this.minutes_;
        }

        public Builder setMinutes(int value) {
            this.minutes_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMinutes() {
            this.minutes_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public int getSeconds() {
            return this.seconds_;
        }

        public Builder setSeconds(int value) {
            this.seconds_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSeconds() {
            this.seconds_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int value) {
            this.nanos_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
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

