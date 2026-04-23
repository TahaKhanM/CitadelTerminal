/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Objects;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ArrayMap<K, V>
extends AbstractMap<K, V>
implements Cloneable {
    int size;
    private Object[] data;

    public static <K, V> ArrayMap<K, V> create() {
        return new ArrayMap<K, V>();
    }

    public static <K, V> ArrayMap<K, V> create(int initialCapacity) {
        ArrayMap<K, V> result2 = ArrayMap.create();
        result2.ensureCapacity(initialCapacity);
        return result2;
    }

    public static <K, V> ArrayMap<K, V> of(Object ... keyValuePairs) {
        ArrayMap<K, V> result2 = ArrayMap.create(1);
        int length = keyValuePairs.length;
        if (1 == length % 2) {
            throw new IllegalArgumentException("missing value for last key: " + keyValuePairs[length - 1]);
        }
        result2.size = keyValuePairs.length / 2;
        result2.data = new Object[length];
        Object[] data = result2.data;
        System.arraycopy(keyValuePairs, 0, data, 0, length);
        return result2;
    }

    @Override
    public final int size() {
        return this.size;
    }

    public final K getKey(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Object result2 = this.data[index << 1];
        return (K)result2;
    }

    public final V getValue(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return this.valueAtDataIndex(1 + (index << 1));
    }

    public final V set(int index, K key, V value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int minSize = index + 1;
        this.ensureCapacity(minSize);
        int dataIndex = index << 1;
        V result2 = this.valueAtDataIndex(dataIndex + 1);
        this.setData(dataIndex, key, value);
        if (minSize > this.size) {
            this.size = minSize;
        }
        return result2;
    }

    public final V set(int index, V value) {
        int size2 = this.size;
        if (index < 0 || index >= size2) {
            throw new IndexOutOfBoundsException();
        }
        int valueDataIndex = 1 + (index << 1);
        V result2 = this.valueAtDataIndex(valueDataIndex);
        this.data[valueDataIndex] = value;
        return result2;
    }

    public final void add(K key, V value) {
        this.set(this.size, key, value);
    }

    public final V remove(int index) {
        return this.removeFromDataIndexOfKey(index << 1);
    }

    @Override
    public final boolean containsKey(Object key) {
        return -2 != this.getDataIndexOfKey(key);
    }

    public final int getIndexOfKey(K key) {
        return this.getDataIndexOfKey(key) >> 1;
    }

    @Override
    public final V get(Object key) {
        return this.valueAtDataIndex(this.getDataIndexOfKey(key) + 1);
    }

    @Override
    public final V put(K key, V value) {
        int index = this.getIndexOfKey(key);
        if (index == -1) {
            index = this.size;
        }
        return this.set(index, key, value);
    }

    @Override
    public final V remove(Object key) {
        return this.removeFromDataIndexOfKey(this.getDataIndexOfKey(key));
    }

    public final void trim() {
        this.setDataCapacity(this.size << 1);
    }

    public final void ensureCapacity(int minCapacity) {
        int oldDataCapacity;
        if (minCapacity < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] data = this.data;
        int minDataCapacity = minCapacity << 1;
        int n = oldDataCapacity = data == null ? 0 : data.length;
        if (minDataCapacity > oldDataCapacity) {
            int newDataCapacity = oldDataCapacity / 2 * 3 + 1;
            if (newDataCapacity % 2 != 0) {
                ++newDataCapacity;
            }
            if (newDataCapacity < minDataCapacity) {
                newDataCapacity = minDataCapacity;
            }
            this.setDataCapacity(newDataCapacity);
        }
    }

    private void setDataCapacity(int newDataCapacity) {
        if (newDataCapacity == 0) {
            this.data = null;
            return;
        }
        int size2 = this.size;
        Object[] oldData = this.data;
        if (size2 == 0 || newDataCapacity != oldData.length) {
            this.data = new Object[newDataCapacity];
            Object[] newData = this.data;
            if (size2 != 0) {
                System.arraycopy(oldData, 0, newData, 0, size2 << 1);
            }
        }
    }

    private void setData(int dataIndexOfKey, K key, V value) {
        Object[] data = this.data;
        data[dataIndexOfKey] = key;
        data[dataIndexOfKey + 1] = value;
    }

    private V valueAtDataIndex(int dataIndex) {
        if (dataIndex < 0) {
            return null;
        }
        Object result2 = this.data[dataIndex];
        return (V)result2;
    }

    private int getDataIndexOfKey(Object key) {
        int dataSize = this.size << 1;
        Object[] data = this.data;
        for (int i = 0; i < dataSize; i += 2) {
            Object k = data[i];
            if (!(key == null ? k == null : key.equals(k))) continue;
            return i;
        }
        return -2;
    }

    private V removeFromDataIndexOfKey(int dataIndexOfKey) {
        int dataSize = this.size << 1;
        if (dataIndexOfKey < 0 || dataIndexOfKey >= dataSize) {
            return null;
        }
        V result2 = this.valueAtDataIndex(dataIndexOfKey + 1);
        Object[] data = this.data;
        int moved = dataSize - dataIndexOfKey - 2;
        if (moved != 0) {
            System.arraycopy(data, dataIndexOfKey + 2, data, dataIndexOfKey, moved);
        }
        --this.size;
        this.setData(dataSize - 2, null, null);
        return result2;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.data = null;
    }

    @Override
    public final boolean containsValue(Object value) {
        int dataSize = this.size << 1;
        Object[] data = this.data;
        for (int i = 1; i < dataSize; i += 2) {
            Object v = data[i];
            if (!(value == null ? v == null : value.equals(v))) continue;
            return true;
        }
        return false;
    }

    @Override
    public final Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override
    public ArrayMap<K, V> clone() {
        try {
            ArrayMap result2 = (ArrayMap)super.clone();
            Object[] data = this.data;
            if (data != null) {
                int length = data.length;
                result2.data = new Object[length];
                Object[] resultData = result2.data;
                System.arraycopy(data, 0, resultData, 0, length);
            }
            return result2;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class Entry
    implements Map.Entry<K, V> {
        private int index;

        Entry(int index) {
            this.index = index;
        }

        @Override
        public K getKey() {
            return ArrayMap.this.getKey(this.index);
        }

        @Override
        public V getValue() {
            return ArrayMap.this.getValue(this.index);
        }

        @Override
        public V setValue(V value) {
            return ArrayMap.this.set(this.index, value);
        }

        @Override
        public int hashCode() {
            Object key = this.getKey();
            Object value = this.getValue();
            return (key != null ? key.hashCode() : 0) ^ (value != null ? value.hashCode() : 0);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry)obj;
            return Objects.equal(this.getKey(), other.getKey()) && Objects.equal(this.getValue(), other.getValue());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntryIterator
    implements Iterator<Map.Entry<K, V>> {
        private boolean removed;
        private int nextIndex;

        EntryIterator() {
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex < ArrayMap.this.size;
        }

        @Override
        public Map.Entry<K, V> next() {
            int index;
            if ((index = this.nextIndex++) == ArrayMap.this.size) {
                throw new NoSuchElementException();
            }
            return new Entry(index);
        }

        @Override
        public void remove() {
            int index = this.nextIndex - 1;
            if (this.removed || index < 0) {
                throw new IllegalArgumentException();
            }
            ArrayMap.this.remove(index);
            this.removed = true;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return ArrayMap.this.size;
        }
    }
}

