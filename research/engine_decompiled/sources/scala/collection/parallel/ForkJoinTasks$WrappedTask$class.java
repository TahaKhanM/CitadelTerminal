/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.ForkJoinTasks;
import scala.concurrent.forkjoin.ForkJoinTask;

public abstract class ForkJoinTasks$WrappedTask$class {
    public static void start(ForkJoinTasks.WrappedTask $this) {
        ((ForkJoinTask)((Object)$this)).fork();
    }

    public static void sync(ForkJoinTasks.WrappedTask $this) {
        ((ForkJoinTask)((Object)$this)).join();
    }

    public static boolean tryCancel(ForkJoinTasks.WrappedTask $this) {
        return ((ForkJoinTask)((Object)$this)).tryUnfork();
    }

    public static void $init$(ForkJoinTasks.WrappedTask $this) {
    }
}

