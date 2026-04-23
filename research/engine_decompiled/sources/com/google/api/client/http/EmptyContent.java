/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpContent;
import java.io.IOException;
import java.io.OutputStream;

public class EmptyContent
implements HttpContent {
    public long getLength() throws IOException {
        return 0L;
    }

    public String getType() {
        return null;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.flush();
    }

    public boolean retrySupported() {
        return true;
    }
}

