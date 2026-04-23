/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;

public class StringUtils {
    public static byte[] getBytesIso8859_1(String string2) {
        return StringUtils.getBytesUnchecked(string2, "ISO-8859-1");
    }

    public static byte[] getBytesUsAscii(String string2) {
        return StringUtils.getBytesUnchecked(string2, "US-ASCII");
    }

    public static byte[] getBytesUtf16(String string2) {
        return StringUtils.getBytesUnchecked(string2, "UTF-16");
    }

    public static byte[] getBytesUtf16Be(String string2) {
        return StringUtils.getBytesUnchecked(string2, "UTF-16BE");
    }

    public static byte[] getBytesUtf16Le(String string2) {
        return StringUtils.getBytesUnchecked(string2, "UTF-16LE");
    }

    public static byte[] getBytesUtf8(String string2) {
        return StringUtils.getBytesUnchecked(string2, "UTF-8");
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

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
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
        return StringUtils.newString(bytes2, "ISO-8859-1");
    }

    public static String newStringUsAscii(byte[] bytes2) {
        return StringUtils.newString(bytes2, "US-ASCII");
    }

    public static String newStringUtf16(byte[] bytes2) {
        return StringUtils.newString(bytes2, "UTF-16");
    }

    public static String newStringUtf16Be(byte[] bytes2) {
        return StringUtils.newString(bytes2, "UTF-16BE");
    }

    public static String newStringUtf16Le(byte[] bytes2) {
        return StringUtils.newString(bytes2, "UTF-16LE");
    }

    public static String newStringUtf8(byte[] bytes2) {
        return StringUtils.newString(bytes2, "UTF-8");
    }
}

