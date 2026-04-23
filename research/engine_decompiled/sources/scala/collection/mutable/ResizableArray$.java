/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ResizableArray;

public final class ResizableArray$
extends SeqFactory<ResizableArray> {
    public static final ResizableArray$ MODULE$;

    static {
        new ResizableArray$();
    }

    public <A> CanBuildFrom<ResizableArray<?>, A, ResizableArray<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, ResizableArray<A>> newBuilder() {
        return new ArrayBuffer();
    }

    private ResizableArray$() {
        MODULE$ = this;
    }
}

