/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListLogEntriesResponseOrBuilder;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOrBuilder;
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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListLogEntriesResponse
extends GeneratedMessageV3
implements ListLogEntriesResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int ENTRIES_FIELD_NUMBER = 1;
    private List<LogEntry> entries_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListLogEntriesResponse DEFAULT_INSTANCE = new ListLogEntriesResponse();
    private static final Parser<ListLogEntriesResponse> PARSER = new AbstractParser<ListLogEntriesResponse>(){

        @Override
        public ListLogEntriesResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListLogEntriesResponse(input2, extensionRegistry);
        }
    };

    private ListLogEntriesResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListLogEntriesResponse() {
        this.entries_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLogEntriesResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    case 10: {
                        if (!(mutable_bitField0_ & true)) {
                            this.entries_ = new ArrayList<LogEntry>();
                            mutable_bitField0_ |= true;
                        }
                        this.entries_.add(input2.readMessage(LogEntry.parser(), extensionRegistry));
                        continue block11;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.nextPageToken_ = s2;
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
                this.entries_ = Collections.unmodifiableList(this.entries_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_logging_v2_ListLogEntriesResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogEntriesResponse.class, Builder.class);
    }

    @Override
    public List<LogEntry> getEntriesList() {
        return this.entries_;
    }

    @Override
    public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList() {
        return this.entries_;
    }

    @Override
    public int getEntriesCount() {
        return this.entries_.size();
    }

    @Override
    public LogEntry getEntries(int index) {
        return this.entries_.get(index);
    }

    @Override
    public LogEntryOrBuilder getEntriesOrBuilder(int index) {
        return this.entries_.get(index);
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
        for (int i = 0; i < this.entries_.size(); ++i) {
            output.writeMessage(1, this.entries_.get(i));
        }
        if (!this.getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.nextPageToken_);
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
        for (int i = 0; i < this.entries_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.entries_.get(i));
        }
        if (!this.getNextPageTokenBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListLogEntriesResponse)) {
            return super.equals(obj);
        }
        ListLogEntriesResponse other = (ListLogEntriesResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getEntriesList().equals(other.getEntriesList());
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
        hash = 19 * hash + ListLogEntriesResponse.getDescriptor().hashCode();
        if (this.getEntriesCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getEntriesList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListLogEntriesResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListLogEntriesResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListLogEntriesResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogEntriesResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogEntriesResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListLogEntriesResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListLogEntriesResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListLogEntriesResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListLogEntriesResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLogEntriesResponse prototype) {
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

    public static ListLogEntriesResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLogEntriesResponse> parser() {
        return PARSER;
    }

    public Parser<ListLogEntriesResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListLogEntriesResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListLogEntriesResponseOrBuilder {
        private int bitField0_;
        private List<LogEntry> entries_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogEntry, LogEntry.Builder, LogEntryOrBuilder> entriesBuilder_;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLogEntriesResponse.class, Builder.class);
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
                this.getEntriesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.entriesBuilder_ == null) {
                this.entries_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.entriesBuilder_.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_ListLogEntriesResponse_descriptor;
        }

        @Override
        public ListLogEntriesResponse getDefaultInstanceForType() {
            return ListLogEntriesResponse.getDefaultInstance();
        }

        @Override
        public ListLogEntriesResponse build() {
            ListLogEntriesResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListLogEntriesResponse buildPartial() {
            ListLogEntriesResponse result2 = new ListLogEntriesResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.entriesBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.entries_ = Collections.unmodifiableList(this.entries_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.entries_ = this.entries_;
            } else {
                result2.entries_ = this.entriesBuilder_.build();
            }
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
            if (other instanceof ListLogEntriesResponse) {
                return this.mergeFrom((ListLogEntriesResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListLogEntriesResponse other) {
            if (other == ListLogEntriesResponse.getDefaultInstance()) {
                return this;
            }
            if (this.entriesBuilder_ == null) {
                if (!other.entries_.isEmpty()) {
                    if (this.entries_.isEmpty()) {
                        this.entries_ = other.entries_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureEntriesIsMutable();
                        this.entries_.addAll(other.entries_);
                    }
                    this.onChanged();
                }
            } else if (!other.entries_.isEmpty()) {
                if (this.entriesBuilder_.isEmpty()) {
                    this.entriesBuilder_.dispose();
                    this.entriesBuilder_ = null;
                    this.entries_ = other.entries_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.entriesBuilder_ = alwaysUseFieldBuilders ? this.getEntriesFieldBuilder() : null;
                } else {
                    this.entriesBuilder_.addAllMessages(other.entries_);
                }
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
            ListLogEntriesResponse parsedMessage = null;
            try {
                parsedMessage = (ListLogEntriesResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListLogEntriesResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureEntriesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.entries_ = new ArrayList<LogEntry>(this.entries_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<LogEntry> getEntriesList() {
            if (this.entriesBuilder_ == null) {
                return Collections.unmodifiableList(this.entries_);
            }
            return this.entriesBuilder_.getMessageList();
        }

        @Override
        public int getEntriesCount() {
            if (this.entriesBuilder_ == null) {
                return this.entries_.size();
            }
            return this.entriesBuilder_.getCount();
        }

        @Override
        public LogEntry getEntries(int index) {
            if (this.entriesBuilder_ == null) {
                return this.entries_.get(index);
            }
            return this.entriesBuilder_.getMessage(index);
        }

        public Builder setEntries(int index, LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.set(index, value);
                this.onChanged();
            } else {
                this.entriesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setEntries(int index, LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addEntries(LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.add(value);
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addEntries(int index, LogEntry value) {
            if (this.entriesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEntriesIsMutable();
                this.entries_.add(index, value);
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addEntries(LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addEntries(int index, LogEntry.Builder builderForValue) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.entriesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllEntries(Iterable<? extends LogEntry> values) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.entries_);
                this.onChanged();
            } else {
                this.entriesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearEntries() {
            if (this.entriesBuilder_ == null) {
                this.entries_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.entriesBuilder_.clear();
            }
            return this;
        }

        public Builder removeEntries(int index) {
            if (this.entriesBuilder_ == null) {
                this.ensureEntriesIsMutable();
                this.entries_.remove(index);
                this.onChanged();
            } else {
                this.entriesBuilder_.remove(index);
            }
            return this;
        }

        public LogEntry.Builder getEntriesBuilder(int index) {
            return this.getEntriesFieldBuilder().getBuilder(index);
        }

        @Override
        public LogEntryOrBuilder getEntriesOrBuilder(int index) {
            if (this.entriesBuilder_ == null) {
                return this.entries_.get(index);
            }
            return this.entriesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList() {
            if (this.entriesBuilder_ != null) {
                return this.entriesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entries_);
        }

        public LogEntry.Builder addEntriesBuilder() {
            return this.getEntriesFieldBuilder().addBuilder(LogEntry.getDefaultInstance());
        }

        public LogEntry.Builder addEntriesBuilder(int index) {
            return this.getEntriesFieldBuilder().addBuilder(index, LogEntry.getDefaultInstance());
        }

        public List<LogEntry.Builder> getEntriesBuilderList() {
            return this.getEntriesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogEntry, LogEntry.Builder, LogEntryOrBuilder> getEntriesFieldBuilder() {
            if (this.entriesBuilder_ == null) {
                this.entriesBuilder_ = new RepeatedFieldBuilderV3(this.entries_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.entries_ = null;
            }
            return this.entriesBuilder_;
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
            this.nextPageToken_ = ListLogEntriesResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListLogEntriesResponse.checkByteStringIsUtf8(value);
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

