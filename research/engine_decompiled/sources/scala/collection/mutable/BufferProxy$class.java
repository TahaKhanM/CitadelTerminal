/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Proxy$class;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Buffer$class;
import scala.collection.mutable.BufferLike$class;
import scala.collection.mutable.BufferProxy;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.SeqLike;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSeq;
import scala.collection.script.Message;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class BufferProxy$class {
    public static int length(BufferProxy $this) {
        return $this.self().length();
    }

    public static Iterator iterator(BufferProxy $this) {
        return $this.self().iterator();
    }

    public static Object apply(BufferProxy $this, int n) {
        return $this.self().apply(n);
    }

    public static BufferProxy $plus$eq(BufferProxy $this, Object elem) {
        $this.self().$plus$eq(elem);
        return $this;
    }

    public static Seq readOnly(BufferProxy $this) {
        return $this.self().readOnly();
    }

    public static BufferProxy $plus$plus$eq(BufferProxy $this, TraversableOnce xs) {
        $this.self().$plus$plus$eq(xs);
        return $this;
    }

    public static void append(BufferProxy $this, Seq elems) {
        $this.self().$plus$plus$eq(elems);
    }

    public static void appendAll(BufferProxy $this, TraversableOnce xs) {
        $this.self().appendAll(xs);
    }

    public static BufferProxy $plus$eq$colon(BufferProxy $this, Object elem) {
        $this.self().$plus$eq$colon(elem);
        return $this;
    }

    public static BufferProxy $plus$plus$eq$colon(BufferProxy $this, TraversableOnce xs) {
        $this.self().$plus$plus$eq$colon(xs);
        return $this;
    }

    public static void prepend(BufferProxy $this, Seq elems) {
        $this.self().prependAll(elems);
    }

    public static void prependAll(BufferProxy $this, TraversableOnce xs) {
        $this.self().prependAll(xs);
    }

    public static void insert(BufferProxy $this, int n, Seq elems) {
        $this.self().insertAll(n, elems);
    }

    public static void insertAll(BufferProxy $this, int n, Iterable iter2) {
        $this.self().insertAll(n, iter2);
    }

    public static void insertAll(BufferProxy $this, int n, Traversable iter2) {
        $this.self().insertAll(n, iter2);
    }

    public static void update(BufferProxy $this, int n, Object newelem) {
        $this.self().update(n, newelem);
    }

    public static Object remove(BufferProxy $this, int n) {
        return $this.self().remove(n);
    }

    public static void clear(BufferProxy $this) {
        $this.self().clear();
    }

    public static void $less$less(BufferProxy $this, Message cmd) {
        $this.self().$less$less(cmd);
    }

    public static Buffer clone(BufferProxy $this) {
        return new BufferProxy<A>($this){
            private final /* synthetic */ BufferProxy $outer;

            public int length() {
                return BufferProxy$class.length(this);
            }

            public Iterator<A> iterator() {
                return BufferProxy$class.iterator(this);
            }

            public A apply(int n) {
                return (A)BufferProxy$class.apply(this, n);
            }

            public BufferProxy<A> $plus$eq(A elem) {
                return BufferProxy$class.$plus$eq(this, elem);
            }

            public Seq<A> readOnly() {
                return BufferProxy$class.readOnly(this);
            }

            public BufferProxy<A> $plus$plus$eq(TraversableOnce<A> xs) {
                return BufferProxy$class.$plus$plus$eq(this, xs);
            }

            public void append(Seq<A> elems) {
                BufferProxy$class.append(this, elems);
            }

            public void appendAll(TraversableOnce<A> xs) {
                BufferProxy$class.appendAll(this, xs);
            }

            public BufferProxy<A> $plus$eq$colon(A elem) {
                return BufferProxy$class.$plus$eq$colon(this, elem);
            }

            public BufferProxy<A> $plus$plus$eq$colon(TraversableOnce<A> xs) {
                return BufferProxy$class.$plus$plus$eq$colon(this, xs);
            }

            public void prepend(Seq<A> elems) {
                BufferProxy$class.prepend(this, elems);
            }

            public void prependAll(TraversableOnce<A> xs) {
                BufferProxy$class.prependAll(this, xs);
            }

            public void insert(int n, Seq<A> elems) {
                BufferProxy$class.insert(this, n, elems);
            }

            public void insertAll(int n, Iterable<A> iter2) {
                BufferProxy$class.insertAll((BufferProxy)this, n, iter2);
            }

            public void insertAll(int n, Traversable<A> iter2) {
                BufferProxy$class.insertAll((BufferProxy)this, n, iter2);
            }

            public void update(int n, A newelem) {
                BufferProxy$class.update(this, n, newelem);
            }

            public A remove(int n) {
                return (A)BufferProxy$class.remove(this, n);
            }

            public void clear() {
                BufferProxy$class.clear(this);
            }

            public void $less$less(Message<A> cmd) {
                BufferProxy$class.$less$less(this, cmd);
            }

            public Buffer<A> clone() {
                return BufferProxy$class.clone(this);
            }

            public int hashCode() {
                return Proxy$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return Proxy$class.equals(this, that);
            }

            public String toString() {
                return Proxy$class.toString(this);
            }

            public GenericCompanion<Buffer> companion() {
                return Buffer$class.companion(this);
            }

            public void remove(int n, int count2) {
                BufferLike$class.remove(this, n, count2);
            }

            public Buffer $minus$eq(Object x) {
                return BufferLike$class.$minus$eq(this, x);
            }

            public void trimStart(int n) {
                BufferLike$class.trimStart(this, n);
            }

            public void trimEnd(int n) {
                BufferLike$class.trimEnd(this, n);
            }

            public String stringPrefix() {
                return BufferLike$class.stringPrefix(this);
            }

            public Buffer<A> $plus$plus(GenTraversableOnce<A> xs) {
                return BufferLike$class.$plus$plus(this, xs);
            }

            public Buffer<A> $minus(A elem) {
                return BufferLike$class.$minus(this, elem);
            }

            public Buffer<A> $minus(A elem1, A elem2, Seq<A> elems) {
                return BufferLike$class.$minus(this, elem1, elem2, elems);
            }

            public Buffer<A> $minus$minus(GenTraversableOnce<A> xs) {
                return BufferLike$class.$minus$minus(this, xs);
            }

            public Shrinkable<A> $minus$eq(A elem1, A elem2, Seq<A> elems) {
                return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
            }

            public Shrinkable<A> $minus$minus$eq(TraversableOnce<A> xs) {
                return Shrinkable$class.$minus$minus$eq(this, xs);
            }

            public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public scala.collection.mutable.Seq<A> seq() {
                return scala.collection.mutable.Seq$class.seq(this);
            }

            public Combiner<A, ParSeq<A>> parCombiner() {
                return scala.collection.mutable.SeqLike$class.parCombiner(this);
            }

            public SeqLike<A, Buffer<A>> transform(Function1<A, A> f) {
                return scala.collection.mutable.SeqLike$class.transform(this, f);
            }

            public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
                return super.clone();
            }

            public Seq<A> thisCollection() {
                return SeqLike$class.thisCollection(this);
            }

            public Seq toCollection(Object repr) {
                return SeqLike$class.toCollection(this, repr);
            }

            public int lengthCompare(int len) {
                return SeqLike$class.lengthCompare(this, len);
            }

            public boolean isEmpty() {
                return SeqLike$class.isEmpty(this);
            }

            public int size() {
                return SeqLike$class.size(this);
            }

            public int segmentLength(Function1<A, Object> p, int from2) {
                return SeqLike$class.segmentLength(this, p, from2);
            }

            public int indexWhere(Function1<A, Object> p, int from2) {
                return SeqLike$class.indexWhere(this, p, from2);
            }

            public int lastIndexWhere(Function1<A, Object> p, int end) {
                return SeqLike$class.lastIndexWhere(this, p, end);
            }

            public Iterator<Buffer<A>> permutations() {
                return SeqLike$class.permutations(this);
            }

            public Iterator<Buffer<A>> combinations(int n) {
                return SeqLike$class.combinations(this, n);
            }

            public Object reverse() {
                return SeqLike$class.reverse(this);
            }

            public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.reverseMap(this, f, bf);
            }

            public Iterator<A> reverseIterator() {
                return SeqLike$class.reverseIterator(this);
            }

            public <B> boolean startsWith(GenSeq<B> that, int offset) {
                return SeqLike$class.startsWith(this, that, offset);
            }

            public <B> boolean endsWith(GenSeq<B> that) {
                return SeqLike$class.endsWith(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that) {
                return SeqLike$class.indexOfSlice(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that, int from2) {
                return SeqLike$class.indexOfSlice(this, that, from2);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that) {
                return SeqLike$class.lastIndexOfSlice(this, that);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
                return SeqLike$class.lastIndexOfSlice(this, that, end);
            }

            public <B> boolean containsSlice(GenSeq<B> that) {
                return SeqLike$class.containsSlice(this, that);
            }

            public <A1> boolean contains(A1 elem) {
                return SeqLike$class.contains(this, elem);
            }

            public <B, That> That union(GenSeq<B> that, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.union(this, that, bf);
            }

            public Object diff(GenSeq that) {
                return SeqLike$class.diff(this, that);
            }

            public Object intersect(GenSeq that) {
                return SeqLike$class.intersect(this, that);
            }

            public Object distinct() {
                return SeqLike$class.distinct(this);
            }

            public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.patch(this, from2, patch2, replaced, bf);
            }

            public <B, That> That updated(int index, B elem, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.updated(this, index, elem, bf);
            }

            public <B, That> That $plus$colon(B elem, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.$plus$colon(this, elem, bf);
            }

            public <B, That> That $colon$plus(B elem, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.$colon$plus(this, elem, bf);
            }

            public <B, That> That padTo(int len, B elem, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)SeqLike$class.padTo(this, len, elem, bf);
            }

            public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
                return SeqLike$class.corresponds(this, that, p);
            }

            public Object sortWith(Function2 lt) {
                return SeqLike$class.sortWith(this, lt);
            }

            public Object sortBy(Function1 f, Ordering ord) {
                return SeqLike$class.sortBy(this, f, ord);
            }

            public Object sorted(Ordering ord) {
                return SeqLike$class.sorted(this, ord);
            }

            public Seq<A> toSeq() {
                return SeqLike$class.toSeq(this);
            }

            public Range indices() {
                return SeqLike$class.indices(this);
            }

            public Object view() {
                return SeqLike$class.view(this);
            }

            public SeqView<A, Buffer<A>> view(int from2, int until2) {
                return SeqLike$class.view(this, from2, until2);
            }

            public boolean isDefinedAt(int idx) {
                return GenSeqLike$class.isDefinedAt(this, idx);
            }

            public int prefixLength(Function1<A, Object> p) {
                return GenSeqLike$class.prefixLength(this, p);
            }

            public int indexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return GenSeqLike$class.indexOf(this, elem);
            }

            public <B> int indexOf(B elem, int from2) {
                return GenSeqLike$class.indexOf(this, elem, from2);
            }

            public <B> int lastIndexOf(B elem) {
                return GenSeqLike$class.lastIndexOf(this, elem);
            }

            public <B> int lastIndexOf(B elem, int end) {
                return GenSeqLike$class.lastIndexOf(this, elem, end);
            }

            public int lastIndexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.lastIndexWhere(this, p);
            }

            public <B> boolean startsWith(GenSeq<B> that) {
                return GenSeqLike$class.startsWith(this, that);
            }

            public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<Object, C> andThen(Function1<A, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<Object, Option<A>> lift() {
                return PartialFunction$class.lift(this);
            }

            public Object applyOrElse(Object x, Function1 function1) {
                return PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<Object, Object> runWith(Function1<A, U> action) {
                return PartialFunction$class.runWith(this, action);
            }

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, A> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            public <U> void foreach(Function1<A, U> f) {
                IterableLike$class.foreach(this, f);
            }

            public boolean forall(Function1<A, Object> p) {
                return IterableLike$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return IterableLike$class.exists(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return IterableLike$class.find(this, p);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)IterableLike$class.foldRight(this, z, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)IterableLike$class.reduceRight(this, op);
            }

            public Iterable<A> toIterable() {
                return IterableLike$class.toIterable(this);
            }

            public Iterator<A> toIterator() {
                return IterableLike$class.toIterator(this);
            }

            public A head() {
                return (A)IterableLike$class.head(this);
            }

            public Object slice(int from2, int until2) {
                return IterableLike$class.slice(this, from2, until2);
            }

            public Object take(int n) {
                return IterableLike$class.take(this, n);
            }

            public Object drop(int n) {
                return IterableLike$class.drop(this, n);
            }

            public Object takeWhile(Function1 p) {
                return IterableLike$class.takeWhile(this, p);
            }

            public Iterator<Buffer<A>> grouped(int size2) {
                return IterableLike$class.grouped(this, size2);
            }

            public Iterator<Buffer<A>> sliding(int size2) {
                return IterableLike$class.sliding(this, size2);
            }

            public Iterator<Buffer<A>> sliding(int size2, int step) {
                return IterableLike$class.sliding(this, size2, step);
            }

            public Object takeRight(int n) {
                return IterableLike$class.takeRight(this, n);
            }

            public Object dropRight(int n) {
                return IterableLike$class.dropRight(this, n);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                IterableLike$class.copyToArray(this, xs, start, len);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Buffer<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<Buffer<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<Buffer<A>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableLike$class.sameElements(this, that);
            }

            public Stream<A> toStream() {
                return IterableLike$class.toStream(this);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public Builder<A, Buffer<A>> newBuilder() {
                return GenericTraversableTemplate$class.newBuilder(this);
            }

            public <B> Builder<B, Buffer<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<Buffer<A1>, Buffer<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<Buffer<A1>, Buffer<A2>, Buffer<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return GenericTraversableTemplate$class.unzip3(this, asTriple);
            }

            public GenTraversable flatten(Function1 asTraversable) {
                return GenericTraversableTemplate$class.flatten(this, asTraversable);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public Object repr() {
                return TraversableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus(this, that, bf);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.flatMap(this, f, bf);
            }

            public Object filter(Function1 p) {
                return TraversableLike$class.filter(this, p);
            }

            public Object filterNot(Function1 p) {
                return TraversableLike$class.filterNot(this, p);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.collect(this, pf, bf);
            }

            public Tuple2<Buffer<A>, Buffer<A>> partition(Function1<A, Object> p) {
                return TraversableLike$class.partition(this, p);
            }

            public <K> Map<K, Buffer<A>> groupBy(Function1<A, K> f) {
                return TraversableLike$class.groupBy(this, f);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Buffer<A>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<Buffer<A>, B, That> bf) {
                return (That)TraversableLike$class.scanRight(this, z, op, bf);
            }

            public Option<A> headOption() {
                return TraversableLike$class.headOption(this);
            }

            public Object tail() {
                return TraversableLike$class.tail(this);
            }

            public A last() {
                return (A)TraversableLike$class.last(this);
            }

            public Option<A> lastOption() {
                return TraversableLike$class.lastOption(this);
            }

            public Object init() {
                return TraversableLike$class.init(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Object dropWhile(Function1 p) {
                return TraversableLike$class.dropWhile(this, p);
            }

            public Tuple2<Buffer<A>, Buffer<A>> span(Function1<A, Object> p) {
                return TraversableLike$class.span(this, p);
            }

            public Tuple2<Buffer<A>, Buffer<A>> splitAt(int n) {
                return TraversableLike$class.splitAt(this, n);
            }

            public Iterator<Buffer<A>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<Buffer<A>> inits() {
                return TraversableLike$class.inits(this);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<A, Buffer<A>> withFilter(Function1<A, Object> p) {
                return TraversableLike$class.withFilter(this, p);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableOnce$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableOnce$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableOnce$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableOnce$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public Buffer<A> self() {
                return this.$outer.self().clone();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                scala.collection.Traversable$class.$init$(this);
                Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                scala.collection.Iterable$class.$init$(this);
                Iterable$class.$init$(this);
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                GenSeqLike$class.$init$(this);
                GenSeq$class.$init$(this);
                SeqLike$class.$init$(this);
                Seq$class.$init$(this);
                Cloneable$class.$init$(this);
                scala.collection.mutable.SeqLike$class.$init$(this);
                scala.collection.mutable.Seq$class.$init$(this);
                Growable$class.$init$(this);
                Shrinkable$class.$init$(this);
                Subtractable$class.$init$(this);
                BufferLike$class.$init$(this);
                Buffer$class.$init$(this);
                Proxy$class.$init$(this);
                BufferProxy$class.$init$(this);
            }
        };
    }

    public static void $init$(BufferProxy $this) {
    }
}

