/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.AbstractFunction1;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime$;

public abstract class TraversableOnce$class {
    public static List reversed(TraversableOnce $this) {
        ObjectRef<Nil$> elems = ObjectRef.create(Nil$.MODULE$);
        $this.foreach(new Serializable($this, elems){
            public static final long serialVersionUID = 0L;
            private final ObjectRef elems$1;

            public final void apply(A x$1) {
                this.elems$1.elem = ((List)this.elems$1.elem).$colon$colon(x$1);
            }
            {
                this.elems$1 = elems$1;
            }
        });
        return (List)elems.elem;
    }

    public static int size(TraversableOnce $this) {
        IntRef result2 = IntRef.create(0);
        $this.foreach(new Serializable($this, result2){
            public static final long serialVersionUID = 0L;
            private final IntRef result$1;

            public final void apply(A x) {
                ++this.result$1.elem;
            }
            {
                this.result$1 = result$1;
            }
        });
        return result2.elem;
    }

    public static boolean nonEmpty(TraversableOnce $this) {
        return !$this.isEmpty();
    }

    public static int count(TraversableOnce $this, Function1 p) {
        IntRef cnt = IntRef.create(0);
        $this.foreach(new Serializable($this, cnt, p){
            public static final long serialVersionUID = 0L;
            private final IntRef cnt$1;
            private final Function1 p$1;

            public final void apply(A x) {
                if (BoxesRunTime.unboxToBoolean(this.p$1.apply(x))) {
                    ++this.cnt$1.elem;
                }
            }
            {
                void var3_3;
                this.cnt$1 = cnt$1;
                this.p$1 = var3_3;
            }
        });
        return cnt.elem;
    }

    public static Option collectFirst(TraversableOnce $this, PartialFunction pf) {
        NonLocalReturnControl nonLocalReturnControl;
        block7: {
            Option option;
            Object object = new Object();
            try {
                Iterator iterator2;
                if ($this instanceof Iterator) {
                    Iterator iterator3;
                    iterator2 = iterator3 = (Iterator)$this;
                } else {
                    if (!($this instanceof GenIterable)) {
                        $this.foreach(pf.runWith(new Serializable($this, object){
                            public static final long serialVersionUID = 0L;
                            private final Object nonLocalReturnKey1$1;

                            public final Nothing$ apply(B b) {
                                throw new NonLocalReturnControl<Some<B>>(this.nonLocalReturnKey1$1, new Some<B>(b));
                            }
                            {
                                this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                            }
                        }));
                        return None$.MODULE$;
                    }
                    iterator2 = $this.toIterator();
                }
                AbstractFunction1 sentinel = new AbstractFunction1<A, Object>($this){

                    public TraversableOnce$.anon.2 apply(A a) {
                        return this;
                    }
                };
                while (iterator2.hasNext()) {
                    Object x = pf.applyOrElse(iterator2.next(), sentinel);
                    if (x == sentinel) continue;
                    return new Some(x);
                }
                option = None$.MODULE$;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block7;
                option = (Option)nonLocalReturnControl.value();
            }
            return option;
        }
        throw nonLocalReturnControl;
    }

    public static Object $div$colon(TraversableOnce $this, Object z, Function2 op) {
        return $this.foldLeft(z, op);
    }

    public static Object $colon$bslash(TraversableOnce $this, Object z, Function2 op) {
        return $this.foldRight(z, op);
    }

    public static Object foldLeft(TraversableOnce $this, Object z, Function2 op) {
        ObjectRef<Object> result2 = ObjectRef.create(z);
        $this.foreach(new Serializable($this, result2, op){
            public static final long serialVersionUID = 0L;
            private final ObjectRef result$2;
            private final Function2 op$1;

            public final void apply(A x) {
                this.result$2.elem = this.op$1.apply(this.result$2.elem, x);
            }
            {
                void var3_3;
                this.result$2 = result$2;
                this.op$1 = var3_3;
            }
        });
        return result2.elem;
    }

    public static Object foldRight(TraversableOnce $this, Object z, Function2 op) {
        return $this.reversed().foldLeft(z, new Serializable($this, op){
            public static final long serialVersionUID = 0L;
            private final Function2 op$2;

            public final B apply(B x, A y) {
                return (B)this.op$2.apply(y, x);
            }
            {
                this.op$2 = op$2;
            }
        });
    }

    public static Object reduceLeft(TraversableOnce $this, Function2 op) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.reduceLeft");
        }
        BooleanRef first = BooleanRef.create(true);
        ObjectRef<Integer> acc = ObjectRef.create(BoxesRunTime.boxToInteger(0));
        $this.foreach(new Serializable($this, first, acc, op){
            public static final long serialVersionUID = 0L;
            private final BooleanRef first$1;
            private final ObjectRef acc$1;
            private final Function2 op$3;

            public final void apply(A x) {
                if (this.first$1.elem) {
                    this.acc$1.elem = x;
                    this.first$1.elem = false;
                } else {
                    this.acc$1.elem = this.op$3.apply(this.acc$1.elem, x);
                }
            }
            {
                void var4_4;
                void var3_3;
                this.first$1 = first$1;
                this.acc$1 = var3_3;
                this.op$3 = var4_4;
            }
        });
        return acc.elem;
    }

    public static Object reduceRight(TraversableOnce $this, Function2 op) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.reduceRight");
        }
        return $this.reversed().reduceLeft(new Serializable($this, op){
            public static final long serialVersionUID = 0L;
            private final Function2 op$4;

            public final B apply(B x, A y) {
                return (B)this.op$4.apply(y, x);
            }
            {
                this.op$4 = op$4;
            }
        });
    }

    public static Option reduceLeftOption(TraversableOnce $this, Function2 op) {
        return $this.isEmpty() ? None$.MODULE$ : new Some($this.reduceLeft(op));
    }

    public static Option reduceRightOption(TraversableOnce $this, Function2 op) {
        return $this.isEmpty() ? None$.MODULE$ : new Some($this.reduceRight(op));
    }

    public static Object reduce(TraversableOnce $this, Function2 op) {
        return $this.reduceLeft(op);
    }

    public static Option reduceOption(TraversableOnce $this, Function2 op) {
        return $this.reduceLeftOption(op);
    }

    public static Object fold(TraversableOnce $this, Object z, Function2 op) {
        return $this.foldLeft(z, op);
    }

    public static Object aggregate(TraversableOnce $this, Function0 z, Function2 seqop, Function2 combop) {
        return $this.foldLeft(z.apply(), seqop);
    }

    public static Object sum(TraversableOnce $this, Numeric num) {
        return $this.foldLeft(num.zero(), new Serializable($this, num){
            public static final long serialVersionUID = 0L;
            private final Numeric num$1;

            public final B apply(B x, B y) {
                return this.num$1.plus(x, y);
            }
            {
                this.num$1 = num$1;
            }
        });
    }

    public static Object product(TraversableOnce $this, Numeric num) {
        return $this.foldLeft(num.one(), new Serializable($this, num){
            public static final long serialVersionUID = 0L;
            private final Numeric num$2;

            public final B apply(B x, B y) {
                return this.num$2.times(x, y);
            }
            {
                this.num$2 = num$2;
            }
        });
    }

    public static Object min(TraversableOnce $this, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.min");
        }
        return $this.reduceLeft(new Serializable($this, cmp){
            public static final long serialVersionUID = 0L;
            private final Ordering cmp$1;

            public final A apply(A x, A y) {
                return this.cmp$1.lteq(x, y) ? x : y;
            }
            {
                this.cmp$1 = cmp$1;
            }
        });
    }

    public static Object max(TraversableOnce $this, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.max");
        }
        return $this.reduceLeft(new Serializable($this, cmp){
            public static final long serialVersionUID = 0L;
            private final Ordering cmp$2;

            public final A apply(A x, A y) {
                return this.cmp$2.gteq(x, y) ? x : y;
            }
            {
                this.cmp$2 = cmp$2;
            }
        });
    }

    public static Object maxBy(TraversableOnce $this, Function1 f, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.maxBy");
        }
        ObjectRef<Object> maxF = ObjectRef.create(null);
        ObjectRef<Object> maxElem = ObjectRef.create(null);
        BooleanRef first = BooleanRef.create(true);
        $this.foreach(new Serializable($this, maxF, maxElem, first, f, cmp){
            public static final long serialVersionUID = 0L;
            private final ObjectRef maxF$1;
            private final ObjectRef maxElem$1;
            private final BooleanRef first$2;
            private final Function1 f$1;
            private final Ordering cmp$3;

            public final void apply(A elem) {
                R fx = this.f$1.apply(elem);
                if (this.first$2.elem || this.cmp$3.gt(fx, this.maxF$1.elem)) {
                    this.maxElem$1.elem = elem;
                    this.maxF$1.elem = fx;
                    this.first$2.elem = false;
                }
            }
            {
                void var6_6;
                void var5_5;
                void var4_4;
                void var3_3;
                this.maxF$1 = maxF$1;
                this.maxElem$1 = var3_3;
                this.first$2 = var4_4;
                this.f$1 = var5_5;
                this.cmp$3 = var6_6;
            }
        });
        return maxElem.elem;
    }

    public static Object minBy(TraversableOnce $this, Function1 f, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.minBy");
        }
        ObjectRef<Object> minF = ObjectRef.create(null);
        ObjectRef<Object> minElem = ObjectRef.create(null);
        BooleanRef first = BooleanRef.create(true);
        $this.foreach(new Serializable($this, minF, minElem, first, f, cmp){
            public static final long serialVersionUID = 0L;
            private final ObjectRef minF$1;
            private final ObjectRef minElem$1;
            private final BooleanRef first$3;
            private final Function1 f$2;
            private final Ordering cmp$4;

            public final void apply(A elem) {
                R fx = this.f$2.apply(elem);
                if (this.first$3.elem || this.cmp$4.lt(fx, this.minF$1.elem)) {
                    this.minElem$1.elem = elem;
                    this.minF$1.elem = fx;
                    this.first$3.elem = false;
                }
            }
            {
                void var6_6;
                void var5_5;
                void var4_4;
                void var3_3;
                this.minF$1 = minF$1;
                this.minElem$1 = var3_3;
                this.first$3 = var4_4;
                this.f$2 = var5_5;
                this.cmp$4 = var6_6;
            }
        });
        return minElem.elem;
    }

    public static void copyToBuffer(TraversableOnce $this, Buffer dest) {
        dest.$plus$plus$eq($this.seq());
    }

    public static void copyToArray(TraversableOnce $this, Object xs, int start) {
        $this.copyToArray(xs, start, ScalaRunTime$.MODULE$.array_length(xs) - start);
    }

    public static void copyToArray(TraversableOnce $this, Object xs) {
        $this.copyToArray(xs, 0, ScalaRunTime$.MODULE$.array_length(xs));
    }

    /*
     * WARNING - void declaration
     */
    public static Object toArray(TraversableOnce $this, ClassTag evidence$1) {
        Object object;
        if ($this.isTraversableAgain()) {
            void var2_2;
            Object result2 = evidence$1.newArray($this.size());
            $this.copyToArray(result2, 0);
            object = var2_2;
        } else {
            object = $this.toBuffer().toArray(evidence$1);
        }
        return object;
    }

    public static List toList(TraversableOnce $this) {
        return $this.to(List$.MODULE$.canBuildFrom());
    }

    public static Iterable toIterable(TraversableOnce $this) {
        return $this.toStream();
    }

    public static Seq toSeq(TraversableOnce $this) {
        return $this.toStream();
    }

    public static IndexedSeq toIndexedSeq(TraversableOnce $this) {
        return $this.to(Predef$.MODULE$.fallbackStringCanBuildFrom());
    }

    public static Buffer toBuffer(TraversableOnce $this) {
        return $this.to(ArrayBuffer$.MODULE$.canBuildFrom());
    }

    public static Set toSet(TraversableOnce $this) {
        return $this.to(Set$.MODULE$.canBuildFrom());
    }

    public static Vector toVector(TraversableOnce $this) {
        return $this.to(Vector$.MODULE$.canBuildFrom());
    }

    public static Object to(TraversableOnce $this, CanBuildFrom cbf) {
        Builder b = cbf.apply();
        b.$plus$plus$eq($this.seq());
        return b.result();
    }

    public static Map toMap(TraversableOnce $this, Predef$.less.colon.less ev) {
        Builder b = Map$.MODULE$.newBuilder();
        $this.foreach(new Serializable($this, b, ev){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;
            private final Predef$.less.colon.less ev$1;

            public final Builder<Tuple2<T, U>, Map<T, U>> apply(A x) {
                return this.b$1.$plus$eq(this.ev$1.apply(x));
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.ev$1 = var3_3;
            }
        });
        return (Map)b.result();
    }

    public static String mkString(TraversableOnce $this, String start, String sep, String end) {
        return $this.addString(new StringBuilder(), start, sep, end).toString();
    }

    public static String mkString(TraversableOnce $this, String sep) {
        return $this.mkString("", sep, "");
    }

    public static String mkString(TraversableOnce $this) {
        return $this.mkString("");
    }

    public static StringBuilder addString(TraversableOnce $this, StringBuilder b, String start, String sep, String end) {
        BooleanRef first = BooleanRef.create(true);
        b.append(start);
        $this.foreach(new Serializable($this, first, b, sep){
            public static final long serialVersionUID = 0L;
            private final BooleanRef first$4;
            private final StringBuilder b$2;
            private final String sep$1;

            public final Object apply(A x) {
                java.io.Serializable serializable;
                if (this.first$4.elem) {
                    this.b$2.append(x);
                    this.first$4.elem = false;
                    serializable = BoxedUnit.UNIT;
                } else {
                    this.b$2.append(this.sep$1);
                    serializable = this.b$2.append(x);
                }
                return serializable;
            }
            {
                void var4_4;
                void var3_3;
                this.first$4 = first$4;
                this.b$2 = var3_3;
                this.sep$1 = var4_4;
            }
        });
        b.append(end);
        return b;
    }

    public static StringBuilder addString(TraversableOnce $this, StringBuilder b, String sep) {
        return $this.addString(b, "", sep, "");
    }

    public static StringBuilder addString(TraversableOnce $this, StringBuilder b) {
        return $this.addString(b, "");
    }

    public static void $init$(TraversableOnce $this) {
    }
}

