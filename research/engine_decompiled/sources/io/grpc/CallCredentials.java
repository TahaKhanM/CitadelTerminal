/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import java.util.concurrent.Executor;

public interface CallCredentials {
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1914")
    public static final Attributes.Key<SecurityLevel> ATTR_SECURITY_LEVEL = Attributes.Key.create("io.grpc.CallCredentials.securityLevel");
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1914")
    public static final Attributes.Key<String> ATTR_AUTHORITY = Attributes.Key.create("io.grpc.CallCredentials.authority");

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1914")
    public void applyRequestMetadata(MethodDescriptor<?, ?> var1, Attributes var2, Executor var3, MetadataApplier var4);

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1914")
    public void thisUsesUnstableApi();

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1914")
    public static interface MetadataApplier {
        public void apply(Metadata var1);

        public void fail(Status var1);
    }
}

