/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

public class StringUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static byte[] getBytesUtf8(String string2) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.getBytesUtf8(string2);
    }

    public static String newStringUtf8(byte[] bytes2) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.newStringUtf8(bytes2);
    }

    private StringUtils() {
    }
}

