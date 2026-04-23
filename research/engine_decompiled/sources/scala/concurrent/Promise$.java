/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import scala.concurrent.Promise;
import scala.concurrent.impl.Promise;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class Promise$ {
    public static final Promise$ MODULE$;

    static {
        new Promise$();
    }

    public <T> Promise<T> apply() {
        return new Promise.DefaultPromise();
    }

    public <T> Promise<T> failed(Throwable exception) {
        return this.fromTry(new Failure(exception));
    }

    public <T> Promise<T> successful(T result2) {
        return this.fromTry(new Success<T>(result2));
    }

    public <T> Promise<T> fromTry(Try<T> result2) {
        return new Promise.KeptPromise<T>(result2);
    }

    private Promise$() {
        MODULE$ = this;
    }
}

