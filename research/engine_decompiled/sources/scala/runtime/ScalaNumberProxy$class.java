/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.runtime.ScalaNumberProxy;

public abstract class ScalaNumberProxy$class {
    public static Object underlying(ScalaNumberProxy $this) {
        return $this.self();
    }

    public static double doubleValue(ScalaNumberProxy $this) {
        return $this.num().toDouble($this.self());
    }

    public static float floatValue(ScalaNumberProxy $this) {
        return $this.num().toFloat($this.self());
    }

    public static long longValue(ScalaNumberProxy $this) {
        return $this.num().toLong($this.self());
    }

    public static int intValue(ScalaNumberProxy $this) {
        return $this.num().toInt($this.self());
    }

    public static byte byteValue(ScalaNumberProxy $this) {
        return (byte)$this.intValue();
    }

    public static short shortValue(ScalaNumberProxy $this) {
        return (short)$this.intValue();
    }

    public static Object min(ScalaNumberProxy $this, Object that) {
        return $this.num().min($this.self(), that);
    }

    public static Object max(ScalaNumberProxy $this, Object that) {
        return $this.num().max($this.self(), that);
    }

    public static Object abs(ScalaNumberProxy $this) {
        return $this.num().abs($this.self());
    }

    public static int signum(ScalaNumberProxy $this) {
        return $this.num().signum($this.self());
    }

    public static void $init$(ScalaNumberProxy $this) {
    }
}

