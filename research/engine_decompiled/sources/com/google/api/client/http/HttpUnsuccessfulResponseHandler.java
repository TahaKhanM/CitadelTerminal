/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;

public interface HttpUnsuccessfulResponseHandler {
    public boolean handleResponse(HttpRequest var1, HttpResponse var2, boolean var3) throws IOException;
}

