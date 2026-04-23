/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.protobuf;

import com.google.protobuf.Message;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;

public class ProtoUtils {
    public static <T extends Message> MethodDescriptor.Marshaller<T> marshaller(T defaultInstance) {
        return ProtoLiteUtils.marshaller(defaultInstance);
    }

    public static <T extends Message> Metadata.Key<T> keyForProto(T instance) {
        return Metadata.Key.of(instance.getDescriptorForType().getFullName() + "-bin", ProtoUtils.metadataMarshaller(instance));
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/4477")
    public static <T extends Message> Metadata.BinaryMarshaller<T> metadataMarshaller(T instance) {
        return ProtoLiteUtils.metadataMarshaller(instance);
    }

    private ProtoUtils() {
    }
}

