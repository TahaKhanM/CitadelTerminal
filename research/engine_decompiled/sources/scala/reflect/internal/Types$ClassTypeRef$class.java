/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Types$ClassTypeRef$class {
    public static Types.Type baseType(Types.ClassTypeRef $this, Symbols.Symbol clazz) {
        Symbols.Symbol symbol = ((Types.TypeRef)((Object)$this)).sym();
        return !(symbol != null ? !symbol.equals(clazz) : clazz != null) ? (Types.Type)((Object)$this) : ((Types.TypeRef)((Object)$this)).transform(((Types.TypeRef)((Object)$this)).sym().info().baseType(clazz));
    }

    public static void $init$(Types.ClassTypeRef $this) {
    }
}

