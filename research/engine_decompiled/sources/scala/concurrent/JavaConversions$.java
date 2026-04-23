/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import scala.concurrent.ExecutionContext$;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.ExecutionContextExecutorService;

public final class JavaConversions$ {
    public static final JavaConversions$ MODULE$;

    static {
        new JavaConversions$();
    }

    public ExecutionContextExecutorService asExecutionContext(ExecutorService exec2) {
        return ExecutionContext$.MODULE$.fromExecutorService(exec2);
    }

    public ExecutionContextExecutor asExecutionContext(Executor exec2) {
        return ExecutionContext$.MODULE$.fromExecutor(exec2);
    }

    private JavaConversions$() {
        MODULE$ = this;
    }
}

