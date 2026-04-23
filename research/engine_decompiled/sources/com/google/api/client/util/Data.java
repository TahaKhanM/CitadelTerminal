/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.DataMap;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Data {
    public static final Boolean NULL_BOOLEAN = new Boolean(true);
    public static final String NULL_STRING = new String();
    public static final Character NULL_CHARACTER = new Character('\u0000');
    public static final Byte NULL_BYTE = new Byte(0);
    public static final Short NULL_SHORT = new Short(0);
    public static final Integer NULL_INTEGER = new Integer(0);
    public static final Float NULL_FLOAT = new Float(0.0f);
    public static final Long NULL_LONG = new Long(0L);
    public static final Double NULL_DOUBLE = new Double(0.0);
    public static final BigInteger NULL_BIG_INTEGER = new BigInteger("0");
    public static final BigDecimal NULL_BIG_DECIMAL = new BigDecimal("0");
    public static final DateTime NULL_DATE_TIME = new DateTime(0L);
    private static final ConcurrentHashMap<Class<?>, Object> NULL_CACHE = new ConcurrentHashMap();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <T> T nullOf(Class<?> objClass) {
        Object result2 = NULL_CACHE.get(objClass);
        if (result2 == null) {
            ConcurrentHashMap<Class<?>, Object> concurrentHashMap = NULL_CACHE;
            synchronized (concurrentHashMap) {
                result2 = NULL_CACHE.get(objClass);
                if (result2 == null) {
                    if (objClass.isArray()) {
                        int dims = 0;
                        Class<?> componentType = objClass;
                        do {
                            componentType = componentType.getComponentType();
                            ++dims;
                        } while (componentType.isArray());
                        result2 = Array.newInstance(componentType, new int[dims]);
                    } else if (objClass.isEnum()) {
                        FieldInfo fieldInfo = ClassInfo.of(objClass).getFieldInfo(null);
                        Preconditions.checkNotNull(fieldInfo, "enum missing constant with @NullValue annotation: %s", objClass);
                        Object e = fieldInfo.enumValue();
                        result2 = e;
                    } else {
                        result2 = Types.newInstance(objClass);
                    }
                    NULL_CACHE.put(objClass, result2);
                }
            }
        }
        Object tResult = result2;
        return (T)tResult;
    }

    public static boolean isNull(Object object) {
        return object != null && object == NULL_CACHE.get(object.getClass());
    }

    public static Map<String, Object> mapOf(Object data) {
        if (data == null || Data.isNull(data)) {
            return Collections.emptyMap();
        }
        if (data instanceof Map) {
            Map result2 = (Map)data;
            return result2;
        }
        DataMap result3 = new DataMap(data, false);
        return result3;
    }

    public static <T> T clone(T data) {
        Object copy2;
        if (data == null || Data.isPrimitive(data.getClass())) {
            return data;
        }
        if (data instanceof GenericData) {
            return (T)((GenericData)data).clone();
        }
        Class<?> dataClass = data.getClass();
        if (dataClass.isArray()) {
            copy2 = Array.newInstance(dataClass.getComponentType(), Array.getLength(data));
        } else if (data instanceof ArrayMap) {
            copy2 = ((ArrayMap)data).clone();
        } else {
            if ("java.util.Arrays$ArrayList".equals(dataClass.getName())) {
                Object[] arrayCopy = ((List)data).toArray();
                Data.deepCopy(arrayCopy, arrayCopy);
                List<Object> copy3 = Arrays.asList(arrayCopy);
                return (T)copy3;
            }
            copy2 = Types.newInstance(dataClass);
        }
        Data.deepCopy(data, copy2);
        return (T)copy2;
    }

    public static void deepCopy(Object src, Object dest) {
        Class<?> srcClass = src.getClass();
        Preconditions.checkArgument(srcClass == dest.getClass());
        if (srcClass.isArray()) {
            Preconditions.checkArgument(Array.getLength(src) == Array.getLength(dest));
            int index = 0;
            for (Object value : Types.iterableOf(src)) {
                Array.set(dest, index++, Data.clone(value));
            }
        } else if (Collection.class.isAssignableFrom(srcClass)) {
            Collection srcCollection = (Collection)src;
            if (ArrayList.class.isAssignableFrom(srcClass)) {
                ArrayList destArrayList = (ArrayList)dest;
                destArrayList.ensureCapacity(srcCollection.size());
            }
            Collection destCollection = (Collection)dest;
            for (Object srcValue : srcCollection) {
                destCollection.add(Data.clone(srcValue));
            }
        } else {
            boolean isGenericData = GenericData.class.isAssignableFrom(srcClass);
            if (isGenericData || !Map.class.isAssignableFrom(srcClass)) {
                ClassInfo classInfo = isGenericData ? ((GenericData)src).classInfo : ClassInfo.of(srcClass);
                for (String fieldName : classInfo.names) {
                    Object srcValue;
                    FieldInfo fieldInfo = classInfo.getFieldInfo(fieldName);
                    if (fieldInfo.isFinal() || isGenericData && fieldInfo.isPrimitive() || (srcValue = fieldInfo.getValue(src)) == null) continue;
                    fieldInfo.setValue(dest, Data.clone(srcValue));
                }
            } else if (ArrayMap.class.isAssignableFrom(srcClass)) {
                ArrayMap destMap = (ArrayMap)dest;
                ArrayMap srcMap = (ArrayMap)src;
                int size2 = srcMap.size();
                for (int i = 0; i < size2; ++i) {
                    Object srcValue = srcMap.getValue(i);
                    destMap.set(i, Data.clone(srcValue));
                }
            } else {
                Map destMap = (Map)dest;
                Map srcMap = (Map)src;
                for (Map.Entry srcEntry : srcMap.entrySet()) {
                    destMap.put(srcEntry.getKey(), Data.clone(srcEntry.getValue()));
                }
            }
        }
    }

    public static boolean isPrimitive(Type type) {
        if (type instanceof WildcardType) {
            type = Types.getBound((WildcardType)type);
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Class typeClass = (Class)type;
        return typeClass.isPrimitive() || typeClass == Character.class || typeClass == String.class || typeClass == Integer.class || typeClass == Long.class || typeClass == Short.class || typeClass == Byte.class || typeClass == Float.class || typeClass == Double.class || typeClass == BigInteger.class || typeClass == BigDecimal.class || typeClass == DateTime.class || typeClass == Boolean.class;
    }

    public static boolean isValueOfPrimitiveType(Object fieldValue) {
        return fieldValue == null || Data.isPrimitive(fieldValue.getClass());
    }

    public static Object parsePrimitiveValue(Type type, String stringValue) {
        Class primitiveClass;
        Class clazz = primitiveClass = type instanceof Class ? (Class)type : null;
        if (type == null || primitiveClass != null) {
            if (primitiveClass == Void.class) {
                return null;
            }
            if (stringValue == null || primitiveClass == null || primitiveClass.isAssignableFrom(String.class)) {
                return stringValue;
            }
            if (primitiveClass == Character.class || primitiveClass == Character.TYPE) {
                if (stringValue.length() != 1) {
                    throw new IllegalArgumentException("expected type Character/char but got " + primitiveClass);
                }
                return Character.valueOf(stringValue.charAt(0));
            }
            if (primitiveClass == Boolean.class || primitiveClass == Boolean.TYPE) {
                return Boolean.valueOf(stringValue);
            }
            if (primitiveClass == Byte.class || primitiveClass == Byte.TYPE) {
                return Byte.valueOf(stringValue);
            }
            if (primitiveClass == Short.class || primitiveClass == Short.TYPE) {
                return Short.valueOf(stringValue);
            }
            if (primitiveClass == Integer.class || primitiveClass == Integer.TYPE) {
                return Integer.valueOf(stringValue);
            }
            if (primitiveClass == Long.class || primitiveClass == Long.TYPE) {
                return Long.valueOf(stringValue);
            }
            if (primitiveClass == Float.class || primitiveClass == Float.TYPE) {
                return Float.valueOf(stringValue);
            }
            if (primitiveClass == Double.class || primitiveClass == Double.TYPE) {
                return Double.valueOf(stringValue);
            }
            if (primitiveClass == DateTime.class) {
                return DateTime.parseRfc3339(stringValue);
            }
            if (primitiveClass == BigInteger.class) {
                return new BigInteger(stringValue);
            }
            if (primitiveClass == BigDecimal.class) {
                return new BigDecimal(stringValue);
            }
            if (primitiveClass.isEnum()) {
                Object result2 = ClassInfo.of(primitiveClass).getFieldInfo(stringValue).enumValue();
                return result2;
            }
        }
        throw new IllegalArgumentException("expected primitive class, but got: " + type);
    }

    public static Collection<Object> newCollectionInstance(Type type) {
        Class collectionClass;
        if (type instanceof WildcardType) {
            type = Types.getBound((WildcardType)type);
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType)type).getRawType();
        }
        Class clazz = collectionClass = type instanceof Class ? (Class)type : null;
        if (type == null || type instanceof GenericArrayType || collectionClass != null && (collectionClass.isArray() || collectionClass.isAssignableFrom(ArrayList.class))) {
            return new ArrayList<Object>();
        }
        if (collectionClass == null) {
            throw new IllegalArgumentException("unable to create new instance of type: " + type);
        }
        if (collectionClass.isAssignableFrom(HashSet.class)) {
            return new HashSet<Object>();
        }
        if (collectionClass.isAssignableFrom(TreeSet.class)) {
            return new TreeSet<Object>();
        }
        Collection result2 = (Collection)Types.newInstance(collectionClass);
        return result2;
    }

    public static Map<String, Object> newMapInstance(Class<?> mapClass) {
        if (mapClass == null || mapClass.isAssignableFrom(ArrayMap.class)) {
            return ArrayMap.create();
        }
        if (mapClass.isAssignableFrom(TreeMap.class)) {
            return new TreeMap<String, Object>();
        }
        Map result2 = (Map)Types.newInstance(mapClass);
        return result2;
    }

    public static Type resolveWildcardTypeOrTypeVariable(List<Type> context, Type type) {
        if (type instanceof WildcardType) {
            type = Types.getBound((WildcardType)type);
        }
        while (type instanceof TypeVariable) {
            Type resolved = Types.resolveTypeVariable(context, (TypeVariable)type);
            if (resolved != null) {
                type = resolved;
            }
            if (!(type instanceof TypeVariable)) continue;
            type = ((TypeVariable)type).getBounds()[0];
        }
        return type;
    }

    static {
        NULL_CACHE.put(Boolean.class, NULL_BOOLEAN);
        NULL_CACHE.put(String.class, NULL_STRING);
        NULL_CACHE.put(Character.class, NULL_CHARACTER);
        NULL_CACHE.put(Byte.class, NULL_BYTE);
        NULL_CACHE.put(Short.class, NULL_SHORT);
        NULL_CACHE.put(Integer.class, NULL_INTEGER);
        NULL_CACHE.put(Float.class, NULL_FLOAT);
        NULL_CACHE.put(Long.class, NULL_LONG);
        NULL_CACHE.put(Double.class, NULL_DOUBLE);
        NULL_CACHE.put(BigInteger.class, NULL_BIG_INTEGER);
        NULL_CACHE.put(BigDecimal.class, NULL_BIG_DECIMAL);
        NULL_CACHE.put(DateTime.class, NULL_DATE_TIME);
    }
}

