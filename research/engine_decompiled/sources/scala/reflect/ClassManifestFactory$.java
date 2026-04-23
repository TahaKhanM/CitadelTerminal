/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.AnyValManifest;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.ClassTypeManifest;
import scala.reflect.Manifest;
import scala.reflect.ManifestFactory$;
import scala.reflect.NoManifest$;
import scala.reflect.OptManifest;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

public final class ClassManifestFactory$ {
    public static final ClassManifestFactory$ MODULE$;
    private final AnyValManifest<Object> Byte;
    private final AnyValManifest<Object> Short;
    private final AnyValManifest<Object> Char;
    private final AnyValManifest<Object> Int;
    private final AnyValManifest<Object> Long;
    private final AnyValManifest<Object> Float;
    private final AnyValManifest<Object> Double;
    private final AnyValManifest<Object> Boolean;
    private final AnyValManifest<BoxedUnit> Unit;
    private final Manifest<Object> Any;
    private final Manifest<Object> Object;
    private final Manifest<Object> AnyVal;
    private final Manifest<Nothing$> Nothing;
    private final Manifest<Null$> Null;

    static {
        new ClassManifestFactory$();
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

    public Manifest<Object> Any() {
        return this.Any;
    }

    public Manifest<Object> Object() {
        return this.Object;
    }

    public Manifest<Object> AnyVal() {
        return this.AnyVal;
    }

    public Manifest<Nothing$> Nothing() {
        return this.Nothing;
    }

    public Manifest<Null$> Null() {
        return this.Null;
    }

    public <T> ClassTag<T> fromClass(Class<T> clazz) {
        ClassTag<Object> classTag;
        Class<Byte> clazz2 = java.lang.Byte.TYPE;
        if (!(clazz2 != null ? !clazz2.equals(clazz) : clazz != null)) {
            classTag = this.Byte();
        } else {
            Class<Short> clazz3 = java.lang.Short.TYPE;
            if (!(clazz3 != null ? !clazz3.equals(clazz) : clazz != null)) {
                classTag = this.Short();
            } else {
                Class<Character> clazz4 = Character.TYPE;
                if (!(clazz4 != null ? !clazz4.equals(clazz) : clazz != null)) {
                    classTag = this.Char();
                } else {
                    Class<Integer> clazz5 = Integer.TYPE;
                    if (!(clazz5 != null ? !clazz5.equals(clazz) : clazz != null)) {
                        classTag = this.Int();
                    } else {
                        Class<Long> clazz6 = java.lang.Long.TYPE;
                        if (!(clazz6 != null ? !clazz6.equals(clazz) : clazz != null)) {
                            classTag = this.Long();
                        } else {
                            Class<Float> clazz7 = java.lang.Float.TYPE;
                            if (!(clazz7 != null ? !clazz7.equals(clazz) : clazz != null)) {
                                classTag = this.Float();
                            } else {
                                Class<Double> clazz8 = java.lang.Double.TYPE;
                                if (!(clazz8 != null ? !clazz8.equals(clazz) : clazz != null)) {
                                    classTag = this.Double();
                                } else {
                                    Class<Boolean> clazz9 = java.lang.Boolean.TYPE;
                                    if (!(clazz9 != null ? !clazz9.equals(clazz) : clazz != null)) {
                                        classTag = this.Boolean();
                                    } else {
                                        Class<Void> clazz10 = Void.TYPE;
                                        classTag = !(clazz10 != null ? !clazz10.equals(clazz) : clazz != null) ? this.Unit() : this.classType(clazz);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return classTag;
    }

    public <T> Manifest<T> singleType(Object value) {
        return package$.MODULE$.Manifest().singleType(value);
    }

    public <T> ClassTag<T> classType(Class<?> clazz) {
        return new ClassTypeManifest(None$.MODULE$, clazz, Nil$.MODULE$);
    }

    public <T> ClassTag<T> classType(Class<?> clazz, OptManifest<?> arg1, Seq<OptManifest<?>> args) {
        return new ClassTypeManifest(None$.MODULE$, clazz, args.toList().$colon$colon(arg1));
    }

    public <T> ClassTag<T> classType(OptManifest<?> prefix, Class<?> clazz, Seq<OptManifest<?>> args) {
        return new ClassTypeManifest(new Some(prefix), clazz, args.toList());
    }

    public <T> ClassTag<Object> arrayType(OptManifest<?> arg) {
        block4: {
            ClassTag<Object> classTag;
            block3: {
                block2: {
                    if (!NoManifest$.MODULE$.equals(arg)) break block2;
                    classTag = this.Object();
                    break block3;
                }
                if (!(arg instanceof ClassTag)) break block4;
                ClassTag classTag2 = (ClassTag)arg;
                classTag = classTag2.arrayManifest();
            }
            return classTag;
        }
        throw new MatchError(arg);
    }

    public <T> ClassTag<T> abstractType(OptManifest<?> prefix, String name, Class<?> clazz, Seq<OptManifest<?>> args) {
        return new ClassTag<T>(prefix, name, clazz, args){
            private final List<OptManifest<?>> typeArguments;
            private final OptManifest prefix$1;
            private final String name$1;
            private final Class clazz$1;

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

            public boolean canEqual(Object x) {
                return ClassTag$class.canEqual(this, x);
            }

            public boolean equals(Object x) {
                return ClassTag$class.equals(this, x);
            }

            public int hashCode() {
                return ClassTag$class.hashCode(this);
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

            public ClassTag<Object> arrayManifest() {
                return ClassManifestDeprecatedApis$class.arrayManifest(this);
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
                return this.clazz$1;
            }

            public List<OptManifest<?>> typeArguments() {
                return this.typeArguments;
            }

            public String toString() {
                return new StringBuilder().append((Object)this.prefix$1.toString()).append((Object)"#").append((Object)this.name$1).append((Object)this.argString()).toString();
            }
            {
                this.prefix$1 = prefix$1;
                this.name$1 = name$1;
                this.clazz$1 = clazz$1;
                ClassManifestDeprecatedApis$class.$init$(this);
                ClassTag$class.$init$(this);
                this.typeArguments = args$1.toList();
            }
        };
    }

    public <T> ClassTag<T> abstractType(OptManifest<?> prefix, String name, ClassTag<?> upperbound, Seq<OptManifest<?>> args) {
        return new ClassTag<T>(prefix, name, upperbound, args){
            private final List<OptManifest<?>> typeArguments;
            private final OptManifest prefix$2;
            private final String name$2;
            private final ClassTag upperbound$1;

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

            public boolean canEqual(Object x) {
                return ClassTag$class.canEqual(this, x);
            }

            public boolean equals(Object x) {
                return ClassTag$class.equals(this, x);
            }

            public int hashCode() {
                return ClassTag$class.hashCode(this);
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

            public ClassTag<Object> arrayManifest() {
                return ClassManifestDeprecatedApis$class.arrayManifest(this);
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
                return this.upperbound$1.runtimeClass();
            }

            public List<OptManifest<?>> typeArguments() {
                return this.typeArguments;
            }

            public String toString() {
                return new StringBuilder().append((Object)this.prefix$2.toString()).append((Object)"#").append((Object)this.name$2).append((Object)this.argString()).toString();
            }
            {
                this.prefix$2 = prefix$2;
                this.name$2 = name$2;
                this.upperbound$1 = upperbound$1;
                ClassManifestDeprecatedApis$class.$init$(this);
                ClassTag$class.$init$(this);
                this.typeArguments = args$2.toList();
            }
        };
    }

    private ClassManifestFactory$() {
        MODULE$ = this;
        this.Byte = ManifestFactory$.MODULE$.Byte();
        this.Short = ManifestFactory$.MODULE$.Short();
        this.Char = ManifestFactory$.MODULE$.Char();
        this.Int = ManifestFactory$.MODULE$.Int();
        this.Long = ManifestFactory$.MODULE$.Long();
        this.Float = ManifestFactory$.MODULE$.Float();
        this.Double = ManifestFactory$.MODULE$.Double();
        this.Boolean = ManifestFactory$.MODULE$.Boolean();
        this.Unit = ManifestFactory$.MODULE$.Unit();
        this.Any = ManifestFactory$.MODULE$.Any();
        this.Object = ManifestFactory$.MODULE$.Object();
        this.AnyVal = ManifestFactory$.MODULE$.AnyVal();
        this.Nothing = ManifestFactory$.MODULE$.Nothing();
        this.Null = ManifestFactory$.MODULE$.Null();
    }
}

