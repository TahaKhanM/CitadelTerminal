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
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.ClientId;
import com.google.auth.oauth2.MemoryTokensStorage;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.auth.oauth2.TokenStore;
import com.google.auth.oauth2.UserCredentials;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

public class UserAuthorizer {
    static final URI DEFAULT_CALLBACK_URI = URI.create("/oauth2callback");
    private final String TOKEN_STORE_ERROR = "Error parsing stored token data.";
    private final String FETCH_TOKEN_ERROR = "Error reading result of Token API:";
    private final ClientId clientId;
    private final Collection<String> scopes;
    private final TokenStore tokenStore;
    private final URI callbackUri;
    private final HttpTransportFactory transportFactory;
    private final URI tokenServerUri;
    private final URI userAuthUri;

    @Deprecated
    public UserAuthorizer(ClientId clientId, Collection<String> scopes, TokenStore tokenStore) {
        this(clientId, scopes, tokenStore, null, null, null, null);
    }

    @Deprecated
    public UserAuthorizer(ClientId clientId, Collection<String> scopes, TokenStore tokenStore, URI callbackUri) {
        this(clientId, scopes, tokenStore, callbackUri, null, null, null);
    }

    @Deprecated
    public UserAuthorizer(ClientId clientId, Collection<String> scopes, TokenStore tokenStore, URI callbackUri, HttpTransportFactory transportFactory, URI tokenServerUri, URI userAuthUri) {
        this.clientId = Preconditions.checkNotNull(clientId);
        this.scopes = ImmutableList.copyOf(Preconditions.checkNotNull(scopes));
        this.callbackUri = callbackUri == null ? DEFAULT_CALLBACK_URI : callbackUri;
        this.transportFactory = transportFactory == null ? OAuth2Utils.HTTP_TRANSPORT_FACTORY : transportFactory;
        this.tokenServerUri = tokenServerUri == null ? OAuth2Utils.TOKEN_SERVER_URI : tokenServerUri;
        this.userAuthUri = userAuthUri == null ? OAuth2Utils.USER_AUTH_URI : userAuthUri;
        this.tokenStore = tokenStore == null ? new MemoryTokensStorage() : tokenStore;
    }

    public ClientId getClientId() {
        return this.clientId;
    }

    public Collection<String> getScopes() {
        return this.scopes;
    }

    public URI getCallbackUri() {
        return this.callbackUri;
    }

    public URI getCallbackUri(URI baseUri) {
        if (this.callbackUri.isAbsolute()) {
            return this.callbackUri;
        }
        if (baseUri == null || !baseUri.isAbsolute()) {
            throw new IllegalStateException("If the callback URI is relative, the baseUri passed must be an absolute URI");
        }
        return baseUri.resolve(this.callbackUri);
    }

    public TokenStore getTokenStore() {
        return this.tokenStore;
    }

    public URL getAuthorizationUrl(String userId, String state, URI baseUri) {
        URI resolvedCallbackUri = this.getCallbackUri(baseUri);
        String scopesString = Joiner.on(' ').join(this.scopes);
        GenericUrl url = new GenericUrl(this.userAuthUri);
        url.put("response_type", (Object)"code");
        url.put("client_id", (Object)this.clientId.getClientId());
        url.put("redirect_uri", (Object)resolvedCallbackUri);
        url.put("scope", (Object)scopesString);
        if (state != null) {
            url.put("state", (Object)state);
        }
        url.put("access_type", (Object)"offline");
        url.put("approval_prompt", (Object)"force");
        if (userId != null) {
            url.put("login_hint", (Object)userId);
        }
        url.put("include_granted_scopes", (Object)true);
        return url.toURL();
    }

    public UserCredentials getCredentials(String userId) throws IOException {
        Preconditions.checkNotNull(userId);
        if (this.tokenStore == null) {
            throw new IllegalStateException("Method cannot be called if token store is not specified.");
        }
        String tokenData = this.tokenStore.load(userId);
        if (tokenData == null) {
            return null;
        }
        GenericJson tokenJson = OAuth2Utils.parseJson(tokenData);
        String accessTokenValue = OAuth2Utils.validateString(tokenJson, "access_token", "Error parsing stored token data.");
        Long expirationMillis = OAuth2Utils.validateLong(tokenJson, "expiration_time_millis", "Error parsing stored token data.");
        Date expirationTime = new Date(expirationMillis);
        AccessToken accessToken = new AccessToken(accessTokenValue, expirationTime);
        String refreshToken = OAuth2Utils.validateOptionalString(tokenJson, "refresh_token", "Error parsing stored token data.");
        UserCredentials credentials = UserCredentials.newBuilder().setClientId(this.clientId.getClientId()).setClientSecret(this.clientId.getClientSecret()).setRefreshToken(refreshToken).setAccessToken(accessToken).setHttpTransportFactory(this.transportFactory).setTokenServerUri(this.tokenServerUri).build();
        this.monitorCredentials(userId, credentials);
        return credentials;
    }

    public UserCredentials getCredentialsFromCode(String code, URI baseUri) throws IOException {
        Preconditions.checkNotNull(code);
        URI resolvedCallbackUri = this.getCallbackUri(baseUri);
        GenericData tokenData = new GenericData();
        tokenData.put("code", (Object)code);
        tokenData.put("client_id", (Object)this.clientId.getClientId());
        tokenData.put("client_secret", (Object)this.clientId.getClientSecret());
        tokenData.put("redirect_uri", (Object)resolvedCallbackUri);
        tokenData.put("grant_type", (Object)"authorization_code");
        UrlEncodedContent tokenContent = new UrlEncodedContent(tokenData);
        HttpRequestFactory requestFactory = this.transportFactory.create().createRequestFactory();
        HttpRequest tokenRequest = requestFactory.buildPostRequest(new GenericUrl(this.tokenServerUri), tokenContent);
        tokenRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        HttpResponse tokenResponse = tokenRequest.execute();
        GenericJson parsedTokens = tokenResponse.parseAs(GenericJson.class);
        String accessTokenValue = OAuth2Utils.validateString(parsedTokens, "access_token", "Error reading result of Token API:");
        int expiresInSecs = OAuth2Utils.validateInt32(parsedTokens, "expires_in", "Error reading result of Token API:");
        Date expirationTime = new Date(new Date().getTime() + (long)(expiresInSecs * 1000));
        AccessToken accessToken = new AccessToken(accessTokenValue, expirationTime);
        String refreshToken = OAuth2Utils.validateOptionalString(parsedTokens, "refresh_token", "Error reading result of Token API:");
        return UserCredentials.newBuilder().setClientId(this.clientId.getClientId()).setClientSecret(this.clientId.getClientSecret()).setRefreshToken(refreshToken).setAccessToken(accessToken).setHttpTransportFactory(this.transportFactory).setTokenServerUri(this.tokenServerUri).build();
    }

    public UserCredentials getAndStoreCredentialsFromCode(String userId, String code, URI baseUri) throws IOException {
        Preconditions.checkNotNull(userId);
        Preconditions.checkNotNull(code);
        UserCredentials credentials = this.getCredentialsFromCode(code, baseUri);
        this.storeCredentials(userId, credentials);
        this.monitorCredentials(userId, credentials);
        return credentials;
    }

    public void revokeAuthorization(String userId) throws IOException {
        Preconditions.checkNotNull(userId);
        if (this.tokenStore == null) {
            throw new IllegalStateException("Method cannot be called if token store is not specified.");
        }
        String tokenData = this.tokenStore.load(userId);
        if (tokenData == null) {
            return;
        }
        IOException deleteTokenException = null;
        try {
            this.tokenStore.delete(userId);
        }
        catch (IOException e) {
            deleteTokenException = e;
        }
        GenericJson tokenJson = OAuth2Utils.parseJson(tokenData);
        String accessTokenValue = OAuth2Utils.validateOptionalString(tokenJson, "access_token", "Error parsing stored token data.");
        String refreshToken = OAuth2Utils.validateOptionalString(tokenJson, "refresh_token", "Error parsing stored token data.");
        String revokeToken = refreshToken != null ? refreshToken : accessTokenValue;
        GenericUrl revokeUrl = new GenericUrl(OAuth2Utils.TOKEN_REVOKE_URI);
        revokeUrl.put("token", (Object)revokeToken);
        HttpRequestFactory requestFactory = this.transportFactory.create().createRequestFactory();
        HttpRequest tokenRequest = requestFactory.buildGetRequest(revokeUrl);
        tokenRequest.execute();
        if (deleteTokenException != null) {
            throw deleteTokenException;
        }
    }

    public void storeCredentials(String userId, UserCredentials credentials) throws IOException {
        if (this.tokenStore == null) {
            throw new IllegalStateException("Cannot store tokens if tokenStore is not specified.");
        }
        AccessToken accessToken = credentials.getAccessToken();
        String acessTokenValue = null;
        Date expiresBy = null;
        if (accessToken != null) {
            acessTokenValue = accessToken.getTokenValue();
            expiresBy = accessToken.getExpirationTime();
        }
        String refreshToken = credentials.getRefreshToken();
        GenericJson tokenStateJson = new GenericJson();
        tokenStateJson.setFactory(OAuth2Utils.JSON_FACTORY);
        tokenStateJson.put("access_token", (Object)acessTokenValue);
        tokenStateJson.put("expiration_time_millis", (Object)expiresBy.getTime());
        if (refreshToken != null) {
            tokenStateJson.put("refresh_token", (Object)refreshToken);
        }
        String tokenState = tokenStateJson.toString();
        this.tokenStore.store(userId, tokenState);
    }

    protected void monitorCredentials(String userId, UserCredentials credentials) {
        credentials.addChangeListener(new UserCredentialsListener(userId));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private ClientId clientId;
        private TokenStore tokenStore;
        private URI callbackUri;
        private URI tokenServerUri;
        private URI userAuthUri;
        private Collection<String> scopes;
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(UserAuthorizer authorizer) {
            this.clientId = authorizer.clientId;
            this.scopes = authorizer.scopes;
            this.transportFactory = authorizer.transportFactory;
            this.tokenServerUri = authorizer.tokenServerUri;
            this.tokenStore = authorizer.tokenStore;
            this.callbackUri = authorizer.callbackUri;
            this.userAuthUri = authorizer.userAuthUri;
        }

        public Builder setClientId(ClientId clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setTokenStore(TokenStore tokenStore) {
            this.tokenStore = tokenStore;
            return this;
        }

        public Builder setScopes(Collection<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        public Builder setTokenServerUri(URI tokenServerUri) {
            this.tokenServerUri = tokenServerUri;
            return this;
        }

        public Builder setCallbackUri(URI callbackUri) {
            this.callbackUri = callbackUri;
            return this;
        }

        public Builder setUserAuthUri(URI userAuthUri) {
            this.userAuthUri = userAuthUri;
            return this;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory transportFactory) {
            this.transportFactory = transportFactory;
            return this;
        }

        public ClientId getClientId() {
            return this.clientId;
        }

        public TokenStore getTokenStore() {
            return this.tokenStore;
        }

        public Collection<String> getScopes() {
            return this.scopes;
        }

        public URI getTokenServerUri() {
            return this.tokenServerUri;
        }

        public URI getCallbackUri() {
            return this.callbackUri;
        }

        public URI getUserAuthUri() {
            return this.userAuthUri;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        public UserAuthorizer build() {
            return new UserAuthorizer(this.clientId, this.scopes, this.tokenStore, this.callbackUri, this.transportFactory, this.tokenServerUri, this.userAuthUri);
        }
    }

    private class UserCredentialsListener
    implements OAuth2Credentials.CredentialsChangedListener {
        private final String userId;

        public UserCredentialsListener(String userId) {
            this.userId = userId;
        }

        @Override
        public void onChanged(OAuth2Credentials credentials) throws IOException {
            UserCredentials userCredentials = (UserCredentials)credentials;
            UserAuthorizer.this.storeCredentials(this.userId, userCredentials);
        }
    }
}

