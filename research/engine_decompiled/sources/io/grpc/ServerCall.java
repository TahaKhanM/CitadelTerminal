/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import javax.annotation.Nullable;

@DoNotMock(value="Use InProcessTransport and make a fake server instead")
public abstract class ServerCall<ReqT, RespT> {
    public abstract void request(int var1);

    public abstract void sendHeaders(Metadata var1);

    public abstract void sendMessage(RespT var1);

    public boolean isReady() {
        return true;
    }

    public abstract void close(Status var1, Metadata var2);

    public abstract boolean isCancelled();

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public void setMessageCompression(boolean enabled) {
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public void setCompression(String compressor) {
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1779")
    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    @Nullable
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2924")
    public String getAuthority() {
        return null;
    }

    public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();

    public static abstract class Listener<ReqT> {
        public void onMessage(ReqT message) {
        }

        public void onHalfClose() {
        }

        public void onCancel() {
        }

        public void onComplete() {
        }

        public void onReady() {
        }
    }
}

