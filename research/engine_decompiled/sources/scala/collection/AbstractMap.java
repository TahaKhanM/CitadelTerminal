/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Map$class;
import scala.collection.MapLike$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.generic.Subtractable;
import scala.collection.generic.Subtractable$class;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParMap;
import scala.reflect.ScalaSignature;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\u00192Q!\u0001\u0002\u0002\u0002\u001d\u00111\"\u00112tiJ\f7\r^'ba*\u00111\u0001B\u0001\u000bG>dG.Z2uS>t'\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019\u0001bE\u000f\u0014\u0007\u0001Iq\u0004E\u0002\u000b\u00175i\u0011AA\u0005\u0003\u0019\t\u0011\u0001#\u00112tiJ\f7\r^%uKJ\f'\r\\3\u0011\t9y\u0011\u0003H\u0007\u0002\t%\u0011\u0001\u0003\u0002\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005I\u0019B\u0002\u0001\u0003\u0006)\u0001\u0011\r!\u0006\u0002\u0002\u0003F\u0011a#\u0007\t\u0003\u001d]I!\u0001\u0007\u0003\u0003\u000f9{G\u000f[5oOB\u0011aBG\u0005\u00037\u0011\u00111!\u00118z!\t\u0011R\u0004\u0002\u0004\u001f\u0001\u0011\u0015\r!\u0006\u0002\u0002\u0005B!!\u0002I\t\u001d\u0013\t\t#AA\u0002NCBDQa\t\u0001\u0005\u0002\u0011\na\u0001P5oSRtD#A\u0013\u0011\t)\u0001\u0011\u0003\b")
public abstract class AbstractMap<A, B>
extends AbstractIterable<Tuple2<A, B>>
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
    public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
        return MapLike$class.newBuilder(this);
    }

    @Override
    public boolean isEmpty() {
        return MapLike$class.isEmpty(this);
    }

    @Override
    public <B1> B1 getOrElse(A key, Function0<B1> function0) {
        return (B1)MapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public B apply(A key) {
        return (B)MapLike$class.apply(this, key);
    }

    @Override
    public boolean contains(A key) {
        return MapLike$class.contains(this, key);
    }

    @Override
    public boolean isDefinedAt(A key) {
        return MapLike$class.isDefinedAt(this, key);
    }

    @Override
    public Set<A> keySet() {
        return MapLike$class.keySet(this);
    }

    @Override
    public Iterator<A> keysIterator() {
        return MapLike$class.keysIterator(this);
    }

    @Override
    public Iterable<A> keys() {
        return MapLike$class.keys(this);
    }

    @Override
    public Iterable<B> values() {
        return MapLike$class.values(this);
    }

    @Override
    public Iterator<B> valuesIterator() {
        return MapLike$class.valuesIterator(this);
    }

    @Override
    public B default(A key) {
        return (B)MapLike$class.default(this, key);
    }

    @Override
    public Map<A, B> filterKeys(Function1<A, Object> p) {
        return MapLike$class.filterKeys(this, p);
    }

    @Override
    public <C> Map<A, C> mapValues(Function1<B, C> f) {
        return MapLike$class.mapValues(this, f);
    }

    @Override
    public <B1> Map<A, B1> updated(A key, B1 value) {
        return MapLike$class.updated(this, key, value);
    }

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> kv1, Tuple2<A, B1> kv2, Seq<Tuple2<A, B1>> kvs) {
        return MapLike$class.$plus(this, kv1, kv2, kvs);
    }

    @Override
    public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
        return MapLike$class.$plus$plus(this, xs);
    }

    @Override
    public Map<A, B> filterNot(Function1<Tuple2<A, B>, Object> p) {
        return MapLike$class.filterNot(this, p);
    }

    @Override
    public Seq<Tuple2<A, B>> toSeq() {
        return MapLike$class.toSeq(this);
    }

    @Override
    public <C> Buffer<C> toBuffer() {
        return MapLike$class.toBuffer(this);
    }

    @Override
    public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
        return MapLike$class.parCombiner(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return MapLike$class.addString(this, b, start, sep, end);
    }

    @Override
    public String stringPrefix() {
        return MapLike$class.stringPrefix(this);
    }

    @Override
    public String toString() {
        return MapLike$class.toString(this);
    }

    @Override
    public Subtractable $minus(Object elem1, Object elem2, Seq elems) {
        return Subtractable$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public Subtractable $minus$minus(GenTraversableOnce xs) {
        return Subtractable$class.$minus$minus(this, xs);
    }

    @Override
    public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
        return PartialFunction$class.orElse(this, that);
    }

    @Override
    public <C> PartialFunction<A, C> andThen(Function1<B, C> k) {
        return PartialFunction$class.andThen(this, k);
    }

    @Override
    public Function1<A, Option<B>> lift() {
        return PartialFunction$class.lift(this);
    }

    @Override
    public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
        return (B1)PartialFunction$class.applyOrElse(this, x, function1);
    }

    @Override
    public <U> Function1<A, Object> runWith(Function1<B, U> action) {
        return PartialFunction$class.runWith(this, action);
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, B> compose(Function1<A, A> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public int hashCode() {
        return GenMapLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenMapLike$class.equals(this, that);
    }

    public AbstractMap() {
        GenMapLike$class.$init$(this);
        Function1$class.$init$(this);
        PartialFunction$class.$init$(this);
        Subtractable$class.$init$(this);
        MapLike$class.$init$(this);
        Map$class.$init$(this);
    }
}

