/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.SourceInfoOrBuilder;
import com.google.api.SourceInfoProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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

public final class SourceInfo
extends GeneratedMessageV3
implements SourceInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int SOURCE_FILES_FIELD_NUMBER = 1;
    private List<Any> sourceFiles_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final SourceInfo DEFAULT_INSTANCE = new SourceInfo();
    private static final Parser<SourceInfo> PARSER = new AbstractParser<SourceInfo>(){

        @Override
        public SourceInfo parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new SourceInfo(input2, extensionRegistry);
        }
    };

    private SourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private SourceInfo() {
        this.sourceFiles_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SourceInfo(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block10: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block10;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block10;
                        done = true;
                        continue block10;
                    }
                    case 10: 
                }
                if (!(mutable_bitField0_ & true)) {
                    this.sourceFiles_ = new ArrayList<Any>();
                    mutable_bitField0_ |= true;
                }
                this.sourceFiles_.add(input2.readMessage(Any.parser(), extensionRegistry));
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
                this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
    }

    @Override
    public List<Any> getSourceFilesList() {
        return this.sourceFiles_;
    }

    @Override
    public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
        return this.sourceFiles_;
    }

    @Override
    public int getSourceFilesCount() {
        return this.sourceFiles_.size();
    }

    @Override
    public Any getSourceFiles(int index) {
        return this.sourceFiles_.get(index);
    }

    @Override
    public AnyOrBuilder getSourceFilesOrBuilder(int index) {
        return this.sourceFiles_.get(index);
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
        for (int i = 0; i < this.sourceFiles_.size(); ++i) {
            output.writeMessage(1, this.sourceFiles_.get(i));
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
        for (int i = 0; i < this.sourceFiles_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(1, this.sourceFiles_.get(i));
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceInfo)) {
            return super.equals(obj);
        }
        SourceInfo other = (SourceInfo)obj;
        boolean result2 = true;
        result2 = result2 && this.getSourceFilesList().equals(other.getSourceFilesList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + SourceInfo.getDescriptor().hashCode();
        if (this.getSourceFilesCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getSourceFilesList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static SourceInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SourceInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SourceInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SourceInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SourceInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static SourceInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static SourceInfo parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static SourceInfo parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static SourceInfo parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static SourceInfo parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static SourceInfo parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static SourceInfo parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return SourceInfo.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SourceInfo prototype) {
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

    public static SourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SourceInfo> parser() {
        return PARSER;
    }

    public Parser<SourceInfo> getParserForType() {
        return PARSER;
    }

    @Override
    public SourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements SourceInfoOrBuilder {
        private int bitField0_;
        private List<Any> sourceFiles_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> sourceFilesBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
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
                this.getSourceFilesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.sourceFilesBuilder_ == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
            } else {
                this.sourceFilesBuilder_.clear();
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        @Override
        public SourceInfo getDefaultInstanceForType() {
            return SourceInfo.getDefaultInstance();
        }

        @Override
        public SourceInfo build() {
            SourceInfo result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public SourceInfo buildPartial() {
            SourceInfo result2 = new SourceInfo(this);
            int from_bitField0_ = this.bitField0_;
            if (this.sourceFilesBuilder_ == null) {
                if ((this.bitField0_ & 1) == 1) {
                    this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result2.sourceFiles_ = this.sourceFiles_;
            } else {
                result2.sourceFiles_ = this.sourceFilesBuilder_.build();
            }
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
            if (other instanceof SourceInfo) {
                return this.mergeFrom((SourceInfo)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(SourceInfo other) {
            if (other == SourceInfo.getDefaultInstance()) {
                return this;
            }
            if (this.sourceFilesBuilder_ == null) {
                if (!other.sourceFiles_.isEmpty()) {
                    if (this.sourceFiles_.isEmpty()) {
                        this.sourceFiles_ = other.sourceFiles_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureSourceFilesIsMutable();
                        this.sourceFiles_.addAll(other.sourceFiles_);
                    }
                    this.onChanged();
                }
            } else if (!other.sourceFiles_.isEmpty()) {
                if (this.sourceFilesBuilder_.isEmpty()) {
                    this.sourceFilesBuilder_.dispose();
                    this.sourceFilesBuilder_ = null;
                    this.sourceFiles_ = other.sourceFiles_;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.sourceFilesBuilder_ = alwaysUseFieldBuilders ? this.getSourceFilesFieldBuilder() : null;
                } else {
                    this.sourceFilesBuilder_.addAllMessages(other.sourceFiles_);
                }
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
            SourceInfo parsedMessage = null;
            try {
                parsedMessage = (SourceInfo)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (SourceInfo)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensureSourceFilesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.sourceFiles_ = new ArrayList<Any>(this.sourceFiles_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        public List<Any> getSourceFilesList() {
            if (this.sourceFilesBuilder_ == null) {
                return Collections.unmodifiableList(this.sourceFiles_);
            }
            return this.sourceFilesBuilder_.getMessageList();
        }

        @Override
        public int getSourceFilesCount() {
            if (this.sourceFilesBuilder_ == null) {
                return this.sourceFiles_.size();
            }
            return this.sourceFilesBuilder_.getCount();
        }

        @Override
        public Any getSourceFiles(int index) {
            if (this.sourceFilesBuilder_ == null) {
                return this.sourceFiles_.get(index);
            }
            return this.sourceFilesBuilder_.getMessage(index);
        }

        public Builder setSourceFiles(int index, Any value) {
            if (this.sourceFilesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.set(index, value);
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setSourceFiles(int index, Any.Builder builderForValue) {
            if (this.sourceFilesBuilder_ == null) {
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addSourceFiles(Any value) {
            if (this.sourceFilesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.add(value);
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addSourceFiles(int index, Any value) {
            if (this.sourceFilesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.add(index, value);
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addSourceFiles(Any.Builder builderForValue) {
            if (this.sourceFilesBuilder_ == null) {
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addSourceFiles(int index, Any.Builder builderForValue) {
            if (this.sourceFilesBuilder_ == null) {
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllSourceFiles(Iterable<? extends Any> values) {
            if (this.sourceFilesBuilder_ == null) {
                this.ensureSourceFilesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.sourceFiles_);
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearSourceFiles() {
            if (this.sourceFilesBuilder_ == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.clear();
            }
            return this;
        }

        public Builder removeSourceFiles(int index) {
            if (this.sourceFilesBuilder_ == null) {
                this.ensureSourceFilesIsMutable();
                this.sourceFiles_.remove(index);
                this.onChanged();
            } else {
                this.sourceFilesBuilder_.remove(index);
            }
            return this;
        }

        public Any.Builder getSourceFilesBuilder(int index) {
            return this.getSourceFilesFieldBuilder().getBuilder(index);
        }

        @Override
        public AnyOrBuilder getSourceFilesOrBuilder(int index) {
            if (this.sourceFilesBuilder_ == null) {
                return this.sourceFiles_.get(index);
            }
            return this.sourceFilesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
            if (this.sourceFilesBuilder_ != null) {
                return this.sourceFilesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sourceFiles_);
        }

        public Any.Builder addSourceFilesBuilder() {
            return this.getSourceFilesFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addSourceFilesBuilder(int index) {
            return this.getSourceFilesFieldBuilder().addBuilder(index, Any.getDefaultInstance());
        }

        public List<Any.Builder> getSourceFilesBuilderList() {
            return this.getSourceFilesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getSourceFilesFieldBuilder() {
            if (this.sourceFilesBuilder_ == null) {
                this.sourceFilesBuilder_ = new RepeatedFieldBuilderV3(this.sourceFiles_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                this.sourceFiles_ = null;
            }
            return this.sourceFilesBuilder_;
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

