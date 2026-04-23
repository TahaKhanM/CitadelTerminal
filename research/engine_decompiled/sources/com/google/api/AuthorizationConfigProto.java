/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class AuthorizationConfigProto {
    static final Descriptors.Descriptor internal_static_google_api_AuthorizationConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_AuthorizationConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private AuthorizationConfigProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        AuthorizationConfigProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n2google/api/experimental/authorization_config.proto\u0012\ngoogle.api\"'\n\u0013AuthorizationConfig\u0012\u0010\n\bprovider\u0018\u0001 \u0001(\tBb\n\u000ecom.google.apiB\u0018AuthorizationConfigProtoP\u0001Z-google.golang.org/genproto/googleapis/api;api\u00a2\u0002\u0004GAPIb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_google_api_AuthorizationConfig_descriptor = AuthorizationConfigProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_AuthorizationConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_AuthorizationConfig_descriptor, new String[]{"Provider"});
    }
}

