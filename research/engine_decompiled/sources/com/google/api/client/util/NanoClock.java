/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

public interface NanoClock {
    public static final NanoClock SYSTEM = new NanoClock(){

        public long nanoTime() {
            return System.nanoTime();
        }
    };

    public long nanoTime();
}

