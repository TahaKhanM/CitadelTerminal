/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.concurrent.duration.Deadline;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration;

public final class Deadline$
implements Serializable {
    public static final Deadline$ MODULE$;

    static {
        new Deadline$();
    }

    public Deadline now() {
        return this.apply(Duration$.MODULE$.apply(System.nanoTime(), TimeUnit.NANOSECONDS));
    }

    public Deadline apply(FiniteDuration time) {
        return new Deadline(time);
    }

    public Option<FiniteDuration> unapply(Deadline x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<FiniteDuration>(x$0.time());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Deadline$() {
        MODULE$ = this;
    }
}

