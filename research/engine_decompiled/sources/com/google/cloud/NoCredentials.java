/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.auth.oauth2.OAuth2Credentials;
import java.io.ObjectStreamException;

public class NoCredentials
extends OAuth2Credentials {
    private static final long serialVersionUID = -6263971603971044288L;
    private static final NoCredentials INSTANCE = new NoCredentials();

    private NoCredentials() {
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    public static NoCredentials getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}

