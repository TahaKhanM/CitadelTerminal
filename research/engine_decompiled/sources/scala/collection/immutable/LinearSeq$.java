/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.LinearSeq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;

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
        return new ListBuffer();
    }

    private LinearSeq$() {
        MODULE$ = this;
    }
}

