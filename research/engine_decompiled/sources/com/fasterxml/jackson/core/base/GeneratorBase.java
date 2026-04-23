/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class GeneratorBase
extends JsonGenerator {
    protected ObjectCodec _objectCodec;
    protected int _features;
    protected boolean _cfgNumbersAsStrings;
    protected JsonWriteContext _writeContext;
    protected boolean _closed;

    protected GeneratorBase(int features, ObjectCodec codec) {
        this._features = features;
        this._writeContext = JsonWriteContext.createRootContext();
        this._objectCodec = codec;
        this._cfgNumbersAsStrings = this.isEnabled(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
    }

    public Version version() {
        return VersionUtil.versionFor(this.getClass());
    }

    public JsonGenerator enable(JsonGenerator.Feature f) {
        this._features |= f.getMask();
        if (f == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = true;
        } else if (f == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            this.setHighestNonEscapedChar(127);
        }
        return this;
    }

    public JsonGenerator disable(JsonGenerator.Feature f) {
        this._features &= ~f.getMask();
        if (f == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = false;
        } else if (f == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            this.setHighestNonEscapedChar(0);
        }
        return this;
    }

    public final boolean isEnabled(JsonGenerator.Feature f) {
        return (this._features & f.getMask()) != 0;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        if (this.getPrettyPrinter() != null) {
            return this;
        }
        return this.setPrettyPrinter(new DefaultPrettyPrinter());
    }

    public JsonGenerator setCodec(ObjectCodec oc) {
        this._objectCodec = oc;
        return this;
    }

    public final ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    public void writeFieldName(SerializableString name) throws IOException, JsonGenerationException {
        this.writeFieldName(name.getValue());
    }

    public void writeString(SerializableString text) throws IOException, JsonGenerationException {
        this.writeString(text.getValue());
    }

    public void writeRawValue(String text) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write raw value");
        this.writeRaw(text);
    }

    public void writeRawValue(String text, int offset, int len) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write raw value");
        this.writeRaw(text, offset, len);
    }

    public void writeRawValue(char[] text, int offset, int len) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write raw value");
        this.writeRaw(text, offset, len);
    }

    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException, JsonGenerationException {
        this._reportUnsupportedOperation();
        return 0;
    }

    public void writeObject(Object value) throws IOException, JsonProcessingException {
        if (value == null) {
            this.writeNull();
        } else {
            if (this._objectCodec != null) {
                this._objectCodec.writeValue(this, value);
                return;
            }
            this._writeSimpleObject(value);
        }
    }

    public void writeTree(TreeNode rootNode) throws IOException, JsonProcessingException {
        if (rootNode == null) {
            this.writeNull();
        } else {
            if (this._objectCodec == null) {
                throw new IllegalStateException("No ObjectCodec defined for the generator, can not serialize JsonNode-based trees");
            }
            this._objectCodec.writeValue(this, rootNode);
        }
    }

    public abstract void flush() throws IOException;

    public void close() throws IOException {
        this._closed = true;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public final void copyCurrentEvent(JsonParser jp) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == null) {
            this._reportError("No current event to copy");
        }
        block0 : switch (t) {
            case START_OBJECT: {
                this.writeStartObject();
                break;
            }
            case END_OBJECT: {
                this.writeEndObject();
                break;
            }
            case START_ARRAY: {
                this.writeStartArray();
                break;
            }
            case END_ARRAY: {
                this.writeEndArray();
                break;
            }
            case FIELD_NAME: {
                this.writeFieldName(jp.getCurrentName());
                break;
            }
            case VALUE_STRING: {
                if (jp.hasTextCharacters()) {
                    this.writeString(jp.getTextCharacters(), jp.getTextOffset(), jp.getTextLength());
                    break;
                }
                this.writeString(jp.getText());
                break;
            }
            case VALUE_NUMBER_INT: {
                switch (jp.getNumberType()) {
                    case INT: {
                        this.writeNumber(jp.getIntValue());
                        break block0;
                    }
                    case BIG_INTEGER: {
                        this.writeNumber(jp.getBigIntegerValue());
                        break block0;
                    }
                }
                this.writeNumber(jp.getLongValue());
                break;
            }
            case VALUE_NUMBER_FLOAT: {
                switch (jp.getNumberType()) {
                    case BIG_DECIMAL: {
                        this.writeNumber(jp.getDecimalValue());
                        break block0;
                    }
                    case FLOAT: {
                        this.writeNumber(jp.getFloatValue());
                        break block0;
                    }
                }
                this.writeNumber(jp.getDoubleValue());
                break;
            }
            case VALUE_TRUE: {
                this.writeBoolean(true);
                break;
            }
            case VALUE_FALSE: {
                this.writeBoolean(false);
                break;
            }
            case VALUE_NULL: {
                this.writeNull();
                break;
            }
            case VALUE_EMBEDDED_OBJECT: {
                this.writeObject(jp.getEmbeddedObject());
                break;
            }
            default: {
                this._cantHappen();
            }
        }
    }

    public final void copyCurrentStructure(JsonParser jp) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.FIELD_NAME) {
            this.writeFieldName(jp.getCurrentName());
            t = jp.nextToken();
        }
        switch (t) {
            case START_ARRAY: {
                this.writeStartArray();
                while (jp.nextToken() != JsonToken.END_ARRAY) {
                    this.copyCurrentStructure(jp);
                }
                this.writeEndArray();
                break;
            }
            case START_OBJECT: {
                this.writeStartObject();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    this.copyCurrentStructure(jp);
                }
                this.writeEndObject();
                break;
            }
            default: {
                this.copyCurrentEvent(jp);
            }
        }
    }

    protected abstract void _releaseBuffers();

    protected abstract void _verifyValueWrite(String var1) throws IOException, JsonGenerationException;

    protected void _reportError(String msg) throws JsonGenerationException {
        throw new JsonGenerationException(msg);
    }

    protected void _cantHappen() {
        throw new RuntimeException("Internal error: should never end up through this code path");
    }

    protected void _writeSimpleObject(Object value) throws IOException, JsonGenerationException {
        if (value == null) {
            this.writeNull();
            return;
        }
        if (value instanceof String) {
            this.writeString((String)value);
            return;
        }
        if (value instanceof Number) {
            Number n = (Number)value;
            if (n instanceof Integer) {
                this.writeNumber(n.intValue());
                return;
            }
            if (n instanceof Long) {
                this.writeNumber(n.longValue());
                return;
            }
            if (n instanceof Double) {
                this.writeNumber(n.doubleValue());
                return;
            }
            if (n instanceof Float) {
                this.writeNumber(n.floatValue());
                return;
            }
            if (n instanceof Short) {
                this.writeNumber(n.shortValue());
                return;
            }
            if (n instanceof Byte) {
                this.writeNumber(n.byteValue());
                return;
            }
            if (n instanceof BigInteger) {
                this.writeNumber((BigInteger)n);
                return;
            }
            if (n instanceof BigDecimal) {
                this.writeNumber((BigDecimal)n);
                return;
            }
            if (n instanceof AtomicInteger) {
                this.writeNumber(((AtomicInteger)n).get());
                return;
            }
            if (n instanceof AtomicLong) {
                this.writeNumber(((AtomicLong)n).get());
                return;
            }
        } else {
            if (value instanceof byte[]) {
                this.writeBinary((byte[])value);
                return;
            }
            if (value instanceof Boolean) {
                this.writeBoolean((Boolean)value);
                return;
            }
            if (value instanceof AtomicBoolean) {
                this.writeBoolean(((AtomicBoolean)value).get());
                return;
            }
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + value.getClass().getName() + ")");
    }

    protected final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + this.getClass().getName());
    }
}

