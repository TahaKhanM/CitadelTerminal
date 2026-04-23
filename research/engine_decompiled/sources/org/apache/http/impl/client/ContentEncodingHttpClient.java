/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.client;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class ContentEncodingHttpClient
extends DefaultHttpClient {
    public ContentEncodingHttpClient(ClientConnectionManager conman, HttpParams params2) {
        super(conman, params2);
    }

    public ContentEncodingHttpClient(HttpParams params2) {
        this(null, params2);
    }

    public ContentEncodingHttpClient() {
        this((HttpParams)null);
    }

    @Override
    protected BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor result2 = super.createHttpProcessor();
        result2.addRequestInterceptor(new RequestAcceptEncoding());
        result2.addResponseInterceptor(new ResponseContentEncoding());
        return result2;
    }
}

