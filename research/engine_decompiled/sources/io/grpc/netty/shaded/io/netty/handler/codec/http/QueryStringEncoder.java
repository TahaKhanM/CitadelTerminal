/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class QueryStringEncoder {
    private final String charsetName;
    private final StringBuilder uriBuilder;
    private boolean hasParams;

    public QueryStringEncoder(String uri) {
        this(uri, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringEncoder(String uri, Charset charset) {
        this.uriBuilder = new StringBuilder(uri);
        this.charsetName = charset.name();
    }

    public void addParam(String name, String value) {
        ObjectUtil.checkNotNull(name, "name");
        if (this.hasParams) {
            this.uriBuilder.append('&');
        } else {
            this.uriBuilder.append('?');
            this.hasParams = true;
        }
        QueryStringEncoder.appendComponent(name, this.charsetName, this.uriBuilder);
        if (value != null) {
            this.uriBuilder.append('=');
            QueryStringEncoder.appendComponent(value, this.charsetName, this.uriBuilder);
        }
    }

    public URI toUri() throws URISyntaxException {
        return new URI(this.toString());
    }

    public String toString() {
        return this.uriBuilder.toString();
    }

    private static void appendComponent(String s2, String charset, StringBuilder sb) {
        try {
            s2 = URLEncoder.encode(s2, charset);
        }
        catch (UnsupportedEncodingException ignored) {
            throw new UnsupportedCharsetException(charset);
        }
        int idx = s2.indexOf(43);
        if (idx == -1) {
            sb.append(s2);
            return;
        }
        sb.append(s2, 0, idx).append("%20");
        int size2 = s2.length();
        ++idx;
        while (idx < size2) {
            char c = s2.charAt(idx);
            if (c != '+') {
                sb.append(c);
            } else {
                sb.append("%20");
            }
            ++idx;
        }
    }
}

