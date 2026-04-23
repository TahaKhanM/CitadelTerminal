/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

public abstract class Types$RewrappingTypeProxy$class {
    public static Types.Type maybeRewrap(Types.RewrappingTypeProxy $this, Types.Type newtp) {
        return newtp == ((Types.Type)((Object)$this)).underlying() ? (Types.Type)((Object)$this) : (!newtp.isWildcard() && !newtp.isHigherKinded() && newtp.$eq$colon$eq(((Types.Type)((Object)$this)).underlying()) ? (Types.Type)((Object)$this) : $this.rewrap(newtp));
    }

    public static Types.Type widen(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().widen());
    }

    public static Types.Type narrow(Types.RewrappingTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().narrow();
    }

    public static Types.Type deconst(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().deconst());
    }

    public static Types.Type resultType(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().resultType());
    }

    public static Types.Type resultType(Types.RewrappingTypeProxy $this, List actuals) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().resultType(actuals));
    }

    public static int paramSectionCount(Types.RewrappingTypeProxy $this) {
        return 0;
    }

    public static List paramss(Types.RewrappingTypeProxy $this) {
        return Nil$.MODULE$;
    }

    public static List params(Types.RewrappingTypeProxy $this) {
        return Nil$.MODULE$;
    }

    public static List paramTypes(Types.RewrappingTypeProxy $this) {
        return Nil$.MODULE$;
    }

    public static List typeArgs(Types.RewrappingTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().typeArgs();
    }

    public static Types.Type instantiateTypeParams(Types.RewrappingTypeProxy $this, List formals, List actuals) {
        return ((Types.Type)((Object)$this)).underlying().instantiateTypeParams(formals, actuals);
    }

    public static Types.Type skolemizeExistential(Types.RewrappingTypeProxy $this, Symbols.Symbol owner2, Object origin) {
        return ((Types.Type)((Object)$this)).underlying().skolemizeExistential(owner2, origin);
    }

    public static Types.Type normalize(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().normalize());
    }

    public static Types.Type etaExpand(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().etaExpand());
    }

    public static Types.Type dealias(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().dealias());
    }

    public static Types.Type cloneInfo(Types.RewrappingTypeProxy $this, Symbols.Symbol owner2) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().cloneInfo(owner2));
    }

    public static Types.Type atOwner(Types.RewrappingTypeProxy $this, Symbols.Symbol owner2) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().atOwner(owner2));
    }

    public static String prefixString(Types.RewrappingTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().prefixString();
    }

    public static boolean isComplete(Types.RewrappingTypeProxy $this) {
        return ((Types.Type)((Object)$this)).underlying().isComplete();
    }

    public static void complete(Types.RewrappingTypeProxy $this, Symbols.Symbol sym) {
        ((Types.Type)((Object)$this)).underlying().complete(sym);
    }

    public static void load(Types.RewrappingTypeProxy $this, Symbols.Symbol sym) {
        ((Types.Type)((Object)$this)).underlying().load(sym);
    }

    public static Types.Type withAnnotations(Types.RewrappingTypeProxy $this, List annots) {
        return $this.maybeRewrap((Types.Type)((Types.Type)((Object)$this)).underlying().withAnnotations(annots));
    }

    public static Types.Type withoutAnnotations(Types.RewrappingTypeProxy $this) {
        return $this.maybeRewrap(((Types.Type)((Object)$this)).underlying().withoutAnnotations());
    }

    public static void $init$(Types.RewrappingTypeProxy $this) {
    }
}

