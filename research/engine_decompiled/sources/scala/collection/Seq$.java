/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;

public final class Seq$
extends SeqFactory<Seq> {
    public static final Seq$ MODULE$;

    static {
        new Seq$();
    }

    public <A> CanBuildFrom<Seq<?>, A, Seq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Seq<A>> newBuilder() {
        return scala.collection.immutable.Seq$.MODULE$.newBuilder();
    }

    private Seq$() {
        MODULE$ = this;
    }
}

