/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
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
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike;
import scala.collection.SeqLike$;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$class;
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
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeq$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.Ordering$;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;

public abstract class SeqLike$class {
    public static Seq thisCollection(SeqLike $this) {
        return (Seq)$this;
    }

    public static Seq toCollection(SeqLike $this, Object repr) {
        return (Seq)repr;
    }

    public static Combiner parCombiner(SeqLike $this) {
        return ParSeq$.MODULE$.newCombiner();
    }

    public static int lengthCompare(SeqLike $this, int len) {
        int n;
        if (len < 0) {
            n = 1;
        } else {
            int i = 0;
            Iterator it = $this.iterator();
            while (it.hasNext()) {
                if (i == len) {
                    return it.hasNext() ? 1 : 0;
                }
                it.next();
                ++i;
            }
            n = i - len;
        }
        return n;
    }

    public static boolean isEmpty(SeqLike $this) {
        return $this.lengthCompare(0) == 0;
    }

    public static int size(SeqLike $this) {
        return $this.length();
    }

    /*
     * WARNING - void declaration
     */
    public static int segmentLength(SeqLike $this, Function1 p, int from2) {
        void var3_3;
        int i = 0;
        Iterator it = $this.iterator().drop(from2);
        while (it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
            ++i;
        }
        return (int)var3_3;
    }

    public static int indexWhere(SeqLike $this, Function1 p, int from2) {
        int i = from2;
        Iterator it = $this.iterator().drop(from2);
        while (it.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    /*
     * WARNING - void declaration
     */
    public static int lastIndexWhere(SeqLike $this, Function1 p, int end) {
        void var3_3;
        int i = $this.length() - 1;
        Iterator it = $this.reverseIterator();
        while (it.hasNext()) {
            Object elem = it.next();
            if (!(i > end || !BoxesRunTime.unboxToBoolean(p.apply(elem)))) break;
            --i;
        }
        return (int)var3_3;
    }

    public static Iterator permutations(SeqLike $this) {
        return $this.isEmpty() ? Iterator$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{$this.repr()})) : new SeqLike.PermutationsItr($this);
    }

    public static Iterator combinations(SeqLike $this, int n) {
        return n < 0 || n > $this.size() ? Iterator$.MODULE$.empty() : new SeqLike.CombinationsItr($this, n);
    }

    public static Object reverse(SeqLike $this) {
        ObjectRef<Nil$> xs = ObjectRef.create(Nil$.MODULE$);
        $this.foreach(new Serializable($this, xs){
            public static final long serialVersionUID = 0L;
            private final ObjectRef xs$1;

            public final void apply(A x) {
                this.xs$1.elem = ((List)this.xs$1.elem).$colon$colon(x);
            }
            {
                this.xs$1 = xs$1;
            }
        });
        Builder b = $this.newBuilder();
        b.sizeHint($this);
        Serializable serializable = new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            public final Builder b$1;

            public final Builder<A, Repr> apply(A x) {
                return this.b$1.$plus$eq(x);
            }
            {
                this.b$1 = b$1;
            }
        };
        List these1 = (List)xs.elem;
        while (!these1.isEmpty()) {
            Object a = these1.head();
            serializable.b$1.$plus$eq(a);
            these1 = (List)these1.tail();
        }
        return b.result();
    }

    public static Object reverseMap(SeqLike $this, Function1 f, CanBuildFrom bf) {
        ObjectRef<Nil$> xs = ObjectRef.create(Nil$.MODULE$);
        $this.foreach(new Serializable($this, xs){
            public static final long serialVersionUID = 0L;
            private final ObjectRef xs$2;

            public final void apply(A x) {
                this.xs$2.elem = ((List)this.xs$2.elem).$colon$colon(x);
            }
            {
                this.xs$2 = xs$2;
            }
        });
        Builder b = bf.apply($this.repr());
        Serializable serializable = new Serializable($this, b, f){
            public static final long serialVersionUID = 0L;
            public final Builder b$2;
            public final Function1 f$1;

            public final Builder<B, That> apply(A x) {
                return this.b$2.$plus$eq(this.f$1.apply(x));
            }
            {
                void var3_3;
                this.b$2 = b$2;
                this.f$1 = var3_3;
            }
        };
        List these1 = (List)xs.elem;
        while (!these1.isEmpty()) {
            Object a = these1.head();
            serializable.b$2.$plus$eq(serializable.f$1.apply(a));
            these1 = (List)these1.tail();
        }
        return b.result();
    }

    public static Iterator reverseIterator(SeqLike $this) {
        return $this.toCollection($this.reverse()).iterator();
    }

    public static boolean startsWith(SeqLike $this, GenSeq that, int offset) {
        Iterator i = $this.iterator().drop(offset);
        Iterator j = that.iterator();
        while (j.hasNext() && i.hasNext()) {
            Object a = j.next();
            Object a2 = i.next();
            if (a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a))))) continue;
            return false;
        }
        return !j.hasNext();
    }

    public static boolean endsWith(SeqLike $this, GenSeq that) {
        Iterator i = $this.iterator().drop($this.length() - that.length());
        Iterator j = that.iterator();
        while (i.hasNext() && j.hasNext()) {
            Object a = j.next();
            Object a2 = i.next();
            if (a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a))))) continue;
            return false;
        }
        return !j.hasNext();
    }

    public static int indexOfSlice(SeqLike $this, GenSeq that) {
        return $this.indexOfSlice(that, 0);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public static int indexOfSlice(SeqLike $this, GenSeq that, int from2) {
        if ($this.hasDefiniteSize() && that.hasDefiniteSize()) {
            int n;
            int l = $this.length();
            int tl = that.length();
            int clippedFrom = package$.MODULE$.max(0, from2);
            if (from2 > l) {
                return -1;
            }
            if (tl < 1) {
                n = clippedFrom;
                return n;
            }
            if (l < tl) {
                return -1;
            }
            n = SeqLike$.MODULE$.scala$collection$SeqLike$$kmpSearch($this.thisCollection(), clippedFrom, l, that.seq(), 0, tl, true);
            return n;
        }
        int i = from2;
        Seq s2 = (Seq)$this.thisCollection().drop(from2);
        while (!s2.isEmpty()) {
            void var6_6;
            Seq seq;
            if (seq.startsWith(that)) {
                return (int)var6_6;
            }
            ++var6_6;
            seq = (Seq)seq.tail();
        }
        return -1;
    }

    public static int lastIndexOfSlice(SeqLike $this, GenSeq that) {
        return $this.lastIndexOfSlice(that, $this.length());
    }

    public static int lastIndexOfSlice(SeqLike $this, GenSeq that, int end) {
        int l = $this.length();
        int tl = that.length();
        int clippedL = package$.MODULE$.min(l - tl, end);
        return end < 0 ? -1 : (tl < 1 ? clippedL : (l < tl ? -1 : SeqLike$.MODULE$.scala$collection$SeqLike$$kmpSearch($this.thisCollection(), 0, clippedL + tl, that.seq(), 0, tl, false)));
    }

    public static boolean containsSlice(SeqLike $this, GenSeq that) {
        return $this.indexOfSlice(that) != -1;
    }

    public static boolean contains(SeqLike $this, Object elem) {
        return $this.exists(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$1;

            public final boolean apply(A x$12) {
                Object object = this.elem$1;
                return x$12 == object ? true : (x$12 == null ? false : (x$12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)x$12, object) : (x$12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)x$12, object) : x$12.equals(object))));
            }
            {
                this.elem$1 = elem$1;
            }
        });
    }

    public static Object union(SeqLike $this, GenSeq that, CanBuildFrom bf) {
        return $this.$plus$plus(that, bf);
    }

    public static Object diff(SeqLike $this, GenSeq that) {
        Map occ = SeqLike$class.occCounts($this, that.seq());
        Builder b = $this.newBuilder();
        $this.foreach(new Serializable($this, occ, b){
            public static final long serialVersionUID = 0L;
            private final Map occ$1;
            private final Builder b$3;

            public final Object apply(A x) {
                Object object;
                int ox = BoxesRunTime.unboxToInt(this.occ$1.apply(x));
                if (ox == 0) {
                    object = this.b$3.$plus$eq(x);
                } else {
                    this.occ$1.update(x, BoxesRunTime.boxToInteger(ox - 1));
                    object = BoxedUnit.UNIT;
                }
                return object;
            }
            {
                void var3_3;
                this.occ$1 = occ$1;
                this.b$3 = var3_3;
            }
        });
        return b.result();
    }

    public static Object intersect(SeqLike $this, GenSeq that) {
        Map occ = SeqLike$class.occCounts($this, that.seq());
        Builder b = $this.newBuilder();
        $this.foreach(new Serializable($this, occ, b){
            public static final long serialVersionUID = 0L;
            private final Map occ$2;
            private final Builder b$4;

            public final void apply(A x) {
                int ox = BoxesRunTime.unboxToInt(this.occ$2.apply(x));
                if (ox > 0) {
                    this.b$4.$plus$eq(x);
                    this.occ$2.update(x, BoxesRunTime.boxToInteger(ox - 1));
                }
            }
            {
                void var3_3;
                this.occ$2 = occ$2;
                this.b$4 = var3_3;
            }
        });
        return b.result();
    }

    /*
     * WARNING - void declaration
     */
    private static Map occCounts(SeqLike $this, Seq sq) {
        void var2_2;
        HashMap<Object, Object> occ = new HashMap<Object, Object>($this){

            public int default(Object k) {
                return 0;
            }
        };
        sq.foreach(new Serializable($this, (HashMap)occ){
            public static final long serialVersionUID = 0L;
            private final HashMap occ$3;

            public final void apply(Object y) {
                this.occ$3.update(y, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$3.apply(y)) + 1));
            }
            {
                this.occ$3 = occ$3;
            }
        });
        return var2_2;
    }

    public static Object distinct(SeqLike $this) {
        Builder b = $this.newBuilder();
        HashSet seen = (HashSet)HashSet$.MODULE$.apply(Nil$.MODULE$);
        $this.foreach(new Serializable($this, b, seen){
            public static final long serialVersionUID = 0L;
            private final Builder b$5;
            private final HashSet seen$1;

            public final Object apply(A x) {
                Object object;
                if (this.seen$1.apply(x)) {
                    object = BoxedUnit.UNIT;
                } else {
                    this.b$5.$plus$eq(x);
                    object = this.seen$1.$plus$eq((Object)x);
                }
                return object;
            }
            {
                void var3_3;
                this.b$5 = b$5;
                this.seen$1 = var3_3;
            }
        });
        return b.result();
    }

    public static Object patch(SeqLike $this, int from2, GenSeq patch2, int replaced, CanBuildFrom bf) {
        int i;
        Builder b = bf.apply($this.repr());
        Iterator it = $this.iterator();
        for (i = 0; i < from2 && it.hasNext(); ++i) {
            b.$plus$eq(it.next());
        }
        b.$plus$plus$eq(patch2.seq());
        for (i = replaced; i > 0 && it.hasNext(); --i) {
            it.next();
        }
        while (it.hasNext()) {
            b.$plus$eq(it.next());
        }
        return b.result();
    }

    public static Object updated(SeqLike $this, int index, Object elem, CanBuildFrom bf) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(index)).toString());
        }
        Builder b = bf.apply($this.repr());
        Iterator it = $this.iterator();
        for (int i = 0; i < index && it.hasNext(); ++i) {
            b.$plus$eq((Object)it.next());
        }
        if (it.hasNext()) {
            b.$plus$eq(elem);
            it.next();
            while (it.hasNext()) {
                b.$plus$eq((Object)it.next());
            }
            return b.result();
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(index)).toString());
    }

    public static Object $plus$colon(SeqLike $this, Object elem, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        b.$plus$eq(elem);
        b.$plus$plus$eq($this.thisCollection());
        return b.result();
    }

    public static Object $colon$plus(SeqLike $this, Object elem, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        b.$plus$plus$eq($this.thisCollection());
        b.$plus$eq(elem);
        return b.result();
    }

    public static Object padTo(SeqLike $this, int len, Object elem, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        int L = $this.length();
        b.sizeHint(package$.MODULE$.max(L, len));
        b.$plus$plus$eq($this.thisCollection());
        for (int diff2 = len - L; diff2 > 0; --diff2) {
            b.$plus$eq(elem);
        }
        return b.result();
    }

    public static boolean corresponds(SeqLike $this, GenSeq that, Function2 p) {
        Iterator i = $this.iterator();
        Iterator j = that.iterator();
        while (i.hasNext() && j.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(p.apply(i.next(), j.next()))) continue;
            return false;
        }
        return !i.hasNext() && !j.hasNext();
    }

    public static Object sortWith(SeqLike $this, Function2 lt) {
        Ordering$ ordering$ = Ordering$.MODULE$;
        return $this.sorted(new Ordering<T>(lt){
            private final Function2 cmp$1;

            public Some<Object> tryCompare(T x, T y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean equiv(T x, T y) {
                return Ordering$class.equiv(this, x, y);
            }

            public T max(T x, T y) {
                return (T)Ordering$class.max(this, x, y);
            }

            public T min(T x, T y) {
                return (T)Ordering$class.min(this, x, y);
            }

            public Ordering<T> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, T> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(T lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y)) ? -1 : (BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x)) ? 1 : 0);
            }

            public boolean lt(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
            }

            public boolean gt(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
            }

            public boolean gteq(T x, T y) {
                return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
            }

            public boolean lteq(T x, T y) {
                return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
            }
            {
                this.cmp$1 = cmp$1;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        });
    }

    public static Object sortBy(SeqLike $this, Function1 f, Ordering ord) {
        return $this.sorted(ord.on(f));
    }

    public static Object sorted(SeqLike $this, Ordering ord) {
        Object object;
        int len = $this.length();
        Builder b = $this.newBuilder();
        if (len == 1) {
            object = b.$plus$plus$eq($this);
        } else {
            if (len > 1) {
                b.sizeHint(len);
                Object[] arr = new Object[len];
                IntRef i = IntRef.create(0);
                $this.foreach(new Serializable($this, arr, i){
                    public static final long serialVersionUID = 0L;
                    private final Object[] arr$1;
                    private final IntRef i$1;

                    public final void apply(A x) {
                        this.arr$1[this.i$1.elem] = x;
                        ++this.i$1.elem;
                    }
                    {
                        void var3_3;
                        this.arr$1 = arr$1;
                        this.i$1 = var3_3;
                    }
                });
                Arrays.sort(arr, ord);
                i.elem = 0;
                while (i.elem < arr.length) {
                    b.$plus$eq(arr[i.elem]);
                    ++i.elem;
                }
            }
            object = BoxedUnit.UNIT;
        }
        return b.result();
    }

    public static Seq toSeq(SeqLike $this) {
        return $this.thisCollection();
    }

    public static Range indices(SeqLike $this) {
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.until$extension0(0, $this.length());
    }

    public static SeqView view(SeqLike $this) {
        return new SeqView<A, Repr>($this){
            private Repr underlying;
            private final /* synthetic */ SeqLike $outer;
            private volatile boolean bitmap$0;

            private Object underlying$lzycompute() {
                SeqLike$.anon.2 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = this.$outer.repr();
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public <B> SeqViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return SeqViewLike$class.newForced(this, xs);
            }

            public <B> SeqViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return SeqViewLike$class.newAppended(this, that);
            }

            public <B> SeqViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return SeqViewLike$class.newMapped(this, f);
            }

            public <B> SeqViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return SeqViewLike$class.newFlatMapped(this, f);
            }

            public SeqViewLike.Transformed<A> newFiltered(Function1<A, Object> p) {
                return SeqViewLike$class.newFiltered(this, p);
            }

            public SeqViewLike.Transformed<A> newSliced(SliceInterval _endpoints) {
                return SeqViewLike$class.newSliced(this, _endpoints);
            }

            public SeqViewLike.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return SeqViewLike$class.newDroppedWhile(this, p);
            }

            public SeqViewLike.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return SeqViewLike$class.newTakenWhile(this, p);
            }

            public <B> SeqViewLike.Transformed<Tuple2<A, B>> newZipped(GenIterable<B> that) {
                return SeqViewLike$class.newZipped(this, that);
            }

            public <A1, B> SeqViewLike.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
                return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
            }

            public SeqViewLike.Transformed<A> newReversed() {
                return SeqViewLike$class.newReversed(this);
            }

            public <B> SeqViewLike.Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
                return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
            }

            public <B> SeqViewLike.Transformed<B> newPrepended(B elem) {
                return SeqViewLike$class.newPrepended(this, elem);
            }

            public SeqViewLike.Transformed<A> newTaken(int n) {
                return SeqViewLike$class.newTaken(this, n);
            }

            public SeqViewLike.Transformed<A> newDropped(int n) {
                return SeqViewLike$class.newDropped(this, n);
            }

            public SeqView<A, Repr> reverse() {
                return SeqViewLike$class.reverse(this);
            }

            public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.patch(this, from2, patch2, replaced, bf);
            }

            public <B, That> That padTo(int len, B elem, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.padTo(this, len, elem, bf);
            }

            public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.reverseMap(this, f, bf);
            }

            public <B, That> That updated(int index, B elem, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.updated(this, index, elem, bf);
            }

            public <B, That> That $plus$colon(B elem, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
            }

            public <B, That> That $colon$plus(B elem, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
            }

            public <B, That> That union(GenSeq<B> that, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.union(this, that, bf);
            }

            public <B> SeqView<A, Repr> diff(GenSeq<B> that) {
                return SeqViewLike$class.diff(this, that);
            }

            public <B> SeqView<A, Repr> intersect(GenSeq<B> that) {
                return SeqViewLike$class.intersect(this, that);
            }

            public <B> SeqView<A, Repr> sorted(Ordering<B> ord) {
                return SeqViewLike$class.sorted(this, ord);
            }

            public SeqView<A, Repr> sortWith(Function2<A, A, Object> lt) {
                return SeqViewLike$class.sortWith(this, lt);
            }

            public <B> SeqView<A, Repr> sortBy(Function1<A, B> f, Ordering<B> ord) {
                return SeqViewLike$class.sortBy(this, f, ord);
            }

            public Iterator<SeqView<A, Repr>> combinations(int n) {
                return SeqViewLike$class.combinations(this, n);
            }

            public Iterator<SeqView<A, Repr>> permutations() {
                return SeqViewLike$class.permutations(this);
            }

            public SeqView<A, Repr> distinct() {
                return SeqViewLike$class.distinct(this);
            }

            public String stringPrefix() {
                return SeqViewLike$class.stringPrefix(this);
            }

            public IterableView drop(int n) {
                return IterableViewLike$class.drop(this, n);
            }

            public IterableView take(int n) {
                return IterableViewLike$class.take(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<SeqView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zip(this, that, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<SeqView<A, Repr>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableViewLike$class.zipWithIndex(this, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<SeqView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public Iterator<SeqView<A, Repr>> grouped(int size2) {
                return IterableViewLike$class.grouped(this, size2);
            }

            public Iterator<SeqView<A, Repr>> sliding(int size2, int step) {
                return IterableViewLike$class.sliding(this, size2, step);
            }

            public Iterator<SeqView<A, Repr>> sliding(int size2) {
                return IterableViewLike$class.sliding(this, size2);
            }

            public IterableView dropRight(int n) {
                return IterableViewLike$class.dropRight(this, n);
            }

            public IterableView takeRight(int n) {
                return IterableViewLike$class.takeRight(this, n);
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

            public Builder<A, SeqView<A, Repr>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That force(CanBuildFrom<Repr, B, That> bf) {
                return (That)TraversableViewLike$class.force(this, bf);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
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

            public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> partition(Function1<A, Object> p) {
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

            public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> span(Function1<A, Object> p) {
                return TraversableViewLike$class.span(this, p);
            }

            public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> splitAt(int n) {
                return TraversableViewLike$class.splitAt(this, n);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> scala.collection.immutable.Map<K, SeqView<A, Repr>> groupBy(Function1<A, K> f) {
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

            public Iterator<SeqView<A, Repr>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<SeqView<A, Repr>> tails() {
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

            public GenericCompanion<Seq> companion() {
                return Seq$class.companion(this);
            }

            public Seq<A> seq() {
                return Seq$class.seq(this);
            }

            public Seq<A> thisCollection() {
                return SeqLike$class.thisCollection(this);
            }

            public Seq toCollection(Object repr) {
                return SeqLike$class.toCollection(this, repr);
            }

            public Combiner<A, ParSeq<A>> parCombiner() {
                return SeqLike$class.parCombiner(this);
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

            public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
                return SeqLike$class.corresponds(this, that, p);
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

            public SeqView<A, SeqView<A, Repr>> view(int from2, int until2) {
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

            public int hashCode() {
                return GenSeqLike$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return GenSeqLike$class.equals(this, that);
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

            public <B> Builder<B, Seq<B>> genericBuilder() {
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

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<SeqView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<SeqView<A, Repr>, B, That> cbf) {
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

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
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

            public Repr underlying() {
                return (Repr)(this.bitmap$0 ? this.underlying : this.underlying$lzycompute());
            }

            public Iterator<A> iterator() {
                return this.$outer.iterator();
            }

            public int length() {
                return this.$outer.length();
            }

            public A apply(int idx) {
                return this.$outer.apply(idx);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                Iterable$class.$init$(this);
                GenSeqLike$class.$init$(this);
                GenSeq$class.$init$(this);
                SeqLike$class.$init$(this);
                Seq$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
                IterableViewLike$class.$init$(this);
                SeqViewLike$class.$init$(this);
            }
        };
    }

    public static SeqView view(SeqLike $this, int from2, int until2) {
        return (SeqView)$this.view().slice(from2, until2);
    }

    public static String toString(SeqLike $this) {
        return TraversableLike$class.toString($this);
    }

    public static void $init$(SeqLike $this) {
    }
}

