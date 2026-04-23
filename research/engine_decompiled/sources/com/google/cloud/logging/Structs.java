/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.client.util.Types;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class Structs {
    private static final Function<Value, Object> VALUE_TO_OBJECT = new Function<Value, Object>(){

        @Override
        public Object apply(Value value) {
            return Structs.valueToObject(value);
        }
    };
    private static final Function<Object, Value> OBJECT_TO_VALUE = new Function<Object, Value>(){

        @Override
        public Value apply(Object obj) {
            return Structs.objectToValue(obj);
        }
    };

    private Structs() {
    }

    static Map<String, Object> asMap(Struct struct) {
        return new StructMap(Preconditions.checkNotNull(struct));
    }

    static Struct newStruct(Map<String, ?> map2) {
        Map<String, Value> valueMap = Maps.transformValues(Preconditions.checkNotNull(map2), OBJECT_TO_VALUE);
        return Struct.newBuilder().putAllFields(valueMap).build();
    }

    private static Object valueToObject(Value value) {
        switch (value.getKindCase()) {
            case NULL_VALUE: {
                return null;
            }
            case NUMBER_VALUE: {
                return value.getNumberValue();
            }
            case STRING_VALUE: {
                return value.getStringValue();
            }
            case BOOL_VALUE: {
                return value.getBoolValue();
            }
            case STRUCT_VALUE: {
                return new StructMap(value.getStructValue());
            }
            case LIST_VALUE: {
                return Lists.transform(value.getListValue().getValuesList(), VALUE_TO_OBJECT);
            }
        }
        throw new IllegalArgumentException(String.format("Unsupported protobuf value %s", value));
    }

    private static Value objectToValue(Object obj) {
        Value.Builder builder = Value.newBuilder();
        if (obj == null) {
            builder.setNullValue(NullValue.NULL_VALUE);
            return builder.build();
        }
        Class<?> objClass = obj.getClass();
        if (obj instanceof String) {
            builder.setStringValue((String)obj);
        } else if (obj instanceof Number) {
            builder.setNumberValue(((Number)obj).doubleValue());
        } else if (obj instanceof Boolean) {
            builder.setBoolValue((Boolean)obj);
        } else if (obj instanceof Iterable || objClass.isArray()) {
            builder.setListValue(ListValue.newBuilder().addAllValues(Iterables.transform(Types.iterableOf(obj), OBJECT_TO_VALUE)));
        } else if (objClass.isEnum()) {
            builder.setStringValue(((Enum)obj).name());
        } else if (obj instanceof Map) {
            Map map2 = (Map)obj;
            builder.setStructValue(Structs.newStruct(map2));
        } else {
            throw new IllegalArgumentException(String.format("Unsupported protobuf value %s", obj));
        }
        return builder.build();
    }

    private static final class StructMap
    extends AbstractMap<String, Object> {
        private final Set<Map.Entry<String, Object>> entrySet;

        private StructMap(Struct struct) {
            this.entrySet = new StructSet(struct);
        }

        @Override
        public Set<Map.Entry<String, Object>> entrySet() {
            return this.entrySet;
        }

        private static final class StructSet
        extends AbstractSet<Map.Entry<String, Object>> {
            private static final Function<Map.Entry<String, Value>, Map.Entry<String, Object>> VALUE_TO_OBJECT = new Function<Map.Entry<String, Value>, Map.Entry<String, Object>>(){

                @Override
                public Map.Entry<String, Object> apply(Map.Entry<String, Value> entry) {
                    return new AbstractMap.SimpleEntry<String, Object>(entry.getKey(), Structs.valueToObject(entry.getValue()));
                }
            };
            private final Struct struct;

            private StructSet(Struct struct) {
                this.struct = struct;
            }

            @Override
            public Iterator<Map.Entry<String, Object>> iterator() {
                return Iterators.transform(this.struct.getFieldsMap().entrySet().iterator(), VALUE_TO_OBJECT);
            }

            @Override
            public int size() {
                return this.struct.getFieldsMap().size();
            }
        }
    }
}

