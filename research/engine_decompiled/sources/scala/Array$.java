/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Array$;
import scala.FallbackArrayBuilding;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.IndexedSeq;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.compat.Platform$;
import scala.math.Numeric$IntIsIntegral$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime$;

public final class Array$
extends FallbackArrayBuilding
implements Serializable {
    public static final Array$ MODULE$;
    private final boolean[] emptyBooleanArray;
    private final byte[] emptyByteArray;
    private final char[] emptyCharArray;
    private final double[] emptyDoubleArray;
    private final float[] emptyFloatArray;
    private final int[] emptyIntArray;
    private final long[] emptyLongArray;
    private final short[] emptyShortArray;
    private final Object[] emptyObjectArray;

    static {
        new Array$();
    }

    public boolean[] emptyBooleanArray() {
        return this.emptyBooleanArray;
    }

    public byte[] emptyByteArray() {
        return this.emptyByteArray;
    }

    public char[] emptyCharArray() {
        return this.emptyCharArray;
    }

    public double[] emptyDoubleArray() {
        return this.emptyDoubleArray;
    }

    public float[] emptyFloatArray() {
        return this.emptyFloatArray;
    }

    public int[] emptyIntArray() {
        return this.emptyIntArray;
    }

    public long[] emptyLongArray() {
        return this.emptyLongArray;
    }

    public short[] emptyShortArray() {
        return this.emptyShortArray;
    }

    public Object[] emptyObjectArray() {
        return this.emptyObjectArray;
    }

    public <T> CanBuildFrom<Object, T, Object> canBuildFrom(ClassTag<T> t) {
        return new CanBuildFrom<Object, T, Object>(t){
            private final ClassTag t$1;

            public ArrayBuilder<T> apply(Object from2) {
                return ArrayBuilder$.MODULE$.make(this.t$1);
            }

            public ArrayBuilder<T> apply() {
                return ArrayBuilder$.MODULE$.make(this.t$1);
            }
            {
                this.t$1 = t$1;
            }
        };
    }

    public <T> ArrayBuilder<T> newBuilder(ClassTag<T> t) {
        return ArrayBuilder$.MODULE$.make(t);
    }

    private void slowcopy(Object src, int srcPos, Object dest, int destPos, int length) {
        int i = srcPos;
        int j = destPos;
        int srcUntil = srcPos + length;
        while (i < srcUntil) {
            ScalaRunTime$.MODULE$.array_update(dest, j, ScalaRunTime$.MODULE$.array_apply(src, i));
            ++i;
            ++j;
        }
    }

    public void copy(Object src, int srcPos, Object dest, int destPos, int length) {
        Class<?> srcClass = src.getClass();
        if (srcClass.isArray() && dest.getClass().isAssignableFrom(srcClass)) {
            Platform$ platform$ = Platform$.MODULE$;
            System.arraycopy(src, srcPos, dest, destPos, length);
        } else {
            this.slowcopy(src, srcPos, dest, destPos, length);
        }
    }

    public <T> Object empty(ClassTag<T> evidence$1) {
        return evidence$1.newArray(0);
    }

    /*
     * WARNING - void declaration
     */
    public <T> Object apply(Seq<T> xs, ClassTag<T> evidence$2) {
        void var3_3;
        Object array = evidence$2.newArray(xs.length());
        IntRef i = IntRef.create(0);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final Object array$1;
            private final IntRef i$1;

            public final void apply(T x) {
                ScalaRunTime$.MODULE$.array_update(this.array$1, this.i$1.elem, x);
                ++this.i$1.elem;
            }
            {
                this.array$1 = array$1;
                this.i$1 = i$1;
            }
        });
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public boolean[] apply(boolean x, Seq<Object> xs) {
        void var3_3;
        boolean[] array = new boolean[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final boolean[] array$2;
            private final IntRef i$2;

            public final void apply(boolean x) {
                this.array$2[this.i$2.elem] = x;
                ++this.i$2.elem;
            }
            {
                this.array$2 = array$2;
                this.i$2 = i$2;
            }
        });
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public byte[] apply(byte x, Seq<Object> xs) {
        void var3_3;
        byte[] array = new byte[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final byte[] array$3;
            private final IntRef i$3;

            public final void apply(byte x) {
                this.array$3[this.i$3.elem] = x;
                ++this.i$3.elem;
            }
            {
                this.array$3 = array$3;
                this.i$3 = i$3;
            }
        });
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public short[] apply(short x, Seq<Object> xs) {
        void var3_3;
        short[] array = new short[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final short[] array$4;
            private final IntRef i$4;

            public final void apply(short x) {
                this.array$4[this.i$4.elem] = x;
                ++this.i$4.elem;
            }
            {
                this.array$4 = array$4;
                this.i$4 = i$4;
            }
        });
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public char[] apply(char x, Seq<Object> xs) {
        void var3_3;
        char[] array = new char[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final char[] array$5;
            private final IntRef i$5;

            public final void apply(char x) {
                this.array$5[this.i$5.elem] = x;
                ++this.i$5.elem;
            }
            {
                this.array$5 = array$5;
                this.i$5 = i$5;
            }
        });
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public int[] apply(int x, Seq<Object> xs) {
        void var3_3;
        int[] array = new int[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final int[] array$6;
            private final IntRef i$6;

            public final void apply(int x) {
                this.apply$mcVI$sp(x);
            }

            public void apply$mcVI$sp(int x) {
                this.array$6[this.i$6.elem] = x;
                ++this.i$6.elem;
            }
            {
                this.array$6 = array$6;
                this.i$6 = i$6;
            }
        });
        return var3_3;
    }

    public long[] apply(long x, Seq<Object> xs) {
        long[] array = new long[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final long[] array$7;
            private final IntRef i$7;

            public final void apply(long x) {
                this.apply$mcVJ$sp(x);
            }

            public void apply$mcVJ$sp(long x) {
                this.array$7[this.i$7.elem] = x;
                ++this.i$7.elem;
            }
            {
                this.array$7 = array$7;
                this.i$7 = i$7;
            }
        });
        return array;
    }

    /*
     * WARNING - void declaration
     */
    public float[] apply(float x, Seq<Object> xs) {
        void var3_3;
        float[] array = new float[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final float[] array$8;
            private final IntRef i$8;

            public final void apply(float x) {
                this.apply$mcVF$sp(x);
            }

            public void apply$mcVF$sp(float x) {
                this.array$8[this.i$8.elem] = x;
                ++this.i$8.elem;
            }
            {
                this.array$8 = array$8;
                this.i$8 = i$8;
            }
        });
        return var3_3;
    }

    public double[] apply(double x, Seq<Object> xs) {
        double[] array = new double[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final double[] array$9;
            private final IntRef i$9;

            public final void apply(double x) {
                this.apply$mcVD$sp(x);
            }

            public void apply$mcVD$sp(double x) {
                this.array$9[this.i$9.elem] = x;
                ++this.i$9.elem;
            }
            {
                this.array$9 = array$9;
                this.i$9 = i$9;
            }
        });
        return array;
    }

    /*
     * WARNING - void declaration
     */
    public BoxedUnit[] apply(BoxedUnit x, Seq<BoxedUnit> xs) {
        void var3_3;
        BoxedUnit[] array = new BoxedUnit[xs.length() + 1];
        array[0] = x;
        IntRef i = IntRef.create(1);
        xs.iterator().foreach(new Serializable(array, i){
            public static final long serialVersionUID = 0L;
            private final BoxedUnit[] array$10;
            private final IntRef i$10;

            public final void apply(BoxedUnit x) {
                this.array$10[this.i$10.elem] = x;
                ++this.i$10.elem;
            }
            {
                this.array$10 = array$10;
                this.i$10 = i$10;
            }
        });
        return var3_3;
    }

    public <T> Object ofDim(int n1, ClassTag<T> evidence$3) {
        return evidence$3.newArray(n1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public <T> Object[] ofDim(int n1, int n2, ClassTag<T> evidence$4) {
        Object[] arr = (Object[])ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$4.runtimeClass())).newArray(n1);
        Predef$ predef$ = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n1, 1);
        if (range2.isEmpty()) return arr;
        int i1 = range2.start();
        while (true) {
            arr[i1] = evidence$4.newArray(n2);
            if (i1 == range2.lastElement()) {
                return arr;
            }
            i1 += range2.step();
        }
    }

    public <T> Object[][] ofDim(int n1, int n2, int n3, ClassTag<T> evidence$5) {
        return (Object[][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, evidence$5){
            public static final long serialVersionUID = 0L;
            private final int n2$2;
            private final int n3$1;
            private final ClassTag evidence$5$1;

            public final Object[] apply(int x$1) {
                return Array$.MODULE$.ofDim(this.n2$2, this.n3$1, this.evidence$5$1);
            }
            {
                this.n2$2 = n2$2;
                this.n3$1 = n3$1;
                this.evidence$5$1 = evidence$5$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$5.runtimeClass()))));
    }

    public <T> Object[][][] ofDim(int n1, int n2, int n3, int n4, ClassTag<T> evidence$6) {
        return (Object[][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, evidence$6){
            public static final long serialVersionUID = 0L;
            private final int n2$8;
            private final int n3$6;
            private final int n4$4;
            private final ClassTag evidence$6$1;

            public final Object[][] apply(int x$2) {
                return Array$.MODULE$.ofDim(this.n2$8, this.n3$6, this.n4$4, this.evidence$6$1);
            }
            {
                this.n2$8 = n2$8;
                this.n3$6 = n3$6;
                this.n4$4 = n4$4;
                this.evidence$6$1 = evidence$6$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$6.runtimeClass())))));
    }

    public <T> Object[][][][] ofDim(int n1, int n2, int n3, int n4, int n5, ClassTag<T> evidence$7) {
        return (Object[][][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, n5, evidence$7){
            public static final long serialVersionUID = 0L;
            private final int n2$7;
            private final int n3$5;
            private final int n4$3;
            private final int n5$2;
            private final ClassTag evidence$7$1;

            public final Object[][][] apply(int x$3) {
                return Array$.MODULE$.ofDim(this.n2$7, this.n3$5, this.n4$3, this.n5$2, this.evidence$7$1);
            }
            {
                this.n2$7 = n2$7;
                this.n3$5 = n3$5;
                this.n4$3 = n4$3;
                this.n5$2 = n5$2;
                this.evidence$7$1 = evidence$7$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$7.runtimeClass()))))));
    }

    public <T> Object concat(Seq<Object> xss, ClassTag<T> evidence$8) {
        ArrayBuilder<T> b = this.newBuilder(evidence$8);
        b.sizeHint(BoxesRunTime.unboxToInt(((TraversableOnce)xss.map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final int apply(Object x$4) {
                return ScalaRunTime$.MODULE$.array_length(x$4);
            }
        }, Seq$.MODULE$.canBuildFrom())).sum(Numeric$IntIsIntegral$.MODULE$)));
        xss.foreach(new Serializable(b){
            public static final long serialVersionUID = 0L;
            private final ArrayBuilder b$1;

            public final ArrayBuilder<T> apply(Object xs) {
                return (ArrayBuilder)this.b$1.$plus$plus$eq(Predef$.MODULE$.genericArrayOps(xs));
            }
            {
                this.b$1 = b$1;
            }
        });
        return b.result();
    }

    public <T> Object fill(int n, Function0<T> elem, ClassTag<T> evidence$9) {
        ArrayBuilder<T> b = this.newBuilder(evidence$9);
        b.sizeHint(n);
        for (int i = 0; i < n; ++i) {
            b.$plus$eq(elem.apply());
        }
        return b.result();
    }

    public <T> Object[] fill(int n1, int n2, Function0<T> elem, ClassTag<T> evidence$10) {
        return (Object[])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, elem, evidence$10){
            public static final long serialVersionUID = 0L;
            private final int n2$9;
            private final Function0 elem$1;
            private final ClassTag evidence$10$1;

            public final Object apply(int x$5) {
                return Array$.MODULE$.fill(this.n2$9, this.elem$1, this.evidence$10$1);
            }
            {
                this.n2$9 = n2$9;
                this.elem$1 = elem$1;
                this.evidence$10$1 = evidence$10$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$10.runtimeClass())));
    }

    public <T> Object[][] fill(int n1, int n2, int n3, Function0<T> elem, ClassTag<T> evidence$11) {
        return (Object[][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, elem, evidence$11){
            public static final long serialVersionUID = 0L;
            private final int n2$12;
            private final int n3$9;
            private final Function0 elem$4;
            private final ClassTag evidence$11$1;

            public final Object[] apply(int x$6) {
                return Array$.MODULE$.fill(this.n2$12, this.n3$9, this.elem$4, this.evidence$11$1);
            }
            {
                this.n2$12 = n2$12;
                this.n3$9 = n3$9;
                this.elem$4 = elem$4;
                this.evidence$11$1 = evidence$11$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$11.runtimeClass()))));
    }

    public <T> Object[][][] fill(int n1, int n2, int n3, int n4, Function0<T> elem, ClassTag<T> evidence$12) {
        return (Object[][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, elem, evidence$12){
            public static final long serialVersionUID = 0L;
            private final int n2$11;
            private final int n3$8;
            private final int n4$6;
            private final Function0 elem$3;
            private final ClassTag evidence$12$1;

            public final Object[][] apply(int x$7) {
                return Array$.MODULE$.fill(this.n2$11, this.n3$8, this.n4$6, this.elem$3, this.evidence$12$1);
            }
            {
                this.n2$11 = n2$11;
                this.n3$8 = n3$8;
                this.n4$6 = n4$6;
                this.elem$3 = elem$3;
                this.evidence$12$1 = evidence$12$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$12.runtimeClass())))));
    }

    public <T> Object[][][][] fill(int n1, int n2, int n3, int n4, int n5, Function0<T> elem, ClassTag<T> evidence$13) {
        return (Object[][][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, n5, elem, evidence$13){
            public static final long serialVersionUID = 0L;
            private final int n2$10;
            private final int n3$7;
            private final int n4$5;
            private final int n5$3;
            private final Function0 elem$2;
            private final ClassTag evidence$13$1;

            public final Object[][][] apply(int x$8) {
                return Array$.MODULE$.fill(this.n2$10, this.n3$7, this.n4$5, this.n5$3, this.elem$2, this.evidence$13$1);
            }
            {
                this.n2$10 = n2$10;
                this.n3$7 = n3$7;
                this.n4$5 = n4$5;
                this.n5$3 = n5$3;
                this.elem$2 = elem$2;
                this.evidence$13$1 = evidence$13$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$13.runtimeClass()))))));
    }

    public <T> Object tabulate(int n, Function1<Object, T> f, ClassTag<T> evidence$14) {
        ArrayBuilder<T> b = this.newBuilder(evidence$14);
        b.sizeHint(n);
        for (int i = 0; i < n; ++i) {
            b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
        }
        return b.result();
    }

    public <T> Object[] tabulate(int n1, int n2, Function2<Object, Object, T> f, ClassTag<T> evidence$15) {
        return (Object[])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, f, evidence$15){
            public static final long serialVersionUID = 0L;
            private final int n2$6;
            public final Function2 f$4;
            private final ClassTag evidence$15$1;

            public final Object apply(int i1) {
                return Array$.MODULE$.tabulate(this.n2$6, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.tabulate.1 $outer;
                    private final int i1$1;

                    public final T apply(int x$9) {
                        return (T)this.$outer.f$4.apply(BoxesRunTime.boxToInteger(this.i1$1), BoxesRunTime.boxToInteger(x$9));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$1 = i1$1;
                    }
                }, this.evidence$15$1);
            }
            {
                this.n2$6 = n2$6;
                this.f$4 = f$4;
                this.evidence$15$1 = evidence$15$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$15.runtimeClass())));
    }

    public <T> Object[][] tabulate(int n1, int n2, int n3, Function3<Object, Object, Object, T> f, ClassTag<T> evidence$16) {
        return (Object[][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, f, evidence$16){
            public static final long serialVersionUID = 0L;
            private final int n2$5;
            private final int n3$4;
            public final Function3 f$3;
            private final ClassTag evidence$16$1;

            public final Object[] apply(int i1) {
                return Array$.MODULE$.tabulate(this.n2$5, this.n3$4, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.tabulate.2 $outer;
                    private final int i1$2;

                    public final T apply(int x$10, int x$11) {
                        return (T)this.$outer.f$3.apply(BoxesRunTime.boxToInteger(this.i1$2), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$2 = i1$2;
                    }
                }, this.evidence$16$1);
            }
            {
                this.n2$5 = n2$5;
                this.n3$4 = n3$4;
                this.f$3 = f$3;
                this.evidence$16$1 = evidence$16$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass()))));
    }

    public <T> Object[][][] tabulate(int n1, int n2, int n3, int n4, Function4<Object, Object, Object, Object, T> f, ClassTag<T> evidence$17) {
        return (Object[][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, f, evidence$17){
            public static final long serialVersionUID = 0L;
            private final int n2$4;
            private final int n3$3;
            private final int n4$2;
            public final Function4 f$2;
            private final ClassTag evidence$17$1;

            public final Object[][] apply(int i1) {
                return Array$.MODULE$.tabulate(this.n2$4, this.n3$3, this.n4$2, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.tabulate.3 $outer;
                    private final int i1$3;

                    public final T apply(int x$12, int x$13, int x$14) {
                        return (T)this.$outer.f$2.apply(BoxesRunTime.boxToInteger(this.i1$3), BoxesRunTime.boxToInteger(x$12), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$3 = i1$3;
                    }
                }, this.evidence$17$1);
            }
            {
                this.n2$4 = n2$4;
                this.n3$3 = n3$3;
                this.n4$2 = n4$2;
                this.f$2 = f$2;
                this.evidence$17$1 = evidence$17$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$17.runtimeClass())))));
    }

    public <T> Object[][][][] tabulate(int n1, int n2, int n3, int n4, int n5, Function5<Object, Object, Object, Object, Object, T> f, ClassTag<T> evidence$18) {
        return (Object[][][][])this.tabulate(n1, (Function1<Object, T>)((Object)new Serializable(n2, n3, n4, n5, f, evidence$18){
            public static final long serialVersionUID = 0L;
            private final int n2$3;
            private final int n3$2;
            private final int n4$1;
            private final int n5$1;
            public final Function5 f$1;
            private final ClassTag evidence$18$1;

            public final Object[][][] apply(int i1) {
                return Array$.MODULE$.tabulate(this.n2$3, this.n3$2, this.n4$1, this.n5$1, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.tabulate.4 $outer;
                    private final int i1$4;

                    public final T apply(int x$15, int x$16, int x$17, int x$18) {
                        return (T)this.$outer.f$1.apply(BoxesRunTime.boxToInteger(this.i1$4), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16), BoxesRunTime.boxToInteger(x$17), BoxesRunTime.boxToInteger(x$18));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$4 = i1$4;
                    }
                }, this.evidence$18$1);
            }
            {
                this.n2$3 = n2$3;
                this.n3$2 = n3$2;
                this.n4$1 = n4$1;
                this.n5$1 = n5$1;
                this.f$1 = f$1;
                this.evidence$18$1 = evidence$18$1;
            }
        }), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$18.runtimeClass()))))));
    }

    public int[] range(int start, int end) {
        return this.range(start, end, 1);
    }

    public int[] range(int start, int end, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("zero step");
        }
        ArrayBuilder<Object> b = this.newBuilder(ClassTag$.MODULE$.Int());
        b.sizeHint(Range$.MODULE$.count(start, end, step, false));
        int i = start;
        while (true) {
            boolean bl = step < 0 ? end < i : i < end;
            if (!bl) break;
            b.$plus$eq(BoxesRunTime.boxToInteger(i));
            i += step;
        }
        return (int[])b.result();
    }

    public <T> Object iterate(T start, int len, Function1<T, T> f, ClassTag<T> evidence$19) {
        ArrayBuilder<T> b = this.newBuilder(evidence$19);
        if (len > 0) {
            b.sizeHint(len);
            T acc = start;
            b.$plus$eq(start);
            for (int i = 1; i < len; ++i) {
                acc = f.apply(acc);
                b.$plus$eq(acc);
            }
        }
        return b.result();
    }

    public <T> Option<IndexedSeq<T>> unapplySeq(Object x) {
        return x == null ? None$.MODULE$ : new Some(Predef$.MODULE$.genericArrayOps(x).toIndexedSeq());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Array$() {
        MODULE$ = this;
        this.emptyBooleanArray = new boolean[0];
        this.emptyByteArray = new byte[0];
        this.emptyCharArray = new char[0];
        this.emptyDoubleArray = new double[0];
        this.emptyFloatArray = new float[0];
        this.emptyIntArray = new int[0];
        this.emptyLongArray = new long[0];
        this.emptyShortArray = new short[0];
        this.emptyObjectArray = new Object[0];
    }
}

