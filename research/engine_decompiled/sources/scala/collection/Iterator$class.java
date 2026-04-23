/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

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
import scala.StringContext;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.BufferedIterator;
import scala.collection.BufferedIterator$class;
import scala.collection.GenTraversableOnce;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Traversable;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream$;
import scala.collection.immutable.Stream$Empty$;
import scala.collection.immutable.Stream$cons$;
import scala.collection.mutable.Buffer$;
import scala.collection.mutable.Queue;
import scala.collection.mutable.StringBuilder;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class Iterator$class {
    public static Iterator seq(Iterator $this) {
        return $this;
    }

    public static boolean isEmpty(Iterator $this) {
        return !$this.hasNext();
    }

    public static boolean isTraversableAgain(Iterator $this) {
        return false;
    }

    public static boolean hasDefiniteSize(Iterator $this) {
        return $this.isEmpty();
    }

    public static Iterator take(Iterator $this, int n) {
        return $this.slice(0, n);
    }

    public static Iterator drop(Iterator $this, int n) {
        for (int j = 0; j < n && $this.hasNext(); ++j) {
            $this.next();
        }
        return $this;
    }

    public static Iterator slice(Iterator $this, int from2, int until2) {
        int lo;
        Predef$ predef$ = Predef$.MODULE$;
        for (int toDrop = lo = RichInt$.MODULE$.max$extension(from2, 0); toDrop > 0 && $this.hasNext(); --toDrop) {
            $this.next();
        }
        return new AbstractIterator<A>($this, lo, until2){
            private int remaining;
            private final /* synthetic */ Iterator $outer;

            private int remaining() {
                return this.remaining;
            }

            private void remaining_$eq(int x$1) {
                this.remaining = x$1;
            }

            public boolean hasNext() {
                return this.remaining() > 0 && this.$outer.hasNext();
            }

            public A next() {
                Nothing$ nothing$;
                if (this.remaining() > 0) {
                    this.remaining_$eq(this.remaining() - 1);
                    nothing$ = this.$outer.next();
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.remaining = until$1 - lo$1;
            }
        };
    }

    public static Iterator map(Iterator $this, Function1 f) {
        return new AbstractIterator<B>($this, f){
            private final /* synthetic */ Iterator $outer;
            private final Function1 f$3;

            public boolean hasNext() {
                return this.$outer.hasNext();
            }

            public B next() {
                return (B)this.f$3.apply(this.$outer.next());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$3 = f$3;
            }
        };
    }

    public static Iterator $plus$plus(Iterator $this, Function0 that) {
        return new Iterator.JoinIterator($this, that);
    }

    public static Iterator flatMap(Iterator $this, Function1 f) {
        return new AbstractIterator<B>($this, f){
            private Iterator<B> cur;
            private final /* synthetic */ Iterator $outer;
            private final Function1 f$4;

            private Iterator<B> cur() {
                return this.cur;
            }

            private void cur_$eq(Iterator<B> x$1) {
                this.cur = x$1;
            }

            private void nextCur() {
                this.cur_$eq(((GenTraversableOnce)this.f$4.apply(this.$outer.next())).toIterator());
            }

            public boolean hasNext() {
                while (true) {
                    if (this.cur().hasNext()) {
                        return true;
                    }
                    if (!this.$outer.hasNext()) break;
                    this.nextCur();
                }
                return false;
            }

            public B next() {
                return (B)(this.hasNext() ? this.cur() : Iterator$.MODULE$.empty()).next();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$4 = f$4;
                this.cur = Iterator$.MODULE$.empty();
            }
        };
    }

    public static Iterator filter(Iterator $this, Function1 p) {
        return new AbstractIterator<A>($this, p){
            private A hd;
            private boolean hdDefined;
            private final /* synthetic */ Iterator $outer;
            private final Function1 p$1;

            private A hd() {
                return this.hd;
            }

            private void hd_$eq(A x$1) {
                this.hd = x$1;
            }

            private boolean hdDefined() {
                return this.hdDefined;
            }

            private void hdDefined_$eq(boolean x$1) {
                this.hdDefined = x$1;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean hasNext() {
                if (this.hdDefined()) return true;
                while (this.$outer.hasNext()) {
                    this.hd_$eq(this.$outer.next());
                    if (!BoxesRunTime.unboxToBoolean(this.p$1.apply(this.hd()))) continue;
                    this.hdDefined_$eq(true);
                    return true;
                }
                return false;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    this.hdDefined_$eq(false);
                    nothing$ = this.hd();
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
                this.hdDefined = false;
            }
        };
    }

    public static boolean corresponds(Iterator $this, GenTraversableOnce that, Function2 p) {
        Iterator that0 = that.toIterator();
        while ($this.hasNext() && that0.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(p.apply($this.next(), that0.next()))) continue;
            return false;
        }
        return $this.hasNext() == that0.hasNext();
    }

    public static Iterator withFilter(Iterator $this, Function1 p) {
        return $this.filter(p);
    }

    public static Iterator filterNot(Iterator $this, Function1 p) {
        return $this.filter(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$2;

            public final boolean apply(A x$1) {
                return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
            }
            {
                this.p$2 = p$2;
            }
        });
    }

    public static Iterator collect(Iterator $this, PartialFunction pf) {
        return new AbstractIterator<B>($this, pf){
            private A hd;
            private int status;
            private final /* synthetic */ Iterator $outer;
            private final PartialFunction pf$1;

            public boolean hasNext() {
                while (this.status == 0) {
                    if (this.$outer.hasNext()) {
                        this.hd = this.$outer.next();
                        if (!this.pf$1.isDefinedAt(this.hd)) continue;
                        this.status = 1;
                        continue;
                    }
                    this.status = -1;
                }
                return this.status == 1;
            }

            public B next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    this.status = 0;
                    nothing$ = this.pf$1.apply(this.hd);
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (B)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pf$1 = pf$1;
                this.status = 0;
            }
        };
    }

    public static Iterator scanLeft(Iterator $this, Object z, Function2 op) {
        return new AbstractIterator<B>($this, z, op){
            private boolean hasNext;
            private B elem;
            private final /* synthetic */ Iterator $outer;
            private final Function2 op$1;

            public boolean hasNext() {
                return this.hasNext;
            }

            private void hasNext_$eq(boolean x$1) {
                this.hasNext = x$1;
            }

            private B elem() {
                return this.elem;
            }

            private void elem_$eq(B x$1) {
                this.elem = x$1;
            }

            /*
             * WARNING - void declaration
             */
            public B next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    B res = this.elem();
                    if (this.$outer.hasNext()) {
                        this.elem_$eq(this.op$1.apply(this.elem(), this.$outer.next()));
                    } else {
                        this.hasNext_$eq(false);
                    }
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (B)nothing$;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.op$1 = var3_3;
                this.hasNext = true;
                this.elem = z$1;
            }
        };
    }

    public static Iterator scanRight(Iterator $this, Object z, Function2 op) {
        return ((IterableLike)$this.toBuffer().scanRight(z, op, Buffer$.MODULE$.canBuildFrom())).iterator();
    }

    public static Iterator takeWhile(Iterator $this, Function1 p) {
        return new AbstractIterator<A>($this, p){
            private A hd;
            private boolean hdDefined;
            private Iterator<A> tail;
            private final Function1 p$3;

            private A hd() {
                return this.hd;
            }

            private void hd_$eq(A x$1) {
                this.hd = x$1;
            }

            private boolean hdDefined() {
                return this.hdDefined;
            }

            private void hdDefined_$eq(boolean x$1) {
                this.hdDefined = x$1;
            }

            private Iterator<A> tail() {
                return this.tail;
            }

            private void tail_$eq(Iterator<A> x$1) {
                this.tail = x$1;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean hasNext() {
                if (this.hdDefined()) return true;
                if (!this.tail().hasNext()) return false;
                this.hd_$eq(this.tail().next());
                if (BoxesRunTime.unboxToBoolean(this.p$3.apply(this.hd()))) {
                    this.hdDefined_$eq(true);
                } else {
                    this.tail_$eq(Iterator$.MODULE$.empty());
                }
                if (!this.hdDefined()) return false;
                return true;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    this.hdDefined_$eq(false);
                    nothing$ = this.hd();
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                this.p$3 = p$3;
                this.hdDefined = false;
                this.tail = $outer;
            }
        };
    }

    public static Tuple2 partition(Iterator $this, Function1 p) {
        BufferedIterator self = $this.buffered();
        public class Scala_collection_Iterator$PartitionIterator$1
        extends AbstractIterator<A> {
            private final Function1<A, Object> p;
            private Scala_collection_Iterator$PartitionIterator$1 other;
            private final Queue<A> lookahead;
            public final /* synthetic */ Iterator $outer;
            private final BufferedIterator self$1;

            public Scala_collection_Iterator$PartitionIterator$1 other() {
                return this.other;
            }

            public void other_$eq(Scala_collection_Iterator$PartitionIterator$1 x$1) {
                this.other = x$1;
            }

            public Queue<A> lookahead() {
                return this.lookahead;
            }

            public void skip() {
                while (this.self$1.hasNext() && !BoxesRunTime.unboxToBoolean(this.p.apply(this.self$1.head()))) {
                    this.other().lookahead().$plus$eq((Object)this.self$1.next());
                }
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean hasNext() {
                if (!this.lookahead().isEmpty()) return true;
                this.skip();
                if (this.self$1.hasNext()) return true;
                return false;
            }

            public A next() {
                A a;
                if (this.lookahead().isEmpty()) {
                    this.skip();
                    a = this.self$1.next();
                } else {
                    a = this.lookahead().dequeue();
                }
                return a;
            }

            public /* synthetic */ Iterator scala$collection$Iterator$PartitionIterator$$$outer() {
                return this.$outer;
            }

            public Scala_collection_Iterator$PartitionIterator$1(Iterator<A> p, Function1<A, Object> self$1) {
                this.p = p;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.self$1 = self$1;
                this.lookahead = new Queue<A>();
            }
        }
        Scala_collection_Iterator$PartitionIterator$1 l = new Scala_collection_Iterator$PartitionIterator$1($this, p, self);
        Scala_collection_Iterator$PartitionIterator$1 r = new Scala_collection_Iterator$PartitionIterator$1($this, (Function1)((Object)new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$4;

            public final boolean apply(A x$2) {
                return !BoxesRunTime.unboxToBoolean(this.p$4.apply(x$2));
            }
            {
                this.p$4 = p$4;
            }
        }), self);
        l.other_$eq(r);
        r.other_$eq(l);
        return new Tuple2<Scala_collection_Iterator$PartitionIterator$1, Scala_collection_Iterator$PartitionIterator$1>(l, r);
    }

    public static Tuple2 span(Iterator $this, Function1 p) {
        public class Scala_collection_Iterator$Leading$1
        extends AbstractIterator<A> {
            private Queue<A> lookahead;
            private A hd;
            private int status;
            public final /* synthetic */ Iterator $outer;
            private final Function1 p$5;

            private void store(A a) {
                if (this.lookahead == null) {
                    this.lookahead = new Queue<A>();
                }
                this.lookahead.$plus$eq((Object)a);
            }

            public boolean hasNext() {
                boolean bl;
                if (this.status < 0) {
                    bl = this.lookahead != null && this.lookahead.nonEmpty();
                } else if (this.status > 0) {
                    bl = true;
                } else {
                    if (this.scala$collection$Iterator$Leading$$$outer().hasNext()) {
                        this.hd = this.scala$collection$Iterator$Leading$$$outer().next();
                        this.status = BoxesRunTime.unboxToBoolean(this.p$5.apply(this.hd)) ? 1 : -2;
                    } else {
                        this.status = -1;
                    }
                    bl = this.status > 0;
                }
                return bl;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    if (this.status == 1) {
                        this.status = 0;
                        nothing$ = this.hd;
                    } else {
                        nothing$ = this.lookahead.dequeue();
                    }
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }

            public boolean finish() {
                boolean bl;
                int n = this.status;
                switch (n) {
                    default: {
                        throw new MatchError(BoxesRunTime.boxToInteger(n));
                    }
                    case 0: {
                        this.status = -1;
                        while (this.scala$collection$Iterator$Leading$$$outer().hasNext()) {
                            A a = this.scala$collection$Iterator$Leading$$$outer().next();
                            if (BoxesRunTime.unboxToBoolean(this.p$5.apply(a))) {
                                this.store(a);
                                continue;
                            }
                            this.hd = a;
                            return true;
                        }
                        bl = false;
                        break;
                    }
                    case 1: {
                        this.store(this.hd);
                        this.status = 0;
                        bl = this.finish();
                        break;
                    }
                    case -1: {
                        bl = false;
                        break;
                    }
                    case -2: {
                        this.status = -1;
                        bl = true;
                    }
                }
                return bl;
            }

            public A trailer() {
                return this.hd;
            }

            public /* synthetic */ Iterator scala$collection$Iterator$Leading$$$outer() {
                return this.$outer;
            }

            public Scala_collection_Iterator$Leading$1(Iterator<A> p$5) {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$5 = p$5;
                this.lookahead = null;
                this.status = 0;
            }
        }
        Scala_collection_Iterator$Leading$1 leading = new Scala_collection_Iterator$Leading$1($this, p);
        AbstractIterator trailing = new AbstractIterator<A>($this, leading){
            private Scala_collection_Iterator$Leading$1 myLeading;
            private int status;
            private final /* synthetic */ Iterator $outer;

            public boolean hasNext() {
                boolean bl;
                if (this.status > 0) {
                    bl = this.$outer.hasNext();
                } else if (this.status == 0) {
                    bl = true;
                } else if (this.myLeading.finish()) {
                    this.status = 0;
                    bl = true;
                } else {
                    this.status = 1;
                    this.myLeading = null;
                    bl = this.$outer.hasNext();
                }
                return bl;
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    if (this.status > 0) {
                        nothing$ = this.$outer.next();
                    } else {
                        void var1_1;
                        this.status = 1;
                        A ans = this.myLeading.trailer();
                        this.myLeading = null;
                        nothing$ = var1_1;
                    }
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }

            public String toString() {
                return "unknown-if-empty iterator";
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.myLeading = leading$1;
                this.status = -1;
            }
        };
        return new Tuple2<Scala_collection_Iterator$Leading$1, Iterator$.anon.17>(leading, trailing);
    }

    public static Iterator dropWhile(Iterator $this, Function1 p) {
        return new AbstractIterator<A>($this, p){
            private int status;
            private A fst;
            private final /* synthetic */ Iterator $outer;
            private final Function1 p$6;

            public boolean hasNext() {
                boolean bl;
                if (this.status == 1) {
                    bl = this.$outer.hasNext();
                } else if (this.status == 0) {
                    bl = true;
                } else {
                    while (this.$outer.hasNext()) {
                        A a = this.$outer.next();
                        if (BoxesRunTime.unboxToBoolean(this.p$6.apply(a))) continue;
                        this.fst = a;
                        this.status = 0;
                        return true;
                    }
                    this.status = 1;
                    bl = false;
                }
                return bl;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    if (this.status == 1) {
                        nothing$ = this.$outer.next();
                    } else {
                        this.status = 1;
                        nothing$ = this.fst;
                    }
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$6 = p$6;
                this.status = -1;
            }
        };
    }

    public static Iterator zip(Iterator $this, Iterator that) {
        return new AbstractIterator<Tuple2<A, B>>($this, that){
            private final /* synthetic */ Iterator $outer;
            private final Iterator that$3;

            public boolean hasNext() {
                return this.$outer.hasNext() && this.that$3.hasNext();
            }

            public Tuple2<A, B> next() {
                return new Tuple2<A, A>(this.$outer.next(), this.that$3.next());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$3 = that$3;
            }
        };
    }

    public static Iterator padTo(Iterator $this, int len, Object elem) {
        return new AbstractIterator<A1>($this, len, elem){
            private int count;
            private final /* synthetic */ Iterator $outer;
            private final int len$3;
            private final Object elem$4;

            private int count() {
                return this.count;
            }

            private void count_$eq(int x$1) {
                this.count = x$1;
            }

            public boolean hasNext() {
                return this.$outer.hasNext() || this.count() < this.len$3;
            }

            public A1 next() {
                this.count_$eq(this.count() + 1);
                return (A1)(this.$outer.hasNext() ? this.$outer.next() : (this.count() <= this.len$3 ? this.elem$4 : Iterator$.MODULE$.empty().next()));
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.len$3 = len$3;
                this.elem$4 = var3_3;
                this.count = 0;
            }
        };
    }

    public static Iterator zipWithIndex(Iterator $this) {
        return new AbstractIterator<Tuple2<A, Object>>($this){
            private int idx;
            private final /* synthetic */ Iterator $outer;

            private int idx() {
                return this.idx;
            }

            private void idx_$eq(int x$1) {
                this.idx = x$1;
            }

            public boolean hasNext() {
                return this.$outer.hasNext();
            }

            /*
             * WARNING - void declaration
             */
            public Tuple2<A, Object> next() {
                void var1_1;
                Tuple2<A, Integer> ret = new Tuple2<A, Integer>(this.$outer.next(), BoxesRunTime.boxToInteger(this.idx()));
                this.idx_$eq(this.idx() + 1);
                return var1_1;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.idx = 0;
            }
        };
    }

    public static Iterator zipAll(Iterator $this, Iterator that, Object thisElem, Object thatElem) {
        return new AbstractIterator<Tuple2<A1, B1>>($this, that, thisElem, thatElem){
            private final /* synthetic */ Iterator $outer;
            private final Iterator that$4;
            private final Object thisElem$1;
            private final Object thatElem$1;

            public boolean hasNext() {
                return this.$outer.hasNext() || this.that$4.hasNext();
            }

            public Tuple2<A1, B1> next() {
                return this.$outer.hasNext() ? (this.that$4.hasNext() ? new Tuple2<A, A>(this.$outer.next(), this.that$4.next()) : new Tuple2<A, Object>(this.$outer.next(), this.thatElem$1)) : (this.that$4.hasNext() ? new Tuple2<Object, A>(this.thisElem$1, this.that$4.next()) : (Tuple2)((Object)Iterator$.MODULE$.empty().next()));
            }
            {
                void var4_4;
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$4 = that$4;
                this.thisElem$1 = var3_3;
                this.thatElem$1 = var4_4;
            }
        };
    }

    public static void foreach(Iterator $this, Function1 f) {
        while ($this.hasNext()) {
            f.apply($this.next());
        }
    }

    /*
     * WARNING - void declaration
     */
    public static boolean forall(Iterator $this, Function1 p) {
        void var2_2;
        boolean res = true;
        while (res && $this.hasNext()) {
            res = BoxesRunTime.unboxToBoolean(p.apply($this.next()));
        }
        return (boolean)var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean exists(Iterator $this, Function1 p) {
        void var2_2;
        boolean res = false;
        while (!res && $this.hasNext()) {
            res = BoxesRunTime.unboxToBoolean(p.apply($this.next()));
        }
        return (boolean)var2_2;
    }

    public static boolean contains(Iterator $this, Object elem) {
        return $this.exists(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$5;

            public final boolean apply(A x$3) {
                Object object = this.elem$5;
                return x$3 == object ? true : (x$3 == null ? false : (x$3 instanceof Number ? BoxesRunTime.equalsNumObject((Number)x$3, object) : (x$3 instanceof Character ? BoxesRunTime.equalsCharObject((Character)x$3, object) : x$3.equals(object))));
            }
            {
                this.elem$5 = elem$5;
            }
        });
    }

    public static Option find(Iterator $this, Function1 p) {
        while ($this.hasNext()) {
            Object a = $this.next();
            if (!BoxesRunTime.unboxToBoolean(p.apply(a))) continue;
            return new Some(a);
        }
        return None$.MODULE$;
    }

    public static int indexWhere(Iterator $this, Function1 p) {
        int i = 0;
        while ($this.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(p.apply($this.next()))) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public static int indexOf(Iterator $this, Object elem) {
        int i = 0;
        while ($this.hasNext()) {
            Object a = $this.next();
            if (a == elem ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, elem) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, elem) : a.equals(elem))))) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public static BufferedIterator buffered(Iterator $this) {
        return new BufferedIterator<A>($this){
            private A hd;
            private boolean hdDefined;
            private final /* synthetic */ Iterator $outer;

            public BufferedIterator<A> buffered() {
                return BufferedIterator$class.buffered(this);
            }

            private A hd() {
                return this.hd;
            }

            private void hd_$eq(A x$1) {
                this.hd = x$1;
            }

            private boolean hdDefined() {
                return this.hdDefined;
            }

            private void hdDefined_$eq(boolean x$1) {
                this.hdDefined = x$1;
            }

            public A head() {
                if (!this.hdDefined()) {
                    this.hd_$eq(this.next());
                    this.hdDefined_$eq(true);
                }
                return this.hd();
            }

            public boolean hasNext() {
                return this.hdDefined() || this.$outer.hasNext();
            }

            public A next() {
                A a;
                if (this.hdDefined()) {
                    this.hdDefined_$eq(false);
                    a = this.hd();
                } else {
                    a = this.$outer.next();
                }
                return a;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                BufferedIterator$class.$init$(this);
                this.hdDefined = false;
            }
        };
    }

    public static Iterator.GroupedIterator grouped(Iterator $this, int size2) {
        return new Iterator.GroupedIterator($this, $this, size2, size2);
    }

    public static Iterator.GroupedIterator sliding(Iterator $this, int size2, int step) {
        return new Iterator.GroupedIterator($this, $this, size2, step);
    }

    public static int sliding$default$2(Iterator $this) {
        return 1;
    }

    public static int length(Iterator $this) {
        return $this.size();
    }

    public static Tuple2 duplicate(Iterator $this) {
        Queue gap = new Queue();
        ObjectRef<Object> ahead = ObjectRef.create(null);
        public class Scala_collection_Iterator$Partner$1
        extends AbstractIterator<A> {
            public final /* synthetic */ Iterator $outer;
            private final Queue gap$1;
            private final ObjectRef ahead$1;

            public boolean hasNext() {
                Iterator iterator2 = this.scala$collection$Iterator$Partner$$$outer();
                synchronized (iterator2) {
                    boolean bl = this != (Iterator)this.ahead$1.elem && !this.gap$1.isEmpty() || this.scala$collection$Iterator$Partner$$$outer().hasNext();
                    return bl;
                }
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Iterator iterator2 = this.scala$collection$Iterator$Partner$$$outer();
                synchronized (iterator2) {
                    A a;
                    if (this.gap$1.isEmpty()) {
                        this.ahead$1.elem = this;
                    }
                    if (this == (Iterator)this.ahead$1.elem) {
                        void var3_2;
                        A e = this.scala$collection$Iterator$Partner$$$outer().next();
                        this.gap$1.enqueue(Predef$.MODULE$.genericWrapArray(new Object[]{e}));
                        a = var3_2;
                    } else {
                        a = this.gap$1.dequeue();
                    }
                    A a2 = a;
                    return a2;
                }
            }

            private boolean compareGap(Queue<A> queue) {
                return this.gap$1 == queue;
            }

            public int hashCode() {
                return this.gap$1.hashCode();
            }

            public boolean equals(Object other) {
                Scala_collection_Iterator$Partner$1 var2_2;
                boolean bl = other instanceof Scala_collection_Iterator$Partner$1 ? (var2_2 = (Scala_collection_Iterator$Partner$1)other).compareGap(this.gap$1) && this.gap$1.isEmpty() : super.equals(other);
                return bl;
            }

            public /* synthetic */ Iterator scala$collection$Iterator$Partner$$$outer() {
                return this.$outer;
            }

            /*
             * WARNING - void declaration
             */
            public Scala_collection_Iterator$Partner$1(Iterator<A> gap$1) {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.gap$1 = gap$1;
                this.ahead$1 = var3_3;
            }
        }
        return new Tuple2<Scala_collection_Iterator$Partner$1, Scala_collection_Iterator$Partner$1>(new Scala_collection_Iterator$Partner$1($this, gap, ahead), new Scala_collection_Iterator$Partner$1($this, gap, ahead));
    }

    public static Iterator patch(Iterator $this, int from2, Iterator patchElems, int replaced) {
        return new AbstractIterator<B>($this, from2, patchElems, replaced){
            private Iterator<A> origElems;
            private int i;
            private final Iterator patchElems$1;
            private final int replaced$1;

            private Iterator<A> origElems() {
                return this.origElems;
            }

            private void origElems_$eq(Iterator<A> x$1) {
                this.origElems = x$1;
            }

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                if (this.i() == 0) {
                    this.origElems_$eq(this.origElems().drop(this.replaced$1));
                    this.i_$eq(-1);
                }
                return this.origElems().hasNext() || this.patchElems$1.hasNext();
            }

            public B next() {
                A a;
                if (this.i() == 0) {
                    this.origElems_$eq(this.origElems().drop(this.replaced$1));
                    this.i_$eq(-1);
                }
                if (this.i() < 0) {
                    a = this.patchElems$1.hasNext() ? this.patchElems$1.next() : this.origElems().next();
                } else if (this.origElems().hasNext()) {
                    this.i_$eq(this.i() - 1);
                    a = this.origElems().next();
                } else {
                    this.i_$eq(-1);
                    a = this.patchElems$1.next();
                }
                return (B)a;
            }
            {
                void var3_3;
                this.patchElems$1 = var3_3;
                this.replaced$1 = replaced$1;
                this.origElems = $outer;
                this.i = from$1 > 0 ? from$1 : 0;
            }
        };
    }

    public static void copyToArray(Iterator $this, Object xs, int start, int len) {
        Serializable serializable = new Serializable($this, xs, start){
            public static final long serialVersionUID = 0L;
            public final Object xs$2;
            public final int start$4;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"start ", " out of range ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.start$4), BoxesRunTime.boxToInteger(ScalaRunTime$.MODULE$.array_length(this.xs$2))}));
            }
            {
                this.xs$2 = xs$2;
                this.start$4 = start$4;
            }
        };
        boolean bl = start >= 0 && (start < ScalaRunTime$.MODULE$.array_length(xs) || ScalaRunTime$.MODULE$.array_length(xs) == 0);
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            int n = ScalaRunTime$.MODULE$.array_length(xs) - start;
            package$ package$2 = package$.MODULE$;
            int end = start + Math.min(len, n);
            for (int i = start; i < end && $this.hasNext(); ++i) {
                ScalaRunTime$.MODULE$.array_update(xs, i, $this.next());
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"start ", " out of range ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(serializable.start$4), BoxesRunTime.boxToInteger(ScalaRunTime$.MODULE$.array_length(serializable.xs$2))}))).toString());
    }

    public static boolean sameElements(Iterator $this, Iterator that) {
        while ($this.hasNext() && that.hasNext()) {
            Object a = that.next();
            Object a2 = $this.next();
            if (a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a))))) continue;
            return false;
        }
        return !$this.hasNext() && !that.hasNext();
    }

    public static Traversable toTraversable(Iterator $this) {
        return $this.toStream();
    }

    public static Iterator toIterator(Iterator $this) {
        return $this;
    }

    public static Stream toStream(Iterator $this) {
        Stream stream;
        if ($this.hasNext()) {
            Serializable serializable = new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Iterator $outer;

                public final Stream<A> apply() {
                    return this.$outer.toStream();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            Object a = $this.next();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Stream.Cons(a, serializable);
        } else {
            Stream$ stream$ = Stream$.MODULE$;
            stream = Stream$Empty$.MODULE$;
        }
        return stream;
    }

    public static String toString(Iterator $this) {
        return new StringBuilder().append((Object)($this.hasNext() ? "non-empty" : "empty")).append((Object)" iterator").toString();
    }

    public static void $init$(Iterator $this) {
    }
}

