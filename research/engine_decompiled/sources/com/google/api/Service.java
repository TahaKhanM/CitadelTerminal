/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.Authentication;
import com.google.api.AuthenticationOrBuilder;
import com.google.api.Backend;
import com.google.api.BackendOrBuilder;
import com.google.api.Billing;
import com.google.api.BillingOrBuilder;
import com.google.api.Context;
import com.google.api.ContextOrBuilder;
import com.google.api.Control;
import com.google.api.ControlOrBuilder;
import com.google.api.Documentation;
import com.google.api.DocumentationOrBuilder;
import com.google.api.Endpoint;
import com.google.api.EndpointOrBuilder;
import com.google.api.Experimental;
import com.google.api.ExperimentalOrBuilder;
import com.google.api.Http;
import com.google.api.HttpOrBuilder;
import com.google.api.LogDescriptor;
import com.google.api.LogDescriptorOrBuilder;
import com.google.api.Logging;
import com.google.api.LoggingOrBuilder;
import com.google.api.MetricDescriptor;
import com.google.api.MetricDescriptorOrBuilder;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.MonitoredResourceDescriptorOrBuilder;
import com.google.api.Monitoring;
import com.google.api.MonitoringOrBuilder;
import com.google.api.Quota;
import com.google.api.QuotaOrBuilder;
import com.google.api.ServiceOrBuilder;
import com.google.api.ServiceProto;
import com.google.api.SourceInfo;
import com.google.api.SourceInfoOrBuilder;
import com.google.api.SystemParameters;
import com.google.api.SystemParametersOrBuilder;
import com.google.api.Usage;
import com.google.api.UsageOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Service
extends GeneratedMessageV3
implements ServiceOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    private UInt32Value configVersion_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    public static final int ID_FIELD_NUMBER = 33;
    private volatile Object id_;
    public static final int TITLE_FIELD_NUMBER = 2;
    private volatile Object title_;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    private volatile Object producerProjectId_;
    public static final int APIS_FIELD_NUMBER = 3;
    private List<Api> apis_;
    public static final int TYPES_FIELD_NUMBER = 4;
    private List<Type> types_;
    public static final int ENUMS_FIELD_NUMBER = 5;
    private List<Enum> enums_;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    private Documentation documentation_;
    public static final int BACKEND_FIELD_NUMBER = 8;
    private Backend backend_;
    public static final int HTTP_FIELD_NUMBER = 9;
    private Http http_;
    public static final int QUOTA_FIELD_NUMBER = 10;
    private Quota quota_;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    private Authentication authentication_;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    private Context context_;
    public static final int USAGE_FIELD_NUMBER = 15;
    private Usage usage_;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    private List<Endpoint> endpoints_;
    public static final int CONTROL_FIELD_NUMBER = 21;
    private Control control_;
    public static final int LOGS_FIELD_NUMBER = 23;
    private List<LogDescriptor> logs_;
    public static final int METRICS_FIELD_NUMBER = 24;
    private List<MetricDescriptor> metrics_;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    private List<MonitoredResourceDescriptor> monitoredResources_;
    public static final int BILLING_FIELD_NUMBER = 26;
    private Billing billing_;
    public static final int LOGGING_FIELD_NUMBER = 27;
    private Logging logging_;
    public static final int MONITORING_FIELD_NUMBER = 28;
    private Monitoring monitoring_;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    private SystemParameters systemParameters_;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    private SourceInfo sourceInfo_;
    public static final int EXPERIMENTAL_FIELD_NUMBER = 101;
    private Experimental experimental_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Service DEFAULT_INSTANCE = new Service();
    private static final Parser<Service> PARSER = new AbstractParser<Service>(){

        @Override
        public Service parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Service(input2, extensionRegistry);
        }
    };

    private Service(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Service() {
        this.name_ = "";
        this.id_ = "";
        this.title_ = "";
        this.producerProjectId_ = "";
        this.apis_ = Collections.emptyList();
        this.types_ = Collections.emptyList();
        this.enums_ = Collections.emptyList();
        this.endpoints_ = Collections.emptyList();
        this.logs_ = Collections.emptyList();
        this.metrics_ = Collections.emptyList();
        this.monitoredResources_ = Collections.emptyList();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Service(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block35: while (!done) {
                GeneratedMessageV3.Builder subBuilder;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block35;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block35;
                        done = true;
                        continue block35;
                    }
                    case 10: {
                        String s2 = input2.readStringRequireUtf8();
                        this.name_ = s2;
                        continue block35;
                    }
                    case 18: {
                        String s2 = input2.readStringRequireUtf8();
                        this.title_ = s2;
                        continue block35;
                    }
                    case 26: {
                        if ((mutable_bitField0_ & 0x20) != 32) {
                            this.apis_ = new ArrayList<Api>();
                            mutable_bitField0_ |= 0x20;
                        }
                        this.apis_.add(input2.readMessage(Api.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 34: {
                        if ((mutable_bitField0_ & 0x40) != 64) {
                            this.types_ = new ArrayList<Type>();
                            mutable_bitField0_ |= 0x40;
                        }
                        this.types_.add(input2.readMessage(Type.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 42: {
                        if ((mutable_bitField0_ & 0x80) != 128) {
                            this.enums_ = new ArrayList<Enum>();
                            mutable_bitField0_ |= 0x80;
                        }
                        this.enums_.add(input2.readMessage(Enum.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 50: {
                        subBuilder = null;
                        if (this.documentation_ != null) {
                            subBuilder = this.documentation_.toBuilder();
                        }
                        this.documentation_ = input2.readMessage(Documentation.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Documentation.Builder)subBuilder).mergeFrom(this.documentation_);
                        this.documentation_ = ((Documentation.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 66: {
                        subBuilder = null;
                        if (this.backend_ != null) {
                            subBuilder = this.backend_.toBuilder();
                        }
                        this.backend_ = input2.readMessage(Backend.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Backend.Builder)subBuilder).mergeFrom(this.backend_);
                        this.backend_ = ((Backend.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 74: {
                        subBuilder = null;
                        if (this.http_ != null) {
                            subBuilder = this.http_.toBuilder();
                        }
                        this.http_ = input2.readMessage(Http.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Http.Builder)subBuilder).mergeFrom(this.http_);
                        this.http_ = ((Http.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 82: {
                        subBuilder = null;
                        if (this.quota_ != null) {
                            subBuilder = this.quota_.toBuilder();
                        }
                        this.quota_ = input2.readMessage(Quota.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Quota.Builder)subBuilder).mergeFrom(this.quota_);
                        this.quota_ = ((Quota.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 90: {
                        subBuilder = null;
                        if (this.authentication_ != null) {
                            subBuilder = this.authentication_.toBuilder();
                        }
                        this.authentication_ = input2.readMessage(Authentication.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Authentication.Builder)subBuilder).mergeFrom(this.authentication_);
                        this.authentication_ = ((Authentication.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 98: {
                        subBuilder = null;
                        if (this.context_ != null) {
                            subBuilder = this.context_.toBuilder();
                        }
                        this.context_ = input2.readMessage(Context.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Context.Builder)subBuilder).mergeFrom(this.context_);
                        this.context_ = ((Context.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 122: {
                        subBuilder = null;
                        if (this.usage_ != null) {
                            subBuilder = this.usage_.toBuilder();
                        }
                        this.usage_ = input2.readMessage(Usage.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Usage.Builder)subBuilder).mergeFrom(this.usage_);
                        this.usage_ = ((Usage.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 146: {
                        if ((mutable_bitField0_ & 0x8000) != 32768) {
                            this.endpoints_ = new ArrayList<Endpoint>();
                            mutable_bitField0_ |= 0x8000;
                        }
                        this.endpoints_.add(input2.readMessage(Endpoint.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 162: {
                        subBuilder = null;
                        if (this.configVersion_ != null) {
                            subBuilder = this.configVersion_.toBuilder();
                        }
                        this.configVersion_ = input2.readMessage(UInt32Value.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((UInt32Value.Builder)subBuilder).mergeFrom(this.configVersion_);
                        this.configVersion_ = ((UInt32Value.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 170: {
                        subBuilder = null;
                        if (this.control_ != null) {
                            subBuilder = this.control_.toBuilder();
                        }
                        this.control_ = input2.readMessage(Control.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Control.Builder)subBuilder).mergeFrom(this.control_);
                        this.control_ = ((Control.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 178: {
                        String s2 = input2.readStringRequireUtf8();
                        this.producerProjectId_ = s2;
                        continue block35;
                    }
                    case 186: {
                        if ((mutable_bitField0_ & 0x20000) != 131072) {
                            this.logs_ = new ArrayList<LogDescriptor>();
                            mutable_bitField0_ |= 0x20000;
                        }
                        this.logs_.add(input2.readMessage(LogDescriptor.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 194: {
                        if ((mutable_bitField0_ & 0x40000) != 262144) {
                            this.metrics_ = new ArrayList<MetricDescriptor>();
                            mutable_bitField0_ |= 0x40000;
                        }
                        this.metrics_.add(input2.readMessage(MetricDescriptor.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 202: {
                        if ((mutable_bitField0_ & 0x80000) != 524288) {
                            this.monitoredResources_ = new ArrayList<MonitoredResourceDescriptor>();
                            mutable_bitField0_ |= 0x80000;
                        }
                        this.monitoredResources_.add(input2.readMessage(MonitoredResourceDescriptor.parser(), extensionRegistry));
                        continue block35;
                    }
                    case 210: {
                        subBuilder = null;
                        if (this.billing_ != null) {
                            subBuilder = this.billing_.toBuilder();
                        }
                        this.billing_ = input2.readMessage(Billing.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Billing.Builder)subBuilder).mergeFrom(this.billing_);
                        this.billing_ = ((Billing.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 218: {
                        subBuilder = null;
                        if (this.logging_ != null) {
                            subBuilder = this.logging_.toBuilder();
                        }
                        this.logging_ = input2.readMessage(Logging.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Logging.Builder)subBuilder).mergeFrom(this.logging_);
                        this.logging_ = ((Logging.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 226: {
                        subBuilder = null;
                        if (this.monitoring_ != null) {
                            subBuilder = this.monitoring_.toBuilder();
                        }
                        this.monitoring_ = input2.readMessage(Monitoring.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((Monitoring.Builder)subBuilder).mergeFrom(this.monitoring_);
                        this.monitoring_ = ((Monitoring.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 234: {
                        subBuilder = null;
                        if (this.systemParameters_ != null) {
                            subBuilder = this.systemParameters_.toBuilder();
                        }
                        this.systemParameters_ = input2.readMessage(SystemParameters.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((SystemParameters.Builder)subBuilder).mergeFrom(this.systemParameters_);
                        this.systemParameters_ = ((SystemParameters.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 266: {
                        String s2 = input2.readStringRequireUtf8();
                        this.id_ = s2;
                        continue block35;
                    }
                    case 298: {
                        subBuilder = null;
                        if (this.sourceInfo_ != null) {
                            subBuilder = this.sourceInfo_.toBuilder();
                        }
                        this.sourceInfo_ = input2.readMessage(SourceInfo.parser(), extensionRegistry);
                        if (subBuilder == null) continue block35;
                        ((SourceInfo.Builder)subBuilder).mergeFrom(this.sourceInfo_);
                        this.sourceInfo_ = ((SourceInfo.Builder)subBuilder).buildPartial();
                        continue block35;
                    }
                    case 810: 
                }
                subBuilder = null;
                if (this.experimental_ != null) {
                    subBuilder = this.experimental_.toBuilder();
                }
                this.experimental_ = input2.readMessage(Experimental.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                ((Experimental.Builder)subBuilder).mergeFrom(this.experimental_);
                this.experimental_ = ((Experimental.Builder)subBuilder).buildPartial();
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x20) == 32) {
                this.apis_ = Collections.unmodifiableList(this.apis_);
            }
            if ((mutable_bitField0_ & 0x40) == 64) {
                this.types_ = Collections.unmodifiableList(this.types_);
            }
            if ((mutable_bitField0_ & 0x80) == 128) {
                this.enums_ = Collections.unmodifiableList(this.enums_);
            }
            if ((mutable_bitField0_ & 0x8000) == 32768) {
                this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
            }
            if ((mutable_bitField0_ & 0x20000) == 131072) {
                this.logs_ = Collections.unmodifiableList(this.logs_);
            }
            if ((mutable_bitField0_ & 0x40000) == 262144) {
                this.metrics_ = Collections.unmodifiableList(this.metrics_);
            }
            if ((mutable_bitField0_ & 0x80000) == 524288) {
                this.monitoredResources_ = Collections.unmodifiableList(this.monitoredResources_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServiceProto.internal_static_google_api_Service_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
    }

    @Override
    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    @Override
    public UInt32Value getConfigVersion() {
        return this.configVersion_ == null ? UInt32Value.getDefaultInstance() : this.configVersion_;
    }

    @Override
    public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
        return this.getConfigVersion();
    }

    @Override
    public String getName() {
        Object ref = this.name_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.name_ = s2;
        return s2;
    }

    @Override
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getId() {
        Object ref = this.id_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.id_ = s2;
        return s2;
    }

    @Override
    public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.id_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getTitle() {
        Object ref = this.title_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.title_ = s2;
        return s2;
    }

    @Override
    public ByteString getTitleBytes() {
        Object ref = this.title_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.title_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getProducerProjectId() {
        Object ref = this.producerProjectId_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.producerProjectId_ = s2;
        return s2;
    }

    @Override
    public ByteString getProducerProjectIdBytes() {
        Object ref = this.producerProjectId_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.producerProjectId_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public List<Api> getApisList() {
        return this.apis_;
    }

    @Override
    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    @Override
    public int getApisCount() {
        return this.apis_.size();
    }

    @Override
    public Api getApis(int index) {
        return this.apis_.get(index);
    }

    @Override
    public ApiOrBuilder getApisOrBuilder(int index) {
        return this.apis_.get(index);
    }

    @Override
    public List<Type> getTypesList() {
        return this.types_;
    }

    @Override
    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    @Override
    public int getTypesCount() {
        return this.types_.size();
    }

    @Override
    public Type getTypes(int index) {
        return this.types_.get(index);
    }

    @Override
    public TypeOrBuilder getTypesOrBuilder(int index) {
        return this.types_.get(index);
    }

    @Override
    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    @Override
    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    @Override
    public int getEnumsCount() {
        return this.enums_.size();
    }

    @Override
    public Enum getEnums(int index) {
        return this.enums_.get(index);
    }

    @Override
    public EnumOrBuilder getEnumsOrBuilder(int index) {
        return this.enums_.get(index);
    }

    @Override
    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    @Override
    public Documentation getDocumentation() {
        return this.documentation_ == null ? Documentation.getDefaultInstance() : this.documentation_;
    }

    @Override
    public DocumentationOrBuilder getDocumentationOrBuilder() {
        return this.getDocumentation();
    }

    @Override
    public boolean hasBackend() {
        return this.backend_ != null;
    }

    @Override
    public Backend getBackend() {
        return this.backend_ == null ? Backend.getDefaultInstance() : this.backend_;
    }

    @Override
    public BackendOrBuilder getBackendOrBuilder() {
        return this.getBackend();
    }

    @Override
    public boolean hasHttp() {
        return this.http_ != null;
    }

    @Override
    public Http getHttp() {
        return this.http_ == null ? Http.getDefaultInstance() : this.http_;
    }

    @Override
    public HttpOrBuilder getHttpOrBuilder() {
        return this.getHttp();
    }

    @Override
    public boolean hasQuota() {
        return this.quota_ != null;
    }

    @Override
    public Quota getQuota() {
        return this.quota_ == null ? Quota.getDefaultInstance() : this.quota_;
    }

    @Override
    public QuotaOrBuilder getQuotaOrBuilder() {
        return this.getQuota();
    }

    @Override
    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    @Override
    public Authentication getAuthentication() {
        return this.authentication_ == null ? Authentication.getDefaultInstance() : this.authentication_;
    }

    @Override
    public AuthenticationOrBuilder getAuthenticationOrBuilder() {
        return this.getAuthentication();
    }

    @Override
    public boolean hasContext() {
        return this.context_ != null;
    }

    @Override
    public Context getContext() {
        return this.context_ == null ? Context.getDefaultInstance() : this.context_;
    }

    @Override
    public ContextOrBuilder getContextOrBuilder() {
        return this.getContext();
    }

    @Override
    public boolean hasUsage() {
        return this.usage_ != null;
    }

    @Override
    public Usage getUsage() {
        return this.usage_ == null ? Usage.getDefaultInstance() : this.usage_;
    }

    @Override
    public UsageOrBuilder getUsageOrBuilder() {
        return this.getUsage();
    }

    @Override
    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    @Override
    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    @Override
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override
    public Endpoint getEndpoints(int index) {
        return this.endpoints_.get(index);
    }

    @Override
    public EndpointOrBuilder getEndpointsOrBuilder(int index) {
        return this.endpoints_.get(index);
    }

    @Override
    public boolean hasControl() {
        return this.control_ != null;
    }

    @Override
    public Control getControl() {
        return this.control_ == null ? Control.getDefaultInstance() : this.control_;
    }

    @Override
    public ControlOrBuilder getControlOrBuilder() {
        return this.getControl();
    }

    @Override
    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    @Override
    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    @Override
    public int getLogsCount() {
        return this.logs_.size();
    }

    @Override
    public LogDescriptor getLogs(int index) {
        return this.logs_.get(index);
    }

    @Override
    public LogDescriptorOrBuilder getLogsOrBuilder(int index) {
        return this.logs_.get(index);
    }

    @Override
    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    @Override
    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    @Override
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override
    public MetricDescriptor getMetrics(int index) {
        return this.metrics_.get(index);
    }

    @Override
    public MetricDescriptorOrBuilder getMetricsOrBuilder(int index) {
        return this.metrics_.get(index);
    }

    @Override
    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    @Override
    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    @Override
    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    @Override
    public MonitoredResourceDescriptor getMonitoredResources(int index) {
        return this.monitoredResources_.get(index);
    }

    @Override
    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int index) {
        return this.monitoredResources_.get(index);
    }

    @Override
    public boolean hasBilling() {
        return this.billing_ != null;
    }

    @Override
    public Billing getBilling() {
        return this.billing_ == null ? Billing.getDefaultInstance() : this.billing_;
    }

    @Override
    public BillingOrBuilder getBillingOrBuilder() {
        return this.getBilling();
    }

    @Override
    public boolean hasLogging() {
        return this.logging_ != null;
    }

    @Override
    public Logging getLogging() {
        return this.logging_ == null ? Logging.getDefaultInstance() : this.logging_;
    }

    @Override
    public LoggingOrBuilder getLoggingOrBuilder() {
        return this.getLogging();
    }

    @Override
    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    @Override
    public Monitoring getMonitoring() {
        return this.monitoring_ == null ? Monitoring.getDefaultInstance() : this.monitoring_;
    }

    @Override
    public MonitoringOrBuilder getMonitoringOrBuilder() {
        return this.getMonitoring();
    }

    @Override
    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    @Override
    public SystemParameters getSystemParameters() {
        return this.systemParameters_ == null ? SystemParameters.getDefaultInstance() : this.systemParameters_;
    }

    @Override
    public SystemParametersOrBuilder getSystemParametersOrBuilder() {
        return this.getSystemParameters();
    }

    @Override
    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    @Override
    public SourceInfo getSourceInfo() {
        return this.sourceInfo_ == null ? SourceInfo.getDefaultInstance() : this.sourceInfo_;
    }

    @Override
    public SourceInfoOrBuilder getSourceInfoOrBuilder() {
        return this.getSourceInfo();
    }

    @Override
    public boolean hasExperimental() {
        return this.experimental_ != null;
    }

    @Override
    public Experimental getExperimental() {
        return this.experimental_ == null ? Experimental.getDefaultInstance() : this.experimental_;
    }

    @Override
    public ExperimentalOrBuilder getExperimentalOrBuilder() {
        return this.getExperimental();
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
        if (!this.getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        if (!this.getTitleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.title_);
        }
        for (i = 0; i < this.apis_.size(); ++i) {
            output.writeMessage(3, this.apis_.get(i));
        }
        for (i = 0; i < this.types_.size(); ++i) {
            output.writeMessage(4, this.types_.get(i));
        }
        for (i = 0; i < this.enums_.size(); ++i) {
            output.writeMessage(5, this.enums_.get(i));
        }
        if (this.documentation_ != null) {
            output.writeMessage(6, this.getDocumentation());
        }
        if (this.backend_ != null) {
            output.writeMessage(8, this.getBackend());
        }
        if (this.http_ != null) {
            output.writeMessage(9, this.getHttp());
        }
        if (this.quota_ != null) {
            output.writeMessage(10, this.getQuota());
        }
        if (this.authentication_ != null) {
            output.writeMessage(11, this.getAuthentication());
        }
        if (this.context_ != null) {
            output.writeMessage(12, this.getContext());
        }
        if (this.usage_ != null) {
            output.writeMessage(15, this.getUsage());
        }
        for (i = 0; i < this.endpoints_.size(); ++i) {
            output.writeMessage(18, this.endpoints_.get(i));
        }
        if (this.configVersion_ != null) {
            output.writeMessage(20, this.getConfigVersion());
        }
        if (this.control_ != null) {
            output.writeMessage(21, this.getControl());
        }
        if (!this.getProducerProjectIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 22, this.producerProjectId_);
        }
        for (i = 0; i < this.logs_.size(); ++i) {
            output.writeMessage(23, this.logs_.get(i));
        }
        for (i = 0; i < this.metrics_.size(); ++i) {
            output.writeMessage(24, this.metrics_.get(i));
        }
        for (i = 0; i < this.monitoredResources_.size(); ++i) {
            output.writeMessage(25, this.monitoredResources_.get(i));
        }
        if (this.billing_ != null) {
            output.writeMessage(26, this.getBilling());
        }
        if (this.logging_ != null) {
            output.writeMessage(27, this.getLogging());
        }
        if (this.monitoring_ != null) {
            output.writeMessage(28, this.getMonitoring());
        }
        if (this.systemParameters_ != null) {
            output.writeMessage(29, this.getSystemParameters());
        }
        if (!this.getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            output.writeMessage(37, this.getSourceInfo());
        }
        if (this.experimental_ != null) {
            output.writeMessage(101, this.getExperimental());
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
        if (!this.getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (!this.getTitleBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.title_);
        }
        for (i = 0; i < this.apis_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(3, this.apis_.get(i));
        }
        for (i = 0; i < this.types_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(4, this.types_.get(i));
        }
        for (i = 0; i < this.enums_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(5, this.enums_.get(i));
        }
        if (this.documentation_ != null) {
            size2 += CodedOutputStream.computeMessageSize(6, this.getDocumentation());
        }
        if (this.backend_ != null) {
            size2 += CodedOutputStream.computeMessageSize(8, this.getBackend());
        }
        if (this.http_ != null) {
            size2 += CodedOutputStream.computeMessageSize(9, this.getHttp());
        }
        if (this.quota_ != null) {
            size2 += CodedOutputStream.computeMessageSize(10, this.getQuota());
        }
        if (this.authentication_ != null) {
            size2 += CodedOutputStream.computeMessageSize(11, this.getAuthentication());
        }
        if (this.context_ != null) {
            size2 += CodedOutputStream.computeMessageSize(12, this.getContext());
        }
        if (this.usage_ != null) {
            size2 += CodedOutputStream.computeMessageSize(15, this.getUsage());
        }
        for (i = 0; i < this.endpoints_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(18, this.endpoints_.get(i));
        }
        if (this.configVersion_ != null) {
            size2 += CodedOutputStream.computeMessageSize(20, this.getConfigVersion());
        }
        if (this.control_ != null) {
            size2 += CodedOutputStream.computeMessageSize(21, this.getControl());
        }
        if (!this.getProducerProjectIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(22, this.producerProjectId_);
        }
        for (i = 0; i < this.logs_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(23, this.logs_.get(i));
        }
        for (i = 0; i < this.metrics_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(24, this.metrics_.get(i));
        }
        for (i = 0; i < this.monitoredResources_.size(); ++i) {
            size2 += CodedOutputStream.computeMessageSize(25, this.monitoredResources_.get(i));
        }
        if (this.billing_ != null) {
            size2 += CodedOutputStream.computeMessageSize(26, this.getBilling());
        }
        if (this.logging_ != null) {
            size2 += CodedOutputStream.computeMessageSize(27, this.getLogging());
        }
        if (this.monitoring_ != null) {
            size2 += CodedOutputStream.computeMessageSize(28, this.getMonitoring());
        }
        if (this.systemParameters_ != null) {
            size2 += CodedOutputStream.computeMessageSize(29, this.getSystemParameters());
        }
        if (!this.getIdBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            size2 += CodedOutputStream.computeMessageSize(37, this.getSourceInfo());
        }
        if (this.experimental_ != null) {
            size2 += CodedOutputStream.computeMessageSize(101, this.getExperimental());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Service)) {
            return super.equals(obj);
        }
        Service other = (Service)obj;
        boolean result2 = true;
        boolean bl = result2 = result2 && this.hasConfigVersion() == other.hasConfigVersion();
        if (this.hasConfigVersion()) {
            result2 = result2 && this.getConfigVersion().equals(other.getConfigVersion());
        }
        result2 = result2 && this.getName().equals(other.getName());
        result2 = result2 && this.getId().equals(other.getId());
        result2 = result2 && this.getTitle().equals(other.getTitle());
        result2 = result2 && this.getProducerProjectId().equals(other.getProducerProjectId());
        result2 = result2 && this.getApisList().equals(other.getApisList());
        result2 = result2 && this.getTypesList().equals(other.getTypesList());
        result2 = result2 && this.getEnumsList().equals(other.getEnumsList());
        boolean bl2 = result2 = result2 && this.hasDocumentation() == other.hasDocumentation();
        if (this.hasDocumentation()) {
            result2 = result2 && this.getDocumentation().equals(other.getDocumentation());
        }
        boolean bl3 = result2 = result2 && this.hasBackend() == other.hasBackend();
        if (this.hasBackend()) {
            result2 = result2 && this.getBackend().equals(other.getBackend());
        }
        boolean bl4 = result2 = result2 && this.hasHttp() == other.hasHttp();
        if (this.hasHttp()) {
            result2 = result2 && this.getHttp().equals(other.getHttp());
        }
        boolean bl5 = result2 = result2 && this.hasQuota() == other.hasQuota();
        if (this.hasQuota()) {
            result2 = result2 && this.getQuota().equals(other.getQuota());
        }
        boolean bl6 = result2 = result2 && this.hasAuthentication() == other.hasAuthentication();
        if (this.hasAuthentication()) {
            result2 = result2 && this.getAuthentication().equals(other.getAuthentication());
        }
        boolean bl7 = result2 = result2 && this.hasContext() == other.hasContext();
        if (this.hasContext()) {
            result2 = result2 && this.getContext().equals(other.getContext());
        }
        boolean bl8 = result2 = result2 && this.hasUsage() == other.hasUsage();
        if (this.hasUsage()) {
            result2 = result2 && this.getUsage().equals(other.getUsage());
        }
        result2 = result2 && this.getEndpointsList().equals(other.getEndpointsList());
        boolean bl9 = result2 = result2 && this.hasControl() == other.hasControl();
        if (this.hasControl()) {
            result2 = result2 && this.getControl().equals(other.getControl());
        }
        result2 = result2 && this.getLogsList().equals(other.getLogsList());
        result2 = result2 && this.getMetricsList().equals(other.getMetricsList());
        result2 = result2 && this.getMonitoredResourcesList().equals(other.getMonitoredResourcesList());
        boolean bl10 = result2 = result2 && this.hasBilling() == other.hasBilling();
        if (this.hasBilling()) {
            result2 = result2 && this.getBilling().equals(other.getBilling());
        }
        boolean bl11 = result2 = result2 && this.hasLogging() == other.hasLogging();
        if (this.hasLogging()) {
            result2 = result2 && this.getLogging().equals(other.getLogging());
        }
        boolean bl12 = result2 = result2 && this.hasMonitoring() == other.hasMonitoring();
        if (this.hasMonitoring()) {
            result2 = result2 && this.getMonitoring().equals(other.getMonitoring());
        }
        boolean bl13 = result2 = result2 && this.hasSystemParameters() == other.hasSystemParameters();
        if (this.hasSystemParameters()) {
            result2 = result2 && this.getSystemParameters().equals(other.getSystemParameters());
        }
        boolean bl14 = result2 = result2 && this.hasSourceInfo() == other.hasSourceInfo();
        if (this.hasSourceInfo()) {
            result2 = result2 && this.getSourceInfo().equals(other.getSourceInfo());
        }
        boolean bl15 = result2 = result2 && this.hasExperimental() == other.hasExperimental();
        if (this.hasExperimental()) {
            result2 = result2 && this.getExperimental().equals(other.getExperimental());
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
        hash = 19 * hash + Service.getDescriptor().hashCode();
        if (this.hasConfigVersion()) {
            hash = 37 * hash + 20;
            hash = 53 * hash + this.getConfigVersion().hashCode();
        }
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getName().hashCode();
        hash = 37 * hash + 33;
        hash = 53 * hash + this.getId().hashCode();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getTitle().hashCode();
        hash = 37 * hash + 22;
        hash = 53 * hash + this.getProducerProjectId().hashCode();
        if (this.getApisCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getApisList().hashCode();
        }
        if (this.getTypesCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getTypesList().hashCode();
        }
        if (this.getEnumsCount() > 0) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getEnumsList().hashCode();
        }
        if (this.hasDocumentation()) {
            hash = 37 * hash + 6;
            hash = 53 * hash + this.getDocumentation().hashCode();
        }
        if (this.hasBackend()) {
            hash = 37 * hash + 8;
            hash = 53 * hash + this.getBackend().hashCode();
        }
        if (this.hasHttp()) {
            hash = 37 * hash + 9;
            hash = 53 * hash + this.getHttp().hashCode();
        }
        if (this.hasQuota()) {
            hash = 37 * hash + 10;
            hash = 53 * hash + this.getQuota().hashCode();
        }
        if (this.hasAuthentication()) {
            hash = 37 * hash + 11;
            hash = 53 * hash + this.getAuthentication().hashCode();
        }
        if (this.hasContext()) {
            hash = 37 * hash + 12;
            hash = 53 * hash + this.getContext().hashCode();
        }
        if (this.hasUsage()) {
            hash = 37 * hash + 15;
            hash = 53 * hash + this.getUsage().hashCode();
        }
        if (this.getEndpointsCount() > 0) {
            hash = 37 * hash + 18;
            hash = 53 * hash + this.getEndpointsList().hashCode();
        }
        if (this.hasControl()) {
            hash = 37 * hash + 21;
            hash = 53 * hash + this.getControl().hashCode();
        }
        if (this.getLogsCount() > 0) {
            hash = 37 * hash + 23;
            hash = 53 * hash + this.getLogsList().hashCode();
        }
        if (this.getMetricsCount() > 0) {
            hash = 37 * hash + 24;
            hash = 53 * hash + this.getMetricsList().hashCode();
        }
        if (this.getMonitoredResourcesCount() > 0) {
            hash = 37 * hash + 25;
            hash = 53 * hash + this.getMonitoredResourcesList().hashCode();
        }
        if (this.hasBilling()) {
            hash = 37 * hash + 26;
            hash = 53 * hash + this.getBilling().hashCode();
        }
        if (this.hasLogging()) {
            hash = 37 * hash + 27;
            hash = 53 * hash + this.getLogging().hashCode();
        }
        if (this.hasMonitoring()) {
            hash = 37 * hash + 28;
            hash = 53 * hash + this.getMonitoring().hashCode();
        }
        if (this.hasSystemParameters()) {
            hash = 37 * hash + 29;
            hash = 53 * hash + this.getSystemParameters().hashCode();
        }
        if (this.hasSourceInfo()) {
            hash = 37 * hash + 37;
            hash = 53 * hash + this.getSourceInfo().hashCode();
        }
        if (this.hasExperimental()) {
            hash = 37 * hash + 101;
            hash = 53 * hash + this.getExperimental().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Service parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Service parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Service parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Service parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Service parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Service parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Service parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Service parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Service parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Service parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Service parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Service parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Service.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Service prototype) {
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

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return PARSER;
    }

    public Parser<Service> getParserForType() {
        return PARSER;
    }

    @Override
    public Service getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ServiceOrBuilder {
        private int bitField0_;
        private UInt32Value configVersion_ = null;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> configVersionBuilder_;
        private Object name_ = "";
        private Object id_ = "";
        private Object title_ = "";
        private Object producerProjectId_ = "";
        private List<Api> apis_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> apisBuilder_;
        private List<Type> types_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> typesBuilder_;
        private List<Enum> enums_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> enumsBuilder_;
        private Documentation documentation_ = null;
        private SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> documentationBuilder_;
        private Backend backend_ = null;
        private SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> backendBuilder_;
        private Http http_ = null;
        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> httpBuilder_;
        private Quota quota_ = null;
        private SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> quotaBuilder_;
        private Authentication authentication_ = null;
        private SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> authenticationBuilder_;
        private Context context_ = null;
        private SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> contextBuilder_;
        private Usage usage_ = null;
        private SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> usageBuilder_;
        private List<Endpoint> endpoints_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> endpointsBuilder_;
        private Control control_ = null;
        private SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> controlBuilder_;
        private List<LogDescriptor> logs_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> logsBuilder_;
        private List<MetricDescriptor> metrics_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> metricsBuilder_;
        private List<MonitoredResourceDescriptor> monitoredResources_ = Collections.emptyList();
        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> monitoredResourcesBuilder_;
        private Billing billing_ = null;
        private SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> billingBuilder_;
        private Logging logging_ = null;
        private SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> loggingBuilder_;
        private Monitoring monitoring_ = null;
        private SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> monitoringBuilder_;
        private SystemParameters systemParameters_ = null;
        private SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> systemParametersBuilder_;
        private SourceInfo sourceInfo_ = null;
        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> sourceInfoBuilder_;
        private Experimental experimental_ = null;
        private SingleFieldBuilderV3<Experimental, Experimental.Builder, ExperimentalOrBuilder> experimentalBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
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
                this.getApisFieldBuilder();
                this.getTypesFieldBuilder();
                this.getEnumsFieldBuilder();
                this.getEndpointsFieldBuilder();
                this.getLogsFieldBuilder();
                this.getMetricsFieldBuilder();
                this.getMonitoredResourcesFieldBuilder();
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            this.name_ = "";
            this.id_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            if (this.apisBuilder_ == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFDF;
            } else {
                this.apisBuilder_.clear();
            }
            if (this.typesBuilder_ == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFBF;
            } else {
                this.typesBuilder_.clear();
            }
            if (this.enumsBuilder_ == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFF7F;
            } else {
                this.enumsBuilder_.clear();
            }
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            if (this.httpBuilder_ == null) {
                this.http_ = null;
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            if (this.contextBuilder_ == null) {
                this.context_ = null;
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            if (this.endpointsBuilder_ == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFF7FFF;
            } else {
                this.endpointsBuilder_.clear();
            }
            if (this.controlBuilder_ == null) {
                this.control_ = null;
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            if (this.logsBuilder_ == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFDFFFF;
            } else {
                this.logsBuilder_.clear();
            }
            if (this.metricsBuilder_ == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFBFFFF;
            } else {
                this.metricsBuilder_.clear();
            }
            if (this.monitoredResourcesBuilder_ == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= 0xFFF7FFFF;
            } else {
                this.monitoredResourcesBuilder_.clear();
            }
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = null;
            } else {
                this.experimental_ = null;
                this.experimentalBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        @Override
        public Service getDefaultInstanceForType() {
            return Service.getDefaultInstance();
        }

        @Override
        public Service build() {
            Service result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Service buildPartial() {
            Service result2 = new Service(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if (this.configVersionBuilder_ == null) {
                result2.configVersion_ = this.configVersion_;
            } else {
                result2.configVersion_ = this.configVersionBuilder_.build();
            }
            result2.name_ = this.name_;
            result2.id_ = this.id_;
            result2.title_ = this.title_;
            result2.producerProjectId_ = this.producerProjectId_;
            if (this.apisBuilder_ == null) {
                if ((this.bitField0_ & 0x20) == 32) {
                    this.apis_ = Collections.unmodifiableList(this.apis_);
                    this.bitField0_ &= 0xFFFFFFDF;
                }
                result2.apis_ = this.apis_;
            } else {
                result2.apis_ = this.apisBuilder_.build();
            }
            if (this.typesBuilder_ == null) {
                if ((this.bitField0_ & 0x40) == 64) {
                    this.types_ = Collections.unmodifiableList(this.types_);
                    this.bitField0_ &= 0xFFFFFFBF;
                }
                result2.types_ = this.types_;
            } else {
                result2.types_ = this.typesBuilder_.build();
            }
            if (this.enumsBuilder_ == null) {
                if ((this.bitField0_ & 0x80) == 128) {
                    this.enums_ = Collections.unmodifiableList(this.enums_);
                    this.bitField0_ &= 0xFFFFFF7F;
                }
                result2.enums_ = this.enums_;
            } else {
                result2.enums_ = this.enumsBuilder_.build();
            }
            if (this.documentationBuilder_ == null) {
                result2.documentation_ = this.documentation_;
            } else {
                result2.documentation_ = this.documentationBuilder_.build();
            }
            if (this.backendBuilder_ == null) {
                result2.backend_ = this.backend_;
            } else {
                result2.backend_ = this.backendBuilder_.build();
            }
            if (this.httpBuilder_ == null) {
                result2.http_ = this.http_;
            } else {
                result2.http_ = this.httpBuilder_.build();
            }
            if (this.quotaBuilder_ == null) {
                result2.quota_ = this.quota_;
            } else {
                result2.quota_ = this.quotaBuilder_.build();
            }
            if (this.authenticationBuilder_ == null) {
                result2.authentication_ = this.authentication_;
            } else {
                result2.authentication_ = this.authenticationBuilder_.build();
            }
            if (this.contextBuilder_ == null) {
                result2.context_ = this.context_;
            } else {
                result2.context_ = this.contextBuilder_.build();
            }
            if (this.usageBuilder_ == null) {
                result2.usage_ = this.usage_;
            } else {
                result2.usage_ = this.usageBuilder_.build();
            }
            if (this.endpointsBuilder_ == null) {
                if ((this.bitField0_ & 0x8000) == 32768) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                    this.bitField0_ &= 0xFFFF7FFF;
                }
                result2.endpoints_ = this.endpoints_;
            } else {
                result2.endpoints_ = this.endpointsBuilder_.build();
            }
            if (this.controlBuilder_ == null) {
                result2.control_ = this.control_;
            } else {
                result2.control_ = this.controlBuilder_.build();
            }
            if (this.logsBuilder_ == null) {
                if ((this.bitField0_ & 0x20000) == 131072) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                    this.bitField0_ &= 0xFFFDFFFF;
                }
                result2.logs_ = this.logs_;
            } else {
                result2.logs_ = this.logsBuilder_.build();
            }
            if (this.metricsBuilder_ == null) {
                if ((this.bitField0_ & 0x40000) == 262144) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                    this.bitField0_ &= 0xFFFBFFFF;
                }
                result2.metrics_ = this.metrics_;
            } else {
                result2.metrics_ = this.metricsBuilder_.build();
            }
            if (this.monitoredResourcesBuilder_ == null) {
                if ((this.bitField0_ & 0x80000) == 524288) {
                    this.monitoredResources_ = Collections.unmodifiableList(this.monitoredResources_);
                    this.bitField0_ &= 0xFFF7FFFF;
                }
                result2.monitoredResources_ = this.monitoredResources_;
            } else {
                result2.monitoredResources_ = this.monitoredResourcesBuilder_.build();
            }
            if (this.billingBuilder_ == null) {
                result2.billing_ = this.billing_;
            } else {
                result2.billing_ = this.billingBuilder_.build();
            }
            if (this.loggingBuilder_ == null) {
                result2.logging_ = this.logging_;
            } else {
                result2.logging_ = this.loggingBuilder_.build();
            }
            if (this.monitoringBuilder_ == null) {
                result2.monitoring_ = this.monitoring_;
            } else {
                result2.monitoring_ = this.monitoringBuilder_.build();
            }
            if (this.systemParametersBuilder_ == null) {
                result2.systemParameters_ = this.systemParameters_;
            } else {
                result2.systemParameters_ = this.systemParametersBuilder_.build();
            }
            if (this.sourceInfoBuilder_ == null) {
                result2.sourceInfo_ = this.sourceInfo_;
            } else {
                result2.sourceInfo_ = this.sourceInfoBuilder_.build();
            }
            if (this.experimentalBuilder_ == null) {
                result2.experimental_ = this.experimental_;
            } else {
                result2.experimental_ = this.experimentalBuilder_.build();
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
            if (other instanceof Service) {
                return this.mergeFrom((Service)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Service other) {
            if (other == Service.getDefaultInstance()) {
                return this;
            }
            if (other.hasConfigVersion()) {
                this.mergeConfigVersion(other.getConfigVersion());
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                this.onChanged();
            }
            if (!other.getId().isEmpty()) {
                this.id_ = other.id_;
                this.onChanged();
            }
            if (!other.getTitle().isEmpty()) {
                this.title_ = other.title_;
                this.onChanged();
            }
            if (!other.getProducerProjectId().isEmpty()) {
                this.producerProjectId_ = other.producerProjectId_;
                this.onChanged();
            }
            if (this.apisBuilder_ == null) {
                if (!other.apis_.isEmpty()) {
                    if (this.apis_.isEmpty()) {
                        this.apis_ = other.apis_;
                        this.bitField0_ &= 0xFFFFFFDF;
                    } else {
                        this.ensureApisIsMutable();
                        this.apis_.addAll(other.apis_);
                    }
                    this.onChanged();
                }
            } else if (!other.apis_.isEmpty()) {
                if (this.apisBuilder_.isEmpty()) {
                    this.apisBuilder_.dispose();
                    this.apisBuilder_ = null;
                    this.apis_ = other.apis_;
                    this.bitField0_ &= 0xFFFFFFDF;
                    this.apisBuilder_ = alwaysUseFieldBuilders ? this.getApisFieldBuilder() : null;
                } else {
                    this.apisBuilder_.addAllMessages(other.apis_);
                }
            }
            if (this.typesBuilder_ == null) {
                if (!other.types_.isEmpty()) {
                    if (this.types_.isEmpty()) {
                        this.types_ = other.types_;
                        this.bitField0_ &= 0xFFFFFFBF;
                    } else {
                        this.ensureTypesIsMutable();
                        this.types_.addAll(other.types_);
                    }
                    this.onChanged();
                }
            } else if (!other.types_.isEmpty()) {
                if (this.typesBuilder_.isEmpty()) {
                    this.typesBuilder_.dispose();
                    this.typesBuilder_ = null;
                    this.types_ = other.types_;
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.typesBuilder_ = alwaysUseFieldBuilders ? this.getTypesFieldBuilder() : null;
                } else {
                    this.typesBuilder_.addAllMessages(other.types_);
                }
            }
            if (this.enumsBuilder_ == null) {
                if (!other.enums_.isEmpty()) {
                    if (this.enums_.isEmpty()) {
                        this.enums_ = other.enums_;
                        this.bitField0_ &= 0xFFFFFF7F;
                    } else {
                        this.ensureEnumsIsMutable();
                        this.enums_.addAll(other.enums_);
                    }
                    this.onChanged();
                }
            } else if (!other.enums_.isEmpty()) {
                if (this.enumsBuilder_.isEmpty()) {
                    this.enumsBuilder_.dispose();
                    this.enumsBuilder_ = null;
                    this.enums_ = other.enums_;
                    this.bitField0_ &= 0xFFFFFF7F;
                    this.enumsBuilder_ = alwaysUseFieldBuilders ? this.getEnumsFieldBuilder() : null;
                } else {
                    this.enumsBuilder_.addAllMessages(other.enums_);
                }
            }
            if (other.hasDocumentation()) {
                this.mergeDocumentation(other.getDocumentation());
            }
            if (other.hasBackend()) {
                this.mergeBackend(other.getBackend());
            }
            if (other.hasHttp()) {
                this.mergeHttp(other.getHttp());
            }
            if (other.hasQuota()) {
                this.mergeQuota(other.getQuota());
            }
            if (other.hasAuthentication()) {
                this.mergeAuthentication(other.getAuthentication());
            }
            if (other.hasContext()) {
                this.mergeContext(other.getContext());
            }
            if (other.hasUsage()) {
                this.mergeUsage(other.getUsage());
            }
            if (this.endpointsBuilder_ == null) {
                if (!other.endpoints_.isEmpty()) {
                    if (this.endpoints_.isEmpty()) {
                        this.endpoints_ = other.endpoints_;
                        this.bitField0_ &= 0xFFFF7FFF;
                    } else {
                        this.ensureEndpointsIsMutable();
                        this.endpoints_.addAll(other.endpoints_);
                    }
                    this.onChanged();
                }
            } else if (!other.endpoints_.isEmpty()) {
                if (this.endpointsBuilder_.isEmpty()) {
                    this.endpointsBuilder_.dispose();
                    this.endpointsBuilder_ = null;
                    this.endpoints_ = other.endpoints_;
                    this.bitField0_ &= 0xFFFF7FFF;
                    this.endpointsBuilder_ = alwaysUseFieldBuilders ? this.getEndpointsFieldBuilder() : null;
                } else {
                    this.endpointsBuilder_.addAllMessages(other.endpoints_);
                }
            }
            if (other.hasControl()) {
                this.mergeControl(other.getControl());
            }
            if (this.logsBuilder_ == null) {
                if (!other.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = other.logs_;
                        this.bitField0_ &= 0xFFFDFFFF;
                    } else {
                        this.ensureLogsIsMutable();
                        this.logs_.addAll(other.logs_);
                    }
                    this.onChanged();
                }
            } else if (!other.logs_.isEmpty()) {
                if (this.logsBuilder_.isEmpty()) {
                    this.logsBuilder_.dispose();
                    this.logsBuilder_ = null;
                    this.logs_ = other.logs_;
                    this.bitField0_ &= 0xFFFDFFFF;
                    this.logsBuilder_ = alwaysUseFieldBuilders ? this.getLogsFieldBuilder() : null;
                } else {
                    this.logsBuilder_.addAllMessages(other.logs_);
                }
            }
            if (this.metricsBuilder_ == null) {
                if (!other.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = other.metrics_;
                        this.bitField0_ &= 0xFFFBFFFF;
                    } else {
                        this.ensureMetricsIsMutable();
                        this.metrics_.addAll(other.metrics_);
                    }
                    this.onChanged();
                }
            } else if (!other.metrics_.isEmpty()) {
                if (this.metricsBuilder_.isEmpty()) {
                    this.metricsBuilder_.dispose();
                    this.metricsBuilder_ = null;
                    this.metrics_ = other.metrics_;
                    this.bitField0_ &= 0xFFFBFFFF;
                    this.metricsBuilder_ = alwaysUseFieldBuilders ? this.getMetricsFieldBuilder() : null;
                } else {
                    this.metricsBuilder_.addAllMessages(other.metrics_);
                }
            }
            if (this.monitoredResourcesBuilder_ == null) {
                if (!other.monitoredResources_.isEmpty()) {
                    if (this.monitoredResources_.isEmpty()) {
                        this.monitoredResources_ = other.monitoredResources_;
                        this.bitField0_ &= 0xFFF7FFFF;
                    } else {
                        this.ensureMonitoredResourcesIsMutable();
                        this.monitoredResources_.addAll(other.monitoredResources_);
                    }
                    this.onChanged();
                }
            } else if (!other.monitoredResources_.isEmpty()) {
                if (this.monitoredResourcesBuilder_.isEmpty()) {
                    this.monitoredResourcesBuilder_.dispose();
                    this.monitoredResourcesBuilder_ = null;
                    this.monitoredResources_ = other.monitoredResources_;
                    this.bitField0_ &= 0xFFF7FFFF;
                    this.monitoredResourcesBuilder_ = alwaysUseFieldBuilders ? this.getMonitoredResourcesFieldBuilder() : null;
                } else {
                    this.monitoredResourcesBuilder_.addAllMessages(other.monitoredResources_);
                }
            }
            if (other.hasBilling()) {
                this.mergeBilling(other.getBilling());
            }
            if (other.hasLogging()) {
                this.mergeLogging(other.getLogging());
            }
            if (other.hasMonitoring()) {
                this.mergeMonitoring(other.getMonitoring());
            }
            if (other.hasSystemParameters()) {
                this.mergeSystemParameters(other.getSystemParameters());
            }
            if (other.hasSourceInfo()) {
                this.mergeSourceInfo(other.getSourceInfo());
            }
            if (other.hasExperimental()) {
                this.mergeExperimental(other.getExperimental());
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
            Service parsedMessage = null;
            try {
                parsedMessage = (Service)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Service)e.getUnfinishedMessage();
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
        public boolean hasConfigVersion() {
            return this.configVersionBuilder_ != null || this.configVersion_ != null;
        }

        @Override
        public UInt32Value getConfigVersion() {
            if (this.configVersionBuilder_ == null) {
                return this.configVersion_ == null ? UInt32Value.getDefaultInstance() : this.configVersion_;
            }
            return this.configVersionBuilder_.getMessage();
        }

        public Builder setConfigVersion(UInt32Value value) {
            if (this.configVersionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.configVersion_ = value;
                this.onChanged();
            } else {
                this.configVersionBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setConfigVersion(UInt32Value.Builder builderForValue) {
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = builderForValue.build();
                this.onChanged();
            } else {
                this.configVersionBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value value) {
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = this.configVersion_ != null ? UInt32Value.newBuilder(this.configVersion_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.configVersionBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearConfigVersion() {
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
                this.onChanged();
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getConfigVersionBuilder() {
            this.onChanged();
            return this.getConfigVersionFieldBuilder().getBuilder();
        }

        @Override
        public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
            if (this.configVersionBuilder_ != null) {
                return this.configVersionBuilder_.getMessageOrBuilder();
            }
            return this.configVersion_ == null ? UInt32Value.getDefaultInstance() : this.configVersion_;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getConfigVersionFieldBuilder() {
            if (this.configVersionBuilder_ == null) {
                this.configVersionBuilder_ = new SingleFieldBuilderV3(this.getConfigVersion(), this.getParentForChildren(), this.isClean());
                this.configVersion_ = null;
            }
            return this.configVersionBuilder_;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.name_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Service.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Service.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getId() {
            Object ref = this.id_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.id_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getIdBytes() {
            Object ref = this.id_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.id_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.id_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = Service.getDefaultInstance().getId();
            this.onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Service.checkByteStringIsUtf8(value);
            this.id_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getTitle() {
            Object ref = this.title_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.title_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getTitleBytes() {
            Object ref = this.title_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.title_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setTitle(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.title_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearTitle() {
            this.title_ = Service.getDefaultInstance().getTitle();
            this.onChanged();
            return this;
        }

        public Builder setTitleBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Service.checkByteStringIsUtf8(value);
            this.title_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getProducerProjectId() {
            Object ref = this.producerProjectId_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.producerProjectId_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getProducerProjectIdBytes() {
            Object ref = this.producerProjectId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.producerProjectId_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setProducerProjectId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.producerProjectId_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearProducerProjectId() {
            this.producerProjectId_ = Service.getDefaultInstance().getProducerProjectId();
            this.onChanged();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            Service.checkByteStringIsUtf8(value);
            this.producerProjectId_ = value;
            this.onChanged();
            return this;
        }

        private void ensureApisIsMutable() {
            if ((this.bitField0_ & 0x20) != 32) {
                this.apis_ = new ArrayList<Api>(this.apis_);
                this.bitField0_ |= 0x20;
            }
        }

        @Override
        public List<Api> getApisList() {
            if (this.apisBuilder_ == null) {
                return Collections.unmodifiableList(this.apis_);
            }
            return this.apisBuilder_.getMessageList();
        }

        @Override
        public int getApisCount() {
            if (this.apisBuilder_ == null) {
                return this.apis_.size();
            }
            return this.apisBuilder_.getCount();
        }

        @Override
        public Api getApis(int index) {
            if (this.apisBuilder_ == null) {
                return this.apis_.get(index);
            }
            return this.apisBuilder_.getMessage(index);
        }

        public Builder setApis(int index, Api value) {
            if (this.apisBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureApisIsMutable();
                this.apis_.set(index, value);
                this.onChanged();
            } else {
                this.apisBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setApis(int index, Api.Builder builderForValue) {
            if (this.apisBuilder_ == null) {
                this.ensureApisIsMutable();
                this.apis_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.apisBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addApis(Api value) {
            if (this.apisBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureApisIsMutable();
                this.apis_.add(value);
                this.onChanged();
            } else {
                this.apisBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addApis(int index, Api value) {
            if (this.apisBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureApisIsMutable();
                this.apis_.add(index, value);
                this.onChanged();
            } else {
                this.apisBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addApis(Api.Builder builderForValue) {
            if (this.apisBuilder_ == null) {
                this.ensureApisIsMutable();
                this.apis_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.apisBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addApis(int index, Api.Builder builderForValue) {
            if (this.apisBuilder_ == null) {
                this.ensureApisIsMutable();
                this.apis_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.apisBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> values) {
            if (this.apisBuilder_ == null) {
                this.ensureApisIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.apis_);
                this.onChanged();
            } else {
                this.apisBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearApis() {
            if (this.apisBuilder_ == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFDF;
                this.onChanged();
            } else {
                this.apisBuilder_.clear();
            }
            return this;
        }

        public Builder removeApis(int index) {
            if (this.apisBuilder_ == null) {
                this.ensureApisIsMutable();
                this.apis_.remove(index);
                this.onChanged();
            } else {
                this.apisBuilder_.remove(index);
            }
            return this;
        }

        public Api.Builder getApisBuilder(int index) {
            return this.getApisFieldBuilder().getBuilder(index);
        }

        @Override
        public ApiOrBuilder getApisOrBuilder(int index) {
            if (this.apisBuilder_ == null) {
                return this.apis_.get(index);
            }
            return this.apisBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends ApiOrBuilder> getApisOrBuilderList() {
            if (this.apisBuilder_ != null) {
                return this.apisBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.apis_);
        }

        public Api.Builder addApisBuilder() {
            return this.getApisFieldBuilder().addBuilder(Api.getDefaultInstance());
        }

        public Api.Builder addApisBuilder(int index) {
            return this.getApisFieldBuilder().addBuilder(index, Api.getDefaultInstance());
        }

        public List<Api.Builder> getApisBuilderList() {
            return this.getApisFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> getApisFieldBuilder() {
            if (this.apisBuilder_ == null) {
                this.apisBuilder_ = new RepeatedFieldBuilderV3(this.apis_, (this.bitField0_ & 0x20) == 32, this.getParentForChildren(), this.isClean());
                this.apis_ = null;
            }
            return this.apisBuilder_;
        }

        private void ensureTypesIsMutable() {
            if ((this.bitField0_ & 0x40) != 64) {
                this.types_ = new ArrayList<Type>(this.types_);
                this.bitField0_ |= 0x40;
            }
        }

        @Override
        public List<Type> getTypesList() {
            if (this.typesBuilder_ == null) {
                return Collections.unmodifiableList(this.types_);
            }
            return this.typesBuilder_.getMessageList();
        }

        @Override
        public int getTypesCount() {
            if (this.typesBuilder_ == null) {
                return this.types_.size();
            }
            return this.typesBuilder_.getCount();
        }

        @Override
        public Type getTypes(int index) {
            if (this.typesBuilder_ == null) {
                return this.types_.get(index);
            }
            return this.typesBuilder_.getMessage(index);
        }

        public Builder setTypes(int index, Type value) {
            if (this.typesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureTypesIsMutable();
                this.types_.set(index, value);
                this.onChanged();
            } else {
                this.typesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setTypes(int index, Type.Builder builderForValue) {
            if (this.typesBuilder_ == null) {
                this.ensureTypesIsMutable();
                this.types_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.typesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addTypes(Type value) {
            if (this.typesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureTypesIsMutable();
                this.types_.add(value);
                this.onChanged();
            } else {
                this.typesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addTypes(int index, Type value) {
            if (this.typesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureTypesIsMutable();
                this.types_.add(index, value);
                this.onChanged();
            } else {
                this.typesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addTypes(Type.Builder builderForValue) {
            if (this.typesBuilder_ == null) {
                this.ensureTypesIsMutable();
                this.types_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.typesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addTypes(int index, Type.Builder builderForValue) {
            if (this.typesBuilder_ == null) {
                this.ensureTypesIsMutable();
                this.types_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.typesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> values) {
            if (this.typesBuilder_ == null) {
                this.ensureTypesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.types_);
                this.onChanged();
            } else {
                this.typesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearTypes() {
            if (this.typesBuilder_ == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFBF;
                this.onChanged();
            } else {
                this.typesBuilder_.clear();
            }
            return this;
        }

        public Builder removeTypes(int index) {
            if (this.typesBuilder_ == null) {
                this.ensureTypesIsMutable();
                this.types_.remove(index);
                this.onChanged();
            } else {
                this.typesBuilder_.remove(index);
            }
            return this;
        }

        public Type.Builder getTypesBuilder(int index) {
            return this.getTypesFieldBuilder().getBuilder(index);
        }

        @Override
        public TypeOrBuilder getTypesOrBuilder(int index) {
            if (this.typesBuilder_ == null) {
                return this.types_.get(index);
            }
            return this.typesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
            if (this.typesBuilder_ != null) {
                return this.typesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.types_);
        }

        public Type.Builder addTypesBuilder() {
            return this.getTypesFieldBuilder().addBuilder(Type.getDefaultInstance());
        }

        public Type.Builder addTypesBuilder(int index) {
            return this.getTypesFieldBuilder().addBuilder(index, Type.getDefaultInstance());
        }

        public List<Type.Builder> getTypesBuilderList() {
            return this.getTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> getTypesFieldBuilder() {
            if (this.typesBuilder_ == null) {
                this.typesBuilder_ = new RepeatedFieldBuilderV3(this.types_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                this.types_ = null;
            }
            return this.typesBuilder_;
        }

        private void ensureEnumsIsMutable() {
            if ((this.bitField0_ & 0x80) != 128) {
                this.enums_ = new ArrayList<Enum>(this.enums_);
                this.bitField0_ |= 0x80;
            }
        }

        @Override
        public List<Enum> getEnumsList() {
            if (this.enumsBuilder_ == null) {
                return Collections.unmodifiableList(this.enums_);
            }
            return this.enumsBuilder_.getMessageList();
        }

        @Override
        public int getEnumsCount() {
            if (this.enumsBuilder_ == null) {
                return this.enums_.size();
            }
            return this.enumsBuilder_.getCount();
        }

        @Override
        public Enum getEnums(int index) {
            if (this.enumsBuilder_ == null) {
                return this.enums_.get(index);
            }
            return this.enumsBuilder_.getMessage(index);
        }

        public Builder setEnums(int index, Enum value) {
            if (this.enumsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEnumsIsMutable();
                this.enums_.set(index, value);
                this.onChanged();
            } else {
                this.enumsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setEnums(int index, Enum.Builder builderForValue) {
            if (this.enumsBuilder_ == null) {
                this.ensureEnumsIsMutable();
                this.enums_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.enumsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addEnums(Enum value) {
            if (this.enumsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEnumsIsMutable();
                this.enums_.add(value);
                this.onChanged();
            } else {
                this.enumsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addEnums(int index, Enum value) {
            if (this.enumsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEnumsIsMutable();
                this.enums_.add(index, value);
                this.onChanged();
            } else {
                this.enumsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addEnums(Enum.Builder builderForValue) {
            if (this.enumsBuilder_ == null) {
                this.ensureEnumsIsMutable();
                this.enums_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.enumsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addEnums(int index, Enum.Builder builderForValue) {
            if (this.enumsBuilder_ == null) {
                this.ensureEnumsIsMutable();
                this.enums_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.enumsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> values) {
            if (this.enumsBuilder_ == null) {
                this.ensureEnumsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.enums_);
                this.onChanged();
            } else {
                this.enumsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearEnums() {
            if (this.enumsBuilder_ == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFF7F;
                this.onChanged();
            } else {
                this.enumsBuilder_.clear();
            }
            return this;
        }

        public Builder removeEnums(int index) {
            if (this.enumsBuilder_ == null) {
                this.ensureEnumsIsMutable();
                this.enums_.remove(index);
                this.onChanged();
            } else {
                this.enumsBuilder_.remove(index);
            }
            return this;
        }

        public Enum.Builder getEnumsBuilder(int index) {
            return this.getEnumsFieldBuilder().getBuilder(index);
        }

        @Override
        public EnumOrBuilder getEnumsOrBuilder(int index) {
            if (this.enumsBuilder_ == null) {
                return this.enums_.get(index);
            }
            return this.enumsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
            if (this.enumsBuilder_ != null) {
                return this.enumsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.enums_);
        }

        public Enum.Builder addEnumsBuilder() {
            return this.getEnumsFieldBuilder().addBuilder(Enum.getDefaultInstance());
        }

        public Enum.Builder addEnumsBuilder(int index) {
            return this.getEnumsFieldBuilder().addBuilder(index, Enum.getDefaultInstance());
        }

        public List<Enum.Builder> getEnumsBuilderList() {
            return this.getEnumsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> getEnumsFieldBuilder() {
            if (this.enumsBuilder_ == null) {
                this.enumsBuilder_ = new RepeatedFieldBuilderV3(this.enums_, (this.bitField0_ & 0x80) == 128, this.getParentForChildren(), this.isClean());
                this.enums_ = null;
            }
            return this.enumsBuilder_;
        }

        @Override
        public boolean hasDocumentation() {
            return this.documentationBuilder_ != null || this.documentation_ != null;
        }

        @Override
        public Documentation getDocumentation() {
            if (this.documentationBuilder_ == null) {
                return this.documentation_ == null ? Documentation.getDefaultInstance() : this.documentation_;
            }
            return this.documentationBuilder_.getMessage();
        }

        public Builder setDocumentation(Documentation value) {
            if (this.documentationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.documentation_ = value;
                this.onChanged();
            } else {
                this.documentationBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builderForValue) {
            if (this.documentationBuilder_ == null) {
                this.documentation_ = builderForValue.build();
                this.onChanged();
            } else {
                this.documentationBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeDocumentation(Documentation value) {
            if (this.documentationBuilder_ == null) {
                this.documentation_ = this.documentation_ != null ? Documentation.newBuilder(this.documentation_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.documentationBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearDocumentation() {
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
                this.onChanged();
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            return this;
        }

        public Documentation.Builder getDocumentationBuilder() {
            this.onChanged();
            return this.getDocumentationFieldBuilder().getBuilder();
        }

        @Override
        public DocumentationOrBuilder getDocumentationOrBuilder() {
            if (this.documentationBuilder_ != null) {
                return this.documentationBuilder_.getMessageOrBuilder();
            }
            return this.documentation_ == null ? Documentation.getDefaultInstance() : this.documentation_;
        }

        private SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> getDocumentationFieldBuilder() {
            if (this.documentationBuilder_ == null) {
                this.documentationBuilder_ = new SingleFieldBuilderV3(this.getDocumentation(), this.getParentForChildren(), this.isClean());
                this.documentation_ = null;
            }
            return this.documentationBuilder_;
        }

        @Override
        public boolean hasBackend() {
            return this.backendBuilder_ != null || this.backend_ != null;
        }

        @Override
        public Backend getBackend() {
            if (this.backendBuilder_ == null) {
                return this.backend_ == null ? Backend.getDefaultInstance() : this.backend_;
            }
            return this.backendBuilder_.getMessage();
        }

        public Builder setBackend(Backend value) {
            if (this.backendBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.backend_ = value;
                this.onChanged();
            } else {
                this.backendBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setBackend(Backend.Builder builderForValue) {
            if (this.backendBuilder_ == null) {
                this.backend_ = builderForValue.build();
                this.onChanged();
            } else {
                this.backendBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeBackend(Backend value) {
            if (this.backendBuilder_ == null) {
                this.backend_ = this.backend_ != null ? Backend.newBuilder(this.backend_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.backendBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearBackend() {
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
                this.onChanged();
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            return this;
        }

        public Backend.Builder getBackendBuilder() {
            this.onChanged();
            return this.getBackendFieldBuilder().getBuilder();
        }

        @Override
        public BackendOrBuilder getBackendOrBuilder() {
            if (this.backendBuilder_ != null) {
                return this.backendBuilder_.getMessageOrBuilder();
            }
            return this.backend_ == null ? Backend.getDefaultInstance() : this.backend_;
        }

        private SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> getBackendFieldBuilder() {
            if (this.backendBuilder_ == null) {
                this.backendBuilder_ = new SingleFieldBuilderV3(this.getBackend(), this.getParentForChildren(), this.isClean());
                this.backend_ = null;
            }
            return this.backendBuilder_;
        }

        @Override
        public boolean hasHttp() {
            return this.httpBuilder_ != null || this.http_ != null;
        }

        @Override
        public Http getHttp() {
            if (this.httpBuilder_ == null) {
                return this.http_ == null ? Http.getDefaultInstance() : this.http_;
            }
            return this.httpBuilder_.getMessage();
        }

        public Builder setHttp(Http value) {
            if (this.httpBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.http_ = value;
                this.onChanged();
            } else {
                this.httpBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setHttp(Http.Builder builderForValue) {
            if (this.httpBuilder_ == null) {
                this.http_ = builderForValue.build();
                this.onChanged();
            } else {
                this.httpBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeHttp(Http value) {
            if (this.httpBuilder_ == null) {
                this.http_ = this.http_ != null ? Http.newBuilder(this.http_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.httpBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearHttp() {
            if (this.httpBuilder_ == null) {
                this.http_ = null;
                this.onChanged();
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            return this;
        }

        public Http.Builder getHttpBuilder() {
            this.onChanged();
            return this.getHttpFieldBuilder().getBuilder();
        }

        @Override
        public HttpOrBuilder getHttpOrBuilder() {
            if (this.httpBuilder_ != null) {
                return this.httpBuilder_.getMessageOrBuilder();
            }
            return this.http_ == null ? Http.getDefaultInstance() : this.http_;
        }

        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> getHttpFieldBuilder() {
            if (this.httpBuilder_ == null) {
                this.httpBuilder_ = new SingleFieldBuilderV3(this.getHttp(), this.getParentForChildren(), this.isClean());
                this.http_ = null;
            }
            return this.httpBuilder_;
        }

        @Override
        public boolean hasQuota() {
            return this.quotaBuilder_ != null || this.quota_ != null;
        }

        @Override
        public Quota getQuota() {
            if (this.quotaBuilder_ == null) {
                return this.quota_ == null ? Quota.getDefaultInstance() : this.quota_;
            }
            return this.quotaBuilder_.getMessage();
        }

        public Builder setQuota(Quota value) {
            if (this.quotaBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.quota_ = value;
                this.onChanged();
            } else {
                this.quotaBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setQuota(Quota.Builder builderForValue) {
            if (this.quotaBuilder_ == null) {
                this.quota_ = builderForValue.build();
                this.onChanged();
            } else {
                this.quotaBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeQuota(Quota value) {
            if (this.quotaBuilder_ == null) {
                this.quota_ = this.quota_ != null ? Quota.newBuilder(this.quota_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.quotaBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearQuota() {
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
                this.onChanged();
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            return this;
        }

        public Quota.Builder getQuotaBuilder() {
            this.onChanged();
            return this.getQuotaFieldBuilder().getBuilder();
        }

        @Override
        public QuotaOrBuilder getQuotaOrBuilder() {
            if (this.quotaBuilder_ != null) {
                return this.quotaBuilder_.getMessageOrBuilder();
            }
            return this.quota_ == null ? Quota.getDefaultInstance() : this.quota_;
        }

        private SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> getQuotaFieldBuilder() {
            if (this.quotaBuilder_ == null) {
                this.quotaBuilder_ = new SingleFieldBuilderV3(this.getQuota(), this.getParentForChildren(), this.isClean());
                this.quota_ = null;
            }
            return this.quotaBuilder_;
        }

        @Override
        public boolean hasAuthentication() {
            return this.authenticationBuilder_ != null || this.authentication_ != null;
        }

        @Override
        public Authentication getAuthentication() {
            if (this.authenticationBuilder_ == null) {
                return this.authentication_ == null ? Authentication.getDefaultInstance() : this.authentication_;
            }
            return this.authenticationBuilder_.getMessage();
        }

        public Builder setAuthentication(Authentication value) {
            if (this.authenticationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.authentication_ = value;
                this.onChanged();
            } else {
                this.authenticationBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builderForValue) {
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = builderForValue.build();
                this.onChanged();
            } else {
                this.authenticationBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeAuthentication(Authentication value) {
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = this.authentication_ != null ? Authentication.newBuilder(this.authentication_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.authenticationBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearAuthentication() {
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
                this.onChanged();
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            return this;
        }

        public Authentication.Builder getAuthenticationBuilder() {
            this.onChanged();
            return this.getAuthenticationFieldBuilder().getBuilder();
        }

        @Override
        public AuthenticationOrBuilder getAuthenticationOrBuilder() {
            if (this.authenticationBuilder_ != null) {
                return this.authenticationBuilder_.getMessageOrBuilder();
            }
            return this.authentication_ == null ? Authentication.getDefaultInstance() : this.authentication_;
        }

        private SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> getAuthenticationFieldBuilder() {
            if (this.authenticationBuilder_ == null) {
                this.authenticationBuilder_ = new SingleFieldBuilderV3(this.getAuthentication(), this.getParentForChildren(), this.isClean());
                this.authentication_ = null;
            }
            return this.authenticationBuilder_;
        }

        @Override
        public boolean hasContext() {
            return this.contextBuilder_ != null || this.context_ != null;
        }

        @Override
        public Context getContext() {
            if (this.contextBuilder_ == null) {
                return this.context_ == null ? Context.getDefaultInstance() : this.context_;
            }
            return this.contextBuilder_.getMessage();
        }

        public Builder setContext(Context value) {
            if (this.contextBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.context_ = value;
                this.onChanged();
            } else {
                this.contextBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setContext(Context.Builder builderForValue) {
            if (this.contextBuilder_ == null) {
                this.context_ = builderForValue.build();
                this.onChanged();
            } else {
                this.contextBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeContext(Context value) {
            if (this.contextBuilder_ == null) {
                this.context_ = this.context_ != null ? Context.newBuilder(this.context_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.contextBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearContext() {
            if (this.contextBuilder_ == null) {
                this.context_ = null;
                this.onChanged();
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            return this;
        }

        public Context.Builder getContextBuilder() {
            this.onChanged();
            return this.getContextFieldBuilder().getBuilder();
        }

        @Override
        public ContextOrBuilder getContextOrBuilder() {
            if (this.contextBuilder_ != null) {
                return this.contextBuilder_.getMessageOrBuilder();
            }
            return this.context_ == null ? Context.getDefaultInstance() : this.context_;
        }

        private SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> getContextFieldBuilder() {
            if (this.contextBuilder_ == null) {
                this.contextBuilder_ = new SingleFieldBuilderV3(this.getContext(), this.getParentForChildren(), this.isClean());
                this.context_ = null;
            }
            return this.contextBuilder_;
        }

        @Override
        public boolean hasUsage() {
            return this.usageBuilder_ != null || this.usage_ != null;
        }

        @Override
        public Usage getUsage() {
            if (this.usageBuilder_ == null) {
                return this.usage_ == null ? Usage.getDefaultInstance() : this.usage_;
            }
            return this.usageBuilder_.getMessage();
        }

        public Builder setUsage(Usage value) {
            if (this.usageBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.usage_ = value;
                this.onChanged();
            } else {
                this.usageBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setUsage(Usage.Builder builderForValue) {
            if (this.usageBuilder_ == null) {
                this.usage_ = builderForValue.build();
                this.onChanged();
            } else {
                this.usageBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeUsage(Usage value) {
            if (this.usageBuilder_ == null) {
                this.usage_ = this.usage_ != null ? Usage.newBuilder(this.usage_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.usageBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearUsage() {
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
                this.onChanged();
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            return this;
        }

        public Usage.Builder getUsageBuilder() {
            this.onChanged();
            return this.getUsageFieldBuilder().getBuilder();
        }

        @Override
        public UsageOrBuilder getUsageOrBuilder() {
            if (this.usageBuilder_ != null) {
                return this.usageBuilder_.getMessageOrBuilder();
            }
            return this.usage_ == null ? Usage.getDefaultInstance() : this.usage_;
        }

        private SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> getUsageFieldBuilder() {
            if (this.usageBuilder_ == null) {
                this.usageBuilder_ = new SingleFieldBuilderV3(this.getUsage(), this.getParentForChildren(), this.isClean());
                this.usage_ = null;
            }
            return this.usageBuilder_;
        }

        private void ensureEndpointsIsMutable() {
            if ((this.bitField0_ & 0x8000) != 32768) {
                this.endpoints_ = new ArrayList<Endpoint>(this.endpoints_);
                this.bitField0_ |= 0x8000;
            }
        }

        @Override
        public List<Endpoint> getEndpointsList() {
            if (this.endpointsBuilder_ == null) {
                return Collections.unmodifiableList(this.endpoints_);
            }
            return this.endpointsBuilder_.getMessageList();
        }

        @Override
        public int getEndpointsCount() {
            if (this.endpointsBuilder_ == null) {
                return this.endpoints_.size();
            }
            return this.endpointsBuilder_.getCount();
        }

        @Override
        public Endpoint getEndpoints(int index) {
            if (this.endpointsBuilder_ == null) {
                return this.endpoints_.get(index);
            }
            return this.endpointsBuilder_.getMessage(index);
        }

        public Builder setEndpoints(int index, Endpoint value) {
            if (this.endpointsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEndpointsIsMutable();
                this.endpoints_.set(index, value);
                this.onChanged();
            } else {
                this.endpointsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setEndpoints(int index, Endpoint.Builder builderForValue) {
            if (this.endpointsBuilder_ == null) {
                this.ensureEndpointsIsMutable();
                this.endpoints_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.endpointsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addEndpoints(Endpoint value) {
            if (this.endpointsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEndpointsIsMutable();
                this.endpoints_.add(value);
                this.onChanged();
            } else {
                this.endpointsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addEndpoints(int index, Endpoint value) {
            if (this.endpointsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureEndpointsIsMutable();
                this.endpoints_.add(index, value);
                this.onChanged();
            } else {
                this.endpointsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builderForValue) {
            if (this.endpointsBuilder_ == null) {
                this.ensureEndpointsIsMutable();
                this.endpoints_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.endpointsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addEndpoints(int index, Endpoint.Builder builderForValue) {
            if (this.endpointsBuilder_ == null) {
                this.ensureEndpointsIsMutable();
                this.endpoints_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.endpointsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> values) {
            if (this.endpointsBuilder_ == null) {
                this.ensureEndpointsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.endpoints_);
                this.onChanged();
            } else {
                this.endpointsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearEndpoints() {
            if (this.endpointsBuilder_ == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFF7FFF;
                this.onChanged();
            } else {
                this.endpointsBuilder_.clear();
            }
            return this;
        }

        public Builder removeEndpoints(int index) {
            if (this.endpointsBuilder_ == null) {
                this.ensureEndpointsIsMutable();
                this.endpoints_.remove(index);
                this.onChanged();
            } else {
                this.endpointsBuilder_.remove(index);
            }
            return this;
        }

        public Endpoint.Builder getEndpointsBuilder(int index) {
            return this.getEndpointsFieldBuilder().getBuilder(index);
        }

        @Override
        public EndpointOrBuilder getEndpointsOrBuilder(int index) {
            if (this.endpointsBuilder_ == null) {
                return this.endpoints_.get(index);
            }
            return this.endpointsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
            if (this.endpointsBuilder_ != null) {
                return this.endpointsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.endpoints_);
        }

        public Endpoint.Builder addEndpointsBuilder() {
            return this.getEndpointsFieldBuilder().addBuilder(Endpoint.getDefaultInstance());
        }

        public Endpoint.Builder addEndpointsBuilder(int index) {
            return this.getEndpointsFieldBuilder().addBuilder(index, Endpoint.getDefaultInstance());
        }

        public List<Endpoint.Builder> getEndpointsBuilderList() {
            return this.getEndpointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getEndpointsFieldBuilder() {
            if (this.endpointsBuilder_ == null) {
                this.endpointsBuilder_ = new RepeatedFieldBuilderV3(this.endpoints_, (this.bitField0_ & 0x8000) == 32768, this.getParentForChildren(), this.isClean());
                this.endpoints_ = null;
            }
            return this.endpointsBuilder_;
        }

        @Override
        public boolean hasControl() {
            return this.controlBuilder_ != null || this.control_ != null;
        }

        @Override
        public Control getControl() {
            if (this.controlBuilder_ == null) {
                return this.control_ == null ? Control.getDefaultInstance() : this.control_;
            }
            return this.controlBuilder_.getMessage();
        }

        public Builder setControl(Control value) {
            if (this.controlBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.control_ = value;
                this.onChanged();
            } else {
                this.controlBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setControl(Control.Builder builderForValue) {
            if (this.controlBuilder_ == null) {
                this.control_ = builderForValue.build();
                this.onChanged();
            } else {
                this.controlBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeControl(Control value) {
            if (this.controlBuilder_ == null) {
                this.control_ = this.control_ != null ? Control.newBuilder(this.control_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.controlBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearControl() {
            if (this.controlBuilder_ == null) {
                this.control_ = null;
                this.onChanged();
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            return this;
        }

        public Control.Builder getControlBuilder() {
            this.onChanged();
            return this.getControlFieldBuilder().getBuilder();
        }

        @Override
        public ControlOrBuilder getControlOrBuilder() {
            if (this.controlBuilder_ != null) {
                return this.controlBuilder_.getMessageOrBuilder();
            }
            return this.control_ == null ? Control.getDefaultInstance() : this.control_;
        }

        private SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> getControlFieldBuilder() {
            if (this.controlBuilder_ == null) {
                this.controlBuilder_ = new SingleFieldBuilderV3(this.getControl(), this.getParentForChildren(), this.isClean());
                this.control_ = null;
            }
            return this.controlBuilder_;
        }

        private void ensureLogsIsMutable() {
            if ((this.bitField0_ & 0x20000) != 131072) {
                this.logs_ = new ArrayList<LogDescriptor>(this.logs_);
                this.bitField0_ |= 0x20000;
            }
        }

        @Override
        public List<LogDescriptor> getLogsList() {
            if (this.logsBuilder_ == null) {
                return Collections.unmodifiableList(this.logs_);
            }
            return this.logsBuilder_.getMessageList();
        }

        @Override
        public int getLogsCount() {
            if (this.logsBuilder_ == null) {
                return this.logs_.size();
            }
            return this.logsBuilder_.getCount();
        }

        @Override
        public LogDescriptor getLogs(int index) {
            if (this.logsBuilder_ == null) {
                return this.logs_.get(index);
            }
            return this.logsBuilder_.getMessage(index);
        }

        public Builder setLogs(int index, LogDescriptor value) {
            if (this.logsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLogsIsMutable();
                this.logs_.set(index, value);
                this.onChanged();
            } else {
                this.logsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setLogs(int index, LogDescriptor.Builder builderForValue) {
            if (this.logsBuilder_ == null) {
                this.ensureLogsIsMutable();
                this.logs_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.logsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addLogs(LogDescriptor value) {
            if (this.logsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLogsIsMutable();
                this.logs_.add(value);
                this.onChanged();
            } else {
                this.logsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addLogs(int index, LogDescriptor value) {
            if (this.logsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureLogsIsMutable();
                this.logs_.add(index, value);
                this.onChanged();
            } else {
                this.logsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builderForValue) {
            if (this.logsBuilder_ == null) {
                this.ensureLogsIsMutable();
                this.logs_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.logsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addLogs(int index, LogDescriptor.Builder builderForValue) {
            if (this.logsBuilder_ == null) {
                this.ensureLogsIsMutable();
                this.logs_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.logsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> values) {
            if (this.logsBuilder_ == null) {
                this.ensureLogsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.logs_);
                this.onChanged();
            } else {
                this.logsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearLogs() {
            if (this.logsBuilder_ == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFDFFFF;
                this.onChanged();
            } else {
                this.logsBuilder_.clear();
            }
            return this;
        }

        public Builder removeLogs(int index) {
            if (this.logsBuilder_ == null) {
                this.ensureLogsIsMutable();
                this.logs_.remove(index);
                this.onChanged();
            } else {
                this.logsBuilder_.remove(index);
            }
            return this;
        }

        public LogDescriptor.Builder getLogsBuilder(int index) {
            return this.getLogsFieldBuilder().getBuilder(index);
        }

        @Override
        public LogDescriptorOrBuilder getLogsOrBuilder(int index) {
            if (this.logsBuilder_ == null) {
                return this.logs_.get(index);
            }
            return this.logsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
            if (this.logsBuilder_ != null) {
                return this.logsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.logs_);
        }

        public LogDescriptor.Builder addLogsBuilder() {
            return this.getLogsFieldBuilder().addBuilder(LogDescriptor.getDefaultInstance());
        }

        public LogDescriptor.Builder addLogsBuilder(int index) {
            return this.getLogsFieldBuilder().addBuilder(index, LogDescriptor.getDefaultInstance());
        }

        public List<LogDescriptor.Builder> getLogsBuilderList() {
            return this.getLogsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> getLogsFieldBuilder() {
            if (this.logsBuilder_ == null) {
                this.logsBuilder_ = new RepeatedFieldBuilderV3(this.logs_, (this.bitField0_ & 0x20000) == 131072, this.getParentForChildren(), this.isClean());
                this.logs_ = null;
            }
            return this.logsBuilder_;
        }

        private void ensureMetricsIsMutable() {
            if ((this.bitField0_ & 0x40000) != 262144) {
                this.metrics_ = new ArrayList<MetricDescriptor>(this.metrics_);
                this.bitField0_ |= 0x40000;
            }
        }

        @Override
        public List<MetricDescriptor> getMetricsList() {
            if (this.metricsBuilder_ == null) {
                return Collections.unmodifiableList(this.metrics_);
            }
            return this.metricsBuilder_.getMessageList();
        }

        @Override
        public int getMetricsCount() {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.size();
            }
            return this.metricsBuilder_.getCount();
        }

        @Override
        public MetricDescriptor getMetrics(int index) {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.get(index);
            }
            return this.metricsBuilder_.getMessage(index);
        }

        public Builder setMetrics(int index, MetricDescriptor value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.set(index, value);
                this.onChanged();
            } else {
                this.metricsBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setMetrics(int index, MetricDescriptor.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addMetrics(MetricDescriptor value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.add(value);
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor value) {
            if (this.metricsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMetricsIsMutable();
                this.metrics_.add(index, value);
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor.Builder builderForValue) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.metricsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> values) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.metrics_);
                this.onChanged();
            } else {
                this.metricsBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearMetrics() {
            if (this.metricsBuilder_ == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFBFFFF;
                this.onChanged();
            } else {
                this.metricsBuilder_.clear();
            }
            return this;
        }

        public Builder removeMetrics(int index) {
            if (this.metricsBuilder_ == null) {
                this.ensureMetricsIsMutable();
                this.metrics_.remove(index);
                this.onChanged();
            } else {
                this.metricsBuilder_.remove(index);
            }
            return this;
        }

        public MetricDescriptor.Builder getMetricsBuilder(int index) {
            return this.getMetricsFieldBuilder().getBuilder(index);
        }

        @Override
        public MetricDescriptorOrBuilder getMetricsOrBuilder(int index) {
            if (this.metricsBuilder_ == null) {
                return this.metrics_.get(index);
            }
            return this.metricsBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
            if (this.metricsBuilder_ != null) {
                return this.metricsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metrics_);
        }

        public MetricDescriptor.Builder addMetricsBuilder() {
            return this.getMetricsFieldBuilder().addBuilder(MetricDescriptor.getDefaultInstance());
        }

        public MetricDescriptor.Builder addMetricsBuilder(int index) {
            return this.getMetricsFieldBuilder().addBuilder(index, MetricDescriptor.getDefaultInstance());
        }

        public List<MetricDescriptor.Builder> getMetricsBuilderList() {
            return this.getMetricsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> getMetricsFieldBuilder() {
            if (this.metricsBuilder_ == null) {
                this.metricsBuilder_ = new RepeatedFieldBuilderV3(this.metrics_, (this.bitField0_ & 0x40000) == 262144, this.getParentForChildren(), this.isClean());
                this.metrics_ = null;
            }
            return this.metricsBuilder_;
        }

        private void ensureMonitoredResourcesIsMutable() {
            if ((this.bitField0_ & 0x80000) != 524288) {
                this.monitoredResources_ = new ArrayList<MonitoredResourceDescriptor>(this.monitoredResources_);
                this.bitField0_ |= 0x80000;
            }
        }

        @Override
        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            if (this.monitoredResourcesBuilder_ == null) {
                return Collections.unmodifiableList(this.monitoredResources_);
            }
            return this.monitoredResourcesBuilder_.getMessageList();
        }

        @Override
        public int getMonitoredResourcesCount() {
            if (this.monitoredResourcesBuilder_ == null) {
                return this.monitoredResources_.size();
            }
            return this.monitoredResourcesBuilder_.getCount();
        }

        @Override
        public MonitoredResourceDescriptor getMonitoredResources(int index) {
            if (this.monitoredResourcesBuilder_ == null) {
                return this.monitoredResources_.get(index);
            }
            return this.monitoredResourcesBuilder_.getMessage(index);
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor value) {
            if (this.monitoredResourcesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(index, value);
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.setMessage(index, value);
            }
            return this;
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.monitoredResourcesBuilder_ == null) {
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(index, builderForValue.build());
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor value) {
            if (this.monitoredResourcesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(value);
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.addMessage(value);
            }
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor value) {
            if (this.monitoredResourcesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(index, value);
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.addMessage(index, value);
            }
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.monitoredResourcesBuilder_ == null) {
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(builderForValue.build());
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            if (this.monitoredResourcesBuilder_ == null) {
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(index, builderForValue.build());
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> values) {
            if (this.monitoredResourcesBuilder_ == null) {
                this.ensureMonitoredResourcesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.monitoredResources_);
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.addAllMessages(values);
            }
            return this;
        }

        public Builder clearMonitoredResources() {
            if (this.monitoredResourcesBuilder_ == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= 0xFFF7FFFF;
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.clear();
            }
            return this;
        }

        public Builder removeMonitoredResources(int index) {
            if (this.monitoredResourcesBuilder_ == null) {
                this.ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.remove(index);
                this.onChanged();
            } else {
                this.monitoredResourcesBuilder_.remove(index);
            }
            return this;
        }

        public MonitoredResourceDescriptor.Builder getMonitoredResourcesBuilder(int index) {
            return this.getMonitoredResourcesFieldBuilder().getBuilder(index);
        }

        @Override
        public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int index) {
            if (this.monitoredResourcesBuilder_ == null) {
                return this.monitoredResources_.get(index);
            }
            return this.monitoredResourcesBuilder_.getMessageOrBuilder(index);
        }

        @Override
        public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
            if (this.monitoredResourcesBuilder_ != null) {
                return this.monitoredResourcesBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.monitoredResources_);
        }

        public MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder() {
            return this.getMonitoredResourcesFieldBuilder().addBuilder(MonitoredResourceDescriptor.getDefaultInstance());
        }

        public MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder(int index) {
            return this.getMonitoredResourcesFieldBuilder().addBuilder(index, MonitoredResourceDescriptor.getDefaultInstance());
        }

        public List<MonitoredResourceDescriptor.Builder> getMonitoredResourcesBuilderList() {
            return this.getMonitoredResourcesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesFieldBuilder() {
            if (this.monitoredResourcesBuilder_ == null) {
                this.monitoredResourcesBuilder_ = new RepeatedFieldBuilderV3(this.monitoredResources_, (this.bitField0_ & 0x80000) == 524288, this.getParentForChildren(), this.isClean());
                this.monitoredResources_ = null;
            }
            return this.monitoredResourcesBuilder_;
        }

        @Override
        public boolean hasBilling() {
            return this.billingBuilder_ != null || this.billing_ != null;
        }

        @Override
        public Billing getBilling() {
            if (this.billingBuilder_ == null) {
                return this.billing_ == null ? Billing.getDefaultInstance() : this.billing_;
            }
            return this.billingBuilder_.getMessage();
        }

        public Builder setBilling(Billing value) {
            if (this.billingBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.billing_ = value;
                this.onChanged();
            } else {
                this.billingBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setBilling(Billing.Builder builderForValue) {
            if (this.billingBuilder_ == null) {
                this.billing_ = builderForValue.build();
                this.onChanged();
            } else {
                this.billingBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeBilling(Billing value) {
            if (this.billingBuilder_ == null) {
                this.billing_ = this.billing_ != null ? Billing.newBuilder(this.billing_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.billingBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearBilling() {
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
                this.onChanged();
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            return this;
        }

        public Billing.Builder getBillingBuilder() {
            this.onChanged();
            return this.getBillingFieldBuilder().getBuilder();
        }

        @Override
        public BillingOrBuilder getBillingOrBuilder() {
            if (this.billingBuilder_ != null) {
                return this.billingBuilder_.getMessageOrBuilder();
            }
            return this.billing_ == null ? Billing.getDefaultInstance() : this.billing_;
        }

        private SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> getBillingFieldBuilder() {
            if (this.billingBuilder_ == null) {
                this.billingBuilder_ = new SingleFieldBuilderV3(this.getBilling(), this.getParentForChildren(), this.isClean());
                this.billing_ = null;
            }
            return this.billingBuilder_;
        }

        @Override
        public boolean hasLogging() {
            return this.loggingBuilder_ != null || this.logging_ != null;
        }

        @Override
        public Logging getLogging() {
            if (this.loggingBuilder_ == null) {
                return this.logging_ == null ? Logging.getDefaultInstance() : this.logging_;
            }
            return this.loggingBuilder_.getMessage();
        }

        public Builder setLogging(Logging value) {
            if (this.loggingBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.logging_ = value;
                this.onChanged();
            } else {
                this.loggingBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setLogging(Logging.Builder builderForValue) {
            if (this.loggingBuilder_ == null) {
                this.logging_ = builderForValue.build();
                this.onChanged();
            } else {
                this.loggingBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeLogging(Logging value) {
            if (this.loggingBuilder_ == null) {
                this.logging_ = this.logging_ != null ? Logging.newBuilder(this.logging_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.loggingBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearLogging() {
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
                this.onChanged();
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            return this;
        }

        public Logging.Builder getLoggingBuilder() {
            this.onChanged();
            return this.getLoggingFieldBuilder().getBuilder();
        }

        @Override
        public LoggingOrBuilder getLoggingOrBuilder() {
            if (this.loggingBuilder_ != null) {
                return this.loggingBuilder_.getMessageOrBuilder();
            }
            return this.logging_ == null ? Logging.getDefaultInstance() : this.logging_;
        }

        private SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> getLoggingFieldBuilder() {
            if (this.loggingBuilder_ == null) {
                this.loggingBuilder_ = new SingleFieldBuilderV3(this.getLogging(), this.getParentForChildren(), this.isClean());
                this.logging_ = null;
            }
            return this.loggingBuilder_;
        }

        @Override
        public boolean hasMonitoring() {
            return this.monitoringBuilder_ != null || this.monitoring_ != null;
        }

        @Override
        public Monitoring getMonitoring() {
            if (this.monitoringBuilder_ == null) {
                return this.monitoring_ == null ? Monitoring.getDefaultInstance() : this.monitoring_;
            }
            return this.monitoringBuilder_.getMessage();
        }

        public Builder setMonitoring(Monitoring value) {
            if (this.monitoringBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.monitoring_ = value;
                this.onChanged();
            } else {
                this.monitoringBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builderForValue) {
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = builderForValue.build();
                this.onChanged();
            } else {
                this.monitoringBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeMonitoring(Monitoring value) {
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = this.monitoring_ != null ? Monitoring.newBuilder(this.monitoring_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.monitoringBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearMonitoring() {
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
                this.onChanged();
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            return this;
        }

        public Monitoring.Builder getMonitoringBuilder() {
            this.onChanged();
            return this.getMonitoringFieldBuilder().getBuilder();
        }

        @Override
        public MonitoringOrBuilder getMonitoringOrBuilder() {
            if (this.monitoringBuilder_ != null) {
                return this.monitoringBuilder_.getMessageOrBuilder();
            }
            return this.monitoring_ == null ? Monitoring.getDefaultInstance() : this.monitoring_;
        }

        private SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> getMonitoringFieldBuilder() {
            if (this.monitoringBuilder_ == null) {
                this.monitoringBuilder_ = new SingleFieldBuilderV3(this.getMonitoring(), this.getParentForChildren(), this.isClean());
                this.monitoring_ = null;
            }
            return this.monitoringBuilder_;
        }

        @Override
        public boolean hasSystemParameters() {
            return this.systemParametersBuilder_ != null || this.systemParameters_ != null;
        }

        @Override
        public SystemParameters getSystemParameters() {
            if (this.systemParametersBuilder_ == null) {
                return this.systemParameters_ == null ? SystemParameters.getDefaultInstance() : this.systemParameters_;
            }
            return this.systemParametersBuilder_.getMessage();
        }

        public Builder setSystemParameters(SystemParameters value) {
            if (this.systemParametersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.systemParameters_ = value;
                this.onChanged();
            } else {
                this.systemParametersBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builderForValue) {
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = builderForValue.build();
                this.onChanged();
            } else {
                this.systemParametersBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters value) {
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = this.systemParameters_ != null ? SystemParameters.newBuilder(this.systemParameters_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.systemParametersBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearSystemParameters() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
                this.onChanged();
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            return this;
        }

        public SystemParameters.Builder getSystemParametersBuilder() {
            this.onChanged();
            return this.getSystemParametersFieldBuilder().getBuilder();
        }

        @Override
        public SystemParametersOrBuilder getSystemParametersOrBuilder() {
            if (this.systemParametersBuilder_ != null) {
                return this.systemParametersBuilder_.getMessageOrBuilder();
            }
            return this.systemParameters_ == null ? SystemParameters.getDefaultInstance() : this.systemParameters_;
        }

        private SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> getSystemParametersFieldBuilder() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParametersBuilder_ = new SingleFieldBuilderV3(this.getSystemParameters(), this.getParentForChildren(), this.isClean());
                this.systemParameters_ = null;
            }
            return this.systemParametersBuilder_;
        }

        @Override
        public boolean hasSourceInfo() {
            return this.sourceInfoBuilder_ != null || this.sourceInfo_ != null;
        }

        @Override
        public SourceInfo getSourceInfo() {
            if (this.sourceInfoBuilder_ == null) {
                return this.sourceInfo_ == null ? SourceInfo.getDefaultInstance() : this.sourceInfo_;
            }
            return this.sourceInfoBuilder_.getMessage();
        }

        public Builder setSourceInfo(SourceInfo value) {
            if (this.sourceInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.sourceInfo_ = value;
                this.onChanged();
            } else {
                this.sourceInfoBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builderForValue) {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = builderForValue.build();
                this.onChanged();
            } else {
                this.sourceInfoBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo value) {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = this.sourceInfo_ != null ? SourceInfo.newBuilder(this.sourceInfo_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.sourceInfoBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearSourceInfo() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
                this.onChanged();
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            return this;
        }

        public SourceInfo.Builder getSourceInfoBuilder() {
            this.onChanged();
            return this.getSourceInfoFieldBuilder().getBuilder();
        }

        @Override
        public SourceInfoOrBuilder getSourceInfoOrBuilder() {
            if (this.sourceInfoBuilder_ != null) {
                return this.sourceInfoBuilder_.getMessageOrBuilder();
            }
            return this.sourceInfo_ == null ? SourceInfo.getDefaultInstance() : this.sourceInfo_;
        }

        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> getSourceInfoFieldBuilder() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfoBuilder_ = new SingleFieldBuilderV3(this.getSourceInfo(), this.getParentForChildren(), this.isClean());
                this.sourceInfo_ = null;
            }
            return this.sourceInfoBuilder_;
        }

        @Override
        public boolean hasExperimental() {
            return this.experimentalBuilder_ != null || this.experimental_ != null;
        }

        @Override
        public Experimental getExperimental() {
            if (this.experimentalBuilder_ == null) {
                return this.experimental_ == null ? Experimental.getDefaultInstance() : this.experimental_;
            }
            return this.experimentalBuilder_.getMessage();
        }

        public Builder setExperimental(Experimental value) {
            if (this.experimentalBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.experimental_ = value;
                this.onChanged();
            } else {
                this.experimentalBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setExperimental(Experimental.Builder builderForValue) {
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = builderForValue.build();
                this.onChanged();
            } else {
                this.experimentalBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeExperimental(Experimental value) {
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = this.experimental_ != null ? Experimental.newBuilder(this.experimental_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.experimentalBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearExperimental() {
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = null;
                this.onChanged();
            } else {
                this.experimental_ = null;
                this.experimentalBuilder_ = null;
            }
            return this;
        }

        public Experimental.Builder getExperimentalBuilder() {
            this.onChanged();
            return this.getExperimentalFieldBuilder().getBuilder();
        }

        @Override
        public ExperimentalOrBuilder getExperimentalOrBuilder() {
            if (this.experimentalBuilder_ != null) {
                return this.experimentalBuilder_.getMessageOrBuilder();
            }
            return this.experimental_ == null ? Experimental.getDefaultInstance() : this.experimental_;
        }

        private SingleFieldBuilderV3<Experimental, Experimental.Builder, ExperimentalOrBuilder> getExperimentalFieldBuilder() {
            if (this.experimentalBuilder_ == null) {
                this.experimentalBuilder_ = new SingleFieldBuilderV3(this.getExperimental(), this.getParentForChildren(), this.isClean());
                this.experimental_ = null;
            }
            return this.experimentalBuilder_;
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

