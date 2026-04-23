/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Function1$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.mutable.AbstractIterable;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Set$class;
import scala.collection.mutable.SetLike$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSet;
import scala.collection.script.Message;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t2Q!\u0001\u0002\u0002\u0002%\u00111\"\u00112tiJ\f7\r^*fi*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQ\u0011cE\u0002\u0001\u0017m\u00012\u0001D\u0007\u0010\u001b\u0005\u0011\u0011B\u0001\b\u0003\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sC\ndW\r\u0005\u0002\u0011#1\u0001A!\u0002\n\u0001\u0005\u0004\u0019\"!A!\u0012\u0005QA\u0002CA\u000b\u0017\u001b\u00051\u0011BA\f\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!F\r\n\u0005i1!aA!osB\u0019A\u0002H\b\n\u0005u\u0011!aA*fi\")q\u0004\u0001C\u0001A\u00051A(\u001b8jiz\"\u0012!\t\t\u0004\u0019\u0001y\u0001")
public abstract class AbstractSet<A>
extends AbstractIterable<A>
implements scala.collection.mutable.Set<A> {
    @Override
    public GenericCompanion<scala.collection.mutable.Set> companion() {
        return Set$class.companion(this);
    }

    @Override
    public scala.collection.mutable.Set<A> seq() {
        return Set$class.seq(this);
    }

    @Override
    public Builder<A, scala.collection.mutable.Set<A>> newBuilder() {
        return SetLike$class.newBuilder(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return SetLike$class.parCombiner(this);
    }

    @Override
    public boolean add(A elem) {
        return SetLike$class.add(this, elem);
    }

    @Override
    public boolean remove(A elem) {
        return SetLike$class.remove(this, elem);
    }

    @Override
    public void update(A elem, boolean included) {
        SetLike$class.update(this, elem, included);
    }

    @Override
    public void retain(Function1<A, Object> p) {
        SetLike$class.retain(this, p);
    }

    @Override
    public void clear() {
        SetLike$class.clear(this);
    }

    @Override
    public scala.collection.mutable.Set<A> clone() {
        return SetLike$class.clone(this);
    }

    @Override
    public scala.collection.mutable.Set<A> result() {
        return SetLike$class.result(this);
    }

    @Override
    public scala.collection.mutable.Set<A> $plus(A elem) {
        return SetLike$class.$plus(this, elem);
    }

    @Override
    public scala.collection.mutable.Set<A> $plus(A elem1, A elem2, Seq<A> elems) {
        return SetLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set<A> $plus$plus(GenTraversableOnce<A> xs) {
        return SetLike$class.$plus$plus(this, xs);
    }

    @Override
    public scala.collection.mutable.Set<A> $minus(A elem) {
        return SetLike$class.$minus(this, elem);
    }

    @Override
    public scala.collection.mutable.Set<A> $minus(A elem1, A elem2, Seq<A> elems) {
        return SetLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set<A> $minus$minus(GenTraversableOnce<A> xs) {
        return SetLike$class.$minus$minus(this, xs);
    }

    @Override
    public void $less$less(Message<A> cmd) {
        SetLike$class.$less$less(this, cmd);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<scala.collection.mutable.Set<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<A> $plus$plus$eq(TraversableOnce<A> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
        return TraversableLike$class.map(this, f, bf);
    }

    @Override
    public Seq<A> toSeq() {
        return scala.collection.SetLike$class.toSeq(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return scala.collection.SetLike$class.toBuffer(this);
    }

    @Override
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)scala.collection.SetLike$class.map(this, f, bf);
    }

    @Override
    public boolean isEmpty() {
        return scala.collection.SetLike$class.isEmpty(this);
    }

    @Override
    public Set union(GenSet that) {
        return scala.collection.SetLike$class.union(this, that);
    }

    @Override
    public Set diff(GenSet that) {
        return scala.collection.SetLike$class.diff(this, that);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> subsets(int len) {
        return scala.collection.SetLike$class.subsets(this, len);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> subsets() {
        return scala.collection.SetLike$class.subsets(this);
    }

    @Override
    public String stringPrefix() {
        return scala.collection.SetLike$class.stringPrefix(this);
    }

    @Override
    public String toString() {
        return scala.collection.SetLike$class.toString(this);
    }

    @Override
    public GenSet empty() {
        return GenericSetTemplate$class.empty(this);
    }

    @Override
    public boolean apply(A elem) {
        return GenSetLike$class.apply(this, elem);
    }

    @Override
    public Object intersect(GenSet that) {
        return GenSetLike$class.intersect(this, that);
    }

    @Override
    public Object $amp(GenSet that) {
        return GenSetLike$class.$amp(this, that);
    }

    @Override
    public Object $bar(GenSet that) {
        return GenSetLike$class.$bar(this, that);
    }

    @Override
    public Object $amp$tilde(GenSet that) {
        return GenSetLike$class.$amp$tilde(this, that);
    }

    @Override
    public boolean subsetOf(GenSet<A> that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public boolean equals(Object that) {
        return GenSetLike$class.equals(this, that);
    }

    @Override
    public int hashCode() {
        return GenSetLike$class.hashCode(this);
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
    public <A> Function1<A, Object> compose(Function1<A, A> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<A, A> andThen(Function1<Object, A> g) {
        return Function1$class.andThen(this, g);
    }

    public AbstractSet() {
        Function1$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        Subtractable$class.$init$(this);
        scala.collection.SetLike$class.$init$(this);
        scala.collection.Set$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        SetLike$class.$init$(this);
        Set$class.$init$(this);
    }
}

