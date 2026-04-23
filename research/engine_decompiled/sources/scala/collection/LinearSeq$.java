/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.LinearSeq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;

public final class LinearSeq$
extends SeqFactory<LinearSeq> {
    public static final LinearSeq$ MODULE$;

    static {
        new LinearSeq$();
    }

    public <A> CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, LinearSeq<A>> newBuilder() {
        return scala.collection.immutable.LinearSeq$.MODULE$.newBuilder();
    }

    private LinearSeq$() {
        MODULE$ = this;
    }
}

