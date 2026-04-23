/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Seq;

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
        return new ArrayBuffer();
    }

    private Seq$() {
        MODULE$ = this;
    }
}

