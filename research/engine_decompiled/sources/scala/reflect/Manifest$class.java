/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.Predef$;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.Manifest;
import scala.reflect.package$;
import scala.runtime.ScalaRunTime$;

public abstract class Manifest$class {
    public static List typeArguments(Manifest $this) {
        return Nil$.MODULE$;
    }

    public static Manifest arrayManifest(Manifest $this) {
        return package$.MODULE$.Manifest().classType($this.arrayClass($this.runtimeClass()), $this, Predef$.MODULE$.wrapRefArray((Object[])new Manifest[0]));
    }

    public static boolean canEqual(Manifest $this, Object that) {
        boolean bl = that instanceof Manifest;
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean equals(Manifest $this, Object that) {
        if (!(that instanceof Manifest)) return false;
        Manifest manifest = (Manifest)that;
        if (!manifest.canEqual($this)) return false;
        Class<?> clazz = $this.runtimeClass();
        Class<?> clazz2 = manifest.runtimeClass();
        if (clazz == null) {
            if (clazz2 != null) {
                return false;
            }
        } else if (!clazz.equals(clazz2)) return false;
        if (!$this.$less$colon$less(manifest)) return false;
        if (!manifest.$less$colon$less($this)) return false;
        return true;
    }

    public static int hashCode(Manifest $this) {
        return ScalaRunTime$.MODULE$.hash($this.runtimeClass());
    }

    public static void $init$(Manifest $this) {
    }
}

