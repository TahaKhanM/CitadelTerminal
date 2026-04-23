/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListSinksResponseOrBuilder;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.LogSinkOrBuilder;
import com.google.logging.v2.LoggingConfigProto;
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

public final class ListSinksResponse
extends GeneratedMessageV3
implements ListSinksResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SINKS_FIELD_NUMBER = 1;
    private List<LogSink> sinks_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListSinksResponse DEFAULT_INSTANCE = new ListSinksResponse();
    private static final Parser<ListSinksResponse> PARSER = new AbstractParser<ListSinksResponse>(){

        @Override
        public ListSinksResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListSinksResponse(input2, extensionRegistry);
        }
    };

    private ListSinksResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListSinksResponse() {
        this.sinks_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListSinksResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.sinks_ = new ArrayList<LogSink>();
                            mutable_bitField0_ |= true;
                        }
                        this.sinks_.add(input2.readMessage(LogSink.parser(), extensionRegistry));
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
                this.sinks_ = Collections.unmodifiableList(this.sinks_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingConfigProto.internal_static_google_logging_v2_ListSinksResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_ListSinksResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListSinksResponse.class, Builder.class);
    }

    @Override
    public List<LogSink> getSinksList() {
        return this.sinks_;
    }

    @Override
    public List<? extends LogSinkOrBuilder> getSinksOrBuilderList() {
        return this.sinks_;
    }

    @Override
    public int getSinksCount() {
        return this.sinks_.size();
    }

    @Override
    public LogSink getSinks(int index) {
        return this.sinks_.get(index);
    }

    @Override
    public LogSinkOrBuilder getSinksOrBuilder(int index) {
        return this.sinks_.get(index);
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
        for (int i = 0; i < this.sinks_.size(); ++i) {
            output.writeMessage(1, this.sinks_.get(i));
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
        for (int i = 0; i < this.sinks_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.sinks_.get(i));
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
        if (!(obj instanceof ListSinksResponse)) {
            return super.equals(obj);
        }
        ListSinksResponse other = (ListSinksResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getSinksList().equals(other.getSinksList());
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
        hash = 19 * hash + ListSinksResponse.getDescriptor().hashCode();
        if (this.getSinksCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getSinksList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListSinksResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListSinksResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListSinksResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListSinksResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListSinksResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListSinksResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListSinksResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListSinksResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListSinksResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListSinksResponse prototype) {
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

    public static ListSinksResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListSinksResponse> parser() {
        return PARSER;
    }

    public Parser<ListSinksResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListSinksResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListSinksResponseOrBuilder {
        private int bitField0_;
        private List<LogSink> sinks_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogSink, LogSink.Builder, LogSinkOrBuilder> sinksBuilder_;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListSinksResponse.class, Builder.class);
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
                this.getSinksFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.sinksBuilder_ == null) {
                this.sinks_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.sinksBuilder_.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListSinksResponse_descriptor;
        }

        @Override
        public ListSinksResponse getDefaultInstanceForType() {
            return ListSinksResponse.getDefaultInstance();
        }

        @Override
        public ListSinksResponse build() {
            ListSinksResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListSinksResponse buildPartial() {
            ListSinksResponse result2 = new ListSinksResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.sinksBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.sinks_ = Collections.unmodifiableList(this.sinks_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.sinks_ = this.sinks_;
            } else {
                result2.sinks_ = this.sinksBuilder_.build();
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
            if (other instanceof ListSinksResponse) {
                return this.mergeFrom((ListSinksResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListSinksResponse other) {
            if (other == ListSinksResponse.getDefaultInstance()) {
                return this;
            }
            if (this.sinksBuilder_ == null) {
                if (!other.sinks_.isEmpty()) {
                    if (this.sinks_.isEmpty()) {
                        this.sinks_ = other.sinks_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureSinksIsMutable();
                        this.sinks_.addAll(other.sinks_);
                    }
                    this.onChanged();
                }
            } else if (!other.sinks_.isEmpty()) {
                if (this.sinksBuilder_.isEmpty()) {
                    this.sinksBuilder_.dispose();
                    this.sinksBuilder_ = null;
                    this.sinks_ = other.sinks_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.sinksBuilder_ = alwaysUseFieldBuilders ? this.getSinksFieldBuilder() : null;
                } else {
                    this.sinksBuilder_.addAllMessages(other.sinks_);
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
            ListSinksResponse parsedMessage = null;
            try {
                parsedMessage = (ListSinksResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListSinksResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureSinksIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.sinks_ = new ArrayList<LogSink>(this.sinks_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<LogSink> getSinksList() {
            if (this.sinksBuilder_ == null) {
                return Collections.unmodifiableList(this.sinks_);
            }
            return this.sinksBuilder_.getMessageList();
        }

        @Override
        public int getSinksCount() {
            if (this.sinksBuilder_ == null) {
                return this.sinks_.size();
            }
            return this.sinksBuilder_.getCount();
        }

        @Override
        public LogSink getSinks(int index) {
            if (this.sinksBuilder_ == null) {
                return this.sinks_.get(index);
            }
            return this.sinksBuilder_.getMessage(index);
        }

        public Builder setSinks(int index, LogSink value) {
            if (this.sinksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSinksIsMutable();
                this.sinks_.set(index, value);
                this.onChanged();
            } else {
                this.sinksBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setSinks(int index, LogSink.Builder builderForValue) {
            if (this.sinksBuilder_ == null) {
                this.ensureSinksIsMutable();
                this.sinks_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.sinksBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addSinks(LogSink value) {
            if (this.sinksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSinksIsMutable();
                this.sinks_.add(value);
                this.onChanged();
            } else {
                this.sinksBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addSinks(int index, LogSink value) {
            if (this.sinksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSinksIsMutable();
                this.sinks_.add(index, value);
                this.onChanged();
            } else {
                this.sinksBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addSinks(LogSink.Builder builderForValue) {
            if (this.sinksBuilder_ == null) {
                this.ensureSinksIsMutable();
                this.sinks_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.sinksBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addSinks(int index, LogSink.Builder builderForValue) {
            if (this.sinksBuilder_ == null) {
                this.ensureSinksIsMutable();
                this.sinks_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.sinksBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllSinks(Iterable<? extends LogSink> values) {
            if (this.sinksBuilder_ == null) {
                this.ensureSinksIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.sinks_);
                this.onChanged();
            } else {
                this.sinksBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearSinks() {
            if (this.sinksBuilder_ == null) {
                this.sinks_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.sinksBuilder_.clear();
            }
            return this;
        }

        public Builder removeSinks(int index) {
            if (this.sinksBuilder_ == null) {
                this.ensureSinksIsMutable();
                this.sinks_.remove(index);
                this.onChanged();
            } else {
                this.sinksBuilder_.remove(index);
            }
            return this;
        }

        public LogSink.Builder getSinksBuilder(int index) {
            return this.getSinksFieldBuilder().getBuilder(index);
        }

        @Override
        public LogSinkOrBuilder getSinksOrBuilder(int index) {
            if (this.sinksBuilder_ == null) {
                return this.sinks_.get(index);
            }
            return this.sinksBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogSinkOrBuilder> getSinksOrBuilderList() {
            if (this.sinksBuilder_ != null) {
                return this.sinksBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sinks_);
        }

        public LogSink.Builder addSinksBuilder() {
            return this.getSinksFieldBuilder().addBuilder(LogSink.getDefaultInstance());
        }

        public LogSink.Builder addSinksBuilder(int index) {
            return this.getSinksFieldBuilder().addBuilder(index, LogSink.getDefaultInstance());
        }

        public List<LogSink.Builder> getSinksBuilderList() {
            return this.getSinksFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogSink, LogSink.Builder, LogSinkOrBuilder> getSinksFieldBuilder() {
            if (this.sinksBuilder_ == null) {
                this.sinksBuilder_ = new RepeatedFieldBuilderV3(this.sinks_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.sinks_ = null;
            }
            return this.sinksBuilder_;
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
            this.nextPageToken_ = ListSinksResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListSinksResponse.checkByteStringIsUtf8(value);
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

