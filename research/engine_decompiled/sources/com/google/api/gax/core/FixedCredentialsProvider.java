/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.AutoValue_FixedCredentialsProvider;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import javax.annotation.Nullable;

public abstract class FixedCredentialsProvider
implements CredentialsProvider {
    @Override
    @Nullable
    public abstract Credentials getCredentials();

    public static FixedCredentialsProvider create(Credentials credentials) {
        return new AutoValue_FixedCredentialsProvider(credentials);
    }
}

