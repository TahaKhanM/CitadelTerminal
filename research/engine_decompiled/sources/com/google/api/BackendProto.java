/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class BackendProto {
    static final Descriptors.Descriptor internal_static_google_api_Backend_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Backend_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_BackendRule_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_BackendRule_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private BackendProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        BackendProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0018google/api/backend.proto\u0012\ngoogle.api\"1\n\u0007Backend\u0012&\n\u0005rules\u0018\u0001 \u0003(\u000b2\u0017.google.api.BackendRule\"X\n\u000bBackendRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007address\u0018\u0002 \u0001(\t\u0012\u0010\n\bdeadline\u0018\u0003 \u0001(\u0001\u0012\u0014\n\fmin_deadline\u0018\u0004 \u0001(\u0001Bn\n\u000ecom.google.apiB\fBackendProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig\u00a2\u0002\u0004GAPIb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_google_api_Backend_descriptor = BackendProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_Backend_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Backend_descriptor, new String[]{"Rules"});
        internal_static_google_api_BackendRule_descriptor = BackendProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_api_BackendRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_BackendRule_descriptor, new String[]{"Selector", "Address", "Deadline", "MinDeadline"});
    }
}

