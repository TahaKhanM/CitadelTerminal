/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.DefaultCredentialsProvider;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.UserCredentials;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class GoogleCredentials
extends OAuth2Credentials {
    private static final long serialVersionUID = -1522852442442473691L;
    static final String USER_FILE_TYPE = "authorized_user";
    static final String SERVICE_ACCOUNT_FILE_TYPE = "service_account";
    private static final DefaultCredentialsProvider defaultCredentialsProvider = new DefaultCredentialsProvider();

    @Deprecated
    public static GoogleCredentials of(AccessToken accessToken) {
        return GoogleCredentials.create(accessToken);
    }

    public static GoogleCredentials create(AccessToken accessToken) {
        return GoogleCredentials.newBuilder().setAccessToken(accessToken).build();
    }

    public static GoogleCredentials getApplicationDefault() throws IOException {
        return GoogleCredentials.getApplicationDefault(OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static GoogleCredentials getApplicationDefault(HttpTransportFactory transportFactory) throws IOException {
        Preconditions.checkNotNull(transportFactory);
        return defaultCredentialsProvider.getDefaultCredentials(transportFactory);
    }

    public static GoogleCredentials fromStream(InputStream credentialsStream) throws IOException {
        return GoogleCredentials.fromStream(credentialsStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static GoogleCredentials fromStream(InputStream credentialsStream, HttpTransportFactory transportFactory) throws IOException {
        Preconditions.checkNotNull(credentialsStream);
        Preconditions.checkNotNull(transportFactory);
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        JsonObjectParser parser = new JsonObjectParser(jsonFactory);
        GenericJson fileContents = parser.parseAndClose(credentialsStream, OAuth2Utils.UTF_8, GenericJson.class);
        String fileType = (String)fileContents.get("type");
        if (fileType == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if (USER_FILE_TYPE.equals(fileType)) {
            return UserCredentials.fromJson(fileContents, transportFactory);
        }
        if (SERVICE_ACCOUNT_FILE_TYPE.equals(fileType)) {
            return ServiceAccountCredentials.fromJson(fileContents, transportFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s' or '%s'.", fileType, USER_FILE_TYPE, SERVICE_ACCOUNT_FILE_TYPE));
    }

    protected GoogleCredentials() {
        this(null);
    }

    @Deprecated
    public GoogleCredentials(AccessToken accessToken) {
        super(accessToken);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public boolean createScopedRequired() {
        return false;
    }

    public GoogleCredentials createScoped(Collection<String> scopes) {
        return this;
    }

    public GoogleCredentials createDelegated(String user) {
        return this;
    }

    public static class Builder
    extends OAuth2Credentials.Builder {
        protected Builder() {
        }

        protected Builder(GoogleCredentials credentials) {
            this.setAccessToken(credentials.getAccessToken());
        }

        @Override
        public GoogleCredentials build() {
            return new GoogleCredentials(this.getAccessToken());
        }

        @Override
        public Builder setAccessToken(AccessToken token) {
            super.setAccessToken(token);
            return this;
        }
    }
}

