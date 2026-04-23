/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import java.lang.reflect.Array;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public abstract class ClassTag$class {
    public static ClassTag wrap(ClassTag $this) {
        return ClassTag$.MODULE$.apply($this.arrayClass($this.runtimeClass()));
    }

    public static Object newArray(ClassTag $this, int len) {
        Object object;
        Class<?> clazz = $this.runtimeClass();
        Class<Byte> clazz2 = Byte.TYPE;
        if (!(clazz2 != null ? !clazz2.equals(clazz) : clazz != null)) {
            object = new byte[len];
        } else {
            Class<Short> clazz3 = Short.TYPE;
            if (!(clazz3 != null ? !clazz3.equals(clazz) : clazz != null)) {
                object = new short[len];
            } else {
                Class<Character> clazz4 = Character.TYPE;
                if (!(clazz4 != null ? !clazz4.equals(clazz) : clazz != null)) {
                    object = new char[len];
                } else {
                    Class<Integer> clazz5 = Integer.TYPE;
                    if (!(clazz5 != null ? !clazz5.equals(clazz) : clazz != null)) {
                        object = new int[len];
                    } else {
                        Class<Long> clazz6 = Long.TYPE;
                        if (!(clazz6 != null ? !clazz6.equals(clazz) : clazz != null)) {
                            object = new long[len];
                        } else {
                            Class<Float> clazz7 = Float.TYPE;
                            if (!(clazz7 != null ? !clazz7.equals(clazz) : clazz != null)) {
                                object = new float[len];
                            } else {
                                Class<Double> clazz8 = Double.TYPE;
                                if (!(clazz8 != null ? !clazz8.equals(clazz) : clazz != null)) {
                                    object = new double[len];
                                } else {
                                    Class<Boolean> clazz9 = Boolean.TYPE;
                                    if (!(clazz9 != null ? !clazz9.equals(clazz) : clazz != null)) {
                                        object = new boolean[len];
                                    } else {
                                        Class<Void> clazz10 = Void.TYPE;
                                        object = !(clazz10 != null ? !clazz10.equals(clazz) : clazz != null) ? (Object)new BoxedUnit[len] : (Object)Array.newInstance($this.runtimeClass(), len);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return object;
    }

    public static Option unapply(ClassTag $this, Object x) {
        return x != null && ($this.runtimeClass().isInstance(x) || x instanceof Byte && $this.runtimeClass().isAssignableFrom(Byte.TYPE) || x instanceof Short && $this.runtimeClass().isAssignableFrom(Short.TYPE) || x instanceof Character && $this.runtimeClass().isAssignableFrom(Character.TYPE) || x instanceof Integer && $this.runtimeClass().isAssignableFrom(Integer.TYPE) || x instanceof Long && $this.runtimeClass().isAssignableFrom(Long.TYPE) || x instanceof Float && $this.runtimeClass().isAssignableFrom(Float.TYPE) || x instanceof Double && $this.runtimeClass().isAssignableFrom(Double.TYPE) || x instanceof Boolean && $this.runtimeClass().isAssignableFrom(Boolean.TYPE) || x instanceof BoxedUnit && $this.runtimeClass().isAssignableFrom(BoxedUnit.TYPE)) ? new Some<Object>(x) : None$.MODULE$;
    }

    public static Option unapply(ClassTag $this, byte x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToByte(x), Byte.TYPE);
    }

    public static Option unapply(ClassTag $this, short x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToShort(x), Short.TYPE);
    }

    public static Option unapply(ClassTag $this, char x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToCharacter(x), Character.TYPE);
    }

    public static Option unapply(ClassTag $this, int x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToInteger(x), Integer.TYPE);
    }

    public static Option unapply(ClassTag $this, long x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToLong(x), Long.TYPE);
    }

    public static Option unapply(ClassTag $this, float x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToFloat(x), Float.TYPE);
    }

    public static Option unapply(ClassTag $this, double x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToDouble(x), Double.TYPE);
    }

    public static Option unapply(ClassTag $this, boolean x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, BoxesRunTime.boxToBoolean(x), Boolean.TYPE);
    }

    public static Option unapply(ClassTag $this, BoxedUnit x) {
        return ClassTag$class.scala$reflect$ClassTag$$unapplyImpl($this, x, BoxedUnit.TYPE);
    }

    public static Option scala$reflect$ClassTag$$unapplyImpl(ClassTag $this, Object x, Class primitiveCls) {
        return $this.runtimeClass().isInstance(x) || $this.runtimeClass().isAssignableFrom(primitiveCls) ? new Some<Object>(x) : None$.MODULE$;
    }

    public static boolean canEqual(ClassTag $this, Object x) {
        return x instanceof ClassTag;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean equals(ClassTag $this, Object x) {
        if (!(x instanceof ClassTag)) return false;
        Class<?> clazz = $this.runtimeClass();
        Class<?> clazz2 = ((ClassTag)x).runtimeClass();
        if (clazz != null) {
            if (!clazz.equals(clazz2)) return false;
            return true;
        }
        if (clazz2 == null) return true;
        return false;
    }

    public static int hashCode(ClassTag $this) {
        return ScalaRunTime$.MODULE$.hash($this.runtimeClass());
    }

    public static String toString(ClassTag $this) {
        return ClassTag$class.prettyprint$1($this, $this.runtimeClass());
    }

    private static final String prettyprint$1(ClassTag $this, Class clazz) {
        return clazz.isArray() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Array[", "]"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{ClassTag$class.prettyprint$1($this, ScalaRunTime$.MODULE$.arrayElementClass(clazz))})) : clazz.getName();
    }

    public static void $init$(ClassTag $this) {
    }
}

