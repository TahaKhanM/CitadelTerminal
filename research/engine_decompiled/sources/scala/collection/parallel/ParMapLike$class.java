/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate$class;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParIterable$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParMap$;
import scala.collection.parallel.ParMap$class;
import scala.collection.parallel.ParMapLike;
import scala.collection.parallel.ParMapLike$;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public abstract class ParMapLike$class {
    public static Object default(ParMapLike $this, Object key) {
        throw new NoSuchElementException(new StringBuilder().append((Object)"key not found: ").append(key).toString());
    }

    public static Object apply(ParMapLike $this, Object key) {
        Option option;
        block4: {
            Object object;
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!(option instanceof Some)) break block2;
                    Some some = (Some)option;
                    object = some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                object = $this.default(key);
            }
            return object;
        }
        throw new MatchError(option);
    }

    public static Object getOrElse(ParMapLike $this, Object key, Function0 function0) {
        Option option;
        block4: {
            Object object;
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!(option instanceof Some)) break block2;
                    Some some = (Some)option;
                    object = some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                object = function0.apply();
            }
            return object;
        }
        throw new MatchError(option);
    }

    public static boolean contains(ParMapLike $this, Object key) {
        return $this.get(key).isDefined();
    }

    public static boolean isDefinedAt(ParMapLike $this, Object key) {
        return $this.contains(key);
    }

    public static IterableSplitter scala$collection$parallel$ParMapLike$$keysIterator(ParMapLike $this, IterableSplitter s2) {
        return new IterableSplitter<K>($this, s2){
            private final IterableSplitter<Tuple2<K, V>> iter;
            private final /* synthetic */ ParMapLike $outer;
            private Signalling signalDelegate;

            public Signalling signalDelegate() {
                return this.signalDelegate;
            }

            public void signalDelegate_$eq(Signalling x$1) {
                this.signalDelegate = x$1;
            }

            public Seq<IterableSplitter<K>> splitWithSignalling() {
                return IterableSplitter$class.splitWithSignalling(this);
            }

            public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
                return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
            }

            public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
                return IterableSplitter$class.buildString(this, closure);
            }

            public String debugInformation() {
                return IterableSplitter$class.debugInformation(this);
            }

            public IterableSplitter.Taken newTaken(int until2) {
                return IterableSplitter$class.newTaken(this, until2);
            }

            public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
                return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
            }

            public IterableSplitter<K> take(int n) {
                return IterableSplitter$class.take(this, n);
            }

            public IterableSplitter<K> slice(int from1, int until1) {
                return IterableSplitter$class.slice(this, from1, until1);
            }

            public <S> IterableSplitter.Mapped<S> map(Function1<K, S> f) {
                return IterableSplitter$class.map(this, f);
            }

            public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
                return IterableSplitter$class.appendParIterable(this, that);
            }

            public <S> IterableSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
                return IterableSplitter$class.zipParSeq(this, that);
            }

            public <S, U, R> IterableSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
                return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
            }

            public boolean isAborted() {
                return DelegatedSignalling$class.isAborted(this);
            }

            public void abort() {
                DelegatedSignalling$class.abort(this);
            }

            public int indexFlag() {
                return DelegatedSignalling$class.indexFlag(this);
            }

            public void setIndexFlag(int f) {
                DelegatedSignalling$class.setIndexFlag(this, f);
            }

            public void setIndexFlagIfGreater(int f) {
                DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
            }

            public void setIndexFlagIfLesser(int f) {
                DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
            }

            public int tag() {
                return DelegatedSignalling$class.tag(this);
            }

            public int count(Function1<K, Object> p) {
                return AugmentedIterableIterator$class.count(this, p);
            }

            public <U> U reduce(Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.reduce(this, op);
            }

            public <U> U fold(U z, Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.fold(this, z, op);
            }

            public <U> U sum(Numeric<U> num) {
                return (U)AugmentedIterableIterator$class.sum(this, num);
            }

            public <U> U product(Numeric<U> num) {
                return (U)AugmentedIterableIterator$class.product(this, num);
            }

            public <U> K min(Ordering<U> ord) {
                return (K)AugmentedIterableIterator$class.min(this, ord);
            }

            public <U> K max(Ordering<U> ord) {
                return (K)AugmentedIterableIterator$class.max(this, ord);
            }

            public <U> void copyToArray(Object array, int from2, int len) {
                AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
            }

            public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
            }

            public <S, That> Combiner<S, That> map2combiner(Function1<K, S> f, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.map2combiner(this, f, cb);
            }

            public <S, That> Combiner<S, That> collect2combiner(PartialFunction<K, S> pf, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
            }

            public <S, That> Combiner<S, That> flatmap2combiner(Function1<K, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
            }

            public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
                return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
            }

            public <U, This> Combiner<U, This> filter2combiner(Function1<K, Object> pred, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
            }

            public <U, This> Combiner<U, This> filterNot2combiner(Function1<K, Object> pred, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<K, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
                return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
            }

            public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.take2combiner(this, n, cb);
            }

            public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
            }

            public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
                return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
            }

            public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<K, Object> p, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<K, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
                return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
            }

            public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
                AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
            }

            public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
                return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
            }

            public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
                return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
            }

            public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
                return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
            }

            public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
                return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
            }

            public boolean isRemainingCheap() {
                return RemainsIterator$class.isRemainingCheap(this);
            }

            public Iterator<K> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<K> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<K, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<K> filter(Function1<K, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<K, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<K> withFilter(Function1<K, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<K> filterNot(Function1<K, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<K, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, K, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<K, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<K> takeWhile(Function1<K, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<K>, Iterator<K>> partition(Function1<K, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<K>, Iterator<K>> span(Function1<K, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<K> dropWhile(Function1<K, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<K, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<K, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<K, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<K, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<K, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<K> find(Function1<K, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<K, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<K> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<K>, Iterator<K>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<K> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<K> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<K> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<K> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public <B> Option<B> collectFirst(PartialFunction<K, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, K, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<K, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, K, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<K, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, K, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<K, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, K, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<K, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, K, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> K maxBy(Function1<K, B> f, Ordering<B> cmp) {
                return (K)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> K minBy(Function1<K, B> f, Ordering<B> cmp) {
                return (K)TraversableOnce$class.minBy(this, f, cmp);
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

            public List<K> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<K> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<K> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<K> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<K> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, K, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<K, Tuple2<T, U>> ev) {
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

            private IterableSplitter<Tuple2<K, V>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public K next() {
                return (K)((Tuple2)this.iter().next())._1();
            }

            /*
             * WARNING - void declaration
             */
            public Seq<IterableSplitter<K>> split() {
                void var1_1;
                Seq<A> ss = this.iter().split().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.3 $outer;

                    public final IterableSplitter<K> apply(IterableSplitter<Tuple2<K, V>> x$1) {
                        return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(this.$outer.scala$collection$parallel$ParMapLike$$anon$$$outer(), x$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, Seq$.MODULE$.canBuildFrom());
                ss.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.3 $outer;

                    public final void apply(IterableSplitter<K> x$2) {
                        x$2.signalDelegate_$eq(this.$outer.signalDelegate());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
                return var1_1;
            }

            public int remaining() {
                return this.iter().remaining();
            }

            public IterableSplitter<K> dup() {
                return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(this.$outer, this.iter().dup());
            }

            public /* synthetic */ ParMapLike scala$collection$parallel$ParMapLike$$anon$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                RemainsIterator$class.$init$(this);
                AugmentedIterableIterator$class.$init$(this);
                DelegatedSignalling$class.$init$(this);
                IterableSplitter$class.$init$(this);
                this.iter = s$1;
            }
        };
    }

    public static IterableSplitter keysIterator(ParMapLike $this) {
        return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator($this, $this.splitter());
    }

    public static IterableSplitter scala$collection$parallel$ParMapLike$$valuesIterator(ParMapLike $this, IterableSplitter s2) {
        return new IterableSplitter<V>($this, s2){
            private final IterableSplitter<Tuple2<K, V>> iter;
            private final /* synthetic */ ParMapLike $outer;
            private Signalling signalDelegate;

            public Signalling signalDelegate() {
                return this.signalDelegate;
            }

            public void signalDelegate_$eq(Signalling x$1) {
                this.signalDelegate = x$1;
            }

            public Seq<IterableSplitter<V>> splitWithSignalling() {
                return IterableSplitter$class.splitWithSignalling(this);
            }

            public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
                return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
            }

            public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
                return IterableSplitter$class.buildString(this, closure);
            }

            public String debugInformation() {
                return IterableSplitter$class.debugInformation(this);
            }

            public IterableSplitter.Taken newTaken(int until2) {
                return IterableSplitter$class.newTaken(this, until2);
            }

            public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
                return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
            }

            public IterableSplitter<V> take(int n) {
                return IterableSplitter$class.take(this, n);
            }

            public IterableSplitter<V> slice(int from1, int until1) {
                return IterableSplitter$class.slice(this, from1, until1);
            }

            public <S> IterableSplitter.Mapped<S> map(Function1<V, S> f) {
                return IterableSplitter$class.map(this, f);
            }

            public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
                return IterableSplitter$class.appendParIterable(this, that);
            }

            public <S> IterableSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
                return IterableSplitter$class.zipParSeq(this, that);
            }

            public <S, U, R> IterableSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
                return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
            }

            public boolean isAborted() {
                return DelegatedSignalling$class.isAborted(this);
            }

            public void abort() {
                DelegatedSignalling$class.abort(this);
            }

            public int indexFlag() {
                return DelegatedSignalling$class.indexFlag(this);
            }

            public void setIndexFlag(int f) {
                DelegatedSignalling$class.setIndexFlag(this, f);
            }

            public void setIndexFlagIfGreater(int f) {
                DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
            }

            public void setIndexFlagIfLesser(int f) {
                DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
            }

            public int tag() {
                return DelegatedSignalling$class.tag(this);
            }

            public int count(Function1<V, Object> p) {
                return AugmentedIterableIterator$class.count(this, p);
            }

            public <U> U reduce(Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.reduce(this, op);
            }

            public <U> U fold(U z, Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.fold(this, z, op);
            }

            public <U> U sum(Numeric<U> num) {
                return (U)AugmentedIterableIterator$class.sum(this, num);
            }

            public <U> U product(Numeric<U> num) {
                return (U)AugmentedIterableIterator$class.product(this, num);
            }

            public <U> V min(Ordering<U> ord) {
                return (V)AugmentedIterableIterator$class.min(this, ord);
            }

            public <U> V max(Ordering<U> ord) {
                return (V)AugmentedIterableIterator$class.max(this, ord);
            }

            public <U> void copyToArray(Object array, int from2, int len) {
                AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
            }

            public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
                return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
            }

            public <S, That> Combiner<S, That> map2combiner(Function1<V, S> f, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.map2combiner(this, f, cb);
            }

            public <S, That> Combiner<S, That> collect2combiner(PartialFunction<V, S> pf, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
            }

            public <S, That> Combiner<S, That> flatmap2combiner(Function1<V, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
                return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
            }

            public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
                return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
            }

            public <U, This> Combiner<U, This> filter2combiner(Function1<V, Object> pred, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
            }

            public <U, This> Combiner<U, This> filterNot2combiner(Function1<V, Object> pred, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<V, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
                return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
            }

            public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.take2combiner(this, n, cb);
            }

            public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
            }

            public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
                return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
            }

            public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<V, Object> p, Combiner<U, This> cb) {
                return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
            }

            public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<V, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
                return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
            }

            public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
                AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
            }

            public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
                return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
            }

            public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
                return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
            }

            public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
                return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
            }

            public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
                return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
            }

            public boolean isRemainingCheap() {
                return RemainsIterator$class.isRemainingCheap(this);
            }

            public Iterator<V> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<V> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<V, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<V> filter(Function1<V, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<V, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<V> withFilter(Function1<V, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<V> filterNot(Function1<V, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<V, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, V, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<V, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<V> takeWhile(Function1<V, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<V>, Iterator<V>> partition(Function1<V, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<V>, Iterator<V>> span(Function1<V, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<V> dropWhile(Function1<V, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<V, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<V, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<V, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<V, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<V, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<V> find(Function1<V, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<V, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<V> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<V>, Iterator<V>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<V> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<V> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<V> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<V> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public <B> Option<B> collectFirst(PartialFunction<V, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, V, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<V, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, V, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<V, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, V, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<V, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, V, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<V, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, V, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> V maxBy(Function1<V, B> f, Ordering<B> cmp) {
                return (V)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> V minBy(Function1<V, B> f, Ordering<B> cmp) {
                return (V)TraversableOnce$class.minBy(this, f, cmp);
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

            public List<V> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<V> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<V> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<V> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<V> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, V, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<V, Tuple2<T, U>> ev) {
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

            private IterableSplitter<Tuple2<K, V>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public V next() {
                return (V)((Tuple2)this.iter().next())._2();
            }

            /*
             * WARNING - void declaration
             */
            public Seq<IterableSplitter<V>> split() {
                void var1_1;
                Seq<A> ss = this.iter().split().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.4 $outer;

                    public final IterableSplitter<V> apply(IterableSplitter<Tuple2<K, V>> x$3) {
                        return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(this.$outer.scala$collection$parallel$ParMapLike$$anon$$$outer(), x$3);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, Seq$.MODULE$.canBuildFrom());
                ss.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.4 $outer;

                    public final void apply(IterableSplitter<V> x$4) {
                        x$4.signalDelegate_$eq(this.$outer.signalDelegate());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
                return var1_1;
            }

            public int remaining() {
                return this.iter().remaining();
            }

            public IterableSplitter<V> dup() {
                return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(this.$outer, this.iter().dup());
            }

            public /* synthetic */ ParMapLike scala$collection$parallel$ParMapLike$$anon$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                RemainsIterator$class.$init$(this);
                AugmentedIterableIterator$class.$init$(this);
                DelegatedSignalling$class.$init$(this);
                IterableSplitter$class.$init$(this);
                this.iter = s$2;
            }
        };
    }

    public static IterableSplitter valuesIterator(ParMapLike $this) {
        return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator($this, $this.splitter());
    }

    public static ParSet keySet(ParMapLike $this) {
        return new ParMapLike.DefaultKeySet($this);
    }

    public static ParIterable keys(ParMapLike $this) {
        return $this.keySet();
    }

    public static ParIterable values(ParMapLike $this) {
        return new ParMapLike.DefaultValuesIterable($this);
    }

    public static ParMap filterKeys(ParMapLike $this, Function1 p) {
        return new ParMap<K, V>($this, p){
            private Repr filtered;
            private final /* synthetic */ ParMapLike $outer;
            public final Function1 p$1;
            private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
            private volatile boolean bitmap$0;
            private volatile ParIterableLike$ScanNode$ ScanNode$module;
            private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

            private ParMap filtered$lzycompute() {
                ParMapLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.filtered = (ParMap)this.$outer.filter(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ ParMapLike$.anon.1 $outer;

                            public final boolean apply(Tuple2<K, V> kv) {
                                return BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(kv._1()));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        });
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.filtered;
                }
            }

            public GenericParMapCompanion<ParMap> mapCompanion() {
                return ParMap$class.mapCompanion(this);
            }

            public ParMap<K, V> empty() {
                return ParMap$class.empty(this);
            }

            public String stringPrefix() {
                return ParMap$class.stringPrefix(this);
            }

            public <U> ParMap<K, U> updated(K key, U value) {
                return ParMap$class.updated(this, key, value);
            }

            public V default(K key) {
                return (V)ParMapLike$class.default(this, key);
            }

            public V apply(K key) {
                return (V)ParMapLike$class.apply(this, key);
            }

            public <U> U getOrElse(K key, Function0<U> function0) {
                return (U)ParMapLike$class.getOrElse(this, key, function0);
            }

            public boolean isDefinedAt(K key) {
                return ParMapLike$class.isDefinedAt(this, key);
            }

            public IterableSplitter<K> keysIterator() {
                return ParMapLike$class.keysIterator(this);
            }

            public IterableSplitter<V> valuesIterator() {
                return ParMapLike$class.valuesIterator(this);
            }

            public ParSet<K> keySet() {
                return ParMapLike$class.keySet(this);
            }

            public ParIterable<K> keys() {
                return ParMapLike$class.keys(this);
            }

            public ParIterable<V> values() {
                return ParMapLike$class.values(this);
            }

            public ParMap<K, V> filterKeys(Function1<K, Object> p) {
                return ParMapLike$class.filterKeys(this, p);
            }

            public <S> ParMap<K, S> mapValues(Function1<V, S> f) {
                return ParMapLike$class.mapValues(this, f);
            }

            public GenericCompanion<ParIterable> companion() {
                return ParIterable$class.companion(this);
            }

            public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
                return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
            }

            @TraitSetter
            public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
                this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
            }

            private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
                ParMapLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (this.ScanNode$module == null) {
                        this.ScanNode$module = new ParIterableLike$ScanNode$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ScanNode$module;
                }
            }

            public ParIterableLike$ScanNode$ ScanNode() {
                return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
            }

            private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
                ParMapLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (this.ScanLeaf$module == null) {
                        this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ScanLeaf$module;
                }
            }

            public ParIterableLike$ScanLeaf$ ScanLeaf() {
                return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
            }

            public void initTaskSupport() {
                ParIterableLike$class.initTaskSupport(this);
            }

            public TaskSupport tasksupport() {
                return ParIterableLike$class.tasksupport(this);
            }

            public void tasksupport_$eq(TaskSupport ts) {
                ParIterableLike$class.tasksupport_$eq(this, ts);
            }

            public ParIterable repr() {
                return ParIterableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return ParIterableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return ParIterableLike$class.hasDefiniteSize(this);
            }

            public boolean isEmpty() {
                return ParIterableLike$class.isEmpty(this);
            }

            public boolean nonEmpty() {
                return ParIterableLike$class.nonEmpty(this);
            }

            public Object head() {
                return ParIterableLike$class.head(this);
            }

            public Option<Tuple2<K, V>> headOption() {
                return ParIterableLike$class.headOption(this);
            }

            public ParIterable tail() {
                return ParIterableLike$class.tail(this);
            }

            public Object last() {
                return ParIterableLike$class.last(this);
            }

            public Option<Tuple2<K, V>> lastOption() {
                return ParIterableLike$class.lastOption(this);
            }

            public ParIterable init() {
                return ParIterableLike$class.init(this);
            }

            public Splitter<Tuple2<K, V>> iterator() {
                return ParIterableLike$class.iterator(this);
            }

            public ParIterable par() {
                return ParIterableLike$class.par(this);
            }

            public boolean isStrictSplitterCollection() {
                return ParIterableLike$class.isStrictSplitterCollection(this);
            }

            public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
                return ParIterableLike$class.reuse(this, oldc, newc);
            }

            public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
                return ParIterableLike$class.task2ops(this, tsk);
            }

            public <R> Object wrap(Function0<R> body2) {
                return ParIterableLike$class.wrap(this, body2);
            }

            public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
                return ParIterableLike$class.delegatedSignalling2ops(this, it);
            }

            public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
                return ParIterableLike$class.builder2ops(this, cb);
            }

            public <S, That> Object bf2seq(CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return ParIterableLike$class.bf2seq(this, bf);
            }

            public ParIterable sequentially(Function1 b) {
                return ParIterableLike$class.sequentially(this, b);
            }

            public String mkString(String start, String sep, String end) {
                return ParIterableLike$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return ParIterableLike$class.mkString(this, sep);
            }

            public String mkString() {
                return ParIterableLike$class.mkString(this);
            }

            public String toString() {
                return ParIterableLike$class.toString(this);
            }

            public boolean canEqual(Object other) {
                return ParIterableLike$class.canEqual(this, other);
            }

            public <U> U reduce(Function2<U, U, U> op) {
                return (U)ParIterableLike$class.reduce(this, op);
            }

            public <U> Option<U> reduceOption(Function2<U, U, U> op) {
                return ParIterableLike$class.reduceOption(this, op);
            }

            public <U> U fold(U z, Function2<U, U, U> op) {
                return (U)ParIterableLike$class.fold(this, z, op);
            }

            public <S> S aggregate(Function0<S> z, Function2<S, Tuple2<K, V>, S> seqop, Function2<S, S, S> combop) {
                return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
            }

            public <S> S foldLeft(S z, Function2<S, Tuple2<K, V>, S> op) {
                return (S)ParIterableLike$class.foldLeft(this, z, op);
            }

            public <S> S foldRight(S z, Function2<Tuple2<K, V>, S, S> op) {
                return (S)ParIterableLike$class.foldRight(this, z, op);
            }

            public <U> U reduceLeft(Function2<U, Tuple2<K, V>, U> op) {
                return (U)ParIterableLike$class.reduceLeft(this, op);
            }

            public <U> U reduceRight(Function2<Tuple2<K, V>, U, U> op) {
                return (U)ParIterableLike$class.reduceRight(this, op);
            }

            public <U> Option<U> reduceLeftOption(Function2<U, Tuple2<K, V>, U> op) {
                return ParIterableLike$class.reduceLeftOption(this, op);
            }

            public <U> Option<U> reduceRightOption(Function2<Tuple2<K, V>, U, U> op) {
                return ParIterableLike$class.reduceRightOption(this, op);
            }

            public int count(Function1<Tuple2<K, V>, Object> p) {
                return ParIterableLike$class.count(this, p);
            }

            public <U> U sum(Numeric<U> num) {
                return (U)ParIterableLike$class.sum(this, num);
            }

            public <U> U product(Numeric<U> num) {
                return (U)ParIterableLike$class.product(this, num);
            }

            public Object min(Ordering ord) {
                return ParIterableLike$class.min(this, ord);
            }

            public Object max(Ordering ord) {
                return ParIterableLike$class.max(this, ord);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return ParIterableLike$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return ParIterableLike$class.minBy(this, f, cmp);
            }

            public <S, That> That map(Function1<Tuple2<K, V>, S> f, CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return (That)ParIterableLike$class.map(this, f, bf);
            }

            public <S, That> That collect(PartialFunction<Tuple2<K, V>, S> pf, CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return (That)ParIterableLike$class.collect(this, pf, bf);
            }

            public <S, That> That flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return (That)ParIterableLike$class.flatMap(this, f, bf);
            }

            public boolean forall(Function1<Tuple2<K, V>, Object> p) {
                return ParIterableLike$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<K, V>, Object> p) {
                return ParIterableLike$class.exists(this, p);
            }

            public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
                return ParIterableLike$class.find(this, p);
            }

            public Object combinerFactory() {
                return ParIterableLike$class.combinerFactory(this);
            }

            public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
                return ParIterableLike$class.combinerFactory(this, cbf);
            }

            public ParIterable withFilter(Function1 pred) {
                return ParIterableLike$class.withFilter(this, pred);
            }

            public ParIterable filter(Function1 pred) {
                return ParIterableLike$class.filter(this, pred);
            }

            public ParIterable filterNot(Function1 pred) {
                return ParIterableLike$class.filterNot(this, pred);
            }

            public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParMap<K, V>, U, That> bf) {
                return (That)ParIterableLike$class.$plus$plus(this, that, bf);
            }

            public Tuple2<ParMap<K, V>, ParMap<K, V>> partition(Function1<Tuple2<K, V>, Object> pred) {
                return ParIterableLike$class.partition(this, pred);
            }

            public <K> scala.collection.parallel.immutable.ParMap<K, ParMap<K, V>> groupBy(Function1<Tuple2<K, V>, K> f) {
                return ParIterableLike$class.groupBy(this, f);
            }

            public ParIterable take(int n) {
                return ParIterableLike$class.take(this, n);
            }

            public ParIterable drop(int n) {
                return ParIterableLike$class.drop(this, n);
            }

            public ParIterable slice(int unc_from, int unc_until) {
                return ParIterableLike$class.slice(this, unc_from, unc_until);
            }

            public Tuple2<ParMap<K, V>, ParMap<K, V>> splitAt(int n) {
                return ParIterableLike$class.splitAt(this, n);
            }

            public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParMap<K, V>, U, That> bf) {
                return (That)ParIterableLike$class.scan(this, z, op, bf);
            }

            public <S, That> That scanLeft(S z, Function2<S, Tuple2<K, V>, S> op, CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
            }

            public <S, That> That scanRight(S z, Function2<Tuple2<K, V>, S, S> op, CanBuildFrom<ParMap<K, V>, S, That> bf) {
                return (That)ParIterableLike$class.scanRight(this, z, op, bf);
            }

            public ParIterable takeWhile(Function1 pred) {
                return ParIterableLike$class.takeWhile(this, pred);
            }

            public Tuple2<ParMap<K, V>, ParMap<K, V>> span(Function1<Tuple2<K, V>, Object> pred) {
                return ParIterableLike$class.span(this, pred);
            }

            public ParIterable dropWhile(Function1 pred) {
                return ParIterableLike$class.dropWhile(this, pred);
            }

            public <U> void copyToArray(Object xs) {
                ParIterableLike$class.copyToArray(this, xs);
            }

            public <U> void copyToArray(Object xs, int start) {
                ParIterableLike$class.copyToArray(this, xs, start);
            }

            public <U> void copyToArray(Object xs, int start, int len) {
                ParIterableLike$class.copyToArray(this, xs, start, len);
            }

            public <U> boolean sameElements(GenIterable<U> that) {
                return ParIterableLike$class.sameElements(this, that);
            }

            public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParMap<K, V>, Tuple2<U, S>, That> bf) {
                return (That)ParIterableLike$class.zip(this, that, bf);
            }

            public <U, That> That zipWithIndex(CanBuildFrom<ParMap<K, V>, Tuple2<U, Object>, That> bf) {
                return (That)ParIterableLike$class.zipWithIndex(this, bf);
            }

            public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParMap<K, V>, Tuple2<U, S>, That> bf) {
                return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
                return (That)ParIterableLike$class.toParCollection(this, cbf);
            }

            public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
                return (That)ParIterableLike$class.toParMap(this, cbf, ev);
            }

            public Object view() {
                return ParIterableLike$class.view(this);
            }

            public <U> Object toArray(ClassTag<U> evidence$1) {
                return ParIterableLike$class.toArray(this, evidence$1);
            }

            public List<Tuple2<K, V>> toList() {
                return ParIterableLike$class.toList(this);
            }

            public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
                return ParIterableLike$class.toIndexedSeq(this);
            }

            public Stream<Tuple2<K, V>> toStream() {
                return ParIterableLike$class.toStream(this);
            }

            public Iterator<Tuple2<K, V>> toIterator() {
                return ParIterableLike$class.toIterator(this);
            }

            public <U> Buffer<U> toBuffer() {
                return ParIterableLike$class.toBuffer(this);
            }

            public GenTraversable<Tuple2<K, V>> toTraversable() {
                return ParIterableLike$class.toTraversable(this);
            }

            public ParIterable<Tuple2<K, V>> toIterable() {
                return ParIterableLike$class.toIterable(this);
            }

            public ParSeq<Tuple2<K, V>> toSeq() {
                return ParIterableLike$class.toSeq(this);
            }

            public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
                return ParIterableLike$class.toSet(this);
            }

            public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
                return ParIterableLike$class.toMap(this, ev);
            }

            public Vector<Tuple2<K, V>> toVector() {
                return ParIterableLike$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
                return (Col)ParIterableLike$class.to(this, cbf);
            }

            public int scanBlockSize() {
                return ParIterableLike$class.scanBlockSize(this);
            }

            public <S> S $div$colon(S z, Function2<S, Tuple2<K, V>, S> op) {
                return (S)ParIterableLike$class.$div$colon(this, z, op);
            }

            public <S> S $colon$bslash(S z, Function2<Tuple2<K, V>, S, S> op) {
                return (S)ParIterableLike$class.$colon$bslash(this, z, op);
            }

            public String debugInformation() {
                return ParIterableLike$class.debugInformation(this);
            }

            public Seq<String> brokenInvariants() {
                return ParIterableLike$class.brokenInvariants(this);
            }

            public ArrayBuffer<String> debugBuffer() {
                return ParIterableLike$class.debugBuffer(this);
            }

            public void debugclear() {
                ParIterableLike$class.debugclear(this);
            }

            public ArrayBuffer<String> debuglog(String s2) {
                return ParIterableLike$class.debuglog(this, s2);
            }

            public void printDebugBuffer() {
                ParIterableLike$class.printDebugBuffer(this);
            }

            public Combiner<Tuple2<K, V>, ParMap<K, V>> parCombiner() {
                return CustomParallelizable$class.parCombiner(this);
            }

            public Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
                return GenericParMapTemplate$class.newCombiner(this);
            }

            public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
                return GenericParMapTemplate$class.genericMapCombiner(this);
            }

            public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
                return GenericParTemplate$class.newBuilder(this);
            }

            public <B> Combiner<B, ParIterable<B>> genericBuilder() {
                return GenericParTemplate$class.genericBuilder(this);
            }

            public <B> Combiner<B, ParIterable<B>> genericCombiner() {
                return GenericParTemplate$class.genericCombiner(this);
            }

            public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<Tuple2<K, V>, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<Tuple2<K, V>, Tuple3<A1, A2, A3>> asTriple) {
                return GenericTraversableTemplate$class.unzip3(this, asTriple);
            }

            public GenTraversable flatten(Function1 asTraversable) {
                return GenericTraversableTemplate$class.flatten(this, asTraversable);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public int hashCode() {
                return GenMapLike$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return GenMapLike$class.equals(this, that);
            }

            private Repr filtered() {
                return (Repr)(this.bitmap$0 ? this.filtered : this.filtered$lzycompute());
            }

            public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
                this.$outer.foreach(new Serializable(this, f){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.1 $outer;
                    private final Function1 f$3;

                    public final Object apply(Tuple2<K, V> kv) {
                        return BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(kv._1())) ? this.f$3.apply(kv) : BoxedUnit.UNIT;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.f$3 = f$3;
                    }
                });
            }

            public IterableSplitter<Tuple2<K, V>> splitter() {
                return this.filtered().splitter();
            }

            public boolean contains(K key) {
                return this.$outer.contains(key) && BoxesRunTime.unboxToBoolean(this.p$1.apply(key));
            }

            public Option<V> get(K key) {
                return BoxesRunTime.unboxToBoolean(this.p$1.apply(key)) ? this.$outer.get(key) : None$.MODULE$;
            }

            public Map<K, V> seq() {
                return ((MapLike)this.$outer.seq()).filterKeys(this.p$1);
            }

            public int size() {
                return this.filtered().size();
            }

            public <U> ParMap<K, U> $plus(Tuple2<K, U> kv) {
                return ((ParIterableLike)ParMap$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParMap$.MODULE$.canBuildFrom()).$plus(kv);
            }

            public ParMap<K, V> $minus(K key) {
                return (ParMap)((GenMapLike)((ParIterableLike)ParMap$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParMap$.MODULE$.canBuildFrom())).$minus(key);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
                Parallelizable$class.$init$(this);
                GenMapLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                GenIterable$class.$init$(this);
                GenericParTemplate$class.$init$(this);
                GenericParMapTemplate$class.$init$(this);
                CustomParallelizable$class.$init$(this);
                ParIterableLike$class.$init$(this);
                ParIterable$class.$init$(this);
                ParMapLike$class.$init$(this);
                ParMap$class.$init$(this);
            }
        };
    }

    public static ParMap mapValues(ParMapLike $this, Function1 f) {
        return new ParMap<K, S>($this, f){
            private final /* synthetic */ ParMapLike $outer;
            public final Function1 f$4;
            private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
            private volatile ParIterableLike$ScanNode$ ScanNode$module;
            private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

            public GenericParMapCompanion<ParMap> mapCompanion() {
                return ParMap$class.mapCompanion(this);
            }

            public ParMap<K, S> empty() {
                return ParMap$class.empty(this);
            }

            public String stringPrefix() {
                return ParMap$class.stringPrefix(this);
            }

            public <U> ParMap<K, U> updated(K key, U value) {
                return ParMap$class.updated(this, key, value);
            }

            public S default(K key) {
                return (S)ParMapLike$class.default(this, key);
            }

            public S apply(K key) {
                return (S)ParMapLike$class.apply(this, key);
            }

            public <U> U getOrElse(K key, Function0<U> function0) {
                return (U)ParMapLike$class.getOrElse(this, key, function0);
            }

            public boolean isDefinedAt(K key) {
                return ParMapLike$class.isDefinedAt(this, key);
            }

            public IterableSplitter<K> keysIterator() {
                return ParMapLike$class.keysIterator(this);
            }

            public IterableSplitter<S> valuesIterator() {
                return ParMapLike$class.valuesIterator(this);
            }

            public ParSet<K> keySet() {
                return ParMapLike$class.keySet(this);
            }

            public ParIterable<K> keys() {
                return ParMapLike$class.keys(this);
            }

            public ParIterable<S> values() {
                return ParMapLike$class.values(this);
            }

            public ParMap<K, S> filterKeys(Function1<K, Object> p) {
                return ParMapLike$class.filterKeys(this, p);
            }

            public <S> ParMap<K, S> mapValues(Function1<S, S> f) {
                return ParMapLike$class.mapValues(this, f);
            }

            public GenericCompanion<ParIterable> companion() {
                return ParIterable$class.companion(this);
            }

            public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
                return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
            }

            @TraitSetter
            public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
                this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
            }

            private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
                ParMapLike$.anon.2 var1_1 = this;
                synchronized (var1_1) {
                    if (this.ScanNode$module == null) {
                        this.ScanNode$module = new ParIterableLike$ScanNode$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ScanNode$module;
                }
            }

            public ParIterableLike$ScanNode$ ScanNode() {
                return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
            }

            private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
                ParMapLike$.anon.2 var1_1 = this;
                synchronized (var1_1) {
                    if (this.ScanLeaf$module == null) {
                        this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ScanLeaf$module;
                }
            }

            public ParIterableLike$ScanLeaf$ ScanLeaf() {
                return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
            }

            public void initTaskSupport() {
                ParIterableLike$class.initTaskSupport(this);
            }

            public TaskSupport tasksupport() {
                return ParIterableLike$class.tasksupport(this);
            }

            public void tasksupport_$eq(TaskSupport ts) {
                ParIterableLike$class.tasksupport_$eq(this, ts);
            }

            public ParIterable repr() {
                return ParIterableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return ParIterableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return ParIterableLike$class.hasDefiniteSize(this);
            }

            public boolean isEmpty() {
                return ParIterableLike$class.isEmpty(this);
            }

            public boolean nonEmpty() {
                return ParIterableLike$class.nonEmpty(this);
            }

            public Object head() {
                return ParIterableLike$class.head(this);
            }

            public Option<Tuple2<K, S>> headOption() {
                return ParIterableLike$class.headOption(this);
            }

            public ParIterable tail() {
                return ParIterableLike$class.tail(this);
            }

            public Object last() {
                return ParIterableLike$class.last(this);
            }

            public Option<Tuple2<K, S>> lastOption() {
                return ParIterableLike$class.lastOption(this);
            }

            public ParIterable init() {
                return ParIterableLike$class.init(this);
            }

            public Splitter<Tuple2<K, S>> iterator() {
                return ParIterableLike$class.iterator(this);
            }

            public ParIterable par() {
                return ParIterableLike$class.par(this);
            }

            public boolean isStrictSplitterCollection() {
                return ParIterableLike$class.isStrictSplitterCollection(this);
            }

            public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
                return ParIterableLike$class.reuse(this, oldc, newc);
            }

            public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
                return ParIterableLike$class.task2ops(this, tsk);
            }

            public <R> Object wrap(Function0<R> body2) {
                return ParIterableLike$class.wrap(this, body2);
            }

            public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
                return ParIterableLike$class.delegatedSignalling2ops(this, it);
            }

            public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
                return ParIterableLike$class.builder2ops(this, cb);
            }

            public <S, That> Object bf2seq(CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return ParIterableLike$class.bf2seq(this, bf);
            }

            public ParIterable sequentially(Function1 b) {
                return ParIterableLike$class.sequentially(this, b);
            }

            public String mkString(String start, String sep, String end) {
                return ParIterableLike$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return ParIterableLike$class.mkString(this, sep);
            }

            public String mkString() {
                return ParIterableLike$class.mkString(this);
            }

            public String toString() {
                return ParIterableLike$class.toString(this);
            }

            public boolean canEqual(Object other) {
                return ParIterableLike$class.canEqual(this, other);
            }

            public <U> U reduce(Function2<U, U, U> op) {
                return (U)ParIterableLike$class.reduce(this, op);
            }

            public <U> Option<U> reduceOption(Function2<U, U, U> op) {
                return ParIterableLike$class.reduceOption(this, op);
            }

            public <U> U fold(U z, Function2<U, U, U> op) {
                return (U)ParIterableLike$class.fold(this, z, op);
            }

            public <S> S aggregate(Function0<S> z, Function2<S, Tuple2<K, S>, S> seqop, Function2<S, S, S> combop) {
                return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
            }

            public <S> S foldLeft(S z, Function2<S, Tuple2<K, S>, S> op) {
                return (S)ParIterableLike$class.foldLeft(this, z, op);
            }

            public <S> S foldRight(S z, Function2<Tuple2<K, S>, S, S> op) {
                return (S)ParIterableLike$class.foldRight(this, z, op);
            }

            public <U> U reduceLeft(Function2<U, Tuple2<K, S>, U> op) {
                return (U)ParIterableLike$class.reduceLeft(this, op);
            }

            public <U> U reduceRight(Function2<Tuple2<K, S>, U, U> op) {
                return (U)ParIterableLike$class.reduceRight(this, op);
            }

            public <U> Option<U> reduceLeftOption(Function2<U, Tuple2<K, S>, U> op) {
                return ParIterableLike$class.reduceLeftOption(this, op);
            }

            public <U> Option<U> reduceRightOption(Function2<Tuple2<K, S>, U, U> op) {
                return ParIterableLike$class.reduceRightOption(this, op);
            }

            public int count(Function1<Tuple2<K, S>, Object> p) {
                return ParIterableLike$class.count(this, p);
            }

            public <U> U sum(Numeric<U> num) {
                return (U)ParIterableLike$class.sum(this, num);
            }

            public <U> U product(Numeric<U> num) {
                return (U)ParIterableLike$class.product(this, num);
            }

            public Object min(Ordering ord) {
                return ParIterableLike$class.min(this, ord);
            }

            public Object max(Ordering ord) {
                return ParIterableLike$class.max(this, ord);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return ParIterableLike$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return ParIterableLike$class.minBy(this, f, cmp);
            }

            public <S, That> That map(Function1<Tuple2<K, S>, S> f, CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return (That)ParIterableLike$class.map(this, f, bf);
            }

            public <S, That> That collect(PartialFunction<Tuple2<K, S>, S> pf, CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return (That)ParIterableLike$class.collect(this, pf, bf);
            }

            public <S, That> That flatMap(Function1<Tuple2<K, S>, GenTraversableOnce<S>> f, CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return (That)ParIterableLike$class.flatMap(this, f, bf);
            }

            public boolean forall(Function1<Tuple2<K, S>, Object> p) {
                return ParIterableLike$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<K, S>, Object> p) {
                return ParIterableLike$class.exists(this, p);
            }

            public Option<Tuple2<K, S>> find(Function1<Tuple2<K, S>, Object> p) {
                return ParIterableLike$class.find(this, p);
            }

            public Object combinerFactory() {
                return ParIterableLike$class.combinerFactory(this);
            }

            public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
                return ParIterableLike$class.combinerFactory(this, cbf);
            }

            public ParIterable withFilter(Function1 pred) {
                return ParIterableLike$class.withFilter(this, pred);
            }

            public ParIterable filter(Function1 pred) {
                return ParIterableLike$class.filter(this, pred);
            }

            public ParIterable filterNot(Function1 pred) {
                return ParIterableLike$class.filterNot(this, pred);
            }

            public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParMap<K, S>, U, That> bf) {
                return (That)ParIterableLike$class.$plus$plus(this, that, bf);
            }

            public Tuple2<ParMap<K, S>, ParMap<K, S>> partition(Function1<Tuple2<K, S>, Object> pred) {
                return ParIterableLike$class.partition(this, pred);
            }

            public <K> scala.collection.parallel.immutable.ParMap<K, ParMap<K, S>> groupBy(Function1<Tuple2<K, S>, K> f) {
                return ParIterableLike$class.groupBy(this, f);
            }

            public ParIterable take(int n) {
                return ParIterableLike$class.take(this, n);
            }

            public ParIterable drop(int n) {
                return ParIterableLike$class.drop(this, n);
            }

            public ParIterable slice(int unc_from, int unc_until) {
                return ParIterableLike$class.slice(this, unc_from, unc_until);
            }

            public Tuple2<ParMap<K, S>, ParMap<K, S>> splitAt(int n) {
                return ParIterableLike$class.splitAt(this, n);
            }

            public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParMap<K, S>, U, That> bf) {
                return (That)ParIterableLike$class.scan(this, z, op, bf);
            }

            public <S, That> That scanLeft(S z, Function2<S, Tuple2<K, S>, S> op, CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
            }

            public <S, That> That scanRight(S z, Function2<Tuple2<K, S>, S, S> op, CanBuildFrom<ParMap<K, S>, S, That> bf) {
                return (That)ParIterableLike$class.scanRight(this, z, op, bf);
            }

            public ParIterable takeWhile(Function1 pred) {
                return ParIterableLike$class.takeWhile(this, pred);
            }

            public Tuple2<ParMap<K, S>, ParMap<K, S>> span(Function1<Tuple2<K, S>, Object> pred) {
                return ParIterableLike$class.span(this, pred);
            }

            public ParIterable dropWhile(Function1 pred) {
                return ParIterableLike$class.dropWhile(this, pred);
            }

            public <U> void copyToArray(Object xs) {
                ParIterableLike$class.copyToArray(this, xs);
            }

            public <U> void copyToArray(Object xs, int start) {
                ParIterableLike$class.copyToArray(this, xs, start);
            }

            public <U> void copyToArray(Object xs, int start, int len) {
                ParIterableLike$class.copyToArray(this, xs, start, len);
            }

            public <U> boolean sameElements(GenIterable<U> that) {
                return ParIterableLike$class.sameElements(this, that);
            }

            public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParMap<K, S>, Tuple2<U, S>, That> bf) {
                return (That)ParIterableLike$class.zip(this, that, bf);
            }

            public <U, That> That zipWithIndex(CanBuildFrom<ParMap<K, S>, Tuple2<U, Object>, That> bf) {
                return (That)ParIterableLike$class.zipWithIndex(this, bf);
            }

            public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParMap<K, S>, Tuple2<U, S>, That> bf) {
                return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
                return (That)ParIterableLike$class.toParCollection(this, cbf);
            }

            public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Tuple2<K, S>, Tuple2<K, V>> ev) {
                return (That)ParIterableLike$class.toParMap(this, cbf, ev);
            }

            public Object view() {
                return ParIterableLike$class.view(this);
            }

            public <U> Object toArray(ClassTag<U> evidence$1) {
                return ParIterableLike$class.toArray(this, evidence$1);
            }

            public List<Tuple2<K, S>> toList() {
                return ParIterableLike$class.toList(this);
            }

            public IndexedSeq<Tuple2<K, S>> toIndexedSeq() {
                return ParIterableLike$class.toIndexedSeq(this);
            }

            public Stream<Tuple2<K, S>> toStream() {
                return ParIterableLike$class.toStream(this);
            }

            public Iterator<Tuple2<K, S>> toIterator() {
                return ParIterableLike$class.toIterator(this);
            }

            public <U> Buffer<U> toBuffer() {
                return ParIterableLike$class.toBuffer(this);
            }

            public GenTraversable<Tuple2<K, S>> toTraversable() {
                return ParIterableLike$class.toTraversable(this);
            }

            public ParIterable<Tuple2<K, S>> toIterable() {
                return ParIterableLike$class.toIterable(this);
            }

            public ParSeq<Tuple2<K, S>> toSeq() {
                return ParIterableLike$class.toSeq(this);
            }

            public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
                return ParIterableLike$class.toSet(this);
            }

            public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<Tuple2<K, S>, Tuple2<K, V>> ev) {
                return ParIterableLike$class.toMap(this, ev);
            }

            public Vector<Tuple2<K, S>> toVector() {
                return ParIterableLike$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, S>, Col> cbf) {
                return (Col)ParIterableLike$class.to(this, cbf);
            }

            public int scanBlockSize() {
                return ParIterableLike$class.scanBlockSize(this);
            }

            public <S> S $div$colon(S z, Function2<S, Tuple2<K, S>, S> op) {
                return (S)ParIterableLike$class.$div$colon(this, z, op);
            }

            public <S> S $colon$bslash(S z, Function2<Tuple2<K, S>, S, S> op) {
                return (S)ParIterableLike$class.$colon$bslash(this, z, op);
            }

            public String debugInformation() {
                return ParIterableLike$class.debugInformation(this);
            }

            public Seq<String> brokenInvariants() {
                return ParIterableLike$class.brokenInvariants(this);
            }

            public ArrayBuffer<String> debugBuffer() {
                return ParIterableLike$class.debugBuffer(this);
            }

            public void debugclear() {
                ParIterableLike$class.debugclear(this);
            }

            public ArrayBuffer<String> debuglog(String s2) {
                return ParIterableLike$class.debuglog(this, s2);
            }

            public void printDebugBuffer() {
                ParIterableLike$class.printDebugBuffer(this);
            }

            public Combiner<Tuple2<K, S>, ParMap<K, S>> parCombiner() {
                return CustomParallelizable$class.parCombiner(this);
            }

            public Combiner<Tuple2<K, S>, ParMap<K, S>> newCombiner() {
                return GenericParMapTemplate$class.newCombiner(this);
            }

            public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
                return GenericParMapTemplate$class.genericMapCombiner(this);
            }

            public Builder<Tuple2<K, S>, ParIterable<Tuple2<K, S>>> newBuilder() {
                return GenericParTemplate$class.newBuilder(this);
            }

            public <B> Combiner<B, ParIterable<B>> genericBuilder() {
                return GenericParTemplate$class.genericBuilder(this);
            }

            public <B> Combiner<B, ParIterable<B>> genericCombiner() {
                return GenericParTemplate$class.genericCombiner(this);
            }

            public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<Tuple2<K, S>, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<Tuple2<K, S>, Tuple3<A1, A2, A3>> asTriple) {
                return GenericTraversableTemplate$class.unzip3(this, asTriple);
            }

            public GenTraversable flatten(Function1 asTraversable) {
                return GenericTraversableTemplate$class.flatten(this, asTraversable);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public int hashCode() {
                return GenMapLike$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return GenMapLike$class.equals(this, that);
            }

            public <U> void foreach(Function1<Tuple2<K, S>, U> g) {
                this.$outer.withFilter(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final boolean apply(Tuple2<K, V> check$ifrefutable$3) {
                        boolean bl = check$ifrefutable$3 != null;
                        return bl;
                    }
                }).foreach(new Serializable(this, g){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.2 $outer;
                    private final Function1 g$1;

                    public final U apply(Tuple2<K, V> x$7) {
                        if (x$7 != null) {
                            return (U)this.g$1.apply(new Tuple2<K, R>(x$7._1(), this.$outer.f$4.apply(x$7._2())));
                        }
                        throw new MatchError(x$7);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.g$1 = g$1;
                    }
                });
            }

            public IterableSplitter.Mapped<Tuple2<K, S>> splitter() {
                return this.$outer.splitter().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParMapLike$.anon.2 $outer;

                    public final Tuple2<K, S> apply(Tuple2<K, V> kv) {
                        return new Tuple2<K, R>(kv._1(), this.$outer.f$4.apply(kv._2()));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public int size() {
                return this.$outer.size();
            }

            public boolean contains(K key) {
                return this.$outer.contains(key);
            }

            public Option<S> get(K key) {
                Function1 function1 = this.f$4;
                Option<B> option = this.$outer.get(key);
                return option.isEmpty() ? None$.MODULE$ : new Some<R>(function1.apply(option.get()));
            }

            public Map<K, S> seq() {
                return ((MapLike)this.$outer.seq()).mapValues(this.f$4);
            }

            public <U> ParMap<K, U> $plus(Tuple2<K, U> kv) {
                return ((ParIterableLike)ParMap$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParMap$.MODULE$.canBuildFrom()).$plus(kv);
            }

            public ParMap<K, S> $minus(K key) {
                return (ParMap)((GenMapLike)((ParIterableLike)ParMap$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParMap$.MODULE$.canBuildFrom())).$minus(key);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$4 = f$4;
                Parallelizable$class.$init$(this);
                GenMapLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                GenIterable$class.$init$(this);
                GenericParTemplate$class.$init$(this);
                GenericParMapTemplate$class.$init$(this);
                CustomParallelizable$class.$init$(this);
                ParIterableLike$class.$init$(this);
                ParIterable$class.$init$(this);
                ParMapLike$class.$init$(this);
                ParMap$class.$init$(this);
            }
        };
    }

    public static void $init$(ParMapLike $this) {
    }
}

