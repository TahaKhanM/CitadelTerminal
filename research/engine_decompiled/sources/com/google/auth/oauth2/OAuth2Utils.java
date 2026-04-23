/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.http.HttpTransportFactory;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

class OAuth2Utils {
    static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    static final URI TOKEN_SERVER_URI = URI.create("https://accounts.google.com/o/oauth2/token");
    static final URI TOKEN_REVOKE_URI = URI.create("https://accounts.google.com/o/oauth2/revoke");
    static final URI USER_AUTH_URI = URI.create("https://accounts.google.com/o/oauth2/auth");
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final HttpTransportFactory HTTP_TRANSPORT_FACTORY = new DefaultHttpTransportFactory();
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static String VALUE_NOT_FOUND_MESSAGE = "%sExpected value %s not found.";
    private static String VALUE_WRONG_TYPE_MESSAGE = "%sExpected %s value %s of wrong type.";
    static final String BEARER_PREFIX = "Bearer ";

    static boolean headersContainValue(HttpHeaders headers, String headerName, String value) {
        Object values = headers.get(headerName);
        if (values instanceof Collection) {
            Collection valuesCollection = (Collection)values;
            return valuesCollection.contains(value);
        }
        return false;
    }

    static GenericJson parseJson(String json) throws IOException {
        JsonObjectParser parser = new JsonObjectParser(JSON_FACTORY);
        ByteArrayInputStream stateStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        GenericJson stateJson = parser.parseAndClose((InputStream)stateStream, StandardCharsets.UTF_8, GenericJson.class);
        return stateJson;
    }

    static String validateString(Map<String, Object> map2, String key, String errorPrefix) throws IOException {
        Object value = map2.get(key);
        if (value == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, errorPrefix, key));
        }
        if (!(value instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, errorPrefix, "string", key));
        }
        return (String)value;
    }

    static void writeInputStreamToFile(InputStream credentials, String filePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(new File(filePath));){
            ByteStreams.copy(credentials, outputStream);
        }
    }

    static String validateOptionalString(Map<String, Object> map2, String key, String errorPrefix) throws IOException {
        Object value = map2.get(key);
        if (value == null) {
            return null;
        }
        if (!(value instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, errorPrefix, "string", key));
        }
        return (String)value;
    }

    static int validateInt32(Map<String, Object> map2, String key, String errorPrefix) throws IOException {
        Object value = map2.get(key);
        if (value == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, errorPrefix, key));
        }
        if (value instanceof BigDecimal) {
            BigDecimal bigDecimalValue = (BigDecimal)value;
            return bigDecimalValue.intValueExact();
        }
        if (!(value instanceof Integer)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, errorPrefix, "integer", key));
        }
        return (Integer)value;
    }

    static long validateLong(Map<String, Object> map2, String key, String errorPrefix) throws IOException {
        Object value = map2.get(key);
        if (value == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, errorPrefix, key));
        }
        if (value instanceof BigDecimal) {
            BigDecimal bigDecimalValue = (BigDecimal)value;
            return bigDecimalValue.longValueExact();
        }
        if (!(value instanceof Long)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, errorPrefix, "long", key));
        }
        return (Long)value;
    }

    static Map<String, Object> validateMap(Map<String, Object> map2, String key, String errorPrefix) throws IOException {
        Object value = map2.get(key);
        if (value == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, errorPrefix, key));
        }
        if (!(value instanceof Map)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, errorPrefix, "Map", key));
        }
        return (Map)value;
    }

    private OAuth2Utils() {
    }

    static class DefaultHttpTransportFactory
    implements HttpTransportFactory {
        DefaultHttpTransportFactory() {
        }

        @Override
        public HttpTransport create() {
            return HTTP_TRANSPORT;
        }
    }
}

