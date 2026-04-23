/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;

public abstract class Universe$MacroCompatApi$class {
    public static Universe.MacroCompatApi.MacroCompatibleSymbol MacroCompatibleSymbol(Universe.MacroCompatApi $this, Symbols.SymbolApi symbol) {
        return new Universe.MacroCompatApi.MacroCompatibleSymbol($this, symbol);
    }

    public static Universe.MacroCompatApi.MacroCompatibleTree MacroCompatibleTree(Universe.MacroCompatApi $this, Trees.TreeApi tree) {
        return new Universe.MacroCompatApi.MacroCompatibleTree($this, tree);
    }

    public static Universe.MacroCompatApi.CompatibleTypeTree CompatibleTypeTree(Universe.MacroCompatApi $this, Trees.TypeTreeApi tt) {
        return new Universe.MacroCompatApi.CompatibleTypeTree($this, tt);
    }

    public static void captureVariable(Universe.MacroCompatApi $this, Symbols.SymbolApi vble) {
        ((Universe.MacroInternalApi)$this.scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).captureVariable(vble);
    }

    public static Trees.TreeApi referenceCapturedVariable(Universe.MacroCompatApi $this, Symbols.SymbolApi vble) {
        return ((Universe.MacroInternalApi)$this.scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).referenceCapturedVariable(vble);
    }

    public static Types.TypeApi capturedVariableType(Universe.MacroCompatApi $this, Symbols.SymbolApi vble) {
        return ((Universe.MacroInternalApi)$this.scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).capturedVariableType(vble);
    }

    public static void $init$(Universe.MacroCompatApi $this) {
    }
}

