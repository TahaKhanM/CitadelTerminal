/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.AnnotationsProto;
import com.google.api.MonitoredResourceProto;
import com.google.logging.type.HttpRequestProto;
import com.google.logging.type.LogSeverityProto;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.TimestampProto;

public final class LogEntryProto {
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogEntry_LabelsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogEntry_LabelsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogEntryOperation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogEntryOperation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogEntrySourceLocation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogEntrySourceLocation_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private LogEntryProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        LogEntryProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n!google/logging/v2/log_entry.proto\u0012\u0011google.logging.v2\u001a\u001cgoogle/api/annotations.proto\u001a#google/api/monitored_resource.proto\u001a&google/logging/type/http_request.proto\u001a&google/logging/type/log_severity.proto\u001a\u0019google/protobuf/any.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"\u00f3\u0005\n\bLogEntry\u0012\u0010\n\blog_name\u0018\f \u0001(\t\u0012/\n\bresource\u0018\b \u0001(\u000b2\u001d.google.api.MonitoredResource\u0012-\n\rproto_payload\u0018\u0002 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000\u0012\u0016\n\ftext_payload\u0018\u0003 \u0001(\tH\u0000\u0012/\n\fjson_payload\u0018\u0006 \u0001(\u000b2\u0017.google.protobuf.StructH\u0000\u0012-\n\ttimestamp\u0018\t \u0001(\u000b2\u001a.google.protobuf.Timestamp\u00125\n\u0011receive_timestamp\u0018\u0018 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u00122\n\bseverity\u0018\n \u0001(\u000e2 .google.logging.type.LogSeverity\u0012\u0011\n\tinsert_id\u0018\u0004 \u0001(\t\u00126\n\fhttp_request\u0018\u0007 \u0001(\u000b2 .google.logging.type.HttpRequest\u00127\n\u0006labels\u0018\u000b \u0003(\u000b2'.google.logging.v2.LogEntry.LabelsEntry\u00127\n\bmetadata\u0018\u0019 \u0001(\u000b2%.google.api.MonitoredResourceMetadata\u00127\n\toperation\u0018\u000f \u0001(\u000b2$.google.logging.v2.LogEntryOperation\u0012\r\n\u0005trace\u0018\u0016 \u0001(\t\u0012\u000f\n\u0007span_id\u0018\u001b \u0001(\t\u0012B\n\u000fsource_location\u0018\u0017 \u0001(\u000b2).google.logging.v2.LogEntrySourceLocation\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001B\t\n\u0007payload\"N\n\u0011LogEntryOperation\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u0010\n\bproducer\u0018\u0002 \u0001(\t\u0012\r\n\u0005first\u0018\u0003 \u0001(\b\u0012\f\n\u0004last\u0018\u0004 \u0001(\b\"F\n\u0016LogEntrySourceLocation\u0012\f\n\u0004file\u0018\u0001 \u0001(\t\u0012\f\n\u0004line\u0018\u0002 \u0001(\u0003\u0012\u0010\n\bfunction\u0018\u0003 \u0001(\tB\u0099\u0001\n\u0015com.google.logging.v2B\rLogEntryProtoP\u0001Z8google.golang.org/genproto/googleapis/logging/v2;logging\u00f8\u0001\u0001\u00aa\u0002\u0017Google.Cloud.Logging.V2\u00ca\u0002\u0017Google\\Cloud\\Logging\\V2b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), MonitoredResourceProto.getDescriptor(), HttpRequestProto.getDescriptor(), LogSeverityProto.getDescriptor(), AnyProto.getDescriptor(), StructProto.getDescriptor(), TimestampProto.getDescriptor()}, assigner);
        internal_static_google_logging_v2_LogEntry_descriptor = LogEntryProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_logging_v2_LogEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogEntry_descriptor, new String[]{"LogName", "Resource", "ProtoPayload", "TextPayload", "JsonPayload", "Timestamp", "ReceiveTimestamp", "Severity", "InsertId", "HttpRequest", "Labels", "Metadata", "Operation", "Trace", "SpanId", "SourceLocation", "Payload"});
        internal_static_google_logging_v2_LogEntry_LabelsEntry_descriptor = internal_static_google_logging_v2_LogEntry_descriptor.getNestedTypes().get(0);
        internal_static_google_logging_v2_LogEntry_LabelsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogEntry_LabelsEntry_descriptor, new String[]{"Key", "Value"});
        internal_static_google_logging_v2_LogEntryOperation_descriptor = LogEntryProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_logging_v2_LogEntryOperation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogEntryOperation_descriptor, new String[]{"Id", "Producer", "First", "Last"});
        internal_static_google_logging_v2_LogEntrySourceLocation_descriptor = LogEntryProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_logging_v2_LogEntrySourceLocation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogEntrySourceLocation_descriptor, new String[]{"File", "Line", "Function"});
        AnnotationsProto.getDescriptor();
        MonitoredResourceProto.getDescriptor();
        HttpRequestProto.getDescriptor();
        LogSeverityProto.getDescriptor();
        AnyProto.getDescriptor();
        StructProto.getDescriptor();
        TimestampProto.getDescriptor();
    }
}

