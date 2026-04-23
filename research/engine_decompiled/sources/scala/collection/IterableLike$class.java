/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike;
import scala.collection.IterableLike$;
import scala.collection.IterableView;
import scala.collection.IterableViewLike;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class IterableLike$class {
    public static Iterable thisCollection(IterableLike $this) {
        return (Iterable)$this;
    }

    public static Iterable toCollection(IterableLike $this, Object repr) {
        return (Iterable)repr;
    }

    public static void foreach(IterableLike $this, Function1 f) {
        $this.iterator().foreach(f);
    }

    public static boolean forall(IterableLike $this, Function1 p) {
        return $this.iterator().forall(p);
    }

    public static boolean exists(IterableLike $this, Function1 p) {
        return $this.iterator().exists(p);
    }

    public static Option find(IterableLike $this, Function1 p) {
        return $this.iterator().find(p);
    }

    public static boolean isEmpty(IterableLike $this) {
        return !$this.iterator().hasNext();
    }

    public static Object foldRight(IterableLike $this, Object z, Function2 op) {
        return $this.iterator().foldRight(z, op);
    }

    public static Object reduceRight(IterableLike $this, Function2 op) {
        return $this.iterator().reduceRight(op);
    }

    public static Iterable toIterable(IterableLike $this) {
        return $this.thisCollection();
    }

    public static Iterator toIterator(IterableLike $this) {
        return $this.iterator();
    }

    public static Object head(IterableLike $this) {
        return $this.iterator().next();
    }

    public static Object slice(IterableLike $this, int from2, int until2) {
        Object Repr;
        int lo = package$.MODULE$.max(from2, 0);
        int elems = until2 - lo;
        Builder b = $this.newBuilder();
        if (elems <= 0) {
            Repr = b.result();
        } else {
            b.sizeHintBounded(elems, $this);
            Iterator it = $this.iterator().drop(lo);
            for (int i = 0; i < elems && it.hasNext(); ++i) {
                b.$plus$eq(it.next());
            }
            Repr = b.result();
        }
        return Repr;
    }

    public static Object take(IterableLike $this, int n) {
        Object Repr;
        Builder b = $this.newBuilder();
        if (n <= 0) {
            Repr = b.result();
        } else {
            b.sizeHintBounded(n, $this);
            Iterator it = $this.iterator();
            for (int i = 0; i < n && it.hasNext(); ++i) {
                b.$plus$eq(it.next());
            }
            Repr = b.result();
        }
        return Repr;
    }

    public static Object drop(IterableLike $this, int n) {
        Builder b = $this.newBuilder();
        int lo = package$.MODULE$.max(0, n);
        b.sizeHint($this, -lo);
        Iterator it = $this.iterator();
        for (int i = 0; i < n && it.hasNext(); ++i) {
            it.next();
        }
        return ((Builder)b.$plus$plus$eq(it)).result();
    }

    public static Object takeWhile(IterableLike $this, Function1 p) {
        Builder b = $this.newBuilder();
        Iterator it = $this.iterator();
        while (it.hasNext()) {
            Object x = it.next();
            if (BoxesRunTime.unboxToBoolean(p.apply(x))) {
                b.$plus$eq(x);
                continue;
            }
            return b.result();
        }
        return b.result();
    }

    public static Iterator grouped(IterableLike $this, int size2) {
        return $this.iterator().grouped(size2).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableLike $outer;

            public final Repr apply(Seq<A> xs) {
                Builder<A, Repr> b = this.$outer.newBuilder();
                b.$plus$plus$eq(xs);
                return b.result();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Iterator sliding(IterableLike $this, int size2) {
        return $this.sliding(size2, 1);
    }

    public static Iterator sliding(IterableLike $this, int size2, int step) {
        return $this.iterator().sliding(size2, step).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableLike $outer;

            public final Repr apply(Seq<A> xs) {
                Builder<A, Repr> b = this.$outer.newBuilder();
                b.$plus$plus$eq(xs);
                return b.result();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Object takeRight(IterableLike $this, int n) {
        Builder b = $this.newBuilder();
        b.sizeHintBounded(n, $this);
        Iterator lead = $this.iterator().drop(n);
        Iterator it = $this.iterator();
        while (lead.hasNext()) {
            lead.next();
            it.next();
        }
        while (it.hasNext()) {
            b.$plus$eq(it.next());
        }
        return b.result();
    }

    public static Object dropRight(IterableLike $this, int n) {
        Builder b = $this.newBuilder();
        if (n >= 0) {
            b.sizeHint($this, -n);
        }
        Iterator lead = $this.iterator().drop(n);
        Iterator it = $this.iterator();
        while (lead.hasNext()) {
            b.$plus$eq(it.next());
            lead.next();
        }
        return b.result();
    }

    public static void copyToArray(IterableLike $this, Object xs, int start, int len) {
        int n = start + len;
        Predef$ predef$ = Predef$.MODULE$;
        int end = RichInt$.MODULE$.min$extension(n, ScalaRunTime$.MODULE$.array_length(xs));
        Iterator it = $this.iterator();
        for (int i = start; i < end && it.hasNext(); ++i) {
            ScalaRunTime$.MODULE$.array_update(xs, i, it.next());
        }
    }

    public static Object zip(IterableLike $this, GenIterable that, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        Iterator these = $this.iterator();
        Iterator those = that.iterator();
        while (these.hasNext() && those.hasNext()) {
            b.$plus$eq(new Tuple2(these.next(), those.next()));
        }
        return b.result();
    }

    public static Object zipAll(IterableLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        Iterator these = $this.iterator();
        Iterator those = that.iterator();
        while (these.hasNext() && those.hasNext()) {
            b.$plus$eq(new Tuple2(these.next(), those.next()));
        }
        while (these.hasNext()) {
            b.$plus$eq(new Tuple2(these.next(), thatElem));
        }
        while (those.hasNext()) {
            b.$plus$eq(new Tuple2(thisElem, those.next()));
        }
        return b.result();
    }

    public static Object zipWithIndex(IterableLike $this, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        IntRef i = IntRef.create(0);
        $this.foreach(new Serializable($this, b, i){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;
            private final IntRef i$1;

            public final void apply(A x) {
                this.b$1.$plus$eq(new Tuple2<A, Integer>(x, BoxesRunTime.boxToInteger(this.i$1.elem)));
                ++this.i$1.elem;
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.i$1 = var3_3;
            }
        });
        return b.result();
    }

    public static boolean sameElements(IterableLike $this, GenIterable that) {
        Iterator these = $this.iterator();
        Iterator those = that.iterator();
        while (these.hasNext() && those.hasNext()) {
            Object a = those.next();
            Object a2 = these.next();
            if (a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a))))) continue;
            return false;
        }
        return !these.hasNext() && !those.hasNext();
    }

    public static Stream toStream(IterableLike $this) {
        return $this.iterator().toStream();
    }

    public static boolean canEqual(IterableLike $this, Object that) {
        return true;
    }

    public static IterableView view(IterableLike $this) {
        return new IterableView<A, Repr>($this){
            private Repr underlying;
            private final /* synthetic */ IterableLike $outer;
            private volatile boolean bitmap$0;

            private Object underlying$lzycompute() {
                IterableLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = this.$outer.repr();
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public <B> IterableViewLike.Transformed<Tuple2<A, B>> newZipped(GenIterable<B> that) {
                return IterableViewLike$class.newZipped(this, that);
            }

            public <A1, B> IterableViewLike.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
                return IterableViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
            }

            public <B> IterableViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return IterableViewLike$class.newForced(this, xs);
            }

            public <B> IterableViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return IterableViewLike$class.newAppended(this, that);
            }

            public <B> IterableViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return IterableViewLike$class.newMapped(this, f);
            }

            public <B> IterableViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return IterableViewLike$class.newFlatMapped(this, f);
            }

            public IterableViewLike.Transformed<A> newFiltered(Function1<A, Object> p) {
                return IterableViewLike$class.newFiltered(this, p);
            }

            public IterableViewLike.Transformed<A> newSliced(SliceInterval _endpoints) {
                return IterableViewLike$class.newSliced(this, _endpoints);
            }

            public IterableViewLike.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return IterableViewLike$class.newDroppedWhile(this, p);
            }

            public IterableViewLike.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return IterableViewLike$class.newTakenWhile(this, p);
            }

            public IterableViewLike.Transformed<A> newTaken(int n) {
                return IterableViewLike$class.newTaken(this, n);
            }

            public IterableViewLike.Transformed<A> newDropped(int n) {
                return IterableViewLike$class.newDropped(this, n);
            }

            public IterableView<A, Repr> drop(int n) {
                return IterableViewLike$class.drop(this, n);
            }

            public IterableView<A, Repr> take(int n) {
                return IterableViewLike$class.take(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<IterableView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zip(this, that, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<IterableView<A, Repr>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableViewLike$class.zipWithIndex(this, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<IterableView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public Iterator<IterableView<A, Repr>> grouped(int size2) {
                return IterableViewLike$class.grouped(this, size2);
            }

            public Iterator<IterableView<A, Repr>> sliding(int size2, int step) {
                return IterableViewLike$class.sliding(this, size2, step);
            }

            public Iterator<IterableView<A, Repr>> sliding(int size2) {
                return IterableViewLike$class.sliding(this, size2);
            }

            public IterableView<A, Repr> dropRight(int n) {
                return IterableViewLike$class.dropRight(this, n);
            }

            public IterableView<A, Repr> takeRight(int n) {
                return IterableViewLike$class.takeRight(this, n);
            }

            public String stringPrefix() {
                return IterableViewLike$class.stringPrefix(this);
            }

            public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
                return (TraversableView)TraversableLike$class.tail(this);
            }

            public String viewIdentifier() {
                return TraversableViewLike$class.viewIdentifier(this);
            }

            public String viewIdString() {
                return TraversableViewLike$class.viewIdString(this);
            }

            public String viewToString() {
                return TraversableViewLike$class.viewToString(this);
            }

            public Builder<A, IterableView<A, Repr>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That force(CanBuildFrom<Repr, B, That> bf) {
                return (That)TraversableViewLike$class.force(this, bf);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.flatMap(this, f, bf);
            }

            public <B> TraversableViewLike.Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
                return TraversableViewLike$class.flatten(this, asTraversable);
            }

            public TraversableView filter(Function1 p) {
                return TraversableViewLike$class.filter(this, p);
            }

            public TraversableView withFilter(Function1 p) {
                return TraversableViewLike$class.withFilter(this, p);
            }

            public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> partition(Function1<A, Object> p) {
                return TraversableViewLike$class.partition(this, p);
            }

            public TraversableView init() {
                return TraversableViewLike$class.init(this);
            }

            public TraversableView slice(int from2, int until2) {
                return TraversableViewLike$class.slice(this, from2, until2);
            }

            public TraversableView dropWhile(Function1 p) {
                return TraversableViewLike$class.dropWhile(this, p);
            }

            public TraversableView takeWhile(Function1 p) {
                return TraversableViewLike$class.takeWhile(this, p);
            }

            public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> span(Function1<A, Object> p) {
                return TraversableViewLike$class.span(this, p);
            }

            public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> splitAt(int n) {
                return TraversableViewLike$class.splitAt(this, n);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> Map<K, IterableView<A, Repr>> groupBy(Function1<A, K> f) {
                return TraversableViewLike$class.groupBy(this, f);
            }

            public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return TraversableViewLike$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return TraversableViewLike$class.unzip3(this, asTriple);
            }

            public TraversableView filterNot(Function1 p) {
                return TraversableViewLike$class.filterNot(this, p);
            }

            public Iterator<IterableView<A, Repr>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<IterableView<A, Repr>> tails() {
                return TraversableViewLike$class.tails(this);
            }

            public TraversableView tail() {
                return TraversableViewLike$class.tail(this);
            }

            public String toString() {
                return TraversableViewLike$class.toString(this);
            }

            public Seq<A> thisSeq() {
                return ViewMkString$class.thisSeq(this);
            }

            public String mkString() {
                return ViewMkString$class.mkString(this);
            }

            public String mkString(String sep) {
                return ViewMkString$class.mkString(this, sep);
            }

            public String mkString(String start, String sep, String end) {
                return ViewMkString$class.mkString(this, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return ViewMkString$class.addString(this, b, start, sep, end);
            }

            public GenericCompanion<Iterable> companion() {
                return Iterable$class.companion(this);
            }

            public Iterable<A> seq() {
                return Iterable$class.seq(this);
            }

            public Iterable<A> thisCollection() {
                return IterableLike$class.thisCollection(this);
            }

            public Iterable toCollection(Object repr) {
                return IterableLike$class.toCollection(this, repr);
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

            public boolean isEmpty() {
                return IterableLike$class.isEmpty(this);
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

            public <B> void copyToArray(Object xs, int start, int len) {
                IterableLike$class.copyToArray(this, xs, start, len);
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

            public Object view() {
                return IterableLike$class.view(this);
            }

            public IterableView<A, IterableView<A, Repr>> view(int from2, int until2) {
                return IterableLike$class.view(this, from2, until2);
            }

            public <B> Builder<B, Iterable<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
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

            public Combiner<A, ParIterable<A>> parCombiner() {
                return TraversableLike$class.parCombiner(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<IterableView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<IterableView<A, Repr>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Option<A> headOption() {
                return TraversableLike$class.headOption(this);
            }

            public A last() {
                return (A)TraversableLike$class.last(this);
            }

            public Option<A> lastOption() {
                return TraversableLike$class.lastOption(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
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

            public Seq<A> toSeq() {
                return TraversableOnce$class.toSeq(this);
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

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public Repr underlying() {
                return (Repr)(this.bitmap$0 ? this.underlying : this.underlying$lzycompute());
            }

            public Iterator<A> iterator() {
                return this.$outer.iterator();
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
                Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                Iterable$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
                IterableViewLike$class.$init$(this);
            }
        };
    }

    public static IterableView view(IterableLike $this, int from2, int until2) {
        return (IterableView)$this.view().slice(from2, until2);
    }

    public static void $init$(IterableLike $this) {
    }
}

