/*
 * Decompiled with CFR 0.152.
 */
package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class LatLngProto {
    static final Descriptors.Descriptor internal_static_google_type_LatLng_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_LatLng_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private LatLngProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        LatLngProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0018google/type/latlng.proto\u0012\u000bgoogle.type\"-\n\u0006LatLng\u0012\u0010\n\blatitude\u0018\u0001 \u0001(\u0001\u0012\u0011\n\tlongitude\u0018\u0002 \u0001(\u0001B`\n\u000fcom.google.typeB\u000bLatLngProtoP\u0001Z8google.golang.org/genproto/googleapis/type/latlng;latlng\u00a2\u0002\u0003GTPb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_google_type_LatLng_descriptor = LatLngProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_type_LatLng_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_LatLng_descriptor, new String[]{"Latitude", "Longitude"});
    }
}

