/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.auth;

import com.google.auth.Credentials;
import io.grpc.CallCredentials;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;

public final class MoreCallCredentials {
    public static CallCredentials from(Credentials creds) {
        return new GoogleAuthLibraryCallCredentials(creds);
    }

    private MoreCallCredentials() {
    }
}

