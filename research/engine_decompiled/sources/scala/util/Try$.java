/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function0;
import scala.Option;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.control.NonFatal$;

public final class Try$ {
    public static final Try$ MODULE$;

    static {
        new Try$();
    }

    public <T> Try<T> apply(Function0<T> r) {
        Try try_;
        try {
            try_ = new Success<T>(r.apply());
        }
        catch (Throwable throwable) {
            Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
            if (option.isEmpty()) {
                throw throwable;
            }
            try_ = new Failure(option.get());
        }
        return try_;
    }

    private Try$() {
        MODULE$ = this;
    }
}

