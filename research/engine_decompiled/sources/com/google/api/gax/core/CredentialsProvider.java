/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.auth.Credentials;
import java.io.IOException;

public interface CredentialsProvider {
    public Credentials getCredentials() throws IOException;
}

