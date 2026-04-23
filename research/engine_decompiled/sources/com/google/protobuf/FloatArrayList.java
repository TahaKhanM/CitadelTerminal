/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.AbstractProtobufList;
import com.google.protobuf.Internal;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class FloatArrayList
extends AbstractProtobufList<Float>
implements Internal.FloatList,
RandomAccess,
PrimitiveNonBoxingCollection {
    private static final FloatArrayList EMPTY_LIST = new FloatArrayList();
    private float[] array;
    private int size;

    public static FloatArrayList emptyList() {
        return EMPTY_LIST;
    }

    FloatArrayList() {
        this(new float[10], 0);
    }

    private FloatArrayList(float[] other, int size2) {
        this.array = other;
        this.size = size2;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        this.ensureIsMutable();
        if (toIndex < fromIndex) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.array, toIndex, this.array, fromIndex, this.size - toIndex);
        this.size -= toIndex - fromIndex;
        ++this.modCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FloatArrayList)) {
            return super.equals(o);
        }
        FloatArrayList other = (FloatArrayList)o;
        if (this.size != other.size) {
            return false;
        }
        float[] arr = other.array;
        for (int i = 0; i < this.size; ++i) {
            if (this.array[i] == arr[i]) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result2 = 1;
        for (int i = 0; i < this.size; ++i) {
            result2 = 31 * result2 + Float.floatToIntBits(this.array[i]);
        }
        return result2;
    }

    @Override
    public Internal.FloatList mutableCopyWithCapacity(int capacity) {
        if (capacity < this.size) {
            throw new IllegalArgumentException();
        }
        return new FloatArrayList(Arrays.copyOf(this.array, capacity), this.size);
    }

    @Override
    public Float get(int index) {
        return Float.valueOf(this.getFloat(index));
    }

    @Override
    public float getFloat(int index) {
        this.ensureIndexInRange(index);
        return this.array[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Float set(int index, Float element) {
        return Float.valueOf(this.setFloat(index, element.floatValue()));
    }

    @Override
    public float setFloat(int index, float element) {
        this.ensureIsMutable();
        this.ensureIndexInRange(index);
        float previousValue = this.array[index];
        this.array[index] = element;
        return previousValue;
    }

    @Override
    public void add(int index, Float element) {
        this.addFloat(index, element.floatValue());
    }

    @Override
    public void addFloat(float element) {
        this.addFloat(this.size, element);
    }

    private void addFloat(int index, float element) {
        this.ensureIsMutable();
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(index));
        }
        if (this.size < this.array.length) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        } else {
            int length = this.size * 3 / 2 + 1;
            float[] newArray = new float[length];
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
            this.array = newArray;
        }
        this.array[index] = element;
        ++this.size;
        ++this.modCount;
    }

    @Override
    public boolean addAll(Collection<? extends Float> collection) {
        this.ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof FloatArrayList)) {
            return super.addAll(collection);
        }
        FloatArrayList list2 = (FloatArrayList)collection;
        if (list2.size == 0) {
            return false;
        }
        int overflow = Integer.MAX_VALUE - this.size;
        if (overflow < list2.size) {
            throw new OutOfMemoryError();
        }
        int newSize = this.size + list2.size;
        if (newSize > this.array.length) {
            this.array = Arrays.copyOf(this.array, newSize);
        }
        System.arraycopy(list2.array, 0, this.array, this.size, list2.size);
        this.size = newSize;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        this.ensureIsMutable();
        for (int i = 0; i < this.size; ++i) {
            if (!o.equals(Float.valueOf(this.array[i]))) continue;
            System.arraycopy(this.array, i + 1, this.array, i, this.size - i);
            --this.size;
            ++this.modCount;
            return true;
        }
        return false;
    }

    @Override
    public Float remove(int index) {
        this.ensureIsMutable();
        this.ensureIndexInRange(index);
        float value = this.array[index];
        if (index < this.size - 1) {
            System.arraycopy(this.array, index + 1, this.array, index, this.size - index);
        }
        --this.size;
        ++this.modCount;
        return Float.valueOf(value);
    }

    private void ensureIndexInRange(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(index));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int index) {
        return "Index:" + index + ", Size:" + this.size;
    }

    static {
        EMPTY_LIST.makeImmutable();
    }
}

