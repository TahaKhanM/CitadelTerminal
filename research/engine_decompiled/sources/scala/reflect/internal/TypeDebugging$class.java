/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Serializable;
import scala.collection.immutable.List$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class TypeDebugging$class {
    public static String paramString(SymbolTable $this, Types.Type tp) {
        return $this.typeDebug().str().parentheses(tp.params().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(Symbols.Symbol x$3) {
                return x$3.defString();
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static String typeParamsString(SymbolTable $this, Types.Type tp) {
        return $this.typeDebug().str().brackets(tp.typeParams().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(Symbols.Symbol x$4) {
                return x$4.defString();
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static String debugString(SymbolTable $this, Types.Type tp) {
        return $this.typeDebug().debugString(tp);
    }

    public static void $init$(SymbolTable $this) {
    }
}

