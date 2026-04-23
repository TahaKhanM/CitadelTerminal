/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Excluder
implements TypeAdapterFactory,
Cloneable {
    private static final double IGNORE_VERSIONS = -1.0;
    public static final Excluder DEFAULT = new Excluder();
    private double version = -1.0;
    private int modifiers = 136;
    private boolean serializeInnerClasses = true;
    private boolean requireExpose;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    protected Excluder clone() {
        try {
            return (Excluder)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError((Object)e);
        }
    }

    public Excluder withVersion(double ignoreVersionsAfter) {
        Excluder result2 = this.clone();
        result2.version = ignoreVersionsAfter;
        return result2;
    }

    public Excluder withModifiers(int ... modifiers) {
        Excluder result2 = this.clone();
        result2.modifiers = 0;
        for (int modifier : modifiers) {
            result2.modifiers |= modifier;
        }
        return result2;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder result2 = this.clone();
        result2.serializeInnerClasses = false;
        return result2;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder result2 = this.clone();
        result2.requireExpose = true;
        return result2;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean serialization, boolean deserialization) {
        Excluder result2 = this.clone();
        if (serialization) {
            result2.serializationStrategies = new ArrayList<ExclusionStrategy>(this.serializationStrategies);
            result2.serializationStrategies.add(exclusionStrategy);
        }
        if (deserialization) {
            result2.deserializationStrategies = new ArrayList<ExclusionStrategy>(this.deserializationStrategies);
            result2.deserializationStrategies.add(exclusionStrategy);
        }
        return result2;
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        Class<T> rawType = type.getRawType();
        final boolean skipSerialize = this.excludeClass(rawType, true);
        final boolean skipDeserialize = this.excludeClass(rawType, false);
        if (!skipSerialize && !skipDeserialize) {
            return null;
        }
        return new TypeAdapter<T>(){
            private TypeAdapter<T> delegate;

            @Override
            public T read(JsonReader in) throws IOException {
                if (skipDeserialize) {
                    in.skipValue();
                    return null;
                }
                return this.delegate().read(in);
            }

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (skipSerialize) {
                    out.nullValue();
                    return;
                }
                this.delegate().write(out, value);
            }

            private TypeAdapter<T> delegate() {
                TypeAdapter d = this.delegate;
                return d != null ? d : (this.delegate = gson.getDelegateAdapter(Excluder.this, type));
            }
        };
    }

    public boolean excludeField(Field field2, boolean serialize) {
        List<ExclusionStrategy> list2;
        Expose annotation;
        if ((this.modifiers & field2.getModifiers()) != 0) {
            return true;
        }
        if (this.version != -1.0 && !this.isValidVersion(field2.getAnnotation(Since.class), field2.getAnnotation(Until.class))) {
            return true;
        }
        if (field2.isSynthetic()) {
            return true;
        }
        if (this.requireExpose && ((annotation = field2.getAnnotation(Expose.class)) == null || (serialize ? !annotation.serialize() : !annotation.deserialize()))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(field2.getType())) {
            return true;
        }
        if (this.isAnonymousOrLocal(field2.getType())) {
            return true;
        }
        List<ExclusionStrategy> list3 = list2 = serialize ? this.serializationStrategies : this.deserializationStrategies;
        if (!list2.isEmpty()) {
            FieldAttributes fieldAttributes = new FieldAttributes(field2);
            for (ExclusionStrategy exclusionStrategy : list2) {
                if (!exclusionStrategy.shouldSkipField(fieldAttributes)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean excludeClass(Class<?> clazz, boolean serialize) {
        if (this.version != -1.0 && !this.isValidVersion(clazz.getAnnotation(Since.class), clazz.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(clazz)) {
            return true;
        }
        if (this.isAnonymousOrLocal(clazz)) {
            return true;
        }
        List<ExclusionStrategy> list2 = serialize ? this.serializationStrategies : this.deserializationStrategies;
        for (ExclusionStrategy exclusionStrategy : list2) {
            if (!exclusionStrategy.shouldSkipClass(clazz)) continue;
            return true;
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    private boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !this.isStatic(clazz);
    }

    private boolean isStatic(Class<?> clazz) {
        return (clazz.getModifiers() & 8) != 0;
    }

    private boolean isValidVersion(Since since, Until until2) {
        return this.isValidSince(since) && this.isValidUntil(until2);
    }

    private boolean isValidSince(Since annotation) {
        double annotationVersion;
        return annotation == null || !((annotationVersion = annotation.value()) > this.version);
    }

    private boolean isValidUntil(Until annotation) {
        double annotationVersion;
        return annotation == null || !((annotationVersion = annotation.value()) <= this.version);
    }
}

