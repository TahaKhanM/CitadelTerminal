/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.internal;

public final class StringUtil {
    public static boolean isPrintableString(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (StringUtil.isPrintableChar(str.charAt(i))) continue;
            return false;
        }
        return true;
    }

    private static boolean isPrintableChar(char ch) {
        return ch >= ' ' && ch <= '~';
    }

    private StringUtil() {
    }
}

