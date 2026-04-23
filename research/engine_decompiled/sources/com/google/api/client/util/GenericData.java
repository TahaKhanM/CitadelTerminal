/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.DataMap;
import com.google.api.client.util.FieldInfo;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GenericData
extends AbstractMap<String, Object>
implements Cloneable {
    Map<String, Object> unknownFields = ArrayMap.create();
    final ClassInfo classInfo;

    public GenericData() {
        this(EnumSet.noneOf(Flags.class));
    }

    public GenericData(EnumSet<Flags> flags) {
        this.classInfo = ClassInfo.of(this.getClass(), flags.contains((Object)Flags.IGNORE_CASE));
    }

    @Override
    public final Object get(Object name) {
        if (!(name instanceof String)) {
            return null;
        }
        String fieldName = (String)name;
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            return fieldInfo.getValue(this);
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.get(fieldName);
    }

    @Override
    public final Object put(String fieldName, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            Object oldValue = fieldInfo.getValue(this);
            fieldInfo.setValue(this, value);
            return oldValue;
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.put(fieldName, value);
    }

    public GenericData set(String fieldName, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            fieldInfo.setValue(this, value);
        } else {
            if (this.classInfo.getIgnoreCase()) {
                fieldName = fieldName.toLowerCase();
            }
            this.unknownFields.put(fieldName, value);
        }
        return this;
    }

    @Override
    public final void putAll(Map<? extends String, ?> map2) {
        for (Map.Entry<String, ?> entry : map2.entrySet()) {
            this.set(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public final Object remove(Object name) {
        if (!(name instanceof String)) {
            return null;
        }
        String fieldName = (String)name;
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            throw new UnsupportedOperationException();
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.remove(fieldName);
    }

    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        return new EntrySet();
    }

    @Override
    public GenericData clone() {
        try {
            GenericData result2 = (GenericData)super.clone();
            Data.deepCopy(this, result2);
            result2.unknownFields = Data.clone(this.unknownFields);
            return result2;
        }
        catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final Map<String, Object> getUnknownKeys() {
        return this.unknownFields;
    }

    public final void setUnknownKeys(Map<String, Object> unknownFields) {
        this.unknownFields = unknownFields;
    }

    public final ClassInfo getClassInfo() {
        return this.classInfo;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntryIterator
    implements Iterator<Map.Entry<String, Object>> {
        private boolean startedUnknown;
        private final Iterator<Map.Entry<String, Object>> fieldIterator;
        private final Iterator<Map.Entry<String, Object>> unknownIterator;

        EntryIterator(DataMap.EntrySet dataEntrySet) {
            this.fieldIterator = dataEntrySet.iterator();
            this.unknownIterator = GenericData.this.unknownFields.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.fieldIterator.hasNext() || this.unknownIterator.hasNext();
        }

        @Override
        public Map.Entry<String, Object> next() {
            if (!this.startedUnknown) {
                if (this.fieldIterator.hasNext()) {
                    return this.fieldIterator.next();
                }
                this.startedUnknown = true;
            }
            return this.unknownIterator.next();
        }

        @Override
        public void remove() {
            if (this.startedUnknown) {
                this.unknownIterator.remove();
            }
            this.fieldIterator.remove();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class EntrySet
    extends AbstractSet<Map.Entry<String, Object>> {
        private final DataMap.EntrySet dataEntrySet;

        EntrySet() {
            this.dataEntrySet = new DataMap(GenericData.this, GenericData.this.classInfo.getIgnoreCase()).entrySet();
        }

        @Override
        public Iterator<Map.Entry<String, Object>> iterator() {
            return new EntryIterator(this.dataEntrySet);
        }

        @Override
        public int size() {
            return GenericData.this.unknownFields.size() + this.dataEntrySet.size();
        }

        @Override
        public void clear() {
            GenericData.this.unknownFields.clear();
            this.dataEntrySet.clear();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Flags {
        IGNORE_CASE;

    }
}

