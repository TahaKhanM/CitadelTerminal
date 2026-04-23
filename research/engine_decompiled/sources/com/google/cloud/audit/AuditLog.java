/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.audit;

import com.google.cloud.audit.AuditLogOrBuilder;
import com.google.cloud.audit.AuditLogProto;
import com.google.cloud.audit.AuthenticationInfo;
import com.google.cloud.audit.AuthenticationInfoOrBuilder;
import com.google.cloud.audit.AuthorizationInfo;
import com.google.cloud.audit.AuthorizationInfoOrBuilder;
import com.google.cloud.audit.RequestMetadata;
import com.google.cloud.audit.RequestMetadataOrBuilder;
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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuditLog
extends GeneratedMessageV3
implements AuditLogOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    private volatile Object serviceName_;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    private volatile Object methodName_;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    private volatile Object resourceName_;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    private long numResponseItems_;
    public static final int STATUS_FIELD_NUMBER = 2;
    private Status status_;
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    private AuthenticationInfo authenticationInfo_;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    private List<AuthorizationInfo> authorizationInfo_;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    private RequestMetadata requestMetadata_;
    public static final int REQUEST_FIELD_NUMBER = 16;
    private Struct request_;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    private Struct response_;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    private Any serviceData_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final AuditLog DEFAULT_INSTANCE = new AuditLog();
    private static final Parser<AuditLog> PARSER = new AbstractParser<AuditLog>(){

        @Override
        public AuditLog parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new AuditLog(input2, extensionRegistry);
        }
    };

    private AuditLog(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private AuditLog() {
        this.serviceName_ = "";
        this.methodName_ = "";
        this.resourceName_ = "";
        this.numResponseItems_ = 0L;
        this.authorizationInfo_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuditLog(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block20: while (!done) {
                GeneratedMessageV3.Builder subBuilder;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block20;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block20;
                        done = true;
                        continue block20;
                    }
                    case 18: {
                        subBuilder = null;
                        if (this.status_ != null) {
                            subBuilder = this.status_.toBuilder();
                        }
                        this.status_ = input2.readMessage(Status.parser(), extensionRegistry);
                        if (subBuilder == null) continue block20;
                        ((Status.Builder)subBuilder).mergeFrom(this.status_);
                        this.status_ = ((Status.Builder)subBuilder).buildPartial();
                        continue block20;
                    }
                    case 26: {
                        subBuilder = null;
                        if (this.authenticationInfo_ != null) {
                            subBuilder = this.authenticationInfo_.toBuilder();
                        }
                        this.authenticationInfo_ = input2.readMessage(AuthenticationInfo.parser(), extensionRegistry);
                        if (subBuilder == null) continue block20;
                        ((AuthenticationInfo.Builder)subBuilder).mergeFrom(this.authenticationInfo_);
                        this.authenticationInfo_ = ((AuthenticationInfo.Builder)subBuilder).buildPartial();
                        continue block20;
                    }
                    case 34: {
                        subBuilder = null;
                        if (this.requestMetadata_ != null) {
                            subBuilder = this.requestMetadata_.toBuilder();
                        }
                        this.requestMetadata_ = input2.readMessage(RequestMetadata.parser(), extensionRegistry);
                        if (subBuilder == null) continue block20;
                        ((RequestMetadata.Builder)subBuilder).mergeFrom(this.requestMetadata_);
                        this.requestMetadata_ = ((RequestMetadata.Builder)subBuilder).buildPartial();
                        continue block20;
                    }
                    case 58: {
                        String s2 = input2.readStringRequireUtf8();
                        this.serviceName_ = s2;
                        continue block20;
                    }
                    case 66: {
                        String s2 = input2.readStringRequireUtf8();
                        this.methodName_ = s2;
                        continue block20;
                    }
                    case 74: {
                        if ((mutable_bitField0_ & 0x40) != 64) {
                            this.authorizationInfo_ = new ArrayList<AuthorizationInfo>();
                            mutable_bitField0_ |= 0x40;
                        }
                        this.authorizationInfo_.add(input2.readMessage(AuthorizationInfo.parser(), extensionRegistry));
                        continue block20;
                    }
                    case 90: {
                        String s2 = input2.readStringRequireUtf8();
                        this.resourceName_ = s2;
                        continue block20;
                    }
                    case 96: {
                        this.numResponseItems_ = input2.readInt64();
                        continue block20;
                    }
                    case 122: {
                        subBuilder = null;
                        if (this.serviceData_ != null) {
                            subBuilder = this.serviceData_.toBuilder();
                        }
                        this.serviceData_ = input2.readMessage(Any.parser(), extensionRegistry);
                        if (subBuilder == null) continue block20;
                        ((Any.Builder)subBuilder).mergeFrom(this.serviceData_);
                        this.serviceData_ = ((Any.Builder)subBuilder).buildPartial();
                        continue block20;
                    }
                    case 130: {
                        subBuilder = null;
                        if (this.request_ != null) {
                            subBuilder = this.request_.toBuilder();
                        }
                        this.request_ = input2.readMessage(Struct.parser(), extensionRegistry);
                        if (subBuilder == null) continue block20;
                        ((Struct.Builder)subBuilder).mergeFrom(this.request_);
                        this.request_ = ((Struct.Builder)subBuilder).buildPartial();
                        continue block20;
                    }
                    case 138: 
                }
                subBuilder = null;
                if (this.response_ != null) {
                    subBuilder = this.response_.toBuilder();
                }
                this.response_ = input2.readMessage(Struct.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                ((Struct.Builder)subBuilder).mergeFrom(this.response_);
                this.response_ = ((Struct.Builder)subBuilder).buildPartial();
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x40) == 64) {
                this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
    }

    @Override
    public String getServiceName() {
        Object ref = this.serviceName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.serviceName_ = s2;
        return s2;
    }

    @Override
    public ByteString getServiceNameBytes() {
        Object ref = this.serviceName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.serviceName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getMethodName() {
        Object ref = this.methodName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.methodName_ = s2;
        return s2;
    }

    @Override
    public ByteString getMethodNameBytes() {
        Object ref = this.methodName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.methodName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getResourceName() {
        Object ref = this.resourceName_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.resourceName_ = s2;
        return s2;
    }

    @Override
    public ByteString getResourceNameBytes() {
        Object ref = this.resourceName_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.resourceName_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    @Override
    public boolean hasStatus() {
        return this.status_ != null;
    }

    @Override
    public Status getStatus() {
        return this.status_ == null ? Status.getDefaultInstance() : this.status_;
    }

    @Override
    public StatusOrBuilder getStatusOrBuilder() {
        return this.getStatus();
    }

    @Override
    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo() {
        return this.authenticationInfo_ == null ? AuthenticationInfo.getDefaultInstance() : this.authenticationInfo_;
    }

    @Override
    public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
        return this.getAuthenticationInfo();
    }

    @Override
    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    @Override
    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    @Override
    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(int index) {
        return this.authorizationInfo_.get(index);
    }

    @Override
    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int index) {
        return this.authorizationInfo_.get(index);
    }

    @Override
    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    @Override
    public RequestMetadata getRequestMetadata() {
        return this.requestMetadata_ == null ? RequestMetadata.getDefaultInstance() : this.requestMetadata_;
    }

    @Override
    public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
        return this.getRequestMetadata();
    }

    @Override
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override
    public Struct getRequest() {
        return this.request_ == null ? Struct.getDefaultInstance() : this.request_;
    }

    @Override
    public StructOrBuilder getRequestOrBuilder() {
        return this.getRequest();
    }

    @Override
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override
    public Struct getResponse() {
        return this.response_ == null ? Struct.getDefaultInstance() : this.response_;
    }

    @Override
    public StructOrBuilder getResponseOrBuilder() {
        return this.getResponse();
    }

    @Override
    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    @Override
    public Any getServiceData() {
        return this.serviceData_ == null ? Any.getDefaultInstance() : this.serviceData_;
    }

    @Override
    public AnyOrBuilder getServiceDataOrBuilder() {
        return this.getServiceData();
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
        if (this.status_ != null) {
            output.writeMessage(2, this.getStatus());
        }
        if (this.authenticationInfo_ != null) {
            output.writeMessage(3, this.getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            output.writeMessage(4, this.getRequestMetadata());
        }
        if (!this.getServiceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 7, this.serviceName_);
        }
        if (!this.getMethodNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 8, this.methodName_);
        }
        for (int i = 0; i < this.authorizationInfo_.size(); ++i) {
            output.writeMessage(9, this.authorizationInfo_.get(i));
        }
        if (!this.getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 11, this.resourceName_);
        }
        if (this.numResponseItems_ != 0L) {
            output.writeInt64(12, this.numResponseItems_);
        }
        if (this.serviceData_ != null) {
            output.writeMessage(15, this.getServiceData());
        }
        if (this.request_ != null) {
            output.writeMessage(16, this.getRequest());
        }
        if (this.response_ != null) {
            output.writeMessage(17, this.getResponse());
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
        if (this.status_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, this.getStatus());
        }
        if (this.authenticationInfo_ != null) {
            size2 += CodedOutputStream.computeMessageSize(3, this.getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            size2 += CodedOutputStream.computeMessageSize(4, this.getRequestMetadata());
        }
        if (!this.getServiceNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(7, this.serviceName_);
        }
        if (!this.getMethodNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(8, this.methodName_);
        }
        for (int i = 0; i < this.authorizationInfo_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(9, this.authorizationInfo_.get(i));
        }
        if (!this.getResourceNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.resourceName_);
        }
        if (this.numResponseItems_ != 0L) {
            size2 += CodedOutputStream.computeInt64Size(12, this.numResponseItems_);
        }
        if (this.serviceData_ != null) {
            size2 += CodedOutputStream.computeMessageSize(15, this.getServiceData());
        }
        if (this.request_ != null) {
            size2 += CodedOutputStream.computeMessageSize(16, this.getRequest());
        }
        if (this.response_ != null) {
            size2 += CodedOutputStream.computeMessageSize(17, this.getResponse());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuditLog)) {
            return super.equals(obj);
        }
        AuditLog other = (AuditLog)obj;
        boolean result2 = true;
        result2 = result2 && this.getServiceName().equals(other.getServiceName());
        result2 = result2 && this.getMethodName().equals(other.getMethodName());
        result2 = result2 && this.getResourceName().equals(other.getResourceName());
        result2 = result2 && this.getNumResponseItems() == other.getNumResponseItems();
        boolean bl = result2 = result2 && this.hasStatus() == other.hasStatus();
        if (this.hasStatus()) {
            result2 = result2 && this.getStatus().equals(other.getStatus());
        }
        boolean bl2 = result2 = result2 && this.hasAuthenticationInfo() == other.hasAuthenticationInfo();
        if (this.hasAuthenticationInfo()) {
            result2 = result2 && this.getAuthenticationInfo().equals(other.getAuthenticationInfo());
        }
        result2 = result2 && this.getAuthorizationInfoList().equals(other.getAuthorizationInfoList());
        boolean bl3 = result2 = result2 && this.hasRequestMetadata() == other.hasRequestMetadata();
        if (this.hasRequestMetadata()) {
            result2 = result2 && this.getRequestMetadata().equals(other.getRequestMetadata());
        }
        boolean bl4 = result2 = result2 && this.hasRequest() == other.hasRequest();
        if (this.hasRequest()) {
            result2 = result2 && this.getRequest().equals(other.getRequest());
        }
        boolean bl5 = result2 = result2 && this.hasResponse() == other.hasResponse();
        if (this.hasResponse()) {
            result2 = result2 && this.getResponse().equals(other.getResponse());
        }
        boolean bl6 = result2 = result2 && this.hasServiceData() == other.hasServiceData();
        if (this.hasServiceData()) {
            result2 = result2 && this.getServiceData().equals(other.getServiceData());
        }
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + AuditLog.getDescriptor().hashCode();
        hash = 37 * hash + 7;
        hash = 53 * hash + this.getServiceName().hashCode();
        hash = 37 * hash + 8;
        hash = 53 * hash + this.getMethodName().hashCode();
        hash = 37 * hash + 11;
        hash = 53 * hash + this.getResourceName().hashCode();
        hash = 37 * hash + 12;
        hash = 53 * hash + Internal.hashLong(this.getNumResponseItems());
        if (this.hasStatus()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getStatus().hashCode();
        }
        if (this.hasAuthenticationInfo()) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getAuthenticationInfo().hashCode();
        }
        if (this.getAuthorizationInfoCount() > 0) {
            hash = 37 * hash + 9;
            hash = 53 * hash + this.getAuthorizationInfoList().hashCode();
        }
        if (this.hasRequestMetadata()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getRequestMetadata().hashCode();
        }
        if (this.hasRequest()) {
            hash = 37 * hash + 16;
            hash = 53 * hash + this.getRequest().hashCode();
        }
        if (this.hasResponse()) {
            hash = 37 * hash + 17;
            hash = 53 * hash + this.getResponse().hashCode();
        }
        if (this.hasServiceData()) {
            hash = 37 * hash + 15;
            hash = 53 * hash + this.getServiceData().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static AuditLog parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditLog parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditLog parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditLog parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditLog parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static AuditLog parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static AuditLog parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuditLog parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuditLog parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static AuditLog parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static AuditLog parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static AuditLog parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return AuditLog.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuditLog prototype) {
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

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return PARSER;
    }

    public Parser<AuditLog> getParserForType() {
        return PARSER;
    }

    @Override
    public AuditLog getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements AuditLogOrBuilder {
        private int bitField0_;
        private Object serviceName_ = "";
        private Object methodName_ = "";
        private Object resourceName_ = "";
        private long numResponseItems_;
        private Status status_ = null;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> statusBuilder_;
        private AuthenticationInfo authenticationInfo_ = null;
        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> authenticationInfoBuilder_;
        private List<AuthorizationInfo> authorizationInfo_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> authorizationInfoBuilder_;
        private RequestMetadata requestMetadata_ = null;
        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> requestMetadataBuilder_;
        private Struct request_ = null;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> requestBuilder_;
        private Struct response_ = null;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> responseBuilder_;
        private Any serviceData_ = null;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> serviceDataBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
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
                this.getAuthorizationInfoFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.numResponseItems_ = 0L;
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFBF;
            } else {
                this.authorizationInfoBuilder_.clear();
            }
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            if (this.requestBuilder_ == null) {
                this.request_ = null;
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            if (this.responseBuilder_ == null) {
                this.response_ = null;
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        @Override
        public AuditLog getDefaultInstanceForType() {
            return AuditLog.getDefaultInstance();
        }

        @Override
        public AuditLog build() {
            AuditLog result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public AuditLog buildPartial() {
            AuditLog result2 = new AuditLog(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.serviceName_ = this.serviceName_;
            result2.methodName_ = this.methodName_;
            result2.resourceName_ = this.resourceName_;
            result2.numResponseItems_ = this.numResponseItems_;
            if (this.statusBuilder_ == null) {
                result2.status_ = this.status_;
            } else {
                result2.status_ = this.statusBuilder_.build();
            }
            if (this.authenticationInfoBuilder_ == null) {
                result2.authenticationInfo_ = this.authenticationInfo_;
            } else {
                result2.authenticationInfo_ = this.authenticationInfoBuilder_.build();
            }
            if (this.authorizationInfoBuilder_ == null) {
                if ((this.bitField0_ & 0x40) == 64) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                    this.bitField0_ &= 0xFFFFFFBF;
                }
                result2.authorizationInfo_ = this.authorizationInfo_;
            } else {
                result2.authorizationInfo_ = this.authorizationInfoBuilder_.build();
            }
            if (this.requestMetadataBuilder_ == null) {
                result2.requestMetadata_ = this.requestMetadata_;
            } else {
                result2.requestMetadata_ = this.requestMetadataBuilder_.build();
            }
            if (this.requestBuilder_ == null) {
                result2.request_ = this.request_;
            } else {
                result2.request_ = this.requestBuilder_.build();
            }
            if (this.responseBuilder_ == null) {
                result2.response_ = this.response_;
            } else {
                result2.response_ = this.responseBuilder_.build();
            }
            if (this.serviceDataBuilder_ == null) {
                result2.serviceData_ = this.serviceData_;
            } else {
                result2.serviceData_ = this.serviceDataBuilder_.build();
            }
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
            if (other instanceof AuditLog) {
                return this.mergeFrom((AuditLog)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(AuditLog other) {
            if (other == AuditLog.getDefaultInstance()) {
                return this;
            }
            if (!other.getServiceName().isEmpty()) {
                this.serviceName_ = other.serviceName_;
                this.onChanged();
            }
            if (!other.getMethodName().isEmpty()) {
                this.methodName_ = other.methodName_;
                this.onChanged();
            }
            if (!other.getResourceName().isEmpty()) {
                this.resourceName_ = other.resourceName_;
                this.onChanged();
            }
            if (other.getNumResponseItems() != 0L) {
                this.setNumResponseItems(other.getNumResponseItems());
            }
            if (other.hasStatus()) {
                this.mergeStatus(other.getStatus());
            }
            if (other.hasAuthenticationInfo()) {
                this.mergeAuthenticationInfo(other.getAuthenticationInfo());
            }
            if (this.authorizationInfoBuilder_ == null) {
                if (!other.authorizationInfo_.isEmpty()) {
                    if (this.authorizationInfo_.isEmpty()) {
                        this.authorizationInfo_ = other.authorizationInfo_;
                        this.bitField0_ &= 0xFFFFFFBF;
                    } else {
                        this.ensureAuthorizationInfoIsMutable();
                        this.authorizationInfo_.addAll(other.authorizationInfo_);
                    }
                    this.onChanged();
                }
            } else if (!other.authorizationInfo_.isEmpty()) {
                if (this.authorizationInfoBuilder_.isEmpty()) {
                    this.authorizationInfoBuilder_.dispose();
                    this.authorizationInfoBuilder_ = null;
                    this.authorizationInfo_ = other.authorizationInfo_;
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.authorizationInfoBuilder_ = alwaysUseFieldBuilders ? this.getAuthorizationInfoFieldBuilder() : null;
                } else {
                    this.authorizationInfoBuilder_.addAllMessages(other.authorizationInfo_);
                }
            }
            if (other.hasRequestMetadata()) {
                this.mergeRequestMetadata(other.getRequestMetadata());
            }
            if (other.hasRequest()) {
                this.mergeRequest(other.getRequest());
            }
            if (other.hasResponse()) {
                this.mergeResponse(other.getResponse());
            }
            if (other.hasServiceData()) {
                this.mergeServiceData(other.getServiceData());
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
            AuditLog parsedMessage = null;
            try {
                parsedMessage = (AuditLog)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (AuditLog)e.getUnfinishedMessage();
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
        public String getServiceName() {
            Object ref = this.serviceName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.serviceName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getServiceNameBytes() {
            Object ref = this.serviceName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.serviceName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setServiceName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.serviceName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearServiceName() {
            this.serviceName_ = AuditLog.getDefaultInstance().getServiceName();
            this.onChanged();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuditLog.checkByteStringIsUtf8(value);
            this.serviceName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getMethodName() {
            Object ref = this.methodName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.methodName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getMethodNameBytes() {
            Object ref = this.methodName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.methodName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setMethodName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.methodName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearMethodName() {
            this.methodName_ = AuditLog.getDefaultInstance().getMethodName();
            this.onChanged();
            return this;
        }

        public Builder setMethodNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuditLog.checkByteStringIsUtf8(value);
            this.methodName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getResourceName() {
            Object ref = this.resourceName_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.resourceName_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getResourceNameBytes() {
            Object ref = this.resourceName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.resourceName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setResourceName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.resourceName_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearResourceName() {
            this.resourceName_ = AuditLog.getDefaultInstance().getResourceName();
            this.onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            AuditLog.checkByteStringIsUtf8(value);
            this.resourceName_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public long getNumResponseItems() {
            return this.numResponseItems_;
        }

        public Builder setNumResponseItems(long value) {
            this.numResponseItems_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearNumResponseItems() {
            this.numResponseItems_ = 0L;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasStatus() {
            return this.statusBuilder_ != null || this.status_ != null;
        }

        @Override
        public Status getStatus() {
            if (this.statusBuilder_ == null) {
                return this.status_ == null ? Status.getDefaultInstance() : this.status_;
            }
            return this.statusBuilder_.getMessage();
        }

        public Builder setStatus(Status value) {
            if (this.statusBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.status_ = value;
                this.onChanged();
            } else {
                this.statusBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setStatus(Status.Builder builderForValue) {
            if (this.statusBuilder_ == null) {
                this.status_ = builderForValue.build();
                this.onChanged();
            } else {
                this.statusBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeStatus(Status value) {
            if (this.statusBuilder_ == null) {
                this.status_ = this.status_ != null ? Status.newBuilder(this.status_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.statusBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                this.onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public Status.Builder getStatusBuilder() {
            this.onChanged();
            return this.getStatusFieldBuilder().getBuilder();
        }

        @Override
        public StatusOrBuilder getStatusOrBuilder() {
            if (this.statusBuilder_ != null) {
                return this.statusBuilder_.getMessageOrBuilder();
            }
            return this.status_ == null ? Status.getDefaultInstance() : this.status_;
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3(this.getStatus(), this.getParentForChildren(), this.isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
        }

        @Override
        public boolean hasAuthenticationInfo() {
            return this.authenticationInfoBuilder_ != null || this.authenticationInfo_ != null;
        }

        @Override
        public AuthenticationInfo getAuthenticationInfo() {
            if (this.authenticationInfoBuilder_ == null) {
                return this.authenticationInfo_ == null ? AuthenticationInfo.getDefaultInstance() : this.authenticationInfo_;
            }
            return this.authenticationInfoBuilder_.getMessage();
        }

        public Builder setAuthenticationInfo(AuthenticationInfo value) {
            if (this.authenticationInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.authenticationInfo_ = value;
                this.onChanged();
            } else {
                this.authenticationInfoBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builderForValue) {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = builderForValue.build();
                this.onChanged();
            } else {
                this.authenticationInfoBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo value) {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = this.authenticationInfo_ != null ? AuthenticationInfo.newBuilder(this.authenticationInfo_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.authenticationInfoBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearAuthenticationInfo() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
                this.onChanged();
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            return this;
        }

        public AuthenticationInfo.Builder getAuthenticationInfoBuilder() {
            this.onChanged();
            return this.getAuthenticationInfoFieldBuilder().getBuilder();
        }

        @Override
        public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
            if (this.authenticationInfoBuilder_ != null) {
                return this.authenticationInfoBuilder_.getMessageOrBuilder();
            }
            return this.authenticationInfo_ == null ? AuthenticationInfo.getDefaultInstance() : this.authenticationInfo_;
        }

        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> getAuthenticationInfoFieldBuilder() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfoBuilder_ = new SingleFieldBuilderV3(this.getAuthenticationInfo(), this.getParentForChildren(), this.isClean());
                this.authenticationInfo_ = null;
            }
            return this.authenticationInfoBuilder_;
        }

        private void ensureAuthorizationInfoIsMutable() {
            if ((this.bitField0_ & 0x40) != 64) {
                this.authorizationInfo_ = new ArrayList<AuthorizationInfo>(this.authorizationInfo_);
                this.bitField0_ |= 0x40;
            }
        }

        @Override
        public List<AuthorizationInfo> getAuthorizationInfoList() {
            if (this.authorizationInfoBuilder_ == null) {
                return Collections.unmodifiableList(this.authorizationInfo_);
            }
            return this.authorizationInfoBuilder_.getMessageList();
        }

        @Override
        public int getAuthorizationInfoCount() {
            if (this.authorizationInfoBuilder_ == null) {
                return this.authorizationInfo_.size();
            }
            return this.authorizationInfoBuilder_.getCount();
        }

        @Override
        public AuthorizationInfo getAuthorizationInfo(int index) {
            if (this.authorizationInfoBuilder_ == null) {
                return this.authorizationInfo_.get(index);
            }
            return this.authorizationInfoBuilder_.getMessage(index);
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo value) {
            if (this.authorizationInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(index, value);
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            if (this.authorizationInfoBuilder_ == null) {
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo value) {
            if (this.authorizationInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(value);
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo value) {
            if (this.authorizationInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(index, value);
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builderForValue) {
            if (this.authorizationInfoBuilder_ == null) {
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            if (this.authorizationInfoBuilder_ == null) {
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> values) {
            if (this.authorizationInfoBuilder_ == null) {
                this.ensureAuthorizationInfoIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.authorizationInfo_);
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearAuthorizationInfo() {
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFBF;
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.clear();
            }
            return this;
        }

        public Builder removeAuthorizationInfo(int index) {
            if (this.authorizationInfoBuilder_ == null) {
                this.ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.remove(index);
                this.onChanged();
            } else {
                this.authorizationInfoBuilder_.remove(index);
            }
            return this;
        }

        public AuthorizationInfo.Builder getAuthorizationInfoBuilder(int index) {
            return this.getAuthorizationInfoFieldBuilder().getBuilder(index);
        }

        @Override
        public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int index) {
            if (this.authorizationInfoBuilder_ == null) {
                return this.authorizationInfo_.get(index);
            }
            return this.authorizationInfoBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
            if (this.authorizationInfoBuilder_ != null) {
                return this.authorizationInfoBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.authorizationInfo_);
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder() {
            return this.getAuthorizationInfoFieldBuilder().addBuilder(AuthorizationInfo.getDefaultInstance());
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder(int index) {
            return this.getAuthorizationInfoFieldBuilder().addBuilder(index, AuthorizationInfo.getDefaultInstance());
        }

        public List<AuthorizationInfo.Builder> getAuthorizationInfoBuilderList() {
            return this.getAuthorizationInfoFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> getAuthorizationInfoFieldBuilder() {
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfoBuilder_ = new RepeatedFieldBuilderV3(this.authorizationInfo_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                this.authorizationInfo_ = null;
            }
            return this.authorizationInfoBuilder_;
        }

        @Override
        public boolean hasRequestMetadata() {
            return this.requestMetadataBuilder_ != null || this.requestMetadata_ != null;
        }

        @Override
        public RequestMetadata getRequestMetadata() {
            if (this.requestMetadataBuilder_ == null) {
                return this.requestMetadata_ == null ? RequestMetadata.getDefaultInstance() : this.requestMetadata_;
            }
            return this.requestMetadataBuilder_.getMessage();
        }

        public Builder setRequestMetadata(RequestMetadata value) {
            if (this.requestMetadataBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.requestMetadata_ = value;
                this.onChanged();
            } else {
                this.requestMetadataBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builderForValue) {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = builderForValue.build();
                this.onChanged();
            } else {
                this.requestMetadataBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata value) {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = this.requestMetadata_ != null ? RequestMetadata.newBuilder(this.requestMetadata_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.requestMetadataBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearRequestMetadata() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
                this.onChanged();
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            return this;
        }

        public RequestMetadata.Builder getRequestMetadataBuilder() {
            this.onChanged();
            return this.getRequestMetadataFieldBuilder().getBuilder();
        }

        @Override
        public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
            if (this.requestMetadataBuilder_ != null) {
                return this.requestMetadataBuilder_.getMessageOrBuilder();
            }
            return this.requestMetadata_ == null ? RequestMetadata.getDefaultInstance() : this.requestMetadata_;
        }

        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> getRequestMetadataFieldBuilder() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadataBuilder_ = new SingleFieldBuilderV3(this.getRequestMetadata(), this.getParentForChildren(), this.isClean());
                this.requestMetadata_ = null;
            }
            return this.requestMetadataBuilder_;
        }

        @Override
        public boolean hasRequest() {
            return this.requestBuilder_ != null || this.request_ != null;
        }

        @Override
        public Struct getRequest() {
            if (this.requestBuilder_ == null) {
                return this.request_ == null ? Struct.getDefaultInstance() : this.request_;
            }
            return this.requestBuilder_.getMessage();
        }

        public Builder setRequest(Struct value) {
            if (this.requestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.request_ = value;
                this.onChanged();
            } else {
                this.requestBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setRequest(Struct.Builder builderForValue) {
            if (this.requestBuilder_ == null) {
                this.request_ = builderForValue.build();
                this.onChanged();
            } else {
                this.requestBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeRequest(Struct value) {
            if (this.requestBuilder_ == null) {
                this.request_ = this.request_ != null ? Struct.newBuilder(this.request_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.requestBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearRequest() {
            if (this.requestBuilder_ == null) {
                this.request_ = null;
                this.onChanged();
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getRequestBuilder() {
            this.onChanged();
            return this.getRequestFieldBuilder().getBuilder();
        }

        @Override
        public StructOrBuilder getRequestOrBuilder() {
            if (this.requestBuilder_ != null) {
                return this.requestBuilder_.getMessageOrBuilder();
            }
            return this.request_ == null ? Struct.getDefaultInstance() : this.request_;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getRequestFieldBuilder() {
            if (this.requestBuilder_ == null) {
                this.requestBuilder_ = new SingleFieldBuilderV3(this.getRequest(), this.getParentForChildren(), this.isClean());
                this.request_ = null;
            }
            return this.requestBuilder_;
        }

        @Override
        public boolean hasResponse() {
            return this.responseBuilder_ != null || this.response_ != null;
        }

        @Override
        public Struct getResponse() {
            if (this.responseBuilder_ == null) {
                return this.response_ == null ? Struct.getDefaultInstance() : this.response_;
            }
            return this.responseBuilder_.getMessage();
        }

        public Builder setResponse(Struct value) {
            if (this.responseBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.response_ = value;
                this.onChanged();
            } else {
                this.responseBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setResponse(Struct.Builder builderForValue) {
            if (this.responseBuilder_ == null) {
                this.response_ = builderForValue.build();
                this.onChanged();
            } else {
                this.responseBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeResponse(Struct value) {
            if (this.responseBuilder_ == null) {
                this.response_ = this.response_ != null ? Struct.newBuilder(this.response_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.responseBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                this.response_ = null;
                this.onChanged();
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getResponseBuilder() {
            this.onChanged();
            return this.getResponseFieldBuilder().getBuilder();
        }

        @Override
        public StructOrBuilder getResponseOrBuilder() {
            if (this.responseBuilder_ != null) {
                return this.responseBuilder_.getMessageOrBuilder();
            }
            return this.response_ == null ? Struct.getDefaultInstance() : this.response_;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                this.responseBuilder_ = new SingleFieldBuilderV3(this.getResponse(), this.getParentForChildren(), this.isClean());
                this.response_ = null;
            }
            return this.responseBuilder_;
        }

        @Override
        public boolean hasServiceData() {
            return this.serviceDataBuilder_ != null || this.serviceData_ != null;
        }

        @Override
        public Any getServiceData() {
            if (this.serviceDataBuilder_ == null) {
                return this.serviceData_ == null ? Any.getDefaultInstance() : this.serviceData_;
            }
            return this.serviceDataBuilder_.getMessage();
        }

        public Builder setServiceData(Any value) {
            if (this.serviceDataBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.serviceData_ = value;
                this.onChanged();
            } else {
                this.serviceDataBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setServiceData(Any.Builder builderForValue) {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = builderForValue.build();
                this.onChanged();
            } else {
                this.serviceDataBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeServiceData(Any value) {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = this.serviceData_ != null ? Any.newBuilder(this.serviceData_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.serviceDataBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearServiceData() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
                this.onChanged();
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getServiceDataBuilder() {
            this.onChanged();
            return this.getServiceDataFieldBuilder().getBuilder();
        }

        @Override
        public AnyOrBuilder getServiceDataOrBuilder() {
            if (this.serviceDataBuilder_ != null) {
                return this.serviceDataBuilder_.getMessageOrBuilder();
            }
            return this.serviceData_ == null ? Any.getDefaultInstance() : this.serviceData_;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getServiceDataFieldBuilder() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceDataBuilder_ = new SingleFieldBuilderV3(this.getServiceData(), this.getParentForChildren(), this.isClean());
                this.serviceData_ = null;
            }
            return this.serviceDataBuilder_;
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

