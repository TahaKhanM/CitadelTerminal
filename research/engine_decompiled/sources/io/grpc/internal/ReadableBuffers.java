/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.grpc.KnownLength;
import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ForwardingReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class ReadableBuffers {
    private static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0]);

    public static ReadableBuffer empty() {
        return EMPTY_BUFFER;
    }

    public static ReadableBuffer wrap(byte[] bytes2) {
        return new ByteArrayWrapper(bytes2, 0, bytes2.length);
    }

    public static ReadableBuffer wrap(byte[] bytes2, int offset, int length) {
        return new ByteArrayWrapper(bytes2, offset, length);
    }

    public static ReadableBuffer wrap(ByteBuffer bytes2) {
        return new ByteReadableBufferWrapper(bytes2);
    }

    public static byte[] readArray(ReadableBuffer buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        int length = buffer.readableBytes();
        byte[] bytes2 = new byte[length];
        buffer.readBytes(bytes2, 0, length);
        return bytes2;
    }

    public static String readAsString(ReadableBuffer buffer, Charset charset) {
        Preconditions.checkNotNull(charset, "charset");
        byte[] bytes2 = ReadableBuffers.readArray(buffer);
        return new String(bytes2, charset);
    }

    public static String readAsStringUtf8(ReadableBuffer buffer) {
        return ReadableBuffers.readAsString(buffer, Charsets.UTF_8);
    }

    public static InputStream openStream(ReadableBuffer buffer, boolean owner2) {
        return new BufferInputStream(owner2 ? buffer : ReadableBuffers.ignoreClose(buffer));
    }

    public static ReadableBuffer ignoreClose(ReadableBuffer buffer) {
        return new ForwardingReadableBuffer(buffer){

            @Override
            public void close() {
            }
        };
    }

    private ReadableBuffers() {
    }

    private static final class BufferInputStream
    extends InputStream
    implements KnownLength {
        final ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer buffer) {
            this.buffer = Preconditions.checkNotNull(buffer, "buffer");
        }

        @Override
        public int available() throws IOException {
            return this.buffer.readableBytes();
        }

        @Override
        public int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        @Override
        public int read(byte[] dest, int destOffset, int length) throws IOException {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            length = Math.min(this.buffer.readableBytes(), length);
            this.buffer.readBytes(dest, destOffset, length);
            return length;
        }

        @Override
        public void close() throws IOException {
            this.buffer.close();
        }
    }

    private static class ByteReadableBufferWrapper
    extends AbstractReadableBuffer {
        final ByteBuffer bytes;

        ByteReadableBufferWrapper(ByteBuffer bytes2) {
            this.bytes = Preconditions.checkNotNull(bytes2, "bytes");
        }

        @Override
        public int readableBytes() {
            return this.bytes.remaining();
        }

        @Override
        public int readUnsignedByte() {
            this.checkReadable(1);
            return this.bytes.get() & 0xFF;
        }

        @Override
        public void skipBytes(int length) {
            this.checkReadable(length);
            this.bytes.position(this.bytes.position() + length);
        }

        @Override
        public void readBytes(byte[] dest, int destOffset, int length) {
            this.checkReadable(length);
            this.bytes.get(dest, destOffset, length);
        }

        @Override
        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            this.checkReadable(length);
            int prevLimit = this.bytes.limit();
            this.bytes.limit(this.bytes.position() + length);
            dest.put(this.bytes);
            this.bytes.limit(prevLimit);
        }

        @Override
        public void readBytes(OutputStream dest, int length) throws IOException {
            this.checkReadable(length);
            if (this.hasArray()) {
                dest.write(this.array(), this.arrayOffset(), length);
                this.bytes.position(this.bytes.position() + length);
            } else {
                byte[] array = new byte[length];
                this.bytes.get(array);
                dest.write(array);
            }
        }

        @Override
        public ByteReadableBufferWrapper readBytes(int length) {
            this.checkReadable(length);
            ByteBuffer buffer = this.bytes.duplicate();
            buffer.limit(this.bytes.position() + length);
            this.bytes.position(this.bytes.position() + length);
            return new ByteReadableBufferWrapper(buffer);
        }

        @Override
        public boolean hasArray() {
            return this.bytes.hasArray();
        }

        @Override
        public byte[] array() {
            return this.bytes.array();
        }

        @Override
        public int arrayOffset() {
            return this.bytes.arrayOffset() + this.bytes.position();
        }
    }

    private static class ByteArrayWrapper
    extends AbstractReadableBuffer {
        int offset;
        final int end;
        final byte[] bytes;

        ByteArrayWrapper(byte[] bytes2) {
            this(bytes2, 0, bytes2.length);
        }

        ByteArrayWrapper(byte[] bytes2, int offset, int length) {
            Preconditions.checkArgument(offset >= 0, "offset must be >= 0");
            Preconditions.checkArgument(length >= 0, "length must be >= 0");
            Preconditions.checkArgument(offset + length <= bytes2.length, "offset + length exceeds array boundary");
            this.bytes = Preconditions.checkNotNull(bytes2, "bytes");
            this.offset = offset;
            this.end = offset + length;
        }

        @Override
        public int readableBytes() {
            return this.end - this.offset;
        }

        @Override
        public void skipBytes(int length) {
            this.checkReadable(length);
            this.offset += length;
        }

        @Override
        public int readUnsignedByte() {
            this.checkReadable(1);
            return this.bytes[this.offset++] & 0xFF;
        }

        @Override
        public void readBytes(byte[] dest, int destIndex, int length) {
            System.arraycopy(this.bytes, this.offset, dest, destIndex, length);
            this.offset += length;
        }

        @Override
        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            this.checkReadable(length);
            dest.put(this.bytes, this.offset, length);
            this.offset += length;
        }

        @Override
        public void readBytes(OutputStream dest, int length) throws IOException {
            this.checkReadable(length);
            dest.write(this.bytes, this.offset, length);
            this.offset += length;
        }

        @Override
        public ByteArrayWrapper readBytes(int length) {
            this.checkReadable(length);
            int originalOffset = this.offset;
            this.offset += length;
            return new ByteArrayWrapper(this.bytes, originalOffset, length);
        }

        @Override
        public boolean hasArray() {
            return true;
        }

        @Override
        public byte[] array() {
            return this.bytes;
        }

        @Override
        public int arrayOffset() {
            return this.offset;
        }
    }
}

