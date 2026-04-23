/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

@GwtCompatible
public final class Strings {
    private Strings() {
    }

    public static String nullToEmpty(@Nullable String string2) {
        return string2 == null ? "" : string2;
    }

    @Nullable
    public static String emptyToNull(@Nullable String string2) {
        return Strings.isNullOrEmpty(string2) ? null : string2;
    }

    public static boolean isNullOrEmpty(@Nullable String string2) {
        return Platform.stringIsNullOrEmpty(string2);
    }

    public static String padStart(String string2, int minLength, char padChar) {
        Preconditions.checkNotNull(string2);
        if (string2.length() >= minLength) {
            return string2;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string2.length(); i < minLength; ++i) {
            sb.append(padChar);
        }
        sb.append(string2);
        return sb.toString();
    }

    public static String padEnd(String string2, int minLength, char padChar) {
        Preconditions.checkNotNull(string2);
        if (string2.length() >= minLength) {
            return string2;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(string2);
        for (int i = string2.length(); i < minLength; ++i) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static String repeat(String string2, int count2) {
        int n;
        Preconditions.checkNotNull(string2);
        if (count2 <= 1) {
            Preconditions.checkArgument(count2 >= 0, "invalid count: %s", count2);
            return count2 == 0 ? "" : string2;
        }
        int len = string2.length();
        long longSize = (long)len * (long)count2;
        int size2 = (int)longSize;
        if ((long)size2 != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }
        char[] array = new char[size2];
        string2.getChars(0, len, array, 0);
        for (n = len; n < size2 - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size2 - n);
        return new String(array);
    }

    public static String commonPrefix(CharSequence a, CharSequence b) {
        int p;
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxPrefixLength = Math.min(a.length(), b.length());
        for (p = 0; p < maxPrefixLength && a.charAt(p) == b.charAt(p); ++p) {
        }
        if (Strings.validSurrogatePairAt(a, p - 1) || Strings.validSurrogatePairAt(b, p - 1)) {
            --p;
        }
        return a.subSequence(0, p).toString();
    }

    public static String commonSuffix(CharSequence a, CharSequence b) {
        int s2;
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxSuffixLength = Math.min(a.length(), b.length());
        for (s2 = 0; s2 < maxSuffixLength && a.charAt(a.length() - s2 - 1) == b.charAt(b.length() - s2 - 1); ++s2) {
        }
        if (Strings.validSurrogatePairAt(a, a.length() - s2 - 1) || Strings.validSurrogatePairAt(b, b.length() - s2 - 1)) {
            --s2;
        }
        return a.subSequence(a.length() - s2, a.length()).toString();
    }

    @VisibleForTesting
    static boolean validSurrogatePairAt(CharSequence string2, int index) {
        return index >= 0 && index <= string2.length() - 2 && Character.isHighSurrogate(string2.charAt(index)) && Character.isLowSurrogate(string2.charAt(index + 1));
    }
}

