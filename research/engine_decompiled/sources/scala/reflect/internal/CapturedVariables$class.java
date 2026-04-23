/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.collection.immutable.Map;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;

public abstract class CapturedVariables$class {
    public static void captureVariable(SymbolTable $this, Symbols.Symbol vble) {
        vble.setFlag(65536L);
    }

    public static Trees.Tree referenceCapturedVariable(SymbolTable $this, Symbols.Symbol vble) {
        return new Trees.ReferenceToBoxed($this, $this.Ident(vble));
    }

    public static Types.Type capturedVariableType(SymbolTable $this, Symbols.Symbol vble) {
        return $this.capturedVariableType(vble, $this.NoType(), false);
    }

    public static Types.Type capturedVariableType(SymbolTable $this, Symbols.Symbol vble, Types.Type tpe, boolean erasedTypes) {
        Types.Type type = tpe;
        Types$NoType$ types$NoType$ = $this.NoType();
        Types.Type tpe1 = !(type != null ? !type.equals(types$NoType$) : types$NoType$ != null) ? vble.tpe() : tpe;
        Symbols.Symbol symClass = tpe1.typeSymbol();
        return vble.hasAnnotation($this.definitions().VolatileAttr()) ? CapturedVariables$class.refType$1($this, $this.definitions().volatileRefClass(), $this.definitions().VolatileObjectRefClass(), tpe1, symClass, erasedTypes) : CapturedVariables$class.refType$1($this, $this.definitions().refClass(), $this.definitions().ObjectRefClass(), tpe1, symClass, erasedTypes);
    }

    public static Types.Type capturedVariableType$default$2(SymbolTable $this) {
        return $this.NoType();
    }

    public static boolean capturedVariableType$default$3(SymbolTable $this) {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final Types.Type refType$1(SymbolTable $this, Map valueRef, Symbols.Symbol objectRefClass, Types.Type tpe1$1, Symbols.Symbol symClass$1, boolean erasedTypes$1) {
        Types.Type type;
        if ($this.definitions().isPrimitiveValueClass(symClass$1)) {
            Symbols.Symbol symbol = symClass$1;
            Symbols.ClassSymbol classSymbol = $this.definitions().UnitClass();
            if (symbol == null ? classSymbol != null : !symbol.equals(classSymbol)) {
                type = ((Symbols.Symbol)valueRef.apply(symClass$1)).tpe();
                return type;
            }
        }
        if (erasedTypes$1) {
            type = objectRefClass.tpe();
            return type;
        }
        type = $this.appliedType(objectRefClass, Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{tpe1$1}));
        return type;
    }

    public static void $init$(SymbolTable $this) {
    }
}

