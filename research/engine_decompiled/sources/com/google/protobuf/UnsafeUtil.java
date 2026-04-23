/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class UnsafeUtil {
    private static final Logger logger = Logger.getLogger(UnsafeUtil.class.getName());
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private static final MemoryAccessor MEMORY_ACCESSOR = UnsafeUtil.getMemoryAccessor();
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = UnsafeUtil.supportsUnsafeByteBufferOperations();
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.supportsUnsafeArrayOperations();
    private static final long BYTE_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(byte[].class);
    private static final long BOOLEAN_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(boolean[].class);
    private static final long BOOLEAN_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(boolean[].class);
    private static final long INT_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(int[].class);
    private static final long INT_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(int[].class);
    private static final long LONG_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(long[].class);
    private static final long LONG_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(long[].class);
    private static final long FLOAT_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(float[].class);
    private static final long FLOAT_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(float[].class);
    private static final long DOUBLE_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(double[].class);
    private static final long DOUBLE_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(double[].class);
    private static final long OBJECT_ARRAY_BASE_OFFSET = UnsafeUtil.arrayBaseOffset(Object[].class);
    private static final long OBJECT_ARRAY_INDEX_SCALE = UnsafeUtil.arrayIndexScale(Object[].class);
    private static final long BUFFER_ADDRESS_OFFSET = UnsafeUtil.fieldOffset(UnsafeUtil.bufferAddressField());
    private static final long STRING_VALUE_OFFSET = UnsafeUtil.fieldOffset(UnsafeUtil.stringValueField());

    private UnsafeUtil() {
    }

    static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    static long objectFieldOffset(Field field2) {
        return MEMORY_ACCESSOR.objectFieldOffset(field2);
    }

    private static int arrayBaseOffset(Class<?> clazz) {
        return HAS_UNSAFE_ARRAY_OPERATIONS ? MEMORY_ACCESSOR.arrayBaseOffset(clazz) : -1;
    }

    private static int arrayIndexScale(Class<?> clazz) {
        return HAS_UNSAFE_ARRAY_OPERATIONS ? MEMORY_ACCESSOR.arrayIndexScale(clazz) : -1;
    }

    static byte getByte(Object target, long offset) {
        return MEMORY_ACCESSOR.getByte(target, offset);
    }

    static void putByte(Object target, long offset, byte value) {
        MEMORY_ACCESSOR.putByte(target, offset, value);
    }

    static int getInt(Object target, long offset) {
        return MEMORY_ACCESSOR.getInt(target, offset);
    }

    static void putInt(Object target, long offset, int value) {
        MEMORY_ACCESSOR.putInt(target, offset, value);
    }

    static long getLong(Object target, long offset) {
        return MEMORY_ACCESSOR.getLong(target, offset);
    }

    static void putLong(Object target, long offset, long value) {
        MEMORY_ACCESSOR.putLong(target, offset, value);
    }

    static boolean getBoolean(Object target, long offset) {
        return MEMORY_ACCESSOR.getBoolean(target, offset);
    }

    static void putBoolean(Object target, long offset, boolean value) {
        MEMORY_ACCESSOR.putBoolean(target, offset, value);
    }

    static float getFloat(Object target, long offset) {
        return MEMORY_ACCESSOR.getFloat(target, offset);
    }

    static void putFloat(Object target, long offset, float value) {
        MEMORY_ACCESSOR.putFloat(target, offset, value);
    }

    static double getDouble(Object target, long offset) {
        return MEMORY_ACCESSOR.getDouble(target, offset);
    }

    static void putDouble(Object target, long offset, double value) {
        MEMORY_ACCESSOR.putDouble(target, offset, value);
    }

    static Object getObject(Object target, long offset) {
        return MEMORY_ACCESSOR.getObject(target, offset);
    }

    static void putObject(Object target, long offset, Object value) {
        MEMORY_ACCESSOR.putObject(target, offset, value);
    }

    static byte getByte(byte[] target, long index) {
        return MEMORY_ACCESSOR.getByte(target, BYTE_ARRAY_BASE_OFFSET + index);
    }

    static void putByte(byte[] target, long index, byte value) {
        MEMORY_ACCESSOR.putByte(target, BYTE_ARRAY_BASE_OFFSET + index, value);
    }

    static int getInt(int[] target, long index) {
        return MEMORY_ACCESSOR.getInt(target, INT_ARRAY_BASE_OFFSET + index * INT_ARRAY_INDEX_SCALE);
    }

    static void putInt(int[] target, long index, int value) {
        MEMORY_ACCESSOR.putInt(target, INT_ARRAY_BASE_OFFSET + index * INT_ARRAY_INDEX_SCALE, value);
    }

    static long getLong(long[] target, long index) {
        return MEMORY_ACCESSOR.getLong(target, LONG_ARRAY_BASE_OFFSET + index * LONG_ARRAY_INDEX_SCALE);
    }

    static void putLong(long[] target, long index, long value) {
        MEMORY_ACCESSOR.putLong(target, LONG_ARRAY_BASE_OFFSET + index * LONG_ARRAY_INDEX_SCALE, value);
    }

    static boolean getBoolean(boolean[] target, long index) {
        return MEMORY_ACCESSOR.getBoolean(target, BOOLEAN_ARRAY_BASE_OFFSET + index * BOOLEAN_ARRAY_INDEX_SCALE);
    }

    static void putBoolean(boolean[] target, long index, boolean value) {
        MEMORY_ACCESSOR.putBoolean(target, BOOLEAN_ARRAY_BASE_OFFSET + index * BOOLEAN_ARRAY_INDEX_SCALE, value);
    }

    static float getFloat(float[] target, long index) {
        return MEMORY_ACCESSOR.getFloat(target, FLOAT_ARRAY_BASE_OFFSET + index * FLOAT_ARRAY_INDEX_SCALE);
    }

    static void putFloat(float[] target, long index, float value) {
        MEMORY_ACCESSOR.putFloat(target, FLOAT_ARRAY_BASE_OFFSET + index * FLOAT_ARRAY_INDEX_SCALE, value);
    }

    static double getDouble(double[] target, long index) {
        return MEMORY_ACCESSOR.getDouble(target, DOUBLE_ARRAY_BASE_OFFSET + index * DOUBLE_ARRAY_INDEX_SCALE);
    }

    static void putDouble(double[] target, long index, double value) {
        MEMORY_ACCESSOR.putDouble(target, DOUBLE_ARRAY_BASE_OFFSET + index * DOUBLE_ARRAY_INDEX_SCALE, value);
    }

    static Object getObject(Object[] target, long index) {
        return MEMORY_ACCESSOR.getObject(target, OBJECT_ARRAY_BASE_OFFSET + index * OBJECT_ARRAY_INDEX_SCALE);
    }

    static void putObject(Object[] target, long index, Object value) {
        MEMORY_ACCESSOR.putObject(target, OBJECT_ARRAY_BASE_OFFSET + index * OBJECT_ARRAY_INDEX_SCALE, value);
    }

    static void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
        MEMORY_ACCESSOR.copyMemory(src, srcIndex, targetOffset, length);
    }

    static void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
        MEMORY_ACCESSOR.copyMemory(srcOffset, target, targetIndex, length);
    }

    static void copyMemory(byte[] src, long srcIndex, byte[] target, long targetIndex, long length) {
        System.arraycopy(src, (int)srcIndex, target, (int)targetIndex, (int)length);
    }

    static byte getByte(long address) {
        return MEMORY_ACCESSOR.getByte(address);
    }

    static void putByte(long address, byte value) {
        MEMORY_ACCESSOR.putByte(address, value);
    }

    static int getInt(long address) {
        return MEMORY_ACCESSOR.getInt(address);
    }

    static void putInt(long address, int value) {
        MEMORY_ACCESSOR.putInt(address, value);
    }

    static long getLong(long address) {
        return MEMORY_ACCESSOR.getLong(address);
    }

    static void putLong(long address, long value) {
        MEMORY_ACCESSOR.putLong(address, value);
    }

    static long addressOffset(ByteBuffer buffer) {
        return MEMORY_ACCESSOR.getLong(buffer, BUFFER_ADDRESS_OFFSET);
    }

    static String moveToString(char[] chars) {
        String str;
        if (STRING_VALUE_OFFSET == -1L) {
            return new String(chars);
        }
        try {
            str = (String)UNSAFE.allocateInstance(String.class);
        }
        catch (InstantiationException e) {
            return new String(chars);
        }
        UnsafeUtil.putObject(str, STRING_VALUE_OFFSET, (Object)chars);
        return str;
    }

    static Object getStaticObject(Field field2) {
        return MEMORY_ACCESSOR.getStaticObject(field2);
    }

    static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            unsafe = AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>(){

                @Override
                public Unsafe run() throws Exception {
                    Class<Unsafe> k = Unsafe.class;
                    for (Field f : k.getDeclaredFields()) {
                        f.setAccessible(true);
                        Object x = f.get(null);
                        if (!k.isInstance(x)) continue;
                        return (Unsafe)k.cast(x);
                    }
                    return null;
                }
            });
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return unsafe;
    }

    private static MemoryAccessor getMemoryAccessor() {
        if (UNSAFE == null) {
            return null;
        }
        return new JvmMemoryAccessor(UNSAFE);
    }

    private static boolean supportsUnsafeArrayOperations() {
        if (UNSAFE == null) {
            return false;
        }
        try {
            Class<?> clazz = UNSAFE.getClass();
            clazz.getMethod("objectFieldOffset", Field.class);
            clazz.getMethod("arrayBaseOffset", Class.class);
            clazz.getMethod("arrayIndexScale", Class.class);
            clazz.getMethod("getInt", Object.class, Long.TYPE);
            clazz.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            clazz.getMethod("getLong", Object.class, Long.TYPE);
            clazz.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            clazz.getMethod("getObject", Object.class, Long.TYPE);
            clazz.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            clazz.getMethod("getByte", Object.class, Long.TYPE);
            clazz.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            clazz.getMethod("getBoolean", Object.class, Long.TYPE);
            clazz.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            clazz.getMethod("getFloat", Object.class, Long.TYPE);
            clazz.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            clazz.getMethod("getDouble", Object.class, Long.TYPE);
            clazz.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        }
        catch (Throwable e) {
            logger.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + e);
            return false;
        }
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        if (UNSAFE == null) {
            return false;
        }
        try {
            Class<?> clazz = UNSAFE.getClass();
            clazz.getMethod("objectFieldOffset", Field.class);
            clazz.getMethod("getLong", Object.class, Long.TYPE);
            if (UnsafeUtil.bufferAddressField() == null) {
                return false;
            }
            clazz.getMethod("getByte", Long.TYPE);
            clazz.getMethod("putByte", Long.TYPE, Byte.TYPE);
            clazz.getMethod("getInt", Long.TYPE);
            clazz.getMethod("putInt", Long.TYPE, Integer.TYPE);
            clazz.getMethod("getLong", Long.TYPE);
            clazz.getMethod("putLong", Long.TYPE, Long.TYPE);
            clazz.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            clazz.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        }
        catch (Throwable e) {
            logger.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + e);
            return false;
        }
    }

    private static Field bufferAddressField() {
        Field field2 = UnsafeUtil.field(Buffer.class, "address");
        return field2 != null && field2.getType() == Long.TYPE ? field2 : null;
    }

    private static Field stringValueField() {
        Field field2 = UnsafeUtil.field(String.class, "value");
        return field2 != null && field2.getType() == char[].class ? field2 : null;
    }

    private static long fieldOffset(Field field2) {
        return field2 == null || MEMORY_ACCESSOR == null ? -1L : MEMORY_ACCESSOR.objectFieldOffset(field2);
    }

    private static Field field(Class<?> clazz, String fieldName) {
        Field field2;
        try {
            field2 = clazz.getDeclaredField(fieldName);
            field2.setAccessible(true);
        }
        catch (Throwable t) {
            field2 = null;
        }
        return field2;
    }

    private static final class JvmMemoryAccessor
    extends MemoryAccessor {
        JvmMemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override
        public byte getByte(long address) {
            return this.unsafe.getByte(address);
        }

        @Override
        public void putByte(long address, byte value) {
            this.unsafe.putByte(address, value);
        }

        @Override
        public int getInt(long address) {
            return this.unsafe.getInt(address);
        }

        @Override
        public void putInt(long address, int value) {
            this.unsafe.putInt(address, value);
        }

        @Override
        public long getLong(long address) {
            return this.unsafe.getLong(address);
        }

        @Override
        public void putLong(long address, long value) {
            this.unsafe.putLong(address, value);
        }

        @Override
        public byte getByte(Object target, long offset) {
            return this.unsafe.getByte(target, offset);
        }

        @Override
        public void putByte(Object target, long offset, byte value) {
            this.unsafe.putByte(target, offset, value);
        }

        @Override
        public boolean getBoolean(Object target, long offset) {
            return this.unsafe.getBoolean(target, offset);
        }

        @Override
        public void putBoolean(Object target, long offset, boolean value) {
            this.unsafe.putBoolean(target, offset, value);
        }

        @Override
        public float getFloat(Object target, long offset) {
            return this.unsafe.getFloat(target, offset);
        }

        @Override
        public void putFloat(Object target, long offset, float value) {
            this.unsafe.putFloat(target, offset, value);
        }

        @Override
        public double getDouble(Object target, long offset) {
            return this.unsafe.getDouble(target, offset);
        }

        @Override
        public void putDouble(Object target, long offset, double value) {
            this.unsafe.putDouble(target, offset, value);
        }

        @Override
        public void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
            this.unsafe.copyMemory(null, srcOffset, target, BYTE_ARRAY_BASE_OFFSET + targetIndex, length);
        }

        @Override
        public void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
            this.unsafe.copyMemory(src, BYTE_ARRAY_BASE_OFFSET + srcIndex, null, targetOffset, length);
        }

        @Override
        public Object getStaticObject(Field field2) {
            return this.getObject(this.unsafe.staticFieldBase(field2), this.unsafe.staticFieldOffset(field2));
        }
    }

    private static abstract class MemoryAccessor {
        Unsafe unsafe;

        MemoryAccessor(Unsafe unsafe) {
            this.unsafe = unsafe;
        }

        public final long objectFieldOffset(Field field2) {
            return this.unsafe.objectFieldOffset(field2);
        }

        public abstract byte getByte(Object var1, long var2);

        public abstract void putByte(Object var1, long var2, byte var4);

        public final int getInt(Object target, long offset) {
            return this.unsafe.getInt(target, offset);
        }

        public final void putInt(Object target, long offset, int value) {
            this.unsafe.putInt(target, offset, value);
        }

        public final long getLong(Object target, long offset) {
            return this.unsafe.getLong(target, offset);
        }

        public final void putLong(Object target, long offset, long value) {
            this.unsafe.putLong(target, offset, value);
        }

        public abstract boolean getBoolean(Object var1, long var2);

        public abstract void putBoolean(Object var1, long var2, boolean var4);

        public abstract float getFloat(Object var1, long var2);

        public abstract void putFloat(Object var1, long var2, float var4);

        public abstract double getDouble(Object var1, long var2);

        public abstract void putDouble(Object var1, long var2, double var4);

        public final Object getObject(Object target, long offset) {
            return this.unsafe.getObject(target, offset);
        }

        public final void putObject(Object target, long offset, Object value) {
            this.unsafe.putObject(target, offset, value);
        }

        public final int arrayBaseOffset(Class<?> clazz) {
            return this.unsafe.arrayBaseOffset(clazz);
        }

        public final int arrayIndexScale(Class<?> clazz) {
            return this.unsafe.arrayIndexScale(clazz);
        }

        public abstract byte getByte(long var1);

        public abstract void putByte(long var1, byte var3);

        public abstract int getInt(long var1);

        public abstract void putInt(long var1, int var3);

        public abstract long getLong(long var1);

        public abstract void putLong(long var1, long var3);

        public abstract Object getStaticObject(Field var1);

        public abstract void copyMemory(long var1, byte[] var3, long var4, long var6);

        public abstract void copyMemory(byte[] var1, long var2, long var4, long var6);
    }
}

