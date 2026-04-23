/*
 * Decompiled with CFR 0.152.
 */
package com.github.cliftonlabs.json_simple;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.Yylex;
import com.github.cliftonlabs.json_simple.Yytoken;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Jsoner {
    private Jsoner() {
    }

    public static Object deserialize(Reader readableDeserializable) throws JsonException {
        return Jsoner.deserialize(readableDeserializable, EnumSet.of(DeserializationOptions.ALLOW_JSON_ARRAYS, DeserializationOptions.ALLOW_JSON_OBJECTS, DeserializationOptions.ALLOW_JSON_DATA)).get(0);
    }

    private static JsonArray deserialize(Reader deserializable, Set<DeserializationOptions> flags) throws JsonException {
        Yytoken token;
        States currentState;
        Yylex lexer = new Yylex(deserializable);
        int returnCount = 1;
        LinkedList<States> stateStack = new LinkedList<States>();
        LinkedList<Object> valueStack = new LinkedList<Object>();
        stateStack.addLast(States.INITIAL);
        block31: do {
            currentState = Jsoner.popNextState(stateStack);
            token = Jsoner.lexNextToken(lexer);
            block0 : switch (currentState) {
                case DONE: {
                    if (!flags.contains((Object)DeserializationOptions.ALLOW_CONCATENATED_JSON_VALUES) || Yytoken.Types.END.equals((Object)token.getType())) continue block31;
                    ++returnCount;
                }
                case INITIAL: {
                    switch (token.getType()) {
                        case DATUM: {
                            if (flags.contains((Object)DeserializationOptions.ALLOW_JSON_DATA)) {
                                valueStack.addLast(token.getValue());
                                stateStack.addLast(States.DONE);
                                break block0;
                            }
                            throw new JsonException(lexer.getPosition(), JsonException.Problems.DISALLOWED_TOKEN, token);
                        }
                        case LEFT_BRACE: {
                            if (flags.contains((Object)DeserializationOptions.ALLOW_JSON_OBJECTS)) {
                                valueStack.addLast(new JsonObject());
                                stateStack.addLast(States.PARSING_OBJECT);
                                break block0;
                            }
                            throw new JsonException(lexer.getPosition(), JsonException.Problems.DISALLOWED_TOKEN, token);
                        }
                        case LEFT_SQUARE: {
                            if (flags.contains((Object)DeserializationOptions.ALLOW_JSON_ARRAYS)) {
                                valueStack.addLast(new JsonArray());
                                stateStack.addLast(States.PARSING_ARRAY);
                                break block0;
                            }
                            throw new JsonException(lexer.getPosition(), JsonException.Problems.DISALLOWED_TOKEN, token);
                        }
                    }
                    throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                }
                case PARSED_ERROR: {
                    throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                }
                case PARSING_ARRAY: {
                    switch (token.getType()) {
                        case COMMA: {
                            stateStack.addLast(currentState);
                            break block0;
                        }
                        case DATUM: {
                            JsonArray val = (JsonArray)valueStack.getLast();
                            val.add(token.getValue());
                            stateStack.addLast(currentState);
                            break block0;
                        }
                        case LEFT_BRACE: {
                            JsonArray val = (JsonArray)valueStack.getLast();
                            JsonObject object = new JsonObject();
                            val.add(object);
                            valueStack.addLast(object);
                            stateStack.addLast(currentState);
                            stateStack.addLast(States.PARSING_OBJECT);
                            break block0;
                        }
                        case LEFT_SQUARE: {
                            JsonArray val = (JsonArray)valueStack.getLast();
                            JsonArray array = new JsonArray();
                            val.add(array);
                            valueStack.addLast(array);
                            stateStack.addLast(currentState);
                            stateStack.addLast(States.PARSING_ARRAY);
                            break block0;
                        }
                        case RIGHT_SQUARE: {
                            if (valueStack.size() > returnCount) {
                                valueStack.removeLast();
                                break block0;
                            }
                            stateStack.addLast(States.DONE);
                            break block0;
                        }
                    }
                    throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                }
                case PARSING_OBJECT: {
                    String key;
                    switch (token.getType()) {
                        case COMMA: {
                            stateStack.addLast(currentState);
                            break block0;
                        }
                        case DATUM: {
                            if (token.getValue() instanceof String) {
                                key = (String)token.getValue();
                                valueStack.addLast(key);
                                stateStack.addLast(currentState);
                                stateStack.addLast(States.PARSING_ENTRY);
                                break block0;
                            }
                            throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                        }
                        case RIGHT_BRACE: {
                            if (valueStack.size() > returnCount) {
                                valueStack.removeLast();
                                break block0;
                            }
                            stateStack.addLast(States.DONE);
                            break block0;
                        }
                    }
                    throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                }
                case PARSING_ENTRY: {
                    String key;
                    switch (token.getType()) {
                        case COLON: {
                            stateStack.addLast(currentState);
                            break block0;
                        }
                        case DATUM: {
                            key = (String)valueStack.removeLast();
                            JsonObject parent = (JsonObject)valueStack.getLast();
                            parent.put(key, token.getValue());
                            break block0;
                        }
                        case LEFT_BRACE: {
                            key = (String)valueStack.removeLast();
                            JsonObject parent = (JsonObject)valueStack.getLast();
                            JsonObject object = new JsonObject();
                            parent.put(key, object);
                            valueStack.addLast(object);
                            stateStack.addLast(States.PARSING_OBJECT);
                            break block0;
                        }
                        case LEFT_SQUARE: {
                            key = (String)valueStack.removeLast();
                            JsonObject parent = (JsonObject)valueStack.getLast();
                            JsonArray array = new JsonArray();
                            parent.put(key, array);
                            valueStack.addLast(array);
                            stateStack.addLast(States.PARSING_ARRAY);
                            break block0;
                        }
                    }
                    throw new JsonException(lexer.getPosition(), JsonException.Problems.UNEXPECTED_TOKEN, token);
                }
            }
        } while (!States.DONE.equals((Object)currentState) || !Yytoken.Types.END.equals((Object)token.getType()));
        return new JsonArray((Collection<?>)valueStack);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Object deserialize(String deserializable) throws JsonException {
        Object returnable;
        try (StringReader readableDeserializable = null;){
            readableDeserializable = new StringReader(deserializable);
            returnable = Jsoner.deserialize(readableDeserializable);
        }
        return returnable;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static JsonArray deserialize(String deserializable, JsonArray defaultValue) {
        JsonArray returnable;
        try (StringReader readable = null;){
            readable = new StringReader(deserializable);
            returnable = (JsonArray)Jsoner.deserialize(readable, EnumSet.of(DeserializationOptions.ALLOW_JSON_ARRAYS)).getCollection(0);
        }
        return returnable;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static JsonObject deserialize(String deserializable, JsonObject defaultValue) {
        JsonObject returnable;
        try (StringReader readable = null;){
            readable = new StringReader(deserializable);
            returnable = (JsonObject)Jsoner.deserialize(readable, EnumSet.of(DeserializationOptions.ALLOW_JSON_OBJECTS)).getMap(0);
        }
        return returnable;
    }

    public static JsonArray deserializeMany(Reader deserializable) throws JsonException {
        return Jsoner.deserialize(deserializable, EnumSet.of(DeserializationOptions.ALLOW_JSON_ARRAYS, DeserializationOptions.ALLOW_JSON_OBJECTS, DeserializationOptions.ALLOW_JSON_DATA, DeserializationOptions.ALLOW_CONCATENATED_JSON_VALUES));
    }

    public static String escape(String escapable) {
        StringBuilder builder = new StringBuilder();
        int characters = escapable.length();
        block10: for (int i = 0; i < characters; ++i) {
            char character = escapable.charAt(i);
            switch (character) {
                case '\"': {
                    builder.append("\\\"");
                    continue block10;
                }
                case '\\': {
                    builder.append("\\\\");
                    continue block10;
                }
                case '\b': {
                    builder.append("\\b");
                    continue block10;
                }
                case '\f': {
                    builder.append("\\f");
                    continue block10;
                }
                case '\n': {
                    builder.append("\\n");
                    continue block10;
                }
                case '\r': {
                    builder.append("\\r");
                    continue block10;
                }
                case '\t': {
                    builder.append("\\t");
                    continue block10;
                }
                case '/': {
                    builder.append("\\/");
                    continue block10;
                }
                default: {
                    if (character >= '\u0000' && character <= '\u001f' || character >= '\u007f' && character <= '\u009f' || character >= '\u2000' && character <= '\u20ff') {
                        String characterHexCode = Integer.toHexString(character);
                        builder.append("\\u");
                        for (int k = 0; k < 4 - characterHexCode.length(); ++k) {
                            builder.append("0");
                        }
                        builder.append(characterHexCode.toUpperCase());
                        continue block10;
                    }
                    builder.append(character);
                }
            }
        }
        return builder.toString();
    }

    private static Yytoken lexNextToken(Yylex lexer) throws JsonException {
        Yytoken returnable;
        try {
            returnable = lexer.yylex();
        }
        catch (IOException caught) {
            throw new JsonException(-1, JsonException.Problems.UNEXPECTED_EXCEPTION, caught);
        }
        if (returnable == null) {
            returnable = new Yytoken(Yytoken.Types.END, null);
        }
        return returnable;
    }

    public static JsonKey mintJsonKey(final String key, final Object value) {
        return new JsonKey(){

            @Override
            public String getKey() {
                return key;
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

    private static States popNextState(LinkedList<States> stateStack) {
        if (stateStack.size() > 0) {
            return stateStack.removeLast();
        }
        return States.PARSED_ERROR;
    }

    public static String prettyPrint(String printable) {
        return Jsoner.prettyPrint(printable, "\t");
    }

    public static String prettyPrint(String printable, int spaces2) {
        if (spaces2 > 10 || spaces2 < 2) {
            throw new IllegalArgumentException("Indentation with spaces must be between 2 and 10.");
        }
        StringBuilder indentation = new StringBuilder("");
        for (int i = 0; i < spaces2; ++i) {
            indentation.append(" ");
        }
        return Jsoner.prettyPrint(printable, indentation.toString());
    }

    private static String prettyPrint(String printable, String indentation) {
        Yylex lexer = new Yylex(new StringReader(printable));
        StringBuilder returnable = new StringBuilder();
        int level = 0;
        try {
            Yytoken lexed;
            block9: do {
                lexed = Jsoner.lexNextToken(lexer);
                switch (lexed.getType()) {
                    case COLON: {
                        returnable.append(":");
                        break;
                    }
                    case COMMA: {
                        int i;
                        returnable.append(lexed.getValue());
                        returnable.append("\n");
                        for (i = 0; i < level; ++i) {
                            returnable.append(indentation);
                        }
                        continue block9;
                    }
                    case END: {
                        break;
                    }
                    case LEFT_BRACE: 
                    case LEFT_SQUARE: {
                        int i;
                        returnable.append(lexed.getValue());
                        returnable.append("\n");
                        ++level;
                        for (i = 0; i < level; ++i) {
                            returnable.append(indentation);
                        }
                        continue block9;
                    }
                    case RIGHT_SQUARE: 
                    case RIGHT_BRACE: {
                        int i;
                        returnable.append("\n");
                        --level;
                        for (i = 0; i < level; ++i) {
                            returnable.append(indentation);
                        }
                        returnable.append(lexed.getValue());
                        break;
                    }
                    default: {
                        if (lexed.getValue() instanceof String) {
                            returnable.append("\"");
                            returnable.append(Jsoner.escape((String)lexed.getValue()));
                            returnable.append("\"");
                            break;
                        }
                        returnable.append(lexed.getValue());
                    }
                }
            } while (!lexed.getType().equals((Object)Yytoken.Types.END));
        }
        catch (JsonException caught) {
            return null;
        }
        return returnable.toString();
    }

    public static String serialize(Object jsonSerializable) {
        StringWriter writableDestination = new StringWriter();
        try {
            Jsoner.serialize(jsonSerializable, writableDestination);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return writableDestination.toString();
    }

    public static void serialize(Object jsonSerializable, Writer writableDestination) throws IOException {
        Jsoner.serialize(jsonSerializable, writableDestination, EnumSet.of(SerializationOptions.ALLOW_JSONABLES));
    }

    private static void serialize(Object jsonSerializable, Writer writableDestination, Set<SerializationOptions> flags) throws IOException {
        if (jsonSerializable == null) {
            writableDestination.write("null");
        } else if (jsonSerializable instanceof Jsonable && flags.contains((Object)SerializationOptions.ALLOW_JSONABLES)) {
            writableDestination.write(((Jsonable)jsonSerializable).toJson());
        } else if (jsonSerializable instanceof String) {
            writableDestination.write(34);
            writableDestination.write(Jsoner.escape((String)jsonSerializable));
            writableDestination.write(34);
        } else if (jsonSerializable instanceof Character) {
            writableDestination.write(Jsoner.escape(jsonSerializable.toString()));
        } else if (jsonSerializable instanceof Double) {
            if (((Double)jsonSerializable).isInfinite() || ((Double)jsonSerializable).isNaN()) {
                writableDestination.write("null");
            } else {
                writableDestination.write(jsonSerializable.toString());
            }
        } else if (jsonSerializable instanceof Float) {
            if (((Float)jsonSerializable).isInfinite() || ((Float)jsonSerializable).isNaN()) {
                writableDestination.write("null");
            } else {
                writableDestination.write(jsonSerializable.toString());
            }
        } else if (jsonSerializable instanceof Number) {
            writableDestination.write(jsonSerializable.toString());
        } else if (jsonSerializable instanceof Boolean) {
            writableDestination.write(jsonSerializable.toString());
        } else if (jsonSerializable instanceof Map) {
            boolean isFirstEntry = true;
            Iterator entries = ((Map)jsonSerializable).entrySet().iterator();
            writableDestination.write(123);
            while (entries.hasNext()) {
                if (isFirstEntry) {
                    isFirstEntry = false;
                } else {
                    writableDestination.write(44);
                }
                Map.Entry entry = entries.next();
                Jsoner.serialize(entry.getKey(), writableDestination, flags);
                writableDestination.write(58);
                Jsoner.serialize(entry.getValue(), writableDestination, flags);
            }
            writableDestination.write(125);
        } else if (jsonSerializable instanceof Collection) {
            boolean isFirstElement = true;
            Iterator elements = ((Collection)jsonSerializable).iterator();
            writableDestination.write(91);
            while (elements.hasNext()) {
                if (isFirstElement) {
                    isFirstElement = false;
                } else {
                    writableDestination.write(44);
                }
                Jsoner.serialize(elements.next(), writableDestination, flags);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof byte[]) {
            byte[] writableArray = (byte[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof short[]) {
            short[] writableArray = (short[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof int[]) {
            int[] writableArray = (int[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof long[]) {
            long[] writableArray = (long[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof float[]) {
            float[] writableArray = (float[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(Float.valueOf(writableArray[i]), writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(Float.valueOf(writableArray[i]), writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof double[]) {
            double[] writableArray = (double[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof boolean[]) {
            boolean[] writableArray = (boolean[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(44);
            }
            writableDestination.write(93);
        } else if (jsonSerializable instanceof char[]) {
            char[] writableArray = (char[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write("[\"");
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(Character.valueOf(writableArray[i]), writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(Character.valueOf(writableArray[i]), writableDestination, flags);
                writableDestination.write("\",\"");
            }
            writableDestination.write("\"]");
        } else if (jsonSerializable instanceof Object[]) {
            Object[] writableArray = (Object[])jsonSerializable;
            int numberOfElements = writableArray.length;
            writableDestination.write(91);
            for (int i = 0; i < numberOfElements; ++i) {
                if (i == numberOfElements - 1) {
                    Jsoner.serialize(writableArray[i], writableDestination, flags);
                    continue;
                }
                Jsoner.serialize(writableArray[i], writableDestination, flags);
                writableDestination.write(",");
            }
            writableDestination.write(93);
        } else if (flags.contains((Object)SerializationOptions.ALLOW_INVALIDS)) {
            writableDestination.write(jsonSerializable.toString());
        } else {
            throw new IllegalArgumentException("Encountered a: " + jsonSerializable.getClass().getName() + " as: " + jsonSerializable.toString() + "  that isn't JSON serializable.\n  Try:\n    1) Implementing the Jsonable interface for the object to return valid JSON. If it already does it probably has a bug.\n    2) If you cannot edit the source of the object or couple it with this library consider wrapping it in a class that does implement the Jsonable interface.\n    3) Otherwise convert it to a boolean, null, number, JsonArray, JsonObject, or String value before serializing it.\n    4) If you feel it should have serialized you could use a more tolerant serialization for debugging purposes.");
        }
    }

    public static void serializeCarelessly(Object jsonSerializable, Writer writableDestination) throws IOException {
        Jsoner.serialize(jsonSerializable, writableDestination, EnumSet.of(SerializationOptions.ALLOW_JSONABLES, SerializationOptions.ALLOW_INVALIDS));
    }

    public static void serializeStrictly(Object jsonSerializable, Writer writableDestination) throws IOException {
        Jsoner.serialize(jsonSerializable, writableDestination, EnumSet.noneOf(SerializationOptions.class));
    }

    private static enum States {
        DONE,
        INITIAL,
        PARSED_ERROR,
        PARSING_ARRAY,
        PARSING_ENTRY,
        PARSING_OBJECT;

    }

    private static enum SerializationOptions {
        ALLOW_INVALIDS,
        ALLOW_JSONABLES;

    }

    private static enum DeserializationOptions {
        ALLOW_CONCATENATED_JSON_VALUES,
        ALLOW_JSON_ARRAYS,
        ALLOW_JSON_DATA,
        ALLOW_JSON_OBJECTS;

    }
}

