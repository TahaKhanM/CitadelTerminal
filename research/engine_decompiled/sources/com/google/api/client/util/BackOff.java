/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import java.io.IOException;

public interface BackOff {
    public static final long STOP = -1L;
    public static final BackOff ZERO_BACKOFF = new BackOff(){

        public void reset() throws IOException {
        }

        public long nextBackOffMillis() throws IOException {
            return 0L;
        }
    };
    public static final BackOff STOP_BACKOFF = new BackOff(){

        public void reset() throws IOException {
        }

        public long nextBackOffMillis() throws IOException {
            return -1L;
        }
    };

    public void reset() throws IOException;

    public long nextBackOffMillis() throws IOException;
}

