/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;

public final class ThreadPoolTasks$ {
    public static final ThreadPoolTasks$ MODULE$;
    private final int numCores;
    private final AtomicLong tcount;
    private final ThreadPoolExecutor defaultThreadPool;

    static {
        new ThreadPoolTasks$();
    }

    public int numCores() {
        return this.numCores;
    }

    public AtomicLong tcount() {
        return this.tcount;
    }

    public ThreadPoolExecutor defaultThreadPool() {
        return this.defaultThreadPool;
    }

    private ThreadPoolTasks$() {
        MODULE$ = this;
        this.numCores = Runtime.getRuntime().availableProcessors();
        this.tcount = new AtomicLong(0L);
        this.defaultThreadPool = new ThreadPoolExecutor(this.numCores(), Integer.MAX_VALUE, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory(){

            /*
             * WARNING - void declaration
             */
            public Thread newThread(Runnable r) {
                void var2_2;
                Thread t = new Thread(r);
                t.setName(new StringBuilder().append((Object)"pc-thread-").append(BoxesRunTime.boxToLong(ThreadPoolTasks$.MODULE$.tcount().incrementAndGet())).toString());
                t.setDaemon(true);
                return var2_2;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

