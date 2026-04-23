/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.Names;

public abstract class Names$class {
    public static Names.TermNameApi stringToTermName(Names $this, String s2) {
        return $this.TermName().apply(s2);
    }

    public static Names.TypeNameApi stringToTypeName(Names $this, String s2) {
        return $this.TypeName().apply(s2);
    }

    public static void $init$(Names $this) {
    }
}

