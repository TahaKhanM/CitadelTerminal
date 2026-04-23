/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.collection.AbstractIterable;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Range;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00012Q!\u0001\u0002\u0002\u0002\u001d\u00111\"\u00112tiJ\f7\r^*fc*\u00111\u0001B\u0001\u000bG>dG.Z2uS>t'\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001bD\n\u0004\u0001%I\u0002c\u0001\u0006\f\u001b5\t!!\u0003\u0002\r\u0005\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0003\u001d=a\u0001\u0001\u0002\u0004\u0011\u0001\u0011\u0015\r!\u0005\u0002\u0002\u0003F\u0011!C\u0006\t\u0003'Qi\u0011\u0001B\u0005\u0003+\u0011\u0011qAT8uQ&tw\r\u0005\u0002\u0014/%\u0011\u0001\u0004\u0002\u0002\u0004\u0003:L\bc\u0001\u0006\u001b\u001b%\u00111D\u0001\u0002\u0004'\u0016\f\b\"B\u000f\u0001\t\u0003q\u0012A\u0002\u001fj]&$h\bF\u0001 !\rQ\u0001!\u0004")
public abstract class AbstractSeq<A>
extends AbstractIterable<A>
implements Seq<A> {
    @Override
    public GenericCompanion<Seq> companion() {
        return Seq$class.companion(this);
    }

    @Override
    public Seq<A> seq() {
        return Seq$class.seq(this);
    }

    @Override
    public Seq<A> thisCollection() {
        return SeqLike$class.thisCollection(this);
    }

    @Override
    public Seq toCollection(Object repr) {
        return SeqLike$class.toCollection(this, repr);
    }

    @Override
    public Combiner<A, ParSeq<A>> parCombiner() {
        return SeqLike$class.parCombiner(this);
    }

    @Override
    public int lengthCompare(int len) {
        return SeqLike$class.lengthCompare(this, len);
    }

    @Override
    public boolean isEmpty() {
        return SeqLike$class.isEmpty(this);
    }

    @Override
    public int size() {
        return SeqLike$class.size(this);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return SeqLike$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return SeqLike$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return SeqLike$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Iterator<Seq<A>> permutations() {
        return SeqLike$class.permutations(this);
    }

    @Override
    public Iterator<Seq<A>> combinations(int n) {
        return SeqLike$class.combinations(this, n);
    }

    @Override
    public Object reverse() {
        return SeqLike$class.reverse(this);
    }

    @Override
    public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.reverseMap(this, f, bf);
    }

    @Override
    public Iterator<A> reverseIterator() {
        return SeqLike$class.reverseIterator(this);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that, int offset) {
        return SeqLike$class.startsWith(this, that, offset);
    }

    @Override
    public <B> boolean endsWith(GenSeq<B> that) {
        return SeqLike$class.endsWith(this, that);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that) {
        return SeqLike$class.indexOfSlice(this, that);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that, int from2) {
        return SeqLike$class.indexOfSlice(this, that, from2);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that) {
        return SeqLike$class.lastIndexOfSlice(this, that);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
        return SeqLike$class.lastIndexOfSlice(this, that, end);
    }

    @Override
    public <B> boolean containsSlice(GenSeq<B> that) {
        return SeqLike$class.containsSlice(this, that);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return SeqLike$class.contains(this, elem);
    }

    @Override
    public <B, That> That union(GenSeq<B> that, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.union(this, that, bf);
    }

    @Override
    public Object diff(GenSeq that) {
        return SeqLike$class.diff(this, that);
    }

    @Override
    public Object intersect(GenSeq that) {
        return SeqLike$class.intersect(this, that);
    }

    @Override
    public Object distinct() {
        return SeqLike$class.distinct(this);
    }

    @Override
    public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.patch(this, from2, patch2, replaced, bf);
    }

    @Override
    public <B, That> That updated(int index, B elem, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.updated(this, index, elem, bf);
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.$plus$colon(this, elem, bf);
    }

    @Override
    public <B, That> That $colon$plus(B elem, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.$colon$plus(this, elem, bf);
    }

    @Override
    public <B, That> That padTo(int len, B elem, CanBuildFrom<Seq<A>, B, That> bf) {
        return (That)SeqLike$class.padTo(this, len, elem, bf);
    }

    @Override
    public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return SeqLike$class.corresponds(this, that, p);
    }

    @Override
    public Object sortWith(Function2 lt) {
        return SeqLike$class.sortWith(this, lt);
    }

    @Override
    public Object sortBy(Function1 f, Ordering ord) {
        return SeqLike$class.sortBy(this, f, ord);
    }

    @Override
    public Object sorted(Ordering ord) {
        return SeqLike$class.sorted(this, ord);
    }

    @Override
    public Seq<A> toSeq() {
        return SeqLike$class.toSeq(this);
    }

    @Override
    public Range indices() {
        return SeqLike$class.indices(this);
    }

    @Override
    public Object view() {
        return SeqLike$class.view(this);
    }

    @Override
    public SeqView<A, Seq<A>> view(int from2, int until2) {
        return SeqLike$class.view(this, from2, until2);
    }

    @Override
    public String toString() {
        return SeqLike$class.toString(this);
    }

    @Override
    public boolean isDefinedAt(int idx) {
        return GenSeqLike$class.isDefinedAt(this, idx);
    }

    @Override
    public int prefixLength(Function1<A, Object> p) {
        return GenSeqLike$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<A, Object> p) {
        return GenSeqLike$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return GenSeqLike$class.indexOf(this, elem);
    }

    @Override
    public <B> int indexOf(B elem, int from2) {
        return GenSeqLike$class.indexOf(this, elem, from2);
    }

    @Override
    public <B> int lastIndexOf(B elem) {
        return GenSeqLike$class.lastIndexOf(this, elem);
    }

    @Override
    public <B> int lastIndexOf(B elem, int end) {
        return GenSeqLike$class.lastIndexOf(this, elem, end);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p) {
        return GenSeqLike$class.lastIndexWhere(this, p);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return GenSeqLike$class.startsWith(this, that);
    }

    @Override
    public int hashCode() {
        return GenSeqLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenSeqLike$class.equals(this, that);
    }

    @Override
    public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
        return PartialFunction$class.orElse(this, that);
    }

    @Override
    public <C> PartialFunction<Object, C> andThen(Function1<A, C> k) {
        return PartialFunction$class.andThen(this, k);
    }

    @Override
    public Function1<Object, Option<A>> lift() {
        return PartialFunction$class.lift(this);
    }

    @Override
    public Object applyOrElse(Object x, Function1 function1) {
        return PartialFunction$class.applyOrElse(this, x, function1);
    }

    @Override
    public <U> Function1<Object, Object> runWith(Function1<A, U> action) {
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
    public <A> Function1<A, A> compose(Function1<A, Object> g) {
        return Function1$class.compose(this, g);
    }

    public AbstractSeq() {
        Function1$class.$init$(this);
        PartialFunction$class.$init$(this);
        GenSeqLike$class.$init$(this);
        GenSeq$class.$init$(this);
        SeqLike$class.$init$(this);
        Seq$class.$init$(this);
    }
}

