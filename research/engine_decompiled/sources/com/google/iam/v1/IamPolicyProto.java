/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.api.AnnotationsProto;
import com.google.iam.v1.PolicyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class IamPolicyProto {
    static final Descriptors.Descriptor internal_static_google_iam_v1_SetIamPolicyRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_SetIamPolicyRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_GetIamPolicyRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_GetIamPolicyRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_TestIamPermissionsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_TestIamPermissionsResponse_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private IamPolicyProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        IamPolicyProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u001egoogle/iam/v1/iam_policy.proto\u0012\rgoogle.iam.v1\u001a\u001cgoogle/api/annotations.proto\u001a\u001agoogle/iam/v1/policy.proto\"N\n\u0013SetIamPolicyRequest\u0012\u0010\n\bresource\u0018\u0001 \u0001(\t\u0012%\n\u0006policy\u0018\u0002 \u0001(\u000b2\u0015.google.iam.v1.Policy\"'\n\u0013GetIamPolicyRequest\u0012\u0010\n\bresource\u0018\u0001 \u0001(\t\"B\n\u0019TestIamPermissionsRequest\u0012\u0010\n\bresource\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bpermissions\u0018\u0002 \u0003(\t\"1\n\u001aTestIamPermissionsResponse\u0012\u0013\n\u000bpermissions\u0018\u0001 \u0003(\t2\u0094\u0003\n\tIAMPolicy\u0012t\n\fSetIamPolicy\u0012\".google.iam.v1.SetIamPolicyRequest\u001a\u0015.google.iam.v1.Policy\")\u0082\u00d3\u00e4\u0093\u0002#\"\u001e/v1/{resource=**}:setIamPolicy:\u0001*\u0012t\n\fGetIamPolicy\u0012\".google.iam.v1.GetIamPolicyRequest\u001a\u0015.google.iam.v1.Policy\")\u0082\u00d3\u00e4\u0093\u0002#\"\u001e/v1/{resource=**}:getIamPolicy:\u0001*\u0012\u009a\u0001\n\u0012TestIamPermissions\u0012(.google.iam.v1.TestIamPermissionsRequest\u001a).google.iam.v1.TestIamPermissionsResponse\"/\u0082\u00d3\u00e4\u0093\u0002)\"$/v1/{resource=**}:testIamPermissions:\u0001*B\u0086\u0001\n\u0011com.google.iam.v1B\u000eIamPolicyProtoP\u0001Z0google.golang.org/genproto/googleapis/iam/v1;iam\u00f8\u0001\u0001\u00aa\u0002\u0013Google.Cloud.Iam.V1\u00ca\u0002\u0013Google\\Cloud\\Iam\\V1b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), PolicyProto.getDescriptor()}, assigner);
        internal_static_google_iam_v1_SetIamPolicyRequest_descriptor = IamPolicyProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_iam_v1_SetIamPolicyRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_SetIamPolicyRequest_descriptor, new String[]{"Resource", "Policy"});
        internal_static_google_iam_v1_GetIamPolicyRequest_descriptor = IamPolicyProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_iam_v1_GetIamPolicyRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_GetIamPolicyRequest_descriptor, new String[]{"Resource"});
        internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor = IamPolicyProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_iam_v1_TestIamPermissionsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_TestIamPermissionsRequest_descriptor, new String[]{"Resource", "Permissions"});
        internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor = IamPolicyProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_iam_v1_TestIamPermissionsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_TestIamPermissionsResponse_descriptor, new String[]{"Permissions"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(AnnotationsProto.http);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        AnnotationsProto.getDescriptor();
        PolicyProto.getDescriptor();
    }
}

