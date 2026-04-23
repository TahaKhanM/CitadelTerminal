/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogEntryProto;
import com.google.logging.v2.LogEntrySourceLocationOrBuilder;
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
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LogEntrySourceLocation
extends GeneratedMessageV3
implements LogEntrySourceLocationOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int FILE_FIELD_NUMBER = 1;
    private volatile Object file_;
    public static final int LINE_FIELD_NUMBER = 2;
    private long line_;
    public static final int FUNCTION_FIELD_NUMBER = 3;
    private volatile Object function_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogEntrySourceLocation DEFAULT_INSTANCE = new LogEntrySourceLocation();
    private static final Parser<LogEntrySourceLocation> PARSER = new AbstractParser<LogEntrySourceLocation>(){

        @Override
        public LogEntrySourceLocation parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogEntrySourceLocation(input2, extensionRegistry);
        }
    };

    private LogEntrySourceLocation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogEntrySourceLocation() {
        this.file_ = "";
        this.line_ = 0L;
        this.function_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogEntrySourceLocation(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.file_ = s2;
                        continue block12;
                    }
                    case 16: {
                        this.line_ = input2.readInt64();
                        continue block12;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.function_ = s2;
                        continue block12;
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
        return LogEntryProto.internal_static_google_logging_v2_LogEntrySourceLocation_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LogEntryProto.internal_static_google_logging_v2_LogEntrySourceLocation_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntrySourceLocation.class, Builder.class);
    }

    @Override
    public String getFile() {
        Object ref = this.file_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.file_ = s2;
        return s2;
    }

    @Override
    public ByteString getFileBytes() {
        Object ref = this.file_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.file_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public long getLine() {
        return this.line_;
    }

    @Override
    public String getFunction() {
        Object ref = this.function_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.function_ = s2;
        return s2;
    }

    @Override
    public ByteString getFunctionBytes() {
        Object ref = this.function_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.function_ = b;
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
        if (!this.getFileBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.file_);
        }
        if (this.line_ != 0L) {
            output.writeInt64(2, this.line_);
        }
        if (!this.getFunctionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.function_);
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
        if (!this.getFileBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.file_);
        }
        if (this.line_ != 0L) {
            size2 += CodedOutputStream.computeInt64Size(2, this.line_);
        }
        if (!this.getFunctionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.function_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEntrySourceLocation)) {
            return super.equals(obj);
        }
        LogEntrySourceLocation other = (LogEntrySourceLocation)obj;
        boolean result2 = true;
        result2 = result2 && this.getFile().equals(other.getFile());
        result2 = result2 && this.getLine() == other.getLine();
        result2 = result2 && this.getFunction().equals(other.getFunction());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogEntrySourceLocation.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getFile().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashLong(this.getLine());
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getFunction().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogEntrySourceLocation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntrySourceLocation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntrySourceLocation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntrySourceLocation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntrySourceLocation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogEntrySourceLocation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogEntrySourceLocation parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntrySourceLocation parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntrySourceLocation parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogEntrySourceLocation parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogEntrySourceLocation parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogEntrySourceLocation parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogEntrySourceLocation.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogEntrySourceLocation prototype) {
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

    public static LogEntrySourceLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogEntrySourceLocation> parser() {
        return PARSER;
    }

    public Parser<LogEntrySourceLocation> getParserForType() {
        return PARSER;
    }

    @Override
    public LogEntrySourceLocation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogEntrySourceLocationOrBuilder {
        private Object file_ = "";
        private long line_;
        private Object function_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntrySourceLocation_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntrySourceLocation_fieldAccessorTable.ensureFieldAccessorsInitialized(LogEntrySourceLocation.class, Builder.class);
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
            this.file_ = "";
            this.line_ = 0L;
            this.function_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LogEntryProto.internal_static_google_logging_v2_LogEntrySourceLocation_descriptor;
        }

        @Override
        public LogEntrySourceLocation getDefaultInstanceForType() {
            return LogEntrySourceLocation.getDefaultInstance();
        }

        @Override
        public LogEntrySourceLocation build() {
            LogEntrySourceLocation result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogEntrySourceLocation buildPartial() {
            LogEntrySourceLocation result2 = new LogEntrySourceLocation(this);
            result2.file_ = this.file_;
            result2.line_ = this.line_;
            result2.function_ = this.function_;
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
            if (other instanceof LogEntrySourceLocation) {
                return this.mergeFrom((LogEntrySourceLocation)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogEntrySourceLocation other) {
            if (other == LogEntrySourceLocation.getDefaultInstance()) {
                return this;
            }
            if (!other.getFile().isEmpty()) {
                this.file_ = other.file_;
                this.onChanged();
            }
            if (other.getLine() != 0L) {
                this.setLine(other.getLine());
            }
            if (!other.getFunction().isEmpty()) {
                this.function_ = other.function_;
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
            LogEntrySourceLocation parsedMessage = null;
            try {
                parsedMessage = (LogEntrySourceLocation)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogEntrySourceLocation)e.getUnfinishedMessage();
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
        public String getFile() {
            Object ref = this.file_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.file_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getFileBytes() {
            Object ref = this.file_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.file_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setFile(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.file_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearFile() {
            this.file_ = LogEntrySourceLocation.getDefaultInstance().getFile();
            this.onChanged();
            return this;
        }

        public Builder setFileBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntrySourceLocation.checkByteStringIsUtf8(value);
            this.file_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public long getLine() {
            return this.line_;
        }

        public Builder setLine(long value) {
            this.line_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLine() {
            this.line_ = 0L;
            this.onChanged();
            return this;
        }

        @Override
        public String getFunction() {
            Object ref = this.function_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.function_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getFunctionBytes() {
            Object ref = this.function_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.function_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setFunction(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.function_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearFunction() {
            this.function_ = LogEntrySourceLocation.getDefaultInstance().getFunction();
            this.onChanged();
            return this;
        }

        public Builder setFunctionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogEntrySourceLocation.checkByteStringIsUtf8(value);
            this.function_ = value;
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

