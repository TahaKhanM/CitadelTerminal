/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.MonitoredResourceDescriptor;
import com.google.api.MonitoredResourceDescriptorOrBuilder;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponseOrBuilder;
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

public final class ListMonitoredResourceDescriptorsResponse
extends GeneratedMessageV3
implements ListMonitoredResourceDescriptorsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int RESOURCE_DESCRIPTORS_FIELD_NUMBER = 1;
    private List<MonitoredResourceDescriptor> resourceDescriptors_;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private volatile Object nextPageToken_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final ListMonitoredResourceDescriptorsResponse DEFAULT_INSTANCE = new ListMonitoredResourceDescriptorsResponse();
    private static final Parser<ListMonitoredResourceDescriptorsResponse> PARSER = new AbstractParser<ListMonitoredResourceDescriptorsResponse>(){

        @Override
        public ListMonitoredResourceDescriptorsResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ListMonitoredResourceDescriptorsResponse(input2, extensionRegistry);
        }
    };

    private ListMonitoredResourceDescriptorsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ListMonitoredResourceDescriptorsResponse() {
        this.resourceDescriptors_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListMonitoredResourceDescriptorsResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.resourceDescriptors_ = new ArrayList<MonitoredResourceDescriptor>();
                            mutable_bitField0_ |= true;
                        }
                        this.resourceDescriptors_.add(input2.readMessage(MonitoredResourceDescriptor.parser(), extensionRegistry));
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
                this.resourceDescriptors_ = Collections.unmodifiableList(this.resourceDescriptors_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListMonitoredResourceDescriptorsResponse.class, Builder.class);
    }

    @Override
    public List<MonitoredResourceDescriptor> getResourceDescriptorsList() {
        return this.resourceDescriptors_;
    }

    @Override
    public List<? extends MonitoredResourceDescriptorOrBuilder> getResourceDescriptorsOrBuilderList() {
        return this.resourceDescriptors_;
    }

    @Override
    public int getResourceDescriptorsCount() {
        return this.resourceDescriptors_.size();
    }

    @Override
    public MonitoredResourceDescriptor getResourceDescriptors(int index) {
        return this.resourceDescriptors_.get(index);
    }

    @Override
    public MonitoredResourceDescriptorOrBuilder getResourceDescriptorsOrBuilder(int index) {
        return this.resourceDescriptors_.get(index);
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
        for (int i = 0; i < this.resourceDescriptors_.size(); ++i) {
            output.writeMessage(1, this.resourceDescriptors_.get(i));
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
        for (int i = 0; i < this.resourceDescriptors_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.resourceDescriptors_.get(i));
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
        if (!(obj instanceof ListMonitoredResourceDescriptorsResponse)) {
            return super.equals(obj);
        }
        ListMonitoredResourceDescriptorsResponse other = (ListMonitoredResourceDescriptorsResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getResourceDescriptorsList().equals(other.getResourceDescriptorsList());
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
        hash = 19 * hash + ListMonitoredResourceDescriptorsResponse.getDescriptor().hashCode();
        if (this.getResourceDescriptorsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getResourceDescriptorsList().hashCode();
        }
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getNextPageToken().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListMonitoredResourceDescriptorsResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static ListMonitoredResourceDescriptorsResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static ListMonitoredResourceDescriptorsResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return ListMonitoredResourceDescriptorsResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListMonitoredResourceDescriptorsResponse prototype) {
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

    public static ListMonitoredResourceDescriptorsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListMonitoredResourceDescriptorsResponse> parser() {
        return PARSER;
    }

    public Parser<ListMonitoredResourceDescriptorsResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public ListMonitoredResourceDescriptorsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ListMonitoredResourceDescriptorsResponseOrBuilder {
        private int bitField0_;
        private List<MonitoredResourceDescriptor> resourceDescriptors_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> resourceDescriptorsBuilder_;
        private Object nextPageToken_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListMonitoredResourceDescriptorsResponse.class, Builder.class);
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
                this.getResourceDescriptorsFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.resourceDescriptorsBuilder_ == null) {
                this.resourceDescriptors_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.resourceDescriptorsBuilder_.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor;
        }

        @Override
        public ListMonitoredResourceDescriptorsResponse getDefaultInstanceForType() {
            return ListMonitoredResourceDescriptorsResponse.getDefaultInstance();
        }

        @Override
        public ListMonitoredResourceDescriptorsResponse build() {
            ListMonitoredResourceDescriptorsResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public ListMonitoredResourceDescriptorsResponse buildPartial() {
            ListMonitoredResourceDescriptorsResponse result2 = new ListMonitoredResourceDescriptorsResponse(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.resourceDescriptorsBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.resourceDescriptors_ = Collections.unmodifiableList(this.resourceDescriptors_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.resourceDescriptors_ = this.resourceDescriptors_;
            } else {
                result2.resourceDescriptors_ = this.resourceDescriptorsBuilder_.build();
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
            if (other instanceof ListMonitoredResourceDescriptorsResponse) {
                return this.mergeFrom((ListMonitoredResourceDescriptorsResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ListMonitoredResourceDescriptorsResponse other) {
            if (other == ListMonitoredResourceDescriptorsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.resourceDescriptorsBuilder_ == null) {
                if (!other.resourceDescriptors_.isEmpty()) {
                    if (this.resourceDescriptors_.isEmpty()) {
                        this.resourceDescriptors_ = other.resourceDescriptors_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureResourceDescriptorsIsMutable();
                        this.resourceDescriptors_.addAll(other.resourceDescriptors_);
                    }
                    this.onChanged();
                }
            } else if (!other.resourceDescriptors_.isEmpty()) {
                if (this.resourceDescriptorsBuilder_.isEmpty()) {
                    this.resourceDescriptorsBuilder_.dispose();
                    this.resourceDescriptorsBuilder_ = null;
                    this.resourceDescriptors_ = other.resourceDescriptors_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.resourceDescriptorsBuilder_ = alwaysUseFieldBuilders ? this.getResourceDescriptorsFieldBuilder() : null;
                } else {
                    this.resourceDescriptorsBuilder_.addAllMessages(other.resourceDescriptors_);
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
            ListMonitoredResourceDescriptorsResponse parsedMessage = null;
            try {
                parsedMessage = (ListMonitoredResourceDescriptorsResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (ListMonitoredResourceDescriptorsResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureResourceDescriptorsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.resourceDescriptors_ = new ArrayList<MonitoredResourceDescriptor>(this.resourceDescriptors_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<MonitoredResourceDescriptor> getResourceDescriptorsList() {
            if (this.resourceDescriptorsBuilder_ == null) {
                return Collections.unmodifiableList(this.resourceDescriptors_);
            }
            return this.resourceDescriptorsBuilder_.getMessageList();
        }

        @Override
        public int getResourceDescriptorsCount() {
            if (this.resourceDescriptorsBuilder_ == null) {
                return this.resourceDescriptors_.size();
            }
            return this.resourceDescriptorsBuilder_.getCount();
        }

        @Override
        public MonitoredResourceDescriptor getResourceDescriptors(int index) {
            if (this.resourceDescriptorsBuilder_ == null) {
                return this.resourceDescriptors_.get(index);
            }
            return this.resourceDescriptorsBuilder_.getMessage(index);
        }

        public Builder setResourceDescriptors(int index, MonitoredResourceDescriptor value) {
            if (this.resourceDescriptorsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.set(index, value);
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setResourceDescriptors(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addResourceDescriptors(MonitoredResourceDescriptor value) {
            if (this.resourceDescriptorsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.add(value);
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addResourceDescriptors(int index, MonitoredResourceDescriptor value) {
            if (this.resourceDescriptorsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.add(index, value);
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addResourceDescriptors(MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addResourceDescriptors(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllResourceDescriptors(Iterable<? extends MonitoredResourceDescriptor> values) {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.ensureResourceDescriptorsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.resourceDescriptors_);
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearResourceDescriptors() {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.resourceDescriptors_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.clear();
            }
            return this;
        }

        public Builder removeResourceDescriptors(int index) {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.ensureResourceDescriptorsIsMutable();
                this.resourceDescriptors_.remove(index);
                this.onChanged();
            } else {
                this.resourceDescriptorsBuilder_.remove(index);
            }
            return this;
        }

        public MonitoredResourceDescriptor.Builder getResourceDescriptorsBuilder(int index) {
            return this.getResourceDescriptorsFieldBuilder().getBuilder(index);
        }

        @Override
        public MonitoredResourceDescriptorOrBuilder getResourceDescriptorsOrBuilder(int index) {
            if (this.resourceDescriptorsBuilder_ == null) {
                return this.resourceDescriptors_.get(index);
            }
            return this.resourceDescriptorsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends MonitoredResourceDescriptorOrBuilder> getResourceDescriptorsOrBuilderList() {
            if (this.resourceDescriptorsBuilder_ != null) {
                return this.resourceDescriptorsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.resourceDescriptors_);
        }

        public MonitoredResourceDescriptor.Builder addResourceDescriptorsBuilder() {
            return this.getResourceDescriptorsFieldBuilder().addBuilder(MonitoredResourceDescriptor.getDefaultInstance());
        }

        public MonitoredResourceDescriptor.Builder addResourceDescriptorsBuilder(int index) {
            return this.getResourceDescriptorsFieldBuilder().addBuilder(index, MonitoredResourceDescriptor.getDefaultInstance());
        }

        public List<MonitoredResourceDescriptor.Builder> getResourceDescriptorsBuilderList() {
            return this.getResourceDescriptorsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> getResourceDescriptorsFieldBuilder() {
            if (this.resourceDescriptorsBuilder_ == null) {
                this.resourceDescriptorsBuilder_ = new RepeatedFieldBuilderV3(this.resourceDescriptors_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.resourceDescriptors_ = null;
            }
            return this.resourceDescriptorsBuilder_;
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
            this.nextPageToken_ = ListMonitoredResourceDescriptorsResponse.getDefaultInstance().getNextPageToken();
            this.onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ListMonitoredResourceDescriptorsResponse.checkByteStringIsUtf8(value);
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

