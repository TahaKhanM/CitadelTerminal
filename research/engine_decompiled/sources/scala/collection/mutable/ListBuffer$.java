/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowingBuilder;
import scala.collection.mutable.ListBuffer;

public final class ListBuffer$
extends SeqFactory<ListBuffer>
implements Serializable {
    public static final ListBuffer$ MODULE$;

    static {
        new ListBuffer$();
    }

    public <A> CanBuildFrom<ListBuffer<?>, A, ListBuffer<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, ListBuffer<A>> newBuilder() {
        return new GrowingBuilder(new ListBuffer());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ListBuffer$() {
        MODULE$ = this;
    }
}

