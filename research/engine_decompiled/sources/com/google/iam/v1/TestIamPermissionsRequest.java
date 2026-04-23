/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.IamPolicyProto;
import com.google.iam.v1.TestIamPermissionsRequestOrBuilder;
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

public final class TestIamPermissionsRequest
extends GeneratedMessageV3
implements TestIamPermissionsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int RESOURCE_FIELD_NUMBER = 1;
    private volatile Object resource_;
    public static final int PERMISSIONS_FIELD_NUMBER = 2;
    private LazyStringList permissions_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final TestIamPermissionsRequest DEFAULT_INSTANCE = new TestIamPermissionsRequest();
    private static final Parser<TestIamPermissionsRequest> PARSER = new AbstractParser<TestIamPermissionsRequest>(){

        @Override
        public TestIamPermissionsRequest parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new TestIamPermissionsRequest(input2, extensionRegistry);
        }
    };

    private TestIamPermissionsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private TestIamPermissionsRequest() {
        this.resource_ = "";
        this.permissions_ = LazyStringArrayList.EMPTY;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private TestIamPermissionsRequest(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block11: while (!done) {
                String s2;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block11;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block11;
                        done = true;
                        continue block11;
                    }
                    case 10: {
                        s2 = input2.readStringRequireUtf8();
                        this.resource_ = s2;
                        continue block11;
                    }
                    case 18: 
                }
                s2 = input2.readStringRequireUtf8();
                if ((mutable_bitField0_ & 2) != 2) {
                    this.permissions_ = new LazyStringArrayList();
                    mutable_bitField0_ |= 2;
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
            if ((mutable_bitField0_ & 2) == 2) {
                this.permissions_ = this.permissions_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(TestIamPermissionsRequest.class, Builder.class);
    }

    @Override
    public String getResource() {
        Object ref = this.resource_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.resource_ = s2;
        return s2;
    }

    @Override
    public ByteString getResourceBytes() {
        Object ref = this.resource_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.resource_ = b;
            return b;
        }
        return (ByteString)ref;
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
        if (!this.getResourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.resource_);
        }
        for (int i = 0; i < this.permissions_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 2, this.permissions_.getRaw(i));
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
        if (!this.getResourceBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.resource_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.permissions_.size(); ++i) {
            dataSize += TestIamPermissionsRequest.computeStringSizeNoTag(this.permissions_.getRaw(i));
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
        if (!(obj instanceof TestIamPermissionsRequest)) {
            return super.equals(obj);
        }
        TestIamPermissionsRequest other = (TestIamPermissionsRequest)obj;
        boolean result2 = true;
        result2 = result2 && this.getResource().equals(other.getResource());
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
        hash = 19 * hash + TestIamPermissionsRequest.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getResource().hashCode();
        if (this.getPermissionsCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getPermissionsList().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static TestIamPermissionsRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static TestIamPermissionsRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static TestIamPermissionsRequest parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsRequest parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TestIamPermissionsRequest parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsRequest parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static TestIamPermissionsRequest parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static TestIamPermissionsRequest parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return TestIamPermissionsRequest.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TestIamPermissionsRequest prototype) {
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

    public static TestIamPermissionsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TestIamPermissionsRequest> parser() {
        return PARSER;
    }

    public Parser<TestIamPermissionsRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public TestIamPermissionsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements TestIamPermissionsRequestOrBuilder {
        private int bitField0_;
        private Object resource_ = "";
        private LazyStringList permissions_ = LazyStringArrayList.EMPTY;

        public static final Descriptors.Descriptor getDescriptor() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(TestIamPermissionsRequest.class, Builder.class);
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
            this.resource_ = "";
            this.permissions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return IamPolicyProto.internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor;
        }

        @Override
        public TestIamPermissionsRequest getDefaultInstanceForType() {
            return TestIamPermissionsRequest.getDefaultInstance();
        }

        @Override
        public TestIamPermissionsRequest build() {
            TestIamPermissionsRequest result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public TestIamPermissionsRequest buildPartial() {
            TestIamPermissionsRequest result2 = new TestIamPermissionsRequest(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.resource_ = this.resource_;
            if ((this.bitField0_ & 2) == 2) {
                this.permissions_ = this.permissions_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFFFD;
            }
            result2.permissions_ = this.permissions_;
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
            if (other instanceof TestIamPermissionsRequest) {
                return this.mergeFrom((TestIamPermissionsRequest)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(TestIamPermissionsRequest other) {
            if (other == TestIamPermissionsRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getResource().isEmpty()) {
                this.resource_ = other.resource_;
                this.onChanged();
            }
            if (!other.permissions_.isEmpty()) {
                if (this.permissions_.isEmpty()) {
                    this.permissions_ = other.permissions_;
                    this.bitField0_ &= 0xFFFFFFFD;
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
            TestIamPermissionsRequest parsedMessage = null;
            try {
                parsedMessage = (TestIamPermissionsRequest)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (TestIamPermissionsRequest)e.getUnfinishedMessage();
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
        public String getResource() {
            Object ref = this.resource_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.resource_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getResourceBytes() {
            Object ref = this.resource_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.resource_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setResource(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.resource_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearResource() {
            this.resource_ = TestIamPermissionsRequest.getDefaultInstance().getResource();
            this.onChanged();
            return this;
        }

        public Builder setResourceBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            TestIamPermissionsRequest.checkByteStringIsUtf8(value);
            this.resource_ = value;
            this.onChanged();
            return this;
        }

        private void ensurePermissionsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.permissions_ = new LazyStringArrayList(this.permissions_);
                this.bitField0_ |= 2;
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
            this.bitField0_ &= 0xFFFFFFFD;
            this.onChanged();
            return this;
        }

        public Builder addPermissionsBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            TestIamPermissionsRequest.checkByteStringIsUtf8(value);
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

