/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Serializable;
import scala.collection.parallel.ForkJoinTasks;
import scala.collection.parallel.Task;
import scala.concurrent.forkjoin.ForkJoinPool;
import scala.concurrent.forkjoin.ForkJoinTask;
import scala.concurrent.forkjoin.ForkJoinWorkerThread;
import scala.runtime.BoxedUnit;

public abstract class ForkJoinTasks$class {
    public static ForkJoinPool forkJoinPool(ForkJoinTasks $this) {
        return $this.environment();
    }

    public static Function0 execute(ForkJoinTasks $this, Task task) {
        java.io.Serializable serializable;
        ForkJoinTasks.WrappedTask fjtask = $this.newWrappedTask(task);
        if (Thread.currentThread() instanceof ForkJoinWorkerThread) {
            serializable = ((ForkJoinTask)((Object)fjtask)).fork();
        } else {
            $this.forkJoinPool().execute((ForkJoinTask)((Object)fjtask));
            serializable = BoxedUnit.UNIT;
        }
        return new Serializable($this, fjtask){
            public static final long serialVersionUID = 0L;
            private final ForkJoinTasks.WrappedTask fjtask$1;

            public final R apply() {
                this.fjtask$1.sync();
                this.fjtask$1.body().forwardThrowable();
                return this.fjtask$1.body().result();
            }
            {
                this.fjtask$1 = fjtask$1;
            }
        };
    }

    public static Object executeAndWaitResult(ForkJoinTasks $this, Task task) {
        java.io.Serializable serializable;
        ForkJoinTasks.WrappedTask fjtask = $this.newWrappedTask(task);
        if (Thread.currentThread() instanceof ForkJoinWorkerThread) {
            serializable = ((ForkJoinTask)((Object)fjtask)).fork();
        } else {
            $this.forkJoinPool().execute((ForkJoinTask)((Object)fjtask));
            serializable = BoxedUnit.UNIT;
        }
        fjtask.sync();
        fjtask.body().forwardThrowable();
        return fjtask.body().result();
    }

    public static int parallelismLevel(ForkJoinTasks $this) {
        return $this.forkJoinPool().getParallelism();
    }

    public static void $init$(ForkJoinTasks $this) {
    }
}

