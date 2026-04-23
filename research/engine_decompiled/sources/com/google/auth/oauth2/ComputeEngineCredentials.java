/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.DefaultCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.common.base.MoreObjects;
import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputeEngineCredentials
extends GoogleCredentials
implements ServiceAccountSigner {
    private static final Logger LOGGER = Logger.getLogger(ComputeEngineCredentials.class.getName());
    static final String DEFAULT_METADATA_SERVER_URL = "http://169.254.169.254";
    static final String SIGN_BLOB_URL_FORMAT = "https://iam.googleapis.com/v1/projects/-/serviceAccounts/%s:signBlob?alt=json";
    static final int MAX_COMPUTE_PING_TRIES = 3;
    static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    private static final String PARSE_ERROR_ACCOUNT = "Error parsing service account response. ";
    private static final String PARSE_ERROR_SIGNATURE = "Error parsing signature response. ";
    private static final String PARSE_ERROR_MESSAGE = "Error parsing error message response. ";
    private static final long serialVersionUID = -4113476462526554235L;
    private final String transportFactoryClassName;
    private transient HttpTransportFactory transportFactory;
    private transient String serviceAccountEmail;

    @Deprecated
    public static ComputeEngineCredentials of(HttpTransportFactory transportFactory) {
        return ComputeEngineCredentials.newBuilder().setHttpTransportFactory(transportFactory).build();
    }

    @Deprecated
    public ComputeEngineCredentials() {
        this((HttpTransportFactory)null);
    }

    @Deprecated
    public ComputeEngineCredentials(HttpTransportFactory transportFactory) {
        this.transportFactory = MoreObjects.firstNonNull(transportFactory, ComputeEngineCredentials.getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
    }

    public static ComputeEngineCredentials create() {
        return new ComputeEngineCredentials(null);
    }

    @Override
    public AccessToken refreshAccessToken() throws IOException {
        HttpResponse response = this.getMetadataResponse(ComputeEngineCredentials.getTokenServerEncodedUrl());
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified.", statusCode));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", statusCode, response.parseAsString()));
        }
        InputStream content = response.getContent();
        if (content == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        GenericData responseData = response.parseAs(GenericData.class);
        String accessToken = OAuth2Utils.validateString(responseData, "access_token", PARSE_ERROR_PREFIX);
        int expiresInSeconds = OAuth2Utils.validateInt32(responseData, "expires_in", PARSE_ERROR_PREFIX);
        long expiresAtMilliseconds = this.clock.currentTimeMillis() + (long)(expiresInSeconds * 1000);
        return new AccessToken(accessToken, new Date(expiresAtMilliseconds));
    }

    private HttpResponse getMetadataResponse(String url) throws IOException {
        HttpResponse response;
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest request = this.transportFactory.create().createRequestFactory().buildGetRequest(genericUrl);
        JsonObjectParser parser = new JsonObjectParser(OAuth2Utils.JSON_FACTORY);
        request.setParser(parser);
        request.getHeaders().set("Metadata-Flavor", "Google");
        request.setThrowExceptionOnExecuteError(false);
        try {
            response = request.execute();
        }
        catch (UnknownHostException exception) {
            throw new IOException("ComputeEngineCredentials cannot find the metadata server. This is likely because code is not running on Google Compute Engine.", exception);
        }
        return response;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static boolean runningOnComputeEngine(HttpTransportFactory transportFactory, DefaultCredentialsProvider provider) {
        if (Boolean.parseBoolean(provider.getEnv("NO_GCE_CHECK"))) {
            return false;
        }
        GenericUrl tokenUrl = new GenericUrl(ComputeEngineCredentials.getMetadataServerUrl(provider));
        for (int i = 1; i <= 3; ++i) {
            boolean bl;
            HttpRequest request2 = transportFactory.create().createRequestFactory().buildGetRequest(tokenUrl);
            request2.setConnectTimeout(500);
            HttpResponse response = request2.execute();
            try {
                HttpHeaders headers = response.getHeaders();
                bl = OAuth2Utils.headersContainValue(headers, "Metadata-Flavor", "Google");
            }
            catch (Throwable throwable) {
                try {
                    response.disconnect();
                    throw throwable;
                }
                catch (SocketTimeoutException request2) {
                    continue;
                }
                catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Failed to detect whether we are running on Google Compute Engine.", e);
                }
            }
            response.disconnect();
            return bl;
        }
        return false;
    }

    public static String getMetadataServerUrl(DefaultCredentialsProvider provider) {
        String metadataServerAddress = provider.getEnv("GCE_METADATA_HOST");
        if (metadataServerAddress != null) {
            return "http://" + metadataServerAddress;
        }
        return DEFAULT_METADATA_SERVER_URL;
    }

    public static String getMetadataServerUrl() {
        return ComputeEngineCredentials.getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getTokenServerEncodedUrl(DefaultCredentialsProvider provider) {
        return ComputeEngineCredentials.getMetadataServerUrl(provider) + "/computeMetadata/v1/instance/service-accounts/default/token";
    }

    public static String getTokenServerEncodedUrl() {
        return ComputeEngineCredentials.getTokenServerEncodedUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getServiceAccountsUrl() {
        return ComputeEngineCredentials.getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT) + "/computeMetadata/v1/instance/service-accounts/?recursive=true";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.transportFactoryClassName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("transportFactoryClassName", this.transportFactoryClassName).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeEngineCredentials)) {
            return false;
        }
        ComputeEngineCredentials other = (ComputeEngineCredentials)obj;
        return Objects.equals(this.transportFactoryClassName, other.transportFactoryClassName);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.transportFactory = (HttpTransportFactory)ComputeEngineCredentials.newInstance(this.transportFactoryClassName);
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String getAccount() {
        if (this.serviceAccountEmail == null) {
            try {
                this.serviceAccountEmail = this.getDefaultServiceAccount();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failed to to get service account", ex);
            }
        }
        return this.serviceAccountEmail;
    }

    @Override
    public byte[] sign(byte[] toSign) {
        String signature;
        BaseEncoding base64 = BaseEncoding.base64();
        try {
            signature = this.getSignature(base64.encode(toSign));
        }
        catch (IOException ex) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", ex);
        }
        return base64.decode(signature);
    }

    private String getSignature(String bytes2) throws IOException {
        String signBlobUrl = String.format(SIGN_BLOB_URL_FORMAT, this.getAccount());
        GenericUrl genericUrl = new GenericUrl(signBlobUrl);
        GenericData signRequest = new GenericData();
        signRequest.set("bytesToSign", bytes2);
        JsonHttpContent signContent = new JsonHttpContent(OAuth2Utils.JSON_FACTORY, signRequest);
        HttpRequest request = this.transportFactory.create().createRequestFactory().buildPostRequest(genericUrl, signContent);
        Map<String, List<String>> headers = this.getRequestMetadata();
        HttpHeaders requestHeaders = request.getHeaders();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            requestHeaders.put(entry.getKey(), (Object)entry.getValue());
        }
        JsonObjectParser parser = new JsonObjectParser(OAuth2Utils.JSON_FACTORY);
        request.setParser(parser);
        request.setThrowExceptionOnExecuteError(false);
        HttpResponse response = request.execute();
        int statusCode = response.getStatusCode();
        if (statusCode >= 400 && statusCode < 500) {
            GenericData responseError = response.parseAs(GenericData.class);
            Map<String, Object> error2 = OAuth2Utils.validateMap(responseError, "error", PARSE_ERROR_MESSAGE);
            String errorMessage2 = OAuth2Utils.validateString(error2, "message", PARSE_ERROR_MESSAGE);
            throw new IOException(String.format("Error code %s trying to sign provided bytes: %s", statusCode, errorMessage2));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to sign provided bytes: %s", statusCode, response.parseAsString()));
        }
        InputStream content = response.getContent();
        if (content == null) {
            throw new IOException("Empty content from sign blob server request.");
        }
        GenericData responseData = response.parseAs(GenericData.class);
        return OAuth2Utils.validateString(responseData, "signature", PARSE_ERROR_SIGNATURE);
    }

    private String getDefaultServiceAccount() throws IOException {
        HttpResponse response = this.getMetadataResponse(ComputeEngineCredentials.getServiceAccountsUrl());
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get service accounts from Compute Engine metadata. This may be because the virtual machine instance does not have permission scopes specified.", statusCode));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get service accounts from Compute Engine metadata: %s", statusCode, response.parseAsString()));
        }
        InputStream content = response.getContent();
        if (content == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        GenericData responseData = response.parseAs(GenericData.class);
        Map<String, Object> defaultAccount = OAuth2Utils.validateMap(responseData, "default", PARSE_ERROR_ACCOUNT);
        return OAuth2Utils.validateString(defaultAccount, "email", PARSE_ERROR_ACCOUNT);
    }

    public static class Builder
    extends GoogleCredentials.Builder {
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(ComputeEngineCredentials credentials) {
            this.transportFactory = credentials.transportFactory;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory transportFactory) {
            this.transportFactory = transportFactory;
            return this;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        @Override
        public ComputeEngineCredentials build() {
            return new ComputeEngineCredentials(this.transportFactory);
        }
    }
}

