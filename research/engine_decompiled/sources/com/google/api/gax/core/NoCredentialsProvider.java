/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;

public final class NoCredentialsProvider
implements CredentialsProvider {
    @Override
    public Credentials getCredentials() {
        return null;
    }

    public static NoCredentialsProvider create() {
        return new NoCredentialsProvider();
    }
}

