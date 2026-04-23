/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessageUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

public class DefaultHttpRequest
extends DefaultHttpMessage
implements HttpRequest {
    private static final int HASH_CODE_PRIME = 31;
    private HttpMethod method;
    private String uri;

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
        this(httpVersion, method, uri, true);
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, boolean validateHeaders) {
        super(httpVersion, validateHeaders, false);
        this.method = ObjectUtil.checkNotNull(method, "method");
        this.uri = ObjectUtil.checkNotNull(uri, "uri");
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, HttpHeaders headers) {
        super(httpVersion, headers);
        this.method = ObjectUtil.checkNotNull(method, "method");
        this.uri = ObjectUtil.checkNotNull(uri, "uri");
    }

    @Override
    @Deprecated
    public HttpMethod getMethod() {
        return this.method();
    }

    @Override
    public HttpMethod method() {
        return this.method;
    }

    @Override
    @Deprecated
    public String getUri() {
        return this.uri();
    }

    @Override
    public String uri() {
        return this.uri;
    }

    @Override
    public HttpRequest setMethod(HttpMethod method) {
        if (method == null) {
            throw new NullPointerException("method");
        }
        this.method = method;
        return this;
    }

    @Override
    public HttpRequest setUri(String uri) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        this.uri = uri;
        return this;
    }

    @Override
    public HttpRequest setProtocolVersion(HttpVersion version) {
        super.setProtocolVersion(version);
        return this;
    }

    @Override
    public int hashCode() {
        int result2 = 1;
        result2 = 31 * result2 + this.method.hashCode();
        result2 = 31 * result2 + this.uri.hashCode();
        result2 = 31 * result2 + super.hashCode();
        return result2;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DefaultHttpRequest)) {
            return false;
        }
        DefaultHttpRequest other = (DefaultHttpRequest)o;
        return this.method().equals(other.method()) && this.uri().equalsIgnoreCase(other.uri()) && super.equals(o);
    }

    public String toString() {
        return HttpMessageUtil.appendRequest(new StringBuilder(256), this).toString();
    }
}

