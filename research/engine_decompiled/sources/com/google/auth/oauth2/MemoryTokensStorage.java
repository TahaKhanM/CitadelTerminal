/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.auth.oauth2.TokenStore;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MemoryTokensStorage
implements TokenStore {
    private final Map<String, String> tokensStorage = new HashMap<String, String>();

    @Override
    public String load(String id) throws IOException {
        return this.tokensStorage.get(id);
    }

    @Override
    public void store(String id, String tokens) throws IOException {
        this.tokensStorage.put(id, tokens);
    }

    @Override
    public void delete(String id) throws IOException {
        this.tokensStorage.remove(id);
    }
}

