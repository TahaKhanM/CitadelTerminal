/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

public interface Clock {
    public static final Clock SYSTEM = new Clock(){

        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    };

    public long currentTimeMillis();
}

