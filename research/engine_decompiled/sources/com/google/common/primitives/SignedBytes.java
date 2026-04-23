/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@GwtCompatible
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    private SignedBytes() {
    }

    public static byte checkedCast(long value) {
        byte result2 = (byte)value;
        if ((long)result2 != value) {
            throw new IllegalArgumentException("Out of range: " + value);
        }
        return result2;
    }

    public static byte saturatedCast(long value) {
        if (value > 127L) {
            return 127;
        }
        if (value < -128L) {
            return -128;
        }
        return (byte)value;
    }

    public static int compare(byte a, byte b) {
        return a - b;
    }

    public static byte min(byte ... array) {
        Preconditions.checkArgument(array.length > 0);
        byte min2 = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= min2) continue;
            min2 = array[i];
        }
        return min2;
    }

    public static byte max(byte ... array) {
        Preconditions.checkArgument(array.length > 0);
        byte max2 = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= max2) continue;
            max2 = array[i];
        }
        return max2;
    }

    public static String join(String separator, byte ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private static enum LexicographicalComparator implements Comparator<byte[]>
    {
        INSTANCE;


        @Override
        public int compare(byte[] left, byte[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result2 = SignedBytes.compare(left[i], right[i]);
                if (result2 == 0) continue;
                return result2;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }
    }
}

