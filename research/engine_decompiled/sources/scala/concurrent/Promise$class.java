/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import scala.Serializable;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future$InternalCallbackExecutor$;
import scala.concurrent.Promise;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public abstract class Promise$class {
    private static ExecutionContext internalExecutor(Promise $this) {
        return Future$InternalCallbackExecutor$.MODULE$;
    }

    public static Promise complete(Promise $this, Try result2) {
        if ($this.tryComplete(result2)) {
            return $this;
        }
        throw new IllegalStateException("Promise already completed.");
    }

    public static final Promise completeWith(Promise $this, Future other) {
        return $this.tryCompleteWith(other);
    }

    public static final Promise tryCompleteWith(Promise $this, Future other) {
        other.onComplete(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Promise $outer;

            public final boolean apply(Try<T> x$1) {
                return this.$outer.tryComplete(x$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, Promise$class.internalExecutor($this));
        return $this;
    }

    public static Promise success(Promise $this, Object value) {
        return $this.complete(new Success<Object>(value));
    }

    public static boolean trySuccess(Promise $this, Object value) {
        return $this.tryComplete(new Success<Object>(value));
    }

    public static Promise failure(Promise $this, Throwable cause) {
        return $this.complete(new Failure(cause));
    }

    public static boolean tryFailure(Promise $this, Throwable cause) {
        return $this.tryComplete(new Failure(cause));
    }

    public static void $init$(Promise $this) {
    }
}

