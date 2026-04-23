/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.ListExclusionsResponseOrBuilder;
import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
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

public final class ListExclusionsResponse
extends GeneratedMessageV3
implements ListExclusionsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int EXCLUSIONS_FIELD_NUMBER = 1;
    private List<LogExclusion> exclusions_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListExclusionsResponse DEFAULT_INSTANCE = new ListExclusionsResponse();
    private static final Parser<ListExclusionsResponse> PARSER = new AbstractParser<ListExclusionsResponse>(){

        @Override
        public ListExclusionsResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListExclusionsResponse(input2, extensionRegistry);
        }
    };

    private ListExclusionsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListExclusionsResponse() {
        this.exclusions_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListExclusionsResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.exclusions_ = new ArrayList<LogExclusion>();
                            mutable_bitField0_ |= true;
                        }
                        this.exclusions_.add(input2.readMessage(LogExclusion.parser(), extensionRegistry));
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
                this.exclusions_ = Collections.unmodifiableList(this.exclusions_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingConfigProto.internal_static_google_logging_v2_ListExclusionsResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingConfigProto.internal_static_google_logging_v2_ListExclusionsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListExclusionsResponse.class, Builder.class);
    }

    @Override
    public List<LogExclusion> getExclusionsList() {
        return this.exclusions_;
    }

    @Override
    public List<? extends LogExclusionOrBuilder> getExclusionsOrBuilderList() {
        return this.exclusions_;
    }

    @Override
    public int getExclusionsCount() {
        return this.exclusions_.size();
    }

    @Override
    public LogExclusion getExclusions(int index) {
        return this.exclusions_.get(index);
    }

    @Override
    public LogExclusionOrBuilder getExclusionsOrBuilder(int index) {
        return this.exclusions_.get(index);
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
        for (int i = 0; i < this.exclusions_.size(); ++i) {
            output.writeMessage(1, this.exclusions_.get(i));
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
        for (int i = 0; i < this.exclusions_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.exclusions_.get(i));
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
        if (!(obj instanceof ListExclusionsResponse)) {
            return super.equals(obj);
        }
        ListExclusionsResponse other = (ListExclusionsResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getExclusionsList().equals(other.getExclusionsList());
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
        hash = 19 * hash + ListExclusionsResponse.getDescriptor().hashCode();
        if (this.getExclusionsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getExclusionsList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListExclusionsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListExclusionsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListExclusionsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListExclusionsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListExclusionsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListExclusionsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListExclusionsResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListExclusionsResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListExclusionsResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListExclusionsResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListExclusionsResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListExclusionsResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListExclusionsResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListExclusionsResponse prototype) {
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

    public static ListExclusionsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListExclusionsResponse> parser() {
        return PARSER;
    }

    public Parser<ListExclusionsResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListExclusionsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListExclusionsResponseOrBuilder {
        private int bitField0_;
        private List<LogExclusion> exclusions_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogExclusion, LogExclusion.Builder, LogExclusionOrBuilder> exclusionsBuilder_;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListExclusionsResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListExclusionsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListExclusionsResponse.class, Builder.class);
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
                this.getExclusionsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.exclusionsBuilder_ == null) {
                this.exclusions_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.exclusionsBuilder_.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingConfigProto.internal_static_google_logging_v2_ListExclusionsResponse_descriptor;
        }

        @Override
        public ListExclusionsResponse getDefaultInstanceForType() {
            return ListExclusionsResponse.getDefaultInstance();
        }

        @Override
        public ListExclusionsResponse build() {
            ListExclusionsResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListExclusionsResponse buildPartial() {
            ListExclusionsResponse result2 = new ListExclusionsResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.exclusionsBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.exclusions_ = Collections.unmodifiableList(this.exclusions_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.exclusions_ = this.exclusions_;
            } else {
                result2.exclusions_ = this.exclusionsBuilder_.build();
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
            if (other instanceof ListExclusionsResponse) {
                return this.mergeFrom((ListExclusionsResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListExclusionsResponse other) {
            if (other == ListExclusionsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.exclusionsBuilder_ == null) {
                if (!other.exclusions_.isEmpty()) {
                    if (this.exclusions_.isEmpty()) {
                        this.exclusions_ = other.exclusions_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureExclusionsIsMutable();
                        this.exclusions_.addAll(other.exclusions_);
                    }
                    this.onChanged();
                }
            } else if (!other.exclusions_.isEmpty()) {
                if (this.exclusionsBuilder_.isEmpty()) {
                    this.exclusionsBuilder_.dispose();
                    this.exclusionsBuilder_ = null;
                    this.exclusions_ = other.exclusions_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.exclusionsBuilder_ = alwaysUseFieldBuilders ? this.getExclusionsFieldBuilder() : null;
                } else {
                    this.exclusionsBuilder_.addAllMessages(other.exclusions_);
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
            ListExclusionsResponse parsedMessage = null;
            try {
                parsedMessage = (ListExclusionsResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListExclusionsResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureExclusionsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.exclusions_ = new ArrayList<LogExclusion>(this.exclusions_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<LogExclusion> getExclusionsList() {
            if (this.exclusionsBuilder_ == null) {
                return Collections.unmodifiableList(this.exclusions_);
            }
            return this.exclusionsBuilder_.getMessageList();
        }

        @Override
        public int getExclusionsCount() {
            if (this.exclusionsBuilder_ == null) {
                return this.exclusions_.size();
            }
            return this.exclusionsBuilder_.getCount();
        }

        @Override
        public LogExclusion getExclusions(int index) {
            if (this.exclusionsBuilder_ == null) {
                return this.exclusions_.get(index);
            }
            return this.exclusionsBuilder_.getMessage(index);
        }

        public Builder setExclusions(int index, LogExclusion value) {
            if (this.exclusionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExclusionsIsMutable();
                this.exclusions_.set(index, value);
                this.onChanged();
            } else {
                this.exclusionsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setExclusions(int index, LogExclusion.Builder builderForValue) {
            if (this.exclusionsBuilder_ == null) {
                this.ensureExclusionsIsMutable();
                this.exclusions_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.exclusionsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addExclusions(LogExclusion value) {
            if (this.exclusionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExclusionsIsMutable();
                this.exclusions_.add(value);
                this.onChanged();
            } else {
                this.exclusionsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addExclusions(int index, LogExclusion value) {
            if (this.exclusionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureExclusionsIsMutable();
                this.exclusions_.add(index, value);
                this.onChanged();
            } else {
                this.exclusionsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addExclusions(LogExclusion.Builder builderForValue) {
            if (this.exclusionsBuilder_ == null) {
                this.ensureExclusionsIsMutable();
                this.exclusions_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.exclusionsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addExclusions(int index, LogExclusion.Builder builderForValue) {
            if (this.exclusionsBuilder_ == null) {
                this.ensureExclusionsIsMutable();
                this.exclusions_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.exclusionsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllExclusions(Iterable<? extends LogExclusion> values) {
            if (this.exclusionsBuilder_ == null) {
                this.ensureExclusionsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.exclusions_);
                this.onChanged();
            } else {
                this.exclusionsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearExclusions() {
            if (this.exclusionsBuilder_ == null) {
                this.exclusions_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.exclusionsBuilder_.clear();
            }
            return this;
        }

        public Builder removeExclusions(int index) {
            if (this.exclusionsBuilder_ == null) {
                this.ensureExclusionsIsMutable();
                this.exclusions_.remove(index);
                this.onChanged();
            } else {
                this.exclusionsBuilder_.remove(index);
            }
            return this;
        }

        public LogExclusion.Builder getExclusionsBuilder(int index) {
            return this.getExclusionsFieldBuilder().getBuilder(index);
        }

        @Override
        public LogExclusionOrBuilder getExclusionsOrBuilder(int index) {
            if (this.exclusionsBuilder_ == null) {
                return this.exclusions_.get(index);
            }
            return this.exclusionsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogExclusionOrBuilder> getExclusionsOrBuilderList() {
            if (this.exclusionsBuilder_ != null) {
                return this.exclusionsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.exclusions_);
        }

        public LogExclusion.Builder addExclusionsBuilder() {
            return this.getExclusionsFieldBuilder().addBuilder(LogExclusion.getDefaultInstance());
        }

        public LogExclusion.Builder addExclusionsBuilder(int index) {
            return this.getExclusionsFieldBuilder().addBuilder(index, LogExclusion.getDefaultInstance());
        }

        public List<LogExclusion.Builder> getExclusionsBuilderList() {
            return this.getExclusionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogExclusion, LogExclusion.Builder, LogExclusionOrBuilder> getExclusionsFieldBuilder() {
            if (this.exclusionsBuilder_ == null) {
                this.exclusionsBuilder_ = new RepeatedFieldBuilderV3(this.exclusions_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.exclusions_ = null;
            }
            return this.exclusionsBuilder_;
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
            this.nextPageToken_ = ListExclusionsResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListExclusionsResponse.checkByteStringIsUtf8(value);
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

