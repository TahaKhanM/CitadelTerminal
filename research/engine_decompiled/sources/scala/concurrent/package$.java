/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import scala.Function0;
import scala.concurrent.AwaitPermission$;
import scala.concurrent.BlockContext$;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.concurrent.Promise$;
import scala.concurrent.impl.Future;
import scala.concurrent.impl.Future$;

public final class package$ {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    public <T> Future<T> future(Function0<T> body2, ExecutionContext executor) {
        scala.concurrent.Future$ future$ = scala.concurrent.Future$.MODULE$;
        Future$ future$2 = Future$.MODULE$;
        Future.PromiseCompletingRunnable<T> runnable1 = new Future.PromiseCompletingRunnable<T>(body2);
        executor.prepare().execute(runnable1);
        return runnable1.promise().future();
    }

    public <T> Promise<T> promise() {
        return Promise$.MODULE$.apply();
    }

    public <T> T blocking(Function0<T> body2) throws Exception {
        return BlockContext$.MODULE$.current().blockOn(body2, AwaitPermission$.MODULE$);
    }

    private package$() {
        MODULE$ = this;
    }
}

