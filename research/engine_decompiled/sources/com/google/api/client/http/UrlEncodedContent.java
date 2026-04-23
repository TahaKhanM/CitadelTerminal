/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedParser;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class UrlEncodedContent
extends AbstractHttpContent {
    private Object data;

    public UrlEncodedContent(Object data) {
        super(UrlEncodedParser.MEDIA_TYPE);
        this.setData(data);
    }

    public void writeTo(OutputStream out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, this.getCharset()));
        boolean first = true;
        for (Map.Entry<String, Object> nameValueEntry : Data.mapOf(this.data).entrySet()) {
            Object value = nameValueEntry.getValue();
            if (value == null) continue;
            String name = CharEscapers.escapeUri(nameValueEntry.getKey());
            Class<?> valueClass = value.getClass();
            if (value instanceof Iterable || valueClass.isArray()) {
                for (Object repeatedValue : Types.iterableOf(value)) {
                    first = UrlEncodedContent.appendParam(first, writer, name, repeatedValue);
                }
                continue;
            }
            first = UrlEncodedContent.appendParam(first, writer, name, value);
        }
        ((Writer)writer).flush();
    }

    public UrlEncodedContent setMediaType(HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }

    public final Object getData() {
        return this.data;
    }

    public UrlEncodedContent setData(Object data) {
        this.data = Preconditions.checkNotNull(data);
        return this;
    }

    public static UrlEncodedContent getContent(HttpRequest request) {
        HttpContent content = request.getContent();
        if (content != null) {
            return (UrlEncodedContent)content;
        }
        UrlEncodedContent result2 = new UrlEncodedContent(new HashMap());
        request.setContent(result2);
        return result2;
    }

    private static boolean appendParam(boolean first, Writer writer, String name, Object value) throws IOException {
        if (value == null || Data.isNull(value)) {
            return first;
        }
        if (first) {
            first = false;
        } else {
            writer.write("&");
        }
        writer.write(name);
        String stringValue = CharEscapers.escapeUri(value instanceof Enum ? FieldInfo.of((Enum)value).getName() : value.toString());
        if (stringValue.length() != 0) {
            writer.write("=");
            writer.write(stringValue);
        }
        return first;
    }
}

