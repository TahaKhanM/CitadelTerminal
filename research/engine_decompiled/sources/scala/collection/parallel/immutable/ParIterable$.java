/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParIterable;
import scala.collection.parallel.immutable.ParVector$;

public final class ParIterable$
extends ParFactory<ParIterable> {
    public static final ParIterable$ MODULE$;

    static {
        new ParIterable$();
    }

    public <T> CanCombineFrom<ParIterable<?>, T, ParIterable<T>> canBuildFrom() {
        return new ParFactory.GenericCanCombineFrom(this);
    }

    @Override
    public <T> Combiner<T, ParIterable<T>> newBuilder() {
        return ParVector$.MODULE$.newBuilder();
    }

    @Override
    public <T> Combiner<T, ParIterable<T>> newCombiner() {
        return ParVector$.MODULE$.newCombiner();
    }

    private ParIterable$() {
        MODULE$ = this;
    }
}

