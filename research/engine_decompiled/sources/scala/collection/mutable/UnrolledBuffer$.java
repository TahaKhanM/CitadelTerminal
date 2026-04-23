/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ClassTagTraversableFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.UnrolledBuffer;
import scala.reflect.ClassTag;

public final class UnrolledBuffer$
extends ClassTagTraversableFactory<UnrolledBuffer>
implements Serializable {
    public static final UnrolledBuffer$ MODULE$;
    private final int waterline;
    private final int waterlineDelim;
    private final int unrolledlength;

    static {
        new UnrolledBuffer$();
    }

    public <T> CanBuildFrom<UnrolledBuffer<?>, T, UnrolledBuffer<T>> canBuildFrom(ClassTag<T> t) {
        return new ClassTagTraversableFactory.GenericCanBuildFrom<T>(this, t);
    }

    @Override
    public <T> Builder<T, UnrolledBuffer<T>> newBuilder(ClassTag<T> t) {
        return new UnrolledBuffer<T>(t);
    }

    public int waterline() {
        return this.waterline;
    }

    public int waterlineDelim() {
        return this.waterlineDelim;
    }

    public int unrolledlength() {
        return this.unrolledlength;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private UnrolledBuffer$() {
        MODULE$ = this;
        this.waterline = 50;
        this.waterlineDelim = 100;
        this.unrolledlength = 32;
    }
}

