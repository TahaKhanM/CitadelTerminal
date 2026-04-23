/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import java.util.List;

final class AutoValue_GoogleCredentialsProvider
extends GoogleCredentialsProvider {
    private final List<String> scopesToApply;
    private final List<String> jwtEnabledScopes;

    private AutoValue_GoogleCredentialsProvider(List<String> scopesToApply, List<String> jwtEnabledScopes) {
        if (scopesToApply == null) {
            throw new NullPointerException("Null scopesToApply");
        }
        this.scopesToApply = scopesToApply;
        if (jwtEnabledScopes == null) {
            throw new NullPointerException("Null jwtEnabledScopes");
        }
        this.jwtEnabledScopes = jwtEnabledScopes;
    }

    @Override
    public List<String> getScopesToApply() {
        return this.scopesToApply;
    }

    @Override
    @BetaApi
    public List<String> getJwtEnabledScopes() {
        return this.jwtEnabledScopes;
    }

    public String toString() {
        return "GoogleCredentialsProvider{scopesToApply=" + this.scopesToApply + ", jwtEnabledScopes=" + this.jwtEnabledScopes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof GoogleCredentialsProvider) {
            GoogleCredentialsProvider that = (GoogleCredentialsProvider)o;
            return this.scopesToApply.equals(that.getScopesToApply()) && this.jwtEnabledScopes.equals(that.getJwtEnabledScopes());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.scopesToApply.hashCode();
        h *= 1000003;
        return h ^= this.jwtEnabledScopes.hashCode();
    }

    static final class Builder
    extends GoogleCredentialsProvider.Builder {
        private List<String> scopesToApply;
        private List<String> jwtEnabledScopes;

        Builder() {
        }

        Builder(GoogleCredentialsProvider source) {
            this.scopesToApply = source.getScopesToApply();
            this.jwtEnabledScopes = source.getJwtEnabledScopes();
        }

        @Override
        public GoogleCredentialsProvider.Builder setScopesToApply(List<String> scopesToApply) {
            this.scopesToApply = scopesToApply;
            return this;
        }

        @Override
        public List<String> getScopesToApply() {
            if (this.scopesToApply == null) {
                throw new IllegalStateException("Property \"scopesToApply\" has not been set");
            }
            return this.scopesToApply;
        }

        @Override
        public GoogleCredentialsProvider.Builder setJwtEnabledScopes(List<String> jwtEnabledScopes) {
            this.jwtEnabledScopes = jwtEnabledScopes;
            return this;
        }

        @Override
        public List<String> getJwtEnabledScopes() {
            if (this.jwtEnabledScopes == null) {
                throw new IllegalStateException("Property \"jwtEnabledScopes\" has not been set");
            }
            return this.jwtEnabledScopes;
        }

        @Override
        public GoogleCredentialsProvider build() {
            String missing = "";
            if (this.scopesToApply == null) {
                missing = missing + " scopesToApply";
            }
            if (this.jwtEnabledScopes == null) {
                missing = missing + " jwtEnabledScopes";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_GoogleCredentialsProvider(this.scopesToApply, this.jwtEnabledScopes);
        }
    }
}

