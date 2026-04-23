/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.testing.http.javanet;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Beta
public class MockHttpURLConnection
extends HttpURLConnection {
    private boolean doOutputCalled;
    private OutputStream outputStream = new ByteArrayOutputStream(0);
    @Deprecated
    public static final byte[] INPUT_BUF = new byte[1];
    @Deprecated
    public static final byte[] ERROR_BUF = new byte[5];
    private InputStream inputStream = null;
    private InputStream errorStream = null;
    private Map<String, List<String>> headers = new LinkedHashMap<String, List<String>>();

    public MockHttpURLConnection(URL u) {
        super(u);
    }

    @Override
    public void disconnect() {
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public int getResponseCode() throws IOException {
        return this.responseCode;
    }

    @Override
    public void setDoOutput(boolean dooutput) {
        this.doOutputCalled = true;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (this.outputStream != null) {
            return this.outputStream;
        }
        return super.getOutputStream();
    }

    public final boolean doOutputCalled() {
        return this.doOutputCalled;
    }

    public MockHttpURLConnection setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public MockHttpURLConnection setResponseCode(int responseCode) {
        Preconditions.checkArgument(responseCode >= -1);
        this.responseCode = responseCode;
        return this;
    }

    public MockHttpURLConnection addHeader(String name, String value) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(value);
        if (this.headers.containsKey(name)) {
            this.headers.get(name).add(value);
        } else {
            ArrayList<String> values = new ArrayList<String>();
            values.add(value);
            this.headers.put(name, values);
        }
        return this;
    }

    public MockHttpURLConnection setInputStream(InputStream is) {
        Preconditions.checkNotNull(is);
        if (this.inputStream == null) {
            this.inputStream = is;
        }
        return this;
    }

    public MockHttpURLConnection setErrorStream(InputStream is) {
        Preconditions.checkNotNull(is);
        if (this.errorStream == null) {
            this.errorStream = is;
        }
        return this;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (this.responseCode < 400) {
            return this.inputStream;
        }
        throw new IOException();
    }

    @Override
    public InputStream getErrorStream() {
        return this.errorStream;
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        return this.headers;
    }

    @Override
    public String getHeaderField(String name) {
        List<String> values = this.headers.get(name);
        return values == null ? null : values.get(0);
    }
}

