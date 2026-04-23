/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class JsonParser
implements Closeable,
Versioned {
    private static final int MIN_BYTE_I = -128;
    private static final int MAX_BYTE_I = 255;
    private static final int MIN_SHORT_I = Short.MIN_VALUE;
    private static final int MAX_SHORT_I = Short.MAX_VALUE;
    protected int _features;

    protected JsonParser() {
    }

    protected JsonParser(int features) {
        this._features = features;
    }

    public abstract ObjectCodec getCodec();

    public abstract void setCodec(ObjectCodec var1);

    public Object getInputSource() {
        return null;
    }

    public void setSchema(FormatSchema schema) {
        throw new UnsupportedOperationException("Parser of type " + this.getClass().getName() + " does not support schema of type '" + schema.getSchemaType() + "'");
    }

    public FormatSchema getSchema() {
        return null;
    }

    public boolean canUseSchema(FormatSchema schema) {
        return false;
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    @Override
    public abstract Version version();

    @Override
    public abstract void close() throws IOException;

    public int releaseBuffered(OutputStream out) throws IOException {
        return -1;
    }

    public int releaseBuffered(Writer w) throws IOException {
        return -1;
    }

    public JsonParser enable(Feature f) {
        this._features |= f.getMask();
        return this;
    }

    public JsonParser disable(Feature f) {
        this._features &= ~f.getMask();
        return this;
    }

    public JsonParser configure(Feature f, boolean state) {
        if (state) {
            this.enable(f);
        } else {
            this.disable(f);
        }
        return this;
    }

    public boolean isEnabled(Feature f) {
        return (this._features & f.getMask()) != 0;
    }

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract JsonToken nextValue() throws IOException, JsonParseException;

    public boolean nextFieldName(SerializableString str) throws IOException, JsonParseException {
        return this.nextToken() == JsonToken.FIELD_NAME && str.getValue().equals(this.getCurrentName());
    }

    public String nextTextValue() throws IOException, JsonParseException {
        return this.nextToken() == JsonToken.VALUE_STRING ? this.getText() : null;
    }

    public int nextIntValue(int defaultValue) throws IOException, JsonParseException {
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getIntValue() : defaultValue;
    }

    public long nextLongValue(long defaultValue) throws IOException, JsonParseException {
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getLongValue() : defaultValue;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        switch (this.nextToken()) {
            case VALUE_TRUE: {
                return Boolean.TRUE;
            }
            case VALUE_FALSE: {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    public abstract boolean isClosed();

    public abstract JsonToken getCurrentToken();

    public abstract boolean hasCurrentToken();

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public abstract JsonLocation getTokenLocation();

    public abstract JsonLocation getCurrentLocation();

    public boolean isExpectedStartArrayToken() {
        return this.getCurrentToken() == JsonToken.START_ARRAY;
    }

    public abstract void clearCurrentToken();

    public abstract JsonToken getLastClearedToken();

    public abstract void overrideCurrentName(String var1);

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract boolean hasTextCharacters();

    public abstract Number getNumberValue() throws IOException, JsonParseException;

    public abstract NumberType getNumberType() throws IOException, JsonParseException;

    public byte getByteValue() throws IOException, JsonParseException {
        int value = this.getIntValue();
        if (value < -128 || value > 255) {
            throw this._constructError("Numeric value (" + this.getText() + ") out of range of Java byte");
        }
        return (byte)value;
    }

    public short getShortValue() throws IOException, JsonParseException {
        int value = this.getIntValue();
        if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
            throw this._constructError("Numeric value (" + this.getText() + ") out of range of Java short");
        }
        return (short)value;
    }

    public abstract int getIntValue() throws IOException, JsonParseException;

    public abstract long getLongValue() throws IOException, JsonParseException;

    public abstract BigInteger getBigIntegerValue() throws IOException, JsonParseException;

    public abstract float getFloatValue() throws IOException, JsonParseException;

    public abstract double getDoubleValue() throws IOException, JsonParseException;

    public abstract BigDecimal getDecimalValue() throws IOException, JsonParseException;

    public boolean getBooleanValue() throws IOException, JsonParseException {
        JsonToken t = this.getCurrentToken();
        if (t == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (t == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException("Current token (" + (Object)((Object)t) + ") not of boolean type", this.getCurrentLocation());
    }

    public abstract Object getEmbeddedObject() throws IOException, JsonParseException;

    public abstract byte[] getBinaryValue(Base64Variant var1) throws IOException, JsonParseException;

    public byte[] getBinaryValue() throws IOException, JsonParseException {
        return this.getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public int readBinaryValue(OutputStream out) throws IOException, JsonParseException {
        return this.readBinaryValue(Base64Variants.getDefaultVariant(), out);
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException, JsonParseException {
        this._reportUnsupportedOperation();
        return 0;
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return this.getValueAsInt(0);
    }

    public int getValueAsInt(int defaultValue) throws IOException, JsonParseException {
        return defaultValue;
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return this.getValueAsLong(0L);
    }

    public long getValueAsLong(long defaultValue) throws IOException, JsonParseException {
        return defaultValue;
    }

    public double getValueAsDouble() throws IOException, JsonParseException {
        return this.getValueAsDouble(0.0);
    }

    public double getValueAsDouble(double defaultValue) throws IOException, JsonParseException {
        return defaultValue;
    }

    public boolean getValueAsBoolean() throws IOException, JsonParseException {
        return this.getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean defaultValue) throws IOException, JsonParseException {
        return defaultValue;
    }

    public String getValueAsString() throws IOException, JsonParseException {
        return this.getValueAsString(null);
    }

    public abstract String getValueAsString(String var1) throws IOException, JsonParseException;

    public <T> T readValueAs(Class<T> valueType) throws IOException, JsonProcessingException {
        ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValue(this, valueType);
    }

    public <T> T readValueAs(TypeReference<?> valueTypeRef) throws IOException, JsonProcessingException {
        ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValue(this, valueTypeRef);
    }

    public <T> Iterator<T> readValuesAs(Class<T> valueType) throws IOException, JsonProcessingException {
        ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, valueType);
    }

    public <T> Iterator<T> readValuesAs(TypeReference<?> valueTypeRef) throws IOException, JsonProcessingException {
        ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, valueTypeRef);
    }

    public <T extends TreeNode> T readValueAsTree() throws IOException, JsonProcessingException {
        ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        }
        return codec.readTree(this);
    }

    protected JsonParseException _constructError(String msg) {
        return new JsonParseException(msg, this.getCurrentLocation());
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + this.getClass().getName());
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false);

        private final boolean _defaultState;

        public static int collectDefaults() {
            int flags = 0;
            for (Feature f : Feature.values()) {
                if (!f.enabledByDefault()) continue;
                flags |= f.getMask();
            }
            return flags;
        }

        private Feature(boolean defaultState) {
            this._defaultState = defaultState;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return 1 << this.ordinal();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL;

    }
}

