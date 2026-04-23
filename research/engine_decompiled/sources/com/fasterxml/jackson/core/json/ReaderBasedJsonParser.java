/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.CoreVersion;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class ReaderBasedJsonParser
extends ParserBase {
    protected Reader _reader;
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected final CharsToNameCanonicalizer _symbols;
    protected final int _hashSeed;
    protected boolean _tokenIncomplete = false;

    public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r, ObjectCodec codec, CharsToNameCanonicalizer st) {
        super(ctxt, features);
        this._reader = r;
        this._inputBuffer = ctxt.allocTokenBuffer();
        this._objectCodec = codec;
        this._symbols = st;
        this._hashSeed = st.hashSeed();
    }

    public Version version() {
        return CoreVersion.instance.version();
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void setCodec(ObjectCodec c) {
        this._objectCodec = c;
    }

    public int releaseBuffered(Writer w) throws IOException {
        int count2 = this._inputEnd - this._inputPtr;
        if (count2 < 1) {
            return 0;
        }
        int origPtr = this._inputPtr;
        w.write(this._inputBuffer, origPtr, count2);
        return count2;
    }

    public Object getInputSource() {
        return this._reader;
    }

    protected boolean loadMore() throws IOException {
        this._currInputProcessed += (long)this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._reader != null) {
            int count2 = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (count2 > 0) {
                this._inputPtr = 0;
                this._inputEnd = count2;
                return true;
            }
            this._closeInput();
            if (count2 == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
            }
        }
        return false;
    }

    protected char getNextChar(String eofMsg) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(eofMsg);
        }
        return this._inputBuffer[this._inputPtr++];
    }

    protected void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        char[] buf = this._inputBuffer;
        if (buf != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(buf);
        }
    }

    public String getText() throws IOException, JsonParseException {
        JsonToken t = this._currToken;
        if (t == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return this._getText2(t);
    }

    public String getValueAsString() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return super.getValueAsString(null);
    }

    public String getValueAsString(String defValue) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return super.getValueAsString(defValue);
    }

    protected String _getText2(JsonToken t) {
        if (t == null) {
            return null;
        }
        switch (t) {
            case FIELD_NAME: {
                return this._parsingContext.getCurrentName();
            }
            case VALUE_STRING: 
            case VALUE_NUMBER_INT: 
            case VALUE_NUMBER_FLOAT: {
                return this._textBuffer.contentsAsString();
            }
        }
        return t.asString();
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME: {
                    if (!this._nameCopied) {
                        String name = this._parsingContext.getCurrentName();
                        int nameLen = name.length();
                        if (this._nameCopyBuffer == null) {
                            this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(nameLen);
                        } else if (this._nameCopyBuffer.length < nameLen) {
                            this._nameCopyBuffer = new char[nameLen];
                        }
                        name.getChars(0, nameLen, this._nameCopyBuffer, 0);
                        this._nameCopied = true;
                    }
                    return this._nameCopyBuffer;
                }
                case VALUE_STRING: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case VALUE_NUMBER_INT: 
                case VALUE_NUMBER_FLOAT: {
                    return this._textBuffer.getTextBuffer();
                }
            }
            return this._currToken.asCharArray();
        }
        return null;
    }

    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME: {
                    return this._parsingContext.getCurrentName().length();
                }
                case VALUE_STRING: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case VALUE_NUMBER_INT: 
                case VALUE_NUMBER_FLOAT: {
                    return this._textBuffer.size();
                }
            }
            return this._currToken.asCharArray().length;
        }
        return 0;
    }

    public int getTextOffset() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME: {
                    return 0;
                }
                case VALUE_STRING: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case VALUE_NUMBER_INT: 
                case VALUE_NUMBER_FLOAT: {
                    return this._textBuffer.getTextOffset();
                }
            }
        }
        return 0;
    }

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            this._reportError("Current token (" + (Object)((Object)this._currToken) + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = this._decodeBase64(b64variant);
            }
            catch (IllegalArgumentException iae) {
                throw this._constructError("Failed to decode VALUE_STRING as base64 (" + b64variant + "): " + iae.getMessage());
            }
            this._tokenIncomplete = false;
        } else if (this._binaryValue == null) {
            ByteArrayBuilder builder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), builder, b64variant);
            this._binaryValue = builder.toByteArray();
        }
        return this._binaryValue;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException, JsonParseException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] b = this.getBinaryValue(b64variant);
            out.write(b);
            return b.length;
        }
        byte[] buf = this._ioContext.allocBase64Buffer();
        try {
            int n = this._readBinary(b64variant, out, buf);
            return n;
        }
        finally {
            this._ioContext.releaseBase64Buffer(buf);
        }
    }

    protected int _readBinary(Base64Variant b64variant, OutputStream out, byte[] buffer) throws IOException, JsonParseException {
        int outputPtr = 0;
        int outputEnd = buffer.length - 3;
        int outputCount = 0;
        while (true) {
            char ch;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((ch = this._inputBuffer[this._inputPtr++]) <= ' ') continue;
            int bits2 = b64variant.decodeBase64Char(ch);
            if (bits2 < 0) {
                if (ch == '\"') break;
                bits2 = this._decodeBase64Escape(b64variant, ch, 0);
                if (bits2 < 0) continue;
            }
            if (outputPtr > outputEnd) {
                outputCount += outputPtr;
                out.write(buffer, 0, outputPtr);
                outputPtr = 0;
            }
            int decodedData = bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                bits2 = this._decodeBase64Escape(b64variant, ch, 1);
            }
            decodedData = decodedData << 6 | bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits2 != -2) {
                    if (ch == '\"' && !b64variant.usesPadding()) {
                        buffer[outputPtr++] = (byte)(decodedData >>= 4);
                        break;
                    }
                    bits2 = this._decodeBase64Escape(b64variant, ch, 2);
                }
                if (bits2 == -2) {
                    if (this._inputPtr >= this._inputEnd) {
                        this.loadMoreGuaranteed();
                    }
                    if (!b64variant.usesPaddingChar(ch = this._inputBuffer[this._inputPtr++])) {
                        throw this.reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    buffer[outputPtr++] = (byte)(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits2 != -2) {
                    if (ch == '\"' && !b64variant.usesPadding()) {
                        buffer[outputPtr++] = (byte)((decodedData >>= 2) >> 8);
                        buffer[outputPtr++] = (byte)decodedData;
                        break;
                    }
                    bits2 = this._decodeBase64Escape(b64variant, ch, 3);
                }
                if (bits2 == -2) {
                    buffer[outputPtr++] = (byte)((decodedData >>= 2) >> 8);
                    buffer[outputPtr++] = (byte)decodedData;
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits2;
            buffer[outputPtr++] = (byte)(decodedData >> 16);
            buffer[outputPtr++] = (byte)(decodedData >> 8);
            buffer[outputPtr++] = (byte)decodedData;
        }
        this._tokenIncomplete = false;
        if (outputPtr > 0) {
            outputCount += outputPtr;
            out.write(buffer, 0, outputPtr);
        }
        return outputCount;
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken t;
        boolean inObject;
        int i;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nextAfterName();
        }
        if (this._tokenIncomplete) {
            this._skipString();
        }
        if ((i = this._skipWSOrEnd()) < 0) {
            this.close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = this._currInputProcessed + (long)this._inputPtr - 1L;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = this._inputPtr - this._currInputRowStart - 1;
        this._binaryValue = null;
        if (i == 93) {
            if (!this._parsingContext.inArray()) {
                this._reportMismatchedEndMarker(i, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return this._currToken;
        }
        if (i == 125) {
            if (!this._parsingContext.inObject()) {
                this._reportMismatchedEndMarker(i, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            if (i != 44) {
                this._reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            i = this._skipWS();
        }
        if (inObject = this._parsingContext.inObject()) {
            String name = this._parseFieldName(i);
            this._parsingContext.setCurrentName(name);
            this._currToken = JsonToken.FIELD_NAME;
            i = this._skipWS();
            if (i != 58) {
                this._reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
            }
            i = this._skipWS();
        }
        switch (i) {
            case 34: {
                this._tokenIncomplete = true;
                t = JsonToken.VALUE_STRING;
                break;
            }
            case 91: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                }
                t = JsonToken.START_ARRAY;
                break;
            }
            case 123: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                }
                t = JsonToken.START_OBJECT;
                break;
            }
            case 93: 
            case 125: {
                this._reportUnexpectedChar(i, "expected a value");
            }
            case 116: {
                this._matchToken("true", 1);
                t = JsonToken.VALUE_TRUE;
                break;
            }
            case 102: {
                this._matchToken("false", 1);
                t = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchToken("null", 1);
                t = JsonToken.VALUE_NULL;
                break;
            }
            case 45: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                t = this.parseNumberText(i);
                break;
            }
            default: {
                t = this._handleUnexpectedValue(i);
            }
        }
        if (inObject) {
            this._nextToken = t;
            return this._currToken;
        }
        this._currToken = t;
        return t;
    }

    private JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken t = this._nextToken;
        this._nextToken = null;
        if (t == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (t == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = t;
        return this._currToken;
    }

    public String nextTextValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t = this._nextToken;
            this._nextToken = null;
            this._currToken = t;
            if (t == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    this._finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (t == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        return this.nextToken() == JsonToken.VALUE_STRING ? this.getText() : null;
    }

    public int nextIntValue(int defaultValue) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t = this._nextToken;
            this._nextToken = null;
            this._currToken = t;
            if (t == JsonToken.VALUE_NUMBER_INT) {
                return this.getIntValue();
            }
            if (t == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return defaultValue;
        }
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getIntValue() : defaultValue;
    }

    public long nextLongValue(long defaultValue) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t = this._nextToken;
            this._nextToken = null;
            this._currToken = t;
            if (t == JsonToken.VALUE_NUMBER_INT) {
                return this.getLongValue();
            }
            if (t == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return defaultValue;
        }
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getLongValue() : defaultValue;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t = this._nextToken;
            this._nextToken = null;
            this._currToken = t;
            if (t == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (t == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (t == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
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

    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    /*
     * Unable to fully structure code
     */
    protected JsonToken parseNumberText(int ch) throws IOException, JsonParseException {
        block12: {
            block11: {
                negative = ch == 45;
                ptr = this._inputPtr;
                startPtr = ptr - 1;
                inputLen = this._inputEnd;
                if (!negative) break block11;
                if (ptr < this._inputEnd) {
                    if ((ch = this._inputBuffer[ptr++]) > 57 || ch < 48) {
                        this._inputPtr = ptr;
                        return this._handleInvalidNumberStart(ch, true);
                    } else {
                        ** GOTO lbl11
                    }
                }
                break block12;
            }
            if (ch == 48) break block12;
            intLen = 1;
            block0: while (ptr < this._inputEnd) {
                if ((ch = this._inputBuffer[ptr++]) >= 48 && ch <= 57) {
                    ++intLen;
                    continue;
                }
                fractLen = 0;
                if (ch != 46) ** GOTO lbl26
                while (ptr < inputLen) {
                    if ((ch = this._inputBuffer[ptr++]) >= 48 && ch <= 57) {
                        ++fractLen;
                        continue;
                    }
                    if (fractLen == 0) {
                        this.reportUnexpectedNumberChar(ch, "Decimal point not followed by a digit");
                    }
lbl26:
                    // 4 sources

                    expLen = 0;
                    if (ch == 101 || ch == 69) {
                        if (ptr >= inputLen) break block0;
                        if ((ch = this._inputBuffer[ptr++]) == 45 || ch == 43) {
                            if (ptr >= inputLen) break block0;
                            ch = this._inputBuffer[ptr++];
                        }
                        while (ch <= 57 && ch >= 48) {
                            ++expLen;
                            if (ptr >= inputLen) break block0;
                            ch = this._inputBuffer[ptr++];
                        }
                        if (expLen == 0) {
                            this.reportUnexpectedNumberChar(ch, "Exponent indicator not followed by a digit");
                        }
                    }
                    this._inputPtr = --ptr;
                    len = ptr - startPtr;
                    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
                    return this.reset(negative, intLen, fractLen, expLen);
                }
                break block0;
            }
        }
        this._inputPtr = negative != false ? startPtr + 1 : startPtr;
        return this.parseNumberText2(negative);
    }

    private JsonToken parseNumberText2(boolean negative) throws IOException, JsonParseException {
        char c;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = 0;
        if (negative) {
            outBuf[outPtr++] = 45;
        }
        int intLen = 0;
        char c2 = c = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("No digit following minus sign");
        if (c == '0') {
            c = this._verifyNoLeadingZeroes();
        }
        boolean eof = false;
        while (c >= '0' && c <= '9') {
            ++intLen;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c;
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                c = '\u0000';
                eof = true;
                break;
            }
            c = this._inputBuffer[this._inputPtr++];
        }
        if (intLen == 0) {
            this.reportInvalidNumber("Missing integer part (next char " + ReaderBasedJsonParser._getCharDesc(c) + ")");
        }
        int fractLen = 0;
        if (c == '.') {
            outBuf[outPtr++] = c;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                    eof = true;
                    break;
                }
                if ((c = this._inputBuffer[this._inputPtr++]) < '0' || c > '9') break;
                ++fractLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c;
            }
            if (fractLen == 0) {
                this.reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
        }
        int expLen = 0;
        if (c == 'e' || c == 'E') {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c;
            char c3 = c = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("expected a digit for number exponent");
            if (c == '-' || c == '+') {
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c;
                char c4 = c = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("expected a digit for number exponent");
            }
            while (c <= '9' && c >= '0') {
                ++expLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c;
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                    eof = true;
                    break;
                }
                c = this._inputBuffer[this._inputPtr++];
            }
            if (expLen == 0) {
                this.reportUnexpectedNumberChar(c, "Exponent indicator not followed by a digit");
            }
        }
        if (!eof) {
            --this._inputPtr;
        }
        this._textBuffer.setCurrentLength(outPtr);
        return this.reset(negative, intLen, fractLen, expLen);
    }

    private char _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            return '0';
        }
        char ch = this._inputBuffer[this._inputPtr];
        if (ch < '0' || ch > '9') {
            return '0';
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        ++this._inputPtr;
        if (ch == '0') {
            while (this._inputPtr < this._inputEnd || this.loadMore()) {
                ch = this._inputBuffer[this._inputPtr];
                if (ch < '0' || ch > '9') {
                    return '0';
                }
                ++this._inputPtr;
                if (ch == '0') continue;
                break;
            }
        }
        return ch;
    }

    protected JsonToken _handleInvalidNumberStart(int ch, boolean negative) throws IOException, JsonParseException {
        if (ch == 73) {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOFInValue();
            }
            if ((ch = this._inputBuffer[this._inputPtr++]) == 78) {
                String match = negative ? "-INF" : "+INF";
                this._matchToken(match, 3);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (ch == 110) {
                String match = negative ? "-Infinity" : "+Infinity";
                this._matchToken(match, 3);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        this.reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected String _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return this._handleUnusualFieldName(i);
        }
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = CharTypes.getInputCodeLatin1();
            int maxCode = codes.length;
            do {
                char ch;
                if ((ch = this._inputBuffer[ptr]) < maxCode && codes[ch] != 0) {
                    if (ch != '\"') break;
                    int start = this._inputPtr;
                    this._inputPtr = ptr + 1;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                hash = hash * 33 + ch;
            } while (++ptr < inputLen);
        }
        int start = this._inputPtr;
        this._inputPtr = ptr;
        return this._parseFieldName2(start, hash, 34);
    }

    private String _parseFieldName2(int startPtr, int hash, int endChar) throws IOException, JsonParseException {
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char c;
            char i;
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing '" + (char)endChar + "' for name");
            }
            if ((i = (c = this._inputBuffer[this._inputPtr++])) <= '\\') {
                if (i == '\\') {
                    c = this._decodeEscaped();
                } else if (i <= endChar) {
                    if (i == endChar) break;
                    if (i < ' ') {
                        this._throwUnquotedSpace(i, "name");
                    }
                }
            }
            hash = hash * 33 + i;
            outBuf[outPtr++] = c;
            if (outPtr < outBuf.length) continue;
            outBuf = this._textBuffer.finishCurrentSegment();
            outPtr = 0;
        }
        this._textBuffer.setCurrentLength(outPtr);
        TextBuffer tb = this._textBuffer;
        char[] buf = tb.getTextBuffer();
        int start = tb.getTextOffset();
        int len = tb.size();
        return this._symbols.findSymbol(buf, start, len, hash);
    }

    protected String _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        int[] codes;
        int maxCode;
        boolean firstOk;
        if (i == 39 && this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return this._parseApostropheFieldName();
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            this._reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        if (!(firstOk = i < (maxCode = (codes = CharTypes.getInputCodeLatin1JsNames()).length) ? codes[i] == 0 && (i < 48 || i > 57) : Character.isJavaIdentifierPart((char)i))) {
            this._reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            do {
                char ch;
                if ((ch = this._inputBuffer[ptr]) < maxCode) {
                    if (codes[ch] != 0) {
                        int start = this._inputPtr - 1;
                        this._inputPtr = ptr;
                        return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                    }
                } else if (!Character.isJavaIdentifierPart(ch)) {
                    int start = this._inputPtr - 1;
                    this._inputPtr = ptr;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                hash = hash * 33 + ch;
            } while (++ptr < inputLen);
        }
        int start = this._inputPtr - 1;
        this._inputPtr = ptr;
        return this._parseUnusualFieldName2(start, hash, codes);
    }

    protected String _parseApostropheFieldName() throws IOException, JsonParseException {
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = CharTypes.getInputCodeLatin1();
            int maxCode = codes.length;
            do {
                char ch;
                if ((ch = this._inputBuffer[ptr]) == '\'') {
                    int start = this._inputPtr;
                    this._inputPtr = ptr + 1;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                if (ch < maxCode && codes[ch] != 0) break;
                hash = hash * 33 + ch;
            } while (++ptr < inputLen);
        }
        int start = this._inputPtr;
        this._inputPtr = ptr;
        return this._parseFieldName2(start, hash, 39);
    }

    protected JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        switch (i) {
            case 39: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) break;
                return this._handleApostropheValue();
            }
            case 78: {
                this._matchToken("NaN", 1);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("NaN", Double.NaN);
                }
                this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            }
            case 43: {
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                    this._reportInvalidEOFInValue();
                }
                return this._handleInvalidNumberStart(this._inputBuffer[this._inputPtr++], false);
            }
        }
        this._reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char c;
            char i;
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value");
            }
            if ((i = (c = this._inputBuffer[this._inputPtr++])) <= '\\') {
                if (i == '\\') {
                    c = this._decodeEscaped();
                } else if (i <= '\'') {
                    if (i == '\'') break;
                    if (i < ' ') {
                        this._throwUnquotedSpace(i, "string value");
                    }
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c;
        }
        this._textBuffer.setCurrentLength(outPtr);
        return JsonToken.VALUE_STRING;
    }

    private String _parseUnusualFieldName2(int startPtr, int hash, int[] codes) throws IOException, JsonParseException {
        char c;
        char i;
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int maxCode = codes.length;
        while ((this._inputPtr < this._inputEnd || this.loadMore()) && !((i = (c = this._inputBuffer[this._inputPtr])) <= maxCode ? codes[i] != 0 : !Character.isJavaIdentifierPart(c))) {
            ++this._inputPtr;
            hash = hash * 33 + i;
            outBuf[outPtr++] = c;
            if (outPtr < outBuf.length) continue;
            outBuf = this._textBuffer.finishCurrentSegment();
            outPtr = 0;
        }
        this._textBuffer.setCurrentLength(outPtr);
        TextBuffer tb = this._textBuffer;
        char[] buf = tb.getTextBuffer();
        int start = tb.getTextOffset();
        int len = tb.size();
        return this._symbols.findSymbol(buf, start, len, hash);
    }

    protected void _finishString() throws IOException, JsonParseException {
        int ptr = this._inputPtr;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = CharTypes.getInputCodeLatin1();
            int maxCode = codes.length;
            do {
                char ch;
                if ((ch = this._inputBuffer[ptr]) >= maxCode || codes[ch] == 0) continue;
                if (ch != '\"') break;
                this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
                this._inputPtr = ptr + 1;
                return;
            } while (++ptr < inputLen);
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
        this._inputPtr = ptr;
        this._finishString2();
    }

    protected void _finishString2() throws IOException, JsonParseException {
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char c;
            char i;
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value");
            }
            if ((i = (c = this._inputBuffer[this._inputPtr++])) <= '\\') {
                if (i == '\\') {
                    c = this._decodeEscaped();
                } else if (i <= '\"') {
                    if (i == '\"') break;
                    if (i < ' ') {
                        this._throwUnquotedSpace(i, "string value");
                    }
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c;
        }
        this._textBuffer.setCurrentLength(outPtr);
    }

    protected void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int inputPtr = this._inputPtr;
        int inputLen = this._inputEnd;
        char[] inputBuffer = this._inputBuffer;
        while (true) {
            char c;
            char i;
            if (inputPtr >= inputLen) {
                this._inputPtr = inputPtr;
                if (!this.loadMore()) {
                    this._reportInvalidEOF(": was expecting closing quote for a string value");
                }
                inputPtr = this._inputPtr;
                inputLen = this._inputEnd;
            }
            if ((i = (c = inputBuffer[inputPtr++])) > '\\') continue;
            if (i == '\\') {
                this._inputPtr = inputPtr;
                c = this._decodeEscaped();
                inputPtr = this._inputPtr;
                inputLen = this._inputEnd;
                continue;
            }
            if (i > '\"') continue;
            if (i == '\"') break;
            if (i >= ' ') continue;
            this._inputPtr = inputPtr;
            this._throwUnquotedSpace(i, "string value");
        }
        this._inputPtr = inputPtr;
    }

    protected void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || this.loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
            ++this._inputPtr;
        }
        ++this._currInputRow;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipLF() throws IOException {
        ++this._currInputRow;
        this._currInputRowStart = this._inputPtr;
    }

    private int _skipWS() throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            char i;
            if ((i = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i != '/') {
                    return i;
                }
                this._skipComment();
                continue;
            }
            if (i == ' ') continue;
            if (i == '\n') {
                this._skipLF();
                continue;
            }
            if (i == '\r') {
                this._skipCR();
                continue;
            }
            if (i == '\t') continue;
            this._throwInvalidSpace(i);
        }
        throw this._constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
    }

    private int _skipWSOrEnd() throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            char i;
            if ((i = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i == '/') {
                    this._skipComment();
                    continue;
                }
                return i;
            }
            if (i == ' ') continue;
            if (i == '\n') {
                this._skipLF();
                continue;
            }
            if (i == '\r') {
                this._skipCR();
                continue;
            }
            if (i == '\t') continue;
            this._throwInvalidSpace(i);
        }
        this._handleEOF();
        return -1;
    }

    private void _skipComment() throws IOException, JsonParseException {
        char c;
        if (!this.isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(" in a comment");
        }
        if ((c = this._inputBuffer[this._inputPtr++]) == '/') {
            this._skipCppComment();
        } else if (c == '*') {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            char i;
            if ((i = this._inputBuffer[this._inputPtr++]) > '*') continue;
            if (i == '*') {
                if (this._inputPtr >= this._inputEnd && !this.loadMore()) break;
                if (this._inputBuffer[this._inputPtr] != '/') continue;
                ++this._inputPtr;
                return;
            }
            if (i >= ' ') continue;
            if (i == '\n') {
                this._skipLF();
                continue;
            }
            if (i == '\r') {
                this._skipCR();
                continue;
            }
            if (i == '\t') continue;
            this._throwInvalidSpace(i);
        }
        this._reportInvalidEOF(" in a comment");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd || this.loadMore()) {
            char i;
            if ((i = this._inputBuffer[this._inputPtr++]) >= ' ') continue;
            if (i == '\n') {
                this._skipLF();
                break;
            }
            if (i == '\r') {
                this._skipCR();
                break;
            }
            if (i == '\t') continue;
            this._throwInvalidSpace(i);
        }
    }

    protected char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            this._reportInvalidEOF(" in character escape sequence");
        }
        char c = this._inputBuffer[this._inputPtr++];
        switch (c) {
            case 'b': {
                return '\b';
            }
            case 't': {
                return '\t';
            }
            case 'n': {
                return '\n';
            }
            case 'f': {
                return '\f';
            }
            case 'r': {
                return '\r';
            }
            case '\"': 
            case '/': 
            case '\\': {
                return c;
            }
            case 'u': {
                break;
            }
            default: {
                return this._handleUnrecognizedCharacterEscape(c);
            }
        }
        int value = 0;
        for (int i = 0; i < 4; ++i) {
            char ch;
            int digit;
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOF(" in character escape sequence");
            }
            if ((digit = CharTypes.charToHex(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                this._reportUnexpectedChar(ch, "expected a hex-digit for character escape sequence");
            }
            value = value << 4 | digit;
        }
        return (char)value;
    }

    protected void _matchToken(String matchStr, int i) throws IOException, JsonParseException {
        int len = matchStr.length();
        do {
            if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
                this._reportInvalidEOFInValue();
            }
            if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i)) {
                this._reportInvalidToken(matchStr.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            ++this._inputPtr;
        } while (++i < len);
        if (this._inputPtr >= this._inputEnd && !this.loadMore()) {
            return;
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c == ']' || c == '}') {
            return;
        }
        if (Character.isJavaIdentifierPart(c)) {
            this._reportInvalidToken(matchStr.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected byte[] _decodeBase64(Base64Variant b64variant) throws IOException, JsonParseException {
        ByteArrayBuilder builder = this._getByteArrayBuilder();
        while (true) {
            char ch;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((ch = this._inputBuffer[this._inputPtr++]) <= ' ') continue;
            int bits2 = b64variant.decodeBase64Char(ch);
            if (bits2 < 0) {
                if (ch == '\"') {
                    return builder.toByteArray();
                }
                bits2 = this._decodeBase64Escape(b64variant, ch, 0);
                if (bits2 < 0) continue;
            }
            int decodedData = bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                bits2 = this._decodeBase64Escape(b64variant, ch, 1);
            }
            decodedData = decodedData << 6 | bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits2 != -2) {
                    if (ch == '\"' && !b64variant.usesPadding()) {
                        builder.append(decodedData >>= 4);
                        return builder.toByteArray();
                    }
                    bits2 = this._decodeBase64Escape(b64variant, ch, 2);
                }
                if (bits2 == -2) {
                    if (this._inputPtr >= this._inputEnd) {
                        this.loadMoreGuaranteed();
                    }
                    if (!b64variant.usesPaddingChar(ch = this._inputBuffer[this._inputPtr++])) {
                        throw this.reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    builder.append(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits2;
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            if ((bits2 = b64variant.decodeBase64Char(ch = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits2 != -2) {
                    if (ch == '\"' && !b64variant.usesPadding()) {
                        builder.appendTwoBytes(decodedData >>= 2);
                        return builder.toByteArray();
                    }
                    bits2 = this._decodeBase64Escape(b64variant, ch, 3);
                }
                if (bits2 == -2) {
                    builder.appendTwoBytes(decodedData >>= 2);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits2;
            builder.appendThreeBytes(decodedData);
        }
    }

    protected void _reportInvalidToken(String matchedPart, String msg) throws IOException, JsonParseException {
        char c;
        StringBuilder sb = new StringBuilder(matchedPart);
        while ((this._inputPtr < this._inputEnd || this.loadMore()) && Character.isJavaIdentifierPart(c = this._inputBuffer[this._inputPtr])) {
            ++this._inputPtr;
            sb.append(c);
        }
        this._reportError("Unrecognized token '" + sb.toString() + "': was expecting ");
    }
}

