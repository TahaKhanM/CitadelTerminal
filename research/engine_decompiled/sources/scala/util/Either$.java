/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function0;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class Either$ {
    public static final Either$ MODULE$;

    static {
        new Either$();
    }

    public <A> Either<A, A> MergeableEither(Either<A, A> x) {
        return x;
    }

    public <A, B> Either<A, B> cond(boolean test, Function0<B> right, Function0<A> left) {
        return test ? new Right(right.apply()) : new Left(left.apply());
    }

    private Either$() {
        MODULE$ = this;
    }
}

