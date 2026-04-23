/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.AutoValue_GoogleCredentialsProvider;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.ServiceAccountJwtAccessCredentials;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;

public abstract class GoogleCredentialsProvider
implements CredentialsProvider {
    public abstract List<String> getScopesToApply();

    @BetaApi
    public abstract List<String> getJwtEnabledScopes();

    @Override
    public Credentials getCredentials() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        boolean hasJwtEnabledScope = false;
        for (String scope : this.getJwtEnabledScopes()) {
            if (!this.getScopesToApply().contains(scope)) continue;
            hasJwtEnabledScope = true;
            break;
        }
        if (credentials instanceof ServiceAccountCredentials && hasJwtEnabledScope) {
            ServiceAccountCredentials serviceAccount = (ServiceAccountCredentials)credentials;
            return ServiceAccountJwtAccessCredentials.newBuilder().setClientEmail(serviceAccount.getClientEmail()).setClientId(serviceAccount.getClientId()).setPrivateKey(serviceAccount.getPrivateKey()).setPrivateKeyId(serviceAccount.getPrivateKeyId()).build();
        }
        if (credentials.createScopedRequired()) {
            credentials = credentials.createScoped(this.getScopesToApply());
        }
        return credentials;
    }

    public static Builder newBuilder() {
        return new AutoValue_GoogleCredentialsProvider.Builder().setJwtEnabledScopes(ImmutableList.of());
    }

    public Builder toBuilder() {
        return new AutoValue_GoogleCredentialsProvider.Builder(this);
    }

    @BetaApi
    public static abstract class Builder {
        public abstract Builder setScopesToApply(List<String> var1);

        public abstract List<String> getScopesToApply();

        @BetaApi
        public abstract Builder setJwtEnabledScopes(List<String> var1);

        @BetaApi
        public abstract List<String> getJwtEnabledScopes();

        public abstract GoogleCredentialsProvider build();
    }
}

