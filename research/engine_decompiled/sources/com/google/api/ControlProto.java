/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class ControlProto {
    static final Descriptors.Descriptor internal_static_google_api_Control_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Control_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private ControlProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        ControlProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0018google/api/control.proto\u0012\ngoogle.api\"\u001e\n\u0007Control\u0012\u0013\n\u000benvironment\u0018\u0001 \u0001(\tBn\n\u000ecom.google.apiB\fControlProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig\u00a2\u0002\u0004GAPIb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_google_api_Control_descriptor = ControlProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_Control_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Control_descriptor, new String[]{"Environment"});
    }
}

