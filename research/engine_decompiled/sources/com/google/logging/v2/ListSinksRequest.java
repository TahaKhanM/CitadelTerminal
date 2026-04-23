/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListSinksRequestOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
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

public final class ListSinksRequest
extends GeneratedMessageV3
implements ListSinksRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PARENT_FIELD_NUMBER = 1;
    private volatile Object parent_;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object pageToken_;
    public static final int PAGE_SIZE_FIELD_NUMBER = 3;
    private int pageSize_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListSinksRequest DEFAULT_INSTANCE = new ListSinksRequest();
    private static final Parser<ListSinksRequest> PARSER = new AbstractParser<ListSinksRequest>(){

        @Override
        public ListSinksRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListSinksRequest(input2, extensionRegistry);
        }
    };

    private ListSinksRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListSinksRequest() {
        this.parent_ = "";
        this.pageToken_ = "";
        this.pageSize_ = 0;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListSinksRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.pageToken_ = s2;
                        continue block12;
                    }
                    case 24: {
                        this.pageSize_ = input2.readInt32();
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
        return LoggingConfigProto.internal_static_google_logging_v2_ListSinksRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListSinksRequest.class, Builder.class);
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
    public int getPageSize() {
        return this.pageSize_;
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
        if (!this.getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.pageToken_);
        }
        if (this.pageSize_ != 0) {
            output.writeInt32(3, this.pageSize_);
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
        if (!this.getPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.pageToken_);
        }
        if (this.pageSize_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(3, this.pageSize_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListSinksRequest)) {
            return super.equals(obj);
        }
        ListSinksRequest other = (ListSinksRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getParent().equals(other.getParent());
        result2 = result2 && this.getPageToken().equals(other.getPageToken());
        result2 = result2 && this.getPageSize() == other.getPageSize();
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + ListSinksRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getParent().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getPageToken().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getPageSize();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListSinksRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListSinksRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListSinksRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListSinksRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListSinksRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListSinksRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListSinksRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListSinksRequest prototype) {
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

    public static ListSinksRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListSinksRequest> parser() {
        return PARSER;
    }

    public Parser<ListSinksRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public ListSinksRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListSinksRequestOrBuilder {
        private Object parent_ = "";
        private Object pageToken_ = "";
        private int pageSize_;

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListSinksRequest.class, Builder.class);
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
            this.pageToken_ = "";
            this.pageSize_ = 0;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksRequest_descriptor;
        }

        @Override
        public ListSinksRequest getDefaultInstanceForType() {
            return ListSinksRequest.getDefaultInstance();
        }

        @Override
        public ListSinksRequest build() {
            ListSinksRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListSinksRequest buildPartial() {
            ListSinksRequest result2 = new ListSinksRequest(this);
            result2.parent_ = this.parent_;
            result2.pageToken_ = this.pageToken_;
            result2.pageSize_ = this.pageSize_;
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
            if (other instanceof ListSinksRequest) {
                return this.mergeFrom((ListSinksRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListSinksRequest other) {
            if (other == ListSinksRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getParent().isEmpty()) {
                this.parent_ = other.parent_;
                this.onChanged();
            }
            if (!other.getPageToken().isEmpty()) {
                this.pageToken_ = other.pageToken_;
                this.onChanged();
            }
            if (other.getPageSize() != 0) {
                this.setPageSize(other.getPageSize());
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
            ListSinksRequest parsedMessage = null;
            try {
                parsedMessage = (ListSinksRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListSinksRequest)e.getUnfinishedMessage();
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
            this.parent_ = ListSinksRequest.getDefaultInstance().getParent();
            this.onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListSinksRequest.checkByteStringIsUtf8(value);
            this.parent_ = value;
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
            this.pageToken_ = ListSinksRequest.getDefaultInstance().getPageToken();
            this.onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListSinksRequest.checkByteStringIsUtf8(value);
            this.pageToken_ = value;
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
        public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.setUnknownFieldsProto3(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.mergeUnknownFields(unknownFields);
        }
    }
}

