/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Preconditions;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Types {
    public static ParameterizedType getSuperParameterizedType(Type type, Class<?> superClass) {
        if (type instanceof Class || type instanceof ParameterizedType) {
            block0: while (type != null && type != Object.class) {
                Class<?> rawType;
                if (type instanceof Class) {
                    rawType = (Class<?>)type;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType)type;
                    rawType = Types.getRawClass(parameterizedType);
                    if (rawType == superClass) {
                        return parameterizedType;
                    }
                    if (superClass.isInterface()) {
                        for (Type interfaceType : rawType.getGenericInterfaces()) {
                            Class<?> interfaceClass;
                            Class<?> clazz = interfaceClass = interfaceType instanceof Class ? (Class<?>)interfaceType : Types.getRawClass((ParameterizedType)interfaceType);
                            if (!superClass.isAssignableFrom(interfaceClass)) continue;
                            type = interfaceType;
                            continue block0;
                        }
                    }
                }
                type = rawType.getGenericSuperclass();
            }
        }
        return null;
    }

    public static boolean isAssignableToOrFrom(Class<?> classToCheck, Class<?> anotherClass) {
        return classToCheck.isAssignableFrom(anotherClass) || anotherClass.isAssignableFrom(classToCheck);
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (IllegalAccessException e) {
            throw Types.handleExceptionForNewInstance(e, clazz);
        }
        catch (InstantiationException e) {
            throw Types.handleExceptionForNewInstance(e, clazz);
        }
    }

    private static IllegalArgumentException handleExceptionForNewInstance(Exception e, Class<?> clazz) {
        StringBuilder buf = new StringBuilder("unable to create new instance of class ").append(clazz.getName());
        ArrayList<String> reasons = new ArrayList<String>();
        if (clazz.isArray()) {
            reasons.add("because it is an array");
        } else if (clazz.isPrimitive()) {
            reasons.add("because it is primitive");
        } else if (clazz == Void.class) {
            reasons.add("because it is void");
        } else {
            if (Modifier.isInterface(clazz.getModifiers())) {
                reasons.add("because it is an interface");
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                reasons.add("because it is abstract");
            }
            if (clazz.getEnclosingClass() != null && !Modifier.isStatic(clazz.getModifiers())) {
                reasons.add("because it is not static");
            }
            if (!Modifier.isPublic(clazz.getModifiers())) {
                reasons.add("possibly because it is not public");
            } else {
                try {
                    clazz.getConstructor(new Class[0]);
                }
                catch (NoSuchMethodException e1) {
                    reasons.add("because it has no accessible default constructor");
                }
            }
        }
        boolean and2 = false;
        for (String reason : reasons) {
            if (and2) {
                buf.append(" and");
            } else {
                and2 = true;
            }
            buf.append(" ").append(reason);
        }
        return new IllegalArgumentException(buf.toString(), e);
    }

    public static boolean isArray(Type type) {
        return type instanceof GenericArrayType || type instanceof Class && ((Class)type).isArray();
    }

    public static Type getArrayComponentType(Type array) {
        return array instanceof GenericArrayType ? ((GenericArrayType)array).getGenericComponentType() : ((Class)array).getComponentType();
    }

    public static Class<?> getRawClass(ParameterizedType parameterType) {
        return (Class)parameterType.getRawType();
    }

    public static Type getBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }

    public static Type resolveTypeVariable(List<Type> context, TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class rawGenericDeclaration = (Class)genericDeclaration;
            int contextIndex = context.size();
            ParameterizedType parameterizedType = null;
            while (parameterizedType == null && --contextIndex >= 0) {
                parameterizedType = Types.getSuperParameterizedType(context.get(contextIndex), rawGenericDeclaration);
            }
            if (parameterizedType != null) {
                Type resolve;
                TypeVariable<?> typeParameter;
                int index;
                TypeVariable<?>[] typeParameters = genericDeclaration.getTypeParameters();
                for (index = 0; index < typeParameters.length && !(typeParameter = typeParameters[index]).equals(typeVariable); ++index) {
                }
                Type result2 = parameterizedType.getActualTypeArguments()[index];
                if (result2 instanceof TypeVariable && (resolve = Types.resolveTypeVariable(context, (TypeVariable)result2)) != null) {
                    return resolve;
                }
                return result2;
            }
        }
        return null;
    }

    public static Class<?> getRawArrayComponentType(List<Type> context, Type componentType) {
        if (componentType instanceof TypeVariable) {
            componentType = Types.resolveTypeVariable(context, (TypeVariable)componentType);
        }
        if (componentType instanceof GenericArrayType) {
            Class<?> raw2 = Types.getRawArrayComponentType(context, Types.getArrayComponentType(componentType));
            return Array.newInstance(raw2, 0).getClass();
        }
        if (componentType instanceof Class) {
            return (Class)componentType;
        }
        if (componentType instanceof ParameterizedType) {
            return Types.getRawClass((ParameterizedType)componentType);
        }
        Preconditions.checkArgument(componentType == null, "wildcard type is not supported: %s", componentType);
        return Object.class;
    }

    public static Type getIterableParameter(Type iterableType) {
        return Types.getActualParameterAtPosition(iterableType, Iterable.class, 0);
    }

    public static Type getMapValueParameter(Type mapType) {
        return Types.getActualParameterAtPosition(mapType, Map.class, 1);
    }

    private static Type getActualParameterAtPosition(Type type, Class<?> superClass, int position) {
        Type resolve;
        ParameterizedType parameterizedType = Types.getSuperParameterizedType(type, superClass);
        if (parameterizedType == null) {
            return null;
        }
        Type valueType = parameterizedType.getActualTypeArguments()[position];
        if (valueType instanceof TypeVariable && (resolve = Types.resolveTypeVariable(Arrays.asList(type), (TypeVariable)valueType)) != null) {
            return resolve;
        }
        return valueType;
    }

    public static <T> Iterable<T> iterableOf(final Object value) {
        if (value instanceof Iterable) {
            return (Iterable)value;
        }
        Class<?> valueClass = value.getClass();
        Preconditions.checkArgument(valueClass.isArray(), "not an array or Iterable: %s", valueClass);
        Class<?> subClass = valueClass.getComponentType();
        if (!subClass.isPrimitive()) {
            return Arrays.asList((Object[])value);
        }
        return new Iterable<T>(){

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>(){
                    final int length;
                    int index;
                    {
                        this.length = Array.getLength(value);
                        this.index = 0;
                    }

                    @Override
                    public boolean hasNext() {
                        return this.index < this.length;
                    }

                    @Override
                    public T next() {
                        if (!this.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        return Array.get(value, this.index++);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static Object toArray(Collection<?> collection, Class<?> componentType) {
        if (componentType.isPrimitive()) {
            Object array = Array.newInstance(componentType, collection.size());
            int index = 0;
            for (Object value : collection) {
                Array.set(array, index++, value);
            }
            return array;
        }
        return collection.toArray((Object[])Array.newInstance(componentType, collection.size()));
    }

    private Types() {
    }
}

