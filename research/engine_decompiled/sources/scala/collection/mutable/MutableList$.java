/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.MutableList;

public final class MutableList$
extends SeqFactory<MutableList>
implements Serializable {
    public static final MutableList$ MODULE$;

    static {
        new MutableList$();
    }

    public <A> CanBuildFrom<MutableList<?>, A, MutableList<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, MutableList<A>> newBuilder() {
        return new MutableList();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private MutableList$() {
        MODULE$ = this;
    }
}

