/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.ForkJoinTasks$;
import scala.concurrent.forkjoin.ForkJoinPool;

public final class ForkJoinTaskSupport$ {
    public static final ForkJoinTaskSupport$ MODULE$;

    static {
        new ForkJoinTaskSupport$();
    }

    public ForkJoinPool $lessinit$greater$default$1() {
        return ForkJoinTasks$.MODULE$.defaultForkJoinPool();
    }

    private ForkJoinTaskSupport$() {
        MODULE$ = this;
    }
}

