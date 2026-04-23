/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.IndexedSeqFactory;
import scala.collection.immutable.Vector;
import scala.collection.immutable.VectorBuilder;
import scala.collection.mutable.Builder;
import scala.runtime.Nothing$;

public final class Vector$
extends IndexedSeqFactory<Vector>
implements Serializable {
    public static final Vector$ MODULE$;
    private final Vector<Nothing$> NIL;
    private final int Log2ConcatFaster;
    private final int TinyAppendFaster;

    static {
        new Vector$();
    }

    @Override
    public <A> Builder<A, Vector<A>> newBuilder() {
        return new VectorBuilder();
    }

    public <A> CanBuildFrom<Vector<?>, A, Vector<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    public Vector<Nothing$> NIL() {
        return this.NIL;
    }

    @Override
    public <A> Vector<A> empty() {
        return this.NIL();
    }

    private final int Log2ConcatFaster() {
        return 5;
    }

    private final int TinyAppendFaster() {
        return 2;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Vector$() {
        MODULE$ = this;
        this.NIL = new Vector(0, 0, 0);
    }
}

