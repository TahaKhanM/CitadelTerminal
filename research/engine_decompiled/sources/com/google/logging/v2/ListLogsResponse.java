/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListLogsResponseOrBuilder;
import com.google.logging.v2.LoggingProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ListLogsResponse
extends GeneratedMessageV3
implements ListLogsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int LOG_NAMES_FIELD_NUMBER = 3;
    private LazyStringList logNames_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListLogsResponse DEFAULT_INSTANCE = new ListLogsResponse();
    private static final Parser<ListLogsResponse> PARSER = new AbstractParser<ListLogsResponse>(){

        @Override
        public ListLogsResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListLogsResponse(input2, extensionRegistry);
        }
    };

    private ListLogsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListLogsResponse() {
        this.logNames_ = LazyStringArrayList.EMPTY;
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLogsResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.nextPageToken_ = s2;
                        continue block11;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        if (!(mutable_bitField0_ & true)) {
                            this.logNames_ = new LazyStringArrayList();
                            mutable_bitField0_ |= true;
                        }
                        this.logNames_.add(s2);
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
                this.logNames_ = this.logNames_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_logging_v2_ListLogsResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_ListLogsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogsResponse.class, Builder.class);
    }

    public ProtocolStringList getLogNamesList() {
        return this.logNames_;
    }

    @Override
    public int getLogNamesCount() {
        return this.logNames_.size();
    }

    @Override
    public String getLogNames(int index) {
        return (String)this.logNames_.get(index);
    }

    @Override
    public ByteString getLogNamesBytes(int index) {
        return this.logNames_.getByteString(index);
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
        if (!this.getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.nextPageToken_);
        }
        for (int i = 0; i < this.logNames_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 3, this.logNames_.getRaw(i));
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
        if (!this.getNextPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.logNames_.size(); ++i) {
            dataSize += ListLogsResponse.computeStringSizeNoTag(this.logNames_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getLogNamesList().size();
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListLogsResponse)) {
            return super.equals(obj);
        }
        ListLogsResponse other = (ListLogsResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getLogNamesList().equals(other.getLogNamesList());
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
        hash = 19 * hash + ListLogsResponse.getDescriptor().hashCode();
        if (this.getLogNamesCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getLogNamesList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListLogsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogsResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogsResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogsResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListLogsResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogsResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogsResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListLogsResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLogsResponse prototype) {
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

    public static ListLogsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLogsResponse> parser() {
        return PARSER;
    }

    public Parser<ListLogsResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListLogsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListLogsResponseOrBuilder {
        private int bitField0_;
        private LazyStringList logNames_ = LazyStringArrayList.EMPTY;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogsResponse.class, Builder.class);
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
            this.logNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_ListLogsResponse_descriptor;
        }

        @Override
        public ListLogsResponse getDefaultInstanceForType() {
            return ListLogsResponse.getDefaultInstance();
        }

        @Override
        public ListLogsResponse build() {
            ListLogsResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListLogsResponse buildPartial() {
            ListLogsResponse result2 = new ListLogsResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((this.bitField0_ & 1) == 1) {
                this.logNames_ = this.logNames_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFE;
            }
            result2.logNames_ = this.logNames_;
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
            if (other instanceof ListLogsResponse) {
                return this.mergeFrom((ListLogsResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListLogsResponse other) {
            if (other == ListLogsResponse.getDefaultInstance()) {
                return this;
            }
            if (!other.logNames_.isEmpty()) {
                if (this.logNames_.isEmpty()) {
                    this.logNames_ = other.logNames_;
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.ensureLogNamesIsMutable();
                    this.logNames_.addAll(other.logNames_);
                }
                this.onChanged();
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
            ListLogsResponse parsedMessage = null;
            try {
                parsedMessage = (ListLogsResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListLogsResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureLogNamesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.logNames_ = new LazyStringArrayList(this.logNames_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getLogNamesList() {
            return this.logNames_.getUnmodifiableView();
        }

        @Override
        public int getLogNamesCount() {
            return this.logNames_.size();
        }

        @Override
        public String getLogNames(int index) {
            return (String)this.logNames_.get(index);
        }

        @Override
        public ByteString getLogNamesBytes(int index) {
            return this.logNames_.getByteString(index);
        }

        public Builder setLogNames(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureLogNamesIsMutable();
            this.logNames_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addLogNames(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureLogNamesIsMutable();
            this.logNames_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllLogNames(Iterable<String> values) {
            this.ensureLogNamesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.logNames_);
            this.onChanged();
            return this;
        }

        public Builder clearLogNames() {
            this.logNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }

        public Builder addLogNamesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogsResponse.checkByteStringIsUtf8(value);
            this.ensureLogNamesIsMutable();
            this.logNames_.add(value);
            this.onChanged();
            return this;
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
            this.nextPageToken_ = ListLogsResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogsResponse.checkByteStringIsUtf8(value);
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

