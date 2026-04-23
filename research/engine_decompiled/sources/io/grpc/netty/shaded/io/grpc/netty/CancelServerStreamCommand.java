/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;

class CancelServerStreamCommand
extends WriteQueue.AbstractQueuedCommand {
    private final NettyServerStream.TransportState stream;
    private final Status reason;

    CancelServerStreamCommand(NettyServerStream.TransportState stream, Status reason) {
        this.stream = Preconditions.checkNotNull(stream, "stream");
        this.reason = Preconditions.checkNotNull(reason, "reason");
    }

    NettyServerStream.TransportState stream() {
        return this.stream;
    }

    Status reason() {
        return this.reason;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CancelServerStreamCommand that = (CancelServerStreamCommand)o;
        return Objects.equal(this.stream, that.stream) && Objects.equal(this.reason, that.reason);
    }

    public int hashCode() {
        return Objects.hashCode(this.stream, this.reason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("stream", this.stream).add("reason", this.reason).toString();
    }
}

