/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

public final class LoggingConfigProto {
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogSink_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogSink_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListSinksRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListSinksResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListSinksResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_GetSinkRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_GetSinkRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_CreateSinkRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_UpdateSinkRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_UpdateSinkRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_DeleteSinkRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_DeleteSinkRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogExclusion_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogExclusion_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListExclusionsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListExclusionsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListExclusionsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListExclusionsResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_GetExclusionRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_GetExclusionRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_CreateExclusionRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_CreateExclusionRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_UpdateExclusionRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_UpdateExclusionRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_DeleteExclusionRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_DeleteExclusionRequest_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private LoggingConfigProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        LoggingConfigProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n&google/logging/v2/logging_config.proto\u0012\u0011google.logging.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"\u00e3\u0002\n\u0007LogSink\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdestination\u0018\u0003 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0005 \u0001(\t\u0012K\n\u0015output_version_format\u0018\u0006 \u0001(\u000e2(.google.logging.v2.LogSink.VersionFormatB\u0002\u0018\u0001\u0012\u0017\n\u000fwriter_identity\u0018\b \u0001(\t\u0012\u0018\n\u0010include_children\u0018\t \u0001(\b\u00122\n\nstart_time\u0018\n \u0001(\u000b2\u001a.google.protobuf.TimestampB\u0002\u0018\u0001\u00120\n\bend_time\u0018\u000b \u0001(\u000b2\u001a.google.protobuf.TimestampB\u0002\u0018\u0001\"?\n\rVersionFormat\u0012\u001e\n\u001aVERSION_FORMAT_UNSPECIFIED\u0010\u0000\u0012\u0006\n\u0002V2\u0010\u0001\u0012\u0006\n\u0002V1\u0010\u0002\"I\n\u0010ListSinksRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0012\n\npage_token\u0018\u0002 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0003 \u0001(\u0005\"W\n\u0011ListSinksResponse\u0012)\n\u0005sinks\u0018\u0001 \u0003(\u000b2\u001a.google.logging.v2.LogSink\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"#\n\u000eGetSinkRequest\u0012\u0011\n\tsink_name\u0018\u0001 \u0001(\t\"m\n\u0011CreateSinkRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012(\n\u0004sink\u0018\u0002 \u0001(\u000b2\u001a.google.logging.v2.LogSink\u0012\u001e\n\u0016unique_writer_identity\u0018\u0003 \u0001(\b\"\u00a1\u0001\n\u0011UpdateSinkRequest\u0012\u0011\n\tsink_name\u0018\u0001 \u0001(\t\u0012(\n\u0004sink\u0018\u0002 \u0001(\u000b2\u001a.google.logging.v2.LogSink\u0012\u001e\n\u0016unique_writer_identity\u0018\u0003 \u0001(\b\u0012/\n\u000bupdate_mask\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"&\n\u0011DeleteSinkRequest\u0012\u0011\n\tsink_name\u0018\u0001 \u0001(\t\"S\n\fLogExclusion\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0003 \u0001(\t\u0012\u0010\n\bdisabled\u0018\u0004 \u0001(\b\"N\n\u0015ListExclusionsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0012\n\npage_token\u0018\u0002 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0003 \u0001(\u0005\"f\n\u0016ListExclusionsResponse\u00123\n\nexclusions\u0018\u0001 \u0003(\u000b2\u001f.google.logging.v2.LogExclusion\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"#\n\u0013GetExclusionRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\\\n\u0016CreateExclusionRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u00122\n\texclusion\u0018\u0002 \u0001(\u000b2\u001f.google.logging.v2.LogExclusion\"\u008b\u0001\n\u0016UpdateExclusionRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00122\n\texclusion\u0018\u0002 \u0001(\u000b2\u001f.google.logging.v2.LogExclusion\u0012/\n\u000bupdate_mask\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"&\n\u0016DeleteExclusionRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t2\u0094\u0019\n\u000fConfigServiceV2\u0012\u0087\u0002\n\tListSinks\u0012#.google.logging.v2.ListSinksRequest\u001a$.google.logging.v2.ListSinksResponse\"\u00ae\u0001\u0082\u00d3\u00e4\u0093\u0002\u00a7\u0001\u0012\u0016/v2/{parent=*/*}/sinksZ\u001f\u0012\u001d/v2/{parent=projects/*}/sinksZ$\u0012\"/v2/{parent=organizations/*}/sinksZ\u001e\u0012\u001c/v2/{parent=folders/*}/sinksZ&\u0012$/v2/{parent=billingAccounts/*}/sinks\u0012\u0092\u0002\n\u0007GetSink\u0012!.google.logging.v2.GetSinkRequest\u001a\u001a.google.logging.v2.LogSink\"\u00c7\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c0\u0001\u0012\u001b/v2/{sink_name=*/*/sinks/*}Z$\u0012\"/v2/{sink_name=projects/*/sinks/*}Z)\u0012'/v2/{sink_name=organizations/*/sinks/*}Z#\u0012!/v2/{sink_name=folders/*/sinks/*}Z+\u0012)/v2/{sink_name=billingAccounts/*/sinks/*}\u0012\u009d\u0002\n\nCreateSink\u0012$.google.logging.v2.CreateSinkRequest\u001a\u001a.google.logging.v2.LogSink\"\u00cc\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c5\u0001\"\u0016/v2/{parent=*/*}/sinks:\u0004sinkZ%\"\u001d/v2/{parent=projects/*}/sinks:\u0004sinkZ*\"\"/v2/{parent=organizations/*}/sinks:\u0004sinkZ$\"\u001c/v2/{parent=folders/*}/sinks:\u0004sinkZ,\"$/v2/{parent=billingAccounts/*}/sinks:\u0004sink\u0012\u00f1\u0003\n\nUpdateSink\u0012$.google.logging.v2.UpdateSinkRequest\u001a\u001a.google.logging.v2.LogSink\"\u00a0\u0003\u0082\u00d3\u00e4\u0093\u0002\u0099\u0003\u001a\u001b/v2/{sink_name=*/*/sinks/*}:\u0004sinkZ*\u001a\"/v2/{sink_name=projects/*/sinks/*}:\u0004sinkZ/\u001a'/v2/{sink_name=organizations/*/sinks/*}:\u0004sinkZ)\u001a!/v2/{sink_name=folders/*/sinks/*}:\u0004sinkZ1\u001a)/v2/{sink_name=billingAccounts/*/sinks/*}:\u0004sinkZ*2\"/v2/{sink_name=projects/*/sinks/*}:\u0004sinkZ/2'/v2/{sink_name=organizations/*/sinks/*}:\u0004sinkZ)2!/v2/{sink_name=folders/*/sinks/*}:\u0004sinkZ12)/v2/{sink_name=billingAccounts/*/sinks/*}:\u0004sink\u0012\u0094\u0002\n\nDeleteSink\u0012$.google.logging.v2.DeleteSinkRequest\u001a\u0016.google.protobuf.Empty\"\u00c7\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c0\u0001*\u001b/v2/{sink_name=*/*/sinks/*}Z$*\"/v2/{sink_name=projects/*/sinks/*}Z)*'/v2/{sink_name=organizations/*/sinks/*}Z#*!/v2/{sink_name=folders/*/sinks/*}Z+*)/v2/{sink_name=billingAccounts/*/sinks/*}\u0012\u00af\u0002\n\u000eListExclusions\u0012(.google.logging.v2.ListExclusionsRequest\u001a).google.logging.v2.ListExclusionsResponse\"\u00c7\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c0\u0001\u0012\u001b/v2/{parent=*/*}/exclusionsZ$\u0012\"/v2/{parent=projects/*}/exclusionsZ)\u0012'/v2/{parent=organizations/*}/exclusionsZ#\u0012!/v2/{parent=folders/*}/exclusionsZ+\u0012)/v2/{parent=billingAccounts/*}/exclusions\u0012\u00a1\u0002\n\fGetExclusion\u0012&.google.logging.v2.GetExclusionRequest\u001a\u001f.google.logging.v2.LogExclusion\"\u00c7\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c0\u0001\u0012\u001b/v2/{name=*/*/exclusions/*}Z$\u0012\"/v2/{name=projects/*/exclusions/*}Z)\u0012'/v2/{name=organizations/*/exclusions/*}Z#\u0012!/v2/{name=folders/*/exclusions/*}Z+\u0012)/v2/{name=billingAccounts/*/exclusions/*}\u0012\u00de\u0002\n\u000fCreateExclusion\u0012).google.logging.v2.CreateExclusionRequest\u001a\u001f.google.logging.v2.LogExclusion\"\u00fe\u0001\u0082\u00d3\u00e4\u0093\u0002\u00f7\u0001\"\u001b/v2/{parent=*/*}/exclusions:\texclusionZ/\"\"/v2/{parent=projects/*}/exclusions:\texclusionZ4\"'/v2/{parent=organizations/*}/exclusions:\texclusionZ.\"!/v2/{parent=folders/*}/exclusions:\texclusionZ6\")/v2/{parent=billingAccounts/*}/exclusions:\texclusion\u0012\u00de\u0002\n\u000fUpdateExclusion\u0012).google.logging.v2.UpdateExclusionRequest\u001a\u001f.google.logging.v2.LogExclusion\"\u00fe\u0001\u0082\u00d3\u00e4\u0093\u0002\u00f7\u00012\u001b/v2/{name=*/*/exclusions/*}:\texclusionZ/2\"/v2/{name=projects/*/exclusions/*}:\texclusionZ42'/v2/{name=organizations/*/exclusions/*}:\texclusionZ.2!/v2/{name=folders/*/exclusions/*}:\texclusionZ62)/v2/{name=billingAccounts/*/exclusions/*}:\texclusion\u0012\u009e\u0002\n\u000fDeleteExclusion\u0012).google.logging.v2.DeleteExclusionRequest\u001a\u0016.google.protobuf.Empty\"\u00c7\u0001\u0082\u00d3\u00e4\u0093\u0002\u00c0\u0001*\u001b/v2/{name=*/*/exclusions/*}Z$*\"/v2/{name=projects/*/exclusions/*}Z)*'/v2/{name=organizations/*/exclusions/*}Z#*!/v2/{name=folders/*/exclusions/*}Z+*)/v2/{name=billingAccounts/*/exclusions/*}B\u009e\u0001\n\u0015com.google.logging.v2B\u0012LoggingConfigProtoP\u0001Z8google.golang.org/genproto/googleapis/logging/v2;logging\u00f8\u0001\u0001\u00aa\u0002\u0017Google.Cloud.Logging.V2\u00ca\u0002\u0017Google\\Cloud\\Logging\\V2b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), TimestampProto.getDescriptor()}, assigner);
        internal_static_google_logging_v2_LogSink_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_logging_v2_LogSink_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogSink_descriptor, new String[]{"Name", "Destination", "Filter", "OutputVersionFormat", "WriterIdentity", "IncludeChildren", "StartTime", "EndTime"});
        internal_static_google_logging_v2_ListSinksRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListSinksRequest_descriptor, new String[]{"Parent", "PageToken", "PageSize"});
        internal_static_google_logging_v2_ListSinksResponse_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_logging_v2_ListSinksResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListSinksResponse_descriptor, new String[]{"Sinks", "NextPageToken"});
        internal_static_google_logging_v2_GetSinkRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_logging_v2_GetSinkRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_GetSinkRequest_descriptor, new String[]{"SinkName"});
        internal_static_google_logging_v2_CreateSinkRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(4);
        internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_CreateSinkRequest_descriptor, new String[]{"Parent", "Sink", "UniqueWriterIdentity"});
        internal_static_google_logging_v2_UpdateSinkRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(5);
        internal_static_google_logging_v2_UpdateSinkRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_UpdateSinkRequest_descriptor, new String[]{"SinkName", "Sink", "UniqueWriterIdentity", "UpdateMask"});
        internal_static_google_logging_v2_DeleteSinkRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(6);
        internal_static_google_logging_v2_DeleteSinkRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_DeleteSinkRequest_descriptor, new String[]{"SinkName"});
        internal_static_google_logging_v2_LogExclusion_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(7);
        internal_static_google_logging_v2_LogExclusion_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogExclusion_descriptor, new String[]{"Name", "Description", "Filter", "Disabled"});
        internal_static_google_logging_v2_ListExclusionsRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(8);
        internal_static_google_logging_v2_ListExclusionsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListExclusionsRequest_descriptor, new String[]{"Parent", "PageToken", "PageSize"});
        internal_static_google_logging_v2_ListExclusionsResponse_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(9);
        internal_static_google_logging_v2_ListExclusionsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListExclusionsResponse_descriptor, new String[]{"Exclusions", "NextPageToken"});
        internal_static_google_logging_v2_GetExclusionRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(10);
        internal_static_google_logging_v2_GetExclusionRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_GetExclusionRequest_descriptor, new String[]{"Name"});
        internal_static_google_logging_v2_CreateExclusionRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(11);
        internal_static_google_logging_v2_CreateExclusionRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_CreateExclusionRequest_descriptor, new String[]{"Parent", "Exclusion"});
        internal_static_google_logging_v2_UpdateExclusionRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(12);
        internal_static_google_logging_v2_UpdateExclusionRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_UpdateExclusionRequest_descriptor, new String[]{"Name", "Exclusion", "UpdateMask"});
        internal_static_google_logging_v2_DeleteExclusionRequest_descriptor = LoggingConfigProto.getDescriptor().getMessageTypes().get(13);
        internal_static_google_logging_v2_DeleteExclusionRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_DeleteExclusionRequest_descriptor, new String[]{"Name"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(AnnotationsProto.http);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        AnnotationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        TimestampProto.getDescriptor();
    }
}

