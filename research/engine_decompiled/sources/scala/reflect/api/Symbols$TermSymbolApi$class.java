/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function1;
import scala.Serializable;
import scala.reflect.api.Symbols;

public abstract class Symbols$TermSymbolApi$class {
    public static final boolean isTerm(Symbols.TermSymbolApi $this) {
        return true;
    }

    public static final Symbols.TermSymbolApi asTerm(Symbols.TermSymbolApi $this) {
        return $this;
    }

    public static boolean isOverloadedMethod(Symbols.TermSymbolApi $this) {
        return $this.alternatives().exists((Function1<Symbols.SymbolApi, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Symbols.SymbolApi x$1) {
                return x$1.isMethod();
            }
        }));
    }

    public static void $init$(Symbols.TermSymbolApi $this) {
    }
}

