/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class PolicyProto {
    static final Descriptors.Descriptor internal_static_google_iam_v1_Policy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_Policy_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_Binding_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_Binding_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_PolicyDelta_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_PolicyDelta_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_iam_v1_BindingDelta_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_iam_v1_BindingDelta_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private PolicyProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        PolicyProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u001agoogle/iam/v1/policy.proto\u0012\rgoogle.iam.v1\u001a\u001cgoogle/api/annotations.proto\"Q\n\u0006Policy\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\u0005\u0012(\n\bbindings\u0018\u0004 \u0003(\u000b2\u0016.google.iam.v1.Binding\u0012\f\n\u0004etag\u0018\u0003 \u0001(\f\"(\n\u0007Binding\u0012\f\n\u0004role\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007members\u0018\u0002 \u0003(\t\"B\n\u000bPolicyDelta\u00123\n\u000ebinding_deltas\u0018\u0001 \u0003(\u000b2\u001b.google.iam.v1.BindingDelta\"\u0097\u0001\n\fBindingDelta\u00122\n\u0006action\u0018\u0001 \u0001(\u000e2\".google.iam.v1.BindingDelta.Action\u0012\f\n\u0004role\u0018\u0002 \u0001(\t\u0012\u000e\n\u0006member\u0018\u0003 \u0001(\t\"5\n\u0006Action\u0012\u0016\n\u0012ACTION_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003ADD\u0010\u0001\u0012\n\n\u0006REMOVE\u0010\u0002B\u0083\u0001\n\u0011com.google.iam.v1B\u000bPolicyProtoP\u0001Z0google.golang.org/genproto/googleapis/iam/v1;iam\u00f8\u0001\u0001\u00aa\u0002\u0013Google.Cloud.Iam.V1\u00ca\u0002\u0013Google\\Cloud\\Iam\\V1b\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor()}, assigner);
        internal_static_google_iam_v1_Policy_descriptor = PolicyProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_iam_v1_Policy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_Policy_descriptor, new String[]{"Version", "Bindings", "Etag"});
        internal_static_google_iam_v1_Binding_descriptor = PolicyProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_iam_v1_Binding_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_Binding_descriptor, new String[]{"Role", "Members"});
        internal_static_google_iam_v1_PolicyDelta_descriptor = PolicyProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_iam_v1_PolicyDelta_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_PolicyDelta_descriptor, new String[]{"BindingDeltas"});
        internal_static_google_iam_v1_BindingDelta_descriptor = PolicyProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_iam_v1_BindingDelta_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_iam_v1_BindingDelta_descriptor, new String[]{"Action", "Role", "Member"});
        AnnotationsProto.getDescriptor();
    }
}

