/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinearSeq;
import scala.collection.mutable.MutableList;

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
        return new MutableList();
    }

    private LinearSeq$() {
        MODULE$ = this;
    }
}

