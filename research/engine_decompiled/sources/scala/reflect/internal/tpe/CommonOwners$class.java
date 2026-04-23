/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.collection.AbstractIterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.CommonOwners;

public abstract class CommonOwners$class {
    public static Symbols.Symbol commonOwner(SymbolTable $this, Types.Type t) {
        return $this.commonOwner(Nil$.MODULE$.$colon$colon(t));
    }

    public static Symbols.Symbol commonOwner(SymbolTable $this, List tps) {
        Symbols.Symbol symbol;
        if (tps.isEmpty()) {
            symbol = $this.NoSymbol();
        } else {
            $this.commonOwnerMap().clear();
            List list2 = tps;
            while (!((AbstractIterable)list2).isEmpty()) {
                Types.Type type = (Types.Type)((AbstractIterable)list2).head();
                $this.commonOwnerMap().traverse(type);
                list2 = (List)list2.tail();
            }
            symbol = $this.commonOwnerMap().result() != null ? $this.commonOwnerMap().result() : $this.NoSymbol();
        }
        return symbol;
    }

    public static CommonOwners.CommonOwnerMap commonOwnerMap(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj();
    }

    public static CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj(SymbolTable $this) {
        return new CommonOwners.CommonOwnerMap($this);
    }

    public static void $init$(SymbolTable $this) {
    }
}

