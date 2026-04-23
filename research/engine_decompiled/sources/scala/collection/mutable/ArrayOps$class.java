/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqLike;
import scala.collection.TraversableOnce;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.ArrayOps$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.IndexedSeq;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ParArray$;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class ArrayOps$class {
    private static Class elementClass(ArrayOps $this) {
        return ScalaRunTime$.MODULE$.arrayElementClass($this.repr().getClass());
    }

    public static void copyToArray(ArrayOps $this, Object xs, int start, int len) {
        int l = package$.MODULE$.min(len, ScalaRunTime$.MODULE$.array_length($this.repr()));
        if (ScalaRunTime$.MODULE$.array_length(xs) - start < l) {
            int n = ScalaRunTime$.MODULE$.array_length(xs) - start;
            Predef$ predef$ = Predef$.MODULE$;
            l = RichInt$.MODULE$.max$extension(n, 0);
        }
        Array$.MODULE$.copy($this.repr(), 0, xs, start, l);
    }

    public static Object toArray(ArrayOps $this, ClassTag evidence$1) {
        Predef$ predef$ = Predef$.MODULE$;
        Class<?> thatElementClass = ScalaRunTime$.MODULE$.arrayElementClass(evidence$1);
        return ArrayOps$class.elementClass($this) == thatElementClass ? $this.repr() : $this.scala$collection$mutable$ArrayOps$$super$toArray(evidence$1);
    }

    /*
     * WARNING - void declaration
     */
    public static Object $colon$plus(ArrayOps $this, Object elem, ClassTag evidence$2) {
        void var3_3;
        Object result2 = Array$.MODULE$.ofDim(ScalaRunTime$.MODULE$.array_length($this.repr()) + 1, evidence$2);
        Array$.MODULE$.copy($this.repr(), 0, result2, 0, ScalaRunTime$.MODULE$.array_length($this.repr()));
        ScalaRunTime$.MODULE$.array_update(result2, ScalaRunTime$.MODULE$.array_length($this.repr()), elem);
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public static Object $plus$colon(ArrayOps $this, Object elem, ClassTag evidence$3) {
        void var3_3;
        Object result2 = Array$.MODULE$.ofDim(ScalaRunTime$.MODULE$.array_length($this.repr()) + 1, evidence$3);
        ScalaRunTime$.MODULE$.array_update(result2, 0, elem);
        Array$.MODULE$.copy($this.repr(), 0, result2, 1, ScalaRunTime$.MODULE$.array_length($this.repr()));
        return var3_3;
    }

    public static ParArray par(ArrayOps $this) {
        return ParArray$.MODULE$.handoff($this.repr());
    }

    public static Object flatten(ArrayOps $this, Function1 asTrav, ClassTag m) {
        ArrayBuilder b = Array$.MODULE$.newBuilder(m);
        b.sizeHint(BoxesRunTime.unboxToInt(Predef$.MODULE$.intArrayOps((int[])$this.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(T x0$1) {
                int n = x0$1 instanceof scala.collection.IndexedSeq ? ((SeqLike)x0$1).size() : 0;
                return n;
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()))).sum(Numeric$IntIsIntegral$.MODULE$)));
        $this.foreach(new Serializable($this, b, asTrav){
            public static final long serialVersionUID = 0L;
            private final ArrayBuilder b$1;
            private final Function1 asTrav$1;

            public final ArrayBuilder<U> apply(T xs) {
                return (ArrayBuilder)this.b$1.$plus$plus$eq((TraversableOnce)this.asTrav$1.apply(xs));
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.asTrav$1 = var3_3;
            }
        });
        return b.result();
    }

    public static Object[] transpose(ArrayOps $this, Function1 asArray) {
        Object[] objectArray;
        ArrayBuilder bb = Array$.MODULE$.newBuilder(ClassTag$.MODULE$.apply(ArrayOps$class.elementClass($this)));
        if ($this.isEmpty()) {
            objectArray = (Object[])bb.result();
        } else {
            ArrayBuilder[] bs = (ArrayBuilder[])Predef$.MODULE$.genericArrayOps(asArray.apply($this.head())).map(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ArrayOps $outer;

                public final ArrayBuilder<U> apply(U x$1) {
                    return ArrayOps$class.mkRowBuilder$1(this.$outer);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(ArrayBuilder.class)));
            $this.foreach(new Serializable($this, bs, asArray){
                public static final long serialVersionUID = 0L;
                public final ArrayBuilder[] bs$1;
                private final Function1 asArray$1;

                public final void apply(T xs) {
                    IntRef i = IntRef.create(0);
                    Predef$.MODULE$.genericArrayOps(this.asArray$1.apply(xs)).foreach(new Serializable(this, i){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ArrayOps$.anonfun.transpose.1 $outer;
                        private final IntRef i$1;

                        public final void apply(U x) {
                            this.$outer.bs$1[this.i$1.elem].$plus$eq(x);
                            ++this.i$1.elem;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.i$1 = i$1;
                        }
                    });
                }
                {
                    void var3_3;
                    this.bs$1 = bs$1;
                    this.asArray$1 = var3_3;
                }
            });
            Predef$.MODULE$.refArrayOps((Object[])bs).foreach(new Serializable($this, bb){
                public static final long serialVersionUID = 0L;
                private final Builder bb$1;

                public final Builder<Object, Object[]> apply(ArrayBuilder<U> b) {
                    return this.bb$1.$plus$eq(b.result());
                }
                {
                    this.bb$1 = bb$1;
                }
            });
            objectArray = (Object[])bb.result();
        }
        return objectArray;
    }

    public static Tuple2 unzip(ArrayOps $this, Function1 asPair, ClassTag ct1, ClassTag ct2) {
        Object a1 = ct1.newArray($this.length());
        Object a2 = ct2.newArray($this.length());
        for (int i = 0; i < $this.length(); ++i) {
            Object e = $this.apply(i);
            ScalaRunTime$.MODULE$.array_update(a1, i, ((Tuple2)asPair.apply(e))._1());
            ScalaRunTime$.MODULE$.array_update(a2, i, ((Tuple2)asPair.apply(e))._2());
        }
        return new Tuple2<Object, Object>(a1, a2);
    }

    public static Tuple3 unzip3(ArrayOps $this, Function1 asTriple, ClassTag ct1, ClassTag ct2, ClassTag ct3) {
        Object a1 = ct1.newArray($this.length());
        Object a2 = ct2.newArray($this.length());
        Object a3 = ct3.newArray($this.length());
        for (int i = 0; i < $this.length(); ++i) {
            Object e = $this.apply(i);
            ScalaRunTime$.MODULE$.array_update(a1, i, ((Tuple3)asTriple.apply(e))._1());
            ScalaRunTime$.MODULE$.array_update(a2, i, ((Tuple3)asTriple.apply(e))._2());
            ScalaRunTime$.MODULE$.array_update(a3, i, ((Tuple3)asTriple.apply(e))._3());
        }
        return new Tuple3<Object, Object, Object>(a1, a2, a3);
    }

    public static IndexedSeq seq(ArrayOps $this) {
        return $this.thisCollection();
    }

    public static final ArrayBuilder mkRowBuilder$1(ArrayOps $this) {
        return Array$.MODULE$.newBuilder(ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayElementClass(ArrayOps$class.elementClass($this))));
    }

    public static void $init$(ArrayOps $this) {
    }
}

