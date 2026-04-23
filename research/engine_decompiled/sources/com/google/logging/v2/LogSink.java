/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogSinkOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LogSink
extends GeneratedMessageV3
implements LogSinkOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int DESTINATION_FIELD_NUMBER = 3;
    private volatile Object destination_;
    public static final int FILTER_FIELD_NUMBER = 5;
    private volatile Object filter_;
    public static final int OUTPUT_VERSION_FORMAT_FIELD_NUMBER = 6;
    private int outputVersionFormat_;
    public static final int WRITER_IDENTITY_FIELD_NUMBER = 8;
    private volatile Object writerIdentity_;
    public static final int INCLUDE_CHILDREN_FIELD_NUMBER = 9;
    private boolean includeChildren_;
    public static final int START_TIME_FIELD_NUMBER = 10;
    private Timestamp startTime_;
    public static final int END_TIME_FIELD_NUMBER = 11;
    private Timestamp endTime_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogSink DEFAULT_INSTANCE = new LogSink();
    private static final Parser<LogSink> PARSER = new AbstractParser<LogSink>(){

        @Override
        public LogSink parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogSink(input2, extensionRegistry);
        }
    };

    private LogSink(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogSink() {
        this.name_ = "";
        this.destination_ = "";
        this.filter_ = "";
        this.outputVersionFormat_ = 0;
        this.writerIdentity_ = "";
        this.includeChildren_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogSink(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
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
                    case 26: {
                        String s3 = input2.readStringRequireUtf8();
                        this.destination_ = s3;
                        continue block17;
                    }
                    case 42: {
                        String s4 = input2.readStringRequireUtf8();
                        this.filter_ = s4;
                        continue block17;
                    }
                    case 48: {
                        int rawValue;
                        this.outputVersionFormat_ = rawValue = input2.readEnum();
                        continue block17;
                    }
                    case 66: {
                        String s5 = input2.readStringRequireUtf8();
                        this.writerIdentity_ = s5;
                        continue block17;
                    }
                    case 72: {
                        this.includeChildren_ = input2.readBool();
                        continue block17;
                    }
                    case 82: {
                        Timestamp.Builder subBuilder = null;
                        if (this.startTime_ != null) {
                            subBuilder = this.startTime_.toBuilder();
                        }
                        this.startTime_ = input2.readMessage(Timestamp.parser(), extensionRegistry);
                        if (subBuilder == null) continue block17;
                        subBuilder.mergeFrom(this.startTime_);
                        this.startTime_ = subBuilder.buildPartial();
                        continue block17;
                    }
                    case 90: {
                        Timestamp.Builder subBuilder = null;
                        if (this.endTime_ != null) {
                            subBuilder = this.endTime_.toBuilder();
                        }
                        this.endTime_ = input2.readMessage(Timestamp.parser(), extensionRegistry);
                        if (subBuilder == null) continue block17;
                        subBuilder.mergeFrom(this.endTime_);
                        this.endTime_ = subBuilder.buildPartial();
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
        return LoggingConfigProto.internal_static_google_logging_v2_LogSink_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_LogSink_fieldAccessorTable.ensureFieldAccessorsInitialized(LogSink.class, Builder.class);
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
    public String getDestination() {
        Object ref = this.destination_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.destination_ = s2;
        return s2;
    }

    @Override
    public ByteString getDestinationBytes() {
        Object ref = this.destination_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.destination_ = b;
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
    @Deprecated
    public int getOutputVersionFormatValue() {
        return this.outputVersionFormat_;
    }

    @Override
    @Deprecated
    public VersionFormat getOutputVersionFormat() {
        VersionFormat result2 = VersionFormat.valueOf(this.outputVersionFormat_);
        return result2 == null ? VersionFormat.UNRECOGNIZED : result2;
    }

    @Override
    public String getWriterIdentity() {
        Object ref = this.writerIdentity_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.writerIdentity_ = s2;
        return s2;
    }

    @Override
    public ByteString getWriterIdentityBytes() {
        Object ref = this.writerIdentity_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.writerIdentity_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public boolean getIncludeChildren() {
        return this.includeChildren_;
    }

    @Override
    @Deprecated
    public boolean hasStartTime() {
        return this.startTime_ != null;
    }

    @Override
    @Deprecated
    public Timestamp getStartTime() {
        return this.startTime_ == null ? Timestamp.getDefaultInstance() : this.startTime_;
    }

    @Override
    @Deprecated
    public TimestampOrBuilder getStartTimeOrBuilder() {
        return this.getStartTime();
    }

    @Override
    @Deprecated
    public boolean hasEndTime() {
        return this.endTime_ != null;
    }

    @Override
    @Deprecated
    public Timestamp getEndTime() {
        return this.endTime_ == null ? Timestamp.getDefaultInstance() : this.endTime_;
    }

    @Override
    @Deprecated
    public TimestampOrBuilder getEndTimeOrBuilder() {
        return this.getEndTime();
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
        if (!this.getDestinationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.destination_);
        }
        if (!this.getFilterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.filter_);
        }
        if (this.outputVersionFormat_ != VersionFormat.VERSION_FORMAT_UNSPECIFIED.getNumber()) {
            output.writeEnum(6, this.outputVersionFormat_);
        }
        if (!this.getWriterIdentityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 8, this.writerIdentity_);
        }
        if (this.includeChildren_) {
            output.writeBool(9, this.includeChildren_);
        }
        if (this.startTime_ != null) {
            output.writeMessage(10, this.getStartTime());
        }
        if (this.endTime_ != null) {
            output.writeMessage(11, this.getEndTime());
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
        if (!this.getDestinationBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.destination_);
        }
        if (!this.getFilterBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.filter_);
        }
        if (this.outputVersionFormat_ != VersionFormat.VERSION_FORMAT_UNSPECIFIED.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(6, this.outputVersionFormat_);
        }
        if (!this.getWriterIdentityBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(8, this.writerIdentity_);
        }
        if (this.includeChildren_) {
            size2 += CodedOutputStream.computeBoolSize(9, this.includeChildren_);
        }
        if (this.startTime_ != null) {
            size2 += CodedOutputStream.computeMessageSize(10, this.getStartTime());
        }
        if (this.endTime_ != null) {
            size2 += CodedOutputStream.computeMessageSize(11, this.getEndTime());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogSink)) {
            return super.equals(obj);
        }
        LogSink other = (LogSink)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getDestination().equals(other.getDestination());
        result2 = result2 && this.getFilter().equals(other.getFilter());
        result2 = result2 && this.outputVersionFormat_ == other.outputVersionFormat_;
        result2 = result2 && this.getWriterIdentity().equals(other.getWriterIdentity());
        result2 = result2 && this.getIncludeChildren() == other.getIncludeChildren();
        boolean bl = result2 = result2 && this.hasStartTime() == other.hasStartTime();
        if (this.hasStartTime()) {
            result2 = result2 && this.getStartTime().equals(other.getStartTime());
        }
        boolean bl2 = result2 = result2 && this.hasEndTime() == other.hasEndTime();
        if (this.hasEndTime()) {
            result2 = result2 && this.getEndTime().equals(other.getEndTime());
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
        hash = 19 * hash + LogSink.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getDestination().hashCode();
        hash = 37 * hash + 5;
        hash = 53 * hash + this.getFilter().hashCode();
        hash = 37 * hash + 6;
        hash = 53 * hash + this.outputVersionFormat_;
        hash = 37 * hash + 8;
        hash = 53 * hash + this.getWriterIdentity().hashCode();
        hash = 37 * hash + 9;
        hash = 53 * hash + Internal.hashBoolean(this.getIncludeChildren());
        if (this.hasStartTime()) {
            hash = 37 * hash + 10;
            hash = 53 * hash + this.getStartTime().hashCode();
        }
        if (this.hasEndTime()) {
            hash = 37 * hash + 11;
            hash = 53 * hash + this.getEndTime().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogSink parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogSink parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogSink parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogSink parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogSink parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogSink parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogSink parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogSink parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogSink parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogSink parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogSink parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogSink parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogSink.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogSink prototype) {
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

    public static LogSink getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogSink> parser() {
        return PARSER;
    }

    public Parser<LogSink> getParserForType() {
        return PARSER;
    }

    @Override
    public LogSink getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogSinkOrBuilder {
        private Object name_ = "";
        private Object destination_ = "";
        private Object filter_ = "";
        private int outputVersionFormat_ = 0;
        private Object writerIdentity_ = "";
        private boolean includeChildren_;
        private Timestamp startTime_ = null;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> startTimeBuilder_;
        private Timestamp endTime_ = null;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> endTimeBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogSink_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogSink_fieldAccessorTable.ensureFieldAccessorsInitialized(LogSink.class, Builder.class);
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
            this.destination_ = "";
            this.filter_ = "";
            this.outputVersionFormat_ = 0;
            this.writerIdentity_ = "";
            this.includeChildren_ = false;
            if (this.startTimeBuilder_ == null) {
                this.startTime_ = null;
            } else {
                this.startTime_ = null;
                this.startTimeBuilder_ = null;
            }
            if (this.endTimeBuilder_ == null) {
                this.endTime_ = null;
            } else {
                this.endTime_ = null;
                this.endTimeBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogSink_descriptor;
        }

        @Override
        public LogSink getDefaultInstanceForType() {
            return LogSink.getDefaultInstance();
        }

        @Override
        public LogSink build() {
            LogSink result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogSink buildPartial() {
            LogSink result2 = new LogSink(this);
            result2.name_ = this.name_;
            result2.destination_ = this.destination_;
            result2.filter_ = this.filter_;
            result2.outputVersionFormat_ = this.outputVersionFormat_;
            result2.writerIdentity_ = this.writerIdentity_;
            result2.includeChildren_ = this.includeChildren_;
            if (this.startTimeBuilder_ == null) {
                result2.startTime_ = this.startTime_;
            } else {
                result2.startTime_ = this.startTimeBuilder_.build();
            }
            if (this.endTimeBuilder_ == null) {
                result2.endTime_ = this.endTime_;
            } else {
                result2.endTime_ = this.endTimeBuilder_.build();
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
            if (other instanceof LogSink) {
                return this.mergeFrom((LogSink)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogSink other) {
            if (other == LogSink.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.getDestination().isEmpty()) {
                this.destination_ = other.destination_;
                this.onChanged();
            }
            if (!other.getFilter().isEmpty()) {
                this.filter_ = other.filter_;
                this.onChanged();
            }
            if (other.outputVersionFormat_ != 0) {
                this.setOutputVersionFormatValue(other.getOutputVersionFormatValue());
            }
            if (!other.getWriterIdentity().isEmpty()) {
                this.writerIdentity_ = other.writerIdentity_;
                this.onChanged();
            }
            if (other.getIncludeChildren()) {
                this.setIncludeChildren(other.getIncludeChildren());
            }
            if (other.hasStartTime()) {
                this.mergeStartTime(other.getStartTime());
            }
            if (other.hasEndTime()) {
                this.mergeEndTime(other.getEndTime());
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
            LogSink parsedMessage = null;
            try {
                parsedMessage = (LogSink)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogSink)e.getUnfinishedMessage();
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
            this.name_ = LogSink.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogSink.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getDestination() {
            Object ref = this.destination_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.destination_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getDestinationBytes() {
            Object ref = this.destination_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.destination_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setDestination(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.destination_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDestination() {
            this.destination_ = LogSink.getDefaultInstance().getDestination();
            this.onChanged();
            return this;
        }

        public Builder setDestinationBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogSink.checkByteStringIsUtf8(value);
            this.destination_ = value;
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
            this.filter_ = LogSink.getDefaultInstance().getFilter();
            this.onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogSink.checkByteStringIsUtf8(value);
            this.filter_ = value;
            this.onChanged();
            return this;
        }

        @Override
        @Deprecated
        public int getOutputVersionFormatValue() {
            return this.outputVersionFormat_;
        }

        @Deprecated
        public Builder setOutputVersionFormatValue(int value) {
            this.outputVersionFormat_ = value;
            this.onChanged();
            return this;
        }

        @Override
        @Deprecated
        public VersionFormat getOutputVersionFormat() {
            VersionFormat result2 = VersionFormat.valueOf(this.outputVersionFormat_);
            return result2 == null ? VersionFormat.UNRECOGNIZED : result2;
        }

        @Deprecated
        public Builder setOutputVersionFormat(VersionFormat value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.outputVersionFormat_ = value.getNumber();
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder clearOutputVersionFormat() {
            this.outputVersionFormat_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public String getWriterIdentity() {
            Object ref = this.writerIdentity_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.writerIdentity_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getWriterIdentityBytes() {
            Object ref = this.writerIdentity_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.writerIdentity_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setWriterIdentity(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.writerIdentity_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearWriterIdentity() {
            this.writerIdentity_ = LogSink.getDefaultInstance().getWriterIdentity();
            this.onChanged();
            return this;
        }

        public Builder setWriterIdentityBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogSink.checkByteStringIsUtf8(value);
            this.writerIdentity_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getIncludeChildren() {
            return this.includeChildren_;
        }

        public Builder setIncludeChildren(boolean value) {
            this.includeChildren_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearIncludeChildren() {
            this.includeChildren_ = false;
            this.onChanged();
            return this;
        }

        @Override
        @Deprecated
        public boolean hasStartTime() {
            return this.startTimeBuilder_ != null || this.startTime_ != null;
        }

        @Override
        @Deprecated
        public Timestamp getStartTime() {
            if (this.startTimeBuilder_ == null) {
                return this.startTime_ == null ? Timestamp.getDefaultInstance() : this.startTime_;
            }
            return this.startTimeBuilder_.getMessage();
        }

        @Deprecated
        public Builder setStartTime(Timestamp value) {
            if (this.startTimeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.startTime_ = value;
                this.onChanged();
            } else {
                this.startTimeBuilder_.setMessage(value);
            }
            return this;
        }

        @Deprecated
        public Builder setStartTime(Timestamp.Builder builderForValue) {
            if (this.startTimeBuilder_ == null) {
                this.startTime_ = builderForValue.build();
                this.onChanged();
            } else {
                this.startTimeBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        @Deprecated
        public Builder mergeStartTime(Timestamp value) {
            if (this.startTimeBuilder_ == null) {
                this.startTime_ = this.startTime_ != null ? Timestamp.newBuilder(this.startTime_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.startTimeBuilder_.mergeFrom(value);
            }
            return this;
        }

        @Deprecated
        public Builder clearStartTime() {
            if (this.startTimeBuilder_ == null) {
                this.startTime_ = null;
                this.onChanged();
            } else {
                this.startTime_ = null;
                this.startTimeBuilder_ = null;
            }
            return this;
        }

        @Deprecated
        public Timestamp.Builder getStartTimeBuilder() {
            this.onChanged();
            return this.getStartTimeFieldBuilder().getBuilder();
        }

        @Override
        @Deprecated
        public TimestampOrBuilder getStartTimeOrBuilder() {
            if (this.startTimeBuilder_ != null) {
                return this.startTimeBuilder_.getMessageOrBuilder();
            }
            return this.startTime_ == null ? Timestamp.getDefaultInstance() : this.startTime_;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getStartTimeFieldBuilder() {
            if (this.startTimeBuilder_ == null) {
                this.startTimeBuilder_ = new SingleFieldBuilderV3(this.getStartTime(), this.getParentForChildren(), this.isClean());
                this.startTime_ = null;
            }
            return this.startTimeBuilder_;
        }

        @Override
        @Deprecated
        public boolean hasEndTime() {
            return this.endTimeBuilder_ != null || this.endTime_ != null;
        }

        @Override
        @Deprecated
        public Timestamp getEndTime() {
            if (this.endTimeBuilder_ == null) {
                return this.endTime_ == null ? Timestamp.getDefaultInstance() : this.endTime_;
            }
            return this.endTimeBuilder_.getMessage();
        }

        @Deprecated
        public Builder setEndTime(Timestamp value) {
            if (this.endTimeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.endTime_ = value;
                this.onChanged();
            } else {
                this.endTimeBuilder_.setMessage(value);
            }
            return this;
        }

        @Deprecated
        public Builder setEndTime(Timestamp.Builder builderForValue) {
            if (this.endTimeBuilder_ == null) {
                this.endTime_ = builderForValue.build();
                this.onChanged();
            } else {
                this.endTimeBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        @Deprecated
        public Builder mergeEndTime(Timestamp value) {
            if (this.endTimeBuilder_ == null) {
                this.endTime_ = this.endTime_ != null ? Timestamp.newBuilder(this.endTime_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.endTimeBuilder_.mergeFrom(value);
            }
            return this;
        }

        @Deprecated
        public Builder clearEndTime() {
            if (this.endTimeBuilder_ == null) {
                this.endTime_ = null;
                this.onChanged();
            } else {
                this.endTime_ = null;
                this.endTimeBuilder_ = null;
            }
            return this;
        }

        @Deprecated
        public Timestamp.Builder getEndTimeBuilder() {
            this.onChanged();
            return this.getEndTimeFieldBuilder().getBuilder();
        }

        @Override
        @Deprecated
        public TimestampOrBuilder getEndTimeOrBuilder() {
            if (this.endTimeBuilder_ != null) {
                return this.endTimeBuilder_.getMessageOrBuilder();
            }
            return this.endTime_ == null ? Timestamp.getDefaultInstance() : this.endTime_;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getEndTimeFieldBuilder() {
            if (this.endTimeBuilder_ == null) {
                this.endTimeBuilder_ = new SingleFieldBuilderV3(this.getEndTime(), this.getParentForChildren(), this.isClean());
                this.endTime_ = null;
            }
            return this.endTimeBuilder_;
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

    public static enum VersionFormat implements ProtocolMessageEnum
    {
        VERSION_FORMAT_UNSPECIFIED(0),
        V2(1),
        V1(2),
        UNRECOGNIZED(-1);

        public static final int VERSION_FORMAT_UNSPECIFIED_VALUE = 0;
        public static final int V2_VALUE = 1;
        public static final int V1_VALUE = 2;
        private static final Internal.EnumLiteMap<VersionFormat> internalValueMap;
        private static final VersionFormat[] VALUES;
        private final int value;

        @Override
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static VersionFormat valueOf(int value) {
            return VersionFormat.forNumber(value);
        }

        public static VersionFormat forNumber(int value) {
            switch (value) {
                case 0: {
                    return VERSION_FORMAT_UNSPECIFIED;
                }
                case 1: {
                    return V2;
                }
                case 2: {
                    return V1;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<VersionFormat> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return VersionFormat.getDescriptor().getValues().get(this.ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return VersionFormat.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return LogSink.getDescriptor().getEnumTypes().get(0);
        }

        public static VersionFormat valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != VersionFormat.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private VersionFormat(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<VersionFormat>(){

                @Override
                public VersionFormat findValueByNumber(int number) {
                    return VersionFormat.forNumber(number);
                }
            };
            VALUES = VersionFormat.values();
        }
    }
}

