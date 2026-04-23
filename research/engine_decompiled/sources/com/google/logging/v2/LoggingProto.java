/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.AnnotationsProto;
import com.google.api.MonitoredResourceProto;
import com.google.logging.v2.LogEntryProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;
import com.google.rpc.StatusProto;

public final class LoggingProto {
    static final Descriptors.Descriptor internal_static_google_logging_v2_DeleteLogRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_DeleteLogRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_WriteLogEntriesResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_WriteLogEntriesPartialErrors_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogEntriesRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogEntriesResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogsResponse_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private LoggingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        LoggingProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u001fgoogle/logging/v2/logging.proto\u0012\u0011google.logging.v2\u001a\u001cgoogle/api/annotations.proto\u001a#google/api/monitored_resource.proto\u001a!google/logging/v2/log_entry.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a\u001fgoogle/protobuf/timestamp.proto\u001a\u0017google/rpc/status.proto\"$\n\u0010DeleteLogRequest\u0012\u0010\n\blog_name\u0018\u0001 \u0001(\t\"\u00a9\u0002\n\u0016WriteLogEntriesRequest\u0012\u0010\n\blog_name\u0018\u0001 \u0001(\t\u0012/\n\bresource\u0018\u0002 \u0001(\u000b2\u001d.google.api.MonitoredResource\u0012E\n\u0006labels\u0018\u0003 \u0003(\u000b25.google.logging.v2.WriteLogEntriesRequest.LabelsEntry\u0012,\n\u0007entries\u0018\u0004 \u0003(\u000b2\u001b.google.logging.v2.LogEntry\u0012\u0017\n\u000fpartial_success\u0018\u0005 \u0001(\b\u0012\u000f\n\u0007dry_run\u0018\u0006 \u0001(\b\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"\u0019\n\u0017WriteLogEntriesResponse\"\u00c8\u0001\n\u001cWriteLogEntriesPartialErrors\u0012]\n\u0010log_entry_errors\u0018\u0001 \u0003(\u000b2C.google.logging.v2.WriteLogEntriesPartialErrors.LogEntryErrorsEntry\u001aI\n\u0013LogEntryErrorsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\u0005\u0012!\n\u0005value\u0018\u0002 \u0001(\u000b2\u0012.google.rpc.Status:\u00028\u0001\"\u0091\u0001\n\u0015ListLogEntriesRequest\u0012\u0017\n\u000bproject_ids\u0018\u0001 \u0003(\tB\u0002\u0018\u0001\u0012\u0016\n\u000eresource_names\u0018\b \u0003(\t\u0012\u000e\n\u0006filter\u0018\u0002 \u0001(\t\u0012\u0010\n\border_by\u0018\u0003 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0004 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0005 \u0001(\t\"_\n\u0016ListLogEntriesResponse\u0012,\n\u0007entries\u0018\u0001 \u0003(\u000b2\u001b.google.logging.v2.LogEntry\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"P\n'ListMonitoredResourceDescriptorsRequest\u0012\u0011\n\tpage_size\u0018\u0001 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0002 \u0001(\t\"\u008a\u0001\n(ListMonitoredResourceDescriptorsResponse\u0012E\n\u0014resource_descriptors\u0018\u0001 \u0003(\u000b2'.google.api.MonitoredResourceDescriptor\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"H\n\u000fListLogsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\">\n\u0010ListLogsResponse\u0012\u0011\n\tlog_names\u0018\u0003 \u0003(\t\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t2\u00d8\u0007\n\u0010LoggingServiceV2\u0012\u00eb\u0001\n\tDeleteLog\u0012#.google.logging.v2.DeleteLogRequest\u001a\u0016.google.protobuf.Empty\"\u00a0\u0001\u0082\u00d3\u00e4\u0093\u0002\u0099\u0001* /v2/{log_name=projects/*/logs/*}Z'*%/v2/{log_name=organizations/*/logs/*}Z!*\u001f/v2/{log_name=folders/*/logs/*}Z)*'/v2/{log_name=billingAccounts/*/logs/*}\u0012\u0086\u0001\n\u000fWriteLogEntries\u0012).google.logging.v2.WriteLogEntriesRequest\u001a*.google.logging.v2.WriteLogEntriesResponse\"\u001c\u0082\u00d3\u00e4\u0093\u0002\u0016\"\u0011/v2/entries:write:\u0001*\u0012\u0082\u0001\n\u000eListLogEntries\u0012(.google.logging.v2.ListLogEntriesRequest\u001a).google.logging.v2.ListLogEntriesResponse\"\u001b\u0082\u00d3\u00e4\u0093\u0002\u0015\"\u0010/v2/entries:list:\u0001*\u0012\u00c5\u0001\n ListMonitoredResourceDescriptors\u0012:.google.logging.v2.ListMonitoredResourceDescriptorsRequest\u001a;.google.logging.v2.ListMonitoredResourceDescriptorsResponse\"(\u0082\u00d3\u00e4\u0093\u0002\"\u0012 /v2/monitoredResourceDescriptors\u0012\u00ff\u0001\n\bListLogs\u0012\".google.logging.v2.ListLogsRequest\u001a#.google.logging.v2.ListLogsResponse\"\u00a9\u0001\u0082\u00d3\u00e4\u0093\u0002\u00a2\u0001\u0012\u0015/v2/{parent=*/*}/logsZ\u001e\u0012\u001c/v2/{parent=projects/*}/logsZ#\u0012!/v2/{parent=organizations/*}/logsZ\u001d\u0012\u001b/v2/{parent=folders/*}/logsZ%\u0012#/v2/{parent=billingAccounts/*}/logsB\u0098\u0001\n\u0015com.google.logging.v2B\fLoggingProtoP\u0001Z8google.golang.org/genproto/googleapis/logging/v2;logging\u00f8\u0001\u0001\u00aa\u0002\u0017Google.Cloud.Logging.V2\u00ca\u0002\u0017Google\\Cloud\\Logging\\V2b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), MonitoredResourceProto.getDescriptor(), LogEntryProto.getDescriptor(), DurationProto.getDescriptor(), EmptyProto.getDescriptor(), TimestampProto.getDescriptor(), StatusProto.getDescriptor()}, assigner);
        internal_static_google_logging_v2_DeleteLogRequest_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_logging_v2_DeleteLogRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_DeleteLogRequest_descriptor, new String[]{"LogName"});
        internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor, new String[]{"LogName", "Resource", "Labels", "Entries", "PartialSuccess", "DryRun"});
        internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor = internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor.getNestedTypes().get(0);
        internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor, new String[]{"Key", "Value"});
        internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_logging_v2_WriteLogEntriesResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor, new String[0]);
        internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_logging_v2_WriteLogEntriesPartialErrors_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor, new String[]{"LogEntryErrors"});
        internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_descriptor = internal_static_google_logging_v2_WriteLogEntriesPartialErrors_descriptor.getNestedTypes().get(0);
        internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_WriteLogEntriesPartialErrors_LogEntryErrorsEntry_descriptor, new String[]{"Key", "Value"});
        internal_static_google_logging_v2_ListLogEntriesRequest_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(4);
        internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogEntriesRequest_descriptor, new String[]{"ProjectIds", "ResourceNames", "Filter", "OrderBy", "PageSize", "PageToken"});
        internal_static_google_logging_v2_ListLogEntriesResponse_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(5);
        internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogEntriesResponse_descriptor, new String[]{"Entries", "NextPageToken"});
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(6);
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor, new String[]{"PageSize", "PageToken"});
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(7);
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor, new String[]{"ResourceDescriptors", "NextPageToken"});
        internal_static_google_logging_v2_ListLogsRequest_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(8);
        internal_static_google_logging_v2_ListLogsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogsRequest_descriptor, new String[]{"Parent", "PageSize", "PageToken"});
        internal_static_google_logging_v2_ListLogsResponse_descriptor = LoggingProto.getDescriptor().getMessageTypes().get(9);
        internal_static_google_logging_v2_ListLogsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogsResponse_descriptor, new String[]{"LogNames", "NextPageToken"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(AnnotationsProto.http);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        AnnotationsProto.getDescriptor();
        MonitoredResourceProto.getDescriptor();
        LogEntryProto.getDescriptor();
        DurationProto.getDescriptor();
        EmptyProto.getDescriptor();
        TimestampProto.getDescriptor();
        StatusProto.getDescriptor();
    }
}

