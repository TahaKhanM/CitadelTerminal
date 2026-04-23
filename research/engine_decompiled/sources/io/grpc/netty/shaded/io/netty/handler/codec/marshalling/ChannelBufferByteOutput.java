/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jboss.marshalling.ByteOutput
 */
package io.grpc.netty.shaded.io.netty.handler.codec.marshalling;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.jboss.marshalling.ByteOutput;

class ChannelBufferByteOutput
implements ByteOutput {
    private final ByteBuf buffer;

    ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

    public void write(int b) throws IOException {
        this.buffer.writeByte(b);
    }

    public void write(byte[] bytes2) throws IOException {
        this.buffer.writeBytes(bytes2);
    }

    public void write(byte[] bytes2, int srcIndex, int length) throws IOException {
        this.buffer.writeBytes(bytes2, srcIndex, length);
    }

    ByteBuf getBuffer() {
        return this.buffer;
    }
}

