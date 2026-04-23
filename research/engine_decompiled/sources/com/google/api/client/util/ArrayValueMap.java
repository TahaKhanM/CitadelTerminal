/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class ArrayValueMap {
    private final Map<String, ArrayValue> keyMap = ArrayMap.create();
    private final Map<Field, ArrayValue> fieldMap = ArrayMap.create();
    private final Object destination;

    public ArrayValueMap(Object destination) {
        this.destination = destination;
    }

    public void setValues() {
        for (Map.Entry<String, ArrayValue> entry : this.keyMap.entrySet()) {
            Map destinationMap = (Map)this.destination;
            destinationMap.put(entry.getKey(), entry.getValue().toArray());
        }
        for (Map.Entry<Object, ArrayValue> entry : this.fieldMap.entrySet()) {
            FieldInfo.setFieldValue((Field)entry.getKey(), this.destination, entry.getValue().toArray());
        }
    }

    public void put(Field field2, Class<?> arrayComponentType, Object value) {
        ArrayValue arrayValue = this.fieldMap.get(field2);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(arrayComponentType);
            this.fieldMap.put(field2, arrayValue);
        }
        arrayValue.addValue(arrayComponentType, value);
    }

    public void put(String keyName, Class<?> arrayComponentType, Object value) {
        ArrayValue arrayValue = this.keyMap.get(keyName);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(arrayComponentType);
            this.keyMap.put(keyName, arrayValue);
        }
        arrayValue.addValue(arrayComponentType, value);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class ArrayValue {
        final Class<?> componentType;
        final ArrayList<Object> values = new ArrayList();

        ArrayValue(Class<?> componentType) {
            this.componentType = componentType;
        }

        Object toArray() {
            return Types.toArray(this.values, this.componentType);
        }

        void addValue(Class<?> componentType, Object value) {
            Preconditions.checkArgument(componentType == this.componentType);
            this.values.add(value);
        }
    }
}

