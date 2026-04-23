/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeq$;
import scala.collection.TraversableOnce;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.GenericTraversableTemplate$;
import scala.collection.mutable.Builder;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;

public abstract class GenericTraversableTemplate$class {
    public static Builder newBuilder(GenericTraversableTemplate $this) {
        return $this.companion().newBuilder();
    }

    public static Builder genericBuilder(GenericTraversableTemplate $this) {
        return $this.companion().newBuilder();
    }

    private static TraversableOnce sequential(GenericTraversableTemplate $this) {
        return ((GenTraversableOnce)((Object)$this)).seq();
    }

    public static Tuple2 unzip(GenericTraversableTemplate $this, Function1 asPair) {
        Builder b1 = $this.genericBuilder();
        Builder b2 = $this.genericBuilder();
        GenericTraversableTemplate$class.sequential($this).foreach(new Serializable($this, b1, b2, asPair){
            public static final long serialVersionUID = 0L;
            private final Builder b1$1;
            private final Builder b2$1;
            private final Function1 asPair$1;

            public final Builder<A2, CC> apply(A xy) {
                Tuple2 tuple2 = (Tuple2)this.asPair$1.apply(xy);
                if (tuple2 != null) {
                    Tuple2<T1, T2> tuple22 = new Tuple2<T1, T2>(tuple2._1(), tuple2._2());
                    T1 x = tuple22._1();
                    T2 y = tuple22._2();
                    this.b1$1.$plus$eq(x);
                    return this.b2$1.$plus$eq(y);
                }
                throw new MatchError(tuple2);
            }
            {
                void var4_4;
                void var3_3;
                this.b1$1 = b1$1;
                this.b2$1 = var3_3;
                this.asPair$1 = var4_4;
            }
        });
        return new Tuple2(b1.result(), b2.result());
    }

    public static Tuple3 unzip3(GenericTraversableTemplate $this, Function1 asTriple) {
        Builder b1 = $this.genericBuilder();
        Builder b2 = $this.genericBuilder();
        Builder b3 = $this.genericBuilder();
        GenericTraversableTemplate$class.sequential($this).foreach(new Serializable($this, b1, b2, b3, asTriple){
            public static final long serialVersionUID = 0L;
            private final Builder b1$2;
            private final Builder b2$2;
            private final Builder b3$1;
            private final Function1 asTriple$1;

            public final Builder<A3, CC> apply(A xyz) {
                Tuple3 tuple3 = (Tuple3)this.asTriple$1.apply(xyz);
                if (tuple3 != null) {
                    Tuple3<T1, T2, T3> tuple32 = new Tuple3<T1, T2, T3>(tuple3._1(), tuple3._2(), tuple3._3());
                    T1 x = tuple32._1();
                    T2 y = tuple32._2();
                    T3 z = tuple32._3();
                    this.b1$2.$plus$eq(x);
                    this.b2$2.$plus$eq(y);
                    return this.b3$1.$plus$eq(z);
                }
                throw new MatchError(tuple3);
            }
            {
                void var5_5;
                void var4_4;
                void var3_3;
                this.b1$2 = b1$2;
                this.b2$2 = var3_3;
                this.b3$1 = var4_4;
                this.asTriple$1 = var5_5;
            }
        });
        return new Tuple3(b1.result(), b2.result(), b3.result());
    }

    public static GenTraversable flatten(GenericTraversableTemplate $this, Function1 asTraversable) {
        Builder b = $this.genericBuilder();
        GenericTraversableTemplate$class.sequential($this).foreach(new Serializable($this, b, asTraversable){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;
            private final Function1 asTraversable$1;

            public final Builder<B, CC> apply(A xs) {
                return (Builder)this.b$1.$plus$plus$eq(((GenTraversableOnce)this.asTraversable$1.apply(xs)).seq());
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.asTraversable$1 = var3_3;
            }
        });
        return (GenTraversable)b.result();
    }

    public static GenTraversable transpose(GenericTraversableTemplate $this, Function1 asTraversable) {
        if ($this.isEmpty()) {
            return (GenTraversable)$this.genericBuilder().result();
        }
        int headSize = ((GenTraversableOnce)asTraversable.apply($this.head())).size();
        IndexedSeq bs = (IndexedSeq)IndexedSeq$.MODULE$.fill(headSize, new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenericTraversableTemplate $outer;

            public final Builder<B, CC> apply() {
                return this.$outer.genericBuilder();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        GenericTraversableTemplate$class.sequential($this).foreach(new Serializable($this, headSize, bs, asTraversable){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ GenericTraversableTemplate $outer;
            public final int headSize$1;
            public final IndexedSeq bs$1;
            private final Function1 asTraversable$2;

            public final void apply(A xs) {
                IntRef i = IntRef.create(0);
                ((GenTraversableOnce)this.asTraversable$2.apply(xs)).seq().foreach(new Serializable(this, i){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ GenericTraversableTemplate$.anonfun.transpose.1 $outer;
                    private final IntRef i$1;

                    public final void apply(B x) {
                        if (this.i$1.elem >= this.$outer.headSize$1) {
                            throw GenericTraversableTemplate$class.fail$1(this.$outer.$outer);
                        }
                        ((Builder)this.$outer.bs$1.apply(this.i$1.elem)).$plus$eq(x);
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
                if (i.elem != this.headSize$1) {
                    throw GenericTraversableTemplate$class.fail$1(this.$outer);
                }
            }

            public /* synthetic */ GenericTraversableTemplate scala$collection$generic$GenericTraversableTemplate$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                void var4_4;
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.headSize$1 = headSize$1;
                this.bs$1 = var3_3;
                this.asTraversable$2 = var4_4;
            }
        });
        Builder bb = $this.genericBuilder();
        bs.foreach(new Serializable($this, bb){
            public static final long serialVersionUID = 0L;
            private final Builder bb$1;

            public final Builder<CC, CC> apply(Builder<B, CC> b) {
                return this.bb$1.$plus$eq(b.result());
            }
            {
                this.bb$1 = bb$1;
            }
        });
        return (GenTraversable)bb.result();
    }

    public static final Nothing$ fail$1(GenericTraversableTemplate $this) {
        throw new IllegalArgumentException("transpose requires all collections have the same size");
    }

    public static void $init$(GenericTraversableTemplate $this) {
    }
}

