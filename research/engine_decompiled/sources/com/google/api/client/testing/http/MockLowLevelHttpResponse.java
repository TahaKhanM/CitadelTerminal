/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.testing.http;

import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.util.TestableByteArrayInputStream;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Beta
public class MockLowLevelHttpResponse
extends LowLevelHttpResponse {
    private InputStream content;
    private String contentType;
    private int statusCode = 200;
    private String reasonPhrase;
    private List<String> headerNames = new ArrayList<String>();
    private List<String> headerValues = new ArrayList<String>();
    private String contentEncoding;
    private long contentLength = -1L;
    private boolean isDisconnected;

    public MockLowLevelHttpResponse addHeader(String name, String value) {
        this.headerNames.add(Preconditions.checkNotNull(name));
        this.headerValues.add(Preconditions.checkNotNull(value));
        return this;
    }

    public MockLowLevelHttpResponse setContent(String stringContent) {
        return stringContent == null ? this.setZeroContent() : this.setContent(StringUtils.getBytesUtf8(stringContent));
    }

    public MockLowLevelHttpResponse setContent(byte[] byteContent) {
        if (byteContent == null) {
            return this.setZeroContent();
        }
        this.content = new TestableByteArrayInputStream(byteContent);
        this.setContentLength(byteContent.length);
        return this;
    }

    public MockLowLevelHttpResponse setZeroContent() {
        this.content = null;
        this.setContentLength(0L);
        return this;
    }

    @Override
    public InputStream getContent() throws IOException {
        return this.content;
    }

    @Override
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    @Override
    public long getContentLength() {
        return this.contentLength;
    }

    @Override
    public final String getContentType() {
        return this.contentType;
    }

    @Override
    public int getHeaderCount() {
        return this.headerNames.size();
    }

    @Override
    public String getHeaderName(int index) {
        return this.headerNames.get(index);
    }

    @Override
    public String getHeaderValue(int index) {
        return this.headerValues.get(index);
    }

    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String getStatusLine() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.statusCode);
        if (this.reasonPhrase != null) {
            buf.append(this.reasonPhrase);
        }
        return buf.toString();
    }

    public final List<String> getHeaderNames() {
        return this.headerNames;
    }

    public MockLowLevelHttpResponse setHeaderNames(List<String> headerNames) {
        this.headerNames = Preconditions.checkNotNull(headerNames);
        return this;
    }

    public final List<String> getHeaderValues() {
        return this.headerValues;
    }

    public MockLowLevelHttpResponse setHeaderValues(List<String> headerValues) {
        this.headerValues = Preconditions.checkNotNull(headerValues);
        return this;
    }

    public MockLowLevelHttpResponse setContent(InputStream content) {
        this.content = content;
        return this;
    }

    public MockLowLevelHttpResponse setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public MockLowLevelHttpResponse setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
        return this;
    }

    public MockLowLevelHttpResponse setContentLength(long contentLength) {
        this.contentLength = contentLength;
        Preconditions.checkArgument(contentLength >= -1L);
        return this;
    }

    public MockLowLevelHttpResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public MockLowLevelHttpResponse setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    @Override
    public void disconnect() throws IOException {
        this.isDisconnected = true;
        super.disconnect();
    }

    public boolean isDisconnected() {
        return this.isDisconnected;
    }
}

