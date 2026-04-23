/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1.logging;

import com.google.api.AnnotationsProto;
import com.google.iam.v1.PolicyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class AuditDataProto {
    static final Descriptors.Descriptor internal_static_google_iam_v1_logging_AuditData_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_logging_AuditData_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private AuditDataProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        AuditDataProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n&google/iam/v1/logging/audit_data.proto\u0012\u0015google.iam.v1.logging\u001a\u001cgoogle/api/annotations.proto\u001a\u001agoogle/iam/v1/policy.proto\"=\n\tAuditData\u00120\n\fpolicy_delta\u0018\u0002 \u0001(\u000b2\u001a.google.iam.v1.PolicyDeltaB\u0089\u0001\n\u0019com.google.iam.v1.loggingB\u000eAuditDataProtoP\u0001Z<google.golang.org/genproto/googleapis/iam/v1/logging;logging\u00aa\u0002\u001bGoogle.Cloud.Iam.V1.Loggingb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), PolicyProto.getDescriptor()}, assigner);
        internal_static_google_iam_v1_logging_AuditData_descriptor = AuditDataProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_iam_v1_logging_AuditData_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_logging_AuditData_descriptor, new String[]{"PolicyDelta"});
        AnnotationsProto.getDescriptor();
        PolicyProto.getDescriptor();
    }
}

