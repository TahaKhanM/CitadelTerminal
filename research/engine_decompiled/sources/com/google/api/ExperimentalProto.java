/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AnnotationsProto;
import com.google.api.AuthorizationConfigProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class ExperimentalProto {
    static final Descriptors.Descriptor internal_static_google_api_Experimental_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Experimental_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private ExperimentalProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        ExperimentalProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n*google/api/experimental/experimental.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\u001a2google/api/experimental/authorization_config.proto\"F\n\fExperimental\u00126\n\rauthorization\u0018\b \u0001(\u000b2\u001f.google.api.AuthorizationConfigB[\n\u000ecom.google.apiB\u0011ExperimentalProtoP\u0001Z-google.golang.org/genproto/googleapis/api;api\u00a2\u0002\u0004GAPIb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), AuthorizationConfigProto.getDescriptor()}, assigner);
        internal_static_google_api_Experimental_descriptor = ExperimentalProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_Experimental_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Experimental_descriptor, new String[]{"Authorization"});
        AnnotationsProto.getDescriptor();
        AuthorizationConfigProto.getDescriptor();
    }
}

