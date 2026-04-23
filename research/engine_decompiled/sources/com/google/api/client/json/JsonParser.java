/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.json;

import com.google.api.client.json.CustomizeJsonParser;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.json.JsonString;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap();
    private static final Lock lock = new ReentrantLock();

    public abstract JsonFactory getFactory();

    public abstract void close() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract String getCurrentName() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public abstract String getText() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract float getFloatValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract BigDecimal getDecimalValue() throws IOException;

    public final <T> T parseAndClose(Class<T> destinationClass) throws IOException {
        return this.parseAndClose(destinationClass, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Beta
    public final <T> T parseAndClose(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            T t = this.parse(destinationClass, customizeParser);
            return t;
        }
        finally {
            this.close();
        }
    }

    public final void skipToKey(String keyToFind) throws IOException {
        this.skipToKey(Collections.singleton(keyToFind));
    }

    public final String skipToKey(Set<String> keysToFind) throws IOException {
        JsonToken curToken = this.startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = this.getText();
            this.nextToken();
            if (keysToFind.contains(key)) {
                return key;
            }
            this.skipChildren();
            curToken = this.nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = this.getCurrentToken();
        if (currentToken == null) {
            currentToken = this.nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken currentToken = this.startParsing();
        switch (currentToken) {
            case START_OBJECT: {
                currentToken = this.nextToken();
                Preconditions.checkArgument(currentToken == JsonToken.FIELD_NAME || currentToken == JsonToken.END_OBJECT, (Object)currentToken);
                break;
            }
            case START_ARRAY: {
                currentToken = this.nextToken();
                break;
            }
        }
        return currentToken;
    }

    public final void parseAndClose(Object destination) throws IOException {
        this.parseAndClose(destination, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Beta
    public final void parseAndClose(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        try {
            this.parse(destination, customizeParser);
        }
        finally {
            this.close();
        }
    }

    public final <T> T parse(Class<T> destinationClass) throws IOException {
        return this.parse(destinationClass, null);
    }

    @Beta
    public final <T> T parse(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        Object result2 = this.parse(destinationClass, false, customizeParser);
        return (T)result2;
    }

    public Object parse(Type dataType, boolean close) throws IOException {
        return this.parse(dataType, close, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Beta
    public Object parse(Type dataType, boolean close, CustomizeJsonParser customizeParser) throws IOException {
        try {
            if (!Void.class.equals((Object)dataType)) {
                this.startParsing();
            }
            Object object = this.parseValue(null, dataType, new ArrayList<Type>(), null, customizeParser, true);
            return object;
        }
        finally {
            if (close) {
                this.close();
            }
        }
    }

    public final void parse(Object destination) throws IOException {
        this.parse(destination, null);
    }

    @Beta
    public final void parse(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        ArrayList<Type> context = new ArrayList<Type>();
        context.add(destination.getClass());
        this.parse(context, destination, customizeParser);
    }

    private void parse(ArrayList<Type> context, Object destination, CustomizeJsonParser customizeParser) throws IOException {
        if (destination instanceof GenericJson) {
            ((GenericJson)destination).setFactory(this.getFactory());
        }
        JsonToken curToken = this.startParsingObjectOrArray();
        Class<?> destinationClass = destination.getClass();
        ClassInfo classInfo = ClassInfo.of(destinationClass);
        boolean isGenericData = GenericData.class.isAssignableFrom(destinationClass);
        if (!isGenericData && Map.class.isAssignableFrom(destinationClass)) {
            Map destinationMap = (Map)destination;
            this.parseMap(null, destinationMap, Types.getMapValueParameter(destinationClass), context, customizeParser);
            return;
        }
        while (curToken == JsonToken.FIELD_NAME) {
            String key = this.getText();
            this.nextToken();
            if (customizeParser != null && customizeParser.stopAt(destination, key)) {
                return;
            }
            FieldInfo fieldInfo = classInfo.getFieldInfo(key);
            if (fieldInfo != null) {
                if (fieldInfo.isFinal() && !fieldInfo.isPrimitive()) {
                    throw new IllegalArgumentException("final array/object fields are not supported");
                }
                Field field2 = fieldInfo.getField();
                int contextSize = context.size();
                context.add(field2.getGenericType());
                Object fieldValue = this.parseValue(field2, fieldInfo.getGenericType(), context, destination, customizeParser, true);
                context.remove(contextSize);
                fieldInfo.setValue(destination, fieldValue);
            } else if (isGenericData) {
                GenericData object = (GenericData)destination;
                object.set(key, this.parseValue(null, null, context, destination, customizeParser, true));
            } else {
                if (customizeParser != null) {
                    customizeParser.handleUnrecognizedKey(destination, key);
                }
                this.skipChildren();
            }
            curToken = this.nextToken();
        }
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return this.parseArrayAndClose(destinationCollectionClass, destinationItemClass, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            Collection<T> collection = this.parseArray(destinationCollectionClass, destinationItemClass, customizeParser);
            return collection;
        }
        finally {
            this.close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        this.parseArrayAndClose(destinationCollection, destinationItemClass, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            this.parseArray(destinationCollection, destinationItemClass, customizeParser);
        }
        finally {
            this.close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return this.parseArray(destinationCollectionClass, destinationItemClass, null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        Collection<Object> destinationCollection = Data.newCollectionInstance(destinationCollectionClass);
        this.parseArray(destinationCollection, destinationItemClass, customizeParser);
        return destinationCollection;
    }

    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        this.parseArray(destinationCollection, destinationItemClass, null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        this.parseArray(null, destinationCollection, destinationItemClass, new ArrayList<Type>(), customizeParser);
    }

    private <T> void parseArray(Field fieldContext, Collection<T> destinationCollection, Type destinationItemType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = this.startParsingObjectOrArray();
        while (curToken != JsonToken.END_ARRAY) {
            Object parsedValue = this.parseValue(fieldContext, destinationItemType, context, destinationCollection, customizeParser, true);
            destinationCollection.add(parsedValue);
            curToken = this.nextToken();
        }
    }

    private void parseMap(Field fieldContext, Map<String, Object> destinationMap, Type valueType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = this.startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = this.getText();
            this.nextToken();
            if (customizeParser != null && customizeParser.stopAt(destinationMap, key)) {
                return;
            }
            Object value = this.parseValue(fieldContext, valueType, context, destinationMap, customizeParser, true);
            destinationMap.put(key, value);
            curToken = this.nextToken();
        }
    }

    private final Object parseValue(Field fieldContext, Type valueType, ArrayList<Type> context, Object destination, CustomizeJsonParser customizeParser, boolean handlePolymorphic) throws IOException {
        Class<Comparable<Boolean>> valueClass;
        Class<Comparable<Boolean>> clazz = valueClass = (valueType = Data.resolveWildcardTypeOrTypeVariable(context, valueType)) instanceof Class ? (Class<Comparable<Boolean>>)valueType : null;
        if (valueType instanceof ParameterizedType) {
            valueClass = Types.getRawClass((ParameterizedType)valueType);
        }
        if (valueClass == Void.class) {
            this.skipChildren();
            return null;
        }
        JsonToken token = this.getCurrentToken();
        try {
            switch (this.getCurrentToken()) {
                case START_ARRAY: 
                case END_ARRAY: {
                    boolean isArray = Types.isArray(valueType);
                    Preconditions.checkArgument(valueType == null || isArray || valueClass != null && Types.isAssignableToOrFrom(valueClass, Collection.class), "expected collection or array type but got %s", valueType);
                    Collection<Object> collectionValue = null;
                    if (customizeParser != null && fieldContext != null) {
                        collectionValue = customizeParser.newInstanceForArray(destination, fieldContext);
                    }
                    if (collectionValue == null) {
                        collectionValue = Data.newCollectionInstance(valueType);
                    }
                    Type subType = null;
                    if (isArray) {
                        subType = Types.getArrayComponentType(valueType);
                    } else if (valueClass != null && Iterable.class.isAssignableFrom(valueClass)) {
                        subType = Types.getIterableParameter(valueType);
                    }
                    subType = Data.resolveWildcardTypeOrTypeVariable(context, subType);
                    this.parseArray(fieldContext, collectionValue, subType, context, customizeParser);
                    if (isArray) {
                        return Types.toArray(collectionValue, Types.getRawArrayComponentType(context, subType));
                    }
                    return collectionValue;
                }
                case START_OBJECT: 
                case FIELD_NAME: 
                case END_OBJECT: {
                    boolean isMap;
                    Preconditions.checkArgument(!Types.isArray(valueType), "expected object or map type but got %s", valueType);
                    Field typemapField = handlePolymorphic ? JsonParser.getCachedTypemapFieldFor(valueClass) : null;
                    Map<String, Object> newInstance = null;
                    if (valueClass != null && customizeParser != null) {
                        newInstance = customizeParser.newInstanceForObject(destination, valueClass);
                    }
                    boolean bl = isMap = valueClass != null && Types.isAssignableToOrFrom(valueClass, Map.class);
                    if (typemapField != null) {
                        newInstance = new GenericJson();
                    } else if (newInstance == null) {
                        newInstance = isMap || valueClass == null ? Data.newMapInstance(valueClass) : Types.newInstance(valueClass);
                    }
                    int contextSize = context.size();
                    if (valueType != null) {
                        context.add(valueType);
                    }
                    if (isMap && !GenericData.class.isAssignableFrom(valueClass)) {
                        Type subValueType;
                        Type type = subValueType = Map.class.isAssignableFrom(valueClass) ? Types.getMapValueParameter(valueType) : null;
                        if (subValueType != null) {
                            Map<String, Object> destinationMap = newInstance;
                            this.parseMap(fieldContext, destinationMap, subValueType, context, customizeParser);
                            return newInstance;
                        }
                    }
                    this.parse(context, newInstance, customizeParser);
                    if (valueType != null) {
                        context.remove(contextSize);
                    }
                    if (typemapField == null) {
                        return newInstance;
                    }
                    Object typeValueObject = ((GenericJson)newInstance).get(typemapField.getName());
                    Preconditions.checkArgument(typeValueObject != null, "No value specified for @JsonPolymorphicTypeMap field");
                    String typeValue = typeValueObject.toString();
                    JsonPolymorphicTypeMap typeMap = typemapField.getAnnotation(JsonPolymorphicTypeMap.class);
                    Class<?> typeClass = null;
                    for (JsonPolymorphicTypeMap.TypeDef typeDefinition : typeMap.typeDefinitions()) {
                        if (!typeDefinition.key().equals(typeValue)) continue;
                        typeClass = typeDefinition.ref();
                        break;
                    }
                    Preconditions.checkArgument(typeClass != null, "No TypeDef annotation found with key: " + typeValue);
                    JsonFactory factory = this.getFactory();
                    JsonParser parser = factory.createJsonParser(factory.toString(newInstance));
                    parser.startParsing();
                    return parser.parseValue(fieldContext, typeClass, context, null, null, false);
                }
                case VALUE_TRUE: 
                case VALUE_FALSE: {
                    Preconditions.checkArgument(valueType == null || valueClass == Boolean.TYPE || valueClass != null && valueClass.isAssignableFrom(Boolean.class), "expected type Boolean or boolean but got %s", valueType);
                    return token == JsonToken.VALUE_TRUE ? Boolean.TRUE : Boolean.FALSE;
                }
                case VALUE_NUMBER_FLOAT: 
                case VALUE_NUMBER_INT: {
                    Preconditions.checkArgument(fieldContext == null || fieldContext.getAnnotation(JsonString.class) == null, "number type formatted as a JSON number cannot use @JsonString annotation");
                    if (valueClass == null || valueClass.isAssignableFrom(BigDecimal.class)) {
                        return this.getDecimalValue();
                    }
                    if (valueClass == BigInteger.class) {
                        return this.getBigIntegerValue();
                    }
                    if (valueClass == Double.class || valueClass == Double.TYPE) {
                        return this.getDoubleValue();
                    }
                    if (valueClass == Long.class || valueClass == Long.TYPE) {
                        return this.getLongValue();
                    }
                    if (valueClass == Float.class || valueClass == Float.TYPE) {
                        return Float.valueOf(this.getFloatValue());
                    }
                    if (valueClass == Integer.class || valueClass == Integer.TYPE) {
                        return this.getIntValue();
                    }
                    if (valueClass == Short.class || valueClass == Short.TYPE) {
                        return this.getShortValue();
                    }
                    if (valueClass == Byte.class || valueClass == Byte.TYPE) {
                        return this.getByteValue();
                    }
                    throw new IllegalArgumentException("expected numeric type but got " + valueType);
                }
                case VALUE_STRING: {
                    String text = this.getText().trim().toLowerCase(Locale.US);
                    if (valueClass != Float.TYPE && valueClass != Float.class && valueClass != Double.TYPE && valueClass != Double.class || !text.equals("nan") && !text.equals("infinity") && !text.equals("-infinity")) {
                        Preconditions.checkArgument(valueClass == null || !Number.class.isAssignableFrom(valueClass) || fieldContext != null && fieldContext.getAnnotation(JsonString.class) != null, "number field formatted as a JSON string must use the @JsonString annotation");
                    }
                    return Data.parsePrimitiveValue(valueType, this.getText());
                }
                case VALUE_NULL: {
                    Preconditions.checkArgument(valueClass == null || !valueClass.isPrimitive(), "primitive number field but found a JSON null");
                    if (valueClass != null && 0 != (valueClass.getModifiers() & 0x600)) {
                        if (Types.isAssignableToOrFrom(valueClass, Collection.class)) {
                            return Data.nullOf(Data.newCollectionInstance(valueType).getClass());
                        }
                        if (Types.isAssignableToOrFrom(valueClass, Map.class)) {
                            return Data.nullOf(Data.newMapInstance(valueClass).getClass());
                        }
                    }
                    return Data.nullOf(Types.getRawArrayComponentType(context, valueType));
                }
            }
            throw new IllegalArgumentException("unexpected JSON node type: " + (Object)((Object)token));
        }
        catch (IllegalArgumentException e) {
            StringBuilder contextStringBuilder = new StringBuilder();
            String currentName = this.getCurrentName();
            if (currentName != null) {
                contextStringBuilder.append("key ").append(currentName);
            }
            if (fieldContext != null) {
                if (currentName != null) {
                    contextStringBuilder.append(", ");
                }
                contextStringBuilder.append("field ").append(fieldContext);
            }
            throw new IllegalArgumentException(contextStringBuilder.toString(), e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Field getCachedTypemapFieldFor(Class<?> key) {
        if (key == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(key)) {
                Field field2 = cachedTypemapFields.get(key);
                return field2;
            }
            Field value = null;
            Collection<FieldInfo> fieldInfos = ClassInfo.of(key).getFieldInfos();
            for (FieldInfo fieldInfo : fieldInfos) {
                Field field3 = fieldInfo.getField();
                JsonPolymorphicTypeMap typemapAnnotation = field3.getAnnotation(JsonPolymorphicTypeMap.class);
                if (typemapAnnotation == null) continue;
                Preconditions.checkArgument(value == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", key);
                Preconditions.checkArgument(Data.isPrimitive(field3.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", key, field3.getType());
                value = field3;
                JsonPolymorphicTypeMap.TypeDef[] typeDefs = typemapAnnotation.typeDefinitions();
                HashSet<String> typeDefKeys = Sets.newHashSet();
                Preconditions.checkArgument(typeDefs.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                for (JsonPolymorphicTypeMap.TypeDef typeDef : typeDefs) {
                    Preconditions.checkArgument(typeDefKeys.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                }
            }
            cachedTypemapFields.put(key, value);
            Field field4 = value;
            return field4;
        }
        finally {
            lock.unlock();
        }
    }
}

