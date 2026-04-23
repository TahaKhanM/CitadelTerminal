/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
final class DataMap
extends AbstractMap<String, Object> {
    final Object object;
    final ClassInfo classInfo;

    DataMap(Object object, boolean ignoreCase) {
        this.object = object;
        this.classInfo = ClassInfo.of(object.getClass(), ignoreCase);
        Preconditions.checkArgument(!this.classInfo.isEnum());
    }

    public EntrySet entrySet() {
        return new EntrySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    @Override
    public Object get(Object key) {
        if (!(key instanceof String)) {
            return null;
        }
        FieldInfo fieldInfo = this.classInfo.getFieldInfo((String)key);
        if (fieldInfo == null) {
            return null;
        }
        return fieldInfo.getValue(this.object);
    }

    @Override
    public Object put(String key, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(key);
        Preconditions.checkNotNull(fieldInfo, "no field of key " + key);
        Object oldValue = fieldInfo.getValue(this.object);
        fieldInfo.setValue(this.object, Preconditions.checkNotNull(value));
        return oldValue;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class Entry
    implements Map.Entry<String, Object> {
        private Object fieldValue;
        private final FieldInfo fieldInfo;

        Entry(FieldInfo fieldInfo, Object fieldValue) {
            this.fieldInfo = fieldInfo;
            this.fieldValue = Preconditions.checkNotNull(fieldValue);
        }

        @Override
        public String getKey() {
            String result2 = this.fieldInfo.getName();
            if (DataMap.this.classInfo.getIgnoreCase()) {
                result2 = result2.toLowerCase();
            }
            return result2;
        }

        @Override
        public Object getValue() {
            return this.fieldValue;
        }

        @Override
        public Object setValue(Object value) {
            Object oldValue = this.fieldValue;
            this.fieldValue = Preconditions.checkNotNull(value);
            this.fieldInfo.setValue(DataMap.this.object, value);
            return oldValue;
        }

        @Override
        public int hashCode() {
            return this.getKey().hashCode() ^ this.getValue().hashCode();
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
            return this.getKey().equals(other.getKey()) && this.getValue().equals(other.getValue());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntryIterator
    implements Iterator<Map.Entry<String, Object>> {
        private int nextKeyIndex = -1;
        private FieldInfo nextFieldInfo;
        private Object nextFieldValue;
        private boolean isRemoved;
        private boolean isComputed;
        private FieldInfo currentFieldInfo;

        EntryIterator() {
        }

        @Override
        public boolean hasNext() {
            if (!this.isComputed) {
                this.isComputed = true;
                this.nextFieldValue = null;
                while (this.nextFieldValue == null && ++this.nextKeyIndex < DataMap.this.classInfo.names.size()) {
                    this.nextFieldInfo = DataMap.this.classInfo.getFieldInfo(DataMap.this.classInfo.names.get(this.nextKeyIndex));
                    this.nextFieldValue = this.nextFieldInfo.getValue(DataMap.this.object);
                }
            }
            return this.nextFieldValue != null;
        }

        @Override
        public Map.Entry<String, Object> next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.currentFieldInfo = this.nextFieldInfo;
            Object currentFieldValue = this.nextFieldValue;
            this.isComputed = false;
            this.isRemoved = false;
            this.nextFieldInfo = null;
            this.nextFieldValue = null;
            return new Entry(this.currentFieldInfo, currentFieldValue);
        }

        @Override
        public void remove() {
            Preconditions.checkState(this.currentFieldInfo != null && !this.isRemoved);
            this.isRemoved = true;
            this.currentFieldInfo.setValue(DataMap.this.object, null);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntrySet
    extends AbstractSet<Map.Entry<String, Object>> {
        EntrySet() {
        }

        public EntryIterator iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            int result2 = 0;
            for (String name : DataMap.this.classInfo.names) {
                if (DataMap.this.classInfo.getFieldInfo(name).getValue(DataMap.this.object) == null) continue;
                ++result2;
            }
            return result2;
        }

        @Override
        public void clear() {
            for (String name : DataMap.this.classInfo.names) {
                DataMap.this.classInfo.getFieldInfo(name).setValue(DataMap.this.object, null);
            }
        }

        @Override
        public boolean isEmpty() {
            for (String name : DataMap.this.classInfo.names) {
                if (DataMap.this.classInfo.getFieldInfo(name).getValue(DataMap.this.object) == null) continue;
                return false;
            }
            return true;
        }
    }
}

