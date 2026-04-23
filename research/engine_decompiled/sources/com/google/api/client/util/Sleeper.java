/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

public interface Sleeper {
    public static final Sleeper DEFAULT = new Sleeper(){

        public void sleep(long millis) throws InterruptedException {
            Thread.sleep(millis);
        }
    };

    public void sleep(long var1) throws InterruptedException;
}

