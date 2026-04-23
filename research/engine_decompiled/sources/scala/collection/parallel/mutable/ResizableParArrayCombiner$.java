/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Function1;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Combiner$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.mutable.ExposedArrayBuffer;
import scala.collection.parallel.mutable.LazyCombiner;
import scala.collection.parallel.mutable.LazyCombiner$class;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ResizableParArrayCombiner;
import scala.collection.parallel.mutable.ResizableParArrayCombiner$class;
import scala.runtime.TraitSetter;

public final class ResizableParArrayCombiner$ {
    public static final ResizableParArrayCombiner$ MODULE$;

    static {
        new ResizableParArrayCombiner$();
    }

    public <T> ResizableParArrayCombiner<T> apply(ArrayBuffer<ExposedArrayBuffer<T>> c) {
        return new ResizableParArrayCombiner<T>(c){
            private final ArrayBuffer<ExposedArrayBuffer<T>> chain;
            private final Growable lastbuff;
            private volatile transient TaskSupport _combinerTaskSupport;

            public void sizeHint(int sz) {
                ResizableParArrayCombiner$class.sizeHint(this, sz);
            }

            public final ResizableParArrayCombiner<T> newLazyCombiner(ArrayBuffer<ExposedArrayBuffer<T>> c) {
                return ResizableParArrayCombiner$class.newLazyCombiner(this, c);
            }

            public ParArray<T> allocateAndCopy() {
                return ResizableParArrayCombiner$class.allocateAndCopy(this);
            }

            public String toString() {
                return ResizableParArrayCombiner$class.toString(this);
            }

            public Growable lastbuff() {
                return this.lastbuff;
            }

            public void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(Growable x$1) {
                this.lastbuff = x$1;
            }

            public LazyCombiner<T, ParArray<T>, ExposedArrayBuffer<T>> $plus$eq(T elem) {
                return LazyCombiner$class.$plus$eq(this, elem);
            }

            public Object result() {
                return LazyCombiner$class.result(this);
            }

            public void clear() {
                LazyCombiner$class.clear(this);
            }

            public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> other) {
                return LazyCombiner$class.combine(this, other);
            }

            public int size() {
                return LazyCombiner$class.size(this);
            }

            public TaskSupport _combinerTaskSupport() {
                return this._combinerTaskSupport;
            }

            @TraitSetter
            public void _combinerTaskSupport_$eq(TaskSupport x$1) {
                this._combinerTaskSupport = x$1;
            }

            public TaskSupport combinerTaskSupport() {
                return Combiner$class.combinerTaskSupport(this);
            }

            public void combinerTaskSupport_$eq(TaskSupport cts) {
                Combiner$class.combinerTaskSupport_$eq(this, cts);
            }

            public boolean canBeShared() {
                return Combiner$class.canBeShared(this);
            }

            public Object resultWithTaskSupport() {
                return Combiner$class.resultWithTaskSupport(this);
            }

            public void sizeHint(TraversableLike<?, ?> coll) {
                Builder$class.sizeHint((Builder)this, coll);
            }

            public void sizeHint(TraversableLike<?, ?> coll, int delta) {
                Builder$class.sizeHint(this, coll, delta);
            }

            public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
                Builder$class.sizeHintBounded(this, size2, boundingColl);
            }

            public <NewTo> Builder<T, NewTo> mapResult(Function1<ParArray<T>, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable<T> $plus$eq(T elem1, T elem2, Seq<T> elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Growable<T> $plus$plus$eq(TraversableOnce<T> xs) {
                return Growable$class.$plus$plus$eq(this, xs);
            }

            public ArrayBuffer<ExposedArrayBuffer<T>> chain() {
                return this.chain;
            }
            {
                this.chain = c$1;
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Combiner$class.$init$(this);
                LazyCombiner$class.$init$(this);
                ResizableParArrayCombiner$class.$init$(this);
            }
        };
    }

    public <T> ResizableParArrayCombiner<T> apply() {
        return this.apply((ArrayBuffer<ExposedArrayBuffer<T>>)new ArrayBuffer().$plus$eq(new ExposedArrayBuffer()));
    }

    private ResizableParArrayCombiner$() {
        MODULE$ = this;
    }
}

