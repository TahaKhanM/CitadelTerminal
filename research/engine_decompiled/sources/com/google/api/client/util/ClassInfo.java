/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class ClassInfo {
    private static final Map<Class<?>, ClassInfo> CACHE = new WeakHashMap();
    private static final Map<Class<?>, ClassInfo> CACHE_IGNORE_CASE = new WeakHashMap();
    private final Class<?> clazz;
    private final boolean ignoreCase;
    private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap = new IdentityHashMap();
    final List<String> names;

    public static ClassInfo of(Class<?> underlyingClass) {
        return ClassInfo.of(underlyingClass, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ClassInfo of(Class<?> underlyingClass, boolean ignoreCase) {
        ClassInfo classInfo;
        Map<Class<?>, ClassInfo> cache;
        if (underlyingClass == null) {
            return null;
        }
        Map<Class<?>, ClassInfo> map2 = cache = ignoreCase ? CACHE_IGNORE_CASE : CACHE;
        synchronized (map2) {
            classInfo = cache.get(underlyingClass);
            if (classInfo == null) {
                classInfo = new ClassInfo(underlyingClass, ignoreCase);
                cache.put(underlyingClass, classInfo);
            }
        }
        return classInfo;
    }

    public Class<?> getUnderlyingClass() {
        return this.clazz;
    }

    public final boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public FieldInfo getFieldInfo(String name) {
        if (name != null) {
            if (this.ignoreCase) {
                name = name.toLowerCase();
            }
            name = name.intern();
        }
        return this.nameToFieldInfoMap.get(name);
    }

    public Field getField(String name) {
        FieldInfo fieldInfo = this.getFieldInfo(name);
        return fieldInfo == null ? null : fieldInfo.getField();
    }

    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    public Collection<String> getNames() {
        return this.names;
    }

    private ClassInfo(Class<?> srcClass, boolean ignoreCase) {
        this.clazz = srcClass;
        this.ignoreCase = ignoreCase;
        Preconditions.checkArgument(!ignoreCase || !srcClass.isEnum(), "cannot ignore case on an enum: " + srcClass);
        TreeSet<String> nameSet = new TreeSet<String>(new Comparator<String>(){

            @Override
            public int compare(String s0, String s1) {
                return s0 == s1 ? 0 : (s0 == null ? -1 : (s1 == null ? 1 : s0.compareTo(s1)));
            }
        });
        for (Field field2 : srcClass.getDeclaredFields()) {
            FieldInfo conflictingFieldInfo;
            FieldInfo fieldInfo = FieldInfo.of(field2);
            if (fieldInfo == null) continue;
            String fieldName = fieldInfo.getName();
            if (ignoreCase) {
                fieldName = fieldName.toLowerCase().intern();
            }
            Preconditions.checkArgument((conflictingFieldInfo = this.nameToFieldInfoMap.get(fieldName)) == null, "two fields have the same %sname <%s>: %s and %s", ignoreCase ? "case-insensitive " : "", fieldName, field2, conflictingFieldInfo == null ? null : conflictingFieldInfo.getField());
            this.nameToFieldInfoMap.put(fieldName, fieldInfo);
            nameSet.add(fieldName);
        }
        Class<?> superClass = srcClass.getSuperclass();
        if (superClass != null) {
            ClassInfo superClassInfo = ClassInfo.of(superClass, ignoreCase);
            nameSet.addAll(superClassInfo.names);
            for (Map.Entry<String, FieldInfo> e : superClassInfo.nameToFieldInfoMap.entrySet()) {
                String name = e.getKey();
                if (this.nameToFieldInfoMap.containsKey(name)) continue;
                this.nameToFieldInfoMap.put(name, e.getValue());
            }
        }
        this.names = nameSet.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<String>(nameSet));
    }

    public Collection<FieldInfo> getFieldInfos() {
        return Collections.unmodifiableCollection(this.nameToFieldInfoMap.values());
    }
}

