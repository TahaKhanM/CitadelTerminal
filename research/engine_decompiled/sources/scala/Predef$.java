/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Console$;
import scala.DeprecatedPredef;
import scala.DeprecatedPredef$class;
import scala.Function0;
import scala.LowPriorityImplicits;
import scala.MatchError;
import scala.NotImplementedError;
import scala.Predef;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeq;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.List;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Set$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.StringBuilder$;
import scala.reflect.ClassManifestFactory$;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.ManifestFactory$;
import scala.reflect.NoManifest$;
import scala.reflect.OptManifest;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class Predef$
extends LowPriorityImplicits
implements DeprecatedPredef {
    public static final Predef$ MODULE$;
    private final Map$ Map;
    private final Set$ Set;
    private final ClassManifestFactory$ ClassManifest;
    private final ManifestFactory$ Manifest;
    private final NoManifest$ NoManifest;
    private final CanBuildFrom<String, Object, String> StringCanBuildFrom;
    private final less.colon.less<Object, Object> singleton_$less$colon$less;
    public final eq.colon.eq<Object, Object> scala$Predef$$singleton_$eq$colon$eq;

    static {
        new Predef$();
    }

    @Override
    public <A> A any2ArrowAssoc(A x) {
        return (A)DeprecatedPredef$class.any2ArrowAssoc(this, x);
    }

    @Override
    public <A> A any2Ensuring(A x) {
        return (A)DeprecatedPredef$class.any2Ensuring(this, x);
    }

    @Override
    public Object any2stringfmt(Object x) {
        return DeprecatedPredef$class.any2stringfmt(this, x);
    }

    @Override
    public Throwable exceptionWrapper(Throwable exc) {
        return DeprecatedPredef$class.exceptionWrapper(this, exc);
    }

    @Override
    public CharSequence seqToCharSequence(IndexedSeq<Object> xs) {
        return DeprecatedPredef$class.seqToCharSequence(this, xs);
    }

    @Override
    public CharSequence arrayToCharSequence(char[] xs) {
        return DeprecatedPredef$class.arrayToCharSequence(this, xs);
    }

    @Override
    public String readLine() {
        return DeprecatedPredef$class.readLine(this);
    }

    @Override
    public String readLine(String text, Seq<Object> args) {
        return DeprecatedPredef$class.readLine(this, text, args);
    }

    @Override
    public boolean readBoolean() {
        return DeprecatedPredef$class.readBoolean(this);
    }

    @Override
    public byte readByte() {
        return DeprecatedPredef$class.readByte(this);
    }

    @Override
    public short readShort() {
        return DeprecatedPredef$class.readShort(this);
    }

    @Override
    public char readChar() {
        return DeprecatedPredef$class.readChar(this);
    }

    @Override
    public int readInt() {
        return DeprecatedPredef$class.readInt(this);
    }

    @Override
    public long readLong() {
        return DeprecatedPredef$class.readLong(this);
    }

    @Override
    public float readFloat() {
        return DeprecatedPredef$class.readFloat(this);
    }

    @Override
    public double readDouble() {
        return DeprecatedPredef$class.readDouble(this);
    }

    @Override
    public List<Object> readf(String format2) {
        return DeprecatedPredef$class.readf(this, format2);
    }

    @Override
    public Object readf1(String format2) {
        return DeprecatedPredef$class.readf1(this, format2);
    }

    @Override
    public Tuple2<Object, Object> readf2(String format2) {
        return DeprecatedPredef$class.readf2(this, format2);
    }

    @Override
    public Tuple3<Object, Object, Object> readf3(String format2) {
        return DeprecatedPredef$class.readf3(this, format2);
    }

    public <T> Class<T> classOf() {
        return null;
    }

    public Map$ Map() {
        return this.Map;
    }

    public Set$ Set() {
        return this.Set;
    }

    public ClassManifestFactory$ ClassManifest() {
        return this.ClassManifest;
    }

    public ManifestFactory$ Manifest() {
        return this.Manifest;
    }

    public NoManifest$ NoManifest() {
        return this.NoManifest;
    }

    public <T> Manifest<T> manifest(Manifest<T> m) {
        return m;
    }

    public <T> ClassTag<T> classManifest(ClassTag<T> m) {
        return m;
    }

    public <T> OptManifest<T> optManifest(OptManifest<T> m) {
        return m;
    }

    public <A> A identity(A x) {
        return x;
    }

    public <T> T implicitly(T e) {
        return e;
    }

    public <T> T locally(T x) {
        return x;
    }

    public Nothing$ error(String message) {
        return scala.sys.package$.MODULE$.error(message);
    }

    public void assert(boolean assertion) {
        if (assertion) {
            return;
        }
        throw new AssertionError((Object)"assertion failed");
    }

    public final void assert(boolean assertion, Function0<Object> message) {
        if (assertion) {
            return;
        }
        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(message.apply()).toString());
    }

    public void assume(boolean assumption) {
        if (assumption) {
            return;
        }
        throw new AssertionError((Object)"assumption failed");
    }

    public final void assume(boolean assumption, Function0<Object> message) {
        if (assumption) {
            return;
        }
        throw new AssertionError((Object)new StringBuilder().append((Object)"assumption failed: ").append(message.apply()).toString());
    }

    public void require(boolean requirement) {
        if (requirement) {
            return;
        }
        throw new IllegalArgumentException("requirement failed");
    }

    public final void require(boolean requirement, Function0<Object> message) {
        if (requirement) {
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(message.apply()).toString());
    }

    public Nothing$ $qmark$qmark$qmark() {
        throw new NotImplementedError();
    }

    public <A> A ArrowAssoc(A self) {
        return self;
    }

    public <A> A Ensuring(A self) {
        return self;
    }

    public <A> A StringFormat(A self) {
        return self;
    }

    public Object StringAdd(Object x) {
        return new Predef.StringAdd<Object>(x);
    }

    public <A> A any2stringadd(A self) {
        return self;
    }

    public Throwable RichException(Throwable self) {
        return self;
    }

    public Predef.SeqCharSequence SeqCharSequence(IndexedSeq<Object> __sequenceOfChars) {
        return new Predef.SeqCharSequence(__sequenceOfChars);
    }

    public Predef.ArrayCharSequence ArrayCharSequence(char[] __arrayOfChars) {
        return new Predef.ArrayCharSequence(__arrayOfChars);
    }

    public CanBuildFrom<String, Object, String> StringCanBuildFrom() {
        return this.StringCanBuildFrom;
    }

    public String augmentString(String x) {
        return x;
    }

    public String unaugmentString(String x) {
        return x;
    }

    public void print(Object x) {
        Console$.MODULE$.print(x);
    }

    public void println() {
        Console$.MODULE$.println();
    }

    public void println(Object x) {
        Console$.MODULE$.println(x);
    }

    public void printf(String text, Seq<Object> xs) {
        Console$.MODULE$.print(new StringOps(text).format(xs));
    }

    public <T1, T2> Tuple2<T1, T2> tuple2ToZippedOps(Tuple2<T1, T2> x) {
        return x;
    }

    public <T1, T2, T3> Tuple3<T1, T2, T3> tuple3ToZippedOps(Tuple3<T1, T2, T3> x) {
        return x;
    }

    public <T> ArrayOps<T> genericArrayOps(Object xs) {
        block13: {
            ArrayOps<Object> arrayOps;
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
                                                        if (!(xs instanceof Object[])) break block2;
                                                        Object[] objectArray = (Object[])xs;
                                                        arrayOps = this.refArrayOps(objectArray);
                                                        break block3;
                                                    }
                                                    if (!(xs instanceof boolean[])) break block4;
                                                    boolean[] blArray = (boolean[])xs;
                                                    arrayOps = this.booleanArrayOps(blArray);
                                                    break block3;
                                                }
                                                if (!(xs instanceof byte[])) break block5;
                                                byte[] byArray = (byte[])xs;
                                                arrayOps = this.byteArrayOps(byArray);
                                                break block3;
                                            }
                                            if (!(xs instanceof char[])) break block6;
                                            char[] cArray = (char[])xs;
                                            arrayOps = this.charArrayOps(cArray);
                                            break block3;
                                        }
                                        if (!(xs instanceof double[])) break block7;
                                        double[] dArray = (double[])xs;
                                        arrayOps = this.doubleArrayOps(dArray);
                                        break block3;
                                    }
                                    if (!(xs instanceof float[])) break block8;
                                    float[] fArray = (float[])xs;
                                    arrayOps = this.floatArrayOps(fArray);
                                    break block3;
                                }
                                if (!(xs instanceof int[])) break block9;
                                int[] nArray = (int[])xs;
                                arrayOps = this.intArrayOps(nArray);
                                break block3;
                            }
                            if (!(xs instanceof long[])) break block10;
                            long[] lArray = (long[])xs;
                            arrayOps = this.longArrayOps(lArray);
                            break block3;
                        }
                        if (!(xs instanceof short[])) break block11;
                        short[] sArray = (short[])xs;
                        arrayOps = this.shortArrayOps(sArray);
                        break block3;
                    }
                    if (!(xs instanceof BoxedUnit[])) break block12;
                    BoxedUnit[] boxedUnitArray = (BoxedUnit[])xs;
                    arrayOps = this.unitArrayOps(boxedUnitArray);
                    break block3;
                }
                if (xs != null) break block13;
                arrayOps = null;
            }
            return arrayOps;
        }
        throw new MatchError(xs);
    }

    public ArrayOps<Object> booleanArrayOps(boolean[] xs) {
        return new ArrayOps.ofBoolean(xs);
    }

    public ArrayOps<Object> byteArrayOps(byte[] xs) {
        return new ArrayOps.ofByte(xs);
    }

    public ArrayOps<Object> charArrayOps(char[] xs) {
        return new ArrayOps.ofChar(xs);
    }

    public ArrayOps<Object> doubleArrayOps(double[] xs) {
        return new ArrayOps.ofDouble(xs);
    }

    public ArrayOps<Object> floatArrayOps(float[] xs) {
        return new ArrayOps.ofFloat(xs);
    }

    public ArrayOps<Object> intArrayOps(int[] xs) {
        return new ArrayOps.ofInt(xs);
    }

    public ArrayOps<Object> longArrayOps(long[] xs) {
        return new ArrayOps.ofLong(xs);
    }

    public <T> ArrayOps<T> refArrayOps(T[] xs) {
        return new ArrayOps.ofRef<T>(xs);
    }

    public ArrayOps<Object> shortArrayOps(short[] xs) {
        return new ArrayOps.ofShort(xs);
    }

    public ArrayOps<BoxedUnit> unitArrayOps(BoxedUnit[] xs) {
        return new ArrayOps.ofUnit(xs);
    }

    public Byte byte2Byte(byte x) {
        return x;
    }

    public Short short2Short(short x) {
        return x;
    }

    public Character char2Character(char x) {
        return Character.valueOf(x);
    }

    public Integer int2Integer(int x) {
        return x;
    }

    public Long long2Long(long x) {
        return x;
    }

    public Float float2Float(float x) {
        return Float.valueOf(x);
    }

    public Double double2Double(double x) {
        return x;
    }

    public Boolean boolean2Boolean(boolean x) {
        return x;
    }

    public byte Byte2byte(Byte x) {
        return x;
    }

    public short Short2short(Short x) {
        return x;
    }

    public char Character2char(Character x) {
        return x.charValue();
    }

    public int Integer2int(Integer x) {
        return x;
    }

    public long Long2long(Long x) {
        return x;
    }

    public float Float2float(Float x) {
        return x.floatValue();
    }

    public double Double2double(Double x) {
        return x;
    }

    public boolean Boolean2boolean(Boolean x) {
        return x;
    }

    public <A> less.colon.less<A, A> $conforms() {
        return this.singleton_$less$colon$less;
    }

    public <A> less.colon.less<A, A> conforms() {
        return this.$conforms();
    }

    private Predef$() {
        MODULE$ = this;
        DeprecatedPredef$class.$init$(this);
        this.Map = Map$.MODULE$;
        this.Set = Set$.MODULE$;
        this.ClassManifest = package$.MODULE$.ClassManifest();
        this.Manifest = package$.MODULE$.Manifest();
        this.NoManifest = NoManifest$.MODULE$;
        this.StringCanBuildFrom = new CanBuildFrom<String, Object, String>(){

            public StringBuilder apply(String from2) {
                return this.apply();
            }

            public StringBuilder apply() {
                return StringBuilder$.MODULE$.newBuilder();
            }
        };
        this.singleton_$less$colon$less = new less.colon.less<Object, Object>(){

            public Object apply(Object x) {
                return x;
            }
        };
        this.scala$Predef$$singleton_$eq$colon$eq = new eq.colon.eq<Object, Object>(){

            public Object apply(Object x) {
                return x;
            }
        };
    }
}

