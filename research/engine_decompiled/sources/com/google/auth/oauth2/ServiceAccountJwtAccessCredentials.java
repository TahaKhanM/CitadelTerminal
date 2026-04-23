/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Throwables;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class ServiceAccountJwtAccessCredentials
extends Credentials
implements ServiceAccountSigner {
    private static final long serialVersionUID = -7274955171379494197L;
    static final String JWT_ACCESS_PREFIX = "Bearer ";
    @VisibleForTesting
    static final long LIFE_SPAN_SECS = TimeUnit.HOURS.toSeconds(1L);
    private final String clientId;
    private final String clientEmail;
    private final PrivateKey privateKey;
    private final String privateKeyId;
    private final URI defaultAudience;
    private transient LoadingCache<URI, String> tokenCache;
    @VisibleForTesting
    transient Clock clock = Clock.SYSTEM;

    @Deprecated
    public ServiceAccountJwtAccessCredentials(String clientId, String clientEmail, PrivateKey privateKey, String privateKeyId) {
        this(clientId, clientEmail, privateKey, privateKeyId, null);
    }

    @Deprecated
    public ServiceAccountJwtAccessCredentials(String clientId, String clientEmail, PrivateKey privateKey, String privateKeyId, URI defaultAudience) {
        this.clientId = clientId;
        this.clientEmail = Preconditions.checkNotNull(clientEmail);
        this.privateKey = Preconditions.checkNotNull(privateKey);
        this.privateKeyId = privateKeyId;
        this.defaultAudience = defaultAudience;
        this.tokenCache = this.createCache();
    }

    static ServiceAccountJwtAccessCredentials fromJson(Map<String, Object> json) throws IOException {
        return ServiceAccountJwtAccessCredentials.fromJson(json, null);
    }

    static ServiceAccountJwtAccessCredentials fromJson(Map<String, Object> json, URI defaultAudience) throws IOException {
        String clientId = (String)json.get("client_id");
        String clientEmail = (String)json.get("client_email");
        String privateKeyPkcs8 = (String)json.get("private_key");
        String privateKeyId = (String)json.get("private_key_id");
        if (clientId == null || clientEmail == null || privateKeyPkcs8 == null || privateKeyId == null) {
            throw new IOException("Error reading service account credential from JSON, expecting  'client_id', 'client_email', 'private_key' and 'private_key_id'.");
        }
        return ServiceAccountJwtAccessCredentials.fromPkcs8(clientId, clientEmail, privateKeyPkcs8, privateKeyId, defaultAudience);
    }

    public static ServiceAccountJwtAccessCredentials fromPkcs8(String clientId, String clientEmail, String privateKeyPkcs8, String privateKeyId) throws IOException {
        return ServiceAccountJwtAccessCredentials.fromPkcs8(clientId, clientEmail, privateKeyPkcs8, privateKeyId, null);
    }

    public static ServiceAccountJwtAccessCredentials fromPkcs8(String clientId, String clientEmail, String privateKeyPkcs8, String privateKeyId, URI defaultAudience) throws IOException {
        PrivateKey privateKey = ServiceAccountCredentials.privateKeyFromPkcs8(privateKeyPkcs8);
        return new ServiceAccountJwtAccessCredentials(clientId, clientEmail, privateKey, privateKeyId, defaultAudience);
    }

    public static ServiceAccountJwtAccessCredentials fromStream(InputStream credentialsStream) throws IOException {
        return ServiceAccountJwtAccessCredentials.fromStream(credentialsStream, null);
    }

    public static ServiceAccountJwtAccessCredentials fromStream(InputStream credentialsStream, URI defaultAudience) throws IOException {
        Preconditions.checkNotNull(credentialsStream);
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        JsonObjectParser parser = new JsonObjectParser(jsonFactory);
        GenericJson fileContents = parser.parseAndClose(credentialsStream, OAuth2Utils.UTF_8, GenericJson.class);
        String fileType = (String)fileContents.get("type");
        if (fileType == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if ("service_account".equals(fileType)) {
            return ServiceAccountJwtAccessCredentials.fromJson(fileContents, defaultAudience);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s'.", fileType, "service_account"));
    }

    private LoadingCache<URI, String> createCache() {
        return CacheBuilder.newBuilder().maximumSize(100L).expireAfterWrite(LIFE_SPAN_SECS - 300L, TimeUnit.SECONDS).ticker(new Ticker(){

            @Override
            public long read() {
                return TimeUnit.MILLISECONDS.toNanos(ServiceAccountJwtAccessCredentials.this.clock.currentTimeMillis());
            }
        }).build(new CacheLoader<URI, String>(){

            @Override
            public String load(URI key) throws Exception {
                return ServiceAccountJwtAccessCredentials.this.generateJwtAccess(key);
            }
        });
    }

    @Override
    public String getAuthenticationType() {
        return "JWTAccess";
    }

    @Override
    public boolean hasRequestMetadata() {
        return true;
    }

    @Override
    public boolean hasRequestMetadataOnly() {
        return true;
    }

    @Override
    public void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback callback) {
        this.blockingGetToCallback(uri, callback);
    }

    @Override
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        if (uri == null) {
            if (this.defaultAudience != null) {
                uri = this.defaultAudience;
            } else {
                throw new IOException("JwtAccess requires Audience uri to be passed in or the defaultAudience to be specified");
            }
        }
        String assertion = this.getJwtAccess(uri);
        String authorizationHeader = JWT_ACCESS_PREFIX + assertion;
        List<String> newAuthorizationHeaders = Collections.singletonList(authorizationHeader);
        return Collections.singletonMap("Authorization", newAuthorizationHeaders);
    }

    @Override
    public void refresh() {
        this.tokenCache.invalidateAll();
    }

    private String getJwtAccess(URI uri) throws IOException {
        try {
            return this.tokenCache.get(uri);
        }
        catch (ExecutionException e) {
            Throwables.propagateIfPossible(e.getCause(), IOException.class);
            throw new IllegalStateException("generateJwtAccess threw an unexpected checked exception", e.getCause());
        }
        catch (UncheckedExecutionException e) {
            Throwables.propagateIfPossible(e);
            throw new IllegalStateException("generateJwtAccess threw an unchecked exception that couldn't be rethrown", e);
        }
    }

    private String generateJwtAccess(URI uri) throws IOException {
        String assertion;
        JsonWebSignature.Header header = new JsonWebSignature.Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        header.setKeyId(this.privateKeyId);
        JsonWebToken.Payload payload = new JsonWebToken.Payload();
        long currentTime = this.clock.currentTimeMillis();
        payload.setIssuer(this.clientEmail);
        payload.setSubject(this.clientEmail);
        payload.setAudience(uri.toString());
        payload.setIssuedAtTimeSeconds(currentTime / 1000L);
        payload.setExpirationTimeSeconds(currentTime / 1000L + LIFE_SPAN_SECS);
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        try {
            assertion = JsonWebSignature.signUsingRsaSha256(this.privateKey, jsonFactory, header, payload);
        }
        catch (GeneralSecurityException e) {
            throw new IOException("Error signing service account JWT access header with private key.", e);
        }
        return assertion;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientEmail() {
        return this.clientEmail;
    }

    public final PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public final String getPrivateKeyId() {
        return this.privateKeyId;
    }

    @Override
    public String getAccount() {
        return this.getClientEmail();
    }

    @Override
    public byte[] sign(byte[] toSign) {
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(this.getPrivateKey());
            signer.update(toSign);
            return signer.sign();
        }
        catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException ex) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", ex);
        }
    }

    public int hashCode() {
        return Objects.hash(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.defaultAudience);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("clientId", this.clientId).add("clientEmail", this.clientEmail).add("privateKeyId", this.privateKeyId).add("defaultAudience", this.defaultAudience).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceAccountJwtAccessCredentials)) {
            return false;
        }
        ServiceAccountJwtAccessCredentials other = (ServiceAccountJwtAccessCredentials)obj;
        return Objects.equals(this.clientId, other.clientId) && Objects.equals(this.clientEmail, other.clientEmail) && Objects.equals(this.privateKey, other.privateKey) && Objects.equals(this.privateKeyId, other.privateKeyId) && Objects.equals(this.defaultAudience, other.defaultAudience);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.clock = Clock.SYSTEM;
        this.tokenCache = this.createCache();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String clientId;
        private String clientEmail;
        private PrivateKey privateKey;
        private String privateKeyId;
        private URI defaultAudience;

        protected Builder() {
        }

        protected Builder(ServiceAccountJwtAccessCredentials credentials) {
            this.clientId = credentials.clientId;
            this.clientEmail = credentials.clientEmail;
            this.privateKey = credentials.privateKey;
            this.privateKeyId = credentials.privateKeyId;
            this.defaultAudience = credentials.defaultAudience;
        }

        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setClientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public Builder setPrivateKey(PrivateKey privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder setPrivateKeyId(String privateKeyId) {
            this.privateKeyId = privateKeyId;
            return this;
        }

        public Builder setDefaultAudience(URI defaultAudience) {
            this.defaultAudience = defaultAudience;
            return this;
        }

        public String getClientId() {
            return this.clientId;
        }

        public String getClientEmail() {
            return this.clientEmail;
        }

        public PrivateKey getPrivateKey() {
            return this.privateKey;
        }

        public String getPrivateKeyId() {
            return this.privateKeyId;
        }

        public URI getDefaultAudience() {
            return this.defaultAudience;
        }

        public ServiceAccountJwtAccessCredentials build() {
            return new ServiceAccountJwtAccessCredentials(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.defaultAudience);
        }
    }
}

