/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;

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
        return new ListBuffer();
    }

    private Seq$() {
        MODULE$ = this;
    }
}

