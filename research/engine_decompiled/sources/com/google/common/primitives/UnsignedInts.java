/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.ParseRequest;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedInts {
    static final long INT_MASK = 0xFFFFFFFFL;

    private UnsignedInts() {
    }

    static int flip(int value) {
        return value ^ Integer.MIN_VALUE;
    }

    public static int compare(int a, int b) {
        return Ints.compare(UnsignedInts.flip(a), UnsignedInts.flip(b));
    }

    public static long toLong(int value) {
        return (long)value & 0xFFFFFFFFL;
    }

    public static int min(int ... array) {
        Preconditions.checkArgument(array.length > 0);
        int min2 = UnsignedInts.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            int next2 = UnsignedInts.flip(array[i]);
            if (next2 >= min2) continue;
            min2 = next2;
        }
        return UnsignedInts.flip(min2);
    }

    public static int max(int ... array) {
        Preconditions.checkArgument(array.length > 0);
        int max2 = UnsignedInts.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            int next2 = UnsignedInts.flip(array[i]);
            if (next2 <= max2) continue;
            max2 = next2;
        }
        return UnsignedInts.flip(max2);
    }

    public static String join(String separator, int ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(UnsignedInts.toString(array[0]));
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(UnsignedInts.toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int divide(int dividend, int divisor) {
        return (int)(UnsignedInts.toLong(dividend) / UnsignedInts.toLong(divisor));
    }

    public static int remainder(int dividend, int divisor) {
        return (int)(UnsignedInts.toLong(dividend) % UnsignedInts.toLong(divisor));
    }

    @CanIgnoreReturnValue
    public static int decode(String stringValue) {
        ParseRequest request = ParseRequest.fromString(stringValue);
        try {
            return UnsignedInts.parseUnsignedInt(request.rawValue, request.radix);
        }
        catch (NumberFormatException e) {
            NumberFormatException decodeException = new NumberFormatException("Error parsing value: " + stringValue);
            decodeException.initCause(e);
            throw decodeException;
        }
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String s2) {
        return UnsignedInts.parseUnsignedInt(s2, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String string2, int radix) {
        Preconditions.checkNotNull(string2);
        long result2 = Long.parseLong(string2, radix);
        if ((result2 & 0xFFFFFFFFL) != result2) {
            throw new NumberFormatException("Input " + string2 + " in base " + radix + " is not in the range of an unsigned integer");
        }
        return (int)result2;
    }

    public static String toString(int x) {
        return UnsignedInts.toString(x, 10);
    }

    public static String toString(int x, int radix) {
        long asLong = (long)x & 0xFFFFFFFFL;
        return Long.toString(asLong, radix);
    }

    static enum LexicographicalComparator implements Comparator<int[]>
    {
        INSTANCE;


        @Override
        public int compare(int[] left, int[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                if (left[i] == right[i]) continue;
                return UnsignedInts.compare(left[i], right[i]);
            }
            return left.length - right.length;
        }

        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }
    }
}

