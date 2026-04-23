/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonGenerator
implements Closeable,
Flushable,
Versioned {
    protected PrettyPrinter _cfgPrettyPrinter;

    protected JsonGenerator() {
    }

    public void setSchema(FormatSchema schema) {
        throw new UnsupportedOperationException("Generator of type " + this.getClass().getName() + " does not support schema of type '" + schema.getSchemaType() + "'");
    }

    public FormatSchema getSchema() {
        return null;
    }

    public boolean canUseSchema(FormatSchema schema) {
        return false;
    }

    public abstract Version version();

    public Object getOutputTarget() {
        return null;
    }

    public JsonGenerator setRootValueSeparator(SerializableString sep) {
        throw new UnsupportedOperationException();
    }

    public abstract JsonGenerator enable(Feature var1);

    public abstract JsonGenerator disable(Feature var1);

    public final JsonGenerator configure(Feature f, boolean state) {
        if (state) {
            this.enable(f);
        } else {
            this.disable(f);
        }
        return this;
    }

    public abstract boolean isEnabled(Feature var1);

    public abstract JsonGenerator setCodec(ObjectCodec var1);

    public abstract ObjectCodec getCodec();

    public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
        this._cfgPrettyPrinter = pp;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public JsonGenerator setHighestNonEscapedChar(int charCode) {
        return this;
    }

    public int getHighestEscapedChar() {
        return 0;
    }

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
        return this;
    }

    public abstract void writeStartArray() throws IOException, JsonGenerationException;

    public abstract void writeEndArray() throws IOException, JsonGenerationException;

    public abstract void writeStartObject() throws IOException, JsonGenerationException;

    public abstract void writeEndObject() throws IOException, JsonGenerationException;

    public abstract void writeFieldName(String var1) throws IOException, JsonGenerationException;

    public abstract void writeFieldName(SerializableString var1) throws IOException, JsonGenerationException;

    public abstract void writeString(String var1) throws IOException, JsonGenerationException;

    public abstract void writeString(char[] var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeString(SerializableString var1) throws IOException, JsonGenerationException;

    public abstract void writeRawUTF8String(byte[] var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeUTF8String(byte[] var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeRaw(String var1) throws IOException, JsonGenerationException;

    public abstract void writeRaw(String var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeRaw(char[] var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeRaw(char var1) throws IOException, JsonGenerationException;

    public void writeRaw(SerializableString raw2) throws IOException, JsonGenerationException {
        this.writeRaw(raw2.getValue());
    }

    public abstract void writeRawValue(String var1) throws IOException, JsonGenerationException;

    public abstract void writeRawValue(String var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeRawValue(char[] var1, int var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeBinary(Base64Variant var1, byte[] var2, int var3, int var4) throws IOException, JsonGenerationException;

    public void writeBinary(byte[] data, int offset, int len) throws IOException, JsonGenerationException {
        this.writeBinary(Base64Variants.getDefaultVariant(), data, offset, len);
    }

    public void writeBinary(byte[] data) throws IOException, JsonGenerationException {
        this.writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
    }

    public int writeBinary(InputStream data, int dataLength) throws IOException, JsonGenerationException {
        return this.writeBinary(Base64Variants.getDefaultVariant(), data, dataLength);
    }

    public abstract int writeBinary(Base64Variant var1, InputStream var2, int var3) throws IOException, JsonGenerationException;

    public abstract void writeNumber(int var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(long var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(BigInteger var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(double var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(float var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(BigDecimal var1) throws IOException, JsonGenerationException;

    public abstract void writeNumber(String var1) throws IOException, JsonGenerationException, UnsupportedOperationException;

    public abstract void writeBoolean(boolean var1) throws IOException, JsonGenerationException;

    public abstract void writeNull() throws IOException, JsonGenerationException;

    public abstract void writeObject(Object var1) throws IOException, JsonProcessingException;

    public abstract void writeTree(TreeNode var1) throws IOException, JsonProcessingException;

    public void writeStringField(String fieldName, String value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeString(value);
    }

    public final void writeBooleanField(String fieldName, boolean value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeBoolean(value);
    }

    public final void writeNullField(String fieldName) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNull();
    }

    public final void writeNumberField(String fieldName, int value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, long value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, double value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, float value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, BigDecimal value) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeBinaryField(String fieldName, byte[] data) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeBinary(data);
    }

    public final void writeArrayFieldStart(String fieldName) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeStartArray();
    }

    public final void writeObjectFieldStart(String fieldName) throws IOException, JsonGenerationException {
        this.writeFieldName(fieldName);
        this.writeStartObject();
    }

    public final void writeObjectField(String fieldName, Object pojo) throws IOException, JsonProcessingException {
        this.writeFieldName(fieldName);
        this.writeObject(pojo);
    }

    public abstract void copyCurrentEvent(JsonParser var1) throws IOException, JsonProcessingException;

    public abstract void copyCurrentStructure(JsonParser var1) throws IOException, JsonProcessingException;

    public abstract JsonStreamContext getOutputContext();

    public abstract void flush() throws IOException;

    public abstract boolean isClosed();

    public abstract void close() throws IOException;

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false);

        private final boolean _defaultState;
        private final int _mask = 1 << this.ordinal();

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
            return this._mask;
        }
    }
}

