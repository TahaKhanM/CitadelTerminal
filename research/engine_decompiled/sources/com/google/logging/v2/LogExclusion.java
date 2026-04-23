/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogExclusionOrBuilder;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LogExclusion
extends GeneratedMessageV3
implements LogExclusionOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    private volatile Object description_;
    public static final int FILTER_FIELD_NUMBER = 3;
    private volatile Object filter_;
    public static final int DISABLED_FIELD_NUMBER = 4;
    private boolean disabled_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final LogExclusion DEFAULT_INSTANCE = new LogExclusion();
    private static final Parser<LogExclusion> PARSER = new AbstractParser<LogExclusion>(){

        @Override
        public LogExclusion parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new LogExclusion(input2, extensionRegistry);
        }
    };

    private LogExclusion(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private LogExclusion() {
        this.name_ = "";
        this.description_ = "";
        this.filter_ = "";
        this.disabled_ = false;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LogExclusion(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block13;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.description_ = s2;
                        continue block13;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.filter_ = s2;
                        continue block13;
                    }
                    case 32: {
                        this.disabled_ = input2.readBool();
                        continue block13;
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
        return LoggingConfigProto.internal_static_google_logging_v2_LogExclusion_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_LogExclusion_fieldAccessorTable.ensureFieldAccessorsInitialized(LogExclusion.class, Builder.class);
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
    public boolean getDisabled() {
        return this.disabled_;
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
        if (this.disabled_) {
            output.writeBool(4, this.disabled_);
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
        if (this.disabled_) {
            size2 += CodedOutputStream.computeBoolSize(4, this.disabled_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogExclusion)) {
            return super.equals(obj);
        }
        LogExclusion other = (LogExclusion)obj;
        boolean result2 = true;
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getDescription().equals(other.getDescription());
        result2 = result2 && this.getFilter().equals(other.getFilter());
        result2 = result2 && this.getDisabled() == other.getDisabled();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + LogExclusion.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getDescription().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getFilter().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + Internal.hashBoolean(this.getDisabled());
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static LogExclusion parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogExclusion parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogExclusion parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogExclusion parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogExclusion parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static LogExclusion parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static LogExclusion parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogExclusion parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogExclusion parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static LogExclusion parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static LogExclusion parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static LogExclusion parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return LogExclusion.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogExclusion prototype) {
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

    public static LogExclusion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogExclusion> parser() {
        return PARSER;
    }

    public Parser<LogExclusion> getParserForType() {
        return PARSER;
    }

    @Override
    public LogExclusion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements LogExclusionOrBuilder {
        private Object name_ = "";
        private Object description_ = "";
        private Object filter_ = "";
        private boolean disabled_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogExclusion_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogExclusion_fieldAccessorTable.ensureFieldAccessorsInitialized(LogExclusion.class, Builder.class);
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
            this.disabled_ = false;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_LogExclusion_descriptor;
        }

        @Override
        public LogExclusion getDefaultInstanceForType() {
            return LogExclusion.getDefaultInstance();
        }

        @Override
        public LogExclusion build() {
            LogExclusion result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public LogExclusion buildPartial() {
            LogExclusion result2 = new LogExclusion(this);
            result2.name_ = this.name_;
            result2.description_ = this.description_;
            result2.filter_ = this.filter_;
            result2.disabled_ = this.disabled_;
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
            if (other instanceof LogExclusion) {
                return this.mergeFrom((LogExclusion)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(LogExclusion other) {
            if (other == LogExclusion.getDefaultInstance()) {
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
            if (other.getDisabled()) {
                this.setDisabled(other.getDisabled());
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
            LogExclusion parsedMessage = null;
            try {
                parsedMessage = (LogExclusion)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (LogExclusion)e.getUnfinishedMessage();
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
            this.name_ = LogExclusion.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogExclusion.checkByteStringIsUtf8(value);
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
            this.description_ = LogExclusion.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogExclusion.checkByteStringIsUtf8(value);
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
            this.filter_ = LogExclusion.getDefaultInstance().getFilter();
            this.onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            LogExclusion.checkByteStringIsUtf8(value);
            this.filter_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public boolean getDisabled() {
            return this.disabled_;
        }

        public Builder setDisabled(boolean value) {
            this.disabled_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearDisabled() {
            this.disabled_ = false;
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

