/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import java.util.Arrays;
import java.util.logging.Logger;

public final class TransportFrameUtil {
    private static final Logger logger = Logger.getLogger(TransportFrameUtil.class.getName());
    private static final byte[] binaryHeaderSuffixBytes = "-bin".getBytes(Charsets.US_ASCII);

    public static byte[][] toHttp2Headers(Metadata headers) {
        byte[][] serializedHeaders = InternalMetadata.serialize(headers);
        if (serializedHeaders == null) {
            return new byte[0][];
        }
        int k = 0;
        for (int i = 0; i < serializedHeaders.length; i += 2) {
            byte[] key = serializedHeaders[i];
            byte[] value = serializedHeaders[i + 1];
            if (TransportFrameUtil.endsWith(key, binaryHeaderSuffixBytes)) {
                serializedHeaders[k] = key;
                serializedHeaders[k + 1] = BaseEncoding.base64().encode(value).getBytes(Charsets.US_ASCII);
                k += 2;
                continue;
            }
            if (TransportFrameUtil.isSpecCompliantAscii(value)) {
                serializedHeaders[k] = key;
                serializedHeaders[k + 1] = value;
                k += 2;
                continue;
            }
            String keyString = new String(key, Charsets.US_ASCII);
            logger.warning("Metadata key=" + keyString + ", value=" + Arrays.toString(value) + " contains invalid ASCII characters");
        }
        if (k == serializedHeaders.length) {
            return serializedHeaders;
        }
        return (byte[][])Arrays.copyOfRange(serializedHeaders, 0, k);
    }

    public static byte[][] toRawSerializedHeaders(byte[][] http2Headers) {
        for (int i = 0; i < http2Headers.length; i += 2) {
            byte[] key = http2Headers[i];
            byte[] value = http2Headers[i + 1];
            http2Headers[i] = key;
            if (!TransportFrameUtil.endsWith(key, binaryHeaderSuffixBytes)) continue;
            http2Headers[i + 1] = BaseEncoding.base64().decode(new String(value, Charsets.US_ASCII));
        }
        return http2Headers;
    }

    private static boolean endsWith(byte[] subject, byte[] suffix) {
        int start = subject.length - suffix.length;
        if (start < 0) {
            return false;
        }
        for (int i = start; i < subject.length; ++i) {
            if (subject[i] == suffix[i - start]) continue;
            return false;
        }
        return true;
    }

    private static boolean isSpecCompliantAscii(byte[] subject) {
        for (byte b : subject) {
            if (b >= 32 && b <= 126) continue;
            return false;
        }
        return true;
    }

    private TransportFrameUtil() {
    }
}

