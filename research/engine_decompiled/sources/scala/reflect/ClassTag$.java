/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.OptManifest;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

public final class ClassTag$
implements Serializable {
    public static final ClassTag$ MODULE$;
    private final Class<Object> ObjectTYPE;
    private final Class<Nothing$> NothingTYPE;
    private final Class<Null$> NullTYPE;
    private final ClassTag<Object> Byte;
    private final ClassTag<Object> Short;
    private final ClassTag<Object> Char;
    private final ClassTag<Object> Int;
    private final ClassTag<Object> Long;
    private final ClassTag<Object> Float;
    private final ClassTag<Object> Double;
    private final ClassTag<Object> Boolean;
    private final ClassTag<BoxedUnit> Unit;
    private final ClassTag<Object> Any;
    private final ClassTag<Object> Object;
    private final ClassTag<Object> AnyVal;
    private final ClassTag<Object> AnyRef;
    private final ClassTag<Nothing$> Nothing;
    private final ClassTag<Null$> Null;

    static {
        new ClassTag$();
    }

    private Class<Object> ObjectTYPE() {
        return this.ObjectTYPE;
    }

    private Class<Nothing$> NothingTYPE() {
        return this.NothingTYPE;
    }

    private Class<Null$> NullTYPE() {
        return this.NullTYPE;
    }

    public ClassTag<Object> Byte() {
        return this.Byte;
    }

    public ClassTag<Object> Short() {
        return this.Short;
    }

    public ClassTag<Object> Char() {
        return this.Char;
    }

    public ClassTag<Object> Int() {
        return this.Int;
    }

    public ClassTag<Object> Long() {
        return this.Long;
    }

    public ClassTag<Object> Float() {
        return this.Float;
    }

    public ClassTag<Object> Double() {
        return this.Double;
    }

    public ClassTag<Object> Boolean() {
        return this.Boolean;
    }

    public ClassTag<BoxedUnit> Unit() {
        return this.Unit;
    }

    public ClassTag<Object> Any() {
        return this.Any;
    }

    public ClassTag<Object> Object() {
        return this.Object;
    }

    public ClassTag<Object> AnyVal() {
        return this.AnyVal;
    }

    public ClassTag<Object> AnyRef() {
        return this.AnyRef;
    }

    public ClassTag<Nothing$> Nothing() {
        return this.Nothing;
    }

    public ClassTag<Null$> Null() {
        return this.Null;
    }

    public <T> ClassTag<T> apply(Class<?> runtimeClass1) {
        ClassTag<Object> classTag;
        Class<Byte> clazz = java.lang.Byte.TYPE;
        if (!(clazz != null ? !clazz.equals(runtimeClass1) : runtimeClass1 != null)) {
            classTag = this.Byte();
        } else {
            Class<Short> clazz2 = java.lang.Short.TYPE;
            if (!(clazz2 != null ? !clazz2.equals(runtimeClass1) : runtimeClass1 != null)) {
                classTag = this.Short();
            } else {
                Class<Character> clazz3 = Character.TYPE;
                if (!(clazz3 != null ? !clazz3.equals(runtimeClass1) : runtimeClass1 != null)) {
                    classTag = this.Char();
                } else {
                    Class<Integer> clazz4 = Integer.TYPE;
                    if (!(clazz4 != null ? !clazz4.equals(runtimeClass1) : runtimeClass1 != null)) {
                        classTag = this.Int();
                    } else {
                        Class<Long> clazz5 = java.lang.Long.TYPE;
                        if (!(clazz5 != null ? !clazz5.equals(runtimeClass1) : runtimeClass1 != null)) {
                            classTag = this.Long();
                        } else {
                            Class<Float> clazz6 = java.lang.Float.TYPE;
                            if (!(clazz6 != null ? !clazz6.equals(runtimeClass1) : runtimeClass1 != null)) {
                                classTag = this.Float();
                            } else {
                                Class<Double> clazz7 = java.lang.Double.TYPE;
                                if (!(clazz7 != null ? !clazz7.equals(runtimeClass1) : runtimeClass1 != null)) {
                                    classTag = this.Double();
                                } else {
                                    Class<Boolean> clazz8 = java.lang.Boolean.TYPE;
                                    if (!(clazz8 != null ? !clazz8.equals(runtimeClass1) : runtimeClass1 != null)) {
                                        classTag = this.Boolean();
                                    } else {
                                        Class<Void> clazz9 = Void.TYPE;
                                        if (!(clazz9 != null ? !clazz9.equals(runtimeClass1) : runtimeClass1 != null)) {
                                            classTag = this.Unit();
                                        } else {
                                            Class<Object> clazz10 = this.ObjectTYPE();
                                            if (!(clazz10 != null ? !clazz10.equals(runtimeClass1) : runtimeClass1 != null)) {
                                                classTag = this.Object();
                                            } else {
                                                Class<Nothing$> clazz11 = this.NothingTYPE();
                                                if (!(clazz11 != null ? !clazz11.equals(runtimeClass1) : runtimeClass1 != null)) {
                                                    classTag = this.Nothing();
                                                } else {
                                                    Class<Null$> clazz12 = this.NullTYPE();
                                                    classTag = !(clazz12 != null ? !clazz12.equals(runtimeClass1) : runtimeClass1 != null) ? this.Null() : new ClassTag<T>(runtimeClass1){
                                                        private final Class runtimeClass1$1;

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

                                                        public String toString() {
                                                            return ClassTag$class.toString(this);
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

                                                        public List<OptManifest<?>> typeArguments() {
                                                            return ClassManifestDeprecatedApis$class.typeArguments(this);
                                                        }

                                                        public String argString() {
                                                            return ClassManifestDeprecatedApis$class.argString(this);
                                                        }

                                                        public Class<?> runtimeClass() {
                                                            return this.runtimeClass1$1;
                                                        }
                                                        {
                                                            this.runtimeClass1$1 = runtimeClass1$1;
                                                            ClassManifestDeprecatedApis$class.$init$(this);
                                                            ClassTag$class.$init$(this);
                                                        }
                                                    };
                                                }
                                            }
                                        }
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

    public <T> Option<Class<?>> unapply(ClassTag<T> ctag) {
        return new Some(ctag.runtimeClass());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ClassTag$() {
        MODULE$ = this;
        this.ObjectTYPE = Object.class;
        this.NothingTYPE = Nothing$.class;
        this.NullTYPE = Null$.class;
        this.Byte = package$.MODULE$.Manifest().Byte();
        this.Short = package$.MODULE$.Manifest().Short();
        this.Char = package$.MODULE$.Manifest().Char();
        this.Int = package$.MODULE$.Manifest().Int();
        this.Long = package$.MODULE$.Manifest().Long();
        this.Float = package$.MODULE$.Manifest().Float();
        this.Double = package$.MODULE$.Manifest().Double();
        this.Boolean = package$.MODULE$.Manifest().Boolean();
        this.Unit = package$.MODULE$.Manifest().Unit();
        this.Any = package$.MODULE$.Manifest().Any();
        this.Object = package$.MODULE$.Manifest().Object();
        this.AnyVal = package$.MODULE$.Manifest().AnyVal();
        this.AnyRef = package$.MODULE$.Manifest().AnyRef();
        this.Nothing = package$.MODULE$.Manifest().Nothing();
        this.Null = package$.MODULE$.Manifest().Null();
    }
}

