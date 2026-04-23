/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AccessToken
implements Serializable {
    private static final long serialVersionUID = -8514239465808977353L;
    private final String tokenValue;
    private final Long expirationTimeMillis;

    public AccessToken(String tokenValue, Date expirationTime) {
        this.tokenValue = tokenValue;
        this.expirationTimeMillis = expirationTime == null ? null : Long.valueOf(expirationTime.getTime());
    }

    public String getTokenValue() {
        return this.tokenValue;
    }

    public Date getExpirationTime() {
        if (this.expirationTimeMillis == null) {
            return null;
        }
        return new Date(this.expirationTimeMillis);
    }

    Long getExpirationTimeMillis() {
        return this.expirationTimeMillis;
    }

    public int hashCode() {
        return Objects.hash(this.tokenValue, this.expirationTimeMillis);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("tokenValue", this.tokenValue).add("expirationTimeMillis", this.expirationTimeMillis).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken other = (AccessToken)obj;
        return Objects.equals(this.tokenValue, other.tokenValue) && Objects.equals(this.expirationTimeMillis, other.expirationTimeMillis);
    }
}

