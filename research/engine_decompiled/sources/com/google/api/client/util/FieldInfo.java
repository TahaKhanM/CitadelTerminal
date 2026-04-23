/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import com.google.api.client.util.NullValue;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Value;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap<Field, FieldInfo>();
    private final boolean isPrimitive;
    private final Field field;
    private final String name;

    public static FieldInfo of(Enum<?> enumValue) {
        try {
            FieldInfo result2 = FieldInfo.of(enumValue.getClass().getField(enumValue.name()));
            Preconditions.checkArgument(result2 != null, "enum constant missing @Value or @NullValue annotation: %s", enumValue);
            return result2;
        }
        catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static FieldInfo of(Field field2) {
        if (field2 == null) {
            return null;
        }
        Map<Field, FieldInfo> map2 = CACHE;
        synchronized (map2) {
            FieldInfo fieldInfo = CACHE.get(field2);
            boolean isEnumContant = field2.isEnumConstant();
            if (fieldInfo == null && (isEnumContant || !Modifier.isStatic(field2.getModifiers()))) {
                String fieldName;
                if (isEnumContant) {
                    Value value = field2.getAnnotation(Value.class);
                    if (value != null) {
                        fieldName = value.value();
                    } else {
                        NullValue nullValue = field2.getAnnotation(NullValue.class);
                        if (nullValue == null) {
                            return null;
                        }
                        fieldName = null;
                    }
                } else {
                    Key key = field2.getAnnotation(Key.class);
                    if (key == null) {
                        return null;
                    }
                    fieldName = key.value();
                    field2.setAccessible(true);
                }
                if ("##default".equals(fieldName)) {
                    fieldName = field2.getName();
                }
                fieldInfo = new FieldInfo(field2, fieldName);
                CACHE.put(field2, fieldInfo);
            }
            return fieldInfo;
        }
    }

    FieldInfo(Field field2, String name) {
        this.field = field2;
        this.name = name == null ? null : name.intern();
        this.isPrimitive = Data.isPrimitive(this.getType());
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    public Object getValue(Object obj) {
        return FieldInfo.getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object value) {
        FieldInfo.setFieldValue(this.field, obj, value);
    }

    public ClassInfo getClassInfo() {
        return ClassInfo.of(this.field.getDeclaringClass());
    }

    public <T extends Enum<T>> T enumValue() {
        return (T)Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }

    public static Object getFieldValue(Field field2, Object obj) {
        try {
            return field2.get(obj);
        }
        catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field2, Object obj, Object value) {
        if (Modifier.isFinal(field2.getModifiers())) {
            Object finalValue = FieldInfo.getFieldValue(field2, obj);
            if (value == null ? finalValue != null : !value.equals(finalValue)) {
                throw new IllegalArgumentException("expected final value <" + finalValue + "> but was <" + value + "> on " + field2.getName() + " field in " + obj.getClass().getName());
            }
        } else {
            try {
                field2.set(obj, value);
            }
            catch (SecurityException e) {
                throw new IllegalArgumentException(e);
            }
            catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}

