/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.json;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class JsonFactory {
    public abstract JsonParser createJsonParser(InputStream var1) throws IOException;

    public abstract JsonParser createJsonParser(InputStream var1, Charset var2) throws IOException;

    public abstract JsonParser createJsonParser(String var1) throws IOException;

    public abstract JsonParser createJsonParser(Reader var1) throws IOException;

    public abstract JsonGenerator createJsonGenerator(OutputStream var1, Charset var2) throws IOException;

    public abstract JsonGenerator createJsonGenerator(Writer var1) throws IOException;

    public final JsonObjectParser createJsonObjectParser() {
        return new JsonObjectParser(this);
    }

    public final String toString(Object item) throws IOException {
        return this.toString(item, false);
    }

    public final String toPrettyString(Object item) throws IOException {
        return this.toString(item, true);
    }

    public final byte[] toByteArray(Object item) throws IOException {
        return this.toByteStream(item, false).toByteArray();
    }

    private String toString(Object item, boolean pretty) throws IOException {
        return this.toByteStream(item, pretty).toString("UTF-8");
    }

    private ByteArrayOutputStream toByteStream(Object item, boolean pretty) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        JsonGenerator generator = this.createJsonGenerator(byteStream, Charsets.UTF_8);
        if (pretty) {
            generator.enablePrettyPrint();
        }
        generator.serialize(item);
        generator.flush();
        return byteStream;
    }

    public final <T> T fromString(String value, Class<T> destinationClass) throws IOException {
        return this.createJsonParser(value).parse(destinationClass);
    }

    public final <T> T fromInputStream(InputStream inputStream, Class<T> destinationClass) throws IOException {
        return this.createJsonParser(inputStream).parseAndClose(destinationClass);
    }

    public final <T> T fromInputStream(InputStream inputStream, Charset charset, Class<T> destinationClass) throws IOException {
        return this.createJsonParser(inputStream, charset).parseAndClose(destinationClass);
    }

    public final <T> T fromReader(Reader reader, Class<T> destinationClass) throws IOException {
        return this.createJsonParser(reader).parseAndClose(destinationClass);
    }
}

