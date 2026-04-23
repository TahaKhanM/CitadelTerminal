/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import scala.Function1;
import scala.concurrent.ExecutionContext$;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.impl.ExecutionContextImpl;
import scala.runtime.BoxedUnit;

public final class ExecutionContextImpl$ {
    public static final ExecutionContextImpl$ MODULE$;

    static {
        new ExecutionContextImpl$();
    }

    public ExecutionContextImpl fromExecutor(Executor e, Function1<Throwable, BoxedUnit> reporter) {
        return new ExecutionContextImpl(e, reporter);
    }

    public Function1<Throwable, BoxedUnit> fromExecutor$default$2() {
        return ExecutionContext$.MODULE$.defaultReporter();
    }

    public ExecutionContextImpl fromExecutorService(ExecutorService es, Function1<Throwable, BoxedUnit> reporter) {
        return new ExecutionContextExecutorService(es, reporter){

            private final ExecutorService asExecutorService() {
                return (ExecutorService)this.executor();
            }

            public void execute(Runnable command) {
                this.executor().execute(command);
            }

            public void shutdown() {
                this.asExecutorService().shutdown();
            }

            public List<Runnable> shutdownNow() {
                return this.asExecutorService().shutdownNow();
            }

            public boolean isShutdown() {
                return this.asExecutorService().isShutdown();
            }

            public boolean isTerminated() {
                return this.asExecutorService().isTerminated();
            }

            public boolean awaitTermination(long l, TimeUnit timeUnit) {
                return this.asExecutorService().awaitTermination(l, timeUnit);
            }

            public <T> Future<T> submit(Callable<T> callable) {
                return this.asExecutorService().submit(callable);
            }

            public <T> Future<T> submit(Runnable runnable, T t) {
                return this.asExecutorService().submit(runnable, t);
            }

            public Future<?> submit(Runnable runnable) {
                return this.asExecutorService().submit(runnable);
            }

            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables) {
                return this.asExecutorService().invokeAll(callables);
            }

            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) {
                return this.asExecutorService().invokeAll(callables, l, timeUnit);
            }

            public <T> T invokeAny(Collection<? extends Callable<T>> callables) {
                return this.asExecutorService().invokeAny(callables);
            }

            public <T> T invokeAny(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) {
                return this.asExecutorService().invokeAny(callables, l, timeUnit);
            }
        };
    }

    public Function1<Throwable, BoxedUnit> fromExecutorService$default$2() {
        return ExecutionContext$.MODULE$.defaultReporter();
    }

    private ExecutionContextImpl$() {
        MODULE$ = this;
    }
}

