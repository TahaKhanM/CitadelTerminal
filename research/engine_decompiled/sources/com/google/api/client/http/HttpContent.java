/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public interface HttpContent
extends StreamingContent {
    public long getLength() throws IOException;

    public String getType();

    public boolean retrySupported();

    public void writeTo(OutputStream var1) throws IOException;
}

