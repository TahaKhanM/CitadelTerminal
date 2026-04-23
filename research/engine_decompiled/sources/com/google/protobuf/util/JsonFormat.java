/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf.util;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.Any;
import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Duration;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.NullValue;
import com.google.protobuf.StringValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;
import com.google.protobuf.Value;
import com.google.protobuf.util.Durations;
import com.google.protobuf.util.FieldMaskUtil;
import com.google.protobuf.util.Timestamps;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class JsonFormat {
    private static final Logger logger = Logger.getLogger(JsonFormat.class.getName());

    private JsonFormat() {
    }

    public static Printer printer() {
        return new Printer(TypeRegistry.getEmptyTypeRegistry(), false, Collections.emptySet(), false, false, false);
    }

    public static Parser parser() {
        return new Parser(TypeRegistry.getEmptyTypeRegistry(), false, 100);
    }

    private static String unsignedToString(int value) {
        if (value >= 0) {
            return Integer.toString(value);
        }
        return Long.toString((long)value & 0xFFFFFFFFL);
    }

    private static String unsignedToString(long value) {
        if (value >= 0L) {
            return Long.toString(value);
        }
        return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
    }

    private static String getTypeName(String typeUrl) throws InvalidProtocolBufferException {
        String[] parts = typeUrl.split("/");
        if (parts.length == 1) {
            throw new InvalidProtocolBufferException("Invalid type url found: " + typeUrl);
        }
        return parts[parts.length - 1];
    }

    private static class ParserImpl {
        private final TypeRegistry registry;
        private final JsonParser jsonParser;
        private final boolean ignoringUnknownFields;
        private final int recursionLimit;
        private int currentDepth;
        private static final Map<String, WellKnownTypeParser> wellKnownTypeParsers = ParserImpl.buildWellKnownTypeParsers();
        private final Map<Descriptors.Descriptor, Map<String, Descriptors.FieldDescriptor>> fieldNameMaps = new HashMap<Descriptors.Descriptor, Map<String, Descriptors.FieldDescriptor>>();
        private static final BigInteger MAX_UINT64 = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        private static final double EPSILON = 1.0E-6;
        private static final BigDecimal MORE_THAN_ONE = new BigDecimal(String.valueOf(1.000001));
        private static final BigDecimal MAX_DOUBLE = new BigDecimal(String.valueOf(Double.MAX_VALUE)).multiply(MORE_THAN_ONE);
        private static final BigDecimal MIN_DOUBLE = new BigDecimal(String.valueOf(-1.7976931348623157E308)).multiply(MORE_THAN_ONE);

        ParserImpl(TypeRegistry registry, boolean ignoreUnknownFields, int recursionLimit) {
            this.registry = registry;
            this.ignoringUnknownFields = ignoreUnknownFields;
            this.jsonParser = new JsonParser();
            this.recursionLimit = recursionLimit;
            this.currentDepth = 0;
        }

        void merge(Reader json, Message.Builder builder) throws IOException {
            try {
                JsonReader reader = new JsonReader(json);
                reader.setLenient(false);
                this.merge(this.jsonParser.parse(reader), builder);
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (JsonIOException e) {
                if (e.getCause() instanceof IOException) {
                    throw (IOException)e.getCause();
                }
                throw new InvalidProtocolBufferException(e.getMessage());
            }
            catch (Exception e) {
                throw new InvalidProtocolBufferException(e.getMessage());
            }
        }

        void merge(String json, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                JsonReader reader = new JsonReader(new StringReader(json));
                reader.setLenient(false);
                this.merge(this.jsonParser.parse(reader), builder);
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (Exception e) {
                throw new InvalidProtocolBufferException(e.getMessage());
            }
        }

        private static Map<String, WellKnownTypeParser> buildWellKnownTypeParsers() {
            HashMap<String, WellKnownTypeParser> parsers = new HashMap<String, WellKnownTypeParser>();
            parsers.put(Any.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeAny(json, builder);
                }
            });
            WellKnownTypeParser wrappersPrinter = new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeWrapper(json, builder);
                }
            };
            parsers.put(BoolValue.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(Int32Value.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(UInt32Value.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(Int64Value.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(UInt64Value.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(StringValue.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(BytesValue.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(FloatValue.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(DoubleValue.getDescriptor().getFullName(), wrappersPrinter);
            parsers.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeTimestamp(json, builder);
                }
            });
            parsers.put(Duration.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeDuration(json, builder);
                }
            });
            parsers.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeFieldMask(json, builder);
                }
            });
            parsers.put(Struct.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeStruct(json, builder);
                }
            });
            parsers.put(ListValue.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeListValue(json, builder);
                }
            });
            parsers.put(Value.getDescriptor().getFullName(), new WellKnownTypeParser(){

                @Override
                public void merge(ParserImpl parser, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
                    parser.mergeValue(json, builder);
                }
            });
            return parsers;
        }

        private void merge(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            WellKnownTypeParser specialParser = wellKnownTypeParsers.get(builder.getDescriptorForType().getFullName());
            if (specialParser != null) {
                specialParser.merge(this, json, builder);
                return;
            }
            this.mergeMessage(json, builder, false);
        }

        private Map<String, Descriptors.FieldDescriptor> getFieldNameMap(Descriptors.Descriptor descriptor) {
            if (!this.fieldNameMaps.containsKey(descriptor)) {
                HashMap<String, Descriptors.FieldDescriptor> fieldNameMap = new HashMap<String, Descriptors.FieldDescriptor>();
                for (Descriptors.FieldDescriptor field2 : descriptor.getFields()) {
                    fieldNameMap.put(field2.getName(), field2);
                    fieldNameMap.put(field2.getJsonName(), field2);
                }
                this.fieldNameMaps.put(descriptor, fieldNameMap);
                return fieldNameMap;
            }
            return this.fieldNameMaps.get(descriptor);
        }

        private void mergeMessage(JsonElement json, Message.Builder builder, boolean skipTypeUrl) throws InvalidProtocolBufferException {
            if (!(json instanceof JsonObject)) {
                throw new InvalidProtocolBufferException("Expect message object but got: " + json);
            }
            JsonObject object = (JsonObject)json;
            Map<String, Descriptors.FieldDescriptor> fieldNameMap = this.getFieldNameMap(builder.getDescriptorForType());
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                if (skipTypeUrl && entry.getKey().equals("@type")) continue;
                Descriptors.FieldDescriptor field2 = fieldNameMap.get(entry.getKey());
                if (field2 == null) {
                    if (this.ignoringUnknownFields) continue;
                    throw new InvalidProtocolBufferException("Cannot find field: " + entry.getKey() + " in message " + builder.getDescriptorForType().getFullName());
                }
                this.mergeField(field2, entry.getValue(), builder);
            }
        }

        private void mergeAny(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor descriptor = builder.getDescriptorForType();
            Descriptors.FieldDescriptor typeUrlField = descriptor.findFieldByName("type_url");
            Descriptors.FieldDescriptor valueField = descriptor.findFieldByName("value");
            if (typeUrlField == null || valueField == null || typeUrlField.getType() != Descriptors.FieldDescriptor.Type.STRING || valueField.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            }
            if (!(json instanceof JsonObject)) {
                throw new InvalidProtocolBufferException("Expect message object but got: " + json);
            }
            JsonObject object = (JsonObject)json;
            if (object.entrySet().isEmpty()) {
                return;
            }
            JsonElement typeUrlElement = object.get("@type");
            if (typeUrlElement == null) {
                throw new InvalidProtocolBufferException("Missing type url when parsing: " + json);
            }
            String typeUrl = typeUrlElement.getAsString();
            Descriptors.Descriptor contentType = this.registry.find(JsonFormat.getTypeName(typeUrl));
            if (contentType == null) {
                throw new InvalidProtocolBufferException("Cannot resolve type: " + typeUrl);
            }
            builder.setField(typeUrlField, typeUrl);
            DynamicMessage.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
            WellKnownTypeParser specialParser = wellKnownTypeParsers.get(contentType.getFullName());
            if (specialParser != null) {
                JsonElement value = object.get("value");
                if (value != null) {
                    specialParser.merge(this, value, contentBuilder);
                }
            } else {
                this.mergeMessage(json, contentBuilder, true);
            }
            builder.setField(valueField, contentBuilder.build().toByteString());
        }

        private void mergeFieldMask(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            FieldMask value = FieldMaskUtil.fromJsonString(json.getAsString());
            builder.mergeFrom(value.toByteString());
        }

        private void mergeTimestamp(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                Timestamp value = Timestamps.parse(json.getAsString());
                builder.mergeFrom(value.toByteString());
            }
            catch (ParseException e) {
                throw new InvalidProtocolBufferException("Failed to parse timestamp: " + json);
            }
        }

        private void mergeDuration(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                Duration value = Durations.parse(json.getAsString());
                builder.mergeFrom(value.toByteString());
            }
            catch (ParseException e) {
                throw new InvalidProtocolBufferException("Failed to parse duration: " + json);
            }
        }

        private void mergeStruct(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor descriptor = builder.getDescriptorForType();
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName("fields");
            if (field2 == null) {
                throw new InvalidProtocolBufferException("Invalid Struct type.");
            }
            this.mergeMapField(field2, json, builder);
        }

        private void mergeListValue(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor descriptor = builder.getDescriptorForType();
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName("values");
            if (field2 == null) {
                throw new InvalidProtocolBufferException("Invalid ListValue type.");
            }
            this.mergeRepeatedField(field2, json, builder);
        }

        private void mergeValue(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor type = builder.getDescriptorForType();
            if (json instanceof JsonPrimitive) {
                JsonPrimitive primitive = (JsonPrimitive)json;
                if (primitive.isBoolean()) {
                    builder.setField(type.findFieldByName("bool_value"), primitive.getAsBoolean());
                } else if (primitive.isNumber()) {
                    builder.setField(type.findFieldByName("number_value"), primitive.getAsDouble());
                } else {
                    builder.setField(type.findFieldByName("string_value"), primitive.getAsString());
                }
            } else if (json instanceof JsonObject) {
                Descriptors.FieldDescriptor field2 = type.findFieldByName("struct_value");
                Message.Builder structBuilder = builder.newBuilderForField(field2);
                this.merge(json, structBuilder);
                builder.setField(field2, structBuilder.build());
            } else if (json instanceof JsonArray) {
                Descriptors.FieldDescriptor field3 = type.findFieldByName("list_value");
                Message.Builder listBuilder = builder.newBuilderForField(field3);
                this.merge(json, listBuilder);
                builder.setField(field3, listBuilder.build());
            } else if (json instanceof JsonNull) {
                builder.setField(type.findFieldByName("null_value"), NullValue.NULL_VALUE.getValueDescriptor());
            } else {
                throw new IllegalStateException("Unexpected json data: " + json);
            }
        }

        private void mergeWrapper(JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor type = builder.getDescriptorForType();
            Descriptors.FieldDescriptor field2 = type.findFieldByName("value");
            if (field2 == null) {
                throw new InvalidProtocolBufferException("Invalid wrapper type: " + type.getFullName());
            }
            builder.setField(field2, this.parseFieldValue(field2, json, builder));
        }

        private void mergeField(Descriptors.FieldDescriptor field2, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            if (field2.isRepeated()) {
                if (builder.getRepeatedFieldCount(field2) > 0) {
                    throw new InvalidProtocolBufferException("Field " + field2.getFullName() + " has already been set.");
                }
            } else {
                if (builder.hasField(field2)) {
                    throw new InvalidProtocolBufferException("Field " + field2.getFullName() + " has already been set.");
                }
                if (field2.getContainingOneof() != null && builder.getOneofFieldDescriptor(field2.getContainingOneof()) != null) {
                    Descriptors.FieldDescriptor other = builder.getOneofFieldDescriptor(field2.getContainingOneof());
                    throw new InvalidProtocolBufferException("Cannot set field " + field2.getFullName() + " because another field " + other.getFullName() + " belonging to the same oneof has already been set ");
                }
            }
            if (field2.isRepeated() && json instanceof JsonNull) {
                return;
            }
            if (field2.isMapField()) {
                this.mergeMapField(field2, json, builder);
            } else if (field2.isRepeated()) {
                this.mergeRepeatedField(field2, json, builder);
            } else {
                Object value = this.parseFieldValue(field2, json, builder);
                if (value != null) {
                    builder.setField(field2, value);
                }
            }
        }

        private void mergeMapField(Descriptors.FieldDescriptor field2, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            if (!(json instanceof JsonObject)) {
                throw new InvalidProtocolBufferException("Expect a map object but found: " + json);
            }
            Descriptors.Descriptor type = field2.getMessageType();
            Descriptors.FieldDescriptor keyField = type.findFieldByName("key");
            Descriptors.FieldDescriptor valueField = type.findFieldByName("value");
            if (keyField == null || valueField == null) {
                throw new InvalidProtocolBufferException("Invalid map field: " + field2.getFullName());
            }
            JsonObject object = (JsonObject)json;
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                Message.Builder entryBuilder = builder.newBuilderForField(field2);
                Object key = this.parseFieldValue(keyField, new JsonPrimitive(entry.getKey()), entryBuilder);
                Object value = this.parseFieldValue(valueField, entry.getValue(), entryBuilder);
                if (value == null) {
                    throw new InvalidProtocolBufferException("Map value cannot be null.");
                }
                entryBuilder.setField(keyField, key);
                entryBuilder.setField(valueField, value);
                builder.addRepeatedField(field2, entryBuilder.build());
            }
        }

        private void mergeRepeatedField(Descriptors.FieldDescriptor field2, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            if (!(json instanceof JsonArray)) {
                throw new InvalidProtocolBufferException("Expect an array but found: " + json);
            }
            JsonArray array = (JsonArray)json;
            for (int i = 0; i < array.size(); ++i) {
                Object value = this.parseFieldValue(field2, array.get(i), builder);
                if (value == null) {
                    throw new InvalidProtocolBufferException("Repeated field elements cannot be null in field: " + field2.getFullName());
                }
                builder.addRepeatedField(field2, value);
            }
        }

        private int parseInt32(JsonElement json) throws InvalidProtocolBufferException {
            try {
                return Integer.parseInt(json.getAsString());
            }
            catch (Exception exception) {
                try {
                    BigDecimal value = new BigDecimal(json.getAsString());
                    return value.intValueExact();
                }
                catch (Exception e) {
                    throw new InvalidProtocolBufferException("Not an int32 value: " + json);
                }
            }
        }

        private long parseInt64(JsonElement json) throws InvalidProtocolBufferException {
            try {
                return Long.parseLong(json.getAsString());
            }
            catch (Exception exception) {
                try {
                    BigDecimal value = new BigDecimal(json.getAsString());
                    return value.longValueExact();
                }
                catch (Exception e) {
                    throw new InvalidProtocolBufferException("Not an int64 value: " + json);
                }
            }
        }

        private int parseUint32(JsonElement json) throws InvalidProtocolBufferException {
            try {
                long result2 = Long.parseLong(json.getAsString());
                if (result2 < 0L || result2 > 0xFFFFFFFFL) {
                    throw new InvalidProtocolBufferException("Out of range uint32 value: " + json);
                }
                return (int)result2;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (Exception e) {
                try {
                    BigDecimal decimalValue = new BigDecimal(json.getAsString());
                    BigInteger value = decimalValue.toBigIntegerExact();
                    if (value.signum() < 0 || value.compareTo(new BigInteger("FFFFFFFF", 16)) > 0) {
                        throw new InvalidProtocolBufferException("Out of range uint32 value: " + json);
                    }
                    return value.intValue();
                }
                catch (InvalidProtocolBufferException e2) {
                    throw e2;
                }
                catch (Exception e3) {
                    throw new InvalidProtocolBufferException("Not an uint32 value: " + json);
                }
            }
        }

        private long parseUint64(JsonElement json) throws InvalidProtocolBufferException {
            try {
                BigDecimal decimalValue = new BigDecimal(json.getAsString());
                BigInteger value = decimalValue.toBigIntegerExact();
                if (value.compareTo(BigInteger.ZERO) < 0 || value.compareTo(MAX_UINT64) > 0) {
                    throw new InvalidProtocolBufferException("Out of range uint64 value: " + json);
                }
                return value.longValue();
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (Exception e) {
                throw new InvalidProtocolBufferException("Not an uint64 value: " + json);
            }
        }

        private boolean parseBool(JsonElement json) throws InvalidProtocolBufferException {
            if (json.getAsString().equals("true")) {
                return true;
            }
            if (json.getAsString().equals("false")) {
                return false;
            }
            throw new InvalidProtocolBufferException("Invalid bool value: " + json);
        }

        private float parseFloat(JsonElement json) throws InvalidProtocolBufferException {
            if (json.getAsString().equals("NaN")) {
                return Float.NaN;
            }
            if (json.getAsString().equals("Infinity")) {
                return Float.POSITIVE_INFINITY;
            }
            if (json.getAsString().equals("-Infinity")) {
                return Float.NEGATIVE_INFINITY;
            }
            try {
                double value = Double.parseDouble(json.getAsString());
                if (value > 3.402826869208755E38 || value < -3.402826869208755E38) {
                    throw new InvalidProtocolBufferException("Out of range float value: " + json);
                }
                return (float)value;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (Exception e) {
                throw new InvalidProtocolBufferException("Not a float value: " + json);
            }
        }

        private double parseDouble(JsonElement json) throws InvalidProtocolBufferException {
            if (json.getAsString().equals("NaN")) {
                return Double.NaN;
            }
            if (json.getAsString().equals("Infinity")) {
                return Double.POSITIVE_INFINITY;
            }
            if (json.getAsString().equals("-Infinity")) {
                return Double.NEGATIVE_INFINITY;
            }
            try {
                BigDecimal value = new BigDecimal(json.getAsString());
                if (value.compareTo(MAX_DOUBLE) > 0 || value.compareTo(MIN_DOUBLE) < 0) {
                    throw new InvalidProtocolBufferException("Out of range double value: " + json);
                }
                return value.doubleValue();
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (Exception e) {
                throw new InvalidProtocolBufferException("Not an double value: " + json);
            }
        }

        private String parseString(JsonElement json) {
            return json.getAsString();
        }

        private ByteString parseBytes(JsonElement json) throws InvalidProtocolBufferException {
            try {
                return ByteString.copyFrom(BaseEncoding.base64().decode(json.getAsString()));
            }
            catch (IllegalArgumentException e) {
                return ByteString.copyFrom(BaseEncoding.base64Url().decode(json.getAsString()));
            }
        }

        private Descriptors.EnumValueDescriptor parseEnum(Descriptors.EnumDescriptor enumDescriptor, JsonElement json) throws InvalidProtocolBufferException {
            String value = json.getAsString();
            Descriptors.EnumValueDescriptor result2 = enumDescriptor.findValueByName(value);
            if (result2 == null) {
                try {
                    int numericValue = this.parseInt32(json);
                    result2 = enumDescriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 ? enumDescriptor.findValueByNumberCreatingIfUnknown(numericValue) : enumDescriptor.findValueByNumber(numericValue);
                }
                catch (InvalidProtocolBufferException invalidProtocolBufferException) {
                    // empty catch block
                }
                if (result2 == null) {
                    throw new InvalidProtocolBufferException("Invalid enum value: " + value + " for enum type: " + enumDescriptor.getFullName());
                }
            }
            return result2;
        }

        private Object parseFieldValue(Descriptors.FieldDescriptor field2, JsonElement json, Message.Builder builder) throws InvalidProtocolBufferException {
            if (json instanceof JsonNull) {
                if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && field2.getMessageType().getFullName().equals(Value.getDescriptor().getFullName())) {
                    Value value = Value.newBuilder().setNullValueValue(0).build();
                    return builder.newBuilderForField(field2).mergeFrom(value.toByteString()).build();
                }
                if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM && field2.getEnumType().getFullName().equals(NullValue.getDescriptor().getFullName())) {
                    return field2.getEnumType().findValueByNumber(0);
                }
                return null;
            }
            switch (field2.getType()) {
                case INT32: 
                case SINT32: 
                case SFIXED32: {
                    return this.parseInt32(json);
                }
                case INT64: 
                case SINT64: 
                case SFIXED64: {
                    return this.parseInt64(json);
                }
                case BOOL: {
                    return this.parseBool(json);
                }
                case FLOAT: {
                    return Float.valueOf(this.parseFloat(json));
                }
                case DOUBLE: {
                    return this.parseDouble(json);
                }
                case UINT32: 
                case FIXED32: {
                    return this.parseUint32(json);
                }
                case UINT64: 
                case FIXED64: {
                    return this.parseUint64(json);
                }
                case STRING: {
                    return this.parseString(json);
                }
                case BYTES: {
                    return this.parseBytes(json);
                }
                case ENUM: {
                    return this.parseEnum(field2.getEnumType(), json);
                }
                case MESSAGE: 
                case GROUP: {
                    if (this.currentDepth >= this.recursionLimit) {
                        throw new InvalidProtocolBufferException("Hit recursion limit.");
                    }
                    ++this.currentDepth;
                    Message.Builder subBuilder = builder.newBuilderForField(field2);
                    this.merge(json, subBuilder);
                    --this.currentDepth;
                    return subBuilder.build();
                }
            }
            throw new InvalidProtocolBufferException("Invalid field type: " + (Object)((Object)field2.getType()));
        }

        private static interface WellKnownTypeParser {
            public void merge(ParserImpl var1, JsonElement var2, Message.Builder var3) throws InvalidProtocolBufferException;
        }
    }

    private static final class PrinterImpl {
        private final TypeRegistry registry;
        private final boolean alwaysOutputDefaultValueFields;
        private final Set<Descriptors.FieldDescriptor> includingDefaultValueFields;
        private final boolean preservingProtoFieldNames;
        private final boolean printingEnumsAsInts;
        private final TextGenerator generator;
        private final Gson gson;
        private final CharSequence blankOrSpace;
        private final CharSequence blankOrNewLine;
        private static final Map<String, WellKnownTypePrinter> wellKnownTypePrinters = PrinterImpl.buildWellKnownTypePrinters();

        PrinterImpl(TypeRegistry registry, boolean alwaysOutputDefaultValueFields, Set<Descriptors.FieldDescriptor> includingDefaultValueFields, boolean preservingProtoFieldNames, Appendable jsonOutput, boolean omittingInsignificantWhitespace, boolean printingEnumsAsInts) {
            this.registry = registry;
            this.alwaysOutputDefaultValueFields = alwaysOutputDefaultValueFields;
            this.includingDefaultValueFields = includingDefaultValueFields;
            this.preservingProtoFieldNames = preservingProtoFieldNames;
            this.printingEnumsAsInts = printingEnumsAsInts;
            this.gson = GsonHolder.DEFAULT_GSON;
            if (omittingInsignificantWhitespace) {
                this.generator = new CompactTextGenerator(jsonOutput);
                this.blankOrSpace = "";
                this.blankOrNewLine = "";
            } else {
                this.generator = new PrettyTextGenerator(jsonOutput);
                this.blankOrSpace = " ";
                this.blankOrNewLine = "\n";
            }
        }

        void print(MessageOrBuilder message) throws IOException {
            WellKnownTypePrinter specialPrinter = wellKnownTypePrinters.get(message.getDescriptorForType().getFullName());
            if (specialPrinter != null) {
                specialPrinter.print(this, message);
                return;
            }
            this.print(message, null);
        }

        private static Map<String, WellKnownTypePrinter> buildWellKnownTypePrinters() {
            HashMap<String, WellKnownTypePrinter> printers = new HashMap<String, WellKnownTypePrinter>();
            printers.put(Any.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printAny(message);
                }
            });
            WellKnownTypePrinter wrappersPrinter = new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printWrapper(message);
                }
            };
            printers.put(BoolValue.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(Int32Value.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(UInt32Value.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(Int64Value.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(UInt64Value.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(StringValue.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(BytesValue.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(FloatValue.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(DoubleValue.getDescriptor().getFullName(), wrappersPrinter);
            printers.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printTimestamp(message);
                }
            });
            printers.put(Duration.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printDuration(message);
                }
            });
            printers.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printFieldMask(message);
                }
            });
            printers.put(Struct.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printStruct(message);
                }
            });
            printers.put(Value.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printValue(message);
                }
            });
            printers.put(ListValue.getDescriptor().getFullName(), new WellKnownTypePrinter(){

                @Override
                public void print(PrinterImpl printer, MessageOrBuilder message) throws IOException {
                    printer.printListValue(message);
                }
            });
            return printers;
        }

        private void printAny(MessageOrBuilder message) throws IOException {
            if (Any.getDefaultInstance().equals(message)) {
                this.generator.print("{}");
                return;
            }
            Descriptors.Descriptor descriptor = message.getDescriptorForType();
            Descriptors.FieldDescriptor typeUrlField = descriptor.findFieldByName("type_url");
            Descriptors.FieldDescriptor valueField = descriptor.findFieldByName("value");
            if (typeUrlField == null || valueField == null || typeUrlField.getType() != Descriptors.FieldDescriptor.Type.STRING || valueField.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            }
            String typeUrl = (String)message.getField(typeUrlField);
            String typeName = JsonFormat.getTypeName(typeUrl);
            Descriptors.Descriptor type = this.registry.find(typeName);
            if (type == null) {
                throw new InvalidProtocolBufferException("Cannot find type for url: " + typeUrl);
            }
            ByteString content = (ByteString)message.getField(valueField);
            Message contentMessage = DynamicMessage.getDefaultInstance(type).getParserForType().parseFrom(content);
            WellKnownTypePrinter printer = wellKnownTypePrinters.get(typeName);
            if (printer != null) {
                this.generator.print("{" + this.blankOrNewLine);
                this.generator.indent();
                this.generator.print("\"@type\":" + this.blankOrSpace + this.gson.toJson(typeUrl) + "," + this.blankOrNewLine);
                this.generator.print("\"value\":" + this.blankOrSpace);
                printer.print(this, contentMessage);
                this.generator.print(this.blankOrNewLine);
                this.generator.outdent();
                this.generator.print("}");
            } else {
                this.print(contentMessage, typeUrl);
            }
        }

        private void printWrapper(MessageOrBuilder message) throws IOException {
            Descriptors.Descriptor descriptor = message.getDescriptorForType();
            Descriptors.FieldDescriptor valueField = descriptor.findFieldByName("value");
            if (valueField == null) {
                throw new InvalidProtocolBufferException("Invalid Wrapper type.");
            }
            this.printSingleFieldValue(valueField, message.getField(valueField));
        }

        private ByteString toByteString(MessageOrBuilder message) {
            if (message instanceof Message) {
                return ((Message)message).toByteString();
            }
            return ((Message.Builder)message).build().toByteString();
        }

        private void printTimestamp(MessageOrBuilder message) throws IOException {
            Timestamp value = Timestamp.parseFrom(this.toByteString(message));
            this.generator.print("\"" + Timestamps.toString(value) + "\"");
        }

        private void printDuration(MessageOrBuilder message) throws IOException {
            Duration value = Duration.parseFrom(this.toByteString(message));
            this.generator.print("\"" + Durations.toString(value) + "\"");
        }

        private void printFieldMask(MessageOrBuilder message) throws IOException {
            FieldMask value = FieldMask.parseFrom(this.toByteString(message));
            this.generator.print("\"" + FieldMaskUtil.toJsonString(value) + "\"");
        }

        private void printStruct(MessageOrBuilder message) throws IOException {
            Descriptors.Descriptor descriptor = message.getDescriptorForType();
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName("fields");
            if (field2 == null) {
                throw new InvalidProtocolBufferException("Invalid Struct type.");
            }
            this.printMapFieldValue(field2, message.getField(field2));
        }

        private void printValue(MessageOrBuilder message) throws IOException {
            Map<Descriptors.FieldDescriptor, Object> fields = message.getAllFields();
            if (fields.isEmpty()) {
                this.generator.print("null");
                return;
            }
            if (fields.size() != 1) {
                throw new InvalidProtocolBufferException("Invalid Value type.");
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fields.entrySet()) {
                this.printSingleFieldValue(entry.getKey(), entry.getValue());
            }
        }

        private void printListValue(MessageOrBuilder message) throws IOException {
            Descriptors.Descriptor descriptor = message.getDescriptorForType();
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName("values");
            if (field2 == null) {
                throw new InvalidProtocolBufferException("Invalid ListValue type.");
            }
            this.printRepeatedFieldValue(field2, message.getField(field2));
        }

        private void print(MessageOrBuilder message, String typeUrl) throws IOException {
            this.generator.print("{" + this.blankOrNewLine);
            this.generator.indent();
            boolean printedField = false;
            if (typeUrl != null) {
                this.generator.print("\"@type\":" + this.blankOrSpace + this.gson.toJson(typeUrl));
                printedField = true;
            }
            Map<Descriptors.FieldDescriptor, Object> fieldsToPrint = null;
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                fieldsToPrint = new TreeMap<Descriptors.FieldDescriptor, Object>(message.getAllFields());
                for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                    Descriptors.OneofDescriptor oneof;
                    if (fieldDescriptor.isOptional() && (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && !message.hasField(fieldDescriptor) || (oneof = fieldDescriptor.getContainingOneof()) != null && !message.hasField(fieldDescriptor)) || fieldsToPrint.containsKey(fieldDescriptor) || !this.alwaysOutputDefaultValueFields && !this.includingDefaultValueFields.contains(fieldDescriptor)) continue;
                    fieldsToPrint.put(fieldDescriptor, message.getField(fieldDescriptor));
                }
            } else {
                fieldsToPrint = message.getAllFields();
            }
            for (Map.Entry entry : fieldsToPrint.entrySet()) {
                if (printedField) {
                    this.generator.print("," + this.blankOrNewLine);
                } else {
                    printedField = true;
                }
                this.printField((Descriptors.FieldDescriptor)entry.getKey(), entry.getValue());
            }
            if (printedField) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print("}");
        }

        private void printField(Descriptors.FieldDescriptor field2, Object value) throws IOException {
            if (this.preservingProtoFieldNames) {
                this.generator.print("\"" + field2.getName() + "\":" + this.blankOrSpace);
            } else {
                this.generator.print("\"" + field2.getJsonName() + "\":" + this.blankOrSpace);
            }
            if (field2.isMapField()) {
                this.printMapFieldValue(field2, value);
            } else if (field2.isRepeated()) {
                this.printRepeatedFieldValue(field2, value);
            } else {
                this.printSingleFieldValue(field2, value);
            }
        }

        private void printRepeatedFieldValue(Descriptors.FieldDescriptor field2, Object value) throws IOException {
            this.generator.print("[");
            boolean printedElement = false;
            for (Object element : (List)value) {
                if (printedElement) {
                    this.generator.print("," + this.blankOrSpace);
                } else {
                    printedElement = true;
                }
                this.printSingleFieldValue(field2, element);
            }
            this.generator.print("]");
        }

        private void printMapFieldValue(Descriptors.FieldDescriptor field2, Object value) throws IOException {
            Descriptors.Descriptor type = field2.getMessageType();
            Descriptors.FieldDescriptor keyField = type.findFieldByName("key");
            Descriptors.FieldDescriptor valueField = type.findFieldByName("value");
            if (keyField == null || valueField == null) {
                throw new InvalidProtocolBufferException("Invalid map field.");
            }
            this.generator.print("{" + this.blankOrNewLine);
            this.generator.indent();
            boolean printedElement = false;
            for (Object element : (List)value) {
                Message entry = (Message)element;
                Object entryKey = entry.getField(keyField);
                Object entryValue = entry.getField(valueField);
                if (printedElement) {
                    this.generator.print("," + this.blankOrNewLine);
                } else {
                    printedElement = true;
                }
                this.printSingleFieldValue(keyField, entryKey, true);
                this.generator.print(":" + this.blankOrSpace);
                this.printSingleFieldValue(valueField, entryValue);
            }
            if (printedElement) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print("}");
        }

        private void printSingleFieldValue(Descriptors.FieldDescriptor field2, Object value) throws IOException {
            this.printSingleFieldValue(field2, value, false);
        }

        private void printSingleFieldValue(Descriptors.FieldDescriptor field2, Object value, boolean alwaysWithQuotes) throws IOException {
            switch (field2.getType()) {
                case INT32: 
                case SINT32: 
                case SFIXED32: {
                    if (alwaysWithQuotes) {
                        this.generator.print("\"");
                    }
                    this.generator.print(((Integer)value).toString());
                    if (!alwaysWithQuotes) break;
                    this.generator.print("\"");
                    break;
                }
                case INT64: 
                case SINT64: 
                case SFIXED64: {
                    this.generator.print("\"" + ((Long)value).toString() + "\"");
                    break;
                }
                case BOOL: {
                    if (alwaysWithQuotes) {
                        this.generator.print("\"");
                    }
                    if (((Boolean)value).booleanValue()) {
                        this.generator.print("true");
                    } else {
                        this.generator.print("false");
                    }
                    if (!alwaysWithQuotes) break;
                    this.generator.print("\"");
                    break;
                }
                case FLOAT: {
                    Float floatValue = (Float)value;
                    if (floatValue.isNaN()) {
                        this.generator.print("\"NaN\"");
                        break;
                    }
                    if (floatValue.isInfinite()) {
                        if (floatValue.floatValue() < 0.0f) {
                            this.generator.print("\"-Infinity\"");
                            break;
                        }
                        this.generator.print("\"Infinity\"");
                        break;
                    }
                    if (alwaysWithQuotes) {
                        this.generator.print("\"");
                    }
                    this.generator.print(floatValue.toString());
                    if (!alwaysWithQuotes) break;
                    this.generator.print("\"");
                    break;
                }
                case DOUBLE: {
                    Double doubleValue = (Double)value;
                    if (doubleValue.isNaN()) {
                        this.generator.print("\"NaN\"");
                        break;
                    }
                    if (doubleValue.isInfinite()) {
                        if (doubleValue < 0.0) {
                            this.generator.print("\"-Infinity\"");
                            break;
                        }
                        this.generator.print("\"Infinity\"");
                        break;
                    }
                    if (alwaysWithQuotes) {
                        this.generator.print("\"");
                    }
                    this.generator.print(doubleValue.toString());
                    if (!alwaysWithQuotes) break;
                    this.generator.print("\"");
                    break;
                }
                case UINT32: 
                case FIXED32: {
                    if (alwaysWithQuotes) {
                        this.generator.print("\"");
                    }
                    this.generator.print(JsonFormat.unsignedToString((Integer)value));
                    if (!alwaysWithQuotes) break;
                    this.generator.print("\"");
                    break;
                }
                case UINT64: 
                case FIXED64: {
                    this.generator.print("\"" + JsonFormat.unsignedToString((Long)value) + "\"");
                    break;
                }
                case STRING: {
                    this.generator.print(this.gson.toJson(value));
                    break;
                }
                case BYTES: {
                    this.generator.print("\"");
                    this.generator.print(BaseEncoding.base64().encode(((ByteString)value).toByteArray()));
                    this.generator.print("\"");
                    break;
                }
                case ENUM: {
                    if (field2.getEnumType().getFullName().equals("google.protobuf.NullValue")) {
                        if (alwaysWithQuotes) {
                            this.generator.print("\"");
                        }
                        this.generator.print("null");
                        if (!alwaysWithQuotes) break;
                        this.generator.print("\"");
                        break;
                    }
                    if (this.printingEnumsAsInts || ((Descriptors.EnumValueDescriptor)value).getIndex() == -1) {
                        this.generator.print(String.valueOf(((Descriptors.EnumValueDescriptor)value).getNumber()));
                        break;
                    }
                    this.generator.print("\"" + ((Descriptors.EnumValueDescriptor)value).getName() + "\"");
                    break;
                }
                case MESSAGE: 
                case GROUP: {
                    this.print((Message)value);
                }
            }
        }

        private static interface WellKnownTypePrinter {
            public void print(PrinterImpl var1, MessageOrBuilder var2) throws IOException;
        }

        private static class GsonHolder {
            private static final Gson DEFAULT_GSON = new GsonBuilder().disableHtmlEscaping().create();

            private GsonHolder() {
            }
        }
    }

    private static final class PrettyTextGenerator
    implements TextGenerator {
        private final Appendable output;
        private final StringBuilder indent = new StringBuilder();
        private boolean atStartOfLine = true;

        private PrettyTextGenerator(Appendable output) {
            this.output = output;
        }

        @Override
        public void indent() {
            this.indent.append("  ");
        }

        @Override
        public void outdent() {
            int length = this.indent.length();
            if (length < 2) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        @Override
        public void print(CharSequence text) throws IOException {
            int size2 = text.length();
            int pos = 0;
            for (int i = 0; i < size2; ++i) {
                if (text.charAt(i) != '\n') continue;
                this.write(text.subSequence(pos, i + 1));
                pos = i + 1;
                this.atStartOfLine = true;
            }
            this.write(text.subSequence(pos, size2));
        }

        private void write(CharSequence data) throws IOException {
            if (data.length() == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(data);
        }
    }

    private static final class CompactTextGenerator
    implements TextGenerator {
        private final Appendable output;

        private CompactTextGenerator(Appendable output) {
            this.output = output;
        }

        @Override
        public void indent() {
        }

        @Override
        public void outdent() {
        }

        @Override
        public void print(CharSequence text) throws IOException {
            this.output.append(text);
        }
    }

    static interface TextGenerator {
        public void indent();

        public void outdent();

        public void print(CharSequence var1) throws IOException;
    }

    public static class TypeRegistry {
        private final Map<String, Descriptors.Descriptor> types;

        public static TypeRegistry getEmptyTypeRegistry() {
            return EmptyTypeRegistryHolder.EMPTY;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Descriptors.Descriptor find(String name) {
            return this.types.get(name);
        }

        private TypeRegistry(Map<String, Descriptors.Descriptor> types) {
            this.types = types;
        }

        public static class Builder {
            private final Set<String> files = new HashSet<String>();
            private Map<String, Descriptors.Descriptor> types = new HashMap<String, Descriptors.Descriptor>();

            private Builder() {
            }

            public Builder add(Descriptors.Descriptor messageType) {
                if (this.types == null) {
                    throw new IllegalStateException("A TypeRegistry.Builer can only be used once.");
                }
                this.addFile(messageType.getFile());
                return this;
            }

            public Builder add(Iterable<Descriptors.Descriptor> messageTypes) {
                if (this.types == null) {
                    throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
                }
                for (Descriptors.Descriptor type : messageTypes) {
                    this.addFile(type.getFile());
                }
                return this;
            }

            public TypeRegistry build() {
                TypeRegistry result2 = new TypeRegistry(this.types);
                this.types = null;
                return result2;
            }

            private void addFile(Descriptors.FileDescriptor file) {
                if (!this.files.add(file.getFullName())) {
                    return;
                }
                for (Descriptors.FileDescriptor dependency : file.getDependencies()) {
                    this.addFile(dependency);
                }
                for (Descriptors.Descriptor message : file.getMessageTypes()) {
                    this.addMessage(message);
                }
            }

            private void addMessage(Descriptors.Descriptor message) {
                for (Descriptors.Descriptor nestedType : message.getNestedTypes()) {
                    this.addMessage(nestedType);
                }
                if (this.types.containsKey(message.getFullName())) {
                    logger.warning("Type " + message.getFullName() + " is added multiple times.");
                    return;
                }
                this.types.put(message.getFullName(), message);
            }
        }

        private static class EmptyTypeRegistryHolder {
            private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());

            private EmptyTypeRegistryHolder() {
            }
        }
    }

    public static class Parser {
        private final TypeRegistry registry;
        private final boolean ignoringUnknownFields;
        private final int recursionLimit;
        private static final int DEFAULT_RECURSION_LIMIT = 100;

        private Parser(TypeRegistry registry, boolean ignoreUnknownFields, int recursionLimit) {
            this.registry = registry;
            this.ignoringUnknownFields = ignoreUnknownFields;
            this.recursionLimit = recursionLimit;
        }

        public Parser usingTypeRegistry(TypeRegistry registry) {
            if (this.registry != TypeRegistry.getEmptyTypeRegistry()) {
                throw new IllegalArgumentException("Only one registry is allowed.");
            }
            return new Parser(registry, this.ignoringUnknownFields, this.recursionLimit);
        }

        public Parser ignoringUnknownFields() {
            return new Parser(this.registry, true, this.recursionLimit);
        }

        public void merge(String json, Message.Builder builder) throws InvalidProtocolBufferException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(json, builder);
        }

        public void merge(Reader json, Message.Builder builder) throws IOException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(json, builder);
        }

        Parser usingRecursionLimit(int recursionLimit) {
            return new Parser(this.registry, this.ignoringUnknownFields, recursionLimit);
        }
    }

    public static class Printer {
        private final TypeRegistry registry;
        private boolean alwaysOutputDefaultValueFields;
        private Set<Descriptors.FieldDescriptor> includingDefaultValueFields;
        private final boolean preservingProtoFieldNames;
        private final boolean omittingInsignificantWhitespace;
        private final boolean printingEnumsAsInts;

        private Printer(TypeRegistry registry, boolean alwaysOutputDefaultValueFields, Set<Descriptors.FieldDescriptor> includingDefaultValueFields, boolean preservingProtoFieldNames, boolean omittingInsignificantWhitespace, boolean printingEnumsAsInts) {
            this.registry = registry;
            this.alwaysOutputDefaultValueFields = alwaysOutputDefaultValueFields;
            this.includingDefaultValueFields = includingDefaultValueFields;
            this.preservingProtoFieldNames = preservingProtoFieldNames;
            this.omittingInsignificantWhitespace = omittingInsignificantWhitespace;
            this.printingEnumsAsInts = printingEnumsAsInts;
        }

        public Printer usingTypeRegistry(TypeRegistry registry) {
            if (this.registry != TypeRegistry.getEmptyTypeRegistry()) {
                throw new IllegalArgumentException("Only one registry is allowed.");
            }
            return new Printer(registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace, this.printingEnumsAsInts);
        }

        public Printer includingDefaultValueFields() {
            this.checkUnsetIncludingDefaultValueFields();
            return new Printer(this.registry, true, Collections.emptySet(), this.preservingProtoFieldNames, this.omittingInsignificantWhitespace, this.printingEnumsAsInts);
        }

        public Printer printingEnumsAsInts() {
            this.checkUnsetPrintingEnumsAsInts();
            return new Printer(this.registry, this.alwaysOutputDefaultValueFields, Collections.emptySet(), this.preservingProtoFieldNames, this.omittingInsignificantWhitespace, true);
        }

        private void checkUnsetPrintingEnumsAsInts() {
            if (this.printingEnumsAsInts) {
                throw new IllegalStateException("JsonFormat printingEnumsAsInts has already been set.");
            }
        }

        public Printer includingDefaultValueFields(Set<Descriptors.FieldDescriptor> fieldsToAlwaysOutput) {
            Preconditions.checkArgument(null != fieldsToAlwaysOutput && !fieldsToAlwaysOutput.isEmpty(), "Non-empty Set must be supplied for includingDefaultValueFields.");
            this.checkUnsetIncludingDefaultValueFields();
            return new Printer(this.registry, false, fieldsToAlwaysOutput, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace, this.printingEnumsAsInts);
        }

        private void checkUnsetIncludingDefaultValueFields() {
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                throw new IllegalStateException("JsonFormat includingDefaultValueFields has already been set.");
            }
        }

        public Printer preservingProtoFieldNames() {
            return new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, true, this.omittingInsignificantWhitespace, this.printingEnumsAsInts);
        }

        public Printer omittingInsignificantWhitespace() {
            return new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, true, this.printingEnumsAsInts);
        }

        public void appendTo(MessageOrBuilder message, Appendable output) throws IOException {
            new PrinterImpl(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, output, this.omittingInsignificantWhitespace, this.printingEnumsAsInts).print(message);
        }

        public String print(MessageOrBuilder message) throws InvalidProtocolBufferException {
            try {
                StringBuilder builder = new StringBuilder();
                this.appendTo(message, builder);
                return builder.toString();
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

