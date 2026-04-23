/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.mutable.package$;

public final class ParSeq$
extends ParFactory<ParSeq> {
    public static final ParSeq$ MODULE$;

    static {
        new ParSeq$();
    }

    public <T> CanCombineFrom<ParSeq<?>, T, ParSeq<T>> canBuildFrom() {
        return new ParFactory.GenericCanCombineFrom(this);
    }

    @Override
    public <T> Combiner<T, ParSeq<T>> newBuilder() {
        return package$.MODULE$.ParArrayCombiner().apply();
    }

    @Override
    public <T> Combiner<T, ParSeq<T>> newCombiner() {
        return package$.MODULE$.ParArrayCombiner().apply();
    }

    private ParSeq$() {
        MODULE$ = this;
    }
}

