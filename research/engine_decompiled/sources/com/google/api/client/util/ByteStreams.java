/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ByteStreams {
    private static final int BUF_SIZE = 4096;

    public static long copy(InputStream from2, OutputStream to2) throws IOException {
        int r;
        Preconditions.checkNotNull(from2);
        Preconditions.checkNotNull(to2);
        byte[] buf = new byte[4096];
        long total2 = 0L;
        while ((r = from2.read(buf)) != -1) {
            to2.write(buf, 0, r);
            total2 += (long)r;
        }
        return total2;
    }

    public static InputStream limit(InputStream in, long limit) {
        return new LimitedInputStream(in, limit);
    }

    public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
        int total2;
        int result2;
        Preconditions.checkNotNull(in);
        Preconditions.checkNotNull(b);
        if (len < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        for (total2 = 0; total2 < len && (result2 = in.read(b, off + total2, len - total2)) != -1; total2 += result2) {
        }
        return total2;
    }

    private ByteStreams() {
    }

    private static final class LimitedInputStream
    extends FilterInputStream {
        private long left;
        private long mark = -1L;

        LimitedInputStream(InputStream in, long limit) {
            super(in);
            Preconditions.checkNotNull(in);
            Preconditions.checkArgument(limit >= 0L, "limit must be non-negative");
            this.left = limit;
        }

        public int available() throws IOException {
            return (int)Math.min((long)this.in.available(), this.left);
        }

        public synchronized void mark(int readLimit) {
            this.in.mark(readLimit);
            this.mark = this.left;
        }

        public int read() throws IOException {
            if (this.left == 0L) {
                return -1;
            }
            int result2 = this.in.read();
            if (result2 != -1) {
                --this.left;
            }
            return result2;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            if (this.left == 0L) {
                return -1;
            }
            int result2 = this.in.read(b, off, len = (int)Math.min((long)len, this.left));
            if (result2 != -1) {
                this.left -= (long)result2;
            }
            return result2;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1L) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.left = this.mark;
        }

        public long skip(long n) throws IOException {
            n = Math.min(n, this.left);
            long skipped = this.in.skip(n);
            this.left -= skipped;
            return skipped;
        }
    }
}

