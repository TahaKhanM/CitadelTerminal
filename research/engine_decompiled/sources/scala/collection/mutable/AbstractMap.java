/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$class;
import scala.collection.mutable.MapLike;
import scala.collection.mutable.MapLike$class;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00152Q!\u0001\u0002\u0002\u0002%\u00111\"\u00112tiJ\f7\r^'ba*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\rQ\u0001cG\n\u0004\u0001-i\u0002\u0003\u0002\u0007\u000e\u001dii\u0011\u0001B\u0005\u0003\u0003\u0011\u0001\"a\u0004\t\r\u0001\u0011)\u0011\u0003\u0001b\u0001%\t\t\u0011)\u0005\u0002\u0014/A\u0011A#F\u0007\u0002\r%\u0011aC\u0002\u0002\b\u001d>$\b.\u001b8h!\t!\u0002$\u0003\u0002\u001a\r\t\u0019\u0011I\\=\u0011\u0005=YB!\u0002\u000f\u0001\u0005\u0004\u0011\"!\u0001\"\u0011\tyybBG\u0007\u0002\u0005%\u0011\u0001E\u0001\u0002\u0004\u001b\u0006\u0004\b\"\u0002\u0012\u0001\t\u0003\u0019\u0013A\u0002\u001fj]&$h\bF\u0001%!\u0011q\u0002A\u0004\u000e")
public abstract class AbstractMap<A, B>
extends scala.collection.AbstractMap<A, B>
implements Map<A, B> {
    @Override
    public Map<A, B> empty() {
        return Map$class.empty(this);
    }

    @Override
    public Map<A, B> seq() {
        return Map$class.seq(this);
    }

    @Override
    public Map<A, B> withDefault(Function1<A, B> d) {
        return Map$class.withDefault(this, d);
    }

    @Override
    public Map<A, B> withDefaultValue(B d) {
        return Map$class.withDefaultValue(this, d);
    }

    @Override
    public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
        return MapLike$class.newBuilder(this);
    }

    @Override
    public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
        return MapLike$class.parCombiner(this);
    }

    @Override
    public Option<B> put(A key, B value) {
        return MapLike$class.put(this, key, value);
    }

    @Override
    public void update(A key, B value) {
        MapLike$class.update(this, key, value);
    }

    @Override
    public <B1> Map<A, B1> updated(A key, B1 value) {
        return MapLike$class.updated(this, key, value);
    }

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> kv) {
        return MapLike$class.$plus(this, kv);
    }

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
        return MapLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
        return MapLike$class.$plus$plus(this, xs);
    }

    @Override
    public Option<B> remove(A key) {
        return MapLike$class.remove(this, key);
    }

    @Override
    public Map<A, B> $minus(A key) {
        return MapLike$class.$minus(this, key);
    }

    @Override
    public void clear() {
        MapLike$class.clear(this);
    }

    @Override
    public B getOrElseUpdate(A key, Function0<B> op) {
        return (B)MapLike$class.getOrElseUpdate(this, key, op);
    }

    @Override
    public MapLike<A, B, Map<A, B>> transform(Function2<A, B, B> f) {
        return MapLike$class.transform(this, f);
    }

    @Override
    public MapLike<A, B, Map<A, B>> retain(Function2<A, B, Object> p) {
        return MapLike$class.retain(this, p);
    }

    @Override
    public Map<A, B> clone() {
        return MapLike$class.clone(this);
    }

    @Override
    public Map<A, B> result() {
        return MapLike$class.result(this);
    }

    @Override
    public Map<A, B> $minus(A elem1, A elem2, Seq<A> elems) {
        return MapLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public Map<A, B> $minus$minus(GenTraversableOnce<A> xs) {
        return MapLike$class.$minus$minus(this, xs);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Shrinkable<A> $minus$eq(A elem1, A elem2, Seq<A> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<A> $minus$minus$eq(TraversableOnce<A> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
    }

    @Override
    public void sizeHint(int size2) {
        Builder$class.sizeHint((Builder)this, size2);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1<Map<A, B>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce<Tuple2<A, B>> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public GenericCompanion<Iterable> companion() {
        return Iterable$class.companion(this);
    }

    public AbstractMap() {
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        MapLike$class.$init$(this);
        Map$class.$init$(this);
    }
}

