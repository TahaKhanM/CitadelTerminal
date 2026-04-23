/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.collection.mutable.StringBuilder;
import scala.util.control.Exception;

public abstract class Exception$Described$class {
    public static String desc(Exception.Described $this) {
        return $this.scala$util$control$Exception$Described$$_desc();
    }

    public static Exception.Described withDesc(Exception.Described $this, String s2) {
        $this.scala$util$control$Exception$Described$$_desc_$eq(s2);
        return $this;
    }

    public static String toString(Exception.Described $this) {
        return new StringBuilder().append((Object)$this.name()).append((Object)"(").append((Object)$this.desc()).append((Object)")").toString();
    }

    public static void $init$(Exception.Described $this) {
        $this.scala$util$control$Exception$Described$$_desc_$eq("");
    }
}

