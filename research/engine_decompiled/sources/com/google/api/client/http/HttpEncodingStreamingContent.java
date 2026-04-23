/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpEncoding;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public final class HttpEncodingStreamingContent
implements StreamingContent {
    private final StreamingContent content;
    private final HttpEncoding encoding;

    public HttpEncodingStreamingContent(StreamingContent content, HttpEncoding encoding) {
        this.content = Preconditions.checkNotNull(content);
        this.encoding = Preconditions.checkNotNull(encoding);
    }

    public void writeTo(OutputStream out) throws IOException {
        this.encoding.encode(this.content, out);
    }

    public StreamingContent getContent() {
        return this.content;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }
}

