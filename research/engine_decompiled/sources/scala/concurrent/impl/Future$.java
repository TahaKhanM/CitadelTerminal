/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import scala.Function0;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.impl.Future;

public final class Future$ {
    public static final Future$ MODULE$;

    static {
        new Future$();
    }

    public <T> Future<T> apply(Function0<T> body2, ExecutionContext executor) {
        Future.PromiseCompletingRunnable<T> runnable = new Future.PromiseCompletingRunnable<T>(body2);
        executor.prepare().execute(runnable);
        return runnable.promise().future();
    }

    private Future$() {
        MODULE$ = this;
    }
}

