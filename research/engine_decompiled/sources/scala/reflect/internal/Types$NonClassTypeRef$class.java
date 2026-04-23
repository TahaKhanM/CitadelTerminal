/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Types$NonClassTypeRef$class {
    public static Types.Type scala$reflect$internal$Types$$relativeInfo(Types.NonClassTypeRef $this) {
        if ($this.scala$reflect$internal$Types$NonClassTypeRef$$relativeInfoPeriod() != ((SymbolTable)$this.scala$reflect$internal$Types$NonClassTypeRef$$$outer()).currentPeriod()) {
            Types.Type memberInfo = ((Types.TypeRef)((Object)$this)).pre().memberInfo(((Types.TypeRef)((Object)$this)).sym());
            $this.scala$reflect$internal$Types$NonClassTypeRef$$relativeInfoCache_$eq(((Types.TypeRef)((Object)$this)).transformInfo(memberInfo));
            $this.scala$reflect$internal$Types$NonClassTypeRef$$relativeInfoPeriod_$eq(((SymbolTable)$this.scala$reflect$internal$Types$NonClassTypeRef$$$outer()).currentPeriod());
        }
        return $this.scala$reflect$internal$Types$NonClassTypeRef$$relativeInfoCache();
    }

    public static Types.Type baseType(Types.NonClassTypeRef $this, Symbols.Symbol clazz) {
        Symbols.Symbol symbol = ((Types.TypeRef)((Object)$this)).sym();
        return !(symbol != null ? !symbol.equals(clazz) : clazz != null) ? (Types.Type)((Object)$this) : $this.scala$reflect$internal$Types$NonClassTypeRef$$$outer().baseTypeOfNonClassTypeRef($this, clazz);
    }

    public static void $init$(Types.NonClassTypeRef $this) {
        boolean bl = ((Types.TypeRef)((Object)$this)).sym().isNonClassType();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(((Types.TypeRef)((Object)$this)).sym()).toString());
        }
        $this.scala$reflect$internal$Types$NonClassTypeRef$$relativeInfoPeriod_$eq(0);
    }
}

