/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Utf8;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public final class Internal {
    static final Charset UTF_8 = Charset.forName("UTF-8");
    static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(EMPTY_BYTE_ARRAY);

    private Internal() {
    }

    static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    static <T> T checkNotNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    public static String stringDefaultValue(String bytes2) {
        return new String(bytes2.getBytes(ISO_8859_1), UTF_8);
    }

    public static ByteString bytesDefaultValue(String bytes2) {
        return ByteString.copyFrom(bytes2.getBytes(ISO_8859_1));
    }

    public static byte[] byteArrayDefaultValue(String bytes2) {
        return bytes2.getBytes(ISO_8859_1);
    }

    public static ByteBuffer byteBufferDefaultValue(String bytes2) {
        return ByteBuffer.wrap(Internal.byteArrayDefaultValue(bytes2));
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer source) {
        ByteBuffer temp = source.duplicate();
        temp.clear();
        ByteBuffer result2 = ByteBuffer.allocate(temp.capacity());
        result2.put(temp);
        result2.clear();
        return result2;
    }

    public static boolean isValidUtf8(ByteString byteString) {
        return byteString.isValidUtf8();
    }

    public static boolean isValidUtf8(byte[] byteArray) {
        return Utf8.isValidUtf8(byteArray);
    }

    public static byte[] toByteArray(String value) {
        return value.getBytes(UTF_8);
    }

    public static String toStringUtf8(byte[] bytes2) {
        return new String(bytes2, UTF_8);
    }

    public static int hashLong(long n) {
        return (int)(n ^ n >>> 32);
    }

    public static int hashBoolean(boolean b) {
        return b ? 1231 : 1237;
    }

    public static int hashEnum(EnumLite e) {
        return e.getNumber();
    }

    public static int hashEnumList(List<? extends EnumLite> list2) {
        int hash = 1;
        for (EnumLite enumLite : list2) {
            hash = 31 * hash + Internal.hashEnum(enumLite);
        }
        return hash;
    }

    public static boolean equals(List<byte[]> a, List<byte[]> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            if (Arrays.equals(a.get(i), b.get(i))) continue;
            return false;
        }
        return true;
    }

    public static int hashCode(List<byte[]> list2) {
        int hash = 1;
        for (byte[] bytes2 : list2) {
            hash = 31 * hash + Internal.hashCode(bytes2);
        }
        return hash;
    }

    public static int hashCode(byte[] bytes2) {
        return Internal.hashCode(bytes2, 0, bytes2.length);
    }

    static int hashCode(byte[] bytes2, int offset, int length) {
        int h = Internal.partialHash(length, bytes2, offset, length);
        return h == 0 ? 1 : h;
    }

    static int partialHash(int h, byte[] bytes2, int offset, int length) {
        for (int i = offset; i < offset + length; ++i) {
            h = h * 31 + bytes2[i];
        }
        return h;
    }

    public static boolean equalsByteBuffer(ByteBuffer a, ByteBuffer b) {
        if (a.capacity() != b.capacity()) {
            return false;
        }
        return a.duplicate().clear().equals(b.duplicate().clear());
    }

    public static boolean equalsByteBuffer(List<ByteBuffer> a, List<ByteBuffer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            if (Internal.equalsByteBuffer(a.get(i), b.get(i))) continue;
            return false;
        }
        return true;
    }

    public static int hashCodeByteBuffer(List<ByteBuffer> list2) {
        int hash = 1;
        for (ByteBuffer bytes2 : list2) {
            hash = 31 * hash + Internal.hashCodeByteBuffer(bytes2);
        }
        return hash;
    }

    public static int hashCodeByteBuffer(ByteBuffer bytes2) {
        if (bytes2.hasArray()) {
            int h = Internal.partialHash(bytes2.capacity(), bytes2.array(), bytes2.arrayOffset(), bytes2.capacity());
            return h == 0 ? 1 : h;
        }
        int bufferSize = bytes2.capacity() > 4096 ? 4096 : bytes2.capacity();
        byte[] buffer = new byte[bufferSize];
        ByteBuffer duplicated = bytes2.duplicate();
        duplicated.clear();
        int h = bytes2.capacity();
        while (duplicated.remaining() > 0) {
            int length = duplicated.remaining() <= bufferSize ? duplicated.remaining() : bufferSize;
            duplicated.get(buffer, 0, length);
            h = Internal.partialHash(h, buffer, 0, length);
        }
        return h == 0 ? 1 : h;
    }

    public static <T extends MessageLite> T getDefaultInstance(Class<T> clazz) {
        try {
            Method method = clazz.getMethod("getDefaultInstance", new Class[0]);
            return (T)((MessageLite)method.invoke((Object)method, new Object[0]));
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get default instance for " + clazz, e);
        }
    }

    static Object mergeMessage(Object destination, Object source) {
        return ((MessageLite)destination).toBuilder().mergeFrom((MessageLite)source).buildPartial();
    }

    public static interface FloatList
    extends ProtobufList<Float> {
        public float getFloat(int var1);

        public void addFloat(float var1);

        public float setFloat(int var1, float var2);

        public FloatList mutableCopyWithCapacity(int var1);
    }

    public static interface DoubleList
    extends ProtobufList<Double> {
        public double getDouble(int var1);

        public void addDouble(double var1);

        public double setDouble(int var1, double var2);

        public DoubleList mutableCopyWithCapacity(int var1);
    }

    public static interface LongList
    extends ProtobufList<Long> {
        public long getLong(int var1);

        public void addLong(long var1);

        public long setLong(int var1, long var2);

        public LongList mutableCopyWithCapacity(int var1);
    }

    public static interface BooleanList
    extends ProtobufList<Boolean> {
        public boolean getBoolean(int var1);

        public void addBoolean(boolean var1);

        public boolean setBoolean(int var1, boolean var2);

        public BooleanList mutableCopyWithCapacity(int var1);
    }

    public static interface IntList
    extends ProtobufList<Integer> {
        public int getInt(int var1);

        public void addInt(int var1);

        public int setInt(int var1, int var2);

        public IntList mutableCopyWithCapacity(int var1);
    }

    public static interface ProtobufList<E>
    extends List<E>,
    RandomAccess {
        public void makeImmutable();

        public boolean isModifiable();

        public ProtobufList<E> mutableCopyWithCapacity(int var1);
    }

    public static class MapAdapter<K, V, RealValue>
    extends AbstractMap<K, V> {
        private final Map<K, RealValue> realMap;
        private final Converter<RealValue, V> valueConverter;

        public static <T extends EnumLite> Converter<Integer, T> newEnumConverter(final EnumLiteMap<T> enumMap, final T unrecognizedValue) {
            return new Converter<Integer, T>(){

                @Override
                public T doForward(Integer value) {
                    Object result2 = enumMap.findValueByNumber(value);
                    return result2 == null ? unrecognizedValue : result2;
                }

                @Override
                public Integer doBackward(T value) {
                    return value.getNumber();
                }
            };
        }

        public MapAdapter(Map<K, RealValue> realMap, Converter<RealValue, V> valueConverter) {
            this.realMap = realMap;
            this.valueConverter = valueConverter;
        }

        @Override
        public V get(Object key) {
            RealValue result2 = this.realMap.get(key);
            if (result2 == null) {
                return null;
            }
            return this.valueConverter.doForward(result2);
        }

        @Override
        public V put(K key, V value) {
            RealValue oldValue = this.realMap.put(key, this.valueConverter.doBackward(value));
            if (oldValue == null) {
                return null;
            }
            return this.valueConverter.doForward(oldValue);
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            return new SetAdapter(this.realMap.entrySet());
        }

        private class EntryAdapter
        implements Map.Entry<K, V> {
            private final Map.Entry<K, RealValue> realEntry;

            public EntryAdapter(Map.Entry<K, RealValue> realEntry) {
                this.realEntry = realEntry;
            }

            @Override
            public K getKey() {
                return this.realEntry.getKey();
            }

            @Override
            public V getValue() {
                return MapAdapter.this.valueConverter.doForward(this.realEntry.getValue());
            }

            @Override
            public V setValue(V value) {
                Object oldValue = this.realEntry.setValue(MapAdapter.this.valueConverter.doBackward(value));
                if (oldValue == null) {
                    return null;
                }
                return MapAdapter.this.valueConverter.doForward(oldValue);
            }
        }

        private class IteratorAdapter
        implements Iterator<Map.Entry<K, V>> {
            private final Iterator<Map.Entry<K, RealValue>> realIterator;

            public IteratorAdapter(Iterator<Map.Entry<K, RealValue>> realIterator) {
                this.realIterator = realIterator;
            }

            @Override
            public boolean hasNext() {
                return this.realIterator.hasNext();
            }

            @Override
            public Map.Entry<K, V> next() {
                return new EntryAdapter(this.realIterator.next());
            }

            @Override
            public void remove() {
                this.realIterator.remove();
            }
        }

        private class SetAdapter
        extends AbstractSet<Map.Entry<K, V>> {
            private final Set<Map.Entry<K, RealValue>> realSet;

            public SetAdapter(Set<Map.Entry<K, RealValue>> realSet) {
                this.realSet = realSet;
            }

            @Override
            public Iterator<Map.Entry<K, V>> iterator() {
                return new IteratorAdapter(this.realSet.iterator());
            }

            @Override
            public int size() {
                return this.realSet.size();
            }
        }

        public static interface Converter<A, B> {
            public B doForward(A var1);

            public A doBackward(B var1);
        }
    }

    public static class ListAdapter<F, T>
    extends AbstractList<T> {
        private final List<F> fromList;
        private final Converter<F, T> converter;

        public ListAdapter(List<F> fromList, Converter<F, T> converter) {
            this.fromList = fromList;
            this.converter = converter;
        }

        @Override
        public T get(int index) {
            return this.converter.convert(this.fromList.get(index));
        }

        @Override
        public int size() {
            return this.fromList.size();
        }

        public static interface Converter<F, T> {
            public T convert(F var1);
        }
    }

    public static interface EnumLiteMap<T extends EnumLite> {
        public T findValueByNumber(int var1);
    }

    public static interface EnumLite {
        public int getNumber();
    }
}

