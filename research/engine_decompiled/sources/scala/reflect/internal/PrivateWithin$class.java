/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.reflect.Member;
import scala.Serializable;
import scala.collection.Seq;
import scala.reflect.internal.JavaAccFlags$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;

public abstract class PrivateWithin$class {
    public static void propagatePackageBoundary(SymbolTable $this, Class c, Seq syms) {
        $this.propagatePackageBoundary(JavaAccFlags$.MODULE$.apply(c), (Seq<Symbols.Symbol>)syms);
    }

    public static void propagatePackageBoundary(SymbolTable $this, Member m, Seq syms) {
        $this.propagatePackageBoundary(JavaAccFlags$.MODULE$.apply(m), (Seq<Symbols.Symbol>)syms);
    }

    public static void propagatePackageBoundary(SymbolTable $this, int jflags, Seq syms) {
        if (JavaAccFlags$.MODULE$.hasPackageAccessBoundary$extension(jflags)) {
            syms.foreach(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final Symbols.Symbol apply(Symbols.Symbol sym) {
                    return this.$outer.setPackageAccessBoundary(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }
    }

    public static Symbols.Symbol setPackageAccessBoundary(SymbolTable $this, Symbols.Symbol sym) {
        return sym.enclosingTopLevelClass() == $this.NoSymbol() ? sym : sym.setPrivateWithin(sym.enclosingTopLevelClass().owner());
    }

    public static void $init$(SymbolTable $this) {
    }
}

