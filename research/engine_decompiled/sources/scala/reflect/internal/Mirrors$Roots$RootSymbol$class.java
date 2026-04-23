/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Mirrors$Roots$RootSymbol$class {
    public static final boolean isRootSymbol(Mirrors.Roots.RootSymbol $this) {
        return true;
    }

    public static Symbols.Symbol owner(Mirrors.Roots.RootSymbol $this) {
        return $this.scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer().scala$reflect$internal$Mirrors$Roots$$rootOwner;
    }

    public static Types.Type typeOfThis(Mirrors.Roots.RootSymbol $this) {
        return ((Symbols.Symbol)((Object)$this)).thisSym().tpe();
    }

    public static Mirrors.RootsBase mirror(Mirrors.Roots.RootSymbol $this) {
        return $this.scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer();
    }

    public static void $init$(Mirrors.Roots.RootSymbol $this) {
    }
}

