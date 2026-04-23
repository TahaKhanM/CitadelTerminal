/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class NetHttpResponse
extends LowLevelHttpResponse {
    private final HttpURLConnection connection;
    private final int responseCode;
    private final String responseMessage;
    private final ArrayList<String> headerNames = new ArrayList();
    private final ArrayList<String> headerValues = new ArrayList();

    NetHttpResponse(HttpURLConnection connection) throws IOException {
        this.connection = connection;
        int responseCode = connection.getResponseCode();
        this.responseCode = responseCode == -1 ? 0 : responseCode;
        this.responseMessage = connection.getResponseMessage();
        ArrayList<String> headerNames = this.headerNames;
        ArrayList<String> headerValues = this.headerValues;
        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            String key = entry.getKey();
            if (key == null) continue;
            for (String value : entry.getValue()) {
                if (value == null) continue;
                headerNames.add(key);
                headerValues.add(value);
            }
        }
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public InputStream getContent() throws IOException {
        InputStream in = null;
        try {
            in = this.connection.getInputStream();
        }
        catch (IOException ioe) {
            in = this.connection.getErrorStream();
        }
        return in == null ? null : new SizeValidatingInputStream(in);
    }

    public String getContentEncoding() {
        return this.connection.getContentEncoding();
    }

    public long getContentLength() {
        String string2 = this.connection.getHeaderField("Content-Length");
        return string2 == null ? -1L : Long.parseLong(string2);
    }

    public String getContentType() {
        return this.connection.getHeaderField("Content-Type");
    }

    public String getReasonPhrase() {
        return this.responseMessage;
    }

    public String getStatusLine() {
        String result2 = this.connection.getHeaderField(0);
        return result2 != null && result2.startsWith("HTTP/1.") ? result2 : null;
    }

    public int getHeaderCount() {
        return this.headerNames.size();
    }

    public String getHeaderName(int index) {
        return this.headerNames.get(index);
    }

    public String getHeaderValue(int index) {
        return this.headerValues.get(index);
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    private final class SizeValidatingInputStream
    extends FilterInputStream {
        private long bytesRead;

        public SizeValidatingInputStream(InputStream in) {
            super(in);
            this.bytesRead = 0L;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = this.in.read(b, off, len);
            if (n == -1) {
                this.throwIfFalseEOF();
            } else {
                this.bytesRead += (long)n;
            }
            return n;
        }

        public int read() throws IOException {
            int n = this.in.read();
            if (n == -1) {
                this.throwIfFalseEOF();
            } else {
                ++this.bytesRead;
            }
            return n;
        }

        private void throwIfFalseEOF() throws IOException {
            long contentLength = NetHttpResponse.this.getContentLength();
            if (contentLength == -1L) {
                return;
            }
            if (this.bytesRead != 0L && this.bytesRead < contentLength) {
                throw new IOException("Connection closed prematurely: bytesRead = " + this.bytesRead + ", Content-Length = " + contentLength);
            }
        }
    }
}

