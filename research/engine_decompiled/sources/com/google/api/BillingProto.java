/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AnnotationsProto;
import com.google.api.MetricProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class BillingProto {
    static final Descriptors.Descriptor internal_static_google_api_Billing_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Billing_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_Billing_BillingDestination_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Billing_BillingDestination_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private BillingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        BillingProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0018google/api/billing.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/metric.proto\"\u0093\u0001\n\u0007Billing\u0012E\n\u0015consumer_destinations\u0018\b \u0003(\u000b2&.google.api.Billing.BillingDestination\u001aA\n\u0012BillingDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007metrics\u0018\u0002 \u0003(\tBn\n\u000ecom.google.apiB\fBillingProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig\u00a2\u0002\u0004GAPIb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), MetricProto.getDescriptor()}, assigner);
        internal_static_google_api_Billing_descriptor = BillingProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_Billing_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Billing_descriptor, new String[]{"ConsumerDestinations"});
        internal_static_google_api_Billing_BillingDestination_descriptor = internal_static_google_api_Billing_descriptor.getNestedTypes().get(0);
        internal_static_google_api_Billing_BillingDestination_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Billing_BillingDestination_descriptor, new String[]{"MonitoredResource", "Metrics"});
        AnnotationsProto.getDescriptor();
        MetricProto.getDescriptor();
    }
}

