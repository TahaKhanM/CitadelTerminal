/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Types;

public abstract class Types$AbstractTypeRef$class {
    public static Types.Type thisInfo(Types.AbstractTypeRef $this) {
        Types.Type symInfo = ((Types.TypeRef)((Object)$this)).sym().info();
        if ($this.scala$reflect$internal$Types$AbstractTypeRef$$thisInfoCache() == null || symInfo != $this.scala$reflect$internal$Types$AbstractTypeRef$$symInfoCache()) {
            Types.SubType subType;
            $this.scala$reflect$internal$Types$AbstractTypeRef$$symInfoCache_$eq(symInfo);
            Types.Type type = ((Types.TypeRef)((Object)$this)).transformInfo(symInfo);
            if (type instanceof Types.SubType && (subType = (Types.SubType)type).supertype() == $this) {
                throw new Types.RecoverableCyclicReference((SymbolTable)$this.scala$reflect$internal$Types$AbstractTypeRef$$$outer(), ((Types.TypeRef)((Object)$this)).sym());
            }
            $this.scala$reflect$internal$Types$AbstractTypeRef$$thisInfoCache_$eq(type);
        }
        return $this.scala$reflect$internal$Types$AbstractTypeRef$$thisInfoCache();
    }

    public static Types.TypeBounds bounds(Types.AbstractTypeRef $this) {
        return $this.thisInfo().bounds();
    }

    public static BaseTypeSeqs.BaseTypeSeq baseTypeSeqImpl(Types.AbstractTypeRef $this) {
        return ((Types.TypeRef)((Object)$this)).transform($this.bounds().hi()).baseTypeSeq().prepend((Types.Type)((Object)$this));
    }

    public static String kind(Types.AbstractTypeRef $this) {
        return "AbstractTypeRef";
    }

    public static void $init$(Types.AbstractTypeRef $this) {
        boolean bl = ((Types.TypeRef)((Object)$this)).sym().isAbstractType();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(((Types.TypeRef)((Object)$this)).sym()).toString());
        }
    }
}

