/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.HttpEncodingStreamingContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultipartContent
extends AbstractHttpContent {
    static final String NEWLINE = "\r\n";
    private static final String TWO_DASHES = "--";
    private ArrayList<Part> parts = new ArrayList();

    public MultipartContent() {
        super(new HttpMediaType("multipart/related").setParameter("boundary", "__END_OF_PART__"));
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(out, this.getCharset());
        String boundary = this.getBoundary();
        for (Part part : this.parts) {
            HttpHeaders headers = new HttpHeaders().setAcceptEncoding(null);
            if (part.headers != null) {
                headers.fromHttpHeaders(part.headers);
            }
            headers.setContentEncoding(null).setUserAgent(null).setContentType(null).setContentLength(null).set("Content-Transfer-Encoding", null);
            HttpContent content = part.content;
            StreamingContent streamingContent = null;
            if (content != null) {
                long contentLength;
                headers.set("Content-Transfer-Encoding", Arrays.asList("binary"));
                headers.setContentType(content.getType());
                HttpEncoding encoding = part.encoding;
                if (encoding == null) {
                    contentLength = content.getLength();
                    streamingContent = content;
                } else {
                    headers.setContentEncoding(encoding.getName());
                    streamingContent = new HttpEncodingStreamingContent(content, encoding);
                    contentLength = AbstractHttpContent.computeLength(content);
                }
                if (contentLength != -1L) {
                    headers.setContentLength(contentLength);
                }
            }
            writer.write(TWO_DASHES);
            writer.write(boundary);
            writer.write(NEWLINE);
            HttpHeaders.serializeHeadersForMultipartRequests(headers, null, null, writer);
            if (streamingContent != null) {
                writer.write(NEWLINE);
                ((Writer)writer).flush();
                streamingContent.writeTo(out);
            }
            writer.write(NEWLINE);
        }
        writer.write(TWO_DASHES);
        writer.write(boundary);
        writer.write(TWO_DASHES);
        writer.write(NEWLINE);
        ((Writer)writer).flush();
    }

    @Override
    public boolean retrySupported() {
        for (Part part : this.parts) {
            if (part.content.retrySupported()) continue;
            return false;
        }
        return true;
    }

    @Override
    public MultipartContent setMediaType(HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }

    public final Collection<Part> getParts() {
        return Collections.unmodifiableCollection(this.parts);
    }

    public MultipartContent addPart(Part part) {
        this.parts.add(Preconditions.checkNotNull(part));
        return this;
    }

    public MultipartContent setParts(Collection<Part> parts) {
        this.parts = new ArrayList<Part>(parts);
        return this;
    }

    public MultipartContent setContentParts(Collection<? extends HttpContent> contentParts) {
        this.parts = new ArrayList(contentParts.size());
        for (HttpContent httpContent : contentParts) {
            this.addPart(new Part(httpContent));
        }
        return this;
    }

    public final String getBoundary() {
        return this.getMediaType().getParameter("boundary");
    }

    public MultipartContent setBoundary(String boundary) {
        this.getMediaType().setParameter("boundary", Preconditions.checkNotNull(boundary));
        return this;
    }

    public static final class Part {
        HttpContent content;
        HttpHeaders headers;
        HttpEncoding encoding;

        public Part() {
            this(null);
        }

        public Part(HttpContent content) {
            this(null, content);
        }

        public Part(HttpHeaders headers, HttpContent content) {
            this.setHeaders(headers);
            this.setContent(content);
        }

        public Part setContent(HttpContent content) {
            this.content = content;
            return this;
        }

        public HttpContent getContent() {
            return this.content;
        }

        public Part setHeaders(HttpHeaders headers) {
            this.headers = headers;
            return this;
        }

        public HttpHeaders getHeaders() {
            return this.headers;
        }

        public Part setEncoding(HttpEncoding encoding) {
            this.encoding = encoding;
            return this;
        }

        public HttpEncoding getEncoding() {
            return this.encoding;
        }
    }
}

