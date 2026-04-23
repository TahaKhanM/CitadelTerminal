/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils {
    private static final int MAX_BUFFER_LENGTH = 16384;

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IoUtils.copy(in, out);
        return out.toByteArray();
    }

    public static long copy(InputStream from2, OutputStream to2) throws IOException {
        int r;
        Preconditions.checkNotNull(from2);
        Preconditions.checkNotNull(to2);
        byte[] buf = new byte[16384];
        long total2 = 0L;
        while ((r = from2.read(buf)) != -1) {
            to2.write(buf, 0, r);
            total2 += (long)r;
        }
        return total2;
    }
}

