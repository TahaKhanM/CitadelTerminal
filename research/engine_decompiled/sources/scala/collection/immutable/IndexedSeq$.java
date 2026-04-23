/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.IndexedSeqFactory;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.Builder;

public final class IndexedSeq$
extends IndexedSeqFactory<IndexedSeq> {
    public static final IndexedSeq$ MODULE$;

    static {
        new IndexedSeq$();
    }

    @Override
    public <A> Builder<A, IndexedSeq<A>> newBuilder() {
        return Vector$.MODULE$.newBuilder();
    }

    public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    private IndexedSeq$() {
        MODULE$ = this;
    }
}

