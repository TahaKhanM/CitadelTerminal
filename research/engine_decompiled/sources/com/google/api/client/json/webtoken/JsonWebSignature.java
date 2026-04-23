/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class JsonWebSignature
extends JsonWebToken {
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;

    public JsonWebSignature(Header header, JsonWebToken.Payload payload, byte[] signatureBytes, byte[] signedContentBytes) {
        super(header, payload);
        this.signatureBytes = Preconditions.checkNotNull(signatureBytes);
        this.signedContentBytes = Preconditions.checkNotNull(signedContentBytes);
    }

    public Header getHeader() {
        return (Header)super.getHeader();
    }

    public final boolean verifySignature(PublicKey publicKey) throws GeneralSecurityException {
        Signature signatureAlg = null;
        String algorithm = this.getHeader().getAlgorithm();
        if (!"RS256".equals(algorithm)) {
            return false;
        }
        signatureAlg = SecurityUtils.getSha256WithRsaSignatureAlgorithm();
        return SecurityUtils.verify(signatureAlg, publicKey, this.signatureBytes, this.signedContentBytes);
    }

    @Beta
    public final X509Certificate verifySignature(X509TrustManager trustManager) throws GeneralSecurityException {
        List<String> x509Certificates = this.getHeader().getX509Certificates();
        if (x509Certificates == null || x509Certificates.isEmpty()) {
            return null;
        }
        String algorithm = this.getHeader().getAlgorithm();
        Signature signatureAlg = null;
        if (!"RS256".equals(algorithm)) {
            return null;
        }
        signatureAlg = SecurityUtils.getSha256WithRsaSignatureAlgorithm();
        return SecurityUtils.verify(signatureAlg, trustManager, x509Certificates, this.signatureBytes, this.signedContentBytes);
    }

    @Beta
    public final X509Certificate verifySignature() throws GeneralSecurityException {
        X509TrustManager trustManager = JsonWebSignature.getDefaultX509TrustManager();
        if (trustManager == null) {
            return null;
        }
        return this.verifySignature(trustManager);
    }

    private static X509TrustManager getDefaultX509TrustManager() {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore)null);
            for (TrustManager manager : factory.getTrustManagers()) {
                if (!(manager instanceof X509TrustManager)) continue;
                return (X509TrustManager)manager;
            }
            return null;
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }
        catch (KeyStoreException e) {
            return null;
        }
    }

    public final byte[] getSignatureBytes() {
        return this.signatureBytes;
    }

    public final byte[] getSignedContentBytes() {
        return this.signedContentBytes;
    }

    public static JsonWebSignature parse(JsonFactory jsonFactory, String tokenString) throws IOException {
        return JsonWebSignature.parser(jsonFactory).parse(tokenString);
    }

    public static Parser parser(JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }

    public static String signUsingRsaSha256(PrivateKey privateKey, JsonFactory jsonFactory, Header header, JsonWebToken.Payload payload) throws GeneralSecurityException, IOException {
        String content = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "." + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
        byte[] contentBytes = StringUtils.getBytesUtf8(content);
        byte[] signature = SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, contentBytes);
        return content + "." + Base64.encodeBase64URLSafeString(signature);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class Parser {
        private final JsonFactory jsonFactory;
        private Class<? extends Header> headerClass = Header.class;
        private Class<? extends JsonWebToken.Payload> payloadClass = JsonWebToken.Payload.class;

        public Parser(JsonFactory jsonFactory) {
            this.jsonFactory = Preconditions.checkNotNull(jsonFactory);
        }

        public Class<? extends Header> getHeaderClass() {
            return this.headerClass;
        }

        public Parser setHeaderClass(Class<? extends Header> headerClass) {
            this.headerClass = headerClass;
            return this;
        }

        public Class<? extends JsonWebToken.Payload> getPayloadClass() {
            return this.payloadClass;
        }

        public Parser setPayloadClass(Class<? extends JsonWebToken.Payload> payloadClass) {
            this.payloadClass = payloadClass;
            return this;
        }

        public JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public JsonWebSignature parse(String tokenString) throws IOException {
            int firstDot = tokenString.indexOf(46);
            Preconditions.checkArgument(firstDot != -1);
            byte[] headerBytes = Base64.decodeBase64(tokenString.substring(0, firstDot));
            int secondDot = tokenString.indexOf(46, firstDot + 1);
            Preconditions.checkArgument(secondDot != -1);
            Preconditions.checkArgument(tokenString.indexOf(46, secondDot + 1) == -1);
            byte[] payloadBytes = Base64.decodeBase64(tokenString.substring(firstDot + 1, secondDot));
            byte[] signatureBytes = Base64.decodeBase64(tokenString.substring(secondDot + 1));
            byte[] signedContentBytes = StringUtils.getBytesUtf8(tokenString.substring(0, secondDot));
            Header header = this.jsonFactory.fromInputStream(new ByteArrayInputStream(headerBytes), this.headerClass);
            Preconditions.checkArgument(header.getAlgorithm() != null);
            JsonWebToken.Payload payload = this.jsonFactory.fromInputStream(new ByteArrayInputStream(payloadBytes), this.payloadClass);
            return new JsonWebSignature(header, payload, signatureBytes, signedContentBytes);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Header
    extends JsonWebToken.Header {
        @Key(value="alg")
        private String algorithm;
        @Key(value="jku")
        private String jwkUrl;
        @Key(value="jwk")
        private String jwk;
        @Key(value="kid")
        private String keyId;
        @Key(value="x5u")
        private String x509Url;
        @Key(value="x5t")
        private String x509Thumbprint;
        @Key(value="x5c")
        private List<String> x509Certificates;
        @Key(value="crit")
        private List<String> critical;

        @Override
        public Header setType(String type) {
            super.setType(type);
            return this;
        }

        public final String getAlgorithm() {
            return this.algorithm;
        }

        public Header setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public final String getJwkUrl() {
            return this.jwkUrl;
        }

        public Header setJwkUrl(String jwkUrl) {
            this.jwkUrl = jwkUrl;
            return this;
        }

        public final String getJwk() {
            return this.jwk;
        }

        public Header setJwk(String jwk) {
            this.jwk = jwk;
            return this;
        }

        public final String getKeyId() {
            return this.keyId;
        }

        public Header setKeyId(String keyId) {
            this.keyId = keyId;
            return this;
        }

        public final String getX509Url() {
            return this.x509Url;
        }

        public Header setX509Url(String x509Url) {
            this.x509Url = x509Url;
            return this;
        }

        public final String getX509Thumbprint() {
            return this.x509Thumbprint;
        }

        public Header setX509Thumbprint(String x509Thumbprint) {
            this.x509Thumbprint = x509Thumbprint;
            return this;
        }

        @Deprecated
        public final String getX509Certificate() {
            if (this.x509Certificates == null || this.x509Certificates.isEmpty()) {
                return null;
            }
            return this.x509Certificates.get(0);
        }

        public final List<String> getX509Certificates() {
            return this.x509Certificates;
        }

        @Deprecated
        public Header setX509Certificate(String x509Certificate) {
            ArrayList<String> x509Certificates = new ArrayList<String>();
            x509Certificates.add(x509Certificate);
            this.x509Certificates = x509Certificates;
            return this;
        }

        public Header setX509Certificates(List<String> x509Certificates) {
            this.x509Certificates = x509Certificates;
            return this;
        }

        public final List<String> getCritical() {
            return this.critical;
        }

        public Header setCritical(List<String> critical) {
            this.critical = critical;
            return this;
        }

        @Override
        public Header set(String fieldName, Object value) {
            return (Header)super.set(fieldName, value);
        }

        @Override
        public Header clone() {
            return (Header)super.clone();
        }
    }
}

