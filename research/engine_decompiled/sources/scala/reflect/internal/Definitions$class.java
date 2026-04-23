/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Definitions$class {
    public static Symbols.ClassSymbol scala$reflect$internal$Definitions$$enterNewClass(SymbolTable $this, Symbols.Symbol owner2, Names.TypeName name, List parents2, long flags) {
        Symbols.ClassSymbol clazz = owner2.newClassSymbol(name, $this.NoPosition(), flags);
        return (Symbols.ClassSymbol)clazz.setInfoAndEnter(new Types.ClassInfoType($this, parents2, $this.newScope(), clazz)).markAllCompleted();
    }

    public static long scala$reflect$internal$Definitions$$enterNewClass$default$4(SymbolTable $this) {
        return 0L;
    }

    private static Symbols.MethodSymbol newMethod(SymbolTable $this, Symbols.Symbol owner2, Names.TermName name, List formals, Types.Type restpe, long flags) {
        Symbols.MethodSymbol msym = owner2.newMethod((Names.TermName)name.encode(), $this.NoPosition(), flags);
        List<Symbols.Symbol> params2 = msym.newSyntheticValueParams(formals);
        Types.MethodType info2 = owner2.isJavaDefined() ? $this.JavaMethodType(params2, restpe) : new Types.MethodType($this, params2, restpe);
        return (Symbols.MethodSymbol)msym.setInfo(info2).markAllCompleted();
    }

    public static Symbols.MethodSymbol scala$reflect$internal$Definitions$$enterNewMethod(SymbolTable $this, Symbols.Symbol owner2, Names.TermName name, List formals, Types.Type restpe, long flags) {
        return owner2.info().decls().enter(Definitions$class.newMethod($this, owner2, name, formals, restpe, flags));
    }

    public static long scala$reflect$internal$Definitions$$enterNewMethod$default$5(SymbolTable $this) {
        return 0L;
    }

    public static void $init$(SymbolTable $this) {
    }
}

