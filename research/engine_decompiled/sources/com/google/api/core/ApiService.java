/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.BetaApi;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface ApiService {
    public void addListener(Listener var1, Executor var2);

    public void awaitRunning();

    public void awaitRunning(long var1, TimeUnit var3) throws TimeoutException;

    public void awaitTerminated();

    public void awaitTerminated(long var1, TimeUnit var3) throws TimeoutException;

    public Throwable failureCause();

    public boolean isRunning();

    public ApiService startAsync();

    public State state();

    public ApiService stopAsync();

    @BetaApi
    public static abstract class Listener {
        public void failed(State from2, Throwable failure) {
        }

        public void running() {
        }

        public void starting() {
        }

        public void stopping(State from2) {
        }

        public void terminated(State from2) {
        }
    }

    @BetaApi
    public static enum State {
        FAILED,
        NEW,
        RUNNING,
        STARTING,
        STOPPING,
        TERMINATED;

    }
}

