/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IndexedSeq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.IndexedSeqFactory;
import scala.collection.mutable.Builder;
import scala.runtime.Nothing$;

public final class IndexedSeq$
extends IndexedSeqFactory<IndexedSeq> {
    public static final IndexedSeq$ MODULE$;
    private final GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF;

    static {
        new IndexedSeq$();
    }

    @Override
    public GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return this.ReusableCBF;
    }

    @Override
    public <A> Builder<A, IndexedSeq<A>> newBuilder() {
        return scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
    }

    public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    private IndexedSeq$() {
        MODULE$ = this;
        this.ReusableCBF = new GenTraversableFactory.GenericCanBuildFrom<Nothing$>(){

            public Builder<Nothing$, IndexedSeq<Nothing$>> apply() {
                return IndexedSeq$.MODULE$.newBuilder();
            }
        };
    }
}

