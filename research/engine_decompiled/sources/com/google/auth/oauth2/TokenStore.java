/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import java.io.IOException;

public interface TokenStore {
    public String load(String var1) throws IOException;

    public void store(String var1, String var2) throws IOException;

    public void delete(String var1) throws IOException;
}

