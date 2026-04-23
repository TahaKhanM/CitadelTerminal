/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Base64;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class HttpHeaders
extends GenericData {
    @Key(value="Accept")
    private List<String> accept;
    @Key(value="Accept-Encoding")
    private List<String> acceptEncoding = new ArrayList<String>(Collections.singleton("gzip"));
    @Key(value="Authorization")
    private List<String> authorization;
    @Key(value="Cache-Control")
    private List<String> cacheControl;
    @Key(value="Content-Encoding")
    private List<String> contentEncoding;
    @Key(value="Content-Length")
    private List<Long> contentLength;
    @Key(value="Content-MD5")
    private List<String> contentMD5;
    @Key(value="Content-Range")
    private List<String> contentRange;
    @Key(value="Content-Type")
    private List<String> contentType;
    @Key(value="Cookie")
    private List<String> cookie;
    @Key(value="Date")
    private List<String> date;
    @Key(value="ETag")
    private List<String> etag;
    @Key(value="Expires")
    private List<String> expires;
    @Key(value="If-Modified-Since")
    private List<String> ifModifiedSince;
    @Key(value="If-Match")
    private List<String> ifMatch;
    @Key(value="If-None-Match")
    private List<String> ifNoneMatch;
    @Key(value="If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;
    @Key(value="If-Range")
    private List<String> ifRange;
    @Key(value="Last-Modified")
    private List<String> lastModified;
    @Key(value="Location")
    private List<String> location;
    @Key(value="MIME-Version")
    private List<String> mimeVersion;
    @Key(value="Range")
    private List<String> range;
    @Key(value="Retry-After")
    private List<String> retryAfter;
    @Key(value="User-Agent")
    private List<String> userAgent;
    @Key(value="WWW-Authenticate")
    private List<String> authenticate;
    @Key(value="Age")
    private List<Long> age;

    public HttpHeaders() {
        super(EnumSet.of(GenericData.Flags.IGNORE_CASE));
    }

    @Override
    public HttpHeaders clone() {
        return (HttpHeaders)super.clone();
    }

    @Override
    public HttpHeaders set(String fieldName, Object value) {
        return (HttpHeaders)super.set(fieldName, value);
    }

    public final String getAccept() {
        return this.getFirstHeaderValue(this.accept);
    }

    public HttpHeaders setAccept(String accept) {
        this.accept = this.getAsList(accept);
        return this;
    }

    public final String getAcceptEncoding() {
        return this.getFirstHeaderValue(this.acceptEncoding);
    }

    public HttpHeaders setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = this.getAsList(acceptEncoding);
        return this;
    }

    public final String getAuthorization() {
        return this.getFirstHeaderValue(this.authorization);
    }

    public final List<String> getAuthorizationAsList() {
        return this.authorization;
    }

    public HttpHeaders setAuthorization(String authorization) {
        return this.setAuthorization(this.getAsList(authorization));
    }

    public HttpHeaders setAuthorization(List<String> authorization) {
        this.authorization = authorization;
        return this;
    }

    public final String getCacheControl() {
        return this.getFirstHeaderValue(this.cacheControl);
    }

    public HttpHeaders setCacheControl(String cacheControl) {
        this.cacheControl = this.getAsList(cacheControl);
        return this;
    }

    public final String getContentEncoding() {
        return this.getFirstHeaderValue(this.contentEncoding);
    }

    public HttpHeaders setContentEncoding(String contentEncoding) {
        this.contentEncoding = this.getAsList(contentEncoding);
        return this;
    }

    public final Long getContentLength() {
        return this.getFirstHeaderValue(this.contentLength);
    }

    public HttpHeaders setContentLength(Long contentLength) {
        this.contentLength = this.getAsList(contentLength);
        return this;
    }

    public final String getContentMD5() {
        return this.getFirstHeaderValue(this.contentMD5);
    }

    public HttpHeaders setContentMD5(String contentMD5) {
        this.contentMD5 = this.getAsList(contentMD5);
        return this;
    }

    public final String getContentRange() {
        return this.getFirstHeaderValue(this.contentRange);
    }

    public HttpHeaders setContentRange(String contentRange) {
        this.contentRange = this.getAsList(contentRange);
        return this;
    }

    public final String getContentType() {
        return this.getFirstHeaderValue(this.contentType);
    }

    public HttpHeaders setContentType(String contentType) {
        this.contentType = this.getAsList(contentType);
        return this;
    }

    public final String getCookie() {
        return this.getFirstHeaderValue(this.cookie);
    }

    public HttpHeaders setCookie(String cookie) {
        this.cookie = this.getAsList(cookie);
        return this;
    }

    public final String getDate() {
        return this.getFirstHeaderValue(this.date);
    }

    public HttpHeaders setDate(String date) {
        this.date = this.getAsList(date);
        return this;
    }

    public final String getETag() {
        return this.getFirstHeaderValue(this.etag);
    }

    public HttpHeaders setETag(String etag) {
        this.etag = this.getAsList(etag);
        return this;
    }

    public final String getExpires() {
        return this.getFirstHeaderValue(this.expires);
    }

    public HttpHeaders setExpires(String expires) {
        this.expires = this.getAsList(expires);
        return this;
    }

    public final String getIfModifiedSince() {
        return this.getFirstHeaderValue(this.ifModifiedSince);
    }

    public HttpHeaders setIfModifiedSince(String ifModifiedSince) {
        this.ifModifiedSince = this.getAsList(ifModifiedSince);
        return this;
    }

    public final String getIfMatch() {
        return this.getFirstHeaderValue(this.ifMatch);
    }

    public HttpHeaders setIfMatch(String ifMatch) {
        this.ifMatch = this.getAsList(ifMatch);
        return this;
    }

    public final String getIfNoneMatch() {
        return this.getFirstHeaderValue(this.ifNoneMatch);
    }

    public HttpHeaders setIfNoneMatch(String ifNoneMatch) {
        this.ifNoneMatch = this.getAsList(ifNoneMatch);
        return this;
    }

    public final String getIfUnmodifiedSince() {
        return this.getFirstHeaderValue(this.ifUnmodifiedSince);
    }

    public HttpHeaders setIfUnmodifiedSince(String ifUnmodifiedSince) {
        this.ifUnmodifiedSince = this.getAsList(ifUnmodifiedSince);
        return this;
    }

    public final String getIfRange() {
        return this.getFirstHeaderValue(this.ifRange);
    }

    public HttpHeaders setIfRange(String ifRange) {
        this.ifRange = this.getAsList(ifRange);
        return this;
    }

    public final String getLastModified() {
        return this.getFirstHeaderValue(this.lastModified);
    }

    public HttpHeaders setLastModified(String lastModified) {
        this.lastModified = this.getAsList(lastModified);
        return this;
    }

    public final String getLocation() {
        return this.getFirstHeaderValue(this.location);
    }

    public HttpHeaders setLocation(String location) {
        this.location = this.getAsList(location);
        return this;
    }

    public final String getMimeVersion() {
        return this.getFirstHeaderValue(this.mimeVersion);
    }

    public HttpHeaders setMimeVersion(String mimeVersion) {
        this.mimeVersion = this.getAsList(mimeVersion);
        return this;
    }

    public final String getRange() {
        return this.getFirstHeaderValue(this.range);
    }

    public HttpHeaders setRange(String range2) {
        this.range = this.getAsList(range2);
        return this;
    }

    public final String getRetryAfter() {
        return this.getFirstHeaderValue(this.retryAfter);
    }

    public HttpHeaders setRetryAfter(String retryAfter) {
        this.retryAfter = this.getAsList(retryAfter);
        return this;
    }

    public final String getUserAgent() {
        return this.getFirstHeaderValue(this.userAgent);
    }

    public HttpHeaders setUserAgent(String userAgent) {
        this.userAgent = this.getAsList(userAgent);
        return this;
    }

    public final String getAuthenticate() {
        return this.getFirstHeaderValue(this.authenticate);
    }

    public final List<String> getAuthenticateAsList() {
        return this.authenticate;
    }

    public HttpHeaders setAuthenticate(String authenticate) {
        this.authenticate = this.getAsList(authenticate);
        return this;
    }

    public final Long getAge() {
        return this.getFirstHeaderValue(this.age);
    }

    public HttpHeaders setAge(Long age) {
        this.age = this.getAsList(age);
        return this;
    }

    public HttpHeaders setBasicAuthentication(String username, String password) {
        String userPass = Preconditions.checkNotNull(username) + ":" + Preconditions.checkNotNull(password);
        String encoded = Base64.encodeBase64String(StringUtils.getBytesUtf8(userPass));
        return this.setAuthorization("Basic " + encoded);
    }

    private static void addHeader(Logger logger, StringBuilder logbuf, StringBuilder curlbuf, LowLevelHttpRequest lowLevelHttpRequest, String name, Object value, Writer writer) throws IOException {
        String stringValue;
        if (value == null || Data.isNull(value)) {
            return;
        }
        String loggedStringValue = stringValue = HttpHeaders.toStringValue(value);
        if (!(!"Authorization".equalsIgnoreCase(name) && !"Cookie".equalsIgnoreCase(name) || logger != null && logger.isLoggable(Level.ALL))) {
            loggedStringValue = "<Not Logged>";
        }
        if (logbuf != null) {
            logbuf.append(name).append(": ");
            logbuf.append(loggedStringValue);
            logbuf.append(StringUtils.LINE_SEPARATOR);
        }
        if (curlbuf != null) {
            curlbuf.append(" -H '").append(name).append(": ").append(loggedStringValue).append("'");
        }
        if (lowLevelHttpRequest != null) {
            lowLevelHttpRequest.addHeader(name, stringValue);
        }
        if (writer != null) {
            writer.write(name);
            writer.write(": ");
            writer.write(stringValue);
            writer.write("\r\n");
        }
    }

    private static String toStringValue(Object headerValue) {
        return headerValue instanceof Enum ? FieldInfo.of((Enum)headerValue).getName() : headerValue.toString();
    }

    static void serializeHeaders(HttpHeaders headers, StringBuilder logbuf, StringBuilder curlbuf, Logger logger, LowLevelHttpRequest lowLevelHttpRequest) throws IOException {
        HttpHeaders.serializeHeaders(headers, logbuf, curlbuf, logger, lowLevelHttpRequest, null);
    }

    public static void serializeHeadersForMultipartRequests(HttpHeaders headers, StringBuilder logbuf, Logger logger, Writer writer) throws IOException {
        HttpHeaders.serializeHeaders(headers, logbuf, null, logger, null, writer);
    }

    static void serializeHeaders(HttpHeaders headers, StringBuilder logbuf, StringBuilder curlbuf, Logger logger, LowLevelHttpRequest lowLevelHttpRequest, Writer writer) throws IOException {
        HashSet<String> headerNames = new HashSet<String>();
        for (Map.Entry<String, Object> headerEntry : headers.entrySet()) {
            String name = headerEntry.getKey();
            Preconditions.checkArgument(headerNames.add(name), "multiple headers of the same name (headers are case insensitive): %s", name);
            Object value = headerEntry.getValue();
            if (value == null) continue;
            String displayName = name;
            FieldInfo fieldInfo = headers.getClassInfo().getFieldInfo(name);
            if (fieldInfo != null) {
                displayName = fieldInfo.getName();
            }
            Class<?> valueClass = value.getClass();
            if (value instanceof Iterable || valueClass.isArray()) {
                for (Object repeatedValue : Types.iterableOf(value)) {
                    HttpHeaders.addHeader(logger, logbuf, curlbuf, lowLevelHttpRequest, displayName, repeatedValue, writer);
                }
                continue;
            }
            HttpHeaders.addHeader(logger, logbuf, curlbuf, lowLevelHttpRequest, displayName, value, writer);
        }
        if (writer != null) {
            writer.flush();
        }
    }

    public final void fromHttpResponse(LowLevelHttpResponse response, StringBuilder logger) throws IOException {
        this.clear();
        ParseHeaderState state = new ParseHeaderState(this, logger);
        int headerCount = response.getHeaderCount();
        for (int i = 0; i < headerCount; ++i) {
            this.parseHeader(response.getHeaderName(i), response.getHeaderValue(i), state);
        }
        state.finish();
    }

    private <T> T getFirstHeaderValue(List<T> internalValue) {
        return internalValue == null ? null : (T)internalValue.get(0);
    }

    private <T> List<T> getAsList(T passedValue) {
        if (passedValue == null) {
            return null;
        }
        ArrayList<T> result2 = new ArrayList<T>();
        result2.add(passedValue);
        return result2;
    }

    public String getFirstHeaderStringValue(String name) {
        Iterator i$;
        Object value = this.get(name.toLowerCase());
        if (value == null) {
            return null;
        }
        Class<?> valueClass = value.getClass();
        if ((value instanceof Iterable || valueClass.isArray()) && (i$ = Types.iterableOf(value).iterator()).hasNext()) {
            Object repeatedValue = i$.next();
            return HttpHeaders.toStringValue(repeatedValue);
        }
        return HttpHeaders.toStringValue(value);
    }

    public List<String> getHeaderStringValues(String name) {
        Object value = this.get(name.toLowerCase());
        if (value == null) {
            return Collections.emptyList();
        }
        Class<?> valueClass = value.getClass();
        if (value instanceof Iterable || valueClass.isArray()) {
            ArrayList<String> values = new ArrayList<String>();
            for (Object repeatedValue : Types.iterableOf(value)) {
                values.add(HttpHeaders.toStringValue(repeatedValue));
            }
            return Collections.unmodifiableList(values);
        }
        return Collections.singletonList(HttpHeaders.toStringValue(value));
    }

    public final void fromHttpHeaders(HttpHeaders headers) {
        try {
            ParseHeaderState state = new ParseHeaderState(this, null);
            HttpHeaders.serializeHeaders(headers, null, null, null, new HeaderParsingFakeLevelHttpRequest(this, state));
            state.finish();
        }
        catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }

    void parseHeader(String headerName, String headerValue, ParseHeaderState state) {
        FieldInfo fieldInfo;
        List<Type> context = state.context;
        ClassInfo classInfo = state.classInfo;
        ArrayValueMap arrayValueMap = state.arrayValueMap;
        StringBuilder logger = state.logger;
        if (logger != null) {
            logger.append(headerName + ": " + headerValue).append(StringUtils.LINE_SEPARATOR);
        }
        if ((fieldInfo = classInfo.getFieldInfo(headerName)) != null) {
            Type type = Data.resolveWildcardTypeOrTypeVariable(context, fieldInfo.getGenericType());
            if (Types.isArray(type)) {
                Class<?> rawArrayComponentType = Types.getRawArrayComponentType(context, Types.getArrayComponentType(type));
                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, HttpHeaders.parseValue(rawArrayComponentType, context, headerValue));
            } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(context, type), Iterable.class)) {
                Collection<Object> collection = (Collection<Object>)fieldInfo.getValue(this);
                if (collection == null) {
                    collection = Data.newCollectionInstance(type);
                    fieldInfo.setValue(this, collection);
                }
                Type subFieldType = type == Object.class ? null : Types.getIterableParameter(type);
                collection.add(HttpHeaders.parseValue(subFieldType, context, headerValue));
            } else {
                fieldInfo.setValue(this, HttpHeaders.parseValue(type, context, headerValue));
            }
        } else {
            ArrayList<String> listValue = (ArrayList<String>)this.get(headerName);
            if (listValue == null) {
                listValue = new ArrayList<String>();
                this.set(headerName, listValue);
            }
            listValue.add(headerValue);
        }
    }

    private static Object parseValue(Type valueType, List<Type> context, String value) {
        Type resolved = Data.resolveWildcardTypeOrTypeVariable(context, valueType);
        return Data.parsePrimitiveValue(resolved, value);
    }

    private static final class ParseHeaderState {
        final ArrayValueMap arrayValueMap;
        final StringBuilder logger;
        final ClassInfo classInfo;
        final List<Type> context;

        public ParseHeaderState(HttpHeaders headers, StringBuilder logger) {
            Class<?> clazz = headers.getClass();
            this.context = Arrays.asList(clazz);
            this.classInfo = ClassInfo.of(clazz, true);
            this.logger = logger;
            this.arrayValueMap = new ArrayValueMap(headers);
        }

        void finish() {
            this.arrayValueMap.setValues();
        }
    }

    private static class HeaderParsingFakeLevelHttpRequest
    extends LowLevelHttpRequest {
        private final HttpHeaders target;
        private final ParseHeaderState state;

        HeaderParsingFakeLevelHttpRequest(HttpHeaders target, ParseHeaderState state) {
            this.target = target;
            this.state = state;
        }

        public void addHeader(String name, String value) {
            this.target.parseHeader(name, value, this.state);
        }

        public LowLevelHttpResponse execute() throws IOException {
            throw new UnsupportedOperationException();
        }
    }
}

