/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Symbols$ImplClassSymbol$class {
    public static Symbols.Symbol sourceModule(Symbols.ImplClassSymbol $this) {
        return ((Symbols.ClassSymbol)((Object)$this)).companionModule();
    }

    public static Types.Type typeOfThis(Symbols.ImplClassSymbol $this) {
        return ((Symbols.ClassSymbol)((Object)$this)).thisSym().tpe();
    }

    public static void $init$(Symbols.ImplClassSymbol $this) {
    }
}

