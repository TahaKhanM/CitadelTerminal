/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public interface HttpIOExceptionHandler {
    public boolean handleIOException(HttpRequest var1, boolean var2) throws IOException;
}

