/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.BackOff;
import com.google.api.client.util.BackOffUtils;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;

@Beta
public class HttpBackOffUnsuccessfulResponseHandler
implements HttpUnsuccessfulResponseHandler {
    private final BackOff backOff;
    private BackOffRequired backOffRequired = BackOffRequired.ON_SERVER_ERROR;
    private Sleeper sleeper = Sleeper.DEFAULT;

    public HttpBackOffUnsuccessfulResponseHandler(BackOff backOff) {
        this.backOff = Preconditions.checkNotNull(backOff);
    }

    public final BackOff getBackOff() {
        return this.backOff;
    }

    public final BackOffRequired getBackOffRequired() {
        return this.backOffRequired;
    }

    public HttpBackOffUnsuccessfulResponseHandler setBackOffRequired(BackOffRequired backOffRequired) {
        this.backOffRequired = Preconditions.checkNotNull(backOffRequired);
        return this;
    }

    public final Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpBackOffUnsuccessfulResponseHandler setSleeper(Sleeper sleeper) {
        this.sleeper = Preconditions.checkNotNull(sleeper);
        return this;
    }

    public final boolean handleResponse(HttpRequest request, HttpResponse response, boolean supportsRetry) throws IOException {
        if (!supportsRetry) {
            return false;
        }
        if (this.backOffRequired.isRequired(response)) {
            try {
                return BackOffUtils.next(this.sleeper, this.backOff);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
        return false;
    }

    @Beta
    public static interface BackOffRequired {
        public static final BackOffRequired ALWAYS = new BackOffRequired(){

            public boolean isRequired(HttpResponse response) {
                return true;
            }
        };
        public static final BackOffRequired ON_SERVER_ERROR = new BackOffRequired(){

            public boolean isRequired(HttpResponse response) {
                return response.getStatusCode() / 100 == 5;
            }
        };

        public boolean isRequired(HttpResponse var1);
    }
}

