/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.http.javanet.NetHttpResponse;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

final class NetHttpRequest
extends LowLevelHttpRequest {
    private final HttpURLConnection connection;

    NetHttpRequest(HttpURLConnection connection) {
        this.connection = connection;
        connection.setInstanceFollowRedirects(false);
    }

    public void addHeader(String name, String value) {
        this.connection.addRequestProperty(name, value);
    }

    public void setTimeout(int connectTimeout, int readTimeout) {
        this.connection.setReadTimeout(readTimeout);
        this.connection.setConnectTimeout(connectTimeout);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public LowLevelHttpResponse execute() throws IOException {
        HttpURLConnection connection = this.connection;
        if (this.getStreamingContent() != null) {
            String requestMethod;
            long contentLength;
            String contentEncoding;
            String contentType = this.getContentType();
            if (contentType != null) {
                this.addHeader("Content-Type", contentType);
            }
            if ((contentEncoding = this.getContentEncoding()) != null) {
                this.addHeader("Content-Encoding", contentEncoding);
            }
            if ((contentLength = this.getContentLength()) >= 0L) {
                connection.setRequestProperty("Content-Length", Long.toString(contentLength));
            }
            if ("POST".equals(requestMethod = connection.getRequestMethod()) || "PUT".equals(requestMethod)) {
                connection.setDoOutput(true);
                if (contentLength >= 0L && contentLength <= Integer.MAX_VALUE) {
                    connection.setFixedLengthStreamingMode((int)contentLength);
                } else {
                    connection.setChunkedStreamingMode(0);
                }
                OutputStream out = connection.getOutputStream();
                boolean threw = true;
                try {
                    this.getStreamingContent().writeTo(out);
                    threw = false;
                }
                finally {
                    block18: {
                        try {
                            out.close();
                        }
                        catch (IOException exception) {
                            if (threw) break block18;
                            throw exception;
                        }
                    }
                }
            }
            Preconditions.checkArgument(contentLength == 0L, "%s with non-zero content length is not supported", requestMethod);
        }
        boolean successfulConnection = false;
        try {
            connection.connect();
            NetHttpResponse response = new NetHttpResponse(connection);
            successfulConnection = true;
            NetHttpResponse netHttpResponse = response;
            return netHttpResponse;
        }
        finally {
            if (!successfulConnection) {
                connection.disconnect();
            }
        }
    }
}

