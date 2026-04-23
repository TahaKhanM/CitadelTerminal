/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListLogEntriesRequestOrBuilder;
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

public final class ListLogEntriesRequest
extends GeneratedMessageV3
implements ListLogEntriesRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int PROJECT_IDS_FIELD_NUMBER = 1;
    private LazyStringList projectIds_;
    public static final int RESOURCE_NAMES_FIELD_NUMBER = 8;
    private LazyStringList resourceNames_;
    public static final int FILTER_FIELD_NUMBER = 2;
    private volatile Object filter_;
    public static final int ORDER_BY_FIELD_NUMBER = 3;
    private volatile Object orderBy_;
    public static final int PAGE_SIZE_FIELD_NUMBER = 4;
    private int pageSize_;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 5;
    private volatile Object pageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListLogEntriesRequest DEFAULT_INSTANCE = new ListLogEntriesRequest();
    private static final Parser<ListLogEntriesRequest> PARSER = new AbstractParser<ListLogEntriesRequest>(){

        @Override
        public ListLogEntriesRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListLogEntriesRequest(input2, extensionRegistry);
        }
    };

    private ListLogEntriesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListLogEntriesRequest() {
        this.projectIds_ = LazyStringArrayList.EMPTY;
        this.resourceNames_ = LazyStringArrayList.EMPTY;
        this.filter_ = "";
        this.orderBy_ = "";
        this.pageSize_ = 0;
        this.pageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLogEntriesRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block15: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block15;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 1) != 1) {
                            this.projectIds_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 1;
                        }
                        this.projectIds_.add(s2);
                        continue block15;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.filter_ = s2;
                        continue block15;
                    }
                    case 26: {
                        String s2 = input2.readStringRequireUtf8();
                        this.orderBy_ = s2;
                        continue block15;
                    }
                    case 32: {
                        this.pageSize_ = input2.readInt32();
                        continue block15;
                    }
                    case 42: {
                        String s2 = input2.readStringRequireUtf8();
                        this.pageToken_ = s2;
                        continue block15;
                    }
                    case 66: {
                        String s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 2) != 2) {
                            this.resourceNames_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 2;
                        }
                        this.resourceNames_.add(s2);
                        continue block15;
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
                this.projectIds_ = this.projectIds_.getUnmodifiableView();
            }
            if ((mutable_bitField0_ & 2) == 2) {
                this.resourceNames_ = this.resourceNames_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_logging_v2_ListLogEntriesRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogEntriesRequest.class, Builder.class);
    }

    @Deprecated
    public ProtocolStringList getProjectIdsList() {
        return this.projectIds_;
    }

    @Override
    @Deprecated
    public int getProjectIdsCount() {
        return this.projectIds_.size();
    }

    @Override
    @Deprecated
    public String getProjectIds(int index) {
        return (String)this.projectIds_.get(index);
    }

    @Override
    @Deprecated
    public ByteString getProjectIdsBytes(int index) {
        return this.projectIds_.getByteString(index);
    }

    public ProtocolStringList getResourceNamesList() {
        return this.resourceNames_;
    }

    @Override
    public int getResourceNamesCount() {
        return this.resourceNames_.size();
    }

    @Override
    public String getResourceNames(int index) {
        return (String)this.resourceNames_.get(index);
    }

    @Override
    public ByteString getResourceNamesBytes(int index) {
        return this.resourceNames_.getByteString(index);
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
    public String getOrderBy() {
        Object ref = this.orderBy_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.orderBy_ = s2;
        return s2;
    }

    @Override
    public ByteString getOrderByBytes() {
        Object ref = this.orderBy_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.orderBy_ = b;
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
        int i;
        for (i = 0; i < this.projectIds_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 1, this.projectIds_.getRaw(i));
        }
        if (!this.getFilterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.filter_);
        }
        if (!this.getOrderByBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.orderBy_);
        }
        if (this.pageSize_ != 0) {
            output.writeInt32(4, this.pageSize_);
        }
        if (!this.getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.pageToken_);
        }
        for (i = 0; i < this.resourceNames_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 8, this.resourceNames_.getRaw(i));
        }
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int i;
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        int dataSize = 0;
        for (i = 0; i < this.projectIds_.size(); ++i) {
            dataSize += ListLogEntriesRequest.computeStringSizeNoTag(this.projectIds_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getProjectIdsList().size();
        if (!this.getFilterBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.filter_);
        }
        if (!this.getOrderByBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.orderBy_);
        }
        if (this.pageSize_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(4, this.pageSize_);
        }
        if (!this.getPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.pageToken_);
        }
        dataSize = 0;
        for (i = 0; i < this.resourceNames_.size(); ++i) {
            dataSize += ListLogEntriesRequest.computeStringSizeNoTag(this.resourceNames_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getResourceNamesList().size();
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListLogEntriesRequest)) {
            return super.equals(obj);
        }
        ListLogEntriesRequest other = (ListLogEntriesRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getProjectIdsList().equals(other.getProjectIdsList());
        result2 = result2 && this.getResourceNamesList().equals(other.getResourceNamesList());
        result2 = result2 && this.getFilter().equals(other.getFilter());
        result2 = result2 && this.getOrderBy().equals(other.getOrderBy());
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
        hash = 19 * hash + ListLogEntriesRequest.getDescriptor().hashCode();
        if (this.getProjectIdsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getProjectIdsList().hashCode();
        }
        if (this.getResourceNamesCount() > 0) {
            hash = 37 * hash + 8;
            hash = 53 * hash + this.getResourceNamesList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getFilter().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getOrderBy().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getPageSize();
        hash = 37 * hash + 5;
        hash = 53 * hash + this.getPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListLogEntriesRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogEntriesRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogEntriesRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListLogEntriesRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogEntriesRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogEntriesRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListLogEntriesRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLogEntriesRequest prototype) {
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

    public static ListLogEntriesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLogEntriesRequest> parser() {
        return PARSER;
    }

    public Parser<ListLogEntriesRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public ListLogEntriesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListLogEntriesRequestOrBuilder {
        private int bitField0_;
        private LazyStringList projectIds_ = LazyStringArrayList.EMPTY;
        private LazyStringList resourceNames_ = LazyStringArrayList.EMPTY;
        private Object filter_ = "";
        private Object orderBy_ = "";
        private int pageSize_;
        private Object pageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogEntriesRequest.class, Builder.class);
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
            this.projectIds_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.resourceNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.filter_ = "";
            this.orderBy_ = "";
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesRequest_descriptor;
        }

        @Override
        public ListLogEntriesRequest getDefaultInstanceForType() {
            return ListLogEntriesRequest.getDefaultInstance();
        }

        @Override
        public ListLogEntriesRequest build() {
            ListLogEntriesRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListLogEntriesRequest buildPartial() {
            ListLogEntriesRequest result2 = new ListLogEntriesRequest(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((this.bitField0_ & 1) == 1) {
                this.projectIds_ = this.projectIds_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFE;
            }
            result2.projectIds_ = this.projectIds_;
            if ((this.bitField0_ & 2) == 2) {
                this.resourceNames_ = this.resourceNames_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFD;
            }
            result2.resourceNames_ = this.resourceNames_;
            result2.filter_ = this.filter_;
            result2.orderBy_ = this.orderBy_;
            result2.pageSize_ = this.pageSize_;
            result2.pageToken_ = this.pageToken_;
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
            if (other instanceof ListLogEntriesRequest) {
                return this.mergeFrom((ListLogEntriesRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListLogEntriesRequest other) {
            if (other == ListLogEntriesRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.projectIds_.isEmpty()) {
                if (this.projectIds_.isEmpty()) {
                    this.projectIds_ = other.projectIds_;
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.ensureProjectIdsIsMutable();
                    this.projectIds_.addAll(other.projectIds_);
                }
                this.onChanged();
            }
            if (!other.resourceNames_.isEmpty()) {
                if (this.resourceNames_.isEmpty()) {
                    this.resourceNames_ = other.resourceNames_;
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.ensureResourceNamesIsMutable();
                    this.resourceNames_.addAll(other.resourceNames_);
                }
                this.onChanged();
            }
            if (!other.getFilter().isEmpty()) {
                this.filter_ = other.filter_;
                this.onChanged();
            }
            if (!other.getOrderBy().isEmpty()) {
                this.orderBy_ = other.orderBy_;
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
            ListLogEntriesRequest parsedMessage = null;
            try {
                parsedMessage = (ListLogEntriesRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListLogEntriesRequest)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureProjectIdsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.projectIds_ = new LazyStringArrayList(this.projectIds_);
                this.bitField0_ |= 1;
            }
        }

        @Deprecated
        public ProtocolStringList getProjectIdsList() {
            return this.projectIds_.getUnmodifiableView();
        }

        @Override
        @Deprecated
        public int getProjectIdsCount() {
            return this.projectIds_.size();
        }

        @Override
        @Deprecated
        public String getProjectIds(int index) {
            return (String)this.projectIds_.get(index);
        }

        @Override
        @Deprecated
        public ByteString getProjectIdsBytes(int index) {
            return this.projectIds_.getByteString(index);
        }

        @Deprecated
        public Builder setProjectIds(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureProjectIdsIsMutable();
            this.projectIds_.set(index, value);
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder addProjectIds(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureProjectIdsIsMutable();
            this.projectIds_.add(value);
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllProjectIds(Iterable<String> values) {
            this.ensureProjectIdsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.projectIds_);
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder clearProjectIds() {
            this.projectIds_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }

        @Deprecated
        public Builder addProjectIdsBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesRequest.checkByteStringIsUtf8(value);
            this.ensureProjectIdsIsMutable();
            this.projectIds_.add(value);
            this.onChanged();
            return this;
        }

        private void ensureResourceNamesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.resourceNames_ = new LazyStringArrayList(this.resourceNames_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getResourceNamesList() {
            return this.resourceNames_.getUnmodifiableView();
        }

        @Override
        public int getResourceNamesCount() {
            return this.resourceNames_.size();
        }

        @Override
        public String getResourceNames(int index) {
            return (String)this.resourceNames_.get(index);
        }

        @Override
        public ByteString getResourceNamesBytes(int index) {
            return this.resourceNames_.getByteString(index);
        }

        public Builder setResourceNames(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureResourceNamesIsMutable();
            this.resourceNames_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addResourceNames(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureResourceNamesIsMutable();
            this.resourceNames_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllResourceNames(Iterable<String> values) {
            this.ensureResourceNamesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.resourceNames_);
            this.onChanged();
            return this;
        }

        public Builder clearResourceNames() {
            this.resourceNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            this.onChanged();
            return this;
        }

        public Builder addResourceNamesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesRequest.checkByteStringIsUtf8(value);
            this.ensureResourceNamesIsMutable();
            this.resourceNames_.add(value);
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
            this.filter_ = ListLogEntriesRequest.getDefaultInstance().getFilter();
            this.onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesRequest.checkByteStringIsUtf8(value);
            this.filter_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getOrderBy() {
            Object ref = this.orderBy_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.orderBy_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getOrderByBytes() {
            Object ref = this.orderBy_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.orderBy_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setOrderBy(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.orderBy_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearOrderBy() {
            this.orderBy_ = ListLogEntriesRequest.getDefaultInstance().getOrderBy();
            this.onChanged();
            return this;
        }

        public Builder setOrderByBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesRequest.checkByteStringIsUtf8(value);
            this.orderBy_ = value;
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
            this.pageToken_ = ListLogEntriesRequest.getDefaultInstance().getPageToken();
            this.onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesRequest.checkByteStringIsUtf8(value);
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

