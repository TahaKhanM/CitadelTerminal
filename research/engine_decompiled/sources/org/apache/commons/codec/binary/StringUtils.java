/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;

public class StringUtils {
    private static byte[] getBytes(String string2, Charset charset) {
        if (string2 == null) {
            return null;
        }
        return string2.getBytes(charset);
    }

    public static byte[] getBytesIso8859_1(String string2) {
        return StringUtils.getBytes(string2, Charsets.ISO_8859_1);
    }

    public static byte[] getBytesUnchecked(String string2, String charsetName) {
        if (string2 == null) {
            return null;
        }
        try {
            return string2.getBytes(charsetName);
        }
        catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    public static byte[] getBytesUsAscii(String string2) {
        return StringUtils.getBytes(string2, Charsets.US_ASCII);
    }

    public static byte[] getBytesUtf16(String string2) {
        return StringUtils.getBytes(string2, Charsets.UTF_16);
    }

    public static byte[] getBytesUtf16Be(String string2) {
        return StringUtils.getBytes(string2, Charsets.UTF_16BE);
    }

    public static byte[] getBytesUtf16Le(String string2) {
        return StringUtils.getBytes(string2, Charsets.UTF_16LE);
    }

    public static byte[] getBytesUtf8(String string2) {
        return StringUtils.getBytes(string2, Charsets.UTF_8);
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
    }

    private static String newString(byte[] bytes2, Charset charset) {
        return bytes2 == null ? null : new String(bytes2, charset);
    }

    public static String newString(byte[] bytes2, String charsetName) {
        if (bytes2 == null) {
            return null;
        }
        try {
            return new String(bytes2, charsetName);
        }
        catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    public static String newStringIso8859_1(byte[] bytes2) {
        return new String(bytes2, Charsets.ISO_8859_1);
    }

    public static String newStringUsAscii(byte[] bytes2) {
        return new String(bytes2, Charsets.US_ASCII);
    }

    public static String newStringUtf16(byte[] bytes2) {
        return new String(bytes2, Charsets.UTF_16);
    }

    public static String newStringUtf16Be(byte[] bytes2) {
        return new String(bytes2, Charsets.UTF_16BE);
    }

    public static String newStringUtf16Le(byte[] bytes2) {
        return new String(bytes2, Charsets.UTF_16LE);
    }

    public static String newStringUtf8(byte[] bytes2) {
        return StringUtils.newString(bytes2, Charsets.UTF_8);
    }
}

