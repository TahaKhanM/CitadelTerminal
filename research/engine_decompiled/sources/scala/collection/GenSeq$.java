/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenSeq;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.mutable.Builder;

public final class GenSeq$
extends GenTraversableFactory<GenSeq> {
    public static final GenSeq$ MODULE$;

    static {
        new GenSeq$();
    }

    public <A> GenTraversableFactory.GenericCanBuildFrom<A> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Seq<A>> newBuilder() {
        return Seq$.MODULE$.newBuilder();
    }

    private GenSeq$() {
        MODULE$ = this;
    }
}

