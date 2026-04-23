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
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Combiner$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.mutable.DoublingUnrolledBuffer;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.UnrolledParArrayCombiner;
import scala.collection.parallel.mutable.UnrolledParArrayCombiner$class;
import scala.runtime.TraitSetter;

public final class UnrolledParArrayCombiner$ {
    public static final UnrolledParArrayCombiner$ MODULE$;

    static {
        new UnrolledParArrayCombiner$();
    }

    public <T> UnrolledParArrayCombiner<T> apply() {
        return new UnrolledParArrayCombiner<T>(){
            private final DoublingUnrolledBuffer<Object> buff;
            private volatile transient TaskSupport _combinerTaskSupport;

            public DoublingUnrolledBuffer<Object> buff() {
                return this.buff;
            }

            public void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(DoublingUnrolledBuffer x$1) {
                this.buff = x$1;
            }

            public UnrolledParArrayCombiner<T> $plus$eq(T elem) {
                return UnrolledParArrayCombiner$class.$plus$eq(this, elem);
            }

            public ParArray<T> result() {
                return UnrolledParArrayCombiner$class.result(this);
            }

            public void clear() {
                UnrolledParArrayCombiner$class.clear(this);
            }

            public void sizeHint(int sz) {
                UnrolledParArrayCombiner$class.sizeHint(this, sz);
            }

            public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> other) {
                return UnrolledParArrayCombiner$class.combine(this, other);
            }

            public int size() {
                return UnrolledParArrayCombiner$class.size(this);
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
            {
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Combiner$class.$init$(this);
                UnrolledParArrayCombiner$class.$init$(this);
            }
        };
    }

    private UnrolledParArrayCombiner$() {
        MODULE$ = this;
    }
}

