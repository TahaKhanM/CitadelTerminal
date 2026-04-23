/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.NameTransformer$;
import scala.reflect.internal.Names;
import scala.reflect.internal.StdNames;
import scala.reflect.internal.StdNames$nme$;
import scala.reflect.internal.StdNames$tpnme$;
import scala.reflect.internal.SymbolTable;

public abstract class StdNames$class {
    public static Names.TermName encode(SymbolTable $this, String str) {
        return $this.newTermNameCached(NameTransformer$.MODULE$.encode(str));
    }

    public static String compactifyName(SymbolTable $this, String orig) {
        return $this.scala$reflect$internal$StdNames$$compactify().apply(orig);
    }

    public static StdNames$tpnme$ typeNames(SymbolTable $this) {
        return $this.tpnme();
    }

    public static StdNames$nme$ termNames(SymbolTable $this) {
        return $this.nme();
    }

    public static StdNames.SymbolNames sn(SymbolTable $this) {
        return new StdNames.SymbolNames($this){};
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$StdNames$_setter_$javanme_$eq($this.nme().javaKeywords());
    }
}

