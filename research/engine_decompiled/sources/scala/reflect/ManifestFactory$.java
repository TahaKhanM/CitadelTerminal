/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.AnyValManifest;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.Manifest;
import scala.reflect.Manifest$class;
import scala.reflect.ManifestFactory;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

public final class ManifestFactory$ {
    public static final ManifestFactory$ MODULE$;
    private final AnyValManifest<Object> Byte;
    private final AnyValManifest<Object> Short;
    private final AnyValManifest<Object> Char;
    private final AnyValManifest<Object> Int;
    private final AnyValManifest<Object> Long;
    private final AnyValManifest<Object> Float;
    private final AnyValManifest<Object> Double;
    private final AnyValManifest<Object> Boolean;
    private final AnyValManifest<BoxedUnit> Unit;
    private final Class<Object> scala$reflect$ManifestFactory$$ObjectTYPE;
    private final Class<Nothing$> scala$reflect$ManifestFactory$$NothingTYPE;
    private final Class<Null$> scala$reflect$ManifestFactory$$NullTYPE;
    private final Manifest<Object> Any;
    private final Manifest<Object> Object;
    private final Manifest<Object> AnyRef;
    private final Manifest<Object> AnyVal;
    private final Manifest<Null$> Null;
    private final Manifest<Nothing$> Nothing;

    static {
        new ManifestFactory$();
    }

    public List<AnyValManifest<?>> valueManifests() {
        return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new AnyValManifest[]{this.Byte(), this.Short(), this.Char(), this.Int(), this.Long(), this.Float(), this.Double(), this.Boolean(), this.Unit()}));
    }

    public AnyValManifest<Object> Byte() {
        return this.Byte;
    }

    public AnyValManifest<Object> Short() {
        return this.Short;
    }

    public AnyValManifest<Object> Char() {
        return this.Char;
    }

    public AnyValManifest<Object> Int() {
        return this.Int;
    }

    public AnyValManifest<Object> Long() {
        return this.Long;
    }

    public AnyValManifest<Object> Float() {
        return this.Float;
    }

    public AnyValManifest<Object> Double() {
        return this.Double;
    }

    public AnyValManifest<Object> Boolean() {
        return this.Boolean;
    }

    public AnyValManifest<BoxedUnit> Unit() {
        return this.Unit;
    }

    public Class<Object> scala$reflect$ManifestFactory$$ObjectTYPE() {
        return this.scala$reflect$ManifestFactory$$ObjectTYPE;
    }

    public Class<Nothing$> scala$reflect$ManifestFactory$$NothingTYPE() {
        return this.scala$reflect$ManifestFactory$$NothingTYPE;
    }

    public Class<Null$> scala$reflect$ManifestFactory$$NullTYPE() {
        return this.scala$reflect$ManifestFactory$$NullTYPE;
    }

    public Manifest<Object> Any() {
        return this.Any;
    }

    public Manifest<Object> Object() {
        return this.Object;
    }

    public Manifest<Object> AnyRef() {
        return this.AnyRef;
    }

    public Manifest<Object> AnyVal() {
        return this.AnyVal;
    }

    public Manifest<Null$> Null() {
        return this.Null;
    }

    public Manifest<Nothing$> Nothing() {
        return this.Nothing;
    }

    public <T> Manifest<T> singleType(Object value) {
        return new ManifestFactory.SingletonTypeManifest(value);
    }

    public <T> Manifest<T> classType(Class<?> clazz) {
        return new ManifestFactory.ClassTypeManifest(None$.MODULE$, clazz, Nil$.MODULE$);
    }

    public <T> Manifest<T> classType(Class<T> clazz, Manifest<?> arg1, Seq<Manifest<?>> args) {
        return new ManifestFactory.ClassTypeManifest(None$.MODULE$, clazz, args.toList().$colon$colon(arg1));
    }

    public <T> Manifest<T> classType(Manifest<?> prefix, Class<?> clazz, Seq<Manifest<?>> args) {
        return new ManifestFactory.ClassTypeManifest(new Some(prefix), clazz, args.toList());
    }

    public <T> Manifest<Object> arrayType(Manifest<?> arg) {
        return arg.arrayManifest();
    }

    public <T> Manifest<T> abstractType(Manifest<?> prefix, String name, Class<?> upperBound, Seq<Manifest<?>> args) {
        return new Manifest<T>(prefix, name, upperBound, args){
            private final List<Manifest<?>> typeArguments;
            private final Manifest prefix$1;
            private final String name$1;
            private final Class upperBound$1;

            public Manifest<Object> arrayManifest() {
                return Manifest$class.arrayManifest(this);
            }

            public boolean canEqual(Object that) {
                return Manifest$class.canEqual(this, that);
            }

            public boolean equals(Object that) {
                return Manifest$class.equals(this, that);
            }

            public int hashCode() {
                return Manifest$class.hashCode(this);
            }

            public ClassTag<Object> wrap() {
                return ClassTag$class.wrap(this);
            }

            public Object newArray(int len) {
                return ClassTag$class.newArray(this, len);
            }

            public Option<T> unapply(Object x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(byte x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(short x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(char x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(int x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(long x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(float x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(double x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(boolean x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(BoxedUnit x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Class<?> erasure() {
                return ClassManifestDeprecatedApis$class.erasure(this);
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
            }

            public boolean $greater$colon$greater(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
            }

            public <T> Class<Object> arrayClass(Class<?> tp) {
                return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
            }

            public Object[] newArray2(int len) {
                return ClassManifestDeprecatedApis$class.newArray2(this, len);
            }

            public Object[][] newArray3(int len) {
                return ClassManifestDeprecatedApis$class.newArray3(this, len);
            }

            public Object[][][] newArray4(int len) {
                return ClassManifestDeprecatedApis$class.newArray4(this, len);
            }

            public Object[][][][] newArray5(int len) {
                return ClassManifestDeprecatedApis$class.newArray5(this, len);
            }

            public WrappedArray<T> newWrappedArray(int len) {
                return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
            }

            public ArrayBuilder<T> newArrayBuilder() {
                return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
            }

            public String argString() {
                return ClassManifestDeprecatedApis$class.argString(this);
            }

            public Class<?> runtimeClass() {
                return this.upperBound$1;
            }

            public List<Manifest<?>> typeArguments() {
                return this.typeArguments;
            }

            public String toString() {
                return new StringBuilder().append((Object)this.prefix$1.toString()).append((Object)"#").append((Object)this.name$1).append((Object)this.argString()).toString();
            }
            {
                this.prefix$1 = prefix$1;
                this.name$1 = name$1;
                this.upperBound$1 = upperBound$1;
                ClassManifestDeprecatedApis$class.$init$(this);
                ClassTag$class.$init$(this);
                Manifest$class.$init$(this);
                this.typeArguments = args$1.toList();
            }
        };
    }

    public <T> Manifest<T> wildcardType(Manifest<?> lowerBound, Manifest<?> upperBound) {
        return new Manifest<T>(lowerBound, upperBound){
            private final Manifest lowerBound$1;
            private final Manifest upperBound$2;

            public List<Manifest<?>> typeArguments() {
                return Manifest$class.typeArguments(this);
            }

            public Manifest<Object> arrayManifest() {
                return Manifest$class.arrayManifest(this);
            }

            public boolean canEqual(Object that) {
                return Manifest$class.canEqual(this, that);
            }

            public boolean equals(Object that) {
                return Manifest$class.equals(this, that);
            }

            public int hashCode() {
                return Manifest$class.hashCode(this);
            }

            public ClassTag<Object> wrap() {
                return ClassTag$class.wrap(this);
            }

            public Object newArray(int len) {
                return ClassTag$class.newArray(this, len);
            }

            public Option<T> unapply(Object x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(byte x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(short x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(char x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(int x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(long x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(float x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(double x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(boolean x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(BoxedUnit x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Class<?> erasure() {
                return ClassManifestDeprecatedApis$class.erasure(this);
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
            }

            public boolean $greater$colon$greater(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
            }

            public <T> Class<Object> arrayClass(Class<?> tp) {
                return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
            }

            public Object[] newArray2(int len) {
                return ClassManifestDeprecatedApis$class.newArray2(this, len);
            }

            public Object[][] newArray3(int len) {
                return ClassManifestDeprecatedApis$class.newArray3(this, len);
            }

            public Object[][][] newArray4(int len) {
                return ClassManifestDeprecatedApis$class.newArray4(this, len);
            }

            public Object[][][][] newArray5(int len) {
                return ClassManifestDeprecatedApis$class.newArray5(this, len);
            }

            public WrappedArray<T> newWrappedArray(int len) {
                return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
            }

            public ArrayBuilder<T> newArrayBuilder() {
                return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
            }

            public String argString() {
                return ClassManifestDeprecatedApis$class.argString(this);
            }

            public Class<?> runtimeClass() {
                return this.upperBound$2.runtimeClass();
            }

            public String toString() {
                return new StringBuilder().append((Object)"_").append((Object)(this.lowerBound$1 == ManifestFactory$.MODULE$.Nothing() ? "" : new StringBuilder().append((Object)" >: ").append(this.lowerBound$1).toString())).append((Object)(this.upperBound$2 == ManifestFactory$.MODULE$.Nothing() ? "" : new StringBuilder().append((Object)" <: ").append(this.upperBound$2).toString())).toString();
            }
            {
                this.lowerBound$1 = lowerBound$1;
                this.upperBound$2 = upperBound$2;
                ClassManifestDeprecatedApis$class.$init$(this);
                ClassTag$class.$init$(this);
                Manifest$class.$init$(this);
            }
        };
    }

    public <T> Manifest<T> intersectionType(Seq<Manifest<?>> parents2) {
        return new Manifest<T>(parents2){
            private final Seq parents$1;

            public List<Manifest<?>> typeArguments() {
                return Manifest$class.typeArguments(this);
            }

            public Manifest<Object> arrayManifest() {
                return Manifest$class.arrayManifest(this);
            }

            public boolean canEqual(Object that) {
                return Manifest$class.canEqual(this, that);
            }

            public boolean equals(Object that) {
                return Manifest$class.equals(this, that);
            }

            public int hashCode() {
                return Manifest$class.hashCode(this);
            }

            public ClassTag<Object> wrap() {
                return ClassTag$class.wrap(this);
            }

            public Object newArray(int len) {
                return ClassTag$class.newArray(this, len);
            }

            public Option<T> unapply(Object x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(byte x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(short x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(char x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(int x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(long x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(float x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(double x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(boolean x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Option<T> unapply(BoxedUnit x) {
                return ClassTag$class.unapply((ClassTag)this, x);
            }

            public Class<?> erasure() {
                return ClassManifestDeprecatedApis$class.erasure(this);
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
            }

            public boolean $greater$colon$greater(ClassTag<?> that) {
                return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
            }

            public <T> Class<Object> arrayClass(Class<?> tp) {
                return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
            }

            public Object[] newArray2(int len) {
                return ClassManifestDeprecatedApis$class.newArray2(this, len);
            }

            public Object[][] newArray3(int len) {
                return ClassManifestDeprecatedApis$class.newArray3(this, len);
            }

            public Object[][][] newArray4(int len) {
                return ClassManifestDeprecatedApis$class.newArray4(this, len);
            }

            public Object[][][][] newArray5(int len) {
                return ClassManifestDeprecatedApis$class.newArray5(this, len);
            }

            public WrappedArray<T> newWrappedArray(int len) {
                return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
            }

            public ArrayBuilder<T> newArrayBuilder() {
                return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
            }

            public String argString() {
                return ClassManifestDeprecatedApis$class.argString(this);
            }

            public Class<?> runtimeClass() {
                return ((ClassTag)this.parents$1.head()).runtimeClass();
            }

            public String toString() {
                return this.parents$1.mkString(" with ");
            }
            {
                this.parents$1 = parents$1;
                ClassManifestDeprecatedApis$class.$init$(this);
                ClassTag$class.$init$(this);
                Manifest$class.$init$(this);
            }
        };
    }

    private ManifestFactory$() {
        MODULE$ = this;
        this.Byte = new AnyValManifest<Object>(){

            public Class<Byte> runtimeClass() {
                return java.lang.Byte.TYPE;
            }

            public byte[] newArray(int len) {
                return new byte[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofByte(new byte[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofByte();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Byte();
            }
        };
        this.Short = new AnyValManifest<Object>(){

            public Class<Short> runtimeClass() {
                return java.lang.Short.TYPE;
            }

            public short[] newArray(int len) {
                return new short[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofShort(new short[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofShort();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Short();
            }
        };
        this.Char = new AnyValManifest<Object>(){

            public Class<Character> runtimeClass() {
                return Character.TYPE;
            }

            public char[] newArray(int len) {
                return new char[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofChar(new char[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofChar();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Char();
            }
        };
        this.Int = new AnyValManifest<Object>(){

            public Class<Integer> runtimeClass() {
                return Integer.TYPE;
            }

            public int[] newArray(int len) {
                return new int[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofInt(new int[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofInt();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Int();
            }
        };
        this.Long = new AnyValManifest<Object>(){

            public Class<Long> runtimeClass() {
                return java.lang.Long.TYPE;
            }

            public long[] newArray(int len) {
                return new long[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofLong(new long[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofLong();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Long();
            }
        };
        this.Float = new AnyValManifest<Object>(){

            public Class<Float> runtimeClass() {
                return java.lang.Float.TYPE;
            }

            public float[] newArray(int len) {
                return new float[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofFloat(new float[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofFloat();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Float();
            }
        };
        this.Double = new AnyValManifest<Object>(){

            public Class<Double> runtimeClass() {
                return java.lang.Double.TYPE;
            }

            public double[] newArray(int len) {
                return new double[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofDouble(new double[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofDouble();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Double();
            }
        };
        this.Boolean = new AnyValManifest<Object>(){

            public Class<Boolean> runtimeClass() {
                return java.lang.Boolean.TYPE;
            }

            public boolean[] newArray(int len) {
                return new boolean[len];
            }

            public WrappedArray<Object> newWrappedArray(int len) {
                return new WrappedArray.ofBoolean(new boolean[len]);
            }

            public ArrayBuilder<Object> newArrayBuilder() {
                return new ArrayBuilder.ofBoolean();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Boolean();
            }
        };
        this.Unit = new AnyValManifest<BoxedUnit>(){

            public Class<Void> runtimeClass() {
                return Void.TYPE;
            }

            public BoxedUnit[] newArray(int len) {
                return new BoxedUnit[len];
            }

            public WrappedArray<BoxedUnit> newWrappedArray(int len) {
                return new WrappedArray.ofUnit(new BoxedUnit[len]);
            }

            public ArrayBuilder<BoxedUnit> newArrayBuilder() {
                return new ArrayBuilder.ofUnit();
            }

            public <T> Class<Object> arrayClass(Class<?> tp) {
                return tp == this.runtimeClass() ? BoxedUnit[].class : ClassManifestDeprecatedApis$class.arrayClass(this, tp);
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Unit();
            }
        };
        this.scala$reflect$ManifestFactory$$ObjectTYPE = Object.class;
        this.scala$reflect$ManifestFactory$$NothingTYPE = Nothing$.class;
        this.scala$reflect$ManifestFactory$$NullTYPE = Null$.class;
        this.Any = new ManifestFactory.PhantomManifest<Object>(){

            public Object[] newArray(int len) {
                return new Object[len];
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return that == this;
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Any();
            }
        };
        this.Object = new ManifestFactory.PhantomManifest<Object>(){

            public Object[] newArray(int len) {
                return new Object[len];
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return that == this || that == ManifestFactory$.MODULE$.Any();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Object();
            }
        };
        this.AnyRef = this.Object();
        this.AnyVal = new ManifestFactory.PhantomManifest<Object>(){

            public Object[] newArray(int len) {
                return new Object[len];
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return that == this || that == ManifestFactory$.MODULE$.Any();
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().AnyVal();
            }
        };
        this.Null = new ManifestFactory.PhantomManifest<Null$>(){

            public Object[] newArray(int len) {
                return new Object[len];
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return that != null && that != ManifestFactory$.MODULE$.Nothing() && !that.$less$colon$less(ManifestFactory$.MODULE$.AnyVal());
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Null();
            }
        };
        this.Nothing = new ManifestFactory.PhantomManifest<Nothing$>(){

            public Object[] newArray(int len) {
                return new Object[len];
            }

            public boolean $less$colon$less(ClassTag<?> that) {
                return that != null;
            }

            private Object readResolve() {
                return package$.MODULE$.Manifest().Nothing();
            }
        };
    }
}

