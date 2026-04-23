/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth;

import com.google.auth.RequestMetadataCallback;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public abstract class Credentials
implements Serializable {
    private static final long serialVersionUID = 808575179767517313L;

    public abstract String getAuthenticationType();

    public Map<String, List<String>> getRequestMetadata() throws IOException {
        return this.getRequestMetadata(null);
    }

    public void getRequestMetadata(final URI uri, Executor executor, final RequestMetadataCallback callback) {
        executor.execute(new Runnable(){

            @Override
            public void run() {
                Credentials.this.blockingGetToCallback(uri, callback);
            }
        });
    }

    protected final void blockingGetToCallback(URI uri, RequestMetadataCallback callback) {
        Map<String, List<String>> result2;
        try {
            result2 = this.getRequestMetadata(uri);
        }
        catch (Throwable e) {
            callback.onFailure(e);
            return;
        }
        callback.onSuccess(result2);
    }

    public abstract Map<String, List<String>> getRequestMetadata(URI var1) throws IOException;

    public abstract boolean hasRequestMetadata();

    public abstract boolean hasRequestMetadataOnly();

    public abstract void refresh() throws IOException;
}

