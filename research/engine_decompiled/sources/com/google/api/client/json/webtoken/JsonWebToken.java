/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.json.webtoken;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Preconditions;
import java.util.Collections;
import java.util.List;

public class JsonWebToken {
    private final Header header;
    private final Payload payload;

    public JsonWebToken(Header header, Payload payload) {
        this.header = Preconditions.checkNotNull(header);
        this.payload = Preconditions.checkNotNull(payload);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("header", this.header).add("payload", this.payload).toString();
    }

    public Header getHeader() {
        return this.header;
    }

    public Payload getPayload() {
        return this.payload;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Payload
    extends GenericJson {
        @Key(value="exp")
        private Long expirationTimeSeconds;
        @Key(value="nbf")
        private Long notBeforeTimeSeconds;
        @Key(value="iat")
        private Long issuedAtTimeSeconds;
        @Key(value="iss")
        private String issuer;
        @Key(value="aud")
        private Object audience;
        @Key(value="jti")
        private String jwtId;
        @Key(value="typ")
        private String type;
        @Key(value="sub")
        private String subject;

        public final Long getExpirationTimeSeconds() {
            return this.expirationTimeSeconds;
        }

        public Payload setExpirationTimeSeconds(Long expirationTimeSeconds) {
            this.expirationTimeSeconds = expirationTimeSeconds;
            return this;
        }

        public final Long getNotBeforeTimeSeconds() {
            return this.notBeforeTimeSeconds;
        }

        public Payload setNotBeforeTimeSeconds(Long notBeforeTimeSeconds) {
            this.notBeforeTimeSeconds = notBeforeTimeSeconds;
            return this;
        }

        public final Long getIssuedAtTimeSeconds() {
            return this.issuedAtTimeSeconds;
        }

        public Payload setIssuedAtTimeSeconds(Long issuedAtTimeSeconds) {
            this.issuedAtTimeSeconds = issuedAtTimeSeconds;
            return this;
        }

        public final String getIssuer() {
            return this.issuer;
        }

        public Payload setIssuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public final Object getAudience() {
            return this.audience;
        }

        public final List<String> getAudienceAsList() {
            if (this.audience == null) {
                return Collections.emptyList();
            }
            if (this.audience instanceof String) {
                return Collections.singletonList((String)this.audience);
            }
            return (List)this.audience;
        }

        public Payload setAudience(Object audience) {
            this.audience = audience;
            return this;
        }

        public final String getJwtId() {
            return this.jwtId;
        }

        public Payload setJwtId(String jwtId) {
            this.jwtId = jwtId;
            return this;
        }

        public final String getType() {
            return this.type;
        }

        public Payload setType(String type) {
            this.type = type;
            return this;
        }

        public final String getSubject() {
            return this.subject;
        }

        public Payload setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        @Override
        public Payload set(String fieldName, Object value) {
            return (Payload)super.set(fieldName, value);
        }

        @Override
        public Payload clone() {
            return (Payload)super.clone();
        }
    }

    public static class Header
    extends GenericJson {
        @Key(value="typ")
        private String type;
        @Key(value="cty")
        private String contentType;

        public final String getType() {
            return this.type;
        }

        public Header setType(String type) {
            this.type = type;
            return this;
        }

        public final String getContentType() {
            return this.contentType;
        }

        public Header setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Header set(String fieldName, Object value) {
            return (Header)super.set(fieldName, value);
        }

        public Header clone() {
            return (Header)super.clone();
        }
    }
}

