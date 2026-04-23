/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Platform;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Collection;
import javax.annotation.Nullable;

@GwtCompatible(emulated=true)
public final class ObjectArrays {
    static final Object[] EMPTY_ARRAY = new Object[0];

    private ObjectArrays() {
    }

    @GwtIncompatible
    public static <T> T[] newArray(Class<T> type, int length) {
        return (Object[])Array.newInstance(type, length);
    }

    public static <T> T[] newArray(T[] reference, int length) {
        return Platform.newArray(reference, length);
    }

    @GwtIncompatible
    public static <T> T[] concat(T[] first, T[] second, Class<T> type) {
        T[] result2 = ObjectArrays.newArray(type, first.length + second.length);
        System.arraycopy(first, 0, result2, 0, first.length);
        System.arraycopy(second, 0, result2, first.length, second.length);
        return result2;
    }

    public static <T> T[] concat(@Nullable T element, T[] array) {
        T[] result2 = ObjectArrays.newArray(array, array.length + 1);
        result2[0] = element;
        System.arraycopy(array, 0, result2, 1, array.length);
        return result2;
    }

    public static <T> T[] concat(T[] array, @Nullable T element) {
        T[] result2 = ObjectArrays.arraysCopyOf(array, array.length + 1);
        result2[array.length] = element;
        return result2;
    }

    static <T> T[] arraysCopyOf(T[] original, int newLength) {
        T[] copy2 = ObjectArrays.newArray(original, newLength);
        System.arraycopy(original, 0, copy2, 0, Math.min(original.length, newLength));
        return copy2;
    }

    static <T> T[] toArrayImpl(Collection<?> c, T[] array) {
        int size2 = c.size();
        if (array.length < size2) {
            array = ObjectArrays.newArray(array, size2);
        }
        ObjectArrays.fillArray(c, array);
        if (array.length > size2) {
            array[size2] = null;
        }
        return array;
    }

    static <T> T[] toArrayImpl(Object[] src, int offset, int len, T[] dst) {
        Preconditions.checkPositionIndexes(offset, offset + len, src.length);
        if (dst.length < len) {
            dst = ObjectArrays.newArray(dst, len);
        } else if (dst.length > len) {
            dst[len] = null;
        }
        System.arraycopy(src, offset, dst, 0, len);
        return dst;
    }

    static Object[] toArrayImpl(Collection<?> c) {
        return ObjectArrays.fillArray(c, new Object[c.size()]);
    }

    static Object[] copyAsObjectArray(Object[] elements, int offset, int length) {
        Preconditions.checkPositionIndexes(offset, offset + length, elements.length);
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        Object[] result2 = new Object[length];
        System.arraycopy(elements, offset, result2, 0, length);
        return result2;
    }

    @CanIgnoreReturnValue
    private static Object[] fillArray(Iterable<?> elements, Object[] array) {
        int i = 0;
        for (Object element : elements) {
            array[i++] = element;
        }
        return array;
    }

    static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @CanIgnoreReturnValue
    static Object[] checkElementsNotNull(Object ... array) {
        return ObjectArrays.checkElementsNotNull(array, array.length);
    }

    @CanIgnoreReturnValue
    static Object[] checkElementsNotNull(Object[] array, int length) {
        for (int i = 0; i < length; ++i) {
            ObjectArrays.checkElementNotNull(array[i], i);
        }
        return array;
    }

    @CanIgnoreReturnValue
    static Object checkElementNotNull(Object element, int index) {
        if (element == null) {
            throw new NullPointerException("at index " + index);
        }
        return element;
    }
}

