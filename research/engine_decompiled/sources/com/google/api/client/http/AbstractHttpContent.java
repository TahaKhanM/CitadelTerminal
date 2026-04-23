/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractHttpContent
implements HttpContent {
    private HttpMediaType mediaType;
    private long computedLength = -1L;

    protected AbstractHttpContent(String mediaType) {
        this(mediaType == null ? null : new HttpMediaType(mediaType));
    }

    protected AbstractHttpContent(HttpMediaType mediaType) {
        this.mediaType = mediaType;
    }

    public long getLength() throws IOException {
        if (this.computedLength == -1L) {
            this.computedLength = this.computeLength();
        }
        return this.computedLength;
    }

    public final HttpMediaType getMediaType() {
        return this.mediaType;
    }

    public AbstractHttpContent setMediaType(HttpMediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    protected final Charset getCharset() {
        return this.mediaType == null || this.mediaType.getCharsetParameter() == null ? Charsets.UTF_8 : this.mediaType.getCharsetParameter();
    }

    public String getType() {
        return this.mediaType == null ? null : this.mediaType.build();
    }

    protected long computeLength() throws IOException {
        return AbstractHttpContent.computeLength(this);
    }

    public boolean retrySupported() {
        return true;
    }

    public static long computeLength(HttpContent content) throws IOException {
        if (!content.retrySupported()) {
            return -1L;
        }
        return IOUtils.computeLength(content);
    }
}

