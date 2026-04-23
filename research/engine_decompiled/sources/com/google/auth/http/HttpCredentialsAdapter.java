/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.http;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.Preconditions;
import com.google.auth.Credentials;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class HttpCredentialsAdapter
implements HttpRequestInitializer,
HttpUnsuccessfulResponseHandler {
    private static final Logger LOGGER = Logger.getLogger(HttpCredentialsAdapter.class.getName());
    private static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");
    private final Credentials credentials;

    public HttpCredentialsAdapter(Credentials credentials) {
        Preconditions.checkNotNull(credentials);
        this.credentials = credentials;
    }

    @Override
    public void initialize(HttpRequest request) throws IOException {
        Map<String, List<String>> credentialHeaders;
        request.setUnsuccessfulResponseHandler(this);
        if (!this.credentials.hasRequestMetadata()) {
            return;
        }
        HttpHeaders requestHeaders = request.getHeaders();
        URI uri = null;
        if (request.getUrl() != null) {
            uri = request.getUrl().toURI();
        }
        if ((credentialHeaders = this.credentials.getRequestMetadata(uri)) == null) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : credentialHeaders.entrySet()) {
            String headerName = entry.getKey();
            ArrayList requestValues = new ArrayList();
            requestValues.addAll(entry.getValue());
            requestHeaders.put(headerName, (Object)requestValues);
        }
    }

    @Override
    public boolean handleResponse(HttpRequest request, HttpResponse response, boolean supportsRetry) {
        boolean refreshToken = false;
        boolean bearer = false;
        List<String> authenticateList = response.getHeaders().getAuthenticateAsList();
        if (authenticateList != null) {
            for (String authenticate : authenticateList) {
                if (!authenticate.startsWith("Bearer ")) continue;
                bearer = true;
                refreshToken = INVALID_TOKEN_ERROR.matcher(authenticate).find();
                break;
            }
        }
        if (!bearer) {
            boolean bl = refreshToken = response.getStatusCode() == 401;
        }
        if (refreshToken) {
            try {
                this.credentials.refresh();
                this.initialize(request);
                return true;
            }
            catch (IOException exception) {
                LOGGER.log(Level.SEVERE, "unable to refresh token", exception);
            }
        }
        return false;
    }
}

