/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import scala.Function0;
import scala.Serializable;
import scala.concurrent.ManagedBlocker;
import scala.concurrent.ThreadPoolRunner;

public abstract class ThreadPoolRunner$class {
    public static Callable functionAsTask(ThreadPoolRunner $this, Function0 fun) {
        return new ThreadPoolRunner.RunCallable($this, fun);
    }

    public static Function0 futureAsFunction(ThreadPoolRunner $this, Future x) {
        return new Serializable($this, x){
            public static final long serialVersionUID = 0L;
            private final Future x$1;

            public final S apply() {
                return (S)this.x$1.get();
            }
            {
                this.x$1 = x$1;
            }
        };
    }

    public static Future submit(ThreadPoolRunner $this, Callable task) {
        return $this.executor().submit(task);
    }

    public static void execute(ThreadPoolRunner $this, Callable task) {
        $this.executor().execute((Runnable)((Object)task));
    }

    public static void managedBlock(ThreadPoolRunner $this, ManagedBlocker blocker) {
        blocker.block();
    }

    public static void $init$(ThreadPoolRunner $this) {
    }
}

