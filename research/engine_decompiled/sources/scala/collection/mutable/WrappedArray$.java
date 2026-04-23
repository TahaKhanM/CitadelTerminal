/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.MatchError;
import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class WrappedArray$ {
    public static final WrappedArray$ MODULE$;
    private final WrappedArray.ofRef<Object> EmptyWrappedArray;

    static {
        new WrappedArray$();
    }

    private WrappedArray.ofRef<Object> EmptyWrappedArray() {
        return this.EmptyWrappedArray;
    }

    public <T> WrappedArray<T> empty() {
        return this.EmptyWrappedArray();
    }

    public <T> WrappedArray<T> make(Object x) {
        block13: {
            WrappedArray wrappedArray;
            block3: {
                block12: {
                    block11: {
                        block10: {
                            block9: {
                                block8: {
                                    block7: {
                                        block6: {
                                            block5: {
                                                block4: {
                                                    block2: {
                                                        if (x != null) break block2;
                                                        wrappedArray = null;
                                                        break block3;
                                                    }
                                                    if (!(x instanceof Object[])) break block4;
                                                    Object[] objectArray = (Object[])x;
                                                    wrappedArray = new WrappedArray.ofRef<Object>(objectArray);
                                                    break block3;
                                                }
                                                if (!(x instanceof int[])) break block5;
                                                int[] nArray = (int[])x;
                                                wrappedArray = new WrappedArray.ofInt(nArray);
                                                break block3;
                                            }
                                            if (!(x instanceof double[])) break block6;
                                            double[] dArray = (double[])x;
                                            wrappedArray = new WrappedArray.ofDouble(dArray);
                                            break block3;
                                        }
                                        if (!(x instanceof long[])) break block7;
                                        long[] lArray = (long[])x;
                                        wrappedArray = new WrappedArray.ofLong(lArray);
                                        break block3;
                                    }
                                    if (!(x instanceof float[])) break block8;
                                    float[] fArray = (float[])x;
                                    wrappedArray = new WrappedArray.ofFloat(fArray);
                                    break block3;
                                }
                                if (!(x instanceof char[])) break block9;
                                char[] cArray = (char[])x;
                                wrappedArray = new WrappedArray.ofChar(cArray);
                                break block3;
                            }
                            if (!(x instanceof byte[])) break block10;
                            byte[] byArray = (byte[])x;
                            wrappedArray = new WrappedArray.ofByte(byArray);
                            break block3;
                        }
                        if (!(x instanceof short[])) break block11;
                        short[] sArray = (short[])x;
                        wrappedArray = new WrappedArray.ofShort(sArray);
                        break block3;
                    }
                    if (!(x instanceof boolean[])) break block12;
                    boolean[] blArray = (boolean[])x;
                    wrappedArray = new WrappedArray.ofBoolean(blArray);
                    break block3;
                }
                if (!(x instanceof BoxedUnit[])) break block13;
                BoxedUnit[] boxedUnitArray = (BoxedUnit[])x;
                wrappedArray = new WrappedArray.ofUnit(boxedUnitArray);
            }
            return wrappedArray;
        }
        throw new MatchError(x);
    }

    public <T> CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>> canBuildFrom(ClassTag<T> m) {
        return new CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>>(m){
            private final ClassTag m$1;

            public Builder<T, WrappedArray<T>> apply(WrappedArray<?> from2) {
                return ArrayBuilder$.MODULE$.make(this.m$1).mapResult(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final WrappedArray<T> apply(Object x) {
                        return WrappedArray$.MODULE$.make(x);
                    }
                });
            }

            public Builder<T, WrappedArray<T>> apply() {
                return ArrayBuilder$.MODULE$.make(this.m$1).mapResult(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final WrappedArray<T> apply(Object x) {
                        return WrappedArray$.MODULE$.make(x);
                    }
                });
            }
            {
                this.m$1 = m$1;
            }
        };
    }

    public <A> Builder<A, IndexedSeq<A>> newBuilder() {
        return new ArrayBuffer();
    }

    private WrappedArray$() {
        MODULE$ = this;
        this.EmptyWrappedArray = new WrappedArray.ofRef<Object>(new Object[0]);
    }
}

