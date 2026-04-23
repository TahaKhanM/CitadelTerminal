/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public final class FutureThreadPoolTasks$ {
    public static final FutureThreadPoolTasks$ MODULE$;
    private final int numCores;
    private final AtomicLong tcount;
    private final ExecutorService defaultThreadPool;

    static {
        new FutureThreadPoolTasks$();
    }

    public int numCores() {
        return this.numCores;
    }

    public AtomicLong tcount() {
        return this.tcount;
    }

    public ExecutorService defaultThreadPool() {
        return this.defaultThreadPool;
    }

    private FutureThreadPoolTasks$() {
        MODULE$ = this;
        this.numCores = Runtime.getRuntime().availableProcessors();
        this.tcount = new AtomicLong(0L);
        this.defaultThreadPool = Executors.newCachedThreadPool();
    }
}

