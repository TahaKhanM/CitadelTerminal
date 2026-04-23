/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.common.base.MoreObjects;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class UserCredentials
extends GoogleCredentials {
    private static final String GRANT_TYPE = "refresh_token";
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    private static final long serialVersionUID = -4800758775038679176L;
    private final String clientId;
    private final String clientSecret;
    private final String refreshToken;
    private final URI tokenServerUri;
    private final String transportFactoryClassName;
    private transient HttpTransportFactory transportFactory;

    @Deprecated
    public UserCredentials(String clientId, String clientSecret, String refreshToken) {
        this(clientId, clientSecret, refreshToken, null, null, null);
    }

    @Deprecated
    public UserCredentials(String clientId, String clientSecret, String refreshToken, AccessToken accessToken) {
        this(clientId, clientSecret, refreshToken, accessToken, null, null);
    }

    @Deprecated
    public UserCredentials(String clientId, String clientSecret, String refreshToken, AccessToken accessToken, HttpTransportFactory transportFactory, URI tokenServerUri) {
        super(accessToken);
        this.clientId = Preconditions.checkNotNull(clientId);
        this.clientSecret = Preconditions.checkNotNull(clientSecret);
        this.refreshToken = refreshToken;
        this.transportFactory = MoreObjects.firstNonNull(transportFactory, UserCredentials.getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.tokenServerUri = tokenServerUri == null ? OAuth2Utils.TOKEN_SERVER_URI : tokenServerUri;
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
        Preconditions.checkState(accessToken != null || refreshToken != null, "Either accessToken or refreshToken must not be null");
    }

    static UserCredentials fromJson(Map<String, Object> json, HttpTransportFactory transportFactory) throws IOException {
        String clientId = (String)json.get("client_id");
        String clientSecret = (String)json.get("client_secret");
        String refreshToken = (String)json.get(GRANT_TYPE);
        if (clientId == null || clientSecret == null || refreshToken == null) {
            throw new IOException("Error reading user credential from JSON,  expecting 'client_id', 'client_secret' and 'refresh_token'.");
        }
        return UserCredentials.newBuilder().setClientId(clientId).setClientSecret(clientSecret).setRefreshToken(refreshToken).setAccessToken(null).setHttpTransportFactory(transportFactory).setTokenServerUri(null).build();
    }

    public static UserCredentials fromStream(InputStream credentialsStream) throws IOException {
        return UserCredentials.fromStream(credentialsStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static UserCredentials fromStream(InputStream credentialsStream, HttpTransportFactory transportFactory) throws IOException {
        Preconditions.checkNotNull(credentialsStream);
        Preconditions.checkNotNull(transportFactory);
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        JsonObjectParser parser = new JsonObjectParser(jsonFactory);
        GenericJson fileContents = parser.parseAndClose(credentialsStream, OAuth2Utils.UTF_8, GenericJson.class);
        String fileType = (String)fileContents.get("type");
        if (fileType == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if ("authorized_user".equals(fileType)) {
            return UserCredentials.fromJson(fileContents, transportFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s'.", fileType, "authorized_user"));
    }

    @Override
    public AccessToken refreshAccessToken() throws IOException {
        if (this.refreshToken == null) {
            throw new IllegalStateException("UserCredentials instance cannot refresh because there is no refresh token.");
        }
        GenericData tokenRequest = new GenericData();
        tokenRequest.set("client_id", this.clientId);
        tokenRequest.set("client_secret", this.clientSecret);
        tokenRequest.set(GRANT_TYPE, this.refreshToken);
        tokenRequest.set("grant_type", GRANT_TYPE);
        UrlEncodedContent content = new UrlEncodedContent(tokenRequest);
        HttpRequestFactory requestFactory = this.transportFactory.create().createRequestFactory();
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(this.tokenServerUri), content);
        request.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        HttpResponse response = request.execute();
        GenericData responseData = response.parseAs(GenericData.class);
        String accessToken = OAuth2Utils.validateString(responseData, "access_token", PARSE_ERROR_PREFIX);
        int expiresInSeconds = OAuth2Utils.validateInt32(responseData, "expires_in", PARSE_ERROR_PREFIX);
        long expiresAtMilliseconds = this.clock.currentTimeMillis() + (long)(expiresInSeconds * 1000);
        return new AccessToken(accessToken, new Date(expiresAtMilliseconds));
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    private InputStream getUserCredentialsStream() throws IOException {
        GenericJson json = new GenericJson();
        json.put("type", (Object)"authorized_user");
        if (this.refreshToken != null) {
            json.put(GRANT_TYPE, (Object)this.refreshToken);
        }
        if (this.tokenServerUri != null) {
            json.put("token_server_uri", (Object)this.tokenServerUri);
        }
        if (this.clientId != null) {
            json.put("client_id", (Object)this.clientId);
        }
        if (this.clientSecret != null) {
            json.put("client_secret", (Object)this.clientSecret);
        }
        json.setFactory(OAuth2Utils.JSON_FACTORY);
        String text = json.toPrettyString();
        return new ByteArrayInputStream(text.getBytes(OAuth2Utils.UTF_8));
    }

    public void save(String filePath) throws IOException {
        OAuth2Utils.writeInputStreamToFile(this.getUserCredentialsStream(), filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.clientId, this.clientSecret, this.refreshToken, this.tokenServerUri, this.transportFactoryClassName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("requestMetadata", this.getRequestMetadataInternal()).add("temporaryAccess", this.getAccessToken()).add("clientId", this.clientId).add("refreshToken", this.refreshToken).add("tokenServerUri", this.tokenServerUri).add("transportFactoryClassName", this.transportFactoryClassName).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserCredentials)) {
            return false;
        }
        UserCredentials other = (UserCredentials)obj;
        return super.equals(other) && Objects.equals(this.clientId, other.clientId) && Objects.equals(this.clientSecret, other.clientSecret) && Objects.equals(this.refreshToken, other.refreshToken) && Objects.equals(this.tokenServerUri, other.tokenServerUri) && Objects.equals(this.transportFactoryClassName, other.transportFactoryClassName);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.transportFactory = (HttpTransportFactory)UserCredentials.newInstance(this.transportFactoryClassName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder
    extends GoogleCredentials.Builder {
        private String clientId;
        private String clientSecret;
        private String refreshToken;
        private URI tokenServerUri;
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(UserCredentials credentials) {
            this.clientId = credentials.clientId;
            this.clientSecret = credentials.clientSecret;
            this.refreshToken = credentials.refreshToken;
            this.transportFactory = credentials.transportFactory;
            this.tokenServerUri = credentials.tokenServerUri;
        }

        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder setTokenServerUri(URI tokenServerUri) {
            this.tokenServerUri = tokenServerUri;
            return this;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory transportFactory) {
            this.transportFactory = transportFactory;
            return this;
        }

        @Override
        public Builder setAccessToken(AccessToken token) {
            super.setAccessToken(token);
            return this;
        }

        public String getClientId() {
            return this.clientId;
        }

        public String getClientSecret() {
            return this.clientSecret;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        public URI getTokenServerUri() {
            return this.tokenServerUri;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        @Override
        public UserCredentials build() {
            return new UserCredentials(this.clientId, this.clientSecret, this.refreshToken, this.getAccessToken(), this.transportFactory, this.tokenServerUri);
        }
    }
}

