/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.Executor;
import scala.Function0;
import scala.collection.parallel.ExecutionContextTasks;
import scala.collection.parallel.ForkJoinTaskSupport;
import scala.collection.parallel.FutureTasks;
import scala.collection.parallel.Task;
import scala.collection.parallel.Tasks;
import scala.concurrent.ExecutionContext;
import scala.concurrent.forkjoin.ForkJoinPool;
import scala.concurrent.impl.ExecutionContextImpl;

public abstract class ExecutionContextTasks$class {
    public static ExecutionContext executionContext(ExecutionContextTasks $this) {
        return $this.environment();
    }

    public static Function0 execute(ExecutionContextTasks $this, Task task) {
        return $this.scala$collection$parallel$ExecutionContextTasks$$driver().execute(task);
    }

    public static Object executeAndWaitResult(ExecutionContextTasks $this, Task task) {
        return $this.scala$collection$parallel$ExecutionContextTasks$$driver().executeAndWaitResult(task);
    }

    public static int parallelismLevel(ExecutionContextTasks $this) {
        return $this.scala$collection$parallel$ExecutionContextTasks$$driver().parallelismLevel();
    }

    public static void $init$(ExecutionContextTasks $this) {
        FutureTasks futureTasks;
        ExecutionContext executionContext = $this.executionContext();
        if (executionContext instanceof ExecutionContextImpl) {
            Tasks tasks;
            ExecutionContextImpl executionContextImpl = (ExecutionContextImpl)executionContext;
            Executor executor = executionContextImpl.executor();
            if (executor instanceof ForkJoinPool) {
                ForkJoinPool forkJoinPool = (ForkJoinPool)executor;
                tasks = new ForkJoinTaskSupport(forkJoinPool);
            } else {
                tasks = new FutureTasks($this.environment());
            }
            futureTasks = tasks;
        } else {
            futureTasks = new FutureTasks($this.environment());
        }
        $this.scala$collection$parallel$ExecutionContextTasks$_setter_$scala$collection$parallel$ExecutionContextTasks$$driver_$eq(futureTasks);
    }
}

