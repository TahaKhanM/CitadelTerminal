/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.params;

import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public abstract class HttpAbstractParamBean {
    protected final HttpParams params;

    public HttpAbstractParamBean(HttpParams params2) {
        this.params = Args.notNull(params2, "HTTP parameters");
    }
}

