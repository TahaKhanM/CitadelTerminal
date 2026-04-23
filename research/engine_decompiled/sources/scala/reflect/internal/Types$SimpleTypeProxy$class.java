/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Types$SimpleTypeProxy$class {
    public static boolean isTrivial(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().isTrivial();
    }

    public static boolean isHigherKinded(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().isHigherKinded();
    }

    public static Types.Type typeConstructor(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeConstructor();
    }

    public static boolean isError(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().isError();
    }

    public static boolean isErroneous(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().isErroneous();
    }

    public static int paramSectionCount(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().paramSectionCount();
    }

    public static List paramss(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().paramss();
    }

    public static List params(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().params();
    }

    public static List paramTypes(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().paramTypes();
    }

    public static Symbols.Symbol termSymbol(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().termSymbol();
    }

    public static Symbols.Symbol termSymbolDirect(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().termSymbolDirect();
    }

    public static List typeParams(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeParams();
    }

    public static Set boundSyms(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().boundSyms();
    }

    public static Symbols.Symbol typeSymbol(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeSymbol();
    }

    public static Symbols.Symbol typeSymbolDirect(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeSymbolDirect();
    }

    public static Types.Type widen(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().widen();
    }

    public static Types.Type typeOfThis(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeOfThis();
    }

    public static Types.TypeBounds bounds(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().bounds();
    }

    public static List parents(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().parents();
    }

    public static Types.Type prefix(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().prefix();
    }

    public static Scopes.Scope decls(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().decls();
    }

    public static Types.Type baseType(Types.SimpleTypeProxy $this, Symbols.Symbol clazz) {
        return ((Types.Type)((Object)$this)).underlying().baseType(clazz);
    }

    public static BaseTypeSeqs.BaseTypeSeq baseTypeSeq(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().baseTypeSeq();
    }

    public static int baseTypeSeqDepth(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().baseTypeSeqDepth();
    }

    public static List baseClasses(Types.SimpleTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().baseClasses();
    }

    public static void $init$(Types.SimpleTypeProxy $this) {
    }
}

