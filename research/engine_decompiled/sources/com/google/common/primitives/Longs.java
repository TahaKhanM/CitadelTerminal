/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

@GwtCompatible
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 0x4000000000000000L;
    private static final byte[] asciiDigits = Longs.createAsciiDigits();

    private Longs() {
    }

    public static int hashCode(long value) {
        return (int)(value ^ value >>> 32);
    }

    public static int compare(long a, long b) {
        return a < b ? -1 : (a > b ? 1 : 0);
    }

    public static boolean contains(long[] array, long target) {
        for (long value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(long[] array, long target) {
        return Longs.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(long[] array, long target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(long[] array, long[] target) {
        Preconditions.checkNotNull(array, "array");
        Preconditions.checkNotNull(target, "target");
        if (target.length == 0) {
            return 0;
        }
        block0: for (int i = 0; i < array.length - target.length + 1; ++i) {
            for (int j = 0; j < target.length; ++j) {
                if (array[i + j] != target[j]) continue block0;
            }
            return i;
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long target) {
        return Longs.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(long[] array, long target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static long min(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long min2 = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= min2) continue;
            min2 = array[i];
        }
        return min2;
    }

    public static long max(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long max2 = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= max2) continue;
            max2 = array[i];
        }
        return max2;
    }

    public static long[] concat(long[] ... arrays) {
        int length = 0;
        for (long[] array : arrays) {
            length += array.length;
        }
        long[] result2 = new long[length];
        int pos = 0;
        for (long[] array : arrays) {
            System.arraycopy(array, 0, result2, pos, array.length);
            pos += array.length;
        }
        return result2;
    }

    public static byte[] toByteArray(long value) {
        byte[] result2 = new byte[8];
        for (int i = 7; i >= 0; --i) {
            result2[i] = (byte)(value & 0xFFL);
            value >>= 8;
        }
        return result2;
    }

    public static long fromByteArray(byte[] bytes2) {
        Preconditions.checkArgument(bytes2.length >= 8, "array too small: %s < %s", bytes2.length, 8);
        return Longs.fromBytes(bytes2[0], bytes2[1], bytes2[2], bytes2[3], bytes2[4], bytes2[5], bytes2[6], bytes2[7]);
    }

    public static long fromBytes(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((long)b1 & 0xFFL) << 56 | ((long)b2 & 0xFFL) << 48 | ((long)b3 & 0xFFL) << 40 | ((long)b4 & 0xFFL) << 32 | ((long)b5 & 0xFFL) << 24 | ((long)b6 & 0xFFL) << 16 | ((long)b7 & 0xFFL) << 8 | (long)b8 & 0xFFL;
    }

    private static byte[] createAsciiDigits() {
        int i;
        byte[] result2 = new byte[128];
        Arrays.fill(result2, (byte)-1);
        for (i = 0; i <= 9; ++i) {
            result2[48 + i] = (byte)i;
        }
        for (i = 0; i <= 26; ++i) {
            result2[65 + i] = (byte)(10 + i);
            result2[97 + i] = (byte)(10 + i);
        }
        return result2;
    }

    private static int digit(char c) {
        return c < '\u0080' ? asciiDigits[c] : -1;
    }

    @Nullable
    @CheckForNull
    @Beta
    public static Long tryParse(String string2) {
        return Longs.tryParse(string2, 10);
    }

    @Nullable
    @CheckForNull
    @Beta
    public static Long tryParse(String string2, int radix) {
        int digit;
        int index;
        if (Preconditions.checkNotNull(string2).isEmpty()) {
            return null;
        }
        if (radix < 2 || radix > 36) {
            throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + radix);
        }
        boolean negative = string2.charAt(0) == '-';
        int n = index = negative ? 1 : 0;
        if (index == string2.length()) {
            return null;
        }
        if ((digit = Longs.digit(string2.charAt(index++))) < 0 || digit >= radix) {
            return null;
        }
        long accum = -digit;
        long cap = Long.MIN_VALUE / (long)radix;
        while (index < string2.length()) {
            if ((digit = Longs.digit(string2.charAt(index++))) < 0 || digit >= radix || accum < cap) {
                return null;
            }
            if ((accum *= (long)radix) < Long.MIN_VALUE + (long)digit) {
                return null;
            }
            accum -= (long)digit;
        }
        if (negative) {
            return accum;
        }
        if (accum == Long.MIN_VALUE) {
            return null;
        }
        return -accum;
    }

    @Beta
    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] ensureCapacity(long[] array, int minLength, int padding2) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding2 >= 0, "Invalid padding: %s", padding2);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding2) : array;
    }

    public static String join(String separator, long ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 10);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList)collection).toLongArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        long[] array = new long[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).longValue();
        }
        return array;
    }

    public static List<Long> asList(long ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new LongArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class LongArrayAsList
    extends AbstractList<Long>
    implements RandomAccess,
    Serializable {
        final long[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        LongArrayAsList(long[] array) {
            this(array, 0, array.length);
        }

        LongArrayAsList(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public int size() {
            return this.end - this.start;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Long get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return this.array[this.start + index];
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Long && Longs.indexOf(this.array, (Long)target, this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Long && (i = Longs.indexOf(this.array, (Long)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Long && (i = Longs.lastIndexOf(this.array, (Long)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Long set(int index, Long element) {
            Preconditions.checkElementIndex(index, this.size());
            long oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element);
            return oldValue;
        }

        @Override
        public List<Long> subList(int fromIndex, int toIndex) {
            int size2 = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size2);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new LongArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof LongArrayAsList) {
                LongArrayAsList that = (LongArrayAsList)object;
                int size2 = this.size();
                if (that.size() != size2) {
                    return false;
                }
                for (int i = 0; i < size2; ++i) {
                    if (this.array[this.start + i] == that.array[that.start + i]) continue;
                    return false;
                }
                return true;
            }
            return super.equals(object);
        }

        @Override
        public int hashCode() {
            int result2 = 1;
            for (int i = this.start; i < this.end; ++i) {
                result2 = 31 * result2 + Longs.hashCode(this.array[i]);
            }
            return result2;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 10);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        long[] toLongArray() {
            int size2 = this.size();
            long[] result2 = new long[size2];
            System.arraycopy(this.array, this.start, result2, 0, size2);
            return result2;
        }
    }

    private static enum LexicographicalComparator implements Comparator<long[]>
    {
        INSTANCE;


        @Override
        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result2 = Longs.compare(left[i], right[i]);
                if (result2 == 0) continue;
                return result2;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Longs.lexicographicalComparator()";
        }
    }

    private static final class LongConverter
    extends Converter<String, Long>
    implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1L;

        private LongConverter() {
        }

        @Override
        protected Long doForward(String value) {
            return Long.decode(value);
        }

        @Override
        protected String doBackward(Long value) {
            return value.toString();
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}

