/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import java.util.Arrays;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Predef$;
import scala.Some;
import scala.collection.Seq;
import scala.math.Ordering;
import scala.math.Ordering$;
import scala.math.Ordering$Boolean$;
import scala.math.Ordering$Byte$;
import scala.math.Ordering$Char$;
import scala.math.Ordering$Int$;
import scala.math.Ordering$Long$;
import scala.math.Ordering$Short$;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null$;
import scala.runtime.ScalaRunTime$;

public final class Sorting$ {
    public static final Sorting$ MODULE$;
    private final int qsortThreshold;
    private final int mergeThreshold;

    static {
        new Sorting$();
    }

    public void quickSort(double[] a) {
        Arrays.sort(a);
    }

    public void quickSort(int[] a) {
        Arrays.sort(a);
    }

    public void quickSort(float[] a) {
        Arrays.sort(a);
    }

    private final int qsortThreshold() {
        return 16;
    }

    public <K> void quickSort(Object a, Ordering<K> evidence$1) {
        Predef$ predef$ = Predef$.MODULE$;
        this.inner$1(a, 0, ScalaRunTime$.MODULE$.array_length(a), evidence$1);
    }

    private final int mergeThreshold() {
        return 32;
    }

    public <T> void scala$util$Sorting$$insertionSort(Object a, int i0, int iN, Ordering<T> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, i0 + 1)) > 0) {
            Object temp = ScalaRunTime$.MODULE$.array_apply(a, i0);
            ScalaRunTime$.MODULE$.array_update(a, i0, ScalaRunTime$.MODULE$.array_apply(a, i0 + 1));
            ScalaRunTime$.MODULE$.array_update(a, i0 + 1, temp);
        }
        for (int m = 2; m < n; ++m) {
            Object next2 = ScalaRunTime$.MODULE$.array_apply(a, i0 + m);
            if (ord.compare(next2, ScalaRunTime$.MODULE$.array_apply(a, i0 + m - 1)) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(next2, ScalaRunTime$.MODULE$.array_apply(a, ix)) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(next2, ScalaRunTime$.MODULE$.array_apply(a, iA)) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                ScalaRunTime$.MODULE$.array_update(a, i, ScalaRunTime$.MODULE$.array_apply(a, i - 1));
            }
            ScalaRunTime$.MODULE$.array_update(a, ix, next2);
        }
    }

    public <T> void scala$util$Sorting$$mergeSort(Object a, int i0, int iN, Ordering<T> ord, Object scratch, ClassTag<T> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            Object sc = scratch == null ? evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public <T> Null$ scala$util$Sorting$$mergeSort$default$5() {
        return null;
    }

    public <T> void scala$util$Sorting$$mergeSorted(Object a, int i0, int iK, int iN, Ordering<T> ord, Object scratch) {
        if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iK - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                ScalaRunTime$.MODULE$.array_update(scratch, j, ScalaRunTime$.MODULE$.array_apply(a, i));
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i), ScalaRunTime$.MODULE$.array_apply(scratch, j)) < 0) {
                    ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(a, i));
                    ++i;
                } else {
                    ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(scratch, j));
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(scratch, j));
                ++j;
                ++k;
            }
        }
    }

    public void scala$util$Sorting$$booleanSort(boolean[] a) {
        int i;
        int n = 0;
        for (i = 0; i < a.length; ++i) {
            if (a[i]) continue;
            ++n;
        }
        for (i = 0; i < n; ++i) {
            a[i] = false;
        }
        while (i < a.length) {
            a[i] = true;
            ++i;
        }
    }

    private <T> void sort(Object a, Ordering<T> ord) {
        block26: {
            block18: {
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                if (!(a instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(a) > 1 && ord == null) {
                                                    throw new NullPointerException("Ordering");
                                                }
                                                Arrays.sort((Object[])a, ord);
                                                break block18;
                                            }
                                            if (!(a instanceof int[])) break block19;
                                            int[] nArray = (int[])a;
                                            if (ord == Ordering$Int$.MODULE$) {
                                                Arrays.sort(nArray);
                                            } else {
                                                int n = ScalaRunTime$.MODULE$.array_length(nArray);
                                                this.scala$util$Sorting$$mergeSort$default$5();
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(nArray, 0, n, ord, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(a instanceof double[])) break block20;
                                        double[] dArray = (double[])a;
                                        int n = ScalaRunTime$.MODULE$.array_length(dArray);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(dArray, 0, n, ord, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(a instanceof long[])) break block21;
                                    long[] lArray = (long[])a;
                                    if (ord == Ordering$Long$.MODULE$) {
                                        Arrays.sort(lArray);
                                    } else {
                                        int n = ScalaRunTime$.MODULE$.array_length(lArray);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(lArray, 0, n, ord, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(a instanceof float[])) break block22;
                                float[] fArray = (float[])a;
                                int n = ScalaRunTime$.MODULE$.array_length(fArray);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(fArray, 0, n, ord, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(a instanceof char[])) break block23;
                            char[] cArray = (char[])a;
                            if (ord == Ordering$Char$.MODULE$) {
                                Arrays.sort(cArray);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(cArray);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(cArray, 0, n, ord, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(a instanceof byte[])) break block24;
                        byte[] byArray = (byte[])a;
                        if (ord == Ordering$Byte$.MODULE$) {
                            Arrays.sort(byArray);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(byArray);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(byArray, 0, n, ord, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(a instanceof short[])) break block25;
                    short[] sArray = (short[])a;
                    if (ord == Ordering$Short$.MODULE$) {
                        Arrays.sort(sArray);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(sArray);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(sArray, 0, n, ord, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(a instanceof boolean[])) break block26;
                boolean[] blArray = (boolean[])a;
                if (ord == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(blArray);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(blArray);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(blArray, 0, n, ord, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return;
        }
        if (a == null) {
            throw new NullPointerException();
        }
        throw new MatchError(a);
    }

    public <K> void stableSort(Object a, ClassTag<K> evidence$3, Ordering<K> evidence$4) {
        block26: {
            block18: {
                Ordering<Object> ordering;
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                ordering = Ordering$.MODULE$.apply(evidence$4);
                                                if (!(a instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(a) > 1 && ordering == null) {
                                                    throw new NullPointerException("Ordering");
                                                }
                                                Arrays.sort((Object[])a, ordering);
                                                break block18;
                                            }
                                            if (!(a instanceof int[])) break block19;
                                            int[] x31 = (int[])a;
                                            if (ordering == Ordering$Int$.MODULE$) {
                                                Arrays.sort(x31);
                                            } else {
                                                int n = ScalaRunTime$.MODULE$.array_length(x31);
                                                this.scala$util$Sorting$$mergeSort$default$5();
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(x31, 0, n, ordering, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(a instanceof double[])) break block20;
                                        double[] x41 = (double[])a;
                                        int n = ScalaRunTime$.MODULE$.array_length(x41);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(x41, 0, n, ordering, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(a instanceof long[])) break block21;
                                    long[] x51 = (long[])a;
                                    if (ordering == Ordering$Long$.MODULE$) {
                                        Arrays.sort(x51);
                                    } else {
                                        int n = ScalaRunTime$.MODULE$.array_length(x51);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(x51, 0, n, ordering, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(a instanceof float[])) break block22;
                                float[] x61 = (float[])a;
                                int n = ScalaRunTime$.MODULE$.array_length(x61);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(x61, 0, n, ordering, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(a instanceof char[])) break block23;
                            char[] x71 = (char[])a;
                            if (ordering == Ordering$Char$.MODULE$) {
                                Arrays.sort(x71);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(x71);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(x71, 0, n, ordering, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(a instanceof byte[])) break block24;
                        byte[] x81 = (byte[])a;
                        if (ordering == Ordering$Byte$.MODULE$) {
                            Arrays.sort(x81);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(x81);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(x81, 0, n, ordering, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(a instanceof short[])) break block25;
                    short[] x91 = (short[])a;
                    if (ordering == Ordering$Short$.MODULE$) {
                        Arrays.sort(x91);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(x91);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(x91, 0, n, ordering, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(a instanceof boolean[])) break block26;
                boolean[] x101 = (boolean[])a;
                if (ordering == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(x101);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(x101);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(x101, 0, n, ordering, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return;
        }
        if (a == null) {
            throw new NullPointerException();
        }
        throw new MatchError(a);
    }

    public <K> void stableSort(Object a, Function2<K, K, Object> f, ClassTag<K> evidence$5) {
        block26: {
            block18: {
                Ordering ordering;
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                Ordering$ ordering$ = Ordering$.MODULE$;
                                                ordering = new Ordering<T>(f){
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
                                                };
                                                if (!(a instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(a) > 1) {
                                                    // empty if block
                                                }
                                                Arrays.sort((Object[])a, ordering);
                                                break block18;
                                            }
                                            if (!(a instanceof int[])) break block19;
                                            int[] x31 = (int[])a;
                                            if (ordering == Ordering$Int$.MODULE$) {
                                                Arrays.sort(x31);
                                            } else {
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(x31, 0, ScalaRunTime$.MODULE$.array_length(x31), ordering, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(a instanceof double[])) break block20;
                                        double[] x41 = (double[])a;
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(x41, 0, ScalaRunTime$.MODULE$.array_length(x41), ordering, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(a instanceof long[])) break block21;
                                    long[] x51 = (long[])a;
                                    if (ordering == Ordering$Long$.MODULE$) {
                                        Arrays.sort(x51);
                                    } else {
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(x51, 0, ScalaRunTime$.MODULE$.array_length(x51), ordering, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(a instanceof float[])) break block22;
                                float[] x61 = (float[])a;
                                int n = ScalaRunTime$.MODULE$.array_length(x61);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(x61, 0, n, ordering, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(a instanceof char[])) break block23;
                            char[] x71 = (char[])a;
                            if (ordering == Ordering$Char$.MODULE$) {
                                Arrays.sort(x71);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(x71);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(x71, 0, n, ordering, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(a instanceof byte[])) break block24;
                        byte[] x81 = (byte[])a;
                        if (ordering == Ordering$Byte$.MODULE$) {
                            Arrays.sort(x81);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(x81);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(x81, 0, n, ordering, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(a instanceof short[])) break block25;
                    short[] x91 = (short[])a;
                    if (ordering == Ordering$Short$.MODULE$) {
                        Arrays.sort(x91);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(x91);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(x91, 0, n, ordering, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(a instanceof boolean[])) break block26;
                boolean[] x101 = (boolean[])a;
                if (ordering == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(x101);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(x101);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(x101, 0, n, ordering, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return;
        }
        if (a == null) {
            throw new NullPointerException();
        }
        throw new MatchError(a);
    }

    /*
     * WARNING - void declaration
     */
    public <K> Object stableSort(Seq<K> a, ClassTag<K> evidence$6, Ordering<K> evidence$7) {
        void var13_4;
        block26: {
            Object ret;
            block18: {
                Ordering<Object> ordering;
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                ret = a.toArray(evidence$6);
                                                ordering = Ordering$.MODULE$.apply(evidence$7);
                                                if (!(ret instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(ret) > 1 && ordering == null) {
                                                    throw new NullPointerException("Ordering");
                                                }
                                                Arrays.sort((Object[])ret, ordering);
                                                break block18;
                                            }
                                            if (!(ret instanceof int[])) break block19;
                                            int[] x31 = (int[])ret;
                                            if (ordering == Ordering$Int$.MODULE$) {
                                                Arrays.sort(x31);
                                            } else {
                                                int n = ScalaRunTime$.MODULE$.array_length(x31);
                                                this.scala$util$Sorting$$mergeSort$default$5();
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(x31, 0, n, ordering, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(ret instanceof double[])) break block20;
                                        double[] x41 = (double[])ret;
                                        int n = ScalaRunTime$.MODULE$.array_length(x41);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(x41, 0, n, ordering, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(ret instanceof long[])) break block21;
                                    long[] x51 = (long[])ret;
                                    if (ordering == Ordering$Long$.MODULE$) {
                                        Arrays.sort(x51);
                                    } else {
                                        int n = ScalaRunTime$.MODULE$.array_length(x51);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(x51, 0, n, ordering, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(ret instanceof float[])) break block22;
                                float[] x61 = (float[])ret;
                                int n = ScalaRunTime$.MODULE$.array_length(x61);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(x61, 0, n, ordering, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(ret instanceof char[])) break block23;
                            char[] x71 = (char[])ret;
                            if (ordering == Ordering$Char$.MODULE$) {
                                Arrays.sort(x71);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(x71);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(x71, 0, n, ordering, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(ret instanceof byte[])) break block24;
                        byte[] x81 = (byte[])ret;
                        if (ordering == Ordering$Byte$.MODULE$) {
                            Arrays.sort(x81);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(x81);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(x81, 0, n, ordering, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(ret instanceof short[])) break block25;
                    short[] x91 = (short[])ret;
                    if (ordering == Ordering$Short$.MODULE$) {
                        Arrays.sort(x91);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(x91);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(x91, 0, n, ordering, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(ret instanceof boolean[])) break block26;
                boolean[] x101 = (boolean[])ret;
                if (ordering == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(x101);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(x101);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(x101, 0, n, ordering, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return ret;
        }
        if (var13_4 == null) {
            throw new NullPointerException();
        }
        throw new MatchError(var13_4);
    }

    /*
     * WARNING - void declaration
     */
    public <K> Object stableSort(Seq<K> a, Function2<K, K, Object> f, ClassTag<K> evidence$8) {
        void var14_4;
        block26: {
            Object ret;
            block18: {
                Ordering ordering;
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                ret = a.toArray(evidence$8);
                                                Ordering$ ordering$ = Ordering$.MODULE$;
                                                ordering = new /* invalid duplicate definition of identical inner class */;
                                                if (!(ret instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(ret) > 1) {
                                                    // empty if block
                                                }
                                                Arrays.sort((Object[])ret, ordering);
                                                break block18;
                                            }
                                            if (!(ret instanceof int[])) break block19;
                                            int[] x31 = (int[])ret;
                                            if (ordering == Ordering$Int$.MODULE$) {
                                                Arrays.sort(x31);
                                            } else {
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(x31, 0, ScalaRunTime$.MODULE$.array_length(x31), ordering, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(ret instanceof double[])) break block20;
                                        double[] x41 = (double[])ret;
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(x41, 0, ScalaRunTime$.MODULE$.array_length(x41), ordering, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(ret instanceof long[])) break block21;
                                    long[] x51 = (long[])ret;
                                    if (ordering == Ordering$Long$.MODULE$) {
                                        Arrays.sort(x51);
                                    } else {
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(x51, 0, ScalaRunTime$.MODULE$.array_length(x51), ordering, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(ret instanceof float[])) break block22;
                                float[] x61 = (float[])ret;
                                int n = ScalaRunTime$.MODULE$.array_length(x61);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(x61, 0, n, ordering, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(ret instanceof char[])) break block23;
                            char[] x71 = (char[])ret;
                            if (ordering == Ordering$Char$.MODULE$) {
                                Arrays.sort(x71);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(x71);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(x71, 0, n, ordering, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(ret instanceof byte[])) break block24;
                        byte[] x81 = (byte[])ret;
                        if (ordering == Ordering$Byte$.MODULE$) {
                            Arrays.sort(x81);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(x81);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(x81, 0, n, ordering, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(ret instanceof short[])) break block25;
                    short[] x91 = (short[])ret;
                    if (ordering == Ordering$Short$.MODULE$) {
                        Arrays.sort(x91);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(x91);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(x91, 0, n, ordering, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(ret instanceof boolean[])) break block26;
                boolean[] x101 = (boolean[])ret;
                if (ordering == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(x101);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(x101);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(x101, 0, n, ordering, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return ret;
        }
        if (var14_4 == null) {
            throw new NullPointerException();
        }
        throw new MatchError(var14_4);
    }

    /*
     * WARNING - void declaration
     */
    public <K, M> Object stableSort(Seq<K> a, Function1<K, M> f, ClassTag<K> evidence$9, Ordering<M> evidence$10) {
        void var14_5;
        block26: {
            Object ret;
            block18: {
                Ordering<Object> ordering;
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block17: {
                                                ret = a.toArray(evidence$9);
                                                ordering = Ordering$.MODULE$.apply(evidence$10).on(f);
                                                if (!(ret instanceof Object[])) break block17;
                                                if (ScalaRunTime$.MODULE$.array_length(ret) > 1 && ordering == null) {
                                                    throw new NullPointerException("Ordering");
                                                }
                                                Arrays.sort((Object[])ret, ordering);
                                                break block18;
                                            }
                                            if (!(ret instanceof int[])) break block19;
                                            int[] x31 = (int[])ret;
                                            if (ordering == Ordering$Int$.MODULE$) {
                                                Arrays.sort(x31);
                                            } else {
                                                int n = ScalaRunTime$.MODULE$.array_length(x31);
                                                this.scala$util$Sorting$$mergeSort$default$5();
                                                this.scala$util$Sorting$$mergeSort$mIc$sp(x31, 0, n, ordering, null, ClassTag$.MODULE$.Int());
                                            }
                                            break block18;
                                        }
                                        if (!(ret instanceof double[])) break block20;
                                        double[] x41 = (double[])ret;
                                        int n = ScalaRunTime$.MODULE$.array_length(x41);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mDc$sp(x41, 0, n, ordering, null, ClassTag$.MODULE$.Double());
                                        break block18;
                                    }
                                    if (!(ret instanceof long[])) break block21;
                                    long[] x51 = (long[])ret;
                                    if (ordering == Ordering$Long$.MODULE$) {
                                        Arrays.sort(x51);
                                    } else {
                                        int n = ScalaRunTime$.MODULE$.array_length(x51);
                                        this.scala$util$Sorting$$mergeSort$default$5();
                                        this.scala$util$Sorting$$mergeSort$mJc$sp(x51, 0, n, ordering, null, ClassTag$.MODULE$.Long());
                                    }
                                    break block18;
                                }
                                if (!(ret instanceof float[])) break block22;
                                float[] x61 = (float[])ret;
                                int n = ScalaRunTime$.MODULE$.array_length(x61);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mFc$sp(x61, 0, n, ordering, null, ClassTag$.MODULE$.Float());
                                break block18;
                            }
                            if (!(ret instanceof char[])) break block23;
                            char[] x71 = (char[])ret;
                            if (ordering == Ordering$Char$.MODULE$) {
                                Arrays.sort(x71);
                            } else {
                                int n = ScalaRunTime$.MODULE$.array_length(x71);
                                this.scala$util$Sorting$$mergeSort$default$5();
                                this.scala$util$Sorting$$mergeSort$mCc$sp(x71, 0, n, ordering, null, ClassTag$.MODULE$.Char());
                            }
                            break block18;
                        }
                        if (!(ret instanceof byte[])) break block24;
                        byte[] x81 = (byte[])ret;
                        if (ordering == Ordering$Byte$.MODULE$) {
                            Arrays.sort(x81);
                        } else {
                            int n = ScalaRunTime$.MODULE$.array_length(x81);
                            this.scala$util$Sorting$$mergeSort$default$5();
                            this.scala$util$Sorting$$mergeSort$mBc$sp(x81, 0, n, ordering, null, ClassTag$.MODULE$.Byte());
                        }
                        break block18;
                    }
                    if (!(ret instanceof short[])) break block25;
                    short[] x91 = (short[])ret;
                    if (ordering == Ordering$Short$.MODULE$) {
                        Arrays.sort(x91);
                    } else {
                        int n = ScalaRunTime$.MODULE$.array_length(x91);
                        this.scala$util$Sorting$$mergeSort$default$5();
                        this.scala$util$Sorting$$mergeSort$mSc$sp(x91, 0, n, ordering, null, ClassTag$.MODULE$.Short());
                    }
                    break block18;
                }
                if (!(ret instanceof boolean[])) break block26;
                boolean[] x101 = (boolean[])ret;
                if (ordering == Ordering$Boolean$.MODULE$) {
                    this.scala$util$Sorting$$booleanSort(x101);
                } else {
                    int n = ScalaRunTime$.MODULE$.array_length(x101);
                    this.scala$util$Sorting$$mergeSort$default$5();
                    this.scala$util$Sorting$$mergeSort$mZc$sp(x101, 0, n, ordering, null, ClassTag$.MODULE$.Boolean());
                }
            }
            return ret;
        }
        if (var14_5 == null) {
            throw new NullPointerException();
        }
        throw new MatchError(var14_5);
    }

    private void insertionSort$mZc$sp(boolean[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToBoolean(a[i0]), BoxesRunTime.boxToBoolean(a[i0 + 1])) > 0) {
            boolean temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            boolean next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToBoolean(next2), BoxesRunTime.boxToBoolean(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToBoolean(next2), BoxesRunTime.boxToBoolean(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToBoolean(next2), BoxesRunTime.boxToBoolean(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mBc$sp(byte[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToByte(a[i0]), BoxesRunTime.boxToByte(a[i0 + 1])) > 0) {
            byte temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            byte next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToByte(next2), BoxesRunTime.boxToByte(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToByte(next2), BoxesRunTime.boxToByte(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToByte(next2), BoxesRunTime.boxToByte(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mCc$sp(char[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToCharacter(a[i0]), BoxesRunTime.boxToCharacter(a[i0 + 1])) > 0) {
            char temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            char next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToCharacter(next2), BoxesRunTime.boxToCharacter(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToCharacter(next2), BoxesRunTime.boxToCharacter(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToCharacter(next2), BoxesRunTime.boxToCharacter(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mDc$sp(double[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToDouble(a[i0]), BoxesRunTime.boxToDouble(a[i0 + 1])) > 0) {
            double temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            double next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToDouble(next2), BoxesRunTime.boxToDouble(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToDouble(next2), BoxesRunTime.boxToDouble(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToDouble(next2), BoxesRunTime.boxToDouble(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mFc$sp(float[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToFloat(a[i0]), BoxesRunTime.boxToFloat(a[i0 + 1])) > 0) {
            float temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            float next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToFloat(next2), BoxesRunTime.boxToFloat(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToFloat(next2), BoxesRunTime.boxToFloat(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToFloat(next2), BoxesRunTime.boxToFloat(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mIc$sp(int[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToInteger(a[i0]), BoxesRunTime.boxToInteger(a[i0 + 1])) > 0) {
            int temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            int next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToInteger(next2), BoxesRunTime.boxToInteger(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToInteger(next2), BoxesRunTime.boxToInteger(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToInteger(next2), BoxesRunTime.boxToInteger(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mJc$sp(long[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToLong(a[i0]), BoxesRunTime.boxToLong(a[i0 + 1])) > 0) {
            long temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            long next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToLong(next2), BoxesRunTime.boxToLong(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToLong(next2), BoxesRunTime.boxToLong(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToLong(next2), BoxesRunTime.boxToLong(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mSc$sp(short[] a, int i0, int iN, Ordering<Object> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(BoxesRunTime.boxToShort(a[i0]), BoxesRunTime.boxToShort(a[i0 + 1])) > 0) {
            short temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            short next2 = a[i0 + m];
            if (ord.compare(BoxesRunTime.boxToShort(next2), BoxesRunTime.boxToShort(a[i0 + m - 1])) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(BoxesRunTime.boxToShort(next2), BoxesRunTime.boxToShort(a[ix])) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(BoxesRunTime.boxToShort(next2), BoxesRunTime.boxToShort(a[iA])) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    private void insertionSort$mVc$sp(BoxedUnit[] a, int i0, int iN, Ordering<BoxedUnit> ord) {
        int n = iN - i0;
        if (n < 2) {
            return;
        }
        if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            BoxedUnit temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
        }
        for (int m = 2; m < n; ++m) {
            BoxedUnit next2 = a[i0 + m];
            if (ord.compare(next2, a[i0 + m - 1]) >= 0) continue;
            int iA = i0;
            int iB = i0 + m - 1;
            while (iB - iA > 1) {
                int ix = iA + iB >>> 1;
                if (ord.compare(next2, a[ix]) < 0) {
                    iB = ix;
                    continue;
                }
                iA = ix;
            }
            int ix = iA + (ord.compare(next2, a[iA]) < 0 ? 0 : 1);
            for (int i = i0 + m; i > ix; --i) {
                a[i] = a[i - 1];
            }
            a[ix] = next2;
        }
    }

    public void scala$util$Sorting$$mergeSort$mZc$sp(boolean[] a, int i0, int iN, Ordering<Object> ord, boolean[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            boolean[] sc = scratch == null ? (boolean[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mBc$sp(byte[] a, int i0, int iN, Ordering<Object> ord, byte[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            byte[] sc = scratch == null ? (byte[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mCc$sp(char[] a, int i0, int iN, Ordering<Object> ord, char[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            char[] sc = scratch == null ? (char[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mDc$sp(double[] a, int i0, int iN, Ordering<Object> ord, double[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            double[] sc = scratch == null ? (double[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mFc$sp(float[] a, int i0, int iN, Ordering<Object> ord, float[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            float[] sc = scratch == null ? (float[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mIc$sp(int[] a, int i0, int iN, Ordering<Object> ord, int[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            int[] sc = scratch == null ? (int[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mJc$sp(long[] a, int i0, int iN, Ordering<Object> ord, long[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            long[] sc = scratch == null ? (long[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    public void scala$util$Sorting$$mergeSort$mSc$sp(short[] a, int i0, int iN, Ordering<Object> ord, short[] scratch, ClassTag<Object> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            short[] sc = scratch == null ? (short[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    private void mergeSort$mVc$sp(BoxedUnit[] a, int i0, int iN, Ordering<BoxedUnit> ord, BoxedUnit[] scratch, ClassTag<BoxedUnit> evidence$2) {
        if (iN - i0 < 32) {
            this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
        } else {
            int iK = i0 + iN >>> 1;
            BoxedUnit[] sc = scratch == null ? (BoxedUnit[])evidence$2.newArray(iK - i0) : scratch;
            this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
            this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
        }
    }

    private void mergeSorted$mZc$sp(boolean[] a, int i0, int iK, int iN, Ordering<Object> ord, boolean[] scratch) {
        if (ord.compare(BoxesRunTime.boxToBoolean(a[iK - 1]), BoxesRunTime.boxToBoolean(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToBoolean(a[i]), BoxesRunTime.boxToBoolean(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mBc$sp(byte[] a, int i0, int iK, int iN, Ordering<Object> ord, byte[] scratch) {
        if (ord.compare(BoxesRunTime.boxToByte(a[iK - 1]), BoxesRunTime.boxToByte(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToByte(a[i]), BoxesRunTime.boxToByte(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mCc$sp(char[] a, int i0, int iK, int iN, Ordering<Object> ord, char[] scratch) {
        if (ord.compare(BoxesRunTime.boxToCharacter(a[iK - 1]), BoxesRunTime.boxToCharacter(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToCharacter(a[i]), BoxesRunTime.boxToCharacter(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mDc$sp(double[] a, int i0, int iK, int iN, Ordering<Object> ord, double[] scratch) {
        if (ord.compare(BoxesRunTime.boxToDouble(a[iK - 1]), BoxesRunTime.boxToDouble(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToDouble(a[i]), BoxesRunTime.boxToDouble(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mFc$sp(float[] a, int i0, int iK, int iN, Ordering<Object> ord, float[] scratch) {
        if (ord.compare(BoxesRunTime.boxToFloat(a[iK - 1]), BoxesRunTime.boxToFloat(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToFloat(a[i]), BoxesRunTime.boxToFloat(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mIc$sp(int[] a, int i0, int iK, int iN, Ordering<Object> ord, int[] scratch) {
        if (ord.compare(BoxesRunTime.boxToInteger(a[iK - 1]), BoxesRunTime.boxToInteger(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToInteger(a[i]), BoxesRunTime.boxToInteger(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mJc$sp(long[] a, int i0, int iK, int iN, Ordering<Object> ord, long[] scratch) {
        if (ord.compare(BoxesRunTime.boxToLong(a[iK - 1]), BoxesRunTime.boxToLong(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToLong(a[i]), BoxesRunTime.boxToLong(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mSc$sp(short[] a, int i0, int iK, int iN, Ordering<Object> ord, short[] scratch) {
        if (ord.compare(BoxesRunTime.boxToShort(a[iK - 1]), BoxesRunTime.boxToShort(a[iK])) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(BoxesRunTime.boxToShort(a[i]), BoxesRunTime.boxToShort(scratch[j])) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private void mergeSorted$mVc$sp(BoxedUnit[] a, int i0, int iK, int iN, Ordering<BoxedUnit> ord, BoxedUnit[] scratch) {
        if (ord.compare(a[iK - 1], a[iK]) > 0) {
            int i = i0;
            int jN = iK - i0;
            int j = 0;
            while (i < iK) {
                scratch[j] = a[i];
                ++i;
                ++j;
            }
            int k = i0;
            j = 0;
            while (i < iN && j < jN) {
                if (ord.compare(a[i], scratch[j]) < 0) {
                    a[k] = a[i];
                    ++i;
                } else {
                    a[k] = scratch[j];
                    ++j;
                }
                ++k;
            }
            while (j < jN) {
                a[k] = scratch[j];
                ++j;
                ++k;
            }
        }
    }

    private final void inner$1(Object a, int i0, int iN, Ordering ord) {
        while (true) {
            if (iN - i0 < 16) {
                this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
                return;
            }
            int iK = i0 + iN >>> 1;
            int pL = ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iN - 1)) <= 0 ? (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iN - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? iN - 1 : iK) : i0) : (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? i0 : (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iN - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) <= 0 ? iN - 1 : iK));
            Object pivot = ScalaRunTime$.MODULE$.array_apply(a, pL);
            if (pL != iK) {
                ScalaRunTime$.MODULE$.array_update(a, pL, ScalaRunTime$.MODULE$.array_apply(a, iK));
                ScalaRunTime$.MODULE$.array_update(a, iK, pivot);
                pL = iK;
            }
            int pR = pL + 1;
            int iA = i0;
            int iB = iN;
            block7: while (pL - iA > 0) {
                Object current = ScalaRunTime$.MODULE$.array_apply(a, iA);
                int n = ord.compare(current, pivot);
                switch (n) {
                    default: {
                        if (n < 0) {
                            ++iA;
                            continue block7;
                        }
                        if (iB > pR) {
                            ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, iB - 1));
                            ScalaRunTime$.MODULE$.array_update(a, iB - 1, current);
                            --iB;
                            continue block7;
                        }
                        ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, pL - 1));
                        ScalaRunTime$.MODULE$.array_update(a, pL - 1, ScalaRunTime$.MODULE$.array_apply(a, pR - 1));
                        ScalaRunTime$.MODULE$.array_update(a, pR - 1, current);
                        --pL;
                        --pR;
                        --iB;
                        continue block7;
                    }
                    case 0: 
                }
                ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, pL - 1));
                ScalaRunTime$.MODULE$.array_update(a, pL - 1, current);
                --pL;
            }
            block8: while (iB - pR > 0) {
                Object current = ScalaRunTime$.MODULE$.array_apply(a, iB - 1);
                int n = ord.compare(current, pivot);
                switch (n) {
                    default: {
                        if (n > 0) {
                            --iB;
                            continue block8;
                        }
                        ScalaRunTime$.MODULE$.array_update(a, iB - 1, ScalaRunTime$.MODULE$.array_apply(a, pR));
                        ScalaRunTime$.MODULE$.array_update(a, pR, ScalaRunTime$.MODULE$.array_apply(a, pL));
                        ScalaRunTime$.MODULE$.array_update(a, pL, current);
                        ++iA;
                        ++pL;
                        ++pR;
                        continue block8;
                    }
                    case 0: 
                }
                ScalaRunTime$.MODULE$.array_update(a, iB - 1, ScalaRunTime$.MODULE$.array_apply(a, pR));
                ScalaRunTime$.MODULE$.array_update(a, pR, current);
                ++pR;
            }
            if (iA - i0 < iN - iB) {
                this.inner$1(a, i0, iA, ord);
                i0 = iB;
                continue;
            }
            this.inner$1(a, iB, iN, ord);
            iN = iA;
        }
    }

    private Sorting$() {
        MODULE$ = this;
    }
}

