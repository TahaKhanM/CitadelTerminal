/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.AnnotationsProto;
import com.google.api.DistributionProto;
import com.google.api.MetricProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessageV3;

public final class LoggingMetricsProto {
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogMetric_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogMetric_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogMetricsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogMetricsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_ListLogMetricsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_ListLogMetricsResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_GetLogMetricRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_GetLogMetricRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_CreateLogMetricRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_CreateLogMetricRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_UpdateLogMetricRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_logging_v2_DeleteLogMetricRequest_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private LoggingMetricsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        LoggingMetricsProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n'google/logging/v2/logging_metrics.proto\u0012\u0011google.logging.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u001dgoogle/api/distribution.proto\u001a\u0017google/api/metric.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\"\u00b1\u0003\n\tLogMetric\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0003 \u0001(\t\u00127\n\u0011metric_descriptor\u0018\u0005 \u0001(\u000b2\u001c.google.api.MetricDescriptor\u0012\u0017\n\u000fvalue_extractor\u0018\u0006 \u0001(\t\u0012K\n\u0010label_extractors\u0018\u0007 \u0003(\u000b21.google.logging.v2.LogMetric.LabelExtractorsEntry\u0012>\n\u000ebucket_options\u0018\b \u0001(\u000b2&.google.api.Distribution.BucketOptions\u0012<\n\u0007version\u0018\u0004 \u0001(\u000e2'.google.logging.v2.LogMetric.ApiVersionB\u0002\u0018\u0001\u001a6\n\u0014LabelExtractorsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"\u001c\n\nApiVersion\u0012\u0006\n\u0002V2\u0010\u0000\u0012\u0006\n\u0002V1\u0010\u0001\"N\n\u0015ListLogMetricsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0012\n\npage_token\u0018\u0002 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0003 \u0001(\u0005\"`\n\u0016ListLogMetricsResponse\u0012-\n\u0007metrics\u0018\u0001 \u0003(\u000b2\u001c.google.logging.v2.LogMetric\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"*\n\u0013GetLogMetricRequest\u0012\u0013\n\u000bmetric_name\u0018\u0001 \u0001(\t\"V\n\u0016CreateLogMetricRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012,\n\u0006metric\u0018\u0002 \u0001(\u000b2\u001c.google.logging.v2.LogMetric\"[\n\u0016UpdateLogMetricRequest\u0012\u0013\n\u000bmetric_name\u0018\u0001 \u0001(\t\u0012,\n\u0006metric\u0018\u0002 \u0001(\u000b2\u001c.google.logging.v2.LogMetric\"-\n\u0016DeleteLogMetricRequest\u0012\u0013\n\u000bmetric_name\u0018\u0001 \u0001(\t2\u00d4\u0005\n\u0010MetricsServiceV2\u0012\u008e\u0001\n\u000eListLogMetrics\u0012(.google.logging.v2.ListLogMetricsRequest\u001a).google.logging.v2.ListLogMetricsResponse\"'\u0082\u00d3\u00e4\u0093\u0002!\u0012\u001f/v2/{parent=projects/*}/metrics\u0012\u0084\u0001\n\fGetLogMetric\u0012&.google.logging.v2.GetLogMetricRequest\u001a\u001c.google.logging.v2.LogMetric\".\u0082\u00d3\u00e4\u0093\u0002(\u0012&/v2/{metric_name=projects/*/metrics/*}\u0012\u008b\u0001\n\u000fCreateLogMetric\u0012).google.logging.v2.CreateLogMetricRequest\u001a\u001c.google.logging.v2.LogMetric\"/\u0082\u00d3\u00e4\u0093\u0002)\"\u001f/v2/{parent=projects/*}/metrics:\u0006metric\u0012\u0092\u0001\n\u000fUpdateLogMetric\u0012).google.logging.v2.UpdateLogMetricRequest\u001a\u001c.google.logging.v2.LogMetric\"6\u0082\u00d3\u00e4\u0093\u00020\u001a&/v2/{metric_name=projects/*/metrics/*}:\u0006metric\u0012\u0084\u0001\n\u000fDeleteLogMetric\u0012).google.logging.v2.DeleteLogMetricRequest\u001a\u0016.google.protobuf.Empty\".\u0082\u00d3\u00e4\u0093\u0002(*&/v2/{metric_name=projects/*/metrics/*}B\u009f\u0001\n\u0015com.google.logging.v2B\u0013LoggingMetricsProtoP\u0001Z8google.golang.org/genproto/googleapis/logging/v2;logging\u00f8\u0001\u0001\u00aa\u0002\u0017Google.Cloud.Logging.V2\u00ca\u0002\u0017Google\\Cloud\\Logging\\V2b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), DistributionProto.getDescriptor(), MetricProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor()}, assigner);
        internal_static_google_logging_v2_LogMetric_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_logging_v2_LogMetric_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogMetric_descriptor, new String[]{"Name", "Description", "Filter", "MetricDescriptor", "ValueExtractor", "LabelExtractors", "BucketOptions", "Version"});
        internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_descriptor = internal_static_google_logging_v2_LogMetric_descriptor.getNestedTypes().get(0);
        internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_LogMetric_LabelExtractorsEntry_descriptor, new String[]{"Key", "Value"});
        internal_static_google_logging_v2_ListLogMetricsRequest_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_logging_v2_ListLogMetricsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogMetricsRequest_descriptor, new String[]{"Parent", "PageToken", "PageSize"});
        internal_static_google_logging_v2_ListLogMetricsResponse_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_logging_v2_ListLogMetricsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_ListLogMetricsResponse_descriptor, new String[]{"Metrics", "NextPageToken"});
        internal_static_google_logging_v2_GetLogMetricRequest_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_logging_v2_GetLogMetricRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_GetLogMetricRequest_descriptor, new String[]{"MetricName"});
        internal_static_google_logging_v2_CreateLogMetricRequest_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(4);
        internal_static_google_logging_v2_CreateLogMetricRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_CreateLogMetricRequest_descriptor, new String[]{"Parent", "Metric"});
        internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(5);
        internal_static_google_logging_v2_UpdateLogMetricRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_UpdateLogMetricRequest_descriptor, new String[]{"MetricName", "Metric"});
        internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor = LoggingMetricsProto.getDescriptor().getMessageTypes().get(6);
        internal_static_google_logging_v2_DeleteLogMetricRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_v2_DeleteLogMetricRequest_descriptor, new String[]{"MetricName"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(AnnotationsProto.http);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        AnnotationsProto.getDescriptor();
        DistributionProto.getDescriptor();
        MetricProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
    }
}

