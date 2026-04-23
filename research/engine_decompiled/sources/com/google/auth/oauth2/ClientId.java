/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.auth.oauth2.OAuth2Utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ClientId {
    private static final String FIELD_TYPE_INSTALLED = "installed";
    private static final String FIELD_TYPE_WEB = "web";
    private static final String FIELD_CLIENT_ID = "client_id";
    private static final String FIELD_CLIENT_SECRET = "client_secret";
    private static final String JSON_PARSE_ERROR = "Error parsing Client ID JSON: ";
    private final String clientId;
    private final String clientSecret;

    public static ClientId of(String clientId, String clientSecret) {
        return new ClientId(clientId, clientSecret);
    }

    public static ClientId fromJson(Map<String, Object> json) throws IOException {
        Object rawDetail = null;
        rawDetail = json.get(FIELD_TYPE_INSTALLED);
        if (rawDetail == null) {
            rawDetail = json.get(FIELD_TYPE_WEB);
        }
        if (rawDetail == null || !(rawDetail instanceof Map)) {
            throw new IOException("Unable to parse Client ID JSON. Expecting top-level field 'web' or 'installed' of collection type");
        }
        Map detail = (Map)rawDetail;
        String clientId = OAuth2Utils.validateString(detail, FIELD_CLIENT_ID, JSON_PARSE_ERROR);
        if (clientId == null || clientId.length() == 0) {
            throw new IOException("Unable to parse ClientId. Field 'client_id' is required.");
        }
        String clientSecret = OAuth2Utils.validateOptionalString(detail, FIELD_CLIENT_SECRET, JSON_PARSE_ERROR);
        return new ClientId(clientId, clientSecret);
    }

    public static ClientId fromResource(Class<?> relativeClass, String resourceName) throws IOException {
        InputStream stream = relativeClass.getResourceAsStream(resourceName);
        return ClientId.fromStream(stream);
    }

    public static ClientId fromStream(InputStream stream) throws IOException {
        Preconditions.checkNotNull(stream);
        JsonObjectParser parser = new JsonObjectParser(OAuth2Utils.JSON_FACTORY);
        GenericJson parsedJson = parser.parseAndClose(stream, StandardCharsets.UTF_8, GenericJson.class);
        return ClientId.fromJson(parsedJson);
    }

    @Deprecated
    public ClientId(String clientId, String clientSecret) {
        this.clientId = Preconditions.checkNotNull(clientId);
        this.clientSecret = clientSecret;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;

        protected Builder() {
        }

        protected Builder(ClientId clientId) {
            this.clientId = clientId.getClientId();
            this.clientSecret = clientId.getClientSecret();
        }

        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public String getClientSecret() {
            return this.clientSecret;
        }

        public ClientId build() {
            return new ClientId(this.clientId, this.clientSecret);
        }
    }
}

