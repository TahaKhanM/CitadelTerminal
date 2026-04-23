/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import scala.Predef$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.concurrent.BatchingExecutor;
import scala.concurrent.OnCompleteRunnable;

public abstract class BatchingExecutor$class {
    public static void execute(BatchingExecutor $this, Runnable runnable) {
        if ($this.batchable(runnable)) {
            List<Runnable> list2 = $this.scala$concurrent$BatchingExecutor$$_tasksLocal().get();
            if (list2 == null) {
                $this.unbatchedExecute(new BatchingExecutor.Batch($this, (List<Runnable>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Runnable[]{runnable}))));
            } else {
                $this.scala$concurrent$BatchingExecutor$$_tasksLocal().set(list2.$colon$colon(runnable));
            }
        } else {
            $this.unbatchedExecute(runnable);
        }
    }

    public static boolean batchable(BatchingExecutor $this, Runnable runnable) {
        boolean bl = runnable instanceof OnCompleteRunnable;
        return bl;
    }

    public static void $init$(BatchingExecutor $this) {
        $this.scala$concurrent$BatchingExecutor$_setter_$scala$concurrent$BatchingExecutor$$_tasksLocal_$eq(new ThreadLocal());
    }
}

