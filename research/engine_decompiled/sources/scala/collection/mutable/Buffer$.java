/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;

public final class Buffer$
extends SeqFactory<Buffer> {
    public static final Buffer$ MODULE$;

    static {
        new Buffer$();
    }

    public <A> CanBuildFrom<Buffer<?>, A, Buffer<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Buffer<A>> newBuilder() {
        return new ArrayBuffer();
    }

    private Buffer$() {
        MODULE$ = this;
    }
}

