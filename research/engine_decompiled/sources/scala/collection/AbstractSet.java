/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function1$class;
import scala.collection.AbstractIterable;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Set$class;
import scala.collection.SetLike$class;
import scala.collection.TraversableLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.Subtractable;
import scala.collection.generic.Subtractable$class;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00012Q!\u0001\u0002\u0002\u0002\u001d\u00111\"\u00112tiJ\f7\r^*fi*\u00111\u0001B\u0001\u000bG>dG.Z2uS>t'\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001bD\n\u0004\u0001%I\u0002c\u0001\u0006\f\u001b5\t!!\u0003\u0002\r\u0005\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0003\u001d=a\u0001\u0001B\u0003\u0011\u0001\t\u0007\u0011CA\u0001B#\t\u0011b\u0003\u0005\u0002\u0014)5\tA!\u0003\u0002\u0016\t\t9aj\u001c;iS:<\u0007CA\n\u0018\u0013\tABAA\u0002B]f\u00042A\u0003\u000e\u000e\u0013\tY\"AA\u0002TKRDQ!\b\u0001\u0005\u0002y\ta\u0001P5oSRtD#A\u0010\u0011\u0007)\u0001Q\u0002")
public abstract class AbstractSet<A>
extends AbstractIterable<A>
implements Set<A> {
    @Override
    public GenericCompanion<Set> companion() {
        return Set$class.companion(this);
    }

    @Override
    public Set<A> seq() {
        return Set$class.seq(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
        return TraversableLike$class.map(this, f, bf);
    }

    @Override
    public Builder<A, Set<A>> newBuilder() {
        return SetLike$class.newBuilder(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return SetLike$class.parCombiner(this);
    }

    @Override
    public Seq<A> toSeq() {
        return SetLike$class.toSeq(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return SetLike$class.toBuffer(this);
    }

    @Override
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<Set<A>, B, That> bf) {
        return (That)SetLike$class.map(this, f, bf);
    }

    @Override
    public Set<A> $plus(A elem1, A elem2, Seq<A> elems) {
        return SetLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public Set<A> $plus$plus(GenTraversableOnce<A> elems) {
        return SetLike$class.$plus$plus(this, elems);
    }

    @Override
    public boolean isEmpty() {
        return SetLike$class.isEmpty(this);
    }

    @Override
    public Set<A> union(GenSet<A> that) {
        return SetLike$class.union(this, that);
    }

    @Override
    public Set<A> diff(GenSet<A> that) {
        return SetLike$class.diff(this, that);
    }

    @Override
    public Iterator<Set<A>> subsets(int len) {
        return SetLike$class.subsets(this, len);
    }

    @Override
    public Iterator<Set<A>> subsets() {
        return SetLike$class.subsets(this);
    }

    @Override
    public String stringPrefix() {
        return SetLike$class.stringPrefix(this);
    }

    @Override
    public String toString() {
        return SetLike$class.toString(this);
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
        SetLike$class.$init$(this);
        Set$class.$init$(this);
    }
}

