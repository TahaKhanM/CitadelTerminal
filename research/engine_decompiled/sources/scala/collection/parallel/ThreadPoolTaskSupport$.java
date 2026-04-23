/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.ThreadPoolExecutor;
import scala.collection.parallel.ThreadPoolTasks$;

public final class ThreadPoolTaskSupport$ {
    public static final ThreadPoolTaskSupport$ MODULE$;

    static {
        new ThreadPoolTaskSupport$();
    }

    public ThreadPoolExecutor $lessinit$greater$default$1() {
        return ThreadPoolTasks$.MODULE$.defaultThreadPool();
    }

    private ThreadPoolTaskSupport$() {
        MODULE$ = this;
    }
}

