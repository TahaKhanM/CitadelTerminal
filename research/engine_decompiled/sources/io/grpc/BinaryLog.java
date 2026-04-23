/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ExperimentalApi;
import io.grpc.ServerMethodDefinition;
import java.io.Closeable;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/4017")
public abstract class BinaryLog
implements Closeable {
    public static final CallOptions.Key<CallId> CLIENT_CALL_ID_CALLOPTION_KEY = CallOptions.Key.create("binarylog-calloptions-key");

    public abstract <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethodDefinition(ServerMethodDefinition<ReqT, RespT> var1);

    public abstract Channel wrapChannel(Channel var1);

    public static final class CallId {
        public final long hi;
        public final long lo;

        public CallId(long hi, long lo) {
            this.hi = hi;
            this.lo = lo;
        }
    }
}

