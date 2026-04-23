/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Predef$;
import scala.ScalaReflectionException;
import scala.StringContext;
import scala.reflect.api.Symbols;

public abstract class Symbols$SymbolApi$class {
    public static boolean isType(Symbols.SymbolApi $this) {
        return false;
    }

    public static Symbols.TypeSymbolApi asType(Symbols.SymbolApi $this) {
        throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a type"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this})));
    }

    public static boolean isTerm(Symbols.SymbolApi $this) {
        return false;
    }

    public static Symbols.TermSymbolApi asTerm(Symbols.SymbolApi $this) {
        throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a term"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this})));
    }

    public static boolean isMethod(Symbols.SymbolApi $this) {
        return false;
    }

    public static Symbols.MethodSymbolApi asMethod(Symbols.SymbolApi $this) {
        String msg = $this.isOverloadedMethod() ? Symbols$SymbolApi$class.overloadedMsg$1($this) : Symbols$SymbolApi$class.vanillaMsg$1($this);
        throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this, msg})));
    }

    public static boolean isOverloadedMethod(Symbols.SymbolApi $this) {
        return false;
    }

    public static boolean isModule(Symbols.SymbolApi $this) {
        return false;
    }

    public static Symbols.ModuleSymbolApi asModule(Symbols.SymbolApi $this) {
        throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a module"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this})));
    }

    public static boolean isClass(Symbols.SymbolApi $this) {
        return false;
    }

    public static boolean isModuleClass(Symbols.SymbolApi $this) {
        return false;
    }

    public static Symbols.ClassSymbolApi asClass(Symbols.SymbolApi $this) {
        throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a class"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this})));
    }

    private static final String overloadedMsg$1(Symbols.SymbolApi $this) {
        return "encapsulates multiple overloaded alternatives and cannot be treated as a method. Consider invoking `<offending symbol>.asTerm.alternatives` and manually picking the required method";
    }

    private static final String vanillaMsg$1(Symbols.SymbolApi $this) {
        return "is not a method";
    }

    public static void $init$(Symbols.SymbolApi $this) {
    }
}

