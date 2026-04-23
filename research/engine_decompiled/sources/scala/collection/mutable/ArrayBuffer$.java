/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;

public final class ArrayBuffer$
extends SeqFactory<ArrayBuffer>
implements Serializable {
    public static final ArrayBuffer$ MODULE$;

    static {
        new ArrayBuffer$();
    }

    public <A> CanBuildFrom<ArrayBuffer<?>, A, ArrayBuffer<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, ArrayBuffer<A>> newBuilder() {
        return new ArrayBuffer();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ArrayBuffer$() {
        MODULE$ = this;
    }
}

