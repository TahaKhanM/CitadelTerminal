/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.IndexedSeq;

public final class IndexedSeq$
extends SeqFactory<IndexedSeq> {
    public static final IndexedSeq$ MODULE$;

    static {
        new IndexedSeq$();
    }

    public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, IndexedSeq<A>> newBuilder() {
        return new ArrayBuffer();
    }

    private IndexedSeq$() {
        MODULE$ = this;
    }
}

