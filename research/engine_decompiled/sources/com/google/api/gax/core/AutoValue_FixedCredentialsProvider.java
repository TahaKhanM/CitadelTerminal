/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import javax.annotation.Nullable;

final class AutoValue_FixedCredentialsProvider
extends FixedCredentialsProvider {
    private final Credentials credentials;

    AutoValue_FixedCredentialsProvider(@Nullable Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    @Nullable
    public Credentials getCredentials() {
        return this.credentials;
    }

    public String toString() {
        return "FixedCredentialsProvider{credentials=" + this.credentials + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FixedCredentialsProvider) {
            FixedCredentialsProvider that = (FixedCredentialsProvider)o;
            return this.credentials == null ? that.getCredentials() == null : this.credentials.equals(that.getCredentials());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.credentials == null ? 0 : this.credentials.hashCode();
    }
}

