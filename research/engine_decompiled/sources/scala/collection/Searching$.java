/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Searching;
import scala.collection.generic.IsSeqLike;

public final class Searching$ {
    public static final Searching$ MODULE$;

    static {
        new Searching$();
    }

    public <Repr, A> Searching.SearchImpl<Object, Repr> search(Repr coll, IsSeqLike<Repr> fr) {
        return new Searching.SearchImpl<Object, Repr>(fr.conversion().apply(coll));
    }

    private Searching$() {
        MODULE$ = this;
    }
}

