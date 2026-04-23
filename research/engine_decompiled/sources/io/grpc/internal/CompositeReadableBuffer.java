/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class CompositeReadableBuffer
extends AbstractReadableBuffer {
    private int readableBytes;
    private final Queue<ReadableBuffer> buffers = new ArrayDeque<ReadableBuffer>();

    public void addBuffer(ReadableBuffer buffer) {
        if (!(buffer instanceof CompositeReadableBuffer)) {
            this.buffers.add(buffer);
            this.readableBytes += buffer.readableBytes();
            return;
        }
        CompositeReadableBuffer compositeBuffer = (CompositeReadableBuffer)buffer;
        while (!compositeBuffer.buffers.isEmpty()) {
            ReadableBuffer subBuffer = compositeBuffer.buffers.remove();
            this.buffers.add(subBuffer);
        }
        this.readableBytes += compositeBuffer.readableBytes;
        compositeBuffer.readableBytes = 0;
        compositeBuffer.close();
    }

    @Override
    public int readableBytes() {
        return this.readableBytes;
    }

    @Override
    public int readUnsignedByte() {
        ReadOperation op = new ReadOperation(){

            @Override
            int readInternal(ReadableBuffer buffer, int length) {
                return buffer.readUnsignedByte();
            }
        };
        this.execute(op, 1);
        return op.value;
    }

    @Override
    public void skipBytes(int length) {
        this.execute(new ReadOperation(){

            @Override
            public int readInternal(ReadableBuffer buffer, int length) {
                buffer.skipBytes(length);
                return 0;
            }
        }, length);
    }

    @Override
    public void readBytes(final byte[] dest, final int destOffset, int length) {
        this.execute(new ReadOperation(){
            int currentOffset;
            {
                this.currentOffset = destOffset;
            }

            @Override
            public int readInternal(ReadableBuffer buffer, int length) {
                buffer.readBytes(dest, this.currentOffset, length);
                this.currentOffset += length;
                return 0;
            }
        }, length);
    }

    @Override
    public void readBytes(final ByteBuffer dest) {
        this.execute(new ReadOperation(){

            @Override
            public int readInternal(ReadableBuffer buffer, int length) {
                int prevLimit = dest.limit();
                dest.limit(dest.position() + length);
                buffer.readBytes(dest);
                dest.limit(prevLimit);
                return 0;
            }
        }, dest.remaining());
    }

    @Override
    public void readBytes(final OutputStream dest, int length) throws IOException {
        ReadOperation op = new ReadOperation(){

            @Override
            public int readInternal(ReadableBuffer buffer, int length) throws IOException {
                buffer.readBytes(dest, length);
                return 0;
            }
        };
        this.execute(op, length);
        if (op.isError()) {
            throw op.ex;
        }
    }

    @Override
    public CompositeReadableBuffer readBytes(int length) {
        this.checkReadable(length);
        this.readableBytes -= length;
        CompositeReadableBuffer newBuffer = new CompositeReadableBuffer();
        while (length > 0) {
            ReadableBuffer buffer = this.buffers.peek();
            if (buffer.readableBytes() > length) {
                newBuffer.addBuffer(buffer.readBytes(length));
                length = 0;
                continue;
            }
            newBuffer.addBuffer(this.buffers.poll());
            length -= buffer.readableBytes();
        }
        return newBuffer;
    }

    @Override
    public void close() {
        while (!this.buffers.isEmpty()) {
            this.buffers.remove().close();
        }
    }

    private void execute(ReadOperation op, int length) {
        this.checkReadable(length);
        if (!this.buffers.isEmpty()) {
            this.advanceBufferIfNecessary();
        }
        while (length > 0 && !this.buffers.isEmpty()) {
            ReadableBuffer buffer = this.buffers.peek();
            int lengthToCopy = Math.min(length, buffer.readableBytes());
            op.read(buffer, lengthToCopy);
            if (op.isError()) {
                return;
            }
            length -= lengthToCopy;
            this.readableBytes -= lengthToCopy;
            this.advanceBufferIfNecessary();
        }
        if (length > 0) {
            throw new AssertionError((Object)"Failed executing read operation");
        }
    }

    private void advanceBufferIfNecessary() {
        ReadableBuffer buffer = this.buffers.peek();
        if (buffer.readableBytes() == 0) {
            this.buffers.remove().close();
        }
    }

    private static abstract class ReadOperation {
        int value;
        IOException ex;

        private ReadOperation() {
        }

        final void read(ReadableBuffer buffer, int length) {
            try {
                this.value = this.readInternal(buffer, length);
            }
            catch (IOException e) {
                this.ex = e;
            }
        }

        final boolean isError() {
            return this.ex != null;
        }

        abstract int readInternal(ReadableBuffer var1, int var2) throws IOException;
    }
}

