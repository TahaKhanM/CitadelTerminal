/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import java.lang.reflect.Array;
import scala.Option$;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.AnyValManifest;
import scala.reflect.ClassTag;
import scala.reflect.NoManifest$;
import scala.reflect.OptManifest;
import scala.reflect.package$;

public abstract class ClassManifestDeprecatedApis$class {
    public static Class erasure(ClassTag $this) {
        return $this.runtimeClass();
    }

    private static boolean subtype(ClassTag $this, Class sub, Class sup) {
        return ClassManifestDeprecatedApis$class.loop$1($this, (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Class[]{sub})), (Set)Predef$.MODULE$.Set().apply(Nil$.MODULE$), sup);
    }

    private static boolean subargs(ClassTag $this, List args1, List args2) {
        return args1.corresponds(args2, new Serializable($this){
            public static final long serialVersionUID = 0L;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean apply(OptManifest<?> x0$1, OptManifest<?> x1$1) {
                Tuple2<OptManifest<?>, OptManifest<?>> tuple2 = new Tuple2<OptManifest<?>, OptManifest<?>>(x0$1, x1$1);
                if (tuple2._1() instanceof ClassTag) {
                    ClassTag classTag = (ClassTag)tuple2._1();
                    if (tuple2._2() instanceof ClassTag) {
                        ClassTag classTag2 = (ClassTag)tuple2._2();
                        return classTag.$less$colon$less(classTag2);
                    }
                }
                if (tuple2._1() != NoManifest$.MODULE$) return false;
                if (tuple2._2() != NoManifest$.MODULE$) return false;
                return true;
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean $less$colon$less(ClassTag $this, ClassTag that) {
        if (ClassManifestDeprecatedApis$class.cannotMatch$1($this, that)) return false;
        Class<?> clazz = $this.runtimeClass();
        Class<?> clazz2 = that.runtimeClass();
        if (clazz != null ? !clazz.equals(clazz2) : clazz2 != null) {
            if (!that.typeArguments().isEmpty()) return false;
            if (!ClassManifestDeprecatedApis$class.subtype($this, $this.runtimeClass(), that.runtimeClass())) return false;
            return true;
        }
        boolean bl = ClassManifestDeprecatedApis$class.subargs($this, $this.typeArguments(), that.typeArguments());
        if (!bl) return false;
        return true;
    }

    public static boolean $greater$colon$greater(ClassTag $this, ClassTag that) {
        return that.$less$colon$less($this);
    }

    public static boolean canEqual(ClassTag $this, Object other) {
        boolean bl = other instanceof ClassTag;
        return bl;
    }

    public static Class arrayClass(ClassTag $this, Class tp) {
        return Array.newInstance(tp, 0).getClass();
    }

    public static ClassTag arrayManifest(ClassTag $this) {
        return package$.MODULE$.ClassManifest().classType($this.arrayClass($this.runtimeClass()), $this, Predef$.MODULE$.wrapRefArray((Object[])new OptManifest[0]));
    }

    public static Object newArray(ClassTag $this, int len) {
        return Array.newInstance($this.runtimeClass(), len);
    }

    public static Object[] newArray2(ClassTag $this, int len) {
        return (Object[])Array.newInstance($this.arrayClass($this.runtimeClass()), len);
    }

    public static Object[][] newArray3(ClassTag $this, int len) {
        return (Object[][])Array.newInstance($this.arrayClass($this.arrayClass($this.runtimeClass())), len);
    }

    public static Object[][][] newArray4(ClassTag $this, int len) {
        return (Object[][][])Array.newInstance($this.arrayClass($this.arrayClass($this.arrayClass($this.runtimeClass()))), len);
    }

    public static Object[][][][] newArray5(ClassTag $this, int len) {
        return (Object[][][][])Array.newInstance($this.arrayClass($this.arrayClass($this.arrayClass($this.arrayClass($this.runtimeClass())))), len);
    }

    public static WrappedArray newWrappedArray(ClassTag $this, int len) {
        return new WrappedArray.ofRef<Object>((Object[])$this.newArray(len));
    }

    public static ArrayBuilder newArrayBuilder(ClassTag $this) {
        return new ArrayBuilder.ofRef($this);
    }

    public static List typeArguments(ClassTag $this) {
        return Nil$.MODULE$;
    }

    public static String argString(ClassTag $this) {
        return $this.typeArguments().nonEmpty() ? $this.typeArguments().mkString("[", ", ", "]") : ($this.runtimeClass().isArray() ? new StringBuilder().append((Object)"[").append(package$.MODULE$.ClassManifest().fromClass($this.runtimeClass().getComponentType())).append((Object)"]").toString() : "");
    }

    private static final boolean loop$1(ClassTag $this, Set left, Set seen, Class sup$1) {
        boolean bl;
        block2: {
            while (left.nonEmpty()) {
                Class next2 = (Class)left.head();
                Set supers = (Set)Predef$.MODULE$.refArrayOps((Object[])next2.getInterfaces()).toSet().$plus$plus(Option$.MODULE$.option2Iterable(Option$.MODULE$.apply(next2.getSuperclass())));
                if (supers.apply(sup$1)) {
                    bl = true;
                    break block2;
                }
                Set xs = (Set)left.$plus$plus(supers).filterNot(seen);
                seen = (Set)seen.$plus(next2);
                left = (Set)xs.$minus(next2);
            }
            bl = false;
        }
        return bl;
    }

    private static final boolean cannotMatch$1(ClassTag $this, ClassTag that$1) {
        return that$1 instanceof AnyValManifest || that$1 == package$.MODULE$.Manifest().AnyVal() || that$1 == package$.MODULE$.Manifest().Nothing() || that$1 == package$.MODULE$.Manifest().Null();
    }

    public static void $init$(ClassTag $this) {
    }
}

