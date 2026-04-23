/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

public class BufferRecycler {
    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte[][] _byteBuffers = new byte[ByteBufferType.values().length][];
    protected final char[][] _charBuffers = new char[CharBufferType.values().length][];

    public final byte[] allocByteBuffer(ByteBufferType type) {
        int ix = type.ordinal();
        byte[] buffer = this._byteBuffers[ix];
        if (buffer == null) {
            buffer = this.balloc(type.size);
        } else {
            this._byteBuffers[ix] = null;
        }
        return buffer;
    }

    public final void releaseByteBuffer(ByteBufferType type, byte[] buffer) {
        this._byteBuffers[type.ordinal()] = buffer;
    }

    public final char[] allocCharBuffer(CharBufferType type) {
        return this.allocCharBuffer(type, 0);
    }

    public final char[] allocCharBuffer(CharBufferType type, int minSize) {
        int ix;
        char[] buffer;
        if (type.size > minSize) {
            minSize = type.size;
        }
        if ((buffer = this._charBuffers[ix = type.ordinal()]) == null || buffer.length < minSize) {
            buffer = this.calloc(minSize);
        } else {
            this._charBuffers[ix] = null;
        }
        return buffer;
    }

    public final void releaseCharBuffer(CharBufferType type, char[] buffer) {
        this._charBuffers[type.ordinal()] = buffer;
    }

    private byte[] balloc(int size2) {
        return new byte[size2];
    }

    private char[] calloc(int size2) {
        return new char[size2];
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum CharBufferType {
        TOKEN_BUFFER(2000),
        CONCAT_BUFFER(2000),
        TEXT_BUFFER(200),
        NAME_COPY_BUFFER(200);

        protected final int size;

        private CharBufferType(int size2) {
            this.size = size2;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum ByteBufferType {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(2000),
        BASE64_CODEC_BUFFER(2000);

        protected final int size;

        private ByteBufferType(int size2) {
            this.size = size2;
        }
    }
}

