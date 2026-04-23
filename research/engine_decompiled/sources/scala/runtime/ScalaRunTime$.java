/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import scala.MatchError;
import scala.Predef$;
import scala.Product;
import scala.Product1;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.UninitializedError;
import scala.collection.AbstractIterator;
import scala.collection.GenIterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.IsTraversableLike;
import scala.collection.generic.Sorted;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.StringLike;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.package$;
import scala.runtime.ArrayRuntime;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichChar$;
import scala.runtime.RichInt$;
import scala.util.hashing.MurmurHash3$;

public final class ScalaRunTime$ {
    public static final ScalaRunTime$ MODULE$;

    static {
        new ScalaRunTime$();
    }

    public boolean isArray(Object x, int atLevel) {
        return x != null && this.isArrayClass(x.getClass(), atLevel);
    }

    public int isArray$default$2() {
        return 1;
    }

    private boolean isArrayClass(Class<?> clazz, int atLevel) {
        boolean bl;
        block2: {
            while (clazz.isArray()) {
                if (atLevel == 1) {
                    bl = true;
                    break block2;
                }
                --atLevel;
                clazz = clazz.getComponentType();
            }
            bl = false;
        }
        return bl;
    }

    public boolean isValueClass(Class<?> clazz) {
        return clazz.isPrimitive();
    }

    public boolean isTuple(Object x) {
        return x != null && x.getClass().getName().startsWith("scala.Tuple");
    }

    public boolean isAnyVal(Object x) {
        boolean bl = x instanceof Byte ? true : (x instanceof Short ? true : (x instanceof Character ? true : (x instanceof Integer ? true : (x instanceof Long ? true : (x instanceof Float ? true : (x instanceof Double ? true : (x instanceof Boolean ? true : x instanceof BoxedUnit)))))));
        boolean bl2 = bl;
        return bl2;
    }

    public <Repr> Repr drop(Repr coll, int num, IsTraversableLike<Repr> traversable) {
        return traversable.conversion().apply(coll).drop(num);
    }

    public Class<?> arrayClass(Class<?> clazz) {
        Class<?> clazz2 = clazz;
        Class<Void> clazz3 = Void.TYPE;
        return !(clazz2 != null ? !clazz2.equals(clazz3) : clazz3 != null) ? BoxedUnit[].class : Array.newInstance(clazz, 0).getClass();
    }

    public Class<?> arrayElementClass(Object schematic) {
        block4: {
            Class<?> clazz;
            block3: {
                block2: {
                    if (!(schematic instanceof Class)) break block2;
                    Class clazz2 = (Class)schematic;
                    clazz = clazz2.getComponentType();
                    break block3;
                }
                if (!(schematic instanceof ClassTag)) break block4;
                ClassTag classTag = (ClassTag)schematic;
                clazz = classTag.runtimeClass();
            }
            return clazz;
        }
        throw new UnsupportedOperationException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unsupported schematic ", " (", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{schematic, schematic.getClass()})));
    }

    public <T> Class<T> anyValClass(T value, ClassTag<T> evidence$1) {
        return package$.MODULE$.classTag(evidence$1).runtimeClass();
    }

    public Object array_apply(Object xs, int idx) {
        block14: {
            Object object;
            block5: {
                block13: {
                    block12: {
                        block11: {
                            block10: {
                                block9: {
                                    block8: {
                                        block7: {
                                            block6: {
                                                block4: {
                                                    if (!(xs instanceof Object[])) break block4;
                                                    Object[] objectArray = (Object[])xs;
                                                    object = objectArray[idx];
                                                    break block5;
                                                }
                                                if (!(xs instanceof int[])) break block6;
                                                int[] nArray = (int[])xs;
                                                object = BoxesRunTime.boxToInteger(nArray[idx]);
                                                break block5;
                                            }
                                            if (!(xs instanceof double[])) break block7;
                                            double[] dArray = (double[])xs;
                                            object = BoxesRunTime.boxToDouble(dArray[idx]);
                                            break block5;
                                        }
                                        if (!(xs instanceof long[])) break block8;
                                        long[] lArray = (long[])xs;
                                        object = BoxesRunTime.boxToLong(lArray[idx]);
                                        break block5;
                                    }
                                    if (!(xs instanceof float[])) break block9;
                                    float[] fArray = (float[])xs;
                                    object = BoxesRunTime.boxToFloat(fArray[idx]);
                                    break block5;
                                }
                                if (!(xs instanceof char[])) break block10;
                                char[] cArray = (char[])xs;
                                object = BoxesRunTime.boxToCharacter(cArray[idx]);
                                break block5;
                            }
                            if (!(xs instanceof byte[])) break block11;
                            byte[] byArray = (byte[])xs;
                            object = BoxesRunTime.boxToByte(byArray[idx]);
                            break block5;
                        }
                        if (!(xs instanceof short[])) break block12;
                        short[] sArray = (short[])xs;
                        object = BoxesRunTime.boxToShort(sArray[idx]);
                        break block5;
                    }
                    if (!(xs instanceof boolean[])) break block13;
                    boolean[] blArray = (boolean[])xs;
                    object = BoxesRunTime.boxToBoolean(blArray[idx]);
                    break block5;
                }
                if (!(xs instanceof BoxedUnit[])) break block14;
                BoxedUnit[] boxedUnitArray = (BoxedUnit[])xs;
                object = boxedUnitArray[idx];
            }
            return object;
        }
        if (xs == null) {
            throw new NullPointerException();
        }
        throw new MatchError(xs);
    }

    public void array_update(Object xs, int idx, Object value) {
        block14: {
            block5: {
                block13: {
                    block12: {
                        block11: {
                            block10: {
                                block9: {
                                    block8: {
                                        block7: {
                                            block6: {
                                                block4: {
                                                    if (!(xs instanceof Object[])) break block4;
                                                    Object[] objectArray = (Object[])xs;
                                                    objectArray[idx] = value;
                                                    break block5;
                                                }
                                                if (!(xs instanceof int[])) break block6;
                                                int[] nArray = (int[])xs;
                                                nArray[idx] = BoxesRunTime.unboxToInt(value);
                                                break block5;
                                            }
                                            if (!(xs instanceof double[])) break block7;
                                            double[] dArray = (double[])xs;
                                            dArray[idx] = BoxesRunTime.unboxToDouble(value);
                                            break block5;
                                        }
                                        if (!(xs instanceof long[])) break block8;
                                        long[] lArray = (long[])xs;
                                        lArray[idx] = BoxesRunTime.unboxToLong(value);
                                        break block5;
                                    }
                                    if (!(xs instanceof float[])) break block9;
                                    float[] fArray = (float[])xs;
                                    fArray[idx] = BoxesRunTime.unboxToFloat(value);
                                    break block5;
                                }
                                if (!(xs instanceof char[])) break block10;
                                char[] cArray = (char[])xs;
                                cArray[idx] = BoxesRunTime.unboxToChar(value);
                                break block5;
                            }
                            if (!(xs instanceof byte[])) break block11;
                            byte[] byArray = (byte[])xs;
                            byArray[idx] = BoxesRunTime.unboxToByte(value);
                            break block5;
                        }
                        if (!(xs instanceof short[])) break block12;
                        short[] sArray = (short[])xs;
                        sArray[idx] = BoxesRunTime.unboxToShort(value);
                        break block5;
                    }
                    if (!(xs instanceof boolean[])) break block13;
                    boolean[] blArray = (boolean[])xs;
                    blArray[idx] = BoxesRunTime.unboxToBoolean(value);
                    break block5;
                }
                if (!(xs instanceof BoxedUnit[])) break block14;
                BoxedUnit[] boxedUnitArray = (BoxedUnit[])xs;
                boxedUnitArray[idx] = (BoxedUnit)value;
            }
            return;
        }
        if (xs == null) {
            throw new NullPointerException();
        }
        throw new MatchError(xs);
    }

    public int array_length(Object xs) {
        block14: {
            int n;
            block5: {
                block13: {
                    block12: {
                        block11: {
                            block10: {
                                block9: {
                                    block8: {
                                        block7: {
                                            block6: {
                                                block4: {
                                                    if (!(xs instanceof Object[])) break block4;
                                                    Object[] objectArray = (Object[])xs;
                                                    n = objectArray.length;
                                                    break block5;
                                                }
                                                if (!(xs instanceof int[])) break block6;
                                                int[] nArray = (int[])xs;
                                                n = nArray.length;
                                                break block5;
                                            }
                                            if (!(xs instanceof double[])) break block7;
                                            double[] dArray = (double[])xs;
                                            n = dArray.length;
                                            break block5;
                                        }
                                        if (!(xs instanceof long[])) break block8;
                                        long[] lArray = (long[])xs;
                                        n = lArray.length;
                                        break block5;
                                    }
                                    if (!(xs instanceof float[])) break block9;
                                    float[] fArray = (float[])xs;
                                    n = fArray.length;
                                    break block5;
                                }
                                if (!(xs instanceof char[])) break block10;
                                char[] cArray = (char[])xs;
                                n = cArray.length;
                                break block5;
                            }
                            if (!(xs instanceof byte[])) break block11;
                            byte[] byArray = (byte[])xs;
                            n = byArray.length;
                            break block5;
                        }
                        if (!(xs instanceof short[])) break block12;
                        short[] sArray = (short[])xs;
                        n = sArray.length;
                        break block5;
                    }
                    if (!(xs instanceof boolean[])) break block13;
                    boolean[] blArray = (boolean[])xs;
                    n = blArray.length;
                    break block5;
                }
                if (!(xs instanceof BoxedUnit[])) break block14;
                BoxedUnit[] boxedUnitArray = (BoxedUnit[])xs;
                n = boxedUnitArray.length;
            }
            return n;
        }
        if (xs == null) {
            throw new NullPointerException();
        }
        throw new MatchError(xs);
    }

    public Object array_clone(Object xs) {
        block14: {
            Object[] objectArray;
            block5: {
                block13: {
                    block12: {
                        block11: {
                            block10: {
                                block9: {
                                    block8: {
                                        block7: {
                                            block6: {
                                                block4: {
                                                    if (!(xs instanceof Object[])) break block4;
                                                    Object[] objectArray2 = (Object[])xs;
                                                    objectArray = ArrayRuntime.cloneArray(objectArray2);
                                                    break block5;
                                                }
                                                if (!(xs instanceof int[])) break block6;
                                                int[] nArray = (int[])xs;
                                                objectArray = ArrayRuntime.cloneArray(nArray);
                                                break block5;
                                            }
                                            if (!(xs instanceof double[])) break block7;
                                            double[] dArray = (double[])xs;
                                            objectArray = ArrayRuntime.cloneArray(dArray);
                                            break block5;
                                        }
                                        if (!(xs instanceof long[])) break block8;
                                        long[] lArray = (long[])xs;
                                        objectArray = ArrayRuntime.cloneArray(lArray);
                                        break block5;
                                    }
                                    if (!(xs instanceof float[])) break block9;
                                    float[] fArray = (float[])xs;
                                    objectArray = ArrayRuntime.cloneArray(fArray);
                                    break block5;
                                }
                                if (!(xs instanceof char[])) break block10;
                                char[] cArray = (char[])xs;
                                objectArray = ArrayRuntime.cloneArray(cArray);
                                break block5;
                            }
                            if (!(xs instanceof byte[])) break block11;
                            byte[] byArray = (byte[])xs;
                            objectArray = ArrayRuntime.cloneArray(byArray);
                            break block5;
                        }
                        if (!(xs instanceof short[])) break block12;
                        short[] sArray = (short[])xs;
                        objectArray = ArrayRuntime.cloneArray(sArray);
                        break block5;
                    }
                    if (!(xs instanceof boolean[])) break block13;
                    boolean[] blArray = (boolean[])xs;
                    objectArray = ArrayRuntime.cloneArray(blArray);
                    break block5;
                }
                if (!(xs instanceof BoxedUnit[])) break block14;
                BoxedUnit[] boxedUnitArray = (BoxedUnit[])xs;
                objectArray = boxedUnitArray;
            }
            return objectArray;
        }
        if (xs == null) {
            throw new NullPointerException();
        }
        throw new MatchError(xs);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object[] toObjectArray(Object src) {
        if (src instanceof Object[]) {
            Object[] objectArray = (Object[])src;
            return objectArray;
        }
        int length = this.array_length(src);
        Object[] dest = new Object[length];
        Predef$ predef$ = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, length, 1);
        if (range2.isEmpty()) return dest;
        int i1 = range2.start();
        while (true) {
            MODULE$.array_update(dest, i1, MODULE$.array_apply(src, i1));
            if (i1 == range2.lastElement()) {
                return dest;
            }
            i1 += range2.step();
        }
    }

    /*
     * WARNING - void declaration
     */
    public <T> Object[] toArray(Seq<T> xs) {
        void var2_2;
        Object[] arr = new Object[xs.length()];
        IntRef i = IntRef.create(0);
        xs.foreach(new Serializable(arr, i){
            public static final long serialVersionUID = 0L;
            private final Object[] arr$1;
            private final IntRef i$1;

            public final void apply(T x) {
                this.arr$1[this.i$1.elem] = x;
                ++this.i$1.elem;
            }
            {
                this.arr$1 = arr$1;
                this.i$1 = i$1;
            }
        });
        return var2_2;
    }

    public Method ensureAccessible(Method m) {
        return package$.MODULE$.ensureAccessible(m);
    }

    public <T> T checkInitialized(T x) {
        if (x == null) {
            throw new UninitializedError();
        }
        return x;
    }

    public String _toString(Product x) {
        return x.productIterator().mkString(new StringBuilder().append((Object)x.productPrefix()).append((Object)"(").toString(), ",", ")");
    }

    public int _hashCode(Product x) {
        return MurmurHash3$.MODULE$.productHash(x);
    }

    public <T> Iterator<T> typedProductIterator(Product x) {
        return new AbstractIterator<T>(x){
            private int c;
            private final int cmax;
            private final Product x$2;

            private int c() {
                return this.c;
            }

            private void c_$eq(int x$1) {
                this.c = x$1;
            }

            private int cmax() {
                return this.cmax;
            }

            public boolean hasNext() {
                return this.c() < this.cmax();
            }

            /*
             * WARNING - void declaration
             */
            public T next() {
                void var1_1;
                Object result2 = this.x$2.productElement(this.c());
                this.c_$eq(this.c() + 1);
                return var1_1;
            }
            {
                this.x$2 = x$2;
                this.c = 0;
                this.cmax = x$2.productArity();
            }
        };
    }

    public boolean inlinedEquals(Object x, Object y) {
        return x == y ? true : (x == null ? false : (x instanceof Number ? BoxesRunTime.equalsNumObject((Number)x, y) : (x instanceof Character ? BoxesRunTime.equalsCharObject((Character)x, y) : x.equals(y))));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean _equals(Product x, Object y) {
        if (!(y instanceof Product)) return false;
        Product product2 = (Product)y;
        if (x.productArity() != product2.productArity()) return false;
        return x.productIterator().sameElements(product2.productIterator());
    }

    public int hash(Object x) {
        return x == null ? 0 : (x instanceof Number ? BoxesRunTime.hashFromNumber((Number)x) : x.hashCode());
    }

    public int hash(double dv) {
        int iv = (int)dv;
        if ((double)iv == dv) {
            return iv;
        }
        long lv = (long)dv;
        if ((double)lv == dv) {
            return ((Object)BoxesRunTime.boxToLong(lv)).hashCode();
        }
        float fv = (float)dv;
        return (double)fv == dv ? ((Object)BoxesRunTime.boxToFloat(fv)).hashCode() : ((Object)BoxesRunTime.boxToDouble(dv)).hashCode();
    }

    public int hash(float fv) {
        int iv = (int)fv;
        if ((float)iv == fv) {
            return iv;
        }
        long lv = (long)fv;
        return (double)lv == (double)fv ? this.hash(lv) : ((Object)BoxesRunTime.boxToFloat(fv)).hashCode();
    }

    public int hash(long lv) {
        int low = (int)lv;
        int lowSign = low >>> 31;
        int high = (int)(lv >>> 32);
        return low ^ high + lowSign;
    }

    public int hash(Number x) {
        return BoxesRunTime.hashFromNumber(x);
    }

    public int hash(int x) {
        return x;
    }

    public int hash(short x) {
        return x;
    }

    public int hash(byte x) {
        return x;
    }

    public int hash(char x) {
        return x;
    }

    public int hash(boolean x) {
        return x ? ((Object)BoxesRunTime.boxToBoolean(true)).hashCode() : ((Object)BoxesRunTime.boxToBoolean(false)).hashCode();
    }

    public int hash(BoxedUnit x) {
        return 0;
    }

    public boolean sameElements(Seq<Object> xs1, Seq<Object> xs2) {
        return xs1.sameElements(xs2);
    }

    public String stringOf(Object arg) {
        return this.stringOf(arg, Integer.MAX_VALUE);
    }

    public String stringOf(Object arg, int maxElements) {
        Throwable throwable2;
        block2: {
            String string2;
            try {
                string2 = this.scala$runtime$ScalaRunTime$$inner$1(arg, maxElements);
            }
            catch (Throwable throwable2) {
                boolean bl = throwable2 instanceof UnsupportedOperationException ? true : throwable2 instanceof AssertionError;
                if (!bl) break block2;
                string2 = String.valueOf(arg);
            }
            return string2;
        }
        throw throwable2;
    }

    public String replStringOf(Object arg, int maxElements) {
        String s2 = this.stringOf(arg, maxElements);
        String nl = s2.contains("\n") ? "\n" : "";
        return new StringBuilder().append((Object)nl).append((Object)s2).append((Object)"\n").toString();
    }

    public <T> Class<?> box(Class<T> clazz) {
        Class clazz2;
        Class<Byte> clazz3 = Byte.TYPE;
        if (!(clazz3 != null ? !clazz3.equals(clazz) : clazz != null)) {
            clazz2 = Byte.class;
        } else {
            Class<Short> clazz4 = Short.TYPE;
            if (!(clazz4 != null ? !clazz4.equals(clazz) : clazz != null)) {
                clazz2 = Short.class;
            } else {
                Class<Character> clazz5 = Character.TYPE;
                if (!(clazz5 != null ? !clazz5.equals(clazz) : clazz != null)) {
                    clazz2 = Character.class;
                } else {
                    Class<Integer> clazz6 = Integer.TYPE;
                    if (!(clazz6 != null ? !clazz6.equals(clazz) : clazz != null)) {
                        clazz2 = Integer.class;
                    } else {
                        Class<Long> clazz7 = Long.TYPE;
                        if (!(clazz7 != null ? !clazz7.equals(clazz) : clazz != null)) {
                            clazz2 = Long.class;
                        } else {
                            Class<Float> clazz8 = Float.TYPE;
                            if (!(clazz8 != null ? !clazz8.equals(clazz) : clazz != null)) {
                                clazz2 = Float.class;
                            } else {
                                Class<Double> clazz9 = Double.TYPE;
                                if (!(clazz9 != null ? !clazz9.equals(clazz) : clazz != null)) {
                                    clazz2 = Double.class;
                                } else {
                                    Class<Void> clazz10 = Void.TYPE;
                                    if (!(clazz10 != null ? !clazz10.equals(clazz) : clazz != null)) {
                                        clazz2 = BoxedUnit.class;
                                    } else {
                                        Class<Boolean> clazz11 = Boolean.TYPE;
                                        clazz2 = !(clazz11 != null ? !clazz11.equals(clazz) : clazz != null) ? Boolean.class : clazz;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return clazz2;
    }

    private final String packageOf$1(Object x) {
        Package package_ = x.getClass().getPackage();
        String string2 = package_ == null ? "" : package_.getName();
        return string2;
    }

    private final boolean isScalaClass$1(Object x) {
        return this.packageOf$1(x).startsWith("scala.");
    }

    private final boolean isScalaCompilerClass$1(Object x) {
        return this.packageOf$1(x).startsWith("scala.tools.nsc.");
    }

    private final boolean isSubClassOf$1(Class potentialSubClass, String ofClass) {
        boolean bl;
        try {
            ClassLoader classLoader = potentialSubClass.getClassLoader();
            Class<?> clazz = Class.forName(ofClass, false, classLoader);
            bl = clazz.isAssignableFrom(potentialSubClass);
        }
        catch (ClassNotFoundException classNotFoundException) {
            bl = false;
        }
        return bl;
    }

    private final boolean isXmlNode$1(Class potentialSubClass) {
        return this.isSubClassOf$1(potentialSubClass, "scala.xml.Node");
    }

    private final boolean isXmlMetaData$1(Class potentialSubClass) {
        return this.isSubClassOf$1(potentialSubClass, "scala.xml.MetaData");
    }

    private final boolean useOwnToString$1(Object x) {
        Traversable traversable;
        boolean bl = x instanceof Range ? true : x instanceof NumericRange;
        boolean bl2 = bl ? true : (x instanceof Sorted ? true : (x instanceof StringLike ? true : (x instanceof TraversableView ? true : (x instanceof Traversable ? !(traversable = (Traversable)x).hasDefiniteSize() || !this.isScalaClass$1(traversable) || this.isScalaCompilerClass$1(traversable) || this.isXmlNode$1(traversable.getClass()) || this.isXmlMetaData$1(traversable.getClass()) : false))));
        return bl2;
    }

    public final String scala$runtime$ScalaRunTime$$mapInner$1(Object arg, int maxElements$1) {
        String string2;
        if (arg instanceof Tuple2) {
            Tuple2 tuple2 = (Tuple2)arg;
            string2 = new StringBuilder().append((Object)this.scala$runtime$ScalaRunTime$$inner$1(tuple2._1(), maxElements$1)).append((Object)" -> ").append((Object)this.scala$runtime$ScalaRunTime$$inner$1(tuple2._2(), maxElements$1)).toString();
        } else {
            string2 = this.scala$runtime$ScalaRunTime$$inner$1(arg, maxElements$1);
        }
        return string2;
    }

    private final String arrayToString$1(Object x, int maxElements$1) {
        String string2;
        Class<?> clazz = x.getClass().getComponentType();
        if (clazz != null && clazz.equals(BoxedUnit.class)) {
            Predef$ predef$ = Predef$.MODULE$;
            int n = this.array_length(x);
            Predef$ predef$2 = Predef$.MODULE$;
            string2 = ((TraversableOnce)RichInt$.MODULE$.until$extension0(0, RichInt$.MODULE$.min$extension(n, maxElements$1)).map(new Serializable(){
                public static final long serialVersionUID = 0L;

                public final String apply(int x$1) {
                    return "()";
                }
            }, IndexedSeq$.MODULE$.canBuildFrom())).mkString("Array(", ", ", ")");
        } else {
            string2 = ((TraversableOnce)((TraversableLike)WrappedArray$.MODULE$.make(x).take(maxElements$1)).map(new Serializable(maxElements$1){
                public static final long serialVersionUID = 0L;
                private final int maxElements$1;

                public final String apply(Object arg) {
                    return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
                }
                {
                    this.maxElements$1 = maxElements$1;
                }
            }, WrappedArray$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString("Array(", ", ", ")");
        }
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    public final String scala$runtime$ScalaRunTime$$inner$1(Object arg, int maxElements$1) {
        block13: {
            block15: {
                block14: {
                    block12: {
                        if (arg != null) break block12;
                        var15_3 = "null";
                        break block13;
                    }
                    if (!"".equals(arg)) break block14;
                    var15_3 = "\"\"";
                    break block13;
                }
                if (!(arg instanceof String)) break block15;
                var9_4 = (String)arg;
                var3_5 = Predef$.MODULE$;
                var5_6 = BoxesRunTime.unboxToChar(new StringOps(var9_4).head());
                var4_7 = Predef$.MODULE$;
                if (RichChar$.MODULE$.isWhitespace$extension(var5_6)) ** GOTO lbl-1000
                var6_8 = Predef$.MODULE$;
                var8_9 = BoxesRunTime.unboxToChar(new StringOps(var9_4).last());
                var7_10 = Predef$.MODULE$;
                if (RichChar$.MODULE$.isWhitespace$extension(var8_9)) lbl-1000:
                // 2 sources

                {
                    v0 = new StringBuilder().append((Object)"\"").append((Object)var9_4).append((Object)"\"").toString();
                } else {
                    v0 = var9_4;
                }
                var15_3 = v0;
                break block13;
            }
            if (this.useOwnToString$1(arg)) {
                var15_3 = arg.toString();
            } else if (arg instanceof Object && this.isArray(arg, this.isArray$default$2())) {
                var15_3 = this.arrayToString$1(arg, maxElements$1);
            } else if (arg instanceof Map) {
                var10_11 = (Map)arg;
                var15_3 = var10_11.iterator().take(maxElements$1).map(new Serializable(maxElements$1){
                    public static final long serialVersionUID = 0L;
                    private final int maxElements$1;

                    public final String apply(Object arg) {
                        return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$mapInner$1(arg, this.maxElements$1);
                    }
                    {
                        this.maxElements$1 = maxElements$1;
                    }
                }).mkString(new StringBuilder().append((Object)var10_11.stringPrefix()).append((Object)"(").toString(), ", ", ")");
            } else if (arg instanceof GenIterable) {
                var11_12 = (GenIterable)arg;
                var15_3 = var11_12.iterator().take(maxElements$1).map(new Serializable(maxElements$1){
                    public static final long serialVersionUID = 0L;
                    private final int maxElements$1;

                    public final String apply(Object arg) {
                        return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
                    }
                    {
                        this.maxElements$1 = maxElements$1;
                    }
                }).mkString(new StringBuilder().append((Object)var11_12.stringPrefix()).append((Object)"(").toString(), ", ", ")");
            } else if (arg instanceof Traversable) {
                var12_13 = (Traversable)arg;
                var15_3 = ((TraversableOnce)((TraversableLike)var12_13.take(maxElements$1)).map(new Serializable(maxElements$1){
                    public static final long serialVersionUID = 0L;
                    private final int maxElements$1;

                    public final String apply(Object arg) {
                        return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
                    }
                    {
                        this.maxElements$1 = maxElements$1;
                    }
                }, Traversable$.MODULE$.canBuildFrom())).mkString(new StringBuilder().append((Object)var12_13.stringPrefix()).append((Object)"(").toString(), ", ", ")");
            } else {
                var15_3 = arg instanceof Product1 != false && this.isTuple(var13_14 = (Product1)arg) != false ? new StringBuilder().append((Object)"(").append((Object)this.scala$runtime$ScalaRunTime$$inner$1(var13_14._1(), maxElements$1)).append((Object)",)").toString() : (arg instanceof Product != false && this.isTuple(var14_15 = (Product)arg) != false ? var14_15.productIterator().map(new Serializable(maxElements$1){
                    public static final long serialVersionUID = 0L;
                    private final int maxElements$1;

                    public final String apply(Object arg) {
                        return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
                    }
                    {
                        this.maxElements$1 = maxElements$1;
                    }
                }).mkString("(", ",", ")") : arg.toString());
            }
        }
        return var15_3;
    }

    private ScalaRunTime$() {
        MODULE$ = this;
    }
}

