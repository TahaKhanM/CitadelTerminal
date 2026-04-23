/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.BetaApi;
import com.google.common.base.MoreObjects;
import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@BetaApi
public class ByteArray
implements Iterable<Byte>,
Serializable {
    private static final long serialVersionUID = -1908809133893782840L;
    private static final BaseEncoding encoder = BaseEncoding.base64();
    private final ByteString byteString;

    ByteArray(ByteString byteString) {
        this.byteString = byteString;
    }

    @Override
    public final Iterator<Byte> iterator() {
        return this.byteString.iterator();
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
        StringBuilder stBuilder = new StringBuilder();
        for (int i = 0; i < Math.min(256, this.byteString.size()); ++i) {
            stBuilder.append(String.format("%02x", this.byteString.byteAt(i)));
        }
        if (this.byteString.size() > 256) {
            stBuilder.append("...");
        }
        return toStringHelper.add("bytes", stBuilder.toString()).toString();
    }

    public final int hashCode() {
        return this.byteString.hashCode();
    }

    public final boolean equals(Object obj) {
        return obj == this || obj instanceof ByteArray && this.byteString.equals(((ByteArray)obj).byteString);
    }

    public final int length() {
        return this.byteString.size();
    }

    public final byte[] toByteArray() {
        return this.byteString.toByteArray();
    }

    public final String toStringUtf8() {
        return this.byteString.toStringUtf8();
    }

    public final String toBase64() {
        return encoder.encode(this.toByteArray());
    }

    public final ByteBuffer asReadOnlyByteBuffer() {
        return this.byteString.asReadOnlyByteBuffer();
    }

    public final InputStream asInputStream() {
        return this.byteString.newInput();
    }

    public final void copyTo(ByteBuffer target) {
        this.byteString.copyTo(target);
    }

    public final void copyTo(byte[] target) {
        this.byteString.copyTo(target, 0, 0, this.length());
    }

    public static final ByteArray copyFrom(byte[] bytes2) {
        return new ByteArray(ByteString.copyFrom(bytes2));
    }

    public static final ByteArray copyFrom(String string2) {
        return new ByteArray(ByteString.copyFrom(string2, StandardCharsets.UTF_8));
    }

    public static final ByteArray copyFrom(ByteBuffer bytes2) {
        return new ByteArray(ByteString.copyFrom(bytes2));
    }

    public static final ByteArray copyFrom(InputStream input2) throws IOException {
        return new ByteArray(ByteString.readFrom(input2));
    }

    public static ByteArray fromBase64(String data) {
        return ByteArray.copyFrom(encoder.decode(data));
    }
}

