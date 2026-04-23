/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.concurrent.forkjoin.ForkJoinPool;

public final class ForkJoinTasks$ {
    public static final ForkJoinTasks$ MODULE$;
    private ForkJoinPool defaultForkJoinPool;
    private volatile boolean bitmap$0;

    static {
        new ForkJoinTasks$();
    }

    private ForkJoinPool defaultForkJoinPool$lzycompute() {
        ForkJoinTasks$ forkJoinTasks$ = this;
        synchronized (forkJoinTasks$) {
            if (!this.bitmap$0) {
                this.defaultForkJoinPool = new ForkJoinPool();
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.defaultForkJoinPool;
        }
    }

    public ForkJoinPool defaultForkJoinPool() {
        return this.bitmap$0 ? this.defaultForkJoinPool : this.defaultForkJoinPool$lzycompute();
    }

    private ForkJoinTasks$() {
        MODULE$ = this;
    }
}

