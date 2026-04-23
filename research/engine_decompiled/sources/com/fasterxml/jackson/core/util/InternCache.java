/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class InternCache
extends LinkedHashMap<String, String> {
    private static final int MAX_ENTRIES = 100;
    public static final InternCache instance = new InternCache();

    private InternCache() {
        super(100, 0.8f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        return this.size() > 100;
    }

    public synchronized String intern(String input2) {
        String result2 = (String)this.get(input2);
        if (result2 == null) {
            result2 = input2.intern();
            this.put(result2, result2);
        }
        return result2;
    }
}

