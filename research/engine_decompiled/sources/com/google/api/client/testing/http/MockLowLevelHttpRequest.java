/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.testing.http;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Beta
public class MockLowLevelHttpRequest
extends LowLevelHttpRequest {
    private String url;
    private final Map<String, List<String>> headersMap = new HashMap<String, List<String>>();
    private MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();

    public MockLowLevelHttpRequest() {
    }

    public MockLowLevelHttpRequest(String url) {
        this.url = url;
    }

    @Override
    public void addHeader(String name, String value) throws IOException {
        List<String> values = this.headersMap.get(name = name.toLowerCase());
        if (values == null) {
            values = new ArrayList<String>();
            this.headersMap.put(name, values);
        }
        values.add(value);
    }

    @Override
    public LowLevelHttpResponse execute() throws IOException {
        return this.response;
    }

    public String getUrl() {
        return this.url;
    }

    public Map<String, List<String>> getHeaders() {
        return Collections.unmodifiableMap(this.headersMap);
    }

    public String getFirstHeaderValue(String name) {
        List<String> values = this.headersMap.get(name.toLowerCase());
        return values == null ? null : values.get(0);
    }

    public List<String> getHeaderValues(String name) {
        List<String> values = this.headersMap.get(name.toLowerCase());
        return values == null ? Collections.emptyList() : Collections.unmodifiableList(values);
    }

    public MockLowLevelHttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContentAsString() throws IOException {
        String contentType;
        if (this.getStreamingContent() == null) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.getStreamingContent().writeTo(out);
        String contentEncoding = this.getContentEncoding();
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            GZIPInputStream contentInputStream = new GZIPInputStream(new ByteArrayInputStream(out.toByteArray()));
            out = new ByteArrayOutputStream();
            IOUtils.copy(contentInputStream, out);
        }
        HttpMediaType mediaType = (contentType = this.getContentType()) != null ? new HttpMediaType(contentType) : null;
        Charset charset = mediaType == null || mediaType.getCharsetParameter() == null ? Charsets.ISO_8859_1 : mediaType.getCharsetParameter();
        return out.toString(charset.name());
    }

    public MockLowLevelHttpResponse getResponse() {
        return this.response;
    }

    public MockLowLevelHttpRequest setResponse(MockLowLevelHttpResponse response) {
        this.response = response;
        return this;
    }
}

