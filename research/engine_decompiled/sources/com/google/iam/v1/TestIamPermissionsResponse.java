/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.IamPolicyProto;
import com.google.iam.v1.TestIamPermissionsResponseOrBuilder;
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

public final class TestIamPermissionsResponse
extends GeneratedMessageV3
implements TestIamPermissionsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int PERMISSIONS_FIELD_NUMBER = 1;
    private LazyStringList permissions_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final TestIamPermissionsResponse DEFAULT_INSTANCE = new TestIamPermissionsResponse();
    private static final Parser<TestIamPermissionsResponse> PARSER = new AbstractParser<TestIamPermissionsResponse>(){

        @Override
        public TestIamPermissionsResponse parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new TestIamPermissionsResponse(input2, extensionRegistry);
        }
    };

    private TestIamPermissionsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private TestIamPermissionsResponse() {
        this.permissions_ = LazyStringArrayList.EMPTY;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private TestIamPermissionsResponse(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                String s2 = input2.readStringRequireUtf8();
                if (!(mutable_bitField0_ & true)) {
                    this.permissions_ = new LazyStringArrayList();
                    mutable_bitField0_ |= true;
                }
                this.permissions_.add(s2);
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
                this.permissions_ = this.permissions_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(TestIamPermissionsResponse.class, Builder.class);
    }

    public ProtocolStringList getPermissionsList() {
        return this.permissions_;
    }

    @Override
    public int getPermissionsCount() {
        return this.permissions_.size();
    }

    @Override
    public String getPermissions(int index) {
        return (String)this.permissions_.get(index);
    }

    @Override
    public ByteString getPermissionsBytes(int index) {
        return this.permissions_.getByteString(index);
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
        for (int i = 0; i < this.permissions_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 1, this.permissions_.getRaw(i));
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
        int dataSize = 0;
        for (int i = 0; i < this.permissions_.size(); ++i) {
            dataSize += TestIamPermissionsResponse.computeStringSizeNoTag(this.permissions_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getPermissionsList().size();
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestIamPermissionsResponse)) {
            return super.equals(obj);
        }
        TestIamPermissionsResponse other = (TestIamPermissionsResponse)obj;
        boolean result2 = true;
        result2 = result2 && this.getPermissionsList().equals(other.getPermissionsList());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + TestIamPermissionsResponse.getDescriptor().hashCode();
        if (this.getPermissionsCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getPermissionsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static TestIamPermissionsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsResponse parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsResponse parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TestIamPermissionsResponse parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsResponse parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TestIamPermissionsResponse parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsResponse parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return TestIamPermissionsResponse.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TestIamPermissionsResponse prototype) {
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

    public static TestIamPermissionsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TestIamPermissionsResponse> parser() {
        return PARSER;
    }

    public Parser<TestIamPermissionsResponse> getParserForType() {
        return PARSER;
    }

    @Override
    public TestIamPermissionsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements TestIamPermissionsResponseOrBuilder {
        private int bitField0_;
        private LazyStringList permissions_ = LazyStringArrayList.EMPTY;

        public static final Descriptors.Descriptor getDescriptor() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(TestIamPermissionsResponse.class, Builder.class);
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
            this.permissions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor;
        }

        @Override
        public TestIamPermissionsResponse getDefaultInstanceForType() {
            return TestIamPermissionsResponse.getDefaultInstance();
        }

        @Override
        public TestIamPermissionsResponse build() {
            TestIamPermissionsResponse result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public TestIamPermissionsResponse buildPartial() {
            TestIamPermissionsResponse result2 = new TestIamPermissionsResponse(this);
            int from_bitField0_ = this.bitField0_;
            if ((this.bitField0_ & 1) == 1) {
                this.permissions_ = this.permissions_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFE;
            }
            result2.permissions_ = this.permissions_;
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
            if (other instanceof TestIamPermissionsResponse) {
                return this.mergeFrom((TestIamPermissionsResponse)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(TestIamPermissionsResponse other) {
            if (other == TestIamPermissionsResponse.getDefaultInstance()) {
                return this;
            }
            if (!other.permissions_.isEmpty()) {
                if (this.permissions_.isEmpty()) {
                    this.permissions_ = other.permissions_;
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.ensurePermissionsIsMutable();
                    this.permissions_.addAll(other.permissions_);
                }
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
            TestIamPermissionsResponse parsedMessage = null;
            try {
                parsedMessage = (TestIamPermissionsResponse)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (TestIamPermissionsResponse)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private void ensurePermissionsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.permissions_ = new LazyStringArrayList(this.permissions_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getPermissionsList() {
            return this.permissions_.getUnmodifiableView();
        }

        @Override
        public int getPermissionsCount() {
            return this.permissions_.size();
        }

        @Override
        public String getPermissions(int index) {
            return (String)this.permissions_.get(index);
        }

        @Override
        public ByteString getPermissionsBytes(int index) {
            return this.permissions_.getByteString(index);
        }

        public Builder setPermissions(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensurePermissionsIsMutable();
            this.permissions_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addPermissions(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensurePermissionsIsMutable();
            this.permissions_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllPermissions(Iterable<String> values) {
            this.ensurePermissionsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.permissions_);
            this.onChanged();
            return this;
        }

        public Builder clearPermissions() {
            this.permissions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }

        public Builder addPermissionsBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            TestIamPermissionsResponse.checkByteStringIsUtf8(value);
            this.ensurePermissionsIsMutable();
            this.permissions_.add(value);
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

