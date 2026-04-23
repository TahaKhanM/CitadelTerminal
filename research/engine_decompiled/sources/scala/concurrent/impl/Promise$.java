/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import java.util.concurrent.ExecutionException;
import scala.runtime.NonLocalReturnControl;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.control.ControlThrowable;

public final class Promise$ {
    public static final Promise$ MODULE$;

    static {
        new Promise$();
    }

    public <T> Try<T> scala$concurrent$impl$Promise$$resolveTry(Try<T> source) {
        Try<T> try_;
        if (source instanceof Failure) {
            Failure failure = (Failure)source;
            try_ = this.resolver(failure.exception());
        } else {
            try_ = source;
        }
        return try_;
    }

    private <T> Try<T> resolver(Throwable throwable) {
        Try try_;
        if (throwable instanceof NonLocalReturnControl) {
            NonLocalReturnControl nonLocalReturnControl = (NonLocalReturnControl)throwable;
            try_ = new Success(nonLocalReturnControl.value());
        } else if (throwable instanceof ControlThrowable) {
            ControlThrowable controlThrowable = (ControlThrowable)((Object)throwable);
            try_ = new Failure(new ExecutionException("Boxed ControlThrowable", (Throwable)((Object)controlThrowable)));
        } else if (throwable instanceof InterruptedException) {
            InterruptedException interruptedException = (InterruptedException)throwable;
            try_ = new Failure(new ExecutionException("Boxed InterruptedException", interruptedException));
        } else if (throwable instanceof Error) {
            Error error2 = (Error)throwable;
            try_ = new Failure(new ExecutionException("Boxed Error", error2));
        } else {
            try_ = new Failure(throwable);
        }
        return try_;
    }

    private Promise$() {
        MODULE$ = this;
    }
}

