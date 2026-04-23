/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListLogsRequestOrBuilder;
import com.google.logging.v2.LoggingProto;
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

public final class ListLogsRequest
extends GeneratedMessageV3
implements ListLogsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PARENT_FIELD_NUMBER = 1;
    private volatile Object parent_;
    public static final int PAGE_SIZE_FIELD_NUMBER = 2;
    private int pageSize_;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
    private volatile Object pageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListLogsRequest DEFAULT_INSTANCE = new ListLogsRequest();
    private static final Parser<ListLogsRequest> PARSER = new AbstractParser<ListLogsRequest>(){

        @Override
        public ListLogsRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListLogsRequest(input2, extensionRegistry);
        }
    };

    private ListLogsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListLogsRequest() {
        this.parent_ = "";
        this.pageSize_ = 0;
        this.pageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLogsRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.parent_ = s2;
                        continue block12;
                    }
                    case 16: {
                        this.pageSize_ = input2.readInt32();
                        continue block12;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.pageToken_ = s2;
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
        return LoggingProto.internal_static_google_logging_v2_ListLogsRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_ListLogsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogsRequest.class, Builder.class);
    }

    @Override
    public String getParent() {
        Object ref = this.parent_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.parent_ = s2;
        return s2;
    }

    @Override
    public ByteString getParentBytes() {
        Object ref = this.parent_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.parent_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override
    public String getPageToken() {
        Object ref = this.pageToken_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.pageToken_ = s2;
        return s2;
    }

    @Override
    public ByteString getPageTokenBytes() {
        Object ref = this.pageToken_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.pageToken_ = b;
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
        if (!this.getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.parent_);
        }
        if (this.pageSize_ != 0) {
            output.writeInt32(2, this.pageSize_);
        }
        if (!this.getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.pageToken_);
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
        if (!this.getParentBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.parent_);
        }
        if (this.pageSize_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(2, this.pageSize_);
        }
        if (!this.getPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.pageToken_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListLogsRequest)) {
            return super.equals(obj);
        }
        ListLogsRequest other = (ListLogsRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getParent().equals(other.getParent());
        result2 = result2 && this.getPageSize() == other.getPageSize();
        result2 = result2 && this.getPageToken().equals(other.getPageToken());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ListLogsRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getParent().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getPageSize();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListLogsRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogsRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogsRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListLogsRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogsRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogsRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListLogsRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLogsRequest prototype) {
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

    public static ListLogsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLogsRequest> parser() {
        return PARSER;
    }

    public Parser<ListLogsRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public ListLogsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListLogsRequestOrBuilder {
        private Object parent_ = "";
        private int pageSize_;
        private Object pageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogsRequest.class, Builder.class);
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
            this.parent_ = "";
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsRequest_descriptor;
        }

        @Override
        public ListLogsRequest getDefaultInstanceForType() {
            return ListLogsRequest.getDefaultInstance();
        }

        @Override
        public ListLogsRequest build() {
            ListLogsRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListLogsRequest buildPartial() {
            ListLogsRequest result2 = new ListLogsRequest(this);
            result2.parent_ = this.parent_;
            result2.pageSize_ = this.pageSize_;
            result2.pageToken_ = this.pageToken_;
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
            if (other instanceof ListLogsRequest) {
                return this.mergeFrom((ListLogsRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListLogsRequest other) {
            if (other == ListLogsRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getParent().isEmpty()) {
                this.parent_ = other.parent_;
                this.onChanged();
            }
            if (other.getPageSize() != 0) {
                this.setPageSize(other.getPageSize());
            }
            if (!other.getPageToken().isEmpty()) {
                this.pageToken_ = other.pageToken_;
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
            ListLogsRequest parsedMessage = null;
            try {
                parsedMessage = (ListLogsRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListLogsRequest)e.getUnfinishedMessage();
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
        public String getParent() {
            Object ref = this.parent_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.parent_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getParentBytes() {
            Object ref = this.parent_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.parent_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setParent(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.parent_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearParent() {
            this.parent_ = ListLogsRequest.getDefaultInstance().getParent();
            this.onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogsRequest.checkByteStringIsUtf8(value);
            this.parent_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public int getPageSize() {
            return this.pageSize_;
        }

        public Builder setPageSize(int value) {
            this.pageSize_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPageSize() {
            this.pageSize_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public String getPageToken() {
            Object ref = this.pageToken_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.pageToken_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPageTokenBytes() {
            Object ref = this.pageToken_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.pageToken_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPageToken(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.pageToken_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPageToken() {
            this.pageToken_ = ListLogsRequest.getDefaultInstance().getPageToken();
            this.onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogsRequest.checkByteStringIsUtf8(value);
            this.pageToken_ = value;
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

