/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

final class HttpExtensionMethod
extends HttpEntityEnclosingRequestBase {
    private final String methodName;

    public HttpExtensionMethod(String methodName, String uri) {
        this.methodName = Preconditions.checkNotNull(methodName);
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return this.methodName;
    }
}

