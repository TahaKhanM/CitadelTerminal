/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import scala.Function0;
import scala.Serializable;
import scala.collection.parallel.Task;
import scala.collection.parallel.ThreadPoolTasks;
import scala.collection.parallel.ThreadPoolTasks$;

public abstract class ThreadPoolTasks$class {
    public static ThreadPoolExecutor executor(ThreadPoolTasks $this) {
        return $this.environment();
    }

    public static LinkedBlockingQueue queue(ThreadPoolTasks $this) {
        return (LinkedBlockingQueue)$this.executor().getQueue();
    }

    public static void scala$collection$parallel$ThreadPoolTasks$$incrTasks(ThreadPoolTasks $this) {
        ThreadPoolTasks threadPoolTasks = $this;
        synchronized (threadPoolTasks) {
            $this.totaltasks_$eq($this.totaltasks() + 1);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void scala$collection$parallel$ThreadPoolTasks$$decrTasks(ThreadPoolTasks $this) {
        ThreadPoolTasks threadPoolTasks = $this;
        synchronized (threadPoolTasks) {
            $this.totaltasks_$eq($this.totaltasks() - 1);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Function0 execute(ThreadPoolTasks $this, Task task) {
        ThreadPoolTasks.WrappedTask t = $this.newWrappedTask(task);
        t.start();
        return new Serializable($this, t){
            public static final long serialVersionUID = 0L;
            private final ThreadPoolTasks.WrappedTask t$1;

            public final R apply() {
                this.t$1.sync();
                this.t$1.body().forwardThrowable();
                return this.t$1.body().result();
            }
            {
                this.t$1 = t$1;
            }
        };
    }

    public static Object executeAndWaitResult(ThreadPoolTasks $this, Task task) {
        ThreadPoolTasks.WrappedTask t = $this.newWrappedTask(task);
        t.start();
        t.sync();
        t.body().forwardThrowable();
        return t.body().result();
    }

    public static int parallelismLevel(ThreadPoolTasks $this) {
        return ThreadPoolTasks$.MODULE$.numCores();
    }

    public static void $init$(ThreadPoolTasks $this) {
        $this.totaltasks_$eq(0);
    }
}

