/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;
import io.grpc.Status;
import javax.annotation.concurrent.ThreadSafe;

@DoNotMock
@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2861")
@ThreadSafe
public abstract class StreamTracer {
    public void streamClosed(Status status) {
    }

    public void outboundMessage(int seqNo) {
    }

    public void inboundMessage(int seqNo) {
    }

    public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
    }

    public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
    }

    public void outboundWireSize(long bytes2) {
    }

    public void outboundUncompressedSize(long bytes2) {
    }

    public void inboundWireSize(long bytes2) {
    }

    public void inboundUncompressedSize(long bytes2) {
    }
}

